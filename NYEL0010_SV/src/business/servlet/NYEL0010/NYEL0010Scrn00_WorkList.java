/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0010;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;

public class NYEL0010Scrn00_WorkList extends S21CommonHandlerEx {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;

        scrnMsg.xxWfProcCd.setValue("");
        
        String selectedGroupCode = scrnMsg.menuProcGrpCd.getValue();

        if (selectedGroupCode.length() > 0) {

            if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_A0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_A0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_B0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_B0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_C0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_C0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_D0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_D0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_H1.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_H1.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_H0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_H0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_H2.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_H2.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_J0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_J0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_K0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_K0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_L0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_L0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_M0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_M0.getValue());
            } else if ( selectedGroupCode.equals(scrnMsg.menuProcGrpCd_N0.getValue())) {
                scrnMsg.xxWfProcCd.setValue(scrnMsg.xxWfProcCd_N0.getValue());
            }
        }
        
        ctx.setAttribute("SelectedProcessGroupId", selectedGroupCode );
        
        // setup S21PrcessGroupId into context that will be consumed by worklist page
        saveS21ProcessGroupId(ctx, selectedGroupCode);
        
        Object[] param = new Object[1];
        param[0] = scrnMsg.xxWfProcCd;

        setArgForSubScreen(param);        
	}

}
