/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/18   Hitachi         Y.Ogura         Create          QC#61793
 *</pre>
 */
public class NLBL3060Scrn00_Link_GroupName extends S21CommonHandler {

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

        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }
    
    private Object[] setPopupParameter(NLBL3060BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Link_GroupName");
        scrnMsg.P.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Group Name Popup";
        params[2] = "SELECT DISTINCT A.PHYS_WH_CD PHYS_WH_CD,A.EZCANCELFLAG,A.GLBL_CMPY_CD FROM RTL_WH A WHERE PHYS_WH_CD IS NOT NULL";
        List<Object[]> whereList = new ArrayList<Object[]>();

        String whereSt  = "";
        if(scrnMsg.physWhCd_HD.getValue().isEmpty()){
            
            whereSt = "%";
        }else {
            whereSt = scrnMsg.physWhCd_HD.getValue();
        }
        
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Group Name";
        whereArray1[1] = "PHYS_WH_CD";
        whereArray1[2] = whereSt;
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Group Name";
        columnArray1[1] = "PHYS_WH_CD";
        columnArray1[2] = BigDecimal.valueOf(40);
        columnArray1[3] = "Y";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PHYS_WH_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        params[6] = scrnMsg.P;

        return params;
    }
}
