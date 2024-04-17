/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0671E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_ShipToChange
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_ShipToChange extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        if (selectIdx == -1) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
                scrnMsg.shipToCustCd.setErrorInfo(1, NWAM0671E, new String[] {"Ship To Location" });
                scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
            }

        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                EZDBStringItem shipToCustCd = scrnMsg.A.no(selectIdx).shipToCustLocCd_LC;
                if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
                    shipToCustCd.setErrorInfo(1, NWAM0671E, new String[] {"Ship To Location" });
                    scrnMsg.addCheckItem(shipToCustCd);
                }
            } else {
                EZDBStringItem shipToCustCd = scrnMsg.C.no(selectIdx).shipToCustLocCd_RC;
                if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
                    shipToCustCd.setErrorInfo(1, NWAM0671E, new String[] {"Ship To Location" });
                    scrnMsg.addCheckItem(shipToCustCd);
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (selectIdx == -1) {
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).shipToCustLocCd_LC);
            } else {
                scrnMsg.addCheckItem(scrnMsg.C.no(selectIdx).shipToCustLocCd_RC);
            }
        }
        scrnMsg.putErrorScreen();

        Object[] params = getParamNWAL0140(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Get Param NWAL0140
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL0140
     */
    public static Object[] getParamNWAL0140(NWAL2200BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, ZYPConstant.FLG_OFF_N);

        if (selectIdx == -1) {
            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
            paramList.add(scrnMsg.shipToCustCd);
            paramList.add(scrnMsg.shipToLocNm);
            paramList.add(scrnMsg.shipToAddlLocNm);
            paramList.add(scrnMsg.shipToFirstLineAddr);
            paramList.add(scrnMsg.shipToScdLineAddr);
            paramList.add(scrnMsg.shipToThirdLineAddr);
            paramList.add(scrnMsg.shipToFrthLineAddr);
            paramList.add(scrnMsg.shipTo01RefCmntTxt);
            paramList.add(scrnMsg.shipTo02RefCmntTxt);
            paramList.add(scrnMsg.shipToCtyAddr);
            paramList.add(scrnMsg.shipToStCd);
            paramList.add(scrnMsg.shipToPostCd);
            paramList.add(scrnMsg.shipToCtryCd);
            paramList.add(scrnMsg.shipToCntyNm);
            paramList.add(scrnMsg.dropShipFlg);
            paramList.add(scrnMsg.P.no(1).xxPopPrm);
            paramList.add(scrnMsg.billToCustCd);
            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
            paramList.add(scrnMsg.sellToCustCd);
            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
            paramList.add(scrnMsg.shipToProvNm);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                paramList.add(scrnMsg.A.no(selectIdx).dsOrdPosnNum_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCustLocCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToLocNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToAddlLocNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFirstLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToScdLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToThirdLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFrthLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFirstRefCmntTxt_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToScdRefCmntTxt_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCtyAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToStCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToPostCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCtryCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCntyNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).dropShipFlg_LC);
                paramList.add(scrnMsg.P.no(1).xxPopPrm);
                paramList.add(scrnMsg.A.no(selectIdx).billToCustLocCd_LC);
                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
                paramList.add(scrnMsg.A.no(selectIdx).shipToProvNm_LC);
            } else {
                paramList.add(scrnMsg.C.no(selectIdx).dsOrdPosnNum_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCustLocCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToLocNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToAddlLocNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFirstLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToScdLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToThirdLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFrthLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFirstRefCmntTxt_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToScdRefCmntTxt_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCtyAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToStCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToPostCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCtryCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCntyNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).dropShipFlg_RC);
                paramList.add(scrnMsg.P.no(1).xxPopPrm);
                paramList.add(scrnMsg.C.no(selectIdx).billToCustLocCd_RC);
                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
                paramList.add(scrnMsg.C.no(selectIdx).shipToProvNm_RC);
            }
        }

        return paramList.toArray(new EZDBItem[0]);
    }
}
