/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.CPO_DTL_LINE_SUB_NUM_SET_PRNT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_CANC_PRTL_CANC;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MAX_ERR_REF_NUM_CNT;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE_REF_ACT_CANC;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0682E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0688E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0833E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0912E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0947E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0958E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM8460E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForLineCancel;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.blap.NWAL1500.NWAL1500_GCMsgArray;
import business.blap.NWAL1500.NWAL1500_HCMsgArray;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_ISMsgArray;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * 2016/01/04   Fujitsu         T.ishii         Update          S21_NA#955
 * 2016/01/28   Fujitsu         S.Takami        Update          S21_NA#3254
 * 2016/02/19   Fujitsu         S.Takami        Update          S21_NA#2166
 * 2016/02/24   Fujitsu         M.Hara          Update          QC#4078
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/04/11   Fujitsu         S.Takami        Update          S21_NA#3236-3
 * 2016/04/18   Fujitsu         S.Takami        Update          S21_NA#5406
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/06/03   Fujitsu         M.Yamada        Update          S21_NA#5395
 * 2016/06/03   Fujitsu         S.Takami        Update          S21_NA#9301
 * 2016/07/06   Fujitsu         H.Ikeda         Update          QC#10909
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/11/02   Fujitsu         S.Takami        Update          S21_NA#5394-3
 * 2016/11/09   Fujitsu         S.Iidaka        Update          S21_NA#9867
 * 2017/02/08   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2018/02/03   Fujitsu         S.Takami        Update          S21_NA#19808 (bizMsg.A, B, C, D => glblMsg.A, B, C, D without comments)
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2018/05/16   Fujitsu         M.Ohno          Update          S21_NA#25144
 * 2018/06/19   Fujitsu         M.Yamada        Update          QC#26691
 * 2018/06/14   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/06/27   Fujitsu         K.Ishizuka      Update          S21_NA#26602
 * 2018/09/11   Fujitsu         K.Ishizuka      Update          S21_NA#25352
 * 2019/03/08   Fujitsu         R.Nakamura      Update          S21_NA#30629
 * 2019/03/29   Fujitsu         R.Nakamura      Update          S21_NA#30849
 * 2019/03/19   Fujitsu         M.Ohno          Update          S21_NA#30700
 * 2019/06/03   Fujitsu         C.Hara          Update          QC#50555
 * 2019/06/11   Fujitsu         C.Hara          Update          QC#50787
 * 2019/06/18   Fujitsu         K.Kato          Update          S21_NA#50732
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2023/05/11   CITS            R.Azucena       Update          QC#61514
 * </pre>
 */
public class NWAL1500CommonLogicForLineCancel {

    // 2016/02/19 S21_NA#2166 Add Start
    /**
     * <pre>
     * Prepare values for cancel popup
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    // 2018/05/16 S21_NA#25144 mod start
//    public static void prepareOpenCancelPopup(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
    public static int prepareOpenCancelPopup(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
    // 2018/05/16 S21_NA#25144 mod end

        // 2018/02/03 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        // 2018/02/03 S21_NA#19808 Add End

        // save current page data to global message 
        // initialize open pop up = Y
        bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_ON_Y);

        // 2018/05/16 S21_NA#25144 add start
        int firstSelectPage = 0;
        // 2018/05/16 S21_NA#25144 add end

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
//QC743
//            NWAL1500CommonLogicForLineControl.storeAllLine(glblMsg.J, bizMsg.B);

            // TAB_LINE_CONFIG
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y); // 2018/02/03 S21_NA#19808 Mod
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (selectedRowsForConfig.size() + selectedRowsForLine.size() == 0) {

                // no selected row
                bizMsg.setMessageInfo(NWAM0504E);
                // 2018/05/16 S21_NA#25144mod start
                return 0;
                // 2018/05/16 S21_NA#25144mod end
            }

            // 2018/06/14 S21_NA#25227 Add Start
            if (selectedRowsForConfig.size() > 0 && selectedRowsForLine.size() > 0) {

                bizMsg.setMessageInfo(NWAM0688E);
                return 0;
            }
            // 2018/06/14 S21_NA#25227 Add End

            // If there are Entered or New line inthe config, error!!
            if (isEnteredOrAddLine(bizMsg, glblMsg, selectedRowsForConfig, selectedRowsForLine)) { //2018/02/03 S21_NA#19808 Mod
                // 2018/05/16 S21_NA#25144mod start
                return 0;
                // 2018/05/16 S21_NA#25144mod end
            }
//            if (selectedRowsForConfig.size() + selectedRowsForLine.size() > 1) {
//
//                // multiple rows selected
//                bizMsg.setMessageInfo(NWAM0683E);
//                return;
//            }
//
//            /**
//             * CONFIG LEVEL
//             */
//            if (selectedRowsForConfig.size() > 0) {
//
//                // selected config line
//                int selectedRowForConfig = selectedRowsForConfig.get(0);
//                NWAL1500_ACMsg aMsg = bizMsg.A.no(selectedRowForConfig);
//
//                if (NWAL1500CommonLogicForLineCancel.isRegisteredConfig(glblMsg.J, aMsg.dsOrdPosnNum_LC.getValue())) {
//
//                    // this config line has been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessRegisteredConfig(bizMsg.glblCmpyCd.getValue(), bizMsg, aMsg);
//
//                } else {
//
//                    // this config line has not been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredConfig(bizMsg.glblCmpyCd.getValue(), bizMsg, aMsg);
//                }
//                return;
//            }
//
//            /**
//             * LINE LEVEL
//             */
//            if (selectedRowsForLine.size() > 0) {
//
//                int selectedRowForLine = selectedRowsForLine.get(0);
//                NWAL1500_BCMsg line = bizMsg.B.no(selectedRowForLine);
//
//                if (NWAL1500CommonLogicForLineCancel.isRegisteredLine(line)) {
//
//                    // this line has been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessRegisteredLine(bizMsg.glblCmpyCd.getValue(), bizMsg, line);
//
//                } else {
//
//                    String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
//                    // this line has not been registered
//                    List<NWAL1500_JSMsg> lineList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.J, dsOrdPosnNum);
//                    if (lineList.size() > 1) {
//                        // remove line level
//                        NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredLineCancel(bizMsg, glblMsg, line);
//                    } else {
//                        // remove configuration level
//                        int configRow = NWAL1500CommonLogicForLineControl.getConfigIndex(bizMsg.A, dsOrdPosnNum);
//                        NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredConfigForAllCancel(bizMsg, glblMsg, dsOrdPosnNum, configRow);
//                    }
//
//                    // not open pop up = N
//                    bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//            }
            //Config level check
            boolean ret = false;
            List<NWAL1500_ASMsg> cancelRowForConfig = new ArrayList<NWAL1500_ASMsg>();
            List<NWAL1500_BSMsg> cancelRowForLine = new ArrayList<NWAL1500_BSMsg>();
            int cntRegistared = 0;
            int cntUnRegistared = 0;
            if (selectedRowsForConfig.size() > 0) {
                for (int selectedRowForConfig : selectedRowsForConfig) {
                    NWAL1500_ASMsg aMsg = glblMsg.A.no(selectedRowForConfig);
                    // 2018/05/16 S21_NA#25144 add start
                    if (firstSelectPage == 0) {
                        firstSelectPage = aMsg.xxPageNum_LC.getValueInt();
                    }
                    // 2018/05/16 S21_NA#25144 add end

                    if (NWAL1500CommonLogicForLineCancel.isRegisteredConfig(glblMsg.B, aMsg.dsOrdPosnNum_LC.getValue())) {
                        // ret = NWAL1500CommonLogicForLineCancel.doProcessRegisteredConfig(bizMsg, aMsg); // 2018/06/27 S21_NA#26602 Mod
                        ret = NWAL1500CommonLogicForLineCancel.doProcessRegisteredConfig(bizMsg, aMsg, glblMsg);
                        if (ret) {
                            // 2018/05/16 S21_NA#25144 mod start
//                            return ;
                            return aMsg.xxPageNum_LC.getValueInt();
                            // 2018/05/16 S21_NA#25144 mod start
                        }
                        cntRegistared++;
                    } else {
                        cntUnRegistared++;
                        cancelRowForConfig.add(aMsg);
                    }
                }
            }
            // Line level check
            if (selectedRowsForLine.size() > 0) {
                for (int selectedRowForLine : selectedRowsForLine) {
                    NWAL1500_BSMsg line = glblMsg.B.no(selectedRowForLine);
                    if (NWAL1500CommonLogicForLineCancel.isRegisteredLine(line)) {
                        // 2018/05/16 S21_NA#25144 add start
                        if (firstSelectPage > line.xxPageNum_LL.getValueInt() || firstSelectPage == 0) {
                            firstSelectPage = line.xxPageNum_LL.getValueInt();
                        }
                        // 2018/05/16 S21_NA#25144 add end

                        // this line has been registered
                        ret = NWAL1500CommonLogicForLineCancel.doProcessRegisteredLine(bizMsg, glblMsg, line);
                        if (ret) {
                            // 2018/05/16 S21_NA#25144 mod start
//                            return ;
                            return line.xxPageNum_LL.getValueInt();
                            // 2018/05/16 S21_NA#25144 mod end
                        } else {
                            cntRegistared++;
                        }
                    } else {
                        cntUnRegistared++;
                        cancelRowForLine.add(line);
                    }
                }
            }
            // 2018/06/14 S21_NA#25227 Del Start
//            if (cntRegistared == 1 && cntUnRegistared > 0) {
//                // multiple rows selected
//                bizMsg.setMessageInfo(NWAM0683E);
//                // 2018/05/16 S21_NA#25144 mod start
////                return;
//                return firstSelectPage;
//                // 2018/05/16 S21_NA#25144 mod end
//            }
//            if (cntRegistared > 1) {
//                // multiple rows selected
//                bizMsg.setMessageInfo(NWAM0683E);
//                // 2018/05/16 S21_NA#25144 mod start
////              return;
//                return firstSelectPage;
//              // 2018/05/16 S21_NA#25144 mod end
//            }
            // 2018/06/14 S21_NA#25227 Del End
            if (cntRegistared == 0) {
                bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
                // do cancel all selected row.
                NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredForAllCancel(bizMsg, glblMsg, cancelRowForConfig, cancelRowForLine);
            }
            // 2016/11/08 S21_NA#9867 Add Start
            for (Integer selectedRow : selectedRowsForLine) {
                int idx = selectedRow.intValue();
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(idx);
                NWAL1500_ASMsg configMsg = getConfigLineForLineConfig(glblMsg, lineMsg.dsOrdPosnNum_LL.getValue());
                String configTpCd = configMsg.configTpCd_LC.getValue();

                // ALL_CMPT_REQ_FLG ='Y'
                if (NWXC150001DsCheck.isAllCmptReqConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd, CONFIG_CATG.OUTBOUND)) {
                    if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_LL)) {
                        bizMsg.setMessageInfo(NWAM0912E);
                        lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0912E);
                        // 2018/05/16 S21_NA#25144 mod start
