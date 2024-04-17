/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL3060.NLBL3060CMsg;
//import business.servlet.NLBL3060.constant.NLBL3060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 *</pre>
 */
public class NLBL3060Scrn00_Btn_DtlRtlWh extends S21CommonHandler {

	  @Override
	    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    }

	    @Override
	    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
	        return null;
	    }

	    @Override
	    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

	        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
	        int selectIdx = getButtonSelectNumber();
	        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(selectIdx));

	        Object[] params = setPopupParameter(scrnMsg);
	        setArgForSubScreen(params);
	    }

	    private Object[] setPopupParameter(NLBL3060BMsg scrnMsg) {

	    	scrnMsg.xxScrEventNm.setValue("Btn_DtlRtlWh");
	        scrnMsg.P.clear();
	        Object[] params = new Object[7];
	        params[0] = "";
	        params[1] = "Retail Wh Popup";
	        params[2] = "RTL_WH";
	        List<Object[]> whereList = new ArrayList<Object[]>();

	        Object[] whereArray1 = new Object[4];
	        whereArray1[0] = "Retail Wh Code";
	        whereArray1[1] = "RTL_WH_CD";
	        whereArray1[2] = scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rtlWhCd_A1.getValue();
	        whereArray1[3] = "N";
	        whereList.add(whereArray1);

	        Object[] whereArray2 = new Object[4];
	        whereArray2[0] = "Retail Wh Name";
	        whereArray2[1] = "RTL_WH_NM";
	        whereArray2[2] = "";
	        whereArray2[3] = "Y";
	        whereList.add(whereArray2);

	        params[3] = whereList;

	        List<Object[]> columnList = new ArrayList<Object[]>();

	        Object[] columnArray1 = new Object[4];
	        columnArray1[0] = "Retail Wh Code";
	        columnArray1[1] = "RTL_WH_CD";
	        columnArray1[2] = BigDecimal.valueOf(15);
	        columnArray1[3] = "Y";
	        columnList.add(columnArray1);

	        Object[] columnArray2 = new Object[4];
	        columnArray2[0] = "Retail Wh Name";
	        columnArray2[1] = "RTL_WH_NM";
	        columnArray2[2] = BigDecimal.valueOf(85);
	        columnArray2[3] = "N";
	        columnList.add(columnArray2);

	        params[4] = columnList;

	        List<Object[]> sortConditionList = new ArrayList<Object[]>();

	        Object[] sortConditionArray1 = new Object[2];
	        sortConditionArray1[0] = "RTL_WH_CD";
	        sortConditionArray1[1] = "ASC";
	        sortConditionList.add(sortConditionArray1);

	        params[5] = sortConditionList;

	        params[6] = scrnMsg.P;

	        return params;
	    }
}
