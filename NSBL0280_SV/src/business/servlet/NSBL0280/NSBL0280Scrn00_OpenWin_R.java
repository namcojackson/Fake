/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0280;

import static business.servlet.NSBL0280.constant.NSBL0280Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0280.common.NSBL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280Scrn00_OpenWin_R extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        Object[] params = new Object[NWAL1130_PRM_LENGTH];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Resource Search Popup";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getSqlStr(scrnMsg);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getWhereList(scrnMsg);
        // 4 : Lv2 : Column List (List: Search result columns)
        List<Object[]> columnList = NSBL0280CommonLogic.getResourceTypeColumnList();
        params[i++] = columnList;
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = NSBL0280CommonLogic.getResourceTypeSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.C;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_R");
        setArgForSubScreen(params);

    }

    private String getSqlStr(NSBL0280BMsg scrnMsg) {

        return NSBL0280CommonLogic.getResourceTypeSql(scrnMsg);
    }

    private List<Object[]> getWhereList(NSBL0280BMsg scrnMsg) {

        int index = getButtonSelectNumber();
        return NSBL0280CommonLogic.getResourceTypeWhereList(scrnMsg, index);
    }
}
