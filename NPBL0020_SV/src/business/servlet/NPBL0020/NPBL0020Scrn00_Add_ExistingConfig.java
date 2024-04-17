/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SVC_CONFIG_MSTR_PK;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.ZZZM9025E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Add Existing Config
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   CITS            Makoto Okigami  Create          N/A
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 07/29/2016   CITS            K.Ogino         Update          QC#8288
 *</pre>
 */
public class NPBL0020Scrn00_Add_ExistingConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SVC_CONFIG_MSTR_PK });
        }

        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.putErrorScreen();

        if (!NPBL0020CommonLogic.postInputCheckForAddLineAndConfig(scrnMsg, bizMsg)) {
            scrnMsg.putErrorScreen();
            return;
        }

        NPBL0020CommonLogic.setCtrlScrnItemDispDetailTable(this, scrnMsg, true);

    }
}
