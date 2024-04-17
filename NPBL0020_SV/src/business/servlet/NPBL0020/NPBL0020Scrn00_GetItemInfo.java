/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NAMM0027E;

import java.math.BigDecimal;

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
 * Function Name : GetItemInfo
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/03/2016   CITS            K.Ogino         Create          QC#12517
 * 06/11/2018   CITS            S.Katsuma       Update          QC#26193
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 *</pre>
 */
public class NPBL0020Scrn00_GetItemInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        int index = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).mdseCd_A1)) {
            scrnMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NAMM0027E, new String[] {DISPLAY_NM_MDSE_CD });
        }

        scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        int index = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(BigDecimal.valueOf(index));

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

        int index = scrnMsg.xxNum.getValueInt();
        scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A1);
        // 2018/12/12 QC#29456 Add Start
        scrnMsg.addCheckItem(scrnMsg.A.no(index).xxScrItem50Txt_A1);
        // 2018/12/12 QC#29456 Add End
        scrnMsg.putErrorScreen();

        // START 2018/06/11 S.Katsuma [QC#26193,ADD]
        NPBL0020CommonLogic.setCtrlScrnItemDispDetailTable(this, scrnMsg, true);
        // END 2018/06/11 S.Katsuma [QC#26193,ADD]

        // set focus
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).mdseCd_A1);

    }
}
