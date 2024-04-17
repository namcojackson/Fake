/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CALC_BASE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONF_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SALES_CREDIT;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM1500E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500CompareBSMsg;
import business.blap.NWAL1500.NWAL1500CompareDSMsg;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_GCMsg;
import business.blap.NWAL1500.NWAL1500_HCMsg;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.blap.NWAL1620.constant.NWAL1620Constant;
import business.db.CPO_SRC_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         Y.Kanefusa      Create          n/a
 * 2015/12/10   Fujitsu         S.Takami        Update          S21_NA#1420
 * 2016/01/04   Fujitsu         T.ishii         Update          S21_NA#955
 * 2016/02/24   Fujitsu         Y.Taoka         Update          S21_NA#2095
 * 2016/03/05   Fujitsu         S.Takami        Update          S21_NS#5000#71
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/04/07   Fujitsu         S.Takami        Update          S21_NA#6602
 * 2016/04/15   Fujitsu         Y.Taoka         Update          S21_NA#7099
 * 2016/04/18   Fujitsu         S.Takami        Update          S21_NA#5520, 6911
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#6312
 * 2016/05/12   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/05/17   Fujitsu         S.Takami        Update          S21_NA#8434
 * 2016/06/02   Fujitsu         M.Yamada        Update          S21_NA#5395
 * 2016/06/28   Fujitsu         Y.Taoka         Update          S21_NA#10893
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/08/23   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/10/11   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/12   Fujitsu         K.Sato          Update          S21_NA#11964
 * 2016/10/25   Fujitsu         S.Takami        Update          S21_NA#15000-2
 * 2016/10/27   Fujitsu         S.Takami        Update          S21_NA#7924-3
 * 2016/10/27   Fujitsu         S.Takami        Update          S21_NA#7924-4
 * 2016/10/28   Fujitsu         S.Takami        Update          S21_NA#7924-5
 * 2016/11/08   Fujitsu         S.Takami        Update          S21_NA#7924-6
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#15939
 * 2016/12/02   Fujitsu         W.Honda         Update          S21_NA#16292
 * 2016/12/21   Fujitsu         T.Aoi           Update          S21_NA#16393
 * 2017/01/04   Fujitsu         T.Yoshida       Update          S21_NA#16806
 * 2017/01/12   Fujitsu         M.Ohno          Update          S21_NA#16655
 * 2017/02/22   Fujitsu         S.Ohki          Update          S21_NA#16184
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-4
 * 2017/03/06   Fujitsu         M.Ohno          Update          S21_NA#17790
 * 2017/03/10   Fujitsu         S.Takami        Update          S21_NA#17979
 * 2017/03/29   Fujitsu         Y.Kanefusa      Update          S21_NA#18173
 * 2017/12/08   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2018/02/03   Fujitsu         Y.Kanefusa      Update          S21_NA#19808 (bizMsg.A, B, C, D->glblMsg.A, B, C, D)
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2022/07/04   CITS            R.Azucena       Update          QC#60279
 * </pre>
 */
public class NWAL1500CommonLogicForCopy {

    /** Sub System ID : NWZ */
    public static final String SUB_SYS_ID_NWZ = "NWZ";

    /** Document Type : OM */
    public static final String DOC_TP_OM = "OM";

    /** Document Type : RT */
    public static final String DOC_TP_RT = "RT"; // 2016/02/25 S21_NA#3328 Add

    /** Event ID for Business Process Log : Order Create */
    public static final String ORD_CREATE = "Order Create";

