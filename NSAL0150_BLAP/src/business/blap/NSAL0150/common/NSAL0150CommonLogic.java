/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0150.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMessageEnv;
import parts.common.EZDMessageInfo;
import business.blap.NSAL0150.NSAL0150CMsg;
import business.blap.NSAL0150.NSAL0150Query;
import business.blap.NSAL0150.NSAL0150SMsg;
import business.blap.NSAL0150.NSAL0150_ESMsgArray;
import business.blap.NSAL0150.constant.NSAL0150Constant;
import business.db.DS_MTR_READ_TPTMsg;
import business.file.NSAL0150F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2016/03/24   Hitachi         K.Yamada        Update          QC#4402
 * 2016/07/07   Hitachi         K.Kishimoto     Update          QC#11466
 * 2016/10/03   Hitachi         K.Kishimoto     Update          QC#12274
 * 2017/02/15   Hitachi         T.Mizuki        Update          QC#17489
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/15   Hitachi         K.Kojima        Update          QC#21125
 * 2017/10/06   Hitachi         U.Kim           Update          QC#21125
 * 2017/12/13   Hitachi         M.Kidokoro      Update          QC#21681
 * 2017/12/20   Hitachi         M.Kidokoro      Update          QC#21127
 * 2018/06/11   Hitachi         U.Kim           Update          QC#22480
 * 2018/06/25   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/06   Hitachi         T.Tomita        Update          QC#26972
 * 2018/07/23   Hitachi         K.Kitachi       Update          QC#27109
 * 2019/02/18   Hitachi         S.Kitamura      Update          QC#30339
 * 2019/03/27   Hitachi         T.Tomita        Update          QC#30791
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 *</pre>
 */
public class NSAL0150CommonLogic {

