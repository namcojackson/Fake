/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1790;

import static business.servlet.NWAL1790.constant.NWAL1790Constant.DETAIL_TABLE_ID;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.INDEX_3;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.INDEX_4;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.TRACKING_FLAG;

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
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1790Scrn00_CMN_Close
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   CUSA            T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1790Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1790BMsg scrnMsg = (NWAL1790BMsg) bMsg;

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_ML.getValue())) {
            NWAL1790CommonLogic.checkEMail(scrnMsg);
            for (int i = 0; scrnMsg.A.getValidCount() > i; i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).ctacPsnEmlAddr_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            }
            scrnMsg.putErrorScreen();
        }
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
            EZDMsgArray param1 = (EZDMsgArray) params[1];
            param1.clear();

            int setCount = 0;
            for (int i = 0; scrnMsg.A.length() > i; i++) {
                if (!scrnMsg.A.no(i).xxChkBox_A.isClear()) {
                    EZDMsg.copy(scrnMsg.A.no(i), DETAIL_TABLE_ID, param1.get(setCount), (String) params[2]);
                    setCount++;
                }
            }
            param1.setValidCount(setCount);

            if (scrnMsg.xxRqstFlg_ML.isClear()) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[INDEX_3], ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[INDEX_3], ZYPConstant.FLG_ON_Y);
            }

            if (scrnMsg.xxRqstFlg_PR.isClear() || scrnMsg.xxProcMd.getValue().equals(TRACKING_FLAG)) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[INDEX_4], ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[INDEX_4], ZYPConstant.FLG_ON_Y);
            }
        }
    }
}
