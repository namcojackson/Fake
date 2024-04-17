/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7200;

import static business.servlet.NMAL7200.constant.NMAL7200Constant.BIZ_ID;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7200.NMAL7200CMsg;
import business.servlet.NMAL7200.common.NMAL7200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7200_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/05   Fujitsu         R.Nakamura      Update          QC#8222
 *</pre>
 */
public class NMAL7200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        NMAL7200CMsg bizMsg = new NMAL7200CMsg();
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
                if (ZYPCommonFunc.hasValue(param00)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem29Txt, String.valueOf(param00.getValue()));
                }
            }
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        NMAL7200CMsg bizMsg = (NMAL7200CMsg) cMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem10Txt.setInputProtected(true);
            scrnMsg.A.no(i).prcCatgNm.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem30Txt.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem7Txt.setInputProtected(true);
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7200CommonLogic.initCmnBtnProp(this);
        // Add Start 2016/09/05 QC#8222
        NMAL7200CommonLogic.initDownloadBtnProp(this, scrnMsg);
        // Add End 2016/09/05 QC#8222
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        scrnMsg.setFocusItem(scrnMsg.xxScrItem29Txt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;

        scrnMsg.xxScrItem29Txt.setNameForMessage("Pricing Group Id");
        scrnMsg.prcGrpNm.setNameForMessage("Pricing Group Name");
        scrnMsg.prcGrpDescTxt.setNameForMessage("Pricing Group Description");

    }


}
