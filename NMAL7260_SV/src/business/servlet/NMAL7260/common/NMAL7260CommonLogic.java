/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260.common;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import parts.common.EZDItemAttribute;
import parts.common.EZDMessageInfo;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.db.PRC_RULE_HDRTMsg;
import business.servlet.NMAL7260.NMAL7260BMsg;
import business.servlet.NMAL7260.NMAL7260_ABMsgArray;
import business.servlet.NMAL7260.NMAL7260_CBMsg;
import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7260CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/12   Fujitsu         Y.Kanefusa      Update          QC#6097
 * 2016/04/14   Fujitsu         Y.Kanefusa      Update          QC#6173
 * 2016/04/19   Fujitsu         W.Honda         Update          QC#6606
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#6939
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/07/11   Fujitsu         W.Honda         Update          QC#8477
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/16   Hitachi         T.Mizuki        Update          QC#14549
 * 2016/09/29   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/09   Fujitsu         T.Murai         Update          S21_NA#6599
 * 2016/11/11   Fujitsu         R.Nakamura      Update          QC#5940
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2017/09/12   Fujitsu         H.Nagashima     Update          QC#20968
 * 2018/04/04   Fujitsu         H.Nagashima     Update          QC#24550
 * 2018/04/05   Fujitsu         H.Nagashima     Update          QC#22595
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569
 * 2018/07/18   Fujitsu         W.Honda         Update          QC#20267
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 * 2019/02/25   Fujitsu         C.Hara          Update          QC#30482
 * 2019/12/26   Fujitsu         C.Hara          Update          QC#55204
 *</pre>
 */
public class NMAL7260CommonLogic {

