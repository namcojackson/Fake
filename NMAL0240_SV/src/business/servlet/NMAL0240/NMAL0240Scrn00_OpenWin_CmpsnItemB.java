/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240Scrn00_OpenWin_CmpsnItemB
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0240Scrn00_OpenWin_CmpsnItemB extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

//        scrnMsg.xxScrNm.setValue(OPEN_CMPSN_B);
//
//        Object[] params = new Object[7];
//        params[0] = "";
//        params[1] = "Composition Item Search Popup";
//        params[2] = "ALL_MDSE_V";
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[4];
//
//        whereArray0[0] = "Item Number";
//        whereArray0[1] = "MDSE_CD";
//        whereArray0[2] = scrnMsg.B.no(getButtonSelectNumber()).mdseCd_B1.getValue();
//        whereArray0[3] = "Y";
//        whereList.add(whereArray0);
//
//        params[3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[4];
//        columnArray0[0] = "Item Number";
//        columnArray0[1] = "MDSE_CD";
//        columnArray0[2] = BigDecimal.valueOf(16);
//        columnArray0[3] = "Y";
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[4];
//        columnArray1[0] = "Item Description";
//        columnArray1[1] = "MDSE_NM";
//        columnArray1[2] = BigDecimal.valueOf(30);
//        columnArray1[3] = "N";
//        columnList.add(columnArray1);
//
//        params[4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray = new Object[2];
//        sortConditionArray[0] = "MDSE_CD";
//        sortConditionArray[1] = "ASC";
//        sortConditionList.add(sortConditionArray);
//
//        params[5] = sortConditionList;
//
//        scrnMsg.P.clear();
//
//        params[6] = scrnMsg.P;
//
//        setArgForSubScreen(params);
        
        
		ZYPTableUtil.clear(scrnMsg.O);
		scrnMsg.O.no(0).xxPopPrm.setValue(scrnMsg.B.no(getButtonSelectNumber()).mdseCd_B1.getValue());
		//scrnMsg.O.no(7).xxPopPrm.setValue(scrnMsg.mdseDescShortTxt_H1.getValue());
		scrnMsg.O.no(29).xxPopPrm.setValue("NMAL0240Scrn00_OpenWin_CmpsnItemB");
        int size = scrnMsg.O.length();
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = scrnMsg.O.no(i).xxPopPrm;
        }
		setArgForSubScreen(param);
    }
}
