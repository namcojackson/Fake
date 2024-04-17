package business.blap.NPAL1340.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NPAL1340.NPAL1340CMsg;
import business.blap.NPAL1340.NPAL1340Query;
import business.blap.NPAL1340.NPAL1340SMsg;
import business.blap.NPAL1340.NPAL1340_ACMsgArray;
import business.blap.NPAL1340.NPAL1340_ASMsg;
import business.blap.NPAL1340.NPAL1340_ASMsgArray;
import business.blap.NPAL1340.NPAL1340_NCMsg;
import business.blap.NPAL1340.NPAL1340_NCMsgArray;
import business.blap.NPAL1340.NPAL1340_NSMsg;
import business.blap.NPAL1340.NPAL1340_NSMsgArray;
import business.blap.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 09/20/2017   CITS            T.Tokutomi      Update          QC#21191
 * 06/25/2020   CITS            M.Furugoori     Update          QC#56979
 *</pre>
 */
public class NPAL1340CommonLogic implements NPAL1340Constant{

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NPAL1340CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {

        NPAL1340_ACMsgArray bizMsgAry_A = bizMsg.A;
        NPAL1340_NCMsgArray bizMsgAry_N = bizMsg.N;

        NPAL1340_ASMsgArray shareMsgAry_A = globalMsg.A;
        NPAL1340_NSMsgArray shareMsgAry_N = globalMsg.N;

        ZYPTableUtil.clear(bizMsgAry_A);
        ZYPTableUtil.clear(bizMsgAry_N);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && startIndex + dispRowNum < shareMsgAry_A.getValidCount(); dispRowNum++) {
            // START 2020/06/25 [QC#56979,ADD]
            if (ZYPCommonFunc.hasValue(shareMsgAry_A.no(startIndex + dispRowNum).proNum_A1) && ZYPCommonFunc.hasValue(shareMsgAry_A.no(startIndex + dispRowNum).carrTrkUrl_A1)) {
                editCarrTrkUrl(shareMsgAry_A, startIndex + dispRowNum);
            }
            // END 2020/06/25 [QC#56979,ADD]
            EZDMsg.copy(shareMsgAry_A.get(startIndex + dispRowNum), null, bizMsgAry_A.get(dispRowNum), null);
            EZDMsg.copy(shareMsgAry_N.get(startIndex + dispRowNum), null, bizMsgAry_N.get(dispRowNum), null);
        }
        bizMsgAry_A.setValidCount(dispRowNum);
        bizMsgAry_N.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry_A.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        NPAL1340_ACMsgArray bizMsgAry_A = bizMsg.A;
        NPAL1340_NCMsgArray bizMsgAry_N = bizMsg.N;