    // Add Start 2017/02/27 QC#16011
    /**
     * Update Authority Check
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }

    // Add End 2017/02/27 QC#16011
    /**
     * Initial Common Button properties.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7260BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NMAL7260BMsg scrnMsg, S21UserProfileService userProfileService) {

        // Add Start 2017/02/28 QC#16011
        boolean uploadAuthFlg = updateAuthority(userProfileService);
        // Add Start 2017/02/28 QC#16011

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        // QC#5934 2016/05/19 Mod Start
        //handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        // mod start 2016/09/16 CSA QC#14549
        // Mod Start 2017/02/28 QC#16011
        if (!uploadAuthFlg) {
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        } else if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
            // Mod Start 2017/02/28 QC#16011
            // mod end 2016/09/16 CSA QC#14549
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        }
        // QC#5934 2016/05/19 Mod End
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        if (scrnMsg.C.getValidCount() >= 2) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        }

        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        }
    }

    /**
     * scrnProtect.
     * @param scrnMsg NMAL7260BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void scrnProtect(NMAL7260BMsg scrnMsg, S21UserProfileService userProfileService) {

        // Add Start 2017/02/27 QC#16011
        boolean protectFldFlg = !updateAuthority(userProfileService);
        // Add End 2017/02/27 QC#16011

        scrnMsg.prcRulePrcdGrpNm_H1.setInputProtected(true); // QC#9694 2016/06/28 Add
        scrnMsg.cratDt_H1.setInputProtected(true); // QC#9694 2016/06/28 Add
        scrnMsg.xxFullNm_H1.setInputProtected(true); // QC#9694 2016/06/28 Add
        scrnMsg.lastUpdDt_H1.setInputProtected(true); // QC#9694 2016/06/28 Add
        scrnMsg.xxFullNm_H2.setInputProtected(true); // QC#9694 2016/06/28 Add
        // 2019/02/25 QC#30482 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
            scrnMsg.prcRuleHdrPk_H1.setInputProtected(true);
        }else{
            scrnMsg.prcRuleHdrPk_H1.setInputProtected(false);
        }
        // 2019/02/25 QC#30482 Add End
        // Add Satrt 2017/02/27 QC#16011
        scrnMsg.defRulePrcdNum_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleNm_H1.setInputProtected(protectFldFlg);
        scrnMsg.lineBizTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.applyAutoFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleHdrDescTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleCatgCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.ovrdFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.xxFldValTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleAdjLvlCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.actvFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleModTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.effFromDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleAdjTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.effThruDt_H1.setInputProtected(protectFldFlg);
        // Add End 2017/02/27 QC#16011
        // 2018/09/12 QC#9700 add start
        scrnMsg.prcGrpTrxTpCd_H1.setInputProtected(protectFldFlg);
        // 2018/09/12 QC#9700 add end

        // Table Definition
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).prcRuleCondNum_C1.setInputProtected(true);
            scrnMsg.C.no(i).inpObjTpDescTxt_C1.setInputProtected(true);
            // Mod Start 2017/02/27 QC#16011
            if (protectFldFlg) {
                scrnMsg.C.no(i).prcRuleAttrbCd_C1.setInputProtected(true);
                scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(true);
                scrnMsg.C.no(i).inpReqFlg_C1.setInputProtected(true);
            } else if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
                scrnMsg.C.no(i).prcRuleAttrbCd_C1.setInputProtected(true);
                scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(true);
            } else {
                scrnMsg.C.no(i).prcRuleAttrbCd_C1.setInputProtected(false);
                scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
            }
            // Mod End 2017/02/27 QC#16011
        }

        // Table Data
        for (int j = 0; j < scrnMsg.B.length(); j++) {
            scrnMsg.B.no(j).csmpNumCmntTxt_04.setInputProtected(true);
            scrnMsg.B.no(j).prcGrpNm_05.setInputProtected(true);
            scrnMsg.B.no(j).prodCtrlNm_06.setInputProtected(true);
            scrnMsg.B.no(j).prodCtrlNm_07.setInputProtected(true);
            scrnMsg.B.no(j).coaMdseTpDescTxt_08.setInputProtected(true);
            scrnMsg.B.no(j).coaProdDescTxt_09.setInputProtected(true);
            scrnMsg.B.no(j).mdseDescShortTxt_10.setInputProtected(true);
            scrnMsg.B.no(j).dsOrdCatgDescTxt_11.setInputProtected(true);
            scrnMsg.B.no(j).dsOrdTpDescTxt_12.setInputProtected(true);
            scrnMsg.B.no(j).lineBizTpDescTxt_13.setInputProtected(true);
            scrnMsg.B.no(j).prcGrpNm_14.setInputProtected(true);
            scrnMsg.B.no(j).billToAcctNm_16.setInputProtected(true);
            scrnMsg.B.no(j).coaChDescTxt_17.setInputProtected(true);
            scrnMsg.B.no(j).dsAcctClsDescTxt_18.setInputProtected(true);
            scrnMsg.B.no(j).coaBrDescTxt_19.setInputProtected(true);
            scrnMsg.B.no(j).svcCallTpDescTxt_20.setInputProtected(true);
            scrnMsg.B.no(j).dsOrdLineCatgDescTxt_25.setInputProtected(true);
            scrnMsg.B.no(j).mktMdlDescTxt_27.setInputProtected(true);
            scrnMsg.B.no(j).mktMdlSegDescTxt_28.setInputProtected(true);
            scrnMsg.B.no(j).cpoSrcTpDescTxt_29.setInputProtected(true);
            scrnMsg.B.no(j).dsPmtMethDescTxt_31.setInputProtected(true);
            scrnMsg.B.no(j).prcCatgNm_32.setInputProtected(true);
            scrnMsg.B.no(j).prodCtrlNm_34.setInputProtected(true);
            scrnMsg.B.no(j).prodCtrlNm_35.setInputProtected(true);
            scrnMsg.B.no(j).prodCtrlNm_36.setInputProtected(true);
            scrnMsg.B.no(j).rtrnRsnDescTxt_38.setInputProtected(true);
            scrnMsg.B.no(j).shpgSvcLvlDescTxt_39.setInputProtected(true);
            scrnMsg.B.no(j).mdlDescTxt_40.setInputProtected(true); // 2017/08/24 QC#20729 Mod
            scrnMsg.B.no(j).prcSvcZoneDescTxt_41.setInputProtected(true);
            scrnMsg.B.no(j).dsAcctClsDescTxt_42.setInputProtected(true);
            scrnMsg.B.no(j).spclHdlgTpDescTxt_44.setInputProtected(true);
            scrnMsg.B.no(j).coaExtnDescTxt_46.setInputProtected(true);
            scrnMsg.B.no(j).frtCondDescTxt_48.setInputProtected(true);
            scrnMsg.B.no(j).fill40Txt_49.setInputProtected(true);
            scrnMsg.B.no(j).prcGrpNm_53.setInputProtected(true);
            scrnMsg.B.no(j).dsAcctNm_54.setInputProtected(true);
            scrnMsg.B.no(j).coaChDescTxt_55.setInputProtected(true);
            scrnMsg.B.no(j).dsAcctClsDescTxt_56.setInputProtected(true);
            scrnMsg.B.no(j).prcFmlaNm_B1.setInputProtected(true);
            scrnMsg.B.no(j).prcGrpNm_57.setInputProtected(true); // QC#20249 2017/11/10 Add
            // 2018/04/19 QC#22569 add start
            scrnMsg.B.no(j).slsMatGrpDescTxt_59.setInputProtected(true);
            scrnMsg.B.no(j).slsMatGrpDescTxt_60.setInputProtected(true);
            scrnMsg.B.no(j).slsMatGrpDescTxt_61.setInputProtected(true);
            // 2018/04/19 QC#22569 add end
            scrnMsg.B.no(j).mnfItemCd_10.setInputProtected(true); // QC#20267 2018/07/18 Add

            scrnMsg.B.no(j).xxChkBox_B1.setInputProtected(protectFldFlg);

            scrnMsg.B.no(j).prcRuleCondFromTxt_04.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_05.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_06.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_07.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_08.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_09.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_10.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_11.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_12.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_13.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_14.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_15.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_15.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_16.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_17.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_18.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_19.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_20.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).xxSvcCallDt_FR.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).xxSvcCallDt_TH.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_22.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_22.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_24.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_24.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_25.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_26.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_26.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_27.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_28.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_29.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_30.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_30.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_31.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_32.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcDt_FR.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcDt_TH.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_34.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_35.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_36.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).xxRqstDt_FR.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).xxRqstDt_TH.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_38.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_39.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_40.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_41.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_42.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_44.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_45.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_45.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_46.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_48.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_49.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_53.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_54.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_55.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_56.setInputProtected(protectFldFlg);
            // QC#20249 2017/11/10 Add Start
            scrnMsg.B.no(j).prcRuleCondFromTxt_57.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_58.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondThruTxt_58.setInputProtected(protectFldFlg);
            // QC#20249 2017/11/10 Add End
            // 2018/04/19 QC#22569 add start
            scrnMsg.B.no(j).prcRuleCondFromTxt_59.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_60.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleCondFromTxt_61.setInputProtected(protectFldFlg);
            // 2018/04/19 QC#22569 add end

            scrnMsg.B.no(j).prcFmlaPk_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleDtlRate_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).prcRuleDtlTxt_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).effFromDt_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(j).effThruDt_B1.setInputProtected(protectFldFlg);
        }
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Set Button properties.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7260BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setBtnProp(S21CommonHandler handler, NMAL7260BMsg scrnMsg, S21UserProfileService userProfileService) {

        // Add Start 2017/02/27 QC#16011
        boolean protectBtnFlg = updateAuthority(userProfileService);
        boolean protectFldFlg = !protectBtnFlg;

        handler.setButtonEnabled(OPENWIN_PRCDGRP, protectBtnFlg);
        scrnMsg.xxLinkAncr_DP.setInputProtected(protectFldFlg);
        scrnMsg.xxLinkAncr_DP.setValue(ZYPConstant.FLG_ON_Y); // QC#9694 2016/06/28 Add
        if (!protectBtnFlg) {
            handler.setButtonEnabled("InsertRow_TblDef", false);
            handler.setButtonEnabled("DeleteRow_TblDef", false);
            handler.setButtonEnabled("Show", false);
        } else if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
            handler.setButtonEnabled("InsertRow_TblDef", false);
            handler.setButtonEnabled("DeleteRow_TblDef", false);
            handler.setButtonEnabled("Show", false);
        } else {
            handler.setButtonEnabled("InsertRow_TblDef", true);
            if (scrnMsg.C.getValidCount() == 0) {
                handler.setButtonEnabled("DeleteRow_TblDef", false);
            } else {
                handler.setButtonEnabled("DeleteRow_TblDef", true);
            }
            handler.setButtonEnabled("Show", true);
        }

        if (!protectBtnFlg) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        } else if (scrnMsg.C.getValidCount() >= 2) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        }

        handler.setButtonEnabled("OpenWin_Filter", false);
        // QC#20968 add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
            handler.setButtonEnabled("OpenWin_Filter", true);
        }
        // QC#20968 add End
        handler.setButtonEnabled("InsertRow_TblData", false);
        handler.setButtonEnabled("DeleteRow_TblData", false);
        handler.setButtonEnabled("Upload_CSV", false);
        // Add Start 2018/12/04 QC#29321
        handler.setButtonEnabled("SelectAll", false);
        handler.setButtonEnabled("UnSelectAll", false);
        // Add End 2018/12/04 QC#29321
        handler.setButtonEnabled("MassUpd_PrcAdjTbl", false);   // QC#22595 add
        scrnMsg.effThruDt_MU.setInputProtected(true);           // QC#22595 add
        scrnMsg.xxLinkAncr_TD.clear();
        scrnMsg.xxFileData.setInputProtected(true);
        if (DEFAULT_COL_CNT < getColumnCount(scrnMsg)) {
            handler.setButtonEnabled("InsertRow_TblData", true);
            handler.setButtonEnabled("Upload_CSV", true);
            if (scrnMsg.B.getValidCount() > 0) {
//                QC#20968 del
//                handler.setButtonEnabled("OpenWin_Filter", true);
                handler.setButtonEnabled("DeleteRow_TblData", true);
                // Add Start 2018/12/04 QC#29321
                handler.setButtonEnabled("SelectAll", true);
                handler.setButtonEnabled("UnSelectAll", true);
                // Add End 2018/12/04 QC#29321
                handler.setButtonEnabled("MassUpd_PrcAdjTbl", true);    // QC#22595 add
                scrnMsg.effThruDt_MU.setInputProtected(false);          // QC#22595 add
            } else {
                handler.setButtonEnabled("DeleteRow_TblData", false);
                // Add Start 2018/12/04 QC#29321
                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonEnabled("UnSelectAll", false);
                // Add End 2018/12/04 QC#29321
                handler.setButtonEnabled("MassUpd_PrcAdjTbl", false);   // QC#22595 add
                scrnMsg.effThruDt_MU.setInputProtected(true);           // QC#22595 add
            }
            scrnMsg.xxFileData.setInputProtected(false);
            scrnMsg.xxLinkAncr_TD.setValue(ZYPConstant.FLG_ON_Y);
        }
        if (!protectBtnFlg) {
            handler.setButtonEnabled("InsertRow_TblData", false);
            handler.setButtonEnabled("DeleteRow_TblData", false);
//            QC#20968 del
//            handler.setButtonEnabled("OpenWin_Filter", false);
            handler.setButtonEnabled("Upload_CSV", false);
            // Add Start 2018/12/04 QC#29321
            handler.setButtonEnabled("SelectAll", false);
            handler.setButtonEnabled("UnSelectAll", false);
            // Add End 2018/12/04 QC#29321
            scrnMsg.xxFileData.setInputProtected(true);
            scrnMsg.xxLinkAncr_TD.setInputProtected(true);
            handler.setButtonEnabled("MassUpd_PrcAdjTbl", false);   // QC#22595 add
            scrnMsg.effThruDt_MU.setInputProtected(true);           // QC#22595 add
        }

        handler.setButtonEnabled("OpenWin_CoaCh", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PrcGrpCust", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_AcctNumCust", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_CsmpNum", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PrcGrpMat", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ProdCtrl", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ProdCtrl2", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_CoaMdseTp", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_CoaProd", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ItemSearch", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_DSOrdCatg", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_DSOrdTp", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_LineBizTp", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PrcGrpTrx", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_Formula", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ProdCtrl3", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ProdCtrl4", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ProdCtrl5", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PrcGrpCustSold", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_AcctNumCustSold", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_CoaChSold", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_DsAcctClsSold", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_BillTo", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_BillToAcctChnl", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_BillToAcctClss", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_Branch", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_CallType", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_LineCatg", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_MarketMdlNm", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ModelSeg", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_OrderSrc", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PaymentTp", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_PrcList", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_RtnRsnCd", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ServiceLv", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ServiceMdl", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ServiceZone", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_ShipToAcct", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_SpecialHandTp", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_BizUnit", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_FreightTerm", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_FreightZone", protectBtnFlg);
        // Add End 2017/02/27 QC#16011
        handler.setButtonEnabled("OpenWin_PrcGrpMatQtyBrk", protectBtnFlg);// QC#20249 2017/11/10 Add
        // 2018/04/19 QC#22569 add start
        handler.setButtonEnabled("OpenWin_SlsMatGrpDescTxt1", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_SlsMatGrpDescTxt2", protectBtnFlg);
        handler.setButtonEnabled("OpenWin_SlsMatGrpDescTxt3", protectBtnFlg);
        // 2018/04/19 QC#22569 add end
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL7260BMsg
     * @param scrnAMsgAry NMAL7260_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7260BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL7260BMsg
     * @param scrnAMsgAry NMAL7260_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7260BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL7260BMsg
     * @param scrnAMsgAry NMAL7260_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7260BMsg scrnMsg, NMAL7260_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * getColumnTableData
     * @param scrnMsg NMAL7260BMsg
     * @return String
     */
    public static String getColumnTableData(NMAL7260BMsg scrnMsg) {
        String prcRuleAttrbCd = judgeQtyBreak(scrnMsg); // QC#20249 2017/11/10 Add
        BigDecimal bd = BigDecimal.ONE;
        StringBuilder sb = new StringBuilder();
        sb.append("BH0:");
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // setColumnTableData(scrnMsg, bd.add(new BigDecimal(i)), sb); // QC#20249 2017/11/10 Mod 
            setColumnTableData(scrnMsg, bd.add(new BigDecimal(i)), sb, prcRuleAttrbCd);
        }
        if(ZYPCommonFunc.hasValue(prcRuleAttrbCd)){
            sb.append("BH90:");
        }
        sb.append("BH97:");
        sb.append("BH98:");
        sb.append("BH99");

