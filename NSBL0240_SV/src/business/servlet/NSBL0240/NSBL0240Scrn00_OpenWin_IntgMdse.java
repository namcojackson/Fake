/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0240.constant.NSBL0240Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Travel Charge Table Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/05   SRAA            Y.Chen          Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0240Scrn00_OpenWin_IntgMdse extends S21CommonHandler /*implements NSBL0240Constant*/ {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
//        
//        scrnMsg.xxScrEventNm.setValue(EVENT_NAME_OPEN_WIN_INTG_MDSE);
//        String itngMdseCd = scrnMsg.A.no(getButtonSelectNumber()).intgMdseCd_A.getValue();
//
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {"Merchandise Code", "MDSE_CD", itngMdseCd, ZYPConstant.FLG_ON_Y };
//        Object[] whereObj2 = {"Merchandise Name", "MDSE_NM", null, ZYPConstant.FLG_ON_Y };
//        Object[] whereObj3 = {"COA Prod Cd", "COA_PROD_CD", null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//        whereList.add(whereObj3);
//        
//        List<Object> colList = new ArrayList<Object>();
//        Object[] colObj1 = {"Merchandise Code", "MDSE_CD", new BigDecimal(16), ZYPConstant.FLG_ON_Y };
//        Object[] colObj2 = {"Merchandise Name", "MDSE_NM", new BigDecimal(30), ZYPConstant.FLG_OFF_N };
//        Object[] colObj3 = {"COA Prod Cd", "COA_PROD_CD", new BigDecimal(8), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj1);
//        colList.add(colObj2);
//        colList.add(colObj3);
//
//        List<Object> sortList = new ArrayList<Object>();
//        Object[] sortObj1 = {"MDSE_CD", "ASC" };
//        sortList.add(sortObj1);
//
//        // 0 : Lv1 : Suffix
//        // 1 : Lv1 : Window Title
//        // 2 : Lv1 : Select Table Name
//        // 3 : Lv1 : Where List
//        // 4 : Lv1 : Column List
//        // 5 : Lv1 : Sort Condition List
//        // 6 : Output
//        Object[] params = new Object[7];
//        params[0] = "P1";
//        params[1] = "Service Intangible Merchandise Popup";
//        params[2] = getSelectSQL(scrnMsg);
//        params[3] = whereList;
//        params[4] = colList;
//        params[5] = sortList;
//        params[6] = scrnMsg.P;
//
//        setArgForSubScreen(params);
    }
//    
//    private String getSelectSQL(NSBL0240BMsg scrnMsg) {
//        return "SELECT M.MDSE_CD, M.MDSE_NM, M.COA_PROD_CD, M.GLBL_CMPY_CD, M.EZCANCELFLAG FROM MDSE M WHERE M.INTG_PROD_CATG_CD='30' AND M.RGTN_STS_CD='P20'";
//    }
}
