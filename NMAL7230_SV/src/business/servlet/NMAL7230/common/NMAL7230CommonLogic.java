/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230.common;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_APL;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_APR;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_RST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_CTRY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_LOB;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_PRC;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_SEARCH;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_ST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.BTN_UPLOAD;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.FIVE_POSTALCODE_PATTERN;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8178E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8409E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POSTALCODE_ZERO_PACK;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.SCRN_ID_00;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.TEN_POSTALCODE_PATTERN;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7230.NMAL7230BMsg;
import business.servlet.NMAL7230.NMAL7230Bean;
import business.servlet.NMAL7230.NMAL7230_ABMsg;
import business.servlet.NMAL7230.NMAL7230_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL7230CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7230CommonLogic {

    /**
     * Initial Common Button properties.
     * @param scrnMsg NMAL7230BMsg
     * @param handler Event Handler
     */
    public static void controlScreen(EZDCommonHandler handler, NMAL7230BMsg scrnMsg) {
        controlScreenFields(scrnMsg);
        setCmnBtnProp(handler);
        setBizBtnProp(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * @param scrnMsg NMAL7230BMsg
     * @param handler Event Handler
     */
    private static void setCmnBtnProp(EZDCommonHandler handler) {
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
        // QC#7040 2016/05/26 Mod end
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7230BMsg
     */
    private static void setBizBtnProp(EZDCommonHandler handler, NMAL7230BMsg scrnMsg) {
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
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).frtZonePk_A1)
                    && hasUpdateFuncId()) {
                handler.setButtonEnabled(BTN_LOB[0], i, true);
                handler.setButtonEnabled(BTN_PRC[0], i, true);
                handler.setButtonEnabled(BTN_ST[0], i, true);
                handler.setButtonEnabled(BTN_CTRY[0], i, true);
            } else {
                handler.setButtonEnabled(BTN_LOB[0], i, false);
                handler.setButtonEnabled(BTN_PRC[0], i, false);
                handler.setButtonEnabled(BTN_ST[0], i, false);
                handler.setButtonEnabled(BTN_CTRY[0], i, false);
            }
        }
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7230BMsg
     */
    private static final void controlScreenFields(NMAL7230BMsg scrnMsg) {
        // Header
        scrnMsg.lineBizTpCd.setInputProtected(false);
        scrnMsg.frtZoneNum.setInputProtected(false);
        scrnMsg.xxLinkAncr_HS.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt_HS.setInputProtected(false);
        scrnMsg.xxLinkAncr_HC.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt_HC.setInputProtected(false);
        scrnMsg.xxLinkAncr_HA.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt_HA.setInputProtected(false);
        scrnMsg.xxLinkAncr_HG.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt_HG.setInputProtected(false);
        scrnMsg.shipToFromPostCd.setInputProtected(false);
        scrnMsg.shipToThruPostCd.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.xxFileData_UP.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).frtZoneNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).frtZonePk_A1)) {
                scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).prcGrpNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToStCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToCtryCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToFromPostCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToThruPostCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(false);
                scrnMsg.A.no(i).prcGrpNm_A1.setInputProtected(false);
                scrnMsg.A.no(i).shipToStCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).shipToCtryCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).shipToFromPostCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).shipToThruPostCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
            }
        }
    }

    /**
     * clearMandantoryCheck
     * 
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckDetail(NMAL7230BMsg scrnMsg) {

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
    public static void addCheckItemForHeader(NMAL7230BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.frtZoneNum);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_HS);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_HC);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_HA);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_HG);
        scrnMsg.addCheckItem(scrnMsg.shipToFromPostCd);
        scrnMsg.addCheckItem(scrnMsg.shipToThruPostCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
    }

    /**
     * mandatory combination check for ship to
     * @param scrnLineMsg NMAL7230_ABMsg
     * @param scrnMsg NMAL7230BMsg
     * @return check result
     */
    public static boolean checkCombinationMandatoryForShipto(NMAL7230_ABMsg scrnLineMsg, NMAL7230BMsg scrnMsg) {

        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(scrnLineMsg.shipToCtryCd_A1)) {
            scrnLineMsg.shipToCtryCd_A1.setErrorInfo(1, NMAM8178E, new String[] {scrnLineMsg.shipToStCd_A1.getNameForMessage(), scrnLineMsg.shipToCtryCd_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnLineMsg.shipToCtryCd_A1);
            isErr = true;
        // QC#9809 2016/06/13 Mod start
//        }
//
//        if (!ZYPCommonFunc.hasValue(scrnLineMsg.shipToStCd_A1)) {
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.ctryCd)
                    && scrnMsg.ctryCd.getValue().equals(scrnLineMsg.shipToCtryCd_A1.getValue())
                    && !ZYPCommonFunc.hasValue(scrnLineMsg.shipToStCd_A1)) {
        // QC#9809 2016/06/13 Mod end
                    scrnLineMsg.shipToStCd_A1.setErrorInfo(1, NMAM8178E, new String[] {scrnLineMsg.shipToCtryCd_A1.getNameForMessage(), scrnLineMsg.shipToStCd_A1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnLineMsg.shipToStCd_A1);
                    isErr = true;
                }
        }

        if (ZYPCommonFunc.hasValue(scrnLineMsg.shipToFromPostCd_A1)) {
            scrnLineMsg.shipToFromPostCd_A1.setErrorInfo(1, NMAM8409E, new String[] {scrnLineMsg.shipToCtryCd_A1.getNameForMessage() + "/" + scrnLineMsg.shipToStCd_A1.getNameForMessage(), scrnLineMsg.shipToFromPostCd_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnLineMsg.shipToFromPostCd_A1);
            isErr = true;
        }

        if (ZYPCommonFunc.hasValue(scrnLineMsg.shipToThruPostCd_A1)) {
            scrnLineMsg.shipToThruPostCd_A1.setErrorInfo(1, NMAM8409E, new String[] {scrnLineMsg.shipToCtryCd_A1.getNameForMessage() + "/" + scrnLineMsg.shipToStCd_A1.getNameForMessage(), scrnLineMsg.shipToThruPostCd_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnLineMsg.shipToThruPostCd_A1);
            isErr = true;
        }

        return isErr;
    }

    /**
     * mandatory combination check for postal code
     * @param scrnLineMsg NMAL7230_ABMsg
     * @param scrnMsg NMAL7230BMsg
     * @return check result
     */
    public static boolean checkCombinationMandatoryForPostalCode(NMAL7230_ABMsg scrnLineMsg, NMAL7230BMsg scrnMsg) {

        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(scrnLineMsg.shipToFromPostCd_A1)) {
            scrnLineMsg.shipToFromPostCd_A1.setErrorInfo(1, NMAM8178E, new String[] {scrnLineMsg.shipToThruPostCd_A1.getNameForMessage(), scrnLineMsg.shipToFromPostCd_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnLineMsg.shipToFromPostCd_A1);
            isErr = true;
        }

        if (!ZYPCommonFunc.hasValue(scrnLineMsg.shipToThruPostCd_A1)) {
            scrnLineMsg.shipToThruPostCd_A1.setErrorInfo(1, NMAM8178E, new String[] {scrnLineMsg.shipToFromPostCd_A1.getNameForMessage(), scrnLineMsg.shipToThruPostCd_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnLineMsg.shipToThruPostCd_A1);
            isErr = true;
        }

        return isErr;
    }

    /**
     * addCheckItemForDetail
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForDetail(NMAL7230BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7230_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A1);
            if (scrnLineMsg.lineBizTpDescTxt_A1.isInputProtected()) {
                scrnLineMsg.lineBizTpDescTxt_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.lineBizTpDescTxt_A1);
            if (scrnLineMsg.dsAcctNm_A1.isInputProtected()) {
                scrnLineMsg.dsAcctNm_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.dsAcctNm_A1);
            if (scrnLineMsg.prcGrpNm_A1.isInputProtected()) {
                scrnLineMsg.prcGrpNm_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpNm_A1);
            scrnMsg.addCheckItem(scrnLineMsg.frtZoneNum_A1);
            if (scrnLineMsg.shipToStCd_A1.isInputProtected()) {
                scrnLineMsg.shipToStCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.shipToStCd_A1);
            if (scrnLineMsg.shipToCtryCd_A1.isInputProtected()) {
                scrnLineMsg.shipToCtryCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.shipToCtryCd_A1);
            if (scrnLineMsg.shipToFromPostCd_A1.isInputProtected()) {
                scrnLineMsg.shipToFromPostCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.shipToFromPostCd_A1);
            if (scrnLineMsg.shipToThruPostCd_A1.isInputProtected()) {
                scrnLineMsg.shipToThruPostCd_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.shipToThruPostCd_A1);
            if (scrnLineMsg.effFromDt_A1.isInputProtected()) {
                scrnLineMsg.effFromDt_A1.clearErrorInfo();
            }
            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7230BMsg
     * @param scrnAMsgAry NMAL7230_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7230BMsg scrnMsg, NMAL7230_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7230BMsg
     * @param scrnAMsgAry NMAL7230_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7230BMsg scrnMsg, NMAL7230_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7230BMsg
     * @param scrnAMsgAry NMAL7230_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7230BMsg scrnMsg, NMAL7230_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains("NMAL7230T020")) {
            return true;
        }
        return false;

    }

    /**
     * setFocusRule
     * 
     * @param scrnMsg DWAL0230BMsg
     */
    public static void setFocusRule(NMAL7230BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCRN_ID_00, "");
        ZYPGUIFocusRule focusRule = null;

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.lineBizTpCd);
        focusRule.setNextId(NMAL7230Bean.frtZoneNum);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.frtZoneNum);
        focusRule.setPrevId(NMAL7230Bean.lineBizTpCd);
        focusRule.setNextId(NMAL7230Bean.xxDsMultMsgDplyTxt_HS);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.xxDsMultMsgDplyTxt_HS);
        focusRule.setPrevId(NMAL7230Bean.frtZoneNum);
        focusRule.setNextId(NMAL7230Bean.xxDsMultMsgDplyTxt_HC);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.xxDsMultMsgDplyTxt_HC);
        focusRule.setPrevId(NMAL7230Bean.xxDsMultMsgDplyTxt_HS);
        focusRule.setNextId(NMAL7230Bean.xxDsMultMsgDplyTxt_HA);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.xxDsMultMsgDplyTxt_HA);
        focusRule.setPrevId(NMAL7230Bean.xxDsMultMsgDplyTxt_HC);
        focusRule.setNextId(NMAL7230Bean.xxDsMultMsgDplyTxt_HG);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.xxDsMultMsgDplyTxt_HG);
        focusRule.setPrevId(NMAL7230Bean.xxDsMultMsgDplyTxt_HA);
        focusRule.setNextId(NMAL7230Bean.shipToFromPostCd);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.shipToFromPostCd);
        focusRule.setPrevId(NMAL7230Bean.xxDsMultMsgDplyTxt_HG);
        focusRule.setNextId(NMAL7230Bean.shipToThruPostCd);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.shipToThruPostCd);
        focusRule.setPrevId(NMAL7230Bean.shipToFromPostCd);
        focusRule.setNextId(NMAL7230Bean.effFromDt);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.effFromDt);
        focusRule.setPrevId(NMAL7230Bean.shipToThruPostCd);
        focusRule.setNextId(NMAL7230Bean.effThruDt);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.effThruDt);
        focusRule.setPrevId(NMAL7230Bean.effFromDt);
        focusRule.setNextId(NMAL7230Bean.actvFlg);
        tblFocusRule.addRule(focusRule);

        focusRule = new ZYPGUIFocusRule(NMAL7230Bean.actvFlg);
        focusRule.setPrevId(NMAL7230Bean.effThruDt);
        tblFocusRule.addRule(focusRule);

        scrnMsg.addGUIAttribute(tblFocusRule);
    }

    /**
     * <pre>
     * portal code format check
     * </pre>
     * @param val String
     * @return check result
     */
    public static boolean checkPortalCodeFormat(String val) {

        String regexFive = FIVE_POSTALCODE_PATTERN;
        Pattern pFive = Pattern.compile(regexFive);
        Matcher m5 = pFive.matcher(val);
        if (m5.find()) {
            return true;
        }

        String regexTen = TEN_POSTALCODE_PATTERN;
        Pattern pTen = Pattern.compile(regexTen);
        Matcher m10 = pTen.matcher(val);
        if (m10.find()) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * portal code format check
     * </pre>
     * @param val String
     * @return check result
     */
    public static String convertdigit5to10(String val) {

        String regexFive = FIVE_POSTALCODE_PATTERN;
        Pattern pFive = Pattern.compile(regexFive);
        Matcher m5 = pFive.matcher(val);
        if (m5.find()) {
            return val + POSTALCODE_ZERO_PACK;
        }

        return val;
    }
}
