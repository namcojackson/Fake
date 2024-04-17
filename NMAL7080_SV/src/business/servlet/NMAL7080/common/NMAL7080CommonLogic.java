/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080.common;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.*;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_APL;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_APR;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_RST;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_MASS_UPDATE;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.BTN_SEARCH;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.CMN_PRM_SORT_NUM;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.CMN_PRM_WHERE_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7080.NMAL7080BMsg;
import business.servlet.NMAL7080.NMAL7080Bean;
import business.servlet.NMAL7080.NMAL7080_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080CommonLogic {

    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7080BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NMAL7080BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        if (scrnMsg.A.getValidCount() > 0) {
            searchScreen(handler, scrnMsg);
        } else {
            initScreen(handler, scrnMsg);
        }

        scrnMsg.setFocusItem(scrnMsg.splyAgmtPlnNm);
    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * initButton
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_APPLY[0], BTN_APPLY[1], BTN_APPLY[2], 1, null);
        handler.setButtonProperties(BTN_MASS_UPDATE[0], BTN_MASS_UPDATE[1], BTN_MASS_UPDATE[2], 1, null);
        handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 1, null);
        handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 1, null);
    }

    /**
     * initScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void initScreen(EZDCommonHandler handler, NMAL7080BMsg scrnMsg) {

        scrnMsg.splyAgmtPlnPk.setInputProtected(false);
        scrnMsg.splyAgmtPlnNm.setInputProtected(false);
        scrnMsg.splyAgmtPlnShortNm.setInputProtected(false);
        scrnMsg.splyAgmtPlnDescTxt.setInputProtected(false);
        scrnMsg.splyAgmtPlnTpCd.setInputProtected(false);
        scrnMsg.splyAgmtDocTpCd.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.annTermCapNum.setInputProtected(true);
        scrnMsg.splyAgmtPlnStsCd.setInputProtected(false);
        scrnMsg.xxChkBox_SD.setInputProtected(true);
        scrnMsg.mdseCd_IC.setInputProtected(true);
        handler.setButtonEnabled(BTN_APPLY[0], false);
        scrnMsg.xxDt10Dt_MD.setInputProtected(true);
        handler.setButtonEnabled(BTN_MASS_UPDATE[0], false);
        handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        handler.setButtonEnabled(BTN_DELETE_ROW[0], false);

        handler.setButtonEnabled(BTN_CMN_SUB[0], false);
        handler.setButtonEnabled(BTN_CMN_DWL[0], false);
        handler.setButtonEnabled(BTN_CMN_DEL[0], false);
        checkFuncId(handler);
    }

    /**
     * searchScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void searchScreen(EZDCommonHandler handler, NMAL7080BMsg scrnMsg) {

        scrnMsg.splyAgmtPlnPk.setInputProtected(true);
        scrnMsg.splyAgmtPlnNm.setInputProtected(false);
        scrnMsg.splyAgmtPlnShortNm.setInputProtected(false);
        scrnMsg.splyAgmtPlnDescTxt.setInputProtected(false);
        scrnMsg.splyAgmtPlnTpCd.setInputProtected(false);
        scrnMsg.splyAgmtDocTpCd.setInputProtected(true);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.hdrLvlQtyEntryFlg.getValue())) {
            scrnMsg.annTermCapNum.setInputProtected(false);
        } else {
            scrnMsg.annTermCapNum.setInputProtected(true);
        }

        scrnMsg.splyAgmtPlnStsCd.setInputProtected(false);
        scrnMsg.xxChkBox_SD.setInputProtected(false);
        scrnMsg.mdseCd_IC.setInputProtected(false);
        handler.setButtonEnabled(BTN_APPLY[0], true);
        scrnMsg.xxDt10Dt_MD.setInputProtected(false);
        handler.setButtonEnabled(BTN_MASS_UPDATE[0], true);

        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        } else {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        }

        handler.setButtonEnabled(BTN_DELETE_ROW[0], true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7080_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.mdseCd_A.setInputProtected(false);
            abMsg.mdseDescShortTxt_A.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.hdrLvlQtyEntryFlg.getValue())) {
                abMsg.splyAgmtFreqTpCd_A.setInputProtected(true);
                abMsg.splyAgmtPlnFirstQty_A.setInputProtected(true);
                abMsg.splyAgmtPlnQty_A.setInputProtected(true);
                abMsg.splyAgmtPlnSqNum_A.setInputProtected(true);
            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.dtlLvlQtyEntryFlg.getValue())) {
                abMsg.splyAgmtFreqTpCd_A.setInputProtected(false);
                abMsg.splyAgmtPlnFirstQty_A.setInputProtected(false);
                abMsg.splyAgmtPlnQty_A.setInputProtected(false);
                abMsg.splyAgmtPlnSqNum_A.setInputProtected(false);
            }
            abMsg.xxFullNm_AC.setInputProtected(true);
            abMsg.xxDt10Dt_AC.setInputProtected(false);
            abMsg.xxFullNm_AU.setInputProtected(true);
            abMsg.xxDt10Dt_AU.setInputProtected(false);
        }
        handler.setButtonEnabled(BTN_CMN_SUB[0], true);
        handler.setButtonEnabled(BTN_CMN_DWL[0], true);
        handler.setButtonEnabled(BTN_CMN_DEL[0], true);
        checkFuncId(handler);

    }

    /**
     * insertRowScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void insertRowScreen(EZDCommonHandler handler, NMAL7080BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.hdrLvlQtyEntryFlg.getValue())) {
            scrnMsg.annTermCapNum.setInputProtected(false);
        } else {
            scrnMsg.annTermCapNum.setInputProtected(true);
        }
        scrnMsg.splyAgmtPlnStsCd.setInputProtected(false);
        scrnMsg.xxChkBox_SD.setInputProtected(false);
        scrnMsg.mdseCd_IC.setInputProtected(false);
        handler.setButtonEnabled(BTN_APPLY[0], true);
        scrnMsg.xxDt10Dt_MD.setInputProtected(false);
        handler.setButtonEnabled(BTN_MASS_UPDATE[0], true);
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        } else {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        }
        handler.setButtonEnabled(BTN_DELETE_ROW[0], true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7080_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.mdseCd_A.setInputProtected(false);
            abMsg.mdseDescShortTxt_A.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.hdrLvlQtyEntryFlg.getValue())) {
                abMsg.splyAgmtFreqTpCd_A.setInputProtected(true);
                ZYPEZDItemValueSetter.setValue(abMsg.splyAgmtFreqTpCd_A, SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER);
                abMsg.splyAgmtPlnFirstQty_A.setInputProtected(true);
                abMsg.splyAgmtPlnFirstQty_A.clear();
                abMsg.splyAgmtPlnQty_A.setInputProtected(true);
                abMsg.splyAgmtPlnQty_A.clear();
                abMsg.splyAgmtPlnSqNum_A.setInputProtected(true);
                abMsg.splyAgmtPlnSqNum_A.clear();

            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.dtlLvlQtyEntryFlg.getValue())) {
                abMsg.splyAgmtFreqTpCd_A.setInputProtected(false);
                abMsg.splyAgmtPlnFirstQty_A.setInputProtected(false);
                abMsg.splyAgmtPlnQty_A.setInputProtected(false);
                abMsg.splyAgmtPlnSqNum_A.setInputProtected(false);
            }
            abMsg.xxFullNm_AC.setInputProtected(true);
            abMsg.xxDt10Dt_AC.setInputProtected(false);
            abMsg.xxFullNm_AU.setInputProtected(true);
            abMsg.xxDt10Dt_AU.setInputProtected(false);
        }
        handler.setButtonEnabled(BTN_CMN_SUB[0], true);
        handler.setButtonEnabled(BTN_CMN_DWL[0], true);
        checkFuncId(handler);
    }

    /**
     * headerAddCheckItem
     * @param scrnMsg NMAL7070BMsg
     */
    public static void headerAddCheckItem(NMAL7080BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnPk);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnNm);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnShortNm);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnDescTxt);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnTpCd);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtDocTpCd);
        scrnMsg.addCheckItem(scrnMsg.annTermCapNum);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_IC);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnStsCd);
    }

    /**
     * zeroDetailScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void zeroDetailScreen(EZDCommonHandler handler, NMAL7080BMsg scrnMsg) {
        handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
        handler.setButtonEnabled(BTN_CMN_DEL[0], false);
//        scrnMsg.mdseCd_IC.setInputProtected(true);
//        handler.setButtonEnabled(BTN_APPLY[0], false);
        scrnMsg.xxDt10Dt_MD.setInputProtected(true);
        handler.setButtonEnabled(BTN_MASS_UPDATE[0], false);
        scrnMsg.xxChkBox_SD.setInputProtected(true);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL7070BMsg
     */
    public static void detailAddCheckItem(NMAL7080BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NMAL7080Bean.xxChkBox_A, NMAL7080Bean.mdseCd_A, NMAL7080Bean.splyAgmtFreqTpCd_A, NMAL7080Bean.splyAgmtPlnFirstQty_A, NMAL7080Bean.splyAgmtPlnQty_A, NMAL7080Bean.splyAgmtPlnSqNum_A,
                NMAL7080Bean.effFromDt_A, NMAL7080Bean.effThruDt_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * clearMandantoryCheck
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckHeader(NMAL7080BMsg scrnMsg) {

        // Header
        if (scrnMsg.splyAgmtPlnNm.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("splyAgmtPlnNm");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.splyAgmtPlnNm.clearErrorInfo();
            }
        }
        if (scrnMsg.splyAgmtPlnTpCd.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("splyAgmtPlnTpCd");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.splyAgmtPlnTpCd.clearErrorInfo();
            }
        }
        if (scrnMsg.splyAgmtDocTpCd.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("splyAgmtDocTpCd");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.splyAgmtDocTpCd.clearErrorInfo();
            }
        }
        if (scrnMsg.effFromDt.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt.clearErrorInfo();
            }
        }

    }

    /**
     * clearMandantoryCheck
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckDetail(NMAL7080BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7080_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            if (scrnLineMsg.mdseCd_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("mdseCd_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.mdseCd_A.clearErrorInfo();
                }
            }
            
            if (scrnLineMsg.splyAgmtFreqTpCd_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("splyAgmtFreqTpCd_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.splyAgmtFreqTpCd_A.clearErrorInfo();
                }
            }
            if (scrnLineMsg.splyAgmtPlnQty_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("splyAgmtPlnQty_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.splyAgmtPlnQty_A.clearErrorInfo();
                }
            }
            if (scrnLineMsg.splyAgmtPlnSqNum_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("splyAgmtPlnSqNum_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.splyAgmtPlnSqNum_A.clearErrorInfo();
                }
            }
            if (scrnLineMsg.effFromDt_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("effFromDt_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.effFromDt_A.clearErrorInfo();
                }
            }
        }
    }

    /**
     * setPlanIdPopupParam
     * @param scrnMsg NMAL7080BMsg
     * @return Object[]
     */
    public static Object[] setPlanIdPopupParam(NMAL7080BMsg scrnMsg) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "Supply Agreement Plan Search";

        tblNmP2.append("SPLY_AGMT_PLN");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Plan ID";
        whereArray0[1] = "SPLY_AGMT_PLN_PK";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Plan Name";
        whereArray1[1] = "SPLY_AGMT_PLN_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
        whereArray2[0] = "Effective From Date";
        whereArray2[1] = "EFF_FROM_DT";
        whereArray2[2] = null;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray2);

        Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
        whereArray3[0] = "Effective Thru Date";
        whereArray3[1] = "EFF_THRU_DT";
        whereArray3[2] = null;
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray3);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Plan ID";
        columnArray0[1] = "SPLY_AGMT_PLN_PK";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Plan Name";
        columnArray1[1] = "SPLY_AGMT_PLN_NM";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray2[0] = "Effective From Date";
        columnArray2[1] = "EFF_FROM_DT";
        columnArray2[2] = BigDecimal.valueOf(20);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray3[0] = "Effective Thru Date";
        columnArray3[1] = "EFF_THRU_DT";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray3);

        Object[] sortConditionArray0 = new Object[CMN_PRM_SORT_NUM];
        sortConditionArray0[0] = "SPLY_AGMT_PLN_PK";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * hasUpdateFuncId
     * @param handler EZDCommonHandler
     */
    public static void checkFuncId(EZDCommonHandler handler) {
        if (!hasUpdateFuncId()) {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_MASS_UPDATE[0], false);
            handler.setButtonEnabled(BTN_CMN_SUB[0], false);
            handler.setButtonEnabled(BTN_CMN_DEL[0], false);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }

        return false;
    }
}