//                      return;
                        return lineMsg.xxPageNum_LL.getValueInt();
                        // 2018/05/16 S21_NA#25144 mod end
                    }
                }
            }
            // 2016/11/08 S21_NA#9867 Add End
            // 2018/06/14 S21_NA#25227 Add Start
            if (selectedRowsForConfig.size() > 0) {
                if (NWAL1500CommonLogicForDelete.isReferencedItemDeleting(bizMsg, glblMsg, MSG_PARAM_LINE_REF_ACT_CANC)) {
                    bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
                    return 0;
                }

                List<String> selectedNums = getSelectedNums(glblMsg.A, selectedRowsForConfig);
                bizMsg.xxPopPrm_P0.setValue(convDplyCancNums(selectedNums));
            }
            if (selectedRowsForLine.size() > 0) {
                List<String> selectedNums = getSelectedNums(glblMsg.B, selectedRowsForLine);
                bizMsg.xxPopPrm_P0.setValue(convDplyCancNums(selectedNums));
            }
            // 2018/06/14 S21_NA#25227 Add End
        } else {
//QC743
//            NWAL1500CommonLogicForLineControl.storeAllLine(glblMsg.K, bizMsg.D);

            // TAB RMA
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            if (selectedRowsForConfig.size() + selectedRowsForLine.size() == 0) {
                bizMsg.setMessageInfo(NWAM0504E);
                // 2018/05/16 S21_NA#25144mod start
                return 0;
                // 2018/05/16 S21_NA#25144mod end
            }

            // If there are Entered or New line inthe config, error!!
            if (isEnteredOrAddLine(bizMsg, glblMsg, selectedRowsForConfig, selectedRowsForLine)) { // 2018/02/03 S21_NA#19808 Mod
                // 2018/05/16 S21_NA#25144mod start
                return 0;
                // 2018/05/16 S21_NA#25144mod end
            }

            // 2018/06/14 S21_NA#25227 Add Start
            if (selectedRowsForConfig.size() > 0 && selectedRowsForLine.size() > 0) {

                bizMsg.setMessageInfo(NWAM0688E);
                return 0;
            }
            // 2018/06/14 S21_NA#25227 Add End

//            if (selectedRowsForConfig.size() + selectedRowsForLine.size() > 1) {
//                bizMsg.setMessageInfo(NWAM0683E);
//                return;
//            }
//
//            /**
//             * CONFIG LEVEL
//             */
//            if (selectedRowsForConfig.size() > 0) {
//
//                // selected config line
//                int selectedRowForConfig = selectedRowsForConfig.get(0);
//                NWAL1500_CCMsg cMsg = bizMsg.C.no(selectedRowForConfig);
//
//                if (NWAL1500CommonLogicForLineCancel.isRegisteredConfig(glblMsg.K, cMsg.dsOrdPosnNum_RC.getValue())) {
//
//                    // this config line has been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessRegisteredConfig(bizMsg.glblCmpyCd.getValue(), bizMsg, cMsg);
//
//                } else {
//
//                    // this config line has not been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredConfig(bizMsg.glblCmpyCd.getValue(), bizMsg, cMsg);
//                }
//                return;
//            }
//
//            /**
//             * LINE LEVEL
//             */
//            if (selectedRowsForLine.size() > 0) {
//
//                int selectedRowForLine = selectedRowsForLine.get(0);
//                NWAL1500_DCMsg line = bizMsg.D.no(selectedRowForLine);
//
//                if (NWAL1500CommonLogicForLineCancel.isRegisteredLine(line)) {
//
//                    // this line has been registered
//                    NWAL1500CommonLogicForLineCancel.doProcessRegisteredLine(bizMsg.glblCmpyCd.getValue(), bizMsg, line);
//
//                } else {
//                    String dsOrdPosnNum = line.dsOrdPosnNum_RL.getValue();
//                    // this line has not been registered
//                    List<NWAL1500_KSMsg> lineList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.K, dsOrdPosnNum);
//                    if (lineList.size() > 1) {
//                        // remove line level
//                        NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredLineCancel(bizMsg, glblMsg, line);
//                    } else {
//                        // remove configuration level
//                        int configRow = NWAL1500CommonLogicForLineControl.getConfigIndex(bizMsg.C, dsOrdPosnNum);
//                        NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredConfigForAllCancelRma(bizMsg, glblMsg, dsOrdPosnNum, configRow);
//                    }
//
//                    // not open pop up = N
//                    bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//            }
            boolean ret = false;
            List<NWAL1500_CSMsg> cancelRowForConfig = new ArrayList<NWAL1500_CSMsg>();
            List<NWAL1500_DSMsg> cancelRowForLine = new ArrayList<NWAL1500_DSMsg>();
            int cntRegistared = 0;
            int cntUnRegistared = 0;
            if (selectedRowsForConfig.size() > 0) {
                for (int selectedRowForConfig : selectedRowsForConfig) {
                    NWAL1500_CSMsg cMsg = glblMsg.C.no(selectedRowForConfig);
                    // 2018/05/16 S21_NA#25144 add start
                    if (firstSelectPage == 0) {
                        firstSelectPage = cMsg.xxPageNum_RC.getValueInt();
                    }
                    // 2018/05/16 S21_NA#25144 add end
                    if (NWAL1500CommonLogicForLineCancel.isRegisteredConfig(glblMsg.D, cMsg.dsOrdPosnNum_RC.getValue())) {
                        ret = NWAL1500CommonLogicForLineCancel.doProcessRegisteredConfig(bizMsg, cMsg); // 2018/02/03 S21_NA#19808
                        if (ret) {
                            // 2018/05/16 S21_NA#25144 add start
                            return cMsg.xxPageNum_RC.getValueInt();
                            // 2018/05/16 S21_NA#25144 add end
                        }
                        cntRegistared++;
                    } else {
                        cntUnRegistared++;
                        cancelRowForConfig.add(cMsg);
                    }
                }
            }
            // Line level check
            if (selectedRowsForLine.size() > 0) {
                for (int selectedRowForLine : selectedRowsForLine) {
                    NWAL1500_DSMsg line = glblMsg.D.no(selectedRowForLine);
                    if (NWAL1500CommonLogicForLineCancel.isRegisteredLine(line)) {
                        // 2018/05/16 S21_NA#25144 add start
                        if (firstSelectPage > line.xxPageNum_RL.getValueInt()) {
                            firstSelectPage = line.xxPageNum_RL.getValueInt();
                        }
                        // 2018/05/16 S21_NA#25144 add end
                        // this line has been registered
                        ret = NWAL1500CommonLogicForLineCancel.doProcessRegisteredLine(bizMsg, line);
                        if (ret) {
                            // 2018/05/16 S21_NA#25144 mod start
//                            return;
                            return  line.xxPageNum_RL.getValueInt();
                            // 2018/05/16 S21_NA#25144 mod end
                        } else {
                            cntRegistared++;
                        }
                    } else {
                        cntUnRegistared++;
                        cancelRowForLine.add(line);
                    }
                }
            }
            // 2018/06/14 S21_NA#25227 Del Start
//            if (cntRegistared == 1 && cntUnRegistared > 0) {
//                // multiple rows selected
//                bizMsg.setMessageInfo(NWAM0683E);
//                // 2018/05/16 S21_NA#25144 mod start
////              return;
//                return firstSelectPage;
//                // 2018/05/16 S21_NA#25144 mod end
//            }
//            if (cntRegistared > 1) {
//                // multiple rows selected
//                bizMsg.setMessageInfo(NWAM0683E);
//                // 2018/05/16 S21_NA#25144 mod start
////              return;
//                return firstSelectPage;
//              // 2018/05/16 S21_NA#25144 mod end
//            }
            // 2018/06/14 S21_NA#25227 Del End
            if (cntRegistared == 0) {
                bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
                // do cancel all selected row.
                NWAL1500CommonLogicForLineCancel.doProcessUnRegisteredForAllCancelRma(bizMsg, glblMsg, cancelRowForConfig, cancelRowForLine);
            }
            // 2018/06/14 S21_NA#25227 Add Start
            if (hasSelectedParentAndChild(bizMsg, glblMsg, selectedRowsForLine)) {
                bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
                return 0;
            }
            if (selectedRowsForConfig.size() > 0) {
                if (NWAL1500CommonLogicForDelete.isReferencedItemDeleting(bizMsg, glblMsg, MSG_PARAM_LINE_REF_ACT_CANC)) {
                    bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
                    return 0;
                }

                List<String> selectedNums = getSelectedNums(glblMsg.C, selectedRowsForConfig);
                bizMsg.xxPopPrm_P0.setValue(convDplyCancNums(selectedNums));
            }
            if (selectedRowsForLine.size() > 0) {
                List<String> selectedNums = getSelectedNums(glblMsg.D, selectedRowsForLine);
                bizMsg.xxPopPrm_P0.setValue(convDplyCancNums(selectedNums));
            }
            // 2018/06/14 S21_NA#25227 Add End
        }

        return 0;
    }

    /**
     * <pre>
     * Do Process Cancel Config Or Line
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void doProcessCancelConfigOrLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/02/03 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        // Add Start 2017/02/08 QC#17257
        List<String> posnNums = new ArrayList<String>();
        // Add End 2017/02/08 QC#17257

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {

            // TAB_LINE_CONFIG
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            /**
             * CONFIG LEVEL
             */
            if (selectedRowsForConfig.size() > 0) {

                // selected configuration line
                // 2018/06/14 S21_NA#25227 Mod Start
//                int selectedRowForConfig = selectedRowsForConfig.get(0);
                for (int selectedRowForConfig : selectedRowsForConfig) {
                // 2018/06/14 S21_NA#25227 Mod End
                    NWAL1500_ASMsg config = glblMsg.A.no(selectedRowForConfig);
                    String dsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();
                    if (isRegisteredConfig(glblMsg.B, dsOrdPosnNum)) {

                        // has been registered.
                        doProcessRegisteredConfigForAllCancel(bizMsg, glblMsg, config); // 2018/02/03 S21_NA#19808

                    } else {

                        // has not been registered.
                        doProcessUnRegisteredConfigForAllCancel(bizMsg, glblMsg, dsOrdPosnNum, selectedRowForConfig);
                    }
                // 2018/06/14 S21_NA#25227 Add Start
                }
                // 2018/06/14 S21_NA#25227 Add End
            }

            /**
             * LINE LEVEL
             */
            if (selectedRowsForLine.size() > 0) {

                // 2018/06/14 S21_NA#25227 Mod Start
//                int selectedRowForLine = selectedRowsForLine.get(0);
//                NWAL1500_BSMsg line = glblMsg.B.no(selectedRowForLine);
//
//                if (isRegisteredLine(line)) {
//
//                    // this line has been registered
//                    doProcessRegisteredLineCancel(bizMsg, glblMsg, line, selectedRowForLine); // 2018/02/03 S21_NA#19808
//
//                    // 2016/04/11 S21_NA#3236-3 Add Start
//                    // Edit Base Component
//                    int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selectedRowForLine);// 2018/02/03 S21_NA#19808
//                    NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));
//                    // Edit Line Reference
//                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/02/03 S21_NA#19808
//                    // get model name
//                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/02/03 S21_NA#19808
//                    // 2016/04/11 S21_NA#3236-3 Add End
//                }
                Set<Integer> configSet = new HashSet<Integer>();
                for (int selectedRowForLine : selectedRowsForLine) {
                    NWAL1500_BSMsg line = glblMsg.B.no(selectedRowForLine);

                    if (isRegisteredLine(line)) {
                        
                        // this line has been registered
                        doProcessRegisteredLineCancel(bizMsg, glblMsg, line, selectedRowForLine);

                        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selectedRowForLine);
                        configSet.add(slctConfIndex);
                    }

                }

                for (int confIndex : configSet) {

                    // Edit Base Component
                    // Mod Start 2019/03/29 QC#30849
                    if (NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(confIndex).configTpCd_LC.getValue())) {
                        NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig(bizMsg, glblMsg, glblMsg.A.no(confIndex));
                    }
                    // Mod End 2019/03/29 QC#30849
                    // Edit Line Reference
                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, confIndex);
                    // get model name
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(confIndex));
                }
                // 2018/06/14 S21_NA#25227 Mod End
            }

        } else {

            // TAB_RMA
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            /**
             * CONFIG LEVEL
             */
            if (selectedRowsForConfig.size() > 0) {

                // selected config line
                // 2018/06/14 S21_NA#25227 Mod Start
//                int selectedRowForConfig = selectedRowsForConfig.get(0);
                for (int selectedRowForConfig : selectedRowsForConfig) {
                // 2018/06/14 S21_NA#25227 Mod End
                    NWAL1500_CSMsg config = glblMsg.C.no(selectedRowForConfig);
                    String dsOrdPosnNum = config.dsOrdPosnNum_RC.getValue();

                    if (isRegisteredConfig(glblMsg.D, config.dsOrdPosnNum_RC.getValue())) {

                        // has been registered.
                        doProcessRegisteredConfigForAllCancel(bizMsg, glblMsg, config);  // 2018/02/03 S21_NA#19808

                    } else {

                        // has not been registered.
                        doProcessUnRegisteredConfigForAllCancelRma(bizMsg, glblMsg, dsOrdPosnNum, selectedRowForConfig);
                    }
                // 2018/06/14 S21_NA#25227 Add Start
                }
                // 2018/06/14 S21_NA#25227 Add End
            }

            /**
             * LINE LEVEL
             */
            if (selectedRowsForLine.size() > 0) {

                // 2018/06/14 S21_NA#25227 Mod Start
//                int selectedRowForLine = selectedRowsForLine.get(0);
//                NWAL1500_DSMsg line = glblMsg.D.no(selectedRowForLine);
//
//                if (isRegisteredLine(line)) {
//
//                    // this line has been registered
//                    doProcessRegisteredLineCancel(bizMsg, glblMsg, line, selectedRowForLine); // 2018/02/03 S21_NA#19808
//
//                    // Add Start 2017/02/08 QC#17257
//                    for (int i : selectedRowsForLine) {
//                        String getPosnNums = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
//                        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxChkBox_RL.getValue()) //
//                                && !posnNums.contains(getPosnNums)) {
//                            posnNums.add(getPosnNums);
//                        }
//                    }
//                    // Add End 2017/02/08 QC#17257
//
//                    // 2017/11/21 S21_NA#22555 Add Start
//                    // Edit Base Component
//                    int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selectedRowForLine);  // 2018/02/03 S21_NA#19808
//                    Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();
//                    NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig4Rma(bizMsg, glblMsg, baseComponentMap, slctConfIndex); // 2018/02/03 S21_NA#19808
//                    // get model name
//                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.C.no(slctConfIndex));  // 2018/02/03 S21_NA#19808
//                    // 2017/11/21 S21_NA#22555 Add End
//                }
                Set<Integer> configSet = new HashSet<Integer>();
                for (int selectedRowForLine : selectedRowsForLine) {
                    NWAL1500_DSMsg line = glblMsg.D.no(selectedRowForLine);

                    if (isRegisteredLine(line)) {

                        // this line has been registered
                        doProcessRegisteredLineCancel(bizMsg, glblMsg, line, selectedRowForLine);

                        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selectedRowForLine);
                        configSet.add(slctConfIndex);
                    }
                }

                for (int confIndex : configSet) {

                    // Edit Base Component
                    Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();
                    NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig4Rma(bizMsg, glblMsg, baseComponentMap, confIndex);
                    // Edit Line Reference
                    NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, confIndex); // 2018/09/11 S21_NA#25352 Add
                    // get model name
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.C.no(confIndex));
                }
                // 2018/06/14 S21_NA#25227 Mod End
            }
        }

        // Check Error
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // Click $ Button
        NWAL1500CommonLogicForPricing.calculationOrderAmount(bizMsg, glblMsg);
        bizMsg.xxBtnFlg_PR.setValue(ZYPConstant.FLG_ON_Y);

        // Check Error
        if ("E".equals(bizMsg.getMessageKind())) {
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            NWAL1500CommonLogicForSaveSubmit.resetStatusBeforCancelProc(bizMsg, glblMsg);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }

        // Add Start 2017/02/08 QC#17257
        if (posnNums.size() > 0) {
            NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/02/03 S21_NA#19808
        }
        // Add End 2017/02/08 QC#17257

        // Save Order
        cancelLineOrder(bizMsg, glblMsg);
    }
    // 2016/02/19 S21_NA#2166 Add End

    // 2016/04/11 S21_NA#3236-3 Del Start
//    private static BigDecimal calculateEachQuantityForLineConfig(String glblCmpyCd, NWAL1500_BCMsg bMsg, int cancelQty) {
//
//        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, bMsg.mdseCd_LL.getValue());
//        if (mdseTMsg == null) {
//            return null;
//        }
//        MDSE_STORE_PKGTMsg storePkg = new MDSE_STORE_PKGTMsg();
//        ZYPEZDItemValueSetter.setValue(storePkg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(storePkg.mdseCd, mdseTMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(storePkg.pkgUomCd, bMsg.custUomCd_LL);
//        storePkg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(storePkg);
//        if (storePkg == null) {
//            return null;
//        }
//        return new BigDecimal(bMsg.ordCustUomQty_LL.getValueInt() * storePkg.inEachQty.getValueInt());
//    }
//
//    private static BigDecimal calculateEachQuantityForRMA(String glblCmpyCd, NWAL1500_DCMsg dMsg, int cancelQty) {
//
//        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, dMsg.mdseCd_RL.getValue());
//        if (mdseTMsg == null) {
//            return null;
//        }
//        MDSE_STORE_PKGTMsg storePkg = new MDSE_STORE_PKGTMsg();
//        ZYPEZDItemValueSetter.setValue(storePkg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(storePkg.mdseCd, mdseTMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(storePkg.pkgUomCd, dMsg.custUomCd_RL);
//        storePkg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(storePkg);
//        if (storePkg == null) {
//            return null;
//        }
//        return new BigDecimal((dMsg.ordCustUomQty_RL.getValueInt() - cancelQty) * storePkg.inEachQty.getValueInt());
//    }
    // 2016/04/11 S21_NA#3236-3 Del End

    private static void cancelLineByConfig(NWAL1500CMsg bizMsg, NWAL1500_BSMsgArray bMsgArray, String dsOrdPosnNum) {

        for (int i = 0; i < bMsgArray.getValidCount(); i++) {
            String configNum = bMsgArray.no(i).dsOrdPosnNum_LL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                // 2016/11/02 S21_NA#5394-3 Add Start
                if (S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, bMsgArray.no(i).ordLineStsCd_LL.getValue())) {
                    continue;
                }
                // 2016/11/02 S21_NA#5394-3 Add End
                ZYPEZDItemValueSetter.setValue(bMsgArray.no(i).ordLineStsDescTxt_LB, bMsgArray.no(i).ordLineStsDescTxt_LL); // 2016/04/19 S21_NA#5394 Add
                ZYPEZDItemValueSetter.setValue(bMsgArray.no(i).ordLineStsDescTxt_LL, bizMsg.varCharConstVal_TB.getValue());
            }
        }
    }

    private static void cancelLineByConfig(NWAL1500CMsg bizMsg, NWAL1500_DSMsgArray dMsgArray, String dsOrdPosnNum) {

        for (int i = 0; i < dMsgArray.getValidCount(); i++) {
            String configNum = dMsgArray.no(i).dsOrdPosnNum_RL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                // 2016/11/02 S21_NA#5394-3 Add Start
                if (S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, dMsgArray.no(i).ordLineStsCd_RL.getValue())) {
                    continue;
                }
                // 2016/11/02 S21_NA#5394-3 Add End
                ZYPEZDItemValueSetter.setValue(dMsgArray.no(i).rtrnLineStsDescTxt_RB, dMsgArray.no(i).rtrnLineStsDescTxt_RL); // 2016/04/19 S21_NA#5394 Add
                ZYPEZDItemValueSetter.setValue(dMsgArray.no(i).rtrnLineStsDescTxt_RL, bizMsg.varCharConstVal_TB.getValue());
            }
        }
    }

    /**
     * <pre>
     * cancel config for registered config
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Message
     * @param aMsg Config Message (Line Config)
     * @return true: error false: normal end
     * </pre>
     */
    // public static boolean  doProcessRegisteredConfig(NWAL1500CMsg bizMsg, NWAL1500_ASMsg aMsg) { // 2018/06/27 S21_NA#26602 Mod
    public static boolean  doProcessRegisteredConfig(NWAL1500CMsg bizMsg, NWAL1500_ASMsg aMsg, NWAL1500SMsg glblMsg) {
        // 2017/10/04 S21_NA#21300 Mod Start
//        if (!isCancelableConfig(glblCmpyCd, bizMsg, aMsg)) {
//            aMsg.xxChkBox_LC.setErrorInfo(1, NWAM0682E);
//            bizMsg.setMessageInfo(NWAM0682E);
//            return true;
//        }
//        return false;
        // 2018/06/27 S21_NA#26602 Mod Start
        // String msgId = isCancelableConfig(bizMsg, aMsg);
        String msgId = isCancelableConfig(bizMsg, aMsg, glblMsg);
        // 2018/06/27 S21_NA#26602 Mod End
        if (!msgId.isEmpty()) {
            // 2019/06/11 QC#50787 Mod Start
            // if (NWAM0783E.equals(msgId)) { // 2018/06/27 S21_NA#26602 Add Condition
            //     aMsg.xxChkBox_LC.setErrorInfo(1, msgId, new String[] {"Config Including Rebill Line of Tangible Item"});
            if (NWAM8460E.equals(msgId)) {
                aMsg.xxChkBox_LC.setErrorInfo(1, msgId, new String[] {"Config Including Rebill Line"});
            // 2019/06/11 QC#50787 Mod End
            } else {
                aMsg.xxChkBox_LC.setErrorInfo(1, msgId);
            }
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        return false;
        // 2017/10/04 S21_NA#21300 Mod Start
    }

    /**
     * <pre>
     * @param glblCmpyCd Global Message
     * @param bizMsg Business Message
     * @param cMsg Config Message (RMA)
     * @return true: error false: normal end
     * </pre>
     */
    public static boolean doProcessRegisteredConfig(NWAL1500CMsg bizMsg, NWAL1500_CSMsg cMsg) {

        if (!isCancelableConfig(bizMsg, cMsg)) {
            cMsg.xxChkBox_RC.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Config Cancel process (Line Config)
     * @param glblCmpyCd Global Message
     * @param bizMsg Business Message
     * @param aMsg Config Message (Line Config)
     * @param selectedRowForConfig select index of config message
     * </pre>
     */
    public static void doProcessRegisteredConfigForAllCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg) {

        // cancel all line
        cancelLineByConfig(bizMsg, glblMsg.B, configMsg.dsOrdPosnNum_LC.getValue());
        NWAL1500CommonLogicForLineConfig.clearModelProperties(configMsg); // 2016/04/11 S21_NA#3236-3 Add Start
    }

    /**
     * <pre>
     * Config Cancel process (RMA)
     * @param glblCmpyCd  glblCmpyCd Global Message
     * @param bizMsg bizMsg Business Message
     * @param cMsg Config Message (RMA)
     * @param selectedRowForConfig select index of config message
     */
    public static void doProcessRegisteredConfigForAllCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg rmaConfigMsg) {

        // cancel line
        cancelLineByConfig(bizMsg, glblMsg.D, rmaConfigMsg.dsOrdPosnNum_RC.getValue());
    }

    /**
     * <pre>
     * process for registered line
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Message
     * @param bMsg Line Message (Line Config)
     * @return true: error return false: normal end
     * </pre>
     */
    public static boolean  doProcessRegisteredLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsg bMsg) {

        if (ZYPCommonFunc.hasValue(bMsg.svcMachMstrPk_LL)) {

            // IB Component
            NWAL1500_ASMsg aMsg = getConfigLineForLineConfig(glblMsg, bMsg.dsOrdPosnNum_LL.getValue());
            // if (S21StringUtil.isEquals(CONFIG_TP.EXISTING,
            // aMsg.configTpCd_LC.getValue())) {
            // Out bound N N Y
            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), aMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, false, true)) { // S21_NA#955

                // Existing Config
                bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
                bizMsg.setMessageInfo(NWAM0682E);
                return true;
            }
        }

        // get order quantity
