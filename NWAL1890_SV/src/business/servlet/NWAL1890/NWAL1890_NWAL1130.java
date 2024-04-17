/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890;

import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_0;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_1;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.LINE_CONFIG_MODE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/08   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        if ("OpenWin_ConfigID".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxConfigIdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_Model".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxMdlSrchTxt);

        } else if ("OpenWin_BillToName".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctNmSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxBillToAcctNmSrchTxt);

        } else if ("OpenWin_BillToAcctNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt);

        } else if ("OpenWin_BillToLocNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxBillToLocCdSrchTxt);

        } else if ("OpenWin_ShipToName".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctNmSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxShipToAcctNmSrchTxt);

        } else if ("OpenWin_ShipToAcctNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxShipToAcctCdSrchTxt);

        } else if ("OpenWin_ShipToLocNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxShipToLocCdSrchTxt);

        } else if ("OpenWin_SoldToName".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctNmSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxSoldToAcctNmSrchTxt);

        } else if ("OpenWin_SoldToAcctNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxSoldToAcctCdSrchTxt);

        } else if ("OpenWin_SoldToLocNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxSoldToLocCdSrchTxt);

        } else if ("OpenWin_LineStatus".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineStsSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxLineStsSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineStsSrchTxt_R, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxLineStsSrchTxt_R);

            }
        } else if ("OpenWin_OrderedItem".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdItemSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxOrdItemSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdItemSrchTxt_R, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxOrdItemSrchTxt_R);
            }
        } else if ("OpenWin_ReturnReason".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnRsnSrchTxt_R, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxRtrnRsnSrchTxt_R);

        } else if ("OpenWin_WH".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxRtlWhSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt_R, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxRtlWhSrchTxt_R);
            }
        } else if ("OpenWin_SubWH".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxRtlSwhSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt_R, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxRtlSwhSrchTxt_R);
            }
        } else if ("OpenWin_SourceType".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxCpoSrcTpSrchTxt);

        } else if ("OpenWin_LineSourceRef".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxOrdSrcRefNumSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt_R, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxOrdSrcRefNumSrchTxt_R);
            }
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSbstItemSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.xxSbstItemSrchTxt);

        } else if ("OpenWin_SerialNumber".equals(scrEventNm)) {
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxSerNumSrchTxt);

            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt_R, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.xxSerNumSrchTxt_R);
            }
        }

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }
}
