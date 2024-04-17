/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_LINE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_RMA_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_RMA_LINE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_FORMAT_REC_NUM_INFO;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SAVED;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SUBMITTED;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0780I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0874I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0955E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM8252E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NZZM0003E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM8100I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0973E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0977E;

import java.math.BigDecimal;
import java.net.ContentHandlerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1500.common.NWAL1500CommonLogic;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForDelete;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForDeleteShell;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForLineCancel;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForLineConfig;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForPagination;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForReport;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForSaveSubmit;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.db.CPOTMsg;
import business.db.CPO_RECTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CHNG_RSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_RPT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          N/A
 * 2016/02/10   Fujitsu         S.Takami        Update          S21_NA#1584 (Save Header)
 * 2016/02/18   Fujitsu         S.Takami        Update          S21_NA#2336
 * 2016/02/19   Fujitsu         S.Takami        Update          S21_NA#2166
 * 2016/02/19   Fujitsu         Y.Taoka         Update          S21_NA#1694
 * 2016/02/24   Fujitsu         M.Hara          Update          S21_NA#4078
 * 2016/02/24   Fujitsu         K.Sato          Update          S21_NA#3239
 * 2016/02/26   Fujitsu         M.Suzuki        Update          S21_NA#966
 * 2016/02/25   Fujitsu         K.Sato          Update          S21_NA#1729
 * 2016/03/17   Fujitsu         S.Takami        Update          S21_NA#5355
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/04/20   Fujitsu         S.Takami        Update          S21_NA#5605
 * 2016/05/09   Fujitsu         M.Yamada        Update          S21_NA#5764
 * 2016/05/13   Fujitsu         S.Takami        Update          S21_NA#7924
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#6440
 * 2016/06/03   Fujitsu         M.Yamada        Update          S21_NA#7968
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/08/09   Fujitsu         S.Takami        Update          S21_NA#4657
 * 2016/08/12   SRAA            K.Aratani       Update          S21_NA#13428
 * 2016/08/23   Fujitsu         S.Takami        Update          S21_NA#13504
 * 2016/08/24   Fujitsu         Y.Taoka         Update          S21_NA#11630
 * 2016/08/25   Fujitsu         Y.Taoka         Update          S21_NA#13769(S21_NA#8388)
 * 2016/08/31   Fujitsu         S.Takami        Update          S21_NA#10535
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/09/16   Fujitsu         Y.Taoka         Update          S21_NA#14319
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/10/13   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/20   Fujitsu         S.Iidaka        Update          S21_NA#14778
 * 2016/10/26   Fujitsu         S.Iidaka        Update          S21_NA#15327
 * 2016/11/02   Fujitsu         S.Iidaka        Update          S21_NA#15741
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749
 * 2016/11/29   Fujitsu         S.Ohki          Update          S21_NA#16214
 * 2016/12/07   Fujitsu         T.Yoshida       Update          S21_NA#16409
 * 2016/12/21   Fujitsu         S.Ohki          Update          S21_NA#16392
 * 2017/05/16   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 (Delete Logif For Copy)
 * 2017/08/01   Fujitsu         S.Takami        Update          S21_NA#19991
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 * 2017/09/22   Fujitsu         A.Kosai         Update          S21_NA#21058
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/14   Fujitsu         M.Ohno          Update          S21_NA#24117-1
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/08/30   Fujitsu         S.Takami        Update          S21_NA#26895-3
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/12/18   Fujitsu         K.Ishizuka      Update          S21_NA#29561
 * 2019/01/08   Fujitsu         Y.Kanefusa      Update          S21_NA#29693
 * 2019/01/11   Fujitsu         K.Ishizuka      Update          S21_NA#29811
 * 2019/05/24   Fujitsu         Mz.Takahashi    Update          QC#50043
 * 2019/07/30   Fujitsu         Mz.Takahashi    Update          QC#52207
 * 2019/09/18   Fujitsu         R.Nakamura      Update          S21_NA#53018
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2019/12/18   Fujitsu         S.Takami        Update          S21_NA#54223
 * 2020/01/17   Fujitsu         A.Kazuki        Update          QC#55202
 * 2022/03/24   Hitachi         K.Kishimoto     Update          QC#59825
 * 2022/07/14   Hitachi         K.Kishimoto     Update          QC#55675
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2022/09/12   Hitachi         K.Kishimoto     Update          QC#55675-1
 * 2023/10/13   Hitachi         D.Yoshida       Update          QC#61077
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * </pre>
 */
public class NWAL1500BL06 extends S21BusinessHandler {

    /** true: copy by db select insert false: copy by DS CPO Update API 2016/10/13 S21_NA#7924-2 Add */
    private static final boolean COPY_NEW_LOGIC = true;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
            NWAL1500SMsg glblMsg = (NWAL1500SMsg) sMsg;

            NWAL1500CommonLogic.clearDeleteActionStatus(bizMsg); // 2016/09/20 S21_NA#8279 Add
            bizMsg.setCommitSMsg(true);
            if ("NWAL1500Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_Cancel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_Cancel(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_CMN_Submit".equals(screenAplID)) {
                // 2016/02/26 S21_NA#1729 Mod Start
                if (cMsg.getMessageInfo() != null && cMsg.getMessageInfo().getCode().endsWith("W")) {
                    doNothing();
                } else {
                    doProcess_NWAL1500Scrn00_CMN_Submit(bizMsg, glblMsg, false, false);
                }

                // 2016/02/26 S21_NA#1729 Mod End
            } else if ("NWAL1500Scrn00_CMN_Save".equals(screenAplID)) {
                // 2016/02/23 S21_NA#3239 Mod Start
                if (cMsg.getMessageInfo() != null && cMsg.getMessageInfo().getCode().endsWith("W")) {
                    doNothing();
                } else {
                    doProcess_NWAL1500Scrn00_CMN_Save(bizMsg, glblMsg, false);
                }
                // 2016/02/23 S21_NA#3239 Mod End

                NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());

                NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
            } else if ("NWAL1500Scrn00_Order_Cancel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Order_Cancel(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL2090".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL2090(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Book".equals(screenAplID)) {
                // 2016/02/26 S21_NA#1729 Mod Start
                if (cMsg.getMessageInfo() != null && cMsg.getMessageInfo().getCode().endsWith("W")) {
                    doNothing();
                } else {
                    // QC#1694 / QC#4078 Mod
                    doProcess_NWAL1500Scrn00_CMN_Submit(bizMsg, glblMsg, false, false);
                }
                // 2016/02/26 S21_NA#1729 Mod End

                NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());

                NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
            } else if ("NWAL1500Scrn00_Delete".equals(screenAplID)) { // 2016/02/15 S21_NA#2336 temporarily
                doProcess_NWAL1500Scrn00_Delete(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL2000".equals(screenAplID)) { // 2016/02/19 S21_NA#2166 temporarily
                doProcess_NWAL1500_NWAL2000(bizMsg, glblMsg);
                // QC#4078
            } else if ("NWAL1500_NWAL1620".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1620(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_OrderEntryAction".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(bizMsg, glblMsg); // 2016/02/26 S21_NA#1694 
            // 2018/05/11 S21_NA#22139 Add Start
            } else if ("NWAL1500_NWAL1790".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1790(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL2420".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL2420(bizMsg, glblMsg);
            // 2018/05/11 S21_NA#22139 Add End
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1500Scrn00_Line_Cancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Cancel by Configuration Level.
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_LC", "Y");

        for (Integer selectRow : selectRows) {

            NWAL1500_ACMsg cancelConfigLineMsg = bizMsg.A.no(selectRow);
            String posnNum = cancelConfigLineMsg.dsOrdPosnNum_LC.getValue();

            // Cancel for relational Line(bizMsg).
            boolean setLineFlg = false;
            List<Integer> deleteLines = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (posnNum.equals(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                    deleteLines.add(i);
                    setLineFlg = true;
                } else {
                    if (setLineFlg) {
                        break;
                    }
                }
            }

            if (setLineFlg) {
                ZYPTableUtil.deleteRows(bizMsg.B, deleteLines);
            }

            // Cancel for relational Line(glblMsg).
            setLineFlg = false;
            deleteLines = new ArrayList<Integer>();
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (posnNum.equals(glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                    deleteLines.add(i);
                    setLineFlg = true;
                } else {
                    if (setLineFlg) {
                        break;
                    }
                }
            }

            if (setLineFlg) {
                ZYPTableUtil.deleteRows(glblMsg.B, deleteLines);
            }
        }
        if (!selectRows.isEmpty()) {
            ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
        }

        // Cancel by Line Level.
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", "Y");
        if (!selectRows.isEmpty()) {
            ZYPTableUtil.deleteRows(bizMsg.B, selectRows);
        }

        Map<String, String> bfAfLineNum = new HashMap<String, String>();

        // Renewal Configuration Line Number and Line Number.
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String posnNum = bizMsg.A.no(i).dsOrdPosnNum_LC.getValue();
            bizMsg.A.no(i).dsOrdPosnNum_LC.setValue(Integer.toString(i + 1));

            bfAfLineNum.put(posnNum, bizMsg.A.no(i).dsOrdPosnNum_LC.getValue());
        }

        // Update Line Number(bizMsg).
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            String posnNum = bizMsg.B.no(i).dsOrdPosnNum_LL.getValue();
            String firstIdx = bfAfLineNum.get(posnNum);

            boolean updateLineNumFlg = false;

            int scdIdx = 1;
            for (int j = i; j < bizMsg.B.getValidCount(); j++) {
                String oldPosnNum = bizMsg.B.no(j).dsOrdPosnNum_LL.getValue();
                if (posnNum.equals(oldPosnNum)) {
                    bizMsg.B.no(j).dsOrdPosnNum_LL.setValue(firstIdx);
                    bizMsg.B.no(j).dsCpoLineNum_LL.setValue(scdIdx);
                    bizMsg.B.no(j).xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(bizMsg.B.no(j)));
                    updateLineNumFlg = true;
                    i = j + 1;
                    scdIdx++;
                } else {
                    if (updateLineNumFlg) {
                        i = j - 1;
                        break;
                    }
                }
            }
        }

        // Update Line Number(glblMsg).
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            String posnNum = glblMsg.B.no(i).dsOrdPosnNum_LL.getValue();
            String firstIdx = bfAfLineNum.get(posnNum);

            boolean updateLineNumFlg = false;

            int scdIdx = 1;
            for (int j = i; j < glblMsg.B.getValidCount(); j++) {
                String oldPosnNum = glblMsg.B.no(j).dsOrdPosnNum_LL.getValue();
                if (posnNum.equals(oldPosnNum)) {
                    glblMsg.B.no(j).dsOrdPosnNum_LL.setValue(firstIdx);
                    glblMsg.B.no(j).dsCpoLineNum_LL.setValue(scdIdx);
                    glblMsg.B.no(j).xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(glblMsg.B.no(j)));
                    updateLineNumFlg = true;
                    i = j + 1;
                    scdIdx++;
                } else {
                    if (updateLineNumFlg) {
                        i = j - 1;
                        break;
                    }
                }
            }
        }
    }

    private void doProcess_NWAL1500Scrn00_CMN_Save(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isCopyOrd) {
        doProcess_NWAL1500Scrn00_CMN_Submit(bizMsg, glblMsg, true, isCopyOrd);
    }

    private void doProcess_NWAL1500Scrn00_CMN_Submit(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isSave, boolean isCopyOrd) {

        // 2018/01/31 S21_NA#19808 Add Start
        // Copy Current Page Data to Global Message
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        // 2018/01/31 S21_NA#19808 Add End

        // S21_NA#16409 Add Start
        if (!isCopyOrd && !ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)) {
            bizMsg.cpoOrdNum.clear();
        }
        // S21_NA#16409 Add End

        NWAL1500CommonLogicForSaveSubmit commonLogic = NWAL1500CommonLogicForSaveSubmit.getInstance();
        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(glblMsg); // 2018/01/31 S21_NA#19808

        NWAL1500CommonLogic.clearMeaninglesDetail(bizMsg, glblMsg); // 2018/01/31 S21_NA#19808 Mod
        // 2017/10/13 S21_NA#21267 Add End

        if (!isOrderCredit) { // 2017/10/13 S21_NA#21267 Add Condition
            commonLogic.clearCode(bizMsg, glblMsg); // 2018/01/31 S21_NA#19808 Mod

            NWAL1500CommonLogic.prepareSetToConponent(glblMsg); // S21_NA#2522 // 2018/01/31 S21_NA#19808 Mod

            if (!NWAL1500CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
                return;
            }
            if (!NWAL1500CommonLogic.checkMandatoryAddlHeaderTab(bizMsg)) {
                return;
            }
            //QC#13428
            if (!NWAL1500CommonLogic.checkLineCountLimitation(bizMsg, glblMsg)) {
                return;
            }
            if (!NWAL1500CommonLogic.checkMandatoryLineConfigTab(bizMsg, glblMsg)) {
                return;
            }
            if (!NWAL1500CommonLogic.checkMandatoryRMATab(bizMsg, glblMsg)) {
                return;
            }
        } else { // 2018/12/18 S21_NA#29561 Add Start
            if (!NWAL1500CommonLogic.chkMndRMA4Credit(bizMsg, glblMsg)) {
                return;
            }
        } // 2018/12/18 S21_NA#29561 Add End

        // 2016/08/09 S21_NA#4657 Add Start
        boolean isSaveOrSubmitted = isSave;
        boolean isOrdValidated = false; // 2016/09/05 S21_NA#6523 Add
        // 2017/08/01 S21_NA#19991 Del Start
//        // 2016/10/26 S21_NA#15327 Add Start
//        boolean isFirstBook = false;
//        // 2017/07/25 S21_NA#19991 Mod Start
////        if (!ZYPCommonFunc.hasValue(bizMsg.ordBookReqTsDplyTxt)) {
////            isFirstBook = true;
////        }
//        if (!ZYPCommonFunc.hasValue(bizMsg.ordBookTsDplyTxt)) {
//            isFirstBook = true;
//        }
//        // 2017/07/25 S21_NA#19991 Mod Start
        // 2016/10/26 S21_NA#15327 Add End
        // 2017/08/01 S21_NA#19991 Del End
        if (isSave && ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            cpoTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTMsg);
            // boolean isOrdValidated = false; 2016/09/05 S21_NA#6523 Del
            if (cpoTMsg != null) {
                // START 2023/10/13 [QC#61077, ADD]
                // if category or reason changed. wf will be rejected.
                if ((!S21StringUtil.isEquals(cpoTMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdCatgCd.getValue())) || (!S21StringUtil.isEquals(cpoTMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdTpCd.getValue()))) {
                    ZYPEZDItemValueSetter.setValue(cpoTMsg.wfRejFlg, ZYPConstant.FLG_ON_Y);
                }
                // END   2023/10/13 [QC#61077, ADD]
                // 2019/07/30 QC#52207 Mod Start
                if (!ZYPConstant.FLG_ON_Y.equals(cpoTMsg.wfRejFlg.getValue())){
                    isOrdValidated = S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, cpoTMsg.ordHdrStsCd.getValue());
                }
                //isOrdValidated = S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, cpoTMsg.ordHdrStsCd.getValue());
                // 2019/07/30 QC#52207 Mod End
            }
            if (isSave && isOrdValidated) {
                isSaveOrSubmitted = false;
            }
        }
        // 2016/08/09 S21_NA#4657 Add End
        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add Condition !isOrderCredit
                && !commonLogic.checkCustPoFields(bizMsg, isSaveOrSubmitted)) { // 2016/08/09 S21_NA#4657 Add 2nd Parameter
            return;
        }

        // 2017/09/22 S21_NA#21058 Del Start
        //if (!commonLogic.checkCustPoDt(bizMsg)) {
        //    return;
        //}
        // 2017/09/22 S21_NA#21058 Del End

        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add Condition !isOrderCredit
                && !commonLogic.checkCustSgnDt(bizMsg)) {
            return;
        }

        // QC#4078 Mod
        if (!isCopyOrd //
                && !isOrderCredit) { // 2017/10/13 S21_NA#21267 Add Condition !isOrderCredit
            // 2016/02/25 S21_NA#1729 Add Start
            if (!commonLogic.checkCustIssPoNum(bizMsg)) {
                return;
            }

            if (!commonLogic.checkCntLeaseCmpyPoNum(bizMsg)) {
                return;
            }
            // 2016/02/25 S21_NA#1729 Add End
        }

        // 2016/02/10 S21_NA#1584 Add Start
        boolean isHdrSave = isHeaderSave(bizMsg, isSaveOrSubmitted); // 2016/10/20 S21_NA#14778 Mod 
        // 2016/02/10 S21_NA#1584 Add End

        if (!isHdrSave) { // 2016/02/10 S21_NA#1584 Add Condition
            if (glblMsg.A.getValidCount() == 0 && glblMsg.C.getValidCount() == 0) { // 2018/01/31 S21_NA#19808 Mod
                bizMsg.setMessageInfo(NWAM8252E);
                return;
            }
        } // 2016/02/10 S21_NA#1584 Add Condition

        if (commonLogic.isHeaderTabMasterError(bizMsg, !isSaveOrSubmitted)) {     // 2016/10/20 S21_NA#14778 Mod 
            return;
        }
        if (commonLogic.isAddlHeaderTabMasterError(bizMsg, !isSaveOrSubmitted)) { // 2016/10/20 S21_NA#14778 Mod 
            return;
        }

        int[] cntArray = {0, 0, 0, 0, 0};

        // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
        // 2016/02/10 S21_NA#1584 Add Start
