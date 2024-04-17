/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0682E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.blap.NSAL1240.constant.NSAL1240Constant;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForConfigId;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * 2016/01/04   Fujitsu         T.ishii         Update          S21_NA#955
 * 2016/03/03   Fujitsu         S.Ohki          Update          S21_NA#5000
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#2334
 * 2016/06/07   Fujitsu         S.Yamamoto      Update          S21_NA#9277
 * 2016/10/04   Fujitsu         S.Takami        Update          S21_NA#9215
 * 2018/01/29   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/09/25   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/16   Fujitsu         K.Ishizuka      Update          S21_NA#28727
 * 2018/12/17   Fujitsu         Y.Kanefusa      Update          S21_NA#29638
 * </pre>
 */
public class NWAL1500CommonLogicForConfigId {

    // TODO:common
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

    // 2018/01/29 S21_NA#19808 Del Start
//    // TODO:publish
//    public static boolean prepareLineS2C(NWAL1500_BCMsgArray lineList, NWAL1500_JSMsgArray lineAllList, String dsOrdPosnNum, int lineStartIndex) {
//
//        List<Integer> deleteList = new ArrayList<Integer>();
//        int insertRow = 0;
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                deleteList.add(i);
//            }
//        }
//        ZYPTableUtil.deleteRows(lineList, deleteList);
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                insertRow = i;
//                break;
//            }
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
//                insertRow = i;
//                break;
//            }
//            insertRow = i + 1;
//        }
//
//        for (int lineIndex = 0; lineIndex < lineAllList.getValidCount(); lineIndex++) {
//            NWAL1500_JSMsg sourceLine = lineAllList.no(lineIndex);
//            if (Integer.parseInt(sourceLine.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                NWAL1500_BCMsg line = (NWAL1500_BCMsg) insertNewLine(lineList, insertRow++);
//                if (line == null) {
//                    return false;
//                }
//                EZDMsg.copy(sourceLine, null, line, null);
//            }
//        }
//        return true;
//    }
//
//    public static boolean prepareLineS2C(NWAL1500_DCMsgArray lineList, NWAL1500_KSMsgArray lineAllList, String dsOrdPosnNum, int lineStartIndex) {
//
//        List<Integer> deleteList = new ArrayList<Integer>();
//        int insertRow = 0;
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                deleteList.add(i);
//            }
//        }
//        ZYPTableUtil.deleteRows(lineList, deleteList);
//        for (int i = 0; i < lineList.getValidCount(); i++) {
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                insertRow = i;
//                break;
//            }
//            if (Integer.parseInt(lineList.no(i).dsOrdPosnNum_RL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
//                insertRow = i;
//                break;
//            }
//            insertRow = i + 1;
//        }
//
//        for (int lineIndex = 0; lineIndex < lineAllList.getValidCount(); lineIndex++) {
//            NWAL1500_KSMsg sourceLine = lineAllList.no(lineIndex);
//            if (Integer.parseInt(sourceLine.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
//                NWAL1500_DCMsg line = (NWAL1500_DCMsg) insertNewLine(lineList, insertRow++);
//                if (line == null) {
//                    return false;
//                }
//                EZDMsg.copy(sourceLine, null, line, null);
//            }
//        }
//        return true;
//    }
    // 2018/01/29 S21_NA#19808 Del End

    public static int getMaxLineNum(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i); // 2018/01/29 S21_NA#19808
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_LL.getValueInt();
            }
        }
        return maxLineNum;
    }

    public static int getMaxLineNum(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808

        int maxLineNum = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i); // 2018/01/29 S21_NA#19808
            if (Integer.parseInt(line.dsOrdPosnNum_RL.getValue()) == Integer.parseInt(dsOrdPosnNum)) {
                maxLineNum = line.dsCpoLineNum_RL.getValueInt();
            }
        }
        return maxLineNum;
    }

