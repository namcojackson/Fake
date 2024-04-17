package business.servlet.NMAL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL0100.NMAL0100CMsg;
import business.servlet.NMAL0100.constant.NMAL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0100_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!NMAL0100Constant.CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0100Scrn00_Product_Line_Level_3_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlCd_H1);
				NMAL0100CMsg bizMsg = new NMAL0100CMsg();
				bizMsg.setBusinessID(NMAL0100Constant.BUSINESS_ID);
				bizMsg.setFunctionCode(NMAL0100Constant.FUNC_CD_SRCH);
				EZDMsg.copy(scrnMsg, null, bizMsg, null);

		 		return bizMsg;
			}
		}
 		return null;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		NMAL0100CMsg bizMsg  = (NMAL0100CMsg) cMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!NMAL0100Constant.CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0100Scrn00_Product_Line_Level_3_Link".equals(event)) {
				EZDMsg.copy(bizMsg, null, scrnMsg, null);
			}
		}
		
		if (!NMAL0100Constant.CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0100Scrn00_Product_Line_Group_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlCd_H1);
			} else if ("NMAL0100Scrn00_Product_Line_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.firstProdCtrlCd_H1);
			} else if ("NMAL0100Scrn00_Product_Line_Level_2_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.scdProdCtrlCd_H1);
			} else if ("NMAL0100Scrn00_Product_Line_Level_3_Link".equals(event)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlCd_H1);
			} else if ("NMAL0100Scrn00_Product_Line_Level_4_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.frthProdCtrlCd_H1);
			} else if ("NMAL0100Scrn00_Sls_Mat_Group_Link_01".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_01, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_01, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_01);
			} else if ("NMAL0100Scrn00_Sls_Mat_Group_Link_02".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_02, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_02, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_02);
			} else if ("NMAL0100Scrn00_Sls_Mat_Group_Link_03".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpCd_03, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_03, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.slsMatGrpCd_03);
			} else if ("NMAL0100Scrn00_Comsn_Group_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCmsnGrpCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCmsnGrpDescTxt_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.dsCmsnGrpCd_H1);
			}

		}
    }
}