    /** Event ID for Business Process Log : Order Save */
    public static final String ORD_SAVE = "Order Save";

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    /** Event ID for Business Process Log : Order Create(Sales Credit)" */
//    private static final String ORD_SLS_CR_CREATE = "Order Create(Sales Credit)";
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    /** Event ID for Shell Create */
//    private static final String ORD_SHELL_CREATE = "Shell Create";
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    /**
     * Edit Copy Screen
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    public static void editCopyScrn(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/02/03 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        String curDplyTab = bizMsg.xxDplyTab.getValue(); 
        // 2018/02/03 S21_NA#19808 Add End
        if (NWAL1620Constant.HEADER_MODE.equals(bizMsg.xxPopPrm_P1.getValue())) {
            // Common
            bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_ON_Y);

            // Header
            bizMsg.ordHdrStsDescTxt.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.ordDt, bizMsg.slsDt);
            bizMsg.ordDt_DP.clear();

            // Order Pricing Summary
            bizMsg.xxTotBaseAmt_LN.setValue(BigDecimal.ZERO);
            bizMsg.xxTotTaxAmt_LN.setValue(BigDecimal.ZERO);
            bizMsg.xxTotFrtAmt_LN.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_LN.setValue(BigDecimal.ZERO);

            bizMsg.xxTotBaseAmt_MT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotTaxAmt_MT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotFrtAmt_MT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_MT.setValue(BigDecimal.ZERO);

            bizMsg.xxTotBaseAmt_RT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotTaxAmt_RT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotFrtAmt_RT.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_RT.setValue(BigDecimal.ZERO);

            bizMsg.xxTotBaseAmt.setValue(BigDecimal.ZERO);
            bizMsg.xxTotTaxAmt.setValue(BigDecimal.ZERO);
            bizMsg.xxTotFrtAmt.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt.setValue(BigDecimal.ZERO);

            bizMsg.xxTotInvApplyAmt.setValue(BigDecimal.ZERO);
            bizMsg.xxTotInvPct.setValue(BigDecimal.ZERO);

            // Sales Credit
            for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
                bizMsg.F.no(i).xxRqstTpCd_FS.setValue(NWAL1500Constant.REQ_NEW);
                bizMsg.F.no(i).dsCpoSlsCrPk_FS.clear();
            }

            // Order Source Details
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd, CPO_SRC_TP.COPY);
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpDescTxt, getCpoSrcTpDescTxt(bizMsg, CPO_SRC_TP.COPY));
            ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcImptDt, bizMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum, bizMsg.cpoOrdNum.getValue());
            bizMsg.cpoOrdNum.clear();
            bizMsg.xxSrchNum.clear();
            bizMsg.ordSrcImptTs.clear(); // S21_NA#11964 ADD

            // Credit Card
            bizMsg.dsCrCardPk.clear();

            // PrePayment
            bizMsg.prePmtChkNum.clear();
            bizMsg.prePmtAmt.clear();
            bizMsg.prePmtTpCd.clear();

            bizMsg.addOrigCpoOrdNum.clear();    // S21_NA#10893 ADD
            bizMsg.reBillPairCpoOrdNum.clear(); // S21_NA#10893 ADD
            bizMsg.crRebilRsnCatgCd.clear();    // S21_NA#10893 ADD

            // Additional Details
            // 2016/02/10 S21_NA#1742 Del Start
            // ZYPEZDItemValueSetter.setValue(bizMsg.addRddDt, bizMsg.slsDt);
            // bizMsg.ordSgnDt.clear();
            // 2016/02/10 S21_NA#1742 Del End

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPopPrm_P3.getValue())) {
                ZYPTableUtil.clear(bizMsg.A);
                ZYPTableUtil.clear(bizMsg.B);
                ZYPTableUtil.clear(bizMsg.C);
                ZYPTableUtil.clear(bizMsg.D);
//                ZYPTableUtil.clear(bizMsg.F); 2016/04/07 S21_NA#6602 Del
                ZYPTableUtil.clear(bizMsg.G);
                ZYPTableUtil.clear(bizMsg.H);
                ZYPTableUtil.clear(glblMsg.I);
                ZYPTableUtil.clear(glblMsg.A);
                ZYPTableUtil.clear(glblMsg.B);
                ZYPTableUtil.clear(glblMsg.C);
                ZYPTableUtil.clear(glblMsg.D);
//                ZYPTableUtil.clear(glblMsg.F); 2016/04/07 S21_NA#6602 Del
                ZYPTableUtil.clear(glblMsg.G);
                ZYPTableUtil.clear(glblMsg.H);
                ZYPTableUtil.clear(glblMsg.I);
//QC743
//                ZYPTableUtil.clear(glblMsg.J);
//                ZYPTableUtil.clear(glblMsg.K);
                ZYPTableUtil.clear(glblMsg.T);
                ZYPTableUtil.clear(glblMsg.U);
                ZYPTableUtil.clear(glblMsg.V);
                ZYPTableUtil.clear(glblMsg.W);
                ZYPTableUtil.clear(glblMsg.X);
            } else {
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_LC, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_L, ZYPConstant.FLG_OFF_N); // 2016/01/08 S21_NA#2587
                    bizMsg.A.no(i).dsCpoConfigPk_LC.clear();
                    // Out bound Y N N
                    if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
                        bizMsg.A.no(i).svcConfigMstrPk_LC.clear();
                        bizMsg.A.no(i).xxYesNoCd_LC.clear(); // 2016/02/10 S21_NA#3398 Add
                    }
                }

                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    // 2016/04/18 S21_NA#6911 Add Start
                    if (bizMsg.B.no(i).ordQty_LL.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        setQtyFromCancelQty(bizMsg.glblCmpyCd.getValue(), bizMsg.B.no(i));
                    }
                    // 2016/04/18 S21_NA#6911 Add End
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxChkBox_LL, ZYPConstant.FLG_OFF_N);
                    bizMsg.B.no(i).ordLineStsCd_LL.clear();
//                    bizMsg.B.no(i).serNum_LL.clear(); 2016/04/18 S21_NA#5520 Dell
                    // QC#4078
//                    bizMsg.B.no(i).prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
                    bizMsg.B.no(i).allocQty_LL.setValue(BigDecimal.ZERO);
                    bizMsg.B.no(i).shipQty_LL.setValue(BigDecimal.ZERO);
                    bizMsg.B.no(i).istlQty_LL.setValue(BigDecimal.ZERO);
                    bizMsg.B.no(i).invQty_LL.setValue(BigDecimal.ZERO);
                    bizMsg.B.no(i).cancQty_LL.setValue(BigDecimal.ZERO);
                    bizMsg.B.no(i).ordLineStsDescTxt_LL.clear();
                    bizMsg.B.no(i).xxYesNoCd_LL.clear(); // 2016/02/10 S21_NA#3398 Add
                    bizMsg.B.no(i).loanBalQty_LL.setValue(BigDecimal.ZERO); // 2016/04/15 S21_NA#7099
                    bizMsg.B.no(i).origCpoOrdNum_LL.clear(); // S21_NA#7606 Add
                    bizMsg.B.no(i).origCpoDtlLineNum_LL.clear(); // S21_NA#7606 Add
                    bizMsg.B.no(i).origCpoDtlLineSubNum_LL.clear(); // S21_NA#7606 Add
                    bizMsg.B.no(i).origInvNum_LL.clear(); // S21_NA#10893 Add
                }

                //QC743
//                // 2016/01/08 S21_NA#2587 Add Start
//                for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//                    ZYPEZDItemValueSetter.setValue(glblMsg.J.no(i).xxChkBox_LL, ZYPConstant.FLG_OFF_N);
//                    glblMsg.J.no(i).ordLineStsCd_LL.clear();
//                    glblMsg.J.no(i).serNum_LL.clear();
//                    glblMsg.J.no(i).prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
//                    // 2016/02/10 S21_NA#1742 Del Start
//                    // glblMsg.J.no(i).rddDt_LL.setValue(bizMsg.slsDt.getValue());
//                    // 2016/02/10 S21_NA#1742 Del End
//                    glblMsg.J.no(i).allocQty_LL.setValue(BigDecimal.ZERO);
//                    glblMsg.J.no(i).shipQty_LL.setValue(BigDecimal.ZERO);
//                    glblMsg.J.no(i).istlQty_LL.setValue(BigDecimal.ZERO);
//                    glblMsg.J.no(i).invQty_LL.setValue(BigDecimal.ZERO);
//                    glblMsg.J.no(i).cancQty_LL.setValue(BigDecimal.ZERO);
//                    glblMsg.J.no(i).ordLineStsDescTxt_LL.clear();
//                    glblMsg.J.no(i).xxYesNoCd_LL.clear(); // 2016/02/10 S21_NA#3398 Add
//                }
//                // 2016/01/08 S21_NA#2587 Add End

                for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                    bizMsg.G.no(i).xxRqstTpCd_GS.setValue(NWAL1500Constant.REQ_NEW);
                    bizMsg.G.no(i).dsCpoSlsCrPk_GS.clear();
                    bizMsg.G.no(i).dsCpoConfigPk_GS.clear();
                }

                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxChkBox_RC, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxSmryLineFlg_R, ZYPConstant.FLG_OFF_N); // 2016/01/08 S21_NA#2587
                    bizMsg.C.no(i).dsCpoConfigPk_RC.clear();
                    // In bound Y N N
                    if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), bizMsg.C.no(i).configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, true, false, false)) { // S21_NA#955
                        bizMsg.C.no(i).svcConfigMstrPk_RC.clear();
                    }
                    bizMsg.C.no(i).xxYesNoCd_RC.clear(); // 2016/02/10 S21_NA#3398 Add
                }

                for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                    // 2016/04/18 S21_NA#6911 Add Start
                    if (bizMsg.D.no(i).ordQty_RL.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        setQtyFromCancelQty(bizMsg.glblCmpyCd.getValue(), bizMsg.D.no(i));
                    }
                    // 2016/04/18 S21_NA#6911 Add End
                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxChkBox_RL, ZYPConstant.FLG_OFF_N);
                    bizMsg.D.no(i).ordLineStsCd_RL.clear();
//                  bizMsg.D.no(i).serNum_RL.clear(); 2016/04/18 S21_NA#5520 Dell
                    // QC#4078
//                    bizMsg.D.no(i).prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
//                    bizMsg.D.no(i).rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
                    bizMsg.D.no(i).rtrnQty_RL.setValue(BigDecimal.ZERO);
                    bizMsg.D.no(i).cancQty_RL.setValue(BigDecimal.ZERO);
                    bizMsg.D.no(i).rtrnLineStsDescTxt_RL.clear();
                    bizMsg.D.no(i).xxYesNoCd_RL.clear(); // 2016/02/10 S21_NA#3398 Add
                    bizMsg.D.no(i).origCpoOrdNum_RL.clear(); // S21_NA#7606 Add
                    bizMsg.D.no(i).origCpoDtlLineNum_RL.clear(); // S21_NA#7606 Add
                    bizMsg.D.no(i).origCpoDtlLineSubNum_RL.clear(); // S21_NA#7606 Add
                    bizMsg.D.no(i).origInvNum_RL.clear(); // S21_NA#10893 Add
                }

                //QC743
//                // 2016/01/08 S21_NA#2587 Add Start
//                for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//                    ZYPEZDItemValueSetter.setValue(glblMsg.K.no(i).xxChkBox_RL, ZYPConstant.FLG_OFF_N);
//                    glblMsg.K.no(i).ordLineStsCd_RL.clear();
//                    glblMsg.K.no(i).serNum_RL.clear();
//                    glblMsg.K.no(i).prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
//                    glblMsg.K.no(i).rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
//                    glblMsg.K.no(i).rtrnQty_RL.setValue(BigDecimal.ZERO);
//                    glblMsg.K.no(i).cancQty_RL.setValue(BigDecimal.ZERO);
//                    glblMsg.K.no(i).rtrnLineStsDescTxt_RL.clear();
//                    glblMsg.K.no(i).xxYesNoCd_RL.clear(); // 2016/02/10 S21_NA#3398 Add
//                }
//                // 2016/01/08 S21_NA#2587 Add End

                for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                    bizMsg.H.no(i).xxRqstTpCd_HS.setValue(NWAL1500Constant.REQ_NEW);
                    bizMsg.H.no(i).dsCpoSlsCrPk_HS.clear();
                    bizMsg.H.no(i).dsCpoConfigPk_HS.clear();
                }

                for (int i = 0; i < glblMsg.I.getValidCount(); i++) { // 2015/12/09 S21_NA#1420 Add Start
                    glblMsg.I.no(i).ordPrcCalcBasePk_LP.clear();
                    glblMsg.I.no(i).cpoOrdNum_LP.clear();
                } // 2015/12/09 S21_NA#1420 Add End
            }

        } else if (NWAL1620Constant.CONFIG_MODE.equals(bizMsg.xxPopPrm_P1.getValue())) {
            String dsOrdPosnNum = bizMsg.dsOrdPosnNum_P1.getValue();
            int copyNum = bizMsg.xxQty10Num_AW.getValue().intValue();
            int newConfIndex = 0;

            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.xxPopPrm_P4.getValue())) {
                // 2018/02/03 S21_NA#19808 Add Start
                bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                // 2018/02/03 S21_NA#19808 Add End
//QC743
//                backupScreenData(bizMsg.xxPopPrm_P4.getValue(), dsOrdPosnNum, bizMsg, glblMsg);
                NWAL1500_ASMsg configLine = getDataFromA(glblMsg, dsOrdPosnNum);
                if (configLine == null) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum });
                }
                if (copyNum + glblMsg.A.getValidCount() > glblMsg.A.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CONF_LINE });
                    return;
                }
                List<NWAL1500_GCMsg> salesRepList = getDataFromG(bizMsg, dsOrdPosnNum);
                List<NWAL1500_BSMsg> lineList = getDataFromB(glblMsg, dsOrdPosnNum);
                List<NWAL1500_ISMsg> prcCalcList = getDataFromIForOutbound(bizMsg, glblMsg, lineList);
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                if (salesRepList.size() * copyNum + bizMsg.G.getValidCount() > bizMsg.G.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_SALES_CREDIT });
                    return;
                }
                if (lineList.size() * copyNum + glblMsg.B.getValidCount() > glblMsg.B.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
//                List<String> newDsOrdPosnNumList = new ArrayList<String>(0);
                for (int i = 0; i < copyNum; i++) {
                    String dsOrdPosnNumCopy = copyDataAMsg(bizMsg, glblMsg, configLine, false);
//                    newDsOrdPosnNumList.add(dsOrdPosnNumCopy);
                    copyDataGMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, salesRepList);
                    copyDataBMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);
                    newConfIndex = glblMsg.A.getValidCount() - 1;
                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, newConfIndex);
                }
                //QC743
//                // Copy From J to B
//                int j = bizMsg.B.getValidCount();
//                for (String newDsOrdPosnNum : newDsOrdPosnNumList) {
//                    for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//                        if (newDsOrdPosnNum.equals(glblMsg.J.no(i).dsOrdPosnNum_LL.getValue())) {
//                            EZDMsg.copy(glblMsg.J.no(i), null, bizMsg.B.no(j), null);
//                            j++;
//                        }
//                    }
//                }
//                bizMsg.B.setValidCount(j);

                // S21_NA#8388 ADD
                // Update Decline Service Flg
                ZYPEZDItemValueSetter.setValue(bizMsg.dclnSvcCd, NWAL1500CommonLogic.getDclnSvcChkBoxValHdrFromConfig(bizMsg, glblMsg));

            } else {
                // 2018/02/03 S21_NA#19808 Add Start
                bizMsg.xxDplyTab.setValue(TAB_RMA);
                // 2018/02/03 S21_NA#19808 Add End
//QC743
//                backupScreenData(bizMsg.xxPopPrm_P4.getValue(), dsOrdPosnNum, bizMsg, glblMsg);
                NWAL1500_CSMsg configLine = getDataFromC(glblMsg, dsOrdPosnNum);
                if (configLine == null) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum });
                }
                if (copyNum + glblMsg.C.getValidCount() > glblMsg.C.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CONF_LINE });
                    return;
                }
                List<NWAL1500_HCMsg> salesRepList = getDataFromH(bizMsg, dsOrdPosnNum);
                List<NWAL1500_DSMsg> lineList = getDataFromD(glblMsg, dsOrdPosnNum);
                List<NWAL1500_ISMsg> prcCalcList = getDataFromIForInbound(bizMsg, glblMsg, lineList);
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                if (salesRepList.size() * copyNum + bizMsg.H.getValidCount() > bizMsg.H.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_SALES_CREDIT });
                    return;
                }
                if (lineList.size() * copyNum + glblMsg.D.getValidCount() > glblMsg.D.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
//                List<String> newDsOrdPosnNumList = new ArrayList<String>(0);
                for (int i = 0; i < copyNum; i++) {
                    String dsOrdPosnNumCopy = copyDataCMsg(bizMsg, glblMsg, configLine, false);
//                    newDsOrdPosnNumList.add(dsOrdPosnNumCopy);
                    // copyDataDMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);
                    copyDataHMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, salesRepList);
                    copyDataDMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);
                    newConfIndex = glblMsg.C.getValidCount() - 1;
                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, newConfIndex);
                }
//                // Copy From K to D
//                int j = bizMsg.D.getValidCount();
//                for (String newDsOrdPosnNum : newDsOrdPosnNumList) {
//                    for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//                        if (newDsOrdPosnNum.equals(glblMsg.K.no(i).dsOrdPosnNum_RL.getValue())) {
//                            EZDMsg.copy(glblMsg.K.no(i), null, bizMsg.D.no(j), null);
//                            j++;
//                        }
//                    }
//                }
//                bizMsg.D.setValidCount(j);
            }
        } else {
            String dsOrdPosnNum = bizMsg.dsOrdPosnNum_P1.getValue();
            BigDecimal dsCpoLineNum = bizMsg.dsCpoLineNum_P1.getValue();

            List<Integer> selectRows;

            int copyNum = bizMsg.xxQty10Num_AW.getValue().intValue();
            // 2018/02/03 S21_NA#19808 Add Start
            int copiedLineIdx = 0;
            // 2018/02/03 S21_NA#19808 Add End

            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.xxPopPrm_P4.getValue())) {
                // 2018/02/03 S21_NA#19808 Add Start
                bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                // 2018/02/03 S21_NA#19808 Add End
                selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", "Y");

                List<NWAL1500_BSMsg> lineList = getDataFromB(glblMsg, dsOrdPosnNum, dsCpoLineNum);
                List<NWAL1500_ISMsg> prcCalcList = getDataFromIForOutbound(bizMsg, glblMsg, lineList);
                if (lineList.size() == 0) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum + "." + String.valueOf(dsCpoLineNum.intValue()) });
                }
                if (lineList.size() * copyNum + glblMsg.B.getValidCount() > glblMsg.B.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                for (int i = 0; i < copyNum; i++) {
                    copyDataBMsg(bizMsg, glblMsg, dsOrdPosnNum, lineList, prcCalcList);
                }
                // 2018/02/03 S21_NA#19808 Add Start
                copiedLineIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.B.no(selectRows.get(0)), glblMsg);
                // 2018/02/03 S21_NA#19808 Add End
//QC743
//                copyToExpandedData(bizMsg.xxPopPrm_P4.getValue(), bizMsg, glblMsg);
            } else {
                // 2018/02/03 S21_NA#19808 Add Start
                bizMsg.xxDplyTab.setValue(TAB_RMA);
                // 2018/02/03 S21_NA#19808 Add End
                selectRows = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", "Y");

                List<NWAL1500_DSMsg> lineList = getDataFromD(glblMsg, dsOrdPosnNum, dsCpoLineNum);
                List<NWAL1500_ISMsg> prcCalcList = getDataFromIForInbound(bizMsg, glblMsg, lineList);
                if (lineList.size() == 0) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum + "." + String.valueOf(dsCpoLineNum.intValue()) });
                }
                for (NWAL1500_DSMsg line : lineList) {
                    if (ZYPCommonFunc.hasValue(line.dsCpoLineSubNum_RL)) {
                        bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0678E, new String[] {NWAL1500MsgConstant.MSG_PARAM_SET_COMPONENT });
                    }
                }
                if (lineList.size() * copyNum + glblMsg.D.getValidCount() > glblMsg.D.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                for (int i = 0; i < copyNum; i++) {
                    copyDataDMsg(bizMsg, glblMsg, dsOrdPosnNum, lineList, prcCalcList);
                }
                // 2018/02/03 S21_NA#19808 Add Start
                copiedLineIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.D.no(selectRows.get(0)), glblMsg);
                // 2018/02/03 S21_NA#19808 Add End
//QC743
//                copyToExpandedData(bizMsg.xxPopPrm_P4.getValue(), bizMsg, glblMsg);
            }
            // 2018/02/03 S21_NA#19808 Mod Start
//            int currentConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selectRows.get(0));
            int currentConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, copiedLineIdx);
            // 2018/02/03 S21_NA#19808 Mod End
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, currentConfIndex);
        }
        // 2018/02/03 S21_NA#19808 Add Start
        bizMsg.xxDplyTab.setValue(curDplyTab);
        // 2018/02/03 S21_NA#19808 Add End
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
        }
    }

    /**
     * Edit Copy From Screen
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    @SuppressWarnings("unchecked")
    public static void editCopyFromScrn(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String cpoOrdNum = bizMsg.xxPopPrm_P2.getValue();
        String dsOrdPosnNum = bizMsg.dsOrdPosnNum_P1.getValue();
        BigDecimal dsCpoLineNum = bizMsg.dsCpoLineNum_P1.getValue();
        String configCatgCd = bizMsg.xxPopPrm_P4.getValue();

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsOrdPosnNum", dsOrdPosnNum);
        queryParam.put("dsCpoLineNum", dsCpoLineNum);
        queryParam.put("configCatgCd", configCatgCd);
        queryParam.put("configCatgCdOutbound", CONFIG_CATG.OUTBOUND);
        queryParam.put("configCatgCdInbound", CONFIG_CATG.INBOUND);

        int copyNum = bizMsg.xxQty10Num_AW.getValue().intValue();
        S21SsmEZDResult ssmResult = null;

        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        } else  if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        }
        if (!ZYPCommonFunc.hasValue(dsCpoLineNum)) {
            if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                NWAL1500_ASMsg configLine = new NWAL1500_ASMsg();
                NWAL1500Query.getInstance().getConfigInfoForCopy(queryParam, configLine);
                if (configLine == null) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum });
                    return;
                }

                List<NWAL1500_BSMsg> lineList = new ArrayList<NWAL1500_BSMsg>();
                ssmResult = NWAL1500Query.getInstance().getCpoDtlViewInfoForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    lineList = (List<NWAL1500_BSMsg>) ssmResult.getResultObject();
                }

                List<NWAL1500_ISMsg> prcCalcList = new ArrayList<NWAL1500_ISMsg>();
                ssmResult = NWAL1500Query.getInstance().getPriceInfoOutForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    prcCalcList = (List<NWAL1500_ISMsg>) ssmResult.getResultObject();
                }

                List<NWAL1500_GCMsg> salesRepList = new ArrayList<NWAL1500_GCMsg>();
                ssmResult = NWAL1500Query.getInstance().getSalesCreditForConfigOutForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    salesRepList = (List<NWAL1500_GCMsg>) ssmResult.getResultObject();
                }
                // 2018/02/03 S21_NA#19808 Add Start
                if (copyNum + glblMsg.A.getValidCount() > glblMsg.A.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CONFIG });
                    return;
                }
                // 2018/02/03 S21_NA#19808 Add End
                if (lineList.size() * copyNum + glblMsg.B.getValidCount() > glblMsg.B.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                if (salesRepList.size() * copyNum + bizMsg.G.getValidCount() > bizMsg.G.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_SALES_CREDIT });
                    return;
                }
                for (int i = 0; i < copyNum; i++) {
                    String dsOrdPosnNumCopy = copyDataAMsg(bizMsg, glblMsg, configLine, true);
                    copyDataBMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);
                    copyDataGMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, salesRepList);

                    int addConfIndex = Integer.parseInt(dsOrdPosnNumCopy) - 1;
                    String configTp = glblMsg.A.no(addConfIndex).configTpCd_LC.getValue();
                    if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, true)) {
                        // 2016/07/11 S21_NA#7821 Add Start
//                        for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
//                            if (S21StringUtil.isEquals(dsOrdPosnNumCopy, bizMsg.B.no(j).dsOrdPosnNum_LL.getValue())) {
//                                NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, addConfIndex, j);
//                            }
//                        }
                        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(addConfIndex));
                        // 2016/07/11 S21_NA#7821 Add End
                    }

                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg,  glblMsg, addConfIndex);

                }
                // S21_NA#8388 ADD
                // Update Decline Service Flg
                ZYPEZDItemValueSetter.setValue(bizMsg.dclnSvcCd, NWAL1500CommonLogic.getDclnSvcChkBoxValHdrFromConfig(bizMsg, glblMsg));

            } else {
                NWAL1500_CSMsg configLine = new NWAL1500_CSMsg();
                NWAL1500Query.getInstance().getConfigRtrnInfoForCopy(queryParam, configLine);
                if (configLine == null) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0684E, new String[] {dsOrdPosnNum });
                    return;
                }

                List<NWAL1500_DSMsg> lineList = new ArrayList<NWAL1500_DSMsg>();
                ssmResult = NWAL1500Query.getInstance().getCpoRtrnDtlViewInfoForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    lineList = (List<NWAL1500_DSMsg>) ssmResult.getResultObject();
                }

                List<NWAL1500_ISMsg> prcCalcList = new ArrayList<NWAL1500_ISMsg>();
                ssmResult = NWAL1500Query.getInstance().getPriceInfoInForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    prcCalcList = (List<NWAL1500_ISMsg>) ssmResult.getResultObject();
                }

                List<NWAL1500_HCMsg> salesRepList = new ArrayList<NWAL1500_HCMsg>();
                ssmResult = NWAL1500Query.getInstance().getSalesCreditForConfigInForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    salesRepList = (List<NWAL1500_HCMsg>) ssmResult.getResultObject();
                }
                // 2018/02/03 S21_NA#19808 Add Start
                if (copyNum + glblMsg.C.getValidCount() > glblMsg.C.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CONFIG });
                    return;
                }
                // 2018/02/03 S21_NA#19808 Add End
                if (lineList.size() * copyNum + glblMsg.D.getValidCount() > glblMsg.D.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                if (salesRepList.size() * copyNum + bizMsg.H.getValidCount() > bizMsg.H.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_SALES_CREDIT });
                    return;
                }

                for (int i = 0; i < copyNum; i++) {
                    String dsOrdPosnNumCopy = copyDataCMsg(bizMsg, glblMsg, configLine, true);
                    copyDataDMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);
                    copyDataHMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, salesRepList);
                }
            }

        } else {
            if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                ssmResult = NWAL1500Query.getInstance().getCpoDtlViewInfoForCopy(queryParam);
                if (!ssmResult.isCodeNormal()) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0000E, null);
                    return;
                }
                List<NWAL1500_BSMsg> lineList = (List<NWAL1500_BSMsg>) ssmResult.getResultObject();

                List<NWAL1500_ISMsg> prcCalcList = new ArrayList<NWAL1500_ISMsg>();
                ssmResult = NWAL1500Query.getInstance().getPriceInfoOutForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    prcCalcList = (List<NWAL1500_ISMsg>) ssmResult.getResultObject();
                }

                //QC743
//                List<NWAL1500_JSMsg> copyLineList = new ArrayList<NWAL1500_JSMsg>();
//                for (NWAL1500_BCMsg line : lineList) {
//                    NWAL1500_JSMsg copy = new NWAL1500_JSMsg();
//                    EZDMsg.copy(line, null, copy, null);
//                    copyLineList.add(copy);
//                }
                if (lineList.size() * copyNum + glblMsg.B.getValidCount() > glblMsg.B.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                //QC743
//                if (lineList.size() * copyNum + glblMsg.J.getValidCount() > glblMsg.J.length()) {
//                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
//                    return;
//                }

                List<Integer> checkListLine = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                for (int n : checkListLine) {
                    NWAL1500_ASMsg configLine = glblMsg.A.no(n);
                    for (int i = 0; i < copyNum; i++) {
                        String dsOrdPosnNumCopy = configLine.dsOrdPosnNum_LC.getValue();
                        copyDataBMsg(bizMsg, glblMsg, dsOrdPosnNumCopy, lineList, prcCalcList);

                        int addConfIndex = Integer.parseInt(dsOrdPosnNumCopy) - 1;
                        String configTp = glblMsg.A.no(addConfIndex).configTpCd_LC.getValue();
                        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, true)) {
                            // 2016/07/11 S21_NA#7821 Add Start
//                            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
//                                if (S21StringUtil.isEquals(dsOrdPosnNumCopy, bizMsg.B.no(j).dsOrdPosnNum_LL.getValue())) {
//                                    NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, addConfIndex, j);
//                                }
//                            }
                            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(addConfIndex));
                            // 2016/07/11 S21_NA#7821 Add End
                        }

                        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, addConfIndex);
                    }
                }

            } else {
                ssmResult = NWAL1500Query.getInstance().getCpoRtrnDtlViewInfoForCopy(queryParam);
                if (!ssmResult.isCodeNormal()) {
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0000E, null);
                    return;
                }
                List<NWAL1500_DSMsg> lineList = (List<NWAL1500_DSMsg>) ssmResult.getResultObject();

                //QC743
//                List<NWAL1500_KSMsg> copyLineList = new ArrayList<NWAL1500_KSMsg>();
//                for (NWAL1500_DCMsg line : lineList) {
//                    NWAL1500_KSMsg copy = new NWAL1500_KSMsg();
//                    EZDMsg.copy(line, null, copy, null);
//                    copyLineList.add(copy);
//                }

                List<NWAL1500_ISMsg> prcCalcList = new ArrayList<NWAL1500_ISMsg>();
                ssmResult = NWAL1500Query.getInstance().getPriceInfoInForCopy(queryParam);
                if (ssmResult.isCodeNormal()) {
                    prcCalcList = (List<NWAL1500_ISMsg>) ssmResult.getResultObject();
                }

                for (NWAL1500_DSMsg line : lineList) {
                    if (ZYPCommonFunc.hasValue(line.dsCpoLineSubNum_RL)) {
                        bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0678E, new String[] {NWAL1500MsgConstant.MSG_PARAM_SET_COMPONENT });
                        return;
                    }
                }
                if (lineList.size() * copyNum + glblMsg.D.getValidCount() > glblMsg.D.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
                    return;
                }
                if (prcCalcList.size() * copyNum + glblMsg.I.getValidCount() > glblMsg.I.length()) {
                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_CALC_BASE });
                    return;
                }
                //QC743
//                if (lineList.size() * copyNum + glblMsg.K.getValidCount() > glblMsg.K.length()) {
//                    bizMsg.setMessageInfo(NWAM1500E, new String[] {MSG_PARAM_CONF_LINE, MSG_PARAM_LINE });
//                    return;
//                }

                List<Integer> checkListRMA = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                for (int n : checkListRMA) {
                    NWAL1500_CSMsg configLine = glblMsg.C.no(n);
                    for (int i = 0; i < copyNum; i++) {
                        copyDataDMsg(bizMsg, glblMsg, configLine.dsOrdPosnNum_RC.getValue(), lineList, prcCalcList);
                    }
                }
            }
        }
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.xxPageShowCurNum_LL) || bizMsg.xxPageShowCurNum_LL.getValueInt() < 1) {
                bizMsg.xxPageShowCurNum_LL.setValue(1);
            }
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.xxPageShowCurNum_RL) || bizMsg.xxPageShowCurNum_RL.getValueInt() < 1) {
                bizMsg.xxPageShowCurNum_RL.setValue(1);
            }
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
        }
    }

    private static NWAL1500_ASMsg getDataFromA(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {

        NWAL1500_ASMsg configLine = null;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                configLine = glblMsg.A.no(i);
            }
        }

        return configLine;
    }

    private static List<NWAL1500_BSMsg> getDataFromB(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {

        List<NWAL1500_BSMsg> list = new ArrayList<NWAL1500_BSMsg>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                list.add(glblMsg.B.no(i));
            }
        }

        return list;
    }

    private static List<NWAL1500_BSMsg> getDataFromB(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum) {

        List<NWAL1500_BSMsg> list = new ArrayList<NWAL1500_BSMsg>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && dsCpoLineNum.compareTo(glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                list.add(glblMsg.B.no(i));
            }
        }

        return list;
    }

    private static NWAL1500_CSMsg getDataFromC(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {

        NWAL1500_CSMsg configLine = null;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                configLine = glblMsg.C.no(i);
            }
        }

        return configLine;
    }

    private static List<NWAL1500_DSMsg> getDataFromD(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {

        List<NWAL1500_DSMsg> list = new ArrayList<NWAL1500_DSMsg>();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                list.add(glblMsg.D.no(i));
            }
        }

        return list;
    }

    private static List<NWAL1500_DSMsg> getDataFromD(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum) {

        List<NWAL1500_DSMsg> list = new ArrayList<NWAL1500_DSMsg>();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) && dsCpoLineNum.compareTo(glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                list.add(glblMsg.D.no(i));
            }
        }

        return list;
    }

    private static List<NWAL1500_GCMsg> getDataFromG(NWAL1500CMsg bizMsg, String dsOrdPosnNum) {

        List<NWAL1500_GCMsg> list = new ArrayList<NWAL1500_GCMsg>();
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.G.no(i).dsOrdPosnNum_GS.getValue())) {
                list.add(bizMsg.G.no(i));
            }
        }

        return list;
    }

    private static List<NWAL1500_HCMsg> getDataFromH(NWAL1500CMsg bizMsg, String dsOrdPosnNum) {

        List<NWAL1500_HCMsg> list = new ArrayList<NWAL1500_HCMsg>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.H.no(i).dsOrdPosnNum_HS.getValue())) {
                list.add(bizMsg.H.no(i));
            }
        }

        return list;
    }


    private static List<NWAL1500_ISMsg> getDataFromIForOutbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWAL1500_BSMsg> list) {

        List<NWAL1500_ISMsg> calcBaseList = new ArrayList<NWAL1500_ISMsg>();
        NWAL1500_BSMsg lineMsg = null;
        for (int i = 0; i < list.size(); i++) {
            lineMsg = list.get(i);
            calcBaseList.addAll(getBasePriceData(bizMsg, glblMsg, lineMsg.xxLineNum_LL.getValue(), lineMsg.cpoDtlLineNum_LL.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue(), CONFIG_CATG.OUTBOUND));
        }

        return calcBaseList;
    }


    private static List<NWAL1500_ISMsg> getDataFromIForInbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWAL1500_DSMsg> list) {

        List<NWAL1500_ISMsg> calcBaseList = new ArrayList<NWAL1500_ISMsg>();
        NWAL1500_DSMsg lineMsg = null;
        for (int i = 0; i < list.size(); i++) {
            lineMsg = list.get(i);
            calcBaseList.addAll(getBasePriceData(bizMsg, glblMsg, lineMsg.xxLineNum_RL.getValue(), lineMsg.cpoDtlLineNum_RL.getValue(), lineMsg.cpoDtlLineSubNum_RL.getValue(), CONFIG_CATG.INBOUND));
        }

        return calcBaseList;
    }

//    private static List<NWAL1500_JSMsg> getDataFromJ(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {
//
//        List<NWAL1500_JSMsg> list = new ArrayList<NWAL1500_JSMsg>();
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.J.no(i).dsOrdPosnNum_LL.getValue())) {
//                list.add(glblMsg.J.no(i));
//            }
//        }
//
//        return list;
//    }
//
//    private static List<NWAL1500_JSMsg> getDataFromJ(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum) {
//
//        List<NWAL1500_JSMsg> list = new ArrayList<NWAL1500_JSMsg>();
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.J.no(i).dsOrdPosnNum_LL.getValue()) && dsCpoLineNum.compareTo(glblMsg.J.no(i).dsCpoLineNum_LL.getValue()) == 0) {
//                list.add(glblMsg.J.no(i));
//            }
//        }
//
//        return list;
//    }
//
//    private static NWAL1500_JSMsg getDataFromJ(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {
//
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            BigDecimal dsCpoLineNumJ = glblMsg.J.no(i).dsCpoLineNum_LL.getValue();
//            BigDecimal dsCpoLineSubNumJ = glblMsg.J.no(i).dsCpoLineSubNum_LL.getValue();
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.J.no(i).dsOrdPosnNum_LL.getValue()) && dsCpoLineNum.compareTo(dsCpoLineNumJ) == 0) {
//                if (null == dsCpoLineSubNum && null == dsCpoLineSubNumJ) {
//                    return glblMsg.J.no(i);
//                } else if (null != dsCpoLineSubNum && null != dsCpoLineSubNumJ && dsCpoLineSubNum.compareTo(dsCpoLineSubNumJ) == 0) {
//                    return glblMsg.J.no(i);
//                }
//            }
//        }

//        return null;
//    }
//
//    private static List<NWAL1500_KSMsg> getDataFromK(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {
//
//        List<NWAL1500_KSMsg> list = new ArrayList<NWAL1500_KSMsg>();
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.K.no(i).dsOrdPosnNum_RL.getValue())) {
//                list.add(glblMsg.K.no(i));
//            }
//        }
//
//        return list;
//    }
//
//    private static List<NWAL1500_KSMsg> getDataFromK(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum) {
//
//        List<NWAL1500_KSMsg> list = new ArrayList<NWAL1500_KSMsg>();
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.K.no(i).dsOrdPosnNum_RL.getValue()) && dsCpoLineNum.compareTo(glblMsg.K.no(i).dsCpoLineNum_RL.getValue()) == 0) {
//                list.add(glblMsg.K.no(i));
//            }
//        }
//
//        return list;
//    }
//
//    private static NWAL1500_KSMsg getDataFromK(NWAL1500SMsg glblMsg, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {
//
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            BigDecimal dsCpoLineNumK = glblMsg.K.no(i).dsCpoLineNum_RL.getValue();
//            BigDecimal dsCpoLineSubNumK = glblMsg.K.no(i).dsCpoLineSubNum_RL.getValue();
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.K.no(i).dsOrdPosnNum_RL.getValue()) && dsCpoLineNum.compareTo(dsCpoLineNumK) == 0) {
//                if (null == dsCpoLineSubNum && null == dsCpoLineSubNumK) {
//                    return glblMsg.K.no(i);
//                } else if (null != dsCpoLineSubNum && null != dsCpoLineSubNumK && dsCpoLineSubNum.compareTo(dsCpoLineSubNumK) == 0) {
//                    return glblMsg.K.no(i);
//                }
//            }
//        }
//
//        return null;
//    }

    private static String copyDataAMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg msg, boolean isOrdCopy) {

        if (msg == null) {
            return null;
        }

        NWAL1500_ASMsg configLineMsg = glblMsg.A.no(glblMsg.A.getValidCount());
        String dsOrdPosnNum = Integer.toString(glblMsg.A.getValidCount() + 1);
        EZDMsg.copy(msg, null, configLineMsg, null);
        ZYPEZDItemValueSetter.setValue(configLineMsg.xxChkBox_LC, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(configLineMsg.dsOrdPosnNum_LC, dsOrdPosnNum);
//        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd); //QC#1694
//        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd); //QC#1694
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);

        configLineMsg.dsCpoConfigPk_LC.clear();
        // if
        // (CONFIG_TP.NEW.equals(configLineMsg.configTpCd_LC.getValue()))
        // {
        // Out bound Y N N
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configLineMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
            configLineMsg.svcConfigMstrPk_LC.clear();
        }

        if (isOrdCopy) {
            // Copy Configuration Line.
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_LC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_LC, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_LC, bizMsg.xxAllLineAddr_SH);
            ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_LC, bizMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_LC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_LC, bizMsg.billToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_LC, bizMsg.xxAllLineAddr_BT);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_LC, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_LC, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_LC, bizMsg.soldToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_LC, bizMsg.xxAllLineAddr_SE);
            // 2017/12/08 S21_NA#21621 Add Start
            ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_LC, bizMsg.addShipToLocNm);
            // 2017/12/08 S21_NA#21621 Add End

            // Ship To Information from Header.
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_LC, bizMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_LC, bizMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_LC, bizMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_LC, bizMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_LC, bizMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_LC, bizMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_LC, bizMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_LC, bizMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_LC, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_LC, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_LC, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_LC, bizMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_LC, bizMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_LC, bizMsg.shipToProvNm);
        }

        ZYPEZDItemValueSetter.setValue(configLineMsg.dsCpoConfigHldFlg_LC, ZYPConstant.FLG_OFF_N);

        configLineMsg.cratUsrNm_LC.clear();
        configLineMsg.cratTsDplyTxt_LC.clear();
        configLineMsg.updUsrNm_LC.clear();
        configLineMsg.updTsDplyTxt_LC.clear();

        NWAL1500CommonLogic.setConfigNewFlg(bizMsg, configLineMsg);

        return dsOrdPosnNum;
    }

    private static void copyDataBMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_BSMsg> copylist, List<NWAL1500_ISMsg> prcCalcList) {

        if (dsOrdPosnNum == null) {
            return;
        }

        List<NWAL1500_BSMsg> list = new ArrayList<NWAL1500_BSMsg>();
        List<NWAL1500_ISMsg> prclist = new ArrayList<NWAL1500_ISMsg>();
        BigDecimal max = BigDecimal.ZERO;

        String cpoDtlLineNum = null;
        String cpoDtlLineSubNum = null;
        String oldCpoDtlLineNum = null;
        String newCpoDtlLineNum = null;
        BigDecimal oldDsCpoLineNum = null;
        BigDecimal dsCpoLineNum = null; // 2016/05/17 S21_NA#8434 Add
        newCpoDtlLineNum = getOrderLineNumberForOutboundInBizMsgString(glblMsg);

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                if (glblMsg.B.no(i).dsCpoLineNum_LL.getValue().compareTo(max) > 0) {
                    max = glblMsg.B.no(i).dsCpoLineNum_LL.getValue();
                }
            }
        }

        // max = max.add(BigDecimal.ONE); 2016/05/17 S21_NA#8434 Del
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = new NWAL1500_BSMsg();
            EZDMsg.copy(glblMsg.B.no(i), null, lineMsg, null);
            list.add(lineMsg);
        }

        for (int i = 0; i < copylist.size(); i++) {
            NWAL1500_BSMsg lineMsg = new NWAL1500_BSMsg();
            cpoDtlLineNum = copylist.get(i).cpoDtlLineNum_LL.getValue();
            cpoDtlLineSubNum = copylist.get(i).cpoDtlLineSubNum_LL.getValue();
            dsCpoLineNum = copylist.get(i).dsCpoLineNum_LL.getValue(); // 2016/05/17 S21_NA#8434 Add

            EZDMsg.copy(copylist.get(i), null, lineMsg, null);
            // 2016/04/18 S21_NA#6911 Add Start
            if (NWAL1500CommonLogic.compareBigDecimal(lineMsg.ordQty_LL.getValue(), BigDecimal.ZERO) == 0) {
                setQtyFromCancelQty(bizMsg.glblCmpyCd.getValue(), lineMsg);
            }
            // 2016/04/18 S21_NA#6911 Add End
            ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_LL, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdPosnNum_LL, dsOrdPosnNum);
            // 2016/05/17 S21_NA#8434 Mod Start
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_LL, max);

//            if (NWAL1500CommonLogic.compareBigDecimal(oldDsCpoLineNum, max) != 0) {
//                oldDsCpoLineNum = max;
//                max = max.add(BigDecimal.ONE);
//            }
            if (NWAL1500CommonLogic.compareBigDecimal(oldDsCpoLineNum, dsCpoLineNum) != 0) {
                oldDsCpoLineNum = dsCpoLineNum;
                max = max.add(BigDecimal.ONE);
            }
            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_LL, max);
            // 2016/05/17 S21_NA#8434 Mod End

            if (!cpoDtlLineNum.equals(oldCpoDtlLineNum)) {
                oldCpoDtlLineNum = cpoDtlLineNum;
                newCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(newCpoDtlLineNum);
            }

            // 2016/03/05 S21_NS#5000#71 Mod Start
            // ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_LL, newCpoDtlLineNum);
            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_LL, newCpoDtlLineNum);
            } else {
                lineMsg.cpoDtlLineNum_LL.clear();
            }
            // 2016/03/05 S21_NS#5000#71 Mod End
            lineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            lineMsg.ordLineStsCd_LL.clear();
            // lineMsg.serNum_LL.clear(); 2016/04/18 S21_NA#5520 Del
            lineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
            lineMsg.allocQty_LL.setValue(BigDecimal.ZERO);
            lineMsg.shipQty_LL.setValue(BigDecimal.ZERO);
            lineMsg.istlQty_LL.setValue(BigDecimal.ZERO);
            lineMsg.invQty_LL.setValue(BigDecimal.ZERO);
            lineMsg.cancQty_LL.setValue(BigDecimal.ZERO);
            // START 2022/07/04 R.Azucena [QC#60279 ADD]
            lineMsg.loanBalQty_LL.setValue(BigDecimal.ZERO);
            // END 2022/07/04 R.Azucena [QC#60279 ADD]
            lineMsg.ordLineStsDescTxt_LL.clear();
            // 2016/12/21 T.Aoi S21_NA#16393 Add Start
            lineMsg.ordLineStsDescTxt_LD.clear();
            // 2016/12/21 T.Aoi S21_NA#16393 Add End
            lineMsg.ordLineStsCd_LL.clear();

            // Add 2016/03/07 S21_NA#5000#78 Start
            ZYPEZDItemValueSetter.setValue(lineMsg.custIssPoNum_LL, bizMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(lineMsg.custIssPoDt_LL, bizMsg.custIssPoDt);
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_LL, bizMsg.cpoSrcTpDescTxt);
            ZYPEZDItemValueSetter.setValue(lineMsg.ordSrcRefNum_LL, bizMsg.ordSrcRefNum);
            // Add 2016/03/07 S21_NA#5000#78 End

            lineMsg.cratTsDplyTxt_LL.clear();
            lineMsg.ordBookTsDplyTxt_LL.clear();
            lineMsg.shipDtTsDplyTxt_LL.clear();
            lineMsg.schdDelyTsDplyTxt_LL.clear();

            lineMsg.cratUsrNm_LL.clear();
            lineMsg.updUsrNm_LL.clear();
            lineMsg.updTsDplyTxt_LL.clear();

            lineMsg.cancRsnDescTxt_LL.clear(); // QC#5395
            lineMsg.mdseCd_LB.clear(); // QC#18173
            lineMsg.cpoDtlLineNum_LB.clear(); // QC#18173
            lineMsg.cpoDtlLineSubNum_LB.clear(); // QC#18173

            list.add(lineMsg);

            prclist.clear();
            for (int j = 0; j < prcCalcList.size(); j++) {
                NWAL1500_ISMsg prcLineMsg = prcCalcList.get(j);
                if (cpoDtlLineNum.equals(prcLineMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcLineMsg.cpoDtlLineSubNum_LP.getValue()) && CONFIG_CATG.OUTBOUND.equals(prcLineMsg.configCatgCd_LP.getValue())) {
                    prclist.add(prcLineMsg);
                }
            }

            int cnt = glblMsg.I.getValidCount();
            for (int j = 0; j < prclist.size(); j++) {
                EZDMsg.copy(prclist.get(j), null, glblMsg.I.no(cnt), null);
                ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).cpoDtlLineNum_LP, newCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).xxLineNum_LP, lineMsg.xxLineNum_LL);
                glblMsg.I.no(cnt).ordPrcCalcBasePk_LP.clear();
                cnt++;
            }
            glblMsg.I.setValidCount(cnt);
        }

        Collections.sort(list, new NWAL1500CompareBSMsg());

        // For Performance QC#8166 Add Start
        Map<String, List<Map<String, String>>> lineUomCacheMap = new HashMap<String, List<Map<String, String>>>();
        // For Performance QC#8166 Add End

        int idx = 0;
        for (EZDMsg line : list) {
            EZDMsg.copy(line, null, glblMsg.B.no(idx), null);
            NWAL1500CommonLogic.setUomPullDownForConfLineFromCache(bizMsg, glblMsg.B.no(idx), lineUomCacheMap); // For Performance QC#8166 Mod //2018/02/03 S21_NA#19808 
            idx++;
        }
        glblMsg.B.setValidCount(idx);
    }

    private static String copyDataCMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg msg, boolean isOrdCopy) {

        if (msg == null) {
            return null;
        }

        NWAL1500_CSMsg configLineMsg = glblMsg.C.no(glblMsg.C.getValidCount());
        String dsOrdPosnNum = Integer.toString(glblMsg.C.getValidCount() + 1);
        EZDMsg.copy(msg, null, configLineMsg, null);
        ZYPEZDItemValueSetter.setValue(configLineMsg.xxChkBox_RC, ZYPConstant.FLG_OFF_N);
        configLineMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N); // 2015/12/22 Add
        ZYPEZDItemValueSetter.setValue(configLineMsg.dsOrdPosnNum_RC, dsOrdPosnNum);
//        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
        glblMsg.C.setValidCount(glblMsg.C.getValidCount() + 1);

        configLineMsg.dsCpoConfigPk_RC.clear();
        // In bound Y N N
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configLineMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, true, false, false)) { // S21_NA#955
            configLineMsg.svcConfigMstrPk_RC.clear();
        }

        if (isOrdCopy) {
            // Copy Configuration Line.
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_RC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_RC, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_RC, bizMsg.xxAllLineAddr_SH);
            ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_RC, bizMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_RC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_RC, bizMsg.billToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_RC, bizMsg.xxAllLineAddr_BT);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_RC, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_RC, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_RC, bizMsg.soldToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_RC, bizMsg.xxAllLineAddr_SE);
            // 2017/12/08 S21_NA#21621 Add Start
            ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_RC, bizMsg.addShipToLocNm);
            // 2017/12/08 S21_NA#21621 Add End

            // // Ship To Information from Header.
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_RC, bizMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_RC, bizMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_RC, bizMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_RC, bizMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_RC, bizMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_RC, bizMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_RC, bizMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_RC, bizMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_RC, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_RC, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_RC, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_RC, bizMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_RC, bizMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_RC, bizMsg.shipToProvNm);
        }
        ZYPEZDItemValueSetter.setValue(configLineMsg.dsCpoConfigHldFlg_RC, ZYPConstant.FLG_OFF_N);

        configLineMsg.cratUsrNm_RC.clear();
        configLineMsg.cratTsDplyTxt_RC.clear();
        configLineMsg.updUsrNm_RC.clear();
        configLineMsg.updTsDplyTxt_RC.clear();

        NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, configLineMsg);

        return dsOrdPosnNum;
    }

    private static void copyDataDMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_DSMsg> copylist, List<NWAL1500_ISMsg> prcCalcList) {

        if (dsOrdPosnNum == null) {
            return;
        }

        List<NWAL1500_DSMsg> list = new ArrayList<NWAL1500_DSMsg>();
        List<NWAL1500_ISMsg> prclist = new ArrayList<NWAL1500_ISMsg>();
        BigDecimal max = BigDecimal.ZERO;

        String cpoDtlLineNum = null;
        String cpoDtlLineSubNum = null;
        String oldCpoDtlLineNum = null;
        String newCpoDtlLineNum = null;
        BigDecimal oldDsCpoLineNum = null;
        BigDecimal dsCpoLineNum = null; // 2016/05/17 S21_NA#8434 Add
        newCpoDtlLineNum = getOrderLineNumberForInboundInBizMsgString(glblMsg);

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                if (glblMsg.D.no(i).dsCpoLineNum_RL.getValue().compareTo(max) > 0) {
                    max = glblMsg.D.no(i).dsCpoLineNum_RL.getValue();
                }
            }
        }

        // max = max.add(BigDecimal.ONE); 2016/05/17 S21_NA#8434 Del
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = new NWAL1500_DSMsg();
            EZDMsg.copy(glblMsg.D.no(i), null, lineMsg, null);
            list.add(lineMsg);
        }

        for (int i = 0; i < copylist.size(); i++) {
            NWAL1500_DSMsg lineMsg = new NWAL1500_DSMsg();
            cpoDtlLineNum = copylist.get(i).cpoDtlLineNum_RL.getValue();
            cpoDtlLineSubNum = copylist.get(i).cpoDtlLineSubNum_RL.getValue();
            dsCpoLineNum = copylist.get(i).dsCpoLineNum_RL.getValue(); // 2016/05/17 S21_NA#8434 Add

            EZDMsg.copy(copylist.get(i), null, lineMsg, null);
            // 2016/04/18 S21_NA#6911 Add Start
//            if (lineMsg.ordQty_RL.getValue().compareTo(BigDecimal.ZERO) == 0) {
//            if (ZYPCommonFunc.hasValue(lineMsg.ordQty_RL) && BigDecimal.ZERO.compareTo(lineMsg.ordQty_RL.getValue()) == 0) {
            if (NWAL1500CommonLogic.compareBigDecimal(lineMsg.ordQty_RL.getValue(), BigDecimal.ZERO) == 0) {
                setQtyFromCancelQty(bizMsg.glblCmpyCd.getValue(), lineMsg);
            }
            // 2016/04/18 S21_NA#6911 Add Start
            ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_RL, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdPosnNum_RL, dsOrdPosnNum);
            // 2016/05/17 S21_NA#8434 Mod Start
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_RL, max);

//            if (NWAL1500CommonLogic.compareBigDecimal(oldDsCpoLineNum, max) != 0) {
//                oldDsCpoLineNum = max;
//                max = max.add(BigDecimal.ONE);
//            }
            if (NWAL1500CommonLogic.compareBigDecimal(oldDsCpoLineNum, dsCpoLineNum) != 0) {
                oldDsCpoLineNum = dsCpoLineNum;
                max = max.add(BigDecimal.ONE);
            }
            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_RL, max);
            // 2016/05/17 S21_NA#8434 Mod End

            if (!cpoDtlLineNum.equals(oldCpoDtlLineNum)) {
                oldCpoDtlLineNum = cpoDtlLineNum;
                newCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(newCpoDtlLineNum);
            }

            // 2016/03/05 S21_NS#5000#71 Mod Start
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_RL, newCpoDtlLineNum);
            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_RL, newCpoDtlLineNum);
            } else {
                lineMsg.cpoDtlLineNum_RL.clear();
            }
            // 2016/03/05 S21_NS#5000#71 Mod End
            lineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            lineMsg.ordLineStsCd_RL.clear();
            lineMsg.serNum_RL.clear();
            lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
            lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
            lineMsg.ordLineStsCd_RL.clear();
            // lineMsg.serNum_RL.clear(); 2016/04/18 S21_NA#5520 Del
            lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
            lineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
            lineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
            lineMsg.rtrnLineStsDescTxt_RL.clear();
            lineMsg.dplyLineRefNum_RL.clear();

            lineMsg.cratTsDplyTxt_RL.clear();
            lineMsg.ordBookTsDplyTxt_RL.clear();

            // Add 2016/03/07 S21_NA#5000#78 Start
            ZYPEZDItemValueSetter.setValue(lineMsg.custIssPoNum_RL, bizMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(lineMsg.custIssPoDt_RL, bizMsg.custIssPoDt);
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_RL, bizMsg.cpoSrcTpDescTxt);
            ZYPEZDItemValueSetter.setValue(lineMsg.ordSrcRefNum_RL, bizMsg.ordSrcRefNum);
            // Add 2016/03/07 S21_NA#5000#78 End

            lineMsg.cratUsrNm_RL.clear();
            lineMsg.updUsrNm_RL.clear();
            lineMsg.updTsDplyTxt_RL.clear();

            lineMsg.cancRsnDescTxt_RL.clear(); // QC#5395
            lineMsg.mdseCd_RB.clear(); // QC#18173
            lineMsg.cpoDtlLineNum_RB.clear(); // QC#18173
            lineMsg.cpoDtlLineSubNum_RB.clear(); // QC#18173

            list.add(lineMsg);

            cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_RL.getValue();
            prclist.clear();
            for (int j = 0; j < prcCalcList.size(); j++) {
                NWAL1500_ISMsg prcLineMsg = prcCalcList.get(j);
                if (cpoDtlLineNum.equals(prcLineMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcLineMsg.cpoDtlLineSubNum_LP.getValue()) && CONFIG_CATG.INBOUND.equals(prcLineMsg.configCatgCd_LP.getValue())) {
                    prclist.add(prcLineMsg);
                }
            }

            int cnt = glblMsg.I.getValidCount();
            for (int j = 0; j < prclist.size(); j++) {
                EZDMsg.copy(prclist.get(j), null, glblMsg.I.no(cnt), null);
                ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).cpoDtlLineNum_LP, newCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).xxLineNum_LP, lineMsg.xxLineNum_RL);
                glblMsg.I.no(cnt).ordPrcCalcBasePk_LP.clear();
                cnt++;
            }
            glblMsg.I.setValidCount(cnt);
        }

        Collections.sort(list, new NWAL1500CompareDSMsg());

        // For Performance QC#8166 Add Start
        Map<String, List<Map<String, String>>> rmaUomCacheMap = new HashMap<String, List<Map<String, String>>>();
        // For Performance QC#8166 Add End

        int idx =  0;
        for (EZDMsg line : list) {
            EZDMsg.copy(line, null, glblMsg.D.no(idx), null);
            NWAL1500CommonLogic.setUomPullDownForRmaFromCache(bizMsg, glblMsg.D.no(idx), rmaUomCacheMap); // For Performance QC#8166 Mod // 2018/02/03 S21_NA#19808 Mod
            idx++;
        }
        glblMsg.D.setValidCount(idx);
        String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, bizMsg.slsDt.getValue());
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
            }
        }
    }

    private static void copyDataGMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_GCMsg> copylist) {

        int cnt = bizMsg.G.getValidCount();
        for (NWAL1500_GCMsg msg : copylist) {
            EZDMsg.copy(msg, null, bizMsg.G.no(cnt), null);
            bizMsg.G.no(cnt).xxRqstTpCd_GS.setValue(NWAL1500Constant.REQ_NEW);
            bizMsg.G.no(cnt).dsCpoSlsCrPk_GS.clear();
            bizMsg.G.no(cnt).dsCpoConfigPk_GS.clear(); // 2015/12/07 S21_NA#1420 add
            bizMsg.G.no(cnt).dsOrdPosnNum_GS.setValue(dsOrdPosnNum); // 2015/12/07 S21_NA#1420 add
            cnt = cnt + 1;
        }
        bizMsg.G.setValidCount(cnt);
    }

    private static void copyDataHMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_HCMsg> copylist) {

        int cnt = glblMsg.H.getValidCount();
        for (NWAL1500_HCMsg msg : copylist) {
            EZDMsg.copy(msg, null, bizMsg.H.no(cnt), null);
            bizMsg.H.no(cnt).xxRqstTpCd_HS.setValue(NWAL1500Constant.REQ_NEW);
            bizMsg.H.no(cnt).dsCpoSlsCrPk_HS.clear();
            bizMsg.H.no(cnt).dsCpoConfigPk_HS.clear(); // 2015/12/07 S21_NA#1420 add
            bizMsg.H.no(cnt).dsOrdPosnNum_HS.setValue(dsOrdPosnNum); // 2015/12/07 S21_NA#1420 add
            cnt = cnt + 1;
        }
        bizMsg.H.setValidCount(cnt);
    }

//    private static void copyDataJMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_JSMsg> copylist, List<NWAL1500_ISMsg> prcCalcList) {
//
//        if (dsOrdPosnNum == null) {
//            return;
//        }
//
//        List<NWAL1500_JSMsg> list = new ArrayList<NWAL1500_JSMsg>();
//        List<NWAL1500_ISMsg> prclist = new ArrayList<NWAL1500_ISMsg>();
//        NWAL1500_ISMsg prcLineMsg = new NWAL1500_ISMsg();
//        BigDecimal max = BigDecimal.ZERO;
//
//        String cpoDtlLineNum = null;
//        String cpoDtlLineSubNum = null;
//        String oldCpoDtlLineNum = null;
//        String newCpoDtlLineNum = null;
//        newCpoDtlLineNum = getOrderLineNumberForOutboundInGlblMsgString(glblMsg);
//
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.J.no(i).dsOrdPosnNum_LL.getValue())) {
//                if (glblMsg.J.no(i).dsCpoLineNum_LL.getValue().compareTo(max) > 0) {
//                    max = glblMsg.J.no(i).dsCpoLineNum_LL.getValue();
//                }
//            }
//        }
//
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            NWAL1500_JSMsg lineMsg = new NWAL1500_JSMsg();
//            EZDMsg.copy(glblMsg.J.no(i), null, lineMsg, null);
//            list.add(lineMsg);
//        }
//
//        String baseXxLineNum = null; // 2015/12/24 S21_NA#2080
//        for (int i = 0; i < copylist.size(); i++) {
//            NWAL1500_JSMsg lineMsg = new NWAL1500_JSMsg();
//            cpoDtlLineNum = copylist.get(i).cpoDtlLineNum_LL.getValue();
//            cpoDtlLineSubNum = copylist.get(i).cpoDtlLineSubNum_LL.getValue();
//
//            if (!ZYPCommonFunc.hasValue(copylist.get(i).dsCpoLineSubNum_LL)) { // S21_NA#2905
//                max = max.add(BigDecimal.ONE);
//            }
//
//            EZDMsg.copy(copylist.get(i), null, lineMsg, null);
//            ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_LL, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdPosnNum_LL, dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_LL, max);
//
//            if (!cpoDtlLineNum.equals(oldCpoDtlLineNum) && ZYPCommonFunc.hasValue(cpoDtlLineNum)) { // S21_NA#2905
//                oldCpoDtlLineNum = cpoDtlLineNum;
//                newCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(newCpoDtlLineNum);
//            }
//
//            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
//                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_LL, newCpoDtlLineNum);
//            } else {
//                lineMsg.cpoDtlLineNum_LL.clear();
//            }
//            lineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
//            lineMsg.ordLineStsCd_LL.clear();
//            lineMsg.ordLineStsDescTxt_LL.clear();
//            lineMsg.serNum_LL.clear();
//            lineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
//            lineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue());
//            lineMsg.allocQty_LL.setValue(BigDecimal.ZERO);
//            lineMsg.shipQty_LL.setValue(BigDecimal.ZERO);
//            lineMsg.istlQty_LL.setValue(BigDecimal.ZERO);
//            lineMsg.invQty_LL.setValue(BigDecimal.ZERO);
//            lineMsg.cancQty_LL.setValue(BigDecimal.ZERO);
//
//            if (null == baseXxLineNum && ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
//                baseXxLineNum = lineMsg.xxLineNum_LL.getValue();
//            }
//            list.add(lineMsg);
//
//            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) { // S21_NA#2905
//                cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
//                prclist.clear();
//                for (int j = 0; j < prcCalcList.size(); j++) {
//                    prcLineMsg = prcCalcList.get(j);
//                    if (cpoDtlLineNum.equals(prcLineMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcLineMsg.cpoDtlLineSubNum_LP.getValue()) && CONFIG_CATG.OUTBOUND.equals(prcLineMsg.configCatgCd_LP.getValue())) {
//                        prclist.add(prcLineMsg);
//                    }
//                }
//
//                int cnt = glblMsg.I.getValidCount();
//                for (int j = 0; j < prclist.size(); j++) {
//                    EZDMsg.copy(prclist.get(j), null, glblMsg.I.no(cnt), null);
//                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).cpoDtlLineNum_LP, newCpoDtlLineNum);
//                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).xxLineNum_LP, lineMsg.xxLineNum_LL);
//                    glblMsg.I.no(cnt).ordPrcCalcBasePk_LP.clear();
//                    cnt++;
//                }
//                glblMsg.I.setValidCount(cnt);
//            }
//        }
//
//        for (NWAL1500_JSMsg lineMsg : list) {
//            if (ZYPConstant.FLG_OFF_N.equals(lineMsg.baseCmptFlg_LL.getValue()) && dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
//                ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_LL, baseXxLineNum);
//            }
//        }
//
//        Collections.sort(list, new NWAL1500CompareJSMsg());
//
//        int idx = 0;
//        for (EZDMsg line : list) {
//            EZDMsg.copy(line, null, glblMsg.J.no(idx), null);
//            NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, glblMsg.J.no(idx));
//            idx++;
//        }
//        glblMsg.J.setValidCount(idx);
//    }
//
//    private static void copyDataKMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, List<NWAL1500_KSMsg> copylist, List<NWAL1500_ISMsg> prcCalcList) {
//
//        if (dsOrdPosnNum == null) {
//            return;
//        }
//
//        List<NWAL1500_DSMsg> list = new ArrayList<NWAL1500_DSMsg>();
//        List<NWAL1500_ISMsg> prclist = new ArrayList<NWAL1500_ISMsg>();
//        NWAL1500_ISMsg prcLineMsg = new NWAL1500_ISMsg();
//        BigDecimal max = BigDecimal.ZERO;
//
//        String cpoDtlLineNum = null;
//        String cpoDtlLineSubNum = null;
//        String oldCpoDtlLineNum = null;
//        String newCpoDtlLineNum = null;
//        newCpoDtlLineNum = getOrderLineNumberForInboundInGlblMsgString(glblMsg);
//
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.K.no(i).dsOrdPosnNum_RL.getValue())) {
//                if (glblMsg.K.no(i).dsCpoLineNum_RL.getValue().compareTo(max) > 0) {
//                    max = glblMsg.K.no(i).dsCpoLineNum_RL.getValue();
//                }
//            }
//        }
//
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            NWAL1500_DSMsg lineMsg = new NWAL1500_DSMsg();
//            EZDMsg.copy(glblMsg.K.no(i), null, lineMsg, null);
//            list.add(lineMsg);
//        }
//
//        for (int i = 0; i < copylist.size(); i++) {
//            NWAL1500_DSMsg lineMsg = new NWAL1500_DSMsg();
//            cpoDtlLineNum = copylist.get(i).cpoDtlLineNum_RL.getValue();
//            cpoDtlLineSubNum = copylist.get(i).cpoDtlLineSubNum_RL.getValue();
//
//            if (!ZYPCommonFunc.hasValue(copylist.get(i).dsCpoLineSubNum_RL)) { // S21_NA#2905
//                max = max.add(BigDecimal.ONE);
//            }
//
//            EZDMsg.copy(copylist.get(i), null, lineMsg, null);
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdPosnNum_RL, dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue(lineMsg.dsCpoLineNum_RL, max);
//
//            if (!cpoDtlLineNum.equals(oldCpoDtlLineNum) && ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
//                oldCpoDtlLineNum = cpoDtlLineNum;
//                newCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(newCpoDtlLineNum);
//            }
//
//            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
//                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlLineNum_RL, newCpoDtlLineNum);
//            } else {
//                lineMsg.cpoDtlLineNum_RL.clear();
//            }
//            lineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
//            lineMsg.ordLineStsCd_RL.clear();
//            lineMsg.serNum_RL.clear();
//            lineMsg.ordLineStsCd_RL.clear();
//            lineMsg.rtrnLineStsDescTxt_RL.clear();
//            lineMsg.serNum_RL.clear();
//            lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
//            lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
//            lineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
//            lineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
//            lineMsg.dplyLineRefNum_RL.clear();
//            list.add(lineMsg);
//
//            if (ZYPCommonFunc.hasValue(cpoDtlLineNum)) { // S21_NA#2905
//                cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_RL.getValue();
//                prclist.clear();
//                for (int j = 0; j < prcCalcList.size(); j++) {
//                    prcLineMsg = prcCalcList.get(j);
//                    if (cpoDtlLineNum.equals(prcLineMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcLineMsg.cpoDtlLineSubNum_LP.getValue()) && CONFIG_CATG.INBOUND.equals(prcLineMsg.configCatgCd_LP.getValue())) {
//                        prclist.add(prcLineMsg);
//                    }
//                }
//
//                int cnt = glblMsg.I.getValidCount();
//                for (int j = 0; j < prclist.size(); j++) {
//                    EZDMsg.copy(prclist.get(j), null, glblMsg.I.no(cnt), null);
//                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).cpoDtlLineNum_LP, newCpoDtlLineNum);
//                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(cnt).xxLineNum_LP, lineMsg.xxLineNum_RL);
//                    glblMsg.I.no(cnt).ordPrcCalcBasePk_LP.clear();
//                    cnt = cnt + 1;
//                }
//                glblMsg.I.setValidCount(cnt);
//            }
//        }
//
//        Collections.sort(list, new NWAL1500CompareDSMsg());
//
//        int idx = 0;
//        for (EZDMsg line : list) {
//            EZDMsg.copy(line, null, glblMsg.K.no(idx), null);
//            NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, glblMsg.K.no(idx));
//            idx++;
//        }
//        glblMsg.K.setValidCount(idx);
//    }

    private static List<NWAL1500_ISMsg> getBasePriceData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String xxLineNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String configCatgCd) {

        List<NWAL1500_ISMsg> list = new ArrayList<NWAL1500_ISMsg>();
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg priceLine = glblMsg.I.no(i);
            String xxLineNumP = priceLine.xxLineNum_LP.getValue();
            String cpoDtlLineNumP = priceLine.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumP = priceLine.cpoDtlLineSubNum_LP.getValue();
            String configCatgCdP = priceLine.configCatgCd_LP.getValue();
            if ((xxLineNum.equals(xxLineNumP) || (cpoDtlLineNum.equals(cpoDtlLineNumP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumP))) && configCatgCd.equals(configCatgCdP)) {
                list.add(priceLine);
            }
        }

        return list;
    }

    // 2015/12/10 S21_NA#1420 Add Start
    /**
     * getNextOrderLineNumber
     * @param bizMsg NWAL1500CMsg
     * @return String
     */
    private static String getOrderLineNumberForOutboundInBizMsgString(NWAL1500SMsg glblMsg) {

        String cpoDtlLineNum = "000";
        String cpoDtlLineNumMax = "000";
        NWAL1500_BSMsg lineMsg = null;

        // get max Number
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            lineMsg = glblMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_LL.getValue())) {
                cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
                if (cpoDtlLineNumMax.compareTo(cpoDtlLineNum) < 0) {
                    cpoDtlLineNumMax = cpoDtlLineNum;
                }
            }
        }

        return cpoDtlLineNumMax;
    }

    /**
     * getNextOrderLineNumber
     * @param bizMsg NWAL1500CMsg
     * @return String
     */
