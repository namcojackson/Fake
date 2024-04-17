/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE_CONFIG_TAB;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RMA_TAB;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONF_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0680E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0681E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0682E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0690E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0950E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0951E;


import java.util.List;
import java.util.TreeSet;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         T.Yoshida       Create         n/a
 * 2016/01/08   Fujitsu         T.Ishii         Update         S21_NA#2444
 * 2016/02/09   Fujitsu         M.Hara          Update          QC#2478
 * 2016/10/21   Fujitsu         S.Takami        Update          S21_NA#15472
 * 2017/10/19   Fujitsu         S.Takami        Update          S21_NA#21122
 * 2018/03/07   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/07/23   Fujitsu         Mz.Takahashi    Update          S21_NA#26188
 * 2019/03/11   Fujitsu         Mz.Takahashi    Update          QC#30701
 * </pre>
 */
public class NWAL1500CommonLogicForMultiWin {

    /**
     * Check Duplicate For Hold
     * @param bizMsg NWAL1500CMsg
     * @param checkedLineConfList Checked LineConf List
     * @param checkedLineList Checked Line List
     * @param checkedRmaLineConfList Checked RMA LineConf List
     * @param checkedRmaLineList Checked RMA Line List
     * @return check OK : true
     */
    public static boolean checkDuplicateForHold(NWAL1500CMsg bizMsg, List<Integer> checkedLineConfList, List<Integer> checkedLineList, List<Integer> checkedRmaLineConfList, List<Integer> checkedRmaLineList) {

        if ((checkedLineConfList.size() > 0 && checkedRmaLineConfList.size() > 0) || (checkedLineConfList.size() > 0 && checkedRmaLineList.size() > 0)) {
            bizMsg.setMessageInfo(NWAM0690E, new String[] {MSG_PARAM_LINE_CONFIG_TAB, MSG_PARAM_RMA_TAB });
            return false;
        } else if ((checkedLineList.size() > 0 && checkedRmaLineConfList.size() > 0) || (checkedLineList.size() > 0 && checkedRmaLineList.size() > 0)) {
            bizMsg.setMessageInfo(NWAM0690E, new String[] {MSG_PARAM_LINE_CONFIG_TAB, MSG_PARAM_RMA_TAB });
            return false;
        }

        return true;
    }

