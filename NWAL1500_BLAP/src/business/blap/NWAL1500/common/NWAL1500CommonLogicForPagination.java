/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ACMsgArray;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BCMsgArray;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CCMsgArray;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DCMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/25   Fujitsu         S.Takami        Create          S21_NA#19808
 * 2019/09/05   Fujitsu         R.Matsuki       Update          QC#53187
 *</pre>
 */
public class NWAL1500CommonLogicForPagination {

    /** Log output flag */
    private static final boolean LOG_OUT = false;
    /**
     * <pre>
     * Save screen data to global message and moving to pageNum page.
     * @param bizMsg Business Message.
     * @param glblMsg Global Message.
     * @param xxDplyTab Display Tab
     * @param pageNum page number.
     * </pre>
     */
    public static void setPageData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String xxDplyTab, int pageNum) {

        if (isTabLineConfig(xxDplyTab)) {
            setPageNumForLineConfig(bizMsg, glblMsg);
            saveLineConfigDataToGlbl(bizMsg, glblMsg);
            pagingLineConfig(bizMsg, glblMsg, pageNum);
        } else if (isTabRma(xxDplyTab)) {
            setPageNumForRma(bizMsg, glblMsg);
            saveRmaDataToGlbl(bizMsg, glblMsg);
            pagingRma(bizMsg, glblMsg, pageNum);
        }
    }

    /**
     * <pre>
     * Save screen data to Global Message for Line Config
     * @param bizMsg Business Message.
     * @param glblMsg Global Message.
     * <pre>
     */
    public static void saveLineConfigDataToGlbl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            copyConfigDataToSMsg(bizMsg.A.no(i), glblMsg);
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            copyLineDataToSMsg(bizMsg.B.no(i), glblMsg);
        }
    }

    /**
     * <pre>
     * Save screen data to Global Message for RMA
     * @param bizMsg Business Message.
     * @param glblMsg Global Message.
     * <pre>
     */
    public static void saveRmaDataToGlbl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            copyRmaConfigDataToSMsg(bizMsg.C.no(i), glblMsg);
        }
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            copyRmaLineDataToSMsg(bizMsg.D.no(i), glblMsg);
        }
    }

    /**
     * <pre>
     * set bizMsg.xxPageShowCurNum_LL page data to Business Message for Line Config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void pagingLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
    }

    /**
     * <pre>
     * set pageNum page data to Business Message for Line Config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param pageNum jump to page number
     * </pre>
     */
    public static void pagingLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int pageNum) {

        if (pageNum < 1) {
            pageNum = 1;
        }
        int maxPageNum = getMaxPageNumAfterSetPageNum(glblMsg, TAB_LINE_CONFIG);
        if (pageNum > maxPageNum) {
            pageNum = maxPageNum;
        }
        int linesPerPage = bizMsg.B.length();
        int startIdx = (pageNum - 1) * linesPerPage;
        int endIdx = startIdx + linesPerPage;
        int validCnt = getValidCount(glblMsg.B);
        if (endIdx > validCnt) {
            endIdx = validCnt;
        }

        int idx = 0;
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            // 2019/09/05 QC#53187 MOD START
            int pageNum_LL = glblMsg.B.no(i).xxPageNum_LL.getValueInt();
            if (pageNum_LL != 0 && pageNum == pageNum_LL) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(idx), null);
                idx++;
            }