//        if (isHdrSave) {
//            // 20160509 S21_NA#5764
//            if (commonLogic.checkBillShipSoldRelation(bizMsg)) {
//                return; // error
//            }
//
//            NWAL1500CommonLogicForSaveHeader.saveHeader(bizMsg);
//            // QC#4078
//            if (isCopyOrd) {
//                NWAL1500CommonLogicForSaveHeader.saveHeaderForCopyHdr(bizMsg);
//            }
//            cntArray[CNT_HEADER] = 1;
//            outputSaveSubmitMsg(bizMsg, isSaveOrSubmitted, cntArray); // 2016/10/20 S21_NA#14778 Mod 
//            return;
//        }
        // 2016/02/10 S21_NA#1584 Add End
        // 2017/04/25 S21_NA#Review structure Lv.2 Del End

        if (commonLogic.isLineConfigTabMasterError(bizMsg, glblMsg, !isSaveOrSubmitted)) { // 2016/10/20 S21_NA#14778 Mod // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        if (commonLogic.isRMATabMasterError(bizMsg, glblMsg, !isSaveOrSubmitted)) {        // 2016/10/20 S21_NA#14778 Mod // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        // QC#29693 2019/01/08 Add Start
        if (commonLogic.isLineConfigTabPrcAmtError(bizMsg, glblMsg)) {
            return;
        }
        if (commonLogic.isRMATabPrcAmtError(bizMsg, glblMsg)) {
            return;
        }
        // QC#29693 2019/01/08 Add End
        // 2016/11/08 S21_NA#7749 Add Start
        if (commonLogic.isAllCmptReqErrOnLineConfig(bizMsg, glblMsg)) { // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        // 2016/11/08 S21_NA#7749 Add End
        // NWAL1500CommonLogic.numberingOrderRma(bizMsg); 2016/08/23 S21_NA#13504 Del
        if (!isOrderCredit) { // 2017/10/13 S21_NA#21267 Add Condition
            commonLogic.renumberForSave(bizMsg, glblMsg); // 2016/08/23 S21_NA#13504 Add // 2018/01/31 S21_NA#19808 Mod
        } // 2017/10/13 S21_NA#21267 Add Condition

        // 2018/08/30 S21_NA#26895-3 Del Start
//        // 2018/07/09 S21_NA#26895 Add Start
//        if (glblMsg.A.getValidCount() > 0) {
//            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, CONFIG_CATG.OUTBOUND);
//        }
//        if (glblMsg.C.getValidCount() > 0) {
//            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, CONFIG_CATG.INBOUND);
//        }
//        // 2018/07/09 S21_NA#26895 Add End
        // 2018/08/30 S21_NA#26895-3 Del End
        // 2019/01/11 S21_NA#29811 Add Start
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue(), CONFIG_CATG.INBOUND);
        }
        // 2019/01/11 S21_NA#29811 Add End

        // QC#2649 Del Start
        // 3-1) Sales Credit Data
        // NWAL1500CommonLogicForSalesCredit.checkAndSetSalesCredit(bizMsg, glblMsg);
        // QC#2649 Del End

        // Add Start 2019/09/18 QC#53018
        if (NWAL1500CommonLogic.checkBillOnlyLineErr(bizMsg, glblMsg)) {
            return;
        }
        // Add End 2019/09/18 QC#53018

        // 3-2) CSMP Price List
        // commonLogic.setCsmpPrcList(bizMsg, glblMsg); 2016/08/31 S21_NA#10535 Del
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // 4-1) Changing config Type
        // 2018/03/14 S21_NA#24117-1 add start
        // add Software Parent Dtl
        commonLogic.setSoftModelParentDtl(bizMsg, glblMsg);
        // 2018/03/14 S21_NA#24117-1 mod end
        if (!isOrderCredit) { // 2017/10/13 S21_NA#21267 Add Condition
            commonLogic.checkConfigType(bizMsg, glblMsg); // 2018/01/31 S21_NA#19808 Mod
        } // 2017/10/13 S21_NA#21267 Add Condition

        // 5) Relation Check
        // 5-1) Header Tab Check
//        if (commonLogic.isRelationErrOnHeader(bizMsg)) {
//            bizMsg.xxDplyTab.setValue(TAB_HEADER);
//            return;
//        }
        // 5-2) Additinal Header Tab Check
//        if (commonLogic.isRelationErrOnAddlHeader(bizMsg)) {
//            bizMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
//            return;
//        }
        // 5-3) Line Config
        // QC#4078 Mod
//        if (commonLogic.isRelationErrOnLineConfig(bizMsg, glblMsg, isSave, !isCopyOrd)) { 2016/09/05 S21_NA#6523 Mod
        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add !isOrderCredit
                && commonLogic.isRelationErrOnLineConfig(bizMsg, glblMsg, isSave, !isCopyOrd, isOrdValidated)) { // 2016/09/05 S21_NA#6523 Mod. Add "isOrdValidated"
//            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
            return;
        }
        // 2016/03/17 S21_NA#5355 Add Start
        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add !isOrderCredit
                && commonLogic.isRelationErrOnRMA(bizMsg, glblMsg)) { // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        // 2016/03/17 S21_NA#5355 Add End
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }

        // 6) Supersession
        // 2016/02/19 S21_NA#3649 Del Start
//        commonLogic.setSuperSessionMdse(bizMsg);
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }
        // 2016/02/19 S21_NA#3649 Del End

        // S21_NA#13769(S21_NA#8388) ADD START
        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add !isOrderCredit
                && !commonLogic.checkDclnSvc(bizMsg, glblMsg)) { // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        // S21_NA#13769(S21_NA#8388) ADD END

        // 2016/12/21 S21_NA#16392 Add Start
        if (!isOrderCredit // 2017/10/13 S21_NA#21267 Add !isOrderCredit
                && !commonLogic.checkRelnRsnAndWh(bizMsg, glblMsg)) { // 2018/01/31 S21_NA#19808 Mod
            return;
        }
        // 2016/12/21 S21_NA#16392 Add End

        // 2018/10/25 S21_NA#28897 Add Start
        if (!isOrderCredit
                && !commonLogic.checkChangeWhForPendingReturn(bizMsg, glblMsg)) {
            return;
        }
        // 2018/10/25 S21_NA#28897 Add End

        // 2019/10/03 QC#53595 Add Start
        if (!commonLogic.checkISOrderBindToISGroup(bizMsg)) {
            bizMsg.setMessageInfo(NWAM0973E);
            bizMsg.psnNum.setErrorInfo(1, NWAM0973E);
            return;
        }
        // 2019/10/03 QC#53595 Add End

        // ADD START 2022/08/19 H.Watanabe [QC#60255]
        if (!commonLogic.checkCarrAcctNumValidation(bizMsg)) {
            return;
        }
        // ADD END   2022/08/19 H.Watanabe [QC#60255]

        // check data were updated by other user
        if (NWAL1500CommonLogic.isModifiedByOtherUser(bizMsg, glblMsg)) {
            bizMsg.setMessageInfo("NWAM0429E");
            return;
        }
        // S21_NA#14319 ADD START
        if (NWAL1500CommonLogic.isModifiedByOtherUserForOutbound(bizMsg, glblMsg)) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }
        if (NWAL1500CommonLogic.isModifiedByOtherUserForInbound(bizMsg, glblMsg)) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }
        // S21_NA#14319 ADD END
        CPOTMsg cpoMsg = null;
        boolean isModifyReq = false;
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            cpoMsg = lockCpo(bizMsg);
            if (cpoMsg == null) {
                return;
            }
            isModifyReq = ZYPConstant.FLG_ON_Y.equals(cpoMsg.submtFlg.getValue());

            // if (!lockShpgPln(bizMsg, sMsg)) {
            // return;
            // }
        }

        // 2019/12/18 S21_NA#54223 Add Start
        NWAL1500CommonLogicForDeleteShell.deleteRelatedShell(bizMsg, glblMsg);
        // 2019/12/18 S21_NA#54223 Add End
        
        // Add Start 2020/01/17 QC#55202
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if(commonLogic.checkInvalidDropVendorReturn(
                    bizMsg.glblCmpyCd.getValue(), glblMsg, glblMsg.D.no(i).mdseCd_RL.getValue(), glblMsg.D.no(i).rtlWhNm_RL.getValue())){
                glblMsg.D.no(i).mdseCd_RL.setErrorInfo(1, NWAM0977E);
                return;
            }
        }
        // Add End   2020/01/17 QC#55202

        // 2023/11/06 QC#61924 Add Start
        if (!commonLogic.hasDeactivateAccountOrLocation(bizMsg, glblMsg)) {
            return;
        }
        // 2023/11/06 QC#61924 Add End

        // QC#4078 Mod
//        boolean isNormal = commonLogic.callDsCpoUpdateApi(bizMsg, glblMsg, isSave, isModifyReq, isCopyOrd);
        // 2016/10/20 S21_NA#14778 Mod 
