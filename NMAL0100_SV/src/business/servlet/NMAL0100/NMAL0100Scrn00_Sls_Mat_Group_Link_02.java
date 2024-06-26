package business.servlet.NMAL0100;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0100.common.NMAL0100CommonLogic;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0100Scrn00_Sls_Mat_Group_Link_02 extends S21CommonHandler {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0100Scrn00_Sls_Mat_Group_Link_02");
		String selectStr = NMAL0100CommonLogic.setSelectForMaterialGroup(scrnMsg, getGlobalCompanyCode(), "2");
		List<Object[]> whereList = NMAL0100CommonLogic.setWhereListForMaterialGroup(scrnMsg.slsMatGrpCd_02.getValue(), scrnMsg.slsMatGrpDescTxt_02.getValue());
		List<Object[]> tblColumnList = NMAL0100CommonLogic.setTblColumnListForMaterialGroup(scrnMsg);
		List<Object[]> sortCondList = NMAL0100CommonLogic.setSortListForMaterialGroup(scrnMsg);
        params[0] = "O1";                   
        params[1] = "Material Group Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);
        
	}

}