//    private static String getOrderLineNumberForOutboundInGlblMsgString(NWAL1500SMsg glblMsg) {
//
//        String cpoDtlLineNum = "000";
//        String cpoDtlLineNumMax = "000";
//        NWAL1500_BCMsg lineBMsg = null;
//
//        // get max Number
//        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
//            lineSMsg = glblMsg.J.no(i);
//            if (ZYPCommonFunc.hasValue(lineSMsg.cpoDtlLineNum_LL.getValue())) {
//                cpoDtlLineNum = lineSMsg.cpoDtlLineNum_LL.getValue();
//                if (cpoDtlLineNumMax.compareTo(cpoDtlLineNum) < 0) {
//                    cpoDtlLineNumMax = cpoDtlLineNum;
//                }
//            }
//        }
//
//        return cpoDtlLineNumMax;
//    }

    /**
     * getNextOrderLineNumber
     * @param bizMsg NWAL1500CMsg
     * @return String
     */
    private static String getOrderLineNumberForInboundInBizMsgString(NWAL1500SMsg glblMsg) {

        String cpoDtlLineNum = "000";
        String cpoDtlLineNumMax = "000";
        NWAL1500_DSMsg lineMsg = null;

        // get max Number
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            lineMsg = glblMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_RL.getValue())) {
                cpoDtlLineNum = lineMsg.cpoDtlLineNum_RL.getValue();
                if (cpoDtlLineNumMax.compareTo(cpoDtlLineNum) < 0) {
                    cpoDtlLineNumMax = cpoDtlLineNum;
                }
            }
        }

        return cpoDtlLineNumMax;
    }

    /**
     * getNextOrderLineNumber
     * @param bizMsg NWAL1500CMsg
     * @return String
     */
