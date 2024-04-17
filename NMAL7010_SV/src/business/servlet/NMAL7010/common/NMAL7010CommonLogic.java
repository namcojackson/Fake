/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010.common;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ACC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ADD_CUST_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ADD_PRC_LIST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ADD_SUB_PRC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ADD_TRX_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_APL;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_APR;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_RST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DEL_CUST_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DEL_PRC_LIST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DEL_SUB_PRC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DEL_TRX_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DS_ACCT_CUST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_GEN;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_ITEM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_LEASE_RATE_PRC_LIST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_MASS_UPLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_MDSE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_MDSE_TP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_MOVE_MASS_UPLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_MTR_LB;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_LIST_BAND;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_CUST_AUDC_01;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_CUST_AUDC_02;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_CUST_AUDC_03;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_FORMULA;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_MODEL;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_MTR_PKG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_QLFY_VAL;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_TRX_AUDC_01;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_TRX_AUDC_02;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PROD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_SUB_PRC_LIST;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_SUPPLY_PLAN;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_UPLOAD_CUST_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PA;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PB;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PF;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PH;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PI;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PJ;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.DIGIT_OFF;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_EQUIP_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_EQUIP_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_LEASE_RATE_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_LEASE_RATE_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SPLY_PGM_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SPLY_PGM_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SVC_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SVC_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYYPE_SVC_TIER_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_SVC_TIER_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_TRADE_IN_DOWNLOAD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.EXCEL_COLUMNTYPE_TRADE_IN_DWLDTEMPLATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8054E;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8190E;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.SCRN_ID_00;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_CUST_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_TRX_AUDC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.UPDATE_AUTHORITY;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.ZZM9000E;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_DOWNLOAD_AS_TEMPLATE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7010.NMAL7010BMsg;
import business.servlet.NMAL7010.constant.NMAL7010Constant;
import business.servlet.NMAL7010.constant.NMAL7010Constant.NMAL7010ConstantIf;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CUST_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TRX_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelColumnStyle;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2016/04/22   SRA             E.Inada         Update          QC#7080
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/03   Fujitsu         Y.Kanefusa      Update          S21_NA#14784
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/11/14   Fujitsu         A.Kosai         Update          QC#20203(Sol#257)
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/08/22   Fujitsu         T.Noguchi       Update          QC#26664
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public class NMAL7010CommonLogic implements NMAL7010ConstantIf {

    // Add Start 2017/02/23 QC#16011
    /**
     * updateAuthority
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }

    /**
     * setScrnCtrl
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setScrnCtrl(EZDCommonHandler handler, NMAL7010BMsg scrnMsg, S21UserProfileService userProfileService) {

        int amtNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
        int rateNum = 5;
        int aotNum = 0;
        boolean protectBtnFlg = updateAuthority(userProfileService);
        boolean protectFldFlg = !protectBtnFlg;

        // Header
        scrnMsg.prcCatgNm_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcListDplyNm_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcCatgDescTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcListTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.custRgtnReqFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.actvFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.effFromDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.effThruDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcSlsVisTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcListGrpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcContrNum_LK.setInputProtected(protectFldFlg);
        scrnMsg.prcContrNum_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcContrNm_H1.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_SUB_PRC, protectBtnFlg);
        handler.setButtonEnabled(BTN_DEL_SUB_PRC, protectBtnFlg);

        handler.setButtonEnabled(BTN_UPLOAD_CUST_AUDC, protectBtnFlg);
        handler.setButtonEnabled(BTN_ADD_CUST_AUDC, protectBtnFlg);
        handler.setButtonEnabled(BTN_DEL_CUST_AUDC, protectBtnFlg);
        for (int i = 0; i < scrnMsg.W.length(); i++) {
            scrnMsg.W.no(i).xxChkBox_SW.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_SUB_PRC_LIST, protectBtnFlg);
            scrnMsg.W.no(i).prcCatgNm_SW.setInputProtected(true);
            scrnMsg.W.no(i).effFromDt_SW.setInputProtected(protectFldFlg);
            scrnMsg.W.no(i).effThruDt_SW.setInputProtected(protectFldFlg);
            scrnMsg.W.no(i).subPrcPrtyNum_SW.setInputProtected(protectFldFlg);
        }

        handler.setButtonEnabled(BTN_ADD_TRX_AUDC, protectBtnFlg);
        handler.setButtonEnabled(BTN_DEL_TRX_AUDC, protectBtnFlg);
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            scrnMsg.X.no(i).xxChkBox_CX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).prcCustAudcCatgCd_X1.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X1.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).pubFlg_CX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X1.setInputProtected(true);
            scrnMsg.X.no(i).prcCustAudcCatgCd_X2.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X2.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X2.setInputProtected(true);
            scrnMsg.X.no(i).prcCustAudcCatgCd_X3.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X3.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X3.setInputProtected(true);
            scrnMsg.X.no(i).reqFlg_CX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).effFromDt_CX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).effThruDt_CX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).defPrcListFlg_CX.setInputProtected(protectFldFlg);
        }

        for (int i = 0; i < scrnMsg.Y.length(); i++) {
            scrnMsg.Y.no(i).xxChkBox_TY.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).xxScrItem30Txt_Y1.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).xxRecNmTxt_Y1.setInputProtected(true);
            scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y2.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).xxScrItem30Txt_Y2.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).xxRecNmTxt_Y2.setInputProtected(true);
            scrnMsg.Y.no(i).effFromDt_TY.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).effThruDt_TY.setInputProtected(protectFldFlg);
        }

        scrnMsg.notToExcdFlg_GA.setInputProtected(protectFldFlg);
        scrnMsg.custBidNum_GA.setInputProtected(protectFldFlg);
        scrnMsg.allwPrcDevnFlg_GA.setInputProtected(protectFldFlg);

        scrnMsg.prcLeaseTpCd_CA.setInputProtected(protectFldFlg);
        scrnMsg.leaseRtrnFeeInclFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.addlShpgFeeInclFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.reloInclFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.hardDriveEraseInclFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.hardDriveRmvInclFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.notExcdContrPrcFlg_CA.setInputProtected(protectFldFlg);
        scrnMsg.spclCsmpExclArNm_CA.setInputProtected(protectFldFlg);
        scrnMsg.somEligTrxTpCd_CA.setInputProtected(protectFldFlg);
        scrnMsg.prcCatgNm_CA.setInputProtected(protectFldFlg);
        handler.setButtonEnabled(BTN_LEASE_RATE_PRC_LIST, protectBtnFlg);

        scrnMsg.notToExcdFlg_GB.setInputProtected(protectFldFlg);
        scrnMsg.allwPrcDevnFlg_GB.setInputProtected(protectFldFlg);

        scrnMsg.grsPrcPct_CB.setAppFracDigit(rateNum);
        scrnMsg.wavBookPrcFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.wavAllFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.allowNewAggrContrFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.allowNewFleetContrFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.allowAddFleetContrFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.allowAddAggrContrFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.allowSvcToDclnFlg_CB.setInputProtected(protectFldFlg);
        scrnMsg.grsPrcPct_CB.setInputProtected(protectFldFlg);

        scrnMsg.prcTierSvcOfferCd_CC.setInputProtected(protectFldFlg);
        scrnMsg.allowSvcToDclnFlg_CC.setInputProtected(protectFldFlg);

        scrnMsg.prcCatgCd_H2.setInputProtected(true);
        scrnMsg.prcCatgNm_H2.setInputProtected(true);

        // Detail
        handler.setButtonEnabled(BTN_MOVE_MASS_UPLOAD, protectBtnFlg);
        scrnMsg.effThruDt_DH.setInputProtected(protectFldFlg);
        handler.setButtonEnabled(BTN_MASS_UPLOAD, protectBtnFlg);
        handler.setButtonEnabled(BTN_ADD_PRC_LIST, protectBtnFlg);
        handler.setButtonEnabled(BTN_DEL_PRC_LIST, protectBtnFlg);

        // In Transaction Audience, the name is an input item.
        // Equipment
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcListEquipPrcAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).minPrcAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).maxPrcAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).prcTermAot_PA.setAppFracDigit(aotNum);
            scrnMsg.A.no(i).mlyPmtAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).minLeasePmtAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).maxLeasePmtAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).xxCalcTotPrcAmt_PA.setAppFracDigit(amtNum);
            scrnMsg.A.no(i).quotRevAmt_PA.setAppFracDigit(amtNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.A.no(i).xxChkBox_SA.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.A.no(i).xxChkBox_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcListEquipConfigNum_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcListEquipConfigNm_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcQlfyTpCd_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcQlfyValTxt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).mdseDescShortTxt_PA.setInputProtected(true);
            scrnMsg.A.no(i).coaProjNm_PA.setInputProtected(true);
            scrnMsg.A.no(i).mdseItemTpNm_PA.setInputProtected(true);
            scrnMsg.A.no(i).coaProdNm_PA.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_PA.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_P0.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_P1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_P2.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_P3.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_P4.setInputProtected(true);
            scrnMsg.A.no(i).pkgUomCd_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcListEquipPrcAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).minPrcAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).maxPrcAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcTermAot_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcTermUomCd_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).custBidQty_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).mlyPmtAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).minLeasePmtAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).maxLeasePmtAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcFmlaPk_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcFmlaNm_PA.setInputProtected(true);
            handler.setButtonEnabled(BTN_PRC_FORMULA, protectBtnFlg);
            scrnMsg.A.no(i).xxCalcTotPrcAmt_PA.setInputProtected(true);
            scrnMsg.A.no(i).openMktFlg_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).quotRevAmt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).compCd_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effFromDt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effThruDt_PA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).xxScrStsTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxFullNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cratDt_PA.setInputProtected(true);
            scrnMsg.A.no(i).xxFullNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).lastUpdDt_PA.setInputProtected(true);
        }

        // Service
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).baseAmt_PB.setAppFracDigit(amtNum);
            scrnMsg.B.no(i).minBaseAmt_PB.setAppFracDigit(amtNum);
            scrnMsg.B.no(i).maxBaseAmt_PB.setAppFracDigit(amtNum);
            scrnMsg.B.no(i).cpcAmtRate_PB.setAppFracDigit(rateNum);
            scrnMsg.B.no(i).minCpcAmtRate_PB.setAppFracDigit(rateNum);
            scrnMsg.B.no(i).maxCpcAmtRate_PB.setAppFracDigit(rateNum);
            scrnMsg.B.no(i).termFromMthAot_PB.setAppFracDigit(aotNum);
            scrnMsg.B.no(i).termThruMthAot_PB.setAppFracDigit(aotNum);
            scrnMsg.B.no(i).quotRevAmt_PB.setAppFracDigit(amtNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.B.no(i).xxChkBox_SB.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.B.no(i).xxChkBox_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRateTpCd_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).mdseDescShortTxt_PB.setInputProtected(true);
            scrnMsg.B.no(i).prcSvcPlnTpCd_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcSvcContrTpCd_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).baseAmt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).minBaseAmt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).maxBaseAmt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).cpcAmtRate_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).minCpcAmtRate_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).maxCpcAmtRate_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).termFromMthAot_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).termThruMthAot_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcSvcZoneCd_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).mdseCd_PB.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MDSE, protectBtnFlg);
            scrnMsg.B.no(i).mdseDescShortTxt_PZ.setInputProtected(true);
            scrnMsg.B.no(i).mtrLbDescTxt_PB.setInputProtected(true);
            scrnMsg.B.no(i).quotRevAmt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).compCd_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).effFromDt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).effThruDt_PB.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).xxScrStsTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxFullNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).cratDt_PB.setInputProtected(true);
            scrnMsg.B.no(i).xxFullNm_B2.setInputProtected(true);
            scrnMsg.B.no(i).lastUpdDt_PB.setInputProtected(true);
        }

        // Service Tiers
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).baseAmt_PC.setAppFracDigit(amtNum);
            scrnMsg.C.no(i).minBaseAmt_PC.setAppFracDigit(amtNum);
            scrnMsg.C.no(i).maxBaseAmt_PC.setAppFracDigit(amtNum);
            scrnMsg.C.no(i).cpcAmtRate_PC.setAppFracDigit(rateNum);
            scrnMsg.C.no(i).minCpcAmtRate_PC.setAppFracDigit(rateNum);
            scrnMsg.C.no(i).maxCpcAmtRate_PC.setAppFracDigit(rateNum);
            scrnMsg.C.no(i).termFromMthAot_PC.setAppFracDigit(aotNum);
            scrnMsg.C.no(i).termThruMthAot_PC.setAppFracDigit(aotNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.C.no(i).xxChkBox_SC.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.C.no(i).xxChkBox_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).mdlNm_PC.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MODEL, protectBtnFlg);
            scrnMsg.C.no(i).prcSvcTierTpCd_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).prcMtrPkgNm_PC.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MTR_PKG, protectBtnFlg);
            scrnMsg.C.no(i).prcSvcPlnTpCd_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).prcSvcContrTpCd_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).mtrLbNm_PC.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MTR_LB, protectBtnFlg);
            scrnMsg.C.no(i).avgCopyVolCnt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).minCopyVolCnt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).maxCopyVolCnt_PC.setInputProtected(protectFldFlg);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.C.no(i).prcListBandCd_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).prcListBandDescTxt_PC.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_LIST_BAND, protectBtnFlg);
            // 2018/11/17 QC#29147 Mod Start
            scrnMsg.C.no(i).baseAmt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).minBaseAmt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).maxBaseAmt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).cpcAmtRate_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).minCpcAmtRate_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).maxCpcAmtRate_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).termFromMthAot_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).termThruMthAot_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).mdseCd_PC.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MDSE, protectBtnFlg);
            scrnMsg.C.no(i).mdseDescShortTxt_PC.setInputProtected(true);
            scrnMsg.C.no(i).mtrLbDescTxt_PC.setInputProtected(true);
            scrnMsg.C.no(i).effFromDt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).effThruDt_PC.setInputProtected(protectFldFlg);
            scrnMsg.C.no(i).xxScrStsTxt_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxFullNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).cratDt_PC.setInputProtected(true);
            scrnMsg.C.no(i).xxFullNm_C2.setInputProtected(true);
            scrnMsg.C.no(i).lastUpdDt_PC.setInputProtected(true);
        }

        // Service Special Charges
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).addlChrgPrcAmt_PD.setAppFracDigit(amtNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.D.no(i).xxChkBox_SD.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.D.no(i).xxChkBox_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).mdseCd_PD.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MDSE, protectBtnFlg);
            scrnMsg.D.no(i).mdseDescShortTxt_PD.setInputProtected(true);
            scrnMsg.D.no(i).prcAddlChrgTpCd_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).mktMdlSegCd_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).mdlNm_PD.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MODEL, protectBtnFlg);
            scrnMsg.D.no(i).addlChrgPrcAmt_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).effFromDt_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).effThruDt_PD.setInputProtected(protectFldFlg);
            scrnMsg.D.no(i).xxScrStsTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).xxFullNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).cratDt_PD.setInputProtected(true);
            scrnMsg.D.no(i).xxFullNm_D2.setInputProtected(true);
            scrnMsg.D.no(i).lastUpdDt_PD.setInputProtected(true);
        }

        // Supply Program
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).totBaseAmt_PE.setAppFracDigit(amtNum);
            scrnMsg.E.no(i).splyBaseAmt_PE.setAppFracDigit(amtNum);
            scrnMsg.E.no(i).cpcAmtRate_PE.setAppFracDigit(rateNum);
            scrnMsg.E.no(i).minCpcAmtRate_PE.setAppFracDigit(rateNum);
            scrnMsg.E.no(i).maxCpcAmtRate_PE.setAppFracDigit(rateNum);
            scrnMsg.E.no(i).termFromMthAot_PE.setAppFracDigit(aotNum);
            scrnMsg.E.no(i).termThruMthAot_PE.setAppFracDigit(aotNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.E.no(i).xxChkBox_SE.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.E.no(i).xxChkBox_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).mdlNm_PE.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MODEL, protectBtnFlg);
            scrnMsg.E.no(i).prcMtrPkgNm_PE.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MTR_PKG, protectBtnFlg);
            scrnMsg.E.no(i).prcSvcPlnTpCd_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).prcSvcContrTpCd_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).mtrLbNm_PE.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MTR_LB, protectBtnFlg);
            scrnMsg.E.no(i).minCopyVolCnt_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).maxCopyVolCnt_PE.setInputProtected(protectFldFlg);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.E.no(i).prcListBandCd_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).prcListBandDescTxt_PE.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_LIST_BAND, protectBtnFlg);
            // 2018/11/17 QC#29147 Mod End
            scrnMsg.E.no(i).totBaseAmt_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).splyBaseAmt_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).cpcAmtRate_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).minCpcAmtRate_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).maxCpcAmtRate_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).termFromMthAot_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).termThruMthAot_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).mdseCd_PE.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_MDSE, protectBtnFlg);
            scrnMsg.E.no(i).mdseDescShortTxt_PE.setInputProtected(true);
            scrnMsg.E.no(i).mtrLbDescTxt_PE.setInputProtected(true);
            scrnMsg.E.no(i).prcSvcZoneCd_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).splyAgmtPlnPk_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).splyAgmtPlnNm_PE.setInputProtected(true);
            handler.setButtonEnabled(BTN_SUPPLY_PLAN, protectBtnFlg);
            scrnMsg.E.no(i).effFromDt_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).effThruDt_PE.setInputProtected(protectFldFlg);
            scrnMsg.E.no(i).xxScrStsTxt_E1.setInputProtected(true);
            scrnMsg.E.no(i).xxFullNm_E1.setInputProtected(true);
            scrnMsg.E.no(i).cratDt_PE.setInputProtected(true);
            scrnMsg.E.no(i).xxFullNm_E2.setInputProtected(true);
            scrnMsg.E.no(i).lastUpdDt_PE.setInputProtected(true);
        }

        // Lease Rate
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).minTotFinAmt_PF.setAppFracDigit(amtNum);
            scrnMsg.F.no(i).maxTotFinAmt_PF.setAppFracDigit(amtNum);
            scrnMsg.F.no(i).termFromMthAot_PF.setAppFracDigit(aotNum);
            scrnMsg.F.no(i).termThruMthAot_PF.setAppFracDigit(aotNum);
            scrnMsg.F.no(i).leaseRate_PF.setAppFracDigit(rateNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.F.no(i).xxChkBox_SF.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.F.no(i).xxChkBox_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).prcLeaseCmpyAbbrNm_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).dsAcctNm_PF.setInputProtected(true);
            handler.setButtonEnabled(BTN_DS_ACCT_CUST, protectBtnFlg);
            scrnMsg.F.no(i).prcPgmTpCd_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).prcEquipTpCd_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).mdlNm_PF.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MODEL, protectBtnFlg);
            scrnMsg.F.no(i).minTotFinAmt_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).maxTotFinAmt_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).termFromMthAot_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).termThruMthAot_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).leaseRate_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).effFromDt_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).effThruDt_PF.setInputProtected(protectFldFlg);
            scrnMsg.F.no(i).xxScrStsTxt_F1.setInputProtected(true);
            scrnMsg.F.no(i).xxFullNm_F1.setInputProtected(true);
            scrnMsg.F.no(i).cratDt_PF.setInputProtected(true);
            scrnMsg.F.no(i).xxFullNm_F2.setInputProtected(true);
            scrnMsg.F.no(i).lastUpdDt_PF.setInputProtected(true);
        }

        // Lease Return Fees
        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).dstMileAmt_PG.setAppFracDigit(amtNum);
            scrnMsg.G.no(i).rtrnFeeAmt_PG.setAppFracDigit(amtNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.G.no(i).xxChkBox_SG.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.G.no(i).xxChkBox_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).prcLeaseCmpyAbbrNm_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).svcSegCd_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).prcInOutRgCd_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).dstMileAmt_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).rtrnFeeAmt_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).effFromDt_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).effThruDt_PG.setInputProtected(protectFldFlg);
            scrnMsg.G.no(i).xxScrStsTxt_G1.setInputProtected(true);
            scrnMsg.G.no(i).xxFullNm_G1.setInputProtected(true);
            scrnMsg.G.no(i).cratDt_PG.setInputProtected(true);
            scrnMsg.G.no(i).xxFullNm_G2.setInputProtected(true);
            scrnMsg.G.no(i).lastUpdDt_PG.setInputProtected(true);
        }

        // Trade In
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).tiAmt_PH.setAppFracDigit(amtNum);

            // 2017/11/14 QC#20203(Sol#257) Add Start
            scrnMsg.H.no(i).xxChkBox_SH.setInputProtected(protectFldFlg);
            // 2017/11/14 QC#20203(Sol#257) Add End
            scrnMsg.H.no(i).xxChkBox_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).mdlNm_PH.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(BTN_PRC_MODEL, protectBtnFlg);
            scrnMsg.H.no(i).tiAmt_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).mtrRngReqFlg_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).fromMtrCopyVolCnt_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).thruMtrCopyVolCnt_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).effFromDt_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).effThruDt_PH.setInputProtected(protectFldFlg);
            scrnMsg.H.no(i).xxScrStsTxt_H1.setInputProtected(true);
            scrnMsg.H.no(i).xxFullNm_H1.setInputProtected(true);
            scrnMsg.H.no(i).cratDt_PH.setInputProtected(true);
            scrnMsg.H.no(i).xxFullNm_H2.setInputProtected(true);
            scrnMsg.H.no(i).lastUpdDt_PH.setInputProtected(true);
        }

        // Qty Discount
        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).qtyDiscPrcAmt_PI.setAppFracDigit(amtNum);

            scrnMsg.I.no(i).prodCtrlNm_PI.setInputProtected(true);
            scrnMsg.I.no(i).coaProjNm_PI.setInputProtected(true);
            scrnMsg.I.no(i).mdseItemTpNm_PI.setInputProtected(true);
            scrnMsg.I.no(i).coaProdNm_PI.setInputProtected(true);
            scrnMsg.I.no(i).t_MdlNm_PI.setInputProtected(true);
            scrnMsg.I.no(i).xxFullNm_I1.setInputProtected(true);
            scrnMsg.I.no(i).cratDt_PI.setInputProtected(true);
            scrnMsg.I.no(i).xxFullNm_I2.setInputProtected(true);
            scrnMsg.I.no(i).lastUpdDt_PI.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.J.length(); i++) {
            scrnMsg.J.no(i).prcBreakAmt_PJ.setAppFracDigit(amtNum);
        }
    }
    // Add End 2017/02/23 QC#16011

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        // Del Start 2017/02/27 QC#16011
        // Submit Control Event is "btnCtrlSubmit"
//        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        // Del Start 2017/02/27 QC#16011
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7010BMsg
     * @param bMsgAry     EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7010BMsg scrnMsg, EZDBMsgArray bMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, bMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7010BMsg
     * @param bMsgAry     EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7010BMsg scrnMsg, EZDBMsgArray bMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, bMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, bMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7010BMsg
     * @param bMsgAry     EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7010BMsg scrnMsg, EZDBMsgArray bMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, bMsgAry);
    }

    /**
     * scrnAllGUIControl.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void scrnAllGUIControl(EZDCommonHandler handler, NMAL7010BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // Add Start 2017/02/23 QC#16011
        boolean updateAuthFlg = updateAuthority(userProfileService);
        // Add End 2017/02/23 QC#16011

        if (isHeaderTab(scrnMsg)) {
            // Price Audience
            if (TAB_CUST_AUDC.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                //// Customer
                for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
                    // Mod Start 2017/02/23 QC#16011
//                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_01, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X1.getValue(), scrnMsg);
//                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_02, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X2.getValue(), scrnMsg);
//                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_03, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X3.getValue(), scrnMsg);
                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_01, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X1.getValue(), scrnMsg, updateAuthFlg);
                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_02, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X2.getValue(), scrnMsg, updateAuthFlg);
                    NMAL7010CommonLogic.prcCustAudcCatgCtrl(handler, BTN_PRC_CUST_AUDC_03, i, scrnMsg.X.no(i).prcCustAudcCatgCd_X3.getValue(), scrnMsg, updateAuthFlg);
                    // Mod End 2017/02/23 QC#16011
               }
            } else if (TAB_TRX_AUDC.equals(scrnMsg.xxDplyTab_H1.getValue())) {

                //// Transaction
                for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
                    // Mod Start 2017/02/24 QC#16011
//                    NMAL7010CommonLogic.prcTrxAudcCatgCtrl(handler, BTN_PRC_TRX_AUDC_01, i, scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue(), scrnMsg);
//                    NMAL7010CommonLogic.prcTrxAudcCatgCtrl(handler, BTN_PRC_TRX_AUDC_02, i, scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg);
                    NMAL7010CommonLogic.prcTrxAudcCatgCtrl(handler, BTN_PRC_TRX_AUDC_01, i, scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue(), scrnMsg, updateAuthFlg);
                    NMAL7010CommonLogic.prcTrxAudcCatgCtrl(handler, BTN_PRC_TRX_AUDC_02, i, scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg, updateAuthFlg);
                    // Mod End 2017/02/24 QC#16011
                }
            }
        } else {

            // Price List
            if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    // Mod Start 2017/02/24 QC#16011
//                    NMAL7010CommonLogic.prcListQlfyTpCtrl(handler, BTN_PRC_QLFY_VAL, i, scrnMsg.A.no(i).prcQlfyTpCd_PA.getValue(), scrnMsg);
                    NMAL7010CommonLogic.prcListQlfyTpCtrl(handler, BTN_PRC_QLFY_VAL, i, scrnMsg.A.no(i).prcQlfyTpCd_PA.getValue(), scrnMsg, updateAuthFlg);
                    // Mod End 2017/02/24 QC#16011
                }
            } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
                    // Mod Start 2017/02/24 QC#16011
//                    NMAL7010CommonLogic.prcListQlfyTpCtrl(handler, BTN_PRC_QLFY_VAL, i, scrnMsg.I.no(i).prcQlfyTpCd_PI.getValue(), scrnMsg);
                    NMAL7010CommonLogic.prcListQlfyTpCtrl(handler, BTN_PRC_QLFY_VAL, i, scrnMsg.I.no(i).prcQlfyTpCd_PI.getValue(), scrnMsg, updateAuthFlg);
                    // Mod End 2017/02/24 QC#16011
                }
            } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    // Mod Start 2017/02/23 QC#16011
//                    prcListRateTypeCtrl(handler, scrnMsg, i);
                    prcListRateTypeCtrl(handler, scrnMsg, i, updateAuthFlg);
                    // Mod End 2017/02/23 QC#16011
                }
            }
        }

        setTableColor(scrnMsg);

        // Mod Start 2017/02/23 QC#16011
//        btnCtrlSubmit(handler, scrnMsg);
        btnCtrlSubmit(handler, scrnMsg, updateAuthFlg);
        // Mod End 2017/02/23 QC#16011

        btnCtrlDownload(handler, scrnMsg);

        // 2018/05/08 QC#20269 Add Start
        btnCtrlDownloadasTemplate(handler, scrnMsg);
        // 2018/05/08 QC#20269 Add End
    }

    /**
     * prcCustAudcCategCtrl.
     * @param handler EZDCommonHandler
     * @param btnName String
     * @param idx int
     * @param prcCustAudcCatgCd String
     * @param scrnMsg NMAL7010BMsg
     * @param updateAuthFlg boolean
     */
    public static void prcCustAudcCatgCtrl(EZDCommonHandler handler, String btnName, int idx, String prcCustAudcCatgCd, NMAL7010BMsg scrnMsg, boolean updateAuthFlg) {
        // Input Field Change
        if (BTN_PRC_CUST_AUDC_01.equals(btnName)) {
            if (PRC_CUST_AUDC_CATG.PUBLIC.equals(prcCustAudcCatgCd)) {
                EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, "pubFlg_CX#" + idx);
                pubFlg.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(pubFlg);

                EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, "xxScrItem30Txt_X1#" + idx);
                custAudcVal.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(custAudcVal);

                scrnMsg.setFocusItem(scrnMsg.X.no(idx).pubFlg_CX);
            } else {

                EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, "pubFlg_CX#" + idx);
                pubFlg.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(pubFlg);

                EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, "xxScrItem30Txt_X1#" + idx);
                custAudcVal.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(custAudcVal);

                scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X1);
            }
        }

        // Button Change
        if (PRC_CUST_AUDC_CATG.PUBLIC.equals(prcCustAudcCatgCd)) {

            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
            pubFlg.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(custAudcVal);

            EZDGUIAttribute acctAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ACC + "#" + idx);
            acctAttr.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(acctAttr);

