/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;

import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/02/2013   Fujitsu         S.Nakai         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */

public class NSAL0020Scrn00_OpenWin_Layer2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //no 

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//
//        scrnMsg.P.clear();
//        Object[] params = new Object[7];
//
//        scrnMsg.xxScrEventNm_P6.setValue(NSAL0020Constant.LAYER_2);
//
//        String selectStr = NSAL0020CommonLogic.setSelectFromStr(scrnMsg, getGlobalCompanyCode());
//
//        List<Object[]> whereList = NSAL0020CommonLogic.setWhereList(scrnMsg);
//
//        List<Object[]> tblColumnList = NSAL0020CommonLogic.setTblColumnList(scrnMsg);
//
//        List<Object[]> sortCondList = NSAL0020CommonLogic.setSortList(scrnMsg);
//
//        params[0] = "P6";
//        params[1] = "Service Org Popup";
//        params[2] = selectStr;
//        params[3] = whereList;
//        params[4] = tblColumnList;
//        params[5] = sortCondList;
//        params[6] = scrnMsg.P;
//
//        setArgForSubScreen(params);
    }
}
