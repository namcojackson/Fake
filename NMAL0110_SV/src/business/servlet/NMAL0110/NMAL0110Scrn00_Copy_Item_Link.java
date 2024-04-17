package business.servlet.NMAL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   SRAA            K.Aratani       Create          QC#6748,9891,9916,9970
 * 2019/10/24   Fujitsu         K.Kato          Update          QC#51967
 *</pre>
 */
public class NMAL0110Scrn00_Copy_Item_Link extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
        // 2019/10/24 QC#51967 Add Start
		scrnMsg.xxRsltFlg_H4.clear(); // Revenue COA_ACCT Warning Flag
		scrnMsg.xxRsltFlg_H5.clear(); // Cost of goods COA_ACCT Warning Flag
        // 2019/10/24 QC#51967 Add End

		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.mdseCd_C1.getValue());
		//scrnMsg.P.no(7).xxPopPrm.setValue(scrnMsg.mdseDescShortTxt_H1.getValue());
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_Copy_Item_Link");
		Object[] params = NMAL0110CommonLogic.toArray_popup(scrnMsg.P, 11);
		setArgForSubScreen(params);

	}

}
