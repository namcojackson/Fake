package business.servlet.NWCL0130;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWCL0130.NWCL0130BMsg;
import business.servlet.NWCL0130.common.NWCL0130CommonLogic;
import business.servlet.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/04/2016   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NWCL0130Scrn00_Inv_Num_Link extends S21CommonHandler implements NWCL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;

		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NWCL0130Scrn00_Inv_Num_Link");
		String selectStr = NWCL0130CommonLogic.setSelectFromStrForInvNum(getGlobalCompanyCode(), scrnMsg);
		List<Object[]> whereList = NWCL0130CommonLogic.setWhereListForInvNum(scrnMsg.invNum_H1.getValue());
		List<Object[]> tblColumnList = NWCL0130CommonLogic.setTblColumnListForInvNum(scrnMsg);
		List<Object[]> sortCondList = NWCL0130CommonLogic.setSortListForInvNum(scrnMsg);
        params[0] = "O1";                   
        params[1] = "Inv# Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);

	}

}
