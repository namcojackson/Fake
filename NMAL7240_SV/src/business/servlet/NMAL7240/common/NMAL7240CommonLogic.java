/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240.common;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_APL;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_APR;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_RST;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_LOB;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_SEARCH;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_SVCLVL;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BTN_UPLOAD;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.SCRN_ID_00;

import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7240.NMAL7240BMsg;
import business.servlet.NMAL7240.NMAL7240Bean;
import business.servlet.NMAL7240.NMAL7240_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL7240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/26   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7240CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7240BMsg
     */
    public static final void controlScreen(EZDCommonHandler handler, NMAL7240BMsg scrnMsg) {
        controlScreenFields(scrnMsg);
        setCmnBtnProp(handler);
        setBizBtnProp(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void setCmnBtnProp(EZDCommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        // QC#7040 2016/05/26 Mod start
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // QC#7040 2016/05/26 Mod start
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NMAL7240BMsg
     */
    private static void setBizBtnProp(EZDCommonHandler handler, NMAL7240BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 1, null);
        handler.setButtonProperties(BTN_UPLOAD[0], BTN_UPLOAD[1], BTN_UPLOAD[2], 1, null);
        if (scrnMsg.A.getValidCount() > 0
                && hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 0, null);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).frtRateShpgPk_A1)
                    && hasUpdateFuncId()) {
                handler.setButtonEnabled(BTN_LOB[0], i, true);
                handler.setButtonEnabled(BTN_SVCLVL[0], i, true);
            } else {
                handler.setButtonEnabled(BTN_LOB[0], i, false);
                handler.setButtonEnabled(BTN_SVCLVL[0], i, false);
            }
        }
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7240BMsg
     */
    private static final void controlScreenFields(NMAL7240BMsg scrnMsg) {
        // Header
        scrnMsg.lineBizTpCd.setInputProtected(false);
        scrnMsg.frtZoneNum.setInputProtected(false);
        scrnMsg.xxLinkAncr_HL.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt_HL.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.xxFileData_UP.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).shpgFrtRate_A1.setInputProtected(false);
            scrnMsg.A.no(i).frtRatePerNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).frtRateShpgPk_A1)) {
                scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).frtZoneNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).fromSclQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).frtZoneNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).fromSclQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
            }
        }
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7240BMsg
     * @param scrnAMsgAry NMAL7240_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7240BMsg scrnMsg, NMAL7240_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7240BMsg
     * @param scrnAMsgAry NMAL7240_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7240BMsg scrnMsg, NMAL7240_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7240BMsg
     * @param scrnAMsgAry NMAL7240_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7240BMsg scrnMsg, NMAL7240_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemForHeader
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForHeader(NMAL7240BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.frtZoneNum);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_HL);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
    }

    /**
     * clearMandantoryCheck
     * 
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckDetail(NMAL7240BMsg scrnMsg) {

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).lineBizTpDescTxt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("lineBizTpDescTxt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).lineBizTpDescTxt_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).frtZoneNum_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("frtZoneNum_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).frtZoneNum_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("shpgSvcLvlDescTxt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).fromSclQty_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("fromSclQty_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).fromSclQty_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).shpgFrtRate_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("shpgFrtRate_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).shpgFrtRate_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).frtRatePerNum_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("frtRatePerNum_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).frtRatePerNum_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).effFromDt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
                }
            }
        }
    }

    /**
     * addCheckItemForHeader
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForDetail(NMAL7240BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            if (scrnMsg.A.no(i).lineBizTpDescTxt_A1.isInputProtected()) {
                scrnMsg.A.no(i).lineBizTpDescTxt_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).lineBizTpDescTxt_A1);
            if (scrnMsg.A.no(i).frtZoneNum_A1.isInputProtected()) {
                scrnMsg.A.no(i).frtZoneNum_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtZoneNum_A1);
            if (scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.isInputProtected()) {
                scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1);
            if (scrnMsg.A.no(i).fromSclQty_A1.isInputProtected()) {
                scrnMsg.A.no(i).fromSclQty_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromSclQty_A1);
            if (scrnMsg.A.no(i).qtyUnitTpCd_A1.isInputProtected()) {
                scrnMsg.A.no(i).qtyUnitTpCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).qtyUnitTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgFrtRate_A1);
            if (scrnMsg.A.no(i).frtRateCcyCd_A1.isInputProtected()) {
                scrnMsg.A.no(i).frtRateCcyCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRateCcyCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRatePerNum_A1);
            if (scrnMsg.A.no(i).perUnitTpCd_A1.isInputProtected()) {
                scrnMsg.A.no(i).perUnitTpCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).perUnitTpCd_A1);
            if (scrnMsg.A.no(i).effFromDt_A1.isInputProtected()) {
                scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
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
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains("NMAL7240T020")) {
            return true;
        }

        return false;
    }

    /**
     * setFocusRule
     * 
     * @param scrnMsg NMAL7240BMsg
     */
    public static void setFocusRule(NMAL7240BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCRN_ID_00, "");
        ZYPGUIFocusRule focusRule = null;

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.lineBizTpCd);
        focusRule.setNextId(NMAL7240Bean.frtZoneNum);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.frtZoneNum);
        focusRule.setPrevId(NMAL7240Bean.lineBizTpCd);
        focusRule.setNextId(NMAL7240Bean.xxDsMultMsgDplyTxt_HL);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.xxDsMultMsgDplyTxt_HL);
        focusRule.setPrevId(NMAL7240Bean.frtZoneNum);
        focusRule.setNextId(NMAL7240Bean.effFromDt);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.effFromDt);
        focusRule.setPrevId(NMAL7240Bean.xxDsMultMsgDplyTxt_HL);
        focusRule.setNextId(NMAL7240Bean.effThruDt);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.effThruDt);
        focusRule.setPrevId(NMAL7240Bean.effFromDt);
        focusRule.setNextId(NMAL7240Bean.actvFlg);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7240Bean.actvFlg);
        focusRule.setPrevId(NMAL7240Bean.effThruDt);
        tblFocusRule.addRule(focusRule);

        scrnMsg.addGUIAttribute(tblFocusRule);
    }
}