//            if (pageNum == glblMsg.B.no(i).xxPageNum_LL.getValueInt()) {
//                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(idx), null);
//                idx++;
//            }
            // 2019/09/05 QC#53187 MOD END
        }
        bizMsg.B.setValidCount(idx);

        bizMsg.xxPageShowFromNum_LL.setValue(startIdx + 1);
        bizMsg.xxPageShowToNum_LL.setValue(endIdx);
        bizMsg.xxPageShowOfNum_LL.setValue(validCnt);
        bizMsg.xxPageShowCurNum_LL.setValue(pageNum);
        bizMsg.xxPageShowTotNum_LL.setValue(getLineConfigTotalPages(bizMsg, glblMsg));

        int confIdx = 0;
        for (int i = 0; i < glblMsg.A.getValidCount() && confIdx < bizMsg.A.length(); i++) {
            if (pageNum == glblMsg.A.no(i).xxPageNum_LC.getValueInt()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(confIdx), null);
                confIdx++;
            }
        }
        bizMsg.A.setValidCount(confIdx);

        // If the 1st record of the config is not for 1st record of the Line
        if (bizMsg.B.getValidCount() > 0 && ZYPCommonFunc.hasValue(bizMsg.B.no(0).dsOrdPosnNum_LL)) {
            if (isNotIncludedConfigDataInBizMsg(bizMsg, bizMsg.B.no(0))) {
                slideData(bizMsg.A);
                String dsOrdPosnNum = bizMsg.B.no(0).dsOrdPosnNum_LL.getValue();
                NWAL1500_ASMsg configMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.A, dsOrdPosnNum);
                EZDMsg.copy(configMsg, null, bizMsg.A.no(0), null);
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_AS, bizMsg.A.no(0).dsOrdPosnNum_LC);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_AT, getValidMaxDsOrdPosn(glblMsg.A));
    }

    /**
     * <pre>
     * get total Line Config pages.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return total pages of Line Config
     * </pre>
     */
    public static int getLineConfigTotalPages(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int linesPerPage = bizMsg.B.length();
        int pages = getValidCount(glblMsg.B) / linesPerPage;
        int resultOfMod = glblMsg.B.getValidCount() % linesPerPage;
        if (resultOfMod > 0) {
            pages++;
        }
        return pages;
    }

    /**
     * <pre>
     * set bizMsg.xxPageShowCurNum_RL page data to Business Message for RMA.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void pagingRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
    }

    /**
     * <pre>
     * set pageNum page data to Business Message for RMA.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param pageNum jump to page number
     * </pre>
     */
    public static void pagingRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int pageNum) {

        if (pageNum < 1) {
            pageNum = 1;
        }
        int maxPageNum = getMaxPageNumAfterSetPageNum(glblMsg, TAB_RMA);
        if (pageNum > maxPageNum) {
            pageNum = maxPageNum;
        }
        int linesPerPage = bizMsg.D.length();
        int startIdx = (pageNum - 1) * linesPerPage;
        int endIdx = startIdx + linesPerPage;
        int validCnt = getValidCount(glblMsg.D);
        if (endIdx > validCnt) {
            endIdx = validCnt;
        }

        int idx = 0;
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            // 2019/09/05 QC#53187 MOD START
            int pageNum_RL = glblMsg.D.no(i).xxPageNum_RL.getValueInt();
            if (pageNum_RL != 0 && pageNum == pageNum_RL) {
                EZDMsg.copy(glblMsg.D.no(i), null, bizMsg.D.no(idx), null);
                idx++;
            }
//            if (pageNum == glblMsg.D.no(i).xxPageNum_RL.getValueInt()) {
//                EZDMsg.copy(glblMsg.D.no(i), null, bizMsg.D.no(idx), null);
//                idx++;
//            }
            // 2019/09/05 QC#53187 MOD END
        }
        bizMsg.D.setValidCount(idx);

        bizMsg.xxPageShowFromNum_RL.setValue(startIdx + 1);
        bizMsg.xxPageShowToNum_RL.setValue(endIdx);
        bizMsg.xxPageShowOfNum_RL.setValue(validCnt);
        bizMsg.xxPageShowCurNum_RL.setValue(pageNum);
        bizMsg.xxPageShowTotNum_RL.setValue(getRmaTotalPages(bizMsg, glblMsg));

        int confIdx = 0;
        for (int i = 0; i < glblMsg.C.getValidCount() && confIdx < bizMsg.C.length(); i++) {
            if (pageNum == glblMsg.C.no(i).xxPageNum_RC.getValueInt()) {
                EZDMsg.copy(glblMsg.C.no(i), null, bizMsg.C.no(confIdx), null);
                confIdx++;
            }
        }
        bizMsg.C.setValidCount(confIdx);

        // If the 1st record of the config is not for 1st record of the Line
        if (bizMsg.D.getValidCount() > 0 && ZYPCommonFunc.hasValue(bizMsg.D.no(0).dsOrdPosnNum_RL)) {
            if (isNotIncludedConfigDataInBizMsg(bizMsg, bizMsg.D.no(0))) {
                slideData(bizMsg.C);
                String dsOrdPosnNum = bizMsg.D.no(0).dsOrdPosnNum_RL.getValue();
                NWAL1500_CSMsg configMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum);
                EZDMsg.copy(configMsg, null, bizMsg.C.no(0), null);
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_CS, bizMsg.C.no(0).dsOrdPosnNum_RC);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_CT, getValidMaxDsOrdPosn(glblMsg.C));
    }

    /**
     * <pre>
     * get total RMA pages.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return total pages of RMA
     * </pre>
     */
    public static int getRmaTotalPages(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int linesPerPage = bizMsg.D.length();
        int pages = glblMsg.D.getValidCount() / linesPerPage;
        int resultOfMod = getValidCount(glblMsg.D) % linesPerPage;
        if (resultOfMod > 0) {
            pages++;
        }
        return pages;
    }
