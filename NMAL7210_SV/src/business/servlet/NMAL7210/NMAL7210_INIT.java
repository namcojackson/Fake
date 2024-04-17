/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7210;

import static business.servlet.NMAL7210.constant.NMAL7210Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7210.NMAL7210CMsg;
import business.servlet.NMAL7210.common.NMAL7210CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7210_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;
        NMAL7210CMsg bizMsg = new NMAL7210CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;
        NMAL7210CMsg bizMsg = (NMAL7210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7210CommonLogic.initCmnBtnProp(this);
        NMAL7210CommonLogic.setBtnProp(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;

        scrnMsg.prcFmlaNm_H1.setNameForMessage("Formula Name(*)");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.prcFmlaDescTxt_H1.setNameForMessage("Formula Description(*)");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");
        scrnMsg.prcFmlaTpCd_H1.setNameForMessage("Formula Type");
        scrnMsg.actvFlg_H1.setNameForMessage("Active");
        scrnMsg.xxRadioBtn.setNameForMessage("Radio Btn");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcFmlaNm_A1.setNameForMessage("Formula Name");
            scrnMsg.A.no(i).prcFmlaDescTxt_A1.setNameForMessage("Formula Description");
            scrnMsg.A.no(i).prcFmlaTpNm_A1.setNameForMessage("Formula Type");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Date To");
        }
    }
}