//        } else if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(prcCustAudcCatgCd)
//                || PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(prcCustAudcCatgCd)) {

        } else if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(prcCustAudcCatgCd)) {
            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
            pubFlg.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(custAudcVal);

            EZDGUIAttribute acctAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ACC + "#" + idx);
            acctAttr.setStyleAttribute("display", "");
            scrnMsg.addGUIAttribute(acctAttr);
        // QC#7303
//        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(prcCustAudcCatgCd)) {
        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(prcCustAudcCatgCd) || PRC_CUST_AUDC_CATG.CSMP_NUM.equals(prcCustAudcCatgCd)) {

            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
            pubFlg.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "");
            scrnMsg.addGUIAttribute(custAudcVal);

            EZDGUIAttribute acctAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ACC + "#" + idx);
            acctAttr.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(acctAttr);

        } else {

            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
//            if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(prcCustAudcCatgCd)) {
//                pubFlg.setStyleAttribute("display", "none");
//            } else {
//                pubFlg.setStyleAttribute("display", "");
//            }
            pubFlg.setStyleAttribute("display", "");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(custAudcVal);

            EZDGUIAttribute acctAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ACC + "#" + idx);
            acctAttr.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(acctAttr);
        }

        // Button Enable
        if (ZYPCommonFunc.hasValue(prcCustAudcCatgCd)) {
//            if (PRC_CUST_AUDC_CATG.PUBLIC.equals(prcCustAudcCatgCd)
//                    || PRC_CUST_AUDC_CATG.CSMP_NUM.equals(prcCustAudcCatgCd)) {
            if (PRC_CUST_AUDC_CATG.PUBLIC.equals(prcCustAudcCatgCd)) {
                handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
                handler.setButtonEnabled(btnName + BTN_GEN, idx, false);
                handler.setButtonEnabled(btnName + BTN_ACC, idx, false);
            } else {
                handler.setButtonEnabled(btnName + BTN_CMN, idx, true);
                handler.setButtonEnabled(btnName + BTN_GEN, idx, true);
                handler.setButtonEnabled(btnName + BTN_ACC, idx, true);
            }
        } else {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
            handler.setButtonEnabled(btnName + BTN_GEN, idx, false);
            handler.setButtonEnabled(btnName + BTN_ACC, idx, false);
        }
        // Add Start 2017/02/23 QC#16011
        if (!updateAuthFlg) {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
            handler.setButtonEnabled(btnName + BTN_GEN, idx, false);
            handler.setButtonEnabled(btnName + BTN_ACC, idx, false);
        }
        // Add Start 2017/02/23 QC#16011
    }

    /**
     * prcListQlfyTpCtrl.
     * @param handler EZDCommonHandler
     * @param btnName String
     * @param idx int
     * @param prcQlfyTpCd String
     * @param scrnMsg NMAL7010BMsg
     * @param updateAuthFlg boolean
     */
    public static void prcListQlfyTpCtrl(EZDCommonHandler handler, String btnName, int idx, String prcQlfyTpCd, NMAL7010BMsg scrnMsg, boolean updateAuthFlg) {
        // Button Enable
        if (idx < 0) {
            if (ZYPCommonFunc.hasValue(prcQlfyTpCd)) {
                handler.setButtonEnabled(btnName + BTN_ITEM, true);
                handler.setButtonEnabled(btnName + BTN_PROD, true);
                handler.setButtonEnabled(btnName + BTN_MDSE_TP, true);
            } else {
                handler.setButtonEnabled(btnName + BTN_ITEM, false);
                handler.setButtonEnabled(btnName + BTN_PROD, false);
                handler.setButtonEnabled(btnName + BTN_MDSE_TP, false);
            }
        } else {
            if (ZYPCommonFunc.hasValue(prcQlfyTpCd)) {
                handler.setButtonEnabled(btnName + BTN_ITEM, idx, true);
                handler.setButtonEnabled(btnName + BTN_PROD, idx, true);
                handler.setButtonEnabled(btnName + BTN_MDSE_TP, idx, true);
            } else {
                handler.setButtonEnabled(btnName + BTN_ITEM, idx, false);
                handler.setButtonEnabled(btnName + BTN_PROD, idx, false);
                handler.setButtonEnabled(btnName + BTN_MDSE_TP, idx, false);
            }
        }

        // Button Change
        if (idx < 0) {
            if (!ZYPCommonFunc.hasValue(prcQlfyTpCd)) {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM);
                btnItem.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD);
                btnProd.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP);
                btnMdseTp.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnMdseTp);
            } else if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM);
                btnItem.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD);
                btnProd.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP);
                btnMdseTp.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnMdseTp);
            } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd)) {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM);
                btnItem.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD);
                btnProd.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP);
                btnMdseTp.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnMdseTp);
            } else {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM);
                btnItem.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD);
                btnProd.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP);
                btnMdseTp.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnMdseTp);
            }
        } else {
            if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM + "#" + idx);
                btnItem.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD + "#" + idx);
                btnProd.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP + "#" + idx);
                btnMdseTp.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnMdseTp);
            } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd)) {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM + "#" + idx);
                btnItem.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD + "#" + idx);
                btnProd.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP + "#" + idx);
                btnMdseTp.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnMdseTp);
            } else {

                EZDGUIAttribute btnItem = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ITEM + "#" + idx);
                btnItem.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnItem);

                EZDGUIAttribute btnProd = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_PROD + "#" + idx);
                btnProd.setStyleAttribute("display", "");
                scrnMsg.addGUIAttribute(btnProd);

                EZDGUIAttribute btnMdseTp = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_MDSE_TP + "#" + idx);
                btnMdseTp.setStyleAttribute("display", "none");
                scrnMsg.addGUIAttribute(btnMdseTp);
            }
        }

        // Add Start 2017/02/24 QC#16011
        if (!updateAuthFlg) {
            handler.setButtonEnabled(btnName + BTN_ITEM, idx, false);
            handler.setButtonEnabled(btnName + BTN_PROD, idx, false);
            handler.setButtonEnabled(btnName + BTN_MDSE_TP, idx, false);
        }
        // Add End 2017/02/24 QC#16011
    }

    /**
     * prcTrxAudcCategCtrl.
     * @param handler EZDCommonHandler
     * @param btnName String
     * @param idx int
     * @param prcCustAudcCatgCd String
     * @param scrnMsg NMAL7010BMsg
     * @param updateAuthFlg boolean
     */
    public static void prcTrxAudcCatgCtrl(EZDCommonHandler handler, String btnName, int idx, String prcCustAudcCatgCd, NMAL7010BMsg scrnMsg, boolean updateAuthFlg) {
        // Button Enable
        if (ZYPCommonFunc.hasValue(prcCustAudcCatgCd)) {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, true);
            handler.setButtonEnabled(btnName + BTN_GEN, idx, true);
        } else {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
            handler.setButtonEnabled(btnName + BTN_GEN, idx, false);
        }

        // Button Change
        if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(prcCustAudcCatgCd)) {

            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
            pubFlg.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "");
            scrnMsg.addGUIAttribute(custAudcVal);
        } else {

            EZDGUIAttribute pubFlg = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
            pubFlg.setStyleAttribute("display", "");
            scrnMsg.addGUIAttribute(pubFlg);

            EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_GEN + "#" + idx);
            custAudcVal.setStyleAttribute("display", "none");
            scrnMsg.addGUIAttribute(custAudcVal);
        }

        // Add Start 2017/02/24 QC#16011
        if (!updateAuthFlg) {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
            handler.setButtonEnabled(btnName + BTN_GEN, idx, false);
        }
        // Add End 2017/02/24 QC#16011
    }

    /**
     * chkSelectPricListDetail
     * @param scrnMsg NMAL7010BMsg
     */
    public static void chkSelectPricListDetail(NMAL7010BMsg scrnMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        int rownum = 0;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_PA, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.A.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, CHK_PB, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.B.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, CHK_PC, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.C.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, CHK_PD, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.D.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.E, CHK_PE, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.E.getValidCount();
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.F, CHK_PF, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.F.getValidCount();
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.G, CHK_PG, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.G.getValidCount();
        } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.H, CHK_PH, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.H.getValidCount();
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.I, CHK_PI, ZYPConstant.FLG_ON_Y);
            rownum = scrnMsg.I.getValidCount();
        }

        if (rownum == 0) {
            scrnMsg.setMessageInfo(NMAM8190E);
            throw new EZDFieldErrorException();
        }
        if (selectRows.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }
    }

    /**
     * chkSelectPricListQtyDetail
     * @param scrnMsg NMAL7010BMsg
     */
    public static void chkSelectPricListQtyDetail(NMAL7010BMsg scrnMsg) {

        List<Integer> selectRows = new ArrayList<Integer>();
        int rownum = 0;

        selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.J, CHK_PJ, ZYPConstant.FLG_ON_Y);
        rownum = scrnMsg.J.getValidCount();

        if (rownum == 0) {
            scrnMsg.setMessageInfo(NMAM8190E);
            throw new EZDFieldErrorException();
        }
        if (selectRows.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }
    }

    /**
     * Set Popup Argument for NMAL6050.
     * @param scrnMsg NMAL7010BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6050(NMAL7010BMsg scrnMsg, String eventNm, int eventRow) {
        // Parameter Clear
        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_Z1.clear();
        scrnMsg.xxPopPrm_Z2.clear();
        scrnMsg.xxPopPrm_Z3.clear();
        scrnMsg.xxPopPrm_Z4.clear();
        scrnMsg.xxPopPrm_Z5.clear();
        scrnMsg.xxPopPrm_Z6.clear();
        scrnMsg.xxPopPrm_Z7.clear();
        scrnMsg.xxPopPrm_Z8.clear();
        scrnMsg.xxPopPrm_Z9.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        Object[] param = new Object[11];
        if ("OpenWin_CustAudcVal_01_Cmn".equals(eventNm)
                || "OpenWin_CustAudcVal_02_Cmn".equals(eventNm)
                || "OpenWin_CustAudcVal_03_Cmn".equals(eventNm)) {
            if ((PRC_CUST_AUDC_CATG.PUBLIC_LOB.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.PUBLIC_LOB.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.PUBLIC_LOB.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "LINE_BIZ_TP");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "LINE_BIZ_TP_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "LINE_BIZ_TP_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "LINE_BIZ_TP_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "PUBLIC LOB Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "PUBLIC LOB");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "PUBLIC LOB Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "PUBLIC LOB");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "PUBLIC LOB Name");

            } else if ((PRC_CUST_AUDC_CATG.ACCT_CHANNEL.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_CHANNEL.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_CHANNEL.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "COA_CH");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "COA_CH_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "COA_CH_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "COA_CH_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "ACCT-CHANNEL Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "ACCT-CHANNEL Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "ACCT-CHANNEL Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "ACCT-CHANNEL Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "ACCT-CHANNEL Name");

            } else if ((PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "DS_ACCT_GRP");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "DS_ACCT_GRP_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "DS_ACCT_GRP_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "DS_ACCT_GRP_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "ACCT-CUSTGROUP Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "ACCT-CUSTGROUP Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "ACCT-CUSTGROUP Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "ACCT-CUSTGROUP Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "ACCT-CUSTGROUP Name");

            } else if ((PRC_CUST_AUDC_CATG.BRANCH.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.BRANCH.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Cmn".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.BRANCH.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "COA_BR");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "COA_BR_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "COA_BR_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "COA_BR_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "BRANCH Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "BRANCH");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "BRANCH Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "BRANCH");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "BRANCH Name");

            }
            if ("OpenWin_CustAudcVal_01_Cmn".equals(eventNm)) {
                param[9]  = scrnMsg.X.no(eventRow).xxScrItem30Txt_X1;
                param[10] = scrnMsg.X.no(eventRow).xxRecNmTxt_X1;
            } else if ("OpenWin_CustAudcVal_02_Cmn".equals(eventNm)) {
                param[9]  = scrnMsg.X.no(eventRow).xxScrItem30Txt_X2;
                param[10] = scrnMsg.X.no(eventRow).xxRecNmTxt_X2;
            } else if ("OpenWin_CustAudcVal_03_Cmn".equals(eventNm)) {
                param[9]  = scrnMsg.X.no(eventRow).xxScrItem30Txt_X3;
                param[10] = scrnMsg.X.no(eventRow).xxRecNmTxt_X3;
            }
        } else if ("OpenWin_TrxAudcVal_01_Cmn".equals(eventNm)
                || "OpenWin_TrxAudcVal_02_Cmn".equals(eventNm)) {
            if ((PRC_TRX_AUDC_CATG.ORDER_CATEGORY.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y1.getValue()) && "OpenWin_TrxAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_TRX_AUDC_CATG.ORDER_CATEGORY.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y2.getValue()) && "OpenWin_TrxAudcVal_02_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "DS_ORD_CATG");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "DS_ORD_CATG_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "DS_ORD_CATG_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "DS_ORD_CATG_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "Order Category Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "Order Category Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "Order Category Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "Order Category Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "Order Category Name");

            } else if ((PRC_TRX_AUDC_CATG.ORDER_REASON.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y1.getValue()) && "OpenWin_TrxAudcVal_01_Cmn".equals(eventNm))
                    || (PRC_TRX_AUDC_CATG.ORDER_REASON.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y2.getValue()) && "OpenWin_TrxAudcVal_02_Cmn".equals(eventNm))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "DS_ORD_TP");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "DS_ORD_TP_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "DS_ORD_TP_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "DS_ORD_TP_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "Order Reason Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "Order Reason Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "Order Reason Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "Order Reason Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "Order Reason Name");
            }

            if ("OpenWin_TrxAudcVal_01_Cmn".equals(eventNm)) {
                param[9]  = scrnMsg.Y.no(eventRow).xxScrItem30Txt_Y1;
                param[10] = scrnMsg.Y.no(eventRow).xxRecNmTxt_Y1;
            } else if ("OpenWin_TrxAudcVal_02_Cmn".equals(eventNm)) {
                param[9]  = scrnMsg.Y.no(eventRow).xxScrItem30Txt_Y2;
                param[10] = scrnMsg.Y.no(eventRow).xxRecNmTxt_Y2;
            }
        } else if ("OpenWin_QualifierValueMdseTp".equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "COA_PROJ");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "COA_PROJ_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "COA_PROJ_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "COA_PROJ_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "Merchandise Type Popup");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "Merchandise Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "Merchandise Type Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "Merchandise Type ID");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "Merchandise Type Name");

            if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow < 0) {
                    // 2018/11/27 QC#29319 Mod Start
                    //param[9] = scrnMsg.prcQlfyValTxt_D1;
                    param[9] = scrnMsg.xxPrcQlfyValSrchTxt_D1;
                    // 2018/11/27 QC#29319 Mod Start
                    param[10] = scrnMsg.xxPopPrm_ZA;
                } else {
                    param[9] = scrnMsg.A.no(eventRow).prcQlfyValTxt_PA;
                    param[10] = scrnMsg.A.no(eventRow).mdseDescShortTxt_PA;
                }
            } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow < 0) {
                    param[9] = scrnMsg.prcQlfyValTxt_D2;
                    param[10] = scrnMsg.xxPopPrm_ZA;
                } else {
                    param[9] = scrnMsg.I.no(eventRow).prcQlfyValTxt_PI;
                    param[10] = scrnMsg.I.no(eventRow).prodCtrlNm_PI;
                }
            }
        }

        param[0]  = scrnMsg.xxPopPrm_Z0;
        param[1]  = scrnMsg.xxPopPrm_Z1;
        param[2]  = scrnMsg.xxPopPrm_Z2;
        param[3]  = scrnMsg.xxPopPrm_Z3;
        param[4]  = scrnMsg.xxPopPrm_Z4;
        param[5]  = scrnMsg.xxPopPrm_Z5;
        param[6]  = scrnMsg.xxPopPrm_Z6;
        param[7]  = scrnMsg.xxPopPrm_Z7;
        param[8]  = scrnMsg.xxPopPrm_Z8;
        return param;
    }

    /**
     * Set Popup Argument for NMAL6800.
     * @param scrnMsg NMAL7010BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NMAL7010BMsg scrnMsg, String eventNm, int eventRow) {
        // Mod Start 2018/07/17 QC#20267
        Object[] param = new Object[13];

        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (eventRow < 0) {
                // 2018/11/27 QC#29319 Mod Start
                //param[0] = scrnMsg.prcQlfyValTxt_D1;
                param[0] = scrnMsg.xxPrcQlfyValSrchTxt_D1;
                // 2018/11/27 QC#29319 Mod End
                param[1] = scrnMsg.xxPopPrm_ZA;
            } else {
                if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_H2.getValue())){
                    scrnMsg.prcQlfyValTxt_B.clear();
                    param[0] = scrnMsg.prcQlfyValTxt_B;
                    param[1] = scrnMsg.xxPopPrm_ZA;
                    param[7] = scrnMsg.A.no(eventRow).mdseDescShortTxt_PA;
                    param[12] = scrnMsg.A.no(eventRow).prcQlfyValTxt_PA;
                } else {
                    param[0] = scrnMsg.A.no(eventRow).prcQlfyValTxt_PA;
                    param[1] = scrnMsg.xxPopPrm_ZA;
                    param[7] = scrnMsg.A.no(eventRow).mdseDescShortTxt_PA;
                }
             // Mod End 2018/07/17 QC#20267
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if ("OpenWin_Mdse".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
                param[0] = scrnMsg.B.no(eventRow).mdseCd_PB;
                // Mod Start 2016/09/12 QC#11615
//                param[1] = scrnMsg.B.no(eventRow).mdseNm_PB;
                param[1] = scrnMsg.B.no(eventRow).mdseDescShortTxt_PZ;
                param[7] = scrnMsg.B.no(eventRow).mdseDescShortTxt_PZ;
                // Mod End 2016/09/12 QC#11615

            } else if ("OpenWin_PrcListMdse".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
                param[0] = scrnMsg.B.no(eventRow).prcListMdseCd_PB;
                param[1] = scrnMsg.xxPopPrm_ZA;
                param[7] = scrnMsg.B.no(eventRow).mdseDescShortTxt_PB;
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[0] = scrnMsg.C.no(eventRow).mdseCd_PC;
            // Mod Start 2016/09/12 QC#11615
//            param[1] = scrnMsg.C.no(eventRow).mdseNm_PC;
            param[1] = scrnMsg.C.no(eventRow).mdseDescShortTxt_PC;
            param[7] = scrnMsg.C.no(eventRow).mdseDescShortTxt_PC;
            // Mod End 2016/09/12 QC#11615
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[0] = scrnMsg.D.no(eventRow).mdseCd_PD;
            // Mod Start 2016/09/12 QC#11615
//            param[1] = scrnMsg.D.no(eventRow).mdseNm_PD;
            param[1] = scrnMsg.D.no(eventRow).mdseDescShortTxt_PD;
            param[7] = scrnMsg.D.no(eventRow).mdseDescShortTxt_PD;
            // Mod End 2016/09/12 QC#11615
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[0] = scrnMsg.E.no(eventRow).mdseCd_PE;
            // Mod Start 2016/09/12 QC#11615
//            param[1] = scrnMsg.E.no(eventRow).mdseNm_PE;
            param[1] = scrnMsg.E.no(eventRow).mdseDescShortTxt_PE;
            param[7] = scrnMsg.E.no(eventRow).mdseDescShortTxt_PE;
            // Mod End 2016/09/12 QC#11615
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (eventRow < 0) {
                param[0] = scrnMsg.prcQlfyValTxt_D2;
                param[1] = scrnMsg.xxPopPrm_ZA;
            } else {
                param[0] = scrnMsg.I.no(eventRow).prcQlfyValTxt_PI;
                param[1] = scrnMsg.I.no(eventRow).prodCtrlNm_PI;
            }
        }
        param[2] = scrnMsg.xxPopPrm_ZA;
        param[3] = scrnMsg.xxPopPrm_ZA;
        param[4] = scrnMsg.xxPopPrm_ZA;
        param[5] = scrnMsg.xxPopPrm_ZA;
        param[6] = scrnMsg.xxPopPrm_ZA;
        if (param[7] == null) {
            param[7] = scrnMsg.xxPopPrm_ZA;
        }
        param[8] = scrnMsg.xxPopPrm_ZA;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "08");
        param[9] = scrnMsg.xxPopPrm_Z0;

        return param;
    }

    /**
     * Set Popup Argument for NMAL7110.
     * @param scrnMsg NMAL7010BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL7110(NMAL7010BMsg scrnMsg, String eventNm, int eventRow) {
        Object[] param = new Object[1];
        // Parameter is not required.
        return param;
    }

    /**
     * Set Popup Argument for NMAL6040.
     * @param scrnMsg NMAL7010BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6040(NMAL7010BMsg scrnMsg, String eventNm, int eventRow) {
        Object[] param = new Object[29];

        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_Z1.clear();
        scrnMsg.xxPopPrm_Z2.clear();
        scrnMsg.xxPopPrm_Z3.clear();
        scrnMsg.xxPopPrm_Z4.clear();
        scrnMsg.xxPopPrm_Z5.clear();
        scrnMsg.xxPopPrm_Z6.clear();
        scrnMsg.xxPopPrm_Z7.clear();
        scrnMsg.xxPopPrm_Z8.clear();
        scrnMsg.xxPopPrm_Z9.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            EZDBStringItem prcQlfyTpCdCItem;
            EZDBStringItem prcQlfyValTxtCItem;
            if (eventRow < 0) {
                prcQlfyTpCdCItem = scrnMsg.prcQlfyTpCd_D1;
                // 2018/11/27 QC#29319 Mod Start
                //prcQlfyValTxtCItem = scrnMsg.prcQlfyValTxt_D1;
                prcQlfyValTxtCItem = scrnMsg.xxPrcQlfyValSrchTxt_D1;
                // 2018/11/27 QC#29319 Mod End
            } else {
                prcQlfyTpCdCItem = scrnMsg.A.no(eventRow).prcQlfyTpCd_PA;
                prcQlfyValTxtCItem = scrnMsg.A.no(eventRow).prcQlfyValTxt_PA;
            }
            if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = prcQlfyValTxtCItem;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = prcQlfyValTxtCItem;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = prcQlfyValTxtCItem;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = prcQlfyValTxtCItem;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = prcQlfyValTxtCItem;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = prcQlfyValTxtCItem;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = prcQlfyValTxtCItem;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = prcQlfyValTxtCItem;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = prcQlfyValTxtCItem;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = prcQlfyValTxtCItem;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = prcQlfyValTxtCItem;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = prcQlfyValTxtCItem;
            }
            param[1] = scrnMsg.xxPopPrm_ZA;
            param[3] = scrnMsg.xxPopPrm_ZA;
            param[5] = scrnMsg.xxPopPrm_ZA;
            param[7] = scrnMsg.xxPopPrm_ZA;
            param[9] = scrnMsg.xxPopPrm_ZA;
            param[11] = scrnMsg.xxPopPrm_ZA;
            param[12] = scrnMsg.xxPopPrm_ZA;
            param[13] = scrnMsg.xxPopPrm_ZA;
            param[15] = scrnMsg.xxPopPrm_ZA;
            param[17] = scrnMsg.xxPopPrm_ZA;
            param[19] = scrnMsg.xxPopPrm_ZA;
            param[21] = scrnMsg.xxPopPrm_ZA;
            param[23] = scrnMsg.xxPopPrm_ZA;
            param[25] = scrnMsg.xxPopPrm_ZA;
            param[26] = scrnMsg.xxPopPrm_ZA;
            param[27] = scrnMsg.xxPopPrm_ZA;
            param[28] = scrnMsg.xxPopPrm_ZA;

        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            EZDBStringItem prcQlfyValTxtCItem;
            EZDBStringItem prcQlfyTpCdCItem;
            if (eventRow < 0) {
                prcQlfyTpCdCItem = scrnMsg.prcQlfyTpCd_D2;
                prcQlfyValTxtCItem = scrnMsg.prcQlfyValTxt_D2;
            } else {
                prcQlfyTpCdCItem = scrnMsg.I.no(eventRow).prcQlfyTpCd_PI;
                prcQlfyValTxtCItem = scrnMsg.I.no(eventRow).prcQlfyValTxt_PI;
            }
            if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = prcQlfyValTxtCItem;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = prcQlfyValTxtCItem;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = prcQlfyValTxtCItem;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = prcQlfyValTxtCItem;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = prcQlfyValTxtCItem;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = prcQlfyValTxtCItem;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = prcQlfyValTxtCItem;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = prcQlfyValTxtCItem;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = prcQlfyValTxtCItem;
                param[10] = scrnMsg.xxPopPrm_ZA;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = prcQlfyValTxtCItem;
                param[24] = scrnMsg.xxPopPrm_ZA;
            } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCdCItem.getValue())) {
                param[0] = scrnMsg.xxPopPrm_Z0;
                param[2] = scrnMsg.xxPopPrm_Z2;
                param[4] = scrnMsg.xxPopPrm_Z4;
                param[6] = scrnMsg.xxPopPrm_Z6;
                param[8] = scrnMsg.xxPopPrm_Z8;
                param[10] = prcQlfyValTxtCItem;
                param[14] = scrnMsg.xxPopPrm_Z0;
                param[16] = scrnMsg.xxPopPrm_Z2;
                param[18] = scrnMsg.xxPopPrm_Z4;
                param[20] = scrnMsg.xxPopPrm_Z6;
                param[22] = scrnMsg.xxPopPrm_Z8;
                param[24] = prcQlfyValTxtCItem;
            }
            param[1] = scrnMsg.xxPopPrm_ZA;
            param[3] = scrnMsg.xxPopPrm_ZA;
            param[5] = scrnMsg.xxPopPrm_ZA;
            param[7] = scrnMsg.xxPopPrm_ZA;
            param[9] = scrnMsg.xxPopPrm_ZA;
            param[11] = scrnMsg.xxPopPrm_ZA;
            param[12] = scrnMsg.xxPopPrm_ZA;
            param[13] = scrnMsg.xxPopPrm_ZA;
            param[15] = scrnMsg.xxPopPrm_ZA;
            param[17] = scrnMsg.xxPopPrm_ZA;
            param[19] = scrnMsg.xxPopPrm_ZA;
            param[21] = scrnMsg.xxPopPrm_ZA;
            param[23] = scrnMsg.xxPopPrm_ZA;
            param[25] = scrnMsg.xxPopPrm_ZA;
            param[26] = scrnMsg.xxPopPrm_ZA;
            param[27] = scrnMsg.xxPopPrm_ZA;
            param[28] = scrnMsg.xxPopPrm_ZA;
        }
        return param;
    }

    /**
     * Set Popup Argument for NWAL1130.
     * @param scrnMsg NMAL7010BMsg
     * @param eventNm String
     * @param eventRow int
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setArgumentNWAL1130(NMAL7010BMsg scrnMsg, String eventNm, int eventRow, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        scrnMsg.xxPopPrm_ZA.clear();

        if ("OpenWin_SubPrcList".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Price List Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PC.GLBL_CMPY_CD");
            tblNmP2.append(",PC.EZCANCELFLAG");
            tblNmP2.append(",PC.PRC_CATG_CD");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PLT.PRC_LIST_TP_DESC_TXT");
            tblNmP2.append(",PLST.PRC_LIST_STRU_TP_DESC_TXT");
            tblNmP2.append(",PCON.PRC_CONTR_NM");
            tblNmP2.append(",PLG.PRC_LIST_GRP_NM");
            tblNmP2.append(",PC.EFF_FROM_DT");
            tblNmP2.append(",PC.EFF_THRU_DT");
            tblNmP2.append(",PC.PRC_CATG_DESC_TXT");
            tblNmP2.append(",PC.PRC_LIST_DPLY_NM");
            tblNmP2.append(",PC.PRC_CONTR_NUM");
            tblNmP2.append(" FROM PRC_CATG PC");
            tblNmP2.append(",PRC_LIST_TP PLT");
            tblNmP2.append(",PRC_LIST_STRU_TP PLST");
            tblNmP2.append(",PRC_CONTR PCON");
            tblNmP2.append(",PRC_LIST_GRP PLG");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLT.EZCANCELFLAG");
            tblNmP2.append(" AND PC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD");
            tblNmP2.append(" AND PLT.GLBL_CMPY_CD = PLST.GLBL_CMPY_CD");
            tblNmP2.append(" AND PLT.EZCANCELFLAG = PLST.EZCANCELFLAG");
            tblNmP2.append(" AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD");
            tblNmP2.append(" AND EXISTS (SELECT 1 FROM PRC_LIST_TP P ");
            tblNmP2.append("                      WHERE P.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append("                      AND   P.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append("                      AND   P.PRC_LIST_TP_CD = '").append(scrnMsg.prcListTpCd_H1.getValue()).append("'");
            tblNmP2.append("                      AND   P.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD )");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PCON.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PCON.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PC.PRC_CONTR_NUM = PCON.PRC_CONTR_NUM(+)");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLG.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLG.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PC.PRC_LIST_GRP_CD = PLG.PRC_LIST_GRP_CD(+)");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price List Name";
            whereArray0[1] = "PRC_CATG_NM";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Price List Disp Name";
            whereArray1[1] = "PRC_LIST_DPLY_NM";
            whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Price Description";
            whereArray2[1] = "PRC_CATG_DESC_TXT";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Contract#";
            whereArray3[1] = "PRC_CONTR_NUM";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Contract Name";
            whereArray4[1] = "PRC_CONTR_NM";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "Y";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Price List Type Name";
            whereArray5[1] = "PRC_LIST_TP_DESC_TXT";
            whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray5[3] = "Y";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Price List Stru Name";
            whereArray6[1] = "PRC_LIST_STRU_TP_DESC_TXT";
            whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray6[3] = "Y";
            whereListP3.add(whereArray6);

            Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
            whereArray7[0] = "Price List Grp Name";
            whereArray7[1] = "PRC_LIST_GRP_NM";
            whereArray7[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray7[3] = "Y";
            whereListP3.add(whereArray7);

            Object[] whereArray9 = new Object[CMN_PRM_WHERE_NUM];
            whereArray9[0] = "Effective Date From";
            whereArray9[1] = "EFF_FROM_DT";
            whereArray9[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray9[3] = "N";
            whereListP3.add(whereArray9);

            Object[] whereArray10 = new Object[CMN_PRM_WHERE_NUM];
            whereArray10[0] = "Effective Date To";
            whereArray10[1] = "EFF_THRU_DT";
            whereArray10[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray10[3] = "N";
            whereListP3.add(whereArray10);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price List ID";
            columnArray0[1] = "PRC_CATG_CD";
            columnArray0[2] = BigDecimal.valueOf(10);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Price List Name";
            columnArray1[1] = "PRC_CATG_NM";
            columnArray1[2] = BigDecimal.valueOf(21);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Price List Type Name";
            columnArray2[1] = "PRC_LIST_TP_DESC_TXT";
            columnArray2[2] = BigDecimal.valueOf(16);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Price List Structure Name";
            columnArray3[1] = "PRC_LIST_STRU_TP_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(16);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Contract Name";
            columnArray4[1] = "PRC_CONTR_NM";
            columnArray4[2] = BigDecimal.valueOf(25);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Price List Group Name";
            columnArray5[1] = "PRC_LIST_GRP_NM";
            columnArray5[2] = BigDecimal.valueOf(21);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Effective Date From";
            columnArray6[1] = "EFF_FROM_DT";
            columnArray6[2] = BigDecimal.valueOf(13);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date To";
            columnArray7[1] = "EFF_THRU_DT";
            columnArray7[2] = BigDecimal.valueOf(13);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] sortConditionArray = new Object[2];
            sortConditionArray[0] = "PRC_CATG_CD";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);

        } else if ("OpenWin_LeaseRatePrcList".equals(eventNm)) {
            // QC#7080
            suffixP0 = "";
            scrnNmP1 = "Lease Rate Price List Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PC.GLBL_CMPY_CD");
            tblNmP2.append(",PC.EZCANCELFLAG");
            tblNmP2.append(",PC.PRC_CATG_CD");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PLT.PRC_LIST_TP_DESC_TXT");
            tblNmP2.append(" FROM PRC_CATG PC");
            tblNmP2.append(",PRC_LIST_TP PLT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLT.EZCANCELFLAG");
            tblNmP2.append(" AND PC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD");
            tblNmP2.append(" AND PLT.PRC_LIST_STRU_TP_CD = '").append(PRC_LIST_STRU_TP.LEASE_RATES).append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price List Name";
            whereArray0[1] = "PRC_CATG_NM";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price List ID";
            columnArray0[1] = "PRC_CATG_CD";
            columnArray0[2] = BigDecimal.valueOf(10);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Price List Name";
            columnArray1[1] = "PRC_CATG_NM";
            columnArray1[2] = BigDecimal.valueOf(21);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Price List Type Name";
            columnArray2[1] = "PRC_LIST_TP_DESC_TXT";
            columnArray2[2] = BigDecimal.valueOf(16);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] sortConditionArray = new Object[2];
            sortConditionArray[0] = "PRC_CATG_CD";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);

        } else if ("OpenWin_CustAudcVal_01_Gen".equals(eventNm)
                || "OpenWin_CustAudcVal_02_Gen".equals(eventNm)
                || "OpenWin_CustAudcVal_03_Gen".equals(eventNm)) {
            if ((PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Gen".equals(eventNm))) {
                suffixP0 = "";
                scrnNmP1 = "ACCT# Popup";

                tblNmP2.append(" SELECT");
                tblNmP2.append(" S.GLBL_CMPY_CD");
                tblNmP2.append(",S.EZCANCELFLAG");
                tblNmP2.append(",S.SELL_TO_CUST_CD");
                tblNmP2.append(",S.DS_ACCT_NM");
                tblNmP2.append(" FROM SELL_TO_CUST S");
                tblNmP2.append(" WHERE");
                tblNmP2.append(" S.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
                tblNmP2.append(" AND S.EZCANCELFLAG = '").append("0").append("'");
                tblNmP2.append(" AND S.DS_ACCT_TP_CD = '").append(DS_ACCT_TP.CUSTOMER).append("'");

                Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
                whereArray0[0] = "ACCT#";
                whereArray0[1] = "SELL_TO_CUST_CD";
                whereArray0[3] = "Y";

                Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
                whereArray1[0] = "ACCT Name";
                whereArray1[1] = "DS_ACCT_NM";
                whereArray1[3] = "Y";

                if ("OpenWin_CustAudcVal_01".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X1.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                } else if ("OpenWin_CustAudcVal_02".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X2.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                } else if ("OpenWin_CustAudcVal_03".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X3.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                }
                whereListP3.add(whereArray0);
                whereListP3.add(whereArray1);

                Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray0[0] = "ACCT#";
                columnArray0[1] = "SELL_TO_CUST";
                columnArray0[2] = BigDecimal.valueOf(28);
                columnArray0[3] = "Y";
                columnListP4.add(columnArray0);

                Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray1[0] = "ACCT Name";
                columnArray1[1] = "DS_ACCT_NM";
                columnArray1[2] = BigDecimal.valueOf(60);
                columnArray1[3] = "N";
                columnListP4.add(columnArray1);

                Object[] sortConditionArray = new Object[2];
                sortConditionArray[0] = "SELL_TO_CUST";
                sortConditionArray[1] = "ASC";
                sortConditionListP5.add(sortConditionArray);

            } else if ((PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Gen".equals(eventNm))) {
                suffixP0 = "";
                scrnNmP1 = "Customer Price Group Popup";

                tblNmP2.append(" SELECT");
                tblNmP2.append(" PG.GLBL_CMPY_CD");
                tblNmP2.append(",PG.EZCANCELFLAG");
                tblNmP2.append(",TO_CHAR(PG.PRC_GRP_PK) PRC_GRP_PK");
                tblNmP2.append(",PG.PRC_GRP_NM");
                tblNmP2.append(",PG.PRC_GRP_DESC_TXT");
                tblNmP2.append(",PG.ACTV_FLG");
                tblNmP2.append(",PGTT.PRC_GRP_TRGT_TP_DESC_TXT");
                tblNmP2.append(",PGTA.PRC_GRP_TRGT_ATTRB_DESC_TXT");
                tblNmP2.append(",PGO.PRC_GRP_OP_DESC_TXT");
                tblNmP2.append(",PGD.PRC_GRP_FROM_TXT");
                tblNmP2.append(",PGD.PRC_GRP_THRU_TXT");
                tblNmP2.append(",PGD.EFF_FROM_DT");
                tblNmP2.append(",PGD.EFF_THRU_DT");
                tblNmP2.append(",PGD.PRC_GRP_DTL_PK");
                tblNmP2.append(" FROM PRC_GRP PG");
                tblNmP2.append(",PRC_GRP_DTL PGD");
                tblNmP2.append(",PRC_GRP_TRGT_TP PGTT");
                tblNmP2.append(",PRC_GRP_TRGT_ATTRB PGTA");
                tblNmP2.append(",PRC_GRP_OP PGO");
                tblNmP2.append(" WHERE");
                tblNmP2.append(" PG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
                tblNmP2.append(" AND PG.EZCANCELFLAG = '").append("0").append("'");
                tblNmP2.append(" AND PG.PRC_GRP_TP_CD = '").append(PRC_GRP_TP.CUSTOMER_PRICING_GROUP).append("'");
                tblNmP2.append(" AND PG.PRC_GRP_PK = PGD.PRC_GRP_PK");
                tblNmP2.append(" AND PG.EZCANCELFLAG  = PGD.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTT.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_TRGT_TP_CD = PGTT.PRC_GRP_TRGT_TP_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTT.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTA.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_TRGT_ATTRB_CD = PGTA.PRC_GRP_TRGT_ATTRB_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTA.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGO.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_OP_CD = PGO.PRC_GRP_OP_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGO.EZCANCELFLAG");

                Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
                whereArray0[0] = "Price Group PK";
                whereArray0[1] = "PRC_GRP_PK";
                whereArray0[3] = "Y";

                Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
                whereArray1[0] = "Price Group Name";
                whereArray1[1] = "PRC_GRP_NM";
                whereArray1[3] = "Y";

                if ("OpenWin_CustAudcVal_01_Gen".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X1.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                } else if ("OpenWin_CustAudcVal_02_Gen".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X2.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                } else if ("OpenWin_CustAudcVal_03_Gen".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X3.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                }
                whereListP3.add(whereArray0);
                whereListP3.add(whereArray1);

                Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
                whereArray2[0] = "Group Description";
                whereArray2[1] = "PRC_GRP_DESC_TXT";
                whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray2[3] = "Y";
                whereListP3.add(whereArray2);

                Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
                whereArray3[0] = "Effective Date From";
                whereArray3[1] = "EFF_FROM_DT";
                whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray3[3] = "N";
                whereListP3.add(whereArray3);

                Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
                whereArray4[0] = "Effective Date To";
                whereArray4[1] = "EFF_THRU_DT";
                whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray4[3] = "N";
                whereListP3.add(whereArray4);

                Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
                whereArray5[0] = "Target Context";
                whereArray5[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
                whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray5[3] = "Y";
                whereListP3.add(whereArray5);

                Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
                whereArray6[0] = "Attribute Name";
                whereArray6[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
                whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray6[3] = "Y";
                whereListP3.add(whereArray6);

                Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
                whereArray7[0] = "Attribute Value From";
                whereArray7[1] = "PRC_GRP_FROM_TXT";
                whereArray7[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray7[3] = "N";
                whereListP3.add(whereArray7);

                Object[] whereArray8 = new Object[CMN_PRM_WHERE_NUM];
                whereArray8[0] = "Attribute Value To";
                whereArray8[1] = "PRC_GRP_THRU_TXT";
                whereArray8[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray8[3] = "N";
                whereListP3.add(whereArray8);

                Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray0[0] = "Price Group PK";
                columnArray0[1] = "PRC_GRP_PK";
                columnArray0[2] = BigDecimal.valueOf(15);
                columnArray0[3] = "Y";
                columnListP4.add(columnArray0);

                Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray1[0] = "Price Group Name";
                columnArray1[1] = "PRC_GRP_NM";
                columnArray1[2] = BigDecimal.valueOf(21);
                columnArray1[3] = "N";
                columnListP4.add(columnArray1);

                Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray2[0] = "Active";
                columnArray2[1] = "ACTV_FLG";
                columnArray2[2] = BigDecimal.valueOf(5);
                columnArray2[3] = "N";
                columnListP4.add(columnArray2);

                Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray3[0] = "Target Context";
                columnArray3[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
                columnArray3[2] = BigDecimal.valueOf(15);
                columnArray3[3] = "N";
                columnListP4.add(columnArray3);

                Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray4[0] = "Attribute Name";
                columnArray4[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
                columnArray4[2] = BigDecimal.valueOf(18);
                columnArray4[3] = "N";
                columnListP4.add(columnArray4);

                Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray5[0] = "Operator";
                columnArray5[1] = "PRC_GRP_OP_DESC_TXT";
                columnArray5[2] = BigDecimal.valueOf(7);
                columnArray5[3] = "N";
                columnListP4.add(columnArray5);

                Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray6[0] = "Attribute Value From";
                columnArray6[1] = "PRC_GRP_FROM_TXT";
                columnArray6[2] = BigDecimal.valueOf(15);
                columnArray6[3] = "N";
                columnListP4.add(columnArray6);

                Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray7[0] = "Attribute Value To";
                columnArray7[1] = "PRC_GRP_THRU_TXT";
                columnArray7[2] = BigDecimal.valueOf(15);
                columnArray7[3] = "N";
                columnListP4.add(columnArray7);

                Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray8[0] = "Effective Date From";
                columnArray8[1] = "EFF_FROM_DT";
                columnArray8[2] = BigDecimal.valueOf(13);
                columnArray8[3] = "N";
                columnListP4.add(columnArray8);

                Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray9[0] = "Effective Date To";
                columnArray9[1] = "EFF_THRU_DT";
                columnArray9[2] = BigDecimal.valueOf(13);
                columnArray9[3] = "N";
                columnListP4.add(columnArray9);


                Object[] sortConditionArray1 = new Object[2];
                sortConditionArray1[0] = "PRC_GRP_PK";
                sortConditionArray1[1] = "ASC";
                sortConditionListP5.add(sortConditionArray1);

                Object[] sortConditionArray2 = new Object[2];
                sortConditionArray2[0] = "PRC_GRP_DTL_PK";
                sortConditionArray2[1] = "ASC";
                sortConditionListP5.add(sortConditionArray2);

            // QC#7303 Add
            } else if ((PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X1.getValue()) && "OpenWin_CustAudcVal_01_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X2.getValue()) && "OpenWin_CustAudcVal_02_Gen".equals(eventNm))
                    || (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(eventRow).prcCustAudcCatgCd_X3.getValue()) && "OpenWin_CustAudcVal_03_Gen".equals(eventNm))) {
                suffixP0 = "";
                scrnNmP1 = "CSMP Number Popup";

                tblNmP2.append(" SELECT CSMP.GLBL_CMPY_CD");
                tblNmP2.append(",CSMP.EZCANCELFLAG");
                tblNmP2.append(",CSMP.DS_ACCT_NUM");
                tblNmP2.append(",CSMP.DS_ACCT_NM");
                tblNmP2.append(",CSMP.CSMP_NUM");
                tblNmP2.append(",CSMP.PRC_CATG_CD");
                tblNmP2.append(",CSMP.PRC_CONTR_NUM");
                tblNmP2.append(",CSMP.RNW_CSMP_NUM");
                tblNmP2.append(",CSMP.CSMP_NUM_CMNT_TXT");
                tblNmP2.append(",CSMP.CSMP_CONTR_ACTV_FLG");
                tblNmP2.append(",CSMP.EFF_FROM_DT");
                tblNmP2.append(",CSMP.EFF_THRU_DT");
                tblNmP2.append(" FROM CSMP_CONTR CSMP");
                tblNmP2.append(" WHERE");
                tblNmP2.append(" CSMP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
                tblNmP2.append(" AND CSMP.EZCANCELFLAG = '").append("0").append("'");

                Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
                whereArray0[0] = "Account Number";
                whereArray0[1] = "DS_ACCT_NUM";
                whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray0[3] = "N";
                whereListP3.add(whereArray0);

                Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
                whereArray1[0] = "Account Name";
                whereArray1[1] = "DS_ACCT_NM";
                whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray1[3] = "Y";
                whereListP3.add(whereArray1);

                Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
                whereArray2[0] = "CSMP Number";
                whereArray2[1] = "CSMP_NUM";
                whereArray2[3] = "N";
                whereListP3.add(whereArray2);

                if ("OpenWin_CustAudcVal_01_Gen".equals(eventNm)) {
                    whereArray2[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X1.getValue();
                } else if ("OpenWin_CustAudcVal_02_Gen".equals(eventNm)) {
                    whereArray2[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X2.getValue();
                } else if ("OpenWin_CustAudcVal_03_Gen".equals(eventNm)) {
                    whereArray2[2] = scrnMsg.X.no(eventRow).xxScrItem30Txt_X3.getValue();
                }

                Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
                whereArray3[0] = "Price List ID";
                whereArray3[1] = "PRC_CATG_CD";
                whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray3[3] = "N";
                whereListP3.add(whereArray3);

                Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
                whereArray4[0] = "Contract Number";
                whereArray4[1] = "PRC_CONTR_NUM";
                whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray4[3] = "N";
                whereListP3.add(whereArray4);

                Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray0[0] = "Account Number";
                columnArray0[1] = "DS_ACCT_NUM";
                columnArray0[2] = BigDecimal.valueOf(20);
                columnArray0[3] = "N";
                columnListP4.add(columnArray0);

                Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray1[0] = "Account Name";
                columnArray1[1] = "DS_ACCT_NM";
                columnArray1[2] = BigDecimal.valueOf(50);
                columnArray1[3] = "N";
                columnListP4.add(columnArray1);

                Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray2[0] = "CSMP Number";
                columnArray2[1] = "CSMP_NUM";
                columnArray2[2] = BigDecimal.valueOf(15);
                columnArray2[3] = "Y";
                columnListP4.add(columnArray2);

                Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray3[0] = "Price List ID";
                columnArray3[1] = "PRC_CATG_CD";
                columnArray3[2] = BigDecimal.valueOf(10);
                columnArray3[3] = "N";
                columnListP4.add(columnArray3);

                Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray4[0] = "Price Contract Number";
                columnArray4[1] = "PRC_CONTR_NUM";
                columnArray4[2] = BigDecimal.valueOf(30);
                columnArray4[3] = "N";
                columnListP4.add(columnArray4);

                Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray5[0] = "Renew CSMP Number";
                columnArray5[1] = "RNW_CSMP_NUM";
                columnArray5[2] = BigDecimal.valueOf(15);
                columnArray5[3] = "N";
                columnListP4.add(columnArray5);

                Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray6[0] = "CSMP Number Comment";
                columnArray6[1] = "CSMP_NUM_CMNT_TXT";
                columnArray6[2] = BigDecimal.valueOf(60);
                columnArray6[3] = "N";
                columnListP4.add(columnArray6);

                Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray7[0] = "Active Flag";
                columnArray7[1] = "CSMP_CONTR_ACTV_FLG";
                columnArray7[2] = BigDecimal.valueOf(6);
                columnArray7[3] = "N";
                columnListP4.add(columnArray7);

                Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray8[0] = "Effective Date From";
                columnArray8[1] = "EFF_FROM_DT";
                columnArray8[2] = BigDecimal.valueOf(12);
                columnArray8[3] = "N";
                columnListP4.add(columnArray8);

                Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray9[0] = "Effective Date To";
                columnArray9[1] = "EFF_THRU_DT";
                columnArray9[2] = BigDecimal.valueOf(12);
                columnArray9[3] = "N";
                columnListP4.add(columnArray9);

                Object[] sortConditionArray0 = new Object[2];
                sortConditionArray0[0] = "DS_ACCT_NUM";
                sortConditionArray0[1] = "ASC";
                sortConditionListP5.add(sortConditionArray0);

                Object[] sortConditionArray1 = new Object[2];
                sortConditionArray1[0] = "CSMP_NUM";
                sortConditionArray1[1] = "ASC";
                sortConditionListP5.add(sortConditionArray1);
            }
        } else if ("OpenWin_TrxAudcVal_01_Gen".equals(eventNm)
                || "OpenWin_TrxAudcVal_02_Gen".equals(eventNm)) {
            if ((PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y1.getValue()) && "OpenWin_TrxAudcVal_01_Gen".equals(eventNm))
                    || (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(scrnMsg.Y.no(eventRow).prcTrxAudcCatgCd_Y2.getValue()) && "OpenWin_TrxAudcVal_02_Gen".equals(eventNm))) {
                suffixP0 = "";
                scrnNmP1 = "Transaction Price Group Popup";

                tblNmP2.append(" SELECT");
                tblNmP2.append(" PG.GLBL_CMPY_CD");
                tblNmP2.append(",PG.EZCANCELFLAG");
                tblNmP2.append(",TO_CHAR(PG.PRC_GRP_PK) PRC_GRP_PK");
                tblNmP2.append(",PG.PRC_GRP_NM");
                tblNmP2.append(",PG.PRC_GRP_DESC_TXT");
                tblNmP2.append(",PG.ACTV_FLG");
                tblNmP2.append(",PGTT.PRC_GRP_TRGT_TP_DESC_TXT");
                tblNmP2.append(",PGTA.PRC_GRP_TRGT_ATTRB_DESC_TXT");
                tblNmP2.append(",PGO.PRC_GRP_OP_DESC_TXT");
                tblNmP2.append(",PGD.PRC_GRP_FROM_TXT");
                tblNmP2.append(",PGD.PRC_GRP_THRU_TXT");
                tblNmP2.append(",PGD.EFF_FROM_DT");
                tblNmP2.append(",PGD.EFF_THRU_DT");
                tblNmP2.append(",PGD.PRC_GRP_DTL_PK");
                tblNmP2.append(" FROM PRC_GRP PG");
                tblNmP2.append(",PRC_GRP_DTL PGD");
                tblNmP2.append(",PRC_GRP_TRGT_TP PGTT");
                tblNmP2.append(",PRC_GRP_TRGT_ATTRB PGTA");
                tblNmP2.append(",PRC_GRP_OP PGO");
                tblNmP2.append(" WHERE");
                tblNmP2.append(" PG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
                tblNmP2.append(" AND PG.EZCANCELFLAG = '").append("0").append("'");
                tblNmP2.append(" AND PG.PRC_GRP_TP_CD = '").append(PRC_GRP_TP.ORDER_CATEGORY_OR_REASON).append("'");
                tblNmP2.append(" AND PG.PRC_GRP_PK = PGD.PRC_GRP_PK");
                tblNmP2.append(" AND PG.EZCANCELFLAG  = PGD.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTT.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_TRGT_TP_CD = PGTT.PRC_GRP_TRGT_TP_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTT.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTA.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_TRGT_ATTRB_CD = PGTA.PRC_GRP_TRGT_ATTRB_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTA.EZCANCELFLAG");
                tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGO.GLBL_CMPY_CD");
                tblNmP2.append(" AND PGD.PRC_GRP_OP_CD = PGO.PRC_GRP_OP_CD");
                tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGO.EZCANCELFLAG");

                Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
                whereArray0[0] = "Price Group PK";
                whereArray0[1] = "PRC_GRP_PK";
                whereArray0[3] = "Y";

                Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
                whereArray1[0] = "Price Group Name";
                whereArray1[1] = "PRC_GRP_NM";
                whereArray1[3] = "Y";

                if ("OpenWin_TrxAudcVal_01_Gen".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.Y.no(eventRow).xxScrItem30Txt_Y1.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                } else if ("OpenWin_TrxAudcVal_02_Gen".equals(eventNm)) {
                    whereArray0[2] = scrnMsg.Y.no(eventRow).xxScrItem30Txt_Y2.getValue();
                    whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
                }
                whereListP3.add(whereArray0);
                whereListP3.add(whereArray1);

                Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
                whereArray2[0] = "Group Description";
                whereArray2[1] = "PRC_GRP_DESC_TXT";
                whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray2[3] = "Y";
                whereListP3.add(whereArray2);

                Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
                whereArray3[0] = "Effective Date From";
                whereArray3[1] = "EFF_FROM_DT";
                whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray3[3] = "N";
                whereListP3.add(whereArray3);

                Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
                whereArray4[0] = "Effective Date To";
                whereArray4[1] = "EFF_THRU_DT";
                whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray4[3] = "N";
                whereListP3.add(whereArray4);

                Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
                whereArray5[0] = "Target Context";
                whereArray5[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
                whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray5[3] = "Y";
                whereListP3.add(whereArray5);

                Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
                whereArray6[0] = "Attribute Name";
                whereArray6[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
                whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray6[3] = "Y";
                whereListP3.add(whereArray6);

                Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
                whereArray7[0] = "Attribute Value From";
                whereArray7[1] = "PRC_GRP_FROM_TXT";
                whereArray7[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray7[3] = "N";
                whereListP3.add(whereArray7);

                Object[] whereArray8 = new Object[CMN_PRM_WHERE_NUM];
                whereArray8[0] = "Attribute Value To";
                whereArray8[1] = "PRC_GRP_THRU_TXT";
                whereArray8[2] = scrnMsg.xxPopPrm_ZA.getValue();
                whereArray8[3] = "N";
                whereListP3.add(whereArray8);

                Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray0[0] = "Price Group PK";
                columnArray0[1] = "PRC_GRP_PK";
                columnArray0[2] = BigDecimal.valueOf(15);
                columnArray0[3] = "Y";
                columnListP4.add(columnArray0);

                Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray1[0] = "Price Group Name";
                columnArray1[1] = "PRC_GRP_NM";
                columnArray1[2] = BigDecimal.valueOf(21);
                columnArray1[3] = "N";
                columnListP4.add(columnArray1);

                Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray2[0] = "Active";
                columnArray2[1] = "ACTV_FLG";
                columnArray2[2] = BigDecimal.valueOf(5);
                columnArray2[3] = "N";
                columnListP4.add(columnArray2);

                Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray3[0] = "Target Context";
                columnArray3[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
                columnArray3[2] = BigDecimal.valueOf(15);
                columnArray3[3] = "N";
                columnListP4.add(columnArray3);

                Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray4[0] = "Attribute Name";
                columnArray4[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
                columnArray4[2] = BigDecimal.valueOf(18);
                columnArray4[3] = "N";
                columnListP4.add(columnArray4);

                Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray5[0] = "Operator";
                columnArray5[1] = "PRC_GRP_OP_DESC_TXT";
                columnArray5[2] = BigDecimal.valueOf(7);
                columnArray5[3] = "N";
                columnListP4.add(columnArray5);

                Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray6[0] = "Attribute Value From";
                columnArray6[1] = "PRC_GRP_FROM_TXT";
                columnArray6[2] = BigDecimal.valueOf(15);
                columnArray6[3] = "N";
                columnListP4.add(columnArray6);

                Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray7[0] = "Attribute Value To";
                columnArray7[1] = "PRC_GRP_THRU_TXT";
                columnArray7[2] = BigDecimal.valueOf(15);
                columnArray7[3] = "N";
                columnListP4.add(columnArray7);

                Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray8[0] = "Effective Date From";
                columnArray8[1] = "EFF_FROM_DT";
                columnArray8[2] = BigDecimal.valueOf(13);
                columnArray8[3] = "N";
                columnListP4.add(columnArray8);

                Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
                columnArray9[0] = "Effective Date To";
                columnArray9[1] = "EFF_THRU_DT";
                columnArray9[2] = BigDecimal.valueOf(13);
                columnArray9[3] = "N";
                columnListP4.add(columnArray9);


                Object[] sortConditionArray1 = new Object[2];
                sortConditionArray1[0] = "PRC_GRP_PK";
                sortConditionArray1[1] = "ASC";
                sortConditionListP5.add(sortConditionArray1);

                Object[] sortConditionArray2 = new Object[2];
                sortConditionArray2[0] = "PRC_GRP_DTL_PK";
                sortConditionArray2[1] = "ASC";
                sortConditionListP5.add(sortConditionArray2);
            }
        } else if ("OpenWin_PrcFormula".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Formula Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PF.GLBL_CMPY_CD");
            tblNmP2.append(",PF.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PF.PRC_FMLA_PK) PRC_FMLA_PK");
            tblNmP2.append(",PF.ACTV_FLG");
            tblNmP2.append(",PF.PRC_FMLA_NM");
            tblNmP2.append(",PF.PRC_FMLA_DESC_TXT");
            tblNmP2.append(",PF.EFF_FROM_DT");
            tblNmP2.append(",PF.EFF_THRU_DT");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PFO.PRC_FMLA_OP_DESC_TXT");
            tblNmP2.append(",PF.PRC_FMLA_NUM");
            tblNmP2.append(" FROM PRC_FMLA PF");
            tblNmP2.append(",PRC_CATG PC");
            tblNmP2.append(",PRC_FMLA_OP PFO");
            tblNmP2.append(",PRC_FMLA_TP PFT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PF.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PF.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFT.EZCANCELFLAG");
            tblNmP2.append(" AND PF.PRC_FMLA_TP_CD = PFT.PRC_FMLA_TP_CD");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PC.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PC.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PF.PRC_CATG_CD = PC.PRC_CATG_CD(+)");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFO.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFO.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PF.PRC_FMLA_OP_CD = PFO.PRC_FMLA_OP_CD(+)");
            tblNmP2.append(" AND (PF.PRC_FMLA_TP_CD = '").append(PRC_FMLA_TP.PRICE_LIST).append("'");
            tblNmP2.append(" AND (EXISTS (SELECT '1'");
            tblNmP2.append(" FROM PRC_LIST_EQUIP PLE");
            tblNmP2.append(" WHERE PLE.GLBL_CMPY_CD = PF.GLBL_CMPY_CD");
            tblNmP2.append(" AND PLE.EZCANCELFLAG = PF.EZCANCELFLAG");
            tblNmP2.append(" AND PLE.PRC_CATG_CD = PF.PRC_CATG_CD");
            tblNmP2.append(" AND PLE.PRC_FMLA_PK IS NULL");
            tblNmP2.append(" AND PLE.PRC_QLFY_VAL_TXT = '").append(scrnMsg.A.no(eventRow).prcQlfyValTxt_PA.getValue()).append("'");
            if (ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_BK)) {
                tblNmP2.append(" AND PLE.PRC_CATG_CD <> '").append(scrnMsg.prcCatgCd_BK.getValue()).append("'");
            }
            tblNmP2.append(" )"); // Select Close
            tblNmP2.append(" )"); // Exists Close
            tblNmP2.append(" OR");
            tblNmP2.append(" (PF.PRC_FMLA_TP_CD = '").append(PRC_FMLA_TP.STANDARD_COST).append("'");
            tblNmP2.append(" AND EXISTS (SELECT '1'");
            tblNmP2.append(" FROM ALL_MDSE_V M");
            tblNmP2.append(" WHERE M.GLBL_CMPY_CD = PF.GLBL_CMPY_CD");
            tblNmP2.append(" AND M.EZCANCELFLAG = PF.EZCANCELFLAG");
            tblNmP2.append(" AND M.MDSE_CD = '").append(scrnMsg.A.no(eventRow).prcQlfyValTxt_PA.getValue()).append("'");
            tblNmP2.append(" )"); // Select Close
            tblNmP2.append(" )"); // Exists Close
            tblNmP2.append(" )"); // Or Close

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Formula PK";
            whereArray0[1] = "PRC_FMLA_PK";
            whereArray0[2] = toStr(scrnMsg.A.no(eventRow).prcFmlaPk_PA.getValue());
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Formula Name";
            whereArray1[1] = "PRC_FMLA_NM";
            whereArray1[2] = scrnMsg.A.no(eventRow).prcFmlaNm_PA.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Formula Description";
            whereArray2[1] = "PRC_FMLA_DESC_TXT";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Formula Type Name";
            whereArray3[1] = "PRC_FMLA_TP_DESC_TXT";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Effective Date From";
            whereArray4[1] = "EFF_FROM_DT";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Effective Date To";
            whereArray5[1] = "EFF_THRU_DT";
            whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray5[3] = "N";
            whereListP3.add(whereArray5);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Formula PK";
            columnArray0[1] = "PRC_FMLA_PK";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(5);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Formula Name";
            columnArray2[1] = "PRC_FMLA_NM";
            columnArray2[2] = BigDecimal.valueOf(21);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Formula Description";
            columnArray3[1] = "PRC_FMLA_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(21);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Effective Date From";
            columnArray4[1] = "EFF_FROM_DT";
            columnArray4[2] = BigDecimal.valueOf(13);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Effective Date To";
            columnArray5[1] = "EFF_THRU_DT";
            columnArray5[2] = BigDecimal.valueOf(13);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Source Price List Name";
            columnArray6[1] = "PRC_CATG_NM";
            columnArray6[2] = BigDecimal.valueOf(21);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Formula Operation Name";
            columnArray7[1] = "PRC_FMLA_OP_DESC_TXT";
            columnArray7[2] = BigDecimal.valueOf(15);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Formula Text";
            columnArray8[1] = "PRC_FMLA_NUM";
            columnArray8[2] = BigDecimal.valueOf(10);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);


            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_FMLA_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_Model".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Service Model Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD GLBL_CMPY_CD");
            tblNmP2.append(",MN.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(MN.T_MDL_ID) T_MDL_ID");
            tblNmP2.append(",MN.T_MDL_NM");
            tblNmP2.append(" FROM MDL_NM MN");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND MN.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Service Model ID";
            whereArray0[1] = "T_MDL_ID";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Service Model Name";
            whereArray1[1] = "T_MDL_NM";
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.B.no(eventRow).mdlNm_PB.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D1.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.C.no(eventRow).mdlNm_PC.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D2.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                whereArray1[2] = scrnMsg.D.no(eventRow).mdlNm_PD.getValue();
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.E.no(eventRow).mdlNm_PE.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D3.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.F.no(eventRow).mdlNm_PF.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D4.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.H.no(eventRow).mdlNm_PH.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D5.getValue();
                }
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Service Model ID";
            columnArray0[1] = "T_MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(22);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Service Model Name";
            columnArray1[1] = "T_MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "T_MDL_ID";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_SupplyPlan".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Supply Plan Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" SAP.GLBL_CMPY_CD");
            tblNmP2.append(",SAP.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(SAP.SPLY_AGMT_PLN_PK) SPLY_AGMT_PLN_PK");
            tblNmP2.append(",SAP.ACTV_FLG");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_NM");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_SHORT_NM");
            tblNmP2.append(",SAPT.SPLY_AGMT_PLN_TP_DESC_TXT");
            tblNmP2.append(",SADT.SPLY_AGMT_DOC_TP_DESC_TXT");
            tblNmP2.append(",SAP.EFF_FROM_DT");
            tblNmP2.append(",SAP.EFF_THRU_DT");
            tblNmP2.append(" FROM SPLY_AGMT_PLN SAP");
            tblNmP2.append(",SPLY_AGMT_PLN_TP SAPT");
            tblNmP2.append(",SPLY_AGMT_DOC_TP SADT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" SAP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SAPT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SAPT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_PLN_TP_CD = SAPT.SPLY_AGMT_PLN_TP_CD");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SADT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SADT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_DOC_TP_CD = SADT.SPLY_AGMT_DOC_TP_CD");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Plan ID";
            whereArray0[1] = "SPLY_AGMT_PLN_PK";
            whereArray0[2] = toStr(scrnMsg.E.no(eventRow).splyAgmtPlnPk_PE.getValue()).toString();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Plan Name";
            whereArray1[1] = "SPLY_AGMT_PLN_NM";
            whereArray1[2] = scrnMsg.E.no(eventRow).splyAgmtPlnNm_PE.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Plan Short Name";
            whereArray2[1] = "SPLY_AGMT_PLN_SHORT_NM";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

//            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
//            whereArray3[0] = "Plan Description";
//            whereArray3[1] = "SPLY_AGMT_PLN_DESC_TXT";
//            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
//            whereArray3[3] = "Y";
//            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Plan Type Name";
            whereArray4[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "Y";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Document Type Name";
            whereArray5[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray5[3] = "Y";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Effective Date From";
            whereArray6[1] = "EFF_FROM_DT";
            whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray6[3] = "N";
            whereListP3.add(whereArray6);

            Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
            whereArray7[0] = "Effective Date To";
            whereArray7[1] = "EFF_THRU_DT";
            whereArray7[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray7[3] = "N";
            whereListP3.add(whereArray7);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Plan ID";
            columnArray0[1] = "SPLY_AGMT_PLN_PK";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(5);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Plan Name";
            columnArray2[1] = "SPLY_AGMT_PLN_NM";
            columnArray2[2] = BigDecimal.valueOf(32);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Plan Short Name";
            columnArray3[1] = "SPLY_AGMT_PLN_SHORT_NM";
            columnArray3[2] = BigDecimal.valueOf(18);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

//            Object[] columnArray4 = new Object[CMN_PRM_WHERE_NUM];
//            columnArray4[0] = "Plan Description/Note";
//            columnArray4[1] = "SPLY_AGMT_PLN_DESC_TXT";
//            columnArray4[2] = BigDecimal.valueOf(50);
//            columnArray4[3] = "N";
//            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Plan Type Name";
            columnArray5[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            columnArray5[2] = BigDecimal.valueOf(15);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Document Type Name";
            columnArray6[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            columnArray6[2] = BigDecimal.valueOf(18);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date From";
            columnArray7[1] = "EFF_FROM_DT";
            columnArray7[2] = BigDecimal.valueOf(13);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date To";
            columnArray8[1] = "EFF_THRU_DT";
            columnArray8[2] = BigDecimal.valueOf(13);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SPLY_AGMT_PLN_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_DsAcctCust".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Lease Account# Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" S.GLBL_CMPY_CD");
            tblNmP2.append(",S.EZCANCELFLAG");
            tblNmP2.append(",S.SELL_TO_CUST_CD");
            tblNmP2.append(",S.DS_ACCT_NM");
            tblNmP2.append(" FROM SELL_TO_CUST S");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" S.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND S.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND S.DS_ACCT_TP_CD = '").append(DS_ACCT_TP.CUSTOMER).append("'");
            tblNmP2.append(" AND S.DS_ACCT_CLS_CD = '").append(DS_ACCT_CLS.LEASE_CO).append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Lease Account#";
            whereArray0[1] = "SELL_TO_CUST_CD";
            whereArray0[2] = scrnMsg.F.no(eventRow).dsAcctNum_PF.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Lease Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = scrnMsg.F.no(eventRow).dsAcctNm_PF.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_WHERE_NUM];
            columnArray0[0] = "Lease Account#";
            columnArray0[1] = "SELL_TO_CUST_CD";
            columnArray0[2] = BigDecimal.valueOf(28);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_WHERE_NUM];
            columnArray1[0] = "Lease Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(60);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SELL_TO_CUST_CD";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_MtrPkg".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Meter Package Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PMP.GLBL_CMPY_CD");
            tblNmP2.append(",PMP.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PMPM.MDL_ID) MDL_ID");
            tblNmP2.append(",PMPM.MDL_NM");
            tblNmP2.append(",TO_CHAR(PMP.PRC_MTR_PKG_PK) PRC_MTR_PKG_PK");
            tblNmP2.append(",PMP.PRC_MTR_PKG_PK PRC_MTR_PKG_PK_SORT");
            tblNmP2.append(",PMP.PRC_MTR_PKG_NM");
            tblNmP2.append(",PMP.PRC_MTR_PKG_DESC_TXT");
            tblNmP2.append(" FROM PRC_MTR_PKG PMP");
            tblNmP2.append(",PRC_MTR_PKG_MDL PMPM");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PMP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PMP.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PMP.GLBL_CMPY_CD = PMPM.GLBL_CMPY_CD");
            tblNmP2.append(" AND PMP.EZCANCELFLAG = PMPM.EZCANCELFLAG");
            tblNmP2.append(" AND PMP.PRC_MTR_PKG_PK = PMPM.PRC_MTR_PKG_PK");
            // QC#8829
            if (!(TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue()) && PRC_RATE_TP.ANNUAL.equals(scrnMsg.B.no(eventRow).prcRateTpCd_PB.getValue()))) {
                tblNmP2.append(" AND (EXISTS (SELECT '1'");
                tblNmP2.append(" FROM PRC_MTR_PKG_BLLG_MTR PMPBM");
                tblNmP2.append(",PRC_MTR_PKG_PHYS_MTR PMPPM");
                tblNmP2.append(" WHERE PMPBM.GLBL_CMPY_CD = PMP.GLBL_CMPY_CD");
                tblNmP2.append(" AND PMPBM.EZCANCELFLAG = PMP.EZCANCELFLAG");
                tblNmP2.append(" AND PMPBM.PRC_MTR_PKG_PK = PMP.PRC_MTR_PKG_PK");
                tblNmP2.append(" AND PMPBM.GLBL_CMPY_CD = PMPPM.GLBL_CMPY_CD");
                tblNmP2.append(" AND PMPBM.EZCANCELFLAG = PMPPM.EZCANCELFLAG");
                tblNmP2.append(" AND PMPBM.PRC_MTR_PKG_BLLG_MTR_PK = PMPPM.PRC_MTR_PKG_BLLG_MTR_PK");
                tblNmP2.append(" )"); // Select Close
                tblNmP2.append(" )"); // Exist Close
            }

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Meter Package Name";
            whereArray0[1] = "PRC_MTR_PKG_NM";
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray0[2] = scrnMsg.B.no(eventRow).prcMtrPkgNm_PB.getValue();
                } else {
                    whereArray0[2] = scrnMsg.prcMtrPkgNm_D1.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray0[2] = scrnMsg.C.no(eventRow).prcMtrPkgNm_PC.getValue();
                } else {
                    whereArray0[2] = scrnMsg.prcMtrPkgNm_D2.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray0[2] = scrnMsg.E.no(eventRow).prcMtrPkgNm_PE.getValue();
                } else {
                    whereArray0[2] = scrnMsg.prcMtrPkgNm_D3.getValue();
                }
            }
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Service Model Name";
            whereArray1[1] = "MDL_NM";
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.B.no(eventRow).mdlNm_PB.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D1.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.C.no(eventRow).mdlNm_PC.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D2.getValue();
                }
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                if (eventRow >= 0) {
                    whereArray1[2] = scrnMsg.E.no(eventRow).mdlNm_PE.getValue();
                } else {
                    whereArray1[2] = scrnMsg.mdlNm_D3.getValue();
                }
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

//            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
//            whereArray2[0] = "Unit of Measure";
//            whereArray2[1] = "PRC_SVC_UOM_TXT";
//            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
//            whereArray2[3] = "Y";
//            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Meter Package PK";
            whereArray3[1] = "PRC_MTR_PKG_PK";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Model ID";
            columnArray0[1] = "MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Service Model Name";
            columnArray1[1] = "MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Meter Package Name";
            columnArray2[1] = "PRC_MTR_PKG_NM";
            columnArray2[2] = BigDecimal.valueOf(25);
            columnArray2[3] = "Y";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Meter Package Description";
            columnArray3[1] = "PRC_MTR_PKG_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(25);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Meter Package PK";
            columnArray4[1] = "PRC_MTR_PKG_PK";
            columnArray4[2] = BigDecimal.valueOf(12);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "PRC_MTR_PKG_PK_SORT";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

        } else if ("OpenWin_MtrLb".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Meter Type Popup";
            String slsDt = ZYPDateUtil.getSalesDate();

            tblNmP2.append(" SELECT");
            tblNmP2.append(" DISTINCT");
            tblNmP2.append(" MPV.GLBL_CMPY_CD");
            tblNmP2.append(",MPV.EZCANCELFLAG");
            tblNmP2.append(",BLLG_MTR_LB_CD");
            tblNmP2.append(",BLLG_MTR_LB_NM");
            tblNmP2.append(" FROM PRC_MTR_PKG_MTR_STRU_V MPV");
            tblNmP2.append(" ,PRC_MTR_PKG P");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" MPV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND MPV.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND MPV.MDL_MTR_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MDL_MTR_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.BLLG_MTR_MAP_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.BLLG_MTR_MAP_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.MTR_PKG_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MTR_PKG_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.MTR_PKG_MDL_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MTR_PKG_MDL_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.GLBL_CMPY_CD = P.GLBL_CMPY_CD");
            tblNmP2.append(" AND MPV.EZCANCELFLAG = P.EZCANCELFLAG");
            tblNmP2.append(" AND MPV.PRC_MTR_PKG_PK = P.PRC_MTR_PKG_PK");
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.B.no(eventRow).prcMtrPkgNm_PB)) {
	                tblNmP2.append(" AND P.PRC_MTR_PKG_NM = '").append(scrnMsg.B.no(eventRow).prcMtrPkgNm_PB.getValue()).append("'");
            	}
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.C.no(eventRow).prcMtrPkgNm_PC)) {
	                tblNmP2.append(" AND P.PRC_MTR_PKG_NM = '").append(scrnMsg.C.no(eventRow).prcMtrPkgNm_PC.getValue()).append("'");
            	}
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.E.no(eventRow).prcMtrPkgNm_PE)) {
	                tblNmP2.append(" AND P.PRC_MTR_PKG_NM = '").append(scrnMsg.E.no(eventRow).prcMtrPkgNm_PE.getValue()).append("'");
            	}
            }
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.B.no(eventRow).mdlNm_PB)) {
	                tblNmP2.append(" AND MPV.MDL_NM = '").append(scrnMsg.B.no(eventRow).mdlNm_PB.getValue()).append("'");
            	}
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.C.no(eventRow).mdlNm_PC)) {
	                tblNmP2.append(" AND MPV.MDL_NM = '").append(scrnMsg.C.no(eventRow).mdlNm_PC.getValue()).append("'");
            	}
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.E.no(eventRow).mdlNm_PE)) {
	                tblNmP2.append(" AND MPV.MDL_NM = '").append(scrnMsg.E.no(eventRow).mdlNm_PE.getValue()).append("'");
            	}
            }
            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Meter Type";
            whereArray0[1] = "BLLG_MTR_LB_CD";
            whereArray0[2] = "";
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Meter Type Name";
            whereArray1[1] = "BLLG_MTR_LB_NM";
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	whereArray1[2] = scrnMsg.B.no(eventRow).mtrLbNm_PB.getValue();
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	whereArray1[2] = scrnMsg.C.no(eventRow).mtrLbNm_PC.getValue();
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            	whereArray1[2] = scrnMsg.E.no(eventRow).mtrLbNm_PE.getValue();
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);
            
            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Meter Type";
            columnArray0[1] = "BLLG_MTR_LB_CD";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Meter Type Name";
            columnArray1[1] = "BLLG_MTR_LB_NM";
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "BLLG_MTR_LB_CD";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);
            
        } else if ("OpenWin_QualifierValueProd-1".equals(eventNm)
        		|| "OpenWin_QualifierValueProd-2".equals(eventNm)
        		|| "OpenWin_QualifierValueProd-3".equals(eventNm)
        		|| "OpenWin_QualifierValueProd-4".equals(eventNm)
        		|| "OpenWin_QualifierValueProd-5".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Product Hierarchy Popup";

            String tableNm = "PROD_CTRL";
            String columnCdNm = "PROD_CTRL_CD";
            String columnNmNm = "PROD_CTRL_DESC_TXT";
            
            tblNmP2.append(" SELECT");
            tblNmP2.append(" DISTINCT");
            tblNmP2.append(" H.GLBL_CMPY_CD");
            tblNmP2.append(",H.EZCANCELFLAG");
            tblNmP2.append(",H.").append(columnCdNm);
            tblNmP2.append(",H.").append(columnNmNm);
            tblNmP2.append(" FROM ").append(tableNm).append(" H ");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" H.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND H.EZCANCELFLAG = '").append("0").append("'");
            if ("OpenWin_QualifierValueProd-1".equals(eventNm)) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP).append("'");
            } else if ("OpenWin_QualifierValueProd-2".equals(eventNm)) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE).append("'");
            } else if ("OpenWin_QualifierValueProd-3".equals(eventNm)) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("'");
            } else if ("OpenWin_QualifierValueProd-4".equals(eventNm)) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3).append("'");
            } else if ("OpenWin_QualifierValueProd-5".equals(eventNm)) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4).append("'");
            }
            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Prod Hierarchy";
            whereArray0[1] = columnCdNm;
            whereArray0[2] = scrnMsg.A.no(eventRow).prcQlfyValTxt_PA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Prod Hierarchy Name";
            whereArray1[1] = columnNmNm;
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRow).mdseDescShortTxt_PA)) {
            	whereArray1[2] = scrnMsg.A.no(eventRow).mdseDescShortTxt_PA.getValue().toUpperCase();
            } else {
            	whereArray1[2] = scrnMsg.A.no(eventRow).mdseDescShortTxt_PA.getValue();
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);
            
            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Prod Hierarchy";
            columnArray0[1] = columnCdNm;
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Prod Hierarchy Name";
            columnArray1[1] = columnNmNm;
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = columnCdNm;
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

        } else if ("OpenWin_ContrNum".equals(eventNm)) {

            suffixP0 = "";
            scrnNmP1 = "Price Contract Search Popup";

            tblNmP2.append(" ");
            tblNmP2.append("SELECT ");
            tblNmP2.append("    PC.EZCANCELFLAG ");
            tblNmP2.append("   ,PC.GLBL_CMPY_CD ");
            tblNmP2.append("   ,PC.PRC_CONTR_PK ");
            tblNmP2.append("   ,PC.PRC_CONTR_NUM ");
            tblNmP2.append("   ,PC.PRC_CONTR_NM ");
            tblNmP2.append("   ,PC.PRC_CONTR_CUST_BID_NUM ");
            tblNmP2.append("   ,PC.PRC_CONTR_SHORT_DESC_TXT ");
            tblNmP2.append("   ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG ");
            tblNmP2.append("   ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT, 'YYYYMMDD'), 'MM/DD/YYYY') EFF_FROM_DT ");
            tblNmP2.append("   ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT, 'YYYYMMDD'), 'MM/DD/YYYY') EFF_THRU_DT ");
            tblNmP2.append("FROM ");
            tblNmP2.append("    PRC_CONTR PC ");
            tblNmP2.append("WHERE ");
            tblNmP2.append("    PC.EZCANCELFLAG = '0' ");
            tblNmP2.append("AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

            Object[] whereArray0 = new Object[4];

            whereArray0[0] = "Price Contract#";
            whereArray0[1] = "PRC_CONTR_NUM";
            if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)) {
                whereArray0[2] = scrnMsg.prcContrNum_H1.getValue();
            }
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Price Contract Name";
            whereArray1[1] = "PRC_CONTR_NM";
            if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNm_H1)) {
                whereArray1[2] = scrnMsg.prcContrNm_H1.getValue();
            }
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[4];
            whereArray2[0] = "Bid Num";
            whereArray2[1] = "PRC_CONTR_CUST_BID_NUM";
            whereArray2[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[4];
            whereArray3[0] = "Line of Business";
            whereArray3[1] = "LINE_BIZ_TP_CD";
            whereArray3[3] = ZYPConstant.FLG_OFF_N;
            whereListP3.add(whereArray3);

            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Contract ID";
            columnArray0[1] = "PRC_CONTR_PK";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Price Contract #";
            columnArray1[1] = "PRC_CONTR_NUM";
            columnArray1[2] = BigDecimal.valueOf(35);
            columnArray1[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[4];
            columnArray2[0] = "Price Contract Name";
            columnArray2[1] = "PRC_CONTR_NM";
            columnArray2[2] = BigDecimal.valueOf(35);
            columnArray2[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[4];
            columnArray3[0] = "Status";
            columnArray3[1] = "ACTV_FLG";
            columnArray3[2] = BigDecimal.valueOf(7);
            columnArray3[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[4];
            columnArray4[0] = "Eff From Date";
            columnArray4[1] = "EFF_FROM_DT";
            columnArray4[2] = BigDecimal.valueOf(9);
            columnArray4[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[4];
            columnArray5[0] = "Eff Thru Date";
            columnArray5[1] = "EFF_THRU_DT";
            columnArray5[2] = BigDecimal.valueOf(9);
            columnArray5[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray5);

            Object[] sortCondArray0 = new Object[2];
            sortCondArray0[0] = "PRC_CONTR_NUM";
            sortCondArray0[1] = "";
            sortConditionListP5.add(sortCondArray0);

            Object[] sortCondArray1 = new Object[2];
            sortCondArray1[0] = "PRC_CONTR_NM";
            sortCondArray1[1] = "";
            sortConditionListP5.add(sortCondArray1);
        // 2018/11/17 QC#29147 Add Start
        } else if ("OpenWin_PrcListBand".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Price List Band Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PLB.GLBL_CMPY_CD GLBL_CMPY_CD");
            tblNmP2.append(",PLB.EZCANCELFLAG");
            tblNmP2.append(",PLB.PRC_LIST_BAND_CD");
            tblNmP2.append(",PLB.PRC_LIST_BAND_DESC_TXT");
            tblNmP2.append(" FROM PRC_LIST_BAND PLB");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PLB.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PLB.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price List Band Code";
            whereArray0[1] = "PRC_LIST_BAND_CD";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Description";
            whereArray1[1] = "PRC_LIST_BAND_DESC_TXT";
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                whereArray1[2] = scrnMsg.B.no(eventRow).prcListBandDescTxt_PB.getValue();
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                whereArray1[2] = scrnMsg.C.no(eventRow).prcListBandDescTxt_PC.getValue();
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                whereArray1[2] = scrnMsg.E.no(eventRow).prcListBandDescTxt_PE.getValue();
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price List Band Code";
            columnArray0[1] = "PRC_LIST_BAND_CD";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Description";
            columnArray1[1] = "PRC_LIST_BAND_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_LIST_BAND_DESC_TXT";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);
        // 2018/11/17 QC#29147 Add End
        }

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNMWAL6760
     * @param acctNumItem EZDBStringItem
     * @param accNmItem EZDBStringItem
     * @return Object[]
     */
    public static Object[] createArgumentNMWAL6760(EZDBStringItem acctNumItem, EZDBStringItem accNmItem) {

        Object[] param = new Object[2];
        param[0] = acctNumItem;
        param[1] = accNmItem;

        return param;
    }

    /**
     * checkAllItemInput.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void checkAllItemInput(EZDCommonHandler handler, NMAL7010BMsg  scrnMsg, S21UserProfileService userProfileService) {
        boolean isError = false;

        scrnMsg.addCheckItem(scrnMsg.prcCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcListDplyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcCatgDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);

        scrnMsg.addCheckItem(scrnMsg.custBidNum_GA);
        scrnMsg.addCheckItem(scrnMsg.spclCsmpExclArNm_CA);
        scrnMsg.addCheckItem(scrnMsg.grsPrcPct_CB);

        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_CA);

        //// Mandatory
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgNm_H1)) {
            scrnMsg.prcCatgNm_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcCatgNm_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcCatgNm_H1);
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcListTpCd_H1)) {
            scrnMsg.prcListTpCd_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcListTpCd_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcListTpCd_H1);
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
            isError = true;
        }
        
        // QC#2175
        for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.W.no(i).prcCatgNm_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).effFromDt_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).effThruDt_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).subPrcPrtyNum_SW);

            // Mandatory
            if (!ZYPCommonFunc.hasValue(scrnMsg.W.no(i).prcCatgNm_SW)) {
                scrnMsg.W.no(i).prcCatgNm_SW.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.W.no(i).prcCatgNm_SW.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.W.no(i).prcCatgNm_SW);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.W.no(i).effFromDt_SW)) {
                scrnMsg.W.no(i).effFromDt_SW.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.W.no(i).effFromDt_SW.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.W.no(i).effFromDt_SW);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.W.no(i).subPrcPrtyNum_SW)) {
                scrnMsg.W.no(i).subPrcPrtyNum_SW.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.W.no(i).subPrcPrtyNum_SW.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.W.no(i).subPrcPrtyNum_SW);
                isError = true;
            }
        }

        // for error handling
        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            openHeaderTab(scrnMsg);
            // Mod  Start 2017/02/23 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod  End 2017/02/23 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_CX);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effThruDt_CX);

            // Mandatory
            if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).prcCustAudcCatgCd_X1)) {
                scrnMsg.X.no(i).prcCustAudcCatgCd_X1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).prcCustAudcCatgCd_X1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.X.no(i).prcCustAudcCatgCd_X1);
                isError = true;
            }
            if (!PRC_CUST_AUDC_CATG.PUBLIC.equals(scrnMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxScrItem30Txt_X1)) {
                    scrnMsg.X.no(i).xxScrItem30Txt_X1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).xxScrItem30Txt_X1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
                    isError = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).effFromDt_CX)) {
                scrnMsg.X.no(i).effFromDt_CX.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).effFromDt_CX.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_CX);
                isError = true;
            }
        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            // ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_CUST_AUDC);
            openCustAudcTab(scrnMsg);
            // Mod Start 2017/02/23 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/23 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        isError = false;
        for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y1);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y2);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxRecNmTxt_Y1);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxRecNmTxt_Y2);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_TY);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effThruDt_TY);

            // Mandatory
            if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1)) {
                scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).xxScrItem30Txt_Y1)) {
                scrnMsg.Y.no(i).xxScrItem30Txt_Y1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).xxScrItem30Txt_Y1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y1);
                isError = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y2)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).xxScrItem30Txt_Y2)) {
                    scrnMsg.Y.no(i).xxScrItem30Txt_Y2.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).xxScrItem30Txt_Y2.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y2);
                    isError = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).effFromDt_TY)) {
                scrnMsg.Y.no(i).effFromDt_TY.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).effFromDt_TY.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_TY);
                isError = true;
            }
        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            // ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_TRX_AUDC);
            openTrxAudcTab(scrnMsg);
            // Mod Start 2017/02/23 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/23 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcListEquipConfigNum_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcListEquipConfigNm_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcListEquipPrcAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).minPrcAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).maxPrcAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcTermAot_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custBidQty_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mlyPmtAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).minLeasePmtAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).maxLeasePmtAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcFmlaPk_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).quotRevAmt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).compCd_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_PA);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_PA);
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).mdlNm_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prcMtrPkgNm_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).minCopyVolCnt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).maxCopyVolCnt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).baseAmt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).minBaseAmt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).maxBaseAmt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).cpcAmtRate_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).minCpcAmtRate_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).maxCpcAmtRate_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).termFromMthAot_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).termThruMthAot_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).quotRevAmt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).compCd_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_PB);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_PB);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).mdlNm_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).prcSvcTierTpCd_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).prcMtrPkgNm_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).prcSvcPlnTpCd_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).prcSvcContrTpCd_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).mtrLbCd_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).avgCopyVolCnt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).minCopyVolCnt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).maxCopyVolCnt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).baseAmt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).minBaseAmt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).maxBaseAmt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).cpcAmtRate_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).minCpcAmtRate_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).maxCpcAmtRate_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).termFromMthAot_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).termThruMthAot_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseCd_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_PC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_PC);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_PD);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdlNm_PD);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).addlChrgPrcAmt_PD);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_PD);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_PD);
            }
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.E.no(i).mdlNm_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).prcMtrPkgNm_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).prcSvcPlnTpCd_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).prcSvcContrTpCd_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).mtrLbCd_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).minCopyVolCnt_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).maxCopyVolCnt_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).totBaseAmt_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).splyBaseAmt_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).cpcAmtRate_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).minCpcAmtRate_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).maxCpcAmtRate_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).termFromMthAot_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).termThruMthAot_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).mdseCd_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).effFromDt_PE);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).effThruDt_PE);
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.F.no(i).prcLeaseCmpyAbbrNm_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).mdlNm_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).minTotFinAmt_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).maxTotFinAmt_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).termFromMthAot_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).termThruMthAot_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).leaseRate_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).effFromDt_PF);
                scrnMsg.addCheckItem(scrnMsg.F.no(i).effThruDt_PF);
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.G.no(i).prcLeaseCmpyAbbrNm_PG);
                scrnMsg.addCheckItem(scrnMsg.G.no(i).dstMileAmt_PG);
                scrnMsg.addCheckItem(scrnMsg.G.no(i).rtrnFeeAmt_PG);
                scrnMsg.addCheckItem(scrnMsg.G.no(i).effFromDt_PG);
                scrnMsg.addCheckItem(scrnMsg.G.no(i).effThruDt_PG);
            }
        } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.H.no(i).mdlNm_PH);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).tiAmt_PH);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).fromMtrCopyVolCnt_PH);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).thruMtrCopyVolCnt_PH);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).effFromDt_PH);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).effThruDt_PH);
            }
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.I.no(i).prcQlfyTpCd_PI);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).prcQlfyValTxt_PI);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).qtyDiscPrcAmt_PI);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).pkgUomCd_PI);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).effFromDt_PI);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).effThruDt_PI);
            }
            for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.J.no(i).qtyDiscDtlQty_PJ);
                scrnMsg.addCheckItem(scrnMsg.J.no(i).pkgUomCd_PJ);
                scrnMsg.addCheckItem(scrnMsg.J.no(i).prcBreakAmt_PJ);
            }
        }

        scrnMsg.putErrorScreen();
    }
    /**
     * btnCtrlSubmit.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     * @param updateAuthFlg boolean
     */
    public static void btnCtrlSubmit(EZDCommonHandler handler, NMAL7010BMsg scrnMsg, boolean updateAuthFlg) {
        // Mod Start 2017/02/23 QC#16011
        if (updateAuthFlg) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        // Mod End 2017/02/23 QC#16011
    }

    /**
     * btnCtrlSubmit.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     */
    public static void btnCtrlDownload(EZDCommonHandler handler, NMAL7010BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_BK)) {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        }
    }

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static String toStr(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }

    // QC#2174 Add Start
    public static Object[] setArgumentOpenWinQtyDisc(NMAL7010BMsg scrnMsg) {

        int idx = scrnMsg.xxCellIdx_QH.getValueInt();

        Object[] param = new Object[MAX_INPUT_PARAM_NUM + 1];

        param[INPUT_AFT_DECL_PNT_DIGIT_NUM] = scrnMsg.aftDeclPntDigitNum.getValue();

        param[INPUT_PRC_QLFY_TP_CD_LIST] = scrnMsg.prcQlfyTpCd_LA;
        param[INPUT_PRC_QLFY_TP_DESC_TXT_LIST] = scrnMsg.prcQlfyTpDescTxt_LA;

        param[INPUT_PRC_QLFY_TP_CD] = scrnMsg.A.no(idx).prcQlfyTpCd_PA.getValue();
        param[INPUT_PRC_QLFY_VAL_TXT] = scrnMsg.A.no(idx).prcQlfyValTxt_PA.getValue();
        param[INPUT_PROD_CTRL_NM] = scrnMsg.A.no(idx).mdseDescShortTxt_PA.getValue();
        param[INPUT_PRC_LIST_EQUIP_PK] = scrnMsg.A.no(idx).prcListEquipPk_PA.getValue();

        param[INPUT_LIST] = scrnMsg.Q;

        return param;
    }
    // QC#2174 End

    public static void checkNewQtyDisc(NMAL7010BMsg scrnMsg, int idx) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).xxYesNoCd_PA)) {
            scrnMsg.setMessageInfo(NMAL7010Constant.NMAM8355E);
        }
    }

    /**
     * Set Popup Argument for NWAL1130.
     * @param scrnMsg NMAL7010BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setArgumentNWAL7040(NMAL7010BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        scrnMsg.xxPopPrm_ZA.clear();

        Object[] param = new Object[FILTER_MAX_PARAM_NUM];
        // Init
        param[FILTER_PRC_LIST_STRU_TP_CD] = scrnMsg.prcListStruTpCd_H1;
        param[FILTER_PRC_CATG_CD] = scrnMsg.prcCatgCd_H2;
        param[FILTER_PRC_CATG_NM] = scrnMsg.prcCatgNm_H2;

        param[FILTER_EFF_FROM_DT_H1] = scrnMsg.effFromDt_Z1;
        param[FILTER_EFF_FROM_DT_H2] = scrnMsg.effFromDt_Z2;
        param[FILTER_EFF_THRU_DT_H1] = scrnMsg.effThruDt_Z1;
        param[FILTER_EFF_THRU_DT_H2] = scrnMsg.effThruDt_Z2;
        param[FILTER_XX_FULL_NM_H1] = scrnMsg.xxFullNm_Z1;
        param[FILTER_XX_FULL_NM_H2] = scrnMsg.xxFullNm_Z2;
        param[FILTER_CRAT_DT_H1] = scrnMsg.cratDt_Z1;
        param[FILTER_CRAT_DT_H2] = scrnMsg.cratDt_Z2;
        param[FILTER_LAST_UPD_DT_H1] = scrnMsg.lastUpdDt_Z1;
        param[FILTER_LAST_UPD_DT_H2] = scrnMsg.lastUpdDt_Z2;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_PRC_LIST_EQUIP_CONFIG_NUM] = scrnMsg.prcListEquipConfigNum_D1;
            param[FILTER_PRC_LIST_EQUIP_CONFIG_NM] = scrnMsg.prcListEquipConfigNm_D1;
            param[FILTER_PRC_QLFY_TP_CD] = scrnMsg.prcQlfyTpCd_D1;
            // 2018/11/27 QC#29319 Mod Start
            //param[FILTER_PRC_QLFY_VAL_TXT] = scrnMsg.prcQlfyValTxt_D1;
            param[FILTER_PRC_QLFY_VAL_TXT] = scrnMsg.xxPrcQlfyValSrchTxt_D1;
            // 2018/11/27 QC#29319 Mod End
            param[FILTER_PKG_UOM_CD] = scrnMsg.pkgUomCd_D1;
            param[FILTER_MDSE_DESC_SHORT_TXT] = scrnMsg.mdseDescShortTxt_Z1;
            // Mod Start 2018/07/17 QC#20267
            // 2018/11/27 QC#29319 Mod Start
            //param[FILTER_MNF_ITEM_CD] = scrnMsg.mnfItemCd_Z1;
            param[FILTER_MNF_ITEM_CD] = scrnMsg.xxMnfItemCdSrchTxt_Z1;
            // 2018/11/27 QC#29319 Mod End
            // Mod End 2018/07/17 QC#20267
            // Mod Start 2016/10/17 QC#15193
//            param[FILTER_COA_MDSE_TP_NM] = scrnMsg.coaMdseTpNm_Z1;
            param[FILTER_COA_MDSE_TP_NM] = scrnMsg.coaProjNm_Z1;
            // Mod End 2016/10/17 QC#15193
            param[FILTER_MDSE_ITEM_TP_NM] = scrnMsg.mdseItemTpNm_Z1;
            param[FILTER_COA_PROD_NM] = scrnMsg.coaProdNm_Z1;
            // 2018/11/27 QC#29319 Mod Start
            //param[FILTER_T_MDL_NM] = scrnMsg.t_MdlNm_Z1;
            param[FILTER_T_MDL_NM] = scrnMsg.xxTMdlNmSrchTxt_Z1;
            // 2018/11/27 QC#29319 Mod End
            param[FILTER_ZEROTH_PROD_CTRL_CD] = scrnMsg.zerothProdCtrlCd_Z1;
            param[FILTER_FIRST_PROD_CTRL_CD] = scrnMsg.firstProdCtrlCd_Z1;
            param[FILTER_SCD_PROD_CTRL_CD] = scrnMsg.scdProdCtrlCd_Z1;
            param[FILTER_THIRD_PROD_CTRL_CD] = scrnMsg.thirdProdCtrlCd_Z1;
            param[FILTER_FRTH_PROD_CTRL_CD] = scrnMsg.frthProdCtrlCd_Z1;
            param[FILTER_PRC_TERM_AOT] = scrnMsg.prcTermAot_Z1;
            param[FILTER_PRC_TERM_UOM_CD] = scrnMsg.prcTermUomCd_Z1;
            param[FILTER_CUST_BID_QTY] = scrnMsg.custBidQty_Z1;
            param[FILTER_PRC_FMLA_NM] = scrnMsg.prcFmlaNm_Z1;
            param[FILTER_OPEN_MKT_FLG] = scrnMsg.openMktFlg_Z1;
            param[FILTER_COMP_CD] = scrnMsg.compCd_Z1;
            param[FILTER_XX_YES_NO_CD] = scrnMsg.xxYesNoCd_Z1;

        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_PRC_RATE_TP_CD] = scrnMsg.prcRateTpCd_D1;
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_D1;
            param[FILTER_PRC_MTR_PKG_NM] = scrnMsg.prcMtrPkgNm_D1;
            param[FILTER_PRC_LIST_MDSE_CD] = scrnMsg.prcListMdseCd_Z1;
            param[FILTER_MDSE_DESC_SHORT_TXT] = scrnMsg.mdseDescShortTxt_Z1;
            param[FILTER_PRC_SVC_PLN_TP_CD] = scrnMsg.prcSvcPlnTpCd_D1;
            param[FILTER_PRC_SVC_CONTR_TP_CD] = scrnMsg.prcSvcContrTpCd_D1;
            param[FILTER_MTR_LB_NM] = scrnMsg.mtrLbNm_D1;
            // 2018/11/17 QC#29147 Mod Start
            // param[FILTER_PRC_LIST_BAND_CD] = scrnMsg.prcListBandCd_D1;
            param[FILTER_PRC_LIST_BAND_DESC_TXT] = scrnMsg.prcListBandDescTxt_D1;
            // 2018/11/17 QC#29147 Mod End
            param[FILTER_TERM_FROM_MTH_AOT] = scrnMsg.termFromMthAot_Z1;
            param[FILTER_TERM_THRU_MTH_AOT] = scrnMsg.termThruMthAot_Z1;
            param[FILTER_PRC_SVC_ZONE_CD] = scrnMsg.prcSvcZoneCd_Z1;
            param[FILTER_MDSE_CD] = scrnMsg.mdseCd_Z1;

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_PRC_SVC_TIER_TP_CD] = scrnMsg.prcSvcTierTpCd_D2;
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_D2;
            param[FILTER_PRC_MTR_PKG_NM] = scrnMsg.prcMtrPkgNm_D2;
            param[FILTER_PRC_SVC_PLN_TP_CD] = scrnMsg.prcSvcPlnTpCd_D2;
            param[FILTER_PRC_SVC_CONTR_TP_CD] = scrnMsg.prcSvcContrTpCd_D2;
            param[FILTER_MTR_LB_NM] = scrnMsg.mtrLbNm_D2;
            // 2018/11/17 QC#29147 Mod Start
            // param[FILTER_PRC_LIST_BAND_CD] = scrnMsg.prcListBandCd_D2;
            param[FILTER_PRC_LIST_BAND_DESC_TXT] = scrnMsg.prcListBandDescTxt_D2;
            // 2018/11/17 QC#29147 Mod End
            param[FILTER_TERM_FROM_MTH_AOT] = scrnMsg.termFromMthAot_Z1;
            param[FILTER_TERM_THRU_MTH_AOT] = scrnMsg.termThruMthAot_Z1;
            param[FILTER_MDSE_CD] = scrnMsg.mdseCd_Z1;

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_MDSE_CD] = scrnMsg.mdseCd_DH;
            param[FILTER_PRC_ADDL_CHRG_TP_CD] = scrnMsg.prcAddlChrgTpCd_DH;
            param[FILTER_MKT_MDL_SEG_CD] = scrnMsg.mktMdlSegCd_DH;
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_DH;
            // Mod Start 2016/09/12 QC#11615
//            param[FILTER_MDSE_NM] = scrnMsg.mdseNm_Z1;
            param[FILTER_MDSE_NM] = scrnMsg.mdseDescShortTxt_Z2;
            // Mod End 2016/09/12 QC#11615

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_D3;
            param[FILTER_PRC_MTR_PKG_NM] = scrnMsg.prcMtrPkgNm_D3;
            param[FILTER_PRC_SVC_PLN_TP_CD] = scrnMsg.prcSvcPlnTpCd_D3;
            param[FILTER_PRC_SVC_CONTR_TP_CD] = scrnMsg.prcSvcContrTpCd_D3;
            param[FILTER_MTR_LB_NM] = scrnMsg.mtrLbNm_D3;
            // 2018/11/17 QC#29147 Mod Start
            // param[FILTER_PRC_LIST_BAND_CD] = scrnMsg.prcListBandCd_D3;
            param[FILTER_PRC_LIST_BAND_DESC_TXT] = scrnMsg.prcListBandDescTxt_D3;
            // 2018/11/17 QC#29147 Mod End
            param[FILTER_SPLY_AGMT_PLN_NM] = scrnMsg.splyAgmtPlnNm_D3;
            param[FILTER_TERM_FROM_MTH_AOT] = scrnMsg.termFromMthAot_Z1;
            param[FILTER_TERM_THRU_MTH_AOT] = scrnMsg.termThruMthAot_Z1;
            param[FILTER_PRC_SVC_ZONE_CD] = scrnMsg.prcSvcZoneCd_Z1;
            param[FILTER_MDSE_CD] = scrnMsg.mdseCd_Z1;

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_PRC_LEASE_CMPY_ABBR_NM] = scrnMsg.prcLeaseCmpyAbbrNm_D4;
            param[FILTER_DS_ACCT_NM] = scrnMsg.dsAcctNm_D4;
            param[FILTER_PRC_PGM_TP_CD] = scrnMsg.prcPgmTpCd_DH;
            param[FILTER_PRC_EQUIP_TP_CD] = scrnMsg.prcEquipTpCd_DH;
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_D4;
            param[FILTER_TERM_FROM_MTH_AOT] = scrnMsg.termFromMthAot_Z1;
            param[FILTER_TERM_THRU_MTH_AOT] = scrnMsg.termThruMthAot_Z1;

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_PRC_LEASE_CMPY_ABBR_NM] = scrnMsg.prcLeaseCmpyAbbrNm_D5;
            param[FILTER_SVC_SEG_CD] = scrnMsg.svcSegCd_DH;
            param[FILTER_PRC_IN_OUT_RG_CD] = scrnMsg.prcInOutRgCd_DH;

        } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            param[FILTER_MDL_NM] = scrnMsg.mdlNm_D5;

        }

        return param;
    }

    public static void openHeaderTab(NMAL7010BMsg scrnMsg) {
        scrnMsg.xxTabProt_D1.clear();
        scrnMsg.xxDplyTab_D1.clear();
    }

    public static void openCustAudcTab(NMAL7010BMsg scrnMsg) {
        openHeaderTab(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_CUST_AUDC);
    }

    public static void openTrxAudcTab(NMAL7010BMsg scrnMsg) {
        openHeaderTab(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_TRX_AUDC);
    }

    public static boolean isHeaderTab(NMAL7010BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxTabProt_D1.getValue())) {
            return false;
        }
        return true;
    }

    public static void prcListRateTypeCtrl(EZDCommonHandler handler, NMAL7010BMsg scrnMsg, int idx, boolean updateAuthFlg) {

        if (PRC_RATE_TP.CPC.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue()) || !ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).mdlNm_PB.setInputProtected(false);
            scrnMsg.B.no(idx).prcMtrPkgNm_PB.setInputProtected(false);
            scrnMsg.B.no(idx).prcListMdseCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).mtrLbNm_PB.setInputProtected(false);
            scrnMsg.B.no(idx).minCopyVolCnt_PB.setInputProtected(false);
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.setInputProtected(false);
            scrnMsg.B.no(idx).cpcAmtRate_PB.setInputProtected(false);
            scrnMsg.B.no(idx).minCpcAmtRate_PB.setInputProtected(false);
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.setInputProtected(false);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.setInputProtected(false);
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.setInputProtected(false);
            // 2018/11/17 QC#29147 Mod End

            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MODEL, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MTR_PKG, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_MDSE, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_MTR_LB, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_BAND, idx, true); // 2018/11/17 QC#29147 Add

        } else if (PRC_RATE_TP.ANNUAL.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).mdlNm_PB.setInputProtected(false);
            scrnMsg.B.no(idx).prcMtrPkgNm_PB.setInputProtected(false);
            scrnMsg.B.no(idx).prcListMdseCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).mtrLbNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).cpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod End

            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MODEL, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MTR_PKG, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_MDSE, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_MTR_LB, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_BAND, idx, false); // 2018/11/17 QC#29147 Add

        } else if (PRC_RATE_TP.BASE_ONLY.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).mdlNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcMtrPkgNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcListMdseCd_PB.setInputProtected(false);
            scrnMsg.B.no(idx).mtrLbNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).cpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod End

            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MODEL, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MTR_PKG, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_MDSE, idx, true);
            handler.setButtonEnabled(NMAL7010Constant.BTN_MTR_LB, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_BAND, idx, false); // 2018/11/17 QC#29147 Add
        }

        // Add Start 2017/02/23 QC#16011
        if (!updateAuthFlg) {
            scrnMsg.B.no(idx).mdlNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcMtrPkgNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcListMdseCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).mtrLbNm_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.setInputProtected(true);
            scrnMsg.B.no(idx).cpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).minCpcAmtRate_PB.setInputProtected(true);
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.setInputProtected(true);
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.setInputProtected(true);
            // 2018/11/17 QC#29147 Mod End

            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MODEL, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_MTR_PKG, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_MDSE, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_MTR_LB, idx, false);
            handler.setButtonEnabled(NMAL7010Constant.BTN_PRC_LIST_BAND, idx, false); // 2018/11/17 QC#29147 Add
        }
        // Add End 2017/02/23 QC#16011
    }

    public static void setTableColor(NMAL7010BMsg scrnMsg) {
        if (isHeaderTab(scrnMsg)) {
            // Sub Price List
            setRowsBGWithClear(scrnMsg, scrnMsg.W, TBL_W);

            // Price Audience
            if (TAB_CUST_AUDC.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                // Customer
                setRowsBGWithClear(scrnMsg, scrnMsg.X, TBL_X_HC1);
                setRowsBGWithClear(scrnMsg, scrnMsg.X, TBL_X_HC2);
            } else if (TAB_TRX_AUDC.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                // Transaction
                setRowsBGWithClear(scrnMsg, scrnMsg.Y, TBL_Y_HT1);
                setRowsBGWithClear(scrnMsg, scrnMsg.Y, TBL_Y_HT2);
            }
        } else {
            // Price List Val
            if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.A, TBL_A);
            } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.B, TBL_B);
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.C, TBL_C);
            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.D, TBL_D);
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.E, TBL_E);
            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.F, TBL_F);
            } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.G, TBL_G);
            } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                setRowsBGWithClear(scrnMsg, scrnMsg.H, TBL_H);
            }
        }
    }

    // 2018/05/08 QC#20269 Add Start
    /**
     * btnCtrlDownloadasTemplate.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7010BMsg
     */
    public static void btnCtrlDownloadasTemplate(EZDCommonHandler handler, NMAL7010BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_BK)) {
            handler.setButtonEnabled(BTN_DOWNLOAD_AS_TEMPLATE, true);
        } else {
            handler.setButtonEnabled(BTN_DOWNLOAD_AS_TEMPLATE, false);
        }
    }
    // 2018/05/08 QC#20269 Add End

    // 2018/08/22 QC#26664 Add Start
    /**
     * csvFileToExcel.
     * @param csvFilePath String
     * @param scrnMsg NMAL7010BMsg
     * @return String
     */
    public static String csvFileToExcel(String csvFilePath, NMAL7010BMsg scrnMsg, String btnName) {

        int[][] excelPriceColArray = new int[0][0];
        List<ZYPExcelColumnStyle> columnStyles = new ArrayList<ZYPExcelColumnStyle>();

        int excelDigitStyle = ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_SEPARATOR_TWODIGIT;
        if (scrnMsg.aftDeclPntDigitNum.getValueInt() == 0) {
            excelDigitStyle = ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_SEPARATOR;
        }

        // Download
        if (BTN_CMN_DWL[1].equals(btnName)) {
            // Price List Structure : Equipment(01)
            if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_EQUIP_DOWNLOAD;
            }
            // Price List Structure : Service(02)
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SVC_DOWNLOAD;
            }
            // Price List Structure : Service Tiers(03)
            if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYYPE_SVC_TIER_DOWNLOAD;
            }
            // Price List Structure : Service Special Changes(04)
            if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DOWNLOAD;
            }
            // Price List Structure : Supply Program(05)
            if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SPLY_PGM_DOWNLOAD;
            }
            // Price List Structure : Lease Rates(06)
            if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_LEASE_RATE_DOWNLOAD;
            }
            // Price List Structure : Lease Return Fees(07)
            if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DOWNLOAD;
            }
            // Price List Structure : Trade Ins(08)
            if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_TRADE_IN_DOWNLOAD;
            }
        }
        // Download As Template
        else if (BTN_DOWNLOAD_AS_TEMPLATE.equals(btnName)) {
            // Price List Structure : Equipment(01)
            if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_EQUIP_DWLDTEMPLATE;
            }
            // Price List Structure : Service(02)
            if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SVC_DWLDTEMPLATE;
            }
            // Price List Structure : Service Tiers(03)
            if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SVC_TIER_DWLDTEMPLATE;
            }
            // Price List Structure : Service Special Changes(04)
            if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DWLDTEMPLATE;
            }
            // Price List Structure : Supply Program(05)
            if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_SPLY_PGM_DWLDTEMPLATE;
            }
            // Price List Structure : Lease Rates(06)
            if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_LEASE_RATE_DWLDTEMPLATE;
            }
            // Price List Structure : Lease Return Fees(07)
            if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DWLDTEMPLATE;
            }
            // Price List Structure : Trade Ins(08)
            if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                excelPriceColArray = EXCEL_COLUMNTYPE_TRADE_IN_DWLDTEMPLATE;
            }
        }

        for (int i = 0; i < excelPriceColArray.length; i++) {
            int excelStyle = excelDigitStyle;
            if (excelPriceColArray[i][1] == DIGIT_OFF) {
                excelStyle = ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_SEPARATOR;
            }
            columnStyles.add(new ZYPExcelColumnStyle(excelPriceColArray[i][0], excelStyle));
        }

        return ZYPExcelUtil.csvFileToExcel(csvFilePath, columnStyles);
    }
    
    // 2018/08/22 QC#26664 Add End
}
