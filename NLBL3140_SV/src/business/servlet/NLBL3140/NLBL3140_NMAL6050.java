/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3140.NLBL3140CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/7/10    Hitachi         G.Quan          Create          QC#61543
 *</pre>
 */
public class NLBL3140_NMAL6050 extends S21CommonHandler {

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
            NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
            NLBL3140CMsg bizMsg = (NLBL3140CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
            if ("OpenWin_COA_Product_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd, scrnMsg.Y.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm, scrnMsg.Y.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.coaProdCd);
            } else if ("OpenWin_COA_Product".equals(scrnMsg.xxScrEventNm.getValue())) {
                //scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).coaProdCd_A);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdCd_A, scrnMsg.Y.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdNm_A, scrnMsg.Y.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).lineBizTpCd_A);
            }
        }
    }
}
