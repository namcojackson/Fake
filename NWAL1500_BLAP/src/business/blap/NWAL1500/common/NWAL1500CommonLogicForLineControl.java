/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_BILLING_HOLD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CLOSED_LOAN_RETURN;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CLOSED_LOAN_SOLD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_DELIVERED_TO_SHOP;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_IN_SHOP_OR_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_INVOICED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_ON_LOAN;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PARTIAL_RECEIVED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_DEALER_INSTALL;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_DELIVERY_CONF;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_FULFILLMENT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_INSPECTION;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_INSTALL;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_INVOICE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_RETURN;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_SHIP;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_SHIPPED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_SHIPPED_CLOSED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_WAITING_PICK;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_WAITING_RECEIPT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CLOSED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0772E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForConfigId;
import business.blap.NWAL1500.NWAL1500QueryForLineConfig;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BCMsgArray;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DCMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * 2016/03/15   Fujitsu         S.Takami         Update         S21_NA#4691
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#2334
 * 2016/08/01   Hitachi         Y.Takeno        Update          S21_NA#12599
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/10/05   Fujitsu         S.Takami        Update          S21_NA#7645-3
 * 2016/10/31   Fujitsu         S.Takami        Update          S21_NA#15541
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#9864-2
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2018/01/30   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/19   Fujitsu         K.Ishizuka      Update          S21_NA#24459
 * 2018/08/03   Fujitsu         K.Ishizuka      Update          S21_NA#27040
 * 2018/08/28   Fujitsu         H.Kumagai       Update          QC#26329
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * </pre>
 */
public class NWAL1500CommonLogicForLineControl {

    public static boolean addLine(NWAL1500_BCMsgArray lineAllList, NWAL1500_BCMsg line) {

        int insertPosition = 0;
        int result = 0;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {

            result = compareToLine(lineAllList.no(i), line);

            if (result == 0) {
                // same line
                insertPosition = i;
                break;
            }

            if (result < 0) {
                // insert line
                insertPosition = i;
                break;
            }
            insertPosition = i + 1;
        }

        EZDMsg backLine = insertNewLine(lineAllList, insertPosition);
        if (backLine == null) {
            return false;
        }
        EZDMsg.copy(line, null, backLine, null);

        // renumbering line number
        if (result < -1) {
            for (int i = insertPosition + 1; i < lineAllList.getValidCount(); i++) {
                NWAL1500_BCMsg nextLine = lineAllList.no(i);
                if (Integer.parseInt(nextLine.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(line.dsOrdPosnNum_LL.getValue())) {
                    switch (result) {
                        case -2: // line level
                            nextLine.dsCpoLineNum_LL.setValue(nextLine.dsCpoLineNum_LL.getValueInt() + 1);
                            ZYPEZDItemValueSetter.setValue(nextLine.xxLineNum_LL, editDisplayLineNum(nextLine));
                            break;
                        case -3: // line sub level
                            if (nextLine.dsCpoLineNum_LL.getValueInt() == line.dsCpoLineNum_LL.getValueInt()) {
                                nextLine.dsCpoLineSubNum_LL.setValue(nextLine.dsCpoLineSubNum_LL.getValueInt() + 1);
                                ZYPEZDItemValueSetter.setValue(nextLine.xxLineNum_LL, editDisplayLineNum(nextLine));
                            }
                            break;
                    }
                }
            }
        }
        return true;
    }

    public static boolean addLine(NWAL1500_DCMsgArray lineAllList, NWAL1500_DCMsg line) {

        int insertPosition = 0;
        int result = 0;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {

            result = compareToLine(lineAllList.no(i), line);

            if (result == 0) {
                // same line
                insertPosition = i;
                break;
            }

            if (result < 0) {
                // insert line
                insertPosition = i;
                break;
            }
            insertPosition = i + 1;
        }

        EZDMsg backLine = insertNewLine(lineAllList, insertPosition);
        if (backLine == null) {
            return false;
        }
        EZDMsg.copy(line, null, backLine, null);

        // renumbering line number
        if (result < -1) {
            for (int i = insertPosition + 1; i < lineAllList.getValidCount(); i++) {
                NWAL1500_DCMsg nextLine = lineAllList.no(i);
                if (Integer.parseInt(nextLine.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(line.dsOrdPosnNum_RL.getValue())) {
                    switch (result) {
                        case -2: // line level
                            nextLine.dsCpoLineNum_RL.setValue(nextLine.dsCpoLineNum_RL.getValueInt() + 1);
                            ZYPEZDItemValueSetter.setValue(nextLine.xxLineNum_RL, editDisplayLineNum(nextLine));
                            break;
                        case -3: // line sub level
                            if (nextLine.dsCpoLineNum_RL.getValueInt() == line.dsCpoLineNum_RL.getValueInt()) {
                                nextLine.dsCpoLineSubNum_RL.setValue(nextLine.dsCpoLineSubNum_RL.getValueInt() + 1);
                                ZYPEZDItemValueSetter.setValue(nextLine.xxLineNum_RL, editDisplayLineNum(nextLine));
                            }
                            break;
                    }
                }
            }
        }
        return true;
    }

    public static int compareToLine(NWAL1500_BCMsg source, NWAL1500_BCMsg target) {

        if (Integer.parseInt(source.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(target.dsOrdPosnNum_LL.getValue())) {
            if (source.dsCpoLineNum_LL.getValueInt() == target.dsCpoLineNum_LL.getValueInt()) {
                if (source.dsCpoLineSubNum_LL.getValueInt() == target.dsCpoLineSubNum_LL.getValueInt()) {
                    return 0; // equal line
                } else if (source.dsCpoLineSubNum_LL.getValueInt() > target.dsCpoLineSubNum_LL.getValueInt()) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (source.dsCpoLineNum_LL.getValueInt() > target.dsCpoLineNum_LL.getValueInt()) {
                return -2; // line level
            } else {
                return 2; // line level
            }
        } else if (Integer.parseInt(source.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(target.dsOrdPosnNum_LL.getValue())) {
            return -1; // position level
        } else {
            return 1; // position level
        }
    }

    public static int compareToLine(NWAL1500_BSMsg source, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) { // 2018/01/30 S21_NA#19808 Mod

        if (Integer.parseInt(source.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
            if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineNum_LL.getValue(), dsCpoLineNum) == 0) {
                if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_LL.getValue(), dsCpoLineSubNum) == 0) {
                    return 0; // equal line
                } else if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_LL.getValue(), dsCpoLineSubNum) > 0) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineNum_LL.getValue(), dsCpoLineNum) > 0) {
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

    private static int compareToLine(NWAL1500_DCMsg source, NWAL1500_DCMsg target) {

        if (Integer.parseInt(source.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(target.dsOrdPosnNum_RL.getValue())) {
            if (source.dsCpoLineNum_RL.getValueInt() == target.dsCpoLineNum_RL.getValueInt()) {
                if (source.dsCpoLineSubNum_RL.getValueInt() == target.dsCpoLineSubNum_RL.getValueInt()) {
                    return 0; // equal line
                } else if (source.dsCpoLineSubNum_RL.getValueInt() > target.dsCpoLineSubNum_RL.getValueInt()) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (source.dsCpoLineNum_RL.getValueInt() > target.dsCpoLineNum_RL.getValueInt()) {
                return -2; // line level
            } else {
                return 2; // line level
            }
        } else if (Integer.parseInt(source.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(target.dsOrdPosnNum_RL.getValue())) {
            return -1; // position level
        } else {
            return 1; // position level
        }
    }

    private static int compareToLine(NWAL1500_DSMsg source, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) { // 2018/01/30 S21_NA#19808 Mod

        if (Integer.parseInt(source.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
            if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineNum_RL.getValue(), dsCpoLineNum) == 0) {
                if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_RL.getValue(), dsCpoLineSubNum) == 0) {
                    return 0; // equal line
                } else if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineSubNum_RL.getValue(), dsCpoLineSubNum) > 0) {
                    return -3; // line sub level
                } else {
                    return 3; // line sub level
                }
            } else if (NWAL1500CommonLogic.compareBigDecimal(source.dsCpoLineNum_RL.getValue(), dsCpoLineNum) > 0) {
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

    public static void deleteEmptyLine(NWAL1500_BCMsgArray lineList, String dsOrdPosnNum) {

        int lineCount = 0;
        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BCMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                break;
            }
            if (S21StringUtil.isEquals(dsOrdPosnNum, lineList.no(i).dsOrdPosnNum_LL.getValue())) {
                if (isEmptyLine(lineList.no(i))) {
                    deleteLine.add(i);
                }
                lineCount++;
            }
        }
        if (lineCount > 0 && lineCount == deleteLine.size()) {
            ZYPTableUtil.deleteRows(lineList, deleteLine);
        }
        return;
    }

    public static void deleteEmptyLine(NWAL1500_DCMsgArray lineList, String dsOrdPosnNum) {

        int lineCount = 0;
        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DCMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                break;
            }
            if (S21StringUtil.isEquals(dsOrdPosnNum, lineList.no(i).dsOrdPosnNum_RL.getValue())) {
                if (isEmptyLine(lineList.no(i))) {
                    deleteLine.add(i);
                }
                lineCount++;
            }
        }
        if (lineCount > 0 && lineCount == deleteLine.size()) {
            ZYPTableUtil.deleteRows(lineList, deleteLine);
        }
        return;
    }

    public static boolean deleteLine(NWAL1500_BCMsgArray lineAllList, NWAL1500_BCMsg line) {

        int deleteIndex = -1;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {

            int result = compareToLine(lineAllList.no(i), line);
            if (result == 0) {
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex < 0) {
            return false;
        }
        return true;
    }

    public static String editDisplayLineNum(NWAL1500_BCMsg line) {

        return NWXC150001DsCheck.editDtlLineNum(line.dsOrdPosnNum_LL.getValue(), line.dsCpoLineNum_LL.getValue(), line.dsCpoLineSubNum_LL.getValue());
    }

    public static String editDisplayLineNum(NWAL1500_DCMsg line) {

        return NWXC150001DsCheck.editDtlLineNum(line.dsOrdPosnNum_RL.getValue(), line.dsCpoLineNum_RL.getValue(), line.dsCpoLineSubNum_RL.getValue());
    }
    // 2018/01/30 S21_NA#19808 Add Start
    public static String editDisplayLineNum(NWAL1500_BSMsg line) {

        return NWXC150001DsCheck.editDtlLineNum(line.dsOrdPosnNum_LL.getValue(), line.dsCpoLineNum_LL.getValue(), line.dsCpoLineSubNum_LL.getValue());
    }

    public static String editDisplayLineNum(NWAL1500_DSMsg line) {

        return NWXC150001DsCheck.editDtlLineNum(line.dsOrdPosnNum_RL.getValue(), line.dsCpoLineNum_RL.getValue(), line.dsCpoLineSubNum_RL.getValue());
    }
    // 2018/01/30 S21_NA#19808 Add End

    public static int getAddLineRow(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        int maxLineRow = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i); // 2018/01/30 S21_NA#19808 Mod
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                return i;
            }
            maxLineRow = i + 1;
        }
        return maxLineRow;
    }

    public static int getAddLineRow(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        int maxLineRow = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i); // 2018/01/30 S21_NA#19808 Mod
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                return i;
            }
            maxLineRow = i + 1;
        }
        return maxLineRow;
    }

