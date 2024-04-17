/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

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
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          QC#6700
 *</pre>
 */
public class NSAL1200Scrn00_OpenWin_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.P);
        Object[] params = new Object[PRM_LENGTH_NWAL1130];

        int i = 0;
        // Global company code
        params[i++] = "";
        // Window title
        params[i++] = WINDOW_TITLE_NWAL1130;
        // Table SQL
        params[i++] = getSqlStr(getGlobalCompanyCode());
        // Where List
        params[i++] = getWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.P;

        return params;
    }

    private static String getSqlStr(String glblCmpyCd) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     T.GLBL_CMPY_CD");
        sql.append("    ,T.MTR_GRP_PK");
        sql.append("    ,T.MTR_GRP_NM");
        sql.append("    ,T.MTR_GRP_DESC_TXT");
        sql.append("    ,T.MTR_GRP_TP_CD");
        sql.append("    ,T.EZCANCELFLAG");
        sql.append(" FROM MTR_GRP T");
        sql.append(" WHERE");
        sql.append("     T.GLBL_CMPY_CD = '" + glblCmpyCd + "'");

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL1200BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        // Counter Group Name
        whereList.add(createAttributeNameWhereList(NWAL1130_DISP_NM_MTR_GRP_NM, NWAL1130_SQL_NM_MTR_GRP_NM, null, ZYPConstant.FLG_ON_Y));

        // Counter Group Description
        whereList.add(createAttributeNameWhereList(NWAL1130_DISP_NM_MTR_GRP_DESC_TXT, NWAL1130_SQL_NM_MTR_GRP_DESC_TXT, null, ZYPConstant.FLG_ON_Y));

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // Counter Group Name
        columnList.add(createAttributeNameColumnList(NWAL1130_DISP_NM_MTR_GRP_NM, NWAL1130_SQL_NM_MTR_GRP_NM, NWAL1130_WIDTH_MTR_GRP_NM, ZYPConstant.FLG_ON_Y));

        // Counter Group Description
        columnList.add(createAttributeNameColumnList(NWAL1130_DISP_NM_MTR_GRP_DESC_TXT, NWAL1130_SQL_NM_MTR_GRP_DESC_TXT, NWAL1130_WIDTH_MTR_GRP_DESC_TXT, ZYPConstant.FLG_OFF_N));

        // MTR_GRP_PK (hidden)
        columnList.add(createAttributeNameColumnList(NWAL1130_SQL_NM_MTR_GRP_PK, NWAL1130_SQL_NM_MTR_GRP_PK, 0, ZYPConstant.FLG_OFF_N));

        // MTR_GRP_TP_CD (hidden)
        columnList.add(createAttributeNameColumnList(NWAL1130_SQL_NM_MTR_GRP_TP_CD, NWAL1130_SQL_NM_MTR_GRP_TP_CD, 0, ZYPConstant.FLG_OFF_N));

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        return sortConditionList;
    }

    private Object[] createAttributeNameWhereList(String objNm, String objId, String objValue, String whereFlag) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(objId);
        list.add(objValue);
        list.add(whereFlag);

        // START 2016/04/26 [QC#6700, MOD]
        return list.toArray(new Object[list.size()]);
        // END   2016/04/26 [QC#6700, MOD]
    }

    private Object[] createAttributeNameColumnList(String objNm, String objId, int objLength, String whereFlag) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(objId);
        list.add(BigDecimal.valueOf(objLength));
        list.add(whereFlag);

        // START 2016/04/26 [QC#6700, MOD]
        return list.toArray(new Object[list.size()]);
        // END   2016/04/26 [QC#6700, MOD]
    }
}
