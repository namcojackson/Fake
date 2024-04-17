/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.DELETE_LINE_STEP_2;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MAX_ERR_REF_NUM_CNT;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE_REF_ACT_DEL;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0769E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0770E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0893W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0912E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0956E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0957E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM1476E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM8460E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForDelete;
import business.blap.NWAL1500.NWAL1500QueryForSaveSubmit;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_GCMsg;
import business.blap.NWAL1500.NWAL1500_HCMsg;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.SVC_MACH_MSTR_HISTTMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         S.Takami        Create          S21_NA#2336
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#3236-2
 * 2016/04/05   Fujitsu         S.Takami        Update          S21_NA#5519-2
 * 2016/04/11   Fujitsu         S.Takami        Update          S21_NA#3236-3
 * 2016/04/20   Fujitsu         S.Takami        Update          S21_NA#5605
 * 2016/05/16   Fujitsu         S.Takami        Update          S21_NA#8144
 * 2016/06/03   Fujitsu         S.Takami        Update          S21_NA#9301
 * 2016/06/07   Fujitsu         K.Sato          Update          S21_NA#9275
 * 2016/06/07   Fujitsu         S.Takami        Update          S21_NA#9301-2
 * 2016/06/22   Fujitsu         S.Takami        Update          S21_NA#9301-3
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/08/04   Fujitsu         S.Takami        Update          S21_NA#7821-2
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/09/21   Fujitsu         S.Takami        Update          S21_NA#8279-2
 * 2016/09/21   Fujitsu         S.Takami        Update          S21_NA#10274
 * 2016/11/09   Fujitsu         S.Iidaka        Update          S21_NA#9867
 * 2017/02/03   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/02/24   Fujitsu         S.Takami        Update          S21_NA#17714
 * 2017/06/09   Fujitsu         A.Kosai         Update          S21_NA#18643
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2018/02/03   Fujitsu         S.Takami        Update          S21_NA#19808 (glblMsg.A, B, C, D => glblMsg.A, B, C, D, without any message)
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2018/04/06   Fujitsu         S.Takami        Update          S21_NA#19808-2
 * 2018/05/16   Fujitsu         M.Ohno          Update          S21_NA#25144
 * 2018/06/01   Fujitsu         T.Noguchi       Update          S21_NA#26334
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#26681
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/06/14   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/06/27   Fujitsu         K.Ishizuka      Update          S21_NA#26602
 * 2018/12/21   Fujitsu         K.Ishizuka      Update          S21_NA#28654
 * 2019/01/18   Fujtisu         K.Ishizuka      Update          S21_NA#29784
 * 2019/03/29   Fujtisu         R.Nakamura      Update          S21_NA#30849
 * 2019/03/29   Fujtisu         T.Noguchi       Update          S21_NA#30975
 * 2019/04/15   Fujitsu         K.Ishizuka      Update          S21_NA#31184
 * 2019/06/11   Fujitsu         C.Hara          Update          QC#50787
 * 2019/12/11   Fujitsu         Y.Kanefusa      Update          S21_NA#54998
 * </pre>
 */
public final class NWAL1500CommonLogicForDelete {

    /** Log Output if true. */
    private static final boolean PRNT_FLG = false;

