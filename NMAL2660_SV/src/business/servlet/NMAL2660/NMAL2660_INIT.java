/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2660;

import static business.servlet.NMAL2660.constant.NMAL2660Constant.BIZ_ID;
import static business.servlet.NMAL2660.constant.NMAL2660Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2660.NMAL2660CMsg;
import business.servlet.NMAL2660.common.NMAL2660CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2660_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2660_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //doNothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2660BMsg scrnMsg = (NMAL2660BMsg) bMsg;
        NMAL2660CMsg bizMsg = new NMAL2660CMsg();
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params[0] != null && params[0] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd, param00);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2660BMsg scrnMsg = (NMAL2660BMsg) bMsg;
        NMAL2660CMsg bizMsg = (NMAL2660CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            StringBuilder name = new StringBuilder();
            name.append(bizMsg.A.no(i).psnLastNm.getValue());
            name.append(",");
            name.append(bizMsg.A.no(i).psnFirstNm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxPsnFirstMidLastNm, name.toString());
        }

        NMAL2660CommonLogic.initCmnBtnProp(this);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        //doNothing
    }
}
