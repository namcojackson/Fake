/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3160.common.NLBL3160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2017/10/10   CITS            S.Katsuma       Update          QC#21585
 *</pre>
 */
public class NLBL3160Scrn00_OpenWin_Branch extends S21CommonHandler {

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
        scrnNmP1 = "Service Branch Popup";

        tblNmP2.append("SELECT DISTINCT");
        tblNmP2.append("     X.GLBL_CMPY_CD");
        tblNmP2.append("    ,X.EZCANCELFLAG");
        tblNmP2.append("    ,X.SVC_BR_CD");
        tblNmP2.append("    ,X.SVC_BR_CD_DESC_TXT ");
        tblNmP2.append("FROM");
        tblNmP2.append("    SVC_BR_POST_XREF X ");
        tblNmP2.append("WHERE");
        tblNmP2.append("        X.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        tblNmP2.append("    AND X.EZCANCELFLAG = '0'");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Service Branch Code";
        whereArray0[1] = "SVC_BR_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.svcBrCd)) {
            whereArray0[2] = scrnMsg.svcBrCd.getValue();
        } else {
            whereArray0[2] = "%";
        }
        
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Service Branch Name";
        whereArray1[1] = "SVC_BR_CD_DESC_TXT";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Service Branch Code";
        columnArray0[1] = "SVC_BR_CD";
        // START 2017/10/10 S.Katsuma QC#21585 ADD
//        columnArray0[2] = BigDecimal.valueOf(50);
        columnArray0[2] = BigDecimal.valueOf(20);
        // END 2017/10/10 S.Katsuma QC#21585 ADD
        columnArray0[3] = "Y";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Service Branch Name";
        columnArray1[1] = "SVC_BR_CD_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "N";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "SVC_BR_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.P;

        return param;
    }
}
