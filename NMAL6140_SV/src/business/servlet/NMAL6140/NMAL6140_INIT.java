/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6140;

import static business.servlet.NMAL6140.constant.NMAL6140Constant.BIZ_ID;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_10_CLO_GUARD;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_10_CLO_LABEL;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.BTN_10_CLO_NAME;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.FUNC_CD;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6140.NMAL6140CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL6140_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL6140_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;
        NMAL6140CMsg bizMsg = new NMAL6140CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H3, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr, (EZDBStringItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr, (EZDBStringItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd, (EZDBStringItem) params[8]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd, (EZDBStringItem) params[9]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.provNm, (EZDBStringItem) params[10]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm, (EZDBStringItem) params[11]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd, (EZDBStringItem) params[12]);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;
        NMAL6140CMsg bizMsg = (NMAL6140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        this.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        this.setButtonProperties(BTN_10_CLO_NAME, BTN_10_CLO_GUARD, BTN_10_CLO_LABEL, 1, null);

        scrnMsg.ctryNm.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.firstLineAddr);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;

        scrnMsg.firstLineAddr.setNameForMessage("Address1");
        scrnMsg.scdLineAddr.setNameForMessage("Address2");
        scrnMsg.thirdLineAddr.setNameForMessage("Address3");
        scrnMsg.ctyAddr.setNameForMessage("City");
        scrnMsg.postCd.setNameForMessage("Postal Code");
    }
}