//QC743    
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

    public static int getAddLineRow(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808

        int maxLineRow = 0;
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i); // 2018/01/29 S21_NA#19808
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) > Integer.parseInt(dsOrdPosnNum)) {
                return i;
            }
            maxLineRow = i + 1;
        }
        return maxLineRow;
    }

    public static int getAddLineRow(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808

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

    public static void deleteEmptyLine(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808 Mod

        int lineCount = 0;
        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i); // 2018/01/29 S21_NA#19808 Mod
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

    public static void deleteEmptyLine(NWAL1500_DSMsgArray lineList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808 Mod

        int lineCount = 0;
        List<Integer> deleteLine = new ArrayList<Integer>();
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_DSMsg line = lineList.no(i);
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

    private static boolean isEmptyLine(NWAL1500_BSMsg line) { // 2018/01/29 S21_NA#19808 Mod

        return !ZYPCommonFunc.hasValue(line.mdseCd_LL);
    }

    private static boolean isEmptyLine(NWAL1500_DSMsg line) { // 2018/01/29 S21_NA#19808 Mod

        return !ZYPCommonFunc.hasValue(line.mdseCd_RL);
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getComponentList(String glblCmpyCd, BigDecimal svcConfigMstrPk, String svcMachUsgStsCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("svcMachUsgStsCd", svcMachUsgStsCd);
        S21SsmEZDResult result = NWAL1500QueryForConfigId.getInstance().getComponentList(queryParam);
        return (List<Map<String, Object>>) result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getCoaProd(String glblCmpyCd, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult result = NWAL1500QueryForConfigId.getInstance().getCoaProd(queryParam);
        return (Map<String, Object>) result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getPrimaryOrdLineCatg(NWAL1500CMsg bizMsg, String dsOrdLineDrctnCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        queryParam.put("effDt", bizMsg.slsDt.getValue());
        queryParam.put("dsOrdLineDrctnCd", dsOrdLineDrctnCd);
        queryParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("primLineCatgFlg", ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult result = NWAL1500QueryForConfigId.getInstance().getPrimaryOrdLineCatg(queryParam);
        return (Map<String, Object>) result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getPrimaryRtlSubLoc(NWAL1500CMsg bizMsg, String curLocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("rtlWhCd", curLocCd);
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        S21SsmEZDResult result = NWAL1500QueryForConfigId.getInstance().getPrimaryRtlSubLoc(queryParam);
        return (Map<String, Object>) result;
    }

    // 2018/01/29 S21_NA#19808 Del Start
//    public static boolean setChildLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_JSMsgArray lineAllList, int lineIndex) {
//
//        // for set merchandise
//        // add new row
//        NWAL1500_JSMsg line = lineAllList.no(lineIndex);
//        String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
//        BigDecimal dsCpoLineNum = line.dsCpoLineNum_LL.getValue();
//        int insertRow = lineIndex + 1;
//
//        // get merchandise type
//        MDSETMsg mdse = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue());
//        if (mdse != null && S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
//
//            int lineSubNum = 0;
//            List<Map<String, Object>> childMdseList = getChildMdse(bizMsg, mdse.mdseCd.getValue());
//            for (Map<String, Object> childMdse : childMdseList) {
//
//                NWAL1500_JSMsg newChildLine = (NWAL1500_JSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                if (newChildLine == null) {
//                    return false;
//                }
//                BigDecimal baseEachQty = line.ordQty_LL.getValue();
//
//                // set up child line
//                if (!setUpLine(newChildLine, bizMsg, glblMsg, childMdse, baseEachQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
//                    return false;
//                }
//
//                MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
//                if (mdse != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                    baseEachQty = newChildLine.ordQty_LL.getValue();
//                    List<Map<String, Object>> grandChildMdseList = getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
//                    if (grandChildMdseList.size() > 0) {
//                        insertRow--;
//                        lineSubNum--;
//                        List<Integer> deleteLisy = new ArrayList<Integer>();
//                        deleteLisy.add(insertRow);
//                        ZYPTableUtil.deleteRows(lineAllList, deleteLisy);
//                    }
//                    for (Map<String, Object> grandChildMdse : grandChildMdseList) {
//                        NWAL1500_JSMsg newGrandChildLine = (NWAL1500_JSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                        if (newGrandChildLine == null) {
//                            return false;
//                        }
//
//                        // set up grand child line
//                        if (!setUpLine(newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseEachQty, dsOrdPosnNum, dsCpoLineNum.intValue(), lineSubNum++, insertRow++)) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }
//
//    @SuppressWarnings("unchecked")
//    private static boolean setUpLine(NWAL1500_JSMsg newLine, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, Object> childMdse, BigDecimal baseEachQty, String dsOrdPosnNum, int lineNum, int lineSubNum, int slctLineIndex) {
//
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, dsOrdPosnNum);
//        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_LL, new BigDecimal(lineNum));
//        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineSubNum_LL, new BigDecimal(lineSubNum));
//        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_LL, NWAL1500CommonLogic.concatLineNum(newLine));
//
//        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, (String) childMdse.get("MDSE_CD"));
//        if (baseEachQty != null) {
//            ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_LL, baseEachQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
//            ZYPEZDItemValueSetter.setValue(newLine.ordQty_LL, baseEachQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
//        }
//        ZYPEZDItemValueSetter.setValue(newLine.childMdseQty_LL, newLine.ordCustUomQty_LL);
//
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
//
//        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
//        Map<String, String> mdseInfo = new HashMap<String, String>();
//        if (!ssmResult.isCodeNotFound()) {
//            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
//        }
//
//        NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
//        ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL, mdseTMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(lineMsg.mdseNm_LL, mdseTMsg.mdseNm);
//        ZYPEZDItemValueSetter.setValue(lineMsg.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
//        NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, lineMsg);
//        ZYPEZDItemValueSetter.setValue(lineMsg.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
//        ZYPEZDItemValueSetter.setValue(lineMsg.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, bizMsg.prcCatgCd);
//        ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_LL, bizMsg.prcCatgNm);
//        String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
//        NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(bizMsg, primaryLineCatgCd, null);
//        lineMsg.ordLineStsDescTxt_LL.clear();
//
//        // Call NWZC1800 Default WH API
//        int slctConfIndex = getConfigIndex(bizMsg.A, dsOrdPosnNum);
//        NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);
//
//        // 2015/11/20 S21_NA#934 Add isMdseTangible
//        // if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) &&
//        // NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(),
//        // lineMsg.mdseCd_LL.getValue())) {
//        // S21_NA#2522
//        if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) && NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue())) {
//            NWZC180001PMsg pMsg = new NWZC180001PMsg();
//            if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, lineMsg.dsOrdPosnNum_LL.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.ordQty_LL.getValue())) {
//                return false;
//            }
//
//            String rtlWhCd = pMsg.rtlWhCd.getValue();
//            String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//            ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//        }
//
//        lineMsg.serNum_LL.clear();
//        ZYPEZDItemValueSetter.setValue(lineMsg.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
//        lineMsg.sbstMdseCd_LL.clear();
//        lineMsg.vndInvtyLocCd_LL.clear();
//        ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListCd_LL, bizMsg.flPrcListCd);
//        ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListNm_LL, bizMsg.flPrcListNm);
//        ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(lineMsg.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
//        ZYPEZDItemValueSetter.setValue(lineMsg.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
//        ZYPEZDItemValueSetter.setValue(lineMsg.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
//        lineMsg.dplyLineRefNum_LL.clear();
//        lineMsg.crRebilCd_LL.clear();
//        ZYPEZDItemValueSetter.setValue(lineMsg.prcBaseDt_LL, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(lineMsg.rddDt_LL, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(lineMsg.allocQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.shipQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.istlQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.invQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(lineMsg.cancQty_LL, BigDecimal.ZERO);
//
//        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, slctConfIndex);
//        NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, slctLineIndex, mdseTMsg.mdseCd.getValue());
//        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
//
//        return true;
//    }
    // 2018/01/29 S21_NA#19808 Del End
    
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getChildMdse(NWAL1500CMsg bizMsg, String parentMdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prntMdseCd", parentMdseCd);
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        S21SsmEZDResult ssmResult = NWAL1500QueryForConfigId.getInstance().getChildMdseList(queryParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    public static int getConfigIndex(NWAL1500_ASMsgArray configList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808 Mod

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_LC.getValue())) {
                return i;
            }
        }
        return 0;
    }

    public static int getConfigIndex(NWAL1500_CSMsgArray configList, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808 Mod

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_RC.getValue())) {
                return i;
            }
        }
        return 0;
    }

    // 2018/01/29 S21_NA#19808 Del Start
//    // TODO:event private
//    public static boolean setConfigId(String glblCmpyCd, NWAL1500CMsg bizMsg, NWAL1500_ACMsg config, NWAL1500_BCMsgArray lineAllList, NWAL1500_BCMsgArray lineList) {
//
//        // store pop up result.
//        setReturnParam(bizMsg, config);
//
//        // if (S21StringUtil.isEquals(config.configTpCd_LC.getValue(),
//        // CONFIG_TP.EXISTING)) {
//        // S21_NA#955
//        // Out bound N N Y
//        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, false, true)) {
//            // get component list
//            List<Map<String, Object>> componentList = getComponentList(glblCmpyCd, bizMsg.svcConfigMstrPk_RO.getValue(), SVC_MACH_USG_STS.IN_INVENTORY);
//            Map<String, Object> ordLineCatg = getPrimaryOrdLineCatg(bizMsg, DS_ORD_LINE_DRCTN.OUTBOUND);
//
//            // prepare component
//            String dsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();
//            int lineStartIndex = getAddLineRow(lineAllList, dsOrdPosnNum);
//            int maxLineNum = getMaxLineNum(lineAllList, dsOrdPosnNum);
//            int insertRow = lineStartIndex;
//            int lineCount = 0;
//            // QC743
//            // NWAL1500CommonLogicForLineControl.storeLineByConfig(lineAllList, lineList, dsOrdPosnNum); // S21_NA#1670
//            for (Map<String, Object> component : componentList) {
//                NWAL1500_JSMsg newLine = (NWAL1500_JSMsg) insertNewLine(lineAllList, insertRow++);
//                if (newLine == null) {
//                    return false;
//                }
//                lineCount++;
//                // set up line
//                setUpLine(newLine, glblCmpyCd, bizMsg, component, ordLineCatg, dsOrdPosnNum, maxLineNum + lineCount);
//            }
//            //QC743
//            //prepareLineS2C(lineList, lineAllList, dsOrdPosnNum, lineStartIndex);
//        }
//        return true;
//    }
//
//    public static boolean setConfigId(String glblCmpyCd, NWAL1500CMsg bizMsg, NWAL1500_CCMsg config, NWAL1500_DCMsgArray lineAllList, NWAL1500_DCMsgArray lineList) {
//
//        // store pop up result.
//        setReturnParam(bizMsg, config);
//
//        // if (S21StringUtil.isEquals(config.configTpCd_RC.getValue(),
//        // CONFIG_TP.EXISTING)) {
//        // S21_NA#955
//        // In bound N Y N
//        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, false)) {
//            // get component list
//            List<Map<String, Object>> componentList = getComponentList(glblCmpyCd, bizMsg.svcConfigMstrPk_RO.getValue(), SVC_MACH_USG_STS.AT_CUSTOMER);
//            Map<String, Object> ordLineCatg = getPrimaryOrdLineCatg(bizMsg, DS_ORD_LINE_DRCTN.INBOUND);
//
//            // prepare component
//            String dsOrdPosnNum = config.dsOrdPosnNum_RC.getValue();
//            int lineStartIndex = getAddLineRow(lineAllList, dsOrdPosnNum);
//            int maxLineNum = getMaxLineNum(lineAllList, dsOrdPosnNum);
//            int insertRow = lineStartIndex;
//            int lineCount = 0;
//            for (Map<String, Object> component : componentList) {
//                NWAL1500_KSMsg newLine = (NWAL1500_KSMsg) insertNewLine(lineAllList, insertRow++);
//                lineCount++;
//                // set up line
//                setUpLine(newLine, glblCmpyCd, bizMsg, component, ordLineCatg, dsOrdPosnNum, maxLineNum + lineCount);
//            }
//
//            //QC743
//            //prepareLineS2C(lineList, lineAllList, dsOrdPosnNum, lineStartIndex);
//        }
//        return true;
//    }
    // 2018/01/29 S21_NA#19808 Del End

    private static void setReturnParam(NWAL1500CMsg bizMsg, NWAL1500_ACMsg config) {

        ZYPEZDItemValueSetter.setValue(config.mdlId_LC, bizMsg.mdlId_RO);
        ZYPEZDItemValueSetter.setValue(config.mdlDescTxt_LC, bizMsg.mdlNm_RO);
    }

    private static void setReturnParam(NWAL1500CMsg bizMsg, NWAL1500_CCMsg config) {

        ZYPEZDItemValueSetter.setValue(config.mdlId_RC, bizMsg.mdlId_RO);
        ZYPEZDItemValueSetter.setValue(config.mdlDescTxt_RC, bizMsg.mdlNm_RO);
    }

    // 2018/01/29 S21_NA#19808 Del Start
//    private static void setUpLine(NWAL1500_JSMsg newLine, String glblCmpyCd, NWAL1500CMsg header, Map<String, Object> component, Map<String, Object> ordLineCatg, String dsOrdPosnNum, int lineNum) {
//
//        String orgMdseCd = (String) component.get("MDSE_CD");
//        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, orgMdseCd);
//        if (mdseTMsg == null) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, dsOrdPosnNum);
//        newLine.dsCpoLineNum_LL.setValue(lineNum);
//        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, orgMdseCd);
//        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_LL, mdseTMsg.mdseNm);
//        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_LL, BigDecimal.ONE);
//
//        Map<String, Object> coaProd = getCoaProd(glblCmpyCd, mdseTMsg.mdseCd.getValue());
//
//        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_LL, (String) coaProd.get("PKG_UOM_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.ordQty_LL, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealGrsAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_LL, header.prcCatgCd);
//        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_LL, header.prcCatgNm);
//        newLine.prcListEquipConfigNum_LL.clear();
//
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_LL, (String) ordLineCatg.get("DS_ORD_LINE_CATG_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgDescTxt_LL, (String) ordLineCatg.get("DS_ORD_LINE_CATG_DESC_TXT"));
//        newLine.ordLineStsCd_LL.clear();
//        newLine.ordLineStsDescTxt_LL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, ORD_LINE_SRC.INTERNAL);
//
//        Map<String, Object> rtlSubLoc = getPrimaryRtlSubLoc(header, (String) component.get("CUR_LOC_NUM"));
//
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, (String) rtlSubLoc.get("RTL_WH_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, (String) rtlSubLoc.get("RTL_WH_NM"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, (String) rtlSubLoc.get("RTL_SWH_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, (String) rtlSubLoc.get("RTL_SWH_NM"));
//        ZYPEZDItemValueSetter.setValue(newLine.serNum_LL, (String) component.get("SER_NUM"));
//        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_LL, ZYPConstant.FLG_ON_Y);
//        newLine.sbstMdseCd_LL.clear();
//        newLine.vndInvtyLocCd_LL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_LL, header.flPrcListCd);
//        ZYPEZDItemValueSetter.setValue(newLine.flPrcListDescTxt_LL, header.flPrcListNm);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoLineTotAmt_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_LL, (String) coaProd.get("COA_MDSE_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_LL, (String) coaProd.get("COA_MDSE_TP_DESC_TXT"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_LL, (String) coaProd.get("COA_PROD_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_LL, (String) coaProd.get("COA_PROD_DESC_TXT"));
//        newLine.dplyLineRefNum_LL.clear();
//        newLine.crRebilCd_LL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_LL, header.slsDt);
//        ZYPEZDItemValueSetter.setValue(newLine.rddDt_LL, header.slsDt);
//        newLine.custMdseCd_LL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.allocQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.shipQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.istlQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.invQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.cancQty_LL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.svcMachMstrPk_LL, (BigDecimal) component.get("SVC_MACH_MSTR_PK"));
//    }
//
//    private static void setUpLine(NWAL1500_KSMsg newLine, String glblCmpyCd, NWAL1500CMsg header, Map<String, Object> component, Map<String, Object> ordLineCatg, String dsOrdPosnNum, int lineNum) {
//
//        String orgMdseCd = (String) component.get("MDSE_CD");
//        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, orgMdseCd);
//        if (mdseTMsg == null) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_RL, dsOrdPosnNum);
//        newLine.dsCpoLineNum_RL.setValue(lineNum);
//        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_RL, orgMdseCd);
//        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_RL, mdseTMsg.mdseNm);
//        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_RL, BigDecimal.ONE);
//
//        Map<String, Object> coaProd = getCoaProd(glblCmpyCd, mdseTMsg.mdseCd.getValue());
//
//        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_RL, (String) coaProd.get("PKG_UOM_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.ordQty_RL, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealGrsAmt_RL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_RL, header.prcCatgCd);
//        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_RL, header.prcCatgNm);
//        // newLine.prcListEquipConfigNum_RL.clear();
//
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_RL, (String) ordLineCatg.get("DS_ORD_LINE_CATG_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgDescTxt_RL, (String) ordLineCatg.get("DS_ORD_LINE_CATG_DESC_TXT"));
//        // newLine.ordLineStsCd_RL.clear();
//        // newLine.ordLineStsDescTxt_RL.clear();
//        // ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_RL,
//        // ORD_LINE_SRC.INTERNAL);
//
//        Map<String, Object> rtlSubLoc = getPrimaryRtlSubLoc(header, (String) component.get("CUR_LOC_NUM"));
//
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_RL, (String) rtlSubLoc.get("RTL_WH_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_RL, (String) rtlSubLoc.get("RTL_WH_NM"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_RL, (String) rtlSubLoc.get("RTL_SWH_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_RL, (String) rtlSubLoc.get("RTL_SWH_NM"));
//        ZYPEZDItemValueSetter.setValue(newLine.serNum_RL, (String) component.get("SER_NUM"));
//        // ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_RL,
//        // ZYPConstant.FLG_ON_Y);
//        // newLine.sbstMdseCd_RL.clear();
//        // newLine.vndInvtyLocCd_RL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_RL, header.flPrcListCd);
//        ZYPEZDItemValueSetter.setValue(newLine.flPrcListDescTxt_RL, header.flPrcListNm);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.cpoLineTotAmt_RL, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_RL, (String) coaProd.get("COA_MDSE_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_RL, (String) coaProd.get("COA_MDSE_TP_DESC_TXT"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_RL, (String) coaProd.get("COA_PROD_CD"));
//        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_RL, (String) coaProd.get("COA_PROD_DESC_TXT"));
//        newLine.dplyLineRefNum_RL.clear();
//        // newLine.crRebilCd_RL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_RL, header.slsDt);
//        newLine.custMdseCd_RL.clear();
//        ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_RL, header.slsDt);
//        ZYPEZDItemValueSetter.setValue(newLine.rtrnQty_RL, BigDecimal.ZERO);
//        // ZYPEZDItemValueSetter.setValue(newLine.shipQty_RL,
//        // BigDecimal.ZERO);
//        // ZYPEZDItemValueSetter.setValue(newLine.istlQty_RL,
//        // BigDecimal.ZERO);
//        // ZYPEZDItemValueSetter.setValue(newLine.invQty_RL,
//        // BigDecimal.ZERO);
//        // ZYPEZDItemValueSetter.setValue(newLine.cancQty_RL,
//        // BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(newLine.svcMachMstrPk_RL, (BigDecimal) component.get("SVC_MACH_MSTR_PK"));
//    }
    // 2018/01/29 S21_NA#19808 Del End

    public static void setParameter(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int selectedIndex = bizMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ACMsg config = bizMsg.A.no(selectedIndex);
            // String configTp[] = {CONFIG_TP.ADD_TO_CONFIG,
            // CONFIG_TP.EXISTING };
            // if
            // (!Arrays.asList(configTp).contains(config.configTpCd_LC.getValue()))
            // {
            // Out bound Not N Y Y
            if (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) { // S21_NA#955
                config.configTpCd_LC.setErrorInfo(1, NWAM0682E); // 2016/03/03 S21_NA#5000 Mod
                return;
            }
            setParam(bizMsg, glblMsg, config); // 2018/03/28 S21_NA#25102 Mod
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_CCMsg config = bizMsg.C.no(selectedIndex);
            // String configTp[] = {CONFIG_TP.RETURN_EXISTING_IB };
            // if
            // (!Arrays.asList(configTp).contains(config.configTpCd_RC.getValue()))
            // {
            // In bound Not N Y N
            if (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, false)) { // S21_NA#955
                config.configTpCd_RC.setErrorInfo(1, NWAM0682E); // 2016/03/03 S21_NA#5000 Mod
                return;
            }
            setParam(bizMsg, glblMsg, config); // 2018/03/28 S21_NA#25102 Mod
        } else {
            bizMsg.setMessageInfo(NWAM0682E);
            return;
        }

    }

    private static void setParam(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ACMsg config) {

        // parameter
        // if (S21StringUtil.isEquals(CONFIG_TP.EXISTING,
        // config.configTpCd_LC.getValue())) {
        // Out bound N Y N
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) { // S21_NA#955

            ZYPEZDItemValueSetter.setValue(bizMsg.mdlNm_R, config.mdlDescTxt_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_R, config.svcConfigMstrPk_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R1, ZYPConstant.FLG_OFF_N);// SVC_MACH_MSTR_STS_EDIT_FLG-> 'N'
            bizMsg.Z.clear();
//            bizMsg.Z.no(0).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
            bizMsg.Z.no(0).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.INSTALLED);
            bizMsg.Z.no(1).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.DEALER_SERVICE);
            bizMsg.Z.setValidCount(2);
            // 2018/10/16 S21_NA#28727 Add Start
            if (S21StringUtil.isEquals(config.configTpCd_LC.getValue(), CONFIG_TP.ADD_TO_CONFIG)) {
                bizMsg.Z.no(2).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
                bizMsg.Z.setValidCount(3);
            }
            // 2018/10/16 S21_NA#28727 Add End
//            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(0), SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
//            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(1), SVC_MACH_MSTR_STS.INSTALLED);
//            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(2), SVC_MACH_MSTR_STS.DEALER_SERVICE);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R2, ZYPConstant.FLG_ON_Y);// MAIN_UNIT_FLG -> 'Y'
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R3, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R4, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R5, ZYPConstant.FLG_ON_Y);
            // } else if
            // (S21StringUtil.isEquals(CONFIG_TP.ADD_TO_CONFIG,
            // config.configTpCd_LC.getValue())) {
            // Out bound N N Y
            bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_01); // MACH_ALLOC_MODE_CODE: Not For Condition
            ZYPEZDItemValueSetter.setValue(bizMsg.curLocNum_R, config.shipToCustCd_LC);

            // 2016/06/07 S21_NA#9277 Mod Start(Conversion)
            if (NWXC150001DsCheck.isExpandCmptMatchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R2, ZYPConstant.FLG_OFF_N);// MAIN_UNIT_FLG -> 'N'
                bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_03); // MACH_ALLOC_MODE_CODE: Not Allocated
            }
            // 2016/06/07 S21_NA#9277 Mod End

        } else if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), config.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, false, true)) { // S21_NA#955

            ZYPEZDItemValueSetter.setValue(bizMsg.mdlNm_R, config.mdlDescTxt_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_R, config.svcConfigMstrPk_LC);
            // 2018/09/26 S21_NA#28482 Add Start
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R1, ZYPConstant.FLG_OFF_N);// SVC_MACH_MSTR_STS_EDIT_FLG-> 'N'
            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R1, ZYPConstant.FLG_ON_Y);// SVC_MACH_MSTR_STS_EDIT_FLG-> 'Y'
            // 2018/09/26 S21_NA#28482 Add End
            bizMsg.Z.clear();
            bizMsg.Z.no(0).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.CREATED);
            bizMsg.Z.no(1).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.REMOVED);
            bizMsg.Z.setValidCount(2);
