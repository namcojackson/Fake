/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6140;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6140Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL6140Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.firstLineAddr);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddr);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr);
        scrnMsg.addCheckItem(scrnMsg.postCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null) {
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.xxChkBox_H1);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.xxChkBox_H2);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.xxChkBox_H3);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[7], scrnMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[8], scrnMsg.stCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.postCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], scrnMsg.provNm);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], scrnMsg.cntyNm);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], scrnMsg.ctryCd);
        }
    }
}