    /**
     * <pre>
     * Do delete line process.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void deleteProcess(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        bizMsg.xxPopPrm_PJ.clear(); // 2016/04/20 S21_NA#5605 Add
        if (isErrorSelection(bizMsg, glblMsg)) {
            return;
        }

        // 2018/06/05 S21_NA#26681 Add Start
        // Check Referenced item will be delete
        if (isReferencedItemDeleting(bizMsg, glblMsg, MSG_PARAM_LINE_REF_ACT_DEL)) {
            bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }
        // 2018/06/05 S21_NA#26681 Add End
        // 2016/09/20 S21_NA#8279 Add Start
        String deleteStep = bizMsg.xxYesNoCd_CA.getValue();
        String msgId = "";

        // 2016/09/21 S21_NA#8279-2 Del Start
        // Deleted Step 1!!
//        if (S21StringUtil.isEquals(DELETE_LINE_STEP_1, deleteStep)) {
//            msgId = NWAM0894W;
//            bizMsg.xxYesNoCd_CA.setValue(DELETE_LINE_STEP_2);
//            bizMsg.xxPopPrm_PJ.clear();
//        } else if (S21StringUtil.isEquals(DELETE_LINE_STEP_2, deleteStep)) {
            // 2016/09/21 S21_NA#8279-2 Del End
        if (S21StringUtil.isEquals(DELETE_LINE_STEP_2, deleteStep)) {
            msgId = "";
            bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            msgId = NWAM0893W;
//            bizMsg.xxYesNoCd_CA.setValue(DELETE_LINE_STEP_1); 2016/09/21 S21_NA#8279-2 Del
            bizMsg.xxYesNoCd_CA.setValue(DELETE_LINE_STEP_2); // 2016/09/21 S21_NA#8279-2 Add
            bizMsg.xxPopPrm_PJ.clear();
        }
        if (ZYPCommonFunc.hasValue(msgId)) {
            bizMsg.setMessageInfo(msgId);
            return;
        }
        // 2016/09/20 S21_NA#8279 Add End
        chkConfigIfAllLineSelInConfig(bizMsg, glblMsg); // 2016/04/20 S21_NA#5605 Add

        // 2016/09/21 S21_NA#10274 Add Start
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdseCd_LB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).mdseCd_LL, glblMsg.B.no(i).mdseCd_LB);
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).cpoDtlLineNum_LB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).cpoDtlLineNum_LL, glblMsg.B.no(i).cpoDtlLineNum_LB);
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).cpoDtlLineSubNum_LB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).cpoDtlLineSubNum_LL, glblMsg.B.no(i).cpoDtlLineSubNum_LB);
            }
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_RB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).mdseCd_RL, glblMsg.D.no(i).mdseCd_RB);
            }
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).cpoDtlLineNum_RB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).cpoDtlLineNum_RL, glblMsg.D.no(i).cpoDtlLineNum_RB);
            }
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).cpoDtlLineSubNum_RB)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).cpoDtlLineSubNum_RL, glblMsg.D.no(i).cpoDtlLineSubNum_RB);
            }
        }
        // 2016/09/21 S21_NA#10274 Add End
        // Adding Line Delete
        if (isCurrentTabLine(bizMsg)) {
            deleteForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        } else if (isCurrentTabRMA(bizMsg)) {
            deleteForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
        }
        printDelData(glblMsg);
//        printGlblData(glblMsg);
    }

    // QC#24245 2018/06/13 Add Start
    public static void deleteProcessAfterUpdate(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // QC#28772 2018/10/16 Add Start
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // QC#28772 2018/10/16 Add End
        // Adding Line Delete
        if (isCurrentTabLine(bizMsg)) {
            List<NWAL1500_ASMsg> delTrgtConfigList = new ArrayList<NWAL1500_ASMsg>(0);
            List<NWAL1500_ASMsg> remainTrgtConfigList = new ArrayList<NWAL1500_ASMsg>(0);
            List<NWAL1500_BSMsg> delTrgtLineList = new ArrayList<NWAL1500_BSMsg>(0);
            List<NWAL1500_BSMsg> remainTrgtLineList = new ArrayList<NWAL1500_BSMsg>(0);

            List<String> delConfigList = new ArrayList<String>();
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
                NWAL1500_ASMsg newConfigMsg = new NWAL1500_ASMsg();
                EZDMsg.copy(configMsg, null, newConfigMsg, null);

                if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_LC.getValue())) {
                    delTrgtConfigList.add(newConfigMsg);
                    delConfigList.add(configMsg.dsOrdPosnNum_LC.getValue());

                    String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                    for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                        if (dsOrdPosnNum.equals(glblMsg.B.no(j).dsOrdPosnNum_LL.getValue())) {
                            glblMsg.B.no(j).xxChkBox_LL.setValue(ZYPConstant.FLG_ON_Y);
                        }
                    }
                } else {
                    remainTrgtConfigList.add(newConfigMsg);
                }
            }

            List<String> modConfigList = new ArrayList<String>();
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                NWAL1500_BSMsg newLineMsg = new NWAL1500_BSMsg();
                EZDMsg.copy(lineMsg, null, newLineMsg, null);

                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_LL.getValue())) {
                    delTrgtLineList.add(newLineMsg);

                    if (!modConfigList.contains(lineMsg.dsOrdPosnNum_LL.getValue()) && !delConfigList.contains(lineMsg.dsOrdPosnNum_LL.getValue())) {
                        modConfigList.add(lineMsg.dsOrdPosnNum_LL.getValue());
                    }
                    // for set component
                    int cntForSet = i + 1;
                    for (; cntForSet < glblMsg.B.getValidCount(); cntForSet++) {
                        NWAL1500_BSMsg setChildLineMsg = glblMsg.B.no(cntForSet);
                        if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), setChildLineMsg.dsOrdPosnNum_LL.getValue()) // 
                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_LL.getValue(), setChildLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
                            NWAL1500_BSMsg setChiledNewLineMsg = new NWAL1500_BSMsg();
                            EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                            delTrgtLineList.add(setChiledNewLineMsg);
                            i++;
                        } else {
                            break;
                        }
                    }
                    i = cntForSet - 1;
                } else {
                    remainTrgtLineList.add(newLineMsg);
                }
            }

            // Delete Lines
            glblMsg.B.clear();
            int valCnt = 0;
            for (NWAL1500_BSMsg lineMsg : remainTrgtLineList) {
                EZDMsg.copy(lineMsg, null, glblMsg.B.no(valCnt++), null);
            }
            glblMsg.B.setValidCount(valCnt);

            // Config
            if (delTrgtConfigList.size() > 0) {
                glblMsg.A.clear();
                valCnt = 0;
                List<NWAL1500_GCMsg> remainSlsCrMsgList = new ArrayList<NWAL1500_GCMsg>();
                for (NWAL1500_ASMsg configMsg : remainTrgtConfigList) {
                    EZDMsg.copy(configMsg, null, glblMsg.A.no(valCnt++), null);

                    // Sales Credit Reset
                    String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                    for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                        NWAL1500_GCMsg slsCrMsg = bizMsg.G.no(i);
                        if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_GS.getValue())) {
                            NWAL1500_GCMsg newSlsCrMsg = new NWAL1500_GCMsg();
                            EZDMsg.copy(slsCrMsg, null, newSlsCrMsg, null);
                            remainSlsCrMsgList.add(newSlsCrMsg);
                        }
                    }
                }
                glblMsg.A.setValidCount(valCnt);

                bizMsg.G.clear();
                valCnt = 0;
                for (NWAL1500_GCMsg slsCrMsg : remainSlsCrMsgList) {
                    EZDMsg.copy(slsCrMsg, null, bizMsg.G.no(valCnt++), null);
                }
                bizMsg.G.setValidCount(valCnt);
            }

            renumPosnNum(bizMsg, glblMsg);
            renumDplyLineNum(bizMsg, glblMsg);
            resetDplyLineRefNum(bizMsg, glblMsg); // 2018/12/21 S21_NA#28654 Add
            resetBaseComponet(bizMsg, glblMsg);
            resetModel(bizMsg, glblMsg, modConfigList);
            // Price Message
            List<NWAL1500_ISMsg> remainTrgtPrcMsgList = new ArrayList<NWAL1500_ISMsg>();
            for (NWAL1500_BSMsg lineMsg : remainTrgtLineList) {
                String cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
                String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();

                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    NWAL1500_ISMsg prcMsg = glblMsg.I.no(j);

                    if (!CONFIG_CATG.OUTBOUND.equals(prcMsg.configCatgCd_LP.getValue())) {
                        continue;
                    }
                    if (cpoDtlLineNum.equals(prcMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcMsg.cpoDtlLineSubNum_LP.getValue())) {
                        NWAL1500_ISMsg newPrcMsg = new NWAL1500_ISMsg();
                        EZDMsg.copy(prcMsg, null, newPrcMsg, null);
                        remainTrgtPrcMsgList.add(newPrcMsg);
                    }
                }
            }
            List<NWAL1500_ISMsg> rmaPrcMsgList = getPrcMsg(glblMsg, CONFIG_CATG.INBOUND); 
            glblMsg.I.clear();
            valCnt = 0;
            for (NWAL1500_ISMsg prcMsg : remainTrgtPrcMsgList) {
                EZDMsg.copy(prcMsg, null, glblMsg.I.no(valCnt++), null);
            }
            for (NWAL1500_ISMsg rmaPrcMsg : rmaPrcMsgList) {
                EZDMsg.copy(rmaPrcMsg, null, glblMsg.I.no(valCnt++), null);
            }
            glblMsg.I.setValidCount(valCnt);

            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        } else if (isCurrentTabRMA(bizMsg)) {
            List<NWAL1500_CSMsg> delTrgtConfigList = new ArrayList<NWAL1500_CSMsg>(0);
            List<NWAL1500_CSMsg> remainTrgtConfigList = new ArrayList<NWAL1500_CSMsg>(0);
            List<NWAL1500_DSMsg> delTrgtLineList = new ArrayList<NWAL1500_DSMsg>(0);
            List<NWAL1500_DSMsg> remainTrgtLineList = new ArrayList<NWAL1500_DSMsg>(0);
            String getPosnNum = null;
            List<String> delConfigList = new ArrayList<String>();
            List<String> posnNums = new ArrayList<String>();
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg configMsg = glblMsg.C.no(i);
                NWAL1500_CSMsg newConfigMsg = new NWAL1500_CSMsg();
                EZDMsg.copy(configMsg, null, newConfigMsg, null);

                if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_RC.getValue())) {
                    delTrgtConfigList.add(newConfigMsg);
                    delConfigList.add(configMsg.dsOrdPosnNum_RC.getValue());

                    getPosnNum = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
                    if (!posnNums.contains(getPosnNum)) {
                        posnNums.add(getPosnNum);
                    }

                    String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                    for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                        if (dsOrdPosnNum.equals(glblMsg.D.no(j).dsOrdPosnNum_RL.getValue())) {
                            glblMsg.D.no(j).xxChkBox_RL.setValue(ZYPConstant.FLG_ON_Y);
                        }
                    }
                } else {
                    remainTrgtConfigList.add(newConfigMsg);
                }
            }

            List<String> modConfigList = new ArrayList<String>();
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
                NWAL1500_DSMsg newLineMsg = new NWAL1500_DSMsg();
                EZDMsg.copy(lineMsg, null, newLineMsg, null);

                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_RL.getValue())) {
                    delTrgtLineList.add(newLineMsg);
                    if (!modConfigList.contains(lineMsg.dsOrdPosnNum_RL.getValue())
                            && !delConfigList.contains(lineMsg.dsOrdPosnNum_RL.getValue())) {
                        modConfigList.add(lineMsg.dsOrdPosnNum_RL.getValue());
                    }

                    // for set component
                    int cntForSet = i + 1;
                    for (; cntForSet < glblMsg.D.getValidCount(); cntForSet++) {
                        NWAL1500_DSMsg setChildLineMsg = glblMsg.D.no(cntForSet);
                        if (!ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL) && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), setChildLineMsg.dsOrdPosnNum_RL.getValue()) // 
                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), setChildLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                            NWAL1500_DSMsg setChiledNewLineMsg = new NWAL1500_DSMsg();
                            EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                            delTrgtLineList.add(setChiledNewLineMsg);
                            i++;
                        } else {
                            break;
                        }
                    }
                    i = cntForSet - 1;
                    // delete parent
                    if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)) {
                        boolean isDelPrnt = true;
                        for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                            NWAL1500_DSMsg diffLineMsg = glblMsg.D.no(j);
                            if (ZYPCommonFunc.hasValue(diffLineMsg.dsCpoLineSubNum_RL) && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), diffLineMsg.dsOrdPosnNum_RL.getValue())
                                    && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), diffLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                                isDelPrnt &= ZYPConstant.FLG_ON_Y.equals(diffLineMsg.xxChkBox_RL.getValue());
                            }
                        }

                        if (isDelPrnt) {
                            NWAL1500_DSMsg parentMsg = null;
                            for (NWAL1500_DSMsg remainLine : remainTrgtLineList) {
                                if (!ZYPCommonFunc.hasValue(remainLine.dsCpoLineSubNum_RL) && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), remainLine.dsOrdPosnNum_RL.getValue())
                                        && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), remainLine.dsCpoLineNum_RL.getValue()) == 0) {
                                    parentMsg = remainLine;
                                    break;
                                }
                            }

                            if (parentMsg != null) {
                                remainTrgtLineList.remove(parentMsg);
                            }
                        }
                    }
                } else {
                    remainTrgtLineList.add(newLineMsg);
                }
            }

            // Delete Lines
            glblMsg.D.clear();
            int valCnt = 0;
            for (NWAL1500_DSMsg rmaLineMsg : remainTrgtLineList) {
                // QC#54998 2019/12/11 Add Start
                DS_CPO_RTRN_DTLTMsg inTMsg = new DS_CPO_RTRN_DTLTMsg();
                DS_CPO_RTRN_DTLTMsg outTMsg = new DS_CPO_RTRN_DTLTMsg();
                inTMsg.clear();
                inTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                inTMsg.cpoOrdNum.setValue(bizMsg.cpoOrdNum.getValue());
                inTMsg.dsCpoRtrnLineNum.setValue(rmaLineMsg.cpoDtlLineNum_RL.getValue());
                inTMsg.dsCpoRtrnLineSubNum.setValue(rmaLineMsg.cpoDtlLineSubNum_RL.getValue());
                outTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.ezUpTime_RL, outTMsg.ezUpTime);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.ezUpTimeZone_RL, outTMsg.ezUpTimeZone);
                // QC#54998 2019/12/11 Add End
                EZDMsg.copy(rmaLineMsg, null, glblMsg.D.no(valCnt++), null);
            }
            glblMsg.D.setValidCount(valCnt);

            // Config
            if (delTrgtConfigList.size() > 0) {
                glblMsg.C.clear();
                valCnt = 0;
                List<NWAL1500_HCMsg> remainSlsCrMsgList = new ArrayList<NWAL1500_HCMsg>();
                for (NWAL1500_CSMsg configMsg : remainTrgtConfigList) {
                    EZDMsg.copy(configMsg, null, glblMsg.C.no(valCnt++), null);

                    // Sales Credit Reset
                    String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                    for (int j = 0; j < bizMsg.H.getValidCount(); j++) {
                        NWAL1500_HCMsg slsCrMsg = bizMsg.H.no(j);
                        if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_HS.getValue())) {
                            NWAL1500_HCMsg newSlsCrMsg = new NWAL1500_HCMsg();
                            EZDMsg.copy(slsCrMsg, null, newSlsCrMsg, null);
                            remainSlsCrMsgList.add(newSlsCrMsg);
                        }
                    }
                }
                glblMsg.C.setValidCount(valCnt);

                bizMsg.H.clear();
                valCnt = 0;
                for (NWAL1500_HCMsg slsCrMsg : remainSlsCrMsgList) {
                    EZDMsg.copy(slsCrMsg, null, bizMsg.H.no(valCnt++), null);
                }
                bizMsg.H.setValidCount(valCnt);
            }

            renumPosnNum4Rma(bizMsg, glblMsg);
            renumRefPosn4Rma(glblMsg);
            renumDplyLineNum4Rma(bizMsg, glblMsg);
            resetBaseComponet4Rma(bizMsg, glblMsg);
            NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, true);
            resetRefLineNum4Rma(bizMsg, glblMsg);
            resetModel4Rma(bizMsg, glblMsg, modConfigList);
            // Price Message
            List<NWAL1500_ISMsg> remainTrgtPrcMsgList = new ArrayList<NWAL1500_ISMsg>();
            for (NWAL1500_DSMsg rmaLineMsg : remainTrgtLineList) {
                String cpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
                String cpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();

                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    NWAL1500_ISMsg prcMsg = glblMsg.I.no(j);

                    if (!CONFIG_CATG.INBOUND.equals(prcMsg.configCatgCd_LP.getValue())) {
                        continue;
                    }
                    if (cpoDtlLineNum.equals(prcMsg.cpoDtlLineNum_LP.getValue()) && cpoDtlLineSubNum.equals(prcMsg.cpoDtlLineSubNum_LP.getValue())) {
                        NWAL1500_ISMsg newPrcMsg = new NWAL1500_ISMsg();
                        EZDMsg.copy(prcMsg, null, newPrcMsg, null);
                        remainTrgtPrcMsgList.add(newPrcMsg);
                    }
                }
            }
            List<NWAL1500_ISMsg> linePrcMsgList = getPrcMsg(glblMsg, CONFIG_CATG.OUTBOUND); 
            glblMsg.I.clear();
            valCnt = 0;
            for (NWAL1500_ISMsg prcMsg : remainTrgtPrcMsgList) {
                EZDMsg.copy(prcMsg, null, glblMsg.I.no(valCnt++), null);
            }
            for (NWAL1500_ISMsg linePrcMsg : linePrcMsgList) {
                EZDMsg.copy(linePrcMsg, null, glblMsg.I.no(valCnt++), null);
            }
            glblMsg.I.setValidCount(valCnt);

            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
        }
    }
    // QC#24245 2018/06/13 Add End

    private static boolean isErrorSelection(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        boolean errFlg = false;
        // 2018/04/06 S21_NA#19808-2 Add Start
        int firstErrPage = -1;
        // 2018/04/06 S21_NA#19808-2 Add End
        if (isCurrentTabLine(bizMsg)) {
            // TAB_LINE_CONFIG
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (selectedRowsForConfig.size() == 0 && selectedRowsForLine.size() == 0) {
                bizMsg.setMessageInfo(NWAM0504E);
                return true;
            }
            for (Integer selectedRow : selectedRowsForConfig) {
                int idx = selectedRow.intValue();
                NWAL1500_ASMsg lineConfigMsg = glblMsg.A.no(idx);
                String dsOrdPosnNum = lineConfigMsg.dsOrdPosnNum_LC.getValue();
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                    String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                    if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        continue;
                    }
                    // 2018/06/27 S21_NA#26602 Add Start
                    if (NWAL1500CommonLogic.isRebillTangibleItem(lineMsg, bizMsg.glblCmpyCd.getValue())) {
                        // 2019/06/11 QC#50787 Mod Start
                        // lineConfigMsg.xxChkBox_LC.setErrorInfo(1, NWAM0783E, new String[] {"Config Including Rebill Line of Tangible Item"});
                        lineConfigMsg.xxChkBox_LC.setErrorInfo(1, NWAM8460E, new String[] {"Config Including Rebill Line"});
                        // 2019/06/11 QC#50787 Mod End
                        if (firstErrPage < 0) {
                            firstErrPage = lineMsg.xxPageNum_LL.getValueInt();
                        }
                        errFlg = true;
                        break;
                    }
                    // 2018/06/27 S21_NA#26602 Add End
                    String ordLineStsCd = lineMsg.ordLineStsCd_LL.getValue();
                    if (!ORD_LINE_STS.SAVED.equals(ordLineStsCd) && ZYPCommonFunc.hasValue(ordLineStsCd)) {
                        lineConfigMsg.xxChkBox_LC.setErrorInfo(1, NWAM0769E);
                        // 2018/04/06 S21_NA#19808-2 Add Start
                        if (firstErrPage < 0) {
                            firstErrPage = lineConfigMsg.xxPageNum_LC.getValueInt();
                        }
                        // 2018/04/06 S21_NA#19808-2 Add End
                        errFlg = true;
                    }
                }
            }
            for (Integer selectedRow : selectedRowsForLine) {
                int idx = selectedRow.intValue();
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(idx);
                String ordLineStsCd = lineMsg.ordLineStsCd_LL.getValue();
                if (!ORD_LINE_STS.SAVED.equals(ordLineStsCd) && ZYPCommonFunc.hasValue(ordLineStsCd)) {
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0770E);
                    // 2018/04/06 S21_NA#19808-2 Add Start
                    if (firstErrPage < 0) {
                        firstErrPage = lineMsg.xxPageNum_LL.getValueInt();
                    }
                    // 2018/04/06 S21_NA#19808-2 Add End
                    errFlg = true;
                }

                // 2018/05/16 S21_NA#25144 add start
                if (NWAL1500CommonLogic.isRebillTangibleItem(lineMsg, bizMsg.glblCmpyCd.getValue())) {
                    // 2019/06/11 QC#50787 Mod Start
                    // lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0783E, new String[] {"Rebill Line of Tangible Item"});
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM8460E, new String[] {"Rebill Line"});
                    // 2019/06/11 QC#50787 Mod End
                    if (firstErrPage < 0) {
                        firstErrPage = lineMsg.xxPageNum_LL.getValueInt();
                    }
                    errFlg = true;
                }
                // 2018/05/16 S21_NA#25144 add end
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
                        lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0912E);
                        // 2018/04/06 S21_NA#19808-2 Add Start
                        if (firstErrPage < 0) {
                            firstErrPage = lineMsg.xxPageNum_LL.getValueInt();
                        }
                        // 2018/04/06 S21_NA#19808-2 Add End
                        errFlg = true;
                    }
                }
            }
            // 2016/11/08 S21_NA#9867 Add End
        } else if (isCurrentTabRMA(bizMsg)) {
            // RMA_CONFIG
            List<Integer> selectedRowsForConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> selectedRowsForLine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            if (selectedRowsForConfig.size() == 0 && selectedRowsForLine.size() == 0) {
                glblMsg.C.no(0).xxChkBox_RC.setErrorInfo(1, NWAM0504E);
                // 2018/04/06 S21_NA#19808-2 Add Start
                if (firstErrPage < 0) {
                    firstErrPage = glblMsg.C.no(0).xxPageNum_RC.getValueInt();
                }
                // 2018/04/06 S21_NA#19808-2 Add End
                return true;
            }
            boolean isHdrSaved = NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue()); // 2016/06/03 S21_NA#9301 Add
            boolean isNewData = S21StringUtil.isEmpty(bizMsg.cpoOrdNum.getValue()); // 2016/06/07 Add S21_NA#9301-2
            for (Integer selectedRow : selectedRowsForConfig) {
                int idx = selectedRow.intValue();
                NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(idx);
                // QC#25227
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                    if (!S21StringUtil.isEquals(rmaLineMsg.dsOrdPosnNum_RL.getValue(), rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                        continue;
                    }
                    if (!ZYPCommonFunc.hasValue(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                        isNewData = true;
                        break;
                    }
                }

                // 2016/06/03 S21_NA#9301 Add Start
//                if (!isHdrSaved) { 2016/06/07 Mod Condition S21_NA#9301-2
                if (!isHdrSaved && !isNewData) {
                    rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, NWAM0769E);
                    // 2018/04/06 S21_NA#19808-2 Add Start
                    if (firstErrPage < 0) {
                        firstErrPage = rmaConfigMsg.xxPageNum_RC.getValueInt();
                    }
                    // 2018/04/06 S21_NA#19808-2 Add End
                    errFlg = true;
                    continue;
                }
                // 2016/06/03 S21_NA#9301 Add Start
                String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                    String lineDsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                    if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        continue;
                    }
                    String ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
                    if (!RTRN_LINE_STS.ENTERED.equals(ordLineStsCd) && ZYPCommonFunc.hasValue(ordLineStsCd)) {
                        rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, NWAM0769E);
                        // 2018/04/06 S21_NA#19808-2 Add Start
                        if (firstErrPage < 0) {
                            firstErrPage = rmaLineMsg.xxPageNum_RL.getValueInt();
                        }
                        // 2018/04/06 S21_NA#19808-2 Add End
                        errFlg = true;
                    }
                }
            }
            for (Integer selectedRow : selectedRowsForLine) {
                int idx = selectedRow.intValue();
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(idx);
                String ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
                // 2016/06/03 S21_NA#9301 Add Start
                // if (!isHdrSaved) {  2016/06/07 Mod Condition S21_NA#9301-2
//                if (!isHdrSaved && !isNewData) {
                if (!isHdrSaved && !isNewData && ZYPCommonFunc.hasValue(ordLineStsCd)) { // 2016/06/22 S21_NA#9301-3
                    rmaLineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0770E);
                    // 2018/04/06 S21_NA#19808-2 Add Start
                    if (firstErrPage < 0) {
                        firstErrPage = rmaLineMsg.xxPageNum_RL.getValueInt();
                    }
                    // 2018/04/06 S21_NA#19808-2 Add End
                    errFlg = true;
                } else if (!RTRN_LINE_STS.ENTERED.equals(ordLineStsCd) && ZYPCommonFunc.hasValue(ordLineStsCd)) {
                // 2016/06/03 S21_NA#9301 Add End
                // if (!RTRN_LINE_STS.ENTERED.equals(ordLineStsCd) && ZYPCommonFunc.hasValue(ordLineStsCd)) { 2016/06/03 S21_NA#9301 Del
                    rmaLineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0770E);
                    // 2018/04/06 S21_NA#19808-2 Add Start
                    if (firstErrPage < 0) {
                        firstErrPage = rmaLineMsg.xxPageNum_RL.getValueInt();
                    }
                    // 2018/04/06 S21_NA#19808-2 Add End
                    errFlg = true;
                }
            }
        }
        // 2018/04/06 S21_NA#19808-2 Add Start
        if (errFlg) {
            if (isCurrentTabLine(bizMsg)) {
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPage);
            } else if (isCurrentTabRMA(bizMsg)) {
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPage);
            }
        }
        // 2018/04/06 S21_NA#19808-2 Add End
        return errFlg;
    }

    private static void deleteForLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        List<NWAL1500_ASMsg> delTrgtConfigList = new ArrayList<NWAL1500_ASMsg>(0);
        List<NWAL1500_ASMsg> remainTrgtConfigList = new ArrayList<NWAL1500_ASMsg>(0);
        List<NWAL1500_BSMsg> delTrgtLineList = new ArrayList<NWAL1500_BSMsg>(0);
        List<NWAL1500_BSMsg> remainTrgtLineList = new ArrayList<NWAL1500_BSMsg>(0);

        List<String> delConfigList = new ArrayList<String>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            NWAL1500_ASMsg newConfigMsg = new NWAL1500_ASMsg();
            EZDMsg.copy(configMsg, null, newConfigMsg, null);

            boolean isEntered = ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC); // 2016/07/04 S21_NA#7821 Add
            if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_LC.getValue()) //
                    && !isEntered) { // 2016/07/04 S21_NA#7821 Add
                delTrgtConfigList.add(newConfigMsg);
                delConfigList.add(configMsg.dsOrdPosnNum_LC.getValue());

                String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    if (dsOrdPosnNum.equals(glblMsg.B.no(j).dsOrdPosnNum_LL.getValue())) {
                        glblMsg.B.no(j).xxChkBox_LL.setValue(ZYPConstant.FLG_ON_Y);
                        // 2016/07/11 S21_NA#7821 Del Start
//                        // 2016/04/20 S21_NA#5605 Add Start
//                        if (ZYPCommonFunc.hasValue(glblMsg.B.no(j).ordLineStsDescTxt_LL)) {
//                            bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                        }
//                        // 2016/04/20 S21_NA#5605 Add End
                        // 2016/07/11 S21_NA#7821 Del End
                    }
                }
            } else {
                remainTrgtConfigList.add(newConfigMsg);
                // 2016/07/11 S21_NA#7821 Add Start
                if (isEntered && ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_LC.getValue())) {
                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
                }
                // 2016/07/11 S21_NA#7821 Add End
            }
        }

        // 2019/01/17 S21_NA#29784 Add Start
        List<String> dplyRefNumList = getDplyRefNumList(glblMsg, new ArrayList<String>());
        Map<String, String> dplyRefNumMap = new HashMap<String, String>();
        // 2019/01/17 S21_NA#29784 Add End
        List<String> modConfigList = new ArrayList<String>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            NWAL1500_BSMsg newLineMsg = new NWAL1500_BSMsg();
            EZDMsg.copy(lineMsg, null, newLineMsg, null);
            // 2019/01/17 S21_NA#29784 Add Start
            String dplyCpoLineNum = lineMsg.xxLineNum_LL.getValue();
            String cpoDtlLineSubLineNum = lineMsg.cpoDtlLineNum_LL.getValue() + lineMsg.cpoDtlLineSubNum_LL.getValue();
            if (dplyRefNumList.contains(dplyCpoLineNum)) {
                dplyRefNumMap.put(dplyCpoLineNum, cpoDtlLineSubLineNum);
            }
            // 2019/01/17 S21_NA#29784 Add End
            
            boolean isEntered = ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL); // 2016/07/04 S21_NA#7821 Add
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_LL.getValue()) //
                    && !isEntered) { // 2016/07/04 S21_NA#7821 Add
                delTrgtLineList.add(newLineMsg);
                // 2016/07/11 S21_NA#7821 Del Start
//                // 2016/04/20 S21_NA#5605 Add Start
//                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).ordLineStsDescTxt_LL)) {
//                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                }
//                // 2016/04/20 S21_NA#5605 Add End
                // 2016/07/11 S21_NA#7821 Del End

                if (!modConfigList.contains(lineMsg.dsOrdPosnNum_LL.getValue())
                        && !delConfigList.contains(lineMsg.dsOrdPosnNum_LL.getValue())) {
                    modConfigList.add(lineMsg.dsOrdPosnNum_LL.getValue());
                }
                // for set component
                int cntForSet = i + 1;
                for (; cntForSet < glblMsg.B.getValidCount(); cntForSet++) {
                    NWAL1500_BSMsg setChildLineMsg = glblMsg.B.no(cntForSet);
                    if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), setChildLineMsg.dsOrdPosnNum_LL.getValue()) // 
                            && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_LL.getValue(), setChildLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
                        NWAL1500_BSMsg setChiledNewLineMsg = new NWAL1500_BSMsg();
                        EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                        delTrgtLineList.add(setChiledNewLineMsg);
                        // 2016/07/11 S21_NA#7821 Del Start
//                        // 2016/04/20 S21_NA#5605 Add Start
//                        if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).ordLineStsDescTxt_LL)) {
//                            bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                        }
//                        // 2016/04/20 S21_NA#5605 Add End
                        // 2016/07/11 S21_NA#7821 Del End
                        i++;
                    } else {
                        break;
                    }
                }
                i = cntForSet - 1;
            } else {
                remainTrgtLineList.add(newLineMsg);
                // 2016/07/11 S21_NA#7821 Add Start
                if (isEntered && ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_LL.getValue())) {
                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);

                    // 2016/08/04 S21_NA#7821-2 Add Start
                    // for set component
                    int cntForSet = i + 1;
                    for (; cntForSet < glblMsg.B.getValidCount(); cntForSet++) {
                        NWAL1500_BSMsg setChildLineMsg = glblMsg.B.no(cntForSet);
                        if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), setChildLineMsg.dsOrdPosnNum_LL.getValue()) // 
                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_LL.getValue(), setChildLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
                            setChildLineMsg.xxChkBox_LL.setValue(ZYPConstant.FLG_ON_Y);
                            NWAL1500_BSMsg setChiledNewLineMsg = new NWAL1500_BSMsg();
                            EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                            remainTrgtLineList.add(setChiledNewLineMsg);
                        } else {
                            break;
                        }
                    }
                    i = cntForSet - 1;
                    // 2016/08/04 S21_NA#7821-2 Add End
                }
                // 2016/07/11 S21_NA#7821 Add End
            }
        }

        // Delete Lines
        glblMsg.B.clear();
        int valCnt = 0;
        for (NWAL1500_BSMsg lineMsg : remainTrgtLineList) {
            EZDMsg.copy(lineMsg, null, glblMsg.B.no(valCnt++), null);
        }
        glblMsg.B.setValidCount(valCnt);

//        resetModel(bizMsg, modConfigList); 2016/03/25 S21_NA#3236-2 Del

        // Config
        if (delTrgtConfigList.size() > 0) {
            glblMsg.A.clear();
            valCnt = 0;
            List<NWAL1500_GCMsg> remainSlsCrMsgList = new ArrayList<NWAL1500_GCMsg>();
            for (NWAL1500_ASMsg configMsg : remainTrgtConfigList) {
                EZDMsg.copy(configMsg, null, glblMsg.A.no(valCnt++), null);

                // Sales Credit Reset
                String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                    NWAL1500_GCMsg slsCrMsg = bizMsg.G.no(i);
                    if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_GS.getValue())) {
                        NWAL1500_GCMsg newSlsCrMsg = new NWAL1500_GCMsg();
                        EZDMsg.copy(slsCrMsg, null, newSlsCrMsg, null);
                        remainSlsCrMsgList.add(newSlsCrMsg);
                    }
                }
            }
            glblMsg.A.setValidCount(valCnt);

            bizMsg.G.clear();
            valCnt = 0;
            for (NWAL1500_GCMsg slsCrMsg : remainSlsCrMsgList) {
                EZDMsg.copy(slsCrMsg, null, bizMsg.G.no(valCnt++), null);
            }
            bizMsg.G.setValidCount(valCnt);
        }

        renumPosnNum(bizMsg, glblMsg);
        // 2018/06/01 S21_NA#26334 Del Start
        //renumRefPosn(glblMsg);
        // 2018/06/01 S21_NA#26334 Del End
        renumDplyLineNum(bizMsg, glblMsg);
        resetRefNum4Del(bizMsg, glblMsg, dplyRefNumMap); // 2019/01/18 S21_NA#29784 Add

        resetBaseComponet(bizMsg, glblMsg); // 2018/02/03 S21_NA#19808 Mod
        // 2018/06/01 S21_NA#26334 Del Start
        // resetRefLineNum(bizMsg, glblMsg);
        // 2018/06/01 S21_NA#26334 Del End

        resetModel(bizMsg, glblMsg, modConfigList); // 2016/03/25 S21_NA#3236-2 Add

        // Price Message
        List<NWAL1500_ISMsg> remainTrgtPrcMsgList = new ArrayList<NWAL1500_ISMsg>();
        for (NWAL1500_BSMsg lineMsg : remainTrgtLineList) {
            String cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();

            for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                NWAL1500_ISMsg prcMsg = glblMsg.I.no(j);

                if (!CONFIG_CATG.OUTBOUND.equals(prcMsg.configCatgCd_LP.getValue())) {
                    continue;
                }
                if (cpoDtlLineNum.equals(prcMsg.cpoDtlLineNum_LP.getValue())
                        && cpoDtlLineSubNum.equals(prcMsg.cpoDtlLineSubNum_LP.getValue())) {
                    NWAL1500_ISMsg newPrcMsg = new NWAL1500_ISMsg();
                    EZDMsg.copy(prcMsg, null, newPrcMsg, null);
                    remainTrgtPrcMsgList.add(newPrcMsg);
                }
            }
        }
        List<NWAL1500_ISMsg> rmaPrcMsgList = getPrcMsg(glblMsg, CONFIG_CATG.INBOUND); // 2016/04/20 S21_NA#5605 Add
        glblMsg.I.clear();
        valCnt = 0;
        for (NWAL1500_ISMsg prcMsg : remainTrgtPrcMsgList) {
            EZDMsg.copy(prcMsg, null, glblMsg.I.no(valCnt++), null);
        }
        // 2016/04/20 S21_NA#5605 Add Start
        for (NWAL1500_ISMsg rmaPrcMsg : rmaPrcMsgList) {
            EZDMsg.copy(rmaPrcMsg, null, glblMsg.I.no(valCnt++), null);
        }
        //2016/04/20 S21_NA#5605 Add End
        glblMsg.I.setValidCount(valCnt);

        // 2016/07/11 S21_NA#7821 Del Start
//        // Delete From DB
//        // DS_CPO_CONFIG, DS_CPO_SLS_CR
//        for (NWAL1500_ASMsg delTrgtConfig : delTrgtConfigList) {
//            if (ZYPCommonFunc.hasValue(delTrgtConfig.dsCpoConfigPk_LC)) {
//                removeConfigRec(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), delTrgtConfig);
//            }
//        }
//        // CPO_DTL, DS_CPO_DTL, ORD_PRC_CALC_BASE
//        for (NWAL1500_BCMsg delTrgtLine : delTrgtLineList) {
//            if (ZYPCommonFunc.hasValue(delTrgtLine.ordLineStsDescTxt_LL)) {
//                removeDtlRec(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), delTrgtLine);
//            }
//        }
        // 2016/07/11 S21_NA#7821 Del End
    }


    private static void deleteForRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        List<NWAL1500_CSMsg> delTrgtConfigList = new ArrayList<NWAL1500_CSMsg>(0);
        List<NWAL1500_CSMsg> remainTrgtConfigList = new ArrayList<NWAL1500_CSMsg>(0);
        List<NWAL1500_DSMsg> delTrgtLineList = new ArrayList<NWAL1500_DSMsg>(0);
        List<NWAL1500_DSMsg> remainTrgtLineList = new ArrayList<NWAL1500_DSMsg>(0);
        // Add Start 2017/02/08 QC#17257
        List<String> posnNums = new ArrayList<String>();
        String getPosnNum = null;
        // Add End 2017/02/08 QC#17257

        // 2017/11/21 S21_NA#22555 Add Start
        List<String> delConfigList = new ArrayList<String>();
        // 2017/11/21 S21_NA#22555 Add End
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configMsg = glblMsg.C.no(i);
            NWAL1500_CSMsg newConfigMsg = new NWAL1500_CSMsg();
            EZDMsg.copy(configMsg, null, newConfigMsg, null);

            boolean isEntered = ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_RC); // 2016/07/04 S21_NA#7821 Add
            if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_RC.getValue()) //
                    && !isEntered) { // 2016/07/04 S21_NA#7821 Add
                delTrgtConfigList.add(newConfigMsg);
                // 2017/11/21 S21_NA#22555 Add Start
                delConfigList.add(configMsg.dsOrdPosnNum_RC.getValue());
                // 2017/11/21 S21_NA#22555 Add End

                // Add Start 2017/02/08 QC#17257
                getPosnNum = glblMsg.C.no(i).dsOrdPosnNum_RC.getValue();
                if (!posnNums.contains(getPosnNum)) {
                    posnNums.add(getPosnNum);
                }
                // Add End 2017/02/08 QC#17257

                String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                    if (dsOrdPosnNum.equals(glblMsg.D.no(j).dsOrdPosnNum_RL.getValue())) {
                        glblMsg.D.no(j).xxChkBox_RL.setValue(ZYPConstant.FLG_ON_Y);
                        // 2016/07/11 S21_NA#7821 Del Start
//                        // 2016/04/20 S21_NA#5605 Add Start
//                        if (ZYPCommonFunc.hasValue(glblMsg.D.no(j).rtrnLineStsDescTxt_RL)) {
//                            bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                        }
//                        // 2016/04/20 S21_NA#5605 Add End
                        // 2016/07/11 S21_NA#7821 Del End
                    }
                }
            } else {
                remainTrgtConfigList.add(newConfigMsg);
                // 2016/07/11 S21_NA#7821 Add Start
                if (isEntered && ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_RC.getValue())) {
                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);

                    // Add Start 2017/02/08 QC#17257
                    getPosnNum = glblMsg.C.no(i).dsOrdPosnNum_RC.getValue();
                    if (!posnNums.contains(getPosnNum)) {
                        posnNums.add(getPosnNum);
                    }
                    // Add End 2017/02/08 QC#17257
                }
                // 2016/07/11 S21_NA#7821 Add End
            }
        }

        // 2017/11/21 S21_NA#22555 Add Start
        List<String> modConfigList = new ArrayList<String>();
        // 2017/11/21 S21_NA#22555 Add End
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            NWAL1500_DSMsg newLineMsg = new NWAL1500_DSMsg();
            EZDMsg.copy(lineMsg, null, newLineMsg, null);

            boolean isEntered = ZYPCommonFunc.hasValue(lineMsg.rtrnLineStsDescTxt_RL); // 2016/07/04 S21_NA#7821 Add
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_RL.getValue()) //
                    && !isEntered) { // 2016/07/04 S21_NA#7821 Add
                delTrgtLineList.add(newLineMsg);

                // Add Start 2017/02/08 QC#17257
                getPosnNum = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
                if (!posnNums.contains(getPosnNum)) {
                    posnNums.add(getPosnNum);
                }
                // Add End 2017/02/08 QC#17257

                // 2016/07/11 S21_NA#7821 Del Start
//                // 2016/04/20 S21_NA#5605 Add Start
//                if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL)) {
//                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                }
//                // 2016/04/20 S21_NA#5605 Add End
                // 2016/07/11 S21_NA#7821 Del End
                // 2017/11/21 S21_NA#22555 Add Start
                if (!modConfigList.contains(lineMsg.dsOrdPosnNum_RL.getValue())
                        && !delConfigList.contains(lineMsg.dsOrdPosnNum_RL.getValue())) {
                    modConfigList.add(lineMsg.dsOrdPosnNum_RL.getValue());
                }
                // 2017/11/21 S21_NA#22555 Add End

                // for set component
                int cntForSet = i + 1;
                for (; cntForSet < glblMsg.D.getValidCount(); cntForSet++) {
                    NWAL1500_DSMsg setChildLineMsg = glblMsg.D.no(cntForSet);
                    // 2017/06/09 QC#18643 Mod Start
//                    if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), setChildLineMsg.dsOrdPosnNum_RL.getValue()) // 
//                            && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), setChildLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                    if (!ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)
                            && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), setChildLineMsg.dsOrdPosnNum_RL.getValue()) // 
                            && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), setChildLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                    // 2017/06/09 QC#18643 Mod End
                        NWAL1500_DSMsg setChiledNewLineMsg = new NWAL1500_DSMsg();
                        EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                        delTrgtLineList.add(setChiledNewLineMsg);
                        // 2016/07/11 S21_NA#7821 Del Start
//                        // 2016/04/20 S21_NA#5605 Add Start
//                        if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL)) {
//                            bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);
//                        }
//                        // 2016/04/20 S21_NA#5605 Add End
                        // 2016/07/11 S21_NA#7821 Del End
                        i++;
                    } else {
                        break;
                    }
                }
                i = cntForSet - 1;
                // 2017/06/09 QC#18643 Add Start
                // delete parent
                if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)) {
                    boolean isDelPrnt = true;
                    for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                        NWAL1500_DSMsg diffLineMsg = glblMsg.D.no(j);
                        if (ZYPCommonFunc.hasValue(diffLineMsg.dsCpoLineSubNum_RL)
                                && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), diffLineMsg.dsOrdPosnNum_RL.getValue())
                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), diffLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                            isDelPrnt &= ZYPConstant.FLG_ON_Y.equals(diffLineMsg.xxChkBox_RL.getValue());
                        }
                    }

                    if (isDelPrnt) {
                        NWAL1500_DSMsg parentMsg = null;
                        for (NWAL1500_DSMsg remainLine : remainTrgtLineList) {
                            if (!ZYPCommonFunc.hasValue(remainLine.dsCpoLineSubNum_RL)
                                    && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), remainLine.dsOrdPosnNum_RL.getValue())
                                    && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), remainLine.dsCpoLineNum_RL.getValue()) == 0) {
                                parentMsg = remainLine;
                                break;
                            }
                        }

                        if (parentMsg != null) {
                            remainTrgtLineList.remove(parentMsg);
                        }
                    }
                }
                // 2017/06/09 QC#18643 Add End
            } else {
                remainTrgtLineList.add(newLineMsg);
                // 2016/07/11 S21_NA#7821 Add Start
                if (isEntered && ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_RL.getValue())) {
                    bizMsg.xxPopPrm_PJ.setValue(ZYPConstant.FLG_ON_Y);

                    // Add Start 2017/02/08 QC#17257
                    getPosnNum = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
                    if (!posnNums.contains(getPosnNum)) {
                        posnNums.add(getPosnNum);
                    }
                    // Add End 2017/02/08 QC#17257

                    // 2016/08/04 S21_NA#7821-2 Add Start
                    // for set component
                    int cntForSet = i + 1;
                    for (; cntForSet < glblMsg.D.getValidCount(); cntForSet++) {
                        NWAL1500_DSMsg setChildLineMsg = glblMsg.D.no(cntForSet);
                        // 2017/06/09 QC#18643 Mod Start
//                        if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), setChildLineMsg.dsOrdPosnNum_RL.getValue()) // 
//                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), setChildLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                        if (!ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)
                                && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), setChildLineMsg.dsOrdPosnNum_RL.getValue()) // 
                                && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), setChildLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                        // 2017/06/09 QC#18643 Mod End
                            setChildLineMsg.xxChkBox_RL.setValue(ZYPConstant.FLG_ON_Y);
                            NWAL1500_DSMsg setChiledNewLineMsg = new NWAL1500_DSMsg();
                            EZDMsg.copy(setChildLineMsg, null, setChiledNewLineMsg, null);
                            remainTrgtLineList.add(setChiledNewLineMsg);
                        } else {
                            break;
                        }
                    }
                    i = cntForSet - 1;
                    // 2017/06/09 QC#18643 Add Start
                    // delete parent
                    if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)) {
                        boolean isDelPrnt = true;
                        for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                            NWAL1500_DSMsg diffLineMsg = glblMsg.D.no(j);
                            if (ZYPCommonFunc.hasValue(diffLineMsg.dsCpoLineSubNum_RL)
                                    && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), diffLineMsg.dsOrdPosnNum_RL.getValue())
                                    && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), diffLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                                isDelPrnt &= ZYPConstant.FLG_ON_Y.equals(diffLineMsg.xxChkBox_RL.getValue());
                            }
                        }

                        if (isDelPrnt) {
                            NWAL1500_DSMsg parentMsg = null;
                            for (NWAL1500_DSMsg remainLine : remainTrgtLineList) {
                                if (!ZYPCommonFunc.hasValue(remainLine.dsCpoLineSubNum_RL)
                                        && S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_RL.getValue(), remainLine.dsOrdPosnNum_RL.getValue())
                                        && NWAL1500CommonLogic.compareBigDecimal(lineMsg.dsCpoLineNum_RL.getValue(), remainLine.dsCpoLineNum_RL.getValue()) == 0) {
                                    parentMsg = remainLine;
                                    break;
                                }
                            }

                            if (parentMsg != null) {
                                parentMsg.xxChkBox_RL.setValue(ZYPConstant.FLG_ON_Y);
                            }
                        }
                    }
                    // 2017/06/09 QC#18643 Add End
                    // 2016/08/04 S21_NA#7821-2 Add End
                }
                // 2016/07/11 S21_NA#7821 Add End
            }
        }

        // Delete Lines
        glblMsg.D.clear();
        int valCnt = 0;
        for (NWAL1500_DSMsg lineMsg : remainTrgtLineList) {
            EZDMsg.copy(lineMsg, null, glblMsg.D.no(valCnt++), null);
        }
        glblMsg.D.setValidCount(valCnt);

        // Config
        if (delTrgtConfigList.size() > 0) {
            glblMsg.C.clear();
            valCnt = 0;
            List<NWAL1500_HCMsg> remainSlsCrMsgList = new ArrayList<NWAL1500_HCMsg>();
            for (NWAL1500_CSMsg configMsg : remainTrgtConfigList) {
                EZDMsg.copy(configMsg, null, glblMsg.C.no(valCnt++), null);

                // Sales Credit Reset
                String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                    NWAL1500_HCMsg slsCrMsg = bizMsg.H.no(i);
                    if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_HS.getValue())) {
                        NWAL1500_HCMsg newSlsCrMsg = new NWAL1500_HCMsg();
                        EZDMsg.copy(slsCrMsg, null, newSlsCrMsg, null);
                        remainSlsCrMsgList.add(newSlsCrMsg);
                    }
                }
            }
            glblMsg.C.setValidCount(valCnt);

            bizMsg.H.clear();
            valCnt = 0;
            for (NWAL1500_HCMsg slsCrMsg : remainSlsCrMsgList) {
                EZDMsg.copy(slsCrMsg, null, bizMsg.H.no(valCnt++), null);
            }
            bizMsg.H.setValidCount(valCnt);
        }

        renumPosnNum4Rma(bizMsg, glblMsg);
        renumRefPosn4Rma(glblMsg);
        renumDplyLineNum4Rma(bizMsg, glblMsg);

        // 2017/11/21 S21_NA#22555 Add Start
        resetBaseComponet4Rma(bizMsg, glblMsg);
        // 2017/11/21 S21_NA#22555 Add End

        // Mod Start 2017/02/03 QC#17257
//        resetBaseComponet4Rma(bizMsg);
        NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, true); // 2018/02/03 S21_NA#19808
        // Mod End 2017/02/03 QC#17257
        resetRefLineNum4Rma(bizMsg, glblMsg);
        // 2017/11/21 S21_NA#22555 Add Start
        resetModel4Rma(bizMsg, glblMsg, modConfigList);
        // 2017/11/21 S21_NA#22555 Add End

        //QC743
//        glblMsg.J.clear();
//        for (valCnt = 0; valCnt < glblMsg.D.getValidCount(); valCnt++) {
//            EZDMsg.copy(glblMsg.D.no(valCnt), null, glblMsg.J.no(valCnt), null);
//        }
//        glblMsg.J.setValidCount(valCnt);

        // Price Message
        List<NWAL1500_ISMsg> remainTrgtPrcMsgList = new ArrayList<NWAL1500_ISMsg>();
        for (NWAL1500_DSMsg lineMsg : remainTrgtLineList) {
            String cpoDtlLineNum = lineMsg.cpoDtlLineNum_RL.getValue();
            String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_RL.getValue();

            for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                NWAL1500_ISMsg prcMsg = glblMsg.I.no(j);

                if (!CONFIG_CATG.INBOUND.equals(prcMsg.configCatgCd_LP.getValue())) {
                    continue;
                }
                if (cpoDtlLineNum.equals(prcMsg.cpoDtlLineNum_LP.getValue())
                        && cpoDtlLineSubNum.equals(prcMsg.cpoDtlLineSubNum_LP.getValue())) {
                    NWAL1500_ISMsg newPrcMsg = new NWAL1500_ISMsg();
                    EZDMsg.copy(prcMsg, null, newPrcMsg, null);
                    remainTrgtPrcMsgList.add(newPrcMsg);
                }
            }
        }
        List<NWAL1500_ISMsg> linePrcMsgList = getPrcMsg(glblMsg, CONFIG_CATG.OUTBOUND); // 2016/04/20 S21_NA#5605 Add
        glblMsg.I.clear();
        valCnt = 0;
        for (NWAL1500_ISMsg prcMsg : remainTrgtPrcMsgList) {
            EZDMsg.copy(prcMsg, null, glblMsg.I.no(valCnt++), null);
        }
        // 2016/04/20 S21_NA#5605 Add Start
        for (NWAL1500_ISMsg linePrcMsg : linePrcMsgList) {
            EZDMsg.copy(linePrcMsg, null, glblMsg.I.no(valCnt++), null);
        }
        //2016/04/20 S21_NA#5605 Add End
        glblMsg.I.setValidCount(valCnt);

        // 2016/07/04 S21_NA#7821 Del Start
//        // Delete From DB
//        // DS_CPO_CONFIG, DS_CPO_SLS_CR
//        for (NWAL1500_CCMsg delTrgtConfig : delTrgtConfigList) {
//            if (ZYPCommonFunc.hasValue(delTrgtConfig.dsCpoConfigPk_RC)) {
//                removeConfigRec4Rma(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), delTrgtConfig);
//            }
//        }
//        // CPO_DTL, DS_CPO_DTL, ORD_PRC_CALC_BASE
//        for (NWAL1500_DCMsg delTrgtLine : delTrgtLineList) {
//            if (ZYPCommonFunc.hasValue(delTrgtLine.rtrnLineStsDescTxt_RL)) {
//                cancelAllocForSvcMachMstr(bizMsg, delTrgtLine); // 2016/04/05 S21_NA#5519-2
//                removeDtlRec4Rma(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), delTrgtLine);
//            }
//        }
        // 2016/07/04 S21_NA#7821 Del End
    }

    // 2016/07/04 S21_NA#7821 Del Start
//    private static void removeConfigRec(String glblCmpyCd, String cpoOrdNum, NWAL1500_ACMsg delTrgtConfig) {
//
//        DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
//        configTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        configTMsg.dsCpoConfigPk.setValue(delTrgtConfig.dsCpoConfigPk_LC.getValue());
//        configTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        configTMsg.dsOrdPosnNum.setValue(delTrgtConfig.dsOrdPosnNum_LC.getValue());
//
//        EZDTBLAccessor.remove(configTMsg);
//
//        S21SsmEZDResult ssmRslt = NWAL1500QueryForDelete.getInstance().getDsCpoSlsCrList(configTMsg);
//        if (ssmRslt.isCodeNormal()) {
//            List<DS_CPO_SLS_CRTMsg> dsCpoSlsCrTMsgList = (List<DS_CPO_SLS_CRTMsg>) ssmRslt.getResultObject();
//
//            for (DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg : dsCpoSlsCrTMsgList) {
//                EZDTBLAccessor.remove(dsCpoSlsCrTMsg);
//            }
//        }
//    }
//
//    private static void removeConfigRec4Rma(String glblCmpyCd, String cpoOrdNum, NWAL1500_CCMsg delTrgtConfig) {
//
//        DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
//        configTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        configTMsg.dsCpoConfigPk.setValue(delTrgtConfig.dsCpoConfigPk_RC.getValue());
//        configTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        configTMsg.dsOrdPosnNum.setValue(delTrgtConfig.dsOrdPosnNum_RC.getValue());
//
//        EZDTBLAccessor.remove(configTMsg);
//
//        S21SsmEZDResult ssmRslt = NWAL1500QueryForDelete.getInstance().getDsCpoSlsCrList(configTMsg);
//        if (ssmRslt.isCodeNormal()) {
//            List<DS_CPO_SLS_CRTMsg> dsCpoSlsCrTMsgList = (List<DS_CPO_SLS_CRTMsg>) ssmRslt.getResultObject();
//
//            for (DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg : dsCpoSlsCrTMsgList) {
//                EZDTBLAccessor.remove(dsCpoSlsCrTMsg);
//            }
//        }
//    }
//
//    private static void removeDtlRec(String glblCmpyCd, String cpoOrdNum, NWAL1500_BCMsg lineMsg) {
//
//        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
//        cpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        cpoDtlTMsg.cpoDtlLineNum.setValue(lineMsg.cpoDtlLineNum_LL.getValue());
//        cpoDtlTMsg.cpoDtlLineSubNum.setValue(lineMsg.cpoDtlLineSubNum_LL.getValue());
//
//        EZDTBLAccessor.remove(cpoDtlTMsg);
//
//        DS_CPO_DTLTMsg dsCpoDtlTMsg = new DS_CPO_DTLTMsg();
//        dsCpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        dsCpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        dsCpoDtlTMsg.cpoDtlLineNum.setValue(lineMsg.cpoDtlLineNum_LL.getValue());
//        dsCpoDtlTMsg.cpoDtlLineSubNum.setValue(lineMsg.cpoDtlLineSubNum_LL.getValue());
//
//        EZDTBLAccessor.remove(dsCpoDtlTMsg);
//
//        S21SsmEZDResult ssmRslt = NWAL1500QueryForDelete.getInstance().getOrdPrcCalBaseList(cpoDtlTMsg);
//        if (ssmRslt.isCodeNormal()) {
//            List<ORD_PRC_CALC_BASETMsg> ordPrcCalcBaseTMsgList = (List<ORD_PRC_CALC_BASETMsg>) ssmRslt.getResultObject();
//
//            for (ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg : ordPrcCalcBaseTMsgList) {
//                EZDTBLAccessor.remove(ordPrcCalcBaseTMsg);
//            }
//        }
//    }
//
//    private static void removeDtlRec4Rma(String glblCmpyCd, String cpoOrdNum, NWAL1500_DCMsg lineMsg) {
//
//        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
//        dsCpoRtrnDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        dsCpoRtrnDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.setValue(lineMsg.cpoDtlLineNum_RL.getValue());
//        dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.setValue(lineMsg.cpoDtlLineSubNum_RL.getValue());
//
//        EZDTBLAccessor.remove(dsCpoRtrnDtlTMsg);
//
//        S21SsmEZDResult ssmRslt = NWAL1500QueryForDelete.getInstance().getCpoRtrnPrcCalBaseList(dsCpoRtrnDtlTMsg);
//        if (ssmRslt.isCodeNormal()) {
//            List<CPO_RTRN_PRC_CALC_BASETMsg> cpoRtrnPrcCalcBaseTMsgList = (List<CPO_RTRN_PRC_CALC_BASETMsg>) ssmRslt.getResultObject();
//
//            for (CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseTMsg : cpoRtrnPrcCalcBaseTMsgList) {
//                EZDTBLAccessor.remove(cpoRtrnPrcCalcBaseTMsg);
//            }
//        }
//    }
    // 2016/07/04 S21_NA#7821 Del End

    private static void renumPosnNum(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Map<String, String> renumMap = new HashMap<String, String>(); 2016/05/16 S21_NA#8144 Del
        int posnNum = 1;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            int curPosnNum = Integer.valueOf(glblMsg.A.no(i).dsOrdPosnNum_LC.getValue()).intValue();
            if (posnNum != curPosnNum) {
                // renumMap.put(String.valueOf(curPosnNum), String.valueOf(posnNum)); 2016/05/16 S21_NA#8144 Del
                // glblMsg.A.no(i).dsOrdPosnNum_LC.setValue(String.valueOf(posnNum)); 2016/05/16 S21_NA#8144 Del
                // 2016/05/16 S21_NA#8144 Add Start
                String dsOrdPosnNum = glblMsg.A.no(i).dsOrdPosnNum_LC.getValue();
                String newDsOrdPosnNum = String.valueOf(posnNum);

                glblMsg.A.no(i).dsOrdPosnNum_LC.setValue(newDsOrdPosnNum);

                for (int slsCrCnt = 0; slsCrCnt < bizMsg.G.getValidCount(); slsCrCnt++) {
                    if (dsOrdPosnNum.equals(bizMsg.G.no(slsCrCnt).dsOrdPosnNum_GS.getValue())) {
                        bizMsg.G.no(slsCrCnt).dsOrdPosnNum_GS.setValue(newDsOrdPosnNum);
                    }
                }

                for (int lineCnt = 0; lineCnt < glblMsg.B.getValidCount(); lineCnt++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(lineCnt).dsOrdPosnNum_LL.getValue())) {
                        glblMsg.B.no(lineCnt).dsOrdPosnNum_LL.setValue(newDsOrdPosnNum);
                    }
                }
                // 2016/05/16 S21_NA#8144 Add End
            }
            posnNum++;
        }

        // 2016/05/16 S21_NA#8144 Del Start
//        Set<String> origPosnNumSet = renumMap.keySet();
//
//        for (String dsOrdPosnNum : origPosnNumSet) {
//            System.out.println("@@@@ target Position = " + dsOrdPosnNum + "@@@@");
//            String newDsOrdPosnNum = renumMap.get(dsOrdPosnNum);
//
//            for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
//                if (dsOrdPosnNum.equals(bizMsg.G.no(i).dsOrdPosnNum_GS.getValue())) {
//                    bizMsg.G.no(i).dsOrdPosnNum_GS.setValue(newDsOrdPosnNum);
//                }
//            }
//
//            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                if (dsOrdPosnNum.equals(glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                    glblMsg.B.no(i).dsOrdPosnNum_LL.setValue(newDsOrdPosnNum);
//
//                    String[] xxLineNumArray = glblMsg.B.no(i).xxLineNum_LL.getValue().split("\\.");
//
//                    String newLineNum = newDsOrdPosnNum + "." + xxLineNumArray[1];
//                    if (xxLineNumArray.length == 3) {
//                        newLineNum = newLineNum + "." + xxLineNumArray[2];
//                    }
//                    glblMsg.B.no(i).xxLineNum_LL.setValue(newLineNum);
//                }
//            }
//        }
        // 2016/05/16 S21_NA#8144 Del End
    }

    private static void renumPosnNum4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Map<String, String> renumMap = new HashMap<String, String>(); 2016/05/16 S21_NA#8144 Del
        int posnNum = 1;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            int curPosnNum = Integer.valueOf(glblMsg.C.no(i).dsOrdPosnNum_RC.getValue()).intValue();
            if (posnNum != curPosnNum) {
                // renumMap.put(String.valueOf(curPosnNum), String.valueOf(posnNum)); 2016/05/16 S21_NA#8144 Del
                // glblMsg.C.no(i).dsOrdPosnNum_RC.setValue(String.valueOf(posnNum)); 2016/05/16 S21_NA#8144 Del

                // 2016/05/16 S21_NA#8144 Add Start
                String dsOrdPosnNum = glblMsg.C.no(i).dsOrdPosnNum_RC.getValue();
                String newDsOrdPosnNum = String.valueOf(posnNum);

                glblMsg.C.no(i).dsOrdPosnNum_RC.setValue(newDsOrdPosnNum);

                for (int slsCrCnt = 0; slsCrCnt < bizMsg.H.getValidCount(); slsCrCnt++) {
                    if (dsOrdPosnNum.equals(bizMsg.H.no(slsCrCnt).dsOrdPosnNum_HS.getValue())) {
                        bizMsg.H.no(slsCrCnt).dsOrdPosnNum_HS.setValue(newDsOrdPosnNum);
                    }
                }

                for (int lineCnt = 0; lineCnt < glblMsg.D.getValidCount(); lineCnt++) {
                    if (dsOrdPosnNum.equals(glblMsg.D.no(lineCnt).dsOrdPosnNum_RL.getValue())) {
                        glblMsg.D.no(lineCnt).dsOrdPosnNum_RL.setValue(newDsOrdPosnNum);

                        String[] xxLineNumArray = glblMsg.D.no(lineCnt).xxLineNum_RL.getValue().split("\\.");

                        String newLineNum = newDsOrdPosnNum + "." + xxLineNumArray[1];
                        if (xxLineNumArray.length == 3) {
                            newLineNum = newLineNum + "." + xxLineNumArray[2];
                        }
                        glblMsg.D.no(lineCnt).xxLineNum_RL.setValue(newLineNum);
                    }
                }
                // 2016/05/16 S21_NA#8144 Add End
            }
            posnNum++;
        }

        // 2016/05/16 S21_NA#8144 Del Start
//        Set<String> origPosnNumSet = renumMap.keySet();
//
//        for (String dsOrdPosnNum : origPosnNumSet) {
//            String newDsOrdPosnNum = renumMap.get(dsOrdPosnNum);
//
//            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
//                if (dsOrdPosnNum.equals(bizMsg.H.no(i).dsOrdPosnNum_HS.getValue())) {
//                    bizMsg.H.no(i).dsOrdPosnNum_HS.setValue(newDsOrdPosnNum);
//                }
//            }
//
//            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
//                if (dsOrdPosnNum.equals(glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
//                    glblMsg.D.no(i).dsOrdPosnNum_RL.setValue(newDsOrdPosnNum);
//
//                    String[] xxLineNumArray = glblMsg.D.no(i).xxLineNum_RL.getValue().split("\\.");
//
//                    String newLineNum = newDsOrdPosnNum + "." + xxLineNumArray[1];
//                    if (xxLineNumArray.length == 3) {
//                        newLineNum = newLineNum + "." + xxLineNumArray[2];
//                    }
//                    glblMsg.D.no(i).xxLineNum_RL.setValue(newLineNum);
//                }
//            }
//        }
        // 2016/05/16 S21_NA#8144 Del End
    }

    private static void renumRefPosn(NWAL1500SMsg glblMsg) {
        Map<String, String> newRefNumMap = new HashMap<String, String>();
        // 2016/07/04 S21_NA#7821 Add Start
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            newRefNumMap.put(glblMsg.A.no(i).dsOrdPosnNum_LC.getValue(), "");
        }
        // 2016/07/04 S21_NA#7821 Add End
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
                newRefNumMap.put(dsOrdPosnNum, lineMsg.xxLineNum_LL.getValue());
            }
        }

        Set<String> dsOrdPosnNumSet = newRefNumMap.keySet();
        for (String dsOrdPosnNum : dsOrdPosnNumSet) {
            String newRefNum = newRefNumMap.get(dsOrdPosnNum);
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue()) //
                        && !ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_LL, newRefNum);
                }
            }
        }
    }


    // 2016/07/04 S21_NA#7821 Add Start
    private static void renumRefPosn4Rma(NWAL1500SMsg glblMsg) {
        Map<String, String> newRefNumMap = new HashMap<String, String>();
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            newRefNumMap.put(glblMsg.C.no(i).dsOrdPosnNum_RC.getValue(), "");
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_RL.getValue())) {
                newRefNumMap.put(dsOrdPosnNum, rmaLineMsg.xxLineNum_RL.getValue());
            }
        }

        Set<String> dsOrdPosnNumSet = newRefNumMap.keySet();
        for (String dsOrdPosnNum : dsOrdPosnNumSet) {
            String newRefNum = newRefNumMap.get(dsOrdPosnNum);
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue()) //
                        && !ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_RL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.dplyLineRefNum_RL, newRefNum);
                }
            }
        }
    }
    // 2016/07/04 S21_NA#7821 Add End

    private static void renumDplyLineNum(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int num = 0;
        int prevNum = -1;

        String prevDsOrdPosNum = "";

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);

            String curDsOrdPosNum = lineMsg.dsOrdPosnNum_LL.getValue();
            String origLineNum = lineMsg.xxLineNum_LL.getValue();
            String[] lineNumArray = origLineNum.split("\\.");

            int lineNum = Integer.valueOf(lineNumArray[1]).intValue();
            int subLineNum = -1;
            if (lineNumArray.length > 2) {
                subLineNum = Integer.valueOf(lineNumArray[2]).intValue();
            }

            if (!prevDsOrdPosNum.equals(curDsOrdPosNum)) {
                num = 1;
                prevDsOrdPosNum = curDsOrdPosNum;
                prevNum = lineNum;
            } else if (prevNum != lineNum) {
                num++;
                prevNum = lineNum;
            }

            String newLineNum = curDsOrdPosNum + "." + String.valueOf(num);
            if (subLineNum > 0) {
                newLineNum = newLineNum + "." + String.valueOf(subLineNum);
            }

            lineMsg.dsCpoLineNum_LL.setValue(num);

            if (!origLineNum.equals(newLineNum)) {
                lineMsg.xxLineNum_LL.setValue(newLineNum);

                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    if (!CONFIG_CATG.OUTBOUND.equals(glblMsg.I.no(j).configCatgCd_LP.getValue())) {
                        continue;
                    }
                    if (origLineNum.equals(glblMsg.I.no(j).xxLineNum_LP.getValue())) {
                        glblMsg.I.no(j).xxLineNum_LP.setValue(newLineNum);
                    }
                }
            }
        }
    }

    private static void renumDplyLineNum4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int num = 0;
        int prevNum = -1;

        String prevDsOrdPosNum = "";

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);

            String curDsOrdPosNum = lineMsg.dsOrdPosnNum_RL.getValue();
            String origLineNum = lineMsg.xxLineNum_RL.getValue();
            String[] lineNumArray = origLineNum.split("\\.");

            int lineNum = Integer.valueOf(lineNumArray[1]).intValue();
            int subLineNum = -1;
            if (lineNumArray.length > 2) {
                subLineNum = Integer.valueOf(lineNumArray[2]).intValue();
            }

            if (!prevDsOrdPosNum.equals(curDsOrdPosNum)) {
                num = 1;
                prevDsOrdPosNum = curDsOrdPosNum;
                prevNum = lineNum;
            } else if (prevNum != lineNum) {
                num++;
                prevNum = lineNum;
            }

            String newLineNum = curDsOrdPosNum + "." + String.valueOf(num);
            if (subLineNum > 0) {
                newLineNum = newLineNum + "." + String.valueOf(subLineNum);
            }

            lineMsg.dsCpoLineNum_RL.setValue(num);

            if (!origLineNum.equals(newLineNum)) {
                lineMsg.xxLineNum_RL.setValue(newLineNum);

                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    if (!CONFIG_CATG.INBOUND.equals(glblMsg.I.no(j).configCatgCd_LP.getValue())) {
                        continue;
                    }
                    if (origLineNum.equals(glblMsg.I.no(j).xxLineNum_LP.getValue())) {
                        glblMsg.I.no(j).xxLineNum_LP.setValue(newLineNum);
                    }
                }
            }
        }
    }

    private static void resetBaseComponet(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/02/03 S21_NA#19808

        // 2017/02/24 S21_NA#17714 Add Start
        boolean isReset = NWAL1500CommonLogic.setAllNForBaseCompFLg(bizMsg, glblMsg, null); // 2018/01/29 S21_NA#19808 Mod
        if (isReset) {
            return;
        }
        // 2017/02/24 S21_NA#17714 Add End

        Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>(); // 2016/08/04 S21_NA#13012 Add Start
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
            // NWAL1500_ACMsg configMsg = glblMsg.A.no(slctConfIndex); 2016/07/11 S21_NA#7821 Del
            // 2016/04/11 S21_NA#3236-3 Mod Start
//            for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
//                NWAL1500_BCMsg lineMsg = glblMsg.B.no(slctLineIndex);
//                if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_LC.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {
//                    NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, slctLineIndex);
//                }
//            }
            // 2016/08/04 S21_NA#7821-2 Mod Start
//            resetBaseComponet4OneConfig(bizMsg, slctConfIndex);

            // Add Start 2019/03/29 QC#30849
            String configTpCd = glblMsg.A.no(slctConfIndex).configTpCd_LC.getValue();
            if (!NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd)) {
                continue;
            }
            // Add End 2019/03/29 QC#30849

            String dsOrdPosnNum = glblMsg.A.no(slctConfIndex).dsOrdPosnNum_LC.getValue(); // 2018/02/03 S21_NA#19808
            int baseCmpIdx = NWAL1500CommonLogicForSaveSubmit.getInstance().getBaseComponentIndex(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex), baseComponentMap, true); // 2018/02/03 S21_NA#19808
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
                if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue())) {
                    continue;
                }
                if (slctLineIndex == baseCmpIdx) {
                    glblMsg.B.no(baseCmpIdx).baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                    // 2018/06/01 S21_NA#26334 Del Start
                    // glblMsg.B.no(baseCmpIdx).dplyLineRefNum_LL.clear();
                    // 2018/06/01 S21_NA#26334 Del End
                } else {
                    glblMsg.B.no(slctLineIndex).baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                }
            }
            // 2016/08/04 S21_NA#7821-2 Mod End
            // 2016/04/11 S21_NA#3236-3 Mod End
        }
    }

    // 2016/07/11 S21_NA#7821 Add Start
    private static void resetBaseComponet4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.C.getValidCount(); slctConfIndex++) {
            resetBaseComponet4OneConfig4Rma(bizMsg, glblMsg, baseComponentMap, slctConfIndex); // 2018/02/03 S21_NA#19808
        }
    }
    // 2016/07/11 S21_NA#7821 Add End

    // 2016/04/11 S21_NA#3236-3 Add Start
    /**
     * <pre>
     * reset base component flag for one config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param configMsg Line Config Config Message
     * </pre>
     */
    public static void resetBaseComponet4OneConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg) { // 2018/02/03 S21_NA#19808

        // 2016/07/11 S21_NA#7821 Add Start
//        NWAL1500_ACMsg configMsg = glblMsg.A.no(slctConfIndex);
//        for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
//            NWAL1500_BCMsg lineMsg = glblMsg.B.no(slctLineIndex);
//            if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_LC.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {
//                NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, slctLineIndex);
//            }
//        }
        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, configMsg); // 2018/02/03 S21_NA#19808
        // 2016/07/11 S21_NA#7821 Add End
    }
    // 2016/04/11 S21_NA#3236-3 Add End

    // 2016/07/11 S21_NA#7821 Add Start
    /**
     * <pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param baseComponentMap Base Component Map
     * @param slctConfIndex selected config index
     * </pre>
     */
    public static void resetBaseComponet4OneConfig4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, Map<String, String>> baseComponentMap, int slctConfIndex) { // 2018/02/03 S21_NA#19808

        NWAL1500CommonLogic.setBaseComponentFlagForRma(bizMsg, glblMsg, slctConfIndex, baseComponentMap, true); // 2018/02/03 S21_NA#19808
        // 2016/07/11 S21_NA#7821 Add End
    }
    // 2016/07/11 S21_NA#7821 Add End

    private static void resetRefLineNum(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex);
        }
    }
    
    // 2019/01/18 S21_NA#29784 Add Start
    private static List<String> getDplyRefNumList(NWAL1500SMsg glblMsg, List<String> dplyRefNumList) {
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            String dplyLineRefNum = lineMsg.dplyLineRefNum_LL.getValue();
            if (ZYPCommonFunc.hasValue(dplyLineRefNum) && !dplyRefNumList.contains(dplyLineRefNum)) {
                dplyRefNumList.add(dplyLineRefNum);
            }
        }
        return dplyRefNumList;
    }

    private static void resetRefNum4Del(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, String> dplyRefNumMap) {
        for (int parntIndex = 0; parntIndex < glblMsg.B.getValidCount(); parntIndex++) {

            NWAL1500_BSMsg lineMsg = glblMsg.B.no(parntIndex);
            if (!ZYPCommonFunc.hasValue((lineMsg.dplyLineRefNum_LL))) {
                // no reference line
                continue;
            }
            String origRefNum = lineMsg.dplyLineRefNum_LL.getValue();
            String origCpoDtlLineSubLineNum = dplyRefNumMap.get(origRefNum);
            boolean existRef = false;
            // search new reference line
            for (int childIndex = 0; childIndex < glblMsg.B.getValidCount(); childIndex++) {
                NWAL1500_BSMsg childLineMsg = glblMsg.B.no(childIndex);
                String childLineSubLineNum = childLineMsg.cpoDtlLineNum_LL.getValue() + childLineMsg.cpoDtlLineSubNum_LL.getValue();
                if (!S21StringUtil.isEquals(childLineSubLineNum, origCpoDtlLineSubLineNum)) {
                    continue;
                }
                existRef = true;
                String newCpoDtlLineNum = childLineMsg.xxLineNum_LL.getValue();

                if (!S21StringUtil.isEquals(origRefNum, newCpoDtlLineNum)) {
                    lineMsg.dplyLineRefNum_LL.setValue(newCpoDtlLineNum);
                }
            }
            if (!existRef) {
                lineMsg.dplyLineRefNum_LL.clear();
            }
        }
    }
    // 2019/01/18 S21_NA#29784 Add End

    // 2016/07/11 S21_NA#7821 Add Start
    private static void resetRefLineNum4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.C.getValidCount(); slctConfIndex++) {
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex);
        }
    }
    // 2016/07/11 S21_NA#7821 Add End

    private static void resetModel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<String> modConfigList) {
        for (String dsOrdPosnNum : modConfigList) {
            for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
                BigDecimal origMdlid = glblMsg.A.no(slctConfIndex).mdlId_LC.getValue(); // 2016/04/11 S21_NA#3236-3 Add
                if (dsOrdPosnNum.equals(glblMsg.A.no(slctConfIndex).dsOrdPosnNum_LC.getValue())) {
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));

                    // 2016/04/11 S21_NA#3236-3 Add Start
                    BigDecimal newMdlId = glblMsg.A.no(slctConfIndex).mdlId_LC.getValue();
                    Boolean modMdlId = false;
                    if (null != origMdlid && null != newMdlId) {
                        if (origMdlid.compareTo(newMdlId) != 0) {
                            modMdlId = true;
                        }
                    } else if (null != origMdlid && null == newMdlId) {
                        modMdlId = true;
                    } else if (null == origMdlid && null != newMdlId) {
                        modMdlId = true;
                    }
                    if (modMdlId) {
                        DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
                        ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, glblMsg.A.no(slctConfIndex).dsCpoConfigPk_LC);
                        configTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(configTMsg);

                        if (null != configTMsg) {
                            if (null != newMdlId) {
                                ZYPEZDItemValueSetter.setValue(configTMsg.mdlId, newMdlId);
                                ZYPEZDItemValueSetter.setValue(configTMsg.mdlDescTxt, glblMsg.A.no(slctConfIndex).mdlDescTxt_LC);
                            } else {
                                configTMsg.mdlId.clear();
                                configTMsg.mdlDescTxt.clear();
                            }
                            S21FastTBLAccessor.update(configTMsg);
                        }
                    }
                    // 2016/04/11 S21_NA#3236-3 Add End
                }
            }
        }

        // if there no line on a config, clear model id
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            String dsOrdPosnNum = glblMsg.A.no(i).dsOrdPosnNum_LC.getValue();
            int lineCnt = 0;
            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                if (dsOrdPosnNum.equals(glblMsg.B.no(j).dsOrdPosnNum_LL.getValue())) {
                    lineCnt++;
                }
            }
            if (0 == lineCnt) {
                glblMsg.A.no(i).mdlId_LC.clear();
                glblMsg.A.no(i).mdlNm_LC.clear();
                glblMsg.A.no(i).mdlDescTxt_LC.clear();
                // Add 2016/03/07 S21_NA#5000#78 Start
                glblMsg.A.no(i).mdlGrpDescTxt_LC.clear();
                glblMsg.A.no(i).svcSegDescTxt_LC.clear();
                glblMsg.A.no(i).svcIstlReqFlg_LC.clear();
                glblMsg.A.no(i).siteSrvyReqFlg_LC.clear();
                // Add 2016/03/07 S21_NA#5000#78 End

                // 2016/04/11 S21_NA#3236-3 Add Start
                DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
                ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, glblMsg.A.no(i).dsCpoConfigPk_LC);
                configTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(configTMsg);

                if (null != configTMsg) {
                    configTMsg.mdlId.clear();
                    configTMsg.mdlDescTxt.clear();
                    S21FastTBLAccessor.update(configTMsg);
                }
                // 2016/04/11 S21_NA#3236-3 Add End
            }
        }
    }
    // 2017/11/21 S21_NA#22555 Add Start
    private static void resetModel4Rma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<String> modConfigList) {
        for (String dsOrdPosnNum : modConfigList) {
            for (int slctConfIndex = 0; slctConfIndex < glblMsg.C.getValidCount(); slctConfIndex++) {
                BigDecimal origMdlid = glblMsg.C.no(slctConfIndex).mdlId_RC.getValue();
                if (dsOrdPosnNum.equals(glblMsg.C.no(slctConfIndex).dsOrdPosnNum_RC.getValue())) {
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));

                    BigDecimal newMdlId = glblMsg.C.no(slctConfIndex).mdlId_RC.getValue();
                    Boolean modMdlId = false;
                    if (null != origMdlid && null != newMdlId) {
                        if (origMdlid.compareTo(newMdlId) != 0) {
                            modMdlId = true;
                        }
                    } else if (null != origMdlid && null == newMdlId) {
                        modMdlId = true;
                    } else if (null == origMdlid && null != newMdlId) {
                        modMdlId = true;
                    }
                    if (modMdlId) {
                        DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
                        ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, glblMsg.C.no(slctConfIndex).dsCpoConfigPk_RC);
                        configTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(configTMsg);

                        if (null != configTMsg) {
                            if (null != newMdlId) {
                                ZYPEZDItemValueSetter.setValue(configTMsg.mdlId, newMdlId);
                                ZYPEZDItemValueSetter.setValue(configTMsg.mdlDescTxt, glblMsg.C.no(slctConfIndex).mdlDescTxt_RC);
                            } else {
                                configTMsg.mdlId.clear();
                                configTMsg.mdlDescTxt.clear();
                            }
                            S21FastTBLAccessor.update(configTMsg);
                        }
                    }
                }
            }
        }

        // if there no line on a config, clear model id
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            String dsOrdPosnNum = glblMsg.C.no(i).dsOrdPosnNum_RC.getValue();
            int lineCnt = 0;
            for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                if (dsOrdPosnNum.equals(glblMsg.D.no(j).dsOrdPosnNum_RL.getValue())) {
                    lineCnt++;
                }
            }
            if (0 == lineCnt) {
                glblMsg.C.no(i).mdlId_RC.clear();
                glblMsg.C.no(i).mdlNm_RC.clear();
                glblMsg.C.no(i).mdlDescTxt_RC.clear();
                glblMsg.C.no(i).mdlGrpDescTxt_RC.clear();
                glblMsg.C.no(i).svcSegDescTxt_RC.clear();
                glblMsg.C.no(i).svcIstlReqFlg_RC.clear();
                glblMsg.C.no(i).siteSrvyReqFlg_RC.clear();

                DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
                ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, glblMsg.C.no(i).dsCpoConfigPk_RC);
                configTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(configTMsg);

                if (null != configTMsg) {
                    configTMsg.mdlId.clear();
                    configTMsg.mdlDescTxt.clear();
                    S21FastTBLAccessor.update(configTMsg);
                }
            }
        }
    }
    // 2017/11/21 S21_NA#22555 Add End
    private static boolean isCurrentTabLine(NWAL1500CMsg bizMsg) {
        return TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue());
    }

    private static boolean isCurrentTabRMA(NWAL1500CMsg bizMsg) {
        return TAB_RMA.equals(bizMsg.xxDplyTab.getValue());
    }

    // 2016/04/05 S21_NA#5519-2 Add Start
    /**
     * <pre>
     * cancel Alloc for service machine master
     * @param bizMsg Business Message
     * @param delTrgtLine target line object list for deleting
     * @return true: normal end false: error end
     * </pre>
     */
    public static boolean cancelAllocForSvcMachMstr(NWAL1500CMsg bizMsg, NWAL1500_DSMsg delTrgtLine) {

        DS_CPO_RTRN_DTLTMsg rtrnDtl = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rtrnDtl.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtl.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtl.dsCpoRtrnLineNum, delTrgtLine.cpoDtlLineNum_RL);
        ZYPEZDItemValueSetter.setValue(rtrnDtl.dsCpoRtrnLineSubNum, delTrgtLine.cpoDtlLineSubNum_RL);

        rtrnDtl = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(rtrnDtl);
        // 2016/06/03 S21_NA#9275 Mod Start