        NPAL1340_ASMsgArray shareMsgAry_A = globalMsg.A;
        NPAL1340_NSMsgArray shareMsgAry_N = globalMsg.N;

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && dispRowNum < bizMsgAry_A.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry_A.get(dispRowNum), null, shareMsgAry_A.get(startIndex + dispRowNum), null);
            EZDMsg.copy(bizMsgAry_N.get(dispRowNum), null, shareMsgAry_N.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void prevPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Next Page
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void nextPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Last Page
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void lastPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /**
     * <pre>
     * get Count of Serial#
     * </pre>
     * @param ncMsg
     * @return
     */
    public static BigDecimal getSerialCount(NPAL1340_NCMsg ncMsg) {
        List<EZDCStringItem> serNumList // = Arrays
        = Arrays.asList(
                ncMsg.serNum_A0, ncMsg.serNum_A1, ncMsg.serNum_A2, ncMsg.serNum_A3, ncMsg.serNum_A4, ncMsg.serNum_A5, ncMsg.serNum_A6, ncMsg.serNum_A7, ncMsg.serNum_A8, ncMsg.serNum_A9,
                ncMsg.serNum_B0, ncMsg.serNum_B1, ncMsg.serNum_B2, ncMsg.serNum_B3, ncMsg.serNum_B4, ncMsg.serNum_B5, ncMsg.serNum_B6, ncMsg.serNum_B7, ncMsg.serNum_B8, ncMsg.serNum_B9,
                ncMsg.serNum_C0, ncMsg.serNum_C1, ncMsg.serNum_C2, ncMsg.serNum_C3, ncMsg.serNum_C4, ncMsg.serNum_C5, ncMsg.serNum_C6, ncMsg.serNum_C7, ncMsg.serNum_C8, ncMsg.serNum_C9,
                ncMsg.serNum_D0, ncMsg.serNum_D1, ncMsg.serNum_D2, ncMsg.serNum_D3, ncMsg.serNum_D4, ncMsg.serNum_D5, ncMsg.serNum_D6, ncMsg.serNum_D7, ncMsg.serNum_D8, ncMsg.serNum_D9,
                ncMsg.serNum_E0, ncMsg.serNum_E1, ncMsg.serNum_E2, ncMsg.serNum_E3, ncMsg.serNum_E4, ncMsg.serNum_E5, ncMsg.serNum_E6, ncMsg.serNum_E7, ncMsg.serNum_E8, ncMsg.serNum_E9,
                ncMsg.serNum_F0, ncMsg.serNum_F1, ncMsg.serNum_F2, ncMsg.serNum_F3, ncMsg.serNum_F4, ncMsg.serNum_F5, ncMsg.serNum_F6, ncMsg.serNum_F7, ncMsg.serNum_F8, ncMsg.serNum_F9,
                ncMsg.serNum_G0, ncMsg.serNum_G1, ncMsg.serNum_G2, ncMsg.serNum_G3, ncMsg.serNum_G4, ncMsg.serNum_G5, ncMsg.serNum_G6, ncMsg.serNum_G7, ncMsg.serNum_G8, ncMsg.serNum_G9,
                ncMsg.serNum_H0, ncMsg.serNum_H1, ncMsg.serNum_H2, ncMsg.serNum_H3, ncMsg.serNum_H4, ncMsg.serNum_H5, ncMsg.serNum_H6, ncMsg.serNum_H7, ncMsg.serNum_H8, ncMsg.serNum_H9,
                ncMsg.serNum_I0, ncMsg.serNum_I1, ncMsg.serNum_I2, ncMsg.serNum_I3, ncMsg.serNum_I4, ncMsg.serNum_I5, ncMsg.serNum_I6, ncMsg.serNum_I7, ncMsg.serNum_I8, ncMsg.serNum_I9,
                ncMsg.serNum_J0, ncMsg.serNum_J1, ncMsg.serNum_J2, ncMsg.serNum_J3, ncMsg.serNum_J4, ncMsg.serNum_J5, ncMsg.serNum_J6, ncMsg.serNum_J7, ncMsg.serNum_J8, ncMsg.serNum_J9,
                ncMsg.serNum_K0, ncMsg.serNum_K1, ncMsg.serNum_K2, ncMsg.serNum_K3, ncMsg.serNum_K4, ncMsg.serNum_K5, ncMsg.serNum_K6, ncMsg.serNum_K7, ncMsg.serNum_K8, ncMsg.serNum_K9,
                ncMsg.serNum_L0, ncMsg.serNum_L1, ncMsg.serNum_L2, ncMsg.serNum_L3, ncMsg.serNum_L4, ncMsg.serNum_L5, ncMsg.serNum_L6, ncMsg.serNum_L7, ncMsg.serNum_L8, ncMsg.serNum_L9,
                ncMsg.serNum_M0, ncMsg.serNum_M1, ncMsg.serNum_M2, ncMsg.serNum_M3, ncMsg.serNum_M4, ncMsg.serNum_M5, ncMsg.serNum_M6, ncMsg.serNum_M7, ncMsg.serNum_M8, ncMsg.serNum_M9,
                ncMsg.serNum_N0, ncMsg.serNum_N1, ncMsg.serNum_N2, ncMsg.serNum_N3, ncMsg.serNum_N4, ncMsg.serNum_N5, ncMsg.serNum_N6, ncMsg.serNum_N7, ncMsg.serNum_N8, ncMsg.serNum_N9,
                ncMsg.serNum_O0, ncMsg.serNum_O1, ncMsg.serNum_O2, ncMsg.serNum_O3, ncMsg.serNum_O4, ncMsg.serNum_O5, ncMsg.serNum_O6, ncMsg.serNum_O7, ncMsg.serNum_O8, ncMsg.serNum_O9,
                ncMsg.serNum_P0, ncMsg.serNum_P1, ncMsg.serNum_P2, ncMsg.serNum_P3, ncMsg.serNum_P4, ncMsg.serNum_P5, ncMsg.serNum_P6, ncMsg.serNum_P7, ncMsg.serNum_P8, ncMsg.serNum_P9,
                ncMsg.serNum_Q0, ncMsg.serNum_Q1, ncMsg.serNum_Q2, ncMsg.serNum_Q3, ncMsg.serNum_Q4, ncMsg.serNum_Q5, ncMsg.serNum_Q6, ncMsg.serNum_Q7, ncMsg.serNum_Q8, ncMsg.serNum_Q9,
                ncMsg.serNum_R0, ncMsg.serNum_R1, ncMsg.serNum_R2, ncMsg.serNum_R3, ncMsg.serNum_R4, ncMsg.serNum_R5, ncMsg.serNum_R6, ncMsg.serNum_R7, ncMsg.serNum_R8, ncMsg.serNum_R9,
                ncMsg.serNum_S0, ncMsg.serNum_S1, ncMsg.serNum_S2, ncMsg.serNum_S3, ncMsg.serNum_S4, ncMsg.serNum_S5, ncMsg.serNum_S6, ncMsg.serNum_S7, ncMsg.serNum_S8, ncMsg.serNum_S9,
                ncMsg.serNum_T0, ncMsg.serNum_T1, ncMsg.serNum_T2, ncMsg.serNum_T3, ncMsg.serNum_T4, ncMsg.serNum_T5, ncMsg.serNum_T6, ncMsg.serNum_T7, ncMsg.serNum_T8, ncMsg.serNum_T9);

        int i = 0;
        for (EZDCStringItem ser : serNumList) {
            if (ZYPCommonFunc.hasValue(ser)) {
                i++;
            }
        }
        return BigDecimal.valueOf(i);
    }

    /**
     * <pre>
     * get Count of Serial#
     * </pre>
     * @param nsMsg
     * @return
     */
    public static BigDecimal getSerialCount(NPAL1340_NSMsg nsMsg) {
        List<EZDSStringItem> serNumList // = Arrays
        = Arrays.asList(
                nsMsg.serNum_A0, nsMsg.serNum_A1, nsMsg.serNum_A2, nsMsg.serNum_A3, nsMsg.serNum_A4, nsMsg.serNum_A5, nsMsg.serNum_A6, nsMsg.serNum_A7, nsMsg.serNum_A8, nsMsg.serNum_A9,
                nsMsg.serNum_B0, nsMsg.serNum_B1, nsMsg.serNum_B2, nsMsg.serNum_B3, nsMsg.serNum_B4, nsMsg.serNum_B5, nsMsg.serNum_B6, nsMsg.serNum_B7, nsMsg.serNum_B8, nsMsg.serNum_B9,
                nsMsg.serNum_C0, nsMsg.serNum_C1, nsMsg.serNum_C2, nsMsg.serNum_C3, nsMsg.serNum_C4, nsMsg.serNum_C5, nsMsg.serNum_C6, nsMsg.serNum_C7, nsMsg.serNum_C8, nsMsg.serNum_C9,
                nsMsg.serNum_D0, nsMsg.serNum_D1, nsMsg.serNum_D2, nsMsg.serNum_D3, nsMsg.serNum_D4, nsMsg.serNum_D5, nsMsg.serNum_D6, nsMsg.serNum_D7, nsMsg.serNum_D8, nsMsg.serNum_D9,
                nsMsg.serNum_E0, nsMsg.serNum_E1, nsMsg.serNum_E2, nsMsg.serNum_E3, nsMsg.serNum_E4, nsMsg.serNum_E5, nsMsg.serNum_E6, nsMsg.serNum_E7, nsMsg.serNum_E8, nsMsg.serNum_E9,
                nsMsg.serNum_F0, nsMsg.serNum_F1, nsMsg.serNum_F2, nsMsg.serNum_F3, nsMsg.serNum_F4, nsMsg.serNum_F5, nsMsg.serNum_F6, nsMsg.serNum_F7, nsMsg.serNum_F8, nsMsg.serNum_F9,
                nsMsg.serNum_G0, nsMsg.serNum_G1, nsMsg.serNum_G2, nsMsg.serNum_G3, nsMsg.serNum_G4, nsMsg.serNum_G5, nsMsg.serNum_G6, nsMsg.serNum_G7, nsMsg.serNum_G8, nsMsg.serNum_G9,
                nsMsg.serNum_H0, nsMsg.serNum_H1, nsMsg.serNum_H2, nsMsg.serNum_H3, nsMsg.serNum_H4, nsMsg.serNum_H5, nsMsg.serNum_H6, nsMsg.serNum_H7, nsMsg.serNum_H8, nsMsg.serNum_H9,
                nsMsg.serNum_I0, nsMsg.serNum_I1, nsMsg.serNum_I2, nsMsg.serNum_I3, nsMsg.serNum_I4, nsMsg.serNum_I5, nsMsg.serNum_I6, nsMsg.serNum_I7, nsMsg.serNum_I8, nsMsg.serNum_I9,
                nsMsg.serNum_J0, nsMsg.serNum_J1, nsMsg.serNum_J2, nsMsg.serNum_J3, nsMsg.serNum_J4, nsMsg.serNum_J5, nsMsg.serNum_J6, nsMsg.serNum_J7, nsMsg.serNum_J8, nsMsg.serNum_J9,
                nsMsg.serNum_K0, nsMsg.serNum_K1, nsMsg.serNum_K2, nsMsg.serNum_K3, nsMsg.serNum_K4, nsMsg.serNum_K5, nsMsg.serNum_K6, nsMsg.serNum_K7, nsMsg.serNum_K8, nsMsg.serNum_K9,
                nsMsg.serNum_L0, nsMsg.serNum_L1, nsMsg.serNum_L2, nsMsg.serNum_L3, nsMsg.serNum_L4, nsMsg.serNum_L5, nsMsg.serNum_L6, nsMsg.serNum_L7, nsMsg.serNum_L8, nsMsg.serNum_L9,
                nsMsg.serNum_M0, nsMsg.serNum_M1, nsMsg.serNum_M2, nsMsg.serNum_M3, nsMsg.serNum_M4, nsMsg.serNum_M5, nsMsg.serNum_M6, nsMsg.serNum_M7, nsMsg.serNum_M8, nsMsg.serNum_M9,
                nsMsg.serNum_N0, nsMsg.serNum_N1, nsMsg.serNum_N2, nsMsg.serNum_N3, nsMsg.serNum_N4, nsMsg.serNum_N5, nsMsg.serNum_N6, nsMsg.serNum_N7, nsMsg.serNum_N8, nsMsg.serNum_N9,
                nsMsg.serNum_O0, nsMsg.serNum_O1, nsMsg.serNum_O2, nsMsg.serNum_O3, nsMsg.serNum_O4, nsMsg.serNum_O5, nsMsg.serNum_O6, nsMsg.serNum_O7, nsMsg.serNum_O8, nsMsg.serNum_O9,
                nsMsg.serNum_P0, nsMsg.serNum_P1, nsMsg.serNum_P2, nsMsg.serNum_P3, nsMsg.serNum_P4, nsMsg.serNum_P5, nsMsg.serNum_P6, nsMsg.serNum_P7, nsMsg.serNum_P8, nsMsg.serNum_P9,
                nsMsg.serNum_Q0, nsMsg.serNum_Q1, nsMsg.serNum_Q2, nsMsg.serNum_Q3, nsMsg.serNum_Q4, nsMsg.serNum_Q5, nsMsg.serNum_Q6, nsMsg.serNum_Q7, nsMsg.serNum_Q8, nsMsg.serNum_Q9,
                nsMsg.serNum_R0, nsMsg.serNum_R1, nsMsg.serNum_R2, nsMsg.serNum_R3, nsMsg.serNum_R4, nsMsg.serNum_R5, nsMsg.serNum_R6, nsMsg.serNum_R7, nsMsg.serNum_R8, nsMsg.serNum_R9,
                nsMsg.serNum_S0, nsMsg.serNum_S1, nsMsg.serNum_S2, nsMsg.serNum_S3, nsMsg.serNum_S4, nsMsg.serNum_S5, nsMsg.serNum_S6, nsMsg.serNum_S7, nsMsg.serNum_S8, nsMsg.serNum_S9,
                nsMsg.serNum_T0, nsMsg.serNum_T1, nsMsg.serNum_T2, nsMsg.serNum_T3, nsMsg.serNum_T4, nsMsg.serNum_T5, nsMsg.serNum_T6, nsMsg.serNum_T7, nsMsg.serNum_T8, nsMsg.serNum_T9);

        int i = 0;
        for (EZDSStringItem ser : serNumList) {
            if (ZYPCommonFunc.hasValue(ser)) {
                i++;
            }
        }
        return BigDecimal.valueOf(i);
    }

    /**
     * <pre>
     * get Count of Serial#
     * </pre>
     * @param ncMsg
     * @return
     */
    public static void setSerialList(String glblCmpyCd, NPAL1340SMsg globalMsg, int idx) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serOwnrId", DEF_DROP_SHIP_SER_OWNR_ID);
        ssmParam.put("mdseCd", globalMsg.A.no(idx).mdseCd_A1.getValue());
        ssmParam.put("cpoOrdNum", globalMsg.A.no(idx).cpoOrdNum_AH.getValue());
        ssmParam.put("cpoDtlLineNum", globalMsg.A.no(idx).cpoDtlLineNum_A1.getValue());
        ssmParam.put("cpoDtlLineSubNum", globalMsg.A.no(idx).cpoDtlLineSubNum_A1.getValue());
        ssmParam.put("poOrdNum", globalMsg.A.no(idx).poOrdNum_AH.getValue());
        ssmParam.put("poOrdDtlLineNum", globalMsg.A.no(idx).poOrdDtlLineNum_A1.getValue());

        S21SsmEZDResult ezdResult = NPAL1340Query.getInstance().searchPoSerialList(ssmParam);
        if (ezdResult.isCodeNormal()) {
            List<Map<String, String>> poNumberList = (List<Map<String, String>>) ezdResult.getResultObject();
            String[] serArr = poNumberList.toArray(new String[200]);
            BigDecimal[] rowNumArr = new BigDecimal[200];
            String[] editFlgArr = new String[200];
            for (int i = 0; i < serArr.length; i++) {
                if (ZYPCommonFunc.hasValue(serArr[i])) {
                    rowNumArr[i] = new BigDecimal(i + 1);
                    editFlgArr[i] = ZYPConstant.FLG_ON_Y;
                } else {
                    break;
                }
            }
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A0, serArr[0]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A1, serArr[1]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A2, serArr[2]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A3, serArr[3]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A4, serArr[4]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A5, serArr[5]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A6, serArr[6]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A7, serArr[7]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A8, serArr[8]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_A9, serArr[9]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B0, serArr[10]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B1, serArr[11]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B2, serArr[12]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B3, serArr[13]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B4, serArr[14]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B5, serArr[15]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B6, serArr[16]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B7, serArr[17]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B8, serArr[18]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_B9, serArr[19]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C0, serArr[20]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C1, serArr[21]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C2, serArr[22]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C3, serArr[23]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C4, serArr[24]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C5, serArr[25]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C6, serArr[26]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C7, serArr[27]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C8, serArr[28]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_C9, serArr[29]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D0, serArr[30]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D1, serArr[31]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D2, serArr[32]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D3, serArr[33]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D4, serArr[34]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D5, serArr[35]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D6, serArr[36]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D7, serArr[37]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D8, serArr[38]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_D9, serArr[39]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E0, serArr[40]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E1, serArr[41]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E2, serArr[42]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E3, serArr[43]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E4, serArr[44]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E5, serArr[45]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E6, serArr[46]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E7, serArr[47]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E8, serArr[48]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_E9, serArr[49]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F0, serArr[50]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F1, serArr[51]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F2, serArr[52]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F3, serArr[53]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F4, serArr[54]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F5, serArr[55]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F6, serArr[56]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F7, serArr[57]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F8, serArr[58]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_F9, serArr[59]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G0, serArr[60]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G1, serArr[61]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G2, serArr[62]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G3, serArr[63]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G4, serArr[64]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G5, serArr[65]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G6, serArr[66]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G7, serArr[67]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G8, serArr[68]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_G9, serArr[69]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H0, serArr[70]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H1, serArr[71]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H2, serArr[72]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H3, serArr[73]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H4, serArr[74]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H5, serArr[75]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H6, serArr[76]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H7, serArr[77]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H8, serArr[78]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_H9, serArr[79]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I0, serArr[80]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I1, serArr[81]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I2, serArr[82]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I3, serArr[83]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I4, serArr[84]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I5, serArr[85]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I6, serArr[86]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I7, serArr[87]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I8, serArr[88]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_I9, serArr[89]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J0, serArr[90]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J1, serArr[91]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J2, serArr[92]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J3, serArr[93]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J4, serArr[94]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J5, serArr[95]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J6, serArr[96]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J7, serArr[97]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J8, serArr[98]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_J9, serArr[99]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K0, serArr[100]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K1, serArr[101]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K2, serArr[102]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K3, serArr[103]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K4, serArr[104]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K5, serArr[105]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K6, serArr[106]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K7, serArr[107]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K8, serArr[108]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_K9, serArr[109]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L0, serArr[110]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L1, serArr[111]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L2, serArr[112]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L3, serArr[113]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L4, serArr[114]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L5, serArr[115]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L6, serArr[116]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L7, serArr[117]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L8, serArr[118]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_L9, serArr[119]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M0, serArr[120]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M1, serArr[121]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M2, serArr[122]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M3, serArr[123]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M4, serArr[124]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M5, serArr[125]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M6, serArr[126]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M7, serArr[127]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M8, serArr[128]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_M9, serArr[129]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N0, serArr[130]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N1, serArr[131]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N2, serArr[132]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N3, serArr[133]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N4, serArr[134]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N5, serArr[135]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N6, serArr[136]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N7, serArr[137]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N8, serArr[138]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_N9, serArr[139]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O0, serArr[140]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O1, serArr[141]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O2, serArr[142]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O3, serArr[143]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O4, serArr[144]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O5, serArr[145]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O6, serArr[146]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O7, serArr[147]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O8, serArr[148]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_O9, serArr[149]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P0, serArr[150]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P1, serArr[151]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P2, serArr[152]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P3, serArr[153]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P4, serArr[154]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P5, serArr[155]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P6, serArr[156]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P7, serArr[157]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P8, serArr[158]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_P9, serArr[159]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q0, serArr[160]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q1, serArr[161]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q2, serArr[162]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q3, serArr[163]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q4, serArr[164]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q5, serArr[165]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q6, serArr[166]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q7, serArr[167]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q8, serArr[168]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_Q9, serArr[169]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R0, serArr[170]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R1, serArr[171]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R2, serArr[172]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R3, serArr[173]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R4, serArr[174]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R5, serArr[175]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R6, serArr[176]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R7, serArr[177]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R8, serArr[178]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_R9, serArr[179]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S0, serArr[180]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S1, serArr[181]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S2, serArr[182]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S3, serArr[183]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S4, serArr[184]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S5, serArr[185]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S6, serArr[186]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S7, serArr[187]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S8, serArr[188]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_S9, serArr[189]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T0, serArr[190]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T1, serArr[191]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T2, serArr[192]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T3, serArr[193]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T4, serArr[194]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T5, serArr[195]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T6, serArr[196]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T7, serArr[197]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T8, serArr[198]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).serNum_T9, serArr[199]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A0, rowNumArr[0]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A1, rowNumArr[1]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A2, rowNumArr[2]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A3, rowNumArr[3]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A4, rowNumArr[4]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A5, rowNumArr[5]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A6, rowNumArr[6]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A7, rowNumArr[7]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A8, rowNumArr[8]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_A9, rowNumArr[9]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B0, rowNumArr[10]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B1, rowNumArr[11]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B2, rowNumArr[12]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B3, rowNumArr[13]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B4, rowNumArr[14]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B5, rowNumArr[15]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B6, rowNumArr[16]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B7, rowNumArr[17]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B8, rowNumArr[18]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_B9, rowNumArr[19]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C0, rowNumArr[20]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C1, rowNumArr[21]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C2, rowNumArr[22]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C3, rowNumArr[23]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C4, rowNumArr[24]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C5, rowNumArr[25]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C6, rowNumArr[26]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C7, rowNumArr[27]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C8, rowNumArr[28]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_C9, rowNumArr[29]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D0, rowNumArr[30]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D1, rowNumArr[31]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D2, rowNumArr[32]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D3, rowNumArr[33]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D4, rowNumArr[34]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D5, rowNumArr[35]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D6, rowNumArr[36]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D7, rowNumArr[37]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D8, rowNumArr[38]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_D9, rowNumArr[39]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E0, rowNumArr[40]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E1, rowNumArr[41]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E2, rowNumArr[42]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E3, rowNumArr[43]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E4, rowNumArr[44]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E5, rowNumArr[45]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E6, rowNumArr[46]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E7, rowNumArr[47]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E8, rowNumArr[48]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_E9, rowNumArr[49]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F0, rowNumArr[50]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F1, rowNumArr[51]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F2, rowNumArr[52]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F3, rowNumArr[53]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F4, rowNumArr[54]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F5, rowNumArr[55]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F6, rowNumArr[56]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F7, rowNumArr[57]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F8, rowNumArr[58]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_F9, rowNumArr[59]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G0, rowNumArr[60]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G1, rowNumArr[61]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G2, rowNumArr[62]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G3, rowNumArr[63]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G4, rowNumArr[64]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G5, rowNumArr[65]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G6, rowNumArr[66]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G7, rowNumArr[67]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G8, rowNumArr[68]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_G9, rowNumArr[69]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H0, rowNumArr[70]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H1, rowNumArr[71]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H2, rowNumArr[72]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H3, rowNumArr[73]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H4, rowNumArr[74]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H5, rowNumArr[75]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H6, rowNumArr[76]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H7, rowNumArr[77]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H8, rowNumArr[78]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_H9, rowNumArr[79]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I0, rowNumArr[80]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I1, rowNumArr[81]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I2, rowNumArr[82]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I3, rowNumArr[83]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I4, rowNumArr[84]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I5, rowNumArr[85]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I6, rowNumArr[86]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I7, rowNumArr[87]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I8, rowNumArr[88]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_I9, rowNumArr[89]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J0, rowNumArr[90]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J1, rowNumArr[91]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J2, rowNumArr[92]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J3, rowNumArr[93]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J4, rowNumArr[94]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J5, rowNumArr[95]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J6, rowNumArr[96]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J7, rowNumArr[97]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J8, rowNumArr[98]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_J9, rowNumArr[99]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K0, rowNumArr[100]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K1, rowNumArr[101]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K2, rowNumArr[102]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K3, rowNumArr[103]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K4, rowNumArr[104]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K5, rowNumArr[105]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K6, rowNumArr[106]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K7, rowNumArr[107]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K8, rowNumArr[108]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_K9, rowNumArr[109]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L0, rowNumArr[110]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L1, rowNumArr[111]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L2, rowNumArr[112]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L3, rowNumArr[113]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L4, rowNumArr[114]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L5, rowNumArr[115]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L6, rowNumArr[116]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L7, rowNumArr[117]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L8, rowNumArr[118]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_L9, rowNumArr[119]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M0, rowNumArr[120]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M1, rowNumArr[121]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M2, rowNumArr[122]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M3, rowNumArr[123]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M4, rowNumArr[124]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M5, rowNumArr[125]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M6, rowNumArr[126]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M7, rowNumArr[127]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M8, rowNumArr[128]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_M9, rowNumArr[129]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N0, rowNumArr[130]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N1, rowNumArr[131]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N2, rowNumArr[132]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N3, rowNumArr[133]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N4, rowNumArr[134]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N5, rowNumArr[135]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N6, rowNumArr[136]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N7, rowNumArr[137]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N8, rowNumArr[138]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_N9, rowNumArr[139]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O0, rowNumArr[140]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O1, rowNumArr[141]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O2, rowNumArr[142]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O3, rowNumArr[143]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O4, rowNumArr[144]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O5, rowNumArr[145]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O6, rowNumArr[146]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O7, rowNumArr[147]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O8, rowNumArr[148]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_O9, rowNumArr[149]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P0, rowNumArr[150]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P1, rowNumArr[151]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P2, rowNumArr[152]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P3, rowNumArr[153]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P4, rowNumArr[154]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P5, rowNumArr[155]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P6, rowNumArr[156]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P7, rowNumArr[157]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P8, rowNumArr[158]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_P9, rowNumArr[159]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q0, rowNumArr[160]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q1, rowNumArr[161]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q2, rowNumArr[162]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q3, rowNumArr[163]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q4, rowNumArr[164]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q5, rowNumArr[165]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q6, rowNumArr[166]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q7, rowNumArr[167]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q8, rowNumArr[168]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_Q9, rowNumArr[169]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R0, rowNumArr[170]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R1, rowNumArr[171]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R2, rowNumArr[172]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R3, rowNumArr[173]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R4, rowNumArr[174]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R5, rowNumArr[175]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R6, rowNumArr[176]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R7, rowNumArr[177]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R8, rowNumArr[178]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_R9, rowNumArr[179]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S0, rowNumArr[180]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S1, rowNumArr[181]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S2, rowNumArr[182]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S3, rowNumArr[183]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S4, rowNumArr[184]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S5, rowNumArr[185]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S6, rowNumArr[186]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S7, rowNumArr[187]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S8, rowNumArr[188]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_S9, rowNumArr[189]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T0, rowNumArr[190]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T1, rowNumArr[191]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T2, rowNumArr[192]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T3, rowNumArr[193]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T4, rowNumArr[194]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T5, rowNumArr[195]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T6, rowNumArr[196]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T7, rowNumArr[197]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T8, rowNumArr[198]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxRowNum_T9, rowNumArr[199]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A0, editFlgArr[0]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A1, editFlgArr[1]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A2, editFlgArr[2]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A3, editFlgArr[3]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A4, editFlgArr[4]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A5, editFlgArr[5]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A6, editFlgArr[6]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A7, editFlgArr[7]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A8, editFlgArr[8]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_A9, editFlgArr[9]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B0, editFlgArr[10]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B1, editFlgArr[11]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B2, editFlgArr[12]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B3, editFlgArr[13]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B4, editFlgArr[14]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B5, editFlgArr[15]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B6, editFlgArr[16]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B7, editFlgArr[17]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B8, editFlgArr[18]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_B9, editFlgArr[19]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C0, editFlgArr[20]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C1, editFlgArr[21]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C2, editFlgArr[22]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C3, editFlgArr[23]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C4, editFlgArr[24]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C5, editFlgArr[25]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C6, editFlgArr[26]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C7, editFlgArr[27]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C8, editFlgArr[28]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_C9, editFlgArr[29]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D0, editFlgArr[30]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D1, editFlgArr[31]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D2, editFlgArr[32]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D3, editFlgArr[33]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D4, editFlgArr[34]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D5, editFlgArr[35]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D6, editFlgArr[36]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D7, editFlgArr[37]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D8, editFlgArr[38]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_D9, editFlgArr[39]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E0, editFlgArr[40]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E1, editFlgArr[41]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E2, editFlgArr[42]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E3, editFlgArr[43]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E4, editFlgArr[44]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E5, editFlgArr[45]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E6, editFlgArr[46]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E7, editFlgArr[47]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E8, editFlgArr[48]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_E9, editFlgArr[49]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F0, editFlgArr[50]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F1, editFlgArr[51]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F2, editFlgArr[52]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F3, editFlgArr[53]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F4, editFlgArr[54]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F5, editFlgArr[55]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F6, editFlgArr[56]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F7, editFlgArr[57]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F8, editFlgArr[58]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_F9, editFlgArr[59]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G0, editFlgArr[60]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G1, editFlgArr[61]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G2, editFlgArr[62]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G3, editFlgArr[63]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G4, editFlgArr[64]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G5, editFlgArr[65]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G6, editFlgArr[66]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G7, editFlgArr[67]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G8, editFlgArr[68]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_G9, editFlgArr[69]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H0, editFlgArr[70]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H1, editFlgArr[71]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H2, editFlgArr[72]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H3, editFlgArr[73]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H4, editFlgArr[74]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H5, editFlgArr[75]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H6, editFlgArr[76]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H7, editFlgArr[77]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H8, editFlgArr[78]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_H9, editFlgArr[79]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I0, editFlgArr[80]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I1, editFlgArr[81]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I2, editFlgArr[82]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I3, editFlgArr[83]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I4, editFlgArr[84]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I5, editFlgArr[85]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I6, editFlgArr[86]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I7, editFlgArr[87]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I8, editFlgArr[88]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_I9, editFlgArr[89]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J0, editFlgArr[90]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J1, editFlgArr[91]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J2, editFlgArr[92]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J3, editFlgArr[93]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J4, editFlgArr[94]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J5, editFlgArr[95]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J6, editFlgArr[96]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J7, editFlgArr[97]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J8, editFlgArr[98]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_J9, editFlgArr[99]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K0, editFlgArr[100]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K1, editFlgArr[101]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K2, editFlgArr[102]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K3, editFlgArr[103]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K4, editFlgArr[104]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K5, editFlgArr[105]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K6, editFlgArr[106]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K7, editFlgArr[107]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K8, editFlgArr[108]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_K9, editFlgArr[109]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L0, editFlgArr[110]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L1, editFlgArr[111]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L2, editFlgArr[112]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L3, editFlgArr[113]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L4, editFlgArr[114]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L5, editFlgArr[115]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L6, editFlgArr[116]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L7, editFlgArr[117]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L8, editFlgArr[118]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_L9, editFlgArr[119]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M0, editFlgArr[120]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M1, editFlgArr[121]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M2, editFlgArr[122]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M3, editFlgArr[123]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M4, editFlgArr[124]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M5, editFlgArr[125]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M6, editFlgArr[126]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M7, editFlgArr[127]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M8, editFlgArr[128]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_M9, editFlgArr[129]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N0, editFlgArr[130]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N1, editFlgArr[131]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N2, editFlgArr[132]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N3, editFlgArr[133]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N4, editFlgArr[134]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N5, editFlgArr[135]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N6, editFlgArr[136]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N7, editFlgArr[137]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N8, editFlgArr[138]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_N9, editFlgArr[139]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O0, editFlgArr[140]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O1, editFlgArr[141]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O2, editFlgArr[142]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O3, editFlgArr[143]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O4, editFlgArr[144]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O5, editFlgArr[145]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O6, editFlgArr[146]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O7, editFlgArr[147]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O8, editFlgArr[148]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_O9, editFlgArr[149]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P0, editFlgArr[150]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P1, editFlgArr[151]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P2, editFlgArr[152]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P3, editFlgArr[153]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P4, editFlgArr[154]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P5, editFlgArr[155]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P6, editFlgArr[156]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P7, editFlgArr[157]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P8, editFlgArr[158]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_P9, editFlgArr[159]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q0, editFlgArr[160]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q1, editFlgArr[161]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q2, editFlgArr[162]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q3, editFlgArr[163]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q4, editFlgArr[164]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q5, editFlgArr[165]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q6, editFlgArr[166]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q7, editFlgArr[167]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q8, editFlgArr[168]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_Q9, editFlgArr[169]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R0, editFlgArr[170]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R1, editFlgArr[171]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R2, editFlgArr[172]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R3, editFlgArr[173]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R4, editFlgArr[174]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R5, editFlgArr[175]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R6, editFlgArr[176]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R7, editFlgArr[177]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R8, editFlgArr[178]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_R9, editFlgArr[179]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S0, editFlgArr[180]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S1, editFlgArr[181]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S2, editFlgArr[182]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S3, editFlgArr[183]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S4, editFlgArr[184]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S5, editFlgArr[185]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S6, editFlgArr[186]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S7, editFlgArr[187]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S8, editFlgArr[188]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_S9, editFlgArr[189]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T0, editFlgArr[190]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T1, editFlgArr[191]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T2, editFlgArr[192]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T3, editFlgArr[193]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T4, editFlgArr[194]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T5, editFlgArr[195]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T6, editFlgArr[196]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T7, editFlgArr[197]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T8, editFlgArr[198]);
            ZYPEZDItemValueSetter.setValue(globalMsg.N.no(idx).xxSetFlg_T9, editFlgArr[199]);
        }
    }

    /**
     * <pre>
     * get Count of Serial#
     * </pre>
     * @param ysMsg
     * @return
     */
    public static void setSerialLabelText(NPAL1340CMsg bizMsg, int no) {
        BigDecimal serCnt = NPAL1340CommonLogic.getSerialCount(bizMsg.N.no(no));
        if (serCnt.compareTo(bizMsg.A.no(no).poQty_A1.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).xxScrItem20Txt_A1, "Completed");
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).xxScrItem20Txt_A1, "No Entry");
        }
    }

    public static boolean isExistCarrier(String glblCmpyCd, String carrCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("carrCd", carrCd);
        S21SsmEZDResult ssmResult = NPAL1340Query.getInstance().countCarrier(prmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal count =  (BigDecimal) ssmResult.getResultObject();
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * The parameter for the Sub Screen transition is set.
     *
     * @param globalMsg NPAL1340SMsg
     * @param idx idx
     * @return ArrayList
     */
    public static ArrayList<EZDSStringItem> getArraySerialNum(NPAL1340SMsg globalMsg, int idx) {
        ArrayList<EZDSStringItem> serNumArray = new ArrayList<EZDSStringItem>();

        serNumArray.add(globalMsg.N.no(idx).serNum_A0);
        serNumArray.add(globalMsg.N.no(idx).serNum_A1);
        serNumArray.add(globalMsg.N.no(idx).serNum_A2);
        serNumArray.add(globalMsg.N.no(idx).serNum_A3);
        serNumArray.add(globalMsg.N.no(idx).serNum_A4);
        serNumArray.add(globalMsg.N.no(idx).serNum_A5);
        serNumArray.add(globalMsg.N.no(idx).serNum_A6);
        serNumArray.add(globalMsg.N.no(idx).serNum_A7);
        serNumArray.add(globalMsg.N.no(idx).serNum_A8);
        serNumArray.add(globalMsg.N.no(idx).serNum_A9);
        serNumArray.add(globalMsg.N.no(idx).serNum_B0);
        serNumArray.add(globalMsg.N.no(idx).serNum_B1);
        serNumArray.add(globalMsg.N.no(idx).serNum_B2);
        serNumArray.add(globalMsg.N.no(idx).serNum_B3);
        serNumArray.add(globalMsg.N.no(idx).serNum_B4);
        serNumArray.add(globalMsg.N.no(idx).serNum_B5);
        serNumArray.add(globalMsg.N.no(idx).serNum_B6);
        serNumArray.add(globalMsg.N.no(idx).serNum_B7);
        serNumArray.add(globalMsg.N.no(idx).serNum_B8);
        serNumArray.add(globalMsg.N.no(idx).serNum_B9);
        serNumArray.add(globalMsg.N.no(idx).serNum_C0);
        serNumArray.add(globalMsg.N.no(idx).serNum_C1);
        serNumArray.add(globalMsg.N.no(idx).serNum_C2);
        serNumArray.add(globalMsg.N.no(idx).serNum_C3);
        serNumArray.add(globalMsg.N.no(idx).serNum_C4);
        serNumArray.add(globalMsg.N.no(idx).serNum_C5);
        serNumArray.add(globalMsg.N.no(idx).serNum_C6);
        serNumArray.add(globalMsg.N.no(idx).serNum_C7);
        serNumArray.add(globalMsg.N.no(idx).serNum_C8);
        serNumArray.add(globalMsg.N.no(idx).serNum_C9);
        serNumArray.add(globalMsg.N.no(idx).serNum_D0);
        serNumArray.add(globalMsg.N.no(idx).serNum_D1);
        serNumArray.add(globalMsg.N.no(idx).serNum_D2);
        serNumArray.add(globalMsg.N.no(idx).serNum_D3);
        serNumArray.add(globalMsg.N.no(idx).serNum_D4);
        serNumArray.add(globalMsg.N.no(idx).serNum_D5);
        serNumArray.add(globalMsg.N.no(idx).serNum_D6);
        serNumArray.add(globalMsg.N.no(idx).serNum_D7);
        serNumArray.add(globalMsg.N.no(idx).serNum_D8);
        serNumArray.add(globalMsg.N.no(idx).serNum_D9);
        serNumArray.add(globalMsg.N.no(idx).serNum_E0);
        serNumArray.add(globalMsg.N.no(idx).serNum_E1);
        serNumArray.add(globalMsg.N.no(idx).serNum_E2);
        serNumArray.add(globalMsg.N.no(idx).serNum_E3);
        serNumArray.add(globalMsg.N.no(idx).serNum_E4);
        serNumArray.add(globalMsg.N.no(idx).serNum_E5);
        serNumArray.add(globalMsg.N.no(idx).serNum_E6);
        serNumArray.add(globalMsg.N.no(idx).serNum_E7);
        serNumArray.add(globalMsg.N.no(idx).serNum_E8);
        serNumArray.add(globalMsg.N.no(idx).serNum_E9);
        serNumArray.add(globalMsg.N.no(idx).serNum_F0);
        serNumArray.add(globalMsg.N.no(idx).serNum_F1);
        serNumArray.add(globalMsg.N.no(idx).serNum_F2);
        serNumArray.add(globalMsg.N.no(idx).serNum_F3);
        serNumArray.add(globalMsg.N.no(idx).serNum_F4);
        serNumArray.add(globalMsg.N.no(idx).serNum_F5);
        serNumArray.add(globalMsg.N.no(idx).serNum_F6);
        serNumArray.add(globalMsg.N.no(idx).serNum_F7);
        serNumArray.add(globalMsg.N.no(idx).serNum_F8);
        serNumArray.add(globalMsg.N.no(idx).serNum_F9);
        serNumArray.add(globalMsg.N.no(idx).serNum_G0);
        serNumArray.add(globalMsg.N.no(idx).serNum_G1);
        serNumArray.add(globalMsg.N.no(idx).serNum_G2);
        serNumArray.add(globalMsg.N.no(idx).serNum_G3);
        serNumArray.add(globalMsg.N.no(idx).serNum_G4);
        serNumArray.add(globalMsg.N.no(idx).serNum_G5);
        serNumArray.add(globalMsg.N.no(idx).serNum_G6);
        serNumArray.add(globalMsg.N.no(idx).serNum_G7);
        serNumArray.add(globalMsg.N.no(idx).serNum_G8);
        serNumArray.add(globalMsg.N.no(idx).serNum_G9);
        serNumArray.add(globalMsg.N.no(idx).serNum_H0);
        serNumArray.add(globalMsg.N.no(idx).serNum_H1);
        serNumArray.add(globalMsg.N.no(idx).serNum_H2);
        serNumArray.add(globalMsg.N.no(idx).serNum_H3);
        serNumArray.add(globalMsg.N.no(idx).serNum_H4);
        serNumArray.add(globalMsg.N.no(idx).serNum_H5);
        serNumArray.add(globalMsg.N.no(idx).serNum_H6);
        serNumArray.add(globalMsg.N.no(idx).serNum_H7);
        serNumArray.add(globalMsg.N.no(idx).serNum_H8);
        serNumArray.add(globalMsg.N.no(idx).serNum_H9);
        serNumArray.add(globalMsg.N.no(idx).serNum_I0);
        serNumArray.add(globalMsg.N.no(idx).serNum_I1);
        serNumArray.add(globalMsg.N.no(idx).serNum_I2);
        serNumArray.add(globalMsg.N.no(idx).serNum_I3);
        serNumArray.add(globalMsg.N.no(idx).serNum_I4);
        serNumArray.add(globalMsg.N.no(idx).serNum_I5);
        serNumArray.add(globalMsg.N.no(idx).serNum_I6);
        serNumArray.add(globalMsg.N.no(idx).serNum_I7);
        serNumArray.add(globalMsg.N.no(idx).serNum_I8);
        serNumArray.add(globalMsg.N.no(idx).serNum_I9);
        serNumArray.add(globalMsg.N.no(idx).serNum_J0);
        serNumArray.add(globalMsg.N.no(idx).serNum_J1);
        serNumArray.add(globalMsg.N.no(idx).serNum_J2);
        serNumArray.add(globalMsg.N.no(idx).serNum_J3);
        serNumArray.add(globalMsg.N.no(idx).serNum_J4);
        serNumArray.add(globalMsg.N.no(idx).serNum_J5);
        serNumArray.add(globalMsg.N.no(idx).serNum_J6);
        serNumArray.add(globalMsg.N.no(idx).serNum_J7);
        serNumArray.add(globalMsg.N.no(idx).serNum_J8);
        serNumArray.add(globalMsg.N.no(idx).serNum_J9);
        serNumArray.add(globalMsg.N.no(idx).serNum_K0);
        serNumArray.add(globalMsg.N.no(idx).serNum_K1);
        serNumArray.add(globalMsg.N.no(idx).serNum_K2);
        serNumArray.add(globalMsg.N.no(idx).serNum_K3);
        serNumArray.add(globalMsg.N.no(idx).serNum_K4);
        serNumArray.add(globalMsg.N.no(idx).serNum_K5);
        serNumArray.add(globalMsg.N.no(idx).serNum_K6);
        serNumArray.add(globalMsg.N.no(idx).serNum_K7);
        serNumArray.add(globalMsg.N.no(idx).serNum_K8);
        serNumArray.add(globalMsg.N.no(idx).serNum_K9);
        serNumArray.add(globalMsg.N.no(idx).serNum_L0);
        serNumArray.add(globalMsg.N.no(idx).serNum_L1);
        serNumArray.add(globalMsg.N.no(idx).serNum_L2);
        serNumArray.add(globalMsg.N.no(idx).serNum_L3);
        serNumArray.add(globalMsg.N.no(idx).serNum_L4);
        serNumArray.add(globalMsg.N.no(idx).serNum_L5);
        serNumArray.add(globalMsg.N.no(idx).serNum_L6);
        serNumArray.add(globalMsg.N.no(idx).serNum_L7);
        serNumArray.add(globalMsg.N.no(idx).serNum_L8);
        serNumArray.add(globalMsg.N.no(idx).serNum_L9);
        serNumArray.add(globalMsg.N.no(idx).serNum_M0);
        serNumArray.add(globalMsg.N.no(idx).serNum_M1);
        serNumArray.add(globalMsg.N.no(idx).serNum_M2);
        serNumArray.add(globalMsg.N.no(idx).serNum_M3);
        serNumArray.add(globalMsg.N.no(idx).serNum_M4);
        serNumArray.add(globalMsg.N.no(idx).serNum_M5);
        serNumArray.add(globalMsg.N.no(idx).serNum_M6);
        serNumArray.add(globalMsg.N.no(idx).serNum_M7);
        serNumArray.add(globalMsg.N.no(idx).serNum_M8);
        serNumArray.add(globalMsg.N.no(idx).serNum_M9);
        serNumArray.add(globalMsg.N.no(idx).serNum_N0);
        serNumArray.add(globalMsg.N.no(idx).serNum_N1);
        serNumArray.add(globalMsg.N.no(idx).serNum_N2);
        serNumArray.add(globalMsg.N.no(idx).serNum_N3);
        serNumArray.add(globalMsg.N.no(idx).serNum_N4);
        serNumArray.add(globalMsg.N.no(idx).serNum_N5);
        serNumArray.add(globalMsg.N.no(idx).serNum_N6);
        serNumArray.add(globalMsg.N.no(idx).serNum_N7);
        serNumArray.add(globalMsg.N.no(idx).serNum_N8);
        serNumArray.add(globalMsg.N.no(idx).serNum_N9);
        serNumArray.add(globalMsg.N.no(idx).serNum_O0);
        serNumArray.add(globalMsg.N.no(idx).serNum_O1);
        serNumArray.add(globalMsg.N.no(idx).serNum_O2);
        serNumArray.add(globalMsg.N.no(idx).serNum_O3);
        serNumArray.add(globalMsg.N.no(idx).serNum_O4);
        serNumArray.add(globalMsg.N.no(idx).serNum_O5);
        serNumArray.add(globalMsg.N.no(idx).serNum_O6);
        serNumArray.add(globalMsg.N.no(idx).serNum_O7);
        serNumArray.add(globalMsg.N.no(idx).serNum_O8);
        serNumArray.add(globalMsg.N.no(idx).serNum_O9);
        serNumArray.add(globalMsg.N.no(idx).serNum_P0);
        serNumArray.add(globalMsg.N.no(idx).serNum_P1);
        serNumArray.add(globalMsg.N.no(idx).serNum_P2);
        serNumArray.add(globalMsg.N.no(idx).serNum_P3);
        serNumArray.add(globalMsg.N.no(idx).serNum_P4);
        serNumArray.add(globalMsg.N.no(idx).serNum_P5);
        serNumArray.add(globalMsg.N.no(idx).serNum_P6);
        serNumArray.add(globalMsg.N.no(idx).serNum_P7);
        serNumArray.add(globalMsg.N.no(idx).serNum_P8);
        serNumArray.add(globalMsg.N.no(idx).serNum_P9);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q0);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q1);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q2);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q3);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q4);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q5);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q6);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q7);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q8);
        serNumArray.add(globalMsg.N.no(idx).serNum_Q9);
        serNumArray.add(globalMsg.N.no(idx).serNum_R0);
        serNumArray.add(globalMsg.N.no(idx).serNum_R1);
        serNumArray.add(globalMsg.N.no(idx).serNum_R2);
        serNumArray.add(globalMsg.N.no(idx).serNum_R3);
        serNumArray.add(globalMsg.N.no(idx).serNum_R4);
        serNumArray.add(globalMsg.N.no(idx).serNum_R5);
        serNumArray.add(globalMsg.N.no(idx).serNum_R6);
        serNumArray.add(globalMsg.N.no(idx).serNum_R7);
        serNumArray.add(globalMsg.N.no(idx).serNum_R8);
        serNumArray.add(globalMsg.N.no(idx).serNum_R9);
        serNumArray.add(globalMsg.N.no(idx).serNum_S0);
        serNumArray.add(globalMsg.N.no(idx).serNum_S1);
        serNumArray.add(globalMsg.N.no(idx).serNum_S2);
        serNumArray.add(globalMsg.N.no(idx).serNum_S3);
        serNumArray.add(globalMsg.N.no(idx).serNum_S4);
        serNumArray.add(globalMsg.N.no(idx).serNum_S5);
        serNumArray.add(globalMsg.N.no(idx).serNum_S6);
        serNumArray.add(globalMsg.N.no(idx).serNum_S7);
        serNumArray.add(globalMsg.N.no(idx).serNum_S8);
        serNumArray.add(globalMsg.N.no(idx).serNum_S9);
        serNumArray.add(globalMsg.N.no(idx).serNum_T0);
        serNumArray.add(globalMsg.N.no(idx).serNum_T1);
        serNumArray.add(globalMsg.N.no(idx).serNum_T2);
        serNumArray.add(globalMsg.N.no(idx).serNum_T3);
        serNumArray.add(globalMsg.N.no(idx).serNum_T4);
        serNumArray.add(globalMsg.N.no(idx).serNum_T5);
        serNumArray.add(globalMsg.N.no(idx).serNum_T6);
        serNumArray.add(globalMsg.N.no(idx).serNum_T7);
        serNumArray.add(globalMsg.N.no(idx).serNum_T8);
        serNumArray.add(globalMsg.N.no(idx).serNum_T9);

        return serNumArray;
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     * @return BigDecimal
     */
    public static BigDecimal getErrorPageFromNum(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg, int errNum) {
        errNum++;
        int pageCnt = errNum / bizMsg.A.length();
        int errPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (errNum % bizMsg.A.length() == 0) {
            errPageFromNum = errPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(errPageFromNum);
    }

    /**
     * Last Page
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void firstErrorPage(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg, int errNum) {
        BigDecimal errPageFromNum = getErrorPageFromNum(bizMsg, globalMsg, errNum);
        bizMsg.xxPageShowFromNum_A.setValue(errPageFromNum);
        dispPage(bizMsg, globalMsg);
    }

    /**
     * clearHeaderArea
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void clearHeaderArea(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        bizMsg.poOrdNum_H2.clear();
        bizMsg.poOrdNum_LC.clear();
        bizMsg.poOrdNum_LD.clear();
        bizMsg.poOrdNum_H2.clear();
        bizMsg.cpoOrdNum_H2.clear();
        bizMsg.prntVndCd_H2.clear();
        bizMsg.prntVndNm_H2.clear();
        bizMsg.vndCd_H2.clear();
        bizMsg.vndNm_H2.clear();
        bizMsg.vndInvNum_H2.clear();
        bizMsg.invDt_H2.clear();
        bizMsg.sellToCustCd_H2.clear();
        bizMsg.dsAcctNm_H2.clear();
        bizMsg.custIssPoNum_H2.clear();
        bizMsg.custIssPoDt_H2.clear();
        bizMsg.xxAllLineAddr_H2.clear();
        bizMsg.vndIssPoOrdNum_H2.clear();
        bizMsg.poHdrStsCd_H2.clear();
        bizMsg.poHdrStsDescTxt_H2.clear();
        bizMsg.xxChkBox_H2.clear();
        bizMsg.xxChkBox_H3.clear();
        bizMsg.spTotFuncFrtAmt_H2.clear();
        bizMsg.frtCondDescTxt_H2.clear();
        bizMsg.entPoDtlDealNetAmt_H2.clear();
        bizMsg.ccyCd_H2.clear();
        bizMsg.poQlfyCd_H2.clear();
        bizMsg.poOrdSrcNm_H2.clear();
        bizMsg.poOrdDtlCmntTxt_H2.clear();
    }

    /**
     * clearDetailArea
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    public static void clearDetailArea(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxPageShowFromNum_A.setValue(0);
        bizMsg.xxPageShowOfNum_A.setValue(0);
        bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
        bizMsg.N.clear();
        bizMsg.N.setValidCount(0);
        globalMsg.N.clear();
        globalMsg.N.setValidCount(0);
    }

    /**
     * setCarrierCd
     * QC#21191 Add Method.
     * @param glblCmpyCd
     * @param globalMsgRecord
     * @return true: Success / false:Error
     */
    public static boolean setCarrierCd(String glblCmpyCd, NPAL1340_ASMsg globalMsgRecord){
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("carrNm", globalMsgRecord.carrNm_A1.getValue());
        S21SsmEZDResult ssmResult = NPAL1340Query.getInstance().getCarrier(prmMap);
        if (ssmResult.isCodeNormal()) {
            String carrCd =  (String) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(globalMsgRecord.carrCd_A1, carrCd);
            return true;
        }
        return false;
    }
    
    
    /**
     * getCarrierCd
     * QC#21191 Add Method.
     * @param glblCmpyCd String
     * @param carrNm String
     * @return carrCd
     */
    public static String getCarrierCd(String glblCmpyCd, String carrNm){
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("carrNm", carrNm);
        S21SsmEZDResult ssmResult = NPAL1340Query.getInstance().getCarrier(prmMap);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }
        return null;
    }

    // START 2020/06/25 [QC#56979,ADD]
    /**
     * <pre>
     * editCarrTrkUrl
     * ADD QC:56979
     * </pre>
     * @param shareMsgAry_A NPAL1340_ASMsgArray
     */
    private static void editCarrTrkUrl(NPAL1340_ASMsgArray shareMsgAry_A, int i) {
        String url = shareMsgAry_A.no(i).carrTrkUrl_A1.getValue();
        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String str = matcher.replaceAll(shareMsgAry_A.no(i).proNum_A1.getValue());
        shareMsgAry_A.no(i).carrTrkUrl_A1.setValue(str);
    }
    // END 2020/06/25 [QC#56979,ADD]
}
