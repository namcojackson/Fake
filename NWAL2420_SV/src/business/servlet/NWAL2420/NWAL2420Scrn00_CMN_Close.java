/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2420;

import static business.servlet.NWAL2420.constant.NWAL2420Constant.DETAIL_TABLE_ID;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_AUTHORIZATION_COMMENT;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_EMAIL_ADDRESS;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_MAIL_SEND_FLG;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_PRINT_FLG;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_RETURN_SUPPLIES;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_SUFFIX;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2420.common.NWAL2420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   CUSA            H.Tomimatsu     Create          S21_NA#22139
 *</pre>
 */
public class NWAL2420Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2420BMsg scrnMsg = (NWAL2420BMsg) bMsg;

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_ML.getValue())) {
            NWAL2420CommonLogic.checkEMail(scrnMsg);
            for (int i = 0; scrnMsg.A.getValidCount() > i; i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).ctacPsnEmlAddr_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            }
        }

        NWAL2420CommonLogic.checkComment(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.rtrnAuthCmntTxt_01);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2420BMsg scrnMsg = (NWAL2420BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDMsgArray param1 = (EZDMsgArray) params[IDX_EMAIL_ADDRESS];
            param1.clear();

            int setCount = 0;
            for (int i = 0; scrnMsg.A.length() > i; i++) {
                if (!scrnMsg.A.no(i).xxChkBox_A.isClear()) {
                    EZDMsg.copy(scrnMsg.A.no(i), DETAIL_TABLE_ID, param1.get(setCount), (String) params[IDX_SUFFIX]);
                    setCount++;
                }
            }
            param1.setValidCount(setCount);

            if (scrnMsg.xxRqstFlg_ML.isClear()) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_MAIL_SEND_FLG], ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_MAIL_SEND_FLG], ZYPConstant.FLG_ON_Y);
            }

            if (scrnMsg.xxRqstFlg_PR.isClear()) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_PRINT_FLG], ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_PRINT_FLG], ZYPConstant.FLG_ON_Y);
            }

            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_RETURN_SUPPLIES], scrnMsg.rmaRptTpCd.getValue());
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[IDX_AUTHORIZATION_COMMENT], scrnMsg.rtrnAuthCmntTxt_01.getValue());
        }
    }
}