//      if (!ZYPCommonFunc.hasValue(rtrnDtl.serNum)) {
        if (!ZYPCommonFunc.hasValue(rtrnDtl.serNum) && !ZYPCommonFunc.hasValue(delTrgtLine.svcMachMstrPk_RL)) {
            return true;
        }

//      S21SsmEZDResult rslt = NWAL1500QueryForSaveSubmit.getInstance().getSvcMachMaintFlg(bizMsg.glblCmpyCd.getValue(), rtrnDtl.serNum.getValue(), null); // QC#9277 Mod
        S21SsmEZDResult rslt = new S21SsmEZDResult();
        if (ZYPCommonFunc.hasValue(rtrnDtl.serNum)) {
            rslt = NWAL1500QueryForSaveSubmit.getInstance().getSvcMachMaintFlg(bizMsg.glblCmpyCd.getValue(), rtrnDtl.serNum.getValue(), null); // QC#9277 Mod
        } else {
            rslt = NWAL1500QueryForSaveSubmit.getInstance().getSvcMachMaintFlg(bizMsg.glblCmpyCd.getValue(), null, delTrgtLine.svcMachMstrPk_RL.getValue()); // QC#9275 Add
        }
        // 2016/06/03 S21_NA#9275 Mod End
        if (!rslt.isCodeNormal()) {
            return true;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
        BigDecimal svcMachMstrPk = (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK");

        NSZC001001PMsg rmaCancMachMstrUpdMsg = new NSZC001001PMsg();
        addRmaCancModeMachMstrUpdMsg(bizMsg, rtrnDtl, svcMachMstrPk, rmaCancMachMstrUpdMsg);
        if (!callMachMstrUpdApi(rmaCancMachMstrUpdMsg)) {
            return false;
        }

        NSZC001001PMsg allocOffMachMstrUpdMsg = new NSZC001001PMsg();
        addAllocOffModeMachMstrUpdMsg(bizMsg, rtrnDtl, svcMachMstrPk, allocOffMachMstrUpdMsg);
        if (!callMachMstrUpdApi(allocOffMachMstrUpdMsg)) {
            return false;
        }
        return true;
    }

    /**
     * addRmaCancModeMachMstrUpdMsg
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     * @param machMstrUpdMsg NSZC001001PMsg
     */
    private static void addRmaCancModeMachMstrUpdMsg(NWAL1500CMsg bizMsg, DS_CPO_RTRN_DTLTMsg rtrnDtl, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {

        String latestStatusBeforeW4R = getLatestStatusBeforeWaitingForRemoval(bizMsg.glblCmpyCd.getValue(), svcMachMstrPk);

        machMstrUpdMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        machMstrUpdMsg.slsDt.setValue(bizMsg.slsDt.getValue());
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.RMA_CANCEL.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);

        machMstrUpdMsg.svcMachMstrStsCd.setValue(latestStatusBeforeW4R);

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.stkStsCd, rtrnDtl.stkStsCd);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, bizMsg.cpoOrdNum);
        machMstrUpdMsg.trxLineNum.setValue(rtrnDtl.dsCpoRtrnLineNum.getValue());
        machMstrUpdMsg.trxLineSubNum.setValue(rtrnDtl.dsCpoRtrnLineSubNum.getValue());
        machMstrUpdMsg.svcMachUsgStsCd.setValue(SVC_MACH_USG_STS.AT_CUSTOMER);
    }

    /**
     * addAllocOffModeMachMstrUpdMsg
     * @param pMsg NWZC155001PMsg
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     * @param machMstrUpdMsg NSZC001001PMsg
     */
    private static void addAllocOffModeMachMstrUpdMsg(NWAL1500CMsg bizMsg, DS_CPO_RTRN_DTLTMsg rtrnDtl, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
        machMstrUpdMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        machMstrUpdMsg.slsDt.setValue(bizMsg.slsDt.getValue());
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_OFF.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);
    }

    /**
     * callAddHoldInfoAPI
     * @param machMstrUpdMsg NSZC001001PMsg
     * @param onBatchType NWZC155001ONBATCH_TYPEPMsg
     * @return boolean
     */
    private static boolean callMachMstrUpdApi(NSZC001001PMsg machMstrUpdMsg) {
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, ONBATCH_TYPE.ONLINE);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            isApiResultSuccess = false;
//            for (int i = 0; i < machMstrUpdMsg.xxMsgIdList.getValidCount(); i++) {
//                msgMap.addXxMsgId((String) machMstrUpdMsg.xxMsgIdList.no(i).xxMsgId.getValue());
//                isApiResultSuccess = false;
//            }
        }
        return isApiResultSuccess;
    }

    private static String getLatestStatusBeforeWaitingForRemoval(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        List<SVC_MACH_MSTR_HISTTMsg> svcMachMstrHistList = getSvcMachMstrHist(glblCmpyCd, svcMachMstrPk);
        if (svcMachMstrHistList.size() < 2) {
            return SVC_MACH_MSTR_STS.INSTALLED;
        }

        for (int recCnt = 0; recCnt < svcMachMstrHistList.size(); recCnt++) {
            SVC_MACH_MSTR_HISTTMsg svcMachMstrHistTMsg = svcMachMstrHistList.get(recCnt);
            String svcMachMstrStsCd = svcMachMstrHistTMsg.svcMachMstrStsCd.getValue();
            if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                recCnt++; // For Next Waintig For Remove (Alloc FLog 'Y')
                recCnt++; // For Before Waiting For Removal
                if (recCnt < svcMachMstrHistList.size()) {
                    // Return status before Waiting For Removal.
                    return svcMachMstrHistList.get(recCnt).svcMachMstrStsCd.getValue();
                }
                break;
            }
        }
        return SVC_MACH_MSTR_STS.INSTALLED;
    }

    private static List<SVC_MACH_MSTR_HISTTMsg> getSvcMachMstrHist(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        S21SsmEZDResult ssmRslt = NWAL1500QueryForDelete.getInstance().getSvcMachMstrHist(glblCmpyCd, svcMachMstrPk);
        if (ssmRslt.isCodeNormal()) {
            return (List<SVC_MACH_MSTR_HISTTMsg>) ssmRslt.getResultObject();
        } else {
            return null;
        }
    }
    // 2016/04/05 S21_NA#5519-2 Add End

    // 2016/04/20 S21_NA#5605 Add Start
    private static void chkConfigIfAllLineSelInConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (isCurrentTabLine(bizMsg)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
                String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                int lineCnt = 0;
                int selLineCnt = 0;
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                    if (S21StringUtil.isEquals(dsOrdPosnNum, lineDsOrdPosnNum)) {
                        lineCnt++;
                        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxChkBox_LL.getValue())) {
                            selLineCnt++;
                        }
                    }
                }
                if (lineCnt > 0 && lineCnt == selLineCnt) {
                    configMsg.xxChkBox_LC.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        } else if (isCurrentTabRMA(bizMsg)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
                String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                int lineCnt = 0;
                int selLineCnt = 0;
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(j);
                    String lineDsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                    if (S21StringUtil.isEquals(dsOrdPosnNum, lineDsOrdPosnNum)) {
                        lineCnt++;
                        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.xxChkBox_RL.getValue())) {
                            selLineCnt++;
                        }
                    }
                }
                if (lineCnt > 0 && lineCnt == selLineCnt) {
                    rmaConfigMsg.xxChkBox_RC.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }

    private static List<NWAL1500_ISMsg> getPrcMsg(NWAL1500SMsg glblMsg, String configCatgCd) {

        List<NWAL1500_ISMsg> prcMsgList = new ArrayList<NWAL1500_ISMsg>(0);
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            if (S21StringUtil.isEquals(configCatgCd, glblMsg.I.no(i).configCatgCd_LP.getValue())) {
                NWAL1500_ISMsg prcMsg = new NWAL1500_ISMsg();
                EZDMsg.copy(glblMsg.I.no(i), null, prcMsg, null);
                prcMsgList.add(prcMsg);
            }
        }
        return prcMsgList;
    }
    // 2016/04/20 S21_NA#5605 Add End

    // 2016/07/11 S21_NA#7821 Add Start
    /**
     * Calling DS CPO Update API with Delete Mode
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public static void callDsCpoUpdateAPIForDelete(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (NWAL1500CommonLogic.isModifiedByOtherUser(bizMsg, glblMsg)) {
            bizMsg.setMessageInfo("NWAM0429E");
            return;
        }
        CPOTMsg cpoMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0"));
        cpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cpoMsg);
        if (cpoMsg == null) {
            bizMsg.setMessageInfo(NWAL1500MsgConstant.NZZM0003E);
            return;
        }
        int[] cntArray = {0, 0, 0, 0, 0};

        /////////////////////////////
        // Reset Base Component Flag
        /////////////////////////////
        // Line Config
        // 2016/08/04 S21_NA#13012 Del Start
//        for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
//            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex);
//        }
//        // RMA
//        for (int slctConfIndex = 0; slctConfIndex < glblMsg.C.getValidCount(); slctConfIndex++) {
//            NWAL1500CommonLogic.setBaseComponentFlagForRma(bizMsg, slctConfIndex, true);
//        }
        // 2016/08/04 S21_NA#13012 Del End

        NWAL1500CommonLogicForSaveSubmit.getInstance().callDsCpoUpdateApiForDelete(bizMsg, glblMsg, cntArray);
    }
    // 2016/07/11 S21_NA#7821 Add Start

    private static void printDelData(NWAL1500SMsg glblMsg) {
        if (!PRNT_FLG) {
            return;
        }
        int n = 0;

        System.out.println("#####################################");
        System.out.println("#### Print Data For Delete Start ####");
        System.out.println("#####################################");
        System.out.println("$$$$$$$$ Config $$$$$$$$");
        for (n = 0; n < glblMsg.A.getValidCount(); n++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(n);
            System.out.println("########" + String.valueOf(n) + "########");
            System.out.println("Chk#   : " + configMsg.xxChkBox_LC.getValue());
            System.out.println("Pos#   : " + configMsg.dsOrdPosnNum_LC.getValue());
        }
        System.out.println("$$$$$$$$ Line $$$$$$$$");
        for (n = 0; n < glblMsg.B.getValidCount(); n++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(n);
            System.out.println("########" + String.valueOf(n) + "########");
            System.out.println("Chk#   : " + lineMsg.xxChkBox_LL.getValue());
            System.out.println("Pos#   : " + lineMsg.dsOrdPosnNum_LL.getValue());
            System.out.println("Deply# : " + lineMsg.xxLineNum_LL.getValue());
            System.out.println("Dtl#   : " + lineMsg.cpoDtlLineNum_LL.getValue());
            System.out.println("DtlSub#: " + lineMsg.cpoDtlLineSubNum_LL.getValue());
            System.out.println("DsDtl#   : " + lineMsg.dsCpoLineNum_LL.getValue());
            System.out.println("DsDtlSub#: " + lineMsg.dsCpoLineSubNum_LL.getValue());
            System.out.println("Base Comp Flg: " + lineMsg.baseCmptFlg_LL.getValue());
            System.out.println("Ref Dply Line Num#: " + lineMsg.dplyLineRefNum_LL.getValue());
            System.out.println("Mdse Cd: " + lineMsg.mdseCd_LL.getValue());
        }
        System.out.println("-------- Config --------");
        for (n = 0; n < glblMsg.C.getValidCount(); n++) {
            NWAL1500_CSMsg configMsg = glblMsg.C.no(n);
            System.out.println("########" + String.valueOf(n) + "########");
            System.out.println("Chk#   : " + configMsg.xxChkBox_RC.getValue());
            System.out.println("Pos#   : " + configMsg.dsOrdPosnNum_RC.getValue());
        }
        System.out.println("-------- RMA --------");
        for (n = 0; n < glblMsg.D.getValidCount(); n++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(n);
            System.out.println("########" + String.valueOf(n) + "########");
            System.out.println("Chk#   : " + rmaLineMsg.xxChkBox_RL.getValue());
            System.out.println("Pos#   : " + rmaLineMsg.dsOrdPosnNum_RL.getValue());
            System.out.println("Deply# : " + rmaLineMsg.xxLineNum_RL.getValue());
            System.out.println("Dtl#   : " + rmaLineMsg.cpoDtlLineNum_RL.getValue());
            System.out.println("DtlSub#: " + rmaLineMsg.cpoDtlLineSubNum_RL.getValue());
            System.out.println("DsDtl#   : " + rmaLineMsg.dsCpoLineNum_RL.getValue());
            System.out.println("DsDtlSub#: " + rmaLineMsg.dsCpoLineSubNum_RL.getValue());
            System.out.println("Mdse Cd: " + rmaLineMsg.mdseCd_RL.getValue());
        }
        System.out.println("#####################################");
        System.out.println("#### Print Data For Delete E n d ####");
        System.out.println("#####################################");
    }

    // 2016/11/08 S21_NA#9867 Add Start
    private static NWAL1500_ASMsg getConfigLineForLineConfig(NWAL1500SMsg glblMsg, String configNum) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg aMsg = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(aMsg.dsOrdPosnNum_LC.getValue(), configNum)) {
                return aMsg;
            }
        }
        return null;
    }
    // 2016/11/08 S21_NA#9867 Add End

    // 2018/06/05 S21_NA#26681 Add Start
    // Check Referenced item will be delete
    /**
     * <pre>
     * check reference line is going to be be cancelled or deleted.
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param action MSG_PARAM_LINE_REF_ACT_DEL: "deleted" or MSG_PARAM_LINE_REF_ACT_CANC: "cancelled"
     * @return true: has error, false: no error.
     * </pre>
     */
    public static boolean isReferencedItemDeleting(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String action) {

        Map<NWAL1500_ASMsg, List<String>> selectedConfErrMap = new HashMap<NWAL1500_ASMsg, List<String>>(0);
        Map<NWAL1500_BSMsg, List<String>> selectedLineErrMap = new HashMap<NWAL1500_BSMsg, List<String>>(0);
        Map<NWAL1500_CSMsg, List<String>> selectedRmaConfErrMap = new HashMap<NWAL1500_CSMsg, List<String>>(0);
        Map<NWAL1500_DSMsg, List<String>> selectedRmaLineErrMap = new HashMap<NWAL1500_DSMsg, List<String>>(0);

        boolean errFlg = false;
        int firstErrPage = -1;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                boolean lineErrFlg = false;
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (!ZYPCommonFunc.hasValue(lineMsg.dplyLineRefNum_LL)) {
                    continue;
                }
                if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, lineMsg.xxChkBox_LL.getValue())) {
                    continue;
                }
                // 2018/06/14 S21_NA#25227 Add Start
                if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_LL)) {
                    NWAL1500_BSMsg setParent = getSetParent(glblMsg, lineMsg);
                    // 2019/03/29 S21_NA#30841 Mod Start
                    //if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, setParent.xxChkBox_LL.getValue())) {
                    if (setParent != null && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, setParent.xxChkBox_LL.getValue())) {
                    // 2019/03/29 S21_NA#30841 Mod End
                        continue;
                    }
                }
                // 2018/06/14 S21_NA#25227 Add End
                String[] dplyLineRefArray = lineMsg.dplyLineRefNum_LL.getValue().split("\\.");
                // 2019/04/15 S21_NA#31184 Add Start
                if (dplyLineRefArray.length < 2) {
                    errFlg = true;
                    lineMsg.dplyLineRefNum_LL.setErrorInfo(1, NWZM1476E);
                    for (int slctConfigIdx = 0; slctConfigIdx < glblMsg.A.getValidCount(); slctConfigIdx++) {
                        NWAL1500_ASMsg configMsg = glblMsg.A.no(slctConfigIdx);
                        if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), configMsg.dsOrdPosnNum_LC.getValue())) {
                            if (firstErrPage < 0) {
                                firstErrPage = configMsg.xxPageNum_LC.getValueInt();
                            }
                            break;
                        }
                    }
                    continue;
                }
                // 2019/04/15 S21_NA#31184 Add End
                String refPosnNum = dplyLineRefArray[0];
                if (S21StringUtil.isEquals(refPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    continue;
                }

                boolean confDel = false;
                for (int slctConfigIdx = 0; slctConfigIdx < glblMsg.A.getValidCount(); slctConfigIdx++) {
                    NWAL1500_ASMsg configMsg = glblMsg.A.no(slctConfigIdx);
                    if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_LL.getValue(), configMsg.dsOrdPosnNum_LC.getValue()) //
                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, configMsg.xxChkBox_LC.getValue())) {
                        confDel = true;
                        break;
                    }
                    if (S21StringUtil.isEquals(refPosnNum, configMsg.dsOrdPosnNum_LC.getValue()) //
                        // 2018/06/14 S21_NA#25227 Mod Start
//                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, configMsg.xxChkBox_LC.getValue())) {
                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, configMsg.xxChkBox_LC.getValue())
                            && !S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, NWAL1500CommonLogic.getParentConfig(glblMsg.A, lineMsg.dsOrdPosnNum_LL.getValue()).xxChkBox_LC.getValue())) {
                        // 2018/06/14 S21_NA#25227 Mod End
                        // Error Set
                        lineErrFlg = true;
                        List<String> errRefNumList = selectedConfErrMap.get(configMsg);
                        if (errRefNumList == null) {
                            errRefNumList = new ArrayList<String>(0);
                        }
                        errRefNumList.add(lineMsg.xxLineNum_LL.getValue());
                        selectedConfErrMap.put(configMsg, errRefNumList);
                        if (firstErrPage < 0) {
                            firstErrPage = configMsg.xxPageNum_LC.getValueInt();
                        }
                        break;
                    }
                }
                if (confDel) {
                    continue;
                }
                if (lineErrFlg) {
                    lineMsg.dplyLineRefNum_LL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
                }

                if (!lineErrFlg) {
                    for (int slctLineIdx = 0; slctLineIdx < glblMsg.B.getValidCount(); slctLineIdx++) {
                        if (i == slctLineIdx) {
                            continue;
                        }
                        // 2018/06/14 S21_NA#25227 Mod Start
//                        if (S21StringUtil.isEquals(lineMsg.dplyLineRefNum_LL.getValue(), glblMsg.B.no(slctLineIdx).xxLineNum_LL.getValue()) //
//                                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.B.no(slctLineIdx).xxChkBox_LL.getValue())) {
//                            // Error Set
//                            lineErrFlg = true;
//                            lineMsg.dplyLineRefNum_LL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
//                            List<String> errRefNumList = selectedLineErrMap.get(glblMsg.B.no(slctLineIdx));
//                            if (errRefNumList == null) {
//                                errRefNumList = new ArrayList<String>(0);
//                            }
//                            errRefNumList.add(lineMsg.xxLineNum_LL.getValue());
//                            selectedLineErrMap.put(glblMsg.B.no(slctLineIdx), errRefNumList);
//                            if (firstErrPage < 0) {
//                                firstErrPage = glblMsg.B.no(slctLineIdx).xxPageNum_LL.getValueInt();
//                            }
//                            break;
//                        }
                        if (S21StringUtil.isEquals(lineMsg.dplyLineRefNum_LL.getValue(), glblMsg.B.no(slctLineIdx).xxLineNum_LL.getValue())) {
                            NWAL1500_BSMsg chkTrgt;
                            if (ZYPCommonFunc.hasValue(glblMsg.B.no(slctLineIdx).dsCpoLineSubNum_LL)) {
                                chkTrgt = getSetParent(glblMsg, glblMsg.B.no(slctLineIdx));
                            } else {
                                chkTrgt = glblMsg.B.no(slctLineIdx);
                            }

                            // 2019/03/29 S21_NA#30841 Mod Start
                            //if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, chkTrgt.xxChkBox_LL.getValue())) {
                            if (chkTrgt != null && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, chkTrgt.xxChkBox_LL.getValue())) {
                            // 2019/03/29 S21_NA#30841 Mod End
                                // Error Set
                                lineErrFlg = true;
                                lineMsg.dplyLineRefNum_LL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
                                List<String> errRefNumList = selectedLineErrMap.get(chkTrgt);
                                if (errRefNumList == null) {
                                    errRefNumList = new ArrayList<String>(0);
                                }
                                errRefNumList.add(lineMsg.xxLineNum_LL.getValue());
                                selectedLineErrMap.put(chkTrgt, errRefNumList);
                                if (firstErrPage < 0) {
                                    firstErrPage = chkTrgt.xxPageNum_LL.getValueInt();
                                }
                                break;
                            }
                        }
                        // 2018/06/14 S21_NA#25227 Mod End
                    }
                }
                if (lineErrFlg) {
                    errFlg = true;
                }
            }
            for (NWAL1500_ASMsg configMsg : selectedConfErrMap.keySet()) {
                String errParam = getErrMsgParam(selectedConfErrMap.get(configMsg));
                configMsg.xxChkBox_LC.setErrorInfo(1, NWAM0957E, new String[] {errParam});
            }
            for (NWAL1500_BSMsg lineMsg : selectedLineErrMap.keySet()) {
                String errParam = getErrMsgParam(selectedLineErrMap.get(lineMsg));
                lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0957E, new String[] {errParam});
            }
            if (firstErrPage != -1) {
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPage);
            }
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                boolean lineErrFlg = false;
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (!ZYPCommonFunc.hasValue(rmaLineMsg.dplyLineRefNum_RL)) {
                    continue;
                }
                if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rmaLineMsg.xxChkBox_RL.getValue())) {
                    continue;
                }
                // 2018/06/14 S21_NA#25227 Add Start
                if (ZYPCommonFunc.hasValue(rmaLineMsg.dsCpoLineSubNum_RL)) {
                    NWAL1500_DSMsg setParent = getSetParent(glblMsg, rmaLineMsg);
                    // 2019/03/29 S21_NA#30841 Mod Start
                    //if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, setParent.xxChkBox_RL.getValue())) {
                    if (setParent != null && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, setParent.xxChkBox_RL.getValue())) {
                    // 2019/03/29 S21_NA#30841 Mod End
                        continue;
                    }
                }
                // 2018/06/14 S21_NA#25227 Add End
                String[] dplyLineRefArray = rmaLineMsg.dplyLineRefNum_RL.getValue().split("\\.");
                // 2019/04/15 S21_NA#31184 Add Start
                if (dplyLineRefArray.length < 2) {
                    errFlg = true;
                    rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, NWZM1476E);
                    for (int slctConfigIdx = 0; slctConfigIdx < glblMsg.C.getValidCount(); slctConfigIdx++) {
                        NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(slctConfigIdx);
                        if (S21StringUtil.isEquals(rmaLineMsg.dsOrdPosnNum_RL.getValue(), rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                            if (firstErrPage < 0) {
                                firstErrPage = rmaConfigMsg.xxPageNum_RC.getValueInt();
                            }
                            break;
                        }
                    }
                    continue;
                }
                // 2019/04/15 S21_NA#31184 Add End
                String refPosnNum = dplyLineRefArray[0];
                if (S21StringUtil.isEquals(refPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    continue;
                }

                boolean confDel = false;
                for (int slctConfigIdx = 0; slctConfigIdx < glblMsg.C.getValidCount(); slctConfigIdx++) {
                    NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(slctConfigIdx);
                    // 2018/06/14 S21_NA#25227 Mod Start
//                    if (S21StringUtil.isEquals(rmaLineMsg.dsOrdPosnNum_RL.getValue(), rmaConfigMsg.dsOrdPosnNum_RC.getValue()) //
//                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rmaConfigMsg.xxChkBox_RC.getValue())) {
                    if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rmaConfigMsg.xxChkBox_RC.getValue())) {
                    // 2018/06/14 S21_NA#25227 Mod End
                        confDel = true;
                        break;
                    }
                    if (S21StringUtil.isEquals(refPosnNum, rmaConfigMsg.dsOrdPosnNum_RC.getValue()) //
                        // 2018/06/14 S21_NA#25227 Mod Start
//                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rmaConfigMsg.xxChkBox_RC.getValue())) {
                            && S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rmaConfigMsg.xxChkBox_RC.getValue())
                            && !S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, NWAL1500CommonLogic.getParentConfig(glblMsg.C, rmaLineMsg.dsOrdPosnNum_RL.getValue()).xxChkBox_RC.getValue())) {
                        // 2018/06/14 S21_NA#25227 Mod End
                        // Error Set
                        lineErrFlg = true;
                        List<String> errRefNumList = selectedRmaConfErrMap.get(rmaConfigMsg);
                        if (errRefNumList == null) {
                            errRefNumList = new ArrayList<String>(0);
                        }
                        errRefNumList.add(rmaLineMsg.xxLineNum_RL.getValue());
                        selectedRmaConfErrMap.put(rmaConfigMsg, errRefNumList);
                        if (firstErrPage < 0) {
                            firstErrPage = rmaConfigMsg.xxPageNum_RC.getValueInt();
                        }
                        break;
                    }
                }
                if (confDel) {
                    continue;
                }
                if (lineErrFlg) {
                    rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
                }

                if (!lineErrFlg) {
                    for (int slctLineIdx = 0; slctLineIdx < glblMsg.D.getValidCount(); slctLineIdx++) {
                        if (i == slctLineIdx) {
                            continue;
                        }
                        // 2018/06/14 S21_NA#25227 Mod Start
//                        if (S21StringUtil.isEquals(rmaLineMsg.dplyLineRefNum_RL.getValue(), glblMsg.D.no(slctLineIdx).xxLineNum_RL.getValue()) //
//                                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(slctLineIdx).xxChkBox_RL.getValue())) {
//                            // Error Set
//                            lineErrFlg = true;
//                            rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
//                            List<String> errRefNumList = selectedRmaLineErrMap.get(glblMsg.D.no(slctLineIdx));
//                            if (errRefNumList == null) {
//                                errRefNumList = new ArrayList<String>(0);
//                            }
//                            errRefNumList.add(rmaLineMsg.xxLineNum_RL.getValue());
//                            selectedRmaLineErrMap.put(glblMsg.D.no(slctLineIdx), errRefNumList);
//                            if (firstErrPage < 0) {
//                                firstErrPage = glblMsg.D.no(slctLineIdx).xxPageNum_RL.getValueInt();
//                            }
//                            break;
//                        }
                        if (S21StringUtil.isEquals(rmaLineMsg.dplyLineRefNum_RL.getValue(), glblMsg.D.no(slctLineIdx).xxLineNum_RL.getValue())) {
                            NWAL1500_DSMsg chkTrgt;
                            if (ZYPCommonFunc.hasValue(glblMsg.D.no(slctLineIdx).dsCpoLineSubNum_RL)
                                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(slctLineIdx).xxChkBox_RL.getValue())) {
                                chkTrgt = getSetParent(glblMsg, glblMsg.D.no(slctLineIdx));
                            } else {
                                chkTrgt = glblMsg.D.no(slctLineIdx);
                            }

                            // 2019/03/29 S21_NA#30841 Mod Start
                            //if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, chkTrgt.xxChkBox_RL.getValue())) {
                            if (chkTrgt != null && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, chkTrgt.xxChkBox_RL.getValue())) {
                            // 2019/03/29 S21_NA#30841 Mod End
                                // Error Set
                                lineErrFlg = true;
                                rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, NWAM0956E, new String[] {"action"});
                                List<String> errRefNumList = selectedRmaLineErrMap.get(glblMsg.D.no(slctLineIdx));
                                if (errRefNumList == null) {
                                    errRefNumList = new ArrayList<String>(0);
                                }
                                errRefNumList.add(rmaLineMsg.xxLineNum_RL.getValue());
                                selectedRmaLineErrMap.put(glblMsg.D.no(slctLineIdx), errRefNumList);
                                if (firstErrPage < 0) {
                                    firstErrPage = glblMsg.D.no(slctLineIdx).xxPageNum_RL.getValueInt();
                                }
                                break;
                            }
                        }
                        // 2018/06/14 S21_NA#25227 Mod End
                    }
                }
                if (lineErrFlg) {
                    errFlg = true;
                }
            }
            for (NWAL1500_CSMsg rmaConfigMsg : selectedRmaConfErrMap.keySet()) {
                String errParam = getErrMsgParam(selectedRmaConfErrMap.get(rmaConfigMsg));
                rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, NWAM0957E, new String[] {errParam});
            }
            for (NWAL1500_DSMsg rmaLineMsg : selectedRmaLineErrMap.keySet()) {
                String errParam = getErrMsgParam(selectedRmaLineErrMap.get(rmaLineMsg));
                rmaLineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0957E, new String[] {errParam});
            }
            if (firstErrPage != -1) {
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPage);
            }
        }

        return errFlg;
    }

    private static String getErrMsgParam(List<String> paramList) {

        StringBuffer errParam = new StringBuffer();
        int msgCnt = 0;
        for (String param : paramList) {
            if (msgCnt >= MAX_ERR_REF_NUM_CNT) {
                return errParam.toString().substring(0, errParam.length() - 2) + " ...";
            }
            errParam = errParam.append(param + ", ");
            msgCnt++;
        }
        return errParam.toString().substring(0, errParam.length() - 2);
    }
    // 2018/06/05 S21_NA#26681 Add End
    // 2018/06/14 S21_NA#25227 Add Start
    private static NWAL1500_BSMsg getSetParent(NWAL1500SMsg glblMsg, NWAL1500_BSMsg setCmpsn) {

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg msg = glblMsg.B.no(i);

            if (S21StringUtil.isEquals(msg.dsOrdPosnNum_LL.getValue(), setCmpsn.dsOrdPosnNum_LL.getValue())
                    // 2019/03/29 S21_NA#30841 Mod Start
                    //&& S21StringUtil.isEquals(msg.cpoDtlLineNum_LL.getValue(), setCmpsn.cpoDtlLineNum_LL.getValue())
                    //&& S21StringUtil.isEquals(msg.cpoDtlLineSubNum_LL.getValue(), CPO_DTL_LINE_SUB_NUM_SET_PRNT)) {
                    && NWAL1500CommonLogic.compareBigDecimal(msg.dsCpoLineNum_LL.getValue(), setCmpsn.dsCpoLineNum_LL.getValue()) == 0
                    && !ZYPCommonFunc.hasValue(msg.dsCpoLineSubNum_LL)) {
                    // 2019/03/29 S21_NA#30841 Mod End
                return msg;
            }
        }

        return null;
    }

    private static NWAL1500_DSMsg getSetParent(NWAL1500SMsg glblMsg, NWAL1500_DSMsg setCmpsn) {

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg msg = glblMsg.D.no(i);

            if (S21StringUtil.isEquals(msg.dsOrdPosnNum_RL.getValue(), setCmpsn.dsOrdPosnNum_RL.getValue())
                    // 2019/03/29 S21_NA#30841 Mod Start
                    //&& S21StringUtil.isEquals(msg.cpoDtlLineNum_RL.getValue(), setCmpsn.cpoDtlLineNum_RL.getValue())
                    //&& S21StringUtil.isEquals(msg.cpoDtlLineSubNum_RL.getValue(), CPO_DTL_LINE_SUB_NUM_SET_PRNT)) {
                    && NWAL1500CommonLogic.compareBigDecimal(msg.dsCpoLineNum_RL.getValue(), setCmpsn.dsCpoLineNum_RL.getValue()) == 0
                    && !ZYPCommonFunc.hasValue(msg.dsCpoLineSubNum_RL)) {
                    // 2019/03/29 S21_NA#30841 Mod End
                return msg;
            }
        }

        return null;
    }
    // 2018/06/14 S21_NA#25227 Add End
    
    // 2018/12/21 S21_NA#28654 Add Start
    private static void resetDplyLineRefNum(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("001");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", glblMsg.cpoOrdNum.getValue());
        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlTMsg);
        if (cpoDtlTMsgArray == null || cpoDtlTMsgArray.getValidCount() == 0) {
            return;
        }
        String posnNum = null;
        String lineNum = null;
        String lineSubMun = null;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg msMsg = glblMsg.B.no(i);
            posnNum = msMsg.dsOrdPosnNum_LL.getValue();
            lineNum = msMsg.cpoDtlLineNum_LL.getValue();
            lineSubMun = msMsg.cpoDtlLineSubNum_LL.getValue();

            for (int k = 0; k < cpoDtlTMsgArray.getValidCount(); k++) {
                CPO_DTLTMsg cpoDtlTmsg = cpoDtlTMsgArray.no(k);
                if (S21StringUtil.isEquals(posnNum, cpoDtlTmsg.dsOrdPosnNum.getValue()) && //
                        S21StringUtil.isEquals(lineNum, cpoDtlTmsg.cpoDtlLineNum.getValue()) && //
                        S21StringUtil.isEquals(lineSubMun, cpoDtlTmsg.cpoDtlLineSubNum.getValue())) {
                    ZYPEZDItemValueSetter.setValue(msMsg.dplyLineRefNum_LL, cpoDtlTmsg.dplyLineRefNum);
                    break;
                }
            }
        }
    }
    // 2018/12/21 S21_NA#28654 Add End
}
