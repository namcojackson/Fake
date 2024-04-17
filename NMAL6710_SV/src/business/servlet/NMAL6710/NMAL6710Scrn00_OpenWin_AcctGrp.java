/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

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
 * 2016/02/17   Fujitsu         T.Murai         Create          CSA #2943
 * 2016/10/03   Fujitsu         Mz.Takahashi    Update          CSA #14826
 *</pre>
 */
public class NMAL6710Scrn00_OpenWin_AcctGrp extends S21CommonHandler {

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

// 2016/10/03 CSA-QC#14826 Mod Start
//        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//
//        ZYPTableUtil.clear(scrnMsg.P);
//        scrnMsg.P.no(0).xxPopPrm.setValue("DS_ACCT_GRP");
//        scrnMsg.P.no(1).xxPopPrm.setValue("DS_ACCT_GRP_CD");
//        scrnMsg.P.no(2).xxPopPrm.setValue("DS_ACCT_GRP_DESC_TXT");
//        scrnMsg.P.no(3).xxPopPrm.setValue("DS_ACCT_GRP_SORT_NUM");
//        scrnMsg.P.no(4).xxPopPrm.setValue("Account Group Popup");
//        scrnMsg.P.no(5).xxPopPrm.setValue("Account Group Code");
//        scrnMsg.P.no(6).xxPopPrm.setValue("Account Group Name");
//        scrnMsg.P.no(7).xxPopPrm.setValue("Account Group Code");
//        scrnMsg.P.no(8).xxPopPrm.setValue("Account Group Name");
//        scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.dsAcctGrpCd_DP.getValue());
//        scrnMsg.P.no(10).xxPopPrm.setValue("");
//
//        Object[] param = new Object[11];
//        for (int i = 0; i < 11; i++) {
//            param[i] = scrnMsg.P.no(i).xxPopPrm;
//        }
//
//        setArgForSubScreen(param);

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        int selectedRow = getButtonSelectNumber();
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Account Group Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("DAG.GLBL_CMPY_CD ");
        sb.append(", DAG.EZCANCELFLAG ");
        sb.append(", DAG.DS_ACCT_GRP_CD ");
        sb.append(", DAG.DS_ACCT_GRP_DESC_TXT ");
        sb.append(", DAG.DS_ACCT_GRP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("DS_ACCT_GRP DAG ");
        sb.append("WHERE ");
        sb.append("DAG.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND DAG.EZCANCELFLAG = '0' ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Account Group Code";
        whereArray0[1] = "DS_ACCT_GRP_CD";
        whereArray0[2] = scrnMsg.dsAcctGrpCd_DP.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];

        whereArray1[0] = "Account Group Name";
        whereArray1[1] = "DS_ACCT_GRP_DESC_TXT";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Account Group Code";
        columnArray0[1] = "DS_ACCT_GRP_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Account Group Name";
        columnArray1[1] = "DS_ACCT_GRP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(72);
        columnArray1[3] = "Y";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_ACCT_GRP_SORT_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.I.clear();

        params[6] = scrnMsg.I;

        setArgForSubScreen(params);
// 2016/10/03 CSA-QC#14826 Mod End
    }
}