//        boolean isNormal = commonLogic.callDsCpoUpdateApi(bizMsg, glblMsg, isSave, isModifyReq, isCopyOrd, cntArray);
        boolean isNormal = commonLogic.callDsCpoUpdateApi(bizMsg, glblMsg, isSaveOrSubmitted, isModifyReq, cntArray);

        // Allocation
        if (isNormal) {
            // 2017/08/01 S21_NA#19991 Del Start QC#19991, Allocation Process was deleted.
//            // 2016/11/02 S21_NA#15741 Mod Start
//            if (isFirstBook) {
//                commonLogic.bookedOrderAllocation(bizMsg);
//            }
//            // 2016/11/02 S21_NA#15741 Mod End
            // 2017/08/01 S21_NA#19991 Del End

            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            outputSaveSubmitMsg(bizMsg, isSaveOrSubmitted, cntArray); // 2016/10/20 S21_NA#14778 Mod
//            bizMsg.setMessageInfo(ZZM8100I);
        }
    }

    private void outputSaveSubmitMsg(NWAL1500CMsg bizMsg, boolean isSave, int[] cntArray) {
        String funcStr = "";
        if (isSave) {
            funcStr = MSG_PARAM_SAVED;
        } else {
            funcStr = MSG_PARAM_SUBMITTED;
        }
        // Header + Config + Line + RMA Config + RMA Line.
        int num = cntArray[CNT_HEADER] + cntArray[CNT_CONFIG] + cntArray[CNT_LINE] + cntArray[CNT_RMA_CONFIG] + cntArray[CNT_RMA_LINE];
        String msg = String.format(MSG_FORMAT_REC_NUM_INFO, cntArray[CNT_HEADER], cntArray[CNT_CONFIG], cntArray[CNT_LINE], cntArray[CNT_RMA_CONFIG], cntArray[CNT_RMA_LINE]);
        bizMsg.setMessageInfo(NWAM0780I, new String[] {funcStr
                , String.valueOf(num)
                , msg
                });

    }
    private void doProcess_NWAL1500Scrn00_Order_Cancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(bizMsg.ezUpTime)) {
            if (NWAL1500CommonLogic.isModifiedByOtherUser(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
                return;
            }
            if (NWAL1500CommonLogic.isModifiedByOtherUserForOutbound(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
                return;
            }
            if (NWAL1500CommonLogic.isModifiedByOtherUserForInbound(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
                return;
            }
            // 2017/10/04 S21_NA#21300 Mod Start
//            if (!NWAL1500CommonLogic.isEnableToCancelForOutbound(bizMsg, glblMsg)) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0672E);
//                return;
//            }
            String errStr = NWAL1500CommonLogic.isEnableToCancelForOutbound(bizMsg, glblMsg);
            if (!errStr.isEmpty()) {
                bizMsg.setMessageInfo(errStr);
                return;
            }
            // 2017/10/04 S21_NA#21300 Mod End
            if (!NWAL1500CommonLogic.isEnableToCancelForInbound(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0854E); // S21_NA#7968
                return;
            }
            if (bizMsg.A.getValidCount() > 0 || bizMsg.C.getValidCount() > 0) {
                CPOTMsg cpoMsg = lockCpo(bizMsg);
                if (cpoMsg == null) {
                    return;
                }
                NWAL1500CommonLogicForSaveSubmit.getInstance().callDsCpoUpdateApiForOrderCancel(bizMsg, glblMsg);
                // START 09/12/2022 [QC#55675-1, ADD]
                // print Biz Log
                S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

                bizLogMsg.setSubSysId(NWAL1500Constant.SUB_SYS_ID);
                bizLogMsg.setProcId(NWAL1500Constant.PROCESS_ID);
                bizLogMsg.setDocTpCd(NWAL1500Constant.DOCUMENT_TYPE);
                bizLogMsg.setEventId(NWAL1500Constant.EVENT_ID_ORDER_CANCEL);
                bizLogMsg.setDocId("");
                bizLogMsg.setPrntDocId(bizMsg.cpoOrdNum.getValue());
                bizLogMsg.setBizProcCmntTxt_01("");
                bizLogMsg.setBizProcCmntTxt_02("");
                if (ZYPCommonFunc.hasValue(bizMsg.chngRsnTpCd_P1)) {
                    bizLogMsg.setBizProcCmntTxt_01(ZYPCodeDataUtil.getName(CHNG_RSN_TP.class, bizMsg.glblCmpyCd.getValue(), bizMsg.chngRsnTpCd_P1.getValue()));
                    bizLogMsg.setBizProcCmntTxt_02(bizMsg.bizProcCmntTxt_P1.getValue());
                }
                // END   09/12/2022 [QC#55675-1, ADD]

                S21BusinessProcessLog.print(bizLogMsg);

            } else {
                // Update cpo
                if (!NWAL1500CommonLogicForSaveSubmit.getInstance().updateCpoForCancel(bizMsg)) {
                    return;
                }
                // print Biz Log
                S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

                bizLogMsg.setSubSysId(NWAL1500Constant.SUB_SYS_ID);
                bizLogMsg.setProcId(NWAL1500Constant.PROCESS_ID);
                bizLogMsg.setDocTpCd(NWAL1500Constant.DOCUMENT_TYPE);
                bizLogMsg.setEventId(NWAL1500Constant.EVENT_ID_ORDER_CANCEL);
                bizLogMsg.setDocId("");
                bizLogMsg.setPrntDocId(bizMsg.cpoOrdNum.getValue());
                bizLogMsg.setBizProcCmntTxt_01("");
                bizLogMsg.setBizProcCmntTxt_02("");
                // START 07/14/2022 [QC#55675, ADD]
                if (ZYPCommonFunc.hasValue(bizMsg.chngRsnTpCd_P1)) {
                    bizLogMsg.setBizProcCmntTxt_01(ZYPCodeDataUtil.getName(CHNG_RSN_TP.class, bizMsg.glblCmpyCd.getValue(), bizMsg.chngRsnTpCd_P1.getValue()));
                    bizLogMsg.setBizProcCmntTxt_02(bizMsg.bizProcCmntTxt_P1.getValue());
                }
                // END   07/14/2022 [QC#55675, ADD]

                S21BusinessProcessLog.print(bizLogMsg);

                // get CPO
                CPOTMsg cpoTMsg = NWAL1500CommonLogic.getCpo(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());

                S21SsmEZDResult ssmResult = NWAL1500QueryForSaveSubmit.getInstance().getBizProcLogPK(bizMsg.glblCmpyCd.getValue(), cpoTMsg, NWAL1500Constant.EVENT_ID_ORDER_CANCEL);
                if (!ssmResult.isCodeNormal()) {
                    // Error
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0299E, new String[] {"BIZ_PROC_LOG_PK", "BIZ_PROC_LOG" });
                    return;
                }
                BigDecimal bizProcLogPK = (BigDecimal) ssmResult.getResultObject();

                CPO_RECTMsg cpoRecTMsg = new CPO_RECTMsg();
                EZDMsg.copy(cpoTMsg, null, cpoRecTMsg, null);
                ZYPEZDItemValueSetter.setValue(cpoRecTMsg.bizProcLogPk, bizProcLogPK);
                S21FastTBLAccessor.create(cpoRecTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoRecTMsg.getReturnCode())) {
                    // Error
                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_REC", bizProcLogPK.toString() });
                    return;
                }
            }
        }
    }

    private void doProcess_NWAL1500_NWAL2090(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2017/05/08 S21_NA#Review structure Lv.2 Mod Start
        NWAL1500CommonLogicForSaveSubmit.getInstance().callDsCpoUpdateApiForChngOrdModify(bizMsg, glblMsg);

        if (!"E".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0677I, new String[] {bizMsg.cpoOrdNum.getValue() });
        }
        // 2017/05/08 S21_NA#Review structure Lv.2 Mod End

        // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//        boolean resultFlg = false;
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
//
//        CPOTMsg cpoTMsg = NWAL1500CommonLogic.getCpo(glblCmpyCd, cpoOrdNum);
//        if (cpoTMsg == null) {
//            bizMsg.setMessageInfo(NWAL1500MsgConstant.ZZZM9006E, new String[] {"CPO" });
//            return;
//        }
//
//        DS_CPO_SLS_CRTMsgArray dsCposlsCrAry = NWAL1500CommonLogic.getCposlsCrAryHdr(glblCmpyCd, cpoOrdNum);
//
//        DS_CPO_ISTL_INFOTMsgArray dsCpoIstlInfoAry = NWAL1500CommonLogic.getdsCpoIstlInfoHdr(glblCmpyCd, cpoOrdNum);
//
//        DS_CPO_DELY_INFOTMsgArray dsCpoDelyInfoAry = NWAL1500CommonLogic.getdsCpoDelyInfoHdr(glblCmpyCd, cpoOrdNum);
//
//        DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnAry = NWAL1500CommonLogic.getDsCpoCtacPsn(glblCmpyCd, cpoOrdNum);
//
//        DS_SITE_SRVYTMsgArray dsSiteSrvyTMsgAry = NWAL1500CommonLogic.getDsSiteSrvyTMsgAry(glblCmpyCd, cpoOrdNum);
//
//        // S21_NA#6440 Mod
//        //final String newCpoOrdNum = ZYPNumbering.getUniqueID(glblCmpyCd, "CPO#");
//        final String newCpoOrdNum = ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM");
//
//        resultFlg = NWAL1500CommonLogic.registerCpoForChngOrdModify(bizMsg, newCpoOrdNum, cpoTMsg, this.getContextUserInfo().getUserId());
//        if (resultFlg) {
//            return;
//        }
//        resultFlg = NWAL1500CommonLogic.registerDsCposlsCrForChngOrdModify(bizMsg, newCpoOrdNum, dsCposlsCrAry);
//        if (resultFlg) {
//            return;
//        }
//        resultFlg = NWAL1500CommonLogic.registerDsCpoIstlInfoForChngOrdModify(bizMsg, newCpoOrdNum, dsCpoIstlInfoAry);
//        if (resultFlg) {
//            return;
//        }
//        resultFlg = NWAL1500CommonLogic.registerDsCpoDelyInfoForChngOrdModify(bizMsg, newCpoOrdNum, dsCpoDelyInfoAry);
//        if (resultFlg) {
//            return;
//        }
//        resultFlg = NWAL1500CommonLogic.registerDsCpoCtacPsnForChngOrdModify(bizMsg, newCpoOrdNum, dsCpoCtacPsnAry);
//        if (resultFlg) {
//            return;
//        }
//        resultFlg = NWAL1500CommonLogic.registerDsSiteSrvyForChngOrdModify(bizMsg, newCpoOrdNum, dsSiteSrvyTMsgAry);
//        if (resultFlg) {
//            return;
//        }
//        // 2016/02/25 S21_NA#966 Add Start-----------
//        NWAL1500CommonLogic.registAttachComment(bizMsg, newCpoOrdNum);
//        // 2016/02/25 S21_NA#966 Add End-------------
//
//        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
//        bizLogMsg.setSubSysId(NWAL1500Constant.SUB_SYS_ID);
//        bizLogMsg.setProcId(NWAL1500Constant.PROCESS_ID);
//        bizLogMsg.setDocTpCd(NWAL1500Constant.DOCUMENT_TYPE);
//        bizLogMsg.setEventId(NWAL1500Constant.EVENT_ID_CHNG_ORD_MODIFY);
//        bizLogMsg.setDocId("");
//        bizLogMsg.setPrntDocId(newCpoOrdNum);
//        bizLogMsg.setBizProcCmntTxt_01("");
//        bizLogMsg.setBizProcCmntTxt_02("");
//
//        S21BusinessProcessLog.print(bizLogMsg);
//        // get CPO
//        cpoTMsg = NWAL1500CommonLogic.getCpo(bizMsg.glblCmpyCd.getValue(), newCpoOrdNum);
//        dsCposlsCrAry = NWAL1500CommonLogic.getCposlsCrAryHdr(glblCmpyCd, newCpoOrdNum);
//
//        S21SsmEZDResult ssmResult = NWAL1500QueryForSaveSubmit.getInstance().getBizProcLogPK(bizMsg.glblCmpyCd.getValue(), cpoTMsg, NWAL1500Constant.EVENT_ID_CHNG_ORD_MODIFY);
//        if (!ssmResult.isCodeNormal()) {
//            // Error
//            bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0299E, new String[] {"BIZ_PROC_LOG_PK", "BIZ_PROC_LOG" });
//            return;
//        }
//        BigDecimal bizProcLogPK = (BigDecimal) ssmResult.getResultObject();
//
//        CPO_RECTMsg cpoRecTMsg = new CPO_RECTMsg();
//        EZDMsg.copy(cpoTMsg, null, cpoRecTMsg, null);
//        ZYPEZDItemValueSetter.setValue(cpoRecTMsg.bizProcLogPk, bizProcLogPK);
//        // 2016/02/25 S21_NA#966 Mod Start-----------
//        S21FastTBLAccessor.create(cpoRecTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
//        // 2016/02/25 S21_NA#966 Mod End-----------
//            // Error
//            bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO_REC", bizProcLogPK.toString() });
//            return;
//        }
//
//        DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecTMsg = new DS_CPO_SLS_CR_RECTMsg();
//        if (dsCposlsCrAry != null) {
//            for (int i = 0; i < dsCposlsCrAry.getValidCount(); i++) {
//                DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg = dsCposlsCrAry.no(i);
//                EZDMsg.copy(dsCpoSlsCrTMsg, null, dsCpoSlsCrRecTMsg, null);
//                // 2016/02/25 S21_NA#966 Add Start---------
//                ZYPEZDItemValueSetter.setValue(dsCpoSlsCrRecTMsg.bizProcLogPk, bizProcLogPK);
//                S21FastTBLAccessor.insert(dsCpoSlsCrRecTMsg);
//                if (!S21FastTBLAccessor.RTNCD_NORMAL.endsWith(dsCpoSlsCrRecTMsg.getReturnCode())) {
//                // 2016/02/25 S21_NA#966 Add End-----------
//                    bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_SLS_CR_REC", bizProcLogPK.toString() });
//                    return;
//                }
//            }
//        }
//        // 2016/02/25 S21_NA#966 Mod Start-------
//        //String oldCpoOrdNum = bizMsg.cpoOrdNum.getValue();
//        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, newCpoOrdNum);
//        bizMsg.setMessageInfo(NWAL1500MsgConstant.NWAM0677I, new String[] {newCpoOrdNum });
//        // 2016/02/25 S21_NA#966 Mod End---------
        // 2017/05/08 S21_NA#Review structure Lv.2 Del End
    }

    private CPOTMsg lockCpo(NWAL1500CMsg bizMsg) {

        // CPO
        CPOTMsg cpoMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0"));
        cpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cpoMsg);
        if (cpoMsg == null) {
            bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
            return null;
        }

        // String findEzUpTime = bizMsg.ezUpTime_K1.getValue();
        // String findEzUpTimeZone =
        // bizMsg.ezUpTimeZone_K1.getValue();
        // String cpoEzUpTime = cpoMsg.ezUpTime.getValue();
        // String cpoEzUpTimeZone = cpoMsg.ezUpTimeZone.getValue();
        //
        // if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime,
        // findEzUpTimeZone, cpoEzUpTime, cpoEzUpTimeZone)) {
        // bizMsg.setMessageInfo("NZZM0003E");
        // return null;
        // }

        return cpoMsg;
    }

 // Logic For Moack =>
