/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_OpenWin_ProsRvwInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 *</pre>
 */
public class NMAL2800Scrn00_OpenWin_ProsRvwInfo extends S21CommonHandler {

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

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Prospect Reivew Search";
        params[2] = "DS_ACCT_RVW_PROS";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Prospect#";
        whereArray0[1] = "RVW_PROS_NUM";
        whereArray0[2] = scrnMsg.A.no(index).aftDsXrefAcctCd_A1.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Prospect Name";
        whereArray1[1] = "BEF_DS_ACCT_NM";
        whereArray1[2] = null;
        whereArray1[3] = "Y";
        whereList.add(whereArray1);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Prospect#";
        columnArray0[1] = "RVW_PROS_NUM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Prospect Name";
        columnArray1[1] = "BEF_DS_ACCT_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "N";
        columnList.add(columnArray1);
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "RVW_PROS_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);
        params[5] = sortConditionList;

        scrnMsg.Q.clear();
        params[6] = scrnMsg.Q;

        setArgForSubScreen(params);
    }
}