//        // 2018/06/26 S21_NA#25227 Mod Start
//        Map<String, Object> qtyResult = getLineQuantityForLineConfig(bizMsg, bMsg);
//        if (qtyResult == null) {
        List<Map<String, Object>> qtyResults = getLineQuantityForLineConfig(bizMsg, bMsg, false);
        if (qtyResults == null || qtyResults.isEmpty()) {
        // 2018/06/26 S21_NA#25227 Mod End
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        // 2018/06/26 S21_NA#25227 Add Start
        Map<String, Object> qtyResult = qtyResults.get(0);
        // 2018/06/26 S21_NA#25227 Add End
        // 2017/10/04 S21_NA#21300 Add Start
        String errMsgId = (String) qtyResult.get("MSG_ID");
        if (ZYPCommonFunc.hasValue(errMsgId)) {
            bMsg.xxChkBox_LL.setErrorInfo(1, errMsgId);
            bizMsg.setMessageInfo(errMsgId);
            return true;
        }
        // 2017/10/04 S21_NA#21300 Add End

        // get Uncancelable quantity
        int uncancelableQty = getUncancelQuantity((BigDecimal) qtyResult.get("ORD_QTY_CPO_DTL"), (BigDecimal) qtyResult.get("ORD_QTY_SHPG_PLN"));
        if (!validateCancelQuantity(uncancelableQty, bMsg.ordQty_LL.getValueInt())) {
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }

        // get cancelable quantity.
        BigDecimal cancelableQty = getCancelableQuantity(bizMsg.glblCmpyCd.getValue(), bMsg.mdseCd_LL.getValue(), bMsg.custUomCd_LL.getValue(), uncancelableQty, bMsg.ordQty_LL.getValueInt());
        if (cancelableQty == null) {
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.cancQty_P1, cancelableQty);

        // 2018/05/16 S21_NA#25144 add start
        if (NWAL1500CommonLogic.isRebillTangibleItem(bMsg, bizMsg.glblCmpyCd.getValue())) {
            // 2019/06/03 QC#50555 Mod Start
            // bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0783E, new String[] {"Rebill Line of Tangible Item"});
            // 2019/06/11 QC#50787 Mod Start
            // bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0783E, new String[] {"Rebill Line"});
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM8460E, new String[] {"Rebill Line"});
            // 2019/06/11 QC#50787 Mod End
            // 2019/06/03 QC#50555 Mod End
            return true;
        }
        // 2018/05/16 S21_NA#25144 add end
        return false;

    }

    /**
     * <pre>
     * process for registered line
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Message
     * @param dMsg RMA Line Message
     * @return true: error return false: normal end
     * </pre>
     */
    public static boolean doProcessRegisteredLine(NWAL1500CMsg bizMsg, NWAL1500_DSMsg dMsg) {

        // get order quantity
        Map<String, Object> qtyResult = getLineQuantityForRMA(bizMsg, dMsg);
        if (qtyResult == null) {
            dMsg.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }

        // 2019/06/18 S21_NA#50732 Add Start
        // get Opened RWS
        if (getOpenedRwsForRmaLine(bizMsg, dMsg)) {
            dMsg.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        // 2019/06/18 S21_NA#50732 Add End

        // get Uncancelable quantity
        // 2016/01/28 S21_NA#3254 ADD Mod Start
        // int uncancelableQty = getUncancelQuantity((BigDecimal) qtyResult.get("ORD_QTY"), (BigDecimal) qtyResult.get("RTRN_QTY"));
        int uncancelableQty = ((BigDecimal) qtyResult.get("RTRN_QTY")).intValue();
//        if (!validateCancelQuantity(uncancelableQty, dMsg.ordQty_RL.getValueInt())) { 2016/01/28 S21_NA#3254 ADD Mod
        if (!validateCancelQuantity(uncancelableQty, dMsg.ordQty_RL.getValue().abs().intValue())) {
            dMsg.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }

        // get cancelable quantity.
        BigDecimal cancelableQty = getCancelableQuantity(bizMsg.glblCmpyCd.getValue(), dMsg.mdseCd_RL.getValue(), dMsg.custUomCd_RL.getValue(), uncancelableQty, dMsg.ordQty_RL.getValueInt());
        if (cancelableQty == null) {
            dMsg.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return true;
        }
        // ZYPEZDItemValueSetter.setValue(bizMsg.cancQty_P1, cancelableQty); 2016/01/28 S21_NA#3254 ADD Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.cancQty_P1, cancelableQty.abs());
        return false;
    }

    /**
     * <pre>
     * Line Cancel process
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Message
     * @param bMsg Line Message
     * @param selectedRowForLine Selected Line Index
     * </pre>
     */
    public static void doProcessRegisteredLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsg lineMsg, int selectedRowForLine) { // 2018/02/03 S21_NA#19808

        // get order quantity
        // 2018/06/26 S21_NA#25227 Mod Start
//        Map<String, Object> qtyResult = getLineQuantityForLineConfig(bizMsg, lineMsg);
//        if (qtyResult == null) {
        List<Map<String, Object>> qtyResults = getLineQuantityForLineConfig(bizMsg, lineMsg, false);
        if (qtyResults == null || qtyResults.isEmpty()) {
        // 2018/06/26 S21_NA#25227 Mod End
            glblMsg.B.no(selectedRowForLine).xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return;
        }
        // 2018/06/26 S21_NA#25227 Add Start
        Map<String, Object> qtyResult = qtyResults.get(0);
        // 2018/06/26 S21_NA#25227 Add End
        // 2017/10/04 S21_NA#21300 Add Start
        String errMsgId = (String) qtyResult.get("MSG_ID");
        if (ZYPCommonFunc.hasValue(errMsgId)) {
            glblMsg.B.no(selectedRowForLine).xxChkBox_LL.setErrorInfo(1, errMsgId);
            bizMsg.setMessageInfo(errMsgId);
            return;
        }
        // 2017/10/04 S21_NA#21300 Add End

        // cancelable quantity
        BigDecimal cancelableEachQty = (BigDecimal) qtyResult.get("ORD_QTY_SHPG_PLN");

        // order quantity
        BigDecimal orderQty = (BigDecimal) qtyResult.get("ORD_QTY_CPO_DTL");

        // in each quantity
        BigDecimal inEachQty = getInEachQuantity(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.custUomCd_LL.getValue()); // 2018/02/03 S21_NA#19808
        if (inEachQty == null) {
            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return;
        }

        // cancel request quantity
        BigDecimal cancelReuestEachQty = new BigDecimal(bizMsg.cancQty_P2.getValueInt() * inEachQty.intValue());

        // 2018/06/26 S21_NA#25227 Add Start
        boolean isSetParent = isSetParent(lineMsg.cpoDtlLineSubNum_LL.getValue());
        // Mod Start 2019/03/07 QC#30629
//        List<Map<String, Object>> setQtyResults = new ArrayList<Map<String, Object>>();
//        Map<String, BigDecimal> setCmptMap = new HashMap<String, BigDecimal>();
        List<Map<String, Object>> setPrntResults = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> setChildResults = new ArrayList<Map<String,Object>>();
        // Mod End 2019/03/07 QC#30629

        if (isSetParent) {
            cancelableEachQty = new BigDecimal(-1);
            // Del Start 2019/03/07 QC#30629
//            setQtyResults = getLineQuantityForLineConfig(bizMsg, lineMsg, true);
//            setCmptMap = getSetChildren(lineMsg.mdseCd_LL.getValue(), bizMsg, "getSetChildrenForOutbound");
//
//            Iterator<String> setCmpts = setCmptMap.keySet().iterator();
//            while (setCmpts.hasNext()) {
//                String childMdseCd = setCmpts.next();
//                BigDecimal childQty = setCmptMap.get(childMdseCd);
//                if (!ZYPCommonFunc.hasValue(childQty)) {
//                    childQty = BigDecimal.ZERO;
//                }
//
//                BigDecimal childShpgQty = BigDecimal.ZERO;
//                for (Map<String, Object> setQtyResult : setQtyResults) {
//                    if (S21StringUtil.isEquals(childMdseCd, (String) setQtyResult.get("MDSE_CD"))) {
//                        childShpgQty = (BigDecimal) setQtyResult.get("ORD_QTY_SHPG_PLN");
//                        break;
//                    }
//                }
//
//                BigDecimal cancelableQty = childShpgQty.divide(childQty, BigDecimal.ROUND_DOWN);
//                if (cancelableEachQty.intValue() < 0) {
//                    cancelableEachQty = cancelableQty;
//                } else if (cancelableEachQty.compareTo(cancelableQty) > 0) {
//                    cancelableEachQty = cancelableQty;
//                }
//            }
            // Del End 2019/03/07 QC#30629

            // Add Start 2019/03/07 QC#30629
            setPrntResults = getLineQuantityForSetParent(bizMsg, lineMsg);
            setChildResults = getSetChildrenQty(bizMsg, lineMsg);

            BigDecimal prntQty = (BigDecimal) setPrntResults.get(0).get("ORD_QTY");
            for (Map<String, Object> childResult : setChildResults) {
                BigDecimal childQty = (BigDecimal) childResult.get("ORD_QTY");
                BigDecimal divideQty = childQty.divide(prntQty);

                BigDecimal childShpgQty = (BigDecimal) childResult.get("SHP_QTY");

                BigDecimal cancelableQty = childShpgQty.divide(divideQty, BigDecimal.ROUND_DOWN);
                if (cancelableEachQty.intValue() < 0) {
                    cancelableEachQty = cancelableQty;
                } else if (cancelableEachQty.compareTo(cancelableQty) > 0) {
                    cancelableEachQty = cancelableQty;
                }
            }
            // Add End 2019/03/07 QC#30629
        }
        // 2018/06/26 S21_NA#25227 Add End

        // 2018/06/14 S21_NA#25227 Mod Start
//        if (orderQty.intValue() == cancelableEachQty.intValue() && orderQty.intValue() == cancelReuestEachQty.intValue()) {
        if (orderQty.intValue() == cancelableEachQty.intValue() && orderQty.intValue() <= cancelReuestEachQty.intValue()) {
        // 2018/06/14 S21_NA#25227 Mod End
            // 2018/06/14 S21_NA#25227 Add
            if (NWAL1500CommonLogicForDelete.isReferencedItemDeleting(bizMsg, glblMsg, MSG_PARAM_LINE_REF_ACT_CANC)) {
                bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
                return;
            }
            // 2018/06/14 S21_NA#25227 End

            // all cancel
            String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
            ZYPEZDItemValueSetter.setValue(lineMsg.ordLineStsDescTxt_LB, lineMsg.ordLineStsDescTxt_LL); // 2016/04/19 S21_NA#5394 Add
            ZYPEZDItemValueSetter.setValue(lineMsg.ordLineStsDescTxt_LL, ordLineSts);
            lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N); // 2016/04/11 S21_NA#3236-3 Add

            if (!ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_LL)) {
                // for component
                for (int i = selectedRowForLine + 1; i < glblMsg.B.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) // 
                            && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_LL.getValue(), glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordLineStsDescTxt_LB, glblMsg.B.no(i).ordLineStsDescTxt_LL); // 2016/04/19 S21_NA#5394 Add
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordLineStsDescTxt_LL, ordLineSts);
                        lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N); // 2016/04/11 S21_NA#3236-3 Add
                    } else {
                        break;
                    }
                }
            }
        } else {

            // partially cancel
            // QC#26691 mod
            Integer cancelQty = 0;
            if (cancelableEachQty.intValue() >= cancelReuestEachQty.intValue()) {
                cancelQty = cancelReuestEachQty.intValue();
            } else {
                cancelQty = cancelableEachQty.intValue();
            }
            lineMsg.ordQty_LL.setValue(orderQty.intValue() - cancelQty);

            // 2016/07/06 QC#10909 Mod Start
            //lineMsg.ordCustUomQty_LL.setValue(lineMsg.ordQty_LL.getValue().divide(inEachQty).setScale(0, BigDecimal.ROUND_UP));
            lineMsg.ordCustUomQty_LL.setValue(lineMsg.ordQty_LL.getValue().divide(inEachQty, 0, BigDecimal.ROUND_UP));
            // 2016/07/06 QC#10909 Mod End
            ZYPEZDItemValueSetter.setValue(lineMsg.ordLineStsDescTxt_LB, lineMsg.ordLineStsDescTxt_LL); // 2016/04/19 S21_NA#5394 Add
            lineMsg.ordLineStsDescTxt_LL.setValue(NWAL1500_CANC_PRTL_CANC); // 2016/04/19 S21_NA#5394 Add

            // Cancel for Set Children QC#26691 add
            if(isSetParent){
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg bGlblMsg = glblMsg.B.no(i);
                    if (!lineMsg.dsOrdPosnNum_LL.getValue().equals(bGlblMsg.dsOrdPosnNum_LL.getValue())) {
                        continue;
                    }
                    if (lineMsg.xxLineNum_LL.getValue().equals(bGlblMsg.xxLineNum_LL.getValue())) {
                        continue;
                    }
                    // Del Start 2019/03/08 QC#30629
//                    BigDecimal qty = setCmptMap.get(bGlblMsg.origMdseCd_LL.getValue());
//                    if (!ZYPCommonFunc.hasValue(qty)) {
//                        qty = BigDecimal.ONE;
//                    }

//                    Map<String, Object> rsltMap = null;
//                    for (Map<String, Object> setQtyResult : setQtyResults) {
//                        if (S21StringUtil.isEquals((String) setQtyResult.get("MDSE_CD"), bGlblMsg.origMdseCd_LL.getValue())) {
//                            rsltMap = setQtyResult;
//                            break;
//                        }
//                    }
//                    if (rsltMap == null) {
//                        continue;
//                    }
                    // Del End 2019/03/08 QC#30629

                    // Add Start 2019/03/08 QC#30629
                    String cpoDtlLineNum = bGlblMsg.cpoDtlLineNum_LL.getValue();
                    String cpoDtlLineSubNum = bGlblMsg.cpoDtlLineSubNum_LL.getValue();
                    boolean childExistsFlg = false;
                    BigDecimal prntQty = (BigDecimal) setPrntResults.get(0).get("ORD_QTY");
                    BigDecimal qty = BigDecimal.ONE;
                    for (Map<String, Object> childResult : setChildResults) {
                        String shpgLineNum = (String) childResult.get("CPO_DTL_LINE_NUM");
                        String shpgLineSubNum = (String) childResult.get("CPO_DTL_LINE_SUB_NUM");

                        if(S21StringUtil.isEquals(cpoDtlLineNum, shpgLineNum) && S21StringUtil.isEquals(cpoDtlLineSubNum, shpgLineSubNum)) {
                            BigDecimal childQty = (BigDecimal) childResult.get("ORD_QTY");
                            qty = childQty.divide(prntQty);
                            childExistsFlg = true;
                            break;
                        }
                    }

                    if (!childExistsFlg) {
                        continue;
                    }
                    // Add End 2019/03/08 QC#30629

                    // cancelable quantity
//                    cancelableEachQty = (BigDecimal) rsltMap.get("ORD_QTY_SHPG_PLN");
                    // order quantity
//                    orderQty = (BigDecimal) rsltMap.get("ORD_QTY_CPO_DTL");

                    // in each quantity
                    inEachQty = getInEachQuantity(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.custUomCd_LL.getValue()); // 2018/02/03 S21_NA#19808
                    if (inEachQty == null) {
                        continue;
                    }

                    // cancel request quantity
                    cancelReuestEachQty = new BigDecimal(bizMsg.cancQty_P2.getValueInt() * inEachQty.intValue());
                    if (cancelableEachQty.intValue() >= cancelReuestEachQty.intValue()) {
                        cancelQty = cancelReuestEachQty.intValue();
                    } else {
                        cancelQty = cancelableEachQty.intValue();
                    }

                    Integer childQty = qty.intValue();
                    bGlblMsg.ordQty_LL.setValue(bGlblMsg.ordQty_LL.getValue().intValue() - (cancelQty * childQty));
                    bGlblMsg.ordCustUomQty_LL.setValue(bGlblMsg.ordQty_LL.getValue().divide(inEachQty, 0, BigDecimal.ROUND_UP));
                    ZYPEZDItemValueSetter.setValue(bGlblMsg.ordLineStsDescTxt_LB, bGlblMsg.ordLineStsDescTxt_LL);
                    bGlblMsg.ordLineStsDescTxt_LL.setValue(NWAL1500_CANC_PRTL_CANC);
                }
            }
        }
    }

    // QC#26691 add
    private static Map<String, BigDecimal> getSetChildren(String mdseCd, NWAL1500CMsg bizMsg, String stmtId) {
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("slsDt", bizMsg.slsDt);
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getSetChildren(queryParam, stmtId);
        if (ssmResult.getQueryResultCount() == 0) {
            return new HashMap<String, BigDecimal>(0);
        }
        List<Map<String, Object>> rslt = (List<Map<String, Object>>) ssmResult.getResultObject();

        Map<String, BigDecimal> setChildren = new HashMap<String, BigDecimal>();
        for (Map<String, Object> map : rslt) {
            if (ZYPCommonFunc.hasValue((String) map.get("CHILD_ORD_TAKE_MDSE_CD"))) {
                setChildren.put((String) map.get("CHILD_ORD_TAKE_MDSE_CD"), (BigDecimal) map.get("CHILD_MDSE_QTY"));
            }
            if (ZYPCommonFunc.hasValue((String) map.get("CHILD_MDSE_CD"))) {
                setChildren.put((String) map.get("CHILD_MDSE_CD"), (BigDecimal) map.get("CHILD_MDSE_QTY"));
            }
        }
        return setChildren;
    }

    // QC#26691 add
    private static boolean isSetParent(String cpoDtlLineSubNum) {
        return CPO_DTL_LINE_SUB_NUM_SET_PRNT.equals(cpoDtlLineSubNum);
    }

    /**
     * <pre>
     * Line Cancel process (RMA)
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Message
     * @param dMsg RMA Line Message
     * @param selectedRowForLine Selected Line Index
     * </pre>
     */
    public static void doProcessRegisteredLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg rmaLineMsg, int selectedRowForLine) { // 2018/02/03 S21_NA#19808

        // get order quantity
        Map<String, Object> qtyResult = getLineQuantityForRMA(bizMsg, rmaLineMsg); // 2018/02/03 S21_NA#19808
        if (qtyResult == null) {
            glblMsg.D.no(selectedRowForLine).xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return;
        }

        // order quantity
        BigDecimal orderQty = (BigDecimal) qtyResult.get("ORD_QTY");

        // cancelable quantity
        BigDecimal cancelableEachQty = orderQty.subtract((BigDecimal) qtyResult.get("RTRN_QTY"));

        // in each quantity
        BigDecimal inEachQty = getInEachQuantity(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue(), rmaLineMsg.custUomCd_RL.getValue()); // 2018/02/03 S21_NA#19808
        if (inEachQty == null) {
            glblMsg.D.no(selectedRowForLine).xxChkBox_RL.setErrorInfo(1, NWAM0682E);
            bizMsg.setMessageInfo(NWAM0682E);
            return;
        }

        // cancel request quantity
        BigDecimal cancelReuestEachQty = new BigDecimal(bizMsg.cancQty_P2.getValueInt() * inEachQty.intValue());

        // 2016/01/28 S21_NA#3254 Add Start
        boolean isEql1 = orderQty.abs().intValue() == cancelableEachQty.abs().intValue();
        // 2018/06/14 S21_NA#25227 Mod Start
//        boolean isDql2 = orderQty.abs().intValue() == cancelReuestEachQty.abs().intValue();
        boolean isDql2 = orderQty.abs().intValue() <= cancelReuestEachQty.abs().intValue();
        // 2018/06/14 S21_NA#25227 Mod End
        // 2016/01/28 S21_NA#3254 Add End
        // if (orderQty.intValue() == cancelableEachQty.intValue() && orderQty.intValue() == cancelReuestEachQty.intValue()) { 2016/01/28 S21_NA#3254 Mod
        if (isEql1 && isDql2) {
            // 2018/06/14 S21_NA#25227 Add
            if (NWAL1500CommonLogicForDelete.isReferencedItemDeleting(bizMsg, glblMsg, MSG_PARAM_LINE_REF_ACT_CANC)) {
                bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
                return;
            }
            // 2018/06/14 S21_NA#25227 End

            // all cancel

            String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnLineStsDescTxt_RB, rmaLineMsg.rtrnLineStsDescTxt_RL);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnLineStsDescTxt_RL, ordLineSts);

            if (!ZYPCommonFunc.hasValue(rmaLineMsg.dsCpoLineSubNum_RL)) {
                // for component
                for (int i = selectedRowForLine + 1; i < glblMsg.D.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(rmaLineMsg.dsOrdPosnNum_RL.getValue(), glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) // 
                            && NWAL1500CommonLogic.compareBigDecimal(rmaLineMsg.dsCpoLineNum_RL.getValue(), glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RB, glblMsg.D.no(i).rtrnLineStsDescTxt_RL); // 2016/04/19 S21_NA#5394 Add Start
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL, ordLineSts);
                    } else {
                        break;
                    }
                }
            }

        } else {

            // partially cancel
            // 2016/01/28 S21_NA#3254 Mod Start
//            if (cancelableEachQty.intValue() <= cancelReuestEachQty.intValue()) {
//                rmaLineMsg.ordQty_RL.setValue(orderQty.intValue() - cancelReuestEachQty.intValue());
//            } else {
//                rmaLineMsg.ordQty_RL.setValue(orderQty.intValue() - cancelableEachQty.intValue());
//            }
            // if (cancelableEachQty.abs().intValue() <= cancelReuestEachQty.abs().intValue()) {2016/04/19 S21_NA#5394 Mod Condition
            BigDecimal newOrdQty = BigDecimal.ZERO;
            BigDecimal cancelQty = BigDecimal.ZERO;
            if (cancelableEachQty.abs().intValue() >= cancelReuestEachQty.abs().intValue()) {
                cancelQty = cancelReuestEachQty.abs();
                newOrdQty = orderQty.abs().subtract(cancelReuestEachQty.abs());
            } else {
                cancelQty = cancelableEachQty.abs();
                newOrdQty = orderQty.abs().subtract(cancelableEachQty.abs());
            }
            rmaLineMsg.ordQty_RL.setValue(BigDecimal.ZERO.subtract(newOrdQty));

            // 2016/01/28 S21_NA#3254 Mod End
            // 2016/07/06 QC#10909 Mod Start
            //rmaLineMsg.ordCustUomQty_RL.setValue(rmaLineMsg.ordQty_RL.getValue().divide(inEachQty).setScale(0, BigDecimal.ROUND_UP));
            rmaLineMsg.ordCustUomQty_RL.setValue(rmaLineMsg.ordQty_RL.getValue().divide(inEachQty, 0, BigDecimal.ROUND_UP));
            // 2016/07/06 QC#10909 Mod End
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnLineStsDescTxt_RB, rmaLineMsg.rtrnLineStsDescTxt_RL);  // 2016/04/19 S21_NA#5394 Add
            rmaLineMsg.rtrnLineStsDescTxt_RL.setValue(NWAL1500_CANC_PRTL_CANC); // 2016/04/19 S21_NA#5394 Add

            // Cancel for Set Children QC#26691 add
            if(isSetParent(rmaLineMsg.cpoDtlLineSubNum_RL.getValue())){
                Map<String, BigDecimal> setChildrenMap = getSetChildren(rmaLineMsg.mdseCd_RL.getValue(), bizMsg, "getSetChildrenForInbound");
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg dGlblMsg = glblMsg.D.no(i);

                    if (!rmaLineMsg.dsOrdPosnNum_RL.getValue().equals(dGlblMsg.dsOrdPosnNum_RL.getValue())) {
                        continue;
                    }
                    if (rmaLineMsg.xxLineNum_RL.getValue().equals(dGlblMsg.xxLineNum_RL.getValue())) {
                        continue;
                    }

                    Map<String, Object> rsltMap = getLineQuantityForRMA(bizMsg, dGlblMsg);
                    if (rsltMap == null) {
                        continue;
                    }
                    // order quantity
                    orderQty = (BigDecimal) rsltMap.get("ORD_QTY");
                    // cancelable quantity
                    cancelableEachQty = orderQty.subtract((BigDecimal) rsltMap.get("RTRN_QTY"));

                    BigDecimal childQty = setChildrenMap.get(dGlblMsg.mdseCd_RL.getValue());
                    if (!ZYPCommonFunc.hasValue(childQty)) {
                        childQty = BigDecimal.ONE;
                    }
                    if (cancelableEachQty.abs().intValue() > cancelReuestEachQty.abs().intValue()) {
                        cancelQty = cancelReuestEachQty.abs();
                        dGlblMsg.rtrnLineStsDescTxt_RL.setValue(NWAL1500_CANC_PRTL_CANC);
                    } else {
                        cancelQty = cancelableEachQty.abs();
                        dGlblMsg.rtrnLineStsDescTxt_RL.setValue(LINE_STS_NM_CANCELLED);
                    }
                    dGlblMsg.ordQty_RL.setValue(dGlblMsg.ordQty_RL.getValue().abs().subtract(cancelQty.multiply(childQty)).negate());
                    dGlblMsg.ordCustUomQty_RL.setValue(dGlblMsg.ordQty_RL.getValue().divide(inEachQty, 0, BigDecimal.ROUND_UP));
                    ZYPEZDItemValueSetter.setValue(dGlblMsg.rtrnLineStsDescTxt_RB, dGlblMsg.rtrnLineStsDescTxt_RL);
                }
            }
        }
    }

