/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.blap.NWAL2200.NWAL2200_ACMsg;
import business.blap.NWAL2200.NWAL2200_ACMsgArray;
import business.blap.NWAL2200.NWAL2200_BCMsg;
import business.blap.NWAL2200.NWAL2200_BCMsgArray;
import business.blap.NWAL2200.NWAL2200_CCMsg;
import business.blap.NWAL2200.NWAL2200_CCMsgArray;
import business.blap.NWAL2200.NWAL2200_DCMsg;
import business.blap.NWAL2200.NWAL2200_DCMsgArray;
import business.blap.NWAL2200.NWAL2200_ICMsg;
import business.blap.NWAL2200.NWAL2200_ICMsgArray;
import business.blap.NWAL2200.NWAL2200_ISMsg;
import business.blap.NWAL2200.NWAL2200_ISMsgArray;
import business.blap.NWAL2200.NWAL2200_JSMsg;
import business.blap.NWAL2200.NWAL2200_JSMsgArray;
import business.blap.NWAL2200.NWAL2200_KSMsg;
import business.blap.NWAL2200.NWAL2200_KSMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;

/**
 *<pre>
 * NWAL2200CommonLogicForLineControl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/01/25   Fujitsu         Y.Kanefusa      Update          S21_NA#19808
 *</pre>
 */
public class NWAL2200CommonLogicForLineControl {

