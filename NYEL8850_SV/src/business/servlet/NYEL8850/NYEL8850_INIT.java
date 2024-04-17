/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8850;

import static business.servlet.NYEL8850.constant.NYEL8850Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0005E;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8850.NYEL8850CMsg;
import business.servlet.NYEL8850.common.NYEL8850CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NYEL8850_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8850_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // TODO [Template] security violation check. if popup then
        // delete blow
        // checkBusinessAppGranted(getContextUserInfo().getUserId(),
        // BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;

        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length >= 3) {
                if (params[0] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P1, ((EZDBStringItem) params[0]).getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P1, (String) params[0]);
                }

                if (params[1] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P2, ((EZDBStringItem) params[1]).getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P2, (String) params[1]);
                }

                if (params[2] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P3, ((EZDBStringItem) params[2]).getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P3, (String) params[2]);
                }

            } else {
                scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfProcTagNm.getNameForMessage() });
            }
        } else {
            scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfProcTagNm.getNameForMessage() });
        }

        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }

        ZYPTableUtil.clear(scrnMsg.H);
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);

        NYEL8850CMsg bizMsg = new NYEL8850CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;
        NYEL8850CMsg bizMsg = (NYEL8850CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8850CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8850CommonLogic.initCmnBtnProp(this);

        NYEL8850CommonLogic.setRowsBGWithClearForHead(scrnMsg, scrnMsg.H, "H");
        NYEL8850CommonLogic.setRowsBGWithClearForSts(scrnMsg, scrnMsg.A, "A");
        NYEL8850CommonLogic.setRowsBGWithClearForHist(scrnMsg, scrnMsg.B, "B");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

        //scrnMsg.setFocusItem(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;

        //scrnMsg.wfProcDocId.setNameForMessage("Document ID");
    }
}