//    private void setCpoUpdApiReqPMsg(String xxRqstTpCd, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg) {
//
//        String cpoOrdNum = null;
//        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            cpoOrdNum = ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0");
//        }
//
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, xxRqstTpCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTpCd, bizMsg.cpoOrdTpCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, bizMsg.custIssPoNum.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, bizMsg.custIssPoDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, bizMsg.ordFuflLvlCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.billToCustCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.sellToCustCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, bizMsg.adminPsnCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.payerCustCd, bizMsg.billToCustCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, bizMsg.dropShipFlg.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, bizMsg.shipToCustCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, bizMsg.shipToLocNm.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, bizMsg.shipToAddlLocNm.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, bizMsg.shipToFirstLineAddr.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, bizMsg.shipToScdLineAddr.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, bizMsg.shipToThirdLineAddr.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, bizMsg.shipToFrthLineAddr.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, bizMsg.shipToCtyAddr.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, bizMsg.shipToStCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, bizMsg.shipToProvNm.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, bizMsg.shipToPostCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, bizMsg.shipToCtryCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, bizMsg.shipToCntyNm.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, bizMsg.shipToFirstRefCmntTxt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, bizMsg.shipToScdRefCmntTxt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_ORDER);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, bizMsg.dsOrdRsnCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.invRcpntCustCd, bizMsg.billToCustCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, bizMsg.billToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, bizMsg.soldToCustLocCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, bizMsg.negoDealAmt);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsRepTocCd, bizMsg.slsRepTocCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, bizMsg.prcCatgDescTxt);
//        ZYPEZDItemValueSetter.setValue(pMsg.aquNum, bizMsg.aquNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, bizMsg.ordSrcImptDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, bizMsg.ordSrcRefNum);
////        ZYPEZDItemValueSetter.setValue(pMsg.assnPgmCd, bizMsg.assnPgmCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.assnPgmCd, bizMsg.prcContrNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, bizMsg.loanPerDaysAot);
////        ZYPEZDItemValueSetter.setValue(pMsg.mktgPgmNum, bizMsg.mktgPgmNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.mktgPgmNum, bizMsg.csmpContrNum);
////        ZYPEZDItemValueSetter.setValue(pMsg.leaseEndTermPrchOptCd, bizMsg.leaseEndTermPrchOptCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.leaseEndTermPrchOptCd, bizMsg.leasePrchOptCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.leaseTermCd, bizMsg.leaseTermCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, bizMsg.leasePmtFreqCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, bizMsg.ordSgnDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, bizMsg.carrAcctNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, bizMsg.flPrcListCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
//        // ZYPEZDItemValueSetter.setValue(pMsg.addRddDt,
//        // bizMsg.rddDt);
//
//    }
//
//    private void setConfigUpdApiReqDtlPMsg(String dsCpoDtlSubmit, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500_ACMsg lineMsg) {
//
//        final int dtlPMsgCount = pMsg.B.getValidCount();
//        final NWZC150001_BPMsg dtlPMsg = pMsg.B.no(dtlPMsgCount);
//
//        String cpoOrdNum = null;
//        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            cpoOrdNum = ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0");
//        }
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdNum, cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum, lineMsg.dsOrdPosnNum_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.configTpCd, lineMsg.configTpCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk, lineMsg.svcConfigMstrPk_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdlId, lineMsg.mdlId_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdlDescTxt, lineMsg.mdlDescTxt_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.billToCustAcctCd, bizMsg.billToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.billToCustLocCd, lineMsg.billToCustCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustLocCd, lineMsg.shipToCustCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg, lineMsg.dropShipFlg_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm, lineMsg.shipToLocNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm, lineMsg.shipToAddlLocNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr, lineMsg.shipToFirstLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr, lineMsg.shipToScdLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr, lineMsg.shipToThirdLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr, lineMsg.shipToFrthLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt, lineMsg.shipToFirstRefCmntTxt_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt, lineMsg.shipToScdRefCmntTxt_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr, lineMsg.shipToCtyAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd, lineMsg.shipToStCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm, lineMsg.shipToProvNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm, lineMsg.shipToCntyNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd, lineMsg.shipToPostCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd, lineMsg.shipToCtryCd_LC);
//
//        pMsg.B.setValidCount(dtlPMsgCount + 1);
//    }
//
//    private void setCpoUpdApiReqDtlPMsg(String xxRqstTpCd, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
//
//        String[] lineNums = lineMsg.xxLineNum_LL.getValue().split("\\.", 0);
//        int configLineIdx = 0;
//
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//
//            NWAL1500_ACMsg configLineMsg = bizMsg.A.no(i);
//            if (configLineMsg.dsOrdPosnNum_LC.getValue().equals(lineNums[0])) {
//                configLineIdx = i;
//                break;
//            }
//        }
//
//        // Copy
//        ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, lineMsg.entCpoDtlDealSlsAmt_LL);
//
//        NWAL1500_ACMsg configLineMsg = bizMsg.A.no(configLineIdx);
//
//        final int dtlPMsgCount = pMsg.A.getValidCount();
//
//        final NWZC150001_APMsg dtlPMsg = pMsg.A.no(dtlPMsgCount);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, xxRqstTpCd);
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdTpCd_A1, bizMsg.cpoOrdTpCd);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd, bizMsg.slsRepTocCd);
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg, configLineMsg.dropShipFlg_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd, configLineMsg.shipToCustCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm, configLineMsg.shipToLocNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm, configLineMsg.shipToAddlLocNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr, configLineMsg.shipToFirstLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr, configLineMsg.shipToScdLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr, configLineMsg.shipToThirdLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr, configLineMsg.shipToFrthLineAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr, configLineMsg.shipToCtyAddr_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd, configLineMsg.shipToStCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm, configLineMsg.shipToProvNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd, configLineMsg.shipToPostCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd, configLineMsg.shipToCtryCd_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm, configLineMsg.shipToCntyNm_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt, configLineMsg.shipToFirstRefCmntTxt_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt, configLineMsg.shipToScdRefCmntTxt_LC);
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum, lineMsg.cpoDtlLineNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum, lineMsg.cpoDtlLineSubNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd, lineMsg.mdseCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty, lineMsg.ordQty_LL);
//        // ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd,
//        // lineMsg.invtyLocCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd, lineMsg.rtlWhCd_LL.getValue() + lineMsg.rtlSwhCd_LL.getValue());
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt, lineMsg.entDealNetUnitPrcAmt_LL);
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd, CCY.US_DOLLAR);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, STK_STS.GOOD);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgToCd, FRT_CHRG_TO.CUSTOMER);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgMethCd, FRT_CHRG_METH.INVOICE_IN);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.manPrcFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd, "WS0");
//
//        NWZC150001_priceListPMsg priceList = pMsg.priceList.no(dtlPMsgCount);
//        priceList.cpoDtlLineNum.setValue(lineMsg.cpoDtlLineNum_LL.getValue());
//        priceList.cpoDtlLineSubNum.setValue(lineMsg.cpoDtlLineSubNum_LL.getValue());
//        priceList.prcCondTpCd.setValue("PR01");
//        priceList.prcDtlGrpCd.setValue("BASE");
//        priceList.prcJrnlGrpCd.setValue("BASE");
//        priceList.pkgUomCd.setValue("EA");
//        priceList.prcCondUnitCd.setValue("A");
//        priceList.prcCalcMethCd.setValue("EA");
//        priceList.prcCondManEntryFlg.setValue("Y");
//        priceList.prcCondManAddFlg.setValue("N");
//        priceList.prcCondManDelFlg.setValue("N");
//        priceList.autoPrcAmtRate.setValue(BigDecimal.ZERO);
//        priceList.manPrcAmtRate.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue());
//        priceList.calcPrcAmtRate.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue().multiply(lineMsg.ordQty_LL.getValue()));
//        priceList.unitPrcAmt.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue());
//        priceList.autoPrcCcyCd.setValue(CCY.US_DOLLAR);
//        pMsg.priceList.setValidCount(dtlPMsgCount + 1);
//
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum, lineMsg.dsCpoLineNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineSubNum, lineMsg.dsCpoLineSubNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineCatgCd, lineMsg.dsOrdLineCatgCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd, lineMsg.ordLineSrcCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd, lineMsg.rtlWhCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd, lineMsg.rtlSwhCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum, lineMsg.serNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd, lineMsg.flPrcListCd_LL);
////        ZYPEZDItemValueSetter.setValue(dtlPMsg.entCpoDtlFuncNetAmt, lineMsg.entCpoDtlFuncNetAmt_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlDealTaxAmt, lineMsg.cpoDtlDealTaxAmt_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum, lineMsg.dplyLineRefNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd, lineMsg.crRebilCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt, lineMsg.prcBaseDt_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd, lineMsg.custUomCd_LL);
//
//        pMsg.A.setValidCount(dtlPMsgCount + 1);
//    }
//
//    private boolean callApi(NWAL1500CMsg bizMsg, NWZC150001PMsg cpoUpdApiMsg, boolean isModifyReq) {
//
//        /**************************************************
//         * NWZC150001 : DS CPO Update API
//         **************************************************/
//        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
//
//        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList, ONBATCH_TYPE.ONLINE);
//
//        setCpoUpdateApiErrMsg(bizMsg, cpoUpdApiMsg);
//
//        final boolean isNormalEnd = setCpoUpdateApiDtlErrMsg(bizMsg, cpoUpdApiMsg, cpoUpdApiOutMsgList);
//        if (!isNormalEnd) {
//            return false;
//        }
//
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return false;
//        }
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, cpoUpdApiMsg.cpoOrdNum_A1.getValue());
//
//        return true;
//
//    }
// Logic For Moack <=
//    private static void setCpoUpdateApiErrMsg(NWAL1500CMsg bizMsg, NWZC150001PMsg cpoUpdApiMsg) {
//
//        for (int i = 0; i < cpoUpdApiMsg.xxMsgIdList.getValidCount(); i++) {
//
//            final String xxMsgId = cpoUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//
//            if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCH(xxMsgId)) {
//                relationCpoUpdateApiErrMsg(bizMsg, xxMsgId);
//
//            } else {
//
//                if (!NWZC001001.NWZM0135W.equals(xxMsgId)) {
//                    bizMsg.setMessageInfo(xxMsgId);
//                }
//            }
//        }
//    }
//
//    private static boolean chkCpoUpdAPIErrIdBCH(String msgId) {
//
//        final Set<String> chkIdSet = new HashSet<String>();
//        chkIdSet.add(NWZC001001.NWZM0002E);
//        chkIdSet.add(NWZC001001.NWZM0024E);
//        chkIdSet.add(NWZC001001.NWZM0025E);
//        chkIdSet.add(NWZC001001.NWZM0026E);
//        chkIdSet.add(NWZC001001.NWZM0028E);
//        chkIdSet.add(NWZC001001.NWZM0097E);
//        chkIdSet.add(NWZC001001.NWZM0109E);
//        chkIdSet.add(NWZC001001.NWZM0112E);
//        chkIdSet.add(NWZC001001.NWZM0114E);
//        return chkIdSet.contains(msgId);
//    }
//
//    private static void relationCpoUpdateApiErrMsg(NWAL1500CMsg bizMsg, String msgId) {
//
//        if (NWZC001001.NWZM0002E.equals(msgId)) {
//            bizMsg.cpoOrdNum.setErrorInfo(1, NWZC001001.NWZM0002E);
//
//        } else if (NWZC001001.NWZM0024E.equals(msgId)) {
//            bizMsg.sellToCustCd.setErrorInfo(1, NWZC001001.NWZM0024E);
//
//        } else if (NWZC001001.NWZM0025E.equals(msgId)) {
//            bizMsg.billToCustCd.setErrorInfo(1, NWZC001001.NWZM0025E);
//
//        } else if (NWZC001001.NWZM0026E.equals(msgId)) {
//            bizMsg.shipToCustCd.setErrorInfo(1, NWZC001001.NWZM0026E);
//
//        } else if (NWZC001001.NWZM0028E.equals(msgId)) {
//            bizMsg.dsOrdTpCd.setErrorInfo(1, NWZC001001.NWZM0028E);
//
//        } else if (NWZC001001.NWZM0097E.equals(msgId)) {
//            bizMsg.dsOrdTpCd.setErrorInfo(1, NWZC001001.NWZM0097E);
//
//        } else if (NWZC001001.NWZM0109E.equals(msgId)) {
//            bizMsg.dsOrdTpCd.setErrorInfo(1, NWZC001001.NWZM0109E);
//
//        } else if (NWZC001001.NWZM0112E.equals(msgId)) {
//            bizMsg.billToCustCd.setErrorInfo(1, NWZC001001.NWZM0112E);
//
//        } else if (NWZC001001.NWZM0114E.equals(msgId)) {
//            bizMsg.shipToCustCd.setErrorInfo(1, NWZC001001.NWZM0114E);
//        }
//    }

