package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import java.util.List;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140Scrn00_Sub_WH_Link extends S21CommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;

 	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0140Scrn00_Sub_WH_Link");
		String selectStr = NMAL0140CommonLogic.setSelectFromStr(scrnMsg, getGlobalCompanyCode());
		List<Object[]> whereList = NMAL0140CommonLogic.setWhereList(scrnMsg);
		List<Object[]> tblColumnList = NMAL0140CommonLogic.setTblColumnList(scrnMsg);
		List<Object[]> sortCondList = NMAL0140CommonLogic.setSortList(scrnMsg);
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
