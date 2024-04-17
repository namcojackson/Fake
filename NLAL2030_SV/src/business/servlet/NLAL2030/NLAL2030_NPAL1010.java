/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030_NPAL1010
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Fujitsu         Y.Taoka         Create          N/A
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 02/07/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 03/06/2023   Hitachi         TZ.Win          Update          QC#61160
 *</pre>
 */
public class NLAL2030_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        if (!NLAL2030Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLAL2030Constant.EVENT_NM_OPENWIN_WH.equals(scrnMsg.xxMntEventNm.getValue()) || NLAL2030Constant.EVENT_NM_OPENWIN_SWH.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.P.no(9).xxPopPrm);

            } else if (NLAL2030Constant.EVENT_NM_OPENWIN_RCV_WH.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.whCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.whNm, scrnMsg.P.no(7).xxPopPrm);
            // START 2023/03/06 TZ.Win [QC#61160, MOD]
            } else if (NLAL2030Constant.EVENT_NM_OPENWIN_WH_APPLY.equals(scrnMsg.xxMntEventNm.getValue()) || NLAL2030Constant.EVENT_NM_OPENWIN_SWH_APPLY.equals(scrnMsg.xxMntEventNm.getValue())) {
            // END 2023/03/06 TZ.Win [QC#61160, MOD]    
                // QC#18461-Sol#085 Add Event.
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_AP, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_AP, scrnMsg.P.no(7).xxPopPrm);
                // START 2023/03/06 TZ.Win [QC#61160, ADD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_AP, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_AP, scrnMsg.P.no(9).xxPopPrm);
                // END 2023/03/06 TZ.Win [QC#61160, ADD]
            } else if (NLAL2030Constant.EVENT_NM_OPENWIN_WH_RWSLIST.equals(scrnMsg.xxMntEventNm.getValue())) {
                // QC#18461-Sol#085 Add Event.
                int index = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).rtlWhCd_B1, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).rtlWhNm_B1, scrnMsg.P.no(7).xxPopPrm);
            }
        }

        if (NLAL2030Constant.EVENT_NM_OPENWIN_WH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_SWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);

        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_SWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.whCd);

        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_WH_APPLY.equals(scrnMsg.xxMntEventNm.getValue())) {
            // QC#18461-Sol#085 Add Event.
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_AP);

        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_WH_RWSLIST.equals(scrnMsg.xxMntEventNm.getValue())) {
            // QC#18461-Sol#085 Add Event.
            int index = getButtonSelectNumber();
            scrnMsg.setFocusItem(scrnMsg.B.no(index).rtlWhCd_B1);
        // START 2023/03/06 TZ.Win [QC#61160, ADD]
        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_SWH_APPLY.equals(scrnMsg.xxMntEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_AP);

        // END 2023/03/06 TZ.Win [QC#61160, ADD]
        } else {

            scrnMsg.setFocusItem(scrnMsg.trxOrdNum);
        }
    }
}