//    private boolean setCpoUpdateApiDtlErrMsg(NWAL1500CMsg bizMsg, NWZC150001PMsg cpoUpdApiMsg, List<NWZC150002PMsg> cpoUpdApiOutMsgList) {
//
//        boolean isNormalEnd = true;
//
//        for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {
//
//            final int sMsgIndex = getLineIndex(bizMsg, pMsg.cpoDtlLineNum.getValue(), pMsg.cpoDtlLineSubNum.getValue());
//
//            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//
//                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//
//                if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCHDtl(xxMsgId) || NWZC046001.NWZM0900W.equals(xxMsgId)) {
//
//                    isNormalEnd = false;
//
//                    final NWAL1500_BCMsg lineMsg = bizMsg.B.no(sMsgIndex);
//
//                    // isComponent?
//                    relationCpoUpdateApiDtlErrMsg(bizMsg, lineMsg, xxMsgId, cpoUpdApiMsg);
//
//                } else {
//                    bizMsg.setMessageInfo(xxMsgId);
//                }
//            }
//        }
//
//        return isNormalEnd;
//    }
//
//    private static int getLineIndex(NWAL1500CMsg bizMsg, String pCpoDtlLineNum, String pCpoDtlLineSubNum) {
//
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//
//            String cpoDtlLineNum = bizMsg.B.no(i).cpoDtlLineNum_LL.getValue();
//            String cpoDtlLineSubNum = bizMsg.B.no(i).cpoDtlLineSubNum_LL.getValue();
//
//            if (pCpoDtlLineNum.equals(cpoDtlLineNum) && pCpoDtlLineSubNum.equals(cpoDtlLineSubNum)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }

