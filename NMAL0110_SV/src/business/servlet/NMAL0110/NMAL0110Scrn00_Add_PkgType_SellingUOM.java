package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110Scrn00_Add_PkgType_SellingUOM extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        // 100 records check
        if (scrnMsg.K.getValidCount() >= 100) {
        	//NMAM0050E=0,Details cannot be added because the number will exceed [@].
            scrnMsg.setMessageInfo("NMAM0050E", new String[] { "100" });
        }
        // Mandatory check
        String select = scrnMsg.pkgUomCd_H1.getValue();
        if (!ZYPCommonFunc.hasValue(scrnMsg.pkgUomCd_H1)) {
        	//NMAM0041E=0,Please specify [@].
            scrnMsg.pkgUomCd_H1.setErrorInfo(1, "NMAM0041E", new String[] { "Select Packaging Type" });
        }
        // Duplicate check
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            if (select.equals(scrnMsg.K.no(i).pkgUomCd_K1.getValue())) {
            	//NMAM0010E=0,[@] is already registered.
                scrnMsg.pkgUomCd_H1.setErrorInfo(1, "NMAM0010E", new String[] { "Select Packaging Type" });
            }
        }
        scrnMsg.addCheckItem(scrnMsg.pkgUomCd_H1);
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.K.no(scrnMsg.K.getValidCount()-1).xxChkBox_K1.setInputProtected(false);
        scrnMsg.K.no(scrnMsg.K.getValidCount()-1).inEachQty_K1.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.K.no(scrnMsg.K.getValidCount()-1).inEachQty_K1);
        
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
        // Set detail properties
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        // Button Control.
        NMAL0110CommonLogic.rowButtonControl(scrnMsg, this, TABLE_UOM_K);
        // Table Color
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
	}

}