    /**
     * storeAllLine
     * @param targetList NWAL2200_JSMsgArray
     * @param sourceList NWAL2200_BCMsgArray
     * @return result
     */
    public static boolean storeAllLine(NWAL2200_JSMsgArray targetList, NWAL2200_BCMsgArray sourceList) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            storeLine(targetList, sourceList.no(i));
        }
        return true;
    }

    /**
     * storeAllLine
     * @param targetList NWAL2200_KSMsgArray
     * @param sourceList NWAL2200_DCMsgArray
     * @return result
     */
    public static boolean storeAllLine(NWAL2200_KSMsgArray targetList, NWAL2200_DCMsgArray sourceList) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            storeLine(targetList, sourceList.no(i));
        }
        return true;
    }

    /**
     * storeLine
     * @param lineAllList NWAL2200_JSMsgArray
     * @param line NWAL2200_BCMsg
     * @return result
     */
    public static boolean storeLine(NWAL2200_JSMsgArray lineAllList, NWAL2200_BCMsg line) {

        int result = 0;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {

            result = compareToLine(lineAllList.no(i), line);

            if (result == 0) {
                // same line
                EZDMsg.copy(line, null, lineAllList.no(i), null);
                return true;
            }
        }
        return false;
    }

    /**
     * storeLine
     * @param lineAllList NWAL2200_KSMsgArray
     * @param line NWAL2200_DCMsg
     * @return result
     */
    public static boolean storeLine(NWAL2200_KSMsgArray lineAllList, NWAL2200_DCMsg line) {

        int result = 0;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {

            result = compareToLine(lineAllList.no(i), line);

            if (result == 0) {
                // same line
                EZDMsg.copy(line, null, lineAllList.no(i), null);
                return true;
            }
        }
        return false;
    }

    /**
     * storeLineByConfig
     * @param targetList NWAL2200_JSMsgArray
     * @param sourceList NWAL2200_BCMsgArray
     * @param dsOrdPosnNum String
     * @return result
     */
    public static boolean storeLineByConfig(NWAL2200_JSMsgArray targetList, NWAL2200_BCMsgArray sourceList, String dsOrdPosnNum) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            NWAL2200_BCMsg line = sourceList.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                storeLine(targetList, line);
            }
        }
        return true;
    }

    /**
     * storeLineByConfig
     * @param targetList NWAL2200_KSMsgArray
     * @param sourceList NWAL2200_DCMsgArray
     * @param dsOrdPosnNum String
     * @return result
     */
    public static boolean storeLineByConfig(NWAL2200_KSMsgArray targetList, NWAL2200_DCMsgArray sourceList, String dsOrdPosnNum) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            NWAL2200_DCMsg line = sourceList.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                storeLine(targetList, line);
            }
        }
        return true;
    }

    /**
     * prepareLineS2C
     * @param lineList NWAL2200_BCMsgArray
     * @param lineAllList NWAL2200_JSMsgArray
     * @param dsOrdPosnNum String
     * @return new line
     */
    public static boolean prepareLineS2C(NWAL2200_BCMsgArray lineList, NWAL2200_JSMsgArray lineAllList, String dsOrdPosnNum) {

        // delete CMsg
        List<Integer> deleteList = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(lineList.no(i).dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteList);

        // get insert position
        int insertPosition = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                insertPosition = i;
                break;
            }
            insertPosition = i + 1;
        }

        // prepare S to C
        for (int lineIndex = 0; lineIndex < lineAllList.getValidCount(); lineIndex++) {
            NWAL2200_JSMsg sourceLine = lineAllList.no(lineIndex);
            if (S21StringUtil.isEquals(sourceLine.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                NWAL2200_BCMsg line = (NWAL2200_BCMsg) insertNewLine(lineList, insertPosition++);
                if (line == null) {
                    return false;
                }
                EZDMsg.copy(sourceLine, null, line, null);
            }
        }
        return true;
    }

    /**
     * prepareLineS2C
     * @param lineList NWAL2200_BCMsgArray
     * @param lineAllList NWAL2200_JSMsgArray
     * @param dsOrdPosnNum String
     * @return new line
     */
    public static boolean prepareLineS2C(NWAL2200_DCMsgArray lineList, NWAL2200_KSMsgArray lineAllList, String dsOrdPosnNum) {

        // delete CMsg
        List<Integer> deleteList = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(lineList.no(i).dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteList);

        // get insert position
        int insertPosition = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                insertPosition = i;
                break;
            }
            insertPosition = i + 1;
        }

        // prepare S to C
        for (int lineIndex = 0; lineIndex < lineAllList.getValidCount(); lineIndex++) {
            NWAL2200_KSMsg sourceLine = lineAllList.no(lineIndex);
            if (S21StringUtil.isEquals(sourceLine.dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                NWAL2200_DCMsg line = (NWAL2200_DCMsg) insertNewLine(lineList, insertPosition++);
                if (line == null) {
                    return false;
                }
                EZDMsg.copy(sourceLine, null, line, null);
            }
        }
        return true;
    }

    /**
     * expand
     * @param config NWAL2200_ACMsg
     * @param lineAllList NWAL2200_JSMsgArray
     * @param lineList NWAL2200_BCMsgArray
     */
    public static void expand(NWAL2200_ACMsg config, NWAL2200_JSMsgArray lineAllList, NWAL2200_BCMsgArray lineList) {

        if (!S21StringUtil.isEquals(config.xxSmryLineFlg_L.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }
        prepareLineS2C(lineList, lineAllList, config.dsOrdPosnNum_LC.getValue());
        config.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * expand
     * @param config NWAL2200_CCMsg
     * @param lineAllList NWAL2200_KSMsgArray
     * @param lineList NWAL2200_DCMsgArray
     */
    public static void expand(NWAL2200_CCMsg config, NWAL2200_KSMsgArray lineAllList, NWAL2200_DCMsgArray lineList) {

        if (!S21StringUtil.isEquals(config.xxSmryLineFlg_R.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }
        prepareLineS2C(lineList, lineAllList, config.dsOrdPosnNum_RC.getValue());
        config.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * collapsed
     * @param config NWAL2200_ACMsg
     * @param lineAllList NWAL2200_JSMsgArray
     * @param lineList lineList
     */
    public static void collapsed(NWAL2200_ACMsg config, NWAL2200_JSMsgArray lineAllList, NWAL2200_BCMsgArray lineList) {

        if (S21StringUtil.isEquals(config.xxSmryLineFlg_L.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }

        String dsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();

        // store B to J by configuration
        storeLineByConfig(lineAllList, lineList, dsOrdPosnNum);

        // delete B by configuration
        deleteLineByConfig(lineList, dsOrdPosnNum);

        config.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * collapsed
     * @param config NWAL2200_CCMsg
     * @param lineAllList NWAL2200_KSMsgArray
     * @param lineList NWAL2200_DCMsgArray
     */
    public static void collapsed(NWAL2200_CCMsg config, NWAL2200_KSMsgArray lineAllList, NWAL2200_DCMsgArray lineList) {

        if (S21StringUtil.isEquals(config.xxSmryLineFlg_R.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }

        String dsOrdPosnNum = config.dsOrdPosnNum_RC.getValue();

        // store D to K by configuration
        storeLineByConfig(lineAllList, lineList, dsOrdPosnNum);

        // delete D by configuration
        deleteLineByConfig(lineList, dsOrdPosnNum);

        config.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * getConfig
     * @param configArray NWAL2200_ACMsgArray
     * @param dsOrdPosnNum String
     * @return config
     */
    public static NWAL2200_ACMsg getConfig(NWAL2200_ACMsgArray configArray, String dsOrdPosnNum) {

        for (int i = 0; i < configArray.getValidCount(); i++) {

            if (S21StringUtil.isEquals(configArray.no(i).dsOrdPosnNum_LC.getValue(), dsOrdPosnNum)) {
                return configArray.no(i);
            }
        }
        return null;
    }

    /**
     * getConfig
     * @param configArray NWAL2200_ACMsgArray
     * @param dsOrdPosnNum String
     * @return config
     */
    public static NWAL2200_CCMsg getConfig(NWAL2200_CCMsgArray configArray, String dsOrdPosnNum) {

        for (int i = 0; i < configArray.getValidCount(); i++) {

            if (S21StringUtil.isEquals(configArray.no(i).dsOrdPosnNum_RC.getValue(), dsOrdPosnNum)) {
                return configArray.no(i);
            }
        }
        return null;
    }

    /**
     * getPriceCalcBaseList
     * @param priceCalcBaseArray NWAL2200_ICMsgArray
     * @param dsImptOrdDtlPk BigDecimal
     * @return PriceCalcBaseList
     */
    public static List<NWAL2200_ISMsg> getPriceCalcBaseList(NWAL2200_ISMsgArray priceCalcBaseArray, BigDecimal dsImptOrdDtlPk) {

        List<NWAL2200_ISMsg> priceCalcBaseList = new ArrayList<NWAL2200_ISMsg>();

        for (int i = 0; i < priceCalcBaseArray.getValidCount(); i++) {

            if (NWAL2200CommonLogic.compareBigDecimal(priceCalcBaseArray.no(i).dsImptOrdDtlPk_LP.getValue(), dsImptOrdDtlPk) == 0) {

                priceCalcBaseList.add(priceCalcBaseArray.no(i));
            }
        }

        return priceCalcBaseList;
    }

    /**
     * get line by transaction number
     * @param lineArray lien array
     * @param trxLineNum transaction line number
     * @param trxLineSubNum transaction line sub number
     * @return
     */
    public static NWAL2200_BCMsg getLine(NWAL2200_BCMsgArray lineArray, String trxLineNum, String trxLineSubNum) {

        for (int i = 0; i < lineArray.getValidCount(); i++) {

            NWAL2200_BCMsg line = lineArray.no(i);
            if (!S21StringUtil.isEquals(line.trxLineNum_LL.getValue(), trxLineNum)) {

                continue;
            }

            if (!S21StringUtil.isEquals(line.trxLineSubNum_LL.getValue(), trxLineSubNum)) {

                continue;
            }
            return line;
        }

        return null;
    }

    /**
     * get line by transaction number
     * @param lineArray lien array
     * @param trxLineNum transaction line number
     * @param trxLineSubNum transaction line sub number
     * @return
     */
    public static NWAL2200_DCMsg getLine(NWAL2200_DCMsgArray lineArray, String trxLineNum, String trxLineSubNum) {

        for (int i = 0; i < lineArray.getValidCount(); i++) {

            NWAL2200_DCMsg line = lineArray.no(i);
            if (!S21StringUtil.isEquals(line.trxLineNum_RL.getValue(), trxLineNum)) {

                continue;
            }

            if (!S21StringUtil.isEquals(line.trxLineSubNum_RL.getValue(), trxLineSubNum)) {

                continue;
            }
            return line;
        }

        return null;
    }

    /**
     * insert new row
     * @param list
     * @param insertRow
     * @return new line
     */
    private static EZDMsg insertNewLine(EZDMsgArray list, int insertRow) {

        if (list.getValidCount() >= list.length()) {
            return null;
        }

        for (int i = list.getValidCount() - 1; insertRow <= i; i--) {
            EZDMsg.copy(list.get(i), null, list.get(i + 1), null);
        }
        list.get(insertRow).clear();
        list.setValidCount(list.getValidCount() + 1);
        return list.get(insertRow);
    }

    private static void deleteLineByConfig(NWAL2200_BCMsgArray lineList, String dsOrdPosnNum) {

        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {

            if (S21StringUtil.isEquals(lineList.no(i).dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteRows);
    }

    private static void deleteLineByConfig(NWAL2200_DCMsgArray lineList, String dsOrdPosnNum) {

        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {

            if (S21StringUtil.isEquals(lineList.no(i).dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineList, deleteRows);
    }

    private static int compareToLine(NWAL2200_JSMsg source, NWAL2200_BCMsg target) {

        return compareToLine(source, target.dsOrdPosnNum_LL.getValue(), target.dsCpoLineNum_LL.getValue(), target.dsCpoLineSubNum_LL.getValue());
    }

    private static int compareToLine(NWAL2200_KSMsg source, NWAL2200_DCMsg target) {

        return compareToLine(source, target.dsOrdPosnNum_RL.getValue(), target.dsCpoLineNum_RL.getValue(), target.dsCpoLineSubNum_RL.getValue());
    }

    private static int compareToLine(NWAL2200_JSMsg source, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        if (Integer.parseInt(source.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
            if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineNum_LL.getValue(), dsCpoLineNum) == 0) {
                if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_LL.getValue(), dsCpoLineSubNum) == 0) {
                    return 0; // equal line
                } else if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_LL.getValue(), dsCpoLineSubNum) > 0) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineNum_LL.getValue(), dsCpoLineNum) > 0) {
                return -2; // line level
            } else {
                return 2; // line level
            }
        } else if (Integer.parseInt(source.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
            return -1; // position level
        } else {
            return 1; // position level
        }
    }

    private static int compareToLine(NWAL2200_KSMsg source, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        if (Integer.parseInt(source.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
            if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineNum_RL.getValue(), dsCpoLineNum) == 0) {
                if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_RL.getValue(), dsCpoLineSubNum) == 0) {
                    return 0; // equal line
                } else if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_RL.getValue(), dsCpoLineSubNum) > 0) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (NWAL2200CommonLogic.compareBigDecimal(source.dsCpoLineNum_RL.getValue(), dsCpoLineNum) > 0) {
                return -2; // line level
            } else {
                return 2; // line level
            }
        } else if (Integer.parseInt(source.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
            return -1; // position level
        } else {
            return 1; // position level
        }
    }
}
