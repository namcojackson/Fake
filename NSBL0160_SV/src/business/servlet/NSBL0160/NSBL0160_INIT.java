/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160;

import static business.servlet.NSBL0160.constant.NSBL0160Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0160.NSBL0160CMsg;
import business.servlet.NSBL0160.common.NSBL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/19   Hitachi         K.Kasai            Update          QC#3689
 * 2016/12/27   Hitachi         K.Ochiai           Update          QC#16331
 *</pre>
 */
public class NSBL0160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg = new NSBL0160CMsg();

        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // START 2015/10/29 T.Tomita [N/A, MOD]
            setValue(scrnMsg.svcMemoCatgCd_HD, (EZDBStringItem) params[ARGS_SVC_MEMO_CATG_CD]);
            setValue(scrnMsg.svcMemoTpCd_HD, (EZDBStringItem) params[ARGS_SVC_MEMO_TP_CD]);
            // END 2015/10/29 T.Tomita [N/A, MOD]
            int aIdx = 0;
            // Set AMsg start
            if (hasValue((EZDBStringItem) params[ARGS_CLMN_HD_1])) {
                EZDBStringItem xxComnScrColLbTxt = (EZDBStringItem) params[ARGS_CLMN_HD_1];
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_HD, NSBL0160CommonLogic.getI18NLabel(xxComnScrColLbTxt.getValue().toString()));
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_LB, (EZDBStringItem) params[ARGS_CLMN_LB_1]);
                aIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_CLMN_HD_2])) {
                EZDBStringItem xxComnScrColLbTxt = (EZDBStringItem) params[ARGS_CLMN_HD_2];
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_HD, NSBL0160CommonLogic.getI18NLabel(xxComnScrColLbTxt.getValue().toString()));
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_LB, (EZDBStringItem) params[ARGS_CLMN_LB_2]);
                aIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_CLMN_HD_3])) {
                EZDBStringItem xxComnScrColLbTxt = (EZDBStringItem) params[ARGS_CLMN_HD_3];
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_HD, NSBL0160CommonLogic.getI18NLabel(xxComnScrColLbTxt.getValue().toString()));
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_LB, (EZDBStringItem) params[ARGS_CLMN_LB_3]);
                aIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_CLMN_HD_4])) {
                EZDBStringItem xxComnScrColLbTxt = (EZDBStringItem) params[ARGS_CLMN_HD_4];
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_HD, NSBL0160CommonLogic.getI18NLabel(xxComnScrColLbTxt.getValue().toString()));
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_LB, (EZDBStringItem) params[ARGS_CLMN_LB_4]);
                aIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_CLMN_HD_5])) {
                EZDBStringItem xxComnScrColLbTxt = (EZDBStringItem) params[ARGS_CLMN_HD_5];
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_HD, NSBL0160CommonLogic.getI18NLabel(xxComnScrColLbTxt.getValue().toString()));
                setValue(scrnMsg.A.no(aIdx).xxComnScrColLbTxt_LB, (EZDBStringItem) params[ARGS_CLMN_LB_5]);
                aIdx++;
            }
            scrnMsg.A.setValidCount(aIdx);
            // Set AMsg end

            // Set CMsg start
            int cIdx = 0;
            if (hasValue((EZDBStringItem) params[ARGS_COND_NM_1])) {
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_CL, (EZDBStringItem) params[ARGS_COND_NM_1]);
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_SC, (EZDBStringItem) params[ARGS_COND_CD_1]);
                cIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_COND_NM_2])) {
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_CL, (EZDBStringItem) params[ARGS_COND_NM_2]);
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_SC, (EZDBStringItem) params[ARGS_COND_CD_2]);
                cIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_COND_NM_3])) {
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_CL, (EZDBStringItem) params[ARGS_COND_NM_3]);
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_SC, (EZDBStringItem) params[ARGS_COND_CD_3]);
                cIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_COND_NM_4])) {
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_CL, (EZDBStringItem) params[ARGS_COND_NM_4]);
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_SC, (EZDBStringItem) params[ARGS_COND_CD_4]);
                cIdx++;
            }
            if (hasValue((EZDBStringItem) params[ARGS_COND_NM_5])) {
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_CL, (EZDBStringItem) params[ARGS_COND_NM_5]);
                setValue(scrnMsg.C.no(cIdx).xxComnScrColLbTxt_SC, (EZDBStringItem) params[ARGS_COND_CD_5]);
                cIdx++;
            }

            if (params.length >= 23) {
                if (params[ARGS_READ_ONLY_FLG] != null
                        && params[ARGS_READ_ONLY_FLG] instanceof String) {
                    setValue(scrnMsg.xxYesNoCd, (String) params[ARGS_READ_ONLY_FLG]);
                }
            }
            scrnMsg.C.setValidCount(cIdx);
            // Set CMsg end
            if (aIdx < 1 || cIdx < 1) {
                return null;
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg  = (NSBL0160CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        initialize();

        NSBL0160CommonLogic.setBgColor(scrnMsg);
        NSBL0160CommonLogic.setProtected(scrnMsg, this);
        NSBL0160CommonLogic.checkAuth(scrnMsg, this);

        // mod start 2016/02/19 CSA Defect#3689
        // START 2015/10/29 T.Tomita [N/A, MOD]
//        scrnMsg.setFocusItem(scrnMsg.svcMemoTpCd_SC);
        scrnMsg.setFocusItem(scrnMsg.xxFromDt_SC);
        // END 2015/10/29 T.Tomita [N/A, MOD]
        // mod end 2016/02/19 CSA Defect#3689
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        // mod start 2016/02/26 CSA Defect#3689
        // START 2015/10/29 T.Tomita [N/A, MOD]
        // Search Item
//        scrnMsg.svcMemoTpCd_SC.setNameForMessage("Memo Type");
        scrnMsg.svcMemoRsnCd_SC.setNameForMessage("Memo Reason");
        scrnMsg.xxFromDt_SC.setNameForMessage("From Date");
        scrnMsg.xxThruDt_SC.setNameForMessage("Thru Date");
        // Add Line Item
//        scrnMsg.svcMemoTpCd_AC.setNameForMessage("Memo Type");
//        scrnMsg.svcMemoRsnCd_AC.setNameForMessage("Memo Reason");
        // mod end 2016/02/26 CSA Defect#3689
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).svcCmntTxt_B.setNameForMessage("Comment");
        }
        // END 2015/10/29 T.Tomita [N/A, MOD]
    }

    private void initialize() {
        // Control
        setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 1, null);
        setButtonProperties(BUTTON_NAME_SUBMIT, BUTTON_GUARD_SUBMIT, BUTTON_LABEL_SUBMIT, 0, null);
        setButtonProperties(BUTTON_NAME_APPLY, BUTTON_GUARD_APPLY, BUTTON_LABEL_APPLY, 0, null);
        setButtonProperties(BUTTON_NAME_APPROVE, BUTTON_GUARD_APPROVE, BUTTON_LABEL_APPROVE, 0, null);
        setButtonProperties(BUTTON_NAME_REJECT, BUTTON_GUARD_REJECT, BUTTON_LABEL_REJECT, 0, null);
        // mod start 2016/02/19 CSA Defect#3689
        setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 1, null);
        // mod end 2016/02/19 CSA Defect#3689
        setButtonProperties(BUTTON_NAME_DELETE, BUTTON_GUARD_DELETE, BUTTON_LABEL_DELETE, 0, null);
        // mod start 2016/02/19 CSA Defect#3689
        // mod start 2016/12/27 CSA Defect#16331
        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
        // mod end 2016/12/27 CSA Defect#16331
        // mod end 2016/02/19 CSA Defect#3689
        // START 2015/10/29 T.Tomita [N/A, MOD]
        setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 1, null);
        // END 2015/10/29 T.Tomita [N/A, MOD]
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_RETURN, BUTTON_LABEL_RETURN, 1, null);
    }
}
