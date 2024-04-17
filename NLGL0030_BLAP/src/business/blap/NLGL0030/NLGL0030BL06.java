/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0030;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0030.constant.NLGL0030Constant;
import business.db.WMS_MDSE_LISTTMsg;

import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;

/**
 * <pre>
 * ForcedItem Master download
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            M.Shimamura       Create            MW Replace Initial
 *</pre>
 */
public class NLGL0030BL06 extends S21BusinessHandler implements NLGL0030Constant {

    /**
     * The method explanation: this is a method of the execution after
     * the SV enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0030_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0030Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: The event[SUBMIT] processing is
     * executed.
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NLGL0030Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0030CMsg bizMsg = (NLGL0030CMsg) cMsg;
        NLGL0030SMsg globalMsg = (NLGL0030SMsg) sMsg;

        // Registered Check
        WMS_MDSE_LISTTMsg chkTMsg = new WMS_MDSE_LISTTMsg();
        ZYPEZDItemValueSetter.setValue(chkTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(chkTMsg.whCd, bizMsg.whCd_P1);
        ZYPEZDItemValueSetter.setValue(chkTMsg.wmsMdseCd, bizMsg.mdseCd_H1);
        chkTMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(chkTMsg);

        if (chkTMsg == null) {

            // Add Data Set
            WMS_MDSE_LISTTMsg setTMsg = new WMS_MDSE_LISTTMsg();
            ZYPEZDItemValueSetter.setValue(setTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(setTMsg.whCd, bizMsg.whCd_P1);
            ZYPEZDItemValueSetter.setValue(setTMsg.wmsMdseCd, bizMsg.mdseCd_H1);
            ZYPEZDItemValueSetter.setValue(setTMsg.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.IN_COMPLETED);

            // create
            EZDTBLAccessor.create(setTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(setTMsg.getReturnCode())) {
                // error
                bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_WMS_MDSE_LIST //
                        , NLXCMsgHelper.toListedString(DB_GLBL_CMPY_CD, DB_WH_CD, DB_WMS_MDSE_CD, DB_WMS_MDSE_CD) //
                        , NLXCMsgHelper.toListedString(setTMsg.glblCmpyCd.getValue(), MSG_DELIMITER //
                                , setTMsg.whCd.getValue(), MSG_DELIMITER, setTMsg.wmsMdseCd.getValue()) });
                return;
            } else {
                bizMsg.setMessageInfo(NLGM0002I, new String[] {TBL_WMS_MDSE_LIST, MSG_SUCCESS_COUNT });
            }
        } else {
            // exclusive check
            if (!ZYPDateUtil.isSameTimeStamp(globalMsg.B.no(0).ezUpTime_D2.getValue(), globalMsg.B.no(0).ezUpTimeZone_D2.getValue() //
                    , chkTMsg.ezUpTime.getValue() //
                    , chkTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NLGM0048E //
                                , new String[] {TBL_WMS_MDSE_LIST //
                                , NLXCMsgHelper.toListedString(DB_GLBL_CMPY_CD, DB_WH_CD, DB_WMS_MDSE_CD, DB_WMS_MDSE_CD) //
                                , NLXCMsgHelper.toListedString(chkTMsg.glblCmpyCd.getValue(), MSG_DELIMITER //
                                , chkTMsg.whCd.getValue(), MSG_DELIMITER, chkTMsg.wmsMdseCd.getValue()) });
                return;
            }

            // set value
            ZYPEZDItemValueSetter.setValue(chkTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(chkTMsg.whCd, bizMsg.whCd_P1);
            ZYPEZDItemValueSetter.setValue(chkTMsg.wmsMdseCd, bizMsg.mdseCd_H1);
            ZYPEZDItemValueSetter.setValue(chkTMsg.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.IN_COMPLETED);

            // update
            EZDTBLAccessor.update(chkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(chkTMsg.getReturnCode())) {
                // error
                bizMsg.setMessageInfo(NLGM0008E //
                                , new String[] {TBL_WMS_MDSE_LIST //
                                , NLXCMsgHelper.toListedString(DB_GLBL_CMPY_CD, DB_WH_CD, DB_WMS_MDSE_CD, DB_WMS_MDSE_CD) //
                                , NLXCMsgHelper.toListedString(chkTMsg.glblCmpyCd.getValue(), MSG_DELIMITER //
                                , chkTMsg.whCd.getValue(), MSG_DELIMITER, chkTMsg.wmsMdseCd.getValue()) });
                return;
            } else {
                bizMsg.setMessageInfo(NLGM0001I, new String[] {TBL_WMS_MDSE_LIST, MSG_SUCCESS_COUNT });
            }
        }
    }
}
