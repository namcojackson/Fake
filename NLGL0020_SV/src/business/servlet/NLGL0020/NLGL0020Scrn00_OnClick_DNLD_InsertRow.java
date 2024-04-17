/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 05/25/2017     CITS            S.Endo            Update             RS#3173
 * 06/30/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020Scrn00_OnClick_DNLD_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // there is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.F.getValidCount() > 0){
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.F.no(scrnMsg.F.getValidCount()-1).xxChkBox_F9.getValue())){
                scrnMsg.F.no(scrnMsg.F.getValidCount()-1).wmsUomCd_F2.setInputProtected(true);
            }
        }
        scrnMsg.putErrorScreen();
    }
}
