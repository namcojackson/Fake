/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLGL0040;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0040.constant.NLGL0040Constant;
import business.db.WMS_SHIP_VIA_RTE_MAPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040BL06 extends S21BusinessHandler implements NLGL0040Constant {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0040SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_CMN_Submit(cMsg, sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_CMN_DELETE.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_CMN_Delete(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NLGL0040Scrn00_CMN_Submit
     * <dd> submit button event
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;
        NLGL0040SMsg globalMsg = (NLGL0040SMsg) sMsg;
        WMS_SHIP_VIA_RTE_MAPTMsg editTMsg = new WMS_SHIP_VIA_RTE_MAPTMsg();

        if (ZYPCommonFunc.hasValue(globalMsg.wmsShipViaTpCd_D2)) {
            WMS_SHIP_VIA_RTE_MAPTMsg inTMsg = new WMS_SHIP_VIA_RTE_MAPTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.whCd, bizMsg.whCd_H1);
            ZYPEZDItemValueSetter.setValue(inTMsg.wmsShipViaTpCd, bizMsg.wmsShipViaTpCd_D2);
            editTMsg = (WMS_SHIP_VIA_RTE_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

            if (editTMsg == null) {
                bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP
                        , DB_GLBL_CMPY_CD + DB_WH_CD + DB_WMS_SHIP_VIA_CD//
                        , bizMsg.glblCmpyCd.getValue() + bizMsg.whCd_H1.getValue() + bizMsg.wmsShipViaTpCd_D2.getValue()});
                return;
            } else {

                if (!ZYPDateUtil.isSameTimeStamp(globalMsg.ezUpTime_D2.getValue()
                        , globalMsg.ezUpTimeZone_D2.getValue()//
                        , editTMsg.ezUpTime.getValue()//
                        , editTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP
                            , DB_GLBL_CMPY_CD + DB_WH_CD + DB_WMS_SHIP_VIA_CD//
                            , bizMsg.glblCmpyCd.getValue() + bizMsg.whCd_H1.getValue() + bizMsg.wmsShipViaTpCd_D2.getValue()});

                    return;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(editTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(editTMsg.wmsShipViaTpCd, bizMsg.wmsShipViaTpCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.whCd, bizMsg.whCd_H1);
        ZYPEZDItemValueSetter.setValue(editTMsg.rteGuideNum, bizMsg.rteGuideNum_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.wmsDescTxt, bizMsg.wmsDescTxt_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.mdBreakTpCd, bizMsg.mdBreakTpCd_P2);
        ZYPEZDItemValueSetter.setValue(editTMsg.pclCarrCd, bizMsg.pclCarrCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.ltlCarrCd, bizMsg.ltlCarrCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.tlCarrCd, bizMsg.tlCarrCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.pclMaxCapNum, bizMsg.pclMaxCapNum_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.ltlMaxCapNum, bizMsg.ltlMaxCapNum_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.tlMaxCapNum, bizMsg.tlMaxCapNum_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.pclPrtyCd, bizMsg.pclPrtyCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.ltlPrtyCd, bizMsg.ltlPrtyCd_D2);
        ZYPEZDItemValueSetter.setValue(editTMsg.tlPrtyCd, bizMsg.tlPrtyCd_D2);

        if (ZYPCommonFunc.hasValue(globalMsg.wmsShipViaTpCd_D2)) {
            EZDTBLAccessor.update(editTMsg);
        } else {
            EZDTBLAccessor.create(editTMsg);
        }

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(editTMsg.getReturnCode())) {
            bizMsg.wmsShipViaTpCd_D2.setErrorInfo(BIZ_ERR_CD, NLGM0050E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP//
                    , DB_GLBL_CMPY_CD + MSG_DELIMITER + DB_WH_CD + MSG_DELIMITER + DB_WMS_SHIP_VIA_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.whCd.getValue() + MSG_DELIMITER + editTMsg.wmsShipViaTpCd.getValue() });
            bizMsg.setMessageInfo(NLGM0050E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP//
                    , DB_GLBL_CMPY_CD + MSG_DELIMITER + DB_WH_CD + MSG_DELIMITER + DB_WMS_SHIP_VIA_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.whCd.getValue() + MSG_DELIMITER + editTMsg.wmsShipViaTpCd.getValue() });
            return;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(editTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP, DB_GLBL_CMPY_CD
                    + MSG_DELIMITER + DB_WH_CD + MSG_DELIMITER + DB_WMS_SHIP_VIA_CD//
                    , editTMsg.glblCmpyCd.getValue() + MSG_DELIMITER + editTMsg.whCd.getValue()//
                    + MSG_DELIMITER + editTMsg.wmsShipViaTpCd.getValue() });
            return;
        }

        if (ZYPCommonFunc.hasValue(globalMsg.wmsShipViaTpCd_D2)) {
            bizMsg.setMessageInfo(NLGM0002I, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP, MSG_SUCCESS_COUNT });
        } else {
            bizMsg.setMessageInfo(NLGM0001I, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP, MSG_SUCCESS_COUNT });
        }
    }

    /**
     * Method name: doProcess_NLGL0040Scrn00_CMN_Delete
     * <dd> F7:Delete button event
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;
        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, FIELD_NAME_XXCHKBOX_D1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NLGM0036E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.whCd_H1, bizMsg.whCd_B1);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_D1.getValue())) {
                WMS_SHIP_VIA_RTE_MAPTMsg inTMsg = new WMS_SHIP_VIA_RTE_MAPTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.whCd, bizMsg.whCd_H1);
                ZYPEZDItemValueSetter.setValue(inTMsg.wmsShipViaTpCd, bizMsg.A.no(i).wmsShipViaTpCd_D1);
                WMS_SHIP_VIA_RTE_MAPTMsg delTMsg = (WMS_SHIP_VIA_RTE_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

                if (delTMsg == null) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP
                            , DB_GLBL_CMPY_CD + DB_WH_CD + DB_WMS_SHIP_VIA_CD//
                            , bizMsg.glblCmpyCd.getValue() + bizMsg.whCd_H1.getValue() + bizMsg.wmsShipViaTpCd_D2.getValue()});
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_D1.getValue()
                        , bizMsg.A.no(i).ezUpTimeZone_D1.getValue(), delTMsg.ezUpTime.getValue()//
                        , delTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP
                            , DB_GLBL_CMPY_CD + DB_WH_CD + DB_WMS_SHIP_VIA_CD//
                            , bizMsg.glblCmpyCd.getValue() + bizMsg.whCd_H1.getValue() + bizMsg.wmsShipViaTpCd_D2.getValue()});
                    return;
                }
                EZDTBLAccessor.logicalRemove(delTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NLGM0048E, new String[] {TBL_WMS_SHIP_VIA_RTE_MAP
                            , DB_GLBL_CMPY_CD + DB_WH_CD + DB_WMS_SHIP_VIA_CD//
                            , bizMsg.glblCmpyCd.getValue() + bizMsg.whCd_H1.getValue() + bizMsg.wmsShipViaTpCd_D2.getValue()});
                    return;
                }
            }
        }
        bizMsg.setMessageInfo(NLGM0025I, new String[] {DELETE});
    }
}
