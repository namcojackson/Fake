/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

// import static
// business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0004E;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0029E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_CreateColumn
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_CreateColumn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {
            scrnMsg.xxNum.setErrorInfo(1, NWWM0004E, new String[] {scrnMsg.xxNum.getNameForMessage() });
        } else if (scrnMsg.B.length() < scrnMsg.xxNum.getValueInt()) {
            scrnMsg.xxNum.setErrorInfo(1, NWWM0029E, new String[] {String.valueOf(scrnMsg.B.length()) });
        }
        scrnMsg.addCheckItem(scrnMsg.xxNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        List<Integer> delClmnList = new ArrayList<Integer>();

        if (scrnMsg.xxNum.getValue().compareTo(BigDecimal.valueOf(scrnMsg.B.getValidCount())) < 0) {
            for (int i = scrnMsg.xxNum.getValueInt(); i < scrnMsg.B.getValidCount(); i++) {
                delClmnList.add(i);
            }
            ZYPTableUtil.deleteRows(scrnMsg.B, delClmnList);
        } else {
            scrnMsg.B.setValidCount(scrnMsg.xxNum.getValueInt());
            NWWL0020CommonLogic.setControlFieldsColumns(scrnMsg, false);
        }

        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(0).ntfyActDtlColSortNum_B0);
        } else {
            scrnMsg.setFocusItem(scrnMsg.xxNum);
        }
    }
}