//            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(0), SVC_MACH_MSTR_STS.CREATED);
//            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(1), SVC_MACH_MSTR_STS.REMOVED);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R2, ZYPConstant.FLG_OFF_N);// MAIN_UNIT_FLG -> 'Y'
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R3, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R4, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R5, ZYPConstant.FLG_ON_Y);
            // 2018/09/26 S231_NA#28482 Mod Start
            // bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_03); // MACH_ALLOC_MODE_CODE: Not Allocated
            // QC#29638 2018/11/17 Mod Start
            // bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_02);
            bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_03); // MACH_ALLOC_MODE_CODE: Not Allocated
            // QC#29638 2018/11/17 Mod End
            // 2018/09/26 S231_NA#28482 Mod End
        }
        bizMsg.xxPopPrm_P0.setValue(NSAL1240Constant.MODE_02); // CONFIG_EXST_MODE_CD: Config Existing

        // 2018/03/28 S21_NA#25102 Add Start
        ZYPTableUtil.clear(bizMsg.E);
        String slctDsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();
        int paramCnt = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(slctDsOrdPosnNum, glblMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcConfigMstrPk_LC)) {
                    bizMsg.E.no(paramCnt).svcConfigMstrPk_AW.setValue(glblMsg.A.no(i).svcConfigMstrPk_LC.getValue());
                    paramCnt++;
                }
            }
        }
        bizMsg.E.setValidCount(paramCnt);
        // 2018/03/28 S21_NA#25102 Add End
        return;
    }

    private static void setParam(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CCMsg config) { // // 2018/03/28 S21_NA#25102 Mod

        // parameter
        ZYPEZDItemValueSetter.setValue(bizMsg.mdlNm_R, config.mdlDescTxt_RC);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_R, config.svcConfigMstrPk_RC);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R1, ZYPConstant.FLG_OFF_N);// SVC_MACH_MSTR_STS_EDIT_FLG-> 'N'
        bizMsg.Z.clear();
