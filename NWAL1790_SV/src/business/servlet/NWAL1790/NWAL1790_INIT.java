/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1790;

import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_NAME;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.DETAIL_TABLE_ID;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.MAX_DETAIL;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.PROCESS_MODE;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.SCRN_ID_00;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.ZZZM9025E;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1790.common.NWAL1790CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1790_INIT
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   CUSA            T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1790_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1790BMsg scrnMsg = (NWAL1790BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0) {
                if (params[0] == null || !(params[0] instanceof EZDBStringItem)) {
                    scrnMsg.setMessageInfo(ZZZM9025E, new String[] {PROCESS_MODE });
                    this.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 0, null);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxProcMd, (EZDBStringItem) params[0]);

                if (params.length > 2) {
                    EZDMsg.copy((EZDMsgArray) params[1], (String) params[2], scrnMsg.A, DETAIL_TABLE_ID);

                    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                        if (!scrnMsg.A.no(i).ctacPsnEmlAddr_A.isClear()) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
                        }
                    }
                }
                scrnMsg.A.setValidCount(MAX_DETAIL);
            }
        } else {
            scrnMsg.setMessageInfo(ZZZM9025E, new String[] {PROCESS_MODE });
            this.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 0, null);
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_ML, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_PR, ZYPConstant.CHKBOX_ON_Y);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacPsnEmlAddr_A);
        NWAL1790CommonLogic.initButton(this);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG(DETAIL_TABLE_ID, scrnMsg.A);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1790BMsg scrnMsg = (NWAL1790BMsg) bMsg;

        for (int i = 0; scrnMsg.A.length() > i; i++) {
            scrnMsg.A.no(i).ctacPsnEmlAddr_A.setNameForMessage("EMail Address");
        }
    }
}