        return sb.toString();
    }

    /**
     * getColumnTableData
     * @param scrnMsg NMAL7260BMsg
     * @return String
     */
    public static int getColumnCount(NMAL7260BMsg scrnMsg) {
        String[] colList = scrnMsg.xxComnColOrdTxt.getValue().split(":");
        return colList.length;
    }

    /**
     * setColumnTableData
     * @param scrnMsg NMAL7260BMsg
     * @param bd BigDecimal
     * @param sb StringBuilder
     */
    //public static void setColumnTableData(NMAL7260BMsg scrnMsg, BigDecimal bd, StringBuilder sb) { // QC#20249 2017/11/10 Mod 
    public static void setColumnTableData(NMAL7260BMsg scrnMsg, BigDecimal bd, StringBuilder sb, String prcRuleAttrbCd) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            BigDecimal column = scrnMsg.C.no(i).prcRuleCondNum_C1.getValue();
            if (bd.compareTo(column) == 0) {
                String attCd = scrnMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(attCd)) {
                    sb.append("BH1:");
                    break;
                } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(attCd)) {
                    sb.append("BH2:");
                    break;
                } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(attCd)) {
                    sb.append("BH3:");
                    break;
                } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(attCd)) {
                    sb.append("BH4:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(attCd)) {
                    sb.append("BH5:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(attCd)) {
                    sb.append("BH6:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(attCd)) {
                    sb.append("BH7:");
                    break;
                } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(attCd)) {
                    sb.append("BH8:");
                    break;
                } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(attCd)) {
                    sb.append("BH9:");
                    break;
                } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(attCd)) {
                    sb.append("BH10:");
                    // 2018/07/17 QC#20267 Add Start
                    sb.append("BH62:");
                    // 2018/07/17 QC#20267 Add End
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(attCd)) {
                    sb.append("BH11:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(attCd)) {
                    sb.append("BH12:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(attCd)) {
                    sb.append("BH13:");
                    break;
                } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(attCd)) {
                    sb.append("BH14:");
                    break;
                } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(attCd)) {
                    sb.append("BH15:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(attCd)) {
                    sb.append("BH16:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(attCd)) {
                    sb.append("BH17:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH18:");
                    break;
                } else if (PRC_RULE_ATTRB.BRANCH.equals(attCd)) {
                    sb.append("BH19:");
                    break;
                } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(attCd)) {
                    sb.append("BH20:");
                    break;
                } else if (PRC_RULE_ATTRB.CALL_DATE.equals(attCd)) {
                    sb.append("BH21:");
                    break;
                } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(attCd)) {
                    sb.append("BH22:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(attCd)) {
                    sb.append("BH24:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(attCd)) {
                    sb.append("BH25:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_QTY.equals(attCd)) {
                    // sb.append("BH26:");
                    if(!PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)){
                        sb.append("BH26:");
                    }
                    break;
                } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(attCd)) {
                    sb.append("BH27:");
                    break;
                } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(attCd)) {
                    sb.append("BH28:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(attCd)) {
                    sb.append("BH29:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(attCd)) {
                    sb.append("BH30:");
                    break;
                } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(attCd)) {
                    sb.append("BH31:");
                    break;
                } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(attCd)) {
                    sb.append("BH32:");
                    break;
                } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(attCd)) {
                    sb.append("BH33:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(attCd)) {
                    sb.append("BH34:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(attCd)) {
                    sb.append("BH35:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(attCd)) {
                    sb.append("BH36:");
                    break;
                } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(attCd)) {
                    sb.append("BH37:");
                    break;
                } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(attCd)) {
                    sb.append("BH38:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(attCd)) {
                    sb.append("BH39:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(attCd)) {
                    sb.append("BH40:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(attCd)) {
                    sb.append("BH41:");
                    break;
                } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH42:");
                    break;
                } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(attCd)) {
                    sb.append("BH44:");
                    break;
                } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(attCd)) {
                    sb.append("BH45:");
                    break;
                } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(attCd)) {
                    sb.append("BH46:");
                    break;
                } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(attCd)) {
                    sb.append("BH48:");
                    break;
                } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(attCd)) {
                    sb.append("BH49:");
                    break;
                } else if (PRC_RULE_ATTRB.FORMULA.equals(attCd)) {
                    sb.append("BH50:");
                    break;
                } else if (PRC_RULE_ATTRB.PERCENT.equals(attCd)) {
                    sb.append("BH51:");
                    break;
                } else if (PRC_RULE_ATTRB.VALUE.equals(attCd)) {
                    sb.append("BH52:");
                    break;
                } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(attCd)) {
                    sb.append("BH53:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(attCd)) {
                    sb.append("BH54:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(attCd)) {
                    sb.append("BH55:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH56:");
                    break;
                // QC#20249 2017/11/10 Add Start
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(attCd)) {
                    sb.append("BH57:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(attCd)) {
                    if (!PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(prcRuleAttrbCd)) {
                        sb.append("BH58:");
                    }
                    break;
                // QC#20249 2017/11/10 Add End
                // 2018/04/19 QC#22569 add start
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(attCd)) {
                    sb.append("BH59:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(attCd)) {
                    sb.append("BH60:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(attCd)) {
                    sb.append("BH61:");
                    break;
                }
                // 2018/04/19 QC#22569 add start
            }
        }
    }

    /**
     * searchCheck
     * @param scrnMsg NMAL7260BMsg
     */
    public static void submitCheck(NMAL7260BMsg scrnMsg) {

        // ALLCheck
        if (LINE_BIZ_TP.ALL.equals(scrnMsg.lineBizTpCd_H1.getValue())) {
            scrnMsg.lineBizTpCd_H1.setErrorInfo(1, NMAM8326E, new String[] {LINE_BIZ_TP.ALL, "Line of Business" });
            scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H1);
        }
        detailCheck(scrnMsg);
        // Effective Date From Must Check
        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue())) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        }

        // Effective Date From To Check(Header)
        String fromDt = scrnMsg.effFromDt_H1.getValue();
        String toDt = scrnMsg.effThruDt_H1.getValue();
        if (!ZYPCommonFunc.hasValue(toDt)) {
            toDt = TO_DATE_MAX;
        }
        if (fromDt.compareTo(toDt) > 0) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM8115E);
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        }

        // Modify check
        for (int k = 0; k < scrnMsg.C.getValidCount(); k++) {
            String prcRuleAttrbCd = scrnMsg.C.no(k).prcRuleAttrbCd_C1.getValue();
            if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                String inpReqFlg = scrnMsg.C.no(k).inpReqFlg_C1.getValue();
                if (!"Y".equals(inpReqFlg)) {
                    scrnMsg.C.no(k).inpReqFlg_C1.setErrorInfo(1, NMAM8317E);
                    scrnMsg.addCheckItem(scrnMsg.C.no(k).inpReqFlg_C1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRuleNm_H1)) {
            scrnMsg.prcRuleNm_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.prcRuleNm_H1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.prcRuleNm_H1);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.defRulePrcdNum_H1)) {
            scrnMsg.defRulePrcdNum_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.defRulePrcdNum_H1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.defRulePrcdNum_H1);
        }

        scrnMsg.addCheckItem(scrnMsg.prcRuleHdrPk_H1);
        scrnMsg.addCheckItem(scrnMsg.defRulePrcdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRuleNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRuleHdrDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxFldValTxt_H1);
        // 2018/09/12 QC#9700 add start
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd_H1);
        // 2018/09/12 QC#9700 add start
        if (!scrnMsg.xxFldValTxt_H1.isError() && ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_H1)) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("xxFldValTxt_H1");
            PRC_RULE_HDRTMsg dssTMsg = new PRC_RULE_HDRTMsg();
            EZDItemAttribute checkAttrb = dssTMsg.getAttr("prcRuleHdrCmntTxt");

            if (checkValDigit(scrnMsg.xxFldValTxt_H1.getValue().length(), checkAttrb)) {
                scrnMsg.xxFldValTxt_H1.setErrorInfo(1, NMAM8350E, new String[] {"Additional Comments", String.valueOf(checkAttrb.getDigit()) });
            }
        }
    }

    /**
     * showCheck
     * @param scrnMsg NMAL7260BMsg
     */
    public static void detailCheck(NMAL7260BMsg scrnMsg) {

        // showCheck
        if (scrnMsg.B.getValidCount() > 0) {
            if (!scrnMsg.xxComnColOrdTxt.getValue().equals(getColumnTableData(scrnMsg))) {
                scrnMsg.setMessageInfo(NMAM8324E);
                throw new EZDFieldErrorException();
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_04);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_05);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_06);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_07);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_08);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_09);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_10);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_11);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_12);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_13);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_14);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_15);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_15);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_16);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_17);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_18);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_19);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_20);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSvcCallDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSvcCallDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_22);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_22);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_24);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_24);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_25);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_26);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_26);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_27);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_28);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_29);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_30);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_30);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_31);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_32);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_34);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_35);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_36);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxRqstDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxRqstDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_38);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_39);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_40);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_41);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_42);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_44);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_45);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_45);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_46);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_48);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_49);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_53);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_54);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_55);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcFmlaPk_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_56);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_57);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_58);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_58);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlRate_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlTxt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
            // 2018/04/19 QC#22569 add start
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_59);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_60);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_61);
            // 2018/04/19 QC#22569 add end
            //QC#24550 add Start
            //Value
            checkNumeric(scrnMsg.B.no(i).prcRuleDtlTxt_B1, scrnMsg);
            //Total Transaction Weight
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_15, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_15, scrnMsg);
            //Line Amount
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_24, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_24, scrnMsg);
            //Line Qty
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_26, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_26, scrnMsg);
            //Order Subtotal
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_30, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_30, scrnMsg);
            //Total Qty
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_45, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_45, scrnMsg);
            //Line Qty(QtyBreak)
            checkNumeric(scrnMsg.B.no(i).prcRuleCondFromTxt_58, scrnMsg);
            checkNumeric(scrnMsg.B.no(i).prcRuleCondThruTxt_58, scrnMsg);
            //QC#24550 add End
        }
    }

    /**
     * showCheck
     * @param scrnMsg NMAL7260BMsg
     */
    public static void showCheck(NMAL7260BMsg scrnMsg) {

        // errCheck(No.1)
        checkTableDefintionOverlap(scrnMsg);

        // errCheck(No.2)
        checkTableDefintionMust(scrnMsg);

        // errCheck(No.3)
        checkTableDefintionModiferCnt(scrnMsg);

        // errCheck(No.4)
        checkTableDefintionModiferRequired(scrnMsg);

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).prcRuleAttrbCd_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).inpReqFlg_C1);
        }

        NMAL7260CommonLogic.setTableColor(scrnMsg); // QC#22593 add
        scrnMsg.putErrorScreen();
    }

    private static void checkTableDefintionOverlap(NMAL7260BMsg scrnMsg) {

        if (0 < scrnMsg.C.getValidCount()) {
            List<String> attbCdList = new ArrayList<String>();
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                attbCdList.add(scrnMsg.C.no(i).prcRuleAttrbCd_C1.getValue());
            }

            Set<String> checkHash = new HashSet<String>();
            for (int j = 0; j < attbCdList.size(); j++) {
                if (checkHash.contains(attbCdList.get(j))) {
                    scrnMsg.C.no(j).prcRuleAttrbCd_C1.setErrorInfo(1, NMAM0072E, new String[] {scrnMsg.C.no(j).prcRuleAttrbCd_C1.getNameForMessage() });
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
                } else {
                    checkHash.add(attbCdList.get(j));
                }
            }
        }
    }

    private static void checkTableDefintionMust(NMAL7260BMsg scrnMsg) {

        if (0 < scrnMsg.C.getValidCount()) {
            boolean workFlg = false;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                String prcRuleAttrbCd = scrnMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                    workFlg = true;
                } else {
                    workFlg = false;
                    break;
                }
            }

            if (workFlg) {
                scrnMsg.C.no(0).prcRuleAttrbCd_C1.setErrorInfo(1, NMAM8315E);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
            }
        }
    }

    private static void checkTableDefintionModiferCnt(NMAL7260BMsg scrnMsg) {
        int dataCnt = 0;
        if (0 < scrnMsg.C.getValidCount()) {
            int mCnt = 0;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                String prcRuleAttrbCd = scrnMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                    mCnt = mCnt + 1;
                    dataCnt = i;
                }
            }

            if (0 == mCnt) {
                scrnMsg.C.no(0).prcRuleAttrbCd_C1.setErrorInfo(1, NMAM8352E, new String[] {MODIFIER_COLUMN });
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
            } else if (2 <= mCnt) {
                scrnMsg.C.no(dataCnt).prcRuleAttrbCd_C1.setErrorInfo(1, NMAM8353E, new String[] {MODIFIER_COLUMN });
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
            }
        }
    }

    private static void checkTableDefintionModiferRequired(NMAL7260BMsg scrnMsg) {

        if (0 < scrnMsg.C.getValidCount()) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                String prcRuleAttrbCd = scrnMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).inpReqFlg_C1.getValue())) {
                        scrnMsg.C.no(i).inpReqFlg_C1.setErrorInfo(1, NMAM8317E);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
                        break;
                    }
                }
            }
        }
    }

    /**
     * insertRowDefCheck
     * @param scrnMsg NMAL7260BMsg
     */
    public static void insertRowDefCheck(NMAL7260BMsg scrnMsg) {

        // errCheck
        checkInsertCnt(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    private static void checkInsertCnt(NMAL7260BMsg scrnMsg) {
        if (TABLE_DEF_MAX_CNT <= scrnMsg.C.getValidCount()) {
            scrnMsg.setMessageInfo(NMAM0050E, new String[] {"13" });
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);    //QC#22593 add
            throw new EZDFieldErrorException();
        }
    }

    // Del Start 2016/10/11 QC#14936
    //    public static void onChangeCheck(NMAL7260BMsg scrnMsg)
    //    private static void checkTableDefintionOnChange(NMAL7260BMsg scrnMsg)
    // Del End 2016/10/11 QC#14936

    // QC#9694 2016/06/28 Mod Start
    /**
     * getParamNMAL1130ForDefPrcd
     * @param scrnMsg NMAL7260BMsg
     * @return Object[]
     */
    public static Object[] getParamNMAL1130ForDefPrcd(NMAL7260BMsg scrnMsg) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Default Precedence Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     PRH.GLBL_CMPY_CD ");
        sb.append("    ,PRH.EZCANCELFLAG ");
        sb.append("    ,PRH.PRC_RULE_HDR_PK ");
        sb.append("    ,PRH.PRC_RULE_NM ");
        sb.append("    ,PRH.PRC_RULE_COND_TP_CD ");
        sb.append("    ,PRCT.PRC_RULE_COND_TP_DESC_TXT ");
        sb.append("    ,LOB.LINE_BIZ_TP_DESC_TXT ");
        sb.append("    ,PRC.PRC_RULE_CATG_DESC_TXT ");
        sb.append("    ,PRH.DEF_RULE_PRCD_NUM ");
        sb.append("    ,PRP.PRC_RULE_PRCD_PK ");
        sb.append("    ,PRP.PRC_RULE_PRCD_GRP_NM ");
        sb.append("FROM PRC_RULE_HDR PRH ");
        sb.append("    ,PRC_RULE_COND_TP PRCT ");
        sb.append("    ,LINE_BIZ_TP LOB ");
        sb.append("    ,PRC_RULE_CATG PRC ");
        sb.append("    ,PRC_RULE_PRCD_DTL PRPD ");
        sb.append("    ,PRC_RULE_PRCD PRP ");
        sb.append("WHERE PRH.GLBL_CMPY_CD  = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND   PRH.EZCANCELFLAG  = '0' ");
        sb.append("AND   PRH.DEF_RULE_PRCD_NUM  = ' ").append(scrnMsg.defRulePrcdNum_H1.getValue()).append("' ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRCT.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRCT.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_COND_TP_CD = PRCT.PRC_RULE_COND_TP_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = LOB.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = LOB.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.LINE_BIZ_TP_CD = LOB.LINE_BIZ_TP_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRC.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRC.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_CATG_CD = PRC.PRC_RULE_CATG_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRPD.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRPD.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_HDR_PK = PRPD.PRC_RULE_HDR_PK(+) ");
        sb.append("AND   PRPD.GLBL_CMPY_CD = PRP.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRPD.EZCANCELFLAG = PRP.EZCANCELFLAG(+) ");
        sb.append("AND   PRPD.PRC_RULE_PRCD_PK = PRP.PRC_RULE_PRCD_PK(+) ");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_4);
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Rule or Table Name";
        whereArray0[IDX_1] = "PRC_RULE_NM";
        whereArray0[IDX_2] = "%";
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Precedence Group#";
        whereArray1[IDX_1] = "PRC_RULE_PRCD_PK";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Prcd. Group Name";
        whereArray2[IDX_1] = "PRC_RULE_PRCD_GRP_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_8);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "ID";
        columnArray0[IDX_1] = "PRC_RULE_HDR_PK";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Rule or Table Name";
        columnArray1[IDX_1] = "PRC_RULE_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Price Adjust. Type";
        columnArray2[IDX_1] = "PRC_RULE_COND_TP_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Line of Business";
        columnArray3[IDX_1] = "LINE_BIZ_TP_DESC_TXT";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Price Adjust Category";
        columnArray4[IDX_1] = "PRC_RULE_CATG_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Default Rule Prcd.";
        columnArray5[IDX_1] = "DEF_RULE_PRCD_NUM";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_11);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[IDX_4];
        columnArray6[IDX_0] = "Precedence Group";
        columnArray6[IDX_1] = "PRC_RULE_PRCD_PK";
        columnArray6[IDX_2] = BigDecimal.valueOf(IDX_11);
        columnArray6[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[IDX_4];
        columnArray7[IDX_0] = "Prcd. Group Name";
        columnArray7[IDX_1] = "PRC_RULE_PRCD_GRP_NM";
        columnArray7[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray7[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray7);
        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_3);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_RULE_COND_TP_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "DEF_RULE_PRCD_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "PRC_RULE_NM";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);
        params[IDX_5] = sortConditionList;

        params[IDX_6] = new ArrayList<EZDBStringItem>();
        ;

        return params;
    }

    // QC#9694 2016/06/28 Mod End
    // Add Start 2018/07/18 QC#20267
    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NMAL7260BMsg scrnMsg) {

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[13];

        String mnfItemCd = null;
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.srchOrigItemFlg_MF.getValue())) { // S21_NA#9761 2016/09/28 ADD
            params[0] = scrnMsg.B.no(selectIdx).prcRuleCondFromTxt_10;
        } else {
            // S21_NA#9761 2016/09/28 ADD
            mnfItemCd = scrnMsg.B.no(selectIdx).prcRuleCondFromTxt_10.getValue();
            scrnMsg.B.no(selectIdx).prcRuleCondFromTxt_10.clear();
            params[0] = scrnMsg.B.no(selectIdx).prcRuleCondFromTxt_10;
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(20).xxPopPrm, scrnMsg.B.no(selectIdx).mnfItemCd_10);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).mnfItemCd_10, mnfItemCd);
        }

        params[1] = scrnMsg.P.no(0).xxPopPrm;
        params[2] = scrnMsg.P.no(0).xxPopPrm;
        params[3] = scrnMsg.P.no(0).xxPopPrm;
        params[4] = scrnMsg.P.no(0).xxPopPrm;
        params[5] = scrnMsg.P.no(0).xxPopPrm;
        params[6] = scrnMsg.P.no(0).xxPopPrm;
        params[7] = scrnMsg.P.no(0).xxPopPrm;
        params[8] = scrnMsg.P.no(0).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, "08");
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(0).xxPopPrm;
        params[11] = scrnMsg.P.no(0).xxPopPrm;
        params[12] = scrnMsg.B.no(selectIdx).mnfItemCd_10;

        return params;
    }
    // Add End 2018/07/18 QC#20267

    // Add Start 2016/11/11 QC#5940
    /**
     * <pre>
     * checkValDigit
     * </pre>
     * @param targetLength int
     * @param checkAttrb EZDItemAttribute
     * @return result False : NG True : OK
     */
    public static boolean checkValDigit(int targetLength, EZDItemAttribute checkAttrb) {

        if (null == checkAttrb) {
            return false;
        }

        if (targetLength > checkAttrb.getDigit()) {
            return true;
        }
        return false;
    }

    // Add End 2016/11/11 QC#5940

    // Add Start S21_NA QC#18237(Sol#161)
    /**
     * set Parameters for Popup
     */
    public static Object[] setParamFilterPopupNMAL7420(NMAL7260BMsg scrnMsg, NMAL7260CMsg bizMsg) {

        Object[] params = new Object[scrnMsg.S.length()];

        for (int i = 0; i < scrnMsg.S.length(); i++) {
            params[i] = scrnMsg.S.no(i).xxPopPrm_S;
        }
        params[0] = scrnMsg.prcRuleHdrPk_H1;
        params[1] = scrnMsg.prcRuleNm_H1;
        params[2] = scrnMsg.xxComnColOrdTxt;

        params[21] = scrnMsg.xxSvcCallDt_FF;
        params[121] = scrnMsg.xxSvcCallDt_FT;
        params[181] = scrnMsg.xxSvcCallDt_TF;
        params[182] = scrnMsg.xxSvcCallDt_TT;

        params[33] = scrnMsg.prcDt_FF;
        params[133] = scrnMsg.prcDt_FT;
        params[183] = scrnMsg.prcDt_TF;
        params[184] = scrnMsg.prcDt_TT;

        params[37] = scrnMsg.xxRqstDt_FF;
        params[137] = scrnMsg.xxRqstDt_FT;
        params[185] = scrnMsg.xxRqstDt_TF;
        params[186] = scrnMsg.xxRqstDt_TT;

        params[50] = scrnMsg.prcFmlaPk_PP;
        params[51] = scrnMsg.prcRuleDtlRate_PP;
        params[52] = scrnMsg.prcRuleDtlTxt_PP;

        params[189] = scrnMsg.effFromDt_AF;
        params[190] = scrnMsg.effFromDt_AT;
        params[191] = scrnMsg.effThruDt_AF;
        params[192] = scrnMsg.effThruDt_AT;
        params[193] = scrnMsg.xxFullNm_PC;
        params[194] = scrnMsg.cratDt_BF;
        params[195] = scrnMsg.cratDt_BT;
        params[196] = scrnMsg.xxFullNm_PU;
        params[197] = scrnMsg.lastUpdDt_CF;
        params[198] = scrnMsg.lastUpdDt_CT;

        return params;

    }

    /**
     * set Prameter From popup
     * @param bizMsg NMAL7260CMsg
     * @param scrnMsg NMAL7260BMsg
     */
    public static void setParamFromPopup(NMAL7260CMsg bizMsg, NMAL7260BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_04, scrnMsg.S.no(FILTER_CSMP_NUM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).csmpNumCmntTxt_04, scrnMsg.S.no(FILTER_CSMP_COMMENT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_05, scrnMsg.S.no(FILTER_GRP_PK).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcGrpNm_05, scrnMsg.S.no(FILTER_GRP_NM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_06, scrnMsg.S.no(FILTER_PROD_CTRL_1).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prodCtrlNm_06, scrnMsg.S.no(FILTER_PROD_CTRL_1_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_07, scrnMsg.S.no(FILTER_PROD_CTRL_2).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prodCtrlNm_07, scrnMsg.S.no(FILTER_PROD_CTRL_2_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_08, scrnMsg.S.no(FILTER_COA_MDSE_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_09, scrnMsg.S.no(FILTER_COA_PROD).xxPopPrm_S);
        // Mod Start 2018/12/04 QC#29321
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_10, scrnMsg.S.no(FILTER_MDSE_CD).xxPopPrm_S);
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).mdseDescShortTxt_10, scrnMsg.S.no(FILTER_MDSE_SHORT_TXT).xxPopPrm_S);
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).mnfItemCd_10, scrnMsg.S.no(FILTER_MNF_ITEM_CD).xxPopPrm_S); // QC#20267 2018/07/20 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J1, scrnMsg.S.no(FILTER_MDSE_CD).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J2, scrnMsg.S.no(FILTER_MDSE_SHORT_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J3, scrnMsg.S.no(FILTER_MNF_ITEM_CD).xxPopPrm_S); // QC#20267 2018/07/20 Add
        // Mod End 2018/12/04 QC#29321
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_11, scrnMsg.S.no(FILTER_DS_ORD_CATG).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_12, scrnMsg.S.no(FILTER_DS_ORD_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_13, scrnMsg.S.no(FILTER_LINE_BIZ_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_14, scrnMsg.S.no(FILTER_PRC_GRP_TRT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_AF, scrnMsg.S.no(FILTER_TOTAL_TRX_WEIGHT_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_AT, scrnMsg.S.no(FILTER_TOTAL_TRX_WEIGHT_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_AF, scrnMsg.S.no(FILTER_TOTAL_TRX_WEIGHT_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_AT, scrnMsg.S.no(FILTER_TOTAL_TRX_WEIGHT_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_16, scrnMsg.S.no(FILTER_BILL_TO_ACCT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).billToAcctNm_16, scrnMsg.S.no(FILTER_BILL_TO_ACCT_NM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_17, scrnMsg.S.no(FILTER_COA_CH).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_18, scrnMsg.S.no(FILTER_DS_ACCT_CLS).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_19, scrnMsg.S.no(FILTER_COA_BR).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).coaBrDescTxt_19, scrnMsg.S.no(FILTER_COA_BR_DESC_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_20, scrnMsg.S.no(FILTER_SVC_CALL_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxSvcCallDt_FF, scrnMsg.xxSvcCallDt_FF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxSvcCallDt_FT, scrnMsg.xxSvcCallDt_FT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxSvcCallDt_TF, scrnMsg.xxSvcCallDt_TF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxSvcCallDt_TT, scrnMsg.xxSvcCallDt_TT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_BF, scrnMsg.S.no(FILTER_CUST_PO_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_BT, scrnMsg.S.no(FILTER_CUST_PO_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_BF, scrnMsg.S.no(FILTER_CUST_PO_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_BT, scrnMsg.S.no(FILTER_CUST_PO_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_CF, scrnMsg.S.no(FILTER_LINE_AMT_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_CT, scrnMsg.S.no(FILTER_LINE_AMT_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_CF, scrnMsg.S.no(FILTER_LINE_AMT_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_CT, scrnMsg.S.no(FILTER_LINE_AMT_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_25, scrnMsg.S.no(FILTER_DS_ORD_LINE_CATG).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_DF, scrnMsg.S.no(FILTER_LINE_QTY_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_DT, scrnMsg.S.no(FILTER_LINE_QTY_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_DF, scrnMsg.S.no(FILTER_LINE_QTY_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_DT, scrnMsg.S.no(FILTER_LINE_QTY_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_27, scrnMsg.S.no(FILTER_MKT_MDL).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_28, scrnMsg.S.no(FILTER_MKT_MDL_SEG).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_29, scrnMsg.S.no(FILTER_CPO_SRC_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_EF, scrnMsg.S.no(FILTER_ORD_SUB_TOT_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_ET, scrnMsg.S.no(FILTER_ORD_SUB_TOT_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_EF, scrnMsg.S.no(FILTER_ORD_SUB_TOT_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_ET, scrnMsg.S.no(FILTER_ORD_SUB_TOT_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_31, scrnMsg.S.no(FILTER_DS_PMT_METH).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_32, scrnMsg.S.no(FILTER_PRC_LIST).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcCatgNm_32, scrnMsg.S.no(FILTER_PRC_LIST_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcDt_FF, scrnMsg.prcDt_FF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcDt_FT, scrnMsg.prcDt_FT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcDt_TF, scrnMsg.prcDt_TF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcDt_TT, scrnMsg.prcDt_TT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_34, scrnMsg.S.no(FILTER_PROD_CTRL_3).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_35, scrnMsg.S.no(FILTER_PROD_CTRL_4).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_36, scrnMsg.S.no(FILTER_PROD_CTRL_5).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxRqstDt_FF, scrnMsg.xxRqstDt_FF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxRqstDt_FT, scrnMsg.xxRqstDt_FT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxRqstDt_TF, scrnMsg.xxRqstDt_TF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxRqstDt_TT, scrnMsg.xxRqstDt_TT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_38, scrnMsg.S.no(FILTER_RTRN_RSN).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_39, scrnMsg.S.no(FILTER_SHPG_SVC_LVL).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_40, scrnMsg.S.no(FILTER_MDL).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_41, scrnMsg.S.no(FILTER_PRC_SVC_ZONE).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_42, scrnMsg.S.no(FILTER_DS_ACCT_CLS_BILL).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_44, scrnMsg.S.no(FILTER_SPCL_HDLG_TP).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_FF, scrnMsg.S.no(FILTER_TOT_QTY_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_FT, scrnMsg.S.no(FILTER_TOT_QTY_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_FF, scrnMsg.S.no(FILTER_TOT_QTY_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_FT, scrnMsg.S.no(FILTER_TOT_QTY_TO_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_46, scrnMsg.S.no(FILTER_COA_EXTN).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_48, scrnMsg.S.no(FILTER_FRT_COND).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_49, scrnMsg.S.no(FILTER_FREIGHT_ZONE).xxPopPrm_S);
        // Mod Start 2019/01/08 QC#29751
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_53, scrnMsg.S.no(FILTER_PRC_GRP_CUST_SOLD).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_53, scrnMsg.S.no(FILTER_PRC_GRP_CUST_SOLD).xxPopPrm_S);
        // Mod End 2019/01/08 QC#29751
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcGrpNm_53, scrnMsg.S.no(FILTER_PRC_GRP_CUST_SOLD_TXT).xxPopPrm_S);
        // Mod Start 2018/12/04 QC#29321
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_54, scrnMsg.S.no(FILTER_DS_ACCT).xxPopPrm_S);
        //ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).dsAcctNm_54, scrnMsg.S.no(FILTER_DS_ACCT_NM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_K1, scrnMsg.S.no(FILTER_DS_ACCT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxPrcQlfyValSrchTxt_K2, scrnMsg.S.no(FILTER_DS_ACCT_NM).xxPopPrm_S);
        // Mod End 2018/12/04 QC#29321
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_55, scrnMsg.S.no(FILTER_PRC_RULE_COND).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_56, scrnMsg.S.no(FILTER_ACCT_CLS).xxPopPrm_S);
        // QC#20249 2017/11/10 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_57, scrnMsg.S.no(FILTER_MAT_GRP_QTYBREAK_PK).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcGrpNm_57, scrnMsg.S.no(FILTER_MAT_GRP_QTYBREAK_NM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_GF, scrnMsg.S.no(FILTER_LINEQTY_QTYBREAK_FROM_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_GT, scrnMsg.S.no(FILTER_LINEQTY_QTYBREAK_FROM_TO).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_GF, scrnMsg.S.no(FILTER_LINEQTY_QTYBREAK_TO_FROM).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondThruTxt_GT, scrnMsg.S.no(FILTER_LINEQTY_QTYBREAK_TO_TO).xxPopPrm_S);
        // QC#20249 2017/11/10 Add End
        // 2018/04/09 QC#22569 add start
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_59, scrnMsg.S.no(FILTER_MAT_GRP_1).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).slsMatGrpDescTxt_59, scrnMsg.S.no(FILTER_MAT_GRP_1_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_60, scrnMsg.S.no(FILTER_MAT_GRP_2).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).slsMatGrpDescTxt_60, scrnMsg.S.no(FILTER_MAT_GRP_2_TXT).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleCondFromTxt_61, scrnMsg.S.no(FILTER_MAT_GRP_3).xxPopPrm_S);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).slsMatGrpDescTxt_61, scrnMsg.S.no(FILTER_MAT_GRP_3_TXT).xxPopPrm_S);
         // 2018/04/09 QC#22569 add end
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcFmlaPk, scrnMsg.prcFmlaPk_PP);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleDtlRate, scrnMsg.prcRuleDtlRate_PP);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).prcRuleDtlTxt, scrnMsg.prcRuleDtlTxt_PP);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).effFromDt_AF, scrnMsg.effFromDt_AF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).effFromDt_AT, scrnMsg.effFromDt_AT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).effThruDt_AF, scrnMsg.effThruDt_AF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).effThruDt_AT, scrnMsg.effThruDt_AT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxFullNm_PC, scrnMsg.xxFullNm_PC);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).cratDt_BF, scrnMsg.cratDt_BF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).cratDt_BT, scrnMsg.cratDt_BT);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).xxFullNm_PU, scrnMsg.xxFullNm_PU);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).lastUpdDt_CF, scrnMsg.lastUpdDt_CF);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(0).lastUpdDt_CT, scrnMsg.lastUpdDt_CT);
    }
    // Add End S21_NA QC#18237(Sol#161)
    // QC#20968 add Start
    /**
     * init Parameters for Popup
     */
    public static void initFilterParam(NMAL7260BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.S.length(); i++) {
            scrnMsg.S.no(i).xxPopPrm_S.clear();
        }
        scrnMsg.xxSvcCallDt_FF.clear();
        scrnMsg.xxSvcCallDt_FT.clear();
        scrnMsg.xxSvcCallDt_TF.clear();
        scrnMsg.xxSvcCallDt_TT.clear();
        scrnMsg.prcDt_FF.clear();
        scrnMsg.prcDt_FT.clear();
        scrnMsg.prcDt_TF.clear();
        scrnMsg.prcDt_TT.clear();
        scrnMsg.xxRqstDt_FF.clear();
        scrnMsg.xxRqstDt_FT.clear();
        scrnMsg.xxRqstDt_TF.clear();
        scrnMsg.xxRqstDt_TT.clear();
        scrnMsg.prcFmlaPk_PP.clear();
        scrnMsg.prcRuleDtlRate_PP.clear();
        scrnMsg.prcRuleDtlTxt_PP.clear();
        scrnMsg.effFromDt_AF.clear();
        scrnMsg.effFromDt_AT.clear();
        scrnMsg.effThruDt_AF.clear();
        scrnMsg.effThruDt_AT.clear();
        scrnMsg.xxFullNm_PC.clear();
        scrnMsg.cratDt_BF.clear();
        scrnMsg.cratDt_BT.clear();
        scrnMsg.xxFullNm_PU.clear();
        scrnMsg.lastUpdDt_CF.clear();
        scrnMsg.lastUpdDt_CT.clear();
    }
    // QC#20968 add End
    // QC#20249 2017/11/10 Add Start
    public static Object[] setArgumentOpenWinQtyBreak(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        Object[] param = new Object[NMAL7260Constant.NMAL7300_MAX_INPUT_PARAM_NUM + 1];
        param[NMAL7260Constant.NMAL7300_GLBL_CMPY_CD] = scrnMsg.glblCmpyCd.getValue();
        param[NMAL7260Constant.NMAL7300_PRC_ADJ_LINE] = scrnMsg.B.no(idx);
        param[NMAL7260Constant.NMAL7300_CONDITION_LIST] = scrnMsg.C;
        param[NMAL7260Constant.NMAL7300_INPUT_LIST] = scrnMsg.T;
        return param;
    }
    public static String judgeQtyBreak(NMAL7260BMsg scrnMsg) {
        boolean flg26 = false;
        boolean flg50 = false;
        boolean flg57 = false;
        boolean flg58 = false;
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NMAL7260_CBMsg line = scrnMsg.C.no(i);
            if (PRC_RULE_ATTRB.LINE_QTY.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg26 = true;
            } else if (PRC_RULE_ATTRB.FORMULA.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg50 = true;
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg57 = true;
            } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg58 = true;
            }
        }
        if (flg26 && flg58) {
            return null;
        }
        if (flg50) {
            return null;
        }
        if (flg26) {
            return PRC_RULE_ATTRB.LINE_QTY;
        }
        if (flg58) {
            return PRC_RULE_ATTRB.LINE_QTY_QTYBREAK;
        }
        if (flg57) {
            return PRC_RULE_ATTRB.LINE_QTY_QTYBREAK;
        }
        return PRC_RULE_ATTRB.LINE_QTY;
    }
    // QC#20249 2017/11/10 Add End
    //QC#24550 add Start
    public static void checkNumeric(EZDBStringItem target, NMAL7260BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(target)) {
            //remove Comma
            // ZYPEZDItemValueSetter.setValue(target, target.getValue().replace(",", "")); // 2019/12/26 QC#55204 Del

            //Numeric Check
            if (!ZYPCommonFunc.isNumberCheck(target.getValue())) {
                target.setErrorInfo(1, ZZM9004E, new String[] {target.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, NMAL7260Constant.TAB_ADJ_TBL_DATA);     //QC#22593 add
            }
        }
    }
    //QC#24550 add End
    //QC#22593 add Start
    public static void setTableColor(NMAL7260BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(NMAL7260Constant.SCRN_ID_00);

        if (NMAL7260Constant.TAB_ADJ_TBL_DFN.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        } else {
            NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        }

    }
    //QC#22593 add End

}
