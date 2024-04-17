/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1880.NWAL1880CMsg;
//import business.servlet.NWAL1880.constant.NWAL1880Constant;

import business.servlet.NWAL1880.common.NWAL1880CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1880Scrn00_OpenWin_Zone extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        // 1. check EZDeveloper item attribute values.
        NWAL1880CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        setArgForSubScreen(setPopupParameter(scrnMsg, getGlobalCompanyCode()));
    }

    private static Object[] setPopupParameter(NWAL1880BMsg scrnMsg, String glblCmpyCd) {
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
        scrnNmP1 = "Order Zone Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" OZ.GLBL_CMPY_CD");
        tblNmP2.append(",OZ.EZCANCELFLAG");
        tblNmP2.append(",OZ.ORD_ZN_SORT_NUM");
        tblNmP2.append(",OZ.ORD_ZN_CD");
        tblNmP2.append(",OZ.ORD_ZN_DESC_TXT");
        tblNmP2.append(" FROM ORD_ZN OZ");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" OZ.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND OZ.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Zone Code";
        whereArray0[1] = "ORD_ZN_CD";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Order Zone Name";
        whereArray1[1] = "ORD_ZN_DESC_TXT";
        whereArray1[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Zone Code";
        columnArray0[1] = "ORD_ZN_CD";
        columnArray0[2] = BigDecimal.valueOf(25);
        columnArray0[3] = "Y";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Zone Name";
        columnArray1[1] = "ORD_ZN_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = "N";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "ORD_ZN_SORT_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        scrnMsg.Z.clear();

        String[] strList = scrnMsg.xxOrdZnSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
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
