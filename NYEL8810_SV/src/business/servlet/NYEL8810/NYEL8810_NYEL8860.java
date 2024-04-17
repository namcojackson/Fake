/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8810_NYEL8860 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_FromUsrGrp".equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.wfUsrGrpNm_F);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxGrpFlg_F,  scrnMsg.R.no(0).xxPopPrm_P1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.wfUsrGrpNm_F,  scrnMsg.R.no(0).xxPopPrm_P2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNm_F,  scrnMsg.R.no(0).xxPopPrm_P3.getValue());
                
            } else if ("OpenWin_ToUsrGrp".equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.wfUsrGrpNm_T);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxGrpFlg_T,  scrnMsg.R.no(0).xxPopPrm_P1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.wfUsrGrpNm_T,  scrnMsg.R.no(0).xxPopPrm_P2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNm_T,  scrnMsg.R.no(0).xxPopPrm_P3.getValue());
            }
        }

        scrnMsg.xxScrEventNm.clear();
    }
}