//    /**
//     * @param glblCmpyCd
//     * @param bizMsg
//     * @param msg
//     */
//    public static void doProcessUnRegisteredConfig(String glblCmpyCd, NWAL1500CMsg bizMsg, NWAL1500_ACMsg msg) {
//        // do nothing
//    }
//
//    /**
//     * @param globalCompanyCode
//     * @param bizMsg
//     * @param msg
//     */
//    public static void doProcessUnRegisteredConfig(String globalCompanyCode, NWAL1500CMsg bizMsg, NWAL1500_CCMsg msg) {
//        // do nothing
//    }

    /**
     * <pre>
     * cancel for unregistered config
     * @param bizMsg Bueinss Message
     * @param glblMsg Global Company Code
     * @param dsOrdPosnNum DS Order Position Number
     * @param selectedRowForConfig index of selected config
     * </pre>
     */
    public static void doProcessUnRegisteredConfigForAllCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, int selectedRowForConfig) {

        List<NWAL1500_BSMsg> lineList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.B, dsOrdPosnNum);

        List<String> deleteCpoLineNumList = new ArrayList<String>();
        if (lineList.size() > 0) {
            for (NWAL1500_BSMsg line : lineList) {
                if (ZYPCommonFunc.hasValue(line.cpoDtlLineNum_LL)) {
                    if (deleteCpoLineNumList.contains(line.cpoDtlLineNum_LL.getValue())) {
                        deleteCpoLineNumList.add(line.cpoDtlLineNum_LL.getValue());
                    }
                }
            }
        }
        Collections.sort(deleteCpoLineNumList);
        Collections.reverse(deleteCpoLineNumList);

        // remove line
        removeLine(glblMsg.B, dsOrdPosnNum);

        // renumbering config#
        renumberingConfig(glblMsg.A, glblMsg.B, selectedRowForConfig + 1);

        // remove sales credit
        removeSalesCreditForLineConfig(bizMsg.G, dsOrdPosnNum);

        // remove order pricing calc base
        removeOrderPricing(glblMsg.I, glblMsg.B, dsOrdPosnNum);
        for (String cpoDtlLineNum : deleteCpoLineNumList) {
            renumberingCPOLineNum(glblMsg.B, glblMsg.I, cpoDtlLineNum, cpoDtlLineNum);
        }

        // remove config
        List<Integer> deleteRows = new ArrayList<Integer>();
        deleteRows.add(selectedRowForConfig);
        ZYPTableUtil.deleteRows(glblMsg.A, deleteRows);

        // prepare screen
//        bizMsg.B.clear();
//        bizMsg.B.setValidCount(0);
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            NWAL1500_ACMsg config = bizMsg.A.no(i);
//            if (!S21StringUtil.isEquals(config.xxSmryLineFlg_L.getValue(), ZYPConstant.FLG_ON_Y)) {
//                NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.B, glblMsg.J, config.dsOrdPosnNum_LC.getValue());
//            }
//        }
    }

    /**
     * <pre>
     * cancel for unregistered config
     * @param bizMsg bizMsg Bueinss Message
     * @param glblMsg glblMsg Global Company Code
     * @param configList config message list
     * @param lineList line message list
     * </pre>
     */
    public static void doProcessUnRegisteredForAllCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWAL1500_ASMsg> configList, List<NWAL1500_BSMsg> lineList) {
        List<Integer> deleteConfigRows = new ArrayList<Integer>();
        List<Integer> deleteLineRows = new ArrayList<Integer>();
        List<String> deleteCpoLineNumList = new ArrayList<String>();
        List<String> deletePosnNumList = new ArrayList<String>();
        List<String> remainConfigPosnNumList = new ArrayList<String>();

        for (NWAL1500_ASMsg config : configList) {
            int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.A, config.dsOrdPosnNum_LC.getValue());
            deleteConfigRows.add(slctConfIndex);
            deleteLineRows.addAll(NWAL1500CommonLogicForLineControl.getLineListIndex(glblMsg.B, config.dsOrdPosnNum_LC.getValue()));
            deletePosnNumList.add(config.dsOrdPosnNum_LC.getValue());
        }

        for (NWAL1500_BSMsg line : lineList) {
            String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
            BigDecimal lineNum = line.dsCpoLineNum_LL.getValue();
            List<Integer> deleteLine = getRemoveLine(glblMsg.B, dsOrdPosnNum, lineNum);
            deleteLineRows.addAll(deleteLine);

            if (!remainConfigPosnNumList.contains(dsOrdPosnNum)) {
                remainConfigPosnNumList.add(dsOrdPosnNum);
            }
        }
//        for(String dsOrdPosnNum : remainConfigPosnNumList){
//            NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, dsOrdPosnNum);
//        }

        for (Integer i : deleteLineRows) {
            NWAL1500_BSMsg line = glblMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(line.cpoDtlLineNum_LL)) {
                if (!deleteCpoLineNumList.contains(line.cpoDtlLineNum_LL.getValue())) {
                    deleteCpoLineNumList.add(line.cpoDtlLineNum_LL.getValue());
                }
            }
        }

        Collections.reverse(deleteCpoLineNumList);
        // remove order pricing calc base
        for (Integer i : deleteLineRows) {
            NWAL1500_BSMsg line = glblMsg.B.no(i);
            removeOrderPricing(glblMsg.I, line);
        }
        for (String cpoDtlLineNum : deleteCpoLineNumList) {
            renumberingCPOLineNum(glblMsg.B, glblMsg.I, cpoDtlLineNum, cpoDtlLineNum);
        }

        // remove line
        ZYPTableUtil.deleteRows(glblMsg.B, deleteLineRows);

        //remove config
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configLine = glblMsg.A.no(i);
            if (deleteConfigRows.contains(i)) {
                continue;
            }
            List<NWAL1500_BSMsg> lineCountList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.B, configLine.dsOrdPosnNum_LC.getValue());
            if (lineCountList.size() == 0) {
                deleteConfigRows.add(i);
                deletePosnNumList.add(configLine.dsOrdPosnNum_LC.getValue());
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.A, deleteConfigRows);

        //remove Sales Credit
        List<Integer> deleteSlsCredit = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            String configNum = bizMsg.G.no(i).dsOrdPosnNum_GS.getValue();
            if (deletePosnNumList.contains(configNum)) {
                deleteSlsCredit.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.G, deleteSlsCredit);

        // renumbering config#
        renumberingConfig(glblMsg.A, glblMsg.B, bizMsg.G);

//        // prepare screen
//        bizMsg.B.clear();
//        bizMsg.B.setValidCount(0);
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            NWAL1500_ACMsg config = bizMsg.A.no(i);
//            if (!S21StringUtil.isEquals(config.xxSmryLineFlg_L.getValue(), ZYPConstant.FLG_ON_Y)) {
//                NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.B, glblMsg.J, config.dsOrdPosnNum_LC.getValue());
//            }
//        }

        //Base ConponentFlag / Line Reference / Model
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            String configTp = glblMsg.A.no(i).configTpCd_LC.getValue();
            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
                // 2016/07/11 S21_NA#7821 Add Start
//                for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
//                    if (S21StringUtil.isEquals(bizMsg.B.no(j).dsOrdPosnNum_LL.getValue(), bizMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
//                        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, i, j);
//                    }
//                }
                NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(i));
                // 2016/07/11 S21_NA#7821 Add End
            }
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, i); // 2018/02/03 S21_NA#19808
            // Model
            glblMsg.A.no(i).mdlId_LC.clear();
            glblMsg.A.no(i).mdlDescTxt_LC.clear();
            // Add 2016/03/07 S21_NA#5000#78 Start
            glblMsg.A.no(i).mdlNm_LC.clear();
            glblMsg.A.no(i).mdlGrpDescTxt_LC.clear();
            glblMsg.A.no(i).svcSegDescTxt_LC.clear();
            glblMsg.A.no(i).svcIstlReqFlg_LC.clear();
            glblMsg.A.no(i).siteSrvyReqFlg_LC.clear();
            // Add 2016/03/07 S21_NA#5000#78 End
            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(i)); // 2018/02/03 S21_NA#19808
        }

    }
    /**
     * <pre>
     * cancel for unregistered config (RMA)
     * @param bizMsg bizMsg bizMsg Bueinss Message
     * @param glblMsg glblMsg glblMsg Global Company Code
     * @param dsOrdPosnNum DS Order Position Number
     * @param selectedRowForConfig index of selected config
     * </pre>
     */
    public static void doProcessUnRegisteredConfigForAllCancelRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, int selectedRowForConfig) {

        List<NWAL1500_DSMsg> lineList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.D, dsOrdPosnNum);

        List<String> deleteCpoLineNumList = new ArrayList<String>();
        if (lineList.size() > 0) {
            for (NWAL1500_DSMsg line : lineList) {
                if (ZYPCommonFunc.hasValue(line.cpoDtlLineNum_RL)) {
                    if (!deleteCpoLineNumList.contains(line.cpoDtlLineNum_RL.getValue())) {
                        deleteCpoLineNumList.add(line.cpoDtlLineNum_RL.getValue());
                    }
                }
            }
        }
        Collections.sort(deleteCpoLineNumList);
        Collections.reverse(deleteCpoLineNumList);

        // remove line
        removeLine(glblMsg.D, dsOrdPosnNum);

        // renumbering config#
        renumberingConfig(glblMsg.C, glblMsg.D, selectedRowForConfig + 1);

        // remove sales credit
        removeSalesCreditForRMA(bizMsg.H, dsOrdPosnNum);

        // remove order pricing calc base
        removeOrderPricing(glblMsg.I, glblMsg.D, dsOrdPosnNum);

        for (String cpoDtlLineNum : deleteCpoLineNumList) {
            renumberingCPOLineNum(glblMsg.D, glblMsg.I, cpoDtlLineNum, cpoDtlLineNum);
        }

        // remove config
        List<Integer> deleteRows = new ArrayList<Integer>();
        deleteRows.add(selectedRowForConfig);
        ZYPTableUtil.deleteRows(glblMsg.C, deleteRows);

