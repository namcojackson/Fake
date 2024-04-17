package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import business.servlet.NMAL0110.NMAL0110BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 2019/10/18   Fujitsu         C.Hara          Create          QC#53815
 *</pre>
 */
public class NMAL0110_NMAL6050 extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0110Scrn00_Frt_Cls_Link".equals(event)) {
				scrnMsg.frtClsCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.frtClsDescTxt_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				NMAL0110CMsg bizMsg = new NMAL0110CMsg();
				bizMsg.setBusinessID(BUSINESS_ID);
				bizMsg.setFunctionCode(FUNC_CD_SRCH);
				EZDMsg.copy(scrnMsg, null, bizMsg, null);
	
		 		return bizMsg;
			}
		}
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			// Product Code
			if ("NMAL0110Scrn00_COA_Product_Link".equals(event)) {
				scrnMsg.coaProdCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.coaProdNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
			//Market Model
			} else if ("NMAL0110Scrn00_Mkt_Mdl_Link".equals(event)) {
				scrnMsg.mktMdlCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.mktMdlNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.mktMdlCd_H1);
			//Market Model Segment
			} else if ("NMAL0110Scrn00_Mkt_Mdl_Seg_Link".equals(event)) {
				scrnMsg.mktMdlSegCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.mktMdlSegNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.mktMdlSegCd_H1);
			//Manufacture Country
			} else if ("NMAL0110Scrn00_Mnf_Ctry_Link".equals(event)) {
				scrnMsg.madeInCtryCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.ctryNm_MI.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.madeInCtryCd_H1);
			//Assemble Country 
			} else if ("NMAL0110Scrn00_Asm_Ctry_Link".equals(event)) {
				scrnMsg.asmInCtryCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.ctryNm_AI.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.asmInCtryCd_H1);
            //QC#10449
			//Default Source WH
			//} else if ("NMAL0110Scrn00_Def_Src_Wh_Link".equals(event)) {
			//	scrnMsg.defSrcWhCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
			//	scrnMsg.locNm_DW.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
			//	scrnMsg.setFocusItem(scrnMsg.defSrcWhCd_H1);
			//Default Source Sub WH
			//} else if ("NMAL0110Scrn00_Def_Src_Sub_Wh_Link".equals(event)) {
			//	scrnMsg.defSrcSubWhCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
			//	scrnMsg.locNm_SW.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
			//	scrnMsg.setFocusItem(scrnMsg.defSrcSubWhCd_H1);
			//Account Code(Revenue)
			} else if ("NMAL0110Scrn00_Revenue_Link".equals(event)) {
				scrnMsg.revCoaAcctCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.coaAcctNm_RV.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.revCoaAcctCd_H1);
			//Account Code(GOS)
			} else if ("NMAL0110Scrn00_COG_Link".equals(event)) {
				scrnMsg.cogsCoaAcctCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.coaAcctNm_CG.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.cogsCoaAcctCd_H1);
			//Account Code(Expense)
			} else if ("NMAL0110Scrn00_Expense_Link".equals(event)) {
				scrnMsg.expCoaAcctCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.coaAcctNm_EX.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.expCoaAcctCd_H1);
			//Account Customer Number
			} else if ("NMAL0110Scrn00_LineItem_CUST".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.E.no(row).dsAcctNum_E1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.E.no(row).dsAcctNm_E1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.E.no(row).dsAcctNum_E1);
		    //Software Product Category
			} else if ("NMAL0110Scrn00_Sw_Prod_Catg_Link".equals(event)) {
				scrnMsg.swProdCatgCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.swProdCatgDescTxt_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.swProdCatgCd_H1);
			//Freight Class
			} else if ("NMAL0110Scrn00_Frt_Cls_Link".equals(event)) {
		        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
		        EZDMsg.copy(bizMsg, null, scrnMsg, null);
				scrnMsg.setFocusItem(scrnMsg.frtClsCd_H1);
			//Tariff
			} else if ("NMAL0110Scrn00_Trf_Link".equals(event)) {
				scrnMsg.trfCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.trfDescTxt_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.trfCd_H1);
			// 2019/10/18 QC#53815 Add Start
			} else if("NMAL0110Scrn00_Manufacturer_Link".equals(event)){
				ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemMnfCd_H1, scrnMsg.P.no(9).xxPopPrm);
				ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemMnfNm_H1, scrnMsg.P.no(10).xxPopPrm);
				scrnMsg.setFocusItem(scrnMsg.mdseItemMnfNm_H1);
			// 2019/10/18 QC#53815 Add End
			}
			
		}
	}

}
