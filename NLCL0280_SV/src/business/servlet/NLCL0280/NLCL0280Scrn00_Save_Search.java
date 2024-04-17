/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0280.NLCL0280CMsg;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;
import business.servlet.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 *</pre>
 */
public class NLCL0280Scrn00_Save_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_PS) && !ZYPCommonFunc.hasValue(scrnMsg.srchOptNm_H1)) {

            scrnMsg.srchOptNm_H1.setErrorInfo(1, NLCL0280Constant.ZZM9000E, new String[] {"Search Option Name" });
            scrnMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(NLCL0280Constant.SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);
        NLCL0280CommonLogic.addCheckAllHeaderItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = new NLCL0280CMsg();

        StringBuffer locStsSrchOptTxt = new StringBuffer();
        boolean locStsfirstChkBox = true;

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.L.no(i).xxChkBox_LS.getValue())) {

                if (!locStsfirstChkBox) {

                    locStsSrchOptTxt.append(",");

                } else {

                    locStsfirstChkBox = false;
                }

                locStsSrchOptTxt.append(scrnMsg.L.no(i).locStsCd_LS.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_LS, locStsSrchOptTxt.toString());

        bizMsg.setBusinessID(NLCL0280Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = (NLCL0280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

        if (scrnMsg.srchOptNm_H1.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.srchOptNm_H1);
        }
    }
}