//        // prepare screen
//        bizMsg.D.clear();
//        bizMsg.D.setValidCount(0);
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            NWAL1500_CCMsg config = bizMsg.C.no(i);
//            if (!S21StringUtil.isEquals(config.xxSmryLineFlg_R.getValue(), ZYPConstant.FLG_ON_Y)) {
//                NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.D, glblMsg.K, config.dsOrdPosnNum_RC.getValue());
//            }
//        }
    }
    /**
     * doProcessUnRegisteredForAllCancelRma
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param configList List<NWAL1500_CSMsg>
     * @param lineList List<NWAL1500_DSMsg>
     */
    public static void doProcessUnRegisteredForAllCancelRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWAL1500_CSMsg> configList, List<NWAL1500_DSMsg> lineList) {
        List<Integer> deleteConfigRows = new ArrayList<Integer>();
        List<Integer> deleteLineRows = new ArrayList<Integer>();
        List<String> deleteCpoLineNumList = new ArrayList<String>();
        List<String> deletePosnNumList = new ArrayList<String>();
        List<String> remainConfigPosnNumList = new ArrayList<String>();

        for (NWAL1500_CSMsg config : configList) {
            int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.C, config.dsOrdPosnNum_RC.getValue());
            deleteConfigRows.add(slctConfIndex);
            deleteLineRows.addAll(NWAL1500CommonLogicForLineControl.getLineListIndex(glblMsg.D, config.dsOrdPosnNum_RC.getValue()));
            deletePosnNumList.add(config.dsOrdPosnNum_RC.getValue());
        }

        for (NWAL1500_DSMsg line : lineList) {
            String dsOrdPosnNum = line.dsOrdPosnNum_RL.getValue();
            BigDecimal lineNum = line.dsCpoLineNum_RL.getValue();
            BigDecimal lineSubNum = line.dsCpoLineSubNum_RL.getValue();
            List<Integer> deleteLine = getRemoveLine(glblMsg.D, dsOrdPosnNum, lineNum, lineSubNum);
            deleteLineRows.addAll(deleteLine);

            remainConfigPosnNumList.add(dsOrdPosnNum);
        }

        for (Integer i : deleteLineRows) {
            NWAL1500_DSMsg line = glblMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(line.cpoDtlLineNum_RL)) {
                if (deleteCpoLineNumList.contains(line.cpoDtlLineNum_RL.getValue())) {
                    deleteCpoLineNumList.add(line.cpoDtlLineNum_RL.getValue());
                }
            }
        }

        Collections.reverse(deleteCpoLineNumList);
        // remove order pricing calc base
        for (Integer i : deleteLineRows) {
            NWAL1500_DSMsg line = glblMsg.D.no(i);
            removeOrderPricing(glblMsg.I, line);
        }
        for (String cpoDtlLineNum : deleteCpoLineNumList) {
            renumberingCPOLineNum(glblMsg.D, glblMsg.I, cpoDtlLineNum, cpoDtlLineNum);
        }
        // remove line
        ZYPTableUtil.deleteRows(glblMsg.D, deleteLineRows);

        //remove config
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configLine = glblMsg.C.no(i);
            if (deleteConfigRows.contains(i)) {
                continue;
            }
            List<NWAL1500_DSMsg> lineCountList = NWAL1500CommonLogicForLineControl.getLineList(glblMsg.D, configLine.dsOrdPosnNum_RC.getValue());
            if (lineCountList.size() == 0) {
                deleteConfigRows.add(i);
                deletePosnNumList.add(configLine.dsOrdPosnNum_RC.getValue());
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.C, deleteConfigRows);

        //remove Sales Credit
        List<Integer> deleteSlsCredit = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            String configNum = bizMsg.H.no(i).dsOrdPosnNum_HS.getValue();
            if (deletePosnNumList.contains(configNum)) {
                deleteSlsCredit.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.H, deleteSlsCredit);

        // renumbering config#
        renumberingConfig(glblMsg.C, glblMsg.D, bizMsg.H);

//        // prepare screen
//        bizMsg.D.clear();
//        bizMsg.D.setValidCount(0);
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            NWAL1500_CCMsg config = bizMsg.C.no(i);
//            if (!S21StringUtil.isEquals(config.xxSmryLineFlg_R.getValue(), ZYPConstant.FLG_ON_Y)) {
//                NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.D, glblMsg.K, config.dsOrdPosnNum_RC.getValue());
//            }
//        }

    }

//    /**
//     * @param glblCmpyCd
//     * @param bizMsg
//     * @param dMsg
//     */
//    public static void doProcessUnRegisteredLine(String glblCmpyCd, NWAL1500CMsg bizMsg, NWAL1500_DCMsg dMsg) {
//        // do nothing
//    }

//    /**
//     * @param glblCmpyCd
//     * @param bizMsg
//     * @param line
//     * @param selectedRowForLine
//     */
//    public static void doProcessUnRegisteredLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BCMsg line) {
//
//        String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
//        BigDecimal lineNum = line.dsCpoLineNum_LL.getValue();
//        String cpoLineNum = line.cpoDtlLineNum_LL.getValue();
//
//        // all cancel include sub line
//        List<Integer> deleteLine = getRemoveLine(bizMsg.B, dsOrdPosnNum, lineNum);
//
//        if (deleteLine.size() == 0) {
//            return;
//        }
//
////        NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, dsOrdPosnNum); // S21_NA#1670
//
//        // remove order pricing calc base
//        removeOrderPricing(glblMsg.I, line);
//
//        // renumbering line number
//        renumberingLineNum(bizMsg.B, dsOrdPosnNum, lineNum, deleteLine.get(deleteLine.size() - 1));
//
//        // remove line
//        ZYPTableUtil.deleteRows(bizMsg.B, deleteLine);
//
//        if (S21StringUtil.isNotEmpty(cpoLineNum)) {
//            // renumbering CPO line number
//            renumberingCPOLineNum(bizMsg.B, glblMsg.I, cpoLineNum, cpoLineNum);
//        }
//
//        // reset base component
//        // if
//        // (ZYPConstant.FLG_ON_Y.equals(line.baseCmptFlg_LL.getValue()))
//        // {
//        // if
//        // (!resetBaseComponentForLineConfig(bizMsg.glblCmpyCd.getValue(),
//        // glblMsg.J, dsOrdPosnNum, lineNum)) {
//        // line.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
//        // bizMsg.setMessageInfo(NWAM0682E);
//        // return;
//        // }
//        // }
//        //
//        // // reset model
//        // if (isRetailEquipmentOrder(bizMsg)) {
//        // resetModelForLineConfig(bizMsg, glblMsg.J, dsOrdPosnNum,
//        // lineNum);
//        // }
//
//        // prepare to CMsg
////        NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.B, glblMsg.J, dsOrdPosnNum);
//
//        int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(bizMsg.A, dsOrdPosnNum);
//        String configTp = bizMsg.A.no(slctConfIndex).configTpCd_LC.getValue();
//        // if (!CONFIG_TP.EXISTING.equals(configTp) &&
//        // !CONFIG_TP.ADD_TO_CONFIG.equals(configTp)) {
//        // Out bound Y N N
//        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
//            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                if (S21StringUtil.isEquals(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
//                    NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, i);
//                }
//            }
//        }
//        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, slctConfIndex);
//        // Model
//        bizMsg.A.no(slctConfIndex).mdlId_LC.clear();
//        bizMsg.A.no(slctConfIndex).mdlDescTxt_LC.clear();
//        // Add 2016/03/07 S21_NA#5000#78 Start
//        bizMsg.A.no(slctConfIndex).mdlNm_LC.clear();
//        bizMsg.A.no(slctConfIndex).mdlGrpDescTxt_LC.clear();
//        bizMsg.A.no(slctConfIndex).svcSegDescTxt_LC.clear();
//        bizMsg.A.no(slctConfIndex).svcIstlReqFlg_LC.clear();
//        bizMsg.A.no(slctConfIndex).siteSrvyReqFlg_LC.clear();
//        // Add 2016/03/07 S21_NA#5000#78 End
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
//
////        NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, dsOrdPosnNum);
//    }

//    /**
//     * @param glblCmpyCd
//     * @param bizMsg
//     * @param line
//     * @param selectedRowForLine
//     */
//    public static void doProcessUnRegisteredLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DCMsg line) {
//
//        // all cancel
//
//        String dsOrdPosnNum = line.dsOrdPosnNum_RL.getValue();
//        BigDecimal lineNum = line.dsCpoLineNum_RL.getValue();
//        BigDecimal lineSubNum = null;
//        String cpoLineNum = line.cpoDtlLineNum_RL.getValue();
//        if (ZYPCommonFunc.hasValue(line.mdseCd_RL)) {
//
//            final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), line.mdseCd_RL.getValue());
//            if (mdseTMsg == null) {
//                line.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
//                bizMsg.setMessageInfo(NWAM0682E);
//                return;
//            }
//            if (!MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue())) {
//                if (isSetComponent(bizMsg.D, dsOrdPosnNum, lineNum)) {
//                    lineSubNum = line.dsCpoLineNum_RL.getValue();
//                }
//            }
//        }
////        NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.K, bizMsg.D, dsOrdPosnNum); // S21_NA#1670
//
//        List<Integer> deleteLine = getRemoveLine(bizMsg.D, dsOrdPosnNum, lineNum, lineSubNum);
//
//        if (deleteLine.size() == 0) {
//            return;
//        }
//
//        // remove order pricing calc base
//        removeOrderPricing(glblMsg.I, line);
//
//        // renumbering line number
//        renumberingLineNum(bizMsg.D, dsOrdPosnNum, lineNum, lineSubNum, deleteLine.get(0));
//
//        // remove line
//        ZYPTableUtil.deleteRows(bizMsg.D, deleteLine);
//
//        if (S21StringUtil.isNotEmpty(cpoLineNum)) {
//            // renumbering CPO line number
//            renumberingCPOLineNum(bizMsg.D, glblMsg.I, cpoLineNum, cpoLineNum);
//        }
//
////        // prepare to CMsg
////        NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.D, glblMsg.K, dsOrdPosnNum);
//    }

    /**
     * return pop up
     */

    private static BigDecimal getCancelableQuantity(String glblCmpyCd, String mdseCd, String pkgUomCd, int uncancelableQty, int eachQty) {

        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return BigDecimal.ZERO;
        }
        MDSE_STORE_PKGTMsg storePkg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(storePkg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(storePkg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(storePkg.pkgUomCd, pkgUomCd);
        storePkg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(storePkg);
        if (storePkg == null) {
            return null;
        }
        // 2016/07/06 QC#10909 Mod Start
        // return (new BigDecimal(eachQty - uncancelableQty)).divide(storePkg.inEachQty.getValue()).setScale(0, BigDecimal.ROUND_UP);
        return (new BigDecimal(eachQty - uncancelableQty)).divide(storePkg.inEachQty.getValue(), 0, BigDecimal.ROUND_UP);
        // 2016/07/06 QC#10909 Mod End
    }

    private static NWAL1500_ASMsg getConfigLineForLineConfig(NWAL1500SMsg glblMsg, String configNum) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg lineMsg = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LC.getValue(), configNum)) {
                return lineMsg;
            }
        }
        return null;
    }

    private static NWAL1500_CSMsg getConfigLineForRMA(NWAL1500SMsg glblMsg, String configNum) {
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg rmaLineMsg = glblMsg.C.no(i);
            if (S21StringUtil.isEquals(rmaLineMsg.dsOrdPosnNum_RC.getValue(), configNum)) {
                return rmaLineMsg;
            }
        }
        return null;
    }

    private static BigDecimal getInEachQuantity(String glblCmpyCd, String mdseCd, String pkgUomCd) {

        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return null;
        }
        MDSE_STORE_PKGTMsg storePkg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(storePkg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(storePkg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(storePkg.pkgUomCd, pkgUomCd);
        storePkg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(storePkg);
        if (storePkg == null) {
            return null;
        }
        return storePkg.inEachQty.getValue();
    }

    // 2018/06/26 S21_NA#25227 Mod Start
//    private static Map<String, Object> getLineQuantityForLineConfig(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) {
    private static List<Map<String, Object>> getLineQuantityForLineConfig(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, boolean isGetSetCmpt) {
    // 2018/06/26 S21_NA#25227 Mod End

        // 2018/06/26 S21_NA#25227 Add Start
        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
        // 2018/06/26 S21_NA#25227 Add End

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", lineMsg.dsOrdPosnNum_LL.getValue());
        queryParam.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum_LL.getValue());
        queryParam.put("cpoDtlLineSubNum", lineMsg.cpoDtlLineSubNum_LL.getValue());
        // Mod Start 2019/03/11 QC#30629
//        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED };
        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.S_OR_O_CANCELLED };
        // Mod End 2019/03/11 QC#30629
        queryParam.put("shpgStsCdList", shpgStsCdList);
        // 2017/10/04 S21_NA#21300 Add Start
        List<String> ittAvalCancStsList = NWAL1500CommonLogic.getAvalCancStsOfItt(bizMsg.glblCmpyCd.getValue());
        queryParam.put("ittAvalCancStsList", ittAvalCancStsList);

        List<String> ittAvalCancOrdLineSrcList = NWAL1500CommonLogic.getAvalCancOrdLineSrcCdOfItt(bizMsg.glblCmpyCd.getValue());
        queryParam.put("ittAvalCancOrdLineSrcList", ittAvalCancOrdLineSrcList);
        // 2017/10/04 S21_NA#21300 Add End
        // 2018/06/26 S21_NA#25227 Add Start
        queryParam.put("isGetSetCmpt", isGetSetCmpt ? ZYPConstant.FLG_ON_Y : ZYPConstant.FLG_OFF_N);
        // 2018/06/26 S21_NA#25227 Add End
        // 2019/11/27 QC#52339 Add Start 
        String[] shpgStsCdIntangibleList = {SHPG_STS.INVOICED };
        queryParam.put("shpgStsCdIntangibleList", shpgStsCdIntangibleList);
        queryParam.put("invtyCtrlFlgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("invtyCtrlFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("mdseTpCdSet", MDSE_TP.SET);
        // 2019/11/27 QC#52339 Add End
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getLineQuantityForLineConfig(queryParam);
        if (ssmResult.getQueryResultCount() == 0) {
            // 2017/10/04 S21_NA#21300 Add Start
            shpgStsCdList = null;
            // Mod Start 2019/03/11 QC#30629
//            shpgStsCdList = new String[3];
            shpgStsCdList = new String[5];
            // Mod End 2019/03/11 QC#30629
            shpgStsCdList[0] = SHPG_STS.VALIDATED;
            shpgStsCdList[1] = SHPG_STS.P_OR_O_CANCELLED;
            shpgStsCdList[2] = SHPG_STS.P_OR_O_PRINTED;
            // Add Start 2019/03/08 QC#30629
            shpgStsCdList[3] = SHPG_STS.S_OR_O_CANCELLED;
            shpgStsCdList[4] = SHPG_STS.S_OR_O_PRINTED;
            // Add End 2019/03/08 QC#30629
            queryParam.put("shpgStsCdList", shpgStsCdList);
            queryParam.put("shpgStsPoPrinted", SHPG_STS.P_OR_O_PRINTED);
            // Add Start 2019/03/11 QC#30629
            String[] shpgStsPrintedCdList = new String[2];
            shpgStsPrintedCdList[0] = SHPG_STS.S_OR_O_PRINTED;
            shpgStsPrintedCdList[1] = SHPG_STS.P_OR_O_PRINTED;
            queryParam.put("shpgStsPrintedCdList", shpgStsPrintedCdList);
            // Add End 2019/03/11 QC#30629
            ssmResult = NWAL1500QueryForLineCancel.getInstance().getUncancelableOrderForLineConfigLine(queryParam);
            if (ssmResult.getQueryResultCount() > 0) {
                List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmResult.getResultObject();
                String errSts = "";
                String errPo = "";
                for (Map<String, String> rsltMap : rsltMapList) {
                    String tpCd = rsltMap.get("TP_CD");
                    if (S21StringUtil.isEquals("1", tpCd)) {
                        errSts = NWAM0682E;
                    }
                    if (S21StringUtil.isEquals("2", tpCd)) {
                        errPo = NWAM0947E;
                    }
                    if (S21StringUtil.isEquals("3", tpCd)) {
                        errPo = NWAM0947E;
                    }
                }
                Map<String, Object> rsltMap = new HashMap<String, Object>();
                if (!S21StringUtil.isEmpty(errSts)) {
                    rsltMap.put("MSG_ID", errSts);
                    // 2018/06/26 S21_NA#25227 Mod Start
//                    return rsltMap;
                    ret.add(rsltMap);
                    return ret;
                    // 2018/06/26 S21_NA#25227 Mod End
                } else if (!S21StringUtil.isEmpty(errPo)) {
                    rsltMap.put("MSG_ID", errPo);
                    // 2018/06/26 S21_NA#25227 Mod Start
//                    return rsltMap;
                    ret.add(rsltMap);
                    return ret;
                    // 2018/06/26 S21_NA#25227 Mod End
                } else {
                    return null;
                }
            }
            Map<String, Object> rsltMap = new HashMap<String, Object>();
            rsltMap.put("MSG_ID", NWAM0682E);
            // 2018/06/26 S21_NA#25227 Mod Start
//            return rsltMap;
            ret.add(rsltMap);
            return ret;
            // 2018/06/26 S21_NA#25227 Mod End
            // 2017/10/04 S21_NA#21300 Add End
            // 2017/10/04 S21_NA#21300 Del Start
//            return null;
            // 2017/10/04 S21_NA#21300 Del End
        }
        // 2017/10/04 S21_NA#21300 Mod Start
//        return (Map<String, Object>) ssmResult.getResultObject();
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
        // 2018/06/26 S21_NA#25227 Mod Start
//        String mdseCd = null;
//        BigDecimal ordQtyShpgPln = BigDecimal.ZERO;
//        BigDecimal ordQtyCpoDtl = BigDecimal.ZERO;
//
//        for (Map<String, Object> rslt : rsltList) {
//            mdseCd = (String) rslt.get("MDSE_CD");
//            BigDecimal curOrdQtyShpgPln = (BigDecimal) rslt.get("ORD_QTY_SHPG_PLN");
//            if (curOrdQtyShpgPln == null) {
//                curOrdQtyShpgPln = BigDecimal.ZERO;
//            }
//            ordQtyShpgPln = ordQtyShpgPln.add(curOrdQtyShpgPln);
//
//            BigDecimal curOrdQtyCpoDtl = (BigDecimal) rslt.get("ORD_QTY_CPO_DTL");
//            if (curOrdQtyCpoDtl == null) {
//                curOrdQtyCpoDtl = BigDecimal.ZERO;
//            }
//            ordQtyCpoDtl = ordQtyCpoDtl.add(curOrdQtyCpoDtl);
//        }
//        Map<String, Object> rsltMap = new HashMap<String, Object>();
//        rsltMap.put("MDSE_CD", mdseCd);
//        rsltMap.put("ORD_QTY_SHPG_PLN", ordQtyShpgPln);
//        rsltMap.put("ORD_QTY_CPO_DTL", ordQtyCpoDtl);
//
//        return rsltMap;
        Map<String, Map<String, Object>> mdseQtyInfo = new HashMap<String, Map<String, Object>>();
        // summary qty
        for (Map<String, Object> rslt : rsltList) {
            String mdseCd = (String) rslt.get("MDSE_CD");
            BigDecimal ordQtyShpgPln = (BigDecimal) rslt.get("ORD_QTY_SHPG_PLN");
            BigDecimal ordQtyCpoDtl = (BigDecimal) rslt.get("ORD_QTY_CPO_DTL");

            if (!ZYPCommonFunc.hasValue(ordQtyShpgPln)) {
                ordQtyShpgPln = BigDecimal.ZERO;
            }
            if (!ZYPCommonFunc.hasValue(ordQtyCpoDtl)) {
                ordQtyCpoDtl = BigDecimal.ZERO;
            }

            if (mdseQtyInfo.containsKey(mdseCd)) {
                Map<String, Object> qtyInfo = mdseQtyInfo.get(mdseCd);
                ordQtyShpgPln = ordQtyShpgPln.add((BigDecimal) qtyInfo.get("ORD_QTY_SHPG_PLN"));
                ordQtyCpoDtl = ordQtyCpoDtl.add((BigDecimal) qtyInfo.get("ORD_QTY_CPO_DTL"));
                qtyInfo.put("ORD_QTY_SHPG_PLN", ordQtyShpgPln);
                qtyInfo.put("ORD_QTY_CPO_DTL", ordQtyCpoDtl);
                mdseQtyInfo.put(mdseCd, qtyInfo);
            } else {
                mdseQtyInfo.put(mdseCd, rslt);
            }
        }

        Iterator<String> keys = mdseQtyInfo.keySet().iterator();
        while (keys.hasNext()) {
            ret.add(mdseQtyInfo.get(keys.next()));
        }

        return ret;
        // 2018/06/26 S21_NA#25227 Mod End
        // 2017/10/04 S21_NA#21300 Mod End
    }

    // Add Start 2019/03/07 QC#30629
    private static List<Map<String, Object>> getLineQuantityForSetParent(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", lineMsg.dsOrdPosnNum_LL.getValue());
        queryParam.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum_LL.getValue());
        queryParam.put("cpoDtlLineSubNum", lineMsg.cpoDtlLineSubNum_LL.getValue());
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getLineQuantityForSetParent(queryParam);

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        return rsltList;
    }

    private static List<Map<String, Object>> getSetChildrenQty(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum_LL.getValue());
        queryParam.put("cpoDtlLineSubNum", lineMsg.cpoDtlLineSubNum_LL.getValue());
        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.S_OR_O_CANCELLED };
        queryParam.put("shpgStsCdList", shpgStsCdList);
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getSetChildrenQty(queryParam);
        
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        return rsltList;
    }
    // Add End 2019/03/07 QC#30629

    private static Map<String, Object> getLineQuantityForRMA(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaLineMsg) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", rmaLineMsg.dsOrdPosnNum_RL.getValue());
        queryParam.put("dsCpoDtlLineNum", rmaLineMsg.cpoDtlLineNum_RL.getValue());
        queryParam.put("dsCpoDtlLineSubNum", rmaLineMsg.cpoDtlLineSubNum_RL.getValue());
        // 2019/03/19 S21_NA#30700 Mod Start
        // 2018/06/14 S21_NA#25227 Mod Start
        String[] rtrnLineStsCdList = {RTRN_LINE_STS.ENTERED, RTRN_LINE_STS.BOOKED, RTRN_LINE_STS.RWS_CANCELLED, RTRN_LINE_STS.PARTIALLY_RECEIVED };
