package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 08/07/2018   Fujitsu         N.Sugiura       Update          QC#25385
 *</pre>
 */
public class NMAL0110_NWAL1130 extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0110Scrn00_Product_Line_Level_3_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlCd_H1);
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
		NMAL0110CMsg bizMsg  = (NMAL0110CMsg) cMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0110Scrn00_Product_Line_Level_3_Link".equals(event)) {
				EZDMsg.copy(bizMsg, null, scrnMsg, null);
			}
		}
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0110Scrn00_Def_Src_Sub_Wh_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.defSrcWhCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_DW, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.defSrcSubWhCd_H1, scrnMsg.O.no(2).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_SW, scrnMsg.O.no(3).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.defSrcSubWhCd_H1);
            //QC#10449
			} else if ("NMAL0110Scrn00_Def_Src_Wh_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.defSrcWhCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_DW, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.defSrcWhCd_H1);
			} else if ("NMAL0110Scrn00_Sls_Mat_Group_Link_01".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_01, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_01, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_01);
			} else if ("NMAL0110Scrn00_Sls_Mat_Group_Link_02".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_02, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_02, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_02);
			} else if ("NMAL0110Scrn00_Sls_Mat_Group_Link_03".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_03, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_03, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_03);
			} else if ("NMAL0110Scrn00_Comsn_Group_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCmsnGrpCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCmsnGrpDescTxt_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.dsCmsnGrpCd_H1);
			} else if ("NMAL0110Scrn00_Product_Line_Group_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlCd_H1);
			} else if ("NMAL0110Scrn00_Product_Line_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.firstProdCtrlCd_H1);
			} else if ("NMAL0110Scrn00_Product_Line_Level_2_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.scdProdCtrlCd_H1);
			} else if ("NMAL0110Scrn00_Product_Line_Level_3_Link".equals(event)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlCd_H1);
			} else if ("NMAL0110Scrn00_Product_Line_Level_4_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.frthProdCtrlCd_H1);
			} else if ("NMAL0110Scrn00_Merchandise_Type_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaMdseTpCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjDescTxt_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
                // 2018/08/07 QC#25385 Add Start
                NMAL0110CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
                NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
                NMAL0110CommonLogic.changeTableColorController(scrnMsg);
                // 2018/08/07 QC#25385 Add End
				scrnMsg.setFocusItem(scrnMsg.coaMdseTpCd_H1);
			} else if ("NMAL0110Scrn00_Rtrn_Ctrl_Tp_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpVndRelnPk_H1, new java.math.BigDecimal(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpCd_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpNm_H1, scrnMsg.O.no(2).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToPrntVndCd_H1, scrnMsg.O.no(3).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_P1, scrnMsg.O.no(4).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToVndCd_H1, scrnMsg.O.no(5).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_V1, scrnMsg.O.no(6).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnWhCd_H1, scrnMsg.O.no(7).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_W1, scrnMsg.O.no(8).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.rtrnCtrlTpCd_H1);
				
			}
		}
		
	}

}
