package business.servlet.NMAL0110;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0110Scrn00_Def_Src_Wh_Link extends S21CommonHandler implements NMAL0110Constant {

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
//QC#10449
//		ZYPTableUtil.clear(scrnMsg.P);
//		scrnMsg.P.no(0).xxPopPrm.setValue("RTL_WH");
//		scrnMsg.P.no(1).xxPopPrm.setValue("RTL_WH_CD");
//		scrnMsg.P.no(2).xxPopPrm.setValue("RTL_WH_NM");
//		scrnMsg.P.no(3).xxPopPrm.setValue("RTL_WH_CD");
//		scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Default Source WH Code");
//		scrnMsg.P.no(5).xxPopPrm.setValue("Default Source WH");
//		scrnMsg.P.no(6).xxPopPrm.setValue("Default Source WH Name");
//		scrnMsg.P.no(7).xxPopPrm.setValue("Default Source WH");
//		scrnMsg.P.no(8).xxPopPrm.setValue("Default Source WH Name");
//		scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.defSrcWhCd_H1.getValue());
//		scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.locNm_DW.getValue());
//		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_Def_Src_Wh_Link");
//		Object[] params = NMAL0110CommonLogic.toArray_popup(scrnMsg.P, 11);
//		setArgForSubScreen(params);
//		
//		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
//		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
//		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
//		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_Def_Src_Wh_Link");
		String selectStr = NMAL0110CommonLogic.setSelectFromStrForDefSrcWh(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate());
		List<Object[]> whereList = NMAL0110CommonLogic.setWhereListForDefSrcWh(scrnMsg);
		List<Object[]> tblColumnList = NMAL0110CommonLogic.setTblColumnListForDefSrcWh(scrnMsg);
		List<Object[]> sortCondList = NMAL0110CommonLogic.setSortListForDefSrcWh(scrnMsg);
        params[0] = "O1";                   
        params[1] = "Warehouse Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);
		

	}

}