//    private static boolean chkCpoUpdAPIErrIdBCHDtl(String msgId) {
//
//        final List<String> msgList = new ArrayList<String>();
//        msgList.add(NWZC001001.NWZM0041E);
//        msgList.add(NWZC001001.NWZM0042E);
//        msgList.add(NWZC001001.NWZM0044E);
//        msgList.add(NWZC001001.NWZM0947E);
//        msgList.add(NWZC001001.NWZM0048E);
//        msgList.add(NWZC001001.NWZM0927E);
//        msgList.add(NWZC001001.NWZM0054E);
//        msgList.add(NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP);
//        msgList.add(NWZC001001.NWZM0458E);
//        msgList.add(NWZC001001.NWZM0140E);
//        msgList.add(NWZC001001.NWZM0141E);
//        msgList.add(NWZC001001.NWZM0142E);
//        msgList.add(NWZC001001.NWZM0143E);
//        msgList.add(NWZC001001.NWZM0144E);
//        msgList.add(NWZC001001.NWZM0148E);
//        msgList.add(NWZC001001.NWZM0152E);
//        msgList.add(NWZC001001.NWZM0153E);
//        msgList.add(NWZC001001.NWZM0161E);
//        msgList.add(NWZC001001.NWZM0335E);
//        msgList.add(NWZC001001.NWZM0642E);
//        msgList.add(NWZC001001.NWZM0263E);
//        msgList.add(NWZC001001.NWZM0041E);
//        msgList.add(NWZC001001.NWZM0458E);
//        msgList.add(NWZC001001.NWZM0418E);
//        msgList.add(NWZC001001.NWZM0419E);
//        msgList.add(NWZC001001.NWZM0047E);
//        msgList.add(NWZC046001.NWZM0900W);
//        msgList.add(NWZC001001.NWZM0942E);
//        msgList.add(NWZC001001.NWZM0943E);
//        msgList.add(NWZC001001.NWZM1215E);
//        msgList.add(NWZC001001.NWZM1216E);
//        msgList.add(NWZC001001.NWZM1298E);
//        msgList.add(NWZC001001.NWZM1311E);
//        msgList.add(NWZC001001.NWZM1312E);
//        msgList.add(NWZC001001.NWZM1262E);
//        return msgList.contains(msgId);
//    }
//
//    private void relationCpoUpdateApiDtlErrMsg(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg, String msgId, NWZC150001PMsg cpoUpdApiMsg) {
//
//        if (NWZC001001.NWZM0041E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0042E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0044E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0947E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0048E.equals(msgId)) {
//            lineMsg.entDealNetUnitPrcAmt_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0927E.equals(msgId)) {
//            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0054E.equals(msgId)) {
//            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);
//
//        } else if (NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP.equals(msgId)) {
//            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0458E.equals(msgId)) {
//            bizMsg.carrAcctNum.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0140E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0141E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0142E.equals(msgId)) {
//            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(getGlobalCompanyCode(), lineMsg.mdseCd_LL.getValue());
//            if (mdseMsg != null) {
//                lineMsg.ordQty_LL.setErrorInfo(1, "NWZM0884E", new String[] {String.valueOf(mdseMsg.cpoMinOrdQty.getValue()) });
//            } else {
//                lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//            }
//
//        } else if (NWZC001001.NWZM0143E.equals(msgId)) {
//            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(getGlobalCompanyCode(), lineMsg.mdseCd_LL.getValue());
//            if (mdseMsg != null) {
//                lineMsg.ordQty_LL.setErrorInfo(1, "NWZM0885E", new String[] {String.valueOf(mdseMsg.cpoMaxOrdQty.getValue()) });
//            } else {
//                lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//            }
//
//        } else if (NWZC001001.NWZM0144E.equals(msgId)) {
//            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(getGlobalCompanyCode(), lineMsg.mdseCd_LL.getValue());
//            if (mdseMsg != null) {
//                lineMsg.ordQty_LL.setErrorInfo(1, "NWZM0886E", new String[] {String.valueOf(mdseMsg.cpoIncrOrdQty.getValue()) });
//            } else {
//                lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//            }
//
//        } else if (NWZC001001.NWZM0148E.equals(msgId)) {
//            lineMsg.entDealNetUnitPrcAmt_LL.setErrorInfo(1, msgId);
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0152E.equals(msgId)) {
//            lineMsg.entDealNetUnitPrcAmt_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0153E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0161E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0335E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0642E.equals(msgId)) {
//            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0263E.equals(msgId)) {
//            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0041E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0458E.equals(msgId)) {
//            bizMsg.carrAcctNum.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0418E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0419E.equals(msgId)) {
//            lineMsg.mdseNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0047E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC046001.NWZM0900W.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0942E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM0943E.equals(msgId)) {
//            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM1215E.equals(msgId)) {
//            BigDecimal ordQty = cpoUpdApiMsg.ordQty_A1.getValue();
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId, new String[] {ordQty.toString() });
//
//        } else if (NWZC001001.NWZM1216E.equals(msgId)) {
//            lineMsg.serNum_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM1298E.equals(msgId)) {
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM1311E.equals(msgId)) {
//            lineMsg.mdseNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM1312E.equals(msgId)) {
//            lineMsg.mdseNm_LL.setErrorInfo(1, msgId);
//
//        } else if (NWZC001001.NWZM1262E.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
//        }
//    }

//    /**
//     * Get Instance
//     * @return
//     */
//    private static NWAL1500Query getQuery() {
//        return NWAL1500Query.getInstance();
//    }

    // 2016/02/10 S21_NA#1584 Add Start
    private boolean isHeaderSave(NWAL1500CMsg bizMsg, boolean isSave) {
        boolean isHdrSave = isSave && bizMsg.B.getValidCount() == 0 && bizMsg.D.getValidCount() == 0;

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            boolean isHdrSaved = NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
            isHdrSave = isHdrSave & isHdrSaved;
        }

        return isHdrSave;
    }
    // 2016/02/10 S21_NA#1584 Add End

    // 2016/02/15 S21_NA#2336 Add Start
    private void doProcess_NWAL1500Scrn00_Delete(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);

        NWAL1500CommonLogicForDelete.deleteProcess(bizMsg, glblMsg);
        boolean procRslt = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxPopPrm_PJ.getValue()); // 2016/04/20 S21_NA#5605 Add
        boolean isHdrSaved = NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());

        // 2016/04/20 S21_NA#5605 Add Start
        // if (NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue())) { 2016/04/20 S21_NA#5605 Mod Confition
        if (procRslt && isHdrSaved) {

            // 2016/11/29 S21_NA#16214 Add Start
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808
            for (int selLineNum : selectedRowsForLine) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selLineNum); // 2018/01/31 S21_NA#19808 Mod
                // 2018/05/20 S21_NA#25604 Mod Start
//                NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/31 S21_NA#19808
//                NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/31 S21_NA#19808
                NWAL1500CommonLogicForLineConfig.deriveBaseComponentFlagAndModel(bizMsg, glblMsg, slctConfIndex);
                // 2018/05/20 S21_NA#25604 Mod End
            }
            // 2016/11/29 S21_NA#16214 Add End
            // 2017/11/21 S21_NA#22555 Add Start
            List<Integer> selectedRowsForRma = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808
            for (int selLineNum : selectedRowsForRma) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, selLineNum); // 2018/01/31 S21_NA#19808
                Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();
                NWAL1500CommonLogicForDelete.resetBaseComponet4OneConfig4Rma(bizMsg, glblMsg, baseComponentMap, slctConfIndex); // 2018/01/31 S21_NA#19808
                NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.C.no(slctConfIndex)); // 2018/01/31 S21_NA#19808
            }
            // 2017/11/21 S21_NA#22555 Add End

            // 2016/07/11 S21_NA#7821 Mod Start