//        bizMsg.Z.no(0).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        bizMsg.Z.no(0).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.INSTALLED);
        bizMsg.Z.no(1).svcMachMstrStsCd_I.setValue(SVC_MACH_MSTR_STS.DEALER_SERVICE);
        bizMsg.Z.setValidCount(2);
//        ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(0), SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
//        ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(1), SVC_MACH_MSTR_STS.INSTALLED);
//        ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_R.no(2), SVC_MACH_MSTR_STS.DEALER_SERVICE);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R2, ZYPConstant.FLG_OFF_N);// MAIN_UNIT_FLG -> 'N'
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R3, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R4, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtModeFlg_R5, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(bizMsg.curLocNum_R, config.shipToCustCd_RC);
        bizMsg.xxPopPrm_P0.setValue(NSAL1240Constant.MODE_02); // CONFIG_EXST_MODE_CD: Config Existing
        // 2016/10/04 S21_NA#9215 Add Start
        boolean isTabRma = TAB_RMA.equals(bizMsg.xxDplyTab.getValue());
        boolean isRetailEquipmentOrder = NWAL1500CommonLogicForLineConfig.isExistOrdCatg(bizMsg, false);
        if (isTabRma && !isRetailEquipmentOrder) {
            bizMsg.xxPopPrm_P0.setValue(NSAL1240Constant.MODE_01); // CONFIG_EXST_MODE_CD: Config Existing is not criteria.
        }
        // 2016/10/04 S21_NA#9215 Mod End
        bizMsg.xxPopPrm_P3.setValue(NSAL1240Constant.MODE_01); // MACH_ALLOC_MODE_CODE: Not For Condition

        // 2018/03/28 S21_NA#25102 Add Start
        ZYPTableUtil.clear(bizMsg.E);
        String slctDsOrdPosnNum = config.dsOrdPosnNum_RC.getValue();
        int paramCnt = 0;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(slctDsOrdPosnNum, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).svcConfigMstrPk_RC)) {
                    bizMsg.E.no(paramCnt).svcConfigMstrPk_AW.setValue(glblMsg.C.no(i).svcConfigMstrPk_RC.getValue());
                    paramCnt++;
                }
            }
        }
        bizMsg.E.setValidCount(paramCnt);
        // 2018/03/28 S21_NA#25102 Add End
        return;
    }
}
