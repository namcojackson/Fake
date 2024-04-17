/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NLCL0180.common;

import java.util.List;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.blap.NLCL0180.constant.NLCL0180Constant;
import business.db.MDSETMsg;
import business.db.SPLY_VTMsg;
import business.db.SPLY_VTMsgArray;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao      Create          N/A
 * 12/17/2009   Fujitsu         M.Yamada        Update          2677
 * 05/22/2013   Fujitsu         T.Tozuka        Update          R-OM025 Inventory Transaction
 * </pre>
 */
public class NLCL0180CommonLogic implements NLCL0180Constant {

    /**
     * @param bizMsg NLCL0180CMsg
     * @param inMsg MDSETMsg
     * @return MDSETMsg
     */
    public static MDSETMsg findMdseInfoForRecChk(NLCL0180CMsg bizMsg, MDSETMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.mdseCd.setValue(bizMsg.mdseCd.getValue());

        MDSETMsg outMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * @param bizMsg NLCL0180CMsg
     * @param inMsg VNDTMsg
     * @return VNDTMsgArray
     */
    public static SPLY_VTMsgArray findVndListForRecChk(NLCL0180CMsg bizMsg, SPLY_VTMsg inMsg) {

        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("splyCd01", bizMsg.vndCd.getValue());

        inMsg.setMaxCount(1);
        inMsg.setSQLID("002");

        SPLY_VTMsgArray outMsg = (SPLY_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return MDSETMsg
     */
    public static MDSETMsg getMdseInfo(NLCL0180CMsg bizMsg) {

        MDSETMsg inMDSEMsg = new MDSETMsg();

        MDSETMsg outMDSEMsg = findMdseInfoForRecChk(bizMsg, inMDSEMsg);

        return outMDSEMsg;
    }

    // 2013/05/22 R-OM025 Inventory Transaction Add Start
    /**
     * Get Location Name
     * @param  bizMsg NLCL0180CMsg
     * @param  dataSecurityList Data Security List
     * @return Location Name
     */
    public static String getInvtyLocNm(NLCL0180CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList) {
        NMXC100001EnableWHData locInfo = getInvtyLocInfo(bizMsg, dataSecurityList);
        if (locInfo == null || ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            return null;
        }
        return locInfo.getInvtyLocNm();
    }

    /**
     * Get Location Information
     * @param  bizMsg NLCL0180CMsg
     * @param  dataSecurityList Data Security List
     * @return Location Information Bean
     */
    public static NMXC100001EnableWHData getInvtyLocInfo(NLCL0180CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList) {
        return NMXC100001EnableWH.checkEnableWH(bizMsg.glblCmpyCd.getValue(),
                                                    bizMsg.whCd_P1.getValue(),
                                                    BUSINESS_ID,
                                                    dataSecurityList,
                                                    ZYPConstant.FLG_ON_Y);
    }

    /**
     * Get Location Role Type(COMMA Format)
     * @param glblCmpyCd Global Company Code
     * @return Location Role Type Code List
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd) {
        return NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_ID);
    }
    // 2013/05/22 R-OM025 Inventory Transaction Add End
}
