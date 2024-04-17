/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 *</pre>
 */
public class NWAL2200_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_Salesrep".equals(scrEventNm)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepPsnNum, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd, scrnMsg.Z.no(5).xxComnScrColValTxt.getValue());
            return null;
        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlCd, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlDescTxt, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_Warehouse".equals(scrEventNm)) {

            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlWhCd_LL, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
                // Del Start 2016/08/31 QC#14020
                // ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhCd_LL, scrnMsg.Z.no(2).xxComnScrColValTxt.getValue());
                // ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL, scrnMsg.Z.no(3).xxComnScrColValTxt.getValue());
                // Del Start 2016/08/31 QC#14020
                return null;
            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlWhCd_RL, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            }

        // Add Start 2016/08/31 QC#14020
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhCd_LL, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            return null;
            // Add End 2016/08/31 QC#14020
        }

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_Warehouse".equals(scrEventNm) && TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
            return;
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).sbstMdseCd_LL);
            return;

        } else if ("OpenWin_OrderCategory".equals(scrEventNm)) {

            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
            NWAL2200CommonLogicForScreenFields.setProtectByOrdCatgBizCtx(this, scrnMsg); // 2018/01/23 QC#18798 Add
        } else if ("OnBlur_DeriveFromCategory".equals(scrEventNm)) {

            scrnMsg.setFocusItem(scrnMsg.dsOrdCatgDescTxt);
            NWAL2200CommonLogicForScreenFields.setProtectByOrdCatgBizCtx(this, scrnMsg); // 2018/01/23 QC#18798 Add
        } else if ("OpenWin_Salesrep".equals(scrEventNm)) {

            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {

            scrnMsg.setFocusItem(scrnMsg.carrSvcLvlDescTxt);
        } else if ("OpenWin_Warehouse".equals(scrEventNm)) {

            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
            } else {

                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
            }
            // Add Start 2016/08/31 QC#14020
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).serNum_LL);
            // Add End 2016/08/31 QC#14020
        }
    }
}
