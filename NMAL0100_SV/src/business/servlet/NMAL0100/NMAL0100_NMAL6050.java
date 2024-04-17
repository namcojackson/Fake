package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_CLOSE;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0100.NMAL0100CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 * 2019/10/18   Fujitsu         C.Hara          Create          QC#53815
 *</pre>
 */
public class NMAL0100_NMAL6050 extends S21CommonHandler   {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		NMAL0100CMsg bizMsg = new NMAL0100CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		NMAL0100CMsg bizMsg  = (NMAL0100CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			//PRODUCT CODE
			if ("NMAL0100Scrn00_COA_Product_Link".equals(event)) {
				ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
			//CREATED FROM TEMPLATE
			} else if ("NMAL0100Scrn00_Item_Tmpl_Link".equals(event)) {
				ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCratTmplNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.mdseCratTmplNm_H1);
			//MARKETING MODEL
			} else if ("NMAL0100Scrn00_Mkt_Mdl_Link".equals(event)) {
				ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.mktMdlCd_H1);
			//MARKETING SEGMENT
			} else if ("NMAL0100Scrn00_Mkt_Mdl_Seg_Link".equals(event)) {
				ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlSegCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlSegNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.mktMdlSegCd_H1);
			} else if ("NMAL0100Scrn00_Merchandise_Type_Link".equals(event)) {
				ZYPEZDItemValueSetter.setValue(scrnMsg.coaMdseTpCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjDescTxt_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.coaMdseTpCd_H1);
			// 2019/10/18 QC#53815 Add Start
			} else if("NMAL0100Scrn00_Manufacturer_Link".equals(event)){
				ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemMnfCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemMnfNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.mdseItemMnfNm_H1);
			// 2019/10/18 QC#53815 Add End
			}
		}
	}

}