//            bizMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_ON_Y); // 2016/05/13 S21_NA#7924 Add
//            doProcess_NWAL1500Scrn00_CMN_Save(bizMsg, glblMsg, false);
//            bizMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_OFF_N); // 2016/05/13 S21_NA#7924 Add
            NWAL1500CommonLogicForDelete.callDsCpoUpdateAPIForDelete(bizMsg, glblMsg);
            // 2016/07/11 S21_NA#7821 Mod End
            NWAL1500CommonLogicForDelete.deleteProcessAfterUpdate(bizMsg, glblMsg);
        }
        // 2016/04/20 S21_NA#5605 Add End
    }
    // 2016/02/15 S21_NA#2336 Add End

    // 2016/02/15 S21_NA#2166 Add Start
    private void doProcess_NWAL1500_NWAL2000(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        boolean cancelCreditRebill = false; // S21_NA#11630 ADD

        if (TAB_HEADER.equals(dplyTab) || TAB_ADDL_HEADER.equals(dplyTab)) {
            doProcess_NWAL1500Scrn00_Order_Cancel(bizMsg, glblMsg);

            // S21_NA#11630 ADD START
            // Mod Start 2017/10/20 QC#21773
            //if (CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(bizMsg.cpoSrcTpCd.getValue())) {
            // 2022/03/24 QC#59825 Mod Start 
//            if (CPO_SRC_TP.CREDIT.equals(bizMsg.cpoSrcTpCd.getValue()) //
//                    || CPO_SRC_TP.REBILL.equals(bizMsg.cpoSrcTpCd.getValue())) {
            if (CPO_SRC_TP.CREDIT.equals(bizMsg.cpoSrcTpCd.getValue())) {
            // 2022/03/24 QC#59825 Mod End 
                // Mod End 2017/10/20 QC#21773
                cancelCreditRebill = true;
            }
            // S21_NA#11630 ADD END

        } else if (NWAL1500Constant.TAB_LINE_CONFIG.equals(dplyTab) || NWAL1500Constant.TAB_RMA.equals(dplyTab))  {
            NWAL1500CommonLogicForLineCancel.doProcessCancelConfigOrLine(bizMsg, glblMsg);

        }

        // 2016/04/19 S21_NA#5394 Add Start
        if (!"E".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(ZZM8100I);
        }
        // 2016/04/19 S21_NA#5394 Add End

        // S21_NA#11630 ADD START
        if (!"E".equals(bizMsg.getMessageKind()) && cancelCreditRebill && ZYPCommonFunc.hasValue(bizMsg.reBillPairCpoOrdNum)) {
            bizMsg.setMessageInfo(NWAM0874I,  new String[] {bizMsg.reBillPairCpoOrdNum.getValue()});
        }
        // S21_NA#11630 ADD END
    }
    // 2016/02/15 S21_NA#2166 Add Start

    // QC#4078 Add Start
    private void doProcess_NWAL1500_NWAL1620(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // save Parameter Item
        String xxPopPrmP0 = bizMsg.xxPopPrm_P0.getValue();
        String xxPopPrmP1 = bizMsg.xxPopPrm_P1.getValue();
        String xxPopPrmP2 = bizMsg.xxPopPrm_P2.getValue();
        String xxPopPrmP3 = bizMsg.xxPopPrm_P3.getValue();
        String xxPopPrmP4 = bizMsg.xxPopPrm_P4.getValue();
        String xxPopPrmP5 = bizMsg.xxPopPrm_P5.getValue(); // Add 2017/09/21 S21_NA#18859 
        String dsOrdPosnNumP1 = bizMsg.dsOrdPosnNum_P1.getValue();
        BigDecimal dsCpoLineNumP1 = bizMsg.dsCpoLineNum_P1.getValue();
        BigDecimal copyNumAW = bizMsg.xxQty10Num_AW.getValue();
        // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//        String srcCpoOrdNum = bizMsg.cpoOrdNum.getValue();
        // 2017/04/25 S21_NA#Review structure Lv.2 Del End

        // 2016/10/11 S21_NA#7924-2 Add Start
        if (COPY_NEW_LOGIC) {
            // reset Parameter Item
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, xxPopPrmP0);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P1, xxPopPrmP1);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, xxPopPrmP2);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, xxPopPrmP3);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P4, xxPopPrmP4);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P5, xxPopPrmP5); // Add 2017/09/21 S21_NA#18859 
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_P1, dsOrdPosnNumP1);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_P1, dsCpoLineNumP1);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxQty10Num_AW, copyNumAW);
            bizMsg.xxDplyTab.setValue(TAB_HEADER);

            // 2017/04/25 S21_NA#Review structure Lv.2 Mod Start
//            NWAL1500CommonLogicForCopy.copyOrderWithoutApi(bizMsg, getContextUserInfo());
            NWAL1500CommonLogicForSaveSubmit.getInstance().callDsCpoUpdateApiForOrderCopy(bizMsg, glblMsg);
            // 2017/04/25 S21_NA#Review structure Lv.2 Mod End
            return;
        }

        // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//        // 2016/10/11 S21_NA#7924-2 Add End
//        NWAL1500BL02.doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId(), getUserProfileService());
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }
//
//        // reset Parameter Item
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, xxPopPrmP0);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P1, xxPopPrmP1);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, xxPopPrmP2);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, xxPopPrmP3);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P4, xxPopPrmP4);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_P1, dsOrdPosnNumP1);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_P1, dsCpoLineNumP1);
//        ZYPEZDItemValueSetter.setValue(bizMsg.copyNum_AW, copyNumAW);
//        bizMsg.xxDplyTab.setValue(TAB_HEADER);
//
//        NWAL1500CommonLogicForCopy.editCopyScrn(bizMsg, glblMsg);
//
//        // SAVE
//        bizMsg.cpoOrdNum.clear();
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, srcCpoOrdNum);
//        bizMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_ON_Y); // 2016/05/13 S21_NA#7924 Add
//        doProcess_NWAL1500Scrn00_CMN_Save(bizMsg, glblMsg, true);
//        bizMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_OFF_N); // 2016/05/13 S21_NA#7924 Add
//
//        if ("E".equals(bizMsg.getMessageKind())) {
//
//            EZDMessageInfo msgInfo = bizMsg.getMessageInfo();
//            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, srcCpoOrdNum);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, srcCpoOrdNum);
//
//            NWAL1500BL02.doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId(), getUserProfileService());
//            bizMsg.xxDplyTab.setValue(TAB_HEADER);
//
//            // Restore Parameter Item
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, xxPopPrmP0);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P1, xxPopPrmP1);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, xxPopPrmP2);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, xxPopPrmP3);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P4, xxPopPrmP4);
//
//            bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
//            return;
//        }
//
//        // Set Source Order Num
//        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, bizMsg.cpoOrdNum);
//        }
//        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, srcCpoOrdNum);
        // 2017/04/25 S21_NA#Review structure Lv.2 Del End
    }
    // QC#4078 Add End

    private void doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2019/05/24 QC#50043 Add Start
        NWAL1500CommonLogic.outputEntryActBizProctLog(bizMsg);
        // 2019/05/24 QC#50043 Add End

        String ordEntryAct = bizMsg.ordEntryActCd.getValue();
        if (ORD_ENTRY_ACT.DELETE.equals(ordEntryAct)) {
            doProcess_NWAL1500Scrn00_Delete(bizMsg, glblMsg);
        } else if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryAct)) {
            if (TAB_HEADER.equals(bizMsg.xxDplyTab.getValue())
                    || TAB_ADDL_HEADER.equals(bizMsg.xxDplyTab.getValue())) {
                doProcess_NWAL1500Scrn00_Order_Cancel(bizMsg, glblMsg);
            }
        }
    }

    private void doNothing() {
        return;
    }

    // 2018/05/11 S21_NA##22139 Add Start
    private void doProcess_NWAL1500_NWAL1790(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Call Report Create API
        if (!NWAL1500CommonLogicForReport.callSplyQuoteReportCratApi(bizMsg)) {
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_ML.getValue())) {
            // Execute EIP Process (Send Email)
            NWAL1500CommonLogicForReport.executeEipProcessForSendMail(bizMsg, SPLY_QUOTE_RPT_TP.ORDER_CONF);

            // Create Confirmation Output Pulldown
            if (!NWAL1500CommonLogicForReport.insertReportOutputLog(bizMsg, SPLY_QUOTE_RPT_TP.ORDER_CONF)) {
                return;
            }
        }
    }

    private void doProcess_NWAL1500_NWAL2420(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Return Authorization Check
        if (!NWAL1500CommonLogicForReport.rtrnAuthPrintChk(bizMsg, glblMsg)) {
            bizMsg.setMessageInfo(NWAM0955E);
            return;
        }

        // Update CPO
        if (!NWAL1500CommonLogicForReport.partialUpdateCpo(bizMsg)) {
            return;
        }

        // Call Report Create API
        if (!NWAL1500CommonLogicForReport.callRtrnAuthReportCratApi(bizMsg)) {
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_ML.getValue())) {
            // Execute EIP Process (Send Email)
            NWAL1500CommonLogicForReport.executeEipProcessForSendMail(bizMsg, SPLY_QUOTE_RPT_TP.RETURN_AUTH);

            // Create Confirmation Output Pulldown
            if (!NWAL1500CommonLogicForReport.insertReportOutputLog(bizMsg, SPLY_QUOTE_RPT_TP.RETURN_AUTH)) {
                return;
            }
        }
    }
    // 2018/05/11 S21_NA##22139 Add End
}
