/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;


import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFAL0270.NFAL0270CMsg;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
            NFAL0270CMsg bizMsg = (NFAL0270CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
            //BigDecimal mdlGrpId = new BigDecimal(scrnMsg.xxCondCd_P1.getValue());
            if (BTN_OPEN_WIN_MDL_GRP_ID.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlGrpId_H, new BigDecimal(scrnMsg.xxCondCd_P1.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlGrpNm_H, scrnMsg.xxCondNm_P1.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlGrpId_H);
            } else if (BTN_OPEN_WIN_MDL_GRP_ID_A.equals(scrnMsg.xxScrEventNm.getValue())) {
                int selectIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).mdlGrpId_A, new BigDecimal(scrnMsg.xxCondCd_P1.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).mdlGrpNm_A, scrnMsg.xxCondNm_P1.getValue());
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).mdlGrpId_A);
            }
        }
    }
}
