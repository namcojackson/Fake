/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560.common;

import static business.servlet.NSAL0560.constant.NSAL0560Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0560.NSAL0560BMsg;
import business.servlet.NSAL0560.NSAL0560_ABMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC1427
 * 2016/04/26   Hitachi         T.Iwamoto       Update          QC#1759
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 * 2016/11/22   Hitachi         T.Mizuki        Update          QC#16116
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 * 2017/10/24   Hitachi         K.Kitachi       Update          QC#21815
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/10/19   Hitachi         T.Tomita        Update          QC#28850
 *</pre>
 */
public class NSAL0560CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0560BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0560BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
        // ADD start 2016/04/26 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/26 CSA Defect#1759
        // START 2017/08/21 K.Kitachi [QC#20061, ADD]
        calcTotTermAmt(scrnMsg);
        // END 2017/08/21 K.Kitachi [QC#20061, ADD]
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0560BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0560BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        // START 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // mod start 2016/11/22 CSA QC#16116
        if (scrnMsg.xxModeCd_H1.getValue().equals(REF_MODE) || !hasUpdateFuncId()) {
        // mod end 2016/11/22 CSA QC#16116
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0560BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0560BMsg scrnMsg) {
        // mod start 2016/11/22 CSA QC#16116
        if (scrnMsg.xxModeCd_H1.getValue().equals(REF_MODE) || !hasUpdateFuncId()) {
        // mod end 2016/11/22 CSA QC#16116
            controlScreenHeaderFieldsRefMode(handler, scrnMsg);
            controlScreenDetailFieldsRefMode(scrnMsg);
        } else if (scrnMsg.xxModeCd_H1.getValue().equals(EDIT_MODE)) {
            controlScreenHeaderFieldsEditMode(handler, scrnMsg);
            controlScreenDetailFieldsEditMode(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFieldsEditMode
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0560BMsg
     */
    private static final void controlScreenHeaderFieldsEditMode(EZDCommonHandler handler, NSAL0560BMsg scrnMsg) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        scrnMsg.t_MdlNm_H1.setInputProtected(true);
     // START 2016/09/20 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
     // END 2016/09/20 N.Arai [QC#11616, MOD]
        scrnMsg.xxFirstScdCtyStAddr_H1.setInputProtected(true);
        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
        // START 2017/08/21 K.Kitachi [QC#20061, ADD]
        scrnMsg.basePrcTermDealAmtRate_H1.setInputProtected(true);
        scrnMsg.basePrcTermDealAmtRate_H1.setAppFracDigit(2);
        // END 2017/08/21 K.Kitachi [QC#20061, ADD]

        handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DELETE_ROW[0], true);
            handler.setButtonEnabled(BTN_SCHEDULES[0], true);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], true);
        } else {
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_SCHEDULES[0], false);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], false);
        }

        // START 2018/05/11 K.Kim [QC#25897, ADD]
        // START 2018/07/17 [QC#25959, MOD]
        // Mod Start 2018/10/19 QC#28850
        // if (!isAggMachine(scrnMsg)){
        if (isAggLine(scrnMsg)){
        // Mod End 2018/10/19 QC#28850
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
        }
        // END   2018/07/17 [QC#25959, MOD]
        // END 2018/05/11 K.Kim [QC#25897, ADD]

        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
    }

    /**
     * controlScreenDetailFieldsEditMode
     * @param scrnMsg NSAL0560BMsg
     */
    private static final void controlScreenDetailFieldsEditMode(NSAL0560BMsg scrnMsg) {
        // Mod Start 02/08/2016 <QC#7402>
        // START 2018/07/17 [QC#25959, MOD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
            String stsCd = scrnMsg.A.no(i).dsContrDtlStsCd_A1.getValue();
            String xxExstFlg_X1 = scrnMsg.A.no(i).xxExstFlg_X1.getValue();
            String xxExstFlg_X2 = scrnMsg.A.no(i).xxExstFlg_X2.getValue();
            String xxExstFlg_X3 = scrnMsg.A.no(i).xxExstFlg_X3.getValue();
            // START 2018/05/11 K.Kim [QC#25897, MOD]
            // Mod Start 2018/10/19 QC#28850
            // if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || !isAggMachine(scrnMsg)) {
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || isAggLine(scrnMsg)) {
            // Mod Start 2018/10/19 QC#28850
                scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(false);
            }
            // Mod Start 2018/10/19 QC#28850
            // if (isProtectDsContrCtrlStsCd(stsCd) || !isAggMachine(scrnMsg)) {
            if (isProtectDsContrCtrlStsCd(stsCd) || isAggLine(scrnMsg)) {
            // Mod Start 2018/10/19 QC#28850
                scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(false);
            }
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X2) || isAggMachine(scrnMsg)) {
                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(false);
            }
            // START 2017/08/21 K.Kitachi [QC#20061, MOD]
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X3)) {
                scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setInputProtected(false);
            }
            // END 2017/08/21 K.Kitachi [QC#20061, MOD]
            // Mod Start 2018/10/19 QC#28850
            // if (!isAggMachine(scrnMsg)){
            if (isAggLine(scrnMsg)){
            // Mod End 2018/10/19 QC#28850
                scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setInputProtected(true);
            }
            // END 2018/05/11 K.Kim [QC#25897, MOD]
            scrnMsg.A.no(i).dsContrDtlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cratDt_A1.setInputProtected(true);
            // del start 2016/05/17 CSA Defect#3891
