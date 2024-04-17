/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUSINESS_ID;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.DISPLAY_MDSE_CD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.DISPLAY_SWH_CD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.DISPLAY_WH_CD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_ID_INIT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_ID_SEARCH;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1130.NPAL1130CMsg;
import business.servlet.NPAL1130.common.NPAL1130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/01   CITS            K.Masaki        Update          CSA Project
 * 2017/11/07   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */
public class NPAL1130_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // 0. Item Number
            if (params.length > 0) {
                scrnMsg.mdseCd.setValue(((EZDBStringItem) params[0]).getValue());
            }
            // 1. WH Code
            if (params.length > 1) {
                scrnMsg.rtlWhCd.setValue(((EZDBStringItem) params[1]).getValue());
            }
            // 2. Sub WH Code
            if (params.length > 2) {
                scrnMsg.rtlSwhCd.setValue(((EZDBStringItem) params[2]).getValue());
            }
        } else {
            // To process the menu transition.
            scrnMsg.mdseCd.clear();
            scrnMsg.rtlWhCd.clear();
            scrnMsg.rtlSwhCd.clear();
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        NPAL1130CMsg bizMsg = new NPAL1130CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        NPAL1130CMsg bizMsg = (NPAL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        if (scrnMsg.A.getValidCount() > 0) {
            NPAL1130CommonLogic.setScreenControl(EVENT_ID_SEARCH, this, scrnMsg);
        } else {
            NPAL1130CommonLogic.setScreenControl(EVENT_ID_INIT, this, scrnMsg);
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_MDSE_CD);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_WH_CD);
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_SWH_CD);
    }
}