//        String[] rtrnLineStsCdList = {RTRN_LINE_STS.ENTERED, RTRN_LINE_STS.BOOKED, RTRN_LINE_STS.RWS_CANCELLED };
        // 2018/06/14 S21_NA#25227 Mod End
        // 2019/03/19 S21_NA#30700 Mod End
        queryParam.put("rtrnLineStsCdList", rtrnLineStsCdList);
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getLineQuantityForRMA(queryParam);
        if (ssmResult.getQueryResultCount() == 0) {
            return null;
        }
        return (Map<String, Object>) ssmResult.getResultObject();
    }

    private static List<Integer> getRemoveLine(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum, BigDecimal lineNum) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && lineList.no(i).dsCpoLineNum_LL.getValueInt() == lineNum.intValue()) {
                deleteLine.add(i);
            }
        }
        return deleteLine;
    }

    private static List<Integer> getRemoveLine(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum, BigDecimal lineNum, BigDecimal lineSubNum) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (lineSubNum == null) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && lineList.no(i).dsCpoLineNum_RL.getValueInt() == lineNum.intValue()) {
                    deleteLine.add(i);
                }
            } else {
                if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && lineList.no(i).dsCpoLineNum_RL.getValueInt() == lineNum.intValue()
                        && NWAL1500CommonLogic.compareBigDecimal(lineList.no(i).dsCpoLineSubNum_RL.getValue(), lineSubNum) == 0) {
                    deleteLine.add(i);
                }
            }
        }
        return deleteLine;
    }

    private static int getUncancelQuantity(BigDecimal ordQtyOrdLine, BigDecimal cancelableQty) {

        BigDecimal ordLine = ordQtyOrdLine;
        if (ordLine == null) {
            ordLine = BigDecimal.ZERO;
        }
        BigDecimal cancelable = cancelableQty;
        if (cancelable == null) {
            cancelable = BigDecimal.ZERO;
        }
        // 2016/01/28 S21_NA#3254 Mod Start
        // return ordLine.intValue() - cancelable.intValue();
        return ordLine.abs().subtract(cancelable.abs()).intValue();
        // 2016/01/28 S21_NA#3254 Mod End
    }

//    private static boolean isCancelableConfig(String glblCmpyCd, NWAL1500CMsg bizMsg, NWAL1500_ACMsg config) { 2017/10/04 S21_NA#21300 Mod Interface
    // private static String isCancelableConfig(NWAL1500CMsg bizMsg, NWAL1500_ASMsg config) { // 2018/06/27 S21_NA#26602 Mod
    private static String isCancelableConfig(NWAL1500CMsg bizMsg, NWAL1500_ASMsg config, NWAL1500SMsg glblMsg) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", config.dsOrdPosnNum_LC.getValue());
        // 2017/10/04 S21_NA#21300 Mod Start
//        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.CANCELLED };
        // 2018/06/28 S21_NA#25227 Mod Start
//        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.CANCELLED, SHPG_STS.P_OR_O_PRINTED };
        // 2019/06/18 S21_NA#50732 Mod Start
        //String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.P_OR_O_PRINTED };
        String[] shpgStsCdList = {SHPG_STS.VALIDATED, SHPG_STS.P_OR_O_CANCELLED, SHPG_STS.P_OR_O_PRINTED, SHPG_STS.CANCELLED };
        // 2019/06/18 S21_NA#50732 Mod End
        // 2018/06/28 S21_NA#25227 Mod End
        // 2017/10/04 S21_NA#21300 Mod End
        queryParam.put("shpgStsCdList", shpgStsCdList);
        // 2017/10/04 S21_NA#21300 Add Start
        queryParam.put("shpgStsPoPrinted", SHPG_STS.P_OR_O_PRINTED);

        List<String> ittAvalCancStsList = NWAL1500CommonLogic.getAvalCancStsOfItt(bizMsg.glblCmpyCd.getValue());
        queryParam.put("ittAvalCancStsList", ittAvalCancStsList);

        List<String> ittAvalCancOrdLineSrcList = NWAL1500CommonLogic.getAvalCancOrdLineSrcCdOfItt(bizMsg.glblCmpyCd.getValue());
        queryParam.put("ittAvalCancOrdLineSrcList", ittAvalCancOrdLineSrcList);
        // 2017/10/04 S21_NA#21300 Add End
        // 2019/11/27 QC#52339 Add Start 
        String[] shpgStsCdIntangibleList = {SHPG_STS.INVOICED };
        queryParam.put("shpgStsCdIntangibleList", shpgStsCdIntangibleList);
        queryParam.put("invtyCtrlFlgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("invtyCtrlFlgN", ZYPConstant.FLG_OFF_N);
        // 2019/11/27 QC#52339 Add End
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getUncancelableOrderForLineConfig(queryParam);

        // 2017/10/04 S21_NA#21300 Mod Start
//        return !(ssmResult.getQueryResultCount() > 0);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String errSts = "";
        String errPo = "";
        // 2018/06/28 S21_NA#25227 Add Start
        Set<Boolean> allShpgCanc = new HashSet<Boolean>();
        // 2018/06/28 S21_NA#25227 Add End
        for (Map<String, Object> rslt : rsltList) {
            // 2018/06/28 S21_NA#25227 Add Start
            String shpgSts = (String) rslt.get("SHPG_STS_CD");
            allShpgCanc.add(ZYPCommonFunc.hasValue(shpgSts) && S21StringUtil.isEquals(shpgSts, SHPG_STS.CANCELLED));
            // 2018/06/28 S21_NA#25227 Add End
            String tpCd = (String) rslt.get("TP_CD");
            if (S21StringUtil.isEquals("1", tpCd)) {
                errSts = NWAM0682E;
            }
            if (S21StringUtil.isEquals("2", tpCd)) {
                errPo = NWAM0947E;
            }
            if (S21StringUtil.isEquals("3", tpCd)) {
                errPo = NWAM0947E;
            }
        }
        // 2018/06/28 S21_NA#25227 Add Start
        if (allShpgCanc.size() == 1 && allShpgCanc.contains(true)) {
            errSts = NWAM0682E;
        }
        // 2018/06/28 S21_NA#25227 Add End
        // 2018/06/27 S21_NA#26602 Add Start
        String errRebilLine = "";
        String dsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                continue;
            }
            if (NWAL1500CommonLogic.isRebillTangibleItem(lineMsg, bizMsg.glblCmpyCd.getValue())) {
                // 2019/06/11 QC#50787 Mod Start
                // errRebilLine =  NWAM0783E;
                errRebilLine =  NWAM8460E;
                // 2019/06/11 QC#50787 Mod End
                break;
            }
        }
        // 2018/06/27 S21_NA#26602 Add End
        if (!errSts.isEmpty()) {
            return errSts;
        } else if (!errPo.isEmpty()) {
            return errPo;
        } else if(!errRebilLine.isEmpty()) { // 2018/06/27 S21_NA#26602 Add Condition
            return errRebilLine;
        } else {
            return "";
        }
        // 2017/10/04 S21_NA#21300 Mod End
    }

    private static boolean isCancelableConfig(NWAL1500CMsg bizMsg, NWAL1500_CSMsg config) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", config.dsOrdPosnNum_RC.getValue());
        // 2019/06/18 S21_NA#50732 Mod Start
        //String[] rtrnLineStsCdList = {RTRN_LINE_STS.ENTERED, RTRN_LINE_STS.BOOKED, RTRN_LINE_STS.RWS_CANCELLED };
        String[] rtrnLineStsCdList = {RTRN_LINE_STS.ENTERED, RTRN_LINE_STS.BOOKED, RTRN_LINE_STS.RWS_CANCELLED, RTRN_LINE_STS.CANCELLED };
        // 2019/06/18 S21_NA#50732 Mod End
        queryParam.put("rtrnLineStsCdList", rtrnLineStsCdList);
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getUncancelableOrderForRMA(queryParam);

        return !(ssmResult.getQueryResultCount() > 0);
    }

    private static boolean isPartialCancelForLineConfig(NWAL1500_BSMsg bMsg, BigDecimal cancelQty) {

        if (!ZYPCommonFunc.hasValue(bMsg.ordLineStsDescTxt_LL)) {
            return false;
        }
        return NWAL1500CommonLogic.compareBigDecimal(bMsg.ordCustUomQty_LL.getValue(), cancelQty) != 0;
    }

    private static boolean isPartialCancelForRMA(NWAL1500_DSMsg dMsg, BigDecimal cancelQty) {

        return NWAL1500CommonLogic.compareBigDecimal(dMsg.ordCustUomQty_RL.getValue(), cancelQty) != 0;
    }

    /**
     * <pre>
     * check registered config
     * @param lineList Line Config Line List
     * @param dsOrdPosnNum DS Order Position Number
     * @return true registered false: not registered
     * </pre>
     */
    public static boolean isRegisteredConfig(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                if (ZYPCommonFunc.hasValue(line.ordLineStsDescTxt_LL)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     * check registered config
     * @param lineList RMA Line List
     * @param dsOrdPosnNum DS Order Position Number
     * @return true registered false: not registered
     * </pre>
     */
    public static boolean isRegisteredConfig(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                if (ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     * check registered line
     * @param bMsg line message
     * @return true: regisutered line false not registered line
     * </pre>
     */
    public static boolean isRegisteredLine(NWAL1500_BSMsg bMsg) {
        return ZYPCommonFunc.hasValue(bMsg.ordLineStsDescTxt_LL);
    }

    /**
     * <pre>
     * check registered line
     * @param dMsg RMA Line Message
     * @return true: regisutered line false not registered line
     * </pre>
     */

    public static boolean isRegisteredLine(NWAL1500_DSMsg dMsg) {
        return ZYPCommonFunc.hasValue(dMsg.rtrnLineStsDescTxt_RL);
    }

    // 2018/02/03 S21_NA#19808 Del Start
//    private static boolean isRetailEquipmentOrder(NWAL1500CMsg bizMsg) {
//
//        // retail equipment order
//        final Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("cpoSrcTpCd", bizMsg.cpoSrcTpCd.getValue());
//        queryParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
//        queryParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
//        queryParam.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
//        queryParam.put("dsOrdRsnCd", bizMsg.dsOrdRsnCd.getValue());
//        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getRetailEquipmentOrder(queryParam);
//        return (ssmResult.getQueryResultCount() > 0);
//    }
    // 2018/02/03 S21_NA#19808 Del End

    private static boolean isSetComponent(NWAL1500_DSMsgArray dMsgArray, String dsOrdPosnNum, BigDecimal lineNum) {

        int sameLineCount = 0;
        for (int i = 0; i < dMsgArray.getValidCount(); i++) {
            String configNum = dMsgArray.no(i).dsOrdPosnNum_RL.getValue();
            if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && dMsgArray.no(i).dsCpoLineNum_RL.getValueInt() == lineNum.intValue()) {
                sameLineCount++;
            }
        }
        return (sameLineCount > 1);
    }

    private static boolean isSetMdse(NWAL1500_BSMsg bMsg, NWAL1500_BSMsgArray bMsgArray) {

        for (int i = 0; i < bMsgArray.getValidCount(); i++) {
            if (S21StringUtil.isEquals(bMsgArray.no(i).dsOrdPosnNum_LL.getValue(), bMsg.dsOrdPosnNum_LL.getValue()) && bMsgArray.no(i).dsCpoLineNum_LL.getValueInt() == bMsg.dsCpoLineNum_LL.getValueInt()
                    && bMsgArray.no(i).dsCpoLineSubNum_LL.getValueInt() < bMsg.dsCpoLineSubNum_LL.getValueInt()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSetMdse(NWAL1500_DSMsg dMsg, NWAL1500_DSMsgArray dMsgArray) {

        for (int i = 0; i < dMsgArray.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dMsgArray.no(i).dsOrdPosnNum_RL.getValue(), dMsg.dsOrdPosnNum_RL.getValue()) && dMsgArray.no(i).dsCpoLineNum_RL.getValueInt() == dMsg.dsCpoLineNum_RL.getValueInt()
                    && dMsgArray.no(i).dsCpoLineSubNum_RL.getValueInt() < dMsg.dsCpoLineSubNum_RL.getValueInt()) {
                return false;
            }
        }
        return true;
    }

    private static void removeLine(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                deleteLine.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteLine);
    }

    private static void removeLine(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                deleteLine.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteLine);
    }

    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_BSMsg line) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        String lineNum = line.cpoDtlLineNum_LL.getValue();
        String lineSubNum = line.cpoDtlLineSubNum_LL.getValue();
        for (int j = 0; j < priceList.getValidCount(); j++) {
            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, priceList.no(j).configCatgCd_LP.getValue()) && S21StringUtil.isEquals(lineNum, priceList.no(j).cpoDtlLineNum_LP.getValue())
                    && S21StringUtil.isEquals(lineSubNum, priceList.no(j).cpoDtlLineSubNum_LP.getValue())) {
                deleteLine.add(j);
            }
        }
        ZYPTableUtil.deleteRows(priceList, deleteLine);
    }

    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_DSMsg line) {

        List<Integer> deleteLine = new ArrayList<Integer>();
        String lineNum = line.cpoDtlLineNum_RL.getValue();
        String lineSubNum = line.cpoDtlLineSubNum_RL.getValue();
        for (int j = 0; j < priceList.getValidCount(); j++) {
            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, priceList.no(j).configCatgCd_LP.getValue()) && S21StringUtil.isEquals(lineNum, priceList.no(j).cpoDtlLineNum_LP.getValue())
                    && S21StringUtil.isEquals(lineSubNum, priceList.no(j).cpoDtlLineSubNum_LP.getValue())) {
                deleteLine.add(j);
            }
        }
        ZYPTableUtil.deleteRows(priceList, deleteLine);
    }