    /**
     * Check Unregistered Config Line For Hold
     * @param bizMsg NWAL1500CMsg
     * @param checkedConfLineList Checked LineConf List
     * @param checkedRmaLineConfList Checked RMA LineConf List
     * @return check OK : true
     */
    public static boolean checkUnregisteredConfLineForHold(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedConfLineList, List<Integer> checkedRmaLineConfList) { // 2018/03/07 S21_NA#19808 Mod

        // 2018/03/07 S21_NA#19808 Mod bizMsg => glblMsg
        if (checkedConfLineList.size() > 0) {
            for (int checkedLine : checkedConfLineList) {
                NWAL1500_ASMsg aSMsg = glblMsg.A.no(checkedLine);
                String dsOrdPosnNum = aSMsg.dsOrdPosnNum_LC.getValue();

                if (!ZYPCommonFunc.hasValue(aSMsg.dsCpoConfigPk_LC)) {
                    aSMsg.xxChkBox_LC.setErrorInfo(1, NWAM0680E);
                    bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                    // 2018/03/07 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, aSMsg.xxPageNum_LC.getValueInt());
                    // 2018/03/07 S21_NA#19808 Add End
                    return false;
                }

                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg bSMsg = glblMsg.B.no(i);
                    String lineDsOrdPosnNum = bSMsg.dsOrdPosnNum_LL.getValue();

                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        if (!ZYPCommonFunc.hasValue(bSMsg.ordLineStsDescTxt_LL)) {
                            bSMsg.xxChkBox_LL.setErrorInfo(1, NWAM0680E);
                            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                            // 2018/03/07 S21_NA#19808 Add Start
                            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bSMsg.xxPageNum_LL.getValueInt());
                            // 2018/03/07 S21_NA#19808 Add End
                            return false;
                        }
                    }
                }
            }
        } else if (checkedRmaLineConfList.size() > 0) {
            for (int checkedRmaLine : checkedRmaLineConfList) {
                NWAL1500_CSMsg cSMsg = glblMsg.C.no(checkedRmaLine);
                String dsOrdPosnNum = cSMsg.dsOrdPosnNum_RC.getValue();

                if (!ZYPCommonFunc.hasValue(cSMsg.dsCpoConfigPk_RC)) {
                    cSMsg.xxChkBox_RC.setErrorInfo(1, NWAM0680E);
                    bizMsg.xxDplyTab.setValue(TAB_RMA);
                    // 2018/03/07 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, cSMsg.xxPageNum_RC.getValueInt());
                    // 2018/03/07 S21_NA#19808 Add End
                    return false;
                }

                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg dSMsg = glblMsg.D.no(i);
                    String lineDsOrdPosnNum = dSMsg.dsOrdPosnNum_RL.getValue();

                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        if (!ZYPCommonFunc.hasValue(dSMsg.rtrnLineStsDescTxt_RL)) {
                            dSMsg.xxChkBox_RL.setErrorInfo(1, NWAM0680E);
                            bizMsg.xxDplyTab.setValue(TAB_RMA);
                            // 2018/03/07 S21_NA#19808 Add Start
                            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, dSMsg.xxPageNum_RL.getValueInt());
                            // 2018/03/07 S21_NA#19808 Add End
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Check Unregistered Line For Hold
     * @param bizMsg NWAL1500CMsg
     * @param checkedLineList Checked Line List
     * @param checkedRmaLineList Checked RMA Line List
     * @return check OK : true
     */
    public static boolean checkUnregisteredLineForHold(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedLineList, List<Integer> checkedRmaLineList) { // 2018/03/07 S21_NA#19808 Mod

        // 2018/03/07 S21_NA#19808 Mod bizMsg => glblMsg
        if (checkedLineList.size() > 0) {
            for (int checkedLine : checkedLineList) {
                NWAL1500_BSMsg bSMsg = glblMsg.B.no(checkedLine);

                if (!ZYPCommonFunc.hasValue(bSMsg.ordLineStsDescTxt_LL)) {
                    bSMsg.xxChkBox_LL.setErrorInfo(1, NWAM0680E);
                    bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                    // 2018/03/07 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bSMsg.xxPageNum_LL.getValueInt());
                    // 2018/03/07 S21_NA#19808 Add End
                    return false;
                }
            }
        } else if (checkedRmaLineList.size() > 0) {
            for (int checkedRmaLine : checkedRmaLineList) {
                NWAL1500_DSMsg dSMsg = glblMsg.D.no(checkedRmaLine);

                if (!ZYPCommonFunc.hasValue(dSMsg.rtrnLineStsDescTxt_RL)) {
                    dSMsg.xxChkBox_RL.setErrorInfo(1, NWAM0680E);
                    bizMsg.xxDplyTab.setValue(TAB_RMA);
                    // 2018/03/07 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, dSMsg.xxPageNum_RL.getValueInt());
                    // 2018/03/07 S21_NA#19808 Add End
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Set OutParameter For Hold
     * @param bizMsg NWAL1500CMsg
     * @param checkedLineConfList Checked LineConf List
     * @param checkedLineList Checked Line List
     * @param checkedRmaLineConfList Checked RMA LineConf List
     * @param checkedRmaLineList Checked RMA Line List
     */
    public static boolean setOutParamForHold(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedLineConfList, List<Integer> checkedLineList, List<Integer> checkedRmaLineConfList, List<Integer> checkedRmaLineList) { // 2018/03/07 S21_NA#19808 Mod

        // 2019/03/11 QC#30701 Add Start
        bizMsg.condSqlTxt_AW.clear();
        // 2019/03/11 QC#30701 Add End

        // 2018/03/07 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        TreeSet<String> lineNumList = new TreeSet<String>();

        // 2017/08/21 S21_NA#19917 Add Start
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        boolean isCurTabLine = S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab);
        boolean isCurTabRma = S21StringUtil.isEquals(TAB_RMA, xxDplyTab);
        // 2017/08/21 S21_NA#19917 Add End
        // 2018/03/07 S21_NA#19808 Add Start
        int lineLimit = bizMsg.E.length();
        int firstDtlIdx = -1;
        // 2018/03/07 S21_NA#19808 Add End

        if ((checkedLineConfList.size() > 0 || checkedLineList.size() > 0) && isCurTabLine) { // 2017/08/21 S21_NA#19917 Add Condition isCurTabLine
            for (int checkedLineConf : checkedLineConfList) {
                NWAL1500_ASMsg aSMsg = glblMsg.A.no(checkedLineConf);
                String dsOrdPosnNum = aSMsg.dsOrdPosnNum_LC.getValue();

                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg bSMsg = glblMsg.B.no(i);
                    String lineDsOrdPosnNum = bSMsg.dsOrdPosnNum_LL.getValue();

                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum) && !ZYPCommonFunc.hasValue(bSMsg.dsCpoLineSubNum_LL)) { // S21_NA#2444
                        lineNumList.add(bSMsg.xxLineNum_LL.getValue());
                        if (firstDtlIdx < 0) {
                            firstDtlIdx = i;
                        }
                    }
                }
            }
            for (int checkedLine : checkedLineList) {
                NWAL1500_BSMsg bSMsg = glblMsg.B.no(checkedLine);
                lineNumList.add(bSMsg.xxLineNum_LL.getValue());
                // 2018/03/07 S21_NA#19808 Add Start
                if (firstDtlIdx < 0) {
                    firstDtlIdx = checkedLine;
                }
                // 2018/03/07 S21_NA#19808 Add End
            }
            // 2018/03/07 S21_NA#19808 Add Start
            if (lineNumList.size() > lineLimit) {
                int errPageNum = 0;
                glblMsg.B.no(firstDtlIdx).xxChkBox_LL.setErrorInfo(1, NWAM0950E, new String[]{String.valueOf(lineLimit)});
                errPageNum = glblMsg.B.no(firstDtlIdx).xxPageNum_LL.getValueInt();
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errPageNum);
                return false;
            }
            // 2018/03/07 S21_NA#19808 Add End
            ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_AW, CONFIG_CATG.OUTBOUND);

        } else if ((checkedRmaLineConfList.size() > 0 || checkedRmaLineList.size() > 0) && isCurTabRma) { // 2017/08/21 S21_NA#19917 Add Condition isCurTabRma
            for (int checkedRmaLineConf : checkedRmaLineConfList) {
                NWAL1500_CSMsg cSMsg = glblMsg.C.no(checkedRmaLineConf);
                String dsOrdPosnNum = cSMsg.dsOrdPosnNum_RC.getValue();

                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg dSMsg = glblMsg.D.no(i);
                    String lineDsOrdPosnNum = dSMsg.dsOrdPosnNum_RL.getValue();

                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum) && !ZYPCommonFunc.hasValue(dSMsg.dsCpoLineSubNum_RL)) { // S21_NA#2444
                        lineNumList.add(dSMsg.xxLineNum_RL.getValue());
                        if (firstDtlIdx < 0) {
                            firstDtlIdx = i;
                        }
                    }
                }
            }
            for (int checkedRmaLine : checkedRmaLineList) {
                NWAL1500_DSMsg dSMsg = glblMsg.D.no(checkedRmaLine);
                lineNumList.add(dSMsg.xxLineNum_RL.getValue());
                // 2018/03/07 S21_NA#19808 Add Start
                if (firstDtlIdx < 0) {
                    firstDtlIdx = checkedRmaLine;
                }
                // 2018/03/07 S21_NA#19808 Add End
            }
            // 2018/03/07 S21_NA#19808 Add Start
            if (lineNumList.size() > lineLimit) {
                int errPageNum = 0;
                glblMsg.D.no(firstDtlIdx).xxChkBox_RL.setErrorInfo(1, NWAM0950E, new String[]{String.valueOf(lineLimit)});
                errPageNum = glblMsg.D.no(firstDtlIdx).xxPageNum_RL.getValueInt();
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errPageNum);
                return false;
            }
            // 2018/03/07 S21_NA#19808 Add End
            ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_AW, CONFIG_CATG.INBOUND);
        }

        // 2018/03/07 S21_NA#19808 Del Start
