/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530.common;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_APL;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_APR;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_RST;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.BTN_CMN_SUB;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/28   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/24   Fujitsu         M.suzuki        Update          S21_NA#4642
 *</pre>
 */
public class NWAL1530CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        // 2016/02/24 S21_NA#4642 Mod Start --------------
        //handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
        // 2016/02/24 S21_NA#4642 Mod Start --------------
    }
}
