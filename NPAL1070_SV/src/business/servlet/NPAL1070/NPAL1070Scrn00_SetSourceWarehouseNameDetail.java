/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.ZZZM9007E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPAL1070Scrn00_SetSourceWarehouseNameDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1)) {
            scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1.clear();
            scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        int eventRowIndex = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg  = (NPAL1070CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int eventRowIndex = this.getButtonSelectNumber();

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1070CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        if (scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1.isError()) {
            scrnMsg.addCheckItem(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1);
        }

    }
}
