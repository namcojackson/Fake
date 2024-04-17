/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.BIZ_APP_ID;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1160.NPAL1160CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 01/11/2017   CITS            R Shimamoto     Update          QC#17059
 *</pre>
 */
public class NPAL1160_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

        NPAL1160CMsg bizMsg = new NPAL1160CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {

            NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
            NPAL1160CMsg bizMsg = (NPAL1160CMsg) cMsg;
            String scrEventNm = scrnMsg.xxScrEventNm_P1.getValue();

            int rowNum = getButtonSelectNumber();

            if ("OpenWin_Team".equals(scrEventNm)) {

                if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
                    if (rowNum >= 0) {
                        // detail click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).apvlTeamNm_B1, scrnMsg.P.no(0).xxComnScrColValTxt);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).apvlTeamPk_B1, bizMsg.apvlTeamPk_P1);
                        scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).apvlTeamPosnTpCd_BS);

                    } else {
                        // header click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apvlTeamNm_MT, scrnMsg.P.no(0).xxComnScrColValTxt);
                        scrnMsg.setFocusItem(scrnMsg.apvlTeamPosnTpCd_MS);
                    }

                } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
                    if (rowNum >= 0) {
                        // detail click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(rowNum).apvlTeamNm_C1, scrnMsg.P.no(0).xxComnScrColValTxt);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(rowNum).apvlTeamPk_C1, bizMsg.apvlTeamPk_P1);
                        scrnMsg.setFocusItem(scrnMsg.C.no(rowNum).prchGrpCd_CS);

                    } else {
                        // header click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apvlTeamNm_ST, scrnMsg.P.no(0).xxComnScrColValTxt);
                        scrnMsg.setFocusItem(scrnMsg.prchGrpCd_SS);
                    }

                } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
                    if (rowNum >= 0) {
                        // detail click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(rowNum).apvlTeamNm_D1, scrnMsg.P.no(0).xxComnScrColValTxt);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(rowNum).apvlTeamPk_D1, bizMsg.apvlTeamPk_P1);
                        scrnMsg.setFocusItem(scrnMsg.D.no(rowNum).rtlWhCd_D1);

                    } else {
                        // header click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apvlTeamNm_LT, scrnMsg.P.no(0).xxComnScrColValTxt);
                        scrnMsg.setFocusItem(scrnMsg.rtlWhNm_LT);
                    }

                }

            } else if ("OpenWin_Member".equals(scrEventNm)) {

                if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
                    if (rowNum >= 0) {
                        // detail click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).psnCd_B1, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).fullPsnNm_B1, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                        scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).fullPsnNm_B1);
                    } else {
                        // header click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_MT, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                        scrnMsg.setFocusItem(scrnMsg.fullPsnNm_MT);
                    }

                } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
                    if (rowNum >= 0) {
                        // detail click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(rowNum).psnCd_E1, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
                        ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(rowNum).fullPsnNm_E1, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                        scrnMsg.setFocusItem(scrnMsg.E.no(rowNum).fullPsnNm_E1);
                    } else {
                        // QC#17059 Mod.
                        // header click
                        ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_AT, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                        scrnMsg.setFocusItem(scrnMsg.fullPsnNm_AT);
                    }
                    
                }
            }

        }

    }
}