//    public static int getAddLineRow(NWAL1500_JSMsgArray lineList, String dsOrdPosnNum) {
//
//        int maxLineRow = 0;
//
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            NWAL1500_JSMsg line = lineList.no(i);
//            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
//                return i;
//            }
//            maxLineRow = i + 1;
//        }
//        return maxLineRow;
//    }
//
//    public static int getAddLineRow(NWAL1500_KSMsgArray lineList, String dsOrdPosnNum) {
//
//        int maxLineRow = 0;
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            NWAL1500_KSMsg line = lineList.no(i);
//            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
//                return i;
//            }
//            maxLineRow = i + 1;
//        }
//        return maxLineRow;
//    }
//
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getChildMdse(NWAL1500CMsg bizMsg, String parentMdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prntMdseCd", parentMdseCd);
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        S21SsmEZDResult ssmResult = NWAL1500QueryForConfigId.getInstance().getChildMdseList(queryParam); // TODO:LineControl
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    public static int getConfigIndex(NWAL1500_ASMsgArray configList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_LC.getValue())) {
                return i;
            }
        }
        return 0;
    }

    public static int getConfigIndex(NWAL1500_CSMsgArray configList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_RC.getValue())) {
                return i;
            }
        }
        return 0;
    }

    public static List<NWAL1500_BSMsg> getLineList(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        List<NWAL1500_BSMsg> list = new ArrayList<NWAL1500_BSMsg>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                list.add(lineList.no(i));
            }
        }
        return list;
    }

    public static List<NWAL1500_DSMsg> getLineList(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        List<NWAL1500_DSMsg> list = new ArrayList<NWAL1500_DSMsg>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                list.add(lineList.no(i));
            }
        }
        return list;
    }

    public static List<Integer> getLineListIndex(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_LL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                list.add(i);
            }
        }
        return list;
    }

    public static List<Integer> getLineListIndex(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            String configNum = lineList.no(i).dsOrdPosnNum_RL.getValue();
            if (configNum.equals(dsOrdPosnNum)) {
                list.add(i);
            }
        }
        return list;
    }

    public static int getLineRow(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (compareToLine(line, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int getLineRow(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if (compareToLine(line, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int getMaxLineNum(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i); // 2018/01/30 S21_NA#19808 Mod
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_LL.getValueInt();
            }
        }
        return maxLineNum;
    }

    public static int getMaxLineNum(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);  // 2018/01/30 S21_NA#19808 Mod
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_RL.getValueInt();
            }
        }
        return maxLineNum;
    }

