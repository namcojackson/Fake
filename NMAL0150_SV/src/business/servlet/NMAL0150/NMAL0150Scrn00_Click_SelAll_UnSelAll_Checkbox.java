package business.servlet.NMAL0150;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.NMAL0150BMsg;
import business.servlet.NMAL0150.constant.NMAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 05/30/2022   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150Scrn00_Click_SelAll_UnSelAll_Checkbox extends S21CommonHandler implements NMAL0150Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2022/05/30 R.Onozuka [QC#55970, MOD]
        //return null;
        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        NMAL0150CMsg bizMsg = new NMAL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END 2022/05/30 R.Onozuka [QC#55970, MOD]

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        	if (FLG_ON_Y.equals(scrnMsg.xxChkBox_AL.getValue())) {
        		ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A1, FLG_ON_Y);
        	} else {
        		scrnMsg.A.no(i).xxChkBox_A1.clear();
        	}
        }
    }

}
