/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/09/21   Hitachi         Y.Zhang         Update          QC#12582
 *</pre>
 */
public class NSAL0530Scrn00_OpenWin_SvcMachMstr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    /**
     * set common pop up NWAL1130
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL0530BMsg
     * @return object list for parameter for NWAL1130 pop up
     */
    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0530BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.B);
        setValue(scrnMsg.xxScrEventNm, OPENWIN_SVCMACHMSTR);
        Object[] params = new Object[NWAL1130_PRM_LENGTH];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = NWAL1130_WIN_TITLE_MACH;
        // Table SQL
        params[i++] = SVC_MACH_MSTR_TBL_NM;
        // Where List
        params[i++] = getWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.B;

        return params;
    }

    private List<Object[]> getWhereList(NSAL0530BMsg scrnMsg) {
        String strSvcMachMstrPk = "";
        if (hasValue(scrnMsg.svcMachMstrPk_H.getValue())) {
            strSvcMachMstrPk = scrnMsg.svcMachMstrPk_H.getValue().toString();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Install Base ID";
        h0[WLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
        h0[WLIST_OBJ_VALUE] = strSvcMachMstrPk;
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Serial#";
        h1[WLIST_OBJ_ID] = "SER_NUM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
        // START 2016/09/21 Y.Zhang [QC#12582, Modify]
        h2[WLIST_DSP_OBJ_NM] = "Item Code";
        // END 2016/09/21 Y.Zhang [QC#12582, Modify]
        h2[WLIST_OBJ_ID] = "MDSE_CD";
        h2[WLIST_OBJ_VALUE] = "";
        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h2);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_WHERE_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Install Base ID";
        c0[CLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_MACH_MSTR_PK_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_WHERE_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Serial#";
        c1[CLIST_OBJ_ID] = "SER_NUM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(SER_NUM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_WHERE_LIST];
        // START 2016/09/21 Y.Zhang [QC#12582, Modify]
        c2[CLIST_DSP_OBJ_NM] = "Item Code";
        // END 2016/09/21 Y.Zhang [QC#12582, Modify]
        c2[CLIST_OBJ_ID] = "MDSE_CD";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(MDSE_CD_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "SVC_MACH_MSTR_PK";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
