/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2120;

import static business.servlet.NFBL2120.constant.NFBL2120Constant.BIZ_ID;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.EVENT_NM_INIT;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2120.NFBL2120CMsg;
import business.servlet.NFBL2120.common.NFBL2120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;
        NFBL2120CMsg bizMsg = new NFBL2120CMsg();

        Serializable arg = getArgForSubScreen();
        Object[] params = (Object[]) arg;

        if (params.length >= 1) {
            EZDBStringItem param1 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrDply, param1);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;
        NFBL2120CMsg bizMsg  = (NFBL2120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2120CommonLogic.initCmnBtnProp(this);
        NFBL2120CommonLogic.controlScreenFields(this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_INIT);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;

        scrnMsg.docMgtOrgCd_H.setNameForMessage("Organization");
        scrnMsg.docMgtPrtyCd_H.setNameForMessage("Priority");
        scrnMsg.docMgtCatgCd_H.setNameForMessage("Category");
        scrnMsg.xxRadioBtn.setNameForMessage("Radio Button");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrDply_A1.setNameForMessage("Description");
            scrnMsg.A.no(i).docMgtCatgDescTxt_A1.setNameForMessage("Category");
            scrnMsg.A.no(i).docMgtOrgDescTxt_A1.setNameForMessage("Organization");
            scrnMsg.A.no(i).xxUsrSysDtTxt_A1.setNameForMessage("Received at");
            scrnMsg.A.no(i).docMgtPrtyDescTxt_A1.setNameForMessage("Priority");
        }
    }
}