//            scrnMsg.A.no(i).svcMemoRsnNm_A1.setInputProtected(true);
            // del end 2016/05/17 CSA Defect#3891

            scrnMsg.A.no(i).basePrcDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setAppFracDigit(2);
        }
        // END   2018/07/17 [QC#25959, MOD]
        // Mod End   02/08/2016 <QC#7402>
    }

    /**
     * controlScreenHeaderFieldsRefMode
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0560BMsg
     */
    private static final void controlScreenHeaderFieldsRefMode(EZDCommonHandler handler, NSAL0560BMsg scrnMsg) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        scrnMsg.t_MdlNm_H1.setInputProtected(true);
     // START 2016/09/20 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
     // END 2016/09/20 N.Arai [QC#11616, MOD]
        scrnMsg.xxFirstScdCtyStAddr_H1.setInputProtected(true);
        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
        // START 2017/08/21 K.Kitachi [QC#20061, ADD]
        scrnMsg.basePrcTermDealAmtRate_H1.setInputProtected(true);
        scrnMsg.basePrcTermDealAmtRate_H1.setAppFracDigit(2);
        // END 2017/08/21 K.Kitachi [QC#20061, ADD]

        handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_SCHEDULES[0], true);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], true);
        } else {
            handler.setButtonEnabled(BTN_SCHEDULES[0], false);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], false);
        }

        scrnMsg.svcMemoRsnCd_H3.setInputProtected(true);
        scrnMsg.svcCmntTxt_H1.setInputProtected(true);
    }

    /**
     * controlScreenDetailFieldsRefMode
     * @param scrnMsg NSAL0560BMsg
     */
    private static final void controlScreenDetailFieldsRefMode(NSAL0560BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrDtlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cratDt_A1.setInputProtected(true);
            // del start 2016/05/17 CSA Defect#3891
//            scrnMsg.A.no(i).svcMemoRsnNm_A1.setInputProtected(true);
            // del end 2016/05/17 CSA Defect#3891

            scrnMsg.A.no(i).basePrcDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setAppFracDigit(2);
        }
    }

    private static boolean isProtectDsContrCtrlStsCd(String dsContrCtrlStsCd) {
        if (dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.DRAFT) || dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.ENTERED) || dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.QA_HOLD)) {
            return false;
        }
        return true;
    }

    // ADD start 2016/04/26 CSA Defect#1759
    /**
     * protectedAllHeaderFields
     * @param NSAL0560BMsg scrnMsg
     */
    private static void setRowColors(NSAL0560BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/26 CSA Defect#1759

    /**
     * addCheckItem
     * @param scrnMsg NSAL0560BMsg
     */
    public static void addCheckItem(NSAL0560BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0560_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.contrPrcEffFromDt_A1);
            scrnMsg.addCheckItem(abMsg.contrPrcEffThruDt_A1);
            scrnMsg.addCheckItem(abMsg.basePrcDealAmt_A1);
            scrnMsg.addCheckItem(abMsg.bllgCycleCd_A3);
            // START 2017/08/21 K.Kitachi [QC#20061, ADD]
            scrnMsg.addCheckItem(abMsg.basePrcTermDealAmtRate_A1);
            // END 2017/08/21 K.Kitachi [QC#20061, ADD]
        }
        // add start 2016/05/17 CSA Defect#3891
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        // add end 2016/05/17 CSA Defect#3891
    }

    /**
     * <pre>
     * copyAMsgToBMsg
     * </pre>
     * @param scrnMsg NSAL0560BMsg
     */
    public static final void copyAMsgToBMsg(NSAL0560BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setValue(scrnMsg.B.no(i).contrPrcEffFromDt_B1, scrnMsg.A.no(i).contrPrcEffFromDt_A1);
            setValue(scrnMsg.B.no(i).contrPrcEffThruDt_B1, scrnMsg.A.no(i).contrPrcEffThruDt_A1);
            setValue(scrnMsg.B.no(i).bllgCycleCd_B3, scrnMsg.A.no(i).bllgCycleCd_A3);
            setValue(scrnMsg.B.no(i).basePrcDealAmt_B1, scrnMsg.A.no(i).basePrcDealAmt_A1);
            // del start 2016/05/17 CSA Defect#3891
//            setValue(scrnMsg.B.no(i).svcCmntTxt_B1, scrnMsg.A.no(i).svcCmntTxt_A1);
//            setValue(scrnMsg.B.no(i).svcMemoRsnCd_B3, scrnMsg.A.no(i).svcMemoRsnCd_A3);
            // del end 2016/05/17 CSA Defect#3891
            // START 2017/08/21 K.Kitachi [QC#20061, ADD]
            setValue(scrnMsg.B.no(i).basePrcTermDealAmtRate_B1, scrnMsg.A.no(i).basePrcTermDealAmtRate_A1);
            // END 2017/08/21 K.Kitachi [QC#20061, ADD]
        }
    }
    // mod start 2016/11/22 CSA QC#16116
    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Renewal Rules contract/machine level(" + BUSINESS_ID + "). UserID is -> "
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }
    // mod end 2016/11/22 CSA QC#16116

    // START 2017/08/21 K.Kitachi [QC#20061, ADD]
    /**
     * calcTotTermAmt
     * @param scrnMsg NSAL0560BMsg
     */
    public static void calcTotTermAmt(NSAL0560BMsg scrnMsg) {
        BigDecimal totTermAmt = BigDecimal.ZERO;
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        // START 2017/10/24 K.Kitachi [QC#21815, MOD]
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        // END 2017/10/24 K.Kitachi [QC#21815, MOD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (dsContrCtrlStsCdList.contains(scrnMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                continue;
            }
            if (!hasValue(scrnMsg.A.no(i).basePrcTermDealAmtRate_A1)) {
                continue;
            }
            totTermAmt = totTermAmt.add(scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.getValue());
        }
        setValue(scrnMsg.basePrcTermDealAmtRate_H1, totTermAmt);
    }
    // END 2017/08/21 K.Kitachi [QC#20061, ADD]

    // START 2018/05/11 K.Kim [QC#25897, ADD]
    /**
     * @param scrnMsg NSAL0560BMsg
     * @return boolean
     */
    public static boolean isAggMachine(NSAL0560BMsg scrnMsg) {

        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd_A.getValue())) {
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.dsContrDtlTpCd_A.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2018/05/11 K.Kim [QC#25897, ADD]
    // Add Start 2018/10/19 QC#28850
    /**
     * @param scrnMsg NSAL0560BMsg
     * @return boolean
     */
    public static boolean isAggLine(NSAL0560BMsg scrnMsg) {

        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd_A.getValue())) {
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.dsContrDtlTpCd_A.getValue())) {
                return true;
            }
        }
        return false;
    }
    // Add End 2018/10/19 QC#28850
}
