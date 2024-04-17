/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.LINK_ADD_TO_CONTRACT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NWAL1130_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0655E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_TO_CONTR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Add_To_Contract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        if (scrnMsg.dsContrNum_AD.isInputProtected()) {
            scrnMsg.setMessageInfo(NSAM0655E, new String[] {LINK_ADD_TO_CONTRACT, "Contract#" });
            throw new EZDFieldErrorException();
        }

        scrnMsg.P.clear();

        int index = getButtonSelectNumber();

        String dsAcctNum = scrnMsg.sellToCustCd.getValue();
        String billToCustLocCd = scrnMsg.soldToCustLocCd.getValue();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        // make parameterList
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Contract Number", "DS_CONTR_NUM", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Contract Name", "DS_CONTR_NM", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Control Status", "DS_CONTR_CTRL_STS_CD", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj0 = {"Contract Pk", "DS_CONTR_PK", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj1 = {"Contract Number", "DS_CONTR_NUM", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Contract Name", "DS_CONTR_NM", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {"Control Status", "DS_CONTR_CTRL_STS_CD", new BigDecimal("10"), ZYPConstant.FLG_ON_Y };
        Object[] colObj4 = {"Control Status", "DS_CONTR_CTRL_STS_NM", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        colList.add(colObj0);
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"DS_CONTR_NUM", "ASC" };
        sortList.add(sortObj1);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_ADD_TO_CONTR);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = POP_UP_ADD_TO_CONTR;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = getQueryStr(dsAcctNum, billToCustLocCd);
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
     * @param dsAcctNum
     * @param billToCustLocCd
     * @return query String
     */
    private String getQueryStr(String dsAcctNum, String billToCustLocCd) {

        StringBuilder sbSelect = new StringBuilder();
        sbSelect.append("SELECT")//
                .append("          S.DS_CONTR_PK")//
                .append("        , C.DS_CONTR_NUM")//
                .append("        , C.DS_CONTR_NM")//
                .append("        , S.DS_CONTR_CTRL_STS_CD")//
                .append("        , S.DS_CONTR_CTRL_STS_NM")//
                .append("        , S.GLBL_CMPY_CD")//
                .append("        , S.EZCANCELFLAG")//
                .append("    FROM")//
                .append("        DS_CONTR_STS_V      S")//
                .append("        , DS_CONTR          C")//
                .append("        , DS_CONTR_CTRL_STS CS")//
                .append("    WHERE")//
                .append("        C.DS_CONTR_PK               = S.DS_CONTR_PK")//
                .append("        AND C.GLBL_CMPY_CD          = S.GLBL_CMPY_CD")//
                .append("        AND S.GLBL_CMPY_CD          = 'glblCmpyCd'")//
                .append("        AND S.EZCANCELFLAG          = '0'")//
//                .append("        -- AND S.CONTR_XXXXXX_FLG = 'Y'")//
                .append("        AND C.DS_ACCT_NUM           = 'dsAcctNum'")//
                .append("        AND CS.DS_CONTR_CTRL_STS_CD = S.DS_CONTR_CTRL_STS_CD");

        String query = sbSelect.toString();
        query = query.replaceFirst("glblCmpyCd", getGlobalCompanyCode());
        query = query.replaceFirst("dsAcctNum", dsAcctNum);

        return query;
    }

}
