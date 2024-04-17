/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import static business.servlet.NSAL1050.constant.NSAL1050Constant.NUM_3;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.NUM_4;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.NUM_5;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.NUM_6;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.NUM_7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#5489
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1050Scrn00_OpenWin_TandC_Source extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, BigDecimal.valueOf(index));
        String svcTermDataTpCd = scrnMsg.A.no(index).svcTermDataTpCd_1V.getValue();
        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(svcTermDataTpCd)) {
            setArgForSubScreen(createPopupParamsForDropdown(scrnMsg, index));
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(svcTermDataTpCd)) {
            setArgForSubScreen(createPopupParamsForLookup(scrnMsg, index));
        // add start 2018/06/25 QC#17369
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(svcTermDataTpCd)) {
            setArgForSubScreen(createPopupParamsForLookup(scrnMsg, index));
        // add end 2018/06/25 QC#17369
        }
    }

    private Object[] createPopupParamsForDropdown(NSAL1050BMsg scrnMsg, int index){
        Object[] params = new Object[NUM_7];
        params[0] = "";
        params[1] = "T&C LOV DDF Mapping Search";
        params[2] = getSqlForDropdown(getGlobalCompanyCode(), scrnMsg.A.no(index).svcTermCondSrcDescTxt_A.getValue());
        params[NUM_3] = getSearchConditionSettingForDropdown(index, scrnMsg);
        params[NUM_4] = getDisplayColumnSettingForDropdown();
        params[NUM_5] = getSortSettingForDropdown();
        params[NUM_6] = scrnMsg.B;
        return params;
    }

    private String getSqlForDropdown(String globalCompanyCode, String svcTermCondSrcDescTxt) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT");
        sb.append("     A.GLBL_CMPY_CD ");
        sb.append("    ,A.EZCANCELFLAG ");
        sb.append("    ,A.SVC_TERM_COND_SRC_DESC_TXT ");
        sb.append("    ,A.SVC_TERM_COND_DATA_SRC_CD ");
        sb.append(" FROM ");
        sb.append("     SVC_TERM_COND_DATA_SRC A ");
        sb.append(" WHERE  ");
        sb.append("     A.GLBL_CMPY_CD = '");
        sb.append(globalCompanyCode);
        sb.append("'");
        sb.append("     AND A.EZCANCELFLAG = '0'");
        String sql = sb.toString();
        return sql;
    }

    private List<Object> getSortSettingForDropdown() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[NUM_4];
        sortObj1[0] = "SVC_TERM_COND_DATA_SRC_CD";
        sortObj1[1] = "ASC";
        sortList.add(sortObj1);

        return sortList;
    }

    private List<Object> getDisplayColumnSettingForDropdown() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[NUM_4];
        colObj1[0] = "Description";
        colObj1[1] = "SVC_TERM_COND_SRC_DESC_TXT";
        colObj1[2] = new BigDecimal("50");
        colObj1[NUM_3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);

        Object[] colObj2 = new Object[NUM_4];
        colObj2[0] = "";
        colObj2[1] = "SVC_TERM_COND_DATA_SRC_CD";
        colObj2[2] = new BigDecimal("0");
        colObj2[NUM_3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSearchConditionSettingForDropdown(int index, NSAL1050BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[NUM_4];
        whereObj1[0] = "Description";
        whereObj1[1] = "SVC_TERM_COND_SRC_DESC_TXT";
        whereObj1[2] = scrnMsg.A.no(index).svcTermCondSrcDescTxt_A.getValue();
        whereObj1[NUM_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        return whereList;
    }

    private Object[] createPopupParamsForLookup(NSAL1050BMsg scrnMsg, int index){
        Object[] params = new Object[NUM_7];
        params[0] = "";
        params[1] = "T&C LOV DDF Mapping Search";
        params[2] = getSqlForLookup(getGlobalCompanyCode(), scrnMsg.A.no(index).svcTermCondSrcDescTxt_A.getValue());
        params[NUM_3] = getSearchConditionSettingForLookup(index, scrnMsg);
        params[NUM_4] = getDisplayColumnSettingForLookup();
        params[NUM_5] = getSortSettingForLookup();
        params[NUM_6] = scrnMsg.B;
        return params;
    }

    private String getSqlForLookup(String globalCompanyCode, String svcTermCondSrcDescTxt) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append("     A.GLBL_CMPY_CD ");
        sb.append("    ,A.EZCANCELFLAG ");
        sb.append("    ,A.PHYS_MAINT_TRGT_TBL_NM ");
        sb.append("    ,A.LOGIC_MAINT_TRGT_TBL_NM ");
        sb.append("    ,A.LOGIC_MAINT_TRGT_COL_NM ");
        sb.append("    ,A.LOGIC_DPLY_TRGT_COL_NM ");
        sb.append(" FROM ");
        sb.append("     SVC_TERM_COND_ATTRB_MAP A ");
        sb.append(" WHERE  ");
        sb.append("     A.GLBL_CMPY_CD = '");
        sb.append(globalCompanyCode);
        sb.append("'");
        sb.append("     AND A.EZCANCELFLAG = '0'");
        String sql = sb.toString();
        return sql;
    }

    private List<Object> getSortSettingForLookup() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[NUM_4];
        sortObj1[0] = "PHYS_MAINT_TRGT_TBL_NM";
        sortObj1[1] = "ASC";
        sortList.add(sortObj1);

        return sortList;
    }

    private List<Object> getDisplayColumnSettingForLookup() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[NUM_4];
        colObj1[0] = "Target Table Name";
        colObj1[1] = "LOGIC_MAINT_TRGT_TBL_NM";
        colObj1[2] = new BigDecimal("30");
        colObj1[NUM_3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);

        Object[] colObj2 = new Object[NUM_4];
        colObj2[0] = "";
        colObj2[1] = "PHYS_MAINT_TRGT_TBL_NM";
        colObj2[2] = BigDecimal.ZERO;
        colObj2[NUM_3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj2);

        Object[] colObj3 = new Object[NUM_4];
        colObj3[0] = "Target Column Name";
        colObj3[1] = "LOGIC_MAINT_TRGT_COL_NM";
        colObj3[2] = new BigDecimal("30");
        colObj3[NUM_3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj3);

        Object[] colObj4 = new Object[NUM_4];
        colObj4[0] = "Display Column Name";
        colObj4[1] = "LOGIC_DPLY_TRGT_COL_NM";
        colObj4[2] = new BigDecimal("30");
        colObj4[NUM_3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj4);

        return colList;
    }

    private List<Object> getSearchConditionSettingForLookup(int index, NSAL1050BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[NUM_4];
        whereObj1[0] = "Target Table Name";
        whereObj1[1] = "LOGIC_MAINT_TRGT_TBL_NM";
        whereObj1[2] = scrnMsg.A.no(index).svcTermCondSrcDescTxt_A.getValue();
        whereObj1[NUM_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        return whereList;
    }
}
