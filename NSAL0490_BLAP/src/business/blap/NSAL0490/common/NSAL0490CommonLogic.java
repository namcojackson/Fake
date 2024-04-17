/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0490.common;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsg;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.blap.NSAL0490.NSAL0490Query;
import business.blap.NSAL0490.NSAL0490SMsg;
import business.blap.NSAL0490.NSAL0490_ASMsg;
import business.blap.NSAL0490.NSAL0490_ASMsgArray;
import business.blap.NSAL0490.NSAL0490_DSMsg;
import business.blap.NSAL0490.constant.NSAL0490Constant;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfAttribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfKeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Attribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Event;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.KeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.OPERATIONTYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.REFERENCETYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.wrapper.MasterDataMessagingServiceProxy;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/13   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2015/11/25   Hitachi         K.Yamada        Update          CSA QC#1150
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#5407
 * 2016/05/09   Hitachi         A.Kohinata      Update          QC#8055
 * 2016/07/13   Hitachi         T.Tomita        Update          QC#11801
 * 2017/07/12   Hitachi         K.Kim           Update          QC#18952
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2019/06/06   Hitachi         K.Fujimoto      Update          QC#50638
 *</pre>
 */
public class NSAL0490CommonLogic {

    /**
     * copy NSAL0490_ACMsgArray to NSAL0490_ASMsgArray for Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    public static void saveCurrentPageToSMsgItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * copy NSAL0490_ACMsgArray to NSAL0490_ASMsgArray for Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    public static void saveCurrentPageToSMsgSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(fromIdx + i), null);
        }
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * copy NSAL0490_ACMsgArray to NSAL0490_ASMsgArray for Criticality
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    public static void saveCMsgToSMsgCriticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            EZDMsg.copy(cMsg.D.no(i), null, sMsg.D.no(i), null);
        }
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
    /**
     * check Exist MDSE
     * @param cMsg NSAL0490CMsg
     * @param targetMdseCd target MDSE Code
     * @return true : Exist
     */
    public static boolean isExistMdse(NSAL0490CMsg cMsg, String targetMdseCd) {

        MDSETMsg tMsg = new MDSETMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.mdseCd.setValue(targetMdseCd);
        tMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        }

