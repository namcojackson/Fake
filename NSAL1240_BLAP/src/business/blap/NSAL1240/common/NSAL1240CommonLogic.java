/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1240.common;

import static business.blap.NSAL1240.constant.NSAL1240Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import business.blap.NSAL1240.NSAL1240CMsg;
import business.blap.NSAL1240.NSAL1240Query;
import business.blap.NSAL1240.NSAL1240SMsg;
import business.blap.NSAL1240.NSAL1240_BSMsg;
import business.blap.NSAL1240.NSAL1240_CSMsg;
import business.blap.NSAL1240.NSAL1240_OCMsgArray;
import business.blap.NSAL1240.NSAL1240_PCMsgArray;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4949
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/09/02   Hitachi         T.Kanasaka      Update          QC#11170
 * 2018/04/06   Hitachi         K.Kojima        Update          QC#25161
 *</pre>
 */
public class NSAL1240CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     */
    public static void clearMsg(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H1.clear();
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.serNum_H.clear();
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H2.clear();
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.mdseCd_H.clear();
        cMsg.t_MdlNm_H.clear();
        cMsg.shipDt_H1.clear();
        cMsg.shipDt_H2.clear();
        cMsg.svcMachUsgStsCd_H.clear();
        cMsg.xxChkBox_H1.clear();
        cMsg.xxChkBox_H2.clear();
        cMsg.xxChkBox_H3.clear();
        cMsg.xxChkBox_H4.clear();
        cMsg.xxChkBox_H5.clear();
        cMsg.xxChkBox_H6.clear();
        cMsg.xxChkBox_H7.clear();
        cMsg.xxChkBox_H8.clear();
        cMsg.xxChkBox_HA.clear();
        cMsg.xxChkBox_HB.clear();
        cMsg.xxChkBox_HC.clear();
        cMsg.stkStsCd_H.clear();
        cMsg.curLocNum_H1.clear();
        cMsg.curLocNum_H2.clear();
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H3.clear();
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.svcSlnNm_H.clear();
        cMsg.dsContrNum_H.clear();
        cMsg.ownrAcctNum_H.clear();
        // del start 2016/05/16 CSA Defect#4578
//        cMsg.ownrLocNum_H.clear();
        // del end 2016/05/16 CSA Defect#4578
        cMsg.billToAcctNum_H.clear();
        // mod start 2016/05/16 CSA Defect#4578
        cMsg.indBillToLocNum_H.clear();
        // mod end 2016/05/16 CSA Defect#4578
        cMsg.curLocAcctNum_H.clear();
        // mod start 2016/05/16 CSA Defect#4578
        cMsg.indCurLocNum_H.clear();
        // mod end 2016/05/16 CSA Defect#4578
        cMsg.xxRowNum.clear();
        cMsg.xxPageShowOfNum.clear();
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxComnColOrdTxt.clear();
        cMsg.xxSortTblNm.clear();
        cMsg.xxSortItemNm.clear();
        cMsg.xxSortOrdByTxt.clear();
        sMsg.clear();
    }

    /**
     * Clear Search Resul
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     */
    public static void clearSearchResult(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL1240CMsg
     */
    public static void createPullDown(NSAL1240CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(SVC_MACH_USG_STS.class, cMsg.svcMachUsgStsCd_L, cMsg.svcMachUsgStsDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(STK_STS.class, cMsg.stkStsCd_L, cMsg.stkStsDescTxt_L);
    }

    /**
     * Set Search Condition
     * @param cMsg NSAL1240CMsg
     */
    public static void setSearchCondition(NSAL1240CMsg cMsg) {

        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        if (hasValueItems(cMsg.svcConfigMstrPk_I)) {
            setValue(cMsg.xxScrItem29Txt_H1, cMsg.svcConfigMstrPk_I.getValue().toString());
        }
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        setValue(cMsg.serNum_H, cMsg.serNum_I);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        if (hasValueItems(cMsg.svcMachMstrPk_I)) {
            setValue(cMsg.xxScrItem29Txt_H2, cMsg.svcMachMstrPk_I.getValue().toString());
        }
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        setValue(cMsg.mdseCd_H, cMsg.mdseCd_I);
        setValue(cMsg.t_MdlNm_H, cMsg.mdlNm_I);
        setValue(cMsg.shipDt_H1, cMsg.shipDt_I1);
        setValue(cMsg.shipDt_H2, cMsg.shipDt_I2);
        setValue(cMsg.svcMachUsgStsCd_H, cMsg.svcMachUsgStsCd_I);
        for (int i = 0; i < cMsg.I.getValidCount(); i++) {
            String svcMachMstrStsCd = cMsg.I.no(i).svcMachMstrStsCd_I.getValue();
            if (SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H3, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H4, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H5, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H6, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.DUPLICATE.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H7, ZYPConstant.CHKBOX_ON_Y);
            } else if (SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(svcMachMstrStsCd)) {
                setValue(cMsg.xxChkBox_H8, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        if (MODE_02.equals(cMsg.xxModeCd_I2.getValue())) {
            setValue(cMsg.xxChkBox_HA, ZYPConstant.CHKBOX_ON_Y);
        } else if (MODE_03.equals(cMsg.xxModeCd_I2.getValue())) {
            setValue(cMsg.xxChkBox_HB, ZYPConstant.CHKBOX_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.mainMachFlg_I.getValue())) {
            setValue(cMsg.xxChkBox_HC, ZYPConstant.CHKBOX_ON_Y);
        }
        setValue(cMsg.stkStsCd_H, cMsg.stkStsCd_I);
        setValue(cMsg.curLocNum_H1, cMsg.curLocNum_I1);
        setValue(cMsg.curLocNum_H2, cMsg.curLocNum_I2);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        if (hasValueItems(cMsg.svcSlnSq_I)) {
            setValue(cMsg.xxScrItem29Txt_H3, cMsg.svcSlnSq_I.getValue().toString());
        }
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        setValue(cMsg.svcSlnNm_H, cMsg.svcSlnNm_I);
        setValue(cMsg.dsContrNum_H, cMsg.dsContrNum_I);
        setValue(cMsg.ownrAcctNum_H, cMsg.ownrAcctNum_I);
        // del start 2016/05/16 CSA Defect#4578
//        setValue(cMsg.ownrLocNum_H, cMsg.ownrLocNum_I);
        // del end 2016/05/16 CSA Defect#4578
        setValue(cMsg.billToAcctNum_H, cMsg.billToAcctNum_I);
        // mod start 2016/05/16 CSA Defect#4578
        setValue(cMsg.indBillToLocNum_H, NSAL1240Query.getIndBillToLocNum(cMsg.glblCmpyCd.getValue(), cMsg.billToLocNum_I.getValue()));
        // mod end 2016/05/16 CSA Defect#4578
        setValue(cMsg.curLocAcctNum_H, cMsg.curLocAcctNum_I);
        // mod start 2016/05/16 CSA Defect#4578
        setValue(cMsg.indCurLocNum_H, NSAL1240Query.getIndCurLocNum(cMsg.glblCmpyCd.getValue(), cMsg.curLocNum_I.getValue()));
        // mod end 2016/05/16 CSA Defect#4578
    }

    /**
     * HasValue Search Condition
     * @param cMsg NSAL1240CMsg
     * @return boolean
     */
    public static boolean hasValueSearchCondition(NSAL1240CMsg cMsg) {

        // START 2016/09/02 T.Kanasaka [QC#11170, MOD]
        // mod start 2016/05/16 CSA Defect#4578
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        if (hasValueItems(cMsg.xxScrItem29Txt_H1, cMsg.serNum_H, cMsg.xxScrItem29Txt_H2, cMsg.mdseCd_H, cMsg.t_MdlNm_H, cMsg.shipDt_H1, cMsg.shipDt_H2, cMsg.svcMachUsgStsCd_H, cMsg.xxChkBox_H1, cMsg.xxChkBox_H2, cMsg.xxChkBox_H3,
                cMsg.xxChkBox_H4, cMsg.xxChkBox_H5, cMsg.xxChkBox_H6, cMsg.xxChkBox_H7, cMsg.xxChkBox_H8, cMsg.xxChkBox_HA, cMsg.stkStsCd_H, cMsg.curLocNum_H1, cMsg.curLocNum_H2, cMsg.xxScrItem29Txt_H3,
                cMsg.svcSlnNm_H, cMsg.dsContrNum_H, cMsg.ownrAcctNum_H, cMsg.billToAcctNum_H, cMsg.indBillToLocNum_H, cMsg.curLocAcctNum_H, cMsg.indCurLocNum_H)) {
            return true;
        }
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        // mod end 2016/05/16 CSA Defect#4578
        // END 2016/09/02 T.Kanasaka [QC#11170, MOD]
        return false;
    }

    /**
     * Set MessageInfo SearchCondition
     * @param cMsg NSAL1240CMsg
     */
    public static void setMessageInfoSearchCondition(NSAL1240CMsg cMsg) {

        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H1.setErrorInfo(1, NSAM0005E);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.serNum_H.setErrorInfo(1, NSAM0005E);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H2.setErrorInfo(1, NSAM0005E);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.mdseCd_H.setErrorInfo(1, NSAM0005E);
        cMsg.t_MdlNm_H.setErrorInfo(1, NSAM0005E);
        cMsg.shipDt_H1.setErrorInfo(1, NSAM0005E);
        cMsg.shipDt_H2.setErrorInfo(1, NSAM0005E);
        cMsg.svcMachUsgStsCd_H.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H1.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H2.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H3.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H4.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H5.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H6.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H7.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_H8.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_HA.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_HB.setErrorInfo(1, NSAM0005E);
        cMsg.xxChkBox_HC.setErrorInfo(1, NSAM0005E);
        cMsg.stkStsCd_H.setErrorInfo(1, NSAM0005E);
        cMsg.curLocNum_H1.setErrorInfo(1, NSAM0005E);
        cMsg.curLocNum_H2.setErrorInfo(1, NSAM0005E);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.xxScrItem29Txt_H3.setErrorInfo(1, NSAM0005E);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        cMsg.svcSlnNm_H.setErrorInfo(1, NSAM0005E);
        cMsg.dsContrNum_H.setErrorInfo(1, NSAM0005E);
        cMsg.ownrAcctNum_H.setErrorInfo(1, NSAM0005E);
        // del start 2016/05/16 CSA Defect#4578
//        cMsg.ownrLocNum_H.setErrorInfo(1, NSAM0005E);
        // del end 2016/05/16 CSA Defect#4578
        cMsg.billToAcctNum_H.setErrorInfo(1, NSAM0005E);
        // mod start 2016/05/16 CSA Defect#4578
        cMsg.indBillToLocNum_H.setErrorInfo(1, NSAM0005E);
        // mod end 2016/05/16 CSA Defect#4578
        cMsg.curLocAcctNum_H.setErrorInfo(1, NSAM0005E);
        // mod start 2016/05/16 CSA Defect#4578
        cMsg.indCurLocNum_H.setErrorInfo(1, NSAM0005E);
        // mod end 2016/05/16 CSA Defect#4578
        cMsg.setMessageInfo(NSAM0005E);
    }

    /**
     * Get Search Data
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     */
    public static void getSearchData(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {
        // START 2018/04/06 K.Kojima [QC#25161,ADD]
        outputSearchLog(cMsg);
        // END 2018/04/06 K.Kojima [QC#25161,ADD]
        S21SsmEZDResult ssmResult = NSAL1240Query.getInstance().getSearchData(cMsg, sMsg, sMsg.B.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }

        int idx = 0;
        List<BigDecimal> svcConfigMstrPkList = new ArrayList<BigDecimal>();
        String dplyCtrlFlg = ZYPConstant.FLG_ON_Y;
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_HC.getValue())) {
            dplyCtrlFlg = ZYPConstant.FLG_OFF_N;
        }
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL1240_BSMsg bSMsg = sMsg.B.no(i);
            if (hasValue(bSMsg.svcConfigMstrPk_B) && SVC_MACH_TP.ACCESSORY.equals(bSMsg.svcMachTpCd_B.getValue())) {
                if (!svcConfigMstrPkList.contains(bSMsg.svcConfigMstrPk_B.getValue())) {
                    setValue(sMsg.C.no(idx).svcConfigMstrPk_C, bSMsg.svcConfigMstrPk_B);
                    setValue(sMsg.C.no(idx).xxDplyCtrlFlg_C, dplyCtrlFlg);
                    idx++;
                    svcConfigMstrPkList.add(bSMsg.svcConfigMstrPk_B.getValue());
                }
            }
        }
        sMsg.C.setValidCount(idx);
    }

    /**
     * Create Display Info
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     */
    public static void createDispInfo(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);

        // expand/collapse status of each svcConfigMstrPk
        Map<BigDecimal, String> expandMap = new HashMap<BigDecimal, String>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL1240_CSMsg cSMsg = sMsg.C.no(i);
            expandMap.put(cSMsg.svcConfigMstrPk_C.getValue(), cSMsg.xxDplyCtrlFlg_C.getValue());
        }

        int idx = 0;
        String dplyFlg = null;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL1240_BSMsg bSMsg = sMsg.B.no(i);
            if (hasValue(bSMsg.svcConfigMstrPk_B)) {
                dplyFlg = null;
                if (expandMap.containsKey(bSMsg.svcConfigMstrPk_B.getValue())) {
                    dplyFlg = expandMap.get(bSMsg.svcConfigMstrPk_B.getValue());
                }

                if (SVC_MACH_TP.MACHINE.equals(bSMsg.svcMachTpCd_B.getValue())) {
                    copyBSMsgToASMsg(bSMsg, sMsg, idx, i);
                    setValue(sMsg.A.no(idx).xxDplyCtrlFlg_A, dplyFlg);
                    idx++;
                } else {
                    if (ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
                        copyBSMsgToASMsg(bSMsg, sMsg, idx, i);
                        sMsg.A.no(idx).xxDplyCtrlFlg_A.clear();
                        idx++;
                    }
                }
            } else {
                copyBSMsgToASMsg(bSMsg, sMsg, idx, i);
                sMsg.A.no(idx).xxDplyCtrlFlg_A.clear();
                idx++;
            }
            if (idx >= sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                break;
            }
        }
        sMsg.A.setValidCount(idx);
    }

    private static void copyBSMsgToASMsg(NSAL1240_BSMsg bSMsg, NSAL1240SMsg sMsg, int idxASMsg, int idxBSMsg) {
        EZDMsg.copy(bSMsg, "B", sMsg.A.no(idxASMsg), "A");
        EZDMsg.copy(bSMsg, "B1", sMsg.A.no(idxASMsg), "A1");
        EZDMsg.copy(bSMsg, "B2", sMsg.A.no(idxASMsg), "A2");
        EZDMsg.copy(bSMsg, "B3", sMsg.A.no(idxASMsg), "A3");
        setValue(sMsg.A.no(idxASMsg).xxRowNum_A, BigDecimal.valueOf(idxBSMsg));
    }

    /**
     * Create Display Info
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     * @param dplyCtrlFlg String
     */
    public static void changeDplyCntrl(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg, String dplyCtrlFlg) {
        int rowNum = cMsg.xxRowNum.getValueInt();
        BigDecimal svcConfigMstrPk = cMsg.A.no(rowNum).svcConfigMstrPk_A.getValue();
        if (hasValue(svcConfigMstrPk)) {
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                if (svcConfigMstrPk.compareTo(sMsg.C.no(i).svcConfigMstrPk_C.getValue()) == 0) {
                    setValue(sMsg.C.no(i).xxDplyCtrlFlg_C, dplyCtrlFlg);
                    break;
                }
            }
        }
    }

    /**
     * Set Output Parameter
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     */
    public static void setOutputParam(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        // START 2016/05/11 T.Tomita [QC#7832, MOD]
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);

        int idx = 0;
        if (MODE_01.equals(cMsg.xxModeCd_I3.getValue())) {
            // Check svcConfigMstrPk
            BigDecimal firstSvcConfigMstrPk = null;
            BigDecimal targetSvcConfigMstrPk = null;
            for (int selectedRow : selectedRows) {
                if (idx == 0) {
                    firstSvcConfigMstrPk = sMsg.B.no(selectedRow).svcConfigMstrPk_B.getValue();
                } else {
                    targetSvcConfigMstrPk = sMsg.B.no(selectedRow).svcConfigMstrPk_B.getValue();
                    if (!sameSvcConfigMstrPk(firstSvcConfigMstrPk, targetSvcConfigMstrPk)) {
                        setMessageInfoDetailCheckBox(cMsg);
                        return;
                    }
                }
                idx++;
            }
        }

        cMsg.mdlId_O.clear();
        cMsg.mdlNm_O.clear();
        cMsg.dsContrPk_O.clear();
        cMsg.dsContrNum_O.clear();
        cMsg.svcConfigMstrPk_O.clear();
        ZYPTableUtil.clear(cMsg.O);
        ZYPTableUtil.clear(cMsg.P);
        NSAL1240_OCMsgArray oCMsgArray = cMsg.O;
        NSAL1240_PCMsgArray pCMsgArray = cMsg.P;
        idx = 0;

        // Set Output Parameter
        for (int selectedRow : selectedRows) {
            if (idx == 0) {
                setValue(cMsg.mdlId_O, sMsg.B.no(selectedRow).mdlId_B);
                setValue(cMsg.mdlNm_O, sMsg.B.no(selectedRow).t_MdlNm_B);
                setValue(cMsg.dsContrPk_O, sMsg.B.no(selectedRow).dsContrPk_B);
                setValue(cMsg.dsContrNum_O, sMsg.B.no(selectedRow).dsContrNum_B);
                setValue(cMsg.svcConfigMstrPk_O, sMsg.B.no(selectedRow).svcConfigMstrPk_B);
            }

            setValue(oCMsgArray.no(idx).serNum_O, sMsg.B.no(selectedRow).serNum_B);
            setValue(oCMsgArray.no(idx).mdseCd_O, sMsg.B.no(selectedRow).mdseCd_B);
            setValue(oCMsgArray.no(idx).curLocNum_O, sMsg.B.no(selectedRow).curLocNum_B);
            // add start 2016/05/16 CSA Defect#4578
            setValue(oCMsgArray.no(idx).indCurLocNum_O, sMsg.B.no(selectedRow).indCurLocNum_B);
            // add end 2016/05/16 CSA Defect#4578
            setValue(oCMsgArray.no(idx).locNm_O, sMsg.B.no(selectedRow).locNm_B);
            setValue(oCMsgArray.no(idx).svcMachUsgStsCd_O, sMsg.B.no(selectedRow).svcMachUsgStsCd_B);
            setValue(oCMsgArray.no(idx).svcMachMstrStsCd_O, sMsg.B.no(selectedRow).svcMachMstrStsCd_B);
            setValue(oCMsgArray.no(idx).svcMachMstrPk_O, sMsg.B.no(selectedRow).svcMachMstrPk_B);
            setValue(oCMsgArray.no(idx).svcMachTpCd_O, sMsg.B.no(selectedRow).svcMachTpCd_B);
            idx++;
        }
        cMsg.O.setValidCount(idx);

        if (MODE_02.equals(cMsg.xxModeCd_I3.getValue())) {
            List<BigDecimal> svcConfigMstrPkList = new ArrayList<BigDecimal>();
            BigDecimal svcConfigMstrPk_B = null;
            for (int selectedRow : selectedRows) {
                svcConfigMstrPk_B = sMsg.B.no(selectedRow).svcConfigMstrPk_B.getValue();
                if (!svcConfigMstrPkList.contains(svcConfigMstrPk_B)) {
                    svcConfigMstrPkList.add(svcConfigMstrPk_B);
                }
            }
            idx = 0;
            for (BigDecimal svcConfigMstrPk_P : svcConfigMstrPkList) {
                setValue(pCMsgArray.no(idx).svcConfigMstrPk_P, svcConfigMstrPk_P);
                idx++;
            }
            cMsg.P.setValidCount(idx);
        }
        // END 2016/05/11 T.Tomita [QC#7832, MOD]
    }

    private static boolean sameSvcConfigMstrPk(BigDecimal firstSvcConfigMstrPk, BigDecimal targetSvcConfigMstrPk) {
        if (!hasValue(firstSvcConfigMstrPk) && !hasValue(targetSvcConfigMstrPk)) {
            return true;
        }
        if (hasValue(firstSvcConfigMstrPk) && !hasValue(targetSvcConfigMstrPk)) {
            return false;
        }
        if (!hasValue(firstSvcConfigMstrPk) && hasValue(targetSvcConfigMstrPk)) {
            return false;
        }
        if (firstSvcConfigMstrPk.compareTo(targetSvcConfigMstrPk) != 0) {
            return false;
        }
        return true;
    }

    private static void setMessageInfoDetailCheckBox(NSAL1240CMsg cMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        for (int selectedRow : selectedRows) {
            cMsg.A.no(selectedRow).xxChkBox_A.setErrorInfo(1, NSAM0410E, new String[] {"Configuration" });
        }
        cMsg.setMessageInfo(NSAM0410E, new String[] {"Configuration" });
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        int bSMsgCnt = 0;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);

                bSMsgCnt = cMsg.A.no(cnt - pagenationFrom).xxRowNum_A.getValueInt();
                setValue(sMsg.B.no(bSMsgCnt).xxChkBox_B, cMsg.A.no(cnt - pagenationFrom).xxChkBox_A);
            } else {
                break;
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
    public static void pagenation(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private static boolean hasValueItems(EZDCItem... items) {

        for (EZDCItem item : items) {
            if (hasValue(item)) {
                return true;
            }
        }
        return false;
    }

    // START 2018/04/06 K.Kojima [QC#25161,ADD]
    private static void outputSearchLog(NSAL1240CMsg cMsg) {
        boolean title = false;
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL1240 Search Condition : ");
        if (hasValue(cMsg.xxScrItem29Txt_H1)) {
            sb.append(" Configuration#[").append(cMsg.xxScrItem29Txt_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.serNum_H)) {
            sb.append(" Serial#[").append(cMsg.serNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem29Txt_H2)) {
            sb.append(" Install Base ID[").append(cMsg.xxScrItem29Txt_H2.getValue()).append("]");
        }
        if (hasValue(cMsg.mdseCd_H)) {
            sb.append(" Item Code[").append(cMsg.mdseCd_H.getValue()).append("]");
        }
        if (hasValue(cMsg.t_MdlNm_H)) {
            sb.append(" Model Name[").append(cMsg.t_MdlNm_H.getValue()).append("]");
        }
        if (hasValue(cMsg.shipDt_H1) || hasValue(cMsg.shipDt_H2)) {
            sb.append(" Shipped Date[").append(cMsg.shipDt_H1.getValue()).append("-").append(cMsg.shipDt_H2.getValue()).append("]");
        }
        if (hasValue(cMsg.svcMachUsgStsCd_H)) {
            sb.append(" Install Base Usage[").append(cMsg.svcMachUsgStsCd_H.getValue()).append("]");
        }
        if ((hasValue(cMsg.xxChkBox_H1) && cMsg.xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H2) && cMsg.xxChkBox_H2.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H3) && cMsg.xxChkBox_H3.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H4) && cMsg.xxChkBox_H4.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H5) && cMsg.xxChkBox_H5.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H6) && cMsg.xxChkBox_H6.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H7) && cMsg.xxChkBox_H7.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_H8) && cMsg.xxChkBox_H8.getValue().equals(ZYPConstant.FLG_ON_Y))) {
            sb.append(" Install Base Status[");
            String tmpStr = "";
            if (hasValue(cMsg.xxChkBox_H1) && cMsg.xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Created");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H2) && cMsg.xxChkBox_H2.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Waiting for Installation");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H3) && cMsg.xxChkBox_H3.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Installed");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H4) && cMsg.xxChkBox_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Waiting for Removal");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H5) && cMsg.xxChkBox_H5.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Remove");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H6) && cMsg.xxChkBox_H6.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Terminated");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H7) && cMsg.xxChkBox_H7.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Duplicate");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_H8) && cMsg.xxChkBox_H8.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("Dealer Service");
                tmpStr = ",";
            }
            sb.append("]");
        }
        if ((hasValue(cMsg.xxChkBox_HA) && cMsg.xxChkBox_HA.getValue().equals(ZYPConstant.FLG_ON_Y))
                || (hasValue(cMsg.xxChkBox_HB) && cMsg.xxChkBox_HB.getValue().equals(ZYPConstant.FLG_ON_Y))) {
            sb.append(" Machine Allocated[");
            String tmpStr = "";
            if (hasValue(cMsg.xxChkBox_HA) && cMsg.xxChkBox_HA.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("ON");
                tmpStr = ",";
            }
            if (hasValue(cMsg.xxChkBox_HB) && cMsg.xxChkBox_HB.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                sb.append(tmpStr).append("OFF");
                tmpStr = ",";
            }
            sb.append("]");
        }
        if (hasValue(cMsg.xxChkBox_HC) && cMsg.xxChkBox_HC.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            sb.append(" Machine Only[").append(cMsg.xxChkBox_HC.getValue()).append("]");
        }
        if (hasValue(cMsg.stkStsCd_H)) {
            sb.append(" Stock Status[").append(cMsg.stkStsCd_H.getValue()).append("]");
        }
        if (hasValue(cMsg.curLocNum_H1)) {
            sb.append(" WH Code[").append(cMsg.curLocNum_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.curLocNum_H2)) {
            sb.append(" Sub WH Code[").append(cMsg.curLocNum_H2.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem29Txt_H3)) {
            sb.append(" Solution#[").append(cMsg.xxScrItem29Txt_H3.getValue()).append("]");
        }
        if (hasValue(cMsg.svcSlnNm_H)) {
            sb.append(" Solution Name[").append(cMsg.svcSlnNm_H.getValue()).append("]");
        }
        if (hasValue(cMsg.dsContrNum_H)) {
            sb.append(" Contract#[").append(cMsg.dsContrNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.ownrAcctNum_H)) {
            sb.append(" IB Owner Account#[").append(cMsg.ownrAcctNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.billToAcctNum_H)) {
            sb.append(" IB Bill To Account#[").append(cMsg.billToAcctNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.indBillToLocNum_H)) {
            sb.append(" IB Bill To Location#[").append(cMsg.indBillToLocNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.curLocAcctNum_H)) {
            sb.append(" IB Current Loc Account#[").append(cMsg.curLocAcctNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.indCurLocNum_H)) {
            sb.append(" IB Current Location#[").append(cMsg.indCurLocNum_H.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/04/06 K.Kojima [QC#25161,ADD]
}
