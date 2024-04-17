package business.servlet.NWAL2040;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NWAL2040Scrn00_LineItem_OTBD_DEF_SWH extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;

		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(28).xxPopPrm.setValue(String.valueOf(getButtonSelectNumber()));
		scrnMsg.P.no(29).xxPopPrm.setValue("NWAL2040Scrn00_LineItem_OTBD_DEF_SWH");
		String selectStr = NWAL2040CommonLogic.setSelectFromStrForRtlSwh(scrnMsg, getGlobalCompanyCode());
		List<Object[]> whereList = NWAL2040CommonLogic.setWhereListForRtlSwh(scrnMsg, scrnMsg.A.no(getButtonSelectNumber()).otbdDefSwhCd_A1.getValue(), scrnMsg.A.no(getButtonSelectNumber()).rtlSwhNm_03.getValue());
		List<Object[]> tblColumnList = NWAL2040CommonLogic.setTblColumnListForRtlSwh(scrnMsg);
		List<Object[]> sortCondList = NWAL2040CommonLogic.setSortListForRtlSwh(scrnMsg);
        params[0] = "O1";                   
        params[1] = "Sub Warehouse Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);

	}

}
