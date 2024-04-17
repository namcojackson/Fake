/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2590.NMAL2590CMsg;
import business.servlet.NMAL2590.common.NMAL2590CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NMAL2590_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/11   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 * 2016/10/25   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 *</pre>
 */
public class NMAL2590_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 4) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            EZDBStringItem param02 = (EZDBStringItem) params[1];
            EZDBStringItem param03 = (EZDBStringItem) params[2];
            EZDBStringItem param04 = (EZDBStringItem) params[3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr, param01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd, param02);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd, param03);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm, param04);

        } else {
            // 2016/10/25 Delete CSA-QC#4096
            // scrnMsg.setMessageInfo(NMZM0338E);
            return null;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr) && !ZYPCommonFunc.hasValue(scrnMsg.stCd) &&
                !ZYPCommonFunc.hasValue(scrnMsg.postCd) && !ZYPCommonFunc.hasValue(scrnMsg.cntyNm)) {
            // 2016/10/25 Delete CSA-QC#4096
            // scrnMsg.setMessageInfo(NMZM0338E);
            return null;
        }

        NMAL2590CMsg bizMsg = new NMAL2590CMsg();
        bizMsg.setBusinessID("NMAL2590");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;
        NMAL2590CMsg bizMsg  = (NMAL2590CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NMAL2590CommonLogic.initCmnBtnProp(this);
        NMAL2590CommonLogic.setInputProtect(scrnMsg);
        NMAL2590CommonLogic.setRowsBGWithClear(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.ctyAddr);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        // Header
        scrnMsg.ctyAddr.setNameForMessage("City Address");
        scrnMsg.stCd.setNameForMessage("State Code");
        scrnMsg.postCd.setNameForMessage("Post Code");
        scrnMsg.cntyNm.setNameForMessage("County Name");
    }
}