//        int validCnt = 0;
//        for (String lineNum : lineNumList) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCnt++).xxLineNum_AW, lineNum);
//        }
//        bizMsg.E.setValidCount(validCnt);
        // 2018/03/07 S21_NA#19808 Del End

        // 2018/03/07 S21_NA#19808 Add Start
        if (lineNumList.size() == 0) {
            return true;
        }
        int length = 0;
        int limit = bizMsg.getAttr("condSqlTxt_AW").getDigit();
        StringBuffer paramSb = new StringBuffer("");
        int idx = 0;
        for (String lineNum : lineNumList) {
            idx++;
            if (idx > lineNumList.size() - 1) {
                break;
            }
            paramSb = paramSb.append(lineNum + ",");
            length = paramSb.length();
            if (length > limit) {
                break;
            }
        }
        if (length < limit) {
            paramSb = paramSb.append(lineNumList.last());
        }
        length = paramSb.length();
        if (length > limit) {
            if (isCurTabLine) {
                glblMsg.B.no(firstDtlIdx).xxChkBox_LL.setErrorInfo(1, NWAM0951E);
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, glblMsg.B.no(firstDtlIdx).xxPageNum_LL.getValueInt());
                return false;
            } else {
                glblMsg.D.no(firstDtlIdx).xxChkBox_RL.setErrorInfo(1, NWAM0951E);
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, glblMsg.D.no(firstDtlIdx).xxPageNum_RL.getValueInt());
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.condSqlTxt_AW, paramSb.toString());
        // 2018/03/07 S21_NA#19808 Add End
        return true;
    }

    // 2018/07/23 S21_NA#26188 Add Start
    /**
     * checkSelectedConfigForDelyInfo
     * @param bizMsg
     * @param glblMsg
     * @param checkedConfigList
     * @param checkedRmaConfigList
     * @return
     */
    public static boolean checkSelectedConfigForDelyInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedConfigList, List<Integer> checkedRmaConfigList) {

        int firstErrPageNum = 0;
        if (checkedConfigList != null) {
            if (checkedConfigList.size() <= 0){
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    glblMsg.A.no(i).xxChkBox_LC.setErrorInfo(1, NWAM0667E, new String[]{MSG_PARAM_CONF_LINE});

                    if (firstErrPageNum < 1) {
                        firstErrPageNum = glblMsg.A.no(i).xxPageNum_LC.getValueInt();
                    }
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPageNum);
                }
                bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                return false;
            } else if (checkedConfigList.size() >= 2) {
                if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(bizMsg.ordEntryActCd.getValue())){
                    if (checkedConfigList.size() >= 2){
                        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                            glblMsg.A.no(i).xxChkBox_LC.setErrorInfo(1, NWAM0681E, new String[]{MSG_PARAM_CONF_LINE});

                            if (firstErrPageNum < 1) {
                                firstErrPageNum = glblMsg.A.no(i).xxPageNum_LC.getValueInt();
                            }
                            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPageNum);
                        }
                        bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                        return false;
                    }
                }
            }
        } else if (checkedRmaConfigList != null) {
            if (checkedRmaConfigList.size() <= 0){
                for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                    glblMsg.C.no(i).xxChkBox_RC.setErrorInfo(1, NWAM0667E, new String[]{MSG_PARAM_CONF_LINE});

                    if (firstErrPageNum < 1) {
                        firstErrPageNum = glblMsg.C.no(i).xxPageNum_RC.getValueInt();
                    }
                }

                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPageNum);
                bizMsg.xxDplyTab.setValue(TAB_RMA);
                return false;
            } else if (checkedRmaConfigList.size() >= 2){
                if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(bizMsg.ordEntryActCd.getValue())){
                    for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                        glblMsg.C.no(i).xxChkBox_RC.setErrorInfo(1, NWAM0681E, new String[]{MSG_PARAM_CONF_LINE});

                        if (firstErrPageNum < 1) {
                            firstErrPageNum = glblMsg.C.no(i).xxPageNum_RC.getValueInt();
                        }
                    }

                    NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPageNum);
                    bizMsg.xxDplyTab.setValue(TAB_RMA);
                    return false;
                }
            }
        }

        return true;
    }
    // 2018/07/23 S21_NA#26188 Add End

    /**
     * Check selected Line For DeliveryInfo
     * @param bizMsg NWAL1500CMsg
     * @param checkedLineList Checked Line List
     * @param checkedRmaLineList Checked RMA Line List
     * @return check OK : true
     */
    public static boolean checkSelectedLineForDelyInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedLineList, List<Integer> checkedRmaLineList) { // 2018/03/07 S21_NA#19808 Mod

        // QC#2478
        // 2018/03/07 S21_NA#19808 Mod bizMsg.B, D => glblMsg.B, D
        int firstErrPageNum = 0;
        if (checkedLineList != null && checkedLineList.size() > 0) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                glblMsg.B.no(i).xxChkBox_LL.setErrorInfo(1, NWAM0682E);
                // 2018/03/07 S21_NA#19808 Add Start
                if (firstErrPageNum < 1) {
                    firstErrPageNum = glblMsg.B.no(i).xxPageNum_LL.getValueInt();
                }
                // 2018/03/07 S21_NA#19808 Add End
                // 2018/03/07 S21_NA#19808 Add Start
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPageNum);
                // 2018/03/07 S21_NA#19808 Add End
            }
            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
            
            return false;
        } else if (checkedRmaLineList != null && checkedRmaLineList.size() > 0) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                glblMsg.D.no(i).xxChkBox_RL.setErrorInfo(1, NWAM0682E);
                // 2018/03/07 S21_NA#19808 Add Start
                if (firstErrPageNum < 1) {
                    firstErrPageNum = glblMsg.D.no(i).xxPageNum_RL.getValueInt();
                }
                // 2018/03/07 S21_NA#19808 Add End
            }
            bizMsg.xxDplyTab.setValue(TAB_RMA);
            // 2018/03/07 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPageNum);
            // 2018/03/07 S21_NA#19808 Add End
            return false;
        }

        return true;
    }

    /**
     * Set OutParameter For DeliveryInfo
     * @param bizMsg NWAL1500CMsg
     * @param checkedLineConfList Checked LineConf List
     * @param checkedRmaLineConfList Checked RMA LineConf List
     */
    public static void setOutParamForDelyInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<Integer> checkedLineConfList, List<Integer> checkedRmaLineConfList) { // 2018/03/07 S21_NA#19808 Mod

        // 2018/03/07 S21_NA#19808 Mod bizMsg.A, C -> glblMsg.A, C
        // QC#2478
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        // 2017/10/19 S21_NA#21122 Del Start
//        List<String> chkOrdPosNumList = new ArrayList<String>();
        // 2017/10/19 S21_NA#21122 Del End
        boolean hasError = false;
        // 2017/10/19 S21_NA#21122 Del Start
//        String selCsv;
        // 2017/10/19 S21_NA#21122 Del End

        // 2018/03/07 S21_NA#19808 Add Start
        int firstErrPageNum = 0;
        int validCnt = 0;
        // 2018/03/07 S21_NA#19808 Add End
        if (NWAL1500Constant.TAB_LINE_CONFIG.equals(xxDplyTab)) {
            NWAL1500_ASMsg aSMsg = null;

            for (int ordPosNum : checkedLineConfList) {
                aSMsg = glblMsg.A.no(ordPosNum);

                if (!ZYPCommonFunc.hasValue(aSMsg.dsCpoConfigPk_LC)) {
                    aSMsg.xxChkBox_LC.setErrorInfo(1, NWAM0680E);
                    hasError = true;

                    // 2018/03/07 S21_NA#19808 Add Start
                    if (firstErrPageNum < 0) {
                        firstErrPageNum = aSMsg.xxPageNum_LC.getValueInt();
                    }
                    // 2018/03/07 S21_NA#19808 Add End
                    // 2017/10/19 S21_NA#21122 Del Start
//                } else {
//                    chkOrdPosNumList.add(aCMsg.dsOrdPosnNum_LC.getValue());
                    // 2017/10/19 S21_NA#21122 Del End
                } else { // 2018/03/07 S21_NA#19808 Add Start
                    bizMsg.E.no(validCnt++).xxLineNum_AW.setValue(aSMsg.dsOrdPosnNum_LC.getValue());
                    if (validCnt >= bizMsg.E.length()) {
                        int errIdx = checkedLineConfList.get(0);
                        firstErrPageNum = glblMsg.A.no(errIdx).xxPageNum_LC.getValueInt();
                        glblMsg.A.no(errIdx).xxChkBox_LC.setErrorInfo(1, NWAM0950E, new String[]{String.valueOf(bizMsg.E.length())});
                        hasError = true;
                    }
                } // 2018/03/07 S21_NA#19808 Add End
            }
            if (hasError) {
                // 2018/03/07 S21_NA#19808 Add Start
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, firstErrPageNum);
                // 2018/03/07 S21_NA#19808 Add End
                return;
            }

            // 2018/03/07 S21_NA#19808 Add Start
            bizMsg.E.setValidCount(validCnt);
            // 2018/03/07 S21_NA#19808 Add End
            // 2017/10/19 S21_NA#21122 Del Start
//            selCsv = listToCsv(chkOrdPosNumList);
            // 2016/10/21 S21_NA#15472 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_AW, selCsv);
//            int lenCsv = selCsv.length();
//            int sizeTxt = bizMsg.getAttr("xxDsMsgEntryTxt_AW").getDigit();
//            if (lenCsv > sizeTxt) {
//                int idx = checkedLineConfList.get(0);
//                bizMsg.A.no(idx).xxChkBox_LC.setErrorInfo(1, NWAM0904E);
//                return;
//            }
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMsgEntryTxt_AW, selCsv);
            // 2016/10/21 S21_NA#15472 Mod End
            // 2017/10/19 S21_NA#21122 Del End
            ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_AW, CONFIG_CATG.OUTBOUND);

        } else if (NWAL1500Constant.TAB_RMA.equals(xxDplyTab)) {
            NWAL1500_CSMsg cSMsg;

            for (int ordPosNum : checkedRmaLineConfList) {
                cSMsg = glblMsg.C.no(ordPosNum);
                if (!ZYPCommonFunc.hasValue(cSMsg.dsCpoConfigPk_RC)) {
                    cSMsg.xxChkBox_RC.setErrorInfo(1, NWAM0680E);
                    hasError = true;

                    // 2018/03/07 S21_NA#19808 Add Start
                    if (firstErrPageNum < 0) {
                        firstErrPageNum = cSMsg.xxPageNum_RC.getValueInt();
                    }
                    // 2018/03/07 S21_NA#19808 Add End
                    // 2017/10/19 S21_NA#21122 Del Start
//                } else {
//                    chkOrdPosNumList.add(cCMsg.dsOrdPosnNum_RC.getValue());
                    // 2017/10/19 S21_NA#21122 Del End
                } else { // 2018/03/07 S21_NA#19808 Add Start
                    bizMsg.E.no(validCnt++).xxLineNum_AW.setValue(cSMsg.dsOrdPosnNum_RC.getValue());
                    if (validCnt >= bizMsg.E.length()) {
                        int errIdx = checkedLineConfList.get(0);
                        firstErrPageNum = glblMsg.C.no(errIdx).xxPageNum_RC.getValueInt();
                        glblMsg.C.no(errIdx).xxChkBox_RC.setErrorInfo(1, NWAM0950E, new String[]{String.valueOf(bizMsg.E.length())});
                        hasError = true;
                    }
                } // 2018/03/07 S21_NA#19808 Add End
            }
            if (hasError) {
                // 2018/03/07 S21_NA#19808 Add Start
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, firstErrPageNum);
                // 2018/03/07 S21_NA#19808 Add End
                return;
            }

            // 2018/03/07 S21_NA#19808 Add Start
            bizMsg.E.setValidCount(validCnt);
            // 2018/03/07 S21_NA#19808 Add End
            // 2017/10/19 S21_NA#21122 Del Start
//            selCsv = listToCsv(chkOrdPosNumList);
            // 2016/10/21 S21_NA#15472 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_AW, selCsv);
//            int lenCsv = selCsv.length();
//            int sizeTxt = bizMsg.getAttr("xxDsMsgEntryTxt_AW").getDigit();
//            if (lenCsv > sizeTxt) {
//                int idx = checkedRmaLineConfList.get(0);
//                bizMsg.C.no(idx).xxChkBox_RC.setErrorInfo(1, NWAM0904E);
//                return;
//            }
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMsgEntryTxt_AW, selCsv);
            // 2016/10/21 S21_NA#15472 Mod End
            // 2017/10/19 S21_NA#21122 Del End
            ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_AW, CONFIG_CATG.INBOUND);
        }
    }

    /**
     * listToCsv
     * @param val List<String>
     * @return String
     */
   public static String listToCsv(List<String> val) {
        String retString = "";

        if (val == null || val.size() == 0) {
            return retString;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < val.size(); i++) {
            sb.append(val.get(i));
            if (i < (val.size() - 1)) {
                sb.append(",");
            }
        }

        return sb.toString();
    }
}
