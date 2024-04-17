/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6800;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6800.common.NMAL6800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6800Scrn00_Product_Line_Group_Link
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800Scrn00_Product_Line_Group_Link extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
		ZYPTableUtil.clear(scrnMsg.P);
		ZYPTableUtil.clear(scrnMsg.O);
		Object[] params = new Object[7];
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL6800Scrn00_Product_Line_Group_Link");
		String selectStr = NMAL6800CommonLogic.setSelectForProdCtrl(getGlobalCompanyCode(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
		List<Object[]> whereList = NMAL6800CommonLogic.setWhereListForProdCtrl(scrnMsg.zerothProdCtrlNm_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
		List<Object[]> tblColumnList = NMAL6800CommonLogic.setTblColumnListForProdCtrl(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
		List<Object[]> sortCondList = NMAL6800CommonLogic.setSortListForProdCtrl();
        params[0] = "O1";
        params[1] = "Product Level 1 Search Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.O;              //6.Output(R)
        setArgForSubScreen(params);

    }

}
