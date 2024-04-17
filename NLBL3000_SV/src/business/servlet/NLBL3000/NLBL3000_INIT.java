/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3000;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3000.NLBL3000CMsg;
import business.servlet.NLBL3000.common.NLBL3000CommonLogic;
import business.servlet.NLBL3000.constant.NLBL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 *  Serial Number Entry NLBL3000_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLBL3000_INIT extends S21INITCommonHandler implements NLBL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        scrnMsg.clear();

        // 07/26/2013 R-OM031 Del Start
        // ZYPTableUtil.clear(scrnMsg.A);
        // 07/26/2013 R-OM031 Del End
        ZYPTableUtil.clear(scrnMsg.S);

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
        // if (arg instanceof Object[]) {
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        // Set global parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_H1, getGlobalCompanyCode());
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        NLBL3000CommonLogic.setInputParam(scrnMsg, (Object[]) arg);

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
        // }
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */

        NLBL3000CommonLogic.prepareScreenItem(scrnMsg);

        // prepare business logic call
        NLBL3000CMsg bizMsg = NLBL3000CommonLogic.setRequestData_NLBL3000Scrn00_Search();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;
        NLBL3000CMsg bizMsg = (NLBL3000CMsg) cMsg;

        // copy data from CMsg onto BMsg
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3000CommonLogic.setTableBGColor(scrnMsg);
        NLBL3000CommonLogic.initDisplayInfo(this, scrnMsg);

        // 07/26/2013 R-OM031 Del Start
        // NLBL3000CommonLogic.setInitialSerialSelection(scrnMsg);
        // NLBL3000CommonLogic.setInitialMasterSelection(scrnMsg);
        // 07/26/2013 R-OM031 Del End

        // set focus
        // 07/26/2013 R-OM031 Mod Start
        // scrnMsg.setFocusItem(scrnMsg.xxRadioBtn);

        if (scrnMsg.S.getValidCount() > 0) {

            scrnMsg.setFocusItem(scrnMsg.S.no(0).serNum);
        }
        // 07/26/2013 R-OM031 Mod End
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        scrnMsg.xxHdrNum_H1.setNameForMessage("Order Number");
        scrnMsg.rtlWhCd_H1.setNameForMessage("Warehouse");
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        for (int i = 0; i < scrnMsg.S.length(); i++) {

            scrnMsg.S.no(i).rtlSwhCd.setNameForMessage("Sub Warehouse");
            scrnMsg.S.no(i).serNum.setNameForMessage("Serial Number");
        }
    }
}
