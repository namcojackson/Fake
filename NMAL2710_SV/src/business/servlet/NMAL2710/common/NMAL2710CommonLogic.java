/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710.common;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_APL;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_APR;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_RST;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_GET_ORG_CD;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_RQST_HIST;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_SEARCH;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.BTN_UPLOAD;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;

import business.servlet.NMAL2710.NMAL2710BMsg;
import business.servlet.NMAL2710.NMAL2710_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL2710CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#10642
 *</pre>
 */
public class NMAL2710CommonLogic {

    /**
     * Screen Item properties control.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL2710BMsg
     */
    public static void controlScreen(S21CommonHandler handler, NMAL2710BMsg scrnMsg) {
        cmnBtnProp(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void cmnBtnProp(S21CommonHandler handler) {
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
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7230BMsg
     */
    private static final void controlScreenFields(S21CommonHandler handler, NMAL2710BMsg scrnMsg) {

        // Header
        scrnMsg.bizAreaOrgCd_H.setInputProtected(false);
        scrnMsg.trtyGrpTpCd_H.setInputProtected(false);
        scrnMsg.orgNm_H.setInputProtected(false);
        scrnMsg.trtyRuleFromValTxt_H.setInputProtected(false);
        scrnMsg.trtyRuleThruValTxt_H.setInputProtected(false);

        // Search Button
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);

        // Select All and UnSelect All
        scrnMsg.xxChkBox_DH.setInputProtected(false);

        // Detail Control
        scrnMsg.orgCd_DC.setInputProtected(true);
        scrnMsg.orgNm_DC.setInputProtected(false);
        scrnMsg.effFromDt_DC.setInputProtected(false);
        scrnMsg.effThruDt_DC.setInputProtected(false);
        scrnMsg.rqstRsltCmntTxt_DC.setInputProtected(false);

        // CSV Upload Button
        handler.setButtonProperties(BTN_UPLOAD[0], BTN_UPLOAD[1], BTN_UPLOAD[2], 1, null);

        // Get Territory Code
        handler.setButtonProperties(BTN_GET_ORG_CD[0], BTN_GET_ORG_CD[1], BTN_GET_ORG_CD[2], 1, null);

        // Request History Pop up Button
        handler.setButtonProperties(BTN_RQST_HIST[0], BTN_RQST_HIST[1], BTN_RQST_HIST[2], 1, null);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).orgNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).orgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bizAreaOrgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trtyGrpTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trtyRuleOprdTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trtyRuleFromValTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trtyRuleThruValTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trtyRuleLogicTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
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
     * @param scrnMsg     NMAL2710BMsg
     * @param scrnAMsgAry NMAL2710_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2710BMsg scrnMsg, NMAL2710_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2710BMsg
     * @param scrnAMsgAry NMAL2710_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2710BMsg scrnMsg, NMAL2710_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL2710BMsg
     * @param scrnAMsgAry NMAL2710_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2710BMsg scrnMsg, NMAL2710_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
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

        if (funcList.contains("NMAL2710T020")) {
            return true;
        }

        return false;
    }

    /**
     * headerAddCheckItem
     * @param scrnMsg NMAL2710BMsg
     */
    public static void addCheckItemForHeader(NMAL2710BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_H);
        scrnMsg.addCheckItem(scrnMsg.trtyGrpTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H);
        scrnMsg.addCheckItem(scrnMsg.trtyRuleFromValTxt_H);
        scrnMsg.addCheckItem(scrnMsg.trtyRuleThruValTxt_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_DH);
    }

    // QC#10642 2016/06/29 Add start
    /**
     * clearMandantoryCheckDetailControl
     * @param scrnMsg NMAL2710BMsg
     */
    public static void clearMandantoryCheckDetailControl(NMAL2710BMsg scrnMsg) {
        if (scrnMsg.orgNm_DC.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("orgNm_DC");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.orgNm_DC.clearErrorInfo();
            }
        }
        if (scrnMsg.effFromDt_DC.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt_DC");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt_DC.clearErrorInfo();
            }
        }
    }
    // QC#10642 2016/06/29 Add end

    /**
     * DetailControlAddCheckItem
     * @param scrnMsg NMAL2710BMsg
     */
    public static void addCheckItemForDetailControl(NMAL2710BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.orgNm_DC);
        scrnMsg.addCheckItem(scrnMsg.orgCd_DC);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_DC);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_DC);
        scrnMsg.addCheckItem(scrnMsg.rqstRsltCmntTxt_DC);
    }

    /**
     * DetailAddCheckItem
     * @param scrnMsg NMAL2710BMsg
     */
    public static void addCheckItemForDetail(NMAL2710BMsg scrnMsg) {
        // QC#10642 2016/06/29 Add start
        clearMandantoryCheckDetailControl(scrnMsg);
        // QC#10642 2016/06/29 Add end
        addCheckItemForDetailControl(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
    }
}
