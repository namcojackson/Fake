/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710.common;

import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_APL;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_APR;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_RST;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.NWBM0146E;
import business.servlet.NWAL1710.NWAL1710BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1710CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   Fujitsu         M.Suzuki        Create          QC#6336
 *</pre>
 */
public class NWAL1710CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null); //2016/04/04 S21_NA#6336 MOD
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NWAL1710BMsg
     */
    public static void addCheckItemHeader(NWAL1710BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgNm);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpNm);
        scrnMsg.addCheckItem(scrnMsg.ordProcTpCd);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
    }

    /**
     * checkDateFromTo
     * @param scrnMsg NWAL1710BMsg
     */
    public static void checkDateFromTo(NWAL1710BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue()
                , scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NWBM0146E);
                scrnMsg.effThruDt.setErrorInfo(1, NWBM0146E);
            }
        }
    }
}