//    /**
//     * <pre>
//     * Set Pagination numbers.
//     * @param bizMsg Business Message
//     * @param glblMsg Global Message
//     * @param pageNum page number for display.
//     * </pre>
//     */
//    public static void setPageNationData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int pageNum) {
//
//        if (pageNum <= 0) {
//            pageNum = 1;
//        }
//        String dplyTab = bizMsg.xxDplyTab.getValue();
//        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, dplyTab)) {
//            bizMsg.dsOrdPosnNum_AS.setValue("1");
//            bizMsg.dsOrdPosnNum_AT.setValue(String.valueOf(glblMsg.A.getValidCount()));
//        } else if (S21StringUtil.isEquals(TAB_RMA, dplyTab)) {
//            bizMsg.dsOrdPosnNum_CS.setValue("1");
//            bizMsg.dsOrdPosnNum_CT.setValue(String.valueOf(glblMsg.C.getValidCount()));
//        }
//    }
//
//    /**
//     * <pre>
//     * adhoc method.....
//     * @param bizMsg Business Mesasge
//     * @param glblMsg Global Message
//     * @param xxDplyTab current tab
//     * </pre>
//     */
//    public static void setDetailCount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String xxDplyTab) {
//
//        if (isTabLineConfig(xxDplyTab)) {
//            bizMsg.dsOrdPosnNum_AS.setValue("1");
//            bizMsg.dsOrdPosnNum_AT.setValue(String.valueOf(glblMsg.A.getValidCount()));
//            bizMsg.xxPageShowFromNum_LL.setValue(BigDecimal.ONE);
//            bizMsg.xxPageShowToNum_LL.setValue(bizMsg.B.getValidCount());
//            bizMsg.xxPageShowOfNum_LL.setValue(bizMsg.B.getValidCount());
//            bizMsg.xxPageShowCurNum_LL.setValue(BigDecimal.ONE);
//            bizMsg.xxPageShowTotNum_LL.setValue(bizMsg.B.getValidCount());
//        } else if (isTabRma(xxDplyTab)) {
//            bizMsg.dsOrdPosnNum_CS.setValue("1");
//            bizMsg.dsOrdPosnNum_CT.setValue(String.valueOf(glblMsg.C.getValidCount()));
//            bizMsg.xxPageShowFromNum_RL.setValue(BigDecimal.ONE);
//            bizMsg.xxPageShowToNum_RL.setValue(bizMsg.D.getValidCount());
//            bizMsg.xxPageShowOfNum_RL.setValue(bizMsg.D.getValidCount());
//            bizMsg.xxPageShowCurNum_RL.setValue(BigDecimal.ONE);
//            bizMsg.xxPageShowTotNum_RL.setValue(bizMsg.D.getValidCount());
//        }
//    }

    /**
     * <pre>
     * get detected line's page number
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param dtlMsg Detail Message
     * @return page number
     * </pre>
     */
    public static int getNewLinePage(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, EZDMsg dtlMsg) {

        int dtlIdx = getDetectedDetailIndexOfGlobalMessage(dtlMsg, glblMsg);
        if (dtlIdx < 0) {
            return -1;
        }

        return getNewLinePage(bizMsg, dtlMsg, dtlIdx);
    }

    /**
     * <pre>
     * get detected line's page number
     * @param bizMsg Business Message
     * @param dtlSMsg Detail Message
     * @param dtlIdx Details index
     * @return page Number
     * </pre>
     */
    public static int getNewLinePage(NWAL1500CMsg bizMsg, EZDMsg dtlSMsg, int dtlIdx) {

        int linesPerPage = 0;

        if (dtlSMsg instanceof NWAL1500_BSMsg) {
            linesPerPage = bizMsg.B.length();
        } else if (dtlSMsg instanceof NWAL1500_DSMsg) {
            linesPerPage = bizMsg.D.length();
        } else {
            return -1;
        }
        int pageNum = (dtlIdx + 1) / linesPerPage;
        int pageMod = (dtlIdx + 1) % linesPerPage;
        if (pageMod > 0) {
            pageNum++;
        }
        return pageNum;
    }

