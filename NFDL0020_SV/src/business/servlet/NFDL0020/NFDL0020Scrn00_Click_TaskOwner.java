/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Y.Miyauchi         Create          N/A
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 *</pre>
 */
public class NFDL0020Scrn00_Click_TaskOwner extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        //NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFDL0020BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_TaskOwner");
        scrnMsg.Q.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Owner Search Popup";

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT");
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        sb.append(" DISTINCT");
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        sb.append("        INLINE1.EZCANCELFLAG");
        sb.append("       ,INLINE1.GLBL_CMPY_CD");
        sb.append("       ,INLINE1.CLT_PSN_CD");
        sb.append("       ,INLINE1.CLT_PSN_NM");
        sb.append(" FROM  CLT_PTFO INLINE1");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Person Code";
        whereArray0[1] = "CLT_PSN_CD";
        whereArray0[2] = "";
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Person Name";
        whereArray1[1] = "CLT_PSN_NM";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Person Code";
        columnArray0[1] = "CLT_PSN_CD";
        columnArray0[2] = BigDecimal.valueOf(8);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Person Name";
        columnArray1[1] = "CLT_PSN_NM";
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //columnArray1[2] = BigDecimal.valueOf(120);
        columnArray1[2] = BigDecimal.valueOf(80);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "CLT_PSN_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.Q;

        return params;
    }

}
