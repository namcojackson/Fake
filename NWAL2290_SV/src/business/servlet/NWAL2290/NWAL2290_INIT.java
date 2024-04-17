/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2290;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2290.NWAL2290CMsg;
import business.servlet.NWAL2290.common.NWAL2290CommonLogic;
import business.servlet.NWAL2290.constant.NWAL2290Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/05   Fujitsu         A.Kosai         Create          N/A
 * 2019/03/27   Fujitsu         K.Ishizuka      Update          S21_NA#30765
 *</pre>
 */
public class NWAL2290_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2290BMsg scrnMsg = (NWAL2290BMsg) bMsg;

        setParams(scrnMsg);

        NWAL2290CMsg bizMsg = new NWAL2290CMsg();
        bizMsg.setBusinessID(NWAL2290Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2290BMsg scrnMsg = (NWAL2290BMsg) bMsg;
        NWAL2290CMsg bizMsg  = (NWAL2290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        NWAL2290CommonLogic.initCmnBtnProp(this);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2290BMsg scrnMsg = (NWAL2290BMsg) bMsg;

        scrnMsg.ordSrcRefNum.setNameForMessage("Source Reference Num");
    }

    private void setParams(NWAL2290BMsg scrnMsg) {

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            if (params[0] instanceof EZDBStringItem) {
                EZDBStringItem param0 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, param0);
            }
            
            // 2019/03/27 S21_NA#30765 Add Start
            if (params[1] instanceof EZDBStringItem) {
                EZDBStringItem param1 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoSrcTpCd, param1);
            }
            // 2019/03/27 S21_NA#30765 Add End
        }
    }
}