//    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_JSMsg line) {
//
//        List<Integer> deleteLine = new ArrayList<Integer>();
//        String lineNum = line.cpoDtlLineNum_LL.getValue();
//        String lineSubNum = line.cpoDtlLineSubNum_LL.getValue();
//        for (int j = 0; j < priceList.getValidCount(); j++) {
//            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, priceList.no(j).configCatgCd_LP.getValue()) && S21StringUtil.isEquals(lineNum, priceList.no(j).cpoDtlLineNum_LP.getValue())
//                    && S21StringUtil.isEquals(lineSubNum, priceList.no(j).cpoDtlLineSubNum_LP.getValue())) {
//                deleteLine.add(j);
//            }
//        }
//        ZYPTableUtil.deleteRows(priceList, deleteLine);
//    }
//
//    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_KSMsg line) {
//
//        List<Integer> deleteLine = new ArrayList<Integer>();
//        String lineNum = line.cpoDtlLineNum_RL.getValue();
//        String lineSubNum = line.cpoDtlLineSubNum_RL.getValue();
//        for (int j = 0; j < priceList.getValidCount(); j++) {
//            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, priceList.no(j).configCatgCd_LP.getValue()) && S21StringUtil.isEquals(lineNum, priceList.no(j).cpoDtlLineNum_LP.getValue())
//                    && S21StringUtil.isEquals(lineSubNum, priceList.no(j).cpoDtlLineSubNum_LP.getValue())) {
//                deleteLine.add(j);
//            }
//        }
//        ZYPTableUtil.deleteRows(priceList, deleteLine);
//    }

    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                removeOrderPricing(priceList, lineList.no(i));
            }
        }
    }

    private static void removeOrderPricing(NWAL1500_ISMsgArray priceList, NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                removeOrderPricing(priceList, lineList.no(i));
            }
        }
    }

    private static void removeSalesCreditForLineConfig(NWAL1500_GCMsgArray gMsgArray, String dsOrdPosnNum) {

        boolean findConfig = false;
        List<Integer> deleteSlsCredit = new ArrayList<Integer>();
        for (int i = 0; i < gMsgArray.getValidCount(); i++) {
            String configNum = gMsgArray.no(i).dsOrdPosnNum_GS.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                findConfig = true;
                deleteSlsCredit.add(i);
            } else if (findConfig) {
                gMsgArray.no(i).dsOrdPosnNum_GS.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
            }
        }
        ZYPTableUtil.deleteRows(gMsgArray, deleteSlsCredit);
    }

    private static void removeSalesCreditForRMA(NWAL1500_HCMsgArray hMsgArray, String dsOrdPosnNum) {

        boolean findConfig = false;
        List<Integer> deleteSlsCredit = new ArrayList<Integer>();
        for (int i = 0; i < hMsgArray.getValidCount(); i++) {
            String configNum = hMsgArray.no(i).dsOrdPosnNum_HS.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                findConfig = true;
                deleteSlsCredit.add(i);
            } else if (findConfig) {
                hMsgArray.no(i).dsOrdPosnNum_HS.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
            }
        }
        ZYPTableUtil.deleteRows(hMsgArray, deleteSlsCredit);
    }

    private static void renumberingConfig(NWAL1500_ASMsgArray configList, NWAL1500_BSMsgArray lineList, int startIndex) {

        for (int i = startIndex; i < configList.getValidCount(); i++) {
            String configNum = configList.no(i).dsOrdPosnNum_LC.getValue();
            configList.no(i).dsOrdPosnNum_LC.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
            for (int j = 0; j < lineList.getValidCount(); j++) {
                if (S21StringUtil.isEquals(configNum, lineList.no(j).dsOrdPosnNum_LL.getValue())) {
                    lineList.no(j).dsOrdPosnNum_LL.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
                    lineList.no(j).xxLineNum_LL.setValue(NWXC150001DsCheck.editDtlLineNum(lineList.no(j).dsOrdPosnNum_LL.getValue(), lineList.no(j).dsCpoLineNum_LL.getValue(), lineList.no(j).dsCpoLineSubNum_LL.getValue()));
                }
            }
        }
    }

    private static void renumberingConfig(NWAL1500_ASMsgArray configList, NWAL1500_BSMsgArray lineList, NWAL1500_GCMsgArray slsCrList) {

        int dsOrdPosnNum = 0;
        int dsCpoLineNum = 0;
        for (int i = 0; i < configList.getValidCount(); i++) {
            String configNum = configList.no(i).dsOrdPosnNum_LC.getValue();
            dsOrdPosnNum = i + 1;
            configList.no(i).dsOrdPosnNum_LC.setValue(String.format("%d", dsOrdPosnNum));
            dsCpoLineNum = 0;
            for (int j = 0; j < lineList.getValidCount(); j++) {
                if (S21StringUtil.isEquals(configNum, lineList.no(j).dsOrdPosnNum_LL.getValue())) {
                    dsCpoLineNum = dsCpoLineNum + 1;
                    lineList.no(j).dsOrdPosnNum_LL.setValue(String.format("%d", dsOrdPosnNum));
                    lineList.no(i).dsCpoLineNum_LL.setValue(dsCpoLineNum);
                    lineList.no(j).xxLineNum_LL.setValue(NWXC150001DsCheck.editDtlLineNum(lineList.no(j).dsOrdPosnNum_LL.getValue(), lineList.no(j).dsCpoLineNum_LL.getValue(), lineList.no(j).dsCpoLineSubNum_LL.getValue()));
                }
            }
            for (int k = 0; k < lineList.getValidCount(); k++) {
                if (S21StringUtil.isEquals(configNum, slsCrList.no(k).dsOrdPosnNum_GS.getValue())) {
                    slsCrList.no(k).dsOrdPosnNum_GS.setValue(String.format("%d", dsOrdPosnNum));
                }
            }
        }
    }

    private static void renumberingConfig(NWAL1500_CSMsgArray configList, NWAL1500_DSMsgArray lineList, int startIndex) {

        for (int i = startIndex; i < configList.getValidCount(); i++) {
            String configNum = configList.no(i).dsOrdPosnNum_RC.getValue();
            configList.no(i).dsOrdPosnNum_RC.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
            for (int j = 0; j < lineList.getValidCount(); j++) {
                if (S21StringUtil.isEquals(configNum, lineList.no(j).dsOrdPosnNum_RL.getValue())) {
                    lineList.no(j).dsOrdPosnNum_RL.setValue(String.format("%d", Integer.parseInt(configNum) - 1));
                    lineList.no(j).xxLineNum_RL.setValue(NWXC150001DsCheck.editDtlLineNum(lineList.no(j).dsOrdPosnNum_RL.getValue(), lineList.no(j).dsCpoLineNum_RL.getValue(), lineList.no(j).dsCpoLineSubNum_RL.getValue()));
                }
            }
        }
    }

    private static void renumberingConfig(NWAL1500_CSMsgArray configList, NWAL1500_DSMsgArray lineList, NWAL1500_HCMsgArray slsCrList) {

        int dsOrdPosnNum = 0;
        int dsCpoLineNum = 0;
        List<String> newPosnNumList = new ArrayList<String>();
        for (int i = 0; i < configList.getValidCount(); i++) {
            String configNum = configList.no(i).dsOrdPosnNum_RC.getValue();
            dsOrdPosnNum = i + 1;
            configList.no(i).dsOrdPosnNum_RC.setValue(String.format("%d", dsOrdPosnNum));
            dsCpoLineNum = 0;
            for (int j = 0; j < lineList.getValidCount(); j++) {
                if (S21StringUtil.isEquals(configNum, lineList.no(j).dsOrdPosnNum_RL.getValue())) {
                    dsCpoLineNum = dsCpoLineNum + 1;
                    lineList.no(j).dsOrdPosnNum_RL.setValue(String.format("%d", dsOrdPosnNum));
                    lineList.no(i).dsCpoLineNum_RL.setValue(dsCpoLineNum);
                    lineList.no(j).xxLineNum_RL.setValue(NWXC150001DsCheck.editDtlLineNum(lineList.no(j).dsOrdPosnNum_RL.getValue(), lineList.no(j).dsCpoLineNum_RL.getValue(), lineList.no(j).dsCpoLineSubNum_RL.getValue()));
                }
            }
            for (int k = 0; k < lineList.getValidCount(); k++) {
                if (S21StringUtil.isEquals(configNum, slsCrList.no(k).dsOrdPosnNum_HS.getValue())) {
                    slsCrList.no(k).dsOrdPosnNum_HS.setValue(String.format("%d", dsOrdPosnNum));
                }
            }
        }
    }

    private static void renumberingCPOLineNum(NWAL1500_BSMsgArray lineList, NWAL1500_ISMsgArray priceList, String sourceLineNum, String startLineNum) {

        String origLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(sourceLineNum);
        String swapLineNum = startLineNum;

        boolean existsLine = true;
        while (existsLine) {
            existsLine = false;
            for (int i = 0; i < lineList.getValidCount(); i++) {
                NWAL1500_BSMsg line = lineList.no(i);
                if (S21StringUtil.isEquals(line.cpoDtlLineNum_LL.getValue(), origLineNum)) {
                    swapCpoLineNum(lineList, priceList, origLineNum, swapLineNum);
                    swapLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(swapLineNum);
                    origLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(origLineNum);
                    existsLine = true;
                    break;
                }
            }
        }
    }

    private static void renumberingCPOLineNum(NWAL1500_DSMsgArray lineList, NWAL1500_ISMsgArray priceList, String sourceLineNum, String startLineNum) {

        String origLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(sourceLineNum);
        String swapLineNum = startLineNum;

        boolean existsLine = true;
        while (existsLine) {
            existsLine = false;
            for (int i = 0; i < lineList.getValidCount(); i++) {
                NWAL1500_DSMsg line = lineList.no(i);
                if (S21StringUtil.isEquals(line.cpoDtlLineNum_RL.getValue(), origLineNum)) {
                    swapCpoLineNum(lineList, priceList, origLineNum, swapLineNum);
                    swapLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(swapLineNum);
                    origLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(origLineNum);
                    existsLine = true;
                    break;
                }
            }
        }
    }

    private static void renumberingLineNum(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum, BigDecimal lineNum, int startIndex) {

        for (int i = startIndex; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && lineList.no(i).dsCpoLineNum_LL.getValueInt() != lineNum.intValue()) {
                // renumbering line#
                lineList.no(i).dsCpoLineNum_LL.setValue(lineList.no(i).dsCpoLineNum_LL.getValueInt() - 1);
                lineList.no(i).xxLineNum_LL.setValue(NWAL1500CommonLogicForLineControl.editDisplayLineNum(lineList.no(i)));
            }
        }
    }

    private static void renumberingLineNum(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum, BigDecimal lineNum, BigDecimal lineSubNum, int startIndex) {

        for (int i = startIndex; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (S21StringUtil.isEquals(dsOrdPosnNum, configNum)) {
                if (lineSubNum == null && lineList.no(i).dsCpoLineNum_RL.getValueInt() != lineNum.intValue()) {
                    // renumbering line#
                    lineList.no(i).dsCpoLineNum_RL.setValue(lineList.no(i).dsCpoLineNum_RL.getValueInt() - 1);
                    lineList.no(i).xxLineNum_RL.setValue(NWAL1500CommonLogicForLineControl.editDisplayLineNum(lineList.no(i)));
                } else if (lineSubNum != null && lineList.no(i).dsCpoLineSubNum_RL.getValueInt() != lineSubNum.intValue()) {
                    // renumbering sub line#
                    lineList.no(i).dsCpoLineSubNum_RL.setValue(lineList.no(i).dsCpoLineSubNum_RL.getValueInt() - 1);
                    lineList.no(i).xxLineNum_RL.setValue(NWAL1500CommonLogicForLineControl.editDisplayLineNum(lineList.no(i)));
                }
            }
        }
    }

    /**
     * <pre>
     * Reset Base Component Flag For Line Config
     * @param glblCmpyCd Global Company Code
     * @param lineList Line Messate List
     * @param dsOrdPosnNum DS Order Position Number
     * @param lineNum Line Number
     * @return true: normal end false: error 
     * </pre>
     */
    public static boolean resetBaseComponentForLineConfig(String glblCmpyCd, NWAL1500_BSMsgArray lineList, String dsOrdPosnNum, BigDecimal lineNum) {

        NWAL1500_BSMsg istlBseCtrl = null;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (S21StringUtil.isEquals(dsOrdPosnNum, configNum) && lineList.no(i).dsCpoLineNum_LL.getValueInt() != lineNum.intValue()) {
                if (!ZYPCommonFunc.hasValue(lineList.no(i).mdseCd_LL.getValue())) {
                    continue;
                }
                // reset base component
                final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, lineList.no(i).mdseCd_LL.getValue());
                if (mdseTMsg == null) {
                    return false;
                }
                final Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("mdseCd", mdseTMsg.mdseCd.getValue());
                queryParam.put("mdseTpCtxTpCd", "MAIN_MACH");
                S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getMdseTpValSet(queryParam);
                if (ssmResult.getQueryResultCount() > 0) {
                    Map<String, Object> queryResult = (Map<String, Object>) ssmResult.getResultObject();
                    if (S21StringUtil.isNotEmpty((String) queryResult.get("MDSE_TP_CTX_TP_CD"))) {
                        lineList.no(i).baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                        return true;
                    }
                    if (istlBseCtrl == null) {
                        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, (String) queryResult.get("INSTL_BASE_CTRL_FLG"))) {
                            istlBseCtrl = lineList.no(i);
                        }
                    }
                }
            }
        }
        if (istlBseCtrl != null) {
            istlBseCtrl.baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
        }
        return true;
    }

    private static void swapCpoLineNum(NWAL1500_BSMsgArray lineList, NWAL1500_ISMsgArray priceList, String origLineNum, String swapLineNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (S21StringUtil.isEquals(line.cpoDtlLineNum_LL.getValue(), origLineNum)) {
                ZYPEZDItemValueSetter.setValue(line.cpoDtlLineNum_LL, swapLineNum);
            }
        }
        for (int i = 0; i < priceList.getValidCount(); i++) {
            NWAL1500_ISMsg price = priceList.no(i);
            if (S21StringUtil.isEquals(price.cpoDtlLineNum_LP.getValue(), origLineNum) && S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, price.configCatgCd_LP.getValue())) {
                ZYPEZDItemValueSetter.setValue(price.cpoDtlLineNum_LP, swapLineNum);
            }
        }
    }

    private static void swapCpoLineNum(NWAL1500_DSMsgArray lineList, NWAL1500_ISMsgArray priceList, String origLineNum, String swapLineNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if (S21StringUtil.isEquals(line.cpoDtlLineNum_RL.getValue(), origLineNum)) {
                ZYPEZDItemValueSetter.setValue(line.cpoDtlLineNum_RL, swapLineNum);
            }
        }
        for (int i = 0; i < priceList.getValidCount(); i++) {
            NWAL1500_ISMsg price = priceList.no(i);
            if (S21StringUtil.isEquals(price.cpoDtlLineNum_LP.getValue(), origLineNum) && S21StringUtil.isEquals(CONFIG_CATG.INBOUND, price.configCatgCd_LP.getValue())) {
                ZYPEZDItemValueSetter.setValue(price.cpoDtlLineNum_LP, swapLineNum);
            }
        }
    }

    private static boolean validateCancelQuantity(int uncancelableQty, int eachQty) {

        // return (uncancelableQty < eachQty); 2016/01/28 S21_NA#3254 Mod
        return (uncancelableQty <= eachQty);
    }

    // 2016/02/19 S21_NA#2166 Add Start
    private static void cancelLineOrder(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (NWAL1500CommonLogicForSaveSubmit.getInstance().callDsCpoUpdateApiForLineCancel(bizMsg, glblMsg)) {
            // Update Qty 0
            updQtyZero4CpoDtl(bizMsg);
            updQtyZero4DsCpoRtrnDtl(bizMsg);
        }
    }

