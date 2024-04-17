/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2810.common;

import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLS_BTN_NM;
import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLS_EVENT_NM;
import static business.servlet.NMAL2810.constant.NMAL2810Constant.BTN_CMN_CLS_LABEL;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import parts.servletcommon.gui.EZDGUITableAttribute;
import business.servlet.NMAL2810.NMAL2810BMsg;
import business.servlet.NMAL2810.constant.NMAL2810Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 * 2016/09/16   SRAA            Y.Chen          Update          QC#14223
 *</pre>
 */
public class NMAL2810CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_CLS_BTN_NM, BTN_CMN_CLS_EVENT_NM, BTN_CMN_CLS_LABEL, 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2810BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2810BMsg scrnMsg) {

        boolean refFlg = true;

        // QC#14223
        // if (NMAL2810Constant.MODE_SET_LOC.equals(scrnMsg.xxModeCd.getValue()) || NMAL2810Constant.MODE_SET_PROS.equals(scrnMsg.xxModeCd.getValue())) {
        if ((NMAL2810Constant.MODE_SET_LOC.equals(scrnMsg.xxModeCd.getValue()) || NMAL2810Constant.MODE_SET_PROS.equals(scrnMsg.xxModeCd.getValue()))
                && !ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            // Mode : Set
            refFlg = false;
            handler.setButtonEnabled(NMAL2810Constant.BTN_PROS_ALL, true);
            handler.setButtonEnabled(NMAL2810Constant.BTN_MRG_ALL, true);
            handler.setButtonEnabled(NMAL2810Constant.BTN_ADR_VAL, true);
        } else {
            // Mode : Reference
            handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 0, null);
            handler.setButtonEnabled(NMAL2810Constant.BTN_PROS_ALL, false);
            handler.setButtonEnabled(NMAL2810Constant.BTN_MRG_ALL, false);
            handler.setButtonEnabled(NMAL2810Constant.BTN_ADR_VAL, false);
        }

        scrnMsg.rvwProsNum_L.setInputProtected(true);
        scrnMsg.locNum_R.setInputProtected(true);

        scrnMsg.firstLineAddr_L.setInputProtected(true);
        scrnMsg.scdLineAddr_L.setInputProtected(true);
        scrnMsg.thirdLineAddr_L.setInputProtected(true);
        scrnMsg.frthLineAddr_L.setInputProtected(true);
        scrnMsg.ctyAddr_L.setInputProtected(true);
        scrnMsg.stCd_L.setInputProtected(true);
        scrnMsg.postCd_L.setInputProtected(true);
        scrnMsg.telNum_L.setInputProtected(true);
        scrnMsg.dsAcctDunsNm_L.setInputProtected(true);
        scrnMsg.dunsNum_L.setInputProtected(true);
        scrnMsg.dsUltDunsNum_L.setInputProtected(true);
        scrnMsg.dsCustSicCd_L.setInputProtected(true);
        scrnMsg.dsCustSicDescTxt_L.setInputProtected(true);
        scrnMsg.dsLocRevAmt_L.setInputProtected(true);
        scrnMsg.dsLocEmpNum_L.setInputProtected(true);
        scrnMsg.glnNum_L.setInputProtected(true);
        scrnMsg.dsPrntDunsNum_L.setInputProtected(true);
        scrnMsg.hqDunsNum_L.setInputProtected(true);
        scrnMsg.dsCustSicCd_LC.setInputProtected(true);
        scrnMsg.dsCustSicDescTxt_LC.setInputProtected(true);

        scrnMsg.firstLineAddr_R.setInputProtected(true);
        scrnMsg.scdLineAddr_R.setInputProtected(true);
        scrnMsg.thirdLineAddr_R.setInputProtected(true);
        scrnMsg.frthLineAddr_R.setInputProtected(true);
        scrnMsg.ctyAddr_R.setInputProtected(true);
        scrnMsg.stCd_R.setInputProtected(true);
        scrnMsg.postCd_R.setInputProtected(true);
        scrnMsg.telNum_R.setInputProtected(true);
        scrnMsg.dsAcctDunsNm_R.setInputProtected(true);
        scrnMsg.dunsNum_R.setInputProtected(true);
        scrnMsg.dsUltDunsNum_R.setInputProtected(true);
        scrnMsg.dsCustSicCd_R.setInputProtected(true);
        scrnMsg.dsCustSicDescTxt_R.setInputProtected(true);
        scrnMsg.dsLocRevAmt_R.setInputProtected(true);
        scrnMsg.dsLocEmpNum_R.setInputProtected(true);
        scrnMsg.glnNum_R.setInputProtected(true);
        scrnMsg.dsPrntDunsNum_R.setInputProtected(true);
        scrnMsg.hqDunsNum_R.setInputProtected(true);
        scrnMsg.dsCustSicCd_RC.setInputProtected(true);
        scrnMsg.dsCustSicDescTxt_RC.setInputProtected(true);

        scrnMsg.xxRadioBtn_1.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_2.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_3.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_4.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_5.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_6.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_7.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_8.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_9.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_10.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_11.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_12.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_13.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_14.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_15.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_16.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_17.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_18.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_19.setInputProtected(refFlg);
        scrnMsg.xxRadioBtn_20.setInputProtected(refFlg);
    }

    /**
     * setInParams
     * @param scrnMsg NMAL2810BMsg
     * @param params Object[]
     */
    public static final void setInParams(NMAL2810BMsg scrnMsg, Object[] params) {

        if (params != null && params.length == NMAL2810Constant.IN_PARAM_LEN) {
            setItemInValueErrorInfo(params[0], scrnMsg, scrnMsg.xxModeCd);
            setItemInValueErrorInfo(params[1], scrnMsg, scrnMsg.rvwProsNum_L);
            setItemInValueErrorInfo(params[2], scrnMsg, scrnMsg.locNum_R);
            setItemInValue(params[3], scrnMsg.firstLineAddr_L);
            setItemInValue(params[4], scrnMsg.scdLineAddr_L);
            setItemInValue(params[5], scrnMsg.thirdLineAddr_L);
            setItemInValue(params[6], scrnMsg.frthLineAddr_L);
            setItemInValue(params[7], scrnMsg.ctyAddr_L);
            setItemInValueErrorInfo(params[8], scrnMsg, scrnMsg.stCd_L);
            setItemInValueErrorInfo(params[9], scrnMsg, scrnMsg.postCd_L);
            setItemInValue(params[10], scrnMsg.telNum_L);
            setItemInValue(params[11], scrnMsg.dsAcctDunsNm_L);
            setItemInValue(params[12], scrnMsg.dunsNum_L);
            setItemInValue(params[13], scrnMsg.dsUltDunsNum_L);
            setItemInValue(params[14], scrnMsg.dsCustSicCd_L);
            setItemInValue(params[15], scrnMsg.dsCustSicDescTxt_L);
            setItemInValue(params[16], scrnMsg.dsLocRevAmt_L);
            setItemInValue(params[17], scrnMsg.dsLocEmpNum_L);
            setItemInValue(params[18], scrnMsg.glnNum_L);
            setItemInValue(params[19], scrnMsg.dsPrntDunsNum_L);
            setItemInValue(params[20], scrnMsg.hqDunsNum_L);
            setItemInValue(params[21], scrnMsg.dsCustSicCd_LC);
            setItemInValue(params[22], scrnMsg.dsCustSicDescTxt_LC);
        } else {
            scrnMsg.setMessageInfo(NMAL2810Constant.NMAM0052E, new String[] {"Input parameters" });
        }
    }

    /**
     * setOutParams
     * @param scrnMsg NMAL2810BMsg
     * @param params Object[]
     */
    public static final void setOutParams(NMAL2810BMsg scrnMsg, Object[] params) {
        setItemOutValue((EZDBStringItem) params[0], scrnMsg.xxModeCd);
        setItemOutValue((EZDBStringItem) params[1], scrnMsg.rvwProsNum_L);
        setItemOutValue((EZDBStringItem) params[2], scrnMsg.locNum_R);
        setItemOutValue((EZDBStringItem) params[3], scrnMsg.xxRadioBtn_1, scrnMsg.firstLineAddr_L, scrnMsg.firstLineAddr_R);
        setItemOutValue((EZDBStringItem) params[4], scrnMsg.xxRadioBtn_2, scrnMsg.scdLineAddr_L, scrnMsg.scdLineAddr_R);
        setItemOutValue((EZDBStringItem) params[5], scrnMsg.xxRadioBtn_3, scrnMsg.thirdLineAddr_L, scrnMsg.thirdLineAddr_R);
        setItemOutValue((EZDBStringItem) params[6], scrnMsg.xxRadioBtn_4, scrnMsg.frthLineAddr_L, scrnMsg.frthLineAddr_R);
        setItemOutValue((EZDBStringItem) params[7], scrnMsg.xxRadioBtn_5, scrnMsg.ctyAddr_L, scrnMsg.ctyAddr_R);
        setItemOutValue((EZDBStringItem) params[8], scrnMsg.xxRadioBtn_6, scrnMsg.stCd_L, scrnMsg.stCd_R);
        setItemOutValue((EZDBStringItem) params[9], scrnMsg.xxRadioBtn_7, scrnMsg.postCd_L, scrnMsg.postCd_R);
        setItemOutValue((EZDBStringItem) params[10], scrnMsg.xxRadioBtn_8, scrnMsg.telNum_L, scrnMsg.telNum_R);
        setItemOutValue((EZDBStringItem) params[11], scrnMsg.xxRadioBtn_9, scrnMsg.dsAcctDunsNm_L, scrnMsg.dsAcctDunsNm_R);
        setItemOutValue((EZDBStringItem) params[12], scrnMsg.xxRadioBtn_10, scrnMsg.dunsNum_L, scrnMsg.dunsNum_R);
        setItemOutValue((EZDBStringItem) params[13], scrnMsg.xxRadioBtn_11, scrnMsg.dsUltDunsNum_L, scrnMsg.dsUltDunsNum_R);
        setItemOutValue((EZDBStringItem) params[14], scrnMsg.xxRadioBtn_12, scrnMsg.dsCustSicCd_L, scrnMsg.dsCustSicCd_R);
        setItemOutValue((EZDBStringItem) params[15], scrnMsg.xxRadioBtn_13, scrnMsg.dsCustSicDescTxt_L, scrnMsg.dsCustSicDescTxt_R);
        setItemOutValue((EZDBBigDecimalItem) params[16], scrnMsg.xxRadioBtn_14, scrnMsg.dsLocRevAmt_L, scrnMsg.dsLocRevAmt_R);
        setItemOutValue((EZDBBigDecimalItem) params[17], scrnMsg.xxRadioBtn_15, scrnMsg.dsLocEmpNum_L, scrnMsg.dsLocEmpNum_R);
        setItemOutValue((EZDBBigDecimalItem) params[18], scrnMsg.xxRadioBtn_16, scrnMsg.glnNum_L, scrnMsg.glnNum_R);
        setItemOutValue((EZDBStringItem) params[19], scrnMsg.xxRadioBtn_17, scrnMsg.dsPrntDunsNum_L, scrnMsg.dsPrntDunsNum_R);
        setItemOutValue((EZDBStringItem) params[20], scrnMsg.xxRadioBtn_18, scrnMsg.hqDunsNum_L, scrnMsg.hqDunsNum_R);
        setItemOutValue((EZDBStringItem) params[21], scrnMsg.xxRadioBtn_19, scrnMsg.dsCustSicCd_LC, scrnMsg.dsCustSicCd_RC);
        setItemOutValue((EZDBStringItem) params[22], scrnMsg.xxRadioBtn_20, scrnMsg.dsCustSicDescTxt_LC, scrnMsg.dsCustSicDescTxt_RC);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[43], scrnMsg.xxRadioBtn_1);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[44], scrnMsg.xxRadioBtn_2);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[45], scrnMsg.xxRadioBtn_3);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[46], scrnMsg.xxRadioBtn_4);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[47], scrnMsg.xxRadioBtn_5);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[48], scrnMsg.xxRadioBtn_6);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[49], scrnMsg.xxRadioBtn_7);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[50], scrnMsg.xxRadioBtn_8);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[51], scrnMsg.xxRadioBtn_9);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[52], scrnMsg.xxRadioBtn_10);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[53], scrnMsg.xxRadioBtn_11);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[54], scrnMsg.xxRadioBtn_12);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[55], scrnMsg.xxRadioBtn_13);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[56], scrnMsg.xxRadioBtn_14);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[57], scrnMsg.xxRadioBtn_15);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[58], scrnMsg.xxRadioBtn_16);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[59], scrnMsg.xxRadioBtn_17);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[60], scrnMsg.xxRadioBtn_18);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[61], scrnMsg.xxRadioBtn_19);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[62], scrnMsg.xxRadioBtn_20);
    }

    /**
     * setRadioFields
     * @param scrnMsg NMAL2810BMsg
     * @param radioVal BigDecimal
     */
    public static final void setRadioFields(NMAL2810BMsg scrnMsg, BigDecimal radioVal) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_1, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_2, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_3, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_4, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_5, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_6, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_7, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_8, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_9, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_10, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_11, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_12, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_13, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_14, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_15, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_16, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_17, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_18, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_19, radioVal);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_20, radioVal);
    }

    /**
     * setInitBgColor
     * @param scrnMsg NMAL2810BMsg
     */
    public static final void setInitBgColor(NMAL2810BMsg scrnMsg) {

        EZDGUITableAttribute tableA = new EZDGUITableAttribute(NMAL2810Constant.SCRN_ID_00, NMAL2810Constant.TABLE_NAME_A);
        setInitBgColorRow(tableA, scrnMsg);
        scrnMsg.addGUIAttribute(tableA);
    }

    /**
     * setItemInValueErrorInfo
     * @param param Object
     * @param scrnMsg NMAL2810BMsg
     * @param item EZDBStringItem
     */
    private static final void setItemInValueErrorInfo(Object param, NMAL2810BMsg scrnMsg, EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue((EZDBStringItem) param)) {
            ZYPEZDItemValueSetter.setValue(item, (EZDBStringItem) param);
        } else {
            scrnMsg.setMessageInfo(NMAL2810Constant.NMAM8228E, new String[] {item.getNameForMessage() });
        }
    }

    /**
     * setItemInValue
     * @param param Object
     * @param item EZDBStringItem
     */
    private static final void setItemInValue(Object param, EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue((EZDBStringItem) param)) {
            ZYPEZDItemValueSetter.setValue(item, (EZDBStringItem) param);
        }
    }

    /**
     * setItemInValue
     * @param param Object
     * @param item EZDBBigDecimalItem
     */
    private static final void setItemInValue(Object param, EZDBBigDecimalItem item) {

        if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) param)) {
            ZYPEZDItemValueSetter.setValue(item, (EZDBBigDecimalItem) param);
        }
    }

    /**
     * setItemOutValue
     * @param param EZDBStringItem
     * @param item EZDBStringItem
     */
    private static final void setItemOutValue(EZDBStringItem param, EZDBStringItem item) {

        param.clear();

        if (ZYPCommonFunc.hasValue(item)) {
            param.setValue(item.getValue());
        }
    }

    /**
     * setItemOutValue
     * @param param EZDBStringItem
     * @param radioItem EZDBBigDecimalItem
     * @param leftItem EZDBStringItem
     * @param rightItem EZDBStringItem
     */
    private static final void setItemOutValue(EZDBStringItem param, EZDBBigDecimalItem radioItem, EZDBStringItem leftItem, EZDBStringItem rightItem) {

        param.clear();

        if (ZYPCommonFunc.hasValue(radioItem.getValue()) && NMAL2810Constant.RADIO_PROS.compareTo(radioItem.getValue()) == 0) {
            // set Prospect param
            if (ZYPCommonFunc.hasValue(leftItem)) {
                param.setValue(leftItem.getValue());
            }
        } else {
            // set Merge to param
            if (ZYPCommonFunc.hasValue(rightItem)) {
                param.setValue(rightItem.getValue());
            }
        }
    }

    /**
     * setItemOutValue
     * @param param EZDBBigDecimalItem
     * @param radioItem EZDBBigDecimalItem
     * @param leftItem EZDBBigDecimalItem
     * @param rightItem EZDBBigDecimalItem
     */
    private static final void setItemOutValue(EZDBBigDecimalItem param, EZDBBigDecimalItem radioItem, EZDBBigDecimalItem leftItem, EZDBBigDecimalItem rightItem) {

        param.clear();

        if (ZYPCommonFunc.hasValue(radioItem.getValue()) && NMAL2810Constant.RADIO_PROS.compareTo(radioItem.getValue()) == 0) {
            // set Prospect param
            if (ZYPCommonFunc.hasValue(leftItem)) {
                param.setValue(leftItem.getValue());
            }
        } else {
            // set Merge to param
            if (ZYPCommonFunc.hasValue(rightItem)) {
                param.setValue(rightItem.getValue());
            }
        }
    }

    /**
     * setInitBgColorRow
     * @param tblNm EZDGUITableAttribute
     * @param scrnMsg NMAL2810BMsg
     */
    private static final void setInitBgColorRow(EZDGUITableAttribute tblNm, NMAL2810BMsg scrnMsg) {

        setBgColorRow(tblNm, 0, scrnMsg.firstLineAddr_L, scrnMsg.firstLineAddr_R);
        setBgColorRow(tblNm, 1, scrnMsg.scdLineAddr_L, scrnMsg.scdLineAddr_R);
        setBgColorRow(tblNm, 2, scrnMsg.thirdLineAddr_L, scrnMsg.thirdLineAddr_R);
        setBgColorRow(tblNm, 3, scrnMsg.frthLineAddr_L, scrnMsg.frthLineAddr_R);
        setBgColorRow(tblNm, 4, scrnMsg.ctyAddr_L, scrnMsg.ctyAddr_R);
        setBgColorRow(tblNm, 5, scrnMsg.stCd_L, scrnMsg.stCd_R);
        setBgColorRow(tblNm, 6, scrnMsg.postCd_L, scrnMsg.postCd_R);
        setBgColorRow(tblNm, 7, scrnMsg.telNum_L, scrnMsg.telNum_R);
        setBgColorRow(tblNm, 8, scrnMsg.dsAcctDunsNm_L, scrnMsg.dsAcctDunsNm_R);
        setBgColorRow(tblNm, 9, scrnMsg.dunsNum_L, scrnMsg.dunsNum_R);
        setBgColorRow(tblNm, 10, scrnMsg.dsUltDunsNum_L, scrnMsg.dsUltDunsNum_R);
        setBgColorRow(tblNm, 11, scrnMsg.dsCustSicCd_L, scrnMsg.dsCustSicCd_R);
        setBgColorRow(tblNm, 12, scrnMsg.dsCustSicDescTxt_L, scrnMsg.dsCustSicDescTxt_R);
        setBgColorRow(tblNm, 13, scrnMsg.dsLocRevAmt_L, scrnMsg.dsLocRevAmt_R);
        setBgColorRow(tblNm, 14, scrnMsg.dsLocEmpNum_L, scrnMsg.dsLocEmpNum_R);
        setBgColorRow(tblNm, 15, scrnMsg.glnNum_L, scrnMsg.glnNum_R);
        setBgColorRow(tblNm, 16, scrnMsg.dsPrntDunsNum_L, scrnMsg.dsPrntDunsNum_R);
        setBgColorRow(tblNm, 17, scrnMsg.hqDunsNum_L, scrnMsg.hqDunsNum_R);
        setBgColorRow(tblNm, 18, scrnMsg.dsCustSicCd_LC, scrnMsg.dsCustSicCd_RC);
        setBgColorRow(tblNm, 19, scrnMsg.dsCustSicDescTxt_LC, scrnMsg.dsCustSicDescTxt_RC);
    }

    /**
     * setBgColorRow
     * @param tblNm EZDGUITableAttribute
     * @param rowNum int
     * @param leftItem EZDBStringItem
     * @param rightItem EZDBStringItem
     */
    private static final void setBgColorRow(EZDGUITableAttribute tblNm, int rowNum, EZDBStringItem leftItem, EZDBStringItem rightItem) {

        if (ZYPCommonFunc.hasValue(leftItem) && ZYPCommonFunc.hasValue(rightItem)) {
            if (!leftItem.getValue().equals(rightItem.getValue())) {
                tblNm.setRowStyle(rowNum, NMAL2810Constant.BG_COLOR_HIGHTLIGHT);
            }
        } else {
            if (ZYPCommonFunc.hasValue(leftItem) || ZYPCommonFunc.hasValue(rightItem)) {
                tblNm.setRowStyle(rowNum, NMAL2810Constant.BG_COLOR_HIGHTLIGHT);
            }
        }
    }

    /**
     * setBgColorRow
     * @param tblNm EZDGUITableAttribute
     * @param rowNum int
     * @param leftItem EZDBBigDecimalItem
     * @param rightItem EZDBBigDecimalItem
     */
    private static final void setBgColorRow(EZDGUITableAttribute tblNm, int rowNum, EZDBBigDecimalItem leftItem, EZDBBigDecimalItem rightItem) {

        if (ZYPCommonFunc.hasValue(leftItem) && ZYPCommonFunc.hasValue(rightItem)) {
            if (leftItem.getValue().compareTo(rightItem.getValue()) != 0) {
                tblNm.setRowStyle(rowNum, NMAL2810Constant.BG_COLOR_HIGHTLIGHT);
            }
        } else {
            if (ZYPCommonFunc.hasValue(leftItem) || ZYPCommonFunc.hasValue(rightItem)) {
                tblNm.setRowStyle(rowNum, NMAL2810Constant.BG_COLOR_HIGHTLIGHT);
            }
        }
    }

    /**
     * setBgColorForAPI
     * @param scrnMsg NMAL2810BMsg
     */
    public static final void setBgColorForAPI(NMAL2810BMsg scrnMsg) {

        EZDGUITableAttribute tableA = new EZDGUITableAttribute(NMAL2810Constant.SCRN_ID_00, NMAL2810Constant.TABLE_NAME_A);
        setInitBgColorRow(tableA, scrnMsg);

        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxVldStsCd_1.getValue())) {
            tableA.setRowStyle(0, NMAL2810Constant.BG_COLOR_ERROR);
        }
        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxVldStsCd_2.getValue())) {
            tableA.setRowStyle(1, NMAL2810Constant.BG_COLOR_ERROR);
        }
        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxVldStsCd_3.getValue())) {
            tableA.setRowStyle(4, NMAL2810Constant.BG_COLOR_ERROR);
        }
        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxVldStsCd_4.getValue())) {
            tableA.setRowStyle(5, NMAL2810Constant.BG_COLOR_ERROR);
        }
        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxVldStsCd_5.getValue())) {
            tableA.setRowStyle(6, NMAL2810Constant.BG_COLOR_ERROR);
        }

        scrnMsg.addGUIAttribute(tableA);
    }
}
