/**
 * <Pre>
 * 
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NLGL0050;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0050.common.NLGL0050CommonLogic;
import business.blap.NLGL0050.constant.NLGL0050Constant;
import business.db.WMS_SHIP_VIATMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 *</pre>
 */
public class NLGL0050BL06 extends S21BusinessHandler implements NLGL0050Constant {

    /**
     * this is a method of the execution after the SV
     * enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0050Scrn00_CMN_Submit(cMsg, sMsg);
            } else if (EVENT_NM_CMN_DELTE.equals(screenAplID)) {
                doProcess_NLGL0050Scrn00_CMN_Delete(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[F7:Delete] is generated.
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;
        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, FIELD_NAME_CHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        // zero
        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NLGM0036E);
            return;
        }

        NLGL0050CommonLogic.setSearchItemsOnEvent(bizMsg);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                WMS_SHIP_VIATMsg inTMsg = new WMS_SHIP_VIATMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.wmsCarrCd, bizMsg.A.no(i).wmsCarrCd_A1);
                ZYPEZDItemValueSetter.setValue(inTMsg.wmsOrgHostId,  bizMsg.wmsOrgHostId_P1.getValue());
                WMS_SHIP_VIATMsg delTMsg = (WMS_SHIP_VIATMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

                if (delTMsg == null) {
                    bizMsg.setMessageInfo(NLGM0044E, new String[] {TBL_WMS_SHIP_VIA//
                            , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                            , inTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + inTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + inTMsg.wmsCarrCd.getValue() });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), delTMsg.ezUpTime.getValue(), delTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA//
                            , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                            , inTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + inTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + inTMsg.wmsCarrCd.getValue() });
                    return;
                }
                EZDTBLAccessor.logicalRemove(delTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NLGM0048E);
                    return;
                }
            }
        }
        bizMsg.setMessageInfo(NLGM0025I, new String[] {DELETE });
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[F2:Submit] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;
        NLGL0050SMsg globalMsg = (NLGL0050SMsg) sMsg;
        WMS_SHIP_VIATMsg editTMsg = new WMS_SHIP_VIATMsg();
        WMS_SHIP_VIATMsg inTMsg = new WMS_SHIP_VIATMsg();

        if (ZYPCommonFunc.hasValue(globalMsg.wmsCarrCd_T2)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.wmsCarrCd, bizMsg.wmsCarrCd_T2);
            ZYPEZDItemValueSetter.setValue(inTMsg.wmsOrgHostId, bizMsg.wmsOrgHostId_P1.getValue());

            editTMsg = (WMS_SHIP_VIATMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

            if (editTMsg == null) {
                bizMsg.setMessageInfo(NLGM0044E, new String[] {TBL_WMS_SHIP_VIA//
                        , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                        , inTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + inTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + inTMsg.wmsCarrCd.getValue() });
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(globalMsg.ezUpTime_T2.getValue(), globalMsg.ezUpTimeZone_T2.getValue(), editTMsg.ezUpTime.getValue(), editTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA//
                            , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                            , inTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + inTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + inTMsg.wmsCarrCd.getValue() });
                    return;
                }
            }
        } else {
            // Insert
            ZYPEZDItemValueSetter.setValue(editTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(editTMsg.wmsCarrCd, bizMsg.wmsCarrCd_T2);
            ZYPEZDItemValueSetter.setValue(editTMsg.wmsOrgHostId, bizMsg.wmsOrgHostId_P1.getValue());
        }
        // Update
        ZYPEZDItemValueSetter.setValue(editTMsg.carrScacCd, bizMsg.carrScacCd_T2);
        ZYPEZDItemValueSetter.setValue(editTMsg.carrSvcTxt, bizMsg.carrSvcTxt_T2);
        ZYPEZDItemValueSetter.setValue(editTMsg.wmsTrnspTpCd, bizMsg.wmsTrnspTpCd_P2);
        ZYPEZDItemValueSetter.setValue(editTMsg.wmsDescTxt, bizMsg.wmsDescTxt_T2);
        ZYPEZDItemValueSetter.setValue(editTMsg.wmsCarrNm, bizMsg.wmsCarrNm_T2);
        ZYPEZDItemValueSetter.setValue(editTMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd_P2);
        ZYPEZDItemValueSetter.setValue(editTMsg.fuelUpchgTpCd, bizMsg.fuelUpchgTpCd_P2);
        ZYPEZDItemValueSetter.setValue(editTMsg.fuelUpchgAmt, bizMsg.fuelUpchgAmt_T2);
        ZYPEZDItemValueSetter.setValue(editTMsg.addlUpchgTpCd, bizMsg.addlUpchgTpCd_P2);
        ZYPEZDItemValueSetter.setValue(editTMsg.addlUpchgAmt, bizMsg.addlUpchgAmt_T2);

        if (ZYPCommonFunc.hasValue(globalMsg.wmsCarrCd_T2)) {
            EZDTBLAccessor.update(editTMsg);
        } else {
            EZDTBLAccessor.create(editTMsg);
        }

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(editTMsg.getReturnCode())) {
            bizMsg.wmsCarrCd_T2.setErrorInfo(BIZ_ERR_CD, NLGM0050E, new String[] {TBL_WMS_SHIP_VIA//
                    , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + editTMsg.wmsCarrCd.getValue() });
            bizMsg.setMessageInfo(NLGM0050E, new String[] {TBL_WMS_SHIP_VIA//
                    , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + editTMsg.wmsCarrCd.getValue() });
            return;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(editTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_WMS_SHIP_VIA//
                    , CN_GLBL_CMPY_CD + MSG_DELIMITER + CN_WMS_ORG_HOST_ID + MSG_DELIMITER + CN_WMS_CARR_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.wmsOrgHostId.getValue() + MSG_DELIMITER + editTMsg.wmsCarrCd.getValue() });
            return;
        }

        if (ZYPCommonFunc.hasValue(globalMsg.wmsCarrCd_T2)) {
            bizMsg.setMessageInfo(NLGM0002I, new String[] {TBL_WMS_SHIP_VIA, MSG_SUCCESS_COUNT });
        } else {
            bizMsg.setMessageInfo(NLGM0001I, new String[] {TBL_WMS_SHIP_VIA, MSG_SUCCESS_COUNT });
        }
    }
}
