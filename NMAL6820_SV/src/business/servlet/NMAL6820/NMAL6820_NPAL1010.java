/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION_SW;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_EMERGENCY_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_WH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;

import java.util.List;

import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;
import business.servlet.NMAL6820.constant.NMAL6820Constant;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Return Action from NPAL1010(Location Popup)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#2380
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 *</pre>
 */
public class NMAL6820_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.rtlWhCd_P1);

                NMAL6820CMsg bizMsg = new NMAL6820CMsg();
                bizMsg.setBusinessID(BIZ_APP_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;

            } else {

                return null;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION_SW)) {

            int eventRowIndex = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).prntVndNm_AS, scrnMsg.rtlWhNm_P1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).vndNm_AS, scrnMsg.rtlSwhNm_P1);

            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).prntVndNm_AS);
        } else if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW)) {

            int eventRowIndex = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).prntVndNm_AR, scrnMsg.rtlWhNm_P1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).vndNm_AR, scrnMsg.rtlSwhNm_P1);

            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).prntVndNm_AR);
        } else if (!NMAL6820Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

                if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {

                    EZDMsg.copy(bizMsg, null, scrnMsg, null);

                    // column and button input protection
                    NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);

                    // cursor focus
                    scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
                } else {

                    // to keep a wh code for search when it is already registered.
                    ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H1, scrnMsg.rtlWhCd_H1);

                    // clear html attribute
                    scrnMsg.clearAllGUIAttribute(SCRN_ID);

                    // column and button input protection
                    NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_UPDATE);

                    // cursor focus
                    scrnMsg.setFocusItem(scrnMsg.rtlWhNm_H1);
                }

            } else if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SD, scrnMsg.rtlWhNm_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SD, scrnMsg.rtlSwhNm_P1);

                scrnMsg.setFocusItem(scrnMsg.procrTpCd_S1);

            } else if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SR, scrnMsg.rtlWhNm_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SR, scrnMsg.rtlSwhNm_P1);

                scrnMsg.setFocusItem(scrnMsg.procrTpCd_R1);

            } else if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_AL6820SCRN00_OPEN_WIN_EMERGENCY_LOCATION)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SE, scrnMsg.rtlWhNm_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SE, scrnMsg.rtlSwhNm_P1);

                scrnMsg.setFocusItem(scrnMsg.procrTpCd_E1);
            } else {

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
            }
        }
    }
}
