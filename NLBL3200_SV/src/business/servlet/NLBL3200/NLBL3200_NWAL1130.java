/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL2020 Manage Shipping Orders
 * Function Name : Return Action from NWAL1130(Common PopUp)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            D.Fukaya        Create          QC# 2200
 *</pre>
 */
public class NLBL3200_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        if (NLBL3200Constant.EVENT_NM_NLBL3200SCRN00_OPENWIN_APLYCARRCODE.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLBL3200Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_D1, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_D1, scrnMsg.P.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.carrNm_D1);

        } else if (NLBL3200Constant.EVENT_NM_NLBL3200SCRN00_OPENWIN_CARRINFO.equals(scrnMsg.xxMntEventNm.getValue())) {

            int index = getButtonSelectNumber();

            if (!NLBL3200Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrCd_A1, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).actlShpgSvcLvlCd_A1, scrnMsg.P.no(2).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(index).carrNm_A1);
        }

    }
}