        return true;
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * checkItemIsSetParent
     * @param cMsg
     * @param targetMdseCd
     * @return boolean
     */
    public static boolean checkItemIsSetParent(NSAL0490CMsg cMsg, String targetMdseCd) {
        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().checkItemIsSetParent(cMsg.glblCmpyCd.getValue(), targetMdseCd);
        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /**
     * Exist AllMdseV check
     * @param cMsg NSAL0490CMsg
     * @param targetMdseCd target MDSE Code
     * @return true : Exist
     */
    public static boolean isExistAllMdseV(NSAL0490CMsg cMsg, String targetMdseCd) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getAllMdseV(cMsg, targetMdseCd);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                return true;
            }
        }
        return false;
    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    // add start 2016/04/22 CSA Defect#5407
    public static boolean isErrorMdseLenChk(String glblCmpyCd, String targetMdseCd) {
        String mercuryMdseCd = null;
        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMercuryMdse(glblCmpyCd, targetMdseCd);
        if (ssmResult.isCodeNormal()) {
            mercuryMdseCd = (String) ssmResult.getResultObject();
        }

        if (!ZYPCommonFunc.hasValue(mercuryMdseCd)) {
            return false;
        }

        // START 2017/07/12 K.Kim [QC#18952, MOD]
        // Mercury MDSE Size Check
//        if (mercuryMdseCd.length() == NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT) {
        if (mercuryMdseCd.length() <= NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT) {
            return false;
        }
        String ordTakeMdseCd = mercuryMdseCd.substring(0, NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT);
        ORD_TAKE_MDSETMsg tMsg = NSAL0490Query.getInstance().getMercuryOrdTakeMdseCd(glblCmpyCd, ordTakeMdseCd);
        if (tMsg == null) {
            return false;
        }
        return true;
        // END 2017/07/12 K.Kim [QC#18952, MOD]
    }
    // add end 2016/04/22 CSA Defect#5407

    /**
     * check Item Combination
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return false : No Error
     */
    public static boolean checkItemCombination(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;
        //START 2019/06/06 QC#50638 K.Fujimoto [ADD]
        List<List<NSAL0490_ASMsg>> combinationList = getCombinationList(sMsg);
        //END 2019/06/06 QC#50638 K.Fujimoto [ADD]
        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
        //if (checkItemCombinationForSameMdl(cMsg, sMsg, pageFromIdx, pageToIdx)) {
        if (checkItemCombinationForSameMdl(cMsg, sMsg, pageFromIdx, pageToIdx, combinationList)) {
        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
            return true;
        }

        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
        //if (checkItemCombinationForOtherMdl(cMsg, sMsg, pageFromIdx, pageToIdx)) {
        if (checkItemCombinationForOtherMdl(cMsg, sMsg, pageFromIdx, pageToIdx, combinationList)) {
        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
            return true;
        }

        return false;
    }

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /**
     * check Exist Machine Only
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return false : No Error
     */
    public static boolean checkExistMachineOnly(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        // get Combination List
        List<List<NSAL0490_ASMsg>> combinationList = getCombinationList(sMsg);

        // get MachineOnly MDSE_CD
        HashSet<String> machOnlySet = new HashSet<String>();
        for (int i = 0; i < combinationList.size(); i++) {
            List<NSAL0490_ASMsg> targetSMsgList = combinationList.get(i);
            if (targetSMsgList.size() == 1) {
                machOnlySet.add(((NSAL0490_ASMsg) targetSMsgList.get(0)).prntMdseCd_A.getValue());
            }
        }

        // Check Machine Only Screen List
        NSAL0490_ASMsgArray checkTargetList = sMsg.A;
        for (int i = 0; i < checkTargetList.getValidCount(); i++) {
            NSAL0490_ASMsg targetSMsg = (NSAL0490_ASMsg) checkTargetList.get(i);
            if (!ZYPCommonFunc.hasValue(targetSMsg.prntMdseCd_A)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(targetSMsg.mdseCd_A)) {
                continue;
            }
            String targetMdseCd = targetSMsg.prntMdseCd_A.getValue();
            if (machOnlySet.contains(targetMdseCd)) {
                continue;
            } else {
                // Check Machine Only Database
                if (isExistMachineOnlyInfo(cMsg, targetMdseCd)) {
                    continue;
                } else {
                    setCombinationErrorForItemConfigForMachine(cMsg, sMsg, targetSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isExistMachineOnlyInfo(NSAL0490CMsg cMsg, String targetMdseCd) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMachineOnlyInfo(cMsg, targetMdseCd);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                return true;
            }
        }
        return false;

    }

    private static void setCombinationErrorForItemConfigForMachine(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, String targetLineNum, int pageFromIdx, int pageToIdx) {

        Integer errPrntLineNum = getErrPrntLineNum(sMsg, targetLineNum);
        if (errPrntLineNum != null && (errPrntLineNum.intValue() >= pageFromIdx && errPrntLineNum.intValue() <= pageToIdx)) {
            cMsg.A.no(errPrntLineNum.intValue() - pageFromIdx).effFromDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0365E);
            cMsg.A.no(errPrntLineNum.intValue() - pageFromIdx).effThruDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0365E);
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0365E);
        }
    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    /**
     * check Item Combination For Same Model ('basicSMsgList' VS 'targetSMsgList')
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @return false : No Error
     */
    //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
    //private static boolean checkItemCombinationForSameMdl(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx) {
    private static boolean checkItemCombinationForSameMdl(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx, List<List<NSAL0490_ASMsg>> combinationList) {

        //START 2019/06/06 QC#50638 K.Fujimoto [ADD]
        HashSet<String> ordTakeMdseSet = new HashSet<String>();
        //END 2019/06/06 QC#50638 K.Fujimoto [ADD]
        // get Combination List
        //START 2019/06/06 QC#50638 K.Fujimoto [DEL]
        //List<List<NSAL0490_ASMsg>> combinationList = getCombinationList(sMsg);
        //END 2019/06/06 QC#50638 K.Fujimoto [DEL]

        for (int basicCnt = 0; basicCnt < combinationList.size(); basicCnt++) {
            List<NSAL0490_ASMsg> basicSMsgList = combinationList.get(basicCnt);

            for (int targetCnt = 0; targetCnt < combinationList.size(); targetCnt++) {
                // own line
                if (basicCnt == targetCnt) {
                    continue;
                }

                List<NSAL0490_ASMsg> targetSMsgList = combinationList.get(targetCnt);

                // check Hierarchy Size
                if (basicSMsgList.size() != targetSMsgList.size()) {
                    continue;
                }

                int sameCnt = 0;
                for (int cnt = 0; cnt < basicSMsgList.size(); cnt++) {
                    NSAL0490_ASMsg basicSMsg = basicSMsgList.get(cnt);
                    NSAL0490_ASMsg targetSMsg = targetSMsgList.get(cnt);
                    // 2015/10/07 CSA Y.Tsuchimoto Mod Start
//                    if (cnt == 0) {
//                        if (!basicSMsg.prntMdseCd_A.getValue().equals(targetSMsg.prntMdseCd_A.getValue())) {
//                            break;
//                        }
//
//                        // check Range Date
//                        String basicStartDate = basicSMsg.effFromDt_A.getValue();
//                        String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
//                        String targetStartDate = targetSMsg.effFromDt_A.getValue();
//                        String targetEndDate = getMaxDate(targetSMsg.effThruDt_A.getValue());
//
//                        if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
//                            continue;
//                        } else {
//                            if (targetSMsgList.size() == 1) {
//                                setCombinationErrorForItemConfig(cMsg, sMsg, targetSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx, NSAL0490Constant.SAME_MDL);
//                                return true;
//                            }
//                        }
//
//                        sameCnt++;
//                        continue;
//                    }
                    // check Parent Mdse
                    if (cnt == 0) {
                        String basicMdseCd = basicSMsg.mdseCd_A.getValue();
                        if (basicMdseCd == null) {
                            basicMdseCd = basicSMsg.prntMdseCd_A.getValue();
                        }
                        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
                        //String targetMdseCd = NSAL0490CommonLogic.getOrdTakeMsdeCd(cMsg, targetSMsg.prntMdseCd_A.getValue());
                        String targetMdseCd = NSAL0490CommonLogic.getOrdTakeMsdeCd(cMsg, targetSMsg.prntMdseCd_A.getValue(), ordTakeMdseSet);
                        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
                        if (targetMdseCd == null) {
                            targetMdseCd = targetSMsg.prntMdseCd_A.getValue();
                        }
                        if (!basicMdseCd.equals(targetMdseCd)) {
                            break;
                        }

                        // check Range Date
                        String basicStartDate = basicSMsg.effFromDt_A.getValue();
                        String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
                        String targetStartDate = targetSMsg.effFromDt_A.getValue();
                        String targetEndDate = getMaxDate(targetSMsg.effThruDt_A.getValue());

                        if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
                            continue;
                        } else {
                            if (targetSMsgList.size() == 1) {
                                setCombinationErrorForItemConfig(cMsg, sMsg, targetSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx, NSAL0490Constant.SAME_MDL);
                                return true;
                            }
                        }

                        sameCnt++;
                        continue;
                    }
                    // 2015/10/07 CSA Y.Tsuchimoto Mod End

                    // check Child Mdse
                    // 2015/10/07 CSA Y.Tsuchimoto Mod Start
//                    for (int childCnt = 0; childCnt < targetSMsgList.size(); childCnt++) {
//                        NSAL0490_ASMsg targetChildSMsg = targetSMsgList.get(childCnt);
//                        // skip Parent Mdse
//                        if (ZYPCommonFunc.hasValue(targetChildSMsg.prntMdseCd_A)) {
//                            continue;
//                        }
//
//                        if (!basicSMsg.childMdseCd_A.getValue().equals(targetChildSMsg.childMdseCd_A.getValue())) {
//                            continue;
//                        }
//
//                        // check Range Date
//                        String basicStartDate = basicSMsg.effFromDt_A.getValue();
//                        String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
//                        String targetStartDate = targetChildSMsg.effFromDt_A.getValue();
//                        String targetEndDate = getMaxDate(targetChildSMsg.effThruDt_A.getValue());
//
//                        if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
//                            continue;
//                        }
//
//                        sameCnt++;
//                        break;
//                    }
                    for (int childCnt = 0; childCnt < targetSMsgList.size(); childCnt++) {
                        NSAL0490_ASMsg targetChildSMsg = targetSMsgList.get(childCnt);
                        String basicChildMdseCd = basicSMsg.mdseCd_A.getValue();
                        if (basicChildMdseCd == null) {
                            basicChildMdseCd = basicSMsg.childMdseCd_A.getValue();
                        }
                        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
                        //String targetChildMdseCd = NSAL0490CommonLogic.getOrdTakeMsdeCd(cMsg, targetSMsg.childMdseCd_A.getValue());
                        String targetChildMdseCd = NSAL0490CommonLogic.getOrdTakeMsdeCd(cMsg, targetSMsg.childMdseCd_A.getValue(), ordTakeMdseSet);
                        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
                        if (targetChildMdseCd == null) {
                            targetChildMdseCd = targetSMsg.childMdseCd_A.getValue();
                        }
                        // skip Parent Mdse
                        if (ZYPCommonFunc.hasValue(targetChildSMsg.prntMdseCd_A)) {
                            continue;
                        }

                        if (!basicChildMdseCd.equals(targetChildMdseCd)) {
                            continue;
                        }

                        // check Range Date
                        String basicStartDate = basicSMsg.effFromDt_A.getValue();
                        String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
                        String targetStartDate = targetChildSMsg.effFromDt_A.getValue();
                        String targetEndDate = getMaxDate(targetChildSMsg.effThruDt_A.getValue());

                        if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
                            continue;
                        }

                        sameCnt++;
                        break;
                    }
                    // 2015/10/07 CSA Y.Tsuchimoto Mod End
                    if (cnt == basicSMsgList.size() - 1) {
                        if (sameCnt == basicSMsgList.size()) {
                            setCombinationErrorForItemConfig(cMsg, sMsg, targetSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx, NSAL0490Constant.SAME_MDL);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * check Item Combination For Other Model(DB check)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @return false : No Error
     */
    //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
    private static boolean checkItemCombinationForOtherMdl(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx, List<List<NSAL0490_ASMsg>> combinationList) {
    //private static boolean checkItemCombinationForOtherMdl(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx) {
    //END 2019/06/06 QC#50638 K.Fujimoto [MOD]

        //START 2019/06/06 QC#50638 K.Fujimoto [ADD]
        HashMap<BigDecimal, List<Map<String, Object>>> mdseCombMap = new HashMap<BigDecimal, List<Map<String, Object>>>();
        //END 2019/06/06 QC#50638 K.Fujimoto [ADD]

        if (ZYPConstant.FLG_OFF_N.equals(cMsg.mdlActvFlg.getValue())) {
            return false;
        }

        // get Combination List
        //START 2019/06/06 QC#50638 K.Fujimoto [ADD]
        //List<List<NSAL0490_ASMsg>> combinationList = getCombinationList(sMsg);
        //END   2019/06/06 QC#50638 K.Fujimoto [ADD]
        Map<String, List<Map<String, Object>>> otherMdlMap = new HashMap<String, List<Map<String, Object>>>();

        for (List<NSAL0490_ASMsg> basicSMsgList : combinationList) {
            List<Map<String, Object>> pntInfoOfOtherMdlList = getPrntInfoOfOtherMdl(cMsg, basicSMsgList.get(0).prntMdseCd_A.getValue(), otherMdlMap);

            for (Map<String, Object> otherMdl : pntInfoOfOtherMdlList) {
                //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
                List<List<Map<String, Object>>> mdseCombinationAllList = getMdseCombinationAllList(cMsg, (BigDecimal) otherMdl.get("MDL_ID"), (String) otherMdl.get("PRNT_MDSE_CD"), (BigDecimal) otherMdl.get("DS_MDL_CONFIG_PK"), mdseCombMap);
                //List<List<Map<String, Object>>> mdseCombinationAllList = getMdseCombinationAllList(cMsg, (BigDecimal) otherMdl.get("MDL_ID"), (String) otherMdl.get("PRNT_MDSE_CD"), (BigDecimal) otherMdl.get("DS_MDL_CONFIG_PK"));
                //START 2019/06/06 QC#50638 K.Fujimoto [MOD]

                for (List<Map<String, Object>> mdseCombinationList : mdseCombinationAllList) {
                    // check Hierarchy Size
                    if (basicSMsgList.size() != mdseCombinationList.size()) {
                        continue;
                    }

                    int sameCnt = 0;
                    for (int cnt = 0; cnt < basicSMsgList.size(); cnt++) {
                        NSAL0490_ASMsg basicSMsg = basicSMsgList.get(cnt);
                        Map<String, Object> mdseCombination = mdseCombinationList.get(cnt);

                        // check Parent Mdse
                        if (cnt == 0) {
                            if (!basicSMsg.prntMdseCd_A.getValue().equals((String) mdseCombination.get("PRNT_MDSE_CD"))) {
                                break;
                            }

                            // check Range Date
                            String basicStartDate = basicSMsg.effFromDt_A.getValue();
                            String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
                            String targetStartDate = (String) mdseCombination.get("EFF_FROM_DT");
                            String targetEndDate = getMaxDate((String) mdseCombination.get("EFF_THRU_DT"));

                            if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
                                continue;
                            } else {
                                if (mdseCombinationList.size() == 1) {
                                    setCombinationErrorForItemConfig(cMsg, sMsg, basicSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx, NSAL0490Constant.OTHER_MDL);
                                    return true;
                                }
                            }

                            sameCnt++;
                            continue;
                        }

                        // check Child Mdse
                        for (Map<String, Object> childMdseCombination : mdseCombinationList) {
                            // skip Parent Mdse
                            if (!ZYPCommonFunc.hasValue((String) childMdseCombination.get("CHILD_MDSE_CD"))) {
                                continue;
                            }

                            if (!basicSMsg.childMdseCd_A.getValue().equals((String) childMdseCombination.get("CHILD_MDSE_CD"))) {
                                continue;
                            }

                            // check Range Date
                            String basicStartDate = basicSMsg.effFromDt_A.getValue();
                            String basicEndDate = getMaxDate(basicSMsg.effThruDt_A.getValue());
                            String targetStartDate = (String) childMdseCombination.get("EFF_FROM_DT");
                            String targetEndDate = getMaxDate((String) childMdseCombination.get("EFF_THRU_DT"));

                            if (1 == ZYPDateUtil.compare(basicStartDate, targetEndDate) || -1 == ZYPDateUtil.compare(basicEndDate, targetStartDate)) {
                                continue;
                            }

                            sameCnt++;
                            break;
                        }

                        if (cnt == basicSMsgList.size() - 1) {
                            if (sameCnt == basicSMsgList.size()) {
                                setCombinationErrorForItemConfig(cMsg, sMsg, basicSMsg.xxLineNum_A.getValue(), pageFromIdx, pageToIdx, NSAL0490Constant.OTHER_MDL);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * get Combination List
     * @param sMsg NSAL0490SMsg
     * @return Combination List
     */
    private static List<List<NSAL0490_ASMsg>> getCombinationList(NSAL0490SMsg sMsg) {

        List<List<NSAL0490_ASMsg>> scrnCombinationList = new ArrayList<List<NSAL0490_ASMsg>>();
        List<NSAL0490_ASMsg> targetSMsgList = new ArrayList<NSAL0490_ASMsg>();

        String preLineNum = null;
        if (sMsg.A.getValidCount() > 0) {
            preLineNum = sMsg.A.no(0).xxLineNum_A.getValue();
        }

        for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(cnt);
            String targetLineNum = asMsg.xxLineNum_A.getValue();

            if (preLineNum.equals(targetLineNum)) {
                targetSMsgList.add(asMsg);
            } else {
                scrnCombinationList.add(targetSMsgList);
                targetSMsgList = new ArrayList<NSAL0490_ASMsg>();
                targetSMsgList.add(asMsg);
                preLineNum = targetLineNum;
            }

            if (cnt == sMsg.A.getValidCount() - 1) {
                scrnCombinationList.add(targetSMsgList);
            }
        }

        List<List<NSAL0490_ASMsg>> combinationList = new ArrayList<List<NSAL0490_ASMsg>>();

        for (List<NSAL0490_ASMsg> scrnCombinationSMsgList : scrnCombinationList) {
            // get total number of date
            List<String> allDateList = new ArrayList<String>();
            List<String> startDateList = new ArrayList<String>();
            List<String> endDateList = new ArrayList<String>();

            for (NSAL0490_ASMsg asMsg : scrnCombinationSMsgList) {
                allDateList.add(asMsg.effFromDt_A.getValue());
                allDateList.add(getMaxDate(asMsg.effThruDt_A.getValue()));
                startDateList.add(asMsg.effFromDt_A.getValue());
                endDateList.add(getMaxDate(asMsg.effThruDt_A.getValue()));
            }

            // duplicate delete and sort
            allDateList = new ArrayList<String>(new LinkedHashSet<String>(allDateList));
            startDateList = new ArrayList<String>(new LinkedHashSet<String>(startDateList));
            endDateList = new ArrayList<String>(new LinkedHashSet<String>(endDateList));
            Collections.sort(allDateList);
            Collections.sort(startDateList);
            Collections.sort(endDateList);

            String targetStartDate = allDateList.get(0);
            String targetEndDate = allDateList.get(0);

            for (int dtCnt = 0; dtCnt < startDateList.size() + endDateList.size() - 1; dtCnt++) {
                // get target end data
                if (endDateList.indexOf(targetStartDate) != -1) {
                    targetEndDate = targetStartDate;
                } else {
                    String date = null;
                    for (int i = 0; i < allDateList.size(); i++) {
                        date = allDateList.get(i);
                        if (ZYPDateUtil.compare(date, targetEndDate) == 1) {
                            break;
                        }
                    }

                    if (startDateList.indexOf(date) != -1) {
                        targetEndDate = ZYPDateUtil.addDays(date, -1);
                    } else {
                        targetEndDate = date;
                    }
                }

                // set target SMsg List
                targetSMsgList = new ArrayList<NSAL0490_ASMsg>();
                for (NSAL0490_ASMsg combinationSMsg : scrnCombinationSMsgList) {
                    String startDate = combinationSMsg.effFromDt_A.getValue();
                    String endDate = getMaxDate(combinationSMsg.effThruDt_A.getValue());

                    if (ZYPDateUtil.compare(startDate, targetStartDate) != 1 && ZYPDateUtil.compare(targetStartDate, endDate) != 1) {
                        if (ZYPDateUtil.compare(startDate, targetEndDate) != 1 && ZYPDateUtil.compare(targetEndDate, endDate) != 1) {
                            NSAL0490_ASMsg cloneSMsg = (NSAL0490_ASMsg) combinationSMsg.clone();
                            ZYPEZDItemValueSetter.setValue(cloneSMsg.effFromDt_A, targetStartDate);
                            ZYPEZDItemValueSetter.setValue(cloneSMsg.effThruDt_A, targetEndDate);
                            targetSMsgList.add(cloneSMsg);
                        }
                    }
                }

                if (targetSMsgList.size() > 0) {
                    combinationList.add(targetSMsgList);
                }

                targetStartDate = ZYPDateUtil.addDays(targetEndDate, 1);
                targetEndDate = targetStartDate;
            }
        }

        return combinationList;
    }

    /**
     * get Max Date (99991231)
     * @param targetDate Target Date
     * @return Max Date (99991231)
     */
    public static String getMaxDate(String targetDate) {

        if (!ZYPCommonFunc.hasValue(targetDate)) {
            return NSAL0490Constant.MAX_DATE;
        }

        return targetDate;
    }

    /**
     * set Combination Error (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param targetLineNum Target Line Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @param duplicateLocation Duplicate Location
     */
    private static void setCombinationErrorForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, String targetLineNum, int pageFromIdx, int pageToIdx, String duplicateLocation) {

        Integer errPrntLineNum = getErrPrntLineNum(sMsg, targetLineNum);
        if (errPrntLineNum != null && (errPrntLineNum.intValue() >= pageFromIdx && errPrntLineNum.intValue() <= pageToIdx)) {
            cMsg.A.no(errPrntLineNum.intValue() - pageFromIdx).effFromDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0326E, new String[] {duplicateLocation });
            cMsg.A.no(errPrntLineNum.intValue() - pageFromIdx).effThruDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0326E, new String[] {duplicateLocation });
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0326E, new String[] {duplicateLocation });
        }
    }

    /**
     * get Error Parent Line Number
     * @param sMsg NSAL0490SMsg
     * @param targetLineNum Target Line Number
     * @return Error Parent Line Number
     */
    private static Integer getErrPrntLineNum(NSAL0490SMsg sMsg, String targetLineNum) {

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(index);
            if (targetLineNum.equals(asMsg.xxLineNum_A.getValue())) {
                return index;
            }
        }

        return null;
    }

    /**
     * get Parent Info Of Other Model
     * @param cMsg NSAL0490CMsg
     * @param prntMdseCd Parent Mdse Code
     * @param otherMdlMap Other Model Map
     * @return Parent Info Of Other Model
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getPrntInfoOfOtherMdl(NSAL0490CMsg cMsg, String prntMdseCd, Map<String, List<Map<String, Object>>> otherMdlMap) {

        if (!otherMdlMap.containsKey(prntMdseCd)) {
            S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getPrntInfoOfOtherMdl(cMsg, prntMdseCd);

            if (ssmResult.isCodeNotFound()) {
                otherMdlMap.put(prntMdseCd, new ArrayList<Map<String, Object>>());
            } else {
                otherMdlMap.put(prntMdseCd, (List<Map<String, Object>>) ssmResult.getResultObject());
            }
        }

        return otherMdlMap.get(prntMdseCd);
    }

    /**
     * get Mdse Combination All List
     * @param cMsg NSAL0490CMsg
     * @param mdlId Model ID
     * @param prntMdseCd Parent Mdse Code
     * @param dsMdlConfigPk DS Model Config PK
     * @return Mdse Combination
     */
    @SuppressWarnings("unchecked")
    //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
//    private static List<List<Map<String, Object>>> getMdseCombinationAllList(NSAL0490CMsg cMsg, BigDecimal mdlId, String prntMdseCd, BigDecimal dsMdlConfigPk) {
    private static List<List<Map<String, Object>>> getMdseCombinationAllList(NSAL0490CMsg cMsg, BigDecimal mdlId, String prntMdseCd, BigDecimal dsMdlConfigPk, HashMap<BigDecimal, List<Map<String, Object>>> mdseCombMap) {
    //END   2019/06/06 QC#50638 K.Fujimoto [MOD]

        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
        //S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMdseCombination(cMsg, mdlId, prntMdseCd, dsMdlConfigPk);
        List<Map<String, Object>> mdseCombination = NSAL0490Query.getInstance().getMdseCombination(cMsg, mdlId, prntMdseCd, dsMdlConfigPk, mdseCombMap);
        if (mdseCombination == null) {
            return new ArrayList<List<Map<String, Object>>>();
        }
        
        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]

        //START 2019/06/06 QC#50638 K.Fujimoto [DEL]
        //if (ssmResult.isCodeNotFound()) {
        //    return new ArrayList<List<Map<String, Object>>>();
        //}
        //List<Map<String, Object>> mdseCombination = (List<Map<String, Object>>) ssmResult.getResultObject();
        //END   2019/06/06 QC#50638 K.Fujimoto [DEL]

        // get total number of date
        List<String> allDateList = new ArrayList<String>();
        List<String> startDateList = new ArrayList<String>();
        List<String> endDateList = new ArrayList<String>();

        for (Map<String, Object> map : mdseCombination) {
            allDateList.add((String) map.get("EFF_FROM_DT"));
            allDateList.add(getMaxDate((String) map.get("EFF_THRU_DT")));
            startDateList.add((String) map.get("EFF_FROM_DT"));
            endDateList.add(getMaxDate((String) map.get("EFF_THRU_DT")));
        }

        // duplicate delete and sort
        allDateList = new ArrayList<String>(new LinkedHashSet<String>(allDateList));
        startDateList = new ArrayList<String>(new LinkedHashSet<String>(startDateList));
        endDateList = new ArrayList<String>(new LinkedHashSet<String>(endDateList));
        Collections.sort(allDateList);
        Collections.sort(startDateList);
        Collections.sort(endDateList);

        String targetStartDate = allDateList.get(0);
        String targetEndDate = allDateList.get(0);

        List<List<Map<String, Object>>> mdseCombinationList = new ArrayList<List<Map<String, Object>>>();

        for (int dtCnt = 0; dtCnt < startDateList.size() + endDateList.size() - 1; dtCnt++) {
            // get target end data
            if (endDateList.indexOf(targetStartDate) != -1) {
                targetEndDate = targetStartDate;
            } else {
                String date = null;
                for (int i = 0; i < allDateList.size(); i++) {
                    date = allDateList.get(i);
                    if (ZYPDateUtil.compare(date, targetEndDate) == 1) {
                        break;
                    }
                }

                if (startDateList.indexOf(date) != -1) {
                    targetEndDate = ZYPDateUtil.addDays(date, -1);
                } else {
                    targetEndDate = date;
                }
            }

            // set target List
            List<Map<String, Object>> targetList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : mdseCombination) {
                String startDate = (String) map.get("EFF_FROM_DT");
                String endDate = getMaxDate((String) map.get("EFF_THRU_DT"));

                if (ZYPDateUtil.compare(startDate, targetStartDate) != 1 && ZYPDateUtil.compare(targetStartDate, endDate) != 1) {
                    if (ZYPDateUtil.compare(startDate, targetEndDate) != 1 && ZYPDateUtil.compare(targetEndDate, endDate) != 1) {
                        Map<String, Object> cloneMap = new HashMap<String, Object>(map);
                        cloneMap.put("EFF_FROM_DT", targetStartDate);
                        cloneMap.put("EFF_THRU_DT", targetEndDate);
                        targetList.add(cloneMap);
                    }
                }
            }

            if (targetList.size() > 0) {
                mdseCombinationList.add(targetList);
            }

            targetStartDate = ZYPDateUtil.addDays(targetEndDate, 1);
            targetEndDate = targetStartDate;
        }

        return mdseCombinationList;
    }

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
   /**
    * check Exist ORD MDSE
    * @param cMsg NSAL0490CMsg
    * @param targetMdseCd target MDSE Code
    * @return true : Exist
    */
    public static boolean isExistOrdMdseCd(NSAL0490CMsg cMsg, String targetMdseCd) {

        ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdse(cMsg, targetMdseCd);

        if (tMsg == null) {
            return false;
        }

        return true;
    }

    //private static String getOrdTakeMsdeCd(NSAL0490CMsg cMsg, String prntMdseCd) {
    private static String getOrdTakeMsdeCd(NSAL0490CMsg cMsg, String prntMdseCd, HashSet<String> ordTakeMdseSet) {
    //END   2019/06/05 QC#50638[MOD]
        String rtnOrdTakeMsdeCd = null;
        if (!ZYPCommonFunc.hasValue(prntMdseCd)) {
            return null;
        }
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        // START 2016/07/13 T.Tomita [QC#11801, MOD]
//        tMsg.ordTakeMdseCd.setValue(prntMdseCd.substring(0, NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT));
        if (prntMdseCd.length() > NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, prntMdseCd.substring(0, NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, prntMdseCd);
        }
        // END 2016/07/13 T.Tomita [QC#11801, MOD]
        //START 2019/06/05 QC#50638[MOD]
        if (ordTakeMdseSet.contains(tMsg.ordTakeMdseCd.getValue())) {
            rtnOrdTakeMsdeCd = tMsg.ordTakeMdseCd.getValue();
        } else {
            tMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                rtnOrdTakeMsdeCd = tMsg.ordTakeMdseCd.getValue();
                ordTakeMdseSet.add(rtnOrdTakeMsdeCd);
            }
        }
        //END 2019/06/05 QC#50638[MOD]
        return rtnOrdTakeMsdeCd;
    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    // add start 2015/11/25 CSA Defect#1152
    /**
     * get ORD_TAKE_MDSE
     * @param cMsg NSAL0490CMsg
     * @param targetMdseCd target MDSE Code
     * @return ORD_TAKE_MDSETMsg : result
     */
     public static ORD_TAKE_MDSETMsg getOrdTakeMdse(NSAL0490CMsg cMsg, String targetMdseCd) {

         // add start 2015/11/25 CSA Defect#1150
         if (NSAL0490Constant.ORD_TAKE_MDSE_CD_DIGIT < targetMdseCd.length()) {
             return null;
         }
         // add end 2015/11/25 CSA Defect#1150

         ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
         tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
         tMsg.ordTakeMdseCd.setValue(targetMdseCd);
         return (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKey(tMsg);

     }
     // add end 2015/11/25 CSA Defect#1152

    // add start 2016/05/09 CSA Defect#8055
    private static Logger logger = Logger.getLogger(NSAL0490CommonLogic.class);

    /**
    * invokeMasterDataMessaging
    * @param isExistsModel boolean
    * @param ezUpTime String
    * @param mdlId BigDecimal
    * @param mdlNm String
    */
    public static void invokeMasterDataMessaging(boolean isExistsModel, String ezUpTime, BigDecimal mdlId, String mdlNm) {

        try {
            Event event = new Event();
            event.setReferencedEntity(NSAL0490Constant.REF_ENTITY_ITEM);

            if (isExistsModel) {
                event.setOperationType(OPERATIONTYPES.UPDATE);
            } else {
                event.setOperationType(OPERATIONTYPES.CREATE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            Date formatDate = sdf.parse(ezUpTime);

            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();

            // Set Key Model ID
            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();
            Attribute a = new Attribute();
            a.setName(NSAL0490Constant.KEY_NAME_MDL_ID);
            a.setValue(mdlId.toString());
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            // Set Key Model Name
            kr = new KeyReference();
            kr.setType(REFERENCETYPES.MNEMONICAL);

            aa = new ArrayOfAttribute();
            a = new Attribute();
            a.setName(NSAL0490Constant.KEY_NAME_T_MDL_NM);
            a.setValue(mdlNm);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void printRequest(ArrayOfEvent ae) throws JAXBException {

        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfEvent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent", "ArrayOfEvent");
        JAXBElement<ArrayOfEvent> root = new JAXBElement<ArrayOfEvent>(qName, ArrayOfEvent.class, ae);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();

        logger.debug(result);
    }
    // add end 2016/05/09 CSA Defect#8055
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * get Criticality Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    public static boolean getCriticalityData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {
        // get Master Data for pulldown.
        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getCriticalityMstList(cMsg);
        if (ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);
            cMsg.setMessageInfo(NSAL0490Constant.NZZM0000E);
            return false;
        }
        List<Map<String, Object>> critMstList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (critMstList.size() > 0) {
            for (int j = 0; j < critMstList.size(); j++) {
                Map<String, Object> map = (Map<String, Object>) critMstList.get(j);
                ZYPEZDItemValueSetter.setValue(cMsg.backOrdImpctTpCd_PL.no(j), (String) map.get("BACK_ORD_IMPCT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.backOrdImpctTpDescTxt_PL.no(j), (String) map.get("BACK_ORD_IMPCT_TP_DESC_TXT"));
            }
        }

        // get Registered Item and Model's criticality.
        ssmResult = NSAL0490Query.getInstance().getMdlCriticality(cMsg);

        List<Map<String, Object>> mdlCritList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int validCnt = 0;
        if (mdlCritList.size() > 0) {
            for (Map<String, Object> result : mdlCritList) {
                NSAL0490_DSMsg mdlCrit = sMsg.D.no(validCnt++);
                ZYPEZDItemValueSetter.setValue(mdlCrit.mdseCd_D, (String) result.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(mdlCrit.mdseDescShortTxt_D, (String) result.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(mdlCrit.backOrdImpctTpDescTxt_D1, (String) result.get("BACK_ORD_IMPCT_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(mdlCrit.backOrdImpctTpCd_D2, (String) result.get("MDL_BACK_ORD_IMPCT_TP_CD"));
                sMsg.D.setValidCount(validCnt);
            }
        }
        int i = 0;
        for (; i < sMsg.D.getValidCount(); i++) {
            if (i == cMsg.D.length()) {
                break;
            }
            EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
        }
        cMsg.D.setValidCount(i);

        return true;
    }
   //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
}
