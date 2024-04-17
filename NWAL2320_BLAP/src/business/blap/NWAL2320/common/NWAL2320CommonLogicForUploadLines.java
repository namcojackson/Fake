package business.blap.NWAL2320.common;

import java.util.LinkedHashMap;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.blap.NWAL2320.bean.NWAL2320_ImptConfigBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptDetailBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptHeaderBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptRtrnDetailBean;
import business.blap.NWAL2320.constant.NWAL2320Constant;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320CommonLogicForUploadLines {

    /**
     * insertImportTables
     * @param bizMsg NWAL2320CMsg
     * @param imptBeansMap LinkedHashMap<String, NWAL2320_ImptHeaderBean>
     * @return boolean
     */
    public static boolean insertImportTables(NWAL2320CMsg bizMsg, LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap) {

        boolean isSuccess = true;
        for (NWAL2320_ImptHeaderBean hdrBean : imptBeansMap.values()) {
            //******************************************************************
            // DS_IMPT_ORDTMsg
            //******************************************************************
            if (!insertTable(bizMsg, hdrBean.me)) {
                break;
            }

            //******************************************************************
            // DS_IMPT_ORD_CONFIGTMsg
            //******************************************************************
            if (!insertDS_IMPT_ORD_CONFIGTMsg(bizMsg, hdrBean)) {
                break;
            }

            //******************************************************************
            // DS_IMPT_ORD_DTLTMsg
            //******************************************************************
            if (!insertDS_IMPT_ORD_DTLTMsg(bizMsg, hdrBean)) {
                break;
            }

            //******************************************************************
            // DS_IMPT_ORD_RTRN_DTLTMsg
            //******************************************************************
            if (!insertDS_IMPT_ORD_RTRN_DTLTMsg(bizMsg, hdrBean)) {
                break;
            }
        }

        return isSuccess;
    }


    private static boolean insertDS_IMPT_ORD_CONFIGTMsg(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {

        int iCnt = 0;

        for (NWAL2320_ImptConfigBean configBean : hdrBean.configBeanMap.values()) {
            //******************************************************************
            // DS_IMPT_ORD_CONFIGTMsg
            //******************************************************************
            if (!insertTable(bizMsg, configBean.me)) {
                return false;
            }

            //******************************************************************
            // DS_IMPT_ORD_DELY_INFOTMsg
            //******************************************************************
            if (!insertTable(bizMsg, configBean.delyInfoTMsg)) {
                return false;
            }

            //******************************************************************
            // DS_IMPT_ORD_SLS_CRTMsg
            //******************************************************************
            // QC#10490
            if (!hdrBean.isUpldTpNew() || (hdrBean.isUpldTpNew() && iCnt == 0)) {
                if (!insertTable(bizMsg, configBean.slsCrTMsg)) {
                    return false;
                }
            }

            iCnt++;
        }

        return true;
    }

    private static boolean insertDS_IMPT_ORD_DTLTMsg(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {

        for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
            //******************************************************************
            // DS_IMPT_ORD_DTLTMsg
            //******************************************************************
            if (!insertTable(bizMsg, dtlBean.me)) {
                return false;
            }

            //******************************************************************
            // DS_IMPT_PRC_CALC_BASETMsg
            //******************************************************************
            for (DS_IMPT_PRC_CALC_BASETMsg prcCalcBaseTMsg : dtlBean.prcCalcBaseTMsgList)
            {
                if (!insertTable(bizMsg, prcCalcBaseTMsg)) {
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean insertDS_IMPT_ORD_RTRN_DTLTMsg(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {

        for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
            //******************************************************************
            // DS_IMPT_ORD_RTRN_DTLTMsg
            //******************************************************************
            if (!insertTable(bizMsg, rtnDtlBean.me)) {
                return false;
            }

            //******************************************************************
            // DS_IMPT_RTRN_PRC_CALCTMsg
            //******************************************************************
            for (DS_IMPT_RTRN_PRC_CALCTMsg rtnPrcCalcBaseTMsg : rtnDtlBean.rtnPrcCalcBaseTMsgList)
            {
                if (!insertTable(bizMsg, rtnPrcCalcBaseTMsg)) {
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean insertTable(NWAL2320CMsg bizMsg, EZDTMsg tMsg) {
        S21FastTBLAccessor.insert(tMsg);
        if (!S21StringUtil.isEquals(tMsg.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
            bizMsg.setMessageInfo(NWAL2320Constant.NWAM0728E, new String[] {tMsg.getTableName()});
            return false;
        }

        return true;
    }

}
