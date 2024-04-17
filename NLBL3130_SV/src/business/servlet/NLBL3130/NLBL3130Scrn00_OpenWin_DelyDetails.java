/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3130;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 08/24/2018   CITS            K.Ogino         Update          QC#27833
 *</pre>
 */
public class NLBL3130Scrn00_OpenWin_DelyDetails extends S21CommonHandler implements NLBL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        String resultId = "";
        Object[] param = new Object[3];

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_P1)) {

            resultId = ZYPConstant.FLG_ON_Y;

            param[0] = scrnMsg.cpoOrdNum_P1;
            param[2] = scrnMsg.configCatgCd_P1;

            List<String> dsOrdPosnNumList = new ArrayList<String>();
            // QC#27833
            if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_P1)) {

                dsOrdPosnNumList.add(scrnMsg.dsOrdPosnNum_P1.getValue());
            }

            param[1] = dsOrdPosnNumList;

        } else {

            resultId = ZYPConstant.FLG_OFF_N;
        }

        setArgForSubScreen(param);
        setResult(resultId);
        openMultiModeScreen();
    }
}