    /**
     * Reset parameter
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     */
    public static void resetParameter(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk.getValue();
        String serNum = cMsg.serNum.getValue();
        String mdseCd = cMsg.mdseCd.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.E);

        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, mdseCd);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.E);
    }

    /**
     * Check parameter
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     */
    public static void checkParameter(NSAL0150CMsg cMsg) {
        if (!isValidSvcMachMstrPrmCmbn(cMsg.svcMachMstrPk.getValue(), cMsg.serNum.getValue(), cMsg.mdseCd.getValue())) {
            cMsg.setMessageInfo(NSAL0150Constant.NSZM0006E);
            EZDDebugOutput.println(3, String.format("svcMachMstrPk=%s, serNum=%s, mdseCd=%s", cMsg.svcMachMstrPk.getValue(), cMsg.serNum.getValue(), cMsg.mdseCd.getValue()), NSAL0150CommonLogic.class);
        }
    }

    static boolean isValidSvcMachMstrPrmCmbn(BigDecimal svcMachMstrPk, String serNum, String mdseCd) {
        return (ZYPCommonFunc.hasValue(svcMachMstrPk) || (ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(mdseCd)));
    }

    /**
     * Check if NSAL0150CMsg has error
     * @param cMsg NSAL0150CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean hasError(NSAL0150CMsg cMsg) {
        if ("E".equals(cMsg.getMessageKind())) {
            return true;
        }
        try {
            Field field = NSAL0150CMsg.class.getSuperclass().getDeclaredField("errorHash");
            field.setAccessible(true);
            Map<String, EZDMessageInfo> errorHash = (Map<String, EZDMessageInfo>) field.get(cMsg);
            // Add Start 07/07/2016 <QC#11466>
            if (errorHash == null) {
                return false;
            }
            // Add End   07/07/2016 <QC#11466>
            return !errorHash.isEmpty();
        } catch (Exception e) {
            throw new S21AbendException(e);
        }
    }

    // START 2016/03/24 K.Yamada [QC#4402, MOD]
    public static void checkSubmitParameter(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        boolean read = isMtrRead(cMsg);
        boolean changeValid = isChangeValid(sMsg);
        // START 2019/04/09 K.Kitachi [QC#31089, ADD]
        boolean changeComment = isChangeComment(cMsg);
        // END 2019/04/09 K.Kitachi [QC#31089, ADD]
        // START 2019/04/09 K.Kitachi [QC#31089, MOD]
//        if (read || (!read && !changeValid)) {
        if (read || (!read && !changeValid && !changeComment)) {
        // END 2019/04/09 K.Kitachi [QC#31089, MOD]
            if (!ZYPCommonFunc.hasValue(cMsg.B.no(0).mtrReadDt_B)) {
                cMsg.B.no(0).mtrReadDt_B.setErrorInfo(1, NSAL0150Constant.ZZZM9025E, new String[] {"Date" });
                cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
            }
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).readMtrCnt_B)) {
                    cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.ZZZM9025E, new String[] {"New Read" });
                    cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                    continue;
                }
                // START 2017/12/13 M.Kidokoro [QC#21681, ADD]
                // START 2020/03/03 [QC#56123, MOD]
                String inpMtrReadTpGrpCd = cMsg.dsMtrReadTpGrpCd_BS.getValue();
                if ((DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(inpMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(inpMtrReadTpGrpCd)) && 
                        ZYPConstant.FLG_ON_Y.equals(NSAL0150Query.getInstance()
                        .getInvalidMtrFlg(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode(), cMsg.svcMachMstrPk.getValue(), cMsg.B.no(i).mtrReadDt_B.getValue()))) {
                    cMsg.B.no(i).mtrReadDt_B.setErrorInfo(1, NSAL0150Constant.NSAM0709E);
                    cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                    continue;
                }
                // END   2020/03/03 [QC#56123, MOD]
                // END 2017/12/13 M.Kidokoro [QC#21681, ADD]
                // START 2017/12/20 M.Kidokoro [QC#21127, ADD]
                // START 2020/03/03 [QC#56123, MOD]
                int prevMtrIdx = -1;
                int nextMtrIdx = -1;
                String inpMtrReadDt = cMsg.B.no(0).mtrReadDt_B.getValue();
                if (!ZYPCommonFunc.hasValue(inpMtrReadDt)) {
                    continue;
                }
                for (int cnt = 0; cnt < sMsg.E.getValidCount(); cnt++) {
                    if ((cMsg.B.no(i).mtrLbCd_B.getValue().equals(sMsg.E.no(cnt).mtrLbCd_E.getValue())) && (ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(cnt).vldMtrFlg_E.getValue()))) {
                        // START 2018/07/23 K.Kitachi [QC#27109, ADD]
                        if (!hasValue(cMsg.B.no(i).mtrReadDt_B) || !hasValue(cMsg.B.no(i).readMtrCnt_B)) {
                            continue;
                        }
                        // END 2018/07/23 K.Kitachi [QC#27109, ADD]
//                        if ((cMsg.B.no(i).mtrReadDt_B.getValue().compareTo(sMsg.E.no(cnt).mtrReadDt_E.getValue()) < 0) && (cMsg.B.no(i).readMtrCnt_B.getValue().compareTo(sMsg.E.no(cnt).readMtrCnt_E.getValue()) > 0)) {
//                            cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSZM1312E);
//                            break;
//                        }
                        String lstMtrReadTpGrpCd = sMsg.E.no(cnt).dsMtrReadTpGrpCd_E.getValue();
                        if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(inpMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(inpMtrReadTpGrpCd)) {
                            if (!DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(lstMtrReadTpGrpCd) && !DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(lstMtrReadTpGrpCd)) {
                                continue;
                            }
                        } else {
                            if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(lstMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(lstMtrReadTpGrpCd)) {
                                continue;
                            }
                        }
                        String lstMtrReadDt = sMsg.E.no(cnt).mtrReadDt_E.getValue();
                        if (inpMtrReadDt.compareTo(lstMtrReadDt) < 0) {
                            nextMtrIdx = cnt;
                        } else {
                            if (prevMtrIdx == -1) {
                                prevMtrIdx = cnt;
                            }
                        }
                    }
                }
                BigDecimal inpMtrCount = cMsg.B.no(i).readMtrCnt_B.getValue();
                String inpMtrReadTpCd = cMsg.B.no(i).dsMtrReadTpCd_B.getValue();
                String adjFlg = ZYPConstant.FLG_OFF_N;
                DS_MTR_READ_TPTMsg inpMtrReadTpTMsg = NSAL0150Query.getInstance().getDsMtrReadTpTMsg(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode(), inpMtrReadTpCd);
                if (inpMtrReadTpTMsg != null) {
                    adjFlg = inpMtrReadTpTMsg.adjMtrReadTpFlg.getValue();
                }
                if (prevMtrIdx != -1 && ZYPConstant.FLG_OFF_N.equals(adjFlg)) {
                    BigDecimal prevMtrCnt = sMsg.E.no(prevMtrIdx).readMtrCnt_E.getValue();
                    if (prevMtrCnt.compareTo(inpMtrCount) > 0 && !DS_MTR_READ_TP.ROLLOVER.equals(inpMtrReadTpCd)) {
                        cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSAM0516E);
                        cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                        continue;
                    }
                    if (prevMtrCnt.compareTo(inpMtrCount) <= 0 && DS_MTR_READ_TP.ROLLOVER.equals(inpMtrReadTpCd)) {
                        cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSZM1370E);
                        cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                        continue;
                    }
                }
                if (nextMtrIdx != -1) {
                    BigDecimal nextMtrCnt = sMsg.E.no(nextMtrIdx).readMtrCnt_E.getValue();
                    if (nextMtrCnt.compareTo(inpMtrCount) < 0) {
                        cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSZM1312E);
                        cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                        continue;
                    }
                }
                // END   2020/03/03 [QC#56123, MOD]
                // END 2017/12/20 M.Kidokoro [QC#21127, ADD]
            }
            //Add Start 2016/10/03 <QC#12274>
            if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(cMsg.dsMtrReadTpGrpCd_BS.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.svcTaskNum_B)) {
                    cMsg.svcTaskNum_B.setErrorInfo(1, NSAL0150Constant.ZZZM9025E, new String[] {"Task Number" });
                    cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                }
                if (!ZYPCommonFunc.hasValue(cMsg.dsTestCopyClsCd_BS)) {
                    cMsg.dsTestCopyClsCd_BS.setErrorInfo(1, NSAL0150Constant.ZZZM9025E, new String[] {"In/Out Flag" });
                    cMsg.setMessageInfo(NSAL0150Constant.ZZM9037E);
                }
                if (ZYPCommonFunc.hasValue(cMsg.svcTaskNum_B)) {
                    Map<String, Object> fsrVisitInfo = NSAL0150Query.getInstance().getFsrVisitInfo(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode(), cMsg.svcTaskNum_B.getValue(), cMsg.svcMachMstrPk.getValue());
                    if (fsrVisitInfo == null) {
                        // mod start 2017/02/15 CSA QC#17489
//                        cMsg.svcTaskNum_B.setErrorInfo(1, NSAL0150Constant.NSAM0063E, new String[] {"Task Number", "FSR" });
                        // mod end 2017/02/15 CSA QC#17489
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.fsrNum_B, (String) fsrVisitInfo.get("FSR_NUM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.fsrVisitNum_B, (String) fsrVisitInfo.get("FSR_VISIT_NUM"));
                    }
                }
            }
            //Add End   2016/10/03 <QC#12274>
        }
    }
    // END 2016/03/24 K.Yamada [QC#4402, MOD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * Check meter read warning
     * @param cMsg NSAL0150CMsg
     * @return boolean
     */
    public static boolean checkMeterReadWrn(NSAL0150CMsg cMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_MR.getValue())) {
            return false;
        }

        // check Final Meter Read
        String inpMtrReadTpGrpCd = cMsg.dsMtrReadTpGrpCd_BS.getValue();
        boolean existWrn = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String inpMtrReadDt = cMsg.B.no(0).mtrReadDt_B.getValue();
            if (!ZYPCommonFunc.hasValue(inpMtrReadDt)) {
                continue;
            }
            if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(inpMtrReadTpGrpCd)) {
                int resultMap = NSAL0150Query.getInstance().getFinalReadPeriod(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode(), cMsg.svcMachMstrPk.getValue(), inpMtrReadDt);
                String inpMtrReadTpCd = cMsg.B.no(i).dsMtrReadTpCd_B.getValue();
                if (DS_MTR_READ_TP.FINAL_METER_READING.equals(inpMtrReadTpCd) && resultMap == 0) {
                    existWrn = true;
                    cMsg.setMessageInfo(NSAL0150Constant.NSAM0786W);
                    cMsg.B.no(i).dsMtrReadTpCd_B.setErrorInfo(2, NSAL0150Constant.NSAM0786W);
                }
                if (!DS_MTR_READ_TP.FINAL_METER_READING.equals(inpMtrReadTpCd) && resultMap != 0) {
                    existWrn = true;
                    cMsg.setMessageInfo(NSAL0150Constant.NSAM0787W);
                    cMsg.B.no(i).dsMtrReadTpCd_B.setErrorInfo(2, NSAL0150Constant.NSAM0787W);
                }
            }
        }

        if (existWrn) {
            cMsg.xxWrnSkipFlg_MR.setValue(ZYPConstant.FLG_ON_Y);
            return true;
        }
        return false;
    }
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]

    public static void setupSubmitParameter(NSAL0150CMsg cMsg) {
        boolean read = isMtrRead(cMsg);
        if (read) {
            // del start 2017/09/07 QC#15134
            //String dsMtrReadTpCd = cMsg.B.no(0).dsMtrReadTpCd_B.getValue();
            // del end 2017/09/07 QC#15134
            String mtrReadDt = cMsg.B.no(0).mtrReadDt_B.getValue();
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                // del start 2017/09/07 QC#15134
                //ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsMtrReadTpCd_B, dsMtrReadTpCd);
                // del end 2017/09/07 QC#15134
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, mtrReadDt);
            }
        }
    }

    public static boolean isMtrRead(NSAL0150CMsg cMsg) {
        boolean read = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).readMtrCnt_B)) {
                read = true;
            }
        }
        return read;
    }

    // START 2016/03/24 K.Yamada [QC#4402, ADD]
    private static boolean isChangeValid(NSAL0150SMsg sMsg) {
        boolean change = false;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            if (!isEqualStr(sMsg.E.no(i).vldMtrFlg_E.getValue(), sMsg.E.no(i).vldMtrFlg_OG.getValue())) {
                change = true;
            }
        }
        return change;
    }
    // END 2016/03/24 K.Yamada [QC#4402, ADD]

    // START 2019/04/09 K.Kitachi [QC#31089, ADD]
    private static boolean isChangeComment(NSAL0150CMsg cMsg) {
        boolean change = false;
        if (!isEqualStr(cMsg.svcCmntTxt.getValue(), cMsg.svcCmntTxt_HD.getValue())) {
            change = true;
        }
        return change;
    }
    // END 2019/04/09 K.Kitachi [QC#31089, ADD]

    public static void copyCMsgToSMsg(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        BigDecimal preSq = NSAL0150Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ;
        String vldMtrFlg = null;
        for (int c = 0; c < cMsg.E.getValidCount(); c++) {
            BigDecimal cSq = cMsg.E.no(c).svcPhysMtrReadGrpSq_E.getValue();
            if (isEqualNum(cSq, preSq)) {
                ZYPEZDItemValueSetter.setValue(cMsg.E.no(c).vldMtrFlg_E, vldMtrFlg);
            } else {
                String cMsgChk = cMsg.E.no(c).vldMtrFlg_E.getValue();
                for (int s = 0; s < sMsg.E.getValidCount(); s++) {
                    BigDecimal sSq = sMsg.E.no(s).svcPhysMtrReadGrpSq_E.getValue();
                    if (isEqualNum(cSq, sSq)) {
                        if (ZYPConstant.FLG_ON_Y.equals(cMsgChk)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).vldMtrFlg_E, ZYPConstant.FLG_ON_Y);
                        } else {
                            ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).vldMtrFlg_E, ZYPConstant.FLG_OFF_N);
                        }
                    }
                }
                preSq = cSq;
                vldMtrFlg = cMsgChk;
            }
        }
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEqualStr(String a, String b) {
        if (!ZYPCommonFunc.hasValue(a) && !ZYPCommonFunc.hasValue(b)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b) && ZYPCommonFunc.trimTail(a).equals(ZYPCommonFunc.trimTail(b))) {
            return true;
        }
        return false;
    }

    public static String getBizIdSlsDt() {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        return ZYPDateUtil.getSalesDate(glblCmpyCd, NSAL0150Constant.BIZ_ID);
    }

    // START 2017/09/15 K.Kojima [QC#21125,ADD]
    public static boolean checkInvalid(String glblCmpyCd, BigDecimal svcPhysMtrReadPk, NSAL0150_ESMsgArray esMsgArray, int checkLineNumber) {
        BigDecimal countInvalidTarget = NSAL0150Query.getInstance().countInvalidCheckTarget(glblCmpyCd, svcPhysMtrReadPk);
        if (BigDecimal.ZERO.compareTo(countInvalidTarget) == 0) {
            return true;
        }
        // START 2017/10/06 U.Kim [QC#21125,MOD]
        // for (int i = checkLineNumber + 1; i > 0; i--) {
        for (int i = checkLineNumber - 1; i > 0; i--) {
        // END 2017/10/06 U.Kim [QC#21125,MOD]
            if (!esMsgArray.no(i).vldMtrFlg_E.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                continue;
            }
            BigDecimal countDsContrBllgShed = NSAL0150Query.getInstance().countDsContrBllgShedForInvalidCheck(glblCmpyCd, esMsgArray.no(i).svcPhysMtrReadPk_E.getValue());
            if (BigDecimal.ZERO.compareTo(countDsContrBllgShed) < 0) {
                return false;
            }
        }
        return true;
    }
    // END 2017/09/15 K.Kojima [QC#21125,ADD]

    // START 2018/06/11 U.Kim [QC#22480, ADD]
    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }
    // END 2018/06/11 U.Kim [QC#22480, ADD]

    // START 2018/06/25 T.Ogura [QC#26336,ADD]
    /**
     * writeCsvFile
     * @param cMsg NSAL0150CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL0150CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0150F00FMsg fMsg = new NSAL0150F00FMsg();
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        /** CSV Header */
        String[] csvHeader = {"Serial#", "Item Code", "Model", "Counters", "Reading", "Type", "Valid", "Group#", "Date",
                "Meter Count", "Test Copies", "Comment", "Created By", "Creation Date(EST)", "Data Source", "Invoice#",
                "Invoice Date", "Task Number", "In/Out", "Bill To", "Bill To Location", "Install At", "Install Location"};
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            writeCsvLine(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }
        fileWriter.close();
    }
    // END   2018/06/25 T.Ogura [QC#26336,ADD]

    // START 2018/06/25 T.Ogura [QC#26336,ADD]
    /**
     * writeCsvLine
     * @param fMsg NSAL0150F00FMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private static void writeCsvLine(NSAL0150F00FMsg fMsg, ResultSet rs) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.fill65Txt, rs.getString("XX_MTR_READ_TP_GRP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsMtrReadTpDescTxt, rs.getString("DS_MTR_READ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.vldMtrFlg, rs.getString("VLD_MTR_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.fill5Txt, rs.getString("GRP_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RE, rs.getString("XX_READ_DT"));
        if (hasValue(rs.getBigDecimal("READ_MTR_CNT"))) {
            ZYPEZDItemValueSetter.setValue(fMsg.fill15Txt_ME, ZYPFormatUtil.formatNumToSysDisp(rs.getBigDecimal("READ_MTR_CNT")));
        }
        if (hasValue(rs.getBigDecimal("TEST_MTR_CNT"))) {
            ZYPEZDItemValueSetter.setValue(fMsg.fill15Txt_TE, ZYPFormatUtil.formatNumToSysDisp(rs.getBigDecimal("TEST_MTR_CNT")));
        }
        ZYPEZDItemValueSetter.setValue(fMsg.mtrEntryCmntTxt, rs.getString("MTR_ENTRY_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxEmpNmTxt, rs.getString("XX_CRAT_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm, rs.getString("XX_CRT_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.mtrReadSrcTpDescTxt, rs.getString("MTR_READ_SRC_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcInvNum, rs.getString("SVC_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_IN, rs.getString("XX_INV_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsTestCopyClsDescTxt, rs.getString("DS_TEST_COPY_CLS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.billToCustNm, rs.getString("BILL_TO_CUST_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_BI, rs.getString("BILL_TO_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(fMsg.curLocAcctNm, rs.getString("CUR_LOC_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_IN, rs.getString("CUR_LOC_ADDR"));
    }
    // END   2018/06/25 T.Ogura [QC#26336,ADD]
    // Add Start 2018/07/06 QC#26972
    public static void setSvcCmntTxt(String glblCmpyCd, NSAL0150CMsg cMsg) {
        // Clear
        cMsg.svcCmntTxt_PP.clear();

        // Set Value
        setCapOverInfo(glblCmpyCd, cMsg);
        setExistOpenOrdMsg(glblCmpyCd, cMsg);
    }

    private static void setCapOverInfo(String glblCmpyCd, NSAL0150CMsg cMsg) {
        if (!hasValue(cMsg.dsContrNum)) {
            return;
        }
        StringBuilder msgTxt = new StringBuilder(cMsg.svcCmntTxt_PP.getValue());
        // Mod Start 2019/03/27 QC#30791
        List<Map<String, Object>> svcTermCondArray = getSvcTermCond(glblCmpyCd, cMsg.dsContrPk_SP.getValue(), cMsg.dsContrDtlPk_SP.getValue(), cMsg.dsContrCatgCd_OH.getValue());
        // Mod End 2019/03/27 QC#30791
        String name;
        String value;
        for (Map<String, Object> svcTermCond : svcTermCondArray) {
            value = (String) svcTermCond.get("SVC_TERM_ATTRB_MAP_VAL_CD");
            if (!hasValue(value)) {
                continue;
            }
            name = (String) svcTermCond.get("SVC_TERM_ATTRB_DESC_TXT");
            if (msgTxt.length() == 0) {
                msgTxt.append("This is Cap status.");
                msgTxt.append(NSAL0150Constant.LINE_FEED);
            } else {
                msgTxt.append(NSAL0150Constant.LINE_FEED);
            }
            msgTxt.append(NSAL0150Constant.SPACE);
            msgTxt.append(NSAL0150Constant.SPACE);
            msgTxt.append(name);
            msgTxt.append(NSAL0150Constant.SPACE);
            msgTxt.append(NSAL0150Constant.COLON);
            msgTxt.append(NSAL0150Constant.SPACE);
            msgTxt.append(value);
        }

        if (msgTxt.length() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt_PP, editSvcCmnt(msgTxt.toString(), cMsg));
        }
    }

    // Mod Start 2019/03/27 QC#30791
    private static List<Map<String, Object>> getSvcTermCond(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String dsContrCatgCd) {
        String capAttrbPkList = ZYPCodeDataUtil.getVarCharConstValue(NSAL0150Constant.SVC_TERM_COND_CAP_ATTRB, glblCmpyCd);
        if (!hasValue(capAttrbPkList) || !hasValue(dsContrCatgCd)) {
            return new ArrayList<Map<String,Object>>();
        }

        List<BigDecimal> attrbPkList = new ArrayList<BigDecimal>();
        String[] capAttrbPkArray = capAttrbPkList.split(",");
        for (int i = 0; i < capAttrbPkArray.length; i++) {
            attrbPkList.add(new BigDecimal(capAttrbPkArray[i]));
        }

        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            resultList = NSAL0150Query.getInstance().getTermCondForContr(glblCmpyCd, dsContrPk, attrbPkList);
        } else {
            resultList = NSAL0150Query.getInstance().getTermCondForMach(glblCmpyCd, dsContrDtlPk, attrbPkList);
        }
        return resultList;
    }
    // Mod End 2019/03/27 QC#30791

    private static void setExistOpenOrdMsg(String glblCmpyCd, NSAL0150CMsg cMsg) {
        if (!hasValue(cMsg.dsContrNum)) {
            return;
        }

        StringBuilder msgTxt = new StringBuilder(cMsg.svcCmntTxt_PP.getValue());
        if (existOpenOrd(glblCmpyCd, cMsg)) {
            if (msgTxt.length() != 0) {
                msgTxt.append(NSAL0150Constant.LINE_FEED);
                msgTxt.append(NSAL0150Constant.LINE_FEED);
            }
            String msg = EZDMessageEnv.getMessage(NSAL0150Constant.NSAM0730W);
            msgTxt.append(msg);
        }

        if (msgTxt.length() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt_PP, editSvcCmnt(msgTxt.toString(), cMsg));
        }
    }

    private static boolean existOpenOrd(String glblCmpyCd, NSAL0150CMsg cMsg) {
        BigDecimal openOrdCnt = null;
        if (!hasValue(cMsg.dsContrCatgCd_OH)) {
            openOrdCnt = BigDecimal.ZERO;
        } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd_OH.getValue())) {
            openOrdCnt = NSAL0150Query.getInstance().getFleetSupplyOrderCnt(glblCmpyCd, cMsg.dsContrNum.getValue());
        } else {
            openOrdCnt = NSAL0150Query.getInstance().getMachineSupplyOrderCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        }

        if (!hasValue(openOrdCnt) || BigDecimal.ZERO.compareTo(openOrdCnt) == 0) {
            return false;
        }
        return true;
    }

    private static String editSvcCmnt(String msg, NSAL0150CMsg cMsg) {
        String rtnMsg = msg;
        if (!hasValue(msg)) {
            return null;
        }
        int cmntLen = cMsg.getAttr("svcCmntTxt_PP").getDigit();
        if (cmntLen < rtnMsg.length()) {
            rtnMsg = rtnMsg.substring(0, cmntLen);
        }
        return rtnMsg;
    }
    // Add End 2018/07/06 QC#26972
    // START 2019/02/18 S.Kitamura [QC#30339,ADD]
    public static boolean hasCapSvcTermCond(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String dsContrCatgCd){
        NSAL0150CMsg cMsg = new NSAL0150CMsg();
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd_OH, dsContrCatgCd);
        // Mod Start 2019/03/27 QC#30791
        List<Map<String, Object>> svcTermCondArray = getSvcTermCond(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.dsContrCatgCd_OH.getValue());
        // Mod Start 2019/03/27 QC#30791
        for (Map<String, Object> svcTermCond : svcTermCondArray) {
            String value = (String) svcTermCond.get("SVC_TERM_ATTRB_MAP_VAL_CD");
            if (hasValue(value)) {
                return true;
            }
        }
        return false;
    }
    // END 2019/02/18 S.Kitamura [QC#30339,ADD]

    // START 2024/04/08 S.Moriai [QC#63540, ADD]
    /**
     * beforeDate <= afterDate : true<br>
     * beforeDate > afterDate : false
     * 
     * @param beforeDate
     * @param afterDate
     */
    public static boolean compareDate(String beforeDate, String afterDate){
         if (ZYPDateUtil.compare(beforeDate, afterDate) == 0) {
              return true;
         } else if (ZYPDateUtil.compare(beforeDate, afterDate) == -1) {
              return true;
         } else {
             return false;
         }
    }
    // END 2024/04/08 S.Moriai [QC#63540, ADD]
}
