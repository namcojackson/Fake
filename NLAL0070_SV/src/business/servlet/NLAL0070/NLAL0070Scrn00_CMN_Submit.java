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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.servlet.NLAL0070.common.NLAL0070CommonLogic;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_CMN_Submit extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {

                scrnMsg.addCheckItem(scrnMsg.A.no(i).schdWhInEtaDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).finalWhInEtaDt_A1);

                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).finalEtaChkFlg_A1.getValue())) {

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).schdEtaChkFlg_A1.getValue())) {

                        scrnMsg.A.no(i).finalEtaChkFlg_A1.setErrorInfo(1, "NLAM0143E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).finalEtaChkFlg_A1);

                    } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).finalWhInEtaDt_A1)) {

                        scrnMsg.A.no(i).finalWhInEtaDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Final Schedule WETA" });

                    }

                }
                
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).whInPrtyFlg_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).tempWhInEtaDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).railAvalDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).apptTmTxt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).apptDrygCarrCd_A2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).whSchdCmntTxt_A1);
            }
        }
        
        scrnMsg.putErrorScreen();

	}

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        NLAL0070CMsg bizMsg = new NLAL0070CMsg();
        bizMsg.setBusinessID("NLAL0070");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        NLAL0070CMsg bizMsg  = (NLAL0070CMsg) cMsg;
        
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else {
            // no process
        }
        
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).tempWhInEtaDt_A1);        	
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdWhInEtaDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).finalWhInEtaDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).railAvalDt_A1);
            
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).schdEtaChkFlg_A1.getValue())) {
            	scrnMsg.A.no(i).schdWhInEtaDt_A1.setInputProtected(true);
            	scrnMsg.A.no(i).finalEtaChkFlg_A1.setInputProtected(false);
            } else {
            	scrnMsg.A.no(i).schdWhInEtaDt_A1.setInputProtected(false);
            	scrnMsg.A.no(i).finalEtaChkFlg_A1.setInputProtected(true);
            }
            
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).finalEtaChkFlg_A1.getValue())) {
            	scrnMsg.A.no(i).finalWhInEtaDt_A1.setInputProtected(true);
            	scrnMsg.A.no(i).schdEtaChkFlg_A1.setInputProtected(true);
            } else {
            	scrnMsg.A.no(i).finalWhInEtaDt_A1.setInputProtected(false);
            	scrnMsg.A.no(i).schdEtaChkFlg_A1.setInputProtected(false);
            }
        }
        scrnMsg.putErrorScreen();
        
        NLAL0070CommonLogic.scrnItemControl(scrnMsg);
        NLAL0070CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        NLAL0070CommonLogic.disableTableTextFieldForSchedule(scrnMsg);
    }

}