// 2016/04/20 S21_NA#5605 Del Start
//    private static void saveOrder(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
//
//        NWAL1500CommonLogicForSaveSubmit commonLogic = NWAL1500CommonLogicForSaveSubmit.getInstance();
//        if (!commonLogic.checkCustPoFields(bizMsg)) {
//            return;
//        }
//        
//        if (!commonLogic.checkCustPoDt(bizMsg)) {
//            return;
//        }
//
//        if (!commonLogic.checkCustSgnDt(bizMsg)) {
//            return;
//        }
//
//        if (commonLogic.isHeaderTabMasterError(bizMsg)) {
//            return;
//        }
//        if (commonLogic.isAddlHeaderTabMasterError(bizMsg)) {
//            return;
//        }
//
//        if (commonLogic.isLineConfigTabMasterError(bizMsg)) {
//            return;
//        }
//        if (commonLogic.isRMATabMasterError(bizMsg)) {
//            return;
//        }
//        NWAL1500CommonLogic.numberingOrderRma(bizMsg);
//
//        // 3-1) Sales Credit Data
//        NWAL1500CommonLogicForSalesCredit.checkAndSetSalesCredit(bizMsg, glblMsg);
//
//        // 3-2) CSMP Price List
//        commonLogic.setCsmpPrcList(bizMsg, glblMsg);
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }
//        // 4-1) Changing config Type
//        commonLogic.checkConfigType(bizMsg);
//
//        // 5) Relation Check
//        // 5-1) Header Tab Check
////        if (commonLogic.isRelationErrOnHeader(bizMsg)) {
////            bizMsg.xxDplyTab.setValue(TAB_HEADER);
////            return;
////        }
//        // 5-2) Additinal Header Tab Check
////        if (commonLogic.isRelationErrOnAddlHeader(bizMsg)) {
////            bizMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
////            return;
////        }
//        // 5-3) Line Config
//        // QC#4078 Mod
//        if (commonLogic.isRelationErrOnLineConfig(bizMsg, glblMsg, true, true)) {
////            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
//            return;
//        }
////        if ("E".equals(bizMsg.getMessageKind())) {
////            return;
////        }
//
//
//        // check data were updated by other user
//        if (NWAL1500CommonLogic.isModifiedByOtherUser(bizMsg, glblMsg)) {
//            bizMsg.setMessageInfo("NWAM0429E");
//            return;
//        }
//        CPOTMsg cpoMsg = null;
//        boolean isModifyReq = false;
//        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            cpoMsg = lockCpo(bizMsg);
//            if (cpoMsg == null) {
//                return;
//            }
//            isModifyReq = ZYPConstant.FLG_ON_Y.equals(cpoMsg.submtFlg.getValue());
//
//            // if (!lockShpgPln(bizMsg, sMsg)) {
//            // return;
//            // }
//        }
//
//        // QC#4078
//        int[] cntArray = {0, 0, 0, 0, 0};
//        if (commonLogic.callDsCpoUpdateApi(bizMsg, glblMsg, true, isModifyReq, false, cntArray)) {
//           // Updatet Qty 0
//           updQtyZero4CpoDtl(bizMsg);
//           updQtyZero4DsCpoRtrnDtl(bizMsg);
//        }
//    }
 // 2016/04/20 S21_NA#5605 Del End

    private static CPOTMsg lockCpo(NWAL1500CMsg bizMsg) {

        // CPO
        CPOTMsg cpoMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0"));
        cpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cpoMsg);
        if (cpoMsg == null) {
            bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
            return null;
        }

        return cpoMsg;
    }

    private static boolean isEnteredOrAddLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> selectedRowsForConfig, List<Integer> selectedRowsForLine) { // 2018/02/03 S21_NA#19808 Mod

        // 2018/02/03 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        boolean rslt = false;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            for (Integer selectedRow : selectedRowsForConfig) {
                String dsOrdPosnNum = glblMsg.A.no(selectedRow.intValue()).dsOrdPosnNum_LC.getValue();
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                    if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                        if (ORD_LINE_STS.SAVED.equals(lineMsg.ordLineStsCd_LL.getValue())
                                || !ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                            // 2016/04/18 S21_NA#5406 Mod Start
//                            glblMsg.A.no(selectedRow.intValue()).xxChkBox_LC.setErrorInfo(1, NWAM0682E);
//                            bizMsg.setMessageInfo(NWAM0682E);
                            glblMsg.A.no(selectedRow.intValue()).xxChkBox_LC.setErrorInfo(1, NWAM0833E);
                            bizMsg.setMessageInfo(NWAM0833E);
                            // 2016/04/18 S21_NA#5406 Mod End
                            rslt = true;
                            break;
                        }
                    }
                }
            }

            for (Integer selectedRow : selectedRowsForLine) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(selectedRow.intValue());
                if (ORD_LINE_STS.SAVED.equals(lineMsg.ordLineStsCd_LL.getValue())
                        || !ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                    // 2016/04/18 S21_NA#5406 Mod Start
//                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
//                    bizMsg.setMessageInfo(NWAM0682E);
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0833E);
                    bizMsg.setMessageInfo(NWAM0833E);
                    // 2016/04/18 S21_NA#5406 Mod End
                    rslt = true;
                }
            }
        } else {
            boolean isHdrSaved = NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue()); // 2016/06/03 S21_NA#9301 Add

            for (Integer selectedRow : selectedRowsForConfig) {
                String dsOrdPosnNum = glblMsg.C.no(selectedRow.intValue()).dsOrdPosnNum_RC.getValue();
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
                    if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_RL.getValue())) {
//                        if (RTRN_LINE_STS.ENTERED.equals(lineMsg.ordLineStsCd_RL.getValue()) 2016/06/03 S21_NA#9301 Mod Condition
                        if ((RTRN_LINE_STS.ENTERED.equals(lineMsg.ordLineStsCd_RL.getValue()) && isHdrSaved) // 2016/06/03 S21_NA#9301 Mod Condition
                                || !ZYPCommonFunc.hasValue(lineMsg.rtrnLineStsDescTxt_RL)) {
                            // 2016/04/18 S21_NA#5406 Mod Start
//                            glblMsg.C.no(selectedRow.intValue()).xxChkBox_RC.setErrorInfo(1, NWAM0682E);
//                            bizMsg.setMessageInfo(NWAM0682E);
                            glblMsg.C.no(selectedRow.intValue()).xxChkBox_RC.setErrorInfo(1, NWAM0833E);
                            bizMsg.setMessageInfo(NWAM0833E);
                            // 2016/04/18 S21_NA#5406 Mod End
                            rslt = true;
                            break;
                        }
                    }
                }
            }

            for (Integer selectedRow : selectedRowsForLine) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(selectedRow.intValue());
                // if (RTRN_LINE_STS.ENTERED.equals(lineMsg.ordLineStsCd_RL.getValue()) 2016/06/03 S21_NA#9301 Mod Condition
                if ((RTRN_LINE_STS.ENTERED.equals(lineMsg.ordLineStsCd_RL.getValue()) && isHdrSaved) // 2016/06/03 S21_NA#9301 Mod Condition
                        || !ZYPCommonFunc.hasValue(lineMsg.rtrnLineStsDescTxt_RL)) {
                    // 2016/04/18 S21_NA#5406 Mod Start
//                    lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0682E);
//                    bizMsg.setMessageInfo(NWAM0682E);
                    lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0833E);
                    bizMsg.setMessageInfo(NWAM0833E);
                    // 2016/04/18 S21_NA#5406 Mod End
                    rslt = true;
                }
            }
        }
        return rslt;
    }

    private static void updQtyZero4CpoDtl(NWAL1500CMsg bizMsg) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("001");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());

        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(cpoDtlTMsg);
        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg4Upd = cpoDtlTMsgArray.no(i);
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg4Upd.ordLineStsCd.getValue())
                    && cpoDtlTMsg4Upd.ordQty.getValue().compareTo(BigDecimal.ZERO) != 0) {
                cpoDtlTMsg4Upd.ordQty.setValue(BigDecimal.ZERO);
                cpoDtlTMsg4Upd.ordCustUomQty.setValue(BigDecimal.ZERO);
                cpoDtlTMsg4Upd.entDealNetUnitPrcAmt.setValue(BigDecimal.ZERO); // QC#17227 2017/03/03 Add
                cpoDtlTMsg4Upd.entFuncNetUnitPrcAmt.setValue(BigDecimal.ZERO); // QC#17227 2017/03/03 Add
                EZDTBLAccessor.update(cpoDtlTMsg4Upd);
                clearOrdPrcCalcBase(cpoDtlTMsg4Upd); // QC#17227 2017/03/03 Add
            }
        }
    }

    private static void updQtyZero4DsCpoRtrnDtl(NWAL1500CMsg bizMsg) {

        // Return
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        dsCpoRtrnDtlTMsg.setSQLID("001");
        dsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        dsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());

        DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(dsCpoRtrnDtlTMsg);
        for (int i = 0; i < dsCpoRtrnDtlTMsgArray.getValidCount(); i++) {
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg4Upd = dsCpoRtrnDtlTMsgArray.no(i);
            if (RTRN_LINE_STS.CANCELLED.equals(dsCpoRtrnDtlTMsg4Upd.rtrnLineStsCd.getValue())
                    && dsCpoRtrnDtlTMsg4Upd.ordQty.getValue().compareTo(BigDecimal.ZERO) != 0) {
                dsCpoRtrnDtlTMsg4Upd.ordQty.setValue(BigDecimal.ZERO);
                dsCpoRtrnDtlTMsg4Upd.ordCustUomQty.setValue(BigDecimal.ZERO);
                dsCpoRtrnDtlTMsg4Upd.entDealNetUnitPrcAmt.setValue(BigDecimal.ZERO); // QC#17227 2017/03/03 Add
                dsCpoRtrnDtlTMsg4Upd.entFuncNetUnitPrcAmt.setValue(BigDecimal.ZERO); // QC#17227 2017/03/03 Add
                EZDTBLAccessor.update(dsCpoRtrnDtlTMsg4Upd);
                clearCpoRtrnPrcCalcBase(dsCpoRtrnDtlTMsg4Upd); // QC#17227 2017/03/03 Add
            }
        }
    }
    // 2016/02/19 S21_NA#2166 Add End
    // QC#17227 2017/03/03 Add Start
    private static void clearOrdPrcCalcBase(CPO_DTLTMsg cpoDtlTMsg4Upd) {
        ORD_PRC_CALC_BASETMsg inTMsg = new ORD_PRC_CALC_BASETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", cpoDtlTMsg4Upd.glblCmpyCd.getValue());
        inTMsg.setConditionValue("cpoOrdNum01", cpoDtlTMsg4Upd.cpoOrdNum.getValue());
        inTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlTMsg4Upd.cpoDtlLineNum.getValue());
        inTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlTMsg4Upd.cpoDtlLineSubNum.getValue());
        ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inTMsg);
        for (int i = 0; i < ordPrcCalcBaseTMsgArray.getValidCount(); i++) {
            ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = ordPrcCalcBaseTMsgArray.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(ordPrcCalcBaseTMsg.prcDtlGrpCd.getValue())) {
                if (BigDecimal.ZERO.compareTo(ordPrcCalcBaseTMsg.calcPrcAmtRate.getValue()) != 0) {
                    ZYPEZDItemValueSetter.setValue(ordPrcCalcBaseTMsg.autoPrcAmtRate, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(ordPrcCalcBaseTMsg.calcPrcAmtRate, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(ordPrcCalcBaseTMsg.unitPrcAmt, BigDecimal.ZERO);
                    EZDTBLAccessor.update(ordPrcCalcBaseTMsg);
                }
            } else {
                EZDTBLAccessor.logicalRemove(ordPrcCalcBaseTMsg);
            }
        }

    }

    private static void clearCpoRtrnPrcCalcBase(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg4Upd){
        CPO_RTRN_PRC_CALC_BASETMsg inTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", dsCpoRtrnDtlTMsg4Upd.glblCmpyCd.getValue());
        inTMsg.setConditionValue("cpoOrdNum01", dsCpoRtrnDtlTMsg4Upd.cpoOrdNum.getValue());
        inTMsg.setConditionValue("dsCpoRtrnLineNum01", dsCpoRtrnDtlTMsg4Upd.dsCpoRtrnLineNum.getValue());
        inTMsg.setConditionValue("dsCpoRtrnLineSubNum01", dsCpoRtrnDtlTMsg4Upd.dsCpoRtrnLineSubNum.getValue());
        CPO_RTRN_PRC_CALC_BASETMsgArray cpoRtrnPrcCalcBaseTMsgArray = (CPO_RTRN_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inTMsg);
        for (int i = 0; i < cpoRtrnPrcCalcBaseTMsgArray.getValidCount(); i++) {
            CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseTMsg = cpoRtrnPrcCalcBaseTMsgArray.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(cpoRtrnPrcCalcBaseTMsg.prcDtlGrpCd.getValue())) {
                if (BigDecimal.ZERO.compareTo(cpoRtrnPrcCalcBaseTMsg.calcPrcAmtRate.getValue()) != 0) {
                    ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseTMsg.autoPrcAmtRate, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseTMsg.calcPrcAmtRate, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseTMsg.unitPrcAmt, BigDecimal.ZERO);
                    EZDTBLAccessor.update(cpoRtrnPrcCalcBaseTMsg);
                }
            } else {
                EZDTBLAccessor.logicalRemove(cpoRtrnPrcCalcBaseTMsg);
            }
        }

    }
    // QC#17227 2017/03/03 Add End
    // 2018/06/14 S21_NA#25227 Add Start
    private static List<String> getSelectedNums(NWAL1500_ASMsgArray msgArray, List<Integer> selectedRows) {

        List<String> selectedNums = new ArrayList<String>();
        for (int row : selectedRows) {
            selectedNums.add(msgArray.no(row).dsOrdPosnNum_LC.getValue());
        }
        return selectedNums;
    }

    private static List<String> getSelectedNums(NWAL1500_BSMsgArray msgArray, List<Integer> selectedRows) {

        List<String> selectedNums = new ArrayList<String>();
        for (int row : selectedRows) {
            selectedNums.add(msgArray.no(row).xxLineNum_LL.getValue());
        }
        return selectedNums;
    }

    private static List<String> getSelectedNums(NWAL1500_CSMsgArray msgArray, List<Integer> selectedRows) {

        List<String> selectedNums = new ArrayList<String>();
        for (int row : selectedRows) {
            selectedNums.add(msgArray.no(row).dsOrdPosnNum_RC.getValue());
        }
        return selectedNums;
    }

    private static List<String> getSelectedNums(NWAL1500_DSMsgArray msgArray, List<Integer> selectedRows) {

        List<String> selectedNums = new ArrayList<String>();
        for (int row : selectedRows) {
            selectedNums.add(msgArray.no(row).xxLineNum_RL.getValue());
        }
        return selectedNums;
    }

    private static String convDplyCancNums(List<String> nums) {

        if (nums.size() > MAX_ERR_REF_NUM_CNT) {
            return S21StringUtil.arrayToStrList(nums.subList(0, MAX_ERR_REF_NUM_CNT)) + ", ...";
        } else {
            return S21StringUtil.arrayToStrList(nums);
        }
    }

    private static boolean hasSelectedParentAndChild(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> selectedRowsForLine) {

        boolean ret = false;
        int firstErrPage = -1;

        for (int i : selectedRowsForLine) {
            NWAL1500_DSMsg lineBase = glblMsg.D.no(i); 

            if (NWAL1500CommonLogic.isSetMerchandise(bizMsg.glblCmpyCd.getValue(), lineBase.mdseCd_RL.getValue())) {
                for (int j : selectedRowsForLine) {
                    NWAL1500_DSMsg lineSub = glblMsg.D.no(j);
                    if (S21StringUtil.isEquals(lineBase.dsOrdPosnNum_RL.getValue(), lineSub.dsOrdPosnNum_RL.getValue())
                            && S21StringUtil.isEquals(lineBase.cpoDtlLineNum_RL.getValue(), lineSub.cpoDtlLineNum_RL.getValue())
                            && ZYPCommonFunc.hasValue(lineSub.dsCpoLineSubNum_RL)) {
                        lineBase.xxChkBox_RL.setErrorInfo(1, NWAM0958E);
                        if (firstErrPage < 0) {
                            firstErrPage = lineBase.xxPageNum_RL.getValueInt();
                        }
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(lineBase.dsCpoLineSubNum_RL)) {
                for (int j : selectedRowsForLine) {
                    NWAL1500_DSMsg lineSub = glblMsg.D.no(j);
                    if (S21StringUtil.isEquals(lineBase.dsOrdPosnNum_RL.getValue(), lineSub.dsOrdPosnNum_RL.getValue())
                            && S21StringUtil.isEquals(lineBase.cpoDtlLineNum_RL.getValue(), lineSub.cpoDtlLineNum_RL.getValue())
                            && NWAL1500CommonLogic.isSetMerchandise(bizMsg.glblCmpyCd.getValue(), lineSub.mdseCd_RL.getValue())) {
                        lineBase.xxChkBox_RL.setErrorInfo(1, NWAM0958E);
                        if (firstErrPage < 0) {
                            firstErrPage = lineBase.xxPageNum_RL.getValueInt();
                        }
                    }
                }
            }
        }

        if (firstErrPage != -1) {
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPage);
            ret = true;
        }

        return ret;
    }
    // 2018/06/14 S21_NA#25227 Add End

    // 2019/06/18 S21_NA#50732 Add Start
    private static boolean getOpenedRwsForRmaLine(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaLineMsg) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", rmaLineMsg.dsOrdPosnNum_RL.getValue());
        queryParam.put("cpoDtlLineNum", rmaLineMsg.cpoDtlLineNum_RL.getValue());
        queryParam.put("cpoDtlLineSubNum", rmaLineMsg.cpoDtlLineSubNum_RL.getValue());
        queryParam.put("openFlgY", ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineCancel.getInstance().getOpenedRwsForRmaLine(queryParam);

        return (ssmResult.getQueryResultCount() > 0);
    }
    // 2019/06/18 S21_NA#50732 Add End
}
