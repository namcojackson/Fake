/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3160.common.NLBL3160CommonLogic;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160Scrn00_OpenWin_OrderCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        scrnMsg.xxMntEventNm.setValue(ctx.getEventName());

        NLBL3160CommonLogic.clearPopupParameter(scrnMsg);
        setArgForSubScreen(setPopupParameter(scrnMsg, getGlobalCompanyCode()));
    }

    private static Object[] setPopupParameter(NLBL3160BMsg scrnMsg, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Order Category Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" DOC.GLBL_CMPY_CD");
        tblNmP2.append(",DOC.EZCANCELFLAG");
        tblNmP2.append(",DOC.DS_ORD_CATG_SORT_NUM");
        tblNmP2.append(",DOC.DS_ORD_CATG_CD");
        tblNmP2.append(",DOC.DS_ORD_CATG_NM");
        tblNmP2.append(" FROM DS_ORD_CATG DOC");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" DOC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND DOC.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Category Code";
        whereArray0[1] = "DS_ORD_CATG_CD";
        whereArray0[2] = scrnMsg.P.no(0).xxPopPrm.getValue();
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Order Category Name";
        whereArray1[1] = "DS_ORD_CATG_NM";
        whereArray1[2] = scrnMsg.P.no(0).xxPopPrm.getValue();
        whereArray1[3] = "Y";
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Category Code";
        columnArray0[1] = "DS_ORD_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(25);
        columnArray0[3] = "Y";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Category Name";
        columnArray1[1] = "DS_ORD_CATG_NM";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = "N";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        scrnMsg.Z.clear();

        String[] strList = scrnMsg.dsOrdCatgDescTxt.getValue().split(NLBL3160Constant.XX_SPL_CHAR_TXT, 0);
        for (int i = 0; i < strList.length; i++) {
            scrnMsg.Z.no(i).xxComnScrColValTxt_1.setValue(strList[i]);
        }
        scrnMsg.Z.setValidCount(strList.length);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.Z;

        return param;
    }
}
