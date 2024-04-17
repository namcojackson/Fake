/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         K.Kasai         Create          QC#2815
 * 2016/03/14   Hitachi         T.Tomita        Update          QC#2815
 * 2016/09/21   Hitachi         Y.Zhang         Update          QC#12582
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL0440Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.Z);
        // add start 2018/06/25 QC#17369
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        // add end 2018/06/25 QC#17369

        // mod start 2016/02/09 QC#947
        Object[] params = new Object[7];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Serial Number Search Popup";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getSqlStr(scrnMsg);
        // 3 : Lv2 : Where List (List: Search condition columns)
        List<Object[]> whereList = new ArrayList<Object[]>();
        params[i++] = whereList;
        // 4 : Lv2 : Column List (List: Search result columns)
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.Z;
        setArgForSubScreen(params);
    }

    private String getSqlStr(NSAL0440BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();
        String dtlPkString = getDtlPkList(scrnMsg);

        sql.append(" SELECT");
        sql.append("      A.GLBL_CMPY_CD      AS GLBL_CMPY_CD");
        sql.append("     ,A.EZCANCELFLAG      AS EZCANCELFLAG");
        sql.append("     ,A.DS_CONTR_DTL_PK   AS DS_CONTR_DTL_PK");
        sql.append("     ,B.SVC_MACH_MSTR_PK  AS SVC_MACH_MSTR_PK");
        sql.append("     ,B.SER_NUM           AS SER_NUM");
        sql.append("     ,B.MDSE_CD           AS MDSE_CD");
        sql.append("     ,D.T_MDL_NM          AS T_MDL_NM");
        sql.append(" FROM");
        sql.append("      DS_CONTR_DTL    A");
        sql.append("     ,SVC_MACH_MSTR   B");
        sql.append("     ,SVC_CONFIG_MSTR C");
        sql.append("     ,MDL_NM          D");
        sql.append(" WHERE");
        sql.append("     A.GLBL_CMPY_CD       =  '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("'");
        sql.append(" AND A.DS_CONTR_PK        =  ");
        sql.append(scrnMsg.dsContrPk.getValue());
        sql.append("");
        if (hasValue(dtlPkString)) {
            sql.append(" AND A.DS_CONTR_DTL_PK    IN (");
            sql.append(dtlPkString);
            sql.append(")");
        }
        sql.append(" AND A.EZCANCELFLAG       =  '0'");
        sql.append(" AND A.GLBL_CMPY_CD       =  B.GLBL_CMPY_CD");
        sql.append(" AND A.SVC_MACH_MSTR_PK   =  B.SVC_MACH_MSTR_PK");
        sql.append(" AND B.EZCANCELFLAG       =  '0'");
        sql.append(" AND B.GLBL_CMPY_CD       =  C.GLBL_CMPY_CD");
        sql.append(" AND B.SVC_CONFIG_MSTR_PK =  C.SVC_CONFIG_MSTR_PK");
        sql.append(" AND C.EZCANCELFLAG       =  '0'");
        sql.append(" AND C.GLBL_CMPY_CD       =  D.T_GLBL_CMPY_CD");
        sql.append(" AND C.MDL_ID             =  D.T_MDL_ID");
        sql.append(" AND D.EZCANCELFLAG       =  '0'");

        return sql.toString();
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[4];
        c0[0] = "IB ID";
        c0[1] = "SVC_MACH_MSTR_PK";
        c0[2] = new BigDecimal(20);
        c0[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(c0);

        Object[] c1 = new Object[4];
        c1[0] = "Serial#";
        c1[1] = "SER_NUM";
        c1[2] = new BigDecimal(25);
        c1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(c1);

        Object[] c2 = new Object[4];
        // START 2016/09/21 Y.Zhang [QC#12852, MOD]
        c2[0] = "Item Cd";
        // END 2016/09/21 Y.Zhang [QC#12852, MOD]
        c2[1] = "MDSE_CD";
        c2[2] = new BigDecimal(16);
        c2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        Object[] c3 = new Object[4];
        c3[0] = "Model";
        c3[1] = "T_MDL_NM";
        c3[2] = new BigDecimal(30);
        c3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(c3);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "DS_CONTR_DTL_PK";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        return sortConditionList;
    }

    private String getDtlPkList(NSAL0440BMsg scrnMsg) {
        StringBuilder rtnVal = new StringBuilder();
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (rtnVal.length() > 0) {
                rtnVal.append(", ");
            }
            rtnVal.append(scrnMsg.R.no(i).dsContrDtlPk_R.getValue());
        }
        return rtnVal.toString();
    }
}
