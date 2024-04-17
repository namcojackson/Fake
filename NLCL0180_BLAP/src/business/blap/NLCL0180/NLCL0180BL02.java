/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NLCL0180;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NLCL0180.common.NLCL0180CommonLogic;
import business.blap.NLCL0180.constant.NLCL0180Constant;
import business.db.MDSETMsg;
import business.db.SPLY_VTMsg;
import business.db.SPLY_VTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao      Create          N/A
 * 12/17/2009   Fujitsu         M.Yamada        Update          2677
 * 01/27/2010   Fujitsu         M.Yamada        Update          Message ID Change
 * 05/22/2013   Fujitsu         T.Tozuka        Update          R-OM025 Inventory Transaction
 * </pre>
 */
public class NLCL0180BL02 extends S21BusinessHandler implements NLCL0180Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0180_INIT".equals(screenAplID)) {
                doProcess_NLCL0180_INIT(cMsg, sMsg);

            } else if ("NLCL0180Scrn00_Display_MDSEName".equals(screenAplID)) {
                doProcess_NLCL0180Scrn00_Display_MDSEName(cMsg, sMsg);

            } else if ("NLCL0180Scrn00_Display_VendorName".equals(screenAplID)) {
                doProcess_NLCL0180Scrn00_Display_VendorName(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0180_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180_INIT================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        String outGetGlobalCompanyCode = getGlobalCompanyCode();

        bizMsg.glblCmpyCd.setValue(outGetGlobalCompanyCode);

        String salesDate = ZYPDateUtil.getSalesDate();
        bizMsg.arvDt.setValue(salesDate);

        // 2013/05/22 R-OM025 Inventory Transaction Delete Start
//        getLocPulldown(bizMsg);
        // 2013/05/22 R-OM025 Inventory Transaction Delete End

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        // Set Location Role Type Code List
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_LI,
                NLCL0180CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));
        // 2013/05/22 R-OM025 Inventory Transaction Add End

        EZDDebugOutput.println(1, "doProcess_NLCL0180_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0180Scrn00_Display_MDSEName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Display_MDSEName================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        bizMsg.mdseNm.clear();

        MDSETMsg outGetMdseInfo = NLCL0180CommonLogic.getMdseInfo(bizMsg);

        if (outGetMdseInfo != null) {

            bizMsg.mdseNm.setValue(outGetMdseInfo.mdseNm.getValue());
            if (RGTN_STS.TERMINATED.equals(outGetMdseInfo.rgtnStsCd.getValue()) || RGTN_STS.PENDING_COMPLETION.equals(outGetMdseInfo.rgtnStsCd.getValue())) {

                bizMsg.mdseCd.setErrorInfo(1, "NLCM0065E", new String[] {MSG_ARG_MDSE_CD });

                return;

            }

        } else {

            bizMsg.mdseCd.setErrorInfo(1, "NLCM0002E");
        }

        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Display_MDSEName================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0180Scrn00_Display_VendorName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Display_VendorName================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        bizMsg.vndNm.clear();
        SPLY_VTMsg outGetVndInfo = getVndInfo(bizMsg);

        if (outGetVndInfo != null) {

            bizMsg.vndNm.setValue(outGetVndInfo.splyNm.getValue());
            if (RGTN_STS.TERMINATED.equals(outGetVndInfo.rgtnStsCd.getValue()) || RGTN_STS.PENDING_COMPLETION.equals(outGetVndInfo.rgtnStsCd.getValue())) {

                bizMsg.vndCd.setErrorInfo(1, "NLCM0065E", new String[] {MSG_ARG_VENDOR_CD });

                return;

            }

        } else {

            bizMsg.vndCd.setErrorInfo(1, "NLCM0066E", null);
        }

        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Display_VendorName================================END", this);
    }

    // 2013/05/22 R-OM025 Inventory Transaction Delete Start
//    /**
//     * <dd>The method explanation: The business peculiarity
//     * processing is executed.
//     * @param bizMsg Business Component Interface Message
//     * @return void
//     */
//    private void getLocPulldown(NLCL0180CMsg bizMsg) {
//
//        S21UserProfileService profileService = getUserProfileService();
//
//        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor("NLCL0180");
//
//        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(S21DataSecurityAttributeData.NAME_WAREHOUSE);
//
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//        String errMessageId = NLXInventoryLocation.exec(glblCmpyCd, dataSecurityAttrList, bizMsg.whCd_H1, bizMsg.xxLocTxt_H1);
//
//        if (errMessageId != null) {
//            bizMsg.setMessageInfo(errMessageId);
//        }
//    }
    // 2013/05/22 R-OM025 Inventory Transaction Delete End

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return SPLY_VTMsg
     */
    private SPLY_VTMsg getVndInfo(NLCL0180CMsg bizMsg) {

        SPLY_VTMsg inSplyVTMsg = new SPLY_VTMsg();

        SPLY_VTMsgArray outSplyVTMsg = NLCL0180CommonLogic.findVndListForRecChk(bizMsg, inSplyVTMsg);

        if (outSplyVTMsg.length() == 0) {
            return null;
        } else {
            return outSplyVTMsg.no(0);
        }
    }
}
