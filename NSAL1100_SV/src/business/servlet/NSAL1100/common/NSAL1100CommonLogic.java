/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1100.common;

import static business.servlet.NSAL1100.constant.NSAL1100Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1100.NSAL1100BMsg;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/25   Hitachi         K.Yamada        Update          CSA QC#6946
 * 2018/06/12   Fujitsu         T.Ogura         Update          QC#22382
 *</pre>
 */
public class NSAL1100CommonLogic {

    /**
     * set input parameter
     * @param scrnMsg NSAL1100BMsg
     * @param arg Serializable
     */
    public static void setInputParam(NSAL1100BMsg scrnMsg, Serializable arg) {

        scrnMsg.xxModeCd_P.clear();
        scrnMsg.svcCrRebilPk_P.clear();
        scrnMsg.svcCrRebilProcCd_P.clear();
        ZYPTableUtil.clear(scrnMsg.P);

        if (!(arg instanceof Object[])) {
            return;
        }

        Object[] params = (Object[]) arg;
        if (params.length < NSAL1100_PRM_LENGTH) {
            return;
        }

        if (params[0] instanceof EZDBStringItem) {
            setValue(scrnMsg.xxModeCd_P, ((EZDBStringItem) params[0]));
        }
        if (params[1] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.svcCrRebilPk_P, ((EZDBBigDecimalItem) params[1]));
        }
        if (params[2] instanceof EZDBStringItem) {
            setValue(scrnMsg.svcCrRebilProcCd_P, ((EZDBStringItem) params[2]));
        }
        if (params[3] instanceof EZDBMsgArray) {
            EZDBMsgArray params8 = (EZDBMsgArray) params[3];
            EZDMsg.copy(params8, null, scrnMsg.P, null);
        }
    }

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1100BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL1100BMsg scrnMsg) {

        initControlCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * init Control Common Button
     * @param handler EZDCommonHandler
     */
    private static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1100BMsg
     */
    private static void controlScreenFields(EZDCommonHandler handler, NSAL1100BMsg scrnMsg) {

        if (!hasValue(scrnMsg.custIncdtId_H)) {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            scrnMsg.svcCrRebilRsnCd.setInputProtected(true);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            scrnMsg.svcCrRebilRsnTxt.setInputProtected(true);
            handler.setButtonEnabled(BTN_CONTINUE, false);
            handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, false);
            handler.setButtonEnabled(BTN_CANCEL_CI, false);
        } else if (MODE_SUBMIT_FOR_APPROVAL.equals(scrnMsg.xxModeCd_P.getValue())) {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            scrnMsg.svcCrRebilRsnCd.setInputProtected(false);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            scrnMsg.svcCrRebilRsnTxt.setInputProtected(false);
            handler.setButtonEnabled(BTN_CONTINUE, true);
            handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, false);
            handler.setButtonEnabled(BTN_CANCEL_CI, false);
        } else if (MODE_CANCEL_CI.equals(scrnMsg.xxModeCd_P.getValue())) {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            scrnMsg.svcCrRebilRsnCd.setInputProtected(true);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            scrnMsg.svcCrRebilRsnTxt.setInputProtected(false);
            handler.setButtonEnabled(BTN_CONTINUE, false);
            handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, false);
            handler.setButtonEnabled(BTN_CANCEL_CI, true);
        } else if (MODE_VIEW_APPROVERS.equals(scrnMsg.xxModeCd_P.getValue())) {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            scrnMsg.svcCrRebilRsnCd.setInputProtected(false);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            scrnMsg.svcCrRebilRsnTxt.setInputProtected(false);
            handler.setButtonEnabled(BTN_CONTINUE, false);
            // mod start 2016/04/25 CSA Defect#6946
            if (hasUpdateFuncId() && hasWait4ApprovalTask(scrnMsg)) {
            // mod end 2016/04/25 CSA Defect#6946
                handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, true);
            } else {
                handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, false);
            }
            handler.setButtonEnabled(BTN_CANCEL_CI, false);
        } else {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            scrnMsg.svcCrRebilRsnCd.setInputProtected(true);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            scrnMsg.svcCrRebilRsnTxt.setInputProtected(true);
            handler.setButtonEnabled(BTN_CONTINUE, false);
            handler.setButtonEnabled(BTN_OVERRIDE_APPROVE, false);
            handler.setButtonEnabled(BTN_CANCEL_CI, false);
        }

        scrnMsg.custIncdtId_H.setInputProtected(true);
        scrnMsg.svcCrRebilStsDescTxt_H.setInputProtected(true);
        scrnMsg.svcCrRebilDescTxt_H.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxWfAsgToNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxWfCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxWfActOpNm_A.setInputProtected(true);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Supplemantal Task (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        if (funcList.contains(AUTH_UPDATE)) {
            return true;
        }
        return false;
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL1100BMsg
     */
    private static void setRowColors(NSAL1100BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // add start 2016/04/25 CSA Defect#6946
    private static boolean hasWait4ApprovalTask(NSAL1100BMsg scrnMsg) {
        boolean hasWait4ApprovalTask = false;

        String separator = System.getProperty("line.separator");
        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (hasValue(scrnMsg.A.no(i).xxDtTxt_A)) {
                continue;
            }
            String[] asgToUsrIdList = scrnMsg.A.no(i).xxWfAsgToNm_ID.getValue().split(separator);
            if (Arrays.asList(asgToUsrIdList).contains(userId)) {
                hasWait4ApprovalTask = true;
                break;
            }
        }
        return hasWait4ApprovalTask;
    }
    // add end 2016/04/25 CSA Defect#6946
}
