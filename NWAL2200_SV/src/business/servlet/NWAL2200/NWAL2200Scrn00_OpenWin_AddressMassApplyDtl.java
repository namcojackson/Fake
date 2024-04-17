/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0884E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_AddressMassApply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/29   CITS            S.Tanikawa      Create          Unit Test#202
 * 2017/09/07   Fujistu         R.Nakamura      Update          QC#20974
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_AddressMassApplyDtl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        int cnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).addrLbTxt_LC)) {
                cnt++;
            }
        }
        if (cnt <= 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).addrLbTxt_LC.setErrorInfo(1, NWAM0884E);
            }
        }
        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, null, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        int idx = 0;
        Object[] params = new Object[1];
        List<Object[]> itemList = new ArrayList<Object[]>();
        List<String> adGprList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).addrLbTxt_LC)) {
                if (adGprList.indexOf(scrnMsg.A.no(i).addrLbTxt_LC.getValue()) < 0) {
                    // Mod Start 2017/09/07 QC#20974
                    //Object[] items = new Object[6];
                    Object[] items = new Object[11];
                    // Mod End 2017/09/07 QC#20974
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(idx).xxPopPrm_0, scrnMsg.A.no(i).addrLbTxt_LC);
                    items[0] = scrnMsg.P.no(idx).xxPopPrm_0;
                    items[1] = scrnMsg.A.no(i).shipToLocNm_LC.getValue();
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(idx).xxPopPrm_1, scrnMsg.A.no(i).shipToCustAcctCd_LC);
                    items[2] = scrnMsg.P.no(idx).xxPopPrm_1;
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(idx).xxPopPrm_2, scrnMsg.A.no(i).shipToCustLocCd_LC);
                    items[3] = scrnMsg.P.no(idx).xxPopPrm_2;
                    items[4] = scrnMsg.A.no(i).shipToFirstLineAddr_LC.getValue();
                    items[5] = scrnMsg.A.no(i).shipToScdLineAddr_LC.getValue();
                    // Add Start 2017/09/07 QC#20974
                    items[6] = scrnMsg.A.no(i).shipToThirdLineAddr_LC.getValue();
                    items[7] = scrnMsg.A.no(i).shipToFrthLineAddr_LC.getValue();
                    items[8] = scrnMsg.A.no(i).shipToCtyAddr_LC.getValue();
                    items[9] = scrnMsg.A.no(i).shipToStCd_LC.getValue();
                    items[10] = scrnMsg.A.no(i).shipToPostCd_LC.getValue();
                    // Add End 2017/09/07 QC#20974

                    itemList.add(items);
                    adGprList.add(scrnMsg.A.no(i).addrLbTxt_LC.getValue());
                    idx++;
                }
            }
        }
        scrnMsg.P.setValidCount(idx);
        params[0] = itemList;
        setArgForSubScreen(params);
    }
}