//    /**
//     * <pre>
//     * Copy One Config or Detail line Data to Global Message.
//     * @param dtlMsg Config or Line Message
//     * @param glblMsg Global Message.
//     * </pre>
//     */
//    public static void copyDetectedDetailDataToGlobalMessage(EZDMsg dtlMsg, NWAL1500SMsg glblMsg) {
//
//        if (dtlMsg instanceof NWAL1500_ACMsg) {
//            copyConfigDataToSMsg((NWAL1500_ACMsg) dtlMsg, glblMsg);
//        } else if (dtlMsg instanceof NWAL1500_BCMsg) {
//            copyLineDataToSMsg((NWAL1500_BCMsg) dtlMsg, glblMsg);
//        } else if (dtlMsg instanceof NWAL1500_CCMsg) {
//            copyRmaConfigDataToSMsg((NWAL1500_CCMsg) dtlMsg, glblMsg);
//        } else if (dtlMsg instanceof NWAL1500_DCMsg) {
//            copyRmaLineDataToSMsg((NWAL1500_DCMsg) dtlMsg, glblMsg);
//        }
//    }

    /**
     * <pre>
     * Get index of detected object in Global Message table.
     * @param dtlMsg Config or Line message
     * @param glblMsg Global Message
     * @return index of the dtlMsg in the Global Message
     * </pre>
     */
    public static int getDetectedDetailIndexOfGlobalMessage(EZDMsg dtlMsg, NWAL1500SMsg glblMsg) {

        if (dtlMsg instanceof NWAL1500_ACMsg) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg glblConfigMsg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_ACMsg) dtlMsg).dsOrdPosnNum_LC.getValue(), glblConfigMsg.dsOrdPosnNum_LC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_BCMsg) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg glblLineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_BCMsg) dtlMsg).xxLineNum_LL.getValue(), glblLineMsg.xxLineNum_LL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_CCMsg) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg glblRmaConfigMsg = glblMsg.C.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_CCMsg) dtlMsg).dsOrdPosnNum_RC.getValue(), glblRmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_DCMsg) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg glblRmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_DCMsg) dtlMsg).xxLineNum_RL.getValue(), glblRmaLineMsg.xxLineNum_RL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_ASMsg) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg glblLineMsg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_ASMsg) dtlMsg).dsOrdPosnNum_LC.getValue(), glblLineMsg.dsOrdPosnNum_LC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_BSMsg) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg glblLineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_BSMsg) dtlMsg).xxLineNum_LL.getValue(), glblLineMsg.xxLineNum_LL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_CSMsg) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg glblLineMsg = glblMsg.C.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_CSMsg) dtlMsg).dsOrdPosnNum_RC.getValue(), glblLineMsg.dsOrdPosnNum_RC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_DSMsg) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg glblRmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(((NWAL1500_DSMsg) dtlMsg).xxLineNum_RL.getValue(), glblRmaLineMsg.xxLineNum_RL.getValue())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * <pre>
     * Get add line row number for detected position.
     * @param lineList Global Message, Line Config Detail Table
     * @param dsOrdPosnNum Position#
     * @return adding index
     * </pre>
     */
    public static int getAddLineRowForGlobal(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) {

        int maxLineRow = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                return i;
            }
            maxLineRow = i + 1;
        }
        return maxLineRow;
    }

    /**
     * <pre>
     * Get add line row number for detected position.
     * @param lineList  Global Message, RMA Detail Table
     * @param dsOrdPosnNum Position#
     * @return adding index
     * </pre>
     */
    public static int getAddLineRowForGlobal(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) {

        int maxLineRow = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                return i;
            }
            maxLineRow = i + 1;
        }
        return maxLineRow;
    }

    /**
     * <pre>
     * get Max DS_CPO_LINE_NUM from Global Message Line Config details.
     * @param lineList Global Message, Line Config Detail Table.
     * @param dsOrdPosnNum Position#
     * @return max DS_CPO_LINe_NUM of detected position.
     */
    public static int getMaxLineNumForGlobal(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) {

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_LL.getValueInt();
            }
        }
        return maxLineNum;
    }

    /**
     * <pre>
     * get Max DS_CPO_LINE_NUM from Global Message RMA Details.
     * @param lineList Global Message, RMA Detail Table.
     * @param dsOrdPosnNum Position#
     * @return max DS_CPO_LINe_NUM of detected position.
     */
    public static int getMaxLineNumForGlobal(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) {

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_RL.getValueInt();
            }
        }
        return maxLineNum;
    }

    /**
     * <pre>
     * Jump to detected postion.
     * Caution!!.
     * You must call setPageNumForLineConfig() or setPageNumForRma before calling this method
     * @param bizMsg Business Message.
     * @param glblMsg Global Message.
     * @param dsOrdPosnNum Position number
     * @param xxDplyTab Display Tab Name
     * </pre>
     */
    public static void jumpToSpecifinedConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, String xxDplyTab) {

        int dtlIdx = 0;
        int pageNum = 0;
        if (isTabLineConfig(xxDplyTab)) {
            for (dtlIdx = 0; dtlIdx < glblMsg.B.getValidCount(); dtlIdx++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(dtlIdx);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    pageNum = lineMsg.xxPageNum_LL.getValueInt();
                    break;
                }
            }
        } else if (isTabRma(xxDplyTab)) {
            for (dtlIdx = 0; dtlIdx < glblMsg.D.getValidCount(); dtlIdx++) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(dtlIdx);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                    pageNum = lineMsg.xxPageNum_RL.getValueInt();
                    break;
                }
            }
        }
        if (isTabLineConfig(xxDplyTab)) {
            pagingLineConfig(bizMsg, glblMsg, pageNum);
            bizMsg.dsOrdPosnNum_AS.setValue(dsOrdPosnNum);
        } else if (isTabRma(xxDplyTab)) {
            pagingRma(bizMsg, glblMsg, pageNum);
            bizMsg.dsOrdPosnNum_CS.setValue(dsOrdPosnNum);
        }
    }

    /**
     * Check Current Tab is TAB_LINE_CONFIG or not
     * @param xxDplyTab Display Tab Name
     * @return true: TAB_LINE_CONFIG false: not
     */
    public static boolean isTabLineConfig(String xxDplyTab) {

        return S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab);
    }

    /**
     * Check Current Tab is TAB_RMA or not
     * @param xxDplyTab Display Tab Name
     * @return true: TAB_RMA false: not
     */
    public static boolean isTabRma(String xxDplyTab) {

        return S21StringUtil.isEquals(TAB_RMA, xxDplyTab);
    }

    /**
     * <pre>
     * get Global Area Line Config Config Message from Biz Message.
     * @param glblMsg Global Message
     * @param bizConfigMsg Biz. Config Message
     * @return Global Config Message
     * </pre>
     */
    public static NWAL1500_ASMsg getGlobalConfigMsgFromBizMsg(NWAL1500SMsg glblMsg, NWAL1500_ACMsg bizConfigMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg glblConfigMsg = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(bizConfigMsg.dsOrdPosnNum_LC.getValue(), glblConfigMsg.dsOrdPosnNum_LC.getValue())) {
                return glblConfigMsg;
            }
        }
        return null;
    }

    /**
     * <pre>
     * get Global Area RMA Config Message from Biz Message.
     * @param glblMsg Global Message
     * @param bizRmaConfigMsg Biz. RMA Config Message
     * @return Global RMA Config Message
     * </pre>
     */
    public static NWAL1500_CSMsg getGlobalConfigMsgFromBizMsg(NWAL1500SMsg glblMsg, NWAL1500_CCMsg bizRmaConfigMsg) {

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg glblRmaConfigMsg = glblMsg.C.no(i);
            if (S21StringUtil.isEquals(bizRmaConfigMsg.dsOrdPosnNum_RC.getValue(), glblRmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                return glblRmaConfigMsg;
            }
        }
        return null;
    }

    /**
     * <pre>
     * get global config message by position number
     * @param configList Line Config Config List
     * @param dsOrdPosnNum position number
     * @return global line config message
     * <pre>
     */
    public static NWAL1500_ASMsg getParentConfigFromGlobal(NWAL1500_ASMsgArray configList, String dsOrdPosnNum) {

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_LC.getValue())) {
                return configList.no(i);
            }
        }
        return null;
    }

    /**
     * <pre>
     * get global RMA config message by position number
     * @param configList RMA Config list
     * @param dsOrdPosnNum position number
     * @return global RMA config message
     * </pre>
     */
    public static NWAL1500_CSMsg getParentConfigFromGlobal(NWAL1500_CSMsgArray configList, String dsOrdPosnNum) {

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_RC.getValue())) {
                return configList.no(i);
            }
        }
        return null;
    }

    /**
     * <pre>
     * Copy Line Config Config data to Global Area.
     * @param configMsg Biz. Config Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyConfigDataToSMsg(NWAL1500_ACMsg configMsg, NWAL1500SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(configMsg, glblMsg);
        if (idx >= 0) {
            EZDMsg.copy(configMsg, null, glblMsg.A.no(idx), null);
        }
    }

    /**
     * <pre>
     * Copy Line Config Line data to Global Area.
     * @param lineMsg Biz. Line Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyLineDataToSMsg(NWAL1500_BCMsg lineMsg, NWAL1500SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(lineMsg, glblMsg);
        if (idx < 0) {
            return;
        }
        String dsOrdPosnNum = glblMsg.B.no(idx).dsOrdPosnNum_LL.getValue();
        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            dsOrdPosnNum = glblMsg.B.no(idx).xxLineNum_LL.getValue();
            int findIdx = dsOrdPosnNum.indexOf(".");
            dsOrdPosnNum = dsOrdPosnNum.substring(0, findIdx);
        }
        NWAL1500_ASMsg configMsg = getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
            return;
        }
        if (idx >= 0) {
            EZDMsg.copy(lineMsg, null, glblMsg.B.no(idx), null);
        }
    }

    /**
     * <pre>
     * Copy Line Config Line data to Global Area.
     * @param dsOrdPosnNum target Position.
     * @param lineMsgArray Biz. Messageline message array.
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyLineDataToSMsg(String dsOrdPosnNum, NWAL1500_BCMsgArray lineMsgArray, NWAL1500SMsg glblMsg) {

        NWAL1500_ASMsg configMsg = getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
            return;
        }
        for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
            NWAL1500_BCMsg lineMsg = lineMsgArray.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            copyLineDataToSMsg(lineMsg, glblMsg);
        }
    }

    /**
     * <pre>
     * Copy RMA Config data to Global Area.
     * @param rmaConfigMsg Biz. RMA Mesasge
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyRmaConfigDataToSMsg(NWAL1500_CCMsg rmaConfigMsg, NWAL1500SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(rmaConfigMsg, glblMsg);
        if (idx >= 0) {
            EZDMsg.copy(rmaConfigMsg, null, glblMsg.C.no(idx), null);
        }
        return;
    }

    /**
     * <pre>
     * Copy RMA Line data to Global Area.
     * @param rmaLineMsg  RMA Line Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyRmaLineDataToSMsg(NWAL1500_DCMsg rmaLineMsg, NWAL1500SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(rmaLineMsg, glblMsg);
        if (idx < 0) {
            return;
        }
        String dsOrdPosnNum = glblMsg.D.no(idx).dsOrdPosnNum_RL.getValue();
        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            dsOrdPosnNum = glblMsg.D.no(idx).xxLineNum_RL.getValue();
            int findIdx = dsOrdPosnNum.indexOf(".");
            dsOrdPosnNum = dsOrdPosnNum.substring(0, findIdx);
        }
        NWAL1500_CSMsg rmaConfigMsg = getParentConfigFromGlobal(glblMsg.C, glblMsg.D.no(idx).dsOrdPosnNum_RL.getValue());
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaConfigMsg.xxSmryLineFlg_R.getValue())) {
            return;
        }
        if (idx >= 0) {
            EZDMsg.copy(rmaLineMsg, null, glblMsg.D.no(idx), null);
        }
        return;
    }

    /**
     * <pre>
     * Copy Line Config Line data to Global Area.
     * @param dsOrdPosnNum target Position.
     * @param rmaLineMsgArray Biz. Messageline message array.
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyRmaLineDataToSMsg(String dsOrdPosnNum, NWAL1500_DCMsgArray rmaLineMsgArray, NWAL1500SMsg glblMsg) {

        NWAL1500_CSMsg rmaConfigMsg = getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaConfigMsg.xxSmryLineFlg_R.getValue())) {
            return;
        }
        for (int i = 0; i < rmaLineMsgArray.getValidCount(); i++) {
            NWAL1500_DCMsg rmaLineMsg = rmaLineMsgArray.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            copyRmaLineDataToSMsg(rmaLineMsg, glblMsg);
        }
    }

    /**
     * <pre>
     * Set page number for Line Config TAB detail data and configuration data.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void setPageNumForLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int pageNum = 1;
        int perPageLines = bizMsg.B.length();
        int pageLines = 0;

        Map<String, Integer> posPageMap = new HashMap<String, Integer>(0);
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxSmryLineFlg_LL.getValue()) //
                    || ZYPConstant.FLG_ON_Y.equals(lineMsg.xxResFltrFlg_LL.getValue())) {
                lineMsg.xxPageNum_LL.clear();
                continue;
            }
            lineMsg.xxPageNum_LL.setValue(pageNum);
            if (posPageMap.get(lineMsg.dsOrdPosnNum_LL.getValue()) == null) {
                posPageMap.put(lineMsg.dsOrdPosnNum_LL.getValue(), new Integer(pageNum));
            }
            pageLines++;
            if (pageLines >= perPageLines) {
                pageLines = 0;
                pageNum++;
            }
        }

        // Set Page Number For Config
        Set<String> keyPosSet = posPageMap.keySet();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).xxPageNum_LC.clear();
        }
        for (String dsOrdPosnNum : keyPosSet) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    glblMsg.A.no(i).xxPageNum_LC.setValue(posPageMap.get(dsOrdPosnNum).intValue());
                }
            }
        }

        // Set Page Number For Config for which had been collapsed.
//        int curPageNum = 1;
//        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
//            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
//            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxResFltrFlg_LC.getValue())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(configMsg.xxPageNum_LC) //
//                    && configMsg.xxPageNum_LC.getValueInt() > curPageNum) {
//                curPageNum++;
//                continue;
//            }
//            if (!ZYPCommonFunc.hasValue(configMsg.xxPageNum_LC)) {
//                configMsg.xxPageNum_LC.setValue(curPageNum);
//            }
//        }
    }


    /**
     * <pre>
     * Set page number for Line Config TAB detail data and configuration data.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void setPageNumForRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int pageNum = 1;
        int perPageLines = bizMsg.D.length();
        int pageLines = 0;

        Map<String, Integer> posPageMap = new HashMap<String, Integer>(0);
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxSmryLineFlg_RL.getValue()) //
                    || ZYPConstant.FLG_ON_Y.equals(lineMsg.xxResFltrFlg_RL.getValue())) {
                lineMsg.xxPageNum_RL.clear();
                continue;
            }
            lineMsg.xxPageNum_RL.setValue(pageNum);
            if (posPageMap.get(lineMsg.dsOrdPosnNum_RL.getValue()) == null) {
                posPageMap.put(lineMsg.dsOrdPosnNum_RL.getValue(), new Integer(pageNum));
            }
            pageLines++;
            if (pageLines >= perPageLines) {
                pageLines = 0;
                pageNum++;
            }
        }

        // Set Page Number For Config
        Set<String> keyPosSet = posPageMap.keySet();
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblMsg.C.no(i).xxPageNum_RC.clear();
        }
        for (String dsOrdPosnNum : keyPosSet) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                    glblMsg.C.no(i).xxPageNum_RC.setValue(posPageMap.get(dsOrdPosnNum).intValue());
                }
            }
        }

        // Set Page Number For Config for which had been collapsed.
//        int curPageNum = 1;
//        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
//            NWAL1500_CSMsg configMsg = glblMsg.C.no(i);
//            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxResFltrFlg_RC.getValue())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(configMsg.xxPageNum_RC) //
//                    && configMsg.xxPageNum_RC.getValueInt() > curPageNum) {
//                curPageNum++;
//                continue;
//            }
//            if (!ZYPCommonFunc.hasValue(configMsg.xxPageNum_RC)) {
//                configMsg.xxPageNum_RC.setValue(curPageNum);
//            }
//        }
    }

    /**
     * <pre>
     * @param glblMsg Global Message
     * @param xxDplyTab current TAB
     * @return max Page Number of detected TAB.
     * </pre>
     */
    public static int getMaxPageNumAfterSetPageNum(NWAL1500SMsg glblMsg, String xxDplyTab) {

        int maxPageNum = 0;
        if (isTabLineConfig(xxDplyTab)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (ZYPCommonFunc.hasValue(lineMsg.xxPageNum_LL) //
                        && lineMsg.xxPageNum_LL.getValueInt() > maxPageNum) {
                    maxPageNum = lineMsg.xxPageNum_LL.getValueInt();
                }
            }
        } else if (isTabRma(xxDplyTab)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (ZYPCommonFunc.hasValue(rmaLineMsg.xxPageNum_RL) //
                        && rmaLineMsg.xxPageNum_RL.getValueInt() > maxPageNum) {
                    maxPageNum = rmaLineMsg.xxPageNum_RL.getValueInt();
                }
            }
        }
        return maxPageNum;
    }

    public static boolean isCollapesedConfig(EZDMsg pConfigMsg) {

        if (pConfigMsg instanceof NWAL1500_ACMsg) {
            NWAL1500_ACMsg configMsg = (NWAL1500_ACMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg configMsg = (NWAL1500_ASMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_CCMsg) {
            NWAL1500_CCMsg configMsg = (NWAL1500_CCMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_R.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg configMsg = (NWAL1500_CSMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_R.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * <pre>
     * copy Global Detail Message (Config, Line) to Biz Detail Message (Config, Line)
     * @param dtlMsg EZDSMsg (NWAL1500_ASMsg, _BSmsg, _CSMsg, _DSMsg
     * @param bizMsg Business Message
     * </pre>
     */
    public static void copyGlblMsgToBizDtlMsg(EZDSMsg dtlMsg, NWAL1500CMsg bizMsg) {

        if (dtlMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg configMsg = (NWAL1500_ASMsg) dtlMsg;
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_LC.getValue(), bizMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    EZDMsg.copy(configMsg, null, bizMsg.A.no(i), null);
                }
            }
        } else if (dtlMsg instanceof NWAL1500_BSMsg) {
            NWAL1500_BSMsg lineMsg = (NWAL1500_BSMsg) dtlMsg;
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (S21StringUtil.isEquals(lineMsg.xxLineNum_LL.getValue(), bizMsg.B.no(i).xxLineNum_LL.getValue())) {
                    EZDMsg.copy(lineMsg, null, bizMsg.B.no(i), null);
                }
            }
        } else if (dtlMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg rmaConfigMsg = (NWAL1500_CSMsg) dtlMsg;
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                if (S21StringUtil.isEquals(rmaConfigMsg.dsOrdPosnNum_RC.getValue(), bizMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                    EZDMsg.copy(rmaConfigMsg, null, bizMsg.C.no(i), null);
                }
            }
        } else if (dtlMsg instanceof NWAL1500_DSMsg) {
            NWAL1500_DSMsg rmaLineMsg = (NWAL1500_DSMsg) dtlMsg;
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                if (S21StringUtil.isEquals(rmaLineMsg.xxLineNum_RL.getValue(), bizMsg.D.no(i).xxLineNum_RL.getValue())) {
                    EZDMsg.copy(rmaLineMsg, null, bizMsg.D.no(i), null);
                }
            }
        }
    }

    public static boolean isCollapesedLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, EZDMsg pConfigMsg) {

        if (pConfigMsg instanceof NWAL1500_ACMsg) {
            NWAL1500_ACMsg configMsg = (NWAL1500_ACMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg configMsg = (NWAL1500_ASMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_CCMsg) {
            NWAL1500_CCMsg configMsg = (NWAL1500_CCMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_R.getValue())) {
                return true;
            } else {
                return false;
            }
        } else if (pConfigMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg configMsg = (NWAL1500_CSMsg) pConfigMsg;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_R.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static int getValidCount(EZDMsgArray array) {

        int validCnt = 0;
        if (array instanceof NWAL1500_ASMsgArray) {
            NWAL1500_ASMsgArray instanceArray = (NWAL1500_ASMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_LC.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_BSMsgArray) {
            NWAL1500_BSMsgArray instanceArray = (NWAL1500_BSMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_LL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxSmryLineFlg_LL.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_CSMsgArray) {
            NWAL1500_CSMsgArray instanceArray = (NWAL1500_CSMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_RC.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_DSMsgArray) {
            NWAL1500_DSMsgArray instanceArray = (NWAL1500_DSMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_RL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxSmryLineFlg_RL.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_ACMsgArray) {
            NWAL1500_ACMsgArray instanceArray = (NWAL1500_ACMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_LC.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_BCMsgArray) {
            NWAL1500_BCMsgArray instanceArray = (NWAL1500_BCMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_LL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxSmryLineFlg_LL.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_CCMsgArray) {
            NWAL1500_CCMsgArray instanceArray = (NWAL1500_CCMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_RC.getValue())) {
                    continue;
                }
                validCnt++;
            }
        } else if (array instanceof NWAL1500_DCMsgArray) {
            NWAL1500_DCMsgArray instanceArray = (NWAL1500_DCMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_RL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxSmryLineFlg_RL.getValue())) {
                    continue;
                }
                validCnt++;
            }
        }
        return validCnt;
    }

    private static String getValidMaxDsOrdPosn(EZDSMsgArray array) {

        String validMaxDsOrdPosn = "0";

        if (array instanceof NWAL1500_ASMsgArray) {
            NWAL1500_ASMsgArray instanceArray = (NWAL1500_ASMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_LC.getValue())) {
                    continue;
                }
                validMaxDsOrdPosn = instanceArray.no(i).dsOrdPosnNum_LC.getValue();
            }
        } else if (array instanceof NWAL1500_CSMsgArray) {
            NWAL1500_CSMsgArray instanceArray = (NWAL1500_CSMsgArray) array;
            for (int i = 0; i < instanceArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instanceArray.no(i).xxResFltrFlg_RC.getValue())) {
                    continue;
                }
                validMaxDsOrdPosn = instanceArray.no(i).dsOrdPosnNum_RC.getValue();
            }
        }
        return validMaxDsOrdPosn;
    }

    private static void slideData(EZDMsgArray array) {

        List<Integer> deleteRowList = new ArrayList<Integer>(0);
        int validCount = array.getValidCount();
        if (validCount - 1 == array.length()) {
            deleteRowList.add(Integer.valueOf(validCount - 1));
            ZYPTableUtil.deleteRows(array, deleteRowList);
            validCount--;
            array.setValidCount(validCount);
        }

        if (array instanceof NWAL1500_ACMsgArray) {
            NWAL1500_ACMsgArray configArray = (NWAL1500_ACMsgArray) array;
            for (int i = validCount; i > 0; i--) {
                EZDMsg.copy(configArray.no(i - 1), null, configArray.no(i), null);
            }
            configArray.no(0).clear();
            configArray.setValidCount(validCount + 1);
        } else if (array instanceof NWAL1500_CCMsgArray) {
            NWAL1500_CCMsgArray configArray = (NWAL1500_CCMsgArray) array;
            for (int i = validCount; i > 0; i--) {
                EZDMsg.copy(configArray.no(i - 1), null, configArray.no(i), null);
            }
            configArray.no(0).clear();
            configArray.setValidCount(validCount + 1);
        }
    }


    private static boolean isNotIncludedConfigDataInBizMsg(NWAL1500CMsg bizMsg, EZDCMsg dtlMsg) {

        if (dtlMsg instanceof NWAL1500_BCMsg) {
            NWAL1500_BCMsg lineMsg = (NWAL1500_BCMsg) dtlMsg;
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    return false;
                }
            }
        } else if (dtlMsg instanceof NWAL1500_DCMsg) {
            NWAL1500_DCMsg rmaLineMsg = (NWAL1500_DCMsg) dtlMsg;
            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBizMsg(NWAL1500CMsg bizMsg) {

        if (!LOG_OUT) {
            return;
        }
        System.out.println(" -------- Business Message --------");
        System.out.println(" ++++ A message ++++");
        for (int i = 0 ; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500_ACMsg msg = bizMsg.A.no(i);
            String printLine = String.format("Pos#: %s, Smry: %s, page: %d", msg.dsOrdPosnNum_LC.getValue() //
                    , msg.xxSmryLineFlg_L.getValue() //
                    , msg.xxPageNum_LC.getValueInt());
            System.out.println(printLine);
        }
        System.out.println(" ++++ B message ++++");
        for (int i = 0 ; i < bizMsg.B.getValidCount(); i++) {
            NWAL1500_BCMsg msg = bizMsg.B.no(i);
            String printLine = String.format("Pos#: %s, Smry: %s, Filter: %s line num: %spage: %d", 
                    msg.dsOrdPosnNum_LL.getValue() //
                    , msg.xxSmryLineFlg_LL.getValue() //
                    , msg.xxResFltrFlg_LL.getValue() //
                    , msg.xxLineNum_LL.getValue() //
                    , msg.xxPageNum_LL.getValueInt());
            System.out.println(printLine);
        }
    }

    public void printGlblMsg(NWAL1500SMsg glblMsg) {

        if (!LOG_OUT) {
            return;
        }
        System.out.println(" ======== Global Message ========");
        System.out.println(" ++++ A message ++++");
        for (int i = 0 ; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg msg = glblMsg.A.no(i);
            String printLine = String.format("Pos#: %s, Smry: %s, page: %d", msg.dsOrdPosnNum_LC.getValue() //
                    , msg.xxSmryLineFlg_L.getValue() //
                    , msg.xxPageNum_LC.getValueInt());
            System.out.println(printLine);
        }
        System.out.println(" ++++ B message ++++");
        for (int i = 0 ; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg msg = glblMsg.B.no(i);
            String printLine = String.format("Pos#: %s, Smry: %s, Filter: %s line num: %spage: %d", 
                    msg.dsOrdPosnNum_LL.getValue() //
                    , msg.xxSmryLineFlg_LL.getValue() //
                    , msg.xxResFltrFlg_LL.getValue() //
                    , msg.xxLineNum_LL.getValue() //
                    , msg.xxPageNum_LL.getValueInt());
            System.out.println(printLine);
        }
    }
}
