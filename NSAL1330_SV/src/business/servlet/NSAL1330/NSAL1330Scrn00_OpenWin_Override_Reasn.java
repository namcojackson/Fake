/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.LINK_MANUAL_OVERRIDE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NWAL1130_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0655E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_OVERRIDE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Override_Reasn extends S21CommonHandler {

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

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        if (scrnMsg.svcMemoRsnDescTxt.isInputProtected()) {
            scrnMsg.setMessageInfo(NSAM0655E, new String[] {LINK_MANUAL_OVERRIDE, LINK_MANUAL_OVERRIDE });
            throw new EZDFieldErrorException();
        }
        scrnMsg.P.clear();

        // make parameterList
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Reason Code", "SVC_MEMO_RSN_CD", "%", ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Reason Name", "SVC_MEMO_RSN_DESC_TXT", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Reason Code", "SVC_MEMO_RSN_CD", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Reason Name", "SVC_MEMO_RSN_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        colList.add(colObj1);
        colList.add(colObj2);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"SVC_MEMO_RSN_SORT_NUM", "ASC" };
        sortList.add(sortObj1);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_OVERRIDE);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = POP_UP_OVERRIDE;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = getQueryStr(SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        // 3 : Lv1 : Where List
        params[ixP++] = whereList;
        // 4 : Lv1 : Column List
        params[ixP++] = colList;
        // 5 : Lv1 : Sort Condition List
        params[ixP++] = sortList;
        // 6 : Output
        params[ixP++] = scrnMsg.P;

        setArgForSubScreen(params);

    }

    /**
     * @return query String
     */
    private String getQueryStr(String svcMemoTpCd) {

        StringBuilder sbSelect = new StringBuilder();
        sbSelect.append("SELECT")//
                .append("           R.GLBL_CMPY_CD")//
                .append("          ,R.SVC_MEMO_RSN_CD")//
                .append("          ,R.SVC_MEMO_RSN_DESC_TXT")//
                .append("          ,R.SVC_MEMO_RSN_SORT_NUM")//
                .append("          ,R.EZCANCELFLAG")//
                .append("    FROM")//
                .append("          SVC_MEMO_RSN R")//
                .append("    WHERE")//
                .append("          R.GLBL_CMPY_CD    = 'glblCmpyCd'")//
                .append("    AND   R.SVC_MEMO_TP_CD  = 'svcMemoTpCd'")//
                .append("    AND   R.EZCANCELFLAG    = '0'");

        String query = sbSelect.toString();
        query = query.replaceFirst("glblCmpyCd", getGlobalCompanyCode());
        query = query.replaceFirst("svcMemoTpCd", svcMemoTpCd);

        return query;
    }
}
