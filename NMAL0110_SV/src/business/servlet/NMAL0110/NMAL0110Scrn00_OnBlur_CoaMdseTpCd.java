package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   SRAA            K.Aratani       Create          QC#6748,9891,9916,9970
 * 2019/10/24   Fujitsu         K.Kato          Update          QC#51967
 *</pre>
 */
public class NMAL0110Scrn00_OnBlur_CoaMdseTpCd extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        //NMAL0110CommonLogic.clearValue(getUserProfileService(), scrnMsg, false, false);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
        // 2019/10/24 QC#51967 Add Start
		scrnMsg.xxRsltFlg_H4.clear(); // Revenue COA_ACCT Warning Flag
		scrnMsg.xxRsltFlg_H5.clear(); // Cost of goods COA_ACCT Warning Flag
        // 2019/10/24 QC#51967 Add End

		NMAL0110CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {
	        scrnMsg.addCheckItem(scrnMsg.mdseRgtnTpCd_H1);
        }
        scrnMsg.putErrorScreen();

    }

}