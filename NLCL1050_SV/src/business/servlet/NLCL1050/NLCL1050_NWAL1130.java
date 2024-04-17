/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BIZ_ID;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_OPENWIN_ABCCLASS;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            scrnMsg.abcAnlsClsNum_SV.clear();
            ZYPTableUtil.clear(scrnMsg.A);
            scrnMsg.clearAllGUIAttribute(SCREEN_ID);

            List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
            NLCL1050CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

            if (BTN_OPENWIN_ABCCLASS.equals(scrnMsg.xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.abcAnlsClsNm, scrnMsg.P.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
            }
        }

    }
}
