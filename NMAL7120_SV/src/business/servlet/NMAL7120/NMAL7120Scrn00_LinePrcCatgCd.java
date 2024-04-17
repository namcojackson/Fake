package business.servlet.NMAL7120;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL7120Scrn00_LinePrcCatgCd extends S21CommonHandler implements NMAL7120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;

		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL7120Scrn00_LinePrcCatgCd");
		String selectStr = NMAL7120CommonLogic.setSelectFromStrForPrcCatg(getGlobalCompanyCode());
		List<Object[]> whereList = NMAL7120CommonLogic.setWhereListForPrcCatg(scrnMsg.A.no(getButtonSelectNumber()).prcCatgNm_A1.getValue());
		List<Object[]> tblColumnList = NMAL7120CommonLogic.setTblColumnListForPrcCatg(scrnMsg);
		List<Object[]> sortCondList = NMAL7120CommonLogic.setSortListForPrcCatg(scrnMsg);
        params[0] = "O1";                   
        params[1] = "Price List Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);

	}

}
