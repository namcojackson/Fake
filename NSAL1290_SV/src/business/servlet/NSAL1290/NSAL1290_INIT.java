/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1290;

import static business.servlet.NSAL1290.constant.NSAL1290Constant.BUSINESS_ID;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.FUNCTION_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1290.NSAL1290CMsg;
import business.servlet.NSAL1290.common.NSAL1290CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#11813
 *</pre>
 */
public class NSAL1290_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;

        // add start 2016/07/13 QC#11813
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBStringItem) {
                    setValue(scrnMsg.mtrLbNm_P, (EZDBStringItem) params[0]);
                }
            }
        }
        // add end 2016/07/13 QC#11813

        NSAL1290CMsg bizMsg = new NSAL1290CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        NSAL1290CMsg bizMsg  = (NSAL1290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1290CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        scrnMsg.mtrLbNm_H.setNameForMessage("Regular Counter");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mtrLbNm_BC.setNameForMessage("Billing Counter");
            scrnMsg.A.no(i).bllgMtrMapLvlNum_A.setNameForMessage("Level");
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Active");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
        }
    }
}
