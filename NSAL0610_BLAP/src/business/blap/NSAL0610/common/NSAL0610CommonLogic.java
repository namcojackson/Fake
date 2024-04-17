/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0610.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.blap.NSAL0610.NSAL0610CMsg;
import business.blap.NSAL0610.NSAL0610SMsg;
import business.blap.NSAL0610.NSAL0610_ACMsg;
import business.blap.NSAL0610.NSAL0610_ACMsgArray;
import business.blap.NSAL0610.NSAL0610_BSMsg;
import business.blap.NSAL0610.NSAL0610_NCMsg;
import business.blap.NSAL0610.NSAL0610_NCMsgArray;
import business.blap.NSAL0610.NSAL0610_OSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 * 2016/04/21   Hitachi         T.Iwamoto         Update          QC#4424
 *</pre>
 */
public class NSAL0610CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    public static void copyCurrentPageToSMsgA(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        // NSAL0610_ACMsg -> NSAL0610_BSMsg
        NSAL0610_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0610_ACMsg acMsg = (NSAL0610_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;

            NSAL0610_BSMsg bsMsg = sMsg.B.no(targetIdxNumA);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxBtnFlg_A, acMsg.xxBtnFlg_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxChkBox_A, acMsg.xxChkBox_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxSelFlg_A, acMsg.xxSelFlg_A);
        }
    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    public static void copyCurrentPageToSMsgN(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        // NSAL0610_NCMsg -> NSAL0610_OSMsg
        NSAL0610_NCMsgArray ncMsgArray = cMsg.N;
        for (int i = 0; i < ncMsgArray.getValidCount(); i++) {
            NSAL0610_NCMsg ncMsg = (NSAL0610_NCMsg) ncMsgArray.no(i);
            int targetRowNumN = ncMsg.xxRowNum_N.getValueInt();

            for (int j = 0; j < sMsg.O.getValidCount(); j++) {
                NSAL0610_OSMsg osMsg = sMsg.O.no(j);
                if (targetRowNumN == osMsg.xxRowNum_N.getValueInt()) {
                    ZYPEZDItemValueSetter.setValue(osMsg.xxBtnFlg_N, ncMsg.xxBtnFlg_N);
                    ZYPEZDItemValueSetter.setValue(osMsg.xxChkBox_N, ncMsg.xxChkBox_N);
                    break;
                }
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation_A(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());

        // mod start 2016/04/21 CSA Defect#4424
        NSAL0610CommonLogic.setAllSelectedFlg(cMsg, sMsg);
        // mod end 2016/04/21 CSA Defect#4424
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation_N(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.N.length(); i++) {
            if (i < sMsg.N.getValidCount()) {
                EZDMsg.copy(sMsg.N.get(i), null, cMsg.N.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.N.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_N.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_N.setValue(pageFrom + cMsg.N.getValidCount());
        cMsg.xxPageShowOfNum_N.setValue(sMsg.N.getValidCount());
    }

    /**
     * Set View Data processing is executed.
     * @param sMsg Global area information
     */
    public static void setViewData_A(NSAL0610SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);
        if (sMsg.B.getValidCount() == 0) {
            return;
        }
        int aIdx = 0;
        int bIdx = 0;

        for (; bIdx < sMsg.B.getValidCount(); bIdx++) {
            String dplyFlg = (String) sMsg.B.no(bIdx).xxDplyCtrlFlg_A.getValue();
            String btnFlg = (String) sMsg.B.no(bIdx).xxBtnFlg_A.getValue();
            BigDecimal brkDsContrDtlPk = sMsg.B.no(bIdx).dsContrDtlPk_A.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
                EZDMsg.copy(sMsg.B.no(bIdx), null, sMsg.A.no(aIdx), null);
                aIdx++;
                if (ZYPConstant.FLG_ON_Y.equals(btnFlg)) {
                    aIdx = setViewDetail_A(aIdx, brkDsContrDtlPk, sMsg);
                }
            }
        }
        sMsg.A.setValidCount(aIdx);
    }

    private static int setViewDetail_A(int aIdx, BigDecimal brkDsContrDtlPk, NSAL0610SMsg sMsg) {
        int rtrnIdx = aIdx;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (brkDsContrDtlPk.compareTo(sMsg.B.no(i).dsContrDtlPk_A.getValue()) == 0) {
                if (ZYPConstant.FLG_OFF_N.equals((String) sMsg.B.no(i).xxDplyCtrlFlg_A.getValue())) {
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.A.no(rtrnIdx), null);
                    rtrnIdx++;
                }
            }
        }
        return rtrnIdx;
    }

    /**
     * Set View Data processing is executed.
     * @param sMsg Global area information
     */
    public static void setViewData_N(NSAL0610SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.N);
        if (sMsg.O.getValidCount() == 0) {
            return;
        }
        int aIdx = 0;
        int bIdx = 0;

        for (; bIdx < sMsg.O.getValidCount(); bIdx++) {
            String dplyFlg = (String) sMsg.O.no(bIdx).xxDplyCtrlFlg_N.getValue();
            String btnFlg = (String) sMsg.O.no(bIdx).xxBtnFlg_N.getValue();
            BigDecimal brkDsContrDtlPk = sMsg.O.no(bIdx).dsContrDtlPk_N.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
                EZDMsg.copy(sMsg.O.no(bIdx), null, sMsg.N.no(aIdx), null);
                aIdx++;
                if (ZYPConstant.FLG_ON_Y.equals(btnFlg)) {
                    aIdx = setViewDetail_N(aIdx, brkDsContrDtlPk, sMsg);
                }
            }
        }
        sMsg.N.setValidCount(aIdx);
    }

    private static int setViewDetail_N(int aIdx, BigDecimal brkDsContrDtlPk, NSAL0610SMsg sMsg) {
        int rtrnIdx = aIdx;
        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
            if (brkDsContrDtlPk.compareTo(sMsg.O.no(i).dsContrDtlPk_N.getValue()) == 0) {
                if (ZYPConstant.FLG_OFF_N.equals((String) sMsg.O.no(i).xxDplyCtrlFlg_N.getValue())) {
                    EZDMsg.copy(sMsg.O.no(i), null, sMsg.N.no(rtrnIdx), null);
                    rtrnIdx++;
                }
            }
        }
        return rtrnIdx;
    }

    /**
     * set Copy Contract Data.
     * @param sMsg Global area information
     */
    public static void setCopyContractData(NSAL0610SMsg sMsg) {
        int nCnt = sMsg.O.getValidCount();
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_A.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxSelFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).xxBtnFlg_N, ZYPConstant.FLG_OFF_N);

                for (int j = i; j < sMsg.B.getValidCount(); j++) {
                    if (sMsg.B.no(j).dsContrDtlPk_A.getValueInt() != sMsg.B.no(i).dsContrDtlPk_A.getValueInt()) {
                        break;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).dsContrDtlPk_N, sMsg.B.no(j).dsContrDtlPk_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).dsContrDtlTpCd_N, sMsg.B.no(j).dsContrDtlTpCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).svcMachMstrPk_N, sMsg.B.no(j).svcMachMstrPk_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).serNum_N, sMsg.B.no(j).serNum_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).dsContrBllgMtrPk_N, sMsg.B.no(j).dsContrBllgMtrPk_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).mtrLbDescTxt_N, sMsg.B.no(j).mtrLbDescTxt_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).mtrLbSortNum_N, sMsg.B.no(j).mtrLbSortNum_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).xxDplyCtrlFlg_N, sMsg.B.no(j).xxDplyCtrlFlg_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).prntDsContrDtlPk_N, sMsg.B.no(j).prntDsContrDtlPk_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).xxDplyByCtrlAncrLvlCd_N, sMsg.B.no(j).xxDplyByCtrlAncrLvlCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(nCnt).xxRowNum_N, sMsg.B.no(j).xxRowNum_A);
                    nCnt++;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSelFlg_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        sMsg.O.setValidCount(nCnt);
    }

    /**
     * remove Copy Contract Data.
     * @param sMsg Global area information
     */
    public static void removeCopyContractData(NSAL0610SMsg sMsg) {

        int nCnt = sMsg.O.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();

        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.O.no(i).xxChkBox_N.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.O.no(i).xxDplyCtrlFlg_N.getValue())) {
                int target = sMsg.O.no(i).xxRowNum_N.getValueInt() - 1;
                int befDsContrDtlPk = sMsg.O.no(i).dsContrDtlPk_N.getValueInt();

                for (int j = i; j < sMsg.O.getValidCount(); j++) {
                    if (sMsg.O.no(j).dsContrDtlPk_N.getValueInt() != befDsContrDtlPk) {
                        break;
                    }
                    delList.add(j);
                    nCnt = nCnt - 1;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(target).xxSelFlg_A, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(target).xxChkBox_A, ZYPConstant.FLG_OFF_N);
            }
        }

        ZYPTableUtil.deleteRows(sMsg.O, delList);
        sMsg.O.setValidCount(nCnt);
    }

    /**
     * setAllSelectedFlg
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    // add 2016/04/21 CSA Defect#4424
    public static void setAllSelectedFlg(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxSelFlg_A.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg_HA, ZYPConstant.FLG_OFF_N);
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg_HA, ZYPConstant.FLG_ON_Y);
    }

}
