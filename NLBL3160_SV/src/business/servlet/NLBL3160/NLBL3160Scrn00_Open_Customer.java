/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import java.util.ArrayList;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3160.common.NLBL3160CommonLogic;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160Scrn00_Open_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CommonLogic.clearPopupParameter(scrnMsg);

        int index = getButtonSelectNumber();

        Object[] params = new Object[24];
        params[0] = ZYPConstant.FLG_OFF_N;
        params[1] = NLBL3160Constant.RSLT_MODE.ORDER_INQUIRY.getRsltModeCd();
        params[2] = NLBL3160Constant.DPLY_MODE.LINE.getDplyModeCd();
        params[3] = "SRC_REF_OR_CPO_ORD_NUM";
        params[4] = new ArrayList<String>();
        params[5] = new ArrayList<String>();
        params[6] = new ArrayList<String>();
        params[7] = "";
        params[8] = "";
        params[9] = "";
        params[10] = "";
        params[11] = "";
        params[12] = "";
        params[13] = "";
        params[14] = "";
        params[15] = "";
        params[16] = "";
        params[17] = "";
        params[18] = "";
        params[19] = "";
        params[20] = scrnMsg.A.no(index).dsAcctNm_A1.getValue();
        params[21] = scrnMsg.A.no(index).sellToCustCd_A1.getValue();
        params[22] = scrnMsg.A.no(index).shipToCustCd_A1.getValue();
        params[23] = ZYPConstant.FLG_ON_Y;

        setArgForSubScreen(params);
        openMultiModeScreen();

    }
}
