/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7150.common;

import static business.servlet.NMAL7150.constant.NMAL7150Constant.BIZ_ID;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_APL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_APR;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_RST;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_DELETE;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_REPROCESS;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_SEARCH;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_SELECT_ALL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.FUNC_ID_DELETE;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.FUNC_ID_FULL;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.FUNC_ID_READ;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.SCRN_ID_00;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.ZZSM4300E;

import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7150.NMAL7150BMsg;
import business.servlet.NMAL7150.NMAL7150Bean;
import business.servlet.NMAL7150.NMAL7150_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150CommonLogic {
    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7150BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NMAL7150BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        initCommonButton(handler);
        initButton(handler);
        scrnMsg.setFocusItem(scrnMsg.effFromDt_TD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_E, ZYPConstant.CHKBOX_ON_Y);
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
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
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
        handler.setButtonProperties(BTN_SELECT_ALL[0], BTN_SELECT_ALL[1], BTN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(BTN_UN_SELECT_ALL[0], BTN_UN_SELECT_ALL[1], BTN_UN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(BTN_DELETE[0], BTN_DELETE[1], BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_REPROCESS[0], BTN_REPROCESS[1], BTN_REPROCESS[2], 0, null);
    }

    /**
     * headerAddCheckItem
     * @param scrnMsg NMAL7150BMsg
     */
    public static void headerAddCheckItem(NMAL7150BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TD);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_TD);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_D);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_E);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_R);
    }

    /**
     * detailAddCheckItem
     * @param scrnMsg NMAL7150BMsg
     */
    public static void detailAddCheckItem(NMAL7150BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NMAL7150Bean.xxChkBox_A, //
                NMAL7150Bean.dlrRefNum_A, //
                NMAL7150Bean.prevCsmpNum_A, //
                NMAL7150Bean.prevUsrTxt_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * clearMandantoryCheck
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckDetail(NMAL7150BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7150_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            if (scrnLineMsg.dlrRefNum_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("dlrRefNum_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.dlrRefNum_A.clearErrorInfo();
                }
            }
        }
    }

    /**
     * controlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7150BMsg
     */
    public static void controlScreen(EZDCommonHandler handler, NMAL7150BMsg scrnMsg) {
        List<String> funcList = getFuncId();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7150_ABMsg scrnMsgA = scrnMsg.A.no(i);
            scrnMsgA.xxScrItem54Txt_A.setInputProtected(true);
            scrnMsgA.csmpErrMsgTxt_A.setInputProtected(true);
            scrnMsgA.rtlDlrCd_A.setInputProtected(true);
            scrnMsgA.dsAcctNm_A.setInputProtected(true);
            scrnMsgA.xxScrItem40Txt_A.setInputProtected(true);
            scrnMsgA.crListTxt_A.setInputProtected(true);
            scrnMsgA.crListGnrnNum_A.setInputProtected(true);
            if (funcList.contains(FUNC_ID_FULL)) {
                continue;
            } else if (funcList.contains(FUNC_ID_DELETE)) {
                scrnMsgA.dlrRefNum_A.setInputProtected(true);
                scrnMsgA.prevCsmpNum_A.setInputProtected(true);
                scrnMsgA.prevUsrTxt_A.setInputProtected(true);
            } else {
                scrnMsgA.dlrRefNum_A.setInputProtected(true);
                scrnMsgA.prevCsmpNum_A.setInputProtected(true);
                scrnMsgA.prevUsrTxt_A.setInputProtected(true);
                scrnMsgA.xxChkBox_A.setInputProtected(true);
            }
        }

        boolean enableFlag = true;
        if (scrnMsg.A.getValidCount() <= 0) {
            enableFlag = false;
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
        }

        handler.setButtonEnabled(BTN_SELECT_ALL[0], enableFlag);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL[0], enableFlag);
        handler.setButtonEnabled(BTN_DELETE[0], enableFlag);
        handler.setButtonEnabled(BTN_REPROCESS[0], enableFlag);
        handler.setButtonEnabled(BTN_CMN_SUB[0], enableFlag);

        if (funcList.contains(FUNC_ID_FULL)) {
            return;
        } else if (funcList.contains(FUNC_ID_DELETE)) {
            handler.setButtonEnabled(BTN_REPROCESS[0], false);
        } else if (funcList.contains(FUNC_ID_READ)) {
            handler.setButtonEnabled(BTN_SELECT_ALL[0], false);
            handler.setButtonEnabled(BTN_UN_SELECT_ALL[0], false);
            handler.setButtonEnabled(BTN_DELETE[0], false);
            handler.setButtonEnabled(BTN_REPROCESS[0], false);
            handler.setButtonEnabled(BTN_CMN_SUB[0], false);
            handler.setButtonEnabled(BTN_CMN_CLR[0], false);
        }
    }

    /**
     * getFuncId
     * @return List<String>
     */
    public static List<String> getFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        return funcList;
    }
}