//    public static int getMaxLineNum(NWAL1500_JSMsgArray lineList, String dsOrdPosnNum) {
//
//        int maxLineNum = 0;
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            NWAL1500_JSMsg line = lineList.no(i);
//            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                maxLineNum = line.dsCpoLineNum_LL.getValueInt();
//            }
//        }
//        return maxLineNum;
//    }
//
//    public static int getMaxLineNum(NWAL1500_KSMsgArray lineList, String dsOrdPosnNum) {
//
//        int maxLineNum = 0;
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            NWAL1500_KSMsg line = lineList.no(i);
//            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                maxLineNum = line.dsCpoLineNum_RL.getValueInt();
//            }
//        }
//        return maxLineNum;
//    }

    /**
     * insert new row
     * @param list
     * @param insertRow
     * @return new line
     */
    public static EZDMsg insertNewLine(EZDMsgArray list, int insertRow) {

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

    private static boolean isEmptyLine(NWAL1500_BCMsg line) {

        return !ZYPCommonFunc.hasValue(line.mdseCd_LL);
    }

    public static boolean isEmptyLine(NWAL1500_DCMsg line) {

        return !ZYPCommonFunc.hasValue(line.mdseCd_RL);
    }

    public static boolean prepareLineS2C(NWAL1500_BCMsgArray lineList, NWAL1500_BCMsgArray lineAllList, String dsOrdPosnNum) {

        // delete CMsg
        List<Integer> deleteList = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
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
            NWAL1500_BCMsg sourceLine = lineAllList.no(lineIndex);
            if (Integer.parseInt(sourceLine.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                NWAL1500_BCMsg line = (NWAL1500_BCMsg) insertNewLine(lineList, insertPosition++);
                if (line == null) {
                    return false;
                }
                EZDMsg.copy(sourceLine, null, line, null);
            }
        }
        return true;
    }

    public static boolean prepareLineS2C(NWAL1500_DCMsgArray lineList, NWAL1500_DCMsgArray lineAllList, String dsOrdPosnNum) {

        // delete CMsg
        List<Integer> deleteList = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
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
            NWAL1500_DCMsg sourceLine = lineAllList.no(lineIndex);
            if (Integer.parseInt(sourceLine.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                NWAL1500_DCMsg line = (NWAL1500_DCMsg) insertNewLine(lineList, insertPosition++);
                if (line == null) {
                    return false;
                }
                EZDMsg.copy(sourceLine, null, line, null);
            }
        }
        return true;
    }

    /**
     * <pre>
     * Adding Set Children Lines.
     * @param bizMsg Business Message.
     * @param glblMsg Global Message.
     * @param lineAllList Array of Line Config Details.
     * @param lineIndex Set Parent line Index of Business Message.
     * @return inserted row numbers.
     */
    public static int setChildLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsgArray lineAllList, int lineIndex) { // 2018/01/30 S21_NA#19808 Mod

        // for set merchandise
        // add new row
        NWAL1500_BSMsg line = lineAllList.no(lineIndex); // 2018/01/30 S21_NA#19808 Mod
        String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
        BigDecimal dsCpoLineNum = line.dsCpoLineNum_LL.getValue();
        int insertRow = lineIndex + 1;
        int insertCount = 0;

        // delete component
        List<Integer> deleteChild = new ArrayList<Integer>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/30 S21_NA#19808 Mod
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) // 2018/01/30 S21_NA#19808 Mod
                    && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0 // 2018/01/30 S21_NA#19808 Mod
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsCpoLineSubNum_LL)) { // 2018/01/30 S21_NA#19808 Mod
                deleteChild.add(i);
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.B, deleteChild); // 2018/01/30 S21_NA#19808 Mod

        // get merchandise type
        MDSETMsg mdse = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue());
        if (mdse != null && S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {

            List<Map<String, Object>> childMdseList = getChildMdse(bizMsg, mdse.mdseCd.getValue());
            // 2016/11/11 S21_NA#9864-2 Add Start
            if (!isAllowSetOnLoanOrder(bizMsg, childMdseList)){
                return -1;
            }
            // 2016/11/11 S21_NA#9864-2 Add End
            int lineSubNum = 1;
            for (Map<String, Object> childMdse : childMdseList) {

                NWAL1500_BSMsg newChildLine = (NWAL1500_BSMsg) insertNewLine(glblMsg.B, insertRow); // 2018/01/30 S21_NA#19808 Mod
                if (newChildLine == null) {
                    return 0;
                }
                BigDecimal baseQty = line.ordCustUomQty_LL.getValue();
                BigDecimal baseEachQty = line.ordQty_LL.getValue();
                BigDecimal baseChildQty = BigDecimal.ONE;
                // set up child line
                if (!setUpChihldLine(line, newChildLine, bizMsg, glblMsg, childMdse, baseQty, baseEachQty, baseChildQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
                    return 0;
                }
                insertCount++;

                // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, newChildLine); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del

                MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
                if (childMdseTMsg != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
                    baseChildQty = newChildLine.childMdseQty_LL.getValue();
                    List<Map<String, Object>> grandChildMdseList = getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
                    if (grandChildMdseList.size() > 0) {
                        insertRow--;
                        lineSubNum--;
                        insertCount--;
                        List<Integer> deleteList = new ArrayList<Integer>();
                        deleteList.add(insertRow);
                        ZYPTableUtil.deleteRows(lineAllList, deleteList);
                    }
                    for (Map<String, Object> grandChildMdse : grandChildMdseList) {
                        NWAL1500_BSMsg newGrandChildLine = (NWAL1500_BSMsg) insertNewLine(lineAllList, insertRow); // 2018/01/30 S21_NA#19808 Mod
                        if (newGrandChildLine == null) {
                            return 0;
                        }

                        // set up grand child line
                        if (!setUpChihldLine(line, newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseQty, baseEachQty, baseChildQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
                            return 0;
                        }
                        insertCount++;
                        // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, newGrandChildLine); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
                    }
                }
            }
        }// else { // 2016/03/15 S21_NA#4691 Add Start -> 2016/10/05 S21_NA#7645-3 Del
            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineAllList.no(lineIndex)); -> 2016/10/05 S21_NA#7645-3 Del
        //} // 2016/03/15 S21_NA#4691 Add End -> 2016/10/05 S21_NA#7645-3 Del

        // 2018/01/30 S21_NA#19808 Add Start
        if (insertCount > 0) {
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, line, lineIndex);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        }
        // 2018/01/30 S21_NA#19808 Add End

        return insertCount;
    }

    // RMA
    public static int setChildLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsgArray lineAllList, int lineIndex) { // 2018/01/30 S21_NA#19808 Mod

        // for set merchandise
        // add new row
        NWAL1500_DSMsg line = lineAllList.no(lineIndex); // 2018/01/30 S21_NA#19808 Mod
        String dsOrdPosnNum = line.dsOrdPosnNum_RL.getValue();
        BigDecimal dsCpoLineNum = line.dsCpoLineNum_RL.getValue();
        int insertRow = lineIndex + 1;
        int insertCount = 0;

        // delete component
        List<Integer> deleteChild = new ArrayList<Integer>();
        for (int i = 0; i < lineAllList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, lineAllList.no(i).dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, lineAllList.no(i).dsCpoLineNum_RL.getValue()) == 0
                    && ZYPCommonFunc.hasValue(lineAllList.no(i).dsCpoLineSubNum_RL)) {
                deleteChild.add(i);
            }
        }
        ZYPTableUtil.deleteRows(lineAllList, deleteChild);

        // get merchandise type
        MDSETMsg mdse = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_RL.getValue());
        if (mdse != null && S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {

            List<Map<String, Object>> childMdseList = getChildMdse(bizMsg, mdse.mdseCd.getValue());
            int lineSubNum = 1;
            for (Map<String, Object> childMdse : childMdseList) {

                NWAL1500_DSMsg newChildLine = (NWAL1500_DSMsg) insertNewLine(glblMsg.D, insertRow); // 2018/01/30 S21_NA#19808 Mod
                if (newChildLine == null) {
                    return 0;
                }
                BigDecimal baseQty = line.ordCustUomQty_RL.getValue();
                BigDecimal baseEachQty = line.ordQty_RL.getValue();
                BigDecimal baseChildQty = BigDecimal.ONE;

                // set up child line
                if (!setUpChihldLine(line, newChildLine, bizMsg, glblMsg, childMdse, baseQty, baseEachQty, baseChildQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
                    return 0;
                }
                insertCount++;

                MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
                if (mdse != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
                    baseChildQty = newChildLine.childMdseQty_RL.getValue();
                    List<Map<String, Object>> grandChildMdseList = getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
                    if (grandChildMdseList.size() > 0) {
                        insertRow--;
                        lineSubNum--;
                        insertCount--;
                        List<Integer> deleteList = new ArrayList<Integer>();
                        deleteList.add(insertRow);
                        ZYPTableUtil.deleteRows(lineAllList, deleteList);
                    }
                    for (Map<String, Object> grandChildMdse : grandChildMdseList) {
                        NWAL1500_DSMsg newGrandChildLine = (NWAL1500_DSMsg) insertNewLine(glblMsg.D, insertRow); // 2018/01/30 S21_NA#19808 Mod
                        if (newGrandChildLine == null) {
                            return 0;
                        }

                        // set up grand child line
                        if (!setUpChihldLine(line, newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseQty, baseEachQty, baseChildQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
                            return 0;
                        }
                        insertCount++;
                    }
                }
            }
        }

        // 2018/01/30 S21_NA#19808 Add Start
        if (insertCount > 0) {
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, line, lineIndex);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
        // 2018/01/30 S21_NA#19808 Add End

        return insertCount;
    }

    @SuppressWarnings("unchecked")
    private static boolean setUpChihldLine(NWAL1500_BSMsg parentLine // 2018/01/30 S21_NA#19808 Mod
            , NWAL1500_BSMsg newLine // 2018/01/30 S21_NA#19808 Mod
            , NWAL1500CMsg bizMsg //
            , NWAL1500SMsg glblMsg //
            , Map<String, Object> childMdse //
            , BigDecimal baseQty //
            , BigDecimal baseEachQty //
            , BigDecimal baseChildQty //
            , String dsOrdPosnNum //
            , int lineNum //
            , int lineSubNum //
            , int slctLineIndex) {

        NWAL1500_BCMsg workLine = new NWAL1500_BCMsg();

        ZYPEZDItemValueSetter.setValue(workLine.dsOrdPosnNum_LL, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(workLine.dsCpoLineNum_LL, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(workLine.dsCpoLineSubNum_LL, new BigDecimal(lineSubNum));
        ZYPEZDItemValueSetter.setValue(workLine.xxLineNum_LL, NWAL1500CommonLogic.concatLineNum(workLine));
        if (ZYPCommonFunc.hasValue(parentLine.cpoDtlLineNum_LL)) {
            ZYPEZDItemValueSetter.setValue(workLine.cpoDtlLineNum_LL, parentLine.cpoDtlLineNum_LL);
            ZYPEZDItemValueSetter.setValue(workLine.cpoDtlLineSubNum_LL, String.format("%03d", lineSubNum));
        }

        ZYPEZDItemValueSetter.setValue(workLine.mdseCd_LL, (String) childMdse.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.childMdseQty_LL, baseChildQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
        if (baseEachQty != null && baseQty != null) {
            ZYPEZDItemValueSetter.setValue(workLine.ordCustUomQty_LL, baseQty.multiply(workLine.childMdseQty_LL.getValue()));
            ZYPEZDItemValueSetter.setValue(workLine.ordQty_LL, baseEachQty.multiply(workLine.childMdseQty_LL.getValue()));
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        ZYPEZDItemValueSetter.setValue(workLine.mdseNm_LL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(workLine.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, workLine);
        ZYPEZDItemValueSetter.setValue(workLine.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.prcCatgCd_LL, parentLine.prcCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(workLine.prcCatgNm_LL, parentLine.prcCatgNm_LL);
        String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(workLine.dsOrdLineCatgCd_LL, primaryLineCatgCd);
        // NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(bizMsg,
        // primaryLineCatgCd, null);
        workLine.ordLineStsDescTxt_LL.clear();

        // S21_NA#2522 remove : call default API for component.
        // // Call NWZC1800 Default WH API
        // int slctConfIndex = getConfigIndex(bizMsg.A, dsOrdPosnNum);
        // NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);

        // 2015/11/20 S21_NA#934 Add isMdseTangible
        // if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) &&
        // NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(),
        // workLine.mdseCd_LL.getValue())) {
        // NWZC180001PMsg pMsg = new NWZC180001PMsg();
        // if (NWAL1500CommonLogic.callDefWhApiForLineConf(bizMsg,
        // pMsg, workLine.dsOrdPosnNum_LL.getValue(),
        // workLine.mdseCd_LL.getValue(),
        // workLine.ordQty_LL.getValue())) {
        // String rtlWhCd = pMsg.rtlWhCd.getValue();
        // String rtlSwhCd = pMsg.rtlSwhCd.getValue();
        //
        // ZYPEZDItemValueSetter.setValue(workLine.rtlWhCd_LL,
        // rtlWhCd);
        // ZYPEZDItemValueSetter.setValue(workLine.rtlWhNm_LL,
        // NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
        // ZYPEZDItemValueSetter.setValue(workLine.rtlSwhCd_LL,
        // rtlSwhCd);
        // ZYPEZDItemValueSetter.setValue(workLine.rtlSwhNm_LL,
        // NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd,
        // rtlSwhCd));
        // ZYPEZDItemValueSetter.setValue(workLine.ordLineSrcCd_LL,
        // pMsg.ordLineSrcCd);
        // }
        // }
        // if (!ZYPCommonFunc.hasValue(workLine.rtlWhCd_LL) ||
        // !ZYPCommonFunc.hasValue(workLine.rtlSwhCd_LL)) {
        ZYPEZDItemValueSetter.setValue(workLine.rtlWhCd_LL, parentLine.rtlWhCd_LL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlWhNm_LL, parentLine.rtlWhNm_LL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlSwhCd_LL, parentLine.rtlSwhCd_LL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlSwhNm_LL, parentLine.rtlSwhNm_LL);
        ZYPEZDItemValueSetter.setValue(workLine.ordLineSrcCd_LL, parentLine.ordLineSrcCd_LL);
        // }
        workLine.serNum_LL.clear();
        ZYPEZDItemValueSetter.setValue(workLine.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
        workLine.sbstMdseCd_LL.clear();
        workLine.vndInvtyLocCd_LL.clear();
        ZYPEZDItemValueSetter.setValue(workLine.flPrcListCd_LL, parentLine.flPrcListCd_LL);
        ZYPEZDItemValueSetter.setValue(workLine.flPrcListNm_LL, parentLine.flPrcListNm_LL);
        ZYPEZDItemValueSetter.setValue(workLine.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.funcUnitFlPrcAmt_LL, BigDecimal.ZERO); // QC#22372 2018/01/10 Add
        ZYPEZDItemValueSetter.setValue(workLine.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workLine.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
        workLine.dplyLineRefNum_LL.clear();
        workLine.crRebilCd_LL.clear();
        // START 2016/08/01 [S21_NA#12599,ADD]
        workLine.crRebilDescTxt_LL.clear();
        // END   2016/08/01 [S21_NA#12599,ADD]
        ZYPEZDItemValueSetter.setValue(workLine.prcBaseDt_LL, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(workLine.rddDt_LL, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(workLine.allocQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.shipQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.istlQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.invQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.cancQty_LL, BigDecimal.ZERO);

        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500_BCMsg parentLineBizMsg = new NWAL1500_BCMsg();
        EZDMsg.copy(parentLine, null, parentLineBizMsg, null);
        // 2018/01/30 S21_NA#19808 Add End
        copyCsmpDataFromPrntToChild(parentLineBizMsg, workLine); // 2016/08/26 S21_NA#9806 Add // 2018/01/30 S21_NA#19808 Mod
        EZDMsg.copy(workLine, null, newLine, null);

        return true;
    }
    @SuppressWarnings("unchecked")
    private static boolean setUpChihldLine(NWAL1500_DSMsg parentLine, NWAL1500_DSMsg newLine, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, Object> childMdse, BigDecimal baseQty, BigDecimal baseEachQty, BigDecimal baseChildQty, String dsOrdPosnNum, int lineNum, int lineSubNum, int slctLineIndex) { // 2018/01/30 S21_NA#19808 Mod

        NWAL1500_DCMsg workLine = new NWAL1500_DCMsg();

        ZYPEZDItemValueSetter.setValue(workLine.dsOrdPosnNum_RL, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(workLine.dsCpoLineNum_RL, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(workLine.dsCpoLineSubNum_RL, new BigDecimal(lineSubNum));
        ZYPEZDItemValueSetter.setValue(workLine.xxLineNum_RL, NWAL1500CommonLogic.concatLineNum(workLine));

        if (ZYPCommonFunc.hasValue(parentLine.cpoDtlLineNum_RL)) {
            ZYPEZDItemValueSetter.setValue(workLine.cpoDtlLineNum_RL, parentLine.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(workLine.cpoDtlLineSubNum_RL, String.format("%03d", lineSubNum));
        }

        // ZYPEZDItemValueSetter.setValue(workLine.mdseCd_RL, (String)
        // childMdse.get("MDSE_CD")); // convert merchandise code 8
        // digits to 10 digits if return.
        ZYPEZDItemValueSetter.setValue(workLine.childMdseQty_RL, baseChildQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
        if (baseEachQty != null && baseQty != null) {
            ZYPEZDItemValueSetter.setValue(workLine.ordCustUomQty_RL, baseQty.multiply(workLine.childMdseQty_RL.getValue()));
            ZYPEZDItemValueSetter.setValue(workLine.ordQty_RL, baseEachQty.multiply(workLine.childMdseQty_RL.getValue()));
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        // convert merchandise code 8 digits to 10 digits if return.
        ZYPEZDItemValueSetter.setValue(workLine.mdseCd_RL, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(workLine.mdseNm_RL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(workLine.mdseDescShortTxt_RL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, workLine);
        ZYPEZDItemValueSetter.setValue(workLine.custUomCd_RL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.entCpoDtlDealSlsAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.prcCatgCd_RL, parentLine.prcCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(workLine.prcCatgNm_RL, parentLine.prcCatgNm_RL);
        String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, bizMsg.slsDt.getValue()); // 2018/01/30 S21_NA#19808 Mod
        ZYPEZDItemValueSetter.setValue(workLine.dsOrdLineCatgCd_RL, primaryLineCatgCd);
        // 2018/01/30 S21_NA#19808 Add Start
        for (int i = 0; i < glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.length(); i++) {
            ZYPEZDItemValueSetter.setValue(workLine.dsOrdLineCatgCd_CR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.no(i));
            ZYPEZDItemValueSetter.setValue(workLine.dsOrdLineCatgDescTxt_NR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgDescTxt_NR.no(i));
        }
        ZYPEZDItemValueSetter.setValue(workLine.dsOrdLineCatgCd_RL, primaryLineCatgCd);
        // 2018/01/30 S21_NA#19808 Add End
        // NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(bizMsg,
        // primaryLineCatgCd, null);
        workLine.rtrnLineStsDescTxt_RL.clear();

        // S21_NA#2522 remove : call default API for components.
        // // Call NWZC1800 Default WH API
        // int slctConfIndex = getConfigIndex(bizMsg.C, dsOrdPosnNum);
        // NWAL1500_CCMsg confMsg = bizMsg.C.no(slctConfIndex);
        //
        // // 2015/11/20 S21_NA#934 Add isMdseTangible
        // if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_RC) &&
        // NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(),
        // workLine.mdseCd_RL.getValue())) {
        // NWZC180001PMsg pMsg = new NWZC180001PMsg();
        // if (NWAL1500CommonLogic.callDefWhApiForRma(bizMsg, pMsg,
        // workLine)) {
        // String rtlWhCd = pMsg.rtlWhCd.getValue();
        // String rtlSwhCd = pMsg.rtlSwhCd.getValue();
        //
        // ZYPEZDItemValueSetter.setValue(workLine.rtlWhCd_RL,
        // rtlWhCd);
        // ZYPEZDItemValueSetter.setValue(workLine.rtlWhNm_RL,
        // NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
        // ZYPEZDItemValueSetter.setValue(workLine.rtlSwhCd_RL,
        // rtlSwhCd);
        // ZYPEZDItemValueSetter.setValue(workLine.rtlSwhNm_RL,
        // NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd,
        // rtlSwhCd));
        // // ZYPEZDItemValueSetter.setValue(workLine.ordLineSrcCd_RL,
        // // pMsg.ordLineSrcCd);
        // }
        // }
        // if (!ZYPCommonFunc.hasValue(workLine.rtlWhCd_RL) ||
        // !ZYPCommonFunc.hasValue(workLine.rtlSwhCd_RL)) {
        ZYPEZDItemValueSetter.setValue(workLine.rtlWhCd_RL, parentLine.rtlWhCd_RL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlWhNm_RL, parentLine.rtlWhNm_RL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlSwhCd_RL, parentLine.rtlSwhCd_RL);
        ZYPEZDItemValueSetter.setValue(workLine.rtlSwhNm_RL, parentLine.rtlSwhNm_RL);
        // }
        // ZYPEZDItemValueSetter.setValue(workLine.ordLineSrcCd_RL,
        // parentLine.ordLineSrcCd_RL);
        workLine.serNum_RL.clear();
        // ZYPEZDItemValueSetter.setValue(workLine.supdLockFlg_RL,
        // ZYPConstant.FLG_OFF_N);
        // workLine.sbstMdseCd_RL.clear();
        // workLine.vndInvtyLocCd_RL.clear();
        ZYPEZDItemValueSetter.setValue(workLine.flPrcListCd_RL, parentLine.flPrcListCd_RL);
        // ZYPEZDItemValueSetter.setValue(workLine.flPrcListDescTxt_RL,
        // parentLine.flPrcListDescTxt);
        ZYPEZDItemValueSetter.setValue(workLine.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.funcUnitFlPrcAmt_RL, BigDecimal.ZERO); // QC#22372 2018/01/10 Add
        ZYPEZDItemValueSetter.setValue(workLine.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.coaMdseTpCd_RL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.coaMdseTpDescTxt_RL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workLine.coaProdCd_RL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(workLine.coaProdDescTxt_RL, mdseInfo.get("COA_PROD_DESC_TXT"));
        workLine.dplyLineRefNum_RL.clear();
        ZYPEZDItemValueSetter.setValue(workLine.prcBaseDt_RL, bizMsg.slsDt);
        // ZYPEZDItemValueSetter.setValue(workLine.rddDt_RL,
        // bizMsg.slsDt);
        // ZYPEZDItemValueSetter.setValue(workLine.allocQty_RL,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(workLine.shipQty_RL,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(workLine.istlQty_RL,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(workLine.invQty_RL,
        // BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workLine.cancQty_RL, BigDecimal.ZERO);

        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500_DCMsg parentLineBizMsg = new NWAL1500_DCMsg();
        EZDMsg.copy(parentLine, null, parentLineBizMsg, null);
        // 2018/01/30 S21_NA#19808 Add End
        copyCsmpDataFromPrntToChild(parentLineBizMsg, workLine); // 2016/08/26 S21_NA#9806 Add // 2018/01/30 S21_NA#19808 Mod
        EZDMsg.copy(workLine, null, newLine, null);

        return true;
    }

    public static boolean storeAllLine(NWAL1500_BCMsgArray targetList, NWAL1500_BCMsgArray sourceList) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            storeLine(targetList, sourceList.no(i));
        }
        return true;
    }

    public static boolean storeAllLine(NWAL1500_DCMsgArray targetList, NWAL1500_DCMsgArray sourceList) {

        for (int i = 0; i < sourceList.getValidCount(); i++) {
            storeLine(targetList, sourceList.no(i));
        }
        return true;
    }

    public static boolean storeLine(NWAL1500_BCMsgArray lineAllList, NWAL1500_BCMsg line) {

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

    public static boolean storeLine(NWAL1500_DCMsgArray lineAllList, NWAL1500_DCMsg line) {

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

//    public static boolean storeLineByConfig(NWAL1500_JSMsgArray targetList, NWAL1500_BCMsgArray sourceList, String dsOrdPosnNum) {
//
//        for (int i = 0; i < sourceList.getValidCount(); i++) {
//            NWAL1500_BCMsg line = sourceList.no(i);
//            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
//                storeLine(targetList, line);
//            }
//        }
//        return true;
//    }
//
//    public static boolean storeLineByConfig(NWAL1500_KSMsgArray targetList, NWAL1500_DCMsgArray sourceList, String dsOrdPosnNum) {
//
//        for (int i = 0; i < sourceList.getValidCount(); i++) {
//            NWAL1500_DCMsg line = sourceList.no(i);
//            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
//                storeLine(targetList, line);
//            }
//        }
//        return true;
//    }

    public static void setChildUpdate(NWAL1500CMsg bizMsg, NWAL1500_BSMsgArray lineList, int lineIndex) { // 2018/01/30 S21_NA#19808 Mod

        NWAL1500_BSMsg parentLine = lineList.no(lineIndex);
        BigDecimal qty = parentLine.ordCustUomQty_LL.getValue();
        BigDecimal eachQty = parentLine.ordQty_LL.getValue();
        for (int i = lineIndex + 1; i < lineList.getValidCount(); i++) {

            NWAL1500_BSMsg childLine = lineList.no(i);

            if (!ZYPCommonFunc.hasValue(childLine.dsCpoLineSubNum_LL)) {
                return;
            }
            BigDecimal childQty = childLine.childMdseQty_LL.getValue();
            if (qty != null && eachQty != null && childQty != null) {
                ZYPEZDItemValueSetter.setValue(childLine.ordCustUomQty_LL, qty.multiply(childQty));
                ZYPEZDItemValueSetter.setValue(childLine.ordQty_LL, eachQty.multiply(childQty));
            } else {
                childLine.ordCustUomQty_LL.clear();
                childLine.ordQty_LL.clear();
            }
            ZYPEZDItemValueSetter.setValue(childLine.prcCatgCd_LL, parentLine.prcCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(childLine.prcCatgNm_LL, parentLine.prcCatgNm_LL);
            ZYPEZDItemValueSetter.setValue(childLine.rtlWhCd_LL, parentLine.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(childLine.rtlWhNm_LL, parentLine.rtlWhNm_LL);
            ZYPEZDItemValueSetter.setValue(childLine.rtlSwhCd_LL, parentLine.rtlSwhCd_LL);
            ZYPEZDItemValueSetter.setValue(childLine.rtlSwhNm_LL, parentLine.rtlSwhNm_LL);
            ZYPEZDItemValueSetter.setValue(childLine.ordLineSrcCd_LL, parentLine.ordLineSrcCd_LL);
            ZYPEZDItemValueSetter.setValue(childLine.flPrcListCd_LL, parentLine.flPrcListCd_LL);
            ZYPEZDItemValueSetter.setValue(childLine.flPrcListNm_LL, parentLine.flPrcListNm_LL);
            ZYPEZDItemValueSetter.setValue(childLine.prcBaseDt_LL, parentLine.prcBaseDt_LL);
            ZYPEZDItemValueSetter.setValue(childLine.rddDt_LL, parentLine.rddDt_LL);
            ZYPEZDItemValueSetter.setValue(childLine.dsOrdLineCatgCd_LL, parentLine.dsOrdLineCatgCd_LL); // 2018/03/19 S21_NA#24459 Add

            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, childLine); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
            // 2016/10/31 S21_NA#15541 Add Start
            if (eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, childLine.mdseCd_LL.getValue(), eachQty.intValue())) {
                // lineList.no(lineIndex).mdseCd_LL.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
                lineList.no(lineIndex).ordCustUomQty_LL.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
            }
            // 2016/10/31 S21_NA#15541 Add Start
        }
    }

    public static void setChildUpdate(NWAL1500CMsg bizMsg, NWAL1500_DCMsgArray lineList, int lineIndex) { // 2018/01/30 S21_NA#19808 Change InterfAce

        NWAL1500_DCMsg parentLine = lineList.no(lineIndex);
        BigDecimal qty = parentLine.ordCustUomQty_RL.getValue();
        BigDecimal eachQty = parentLine.ordQty_RL.getValue();
        for (int i = lineIndex + 1; i < lineList.getValidCount(); i++) {

            NWAL1500_DCMsg childLine = lineList.no(i);

            if (!ZYPCommonFunc.hasValue(childLine.dsCpoLineSubNum_RL)) {
                return;
            }
            if (S21StringUtil.isEquals(parentLine.dsOrdPosnNum_RL.getValue(), childLine.dsOrdPosnNum_RL.getValue()) //
                    && NWAL1500CommonLogic.compareBigDecimal(parentLine.dsCpoLineNum_RL.getValue(), childLine.dsCpoLineNum_RL.getValue()) == 0) {
                BigDecimal childQty = childLine.childMdseQty_RL.getValue();
                if (qty != null && eachQty != null && childQty != null) {
                    ZYPEZDItemValueSetter.setValue(childLine.ordCustUomQty_RL, qty.multiply(childQty));
                    ZYPEZDItemValueSetter.setValue(childLine.ordQty_RL, eachQty.multiply(childQty));
                } else {
                    childLine.ordCustUomQty_RL.clear();
                    childLine.ordQty_RL.clear();
                }
                ZYPEZDItemValueSetter.setValue(childLine.rtrnRsnCd_RL, parentLine.rtrnRsnCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcCatgCd_RL, parentLine.prcCatgCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcCatgNm_RL, parentLine.prcCatgNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlWhCd_RL, parentLine.rtlWhCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlWhNm_RL, parentLine.rtlWhNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlSwhCd_RL, parentLine.rtlSwhCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlSwhNm_RL, parentLine.rtlSwhNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.flPrcListCd_RL, parentLine.flPrcListCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.flPrcListNm_RL, parentLine.flPrcListNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcBaseDt_RL, parentLine.prcBaseDt_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rqstPickUpDt_RL, parentLine.rqstPickUpDt_RL);
    
                // 2016/10/31 S21_NA#15541 Add Start
                if (eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, childLine.mdseCd_RL.getValue(), eachQty.intValue())) {
                    // lineList.no(lineIndex).mdseCd_RL.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
                    lineList.no(lineIndex).ordCustUomQty_RL.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
                }
                // 2016/10/31 S21_NA#15541 Add Start
            }
        }
    }
    // 2018/01/30 S21_NA#19808 Add Start
    public static void setChildUpdate(NWAL1500CMsg bizMsg, NWAL1500_DSMsgArray lineList, int lineIndex) {

        NWAL1500_DSMsg parentLine = lineList.no(lineIndex);
        BigDecimal qty = parentLine.ordCustUomQty_RL.getValue();
        BigDecimal eachQty = parentLine.ordQty_RL.getValue();
        for (int i = lineIndex + 1; i < lineList.getValidCount(); i++) {

            NWAL1500_DSMsg childLine = lineList.no(i);

            if (!ZYPCommonFunc.hasValue(childLine.dsCpoLineSubNum_RL)) {
                return;
            }
            if (S21StringUtil.isEquals(parentLine.dsOrdPosnNum_RL.getValue(), childLine.dsOrdPosnNum_RL.getValue()) //
                    && NWAL1500CommonLogic.compareBigDecimal(parentLine.dsCpoLineNum_RL.getValue(), childLine.dsCpoLineNum_RL.getValue()) == 0) {
                BigDecimal childQty = childLine.childMdseQty_RL.getValue();
                if (qty != null && eachQty != null && childQty != null) {
                    ZYPEZDItemValueSetter.setValue(childLine.ordCustUomQty_RL, qty.multiply(childQty));
                    ZYPEZDItemValueSetter.setValue(childLine.ordQty_RL, eachQty.multiply(childQty));
                } else {
                    childLine.ordCustUomQty_RL.clear();
                    childLine.ordQty_RL.clear();
                }
                ZYPEZDItemValueSetter.setValue(childLine.rtrnRsnCd_RL, parentLine.rtrnRsnCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcCatgCd_RL, parentLine.prcCatgCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcCatgNm_RL, parentLine.prcCatgNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlWhCd_RL, parentLine.rtlWhCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlWhNm_RL, parentLine.rtlWhNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlSwhCd_RL, parentLine.rtlSwhCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rtlSwhNm_RL, parentLine.rtlSwhNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.flPrcListCd_RL, parentLine.flPrcListCd_RL);
                ZYPEZDItemValueSetter.setValue(childLine.flPrcListNm_RL, parentLine.flPrcListNm_RL);
                ZYPEZDItemValueSetter.setValue(childLine.prcBaseDt_RL, parentLine.prcBaseDt_RL);
                ZYPEZDItemValueSetter.setValue(childLine.rqstPickUpDt_RL, parentLine.rqstPickUpDt_RL);
    
                // 2016/10/31 S21_NA#15541 Add Start
                if (eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, childLine.mdseCd_RL.getValue(), eachQty.intValue())) {
                    // lineList.no(lineIndex).mdseCd_RL.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
                    lineList.no(lineIndex).ordCustUomQty_RL.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
                }
                // 2016/10/31 S21_NA#15541 Add Start
            }
        }
    }
    // 2018/01/30 S21_NA#19808 Add End

    // 2016/08/26 S21_NA#9806 Add Start
    private static void copyCsmpDataFromPrntToChild(NWAL1500_BCMsg prntLineMsg, NWAL1500_BCMsg childLineMsg) {

        ZYPEZDItemValueSetter.setValue(childLineMsg.csmpContrNum_LL, prntLineMsg.csmpContrNum_LL);
        ZYPEZDItemValueSetter.setValue(childLineMsg.dlrRefNum_LL, prntLineMsg.dlrRefNum_LL);
        ZYPEZDItemValueSetter.setValue(childLineMsg.csmpPrcListCd_LL, prntLineMsg.csmpPrcListCd_LL);
    }

    private static void copyCsmpDataFromPrntToChild(NWAL1500_DCMsg prntLineMsg, NWAL1500_DCMsg childLineMsg) {

        ZYPEZDItemValueSetter.setValue(childLineMsg.csmpContrNum_RL, prntLineMsg.csmpContrNum_RL);
        ZYPEZDItemValueSetter.setValue(childLineMsg.dlrRefNum_RL, prntLineMsg.dlrRefNum_RL);
        ZYPEZDItemValueSetter.setValue(childLineMsg.csmpPrcListCd_RL, prntLineMsg.csmpPrcListCd_RL);
    }
    // 2016/08/26 S21_NA#9806 Add End

    // 2016/11/11 S21_NA#9864-2 Add End
    private static boolean isAllowSetOnLoanOrder(NWAL1500CMsg bizMsg, List<Map<String, Object>> childMdseList) {

        boolean isLoanOrder = NWAL1500CommonLogic.isLoanOrder(bizMsg);
        if (!isLoanOrder) {
            return true;
        }
        boolean isTangible = false;
        boolean isIntangible = false;
        boolean isIbTrackable = false;
        boolean isNoIbTrackable = false;

        for (Map<String, Object> childMdseMap : childMdseList) {
            String mdseCd = (String) childMdseMap.get("MDSE_CD");
            MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
            if (mdseTMsg != null) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue())) {
                    isTangible = true;
                } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsg.invtyCtrlFlg.getValue())) {
                    isIntangible = true;
                }
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                    isIbTrackable = true;
                } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                    isNoIbTrackable = true;
                }
            }
        }
        if ((isTangible && isIbTrackable) //
                && (isIntangible || isNoIbTrackable)) {
            return false;
        } else {
            return true;
        }
    }
    // 2016/11/11 S21_NA#9864-2 Add End
    // QC#22965 2018/04/11 Add Start
    public static int getCpoLineRow(NWAL1500_BSMsgArray lineList, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if(S21StringUtil.isEquals(cpoDtlLineNum, line.cpoDtlLineNum_LL.getValue())
                    && S21StringUtil.isEquals(cpoDtlLineSubNum, line.cpoDtlLineSubNum_LL.getValue())){
                return i;
            }
        }
        return -1;
    }

    public static int getCpoLineRow(NWAL1500_DSMsgArray lineList, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
            if(S21StringUtil.isEquals(cpoDtlLineNum, line.cpoDtlLineNum_RL.getValue())
                    && S21StringUtil.isEquals(cpoDtlLineSubNum, line.cpoDtlLineSubNum_RL.getValue())){
                return i;
            }
        }
        return -1;
    }
    // QC#22965 2018/04/11 Add End
    
    // 2018/08/03 S21_NA#27040 Add Start
    public static boolean isNotProtectedFieldForLineTabByLineSts(NWAL1500_BSMsg line){
        
        if(!ZYPCommonFunc.hasValue(line.ordLineStsDescTxt_LL.getValue())){
            return true;
        }
        final String lineStsNm = line.ordLineStsDescTxt_LL.getValue();
        
        if (LINE_STS_NM_WAITING_PICK.equals(lineStsNm) || LINE_STS_NM_DELIVERED_TO_SHOP.equals(lineStsNm) // 
                || LINE_STS_NM_IN_SHOP_OR_CONFIG.equals(lineStsNm) || LINE_STS_NM_PENDING_SHIP.equals(lineStsNm) //
                || LINE_STS_NM_SHIPPED.equals(lineStsNm) || LINE_STS_NM_PENDING_DELIVERY_CONF.equals(lineStsNm) //
                || LINE_STS_NM_PENDING_INSTALL.equals(lineStsNm) || LINE_STS_NM_WAITING_RECEIPT.equals(lineStsNm) //
                || LINE_STS_NM_PENDING_INVOICE.equals(lineStsNm) || LINE_STS_NM_BILLING_HOLD.equals(lineStsNm) //
                || LINE_STS_NM_PENDING_FULFILLMENT.equals(lineStsNm) //
                || LINE_STS_NM_PENDING_DEALER_INSTALL.equals(lineStsNm)) {
            return false;
        }
        return true;
    }
    
    public static boolean isNotProtectedFieldForRmaTabByLineSts(NWAL1500_DSMsg line){
        
        if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
            return true;
        }
        final String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
        
        // 2018/08/28 QC#26329 Mod Start
        if (LINE_STS_NM_PENDING_RETURN.equals(rmaLineStsNm) || LINE_STS_NM_PARTIAL_RECEIVED.equals(rmaLineStsNm)//
                || LINE_STS_NM_PENDING_INSPECTION.equals(rmaLineStsNm) || LINE_STS_NM_BILLING_HOLD.equals(rmaLineStsNm) //
                || LINE_STS_NM_PENDING_INVOICE.equals(rmaLineStsNm)
                || LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
            return false;
        }
        // 2018/08/28 QC#26329 Mod End
        
        return true;
    }
    // 2018/08/03 S21_NA#27040 Add End

    // 2019/07/11 S21_NA#51287 Add Start
    public static boolean isNotProtectedLineTabFieldByConfigSts(NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg) {

        String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            String targetDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (dsOrdPosnNum.equals(targetDsOrdPosnNum)) {
                String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                if (!(LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm) || LINE_STS_NM_INVOICED.equals(lineStsNm)
                        || LINE_STS_NM_CANCELLED.equals(lineStsNm) || LINE_STS_NM_ON_LOAN.equals(lineStsNm)
                        || LINE_STS_NM_CLOSED_LOAN_RETURN.equals(lineStsNm) || LINE_STS_NM_CLOSED_LOAN_SOLD.equals(lineStsNm))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNotProtectedRMATabFieldByConfigSts(NWAL1500SMsg glblMsg, NWAL1500_CSMsg configMsg) {

        String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            String targetDsOrdPosnNum = lineMsg.dsOrdPosnNum_RL.getValue();
            if (dsOrdPosnNum.equals(targetDsOrdPosnNum)) {
                String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                if (!(LINE_STS_NM_CANCELLED.equals(lineStsNm) || LINE_STS_NM_CLOSED.equals(lineStsNm))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNotProtectedRMATabShiptoFieldByConfigSts(NWAL1500SMsg glblMsg, NWAL1500_CSMsg configMsg) {

        String dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            String targetDsOrdPosnNum = lineMsg.dsOrdPosnNum_RL.getValue();
            if (dsOrdPosnNum.equals(targetDsOrdPosnNum)) {
                String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                if (!(LINE_STS_NM_CANCELLED.equals(lineStsNm) || LINE_STS_NM_CLOSED.equals(lineStsNm)
                        || LINE_STS_NM_PENDING_RETURN.equals(lineStsNm) || LINE_STS_NM_PARTIAL_RECEIVED.equals(lineStsNm)
                        || LINE_STS_NM_PENDING_INSPECTION.equals(lineStsNm) || LINE_STS_NM_BILLING_HOLD.equals(lineStsNm)
                        || LINE_STS_NM_PENDING_INVOICE.equals(lineStsNm))) {
                    return true;
                }
            }
        }
        return false;
    }
    // 2019/07/11 S21_NA#51287 Add End
}
