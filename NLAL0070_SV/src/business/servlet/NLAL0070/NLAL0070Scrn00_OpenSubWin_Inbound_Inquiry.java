/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_OpenSubWin_Inbound_Inquiry extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        int cnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                cnt++;
            }
        }
        if (cnt > 1) {
            scrnMsg.setMessageInfo("NLAM0017E");
            throw new EZDFieldErrorException();
        } else {
        	// do nothing
        }
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
		
        Object[] params = new Object[10];
        scrnMsg.xxPopPrm_K0.clear();
        scrnMsg.xxPopPrm_K1.clear();
        scrnMsg.xxPopPrm_K2.clear();
        scrnMsg.xxPopPrm_K3.clear();
        scrnMsg.xxPopPrm_K4.clear();
        scrnMsg.xxPopPrm_K5.clear();
        scrnMsg.xxPopPrm_K6.clear();
        scrnMsg.xxPopPrm_K7.clear();
        scrnMsg.xxPopPrm_K8.clear();
        scrnMsg.xxPopPrm_K9.clear();
        scrnMsg.xxPopPrm_KA.clear();

        int selectedRowNum = 0;
        boolean selectRecord = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                selectedRowNum = i;
                selectRecord = true;
                break;
            }
        }
        if (selectRecord) {
            String sceOrdTp = scrnMsg.A.no(selectedRowNum).sceOrdTpCd_A1.getValue();
            if (NLXSceConst.SCE_ORD_TP_CD_IV.equals(sceOrdTp) || NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTp)) {

                scrnMsg.xxPopPrm_K0.setValue(INV_POP_SCR_NUM_00);
                scrnMsg.xxPopPrm_K1.setValue(scrnMsg.A.no(selectedRowNum).rwsRefNum_A1.getValue());

                params[0] = scrnMsg.xxPopPrm_K0;
                params[1] = scrnMsg.xxPopPrm_K1;
                params[2] = scrnMsg.xxPopPrm_K2;
                params[3] = scrnMsg.xxPopPrm_K3;
                params[4] = scrnMsg.xxPopPrm_K4;
                params[5] = scrnMsg.xxPopPrm_K5;
                params[6] = scrnMsg.xxPopPrm_K6;
                params[7] = scrnMsg.xxPopPrm_K7;
                params[8] = scrnMsg.xxPopPrm_K8;
                params[9] = scrnMsg.xxPopPrm_K9;

            } else if (NLXSceConst.SCE_ORD_TP_CD_CT.equals(sceOrdTp) || NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTp)) {

                scrnMsg.xxPopPrm_K0.setValue(INV_POP_SCR_NUM_02);
                scrnMsg.xxPopPrm_K5.setValue(scrnMsg.A.no(selectedRowNum).rwsRefNum_A1.getValue());

                params[0] = scrnMsg.xxPopPrm_K0;
                params[1] = scrnMsg.xxPopPrm_K1;
                params[2] = scrnMsg.xxPopPrm_K2;
                params[3] = scrnMsg.xxPopPrm_K3;
                params[4] = scrnMsg.xxPopPrm_K4;
                params[5] = scrnMsg.xxPopPrm_K5;
                params[6] = scrnMsg.A.no(selectedRowNum).imptTrmEtaDt_A1;
                params[7] = scrnMsg.xxPopPrm_K7;
                params[8] = scrnMsg.xxPopPrm_K8;
                params[9] = scrnMsg.xxPopPrm_K9;

            } else if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTp)) {

                scrnMsg.xxPopPrm_K0.setValue(INV_POP_SCR_NUM_06);
                scrnMsg.xxPopPrm_K8.setValue(scrnMsg.A.no(selectedRowNum).asnNum_A1.getValue());
                scrnMsg.xxPopPrm_K9.setValue(scrnMsg.A.no(selectedRowNum).carrCd_A1.getValue());

                params[0] = scrnMsg.xxPopPrm_K0;
                params[1] = scrnMsg.xxPopPrm_K1;
                params[2] = scrnMsg.xxPopPrm_K2;
                params[3] = scrnMsg.xxPopPrm_K3;
                params[4] = scrnMsg.xxPopPrm_K4;
                params[5] = scrnMsg.xxPopPrm_K5;
                params[6] = scrnMsg.xxPopPrm_K6;
                params[7] = scrnMsg.xxPopPrm_K7;
                params[8] = scrnMsg.xxPopPrm_K8;
                params[9] = scrnMsg.xxPopPrm_K9;
            } else {
                
                params[0] = scrnMsg.xxPopPrm_K0;
                params[1] = scrnMsg.xxPopPrm_K1;
                params[2] = scrnMsg.xxPopPrm_K2;
                params[3] = scrnMsg.xxPopPrm_K3;
                params[4] = scrnMsg.xxPopPrm_K4;
                params[5] = scrnMsg.xxPopPrm_K5;
                params[6] = scrnMsg.xxPopPrm_K6;
                params[7] = scrnMsg.xxPopPrm_K7;
                params[8] = scrnMsg.xxPopPrm_K8;
                params[9] = scrnMsg.xxPopPrm_K9;
            }
        } else {
            params[0] = scrnMsg.xxPopPrm_K0;
            params[1] = scrnMsg.xxPopPrm_K1;
            params[2] = scrnMsg.xxPopPrm_K2;
            params[3] = scrnMsg.xxPopPrm_K3;
            params[4] = scrnMsg.xxPopPrm_K4;
            params[5] = scrnMsg.xxPopPrm_K5;
            params[6] = scrnMsg.xxPopPrm_K6;
            params[7] = scrnMsg.xxPopPrm_K7;
            params[8] = scrnMsg.xxPopPrm_K8;
            params[9] = scrnMsg.xxPopPrm_K9;        	
        }
        

        setArgForSubScreen(params);
	}

}