//    private static String getOrderLineNumberForInboundInGlblMsgString(NWAL1500SMsg glblMsg) {
//
//        String cpoDtlLineNum = "000";
//        String cpoDtlLineNumMax = "000";
//        NWAL1500_KSMsg lineSMsg = null;
//
//        // get max Number
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            lineSMsg = glblMsg.K.no(i);
//            if (ZYPCommonFunc.hasValue(lineSMsg.cpoDtlLineNum_RL.getValue())) {
//                cpoDtlLineNum = lineSMsg.cpoDtlLineNum_RL.getValue();
//                if (cpoDtlLineNumMax.compareTo(cpoDtlLineNum) < 0) {
//                    cpoDtlLineNumMax = cpoDtlLineNum;
//                }
//            }
//        }
//
//        return cpoDtlLineNumMax;
//    }

    // 2015/12/10 S21_NA#1420 Add End

    /**
     * get CPO Source Type Desc Text
     * @param bizMsg NWAL1500CMsg
     * @param cpoSrcTpCd CPO Source Type Code
     * @return CPO Source Type Desc Text
     */
    private static String getCpoSrcTpDescTxt(NWAL1500CMsg bizMsg, String cpoSrcTpCd) {

        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, cpoSrcTpCd);
        tMsg = (CPO_SRC_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.cpoSrcTpDescTxt.getValue();
        }

        return null;
    }


    /**
     * <pre>
     * Copy from Screen (CMsg) to back yard (Collapsed data, Global Message)
     * </pre>
     * @param configCatgCd CONFIG_CATG_CD (OUTBOUND, INBOUND)
     * @param dsOrdPosnNum Position Number
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
//    public static void backupScreenData(String configCatgCd, String dsOrdPosnNum, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
//        int backupCnt = 0;
//        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//            backupCnt = glblMsg.J.getValidCount();
//            for (int lineCnt = 0; lineCnt < bizMsg.B.getValidCount(); lineCnt++) {
//                NWAL1500_BCMsg lineMsg = bizMsg.B.no(lineCnt);
//                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
//                    NWAL1500_JSMsg backupLine = getDataFromJ(glblMsg, dsOrdPosnNum, lineMsg.dsCpoLineNum_LL.getValue(), lineMsg.dsCpoLineSubNum_LL.getValue());
//                    if (null != backupLine) {
//                        // Modify with Screen data
//                        EZDMsg.copy(lineMsg, null, backupLine, null);
//                    } else {
//                        // Insert to Backyard Data
//                        EZDMsg.copy(lineMsg, null, glblMsg.J.no(backupCnt), null);
//                        backupCnt++;
//                    }
//                }
//            }
//            glblMsg.J.setValidCount(backupCnt);
//        } else {
//            backupCnt = glblMsg.K.getValidCount();
//            for (int lineCnt = 0; lineCnt < bizMsg.D.getValidCount(); lineCnt++) {
//                NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(lineCnt);
//                if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
//                    NWAL1500_KSMsg backupLine = getDataFromK(glblMsg, dsOrdPosnNum, rmaLineMsg.dsCpoLineNum_RL.getValue(), rmaLineMsg.dsCpoLineSubNum_RL.getValue());
//                    if (null != backupLine) {
//                        // Modify with Screen data
//                        EZDMsg.copy(rmaLineMsg, null, backupLine, null);
//                    } else {
//                        // Insert to Backyard Data
//                        EZDMsg.copy(rmaLineMsg, null, glblMsg.K.no(backupCnt), null);
//                        backupCnt++;
//                    }
//                }
//            }
//            glblMsg.K.setValidCount(backupCnt);
//        }
//    }
//
//    private static void copyToExpandedData(String configCatgCd, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
//
//        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//            int bCnt = 0;
//            bizMsg.B.clear();
//            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(i).xxSmryLineFlg_L.getValue())) {
//                    String dsOrdPosnNumForCopy = bizMsg.A.no(i).dsOrdPosnNum_LC.getValue();
//                    for (int j = 0; j < glblMsg.J.getValidCount(); j++) {
//                        String dsOrdPosnNumFromJ = glblMsg.J.no(j).dsOrdPosnNum_LL.getValue();
//                        if (dsOrdPosnNumForCopy.equals(dsOrdPosnNumFromJ)) {
//                            EZDMsg.copy(glblMsg.J.no(j), null, bizMsg.B.no(bCnt), null);
//                            bCnt++;
//                        }
//                    }
//                }
//            }
//            bizMsg.B.setValidCount(bCnt);
//        } else {
//            int dCnt = 0;
//            bizMsg.D.clear();
//            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.C.no(i).xxSmryLineFlg_R.getValue())) {
//                    String dsOrdPosnNumForCopy = bizMsg.C.no(i).dsOrdPosnNum_RC.getValue();
//                    for (int j = 0; j < glblMsg.K.getValidCount(); j++) {
//                        String dsOrdPosnNumFromK = glblMsg.K.no(j).dsOrdPosnNum_RL.getValue();
//                        if (dsOrdPosnNumForCopy.equals(dsOrdPosnNumFromK)) {
//                            EZDMsg.copy(glblMsg.K.no(j), null, bizMsg.D.no(dCnt), null);
//                            dCnt++;
//                        }
//                    }
//                }
//            }
//            bizMsg.D.setValidCount(dCnt);
//        }
//    }

    // 2016/04/18 S21_NA#6911 Add Start
    private static void setQtyFromCancelQty(String glblCmpyCd, EZDMsg lineMsg) {

        String mdseCd = null;
        String pkgUomCd = null;
        BigDecimal ordQty = null;
        BigDecimal ordCustUomQty = null;
        NWAL1500_BCMsg confLineCMsg = null;
        NWAL1500_DCMsg rmaLineCMsg = null;
        NWAL1500_BSMsg confLineSMsg = null;
        NWAL1500_DSMsg rmaLineSMsg = null;

        if (lineMsg instanceof NWAL1500_BCMsg) {
            confLineCMsg = (NWAL1500_BCMsg) lineMsg;
            mdseCd = confLineCMsg.mdseCd_LL.getValue();
            pkgUomCd = confLineCMsg.custUomCd_LL.getValue();
            ordQty = confLineCMsg.cancQty_LL.getValue();
        } else if (lineMsg instanceof NWAL1500_DCMsg) {
            rmaLineCMsg = (NWAL1500_DCMsg) lineMsg;
            mdseCd = rmaLineCMsg.mdseCd_RL.getValue();
            pkgUomCd = rmaLineCMsg.custUomCd_RL.getValue();
            ordQty = rmaLineCMsg.cancQty_RL.getValue();
        } else if (lineMsg instanceof NWAL1500_BSMsg) {
            confLineSMsg = (NWAL1500_BSMsg) lineMsg;
            mdseCd = confLineSMsg.mdseCd_LL.getValue();
            pkgUomCd = confLineSMsg.custUomCd_LL.getValue();
            ordQty = confLineSMsg.cancQty_LL.getValue();
        } else if (lineMsg instanceof NWAL1500_DSMsg) {
            rmaLineSMsg = (NWAL1500_DSMsg) lineMsg;
            mdseCd = rmaLineSMsg.mdseCd_RL.getValue();
            pkgUomCd = rmaLineSMsg.custUomCd_RL.getValue();
            ordQty = rmaLineSMsg.cancQty_RL.getValue();
        }
        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkgInfo(glblCmpyCd, pkgUomCd, mdseCd);

        if (null != mdseStorePkgTMsg) {
            try {
                BigDecimal inEachQty = mdseStorePkgTMsg.inEachQty.getValue();
                int qtyScale = inEachQty.scale();
                ordCustUomQty = ordQty.divide(inEachQty, qtyScale);
            } catch (Exception ex) {
                ordCustUomQty = ordQty;
            }
        } else {
            ordCustUomQty = ordQty;
        }

        if (null != confLineCMsg) {
            ZYPEZDItemValueSetter.setValue(confLineCMsg.ordQty_LL, ordQty);
            ZYPEZDItemValueSetter.setValue(confLineCMsg.ordCustUomQty_LL, ordCustUomQty);
        } else if (null != rmaLineCMsg) {
            ZYPEZDItemValueSetter.setValue(rmaLineCMsg.ordQty_RL, ordQty);
            ZYPEZDItemValueSetter.setValue(rmaLineCMsg.ordCustUomQty_RL, ordCustUomQty);
        } else if (null != confLineSMsg) {
            ZYPEZDItemValueSetter.setValue(confLineSMsg.ordQty_LL, ordQty);
            ZYPEZDItemValueSetter.setValue(confLineSMsg.ordCustUomQty_LL, ordCustUomQty);
        } else if (null != rmaLineSMsg) {
            ZYPEZDItemValueSetter.setValue(rmaLineSMsg.ordQty_RL, ordQty);
            ZYPEZDItemValueSetter.setValue(rmaLineSMsg.ordCustUomQty_RL, ordCustUomQty);
        }
    }

    private static MDSE_STORE_PKGTMsg getMdseStorePkgInfo(String glblCmpyCd, String pkgUomCd, String mdseCd) {

        String locMdseCd = mdseCd;
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, mdseCd);
        if (null != mdseTMsg) {
            locMdseCd = mdseTMsg.mdseCd.getValue();
        }
        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, pkgUomCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, locMdseCd);

        return (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }
    // 2016/04/18 S21_NA#6911 Add Start

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    // 2016/10/11 S21_NA#7924-2 Add Start
//    /**
//     * <pre>
//     * Copy Order without using API
//     * @param bizMsg Business Message cpoOrdNum copy target Order Number should be set.
//     * @param userInfo Login User Info
//     * </pre>
//     */
//    public static void copyOrderWithoutApi(NWAL1500CMsg bizMsg, S21UserInfo userInfo) {
//
//        final String newCpoOrdNum = ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM");
//        bizMsg.xxSrchNum.setValue(newCpoOrdNum);
//
//        String userId = userInfo.getUserId();
//        String timeStamp = EZDDBCICarrier.getStartDateTime();
//
//        if (registCopyCpo(bizMsg, userId, timeStamp)) {
//            return;
//        }
//
//        // Copy Only Header
//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPopPrm_P3.getValue())) {
//            // S21_NA#16806 Add Start
//            // DS_CPO_SLS_CR
//            if (registCopyDsCpoSlsCr(bizMsg, null, userId, timeStamp, false)) {
//                return;
//            }
//
//            // DS_CPO_DELY_INFO
//            if (registCopyDsCpoDelyInfo(bizMsg, null, false)) {
//                return;
//            }
//
//            // DS_CPO_ISTL_INFO
//            if (registCopyDsCpoIstlInfo(bizMsg, null, false)) {
//                return;
//            }
//
//            // DS_SITE_SRVY
//            if (registCopyDsSiteSrvy(bizMsg, null, false)) {
//                return;
//            }
//
//            // DS_CPO_CTAC_PSN
//            if (registCopyDsCpoCtacPsnForHdr(bizMsg)) {
//                return;
//            }
//            // S21_NA#16806 Add End
//
//            return;
//        }
//
//        // DS_CPO_CONFIG
//        Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap = new HashMap<BigDecimal, DS_CPO_CONFIGTMsg>();
//        if (registCopyDsCpoConfig(bizMsg, userId, timeStamp, dsCpoConfigMap)) {
//            return;
//        }
//
//        // CPO_DTL
//        CPOTMsg copyCpoTMsg = new CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
//        copyCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(copyCpoTMsg);
//        if (registCopyCpoDtl(bizMsg, copyCpoTMsg, dsCpoConfigMap, userId, timeStamp)) {
//            return;
//        }
//
//        // ORD_PRC_CALC_BASE
//        if (registCopyOrdPrcCalcBase(bizMsg, userId, timeStamp)) {
//            return;
//        }
//
//        // DS_CPO_RTRN_DTL
//        List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList = new ArrayList<DS_CPO_RTRN_DTLTMsg>(0);
//        if (registCopyDsCpoRtrnDtl(bizMsg, dsCpoConfigMap, userId, timeStamp, copyDsCpoRtrnDtlTMsgList)) {
//            return;
//        }
//
//        // CPO_RTRN_PRC_CALC_BASETMsg
//        if (registCopyCpoRtrnPrcCalcBase(bizMsg, copyDsCpoRtrnDtlTMsgList, userId, timeStamp)) {
//            return;
//        }
//
//        // DS_CPO_SLS_CR
//        if (registCopyDsCpoSlsCr(bizMsg, dsCpoConfigMap, userId, timeStamp, true)) {
//            return;
//        }
//
//        // DS_CPO_DELY_INFO (No REC)
//        if (registCopyDsCpoDelyInfo(bizMsg, dsCpoConfigMap, true)) {
//            return;
//        }
//
//        // DS_CPO_ISTL_INFO (No REC)
//        if (registCopyDsCpoIstlInfo(bizMsg, dsCpoConfigMap, true)) {
//            return;
//        }
//
//        // DS_SITE_SRVY (No REC)
//        if (registCopyDsSiteSrvy(bizMsg, dsCpoConfigMap, true)) {
//            return;
//        }
//
//        // DS_CPO_CTAC_PSN (No REC)
//        if (registCopyDsCpoCtacPsn(bizMsg, dsCpoConfigMap)) {
//            return;
//        }
//
//        // CPO_SVC Shell
//        if (registCopyShellDataCpoSvc(bizMsg, userId, timeStamp)) {
//            return;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyCpo(NWAL1500CMsg bizMsg, String userId, String timeStamp) {
//
//        CPOTMsg origCpoTMsg = new CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(origCpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(origCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
//
//        CPOTMsg copyCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origCpoTMsg);
//        if (copyCpoTMsg == null) {
//            bizMsg.setMessageInfo(NZZM0000E);
//            return true;
//        }
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdNum, bizMsg.xxSrchNum);
//        // cpoOrdTs doesn't accept bizMsg.slsDt, then .getValue() method was added.
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdTs, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordHdrStsCd, ORD_HDR_STS.SAVED);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.adminPsnCd, userId);
//        // 2016/10/27 S21_NA#7924-4 Del Start
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotDealSlsAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotDealDiscAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotDealNetAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotFuncSlsAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotFuncDiscAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.entCpoTotFuncNetAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotDealSlsAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotDealDiscAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotDealNetAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotFuncSlsAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotFuncDiscAmt, BigDecimal.ZERO);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoTotFuncNetAmt, BigDecimal.ZERO);
//        // 2016/10/27 S21_NA#7924-4 Del End
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.addRddDt, bizMsg.slsDt);
//        copyCpoTMsg.addRsdDt.clear();
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
//        copyCpoTMsg.cpoCancDt.clear();
//        copyCpoTMsg.origOrdNum.clear();
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoSrcTpCd, CPO_SRC_TP.COPY);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.sysSrcCd, SYS_SRC.S21_ORDER);
////        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.shpgChrgAmt, BigDecimal.ZERO); 2016/10/27 S21_NA#7924-4 Del
//
//        if (ZYPCommonFunc.hasValue(copyCpoTMsg.orgRqstDelyDt)) {
//            ZYPEZDItemValueSetter.setValue(copyCpoTMsg.orgRqstDelyDt, bizMsg.slsDt);
//        }
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.prcBaseDt, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.prcCalcDt, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordSrcImptDt, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordSrcRefNum, bizMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
//        copyCpoTMsg.ordBookTs.clear();
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.dsCpoPrcInd, ZYPConstant.FLG_OFF_0);
//        copyCpoTMsg.prePmtChkNum.clear();
//        copyCpoTMsg.prePmtAmt.clear();
//        copyCpoTMsg.prePmtTpCd.clear();
//        copyCpoTMsg.dsCpoCratTs.setValue(timeStamp);
//        copyCpoTMsg.dsCpoCratUsrId.setValue(userId);
//        copyCpoTMsg.dsCpoUpdTs.setValue(timeStamp);
//        copyCpoTMsg.dsCpoUpdUsrId.setValue(userId);
//        copyCpoTMsg.ordBookReqUsrId.clear();
//        copyCpoTMsg.ordBookReqTs.clear();
//        copyCpoTMsg.ordSrcImptTs.clear();
//        copyCpoTMsg.dsImptOrdPk.clear();
//
//        S21FastTBLAccessor.insert(copyCpoTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(copyCpoTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO", bizMsg.cpoOrdNum.getValue() });
//            return true;
//        }
//
//        // Biz Log
//        insertProcLog(copyCpoTMsg);
//
//        // Rec
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ezUpTime, timeStamp);
//        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ezUpUserID, userId);
//
//        BigDecimal bizProcLogLogPk = getBizProcLogPk(copyCpoTMsg);
//        if (bizProcLogLogPk != null) {
//            CPO_RECTMsg cpoRecMsg = new CPO_RECTMsg();
//            EZDMsg.copy(copyCpoTMsg, null, cpoRecMsg, null);
//            cpoRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
//            S21FastTBLAccessor.insert(cpoRecMsg);
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoConfig(NWAL1500CMsg bizMsg, String userId, String timeStamp, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {
//
//        DS_CPO_CONFIGTMsg origDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
//        origDsCpoConfigTMsg.setSQLID("001");
//        origDsCpoConfigTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsCpoConfigTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        // 2017/03/02 S21_NA#17714-4 Add Start
//        boolean isRetailEquipmentOrd = false;
//        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) //
//                || !ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
//            CPOTMsg origDsCpoTMsg = new CPOTMsg();
//            ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
//
//            origDsCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origDsCpoTMsg);
//            if (origDsCpoTMsg != null) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, origDsCpoTMsg.dsOrdCatgCd);
//                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, origDsCpoTMsg.dsOrdTpCd);
//            }
//        }
//        isRetailEquipmentOrd = NWAL1500CommonLogicForLineConfig.isExistOrdCatg(bizMsg, false);
//        // 2017/03/02 S21_NA#17714-4 Add End
//        DS_CPO_CONFIGTMsgArray copyDsCpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoConfigTMsg);
//        List<DS_CPO_CONFIGTMsg> copyDsCpoConfigTMsgList = new ArrayList<DS_CPO_CONFIGTMsg>(0);
//        for (int i = 0; i < copyDsCpoConfigTMsgArray.getValidCount(); i++) {
//            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
//            EZDMsg.copy(copyDsCpoConfigTMsgArray.no(i), null, copyDsCpoConfigTMsg, null);
//
//            copyDsCpoConfigTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            BigDecimal dsCpoConfigPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CONFIG_SQ);
//            copyDsCpoConfigTMsg.dsCpoConfigPk.setValue(dsCpoConfigPk);
//            BigDecimal svcConfigMstrPk = null;
//            String configCatgCd = copyDsCpoConfigTMsg.configCatgCd.getValue();
//            boolean newSvcConfigMstrFlg = false;
//            if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//                // 2017/03/10 S21_NA#17979 Mod Start
////                if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), copyDsCpoConfigTMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, false) //
////                        && isRetailEquipmentOrd) { // 2017/03/02 S21_NA#17714-4 Add isRetailEquipmentOrd
////                    newSvcConfigMstrFlg = true;
////                }
//                CONFIG_TPTMsg configTpTMsg = new CONFIG_TPTMsg();
//                configTpTMsg.glblCmpyCd.setValue(copyDsCpoConfigTMsg.glblCmpyCd.getValue());
//                configTpTMsg.configTpCd.setValue(copyDsCpoConfigTMsg.configTpCd.getValue());
//                configTpTMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(configTpTMsg);
//                if (configTpTMsg != null) {
//                    newSvcConfigMstrFlg = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configTpTMsg.configPkAsgFlg.getValue());
//                    if (!isRetailEquipmentOrd) {
//                        newSvcConfigMstrFlg = false;
//                    }
//                } else {
//                    newSvcConfigMstrFlg = true;
//                }
//                // 2017/03/10 S21_NA#17979 Mod End
//            } else if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
//                if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), copyDsCpoConfigTMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, true, false, false)) {
//                    newSvcConfigMstrFlg = false;
//                    copyDsCpoConfigTMsg.svcConfigMstrPk.clear();
//                }
//            } else {
//                continue;
//            }
//            if (newSvcConfigMstrFlg) {
//                svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
//                copyDsCpoConfigTMsg.svcConfigMstrPk.setValue(svcConfigMstrPk);
//            }
//            dsCpoConfigMap.put(copyDsCpoConfigTMsgArray.no(i).dsCpoConfigPk.getValue(), copyDsCpoConfigTMsg);
//            copyDsCpoConfigTMsgList.add(copyDsCpoConfigTMsg);
//        }
//        if (!copyDsCpoConfigTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoConfigTMsgList.toArray(new DS_CPO_CONFIGTMsg[0]));
//            if (rsltCnt != copyDsCpoConfigTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_CONFIG", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Log
//            for (DS_CPO_CONFIGTMsg dsCpoConfigTMsg : copyDsCpoConfigTMsgList) {
//                insertProcLog(dsCpoConfigTMsg);
//
//                // Rec
//                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.ezUpTime, timeStamp);
//                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.ezUpUserID, userId);
//
//                BigDecimal bizProcLogLogPk = getBizProcLogPk(dsCpoConfigTMsg);
//                if (bizProcLogLogPk != null) {
//                    DS_CPO_CONFIG_RECTMsg dsCpoConfigRecMsg = new DS_CPO_CONFIG_RECTMsg();
//                    EZDMsg.copy(dsCpoConfigTMsg, null, dsCpoConfigRecMsg, null);
//                    dsCpoConfigRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
//                    S21FastTBLAccessor.insert(dsCpoConfigRecMsg);
//                }
//            }
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyCpoDtl(NWAL1500CMsg bizMsg, CPOTMsg cpoTMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp) {
//
//        CPO_DTLTMsg origCpoDtlTMsg = new CPO_DTLTMsg();
//        origCpoDtlTMsg.setSQLID("008");
//        origCpoDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origCpoDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        CPO_DTLTMsgArray copyCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(origCpoDtlTMsg);
//        List<CPO_DTLTMsg> copyCpoDtlTMsgList = new ArrayList<CPO_DTLTMsg>(0);
//
//        Map<String, CPO_DTLTMsg> setCacheMap = new HashMap<String, CPO_DTLTMsg>();
//        for (int i = 0; i < copyCpoDtlTMsgArray.getValidCount(); i++) {
//            CPO_DTLTMsg copyCpoDtlTMsg = new CPO_DTLTMsg();
//            EZDMsg.copy(copyCpoDtlTMsgArray.no(i), null, copyCpoDtlTMsg, null);
//            BigDecimal cancQty = copyCpoDtlTMsg.cancQty.getValue();
//            BigDecimal ordQty = copyCpoDtlTMsg.ordQty.getValue();
//            if ((cancQty != null && BigDecimal.ZERO.compareTo(cancQty) <= 0) //
//                    && (ordQty != null && BigDecimal.ZERO.compareTo(ordQty) == 0)) {
//                copyCpoDtlTMsg.ordQty.setValue(cancQty);
//            }
//            copyCpoDtlTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyCpoDtlTMsg.ordLineStsCd.setValue(ORD_LINE_STS.SAVED);
//            copyCpoDtlTMsg.shipQty.setValue(BigDecimal.ZERO);
//            copyCpoDtlTMsg.cancQty.setValue(BigDecimal.ZERO);
//            copyCpoDtlTMsg.origCpoOrdNum.clear();
//            copyCpoDtlTMsg.origInvNum.clear();
//            copyCpoDtlTMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_OFF_N);
//            copyCpoDtlTMsg.cpoDtlCancDt.clear();
//            copyCpoDtlTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//            copyCpoDtlTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
//            copyCpoDtlTMsg.openFlg.setValue(ZYPConstant.FLG_ON_Y);
//
//            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyCpoDtlTMsg.dsCpoConfigPk.getValue());
//            if (copyDsCpoConfigTMsg == null) {
//                continue;
//            }
//            BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//            copyCpoDtlTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyCpoDtlTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//            copyCpoDtlTMsg.loanBalQty.setValue(BigDecimal.ZERO);
//            copyCpoDtlTMsg.origCpoDtlLineNum.clear();
//            copyCpoDtlTMsg.origCpoDtlLineSubNum.clear();
//            ZYPEZDItemValueSetter.setValue(copyCpoDtlTMsg.svcConfigMstrPk, copyDsCpoConfigTMsg.svcConfigMstrPk);
//            copyCpoDtlTMsg.dsCpoDtlCratTs.setValue(timeStamp);
//            copyCpoDtlTMsg.dsCpoDtlCratUsrId.setValue(userId);
//            copyCpoDtlTMsg.dsCpoDtlUpdTs.setValue(timeStamp);
//            copyCpoDtlTMsg.dsCpoDtlUpdUsrId.setValue(userId);
//
//            setRddRsdExpdDt(bizMsg, cpoTMsg, copyCpoDtlTMsg, setCacheMap);
//
//            copyCpoDtlTMsgList.add(copyCpoDtlTMsg);
//        }
//
//        // 2016/12/02 S21_NA#16292 Add Start
//        for (String cpoDtlLineNum : setCacheMap.keySet()) {
//            CPO_DTLTMsg childCpoDtlTMsg = setCacheMap.get(cpoDtlLineNum);
//            for (int i = 0; i < copyCpoDtlTMsgList.size(); i++) {
//                CPO_DTLTMsg setCpoDtlTMsg = copyCpoDtlTMsgList.get(i);
//                if ("000".equals(setCpoDtlTMsg.cpoDtlLineSubNum.getValue())
//                        && S21StringUtil.isEquals(cpoDtlLineNum, setCpoDtlTMsg.cpoDtlLineNum.getValue())) {
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rddDt, childCpoDtlTMsg.rddDt);
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rsdDt, childCpoDtlTMsg.rsdDt);
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.expdShipDt, childCpoDtlTMsg.expdShipDt);
//                }
//            }
//        }
//        // 2016/12/02 S21_NA#16292 Add End
//
//        if (!copyCpoDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyCpoDtlTMsgList.toArray(new CPO_DTLTMsg[0]));
//            if (rsltCnt != copyCpoDtlTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_DTL", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        // 2016/12/02 S21_NA#16292 Add Start
//        for (String cpoDtlLineNum : setCacheMap.keySet()) {
//            CPO_DTLTMsg childCpoDtlTMsg = setCacheMap.get(cpoDtlLineNum);
////            for (int i = 0; i < copyCpoDtlTMsgArray.getValidCount(); i++) {
////                CPO_DTLTMsg setCpoDtlTMsg = copyCpoDtlTMsgArray.no(i);
//            for (int i = 0; i < copyCpoDtlTMsgList.size(); i++) {
//                CPO_DTLTMsg setCpoDtlTMsg = copyCpoDtlTMsgList.get(i);
//                if ("000".equals(setCpoDtlTMsg.cpoDtlLineSubNum.getValue())
//                        && S21StringUtil.isEquals(cpoDtlLineNum, setCpoDtlTMsg.cpoDtlLineNum.getValue())) {
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rddDt, childCpoDtlTMsg.rddDt);
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rsdDt, childCpoDtlTMsg.rsdDt);
//                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.expdShipDt, childCpoDtlTMsg.expdShipDt);
//                }
//            }
//        }
//        // 2016/12/02 S21_NA#16292 Add End
//
//        // Biz Log
//        for (CPO_DTLTMsg cpoDtlTMsg : copyCpoDtlTMsgList) {
//            insertProcLog(cpoDtlTMsg);
//
//            // Rec
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpTime, timeStamp);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpUserID, userId);
//
//            BigDecimal bizProcLogLogPk = getBizProcLogPk(cpoDtlTMsg);
//            if (bizProcLogLogPk != null) {
//                CPO_DTL_RECTMsg cpoDtlRecMsg = new CPO_DTL_RECTMsg();
//                EZDMsg.copy(cpoDtlTMsg, null, cpoDtlRecMsg, null);
//                cpoDtlRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
//                S21FastTBLAccessor.insert(cpoDtlRecMsg);
//            }
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoRtrnDtl(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp, List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList) {
//
//        DS_CPO_RTRN_DTLTMsg origDSCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
//        origDSCpoRtrnDtlTMsg.setSQLID("001");
//        origDSCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDSCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_CPO_RTRN_DTLTMsgArray copyDsCpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoRtrnDtlTMsg);
//        for (int i = 0; i < copyDsCpoRtrnDtlTMsgArray.getValidCount(); i++) {
//            DS_CPO_RTRN_DTLTMsg copyDsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
//            EZDMsg.copy(copyDsCpoRtrnDtlTMsgArray.no(i), null, copyDsCpoRtrnDtlTMsg, null);
//
//            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoRtrnDtlTMsg.dsCpoConfigPk.getValue());
//            if (copyDsCpoConfigTMsg == null) {
//                continue;
//            }
//            BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//            BigDecimal cancQty = copyDsCpoRtrnDtlTMsg.cancQty.getValue();
//            BigDecimal ordQty = copyDsCpoRtrnDtlTMsg.ordQty.getValue();
//            if ((cancQty != null && BigDecimal.ZERO.compareTo(cancQty) <= 0) //
//                    && (ordQty != null && BigDecimal.ZERO.compareTo(ordQty) == 0)) {
//                copyDsCpoRtrnDtlTMsg.ordQty.setValue(cancQty);
//            }
//            copyDsCpoRtrnDtlTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoRtrnDtlTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//            copyDsCpoRtrnDtlTMsg.rtrnLineStsCd.setValue(RTRN_LINE_STS.ENTERED);
//            copyDsCpoRtrnDtlTMsg.rtrnQty.setValue(BigDecimal.ZERO);
//            copyDsCpoRtrnDtlTMsg.cancQty.setValue(BigDecimal.ZERO);
//            copyDsCpoRtrnDtlTMsg.origCpoOrdNum.clear();
//            copyDsCpoRtrnDtlTMsg.origCpoDtlLineNum.clear();
//            copyDsCpoRtrnDtlTMsg.origCpoDtlLineSubNum.clear();
//            copyDsCpoRtrnDtlTMsg.origInvNum.clear();
//            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.svcConfigMstrPk, copyDsCpoConfigTMsg.svcConfigMstrPk);
//            // 2016/10/28 S21_NA#7924-5 Mod Start
//            // cpoOrdTs doesn't accept bizMsg.slsDt, then .getValue() method was added.
//            // ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.cpoOrdTs, bizMsg.slsDt.getValue());
//            copyDsCpoRtrnDtlTMsg.cpoOrdTs.clear();
//            // 2016/10/28 S21_NA#7924-5 Mod End
//            copyDsCpoRtrnDtlTMsg.cpoOrdSubmtTs.clear();
//            copyDsCpoRtrnDtlTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
//            copyDsCpoRtrnDtlTMsg.openFlg.setValue(ZYPConstant.FLG_ON_Y);
//            copyDsCpoRtrnDtlTMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_OFF_N);
//            copyDsCpoRtrnDtlTMsg.cpoDtlCancDt.clear();
//            copyDsCpoRtrnDtlTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.rqstPickUpDt, bizMsg.slsDt);
//
//            copyDsCpoRtrnDtlTMsgList.add(copyDsCpoRtrnDtlTMsg);
//
//        }
//        if (!copyDsCpoRtrnDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoRtrnDtlTMsgList.toArray(new DS_CPO_RTRN_DTLTMsg[0]));
//            if (rsltCnt != copyDsCpoRtrnDtlTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_RTRN_DTL", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : copyDsCpoRtrnDtlTMsgList) {
//                // Biz Log
//                insertProcLog(dsCpoRtrnDtlTMsg);
//
//                // Rec
//                dsCpoRtrnDtlTMsg.ezUpUserID.setValue(userId);
//                dsCpoRtrnDtlTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(dsCpoRtrnDtlTMsg);
//                if (bizProcLogPk != null) {
//                    DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecTMsg = new DS_CPO_RTRN_DTL_RECTMsg();
//                    EZDMsg.copy(dsCpoRtrnDtlTMsg, null, dsCpoRtrnDtlRecTMsg, null);
//                    dsCpoRtrnDtlRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//
//                    S21FastTBLAccessor.insert(dsCpoRtrnDtlRecTMsg);
//                }
//            }
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//   private static boolean registCopyOrdPrcCalcBase(NWAL1500CMsg bizMsg, String userId, String timeStamp) {
//
//        ORD_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
//
//        // For Header DS_CPO_SLS_CR
//        origOrdPrcCalcBaseTMsg.setSQLID("002");
//        origOrdPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origOrdPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        ORD_PRC_CALC_BASETMsgArray origOrdPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(origOrdPrcCalcBaseTMsg);
//        List<ORD_PRC_CALC_BASETMsg> copyOrdPrcCalcBaseTMsgList = new ArrayList<ORD_PRC_CALC_BASETMsg>(0);
//        for (int i = 0; i < origOrdPrcCalcBaseTMsgArray.getValidCount(); i++) {
//            ORD_PRC_CALC_BASETMsg copyOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
//            EZDMsg.copy(origOrdPrcCalcBaseTMsgArray.no(i), null, copyOrdPrcCalcBaseTMsg, null);
//
//            copyOrdPrcCalcBaseTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyOrdPrcCalcBaseTMsg.ordPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRC_CALC_BASE_SQ));
//
//            copyOrdPrcCalcBaseTMsgList.add(copyOrdPrcCalcBaseTMsg);
//        }
//
//        if (!copyOrdPrcCalcBaseTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyOrdPrcCalcBaseTMsgList.toArray(new ORD_PRC_CALC_BASETMsg[0]));
//            if (rsltCnt != copyOrdPrcCalcBaseTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"ORD_PRC_CALC_BASE", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//
//            // REC
//            Map<String, BigDecimal> recKeyMap = new HashMap<String, BigDecimal>(0);
//            for (ORD_PRC_CALC_BASETMsg copyOrdPrcCalcBaseTMsg : copyOrdPrcCalcBaseTMsgList) {
//                String hashKey = copyOrdPrcCalcBaseTMsg.cpoOrdNum.getValue() + ".";
//                hashKey = hashKey + copyOrdPrcCalcBaseTMsg.cpoDtlLineNum.getValue() + ".";
//                hashKey = hashKey + copyOrdPrcCalcBaseTMsg.cpoDtlLineSubNum.getValue();
//
//                BigDecimal bizProcLogPk = recKeyMap.get(hashKey);
//                if (bizProcLogPk == null) {
//                    CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, copyOrdPrcCalcBaseTMsg.cpoOrdNum);
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, copyOrdPrcCalcBaseTMsg.cpoDtlLineNum);
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, copyOrdPrcCalcBaseTMsg.cpoDtlLineSubNum);
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpUserID, userId);
//                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpTime, timeStamp);
//
//                    bizProcLogPk = getBizProcLogPk(cpoDtlTMsg);
//                    if (bizProcLogPk == null) {
//                        continue;
//                    }
//                    recKeyMap.put(hashKey, bizProcLogPk);
//                }
//
//                ORD_PRC_CALC_BASE_RECTMsg copyOrdPrcCalcBaseRecTMsg = new ORD_PRC_CALC_BASE_RECTMsg();
//                EZDMsg.copy(copyOrdPrcCalcBaseTMsg, null, copyOrdPrcCalcBaseRecTMsg, null);
//                copyOrdPrcCalcBaseRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                S21FastTBLAccessor.insert(copyOrdPrcCalcBaseRecTMsg);
//            }
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyCpoRtrnPrcCalcBase(NWAL1500CMsg bizMsg,  List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList, String userId, String timeStamp) {
//
//        List<CPO_RTRN_PRC_CALC_BASETMsg> copyCpoRtrnPrcCalcBaseTMsgList = new ArrayList<CPO_RTRN_PRC_CALC_BASETMsg>(0);
//
//        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : copyDsCpoRtrnDtlTMsgList) {
//            CPO_RTRN_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
//
//            origOrdPrcCalcBaseTMsg.setSQLID("001");
//            origOrdPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            origOrdPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//            origOrdPrcCalcBaseTMsg.setConditionValue("dsCpoRtrnLineNum01", dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue());
//            origOrdPrcCalcBaseTMsg.setConditionValue("dsCpoRtrnLineSubNum01", dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue());
//
//            CPO_RTRN_PRC_CALC_BASETMsgArray origOrdPrcCalcBaseTMsgArray = (CPO_RTRN_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(origOrdPrcCalcBaseTMsg);
//            for (int i = 0; i < origOrdPrcCalcBaseTMsgArray.getValidCount(); i++) {
//                CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
//                EZDMsg.copy(origOrdPrcCalcBaseTMsgArray.no(i), null, cpoRtrnPrcCalcBaseTMsg, null);
//
//                cpoRtrnPrcCalcBaseTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                cpoRtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_RTRN_PRC_CALC_BASE_SQ));
//
//                copyCpoRtrnPrcCalcBaseTMsgList.add(cpoRtrnPrcCalcBaseTMsg);
//            }
//
//        }
//        if (!copyCpoRtrnPrcCalcBaseTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyCpoRtrnPrcCalcBaseTMsgList.toArray(new CPO_RTRN_PRC_CALC_BASETMsg[0]));
//            if (rsltCnt != copyCpoRtrnPrcCalcBaseTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_RTRN_PRC_CALC_BASE", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//        // REC
//        Map<String, BigDecimal> recKeyMap = new HashMap<String, BigDecimal>(0);
//        for (CPO_RTRN_PRC_CALC_BASETMsg copyCpoRtrnPrcCalcBaseTMsg  : copyCpoRtrnPrcCalcBaseTMsgList) {
//            String hashKey = copyCpoRtrnPrcCalcBaseTMsg.cpoOrdNum.getValue() + ".";
//            hashKey = hashKey + copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum.getValue() + ".";
//            hashKey = hashKey + copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum.getValue();
//
//            BigDecimal bizProcLogPk = recKeyMap.get(hashKey);
//            if (bizProcLogPk == null) {
//                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, copyCpoRtrnPrcCalcBaseTMsg.cpoOrdNum);
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum);
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum);
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.ezUpUserID, userId);
//                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.ezUpTime, timeStamp);
//
//                bizProcLogPk = getBizProcLogPk(dsCpoRtrnDtlTMsg);
//                if (bizProcLogPk == null) {
//                    continue;
//                }
//                recKeyMap.put(hashKey, bizProcLogPk);
//            }
//
//            CPO_RTRN_CALC_BASE_RECTMsg copyCpoRtrnPrcCalcBaseRecTMsg = new CPO_RTRN_CALC_BASE_RECTMsg();
//            EZDMsg.copy(copyCpoRtrnPrcCalcBaseTMsg, null, copyCpoRtrnPrcCalcBaseRecTMsg, null);
//            copyCpoRtrnPrcCalcBaseRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//            S21FastTBLAccessor.insert(copyCpoRtrnPrcCalcBaseRecTMsg);
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoSlsCr(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp, boolean isNeedConfigCopy) {
//
//        DS_CPO_SLS_CRTMsg origDSCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
//
//        // For Header DS_CPO_SLS_CR
//        origDSCpoSlsCrTMsg.setSQLID("001");
//        origDSCpoSlsCrTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDSCpoSlsCrTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_CPO_SLS_CRTMsgArray copyDsCpoSlsCrTMsgArray = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoSlsCrTMsg);
//        List<DS_CPO_SLS_CRTMsg> copyDsCpoSlsCrTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>(0);
//        for (int i = 0; i < copyDsCpoSlsCrTMsgArray.getValidCount(); i++) {
//            DS_CPO_SLS_CRTMsg copyDsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
//            EZDMsg.copy(copyDsCpoSlsCrTMsgArray.no(i), null, copyDsCpoSlsCrTMsg, null);
//
//            copyDsCpoSlsCrTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
//
//            copyDsCpoSlsCrTMsgList.add(copyDsCpoSlsCrTMsg);
//        }
//
//        // For DS_CPO_CONFIG
//        if (isNeedConfigCopy) { // S21_NA#16806 Add
//            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
//                origDSCpoSlsCrTMsg.setSQLID("002");
//                origDSCpoSlsCrTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                origDSCpoSlsCrTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
//                DS_CPO_SLS_CRTMsgArray copyDsCpoSlsCrTMsgConfigArray = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoSlsCrTMsg);
//
//                for (int i = 0; i < copyDsCpoSlsCrTMsgConfigArray.getValidCount(); i++) {
//                    DS_CPO_SLS_CRTMsg copyDsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
//                    EZDMsg.copy(copyDsCpoSlsCrTMsgConfigArray.no(i), null, copyDsCpoSlsCrTMsg, null);
//
//                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoSlsCrTMsg.dsCpoConfigPk.getValue());
//                    if (copyDsCpoConfigTMsg == null) {
//                        continue;
//                    }
//                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//
//                    copyDsCpoSlsCrTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                    copyDsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
//                    copyDsCpoSlsCrTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//
//                    copyDsCpoSlsCrTMsgList.add(copyDsCpoSlsCrTMsg);
//                }
//            }
//        }
//
//        if (!copyDsCpoSlsCrTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoSlsCrTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
//            if (rsltCnt != copyDsCpoSlsCrTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_SLS_CR", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        // Biz Proc Log
//        Map<BigDecimal, String> dsCpoConfigPkMap = new HashMap<BigDecimal, String>(0);
//        for (DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg : copyDsCpoSlsCrTMsgList) {
//            insertProcLog(dsCpoSlsCrTMsg, dsCpoConfigPkMap);
//
//            // Rec
//            ZYPEZDItemValueSetter.setValue(dsCpoSlsCrTMsg.ezUpTime, timeStamp);
//            ZYPEZDItemValueSetter.setValue(dsCpoSlsCrTMsg.ezUpUserID, userId);
//
//            BigDecimal bizProcLogLogPk = getBizProcLogPk(dsCpoSlsCrTMsg, dsCpoConfigPkMap);
//            if (bizProcLogLogPk != null) {
//                DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecMsg = new DS_CPO_SLS_CR_RECTMsg();
//                EZDMsg.copy(dsCpoSlsCrTMsg, null, dsCpoSlsCrRecMsg, null);
//                dsCpoSlsCrRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
//                S21FastTBLAccessor.insert(dsCpoSlsCrRecMsg);
//            }
//
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoDelyInfo(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {
//
//        DS_CPO_DELY_INFOTMsg origDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();
//
//        // For Header DS_CPO_DELY_INFO
//        origDsCpoDelyInfoTMsg.setSQLID("001");
//        origDsCpoDelyInfoTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsCpoDelyInfoTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_CPO_DELY_INFOTMsgArray copyDsCpoDelyInfoTMsgArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoDelyInfoTMsg);
//        List<DS_CPO_DELY_INFOTMsg> copyDsCpoDelyInfoTMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>(0);
//        for (int i = 0; i < copyDsCpoDelyInfoTMsgArray.getValidCount(); i++) {
//            DS_CPO_DELY_INFOTMsg copyDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();
//            EZDMsg.copy(copyDsCpoDelyInfoTMsgArray.no(i), null, copyDsCpoDelyInfoTMsg, null);
//
//            copyDsCpoDelyInfoTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoDelyInfoTMsg.dsCpoDelyInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
//            ZYPEZDItemValueSetter.setValue(copyDsCpoDelyInfoTMsg.rddDt, bizMsg.slsDt); // 2016/10/28 S21_NA#7924-5 Add
//
//            copyDsCpoDelyInfoTMsgList.add(copyDsCpoDelyInfoTMsg);
//        }
//
//        // For DS_CPO_CONFIG
//        if (isNeedConfigCopy) { // S21_NA#16806 Add
//            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
//                origDsCpoDelyInfoTMsg.setSQLID("002");
//                origDsCpoDelyInfoTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                origDsCpoDelyInfoTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
//                DS_CPO_DELY_INFOTMsgArray copyDsCpoDelyInfoTMsgConfigArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoDelyInfoTMsg);
//
//                for (int i = 0; i < copyDsCpoDelyInfoTMsgConfigArray.getValidCount(); i++) {
//                    DS_CPO_DELY_INFOTMsg copyDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();
//                    EZDMsg.copy(copyDsCpoDelyInfoTMsgConfigArray.no(i), null, copyDsCpoDelyInfoTMsg, null);
//
//                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoDelyInfoTMsg.dsCpoConfigPk.getValue());
//                    if (copyDsCpoConfigTMsg == null) {
//                        continue;
//                    }
//                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//
//                    copyDsCpoDelyInfoTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                    copyDsCpoDelyInfoTMsg.dsCpoDelyInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
//                    copyDsCpoDelyInfoTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//                    ZYPEZDItemValueSetter.setValue(copyDsCpoDelyInfoTMsg.rddDt, bizMsg.slsDt); // 2016/10/28 S21_NA#7924-5 Add
//
//                    copyDsCpoDelyInfoTMsgList.add(copyDsCpoDelyInfoTMsg);
//                }
//            }
//        }
//
//        if (!copyDsCpoDelyInfoTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoDelyInfoTMsgList.toArray(new DS_CPO_DELY_INFOTMsg[0]));
//            if (rsltCnt != copyDsCpoDelyInfoTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_DELY_INFO", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoIstlInfo(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {
//
//        DS_CPO_ISTL_INFOTMsg origDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
//
//        // For Header DS_CPO_ISTL_INFO
//        origDsCpoIstlInfoTMsg.setSQLID("001");
//        origDsCpoIstlInfoTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsCpoIstlInfoTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_CPO_ISTL_INFOTMsgArray copyDsCpoIstlInfoTMsgArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoIstlInfoTMsg);
//        List<DS_CPO_ISTL_INFOTMsg> copyDsCpoIstlInfoTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>(0);
//        for (int i = 0; i < copyDsCpoIstlInfoTMsgArray.getValidCount(); i++) {
//            DS_CPO_ISTL_INFOTMsg copyDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
//            EZDMsg.copy(copyDsCpoIstlInfoTMsgArray.no(i), null, copyDsCpoIstlInfoTMsg, null);
//
//            copyDsCpoIstlInfoTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoIstlInfoTMsg.dsCpoIstlInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
//            ZYPEZDItemValueSetter.setValue(copyDsCpoIstlInfoTMsg.rqstIstlDt, bizMsg.slsDt); // 2016/10/28 S21_NA#7924-5 Add
//
//            copyDsCpoIstlInfoTMsgList.add(copyDsCpoIstlInfoTMsg);
//        }
//
//        // For DS_CPO_CONFIG
//        if (isNeedConfigCopy) { // S21_NA#16806 Add
//            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
//                origDsCpoIstlInfoTMsg.setSQLID("002");
//                origDsCpoIstlInfoTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                origDsCpoIstlInfoTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
//                DS_CPO_ISTL_INFOTMsgArray copyDsCpoIstlInfoTMsgConfigArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoIstlInfoTMsg);
//
//                for (int i = 0; i < copyDsCpoIstlInfoTMsgConfigArray.getValidCount(); i++) {
//                    DS_CPO_ISTL_INFOTMsg copyDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
//                    EZDMsg.copy(copyDsCpoIstlInfoTMsgConfigArray.no(i), null, copyDsCpoIstlInfoTMsg, null);
//
//                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoIstlInfoTMsg.dsCpoConfigPk.getValue());
//                    if (copyDsCpoConfigTMsg == null) {
//                        continue;
//                    }
//                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//
//                    copyDsCpoIstlInfoTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                    copyDsCpoIstlInfoTMsg.dsCpoIstlInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
//                    copyDsCpoIstlInfoTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//                    ZYPEZDItemValueSetter.setValue(copyDsCpoIstlInfoTMsg.rqstIstlDt, bizMsg.slsDt); // 2016/10/28 S21_NA#7924-5 Add
//
//                    copyDsCpoIstlInfoTMsgList.add(copyDsCpoIstlInfoTMsg);
//                }
//            }
//        }
//
//        if (!copyDsCpoIstlInfoTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoIstlInfoTMsgList.toArray(new DS_CPO_ISTL_INFOTMsg[0]));
//            if (rsltCnt != copyDsCpoIstlInfoTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_ISTL_INFO", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsSiteSrvy(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {
//
//        DS_SITE_SRVYTMsg origDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();
//
//        // For Header DS_SITE_SRVY
//        origDsSiteSrvyTMsg.setSQLID("005");
//        origDsSiteSrvyTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsSiteSrvyTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_SITE_SRVYTMsgArray copyDsSiteSrvyTMsgArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(origDsSiteSrvyTMsg);
//        List<DS_SITE_SRVYTMsg> copyDsSiteSrvyTMsgList = new ArrayList<DS_SITE_SRVYTMsg>(0);
//        for (int i = 0; i < copyDsSiteSrvyTMsgArray.getValidCount(); i++) {
//            DS_SITE_SRVYTMsg copyDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();
//            EZDMsg.copy(copyDsSiteSrvyTMsgArray.no(i), null, copyDsSiteSrvyTMsg, null);
//
//            copyDsSiteSrvyTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsSiteSrvyTMsg.dsSiteSrvyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
//
//            copyDsSiteSrvyTMsgList.add(copyDsSiteSrvyTMsg);
//        }
//
//        // For DS_CPO_CONFIG
//        if (isNeedConfigCopy) { // S21_NA#16806 Add
//            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
//                origDsSiteSrvyTMsg.setSQLID("006");
//                origDsSiteSrvyTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                origDsSiteSrvyTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
//                DS_SITE_SRVYTMsgArray copyDsSiteSrvyTMsgConfigArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(origDsSiteSrvyTMsg);
//
//                for (int i = 0; i < copyDsSiteSrvyTMsgConfigArray.getValidCount(); i++) {
//                    DS_SITE_SRVYTMsg copyDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();
//                    EZDMsg.copy(copyDsSiteSrvyTMsgConfigArray.no(i), null, copyDsSiteSrvyTMsg, null);
//
//                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsSiteSrvyTMsg.dsCpoConfigPk.getValue());
//                    if (copyDsCpoConfigTMsg == null) {
//                        continue;
//                    }
//                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//
//                    copyDsSiteSrvyTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                    copyDsSiteSrvyTMsg.dsSiteSrvyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
//                    copyDsSiteSrvyTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//
//                    copyDsSiteSrvyTMsgList.add(copyDsSiteSrvyTMsg);
//                }
//            }
//        }
//
//        if (!copyDsSiteSrvyTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsSiteSrvyTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
//            if (rsltCnt != copyDsSiteSrvyTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_SITE_SRVY", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyDsCpoCtacPsn(NWAL1500CMsg bizMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {
//
//        DS_CPO_CTAC_PSNTMsg origDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
//
//        origDsCpoCtacPsnTMsg.setSQLID("005");
//        origDsCpoCtacPsnTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsCpoCtacPsnTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        DS_CPO_CTAC_PSNTMsgArray copyDsCpoCtacPsnTMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoCtacPsnTMsg);
//        List<DS_CPO_CTAC_PSNTMsg> copyDsCpoCtacPsnTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>(0);
//        for (int i = 0; i < copyDsCpoCtacPsnTMsgArray.getValidCount(); i++) {
//            DS_CPO_CTAC_PSNTMsg copyDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
//            EZDMsg.copy(copyDsCpoCtacPsnTMsgArray.no(i), null, copyDsCpoCtacPsnTMsg, null);
//
//            copyDsCpoCtacPsnTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoCtacPsnTMsg.dsCpoCtacPsnPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
//            if (ZYPCommonFunc.hasValue(copyDsCpoCtacPsnTMsg.dsCpoConfigPk)) {
//                DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoCtacPsnTMsg.dsCpoConfigPk.getValue());
//                if (copyDsCpoConfigTMsg == null) {
//                    continue;
//                }
//                BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
//
//                copyDsCpoCtacPsnTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
//            }
//
//            copyDsCpoCtacPsnTMsgList.add(copyDsCpoCtacPsnTMsg);
//        }
//
//        if (!copyDsCpoCtacPsnTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoCtacPsnTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
//            if (rsltCnt != copyDsCpoCtacPsnTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_CTAC_PSN", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean registCopyShellDataCpoSvc(NWAL1500CMsg bizMsg, String userId, String timeStamp) {
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getCpoSvcInfoByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
//        if (ssmRslt.isCodeNotFound()) {
//            return false;
//        }
//        if (!ssmRslt.isCodeNormal()) {
//            return false;
//        }
//        List<CPO_SVCTMsg> origCpoSvcTMsgList = (List<CPO_SVCTMsg>) ssmRslt.getResultObject();
//        List<CPO_SVCTMsg> copyCpoSvcTMsgList = new ArrayList<CPO_SVCTMsg>(0);
//        Map<BigDecimal, BigDecimal> cpoSvcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        for (CPO_SVCTMsg origCpoSvcTMsg : origCpoSvcTMsgList) {
//            CPO_SVCTMsg copyCpoSvcTMsg = new CPO_SVCTMsg();
//            EZDMsg.copy(origCpoSvcTMsg, null, copyCpoSvcTMsg, null);
//            copyCpoSvcTMsg.refCpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            BigDecimal newCpoSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_SQ);
//            cpoSvcPkMap.put(copyCpoSvcTMsg.cpoSvcPk.getValue(), newCpoSvcPk);
//            copyCpoSvcTMsg.cpoSvcPk.setValue(newCpoSvcPk);
//
//            copyCpoSvcTMsgList.add(copyCpoSvcTMsg);
//        }
//        // CPO_SVC_DTL
//        Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList = getCopyCpoSvcDtl(bizMsg, cpoSvcPkMap, cpoSvcDtlPkMap);
//        if (copyCpoSvcDtlTMsgList == null || (copyCpoSvcDtlTMsgList != null && copyCpoSvcDtlTMsgList.isEmpty())) {
//            return false;
//        }
//
//        // If header without detail, erase such header from the list
//        for (int i = copyCpoSvcTMsgList.size() - 1; i >= 0; i--) {
//            if (isNoDetail(copyCpoSvcTMsgList.get(i), copyCpoSvcDtlTMsgList)) {
//                BigDecimal origCpoSvcPk = getKeyFromCpoSvcPkMap(cpoSvcPkMap, copyCpoSvcTMsgList.get(i).cpoSvcPk.getValue());
//                if (origCpoSvcPk != null) {
//                    cpoSvcPkMap.remove(origCpoSvcPk);
//                }
//                copyCpoSvcTMsgList.remove(i);
//            }
//        }
//        if (copyCpoSvcTMsgList.isEmpty()) {
//            return false;
//        }
//
//        // CPO_SVC_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_PRCTMsg> copyCpoSvcPrcTMsgList = getCopyCpoSvcPrcList(bizMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap);
//
//        // CPO_SVC_CONFIG_REF
//        Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList = getCopyCpoSvcConfigRefList(bizMsg, cpoSvcPkMap, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcConfigRefPkMap);
//
//        // 2017/03/06 S21_NA#17790 Add Start
//        // replace cpoSvcConfigRefPk
//        for (CPO_SVC_PRCTMsg copyCpoSvcPrcTMsg : copyCpoSvcPrcTMsgList) {
//            if (ZYPCommonFunc.hasValue(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk)) {
//                BigDecimal newCpoSvcConfigRefPk = cpoSvcConfigRefPkMap.get(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk.getValue());
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk, newCpoSvcConfigRefPk);
//            }
//        }
//        // 2017/03/06 S21_NA#17790 Add End
//        
//        // If detail without config_ref, erase such detail from the list
//        for (int i = copyCpoSvcDtlTMsgList.size() - 1; i >= 0; i--) {
//            if (isNoConfigRef(copyCpoSvcDtlTMsgList.get(i), copyCpoSvcConfigRefTMsgList)) {
//                BigDecimal origCpoSvcDtlPk = getKeyFromCpoSvcDtlPkMap(cpoSvcDtlPkMap, copyCpoSvcDtlTMsgList.get(i).cpoSvcDtlPk.getValue());
//                if (origCpoSvcDtlPk != null) {
//                    cpoSvcDtlPkMap.remove(origCpoSvcDtlPk);
//                }
//                copyCpoSvcDtlTMsgList.remove(i);
//            }
//        }
//        if (copyCpoSvcDtlTMsgList.isEmpty()) {
//            return false;
//        }
//
//        // Insert CPO_SVC_DTL
//        if (!copyCpoSvcDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcDtlTMsgList.toArray(new CPO_SVC_DTLTMsg[0]));
//            if (rsltCnt != copyCpoSvcDtlTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_DTL", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Proc Log
//            for (CPO_SVC_DTLTMsg cpoSvcDtlTMsg : copyCpoSvcDtlTMsgList) {
//                insertProcLog(bizMsg.xxSrchNum.getValue(), cpoSvcDtlTMsg);
//
//                // Rec
//                cpoSvcDtlTMsg.ezUpUserID.setValue(userId);
//                cpoSvcDtlTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(bizMsg.xxSrchNum.getValue(), cpoSvcDtlTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_DTL_RECTMsg cpoSvcDtlRecTMsg = new CPO_SVC_DTL_RECTMsg();
//                    EZDMsg.copy(cpoSvcDtlTMsg, null, cpoSvcDtlRecTMsg, null);
//                    cpoSvcDtlRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcDtlRecTMsg);
//                }
//            }
//        }
//
//        // Insert CPO_SVC_PRC
//        if (!copyCpoSvcPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcPrcTMsgList.toArray(new CPO_SVC_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcPrcTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_PRC", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Proc Log
//            for (CPO_SVC_PRCTMsg cpoSvcPrcTMsg : copyCpoSvcPrcTMsgList) {
//                insertProcLog(bizMsg.xxSrchNum.getValue(), cpoSvcPrcTMsg);
//
//                // Rec
//                cpoSvcPrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcPrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(bizMsg.xxSrchNum.getValue(), cpoSvcPrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_PRC_RECTMsg cpoSvcPrcRecTMsg = new CPO_SVC_PRC_RECTMsg();
//                    EZDMsg.copy(cpoSvcPrcTMsg, null, cpoSvcPrcRecTMsg, null);
//                    cpoSvcPrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcPrcRecTMsg);
//                }
//            }
//        }
//
//        // Insert CPO_SVC_CONFIG_REF
//        if (!copyCpoSvcConfigRefTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcConfigRefTMsgList.toArray(new CPO_SVC_CONFIG_REFTMsg[0]));
//            if (rsltCnt != copyCpoSvcConfigRefTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_CONFIG_REF", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Proc Log
//            for (CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg : copyCpoSvcConfigRefTMsgList) {
//                insertProcLog(cpoSvcConfigRefTMsg);
//
//                // Rec
//                cpoSvcConfigRefTMsg.ezUpUserID.setValue(userId);
//                cpoSvcConfigRefTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcConfigRefTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_CONFIG_REF_RECTMsg cpoSvcConfigRefRecTMsg = new CPO_SVC_CONFIG_REF_RECTMsg();
//                    EZDMsg.copy(cpoSvcConfigRefTMsg, null, cpoSvcConfigRefRecTMsg, null);
//                    cpoSvcConfigRefRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcConfigRefRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_USG_PRC
//        List<CPO_SVC_USG_PRCTMsg> copyCpoSvcUsgPrcTMsgList = getCopyCpoSvcUsgPrcList(bizMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap);
//        if (!copyCpoSvcUsgPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcUsgPrcTMsgList.toArray(new CPO_SVC_USG_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcUsgPrcTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_USG_PRC", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        // CPO_SVC_ADDL_BASE_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_ADDL_BASE_PRCTMsg> copyCpoSvcAddlBasePrcTMsgList = getCopyCpoSvcAddlBasePrcList(bizMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcAddlBasePrcPkMap);
//        if (!copyCpoSvcAddlBasePrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcAddlBasePrcTMsgList.toArray(new CPO_SVC_ADDL_BASE_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcAddlBasePrcTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_ADDL_BASE_PRC", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Proc Log
//            for (CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg : copyCpoSvcAddlBasePrcTMsgList) {
//                insertProcLog(cpoSvcAddlBasePrcTMsg);
//
//                // Rec
//                cpoSvcAddlBasePrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcAddlBasePrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcAddlBasePrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBasePrcRecTMsg = new CPO_SVC_ADDL_BASE_RECTMsg();
//                    EZDMsg.copy(cpoSvcAddlBasePrcTMsg, null, cpoSvcAddlBasePrcRecTMsg, null);
//                    cpoSvcAddlBasePrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcAddlBasePrcRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_ADDL_CHRG_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_ADDL_CHRG_PRCTMsg> copyCpoSvcAddlChrgPrcTMsgList = getCopyCpoSvcAddlChrgPrcList(bizMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcAddlChrgPrcPkMap);
//        if (!copyCpoSvcAddlChrgPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcAddlChrgPrcTMsgList.toArray(new CPO_SVC_ADDL_CHRG_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcAddlChrgPrcTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_ADDL_CHRG_PRC", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//            // Biz Proc Log
//            for (CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg : copyCpoSvcAddlChrgPrcTMsgList) {
//                insertProcLog(cpoSvcAddlChrgPrcTMsg);
//
//                // Rec
//                cpoSvcAddlChrgPrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcAddlChrgPrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcAddlChrgPrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgPrcRecTMsg = new CPO_SVC_ADDL_CHRG_RECTMsg();
//                    EZDMsg.copy(cpoSvcAddlChrgPrcTMsg, null, cpoSvcAddlChrgPrcRecTMsg, null);
//                    cpoSvcAddlChrgPrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcAddlChrgPrcRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_PRC_TAX_DTL
//        List<CPO_SVC_PRC_TAX_DTLTMsg> copyCpoSvcPrcTaxDtlTMsgList = getCopyCpoSvcPrcTaxDtlList(bizMsg, cpoSvcPkMap, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcConfigRefPkMap, cpoSvcAddlBasePrcPkMap, cpoSvcAddlChrgPrcPkMap);
//        if (!copyCpoSvcPrcTaxDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcPrcTaxDtlTMsgList.toArray(new CPO_SVC_PRC_TAX_DTLTMsg[0]));
//            if (rsltCnt != copyCpoSvcPrcTaxDtlTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC_PRC_TAX_DTLTMsg", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        // 2017/03/06 S21_NA#17790 Add Start
//        // SCHD_CRAT_TMPL
//        Map<BigDecimal, BigDecimal> schdCratTmplPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<SCHD_CRAT_TMPLTMsg> copySchdCratTmplTMsgList = getCopySchdCratTmplList(bizMsg, cpoSvcDtlPkMap, schdCratTmplPkMap);
//        if (!copySchdCratTmplTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copySchdCratTmplTMsgList.toArray(new SCHD_CRAT_TMPLTMsg[0]));
//            if (rsltCnt != copySchdCratTmplTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"SCHD_CRAT_TMPLTMsg", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        // SCHD_CRAT_TMPL_LINE
//        List<SCHD_CRAT_TMPL_LINETMsg> copySchdCratTmplLineTMsgList = getCopySchdCratTmplLineList(bizMsg, schdCratTmplPkMap, cpoSvcConfigRefPkMap);
//        if (!copySchdCratTmplLineTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copySchdCratTmplLineTMsgList.toArray(new SCHD_CRAT_TMPL_LINETMsg[0]));
//            if (rsltCnt != copySchdCratTmplLineTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"SCHD_CRAT_TMPL_LINETMsg", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//        // 2017/03/06 S21_NA#17790 Add End
//
//        // Insert Header
//        if (!copyCpoSvcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcTMsgList.toArray(new CPO_SVCTMsg[0]));
//            if (rsltCnt != copyCpoSvcTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_SVC", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_DTLTMsg> getCopyCpoSvcDtl(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap) {
//
//        List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList = new ArrayList<CPO_SVC_DTLTMsg>(0);
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//
//            S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getCpoSvcDtlForShell(bizMsg.glblCmpyCd.getValue(), origCpoSvcPk);
//            if (ssmRslt.isCodeNormal()) {
//                List<Map<String, Object>> rsltObjList = (List<Map<String, Object>>) ssmRslt.getResultObject();
//                for (Map<String, Object> rsltObj : rsltObjList) {
//                    CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg = new CPO_SVC_DTLTMsg();
//
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.glblCmpyCd,            bizMsg.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcDtlPk,           (BigDecimal) rsltObj.get("CPO_SVC_DTL_PK"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcPk,              cpoSvcPkMap.get(origCpoSvcPk));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcLineNum,         (BigDecimal) rsltObj.get("CPO_SVC_LINE_NUM"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcMdseCd,          (String) rsltObj.get("CPO_SVC_MDSE_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.prcSvcContrTpCd,       (String) rsltObj.get("PRC_SVC_CONTR_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.prcSvcPlnTpCd,         (String) rsltObj.get("PRC_SVC_PLN_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.dsContrCatgCd,         (String) rsltObj.get("DS_CONTR_CATG_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.baseBllgCycleCd,       (String) rsltObj.get("BASE_BLLG_CYCLE_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.usgBllgCycleCd,        (String) rsltObj.get("USG_BLLG_CYCLE_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.fromPerMthNum,         (BigDecimal) rsltObj.get("FROM_PER_MTH_NUM"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.toPerMthNum,           (BigDecimal) rsltObj.get("TO_PER_MTH_NUM"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.termMthNum,            (BigDecimal) rsltObj.get("TERM_MTH_NUM"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.billWithEquipFlg,      (String) rsltObj.get("BILL_WITH_EQUIP_FLG"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.billByTpCd,            (String) rsltObj.get("BILL_BY_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.soldToCustLocCd,       (String) rsltObj.get("SOLD_TO_CUST_LOC_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.sellToCustCd,          (String) rsltObj.get("SELL_TO_CUST_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcAgmtVerNum,      (String) rsltObj.get("CPO_SVC_AGMT_VER_NUM"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdFlg,       (String) rsltObj.get("MAN_CONTR_OVRD_FLG"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdRsnCd,     (String) rsltObj.get("MAN_CONTR_OVRD_RSN_CD"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdCmntTxt,   (String) rsltObj.get("MAN_CONTR_OVRD_CMNT_TXT"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.dsContrPk,             (BigDecimal) rsltObj.get("DS_CONTR_PK"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.svcPrcCatgCd,          (String) rsltObj.get("SVC_PRC_CATG_CD"));
////                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.billWithEquipInvdFlg,  (String) rsltObj.get("BILL_WITH_EQUIP_INVD_FLG"));  // 2016/11/11 S21_NA#15939 Del
//                    copyCpoSvcDtlTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N);  // 2016/11/11 S21_NA#15939 Add
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.crRebilCd,             (String) rsltObj.get("CR_REBIL_CD"));
////                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.contrCratFlg,          (String) rsltObj.get("CONTR_CRAT_FLG")); 2016/10/25 S21_NA#15000-2 Del
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.contrCratFlg,          ZYPConstant.FLG_OFF_N); // 2016/10/25 S21_NA#15000-2 Add
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.applyEquipBillToFlg,   (String) rsltObj.get("APPLY_EQUIP_BILL_TO_FLG"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.totBasePrcDealAmt,     (BigDecimal) rsltObj.get("TOT_BASE_PRC_DEAL_AMT"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.totBasePrcFuncAmt,     (BigDecimal) rsltObj.get("TOT_BASE_PRC_FUNC_AMT"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.addAsryFlg,            (String) rsltObj.get("ADD_ASRY_FLG"));
//                    // 2017/03/06 S21_NA#17790 Add Start
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.fixTermInMthAot,       (BigDecimal) rsltObj.get("FIX_TERM_IN_MTH_AOT"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.maxUplftPct,           (BigDecimal) rsltObj.get("MAX_UPLFT_PCT"));
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcLineActCd,       (String) rsltObj.get("CPO_SVC_LINE_ACT_CD"));
//                    // 2017/03/06 S21_NA#17790 Add End
//
//                    BigDecimal origCpoSvcDtlPk = copyCpoSvcDtlTMsg.cpoSvcDtlPk.getValue();
//                    BigDecimal newCpoSvcDltPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_DTL_SQ);
//                    cpoSvcDtlPkMap.put(origCpoSvcDtlPk, newCpoSvcDltPk);
//                    copyCpoSvcDtlTMsg.cpoSvcDtlPk.setValue(newCpoSvcDltPk);
//
//                    copyCpoSvcDtlTMsgList.add(copyCpoSvcDtlTMsg);
//                }
//            }
//        }
//        return copyCpoSvcDtlTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_PRCTMsg> getCopyCpoSvcPrcList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap) {
//
//        List<CPO_SVC_PRCTMsg> copyCpoSvcPrcTMsgList = new ArrayList<CPO_SVC_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_PRCTMsg origCpoSvcPrcTMsg = new CPO_SVC_PRCTMsg();
//            origCpoSvcPrcTMsg.setSQLID("001");
//            origCpoSvcPrcTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            origCpoSvcPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_PRCTMsgArray copyCpoSvcPrcTMsgArray = (CPO_SVC_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcPrcTMsg);
//            for (int i = 0; i < copyCpoSvcPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_PRCTMsg copyCpoSvcPrcTMsg = new CPO_SVC_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcPrcTMsgArray.no(i), null, copyCpoSvcPrcTMsg, null);
//
//                BigDecimal newCpoSvcPrcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_PRC_SQ);
//                BigDecimal origCpoSvcPrcPk = copyCpoSvcPrcTMsg.cpoSvcPrcPk.getValue();
//
//                cpoSvcPrcPkMap.put(origCpoSvcPrcPk, newCpoSvcPrcPk);
//
//                copyCpoSvcPrcTMsg.cpoSvcPrcPk.setValue(newCpoSvcPrcPk);
//                copyCpoSvcPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcPrcTMsgList.add(copyCpoSvcPrcTMsg);
//            }
//        }
//        return copyCpoSvcPrcTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_CONFIG_REFTMsg> getCopyCpoSvcConfigRefList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap) {
//
//        List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList = new ArrayList<CPO_SVC_CONFIG_REFTMsg>(0);
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//            S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getCpoSvcConfigRefForShell(bizMsg.glblCmpyCd.getValue(), origCpoSvcPk);
//            if (!ssmRslt.isCodeNormal()) {
//                continue;
//            }
//            List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmRslt.getResultObject();
//            for (Map<String, Object> rsltMap : rsltMapList) {
//                BigDecimal origCpoSvcDtlPk = (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK");
//                String cpoOrdNum = (String) rsltMap.get("CPO_ORD_NUM");
//                String cpoDtlLineNum = (String) rsltMap.get("CPO_DTL_LINE_NUM");
//                String cpoDtlLineSubNum = (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM");
//
//                CPO_SVC_CONFIG_REFTMsg origCpoConfigRefTMsg = new CPO_SVC_CONFIG_REFTMsg();
//                origCpoConfigRefTMsg.setSQLID("001");
//                origCpoConfigRefTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                origCpoConfigRefTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//                CPO_SVC_CONFIG_REFTMsgArray copyCpoConfigRefTMsgArray = (CPO_SVC_CONFIG_REFTMsgArray) EZDTBLAccessor.findByCondition(origCpoConfigRefTMsg);
//                for (int i = 0; i < copyCpoConfigRefTMsgArray.getValidCount(); i++) {
//                    CPO_SVC_CONFIG_REFTMsg copyCpoSvcConfigRefTMsg = new CPO_SVC_CONFIG_REFTMsg();
//                    EZDMsg.copy(copyCpoConfigRefTMsgArray.no(i), null, copyCpoSvcConfigRefTMsg, null);
//
//                    copyCpoSvcConfigRefTMsg.cpoSvcConfigRefPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_CONFIG_REF_SQ));
//                    copyCpoSvcConfigRefTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                    if (ZYPCommonFunc.hasValue(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk)) {
//                        BigDecimal newCpoSvcPrcPk = cpoSvcPrcPkMap.get(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk.getValue());
//                        ZYPEZDItemValueSetter.setValue(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk, newCpoSvcPrcPk);
//                    }
//                    copyCpoSvcConfigRefTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                    copyCpoSvcConfigRefTMsg.contrCratFlg.setValue(ZYPConstant.FLG_OFF_N); // 2016/10/25 S21_NA#15000-2 Add
//                    copyCpoSvcConfigRefTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N); // 2016/11/11 S21_NA#15939 Add
//
//                    // SVC_CONFIG_MSTR_PK
//                    CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoOrdNum, copyCpoSvcConfigRefTMsg.cpoOrdNum);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineNum, copyCpoSvcConfigRefTMsg.cpoDtlLineNum);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineSubNum, copyCpoSvcConfigRefTMsg.cpoDtlLineSubNum);
//                    dsCpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlTMsg);
//                    if (dsCpoDtlTMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(copyCpoSvcConfigRefTMsg.svcConfigMstrPk, dsCpoDtlTMsg.svcConfigMstrPk);
//                    }
//
//                    copyCpoSvcConfigRefTMsgList.add(copyCpoSvcConfigRefTMsg);
//                    cpoSvcConfigRefPkMap.put(copyCpoConfigRefTMsgArray.no(i).cpoSvcConfigRefPk.getValue(), copyCpoSvcConfigRefTMsg.cpoSvcConfigRefPk.getValue());
//                }
//            }
//        }
//        return copyCpoSvcConfigRefTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_USG_PRCTMsg> getCopyCpoSvcUsgPrcList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap) {
//
//        List<CPO_SVC_USG_PRCTMsg> copyCpoSvcUsgPrcTMsgList = new ArrayList<CPO_SVC_USG_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_USG_PRCTMsg origCpoSvcUsgPrcTMsg = new CPO_SVC_USG_PRCTMsg();
//            origCpoSvcUsgPrcTMsg.setSQLID("001");
//            origCpoSvcUsgPrcTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            origCpoSvcUsgPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_USG_PRCTMsgArray copyCpoSvcUsgPrcTMsgArray = (CPO_SVC_USG_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcUsgPrcTMsg);
//            for (int i = 0; i < copyCpoSvcUsgPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_USG_PRCTMsg copyCpoSvcUsgPrcTMsg = new CPO_SVC_USG_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcUsgPrcTMsgArray.no(i), null, copyCpoSvcUsgPrcTMsg, null);
//
//                copyCpoSvcUsgPrcTMsg.cpoSvcUsgPrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_USG_PRC_SQ));
//                copyCpoSvcUsgPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                if (ZYPCommonFunc.hasValue(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk)) {
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk, cpoSvcPrcPkMap.get(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk.getValue()));
//                }
//                copyCpoSvcUsgPrcTMsgList.add(copyCpoSvcUsgPrcTMsg);
//            }
//        }
//        return copyCpoSvcUsgPrcTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_ADDL_BASE_PRCTMsg> getCopyCpoSvcAddlBasePrcList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap) {
//
//        List<CPO_SVC_ADDL_BASE_PRCTMsg> copyCpoSvcAddlBasePrcTMsgList = new ArrayList<CPO_SVC_ADDL_BASE_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_ADDL_BASE_PRCTMsg origCpoSvcAddlBasePrcTMsg = new CPO_SVC_ADDL_BASE_PRCTMsg();
//            origCpoSvcAddlBasePrcTMsg.setSQLID("001");
//            origCpoSvcAddlBasePrcTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            origCpoSvcAddlBasePrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_ADDL_BASE_PRCTMsgArray copyCpoSvcAddlBasePrcTMsgArray = (CPO_SVC_ADDL_BASE_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcAddlBasePrcTMsg);
//            for (int i = 0; i < copyCpoSvcAddlBasePrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_ADDL_BASE_PRCTMsg copyCpoSvcAddlBasePrcTMsg = new CPO_SVC_ADDL_BASE_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcAddlBasePrcTMsgArray.no(i), null, copyCpoSvcAddlBasePrcTMsg, null);
//
//                copyCpoSvcAddlBasePrcTMsg.cpoSvcAddlBasePrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_ADDL_BASE_PRC_SQ));
//                copyCpoSvcAddlBasePrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcAddlBasePrcTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                copyCpoSvcAddlBasePrcTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N); // 2016/11/11 S21_NA#15939 Add
//
//                copyCpoSvcAddlBasePrcTMsgList.add(copyCpoSvcAddlBasePrcTMsg);
//                cpoSvcAddlBasePrcPkMap.put(copyCpoSvcAddlBasePrcTMsgArray.no(i).cpoSvcAddlBasePrcPk.getValue(), copyCpoSvcAddlBasePrcTMsg.cpoSvcAddlBasePrcPk.getValue());
//            }
//        }
//        return copyCpoSvcAddlBasePrcTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_ADDL_CHRG_PRCTMsg> getCopyCpoSvcAddlChrgPrcList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap) {
//
//        List<CPO_SVC_ADDL_CHRG_PRCTMsg> copyCpoSvcAddlChrgPrcTMsgList = new ArrayList<CPO_SVC_ADDL_CHRG_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_ADDL_CHRG_PRCTMsg origCpoSvcAddlChrgPrcTMsg = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//            origCpoSvcAddlChrgPrcTMsg.setSQLID("001");
//            origCpoSvcAddlChrgPrcTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            origCpoSvcAddlChrgPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_ADDL_CHRG_PRCTMsgArray copyCpoSvcAddlChrgPrcTMsgArray = (CPO_SVC_ADDL_CHRG_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcAddlChrgPrcTMsg);
//            for (int i = 0; i < copyCpoSvcAddlChrgPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_ADDL_CHRG_PRCTMsg copyCpoSvcAddlChrgPrcTMsg = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcAddlChrgPrcTMsgArray.no(i), null, copyCpoSvcAddlChrgPrcTMsg, null);
//
//                copyCpoSvcAddlChrgPrcTMsg.cpoSvcAddlChrgPrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_ADDL_CHRG_PRC_SQ));
//                copyCpoSvcAddlChrgPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcAddlChrgPrcTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//                copyCpoSvcAddlChrgPrcTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N); // 2016/11/11 S21_NA#15939 Add
//
//                copyCpoSvcAddlChrgPrcTMsgList.add(copyCpoSvcAddlChrgPrcTMsg);
//                cpoSvcAddlChrgPrcPkMap.put(copyCpoSvcAddlChrgPrcTMsgArray.no(i).cpoSvcAddlChrgPrcPk.getValue(), copyCpoSvcAddlChrgPrcTMsg.cpoSvcAddlChrgPrcPk.getValue());
//            }
//        }
//        return copyCpoSvcAddlChrgPrcTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<CPO_SVC_PRC_TAX_DTLTMsg> getCopyCpoSvcPrcTaxDtlList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap) {
//
//        List<CPO_SVC_PRC_TAX_DTLTMsg> copyCpoSvcPrcTaxDtlTMsgList = new ArrayList<CPO_SVC_PRC_TAX_DTLTMsg>(0);
//
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//
//            CPO_SVC_PRC_TAX_DTLTMsg keyCpoSvcPrcTaxDtlTMsg = new CPO_SVC_PRC_TAX_DTLTMsg();
//            keyCpoSvcPrcTaxDtlTMsg.setSQLID("003");
//            keyCpoSvcPrcTaxDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            keyCpoSvcPrcTaxDtlTMsg.setConditionValue("cpoSvcPk01", origCpoSvcPk);
//
//            CPO_SVC_PRC_TAX_DTLTMsgArray copyCpoSvcPrcTaxDtlTMsgArray = (CPO_SVC_PRC_TAX_DTLTMsgArray) EZDTBLAccessor.findByCondition(keyCpoSvcPrcTaxDtlTMsg);
//            for (int i = 0; i < copyCpoSvcPrcTaxDtlTMsgArray.getValidCount(); i++) {
//                CPO_SVC_PRC_TAX_DTLTMsg copyCpoSvcPrcTaxDtlTMsg = new CPO_SVC_PRC_TAX_DTLTMsg();
//                EZDMsg.copy(copyCpoSvcPrcTaxDtlTMsgArray.no(i), null, copyCpoSvcPrcTaxDtlTMsg, null);
//                copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcTaxDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_PRC_TAX_DTL_SQ));
//
//                BigDecimal cpoSvcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcPk.getValue();
//                BigDecimal cpoSvcDtlPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.getValue();
//                BigDecimal cpoSvcPrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.getValue();
//                BigDecimal cpoSvcConfigRefPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.getValue();
//                BigDecimal cpoSvcAddlBasePrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.getValue();
//                BigDecimal cpoSvcAddlChrgPrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.getValue();
//
//                copyCpoSvcPrcTaxDtlTMsg.cpoSvcPk.setValue(cpoSvcPkMap.get(cpoSvcPk));
//                if (ZYPCommonFunc.hasValue(cpoSvcDtlPk) && ZYPCommonFunc.hasValue(cpoSvcDtlPkMap.get(cpoSvcDtlPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(cpoSvcDtlPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcPrcPk) && ZYPCommonFunc.hasValue(cpoSvcPrcPkMap.get(cpoSvcPrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.setValue(cpoSvcPrcPkMap.get(cpoSvcPrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) && ZYPCommonFunc.hasValue(cpoSvcConfigRefPkMap.get(cpoSvcConfigRefPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.setValue(cpoSvcConfigRefPkMap.get(cpoSvcConfigRefPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcAddlBasePrcPk) && ZYPCommonFunc.hasValue(cpoSvcAddlBasePrcPkMap.get(cpoSvcAddlBasePrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.setValue(cpoSvcAddlBasePrcPkMap.get(cpoSvcAddlBasePrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcAddlChrgPrcPk) && ZYPCommonFunc.hasValue(cpoSvcAddlChrgPrcPkMap.get(cpoSvcAddlChrgPrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.setValue(cpoSvcAddlChrgPrcPkMap.get(cpoSvcAddlChrgPrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.clear();
//                }
//
//                copyCpoSvcPrcTaxDtlTMsgList.add(copyCpoSvcPrcTaxDtlTMsg);
//            }
//        }
//        return copyCpoSvcPrcTaxDtlTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    // 2017/03/06 S21_NA#17790 Add Start
//    private static List<SCHD_CRAT_TMPLTMsg> getCopySchdCratTmplList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> schdCratTmplPkMap) {
//        List<SCHD_CRAT_TMPLTMsg> copySchdCratTmplTMsgList = new ArrayList<SCHD_CRAT_TMPLTMsg>(0);
//
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            SCHD_CRAT_TMPLTMsg keySchdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
//            keySchdCratTmplTMsg.setSQLID("001");
//            keySchdCratTmplTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            keySchdCratTmplTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            SCHD_CRAT_TMPLTMsgArray copySchdCratTmplTMsgArray = (SCHD_CRAT_TMPLTMsgArray) EZDTBLAccessor.findByCondition(keySchdCratTmplTMsg);
//            for (int i = 0; i < copySchdCratTmplTMsgArray.getValidCount(); i++) {
//                SCHD_CRAT_TMPLTMsg copySchdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
//                EZDMsg.copy(copySchdCratTmplTMsgArray.no(i), null, copySchdCratTmplTMsg, null);
//
//                copySchdCratTmplTMsg.schdCratTmplPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_CRAT_TMPL_SQ));
//                copySchdCratTmplTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copySchdCratTmplTMsg.refCpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//
//                copySchdCratTmplTMsgList.add(copySchdCratTmplTMsg);
//                schdCratTmplPkMap.put(copySchdCratTmplTMsgArray.no(i).schdCratTmplPk.getValue(), copySchdCratTmplTMsg.schdCratTmplPk.getValue());
//            }
//        }
//        return copySchdCratTmplTMsgList;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static List<SCHD_CRAT_TMPL_LINETMsg> getCopySchdCratTmplLineList(NWAL1500CMsg bizMsg, Map<BigDecimal, BigDecimal> schdCratTmplPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMa) {
//        List<SCHD_CRAT_TMPL_LINETMsg> copySchdCratTmplLineTMsgList = new ArrayList<SCHD_CRAT_TMPL_LINETMsg>(0);
//
//        for (BigDecimal origSchdCratTmplPk : schdCratTmplPkMap.keySet()) {
//            SCHD_CRAT_TMPL_LINETMsg keySchdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
//            keySchdCratTmplLineTMsg.setSQLID("003");
//            keySchdCratTmplLineTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            keySchdCratTmplLineTMsg.setConditionValue("schdCratTmplPk01", origSchdCratTmplPk);
//
//            SCHD_CRAT_TMPL_LINETMsgArray copySchdCratTmplLineTMsgArray = (SCHD_CRAT_TMPL_LINETMsgArray) EZDTBLAccessor.findByCondition(keySchdCratTmplLineTMsg);
//            for (int i = 0; i < copySchdCratTmplLineTMsgArray.getValidCount(); i++) {
//                SCHD_CRAT_TMPL_LINETMsg copySchdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
//                EZDMsg.copy(copySchdCratTmplLineTMsgArray.no(i), null, copySchdCratTmplLineTMsg, null);
//
//                copySchdCratTmplLineTMsg.schdCratTmplPk.setValue(schdCratTmplPkMap.get(origSchdCratTmplPk));
//                copySchdCratTmplLineTMsg.cpoSvcConfigRefPk.setValue(cpoSvcConfigRefPkMa.get(copySchdCratTmplLineTMsgArray.no(i).cpoSvcConfigRefPk.getValue()));
//
//                copySchdCratTmplLineTMsgList.add(copySchdCratTmplLineTMsg);
//            }
//        }
//        return copySchdCratTmplLineTMsgList;
//    }
//    // 2017/03/06 S21_NA#17790 Add End
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean isNoDetail(CPO_SVCTMsg copyCpoSvcTMsg, List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList) {
//
//        boolean isNoDetail = true;
//        BigDecimal newCpoSvcPk = copyCpoSvcTMsg.cpoSvcPk.getValue();
//        for (CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg : copyCpoSvcDtlTMsgList) {
//            if (newCpoSvcPk.compareTo(copyCpoSvcDtlTMsg.cpoSvcPk.getValue()) == 0) {
//                isNoDetail = false;
//                break;
//            }
//        }
//        return isNoDetail;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean isNoConfigRef(CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg, List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList) {
//
//        boolean isNoConfigRef = true;
//        BigDecimal newCpoSvcDtlPk = copyCpoSvcDtlTMsg.cpoSvcDtlPk.getValue();
//        for (CPO_SVC_CONFIG_REFTMsg copyCpoSvcConfigRefTMsg : copyCpoSvcConfigRefTMsgList) {
//            if (newCpoSvcDtlPk.compareTo(copyCpoSvcConfigRefTMsg.cpoSvcDtlPk.getValue()) == 0) {
//                isNoConfigRef = false;
//                break;
//            }
//        }
//        return isNoConfigRef;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getKeyFromCpoSvcPkMap(Map<BigDecimal, BigDecimal> cpoSvcPkMap, BigDecimal newCpoSvcPk) {
//
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//            BigDecimal valCpoSvcPk = cpoSvcPkMap.get(origCpoSvcPk);
//            if (valCpoSvcPk == null) {
//                continue;
//            }
//            if (valCpoSvcPk.compareTo(newCpoSvcPk) == 0) {
//                return origCpoSvcPk;
//            }
//        }
//        return null;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getKeyFromCpoSvcDtlPkMap(Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, BigDecimal newCpoSvcDtlPk) {
//
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            BigDecimal valCpoSvcDtlPk = cpoSvcDtlPkMap.get(origCpoSvcDtlPk);
//            if (valCpoSvcDtlPk == null) {
//                continue;
//            }
//            if (valCpoSvcDtlPk.compareTo(newCpoSvcDtlPk) == 0) {
//                return origCpoSvcDtlPk;
//            }
//        }
//        return null;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void setRddRsdExpdDt(NWAL1500CMsg bizMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, Map<String, CPO_DTLTMsg> setCacheMap) {
//
//        if (ZYPCommonFunc.hasValue(cpoDtlTMsg.setMdseCd)) {
//            CPO_DTLTMsg setCpoDtlTMsg = setCacheMap.get(cpoDtlTMsg.cpoDtlLineNum.getValue());
//            if (setCpoDtlTMsg != null) {
//                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rsdDt, setCpoDtlTMsg.rsdDt);
//                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.expdShipDt, setCpoDtlTMsg.expdShipDt);
//                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rddDt, setCpoDtlTMsg.rddDt);
//                return;
//            }
//        }
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//        String slsDt = bizMsg.slsDt.getValue();
//        String rdd = slsDt;
//        String rsd = "";
//        String esd = "";
//
//        slsDt = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), slsDt);
//        boolean callCalcLtAPI = false;
//        if (!"000".equals(cpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
//            callCalcLtAPI = true;
//        }
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, cpoDtlTMsg.mdseCd.getValue());
//        if (mdseTMsg == null) {
//            callCalcLtAPI = false;
//        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsg.invtyCtrlFlg.getValue())) {
//            callCalcLtAPI = false;
//        }
//        if (!callCalcLtAPI) {
//            cpoDtlTMsg.rddDt.clear();
//            cpoDtlTMsg.rsdDt.clear();
//            cpoDtlTMsg.expdShipDt.clear();
//            return;
//        }
//        if (callCalcLtAPI) {
//            if (isExportForCtry(glblCmpyCd, cpoDtlTMsg.shipToCtryCd.getValue())) {
//                // [SHPG_SVC_LVL]
//                SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = new SHPG_SVC_LVLTMsg();
//                ZYPEZDItemValueSetter.setValue(shpgSvcLvlTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(shpgSvcLvlTMsg.shpgSvcLvlCd, cpoDtlTMsg.shpgSvcLvlCd.getValue());
//                shpgSvcLvlTMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(shpgSvcLvlTMsg);
//
//                // needs shipToStCd? (0:Ground Standard
//                // Delivery, 1:Pick Up)
//                boolean needsShipToStCd = false;
//                if (shpgSvcLvlTMsg != null) {
//                    needsShipToStCd = asList(SHPG_SVC_TP.GROUND_STANDARD_DELIVERY, SHPG_SVC_TP.PICK_UP).contains(shpgSvcLvlTMsg.shpgSvcTpCd.getValue());
//                }
//
//                if (needsShipToStCd) {
//
//                    // State
//                    final String shipToStCd = cpoDtlTMsg.shipToStCd.getValue();
//                    if (!ZYPCommonFunc.hasValue(shipToStCd)) {
//                        callCalcLtAPI = false;
//                    } else if (!existsShipToSt(glblCmpyCd, shipToStCd)) {
//                        callCalcLtAPI = false;
//                    }
//
//                    if (!callCalcLtAPI) {
//                        // RSD
//                        if (!ZYPCommonFunc.hasValue(rsd)) {
//                            rsd = slsDt;
//                        }
//                        esd = rsd;
//                    }
//                }
//            }
//        }
//
//        // --------------------------------------------------
//        // [NWZC002001] : Lead Time Calculation API
//        // --------------------------------------------------
//        boolean hasError = false;
//
//        if (callCalcLtAPI) {
//
//            final NWZC002001PMsg apiPMsg = new NWZC002001PMsg();
//            ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, mdseTMsg.mdseCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ordQty, cpoDtlTMsg.ordQty);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.shpgSvcLvlCd, cpoDtlTMsg.shpgSvcLvlCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.frtChrgToCd, cpoDtlTMsg.frtChrgToCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.frtChrgMethCd, cpoDtlTMsg.frtChrgMethCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.xxRddDt, rdd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.invtyLocCd, cpoDtlTMsg.invtyLocCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToPostCd, cpoDtlTMsg.shipToPostCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd, cpoDtlTMsg.shipToCustCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToStCd, cpoDtlTMsg.shipToStCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.sellToCustCd, cpoTMsg.sellToCustCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.uomCd, cpoDtlTMsg.custUomCd);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, slsDt);
//
//            new NWZC002001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
//
//            // has Error.
//            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
//                hasError = true;
//            } else {
//                // ESD = RDD - a longest transportation lead
//                // time within the SSL
//                esd = apiPMsg.xxPsdDt.getValue();
//                // RSD = RDD - a longest transportation lead
//                // time within the SSL -
//                // mercahndise.allocation lead time
//                rsd = apiPMsg.xxPsdDt.getValue();
//                int daysPriAllocNum = nullToZero(mdseTMsg.daysPriAllocNum.getValue()).intValue();
//                if (daysPriAllocNum > 0) {
//                    rsd = ZYPDateUtil.addDays(esd, -1 * daysPriAllocNum);
//                    rsd = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), rsd);
//                }
//            }
//        }
//
//        // set RSD/ESD/RDD
//        if (!hasError) {
//
//            // RSD/ESD/RDD
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rsdDt, rsd);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.expdShipDt, esd);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rddDt, rdd);
//
//            // Component
//            if (ZYPCommonFunc.hasValue(cpoDtlTMsg.setMdseCd)) {
//                final String key = cpoDtlTMsg.cpoDtlLineNum.getValue();
//                if (!setCacheMap.containsKey(key)) {
//                    setCacheMap.put(key, cpoDtlTMsg);
//                }
//            }
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String adjustBizDay(String glblCmpyCd, String invtyLocCd, String targetDate) {
//
//        // Illegal date.
//        if (!ZYPCommonFunc.hasValue(targetDate)) {
//            return targetDate;
//        }
//
//        // WAREHOUSE_CALENDAR
//        CAL_RELNTMsg calReln = getCalRelnTMsg(glblCmpyCd, CAL_SUB_TP.WAREHOUSE_CALENDAR, invtyLocCd);
//
//        if (calReln == null) {
//            return targetDate;
//        }
//
//        final String calTpCd = calReln.calTpCd.getValue();
//
//        if (isBizDay(glblCmpyCd, calTpCd, targetDate)) {
//            return targetDate;
//        } else {
//            return getBizDay(glblCmpyCd, calTpCd, targetDate);
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static CAL_RELNTMsg getCalRelnTMsg(String glblCmpyCd, String calSubTpCd, String calMultCd) {
//
//        final CAL_RELNTMsg calRelnTMsg = new CAL_RELNTMsg();
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calSubTpCd, calSubTpCd);
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calMultCd, calMultCd);
//
//        return (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean isBizDay(String glblCmpyCd, String calTpCd, String targetDate) {
//
//        boolean isBizDay = false;
//        try {
//            isBizDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, targetDate);
//        } catch (S21AbendException e) {
//            isBizDay = false;
//        }
//
//        return isBizDay;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String getBizDay(String glblCmpyCd, String calTpCd, String targetDate) {
//
//        String bizDay = targetDate;
//        try {
//            bizDay = ZYPDateUtil.addBusinessDayEx(glblCmpyCd, calTpCd, targetDate, -1);
//        } catch (S21AbendException e) {
//            bizDay = targetDate;
//        }
//
//        return targetDate;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean isExportForCtry(String glblCmpyCd, String shipToCtryCd) {
//
//        return NWXC001001Export.isExportForCtry(glblCmpyCd, shipToCtryCd);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean existsShipToSt(String glblCmpyCd, String shipToStCd) {
//
//        if (!ZYPCommonFunc.hasValue(shipToStCd)) {
//            return true;
//        }
//
//        final STTMsg sTTMsg = new STTMsg();
//        sTTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        sTTMsg.stCd.setValue(shipToStCd);
//
//        return S21CacheTBLAccessor.findByKey(sTTMsg) != null;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal nullToZero(BigDecimal decimal) {
//        if (ZYPCommonFunc.hasValue(decimal)) {
//            return decimal;
//        }
//        return ZERO;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(CPOTMsg cpoTMsg) {
//
//        insertBizProcLog(cpoTMsg.cpoOrdNum.getValue(), ORD_CREATE, null, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(DS_CPO_CONFIGTMsg dsCpoConfigTMsg) {
//
//        String cpoOrdNum = dsCpoConfigTMsg.cpoOrdNum.getValue();
//        String docId = dsCpoConfigTMsg.dsOrdPosnNum.getValue();
//        String docTp = DOC_TP_OM;
//        if (CONFIG_CATG.INBOUND.equals(dsCpoConfigTMsg.configCatgCd.getValue())) {
//            docTp = DOC_TP_RT;
//        }
//        insertBizProcLog(cpoOrdNum, ORD_CREATE, docId, docTp);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(CPO_DTLTMsg cpoDtlTMsg) {
//
//        String cpoOrdNum = cpoDtlTMsg.cpoOrdNum.getValue();
//        String docId = cpoDtlTMsg.cpoDtlLineNum.getValue() + "." + cpoDtlTMsg.cpoDtlLineSubNum.getValue();
//        insertBizProcLog(cpoOrdNum, ORD_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {
//
//        boolean isPureRtrn = isPureReturn(dsCpoRtrnDtlTMsg);
//
//        String cpoOrdNum = dsCpoRtrnDtlTMsg.cpoOrdNum.getValue();
//        String docId = dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue() + "." + dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
//        String eventId = ORD_SAVE;
//
//        if (isPureRtrn) {
//            eventId = ORD_CREATE;
//        }
//        insertBizProcLog(cpoOrdNum, eventId, docId, DOC_TP_RT);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg, Map<BigDecimal, String> dsCpoConfigPkMap) {
//
//        String cpoOrdNum = dsCpoSlsCrTMsg.cpoOrdNum.getValue();
//        String docId = null;
//        String docTp = DOC_TP_OM;
//
//        DS_CPO_CONFIGTMsg configMsg = null;
//        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsCpoConfigPk)) {
//            configMsg = new DS_CPO_CONFIGTMsg();
//            ZYPEZDItemValueSetter.setValue(configMsg.glblCmpyCd, dsCpoSlsCrTMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(configMsg.dsCpoConfigPk, dsCpoSlsCrTMsg.dsCpoConfigPk);
//            configMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(configMsg);
//        }
//        if (configMsg != null) {
//            docId = configMsg.dsOrdPosnNum.getValue();
//            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
//                docTp = DOC_TP_RT;
//            }
//            if (!dsCpoConfigPkMap.containsKey(configMsg.dsCpoConfigPk.getValue())) {
//                dsCpoConfigPkMap.put(configMsg.dsCpoConfigPk.getValue(), docTp);
//            }
//        }
//        insertBizProcLog(cpoOrdNum, ORD_SLS_CR_CREATE, docId, docTp);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(String cpoOrdNum, CPO_SVC_DTLTMsg cpoSvcDtlTMsg) {
//
//        String docId = String.valueOf(cpoSvcDtlTMsg.cpoSvcLineNum.getValue());
//        insertBizProcLog(cpoOrdNum, ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        String docId = getCpoSvcAddlBasePrcDocId(cpoSvcAddlBasePrcTMsg);
//        insertBizProcLog(cpoSvcAddlBasePrcTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        String docId = getCpoSvcAddlChrgPrcDocId(cpoSvcAddlChrgPrcTMsg);
//        insertBizProcLog(cpoSvcAddlChrgPrcTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        String docId = getCpoSvcConfigRefDocId(cpoSvcConfigRefTMsg);
//        insertBizProcLog(cpoSvcConfigRefTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertProcLog(String cpoOrdNum, CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        String docId = getCpoSvcPrcDocId(cpoSvcPrcTMsg);
//        insertBizProcLog(cpoOrdNum, ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static void insertBizProcLog(String cpoOrdNum, String eventId, String docId, String docTpCd) {
//        S21BusinessProcessLogMsg bizProcLog = new S21BusinessProcessLogMsg();
//
//        bizProcLog.setSubSysId(SUB_SYS_ID_NWZ);
//        bizProcLog.setProcId(DOC_TP_OM);
//        bizProcLog.setDocTpCd(docTpCd);
//        bizProcLog.setPrntDocId(cpoOrdNum);
//        bizProcLog.setEventId(eventId);
//        if (ZYPCommonFunc.hasValue(docId)) {
//            bizProcLog.setDocId(docId);
//        }
////        bizProcLog.setBizProcCmntTxt_01(getBizProcCmntTxt_01(cpoBean));
////        bizProcLog.setBizProcCmntTxt_02(cpoBean.getBizProcCmntTxt());
//
//        S21BusinessProcessLog.print(bizProcLog);
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static boolean isPureReturn(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {
//
//        boolean isPureRtrn = true;
//        CPO_DTLTMsg origCpoDtlTMsg = new CPO_DTLTMsg();
//        origCpoDtlTMsg.setSQLID("008");
//        origCpoDtlTMsg.setConditionValue("glblCmpyCd01", dsCpoRtrnDtlTMsg.glblCmpyCd.getValue());
//        origCpoDtlTMsg.setConditionValue("cpoOrdNum01", dsCpoRtrnDtlTMsg.cpoOrdNum.getValue());
//
//        CPO_DTLTMsgArray copyCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(origCpoDtlTMsg);
//
//        if (copyCpoDtlTMsgArray.getValidCount() > 0) {
//            isPureRtrn = false;
//        }
//        return isPureRtrn;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String getCpoSvcAddlBasePrcDocId(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcAddlBasePrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcAddlBasePrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcAddlBasePrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcAddlBasePrcTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcAddlBasePrcTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcAddlBasePrcTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue() + ".";
//            docId = docId + cpoSvcAddlBasePrcTMsg.cpoDtlLineNum.getValue() + "." + cpoSvcAddlBasePrcTMsg.cpoDtlLineSubNum.getValue();
//        }
//        return docId;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String getCpoSvcAddlChrgPrcDocId(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcAddlChrgPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcAddlChrgPrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcAddlChrgPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcAddlChrgPrcTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcAddlChrgPrcTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcAddlChrgPrcTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue() + ".";
//            docId = docId + cpoSvcAddlChrgPrcTMsg.cpoDtlLineNum.getValue() + "." + cpoSvcAddlChrgPrcTMsg.cpoDtlLineSubNum.getValue();
//        }
//        return docId;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String getCpoSvcConfigRefDocId(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcConfigRefTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcConfigRefTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcConfigRefTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcConfigRefTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcConfigRefTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcConfigRefTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue();
//        }
//        return docId;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static String getCpoSvcPrcDocId(CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcPrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            if (ZYPCommonFunc.hasValue(cpoSvcPrcTMsg.mdlId)) {
//                docId = docId + String.valueOf(cpoSvcPrcTMsg.mdlId.getValue());
//            } else {
//                docId = docId + DS_CONTR_CATG.FLEET;
//            }
//        }
//        return docId;
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(CPOTMsg cpoTMsg) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//        queryParam.remove("docId");
//        queryParam.put("cpoOrdNum", cpoTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(DS_CPO_CONFIGTMsg dsCpoConfigTMsg) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_CREATE + "%");
//
//        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, dsCpoConfigTMsg.configCatgCd.getValue())) {
//            queryParam.put("docTpCd", DOC_TP_OM);
//        } else {
//            queryParam.put("docTpCd", DOC_TP_RT);
//        }
//
//        String docId = dsCpoConfigTMsg.dsOrdPosnNum.getValue();
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", dsCpoConfigTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", dsCpoConfigTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", dsCpoConfigTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(CPO_DTLTMsg cpoDtlTMsg) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        String docId = cpoDtlTMsg.cpoDtlLineNum.getValue() + "." + cpoDtlTMsg.cpoDtlLineSubNum.getValue();
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoDtlTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoDtlTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoDtlTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//
//        boolean isPureRtrn = isPureReturn(dsCpoRtrnDtlTMsg);
//        String eventId = ORD_SAVE;
//
//        if (isPureRtrn) {
//            eventId = ORD_CREATE;
//        }
//
//        queryParam.put("eventId", eventId + "%");
//        queryParam.put("docTpCd", DOC_TP_RT);
//
//        String docId = dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue() + "." + dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", dsCpoRtrnDtlTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", dsCpoRtrnDtlTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", dsCpoRtrnDtlTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg, Map<BigDecimal, String> dsCpoConfigPkMap) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//
//        queryParam.put("eventId", ORD_SLS_CR_CREATE + "%");
//
//        String docTpCd = DOC_TP_OM;
//        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsCpoConfigPk.getValue())) {
//            docTpCd = dsCpoConfigPkMap.get(dsCpoSlsCrTMsg.dsCpoConfigPk.getValue());
//            if (docTpCd == null) {
//                docTpCd = DOC_TP_OM;
//            }
//        }
//        queryParam.put("docTpCd", docTpCd);
//
//        String docId = null;
//        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsOrdPosnNum)) {
//            docId = dsCpoSlsCrTMsg.dsOrdPosnNum.getValue();
//        }
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", dsCpoSlsCrTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", dsCpoSlsCrTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", dsCpoSlsCrTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(String cpoOrdNum, CPO_SVC_DTLTMsg cpoSvcDtlTMsg) {
//
//        String docId = String.valueOf(cpoSvcDtlTMsg.cpoSvcLineNum.getValue());
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        queryParam.put("userId", cpoSvcDtlTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcDtlTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        String docId = getCpoSvcAddlBasePrcDocId(cpoSvcAddlBasePrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcAddlBasePrcTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcAddlBasePrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcAddlBasePrcTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        String docId = getCpoSvcAddlChrgPrcDocId(cpoSvcAddlChrgPrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcAddlChrgPrcTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcAddlChrgPrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcAddlChrgPrcTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        String docId = getCpoSvcConfigRefDocId(cpoSvcConfigRefTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcConfigRefTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcConfigRefTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcConfigRefTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    private static BigDecimal getBizProcLogPk(String cpoOrdNum, CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        String docId = getCpoSvcPrcDocId(cpoSvcPrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        queryParam.put("userId", cpoSvcPrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcPrcTMsg.ezUpTime.getValue());
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getMaxBizProcPk(queryParam);
//
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return null;
//        }
//    }
//    // 2016/10/11 S21_NA#7924-2 Add End
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    // S21_NA#16806 Add Start
//    private static boolean registCopyDsCpoCtacPsnForHdr(NWAL1500CMsg bizMsg) {
//
//        DS_CPO_CTAC_PSNTMsg origDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
//
//        origDsCpoCtacPsnTMsg.setSQLID("009");
//        origDsCpoCtacPsnTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        origDsCpoCtacPsnTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//        DS_CPO_CTAC_PSNTMsgArray copyDsCpoCtacPsnTMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoCtacPsnTMsg);
//
//        List<DS_CPO_CTAC_PSNTMsg> copyDsCpoCtacPsnTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>(0);
//
//        for (int i = 0; i < copyDsCpoCtacPsnTMsgArray.getValidCount(); i++) {
//            DS_CPO_CTAC_PSNTMsg copyDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
//            EZDMsg.copy(copyDsCpoCtacPsnTMsgArray.no(i), null, copyDsCpoCtacPsnTMsg, null);
//
//            copyDsCpoCtacPsnTMsg.cpoOrdNum.setValue(bizMsg.xxSrchNum.getValue());
//            copyDsCpoCtacPsnTMsg.dsCpoCtacPsnPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
//            copyDsCpoCtacPsnTMsgList.add(copyDsCpoCtacPsnTMsg);
//        }
//
//        if (!copyDsCpoCtacPsnTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoCtacPsnTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
//            if (rsltCnt != copyDsCpoCtacPsnTMsgList.size()) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_CTAC_PSN", bizMsg.cpoOrdNum.getValue() });
//                return true;
//            }
//        }
//
//        return false;
//    }
//    // S21_NA#16806 Add End
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End
}
