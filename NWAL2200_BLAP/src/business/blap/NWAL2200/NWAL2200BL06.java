/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200;

import static business.blap.NWAL2200.constant.NWAL2200Constant.MAIL_FROM_REJECT;
import static business.blap.NWAL2200.constant.NWAL2200Constant.MAIL_TMPL_REJECT;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0181E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0268E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0729E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0730E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NZZM0003E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2200.common.NWAL2200CommonLogic;
import business.blap.NWAL2200.common.NWAL2200CommonLogicForCUSARetail;
import business.blap.NWAL2200.common.NWAL2200CommonLogicForLineControl;
import business.blap.NWAL2200.common.NWAL2200CommonLogicForOrderCreation;
import business.blap.NWAL2200.common.NWAL2200CommonLogicForPricing;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001DcWebService;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001SomWebService;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2200BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/07/13   Fujitsu         T.Ishii         Update          S21_NA#11428
 * 2016/07/15   Fujitsu         T.Ishii         Update          S21_NA#11561
 * 2016/08/31   CITS            S.Tanikawa      Update          Unit Test#202
 * 2016/10/05   Fujitsu         T.Ishii         Update          S21_NA#11595
 * 2016/10/27   SRAA            K.Aratani       Update          S21_NA#15638
 * 2016/11/25   Fujitsu         S.Ohki          Update          S21_NA#16066
 * 2016/11/29   Fujitsu         M.Yamada        Update          S21_NA#16266
 * 2017/01/17   Fujitsu         H.Nagashima     Update          QC#17124
 * 2017/02/10   Fujitsu         M.Ohno          Update          QC#17302
 * 2017/08/03   Fujitsu         A.Kosai         Update          QC#20391
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 * 2018/01/25   Fujitsu         Y.Kanefusa      Update          S21_NA#19808
 * 2018/03/13   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/07/23   Fujitsu         M.Ishii         Update          QC#24177
 * 2018/11/05   Fujitsu         Y.Kanefusa      Update          S21_NA#27364
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2200BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
            NWAL2200SMsg glblMsg = (NWAL2200SMsg) sMsg;

            if ("NWAL2200Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Reject".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Reject(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Approve(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Validate".equals(screenAplID)) {
                // 2018/07/23 QC#24177 Mod Start
//                doProcess_NWAL2200Scrn00_Validate(bizMsg, glblMsg);
                doProcess_NWAL2200Scrn00_Validate(bizMsg, glblMsg, false);
                // 2018/07/23 QC#24177 Mod End

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Reject Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Reject(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // 2018/07/23 QC#24177 Mod Start
//        if (!updateStatus(bizMsg, IMPT_STS.REJECT, null)) { // S21_NA#16066
        if (!updateStatus(bizMsg, IMPT_STS.REJECT, null, false)) {
        // 2018/07/23 QC#24177 Mod End
            return;
        }
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
            if (!updateStatusForConfig(bizMsg, IMPT_STS.REJECT)) {
                return;
            }
        }
        // 2018/01/23 QC#18798 Add End

        // UPDATE START 2016/09/14 Unit Test#202
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue())) {
            String messageId = NWXC412001SomWebService.reject(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), null);

            if (S21StringUtil.isNotEmpty(messageId)) {
                bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
                return;
            }
        }
        // UPDATE START 2016/09/14 Unit Test#202

        // QC#18548(L3)
        if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(bizMsg.cpoSrcTpCd.getValue())) {
            String messageId = NWXC412001DcWebService.reject(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue());

            if (S21StringUtil.isNotEmpty(messageId)) {
                bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
                return;
            }
        }

        // 2018/01/23 QC#18798 Add Start
//        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
//            String messageId = NWXC412001SomWebService.reject(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), null);
//
//            if (S21StringUtil.isNotEmpty(messageId)) {
//                bizMsg.setMessageInfo(messageId, new String[] {"OM Denied"});
//            }
//        }
        // 2018/01/23 QC#18798 Add End

        if (!postMailToSalesRep(bizMsg, glblMsg)) {
            return;
        }
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Save(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        
        // 2018/07/23 QC#24177 Add Start
        doProcess_NWAL2200Scrn00_Validate(bizMsg, glblMsg, true);
        // 2018/07/23 QC#24177 Add End

        // 2018/07/23 QC#24177 Del Start
////        if (!refreshInputData(bizMsg, glblMsg)) {   //QC#17124 mod
//        if (!NWAL2200CommonLogic.refreshInputData(bizMsg, glblMsg)) {
//            return;
//        }
//        if (!save(bizMsg, glblMsg)) {
//            return;
//        }
//        EZDConnectionMgr.getInstance().commit();
        // 2018/07/23 QC#24177 Del End
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Submit(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

//        if (!refreshInputData(bizMsg, glblMsg)) {   //QC#17124 mod
        if (!NWAL2200CommonLogic.refreshInputData(bizMsg, glblMsg)) {
            return;
        }
        if (!S21StringUtil.isEquals(glblMsg.slsRepPsnNum.getValue(), bizMsg.slsRepPsnNum.getValue()) || !S21StringUtil.isEquals(glblMsg.slsRepTocNm.getValue(), bizMsg.slsRepTocNm.getValue())) {
            // for edit able order type only
            if (!deriveSalesrep(bizMsg, glblMsg)) {
                return;
            }
        }

//        if (!deriveCustomerAccount(bizMsg, glblMsg)) {   //QC#17124 mod
        if (!NWAL2200CommonLogic.deriveCustomerAccount(bizMsg, glblMsg)) {
            return;
        }

        // for edit able order type only
        if (!deriveModifiedItems(bizMsg, glblMsg)) {
            return;
        }

        // 2017/02/09 S21_NA#17302 Mod Start
        List<DS_IMPT_ORD_ERRTMsg> errorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
        if (S21StringUtil.isEquals(bizMsg.cpoSrcTpCd.getValue(), CPO_SRC_TP.CUSA_NAD_OR_GMD)) {
            if (!NWAL2200CommonLogicForCUSARetail.deriveData(bizMsg, glblMsg, errorList)) {
                return;
            }
        }
        // 2017/02/09 S21_NA#17302 Mod End

        if (!save(bizMsg, glblMsg)) {
            return;
        }

        // 2017/02/09 S21_NA#17302 Add Start
        if (errorList.size() > 0) {
            if (!saveImptOrdErr(bizMsg, glblMsg, errorList)) {
                return;
            }
            EZDConnectionMgr.getInstance().commit();
            return;
        }
        // 2017/02/09 S21_NA#17302 Add End

        // Del Start 2018/03/13 QC#22967
        // Delete because of dead logic
//        if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("ASYNC_IMPORT_ORDER_CRAT_FLG", bizMsg.glblCmpyCd.getValue()), ZYPConstant.FLG_ON_Y)) { // FIXME:temporary
//
//            // FIXME: need to remove the following in the future
//            // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//            // DS check
//            List<DS_IMPT_ORD_ERRTMsg> errList = NWAL2200CommonLogicForDsCheck.dsCheck(bizMsg);
//
//            if (!saveImptOrdErr(bizMsg, glblMsg, errList)) {
//                return;
//            }
//
//            String imptSts;
//            String adminPsnCd = null; // S21_NA#16066
//            if (errList.size() > 0) {
//
//                imptSts = IMPT_STS.ERROR;
//            } else {
//
//                imptSts = IMPT_STS.ACCEPTED;
//                // 11/24/2016 S21_NA#16066 add Start
//                adminPsnCd = getContextUserInfo().getUserId();
//                // 11/24/2016 S21_NA#16066 add End
//            }
//            if (!updateStatus(bizMsg, imptSts, adminPsnCd)) { // S21_NA#16066
//                return;
//            }
//            // 2018/01/23 QC#18798 Add Start
//            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
//                if (!updateStatusForConfig(bizMsg, imptSts)) {
//                    return;
//                }
//            }
//            // 2018/01/23 QC#18798 Add End
//
//            // UPDATE START 2016/09/14 Unit Test#202
//            if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.ACCEPTED.equals(imptSts)) {
//
//                String messageId = NWXC412001SomWebService.approve(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), null);
//
//                if (S21StringUtil.isNotEmpty(messageId)) {
//                    bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
//                    return;
//                }
//            }
//            // UPDATE START 2016/09/14 Unit Test#202
//            // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//            // 2018/01/23 QC#18798 Add Start
////            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.ACCEPTED.equals(imptSts)) {
////
////                String messageId = NWXC412001SomWebService.approve(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), null);
////
////                if (S21StringUtil.isNotEmpty(messageId)) {
////                    bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
////                    return;
////                }
////            }
//            // 2018/01/23 QC#18798 Add End
//
//        } else {
            // Del End 2018/03/13 QC#22967

            // *****************************************
            // create order
            // *****************************************
            // 2017/08/03 S21_NA#20391 Mod Start
            // if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, bizMsg.adminPsnCd.getValue())) {
            String adminPsnCd;
            if (ZYPCommonFunc.hasValue(bizMsg.adminPsnCd.getValue())) {
                adminPsnCd = bizMsg.adminPsnCd.getValue();
            } else {
                adminPsnCd = getContextUserInfo().getUserId();
            }
            // 2018/07/23 QC#24177 Mod Start
//            if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, adminPsnCd)) {
            if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, adminPsnCd, false)) {
            // 2018/07/23 QC#24177 Mod End
            // 2017/08/03 S21_NA#20391 Mod End

                return;
            }
            // 2018/01/23 QC#18798 Add Start
            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
                if (!updateStatusForConfig(bizMsg, IMPT_STS.NOT_PROCESSED)) {
                    return;
                }
            }
            // 2018/01/23 QC#18798 Add End
            List<DS_IMPT_ORD_ERRTMsg> errList = NWAL2200CommonLogicForOrderCreation.createOrder(bizMsg);
            String imptSts;
            // 2017/08/03 S21_NA#20391 Mod Start
            // String adminPsnCd = null;
            adminPsnCd = null;
            // 2017/08/03 S21_NA#20391 Mod End
            String cpoOrdNum = null;
            if (errList.size() > 0) {

                imptSts = IMPT_STS.ERROR;

                // roll back order creation.
                // 2018/01/23 QC#18798 Mod Start
                //EZDConnectionMgr.getInstance().rollback();
                if (!CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
                    EZDConnectionMgr.getInstance().rollback();
                }
                // 2018/01/23 QC#18798 Mod End
            } else {

                imptSts = IMPT_STS.SUCCESS;
                adminPsnCd = getContextUserInfo().getUserId();
                cpoOrdNum = bizMsg.cpoOrdNum.getValue();
            }

            // *****************************************
            // register errors and update status.
            // *****************************************
            if (!saveImptOrdErr(bizMsg, glblMsg, errList)) {

                return;
            }
            // 2018/07/23 QC#24177 Mod Start
//            if (!updateStatus(bizMsg, imptSts, adminPsnCd, cpoOrdNum)) {
            if (!updateStatus(bizMsg, imptSts, adminPsnCd, cpoOrdNum, false)) {
            // 2018/07/23 QC#24177 Mod End

                return;
            }
            // 2018/01/23 QC#18798 Add Start
            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
                if (!updateStatusForConfig(bizMsg, null)) {
                    return;
                }
            }
            // 2018/01/23 QC#18798 Add End

            // *****************************************
            // order created notification for SOM
            // *****************************************
            if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.SUCCESS.equals(imptSts)) {

                String messageId = NWXC412001SomWebService.orderCreated(bizMsg.ordSrcRefNum.getValue(), cpoOrdNum, bizMsg.slsRepPsnNum.getValue(), null);

                if (S21StringUtil.isNotEmpty(messageId)) {

                    bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
                    return;
                }
            }

            // 2018/01/23 QC#18798 Add Start
            // *****************************************
            // order created notification for EOPS
            // *****************************************
//            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.SUCCESS.equals(imptSts)) {
//
//                String messageId = NWXC412001SomWebService.orderCreated(bizMsg.ordSrcRefNum.getValue(), cpoOrdNum, bizMsg.slsRepPsnNum.getValue(), null);
//
//                if (S21StringUtil.isNotEmpty(messageId)) {
//
//                    bizMsg.setMessageInfo(messageId, new String[] {cpoOrdNum });
//                    return;
//                }
//            }
            // 2018/01/23 QC#18798 Add End
        // Del Start 2018/03/13 QC#22967
        //}
        // Del End 2018/03/13 QC#22967

        EZDConnectionMgr.getInstance().commit();
    }

    /**
     * CMN_Approve Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    @Deprecated
    private void doProcess_NWAL2200Scrn00_CMN_Approve(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // *****************************************
        // refresh screen field value
        // *****************************************
//        if (!refreshInputData(bizMsg, glblMsg)) {
        if (!NWAL2200CommonLogic.refreshInputData(bizMsg, glblMsg)) {
            return;
        }

        if (!S21StringUtil.isEquals(glblMsg.slsRepPsnNum.getValue(), bizMsg.slsRepPsnNum.getValue()) || !S21StringUtil.isEquals(glblMsg.slsRepTocNm.getValue(), bizMsg.slsRepTocNm.getValue())) {
            // for edit able order type only
            if (!deriveSalesrep(bizMsg, glblMsg)) {
                return;
            }
        }

//        if (!deriveCustomerAccount(bizMsg, glblMsg)) {
        if (!NWAL2200CommonLogic.deriveCustomerAccount(bizMsg, glblMsg)) {
            return;
        }

        // for edit able order type only
        if (!deriveModifiedItems(bizMsg, glblMsg)) {
            return;
        }

        List<DS_IMPT_ORD_ERRTMsg> errorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
        if (S21StringUtil.isEquals(bizMsg.cpoSrcTpCd.getValue(), CPO_SRC_TP.CUSA_NAD_OR_GMD)) {
            if (!NWAL2200CommonLogicForCUSARetail.deriveData(bizMsg, glblMsg, errorList)) {
                return;
            }
        }

        if (!save(bizMsg, glblMsg)) {
            return;
        }

        if (errorList.size() > 0) {
            if (!saveImptOrdErr(bizMsg, glblMsg, errorList)) {
                return;
            }
            EZDConnectionMgr.getInstance().commit();
            return;
        }

        // commit screen data.
        EZDConnectionMgr.getInstance().commit();

        // *****************************************
        // create order
        // *****************************************
        if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, bizMsg.adminPsnCd.getValue(), false)) {

            return;
        }
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
            if (!updateStatusForConfig(bizMsg, IMPT_STS.NOT_PROCESSED)) {
                return;
            }
        }
        // 2018/01/23 QC#18798 Add End
        List<DS_IMPT_ORD_ERRTMsg> errList = NWAL2200CommonLogicForOrderCreation.createOrder(bizMsg);
        String imptSts;
        String adminPsnCd = null;
        String cpoOrdNum = null;
        if (errList.size() > 0) {

            imptSts = IMPT_STS.ERROR;

            // roll back order creation.
            EZDConnectionMgr.getInstance().rollback();
        } else {

            imptSts = IMPT_STS.SUCCESS;
            adminPsnCd = getContextUserInfo().getUserId();
            cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        }

        // *****************************************
        // register errors and update status.
        // *****************************************
        if (!saveImptOrdErr(bizMsg, glblMsg, errList)) {

            return;
        }
        if (!updateStatus(bizMsg, imptSts, adminPsnCd, cpoOrdNum, false)) {

            return;
        }
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
            if (!updateStatusForConfig(bizMsg, null)) {
                return;
            }
        }
        // 2018/01/23 QC#18798 Add End

        // *****************************************
        // order created notification for SOM
        // *****************************************
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.SUCCESS.equals(imptSts)) {

            String messageId = NWXC412001SomWebService.orderCreated(bizMsg.ordSrcRefNum.getValue(), cpoOrdNum, bizMsg.slsRepPsnNum.getValue(), null);

            if (S21StringUtil.isNotEmpty(messageId)) {

                if (S21StringUtil.isEquals(messageId, "NWAM4120E")) {

                    bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
                } else {

                    bizMsg.setMessageInfo(messageId, new String[] {cpoOrdNum });
                }
                return;
            }
        }

        // 2018/01/23 QC#18798 Add Start
        // *****************************************
        // order created notification for EOPS
        // *****************************************
//        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.SUCCESS.equals(imptSts)) {
//
//            String messageId = NWXC412001SomWebService.orderCreated(bizMsg.ordSrcRefNum.getValue(), cpoOrdNum, bizMsg.slsRepPsnNum.getValue(), null);
//
//            if (S21StringUtil.isNotEmpty(messageId)) {
//
//                if (S21StringUtil.isEquals(messageId, "NWAM4120E")) {
//
//                    bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
//                } else {
//
//                    bizMsg.setMessageInfo(messageId, new String[] {cpoOrdNum });
//                }
//                return;
//            }
//        }
        // 2018/01/23 QC#18798 Add End

        // commit all (if the transaction has error.)
        EZDConnectionMgr.getInstance().commit();
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    // 2018/07/23 QC#24177 Mod Start
//    private void doProcess_NWAL2200Scrn00_Validate(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
    private void doProcess_NWAL2200Scrn00_Validate(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, boolean isRegisterForSave) {
    // 2018/07/23 QC#24177 Mod End

//        if (!refreshInputData(bizMsg, glblMsg)) {   //QC#17124 mod
        if (!NWAL2200CommonLogic.refreshInputData(bizMsg, glblMsg)) {
            return;
        }
        if (!S21StringUtil.isEquals(glblMsg.slsRepPsnNum.getValue(), bizMsg.slsRepPsnNum.getValue()) || !S21StringUtil.isEquals(glblMsg.slsRepTocNm.getValue(), bizMsg.slsRepTocNm.getValue())) {
            // for edit able order type only
            if (!deriveSalesrep(bizMsg, glblMsg)) {
                return;
            }
        }

//        if (!deriveCustomerAccount(bizMsg, glblMsg)) {   //QC#17124 mod
        if (!NWAL2200CommonLogic.deriveCustomerAccount(bizMsg, glblMsg)) {
            return;
        }

        // for edit able order type only
        if (!deriveModifiedItems(bizMsg, glblMsg)) {
            return;
        }

        // 2017/02/09 S21_NA#17302 Mod Start
        List<DS_IMPT_ORD_ERRTMsg> errorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
        if (S21StringUtil.isEquals(bizMsg.cpoSrcTpCd.getValue(), CPO_SRC_TP.CUSA_NAD_OR_GMD)) {
            if (!NWAL2200CommonLogicForCUSARetail.deriveData(bizMsg, glblMsg, errorList)) {
                return;
            }
        }
        // 2017/02/09 S21_NA#17302 Mod End

        // 2023/11/06 QC#61924 Add Start
        if (!NWAL2200CommonLogic.hasDeactivateAccountOrLocation(bizMsg, glblMsg)) {
            return;
        }
        // 2023/11/06 QC#61924 Add End

        if (!save(bizMsg, glblMsg)) {
            return;
        }

        // 2017/02/09 S21_NA#17302 AddS Start
        if (errorList.size() > 0) {
            if (!saveImptOrdErr(bizMsg, glblMsg, errorList)) {
                return;
            }
            EZDConnectionMgr.getInstance().commit();
            return;
        }
        // 2017/02/09 S21_NA#17302 Add End

        // Del Start 2018/03/13 QC#22967
        // Delete because of dead logic
//        if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("ASYNC_IMPORT_ORDER_CRAT_FLG", bizMsg.glblCmpyCd.getValue()), ZYPConstant.FLG_ON_Y)) { // FIXME:temporary
//
//            // FIXME: need to remove the following in the future
//            // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//            if (!validate(bizMsg, glblMsg)) {
//                return;
//            }
//            // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//        } else {
            // Del End 2018/03/13 QC#22967

            // commit screen data.
            EZDConnectionMgr.getInstance().commit();

            // *****************************************
            // validate
            // *****************************************
            // 2018/07/23 QC#24177 Mod Start
//            if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, bizMsg.adminPsnCd.getValue())) {
            if (!updateStatus(bizMsg, IMPT_STS.NOT_PROCESSED, bizMsg.adminPsnCd.getValue(), false)) {    
            // 2018/07/23 QC#24177 Mod End

                return;
            }
            // 2018/01/23 QC#18798 Add Start
            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
                if (!updateStatusForConfig(bizMsg, IMPT_STS.NOT_PROCESSED)) {
                    return;
                }
            }
            // 2018/01/23 QC#18798 Add End
            List<DS_IMPT_ORD_ERRTMsg> errList = NWAL2200CommonLogicForOrderCreation.validate(bizMsg);
            // roll back for validation.
            EZDConnectionMgr.getInstance().rollback();

            String imptSts;
            String adminPsnCd = null;
            String cpoOrdNum = null;
            if (errList.size() > 0) {

                imptSts = IMPT_STS.ERROR;

            } else {

                imptSts = IMPT_STS.VALIDATED;
            }

            // *****************************************
            // register errors and update status.
            // *****************************************
            if (!saveImptOrdErr(bizMsg, glblMsg, errList)) {

                return;
            }
            // 2018/07/23 QC#24177 Mod Start
//            if (!updateStatus(bizMsg, imptSts, adminPsnCd, cpoOrdNum)) {
            if (!updateStatus(bizMsg, imptSts, adminPsnCd, cpoOrdNum, isRegisterForSave)) {
            // 2018/07/23 QC#24177 Mod End

                return;
            }
            // 2018/01/23 QC#18798 Add Start
            if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
                if (!updateStatusForConfig(bizMsg, null)) {
                    return;
                }
            }
            // 2018/01/23 QC#18798 Add End
        // Del Start 2018/03/13 QC#22967
        //}
        // Del End 2018/03/13 QC#22967
        EZDConnectionMgr.getInstance().commit();
    }

    // Del Start 2018/03/13 QC#22967
    // Delete because of dead logic
//    @Deprecated
//    private boolean validate(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
//
//        // DS check
//        List<DS_IMPT_ORD_ERRTMsg> errList = NWAL2200CommonLogicForDsCheck.dsCheck(bizMsg);
//
//        if (!saveImptOrdErr(bizMsg, glblMsg, errList)) {
//            return false;
//        }
//
//        String imptSts;
//        if (errList.size() > 0) {
//
//            imptSts = IMPT_STS.ERROR;
//        } else {
//
//            imptSts = IMPT_STS.VALIDATED;
//        }
//        if (!updateStatus(bizMsg, imptSts, null)) { // S21_NA#16066
//            return false;
//        }
//
//        return true;
//    }
    // Del End 2018/03/13 QC#22967

    private boolean save(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        if (!saveImptOrd(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdConfig(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdDtl(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdRtrnDtl(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdSlsCrHeader(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdSlsCrOutbound(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptOrdSlsCrInbound(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptPrcCalcBase(bizMsg, glblMsg)) {
            return false;
        }
        if (!saveImptRtrnPrcCalcBase(bizMsg, glblMsg)) {
            return false;
        }
        // QC#27364 2018/11/05 Add Start
        if (!saveImptSvcUsgPrc(bizMsg, glblMsg)) {
            return false;
        }
        // QC#27364 2018/11/05 Add End

        return true;
    }

    private boolean saveImptOrd(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, bizMsg.dsImptOrdPk);
        dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrd);
        if (dsImptOrd == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        String beforeEzUpTime = bizMsg.ezUpTime.getValue();
        String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = dsImptOrd.ezUpTime.getValue();
        String currentEzUpTimeZone = dsImptOrd.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        EZDMsg.copy(bizMsg, null, dsImptOrd, null);

        S21FastTBLAccessor.update(dsImptOrd);

        if (!S21StringUtil.isEquals(dsImptOrd.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
            bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrd.getTableName() });
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, dsImptOrd.ezUpTime);
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, dsImptOrd.ezUpTimeZone);
        return true;
    }
    // 2018/07/23 QC#24177 Mod Start
//    private boolean updateStatus(NWAL2200CMsg bizMsg, String imptSts, String adminPsnCd) {
//        return updateStatus(bizMsg, imptSts, adminPsnCd, null);
    private boolean updateStatus(NWAL2200CMsg bizMsg, String imptSts, String adminPsnCd, boolean isRegisterForSave) {
        return updateStatus(bizMsg, imptSts, adminPsnCd, null, isRegisterForSave);
    // 2018/07/23 QC#24177 Mod End
    }

    // 2018/07/23 QC#24177 Mod Start
//    private boolean updateStatus(NWAL2200CMsg bizMsg, String imptSts, String adminPsnCd, String cpoOrdNum) { // S21_NA#16066
        private boolean updateStatus(NWAL2200CMsg bizMsg, String imptSts, String adminPsnCd, String cpoOrdNum, boolean isRegisterForSave) {
    // 2018/07/23 QC#24177 Mod End

        DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, bizMsg.dsImptOrdPk);
        dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrd);
        if (dsImptOrd == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        String beforeEzUpTime = bizMsg.ezUpTime.getValue();
        String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = dsImptOrd.ezUpTime.getValue();
        String currentEzUpTimeZone = dsImptOrd.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        
        // 2018/07/23 QC#24177 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsImptOrd.imptStsCd, imptSts);
        if (!isRegisterForSave) {
            ZYPEZDItemValueSetter.setValue(dsImptOrd.imptStsCd, imptSts);
        }
        // 2018/07/23 QC#24177 Mod End
        
        
        // 11/24/2016 S21_NA#16066 add Start
        if (ZYPCommonFunc.hasValue(adminPsnCd) && !ZYPCommonFunc.hasValue(dsImptOrd.adminPsnCd)) {
            ZYPEZDItemValueSetter.setValue(dsImptOrd.adminPsnCd, adminPsnCd);
        }
        // 11/24/2016 S21_NA#16066 add End
        ZYPEZDItemValueSetter.setValue(dsImptOrd.cpoOrdNum, cpoOrdNum);

        S21FastTBLAccessor.update(dsImptOrd);

        if (!S21StringUtil.isEquals(dsImptOrd.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
            bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrd.getTableName() });
            return false;
        }
        return true;
    }

    // 2018/01/23 QC#18798 Add Start
    private boolean updateStatusForConfig(NWAL2200CMsg bizMsg, String imptStsCd) { // S21_NA#16066

        List<DS_IMPT_ORD_CONFIGTMsg> dsImptOrdConfigList = new ArrayList<DS_IMPT_ORD_CONFIGTMsg>();
        // Line Config
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdConfigPk, bizMsg.A.no(i).dsImptOrdConfigPk_LC);
            dsImptOrdConfig = (DS_IMPT_ORD_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdConfig);
            if (dsImptOrdConfig == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String emsdImptStsCd = dsImptOrdConfig.emsdImptStsCd.getValue();
            if (emsdImptStsCd != null) {
                if (IMPT_STS.SUCCESS.equals(emsdImptStsCd) || IMPT_STS.REJECT.equals(emsdImptStsCd)) {
                    continue;
                }
            }

            String beforeEzUpTime = bizMsg.ezUpTime.getValue();
            String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();
            String currentEzUpTime = dsImptOrdConfig.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdConfig.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            if (imptStsCd != null) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.emsdImptStsCd, imptStsCd);
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).emsdImptStsCd_LC)) {
                    ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.emsdImptStsCd, bizMsg.A.no(i).emsdImptStsCd_LC);
                }
            }

            dsImptOrdConfigList.add(dsImptOrdConfig);
        }

        // RMA
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdConfigPk, bizMsg.C.no(i).dsImptOrdConfigPk_RC);
            dsImptOrdConfig = (DS_IMPT_ORD_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdConfig);
            if (dsImptOrdConfig == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = bizMsg.ezUpTime.getValue();
            String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();
            String currentEzUpTime = dsImptOrdConfig.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdConfig.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            if (imptStsCd != null) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.emsdImptStsCd, imptStsCd);
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).emsdImptStsCd_RC)) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.emsdImptStsCd, bizMsg.C.no(i).emsdImptStsCd_RC);
                }
            }

            dsImptOrdConfigList.add(dsImptOrdConfig);
        }

        if (dsImptOrdConfigList.size() > 0) {
            int count = S21FastTBLAccessor.update((DS_IMPT_ORD_CONFIGTMsg[]) dsImptOrdConfigList.toArray(new DS_IMPT_ORD_CONFIGTMsg[dsImptOrdConfigList.size()]));

            if (dsImptOrdConfigList.size() != count) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {"DS_IMPT_ORD_CONFIG" });
                return false;
            }
        }
        return true;
    }
    // 2018/01/23 QC#18798 Add End

    private boolean saveImptOrdConfig(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NWAL2200_ASMsg config = glblMsg.A.no(i);

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdConfigPk, config.dsImptOrdConfigPk_LC);
            dsImptOrdConfig = (DS_IMPT_ORD_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdConfig);
            if (dsImptOrdConfig == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = config.ezUpTime_LC.getValue();
            String beforeEzUpTimeZone = config.ezUpTimeZone_LC.getValue();
            String currentEzUpTime = dsImptOrdConfig.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdConfig.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDMsg.copy(config, "LC", dsImptOrdConfig, "");

            S21FastTBLAccessor.update(dsImptOrdConfig);

            if (!S21StringUtil.isEquals(dsImptOrdConfig.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdConfig.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

            NWAL2200_CSMsg config = glblMsg.C.no(i);

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdConfigPk, config.dsImptOrdConfigPk_RC);
            dsImptOrdConfig = (DS_IMPT_ORD_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdConfig);
            if (dsImptOrdConfig == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = config.ezUpTime_RC.getValue();
            String beforeEzUpTimeZone = config.ezUpTimeZone_RC.getValue();
            String currentEzUpTime = dsImptOrdConfig.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdConfig.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDMsg.copy(config, "RC", dsImptOrdConfig, "");

            S21FastTBLAccessor.update(dsImptOrdConfig);

            if (!S21StringUtil.isEquals(dsImptOrdConfig.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdConfig.getTableName() });
                return false;
            }
        }

        return true;
    }

    private boolean saveImptOrdDtl(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NWAL2200_BSMsg line = glblMsg.B.no(i);

            if (ZYPCommonFunc.hasValue(line.dsImptOrdDtlPk_LL)) {

                // update

                DS_IMPT_ORD_DTLTMsg dsImptOrdDtl = new DS_IMPT_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.dsImptOrdDtlPk, line.dsImptOrdDtlPk_LL);
                dsImptOrdDtl = (DS_IMPT_ORD_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdDtl);
                if (dsImptOrdDtl == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = line.ezUpTime_LL.getValue();
                String beforeEzUpTimeZone = line.ezUpTimeZone_LL.getValue();
                String currentEzUpTime = dsImptOrdDtl.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptOrdDtl.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(line, "LL", dsImptOrdDtl, "");

                S21FastTBLAccessor.update(dsImptOrdDtl);

                if (!S21StringUtil.isEquals(dsImptOrdDtl.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdDtl.getTableName() });
                    return false;
                }
            } else {

                // create

                DS_IMPT_ORD_DTLTMsg dsImptOrdDtl = new DS_IMPT_ORD_DTLTMsg();

                EZDMsg.copy(line, "LL", dsImptOrdDtl, "");

                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.preExistFlg, ZYPConstant.FLG_OFF_N); // QC#16266
                ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.finItemLineFlg, ZYPConstant.FLG_OFF_N); // QC#16266
                EZDTBLAccessor.create(dsImptOrdDtl);

                if (!S21StringUtil.isEquals(dsImptOrdDtl.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdDtl.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean saveImptOrdRtrnDtl(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

            NWAL2200_DSMsg line = glblMsg.D.no(i);

            if (ZYPCommonFunc.hasValue(line.dsImptOrdRtrnDtlPk_RL)) {

                // update

                DS_IMPT_ORD_RTRN_DTLTMsg dsImptOrdRtrnDtl = new DS_IMPT_ORD_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.dsImptOrdRtrnDtlPk, line.dsImptOrdRtrnDtlPk_RL);
                dsImptOrdRtrnDtl = (DS_IMPT_ORD_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdRtrnDtl);
                if (dsImptOrdRtrnDtl == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = line.ezUpTime_RL.getValue();
                String beforeEzUpTimeZone = line.ezUpTimeZone_RL.getValue();
                String currentEzUpTime = dsImptOrdRtrnDtl.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptOrdRtrnDtl.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(line, "RL", dsImptOrdRtrnDtl, "");

                S21FastTBLAccessor.update(dsImptOrdRtrnDtl);

                if (!S21StringUtil.isEquals(dsImptOrdRtrnDtl.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdRtrnDtl.getTableName() });
                    return false;
                }
            } else {

                // create

                DS_IMPT_ORD_RTRN_DTLTMsg dsImptOrdRtrnDtl = new DS_IMPT_ORD_RTRN_DTLTMsg();

                EZDMsg.copy(line, "RL", dsImptOrdRtrnDtl, "");

                ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.dsImptOrdRtrnDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_RTRN_DTL_SQ));
                EZDTBLAccessor.create(dsImptOrdRtrnDtl);

                if (!S21StringUtil.isEquals(dsImptOrdRtrnDtl.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdRtrnDtl.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean saveImptOrdSlsCrHeader(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // find deleted data.
        List<NWAL2200_FSMsg> deleteList = new ArrayList<NWAL2200_FSMsg>();
        for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
            NWAL2200_FSMsg deleteSlsCr = glblMsg.F.no(i);
            boolean found = false;
            for (int j = 0; j < bizMsg.F.getValidCount(); j++) {
                NWAL2200_FCMsg slsCr = bizMsg.F.no(i);
                if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_FS)) {
                    if (NWAL2200CommonLogic.compareBigDecimal(deleteSlsCr.dsImptOrdSlsCrPk_FS.getValue(), slsCr.dsImptOrdSlsCrPk_FS.getValue()) == 0) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                deleteList.add(deleteSlsCr);
            }
        }

        for (NWAL2200_FSMsg slsCr : deleteList) {

            // delete

            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_FS);
            dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
            if (dsImptOrdSlsCr == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = slsCr.ezUpTime_FS.getValue();
            String beforeEzUpTimeZone = slsCr.ezUpTimeZone_FS.getValue();
            String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptOrdSlsCr);

            if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {

            NWAL2200_FCMsg slsCr = bizMsg.F.no(i);

            if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_FS)) {

                // update

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_FS);
                dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
                if (dsImptOrdSlsCr == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = slsCr.ezUpTime_FS.getValue();
                String beforeEzUpTimeZone = slsCr.ezUpTimeZone_FS.getValue();
                String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(slsCr, "FS", dsImptOrdSlsCr, "");

                S21FastTBLAccessor.update(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            } else {

                // create

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();

                EZDMsg.copy(slsCr, "FS", dsImptOrdSlsCr, "");

                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ));
                EZDTBLAccessor.create(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean saveImptOrdSlsCrOutbound(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // find deleted data.
        List<NWAL2200_GSMsg> deleteList = new ArrayList<NWAL2200_GSMsg>();
        for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
            NWAL2200_GSMsg deleteSlsCr = glblMsg.G.no(i);
            boolean found = false;
            for (int j = 0; j < bizMsg.G.getValidCount(); j++) {
                NWAL2200_GCMsg slsCr = bizMsg.G.no(i);
                if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_GS)) {
                    if (NWAL2200CommonLogic.compareBigDecimal(deleteSlsCr.dsImptOrdSlsCrPk_GS.getValue(), slsCr.dsImptOrdSlsCrPk_GS.getValue()) == 0) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                deleteList.add(deleteSlsCr);
            }
        }

        for (NWAL2200_GSMsg slsCr : deleteList) {

            // delete

            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_GS);
            dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
            if (dsImptOrdSlsCr == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = slsCr.ezUpTime_GS.getValue();
            String beforeEzUpTimeZone = slsCr.ezUpTimeZone_GS.getValue();
            String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptOrdSlsCr);

            if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {

            NWAL2200_GCMsg slsCr = bizMsg.G.no(i);

            if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_GS)) {

                // update

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_GS);
                dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
                if (dsImptOrdSlsCr == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = slsCr.ezUpTime_GS.getValue();
                String beforeEzUpTimeZone = slsCr.ezUpTimeZone_GS.getValue();
                String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(slsCr, "GS", dsImptOrdSlsCr, "");

                S21FastTBLAccessor.update(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            } else {

                // create

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();

                EZDMsg.copy(slsCr, "GS", dsImptOrdSlsCr, "");

                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ));
                EZDTBLAccessor.create(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean saveImptOrdSlsCrInbound(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // find deleted data.
        List<NWAL2200_HSMsg> deleteList = new ArrayList<NWAL2200_HSMsg>();
        for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
            NWAL2200_HSMsg deleteSlsCr = glblMsg.H.no(i);
            boolean found = false;
            for (int j = 0; j < bizMsg.H.getValidCount(); j++) {
                NWAL2200_HCMsg slsCr = bizMsg.H.no(i);
                if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_HS)) {
                    if (NWAL2200CommonLogic.compareBigDecimal(deleteSlsCr.dsImptOrdSlsCrPk_HS.getValue(), slsCr.dsImptOrdSlsCrPk_HS.getValue()) == 0) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                deleteList.add(deleteSlsCr);
            }
        }

        for (NWAL2200_HSMsg slsCr : deleteList) {

            // delete

            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_HS);
            dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
            if (dsImptOrdSlsCr == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = slsCr.ezUpTime_HS.getValue();
            String beforeEzUpTimeZone = slsCr.ezUpTimeZone_HS.getValue();
            String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptOrdSlsCr);

            if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {

            NWAL2200_HCMsg slsCr = bizMsg.H.no(i);

            if (ZYPCommonFunc.hasValue(slsCr.dsImptOrdSlsCrPk_HS)) {

                // update

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, slsCr.dsImptOrdSlsCrPk_HS);
                dsImptOrdSlsCr = (DS_IMPT_ORD_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSlsCr);
                if (dsImptOrdSlsCr == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = slsCr.ezUpTime_HS.getValue();
                String beforeEzUpTimeZone = slsCr.ezUpTimeZone_HS.getValue();
                String currentEzUpTime = dsImptOrdSlsCr.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptOrdSlsCr.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(slsCr, "HS", dsImptOrdSlsCr, "");

                S21FastTBLAccessor.update(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            } else {

                // create

                DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();

                EZDMsg.copy(slsCr, "HS", dsImptOrdSlsCr, "");

                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ));
                EZDTBLAccessor.create(dsImptOrdSlsCr);

                if (!S21StringUtil.isEquals(dsImptOrdSlsCr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdSlsCr.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean saveImptPrcCalcBase(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // find deleted data.
        List<NWAL2200_ISMsg> deleteList = new ArrayList<NWAL2200_ISMsg>();
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL2200_ISMsg deleteCalcBase = glblMsg.I.no(i);
            if (ZYPCommonFunc.hasValue(deleteCalcBase.dsImptPrcCalcBasePk_LP)) {
                boolean found = false;
                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    NWAL2200_ISMsg calcBase = glblMsg.I.no(i);
                    if (NWAL2200CommonLogic.compareBigDecimal(deleteCalcBase.dsImptPrcCalcBasePk_LP.getValue(), calcBase.dsImptPrcCalcBasePk_LP.getValue()) == 0) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    deleteList.add(deleteCalcBase);
                }
            }
        }

        for (NWAL2200_ISMsg calcBase : deleteList) {

            // delete

            DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptPrcCalcBasePk, calcBase.dsImptPrcCalcBasePk_LP);
            dsImptPrcCalcBase = (DS_IMPT_PRC_CALC_BASETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptPrcCalcBase);
            if (dsImptPrcCalcBase == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = calcBase.ezUpTime_LP.getValue();
            String beforeEzUpTimeZone = calcBase.ezUpTimeZone_LP.getValue();
            String currentEzUpTime = dsImptPrcCalcBase.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptPrcCalcBase.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptPrcCalcBase);

            if (!S21StringUtil.isEquals(dsImptPrcCalcBase.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptPrcCalcBase.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {

            NWAL2200_ISMsg calcBase = glblMsg.I.no(i);

            if (ZYPCommonFunc.hasValue(calcBase.dsImptPrcCalcBasePk_LP)) {

                // update

                DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptPrcCalcBasePk, calcBase.dsImptPrcCalcBasePk_LP);
                dsImptPrcCalcBase = (DS_IMPT_PRC_CALC_BASETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptPrcCalcBase);
                if (dsImptPrcCalcBase == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = calcBase.ezUpTime_LP.getValue();
                String beforeEzUpTimeZone = calcBase.ezUpTimeZone_LP.getValue();
                String currentEzUpTime = dsImptPrcCalcBase.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptPrcCalcBase.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(calcBase, "LP", dsImptPrcCalcBase, "");

                S21FastTBLAccessor.update(dsImptPrcCalcBase);

                if (!S21StringUtil.isEquals(dsImptPrcCalcBase.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptPrcCalcBase.getTableName() });
                    return false;
                }
            } else {
                if (!ZYPCommonFunc.hasValue(calcBase.dsImptRtrnPrcCalcPk_LP)) {
                    // create

                    DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();

                    EZDMsg.copy(calcBase, "LP", dsImptPrcCalcBase, "");

                    ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptPrcCalcBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_PRC_CALC_BASE_SQ));
                    EZDTBLAccessor.create(dsImptPrcCalcBase);

                    if (!S21StringUtil.isEquals(dsImptPrcCalcBase.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                        bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptPrcCalcBase.getTableName() });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean saveImptRtrnPrcCalcBase(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // find deleted data.
        List<NWAL2200_ISMsg> deleteList = new ArrayList<NWAL2200_ISMsg>();
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL2200_ISMsg deleteCalcBase = glblMsg.I.no(i);
            if (ZYPCommonFunc.hasValue(deleteCalcBase.dsImptRtrnPrcCalcPk_LP)) {
                boolean found = false;
                for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                    NWAL2200_ISMsg calcBase = glblMsg.I.no(i);
                    if (NWAL2200CommonLogic.compareBigDecimal(deleteCalcBase.dsImptRtrnPrcCalcPk_LP.getValue(), calcBase.dsImptRtrnPrcCalcPk_LP.getValue()) == 0) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    deleteList.add(deleteCalcBase);
                }
            }
        }

        for (NWAL2200_ISMsg calcBase : deleteList) {

            // delete

            DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptRtrnPrcCalcPk, calcBase.dsImptRtrnPrcCalcPk_LP);
            dsImptRtrnPrcCalc = (DS_IMPT_RTRN_PRC_CALCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptRtrnPrcCalc);
            if (dsImptRtrnPrcCalc == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = calcBase.ezUpTime_LP.getValue();
            String beforeEzUpTimeZone = calcBase.ezUpTimeZone_LP.getValue();
            String currentEzUpTime = dsImptRtrnPrcCalc.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptRtrnPrcCalc.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptRtrnPrcCalc);

            if (!S21StringUtil.isEquals(dsImptRtrnPrcCalc.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptRtrnPrcCalc.getTableName() });
                return false;
            }
        }

        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {

            NWAL2200_ISMsg calcBase = glblMsg.I.no(i);

            if (ZYPCommonFunc.hasValue(calcBase.dsImptRtrnPrcCalcPk_LP)) {

                // update

                DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptRtrnPrcCalcPk, calcBase.dsImptRtrnPrcCalcPk_LP);
                dsImptRtrnPrcCalc = (DS_IMPT_RTRN_PRC_CALCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptRtrnPrcCalc);
                if (dsImptRtrnPrcCalc == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                String beforeEzUpTime = calcBase.ezUpTime_LP.getValue();
                String beforeEzUpTimeZone = calcBase.ezUpTimeZone_LP.getValue();
                String currentEzUpTime = dsImptRtrnPrcCalc.ezUpTime.getValue();
                String currentEzUpTimeZone = dsImptRtrnPrcCalc.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDMsg.copy(calcBase, "LP", dsImptRtrnPrcCalc, "");

                S21FastTBLAccessor.update(dsImptRtrnPrcCalc);

                if (!S21StringUtil.isEquals(dsImptRtrnPrcCalc.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptRtrnPrcCalc.getTableName() });
                    return false;
                }
            } else {
                if (!ZYPCommonFunc.hasValue(calcBase.dsImptPrcCalcBasePk_LP)) {
                    // create

                    DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();

                    EZDMsg.copy(calcBase, "LP", dsImptRtrnPrcCalc, "");

                    ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptRtrnPrcCalcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_RTRN_PRC_CALC_SQ));
                    EZDTBLAccessor.create(dsImptRtrnPrcCalc);

                    if (!S21StringUtil.isEquals(dsImptRtrnPrcCalc.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                        bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptRtrnPrcCalc.getTableName() });
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // QC#27364 2018/11/05 Add Start
    private boolean saveImptSvcUsgPrc(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        List<Map<String, Object>> targetList = getImptSvcForBand(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsImptOrdPk.getValue());
        for (Map<String, Object> map : targetList) {
            Map<String, Object> prcListBandCdPrcBookMdseCd = getPrcListBandCdPrcBookMdseCd(//
                    bizMsg.glblCmpyCd.getValue() //
                    , (String) map.get("MAINT_PRC_CATG_CD") //
                    , (BigDecimal) map.get("MDL_ID") //
                    , (BigDecimal) map.get("PRC_MTR_PKG_PK") //
                    , (String) map.get("PRC_SVC_PLN_TP_CD") //
                    , (String) map.get("PRC_SVC_CONTR_TP_CD") //
                    , (String) map.get("BLLG_MTR_LB_CD") //
                    , bizMsg.slsDt.getValue()//
                    , (String) map.get("SPLY_AGMT_PLN_SHORT_NM"));
            if (prcListBandCdPrcBookMdseCd != null) {
                DS_IMPT_SVC_USG_PRCTMsg inTMsg = new DS_IMPT_SVC_USG_PRCTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.dsImptSvcUsgPrcPk, (BigDecimal) map.get("DS_IMPT_SVC_USG_PRC_PK"));
                DS_IMPT_SVC_USG_PRCTMsg outTMsg = (DS_IMPT_SVC_USG_PRCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
                if (outTMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                String beforeEzUpTime = (String) map.get("EZUPTIME");
                String beforeEzUpTimeZone = (String) map.get("EZUPTIMEZONE");
                String currentEzUpTime = outTMsg.ezUpTime.getValue();
                String currentEzUpTimeZone = outTMsg.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(outTMsg.prcListBandCd, (String) prcListBandCdPrcBookMdseCd.get("PRC_LIST_BAND_CD"));
                ZYPEZDItemValueSetter.setValue(outTMsg.prcBookMdseCd, (String) prcListBandCdPrcBookMdseCd.get("MDSE_CD"));
                S21FastTBLAccessor.update(outTMsg);
                if (!S21StringUtil.isEquals(outTMsg.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {outTMsg.getTableName() });
                    return false;
                }
            }
        }
        
        return true;
    }
    // QC#27364 2018/11/05 Add End

    private boolean saveImptOrdErr(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        for (int i = 0; i < glblMsg.E.getValidCount(); i++) {

            // delete

            NWAL2200_ESMsg imptOrdErr = glblMsg.E.no(i);

            DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrPk, imptOrdErr.dsImptOrdErrPk_EL);
            dsImptOrdErr = (DS_IMPT_ORD_ERRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdErr);
            if (dsImptOrdErr == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            String beforeEzUpTime = imptOrdErr.ezUpTime_EL.getValue();
            String beforeEzUpTimeZone = imptOrdErr.ezUpTimeZone_EL.getValue();
            String currentEzUpTime = dsImptOrdErr.ezUpTime.getValue();
            String currentEzUpTimeZone = dsImptOrdErr.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsImptOrdErr);

            if (!S21StringUtil.isEquals(dsImptOrdErr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptOrdErr.getTableName() });
                return false;
            }
        }

        for (DS_IMPT_ORD_ERRTMsg dsImptOrdErr : errList) {
            // create

            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ERR_SQ));
            EZDTBLAccessor.create(dsImptOrdErr);

            if (!S21StringUtil.isEquals(dsImptOrdErr.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0730E, new String[] {dsImptOrdErr.getTableName() });
                return false;
            }
        }
        return true;
    }

    private boolean postMailToSalesRep(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        List<String> emlAdderList = getSlsRepEmlAddr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsImptOrdPk.getValue());
        // emlAdderList.addAll(getSlsRepBranchAdminEmlAddr(bizMsg.glblCmpyCd.getValue(),
        // bizMsg.slsDt.getValue(), bizMsg.dsImptOrdPk.getValue()));
        emlAdderList.addAll(getSlsAdminEmlAddr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsImptOrdPk.getValue()));

        if (emlAdderList.size() == 0) {
            return true;
        }

        // Get From
        S21MailGroup groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), MAIL_FROM_REJECT);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            bizMsg.setMessageInfo(NWAM0268E);
            return false;
        }

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(bizMsg.glblCmpyCd.getValue(), MAIL_TMPL_REJECT);
        if (template == null) {
            bizMsg.setMessageInfo(NWAM0268E);
            return false;
        }

        template.setTemplateParameter("ordSrcImptDate", ZYPDateUtil.formatEzd17ToDisp(bizMsg.ordSrcImptTs.getValue()));
        template.setTemplateParameter("procDate", ZYPDateUtil.formatEzd17ToDisp(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS")));
        template.setTemplateParameter("ordSrcRefNum", bizMsg.ordSrcRefNum.getValue());
        template.setTemplateParameter("billToCustAcctCd", bizMsg.billToCustAcctCd.getValue());
        template.setTemplateParameter("billToName", bizMsg.billToCustAcctNm.getValue());
        template.setTemplateParameter("billToCustCd", bizMsg.billToCustCd.getValue());
        template.setTemplateParameter("shipToCustAcctCd", bizMsg.shipToCustAcctCd.getValue());
        template.setTemplateParameter("shipToName", bizMsg.shipToCustAcctNm.getValue());
        template.setTemplateParameter("shipToCustCd", bizMsg.shipToCustCd.getValue());
        template.setTemplateParameter("sellToCustAcctCd", bizMsg.sellToCustCd.getValue());
        template.setTemplateParameter("sellToName", bizMsg.soldToCustAcctNm.getValue());
        template.setTemplateParameter("soldToCustLocCd", bizMsg.soldToCustLocCd.getValue());

        // Call Mail API
        for (String emlAdder : emlAdderList) {

            S21Mail mail = new S21Mail(bizMsg.glblCmpyCd.getValue());
            mail.setFromAddress(from);

            List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
            S21MailAddress addressInfo = new S21MailAddress(bizMsg.glblCmpyCd.getValue(), emlAdder);
            addrToList.add(addressInfo);
            mail.setToAddressList(addrToList);
            mail.setMailTemplate(template);
            mail.postMail();
        }

        return true;
    }

    private List<String> getSlsRepEmlAddr(String glblCmpyCd, String slsDt, BigDecimal dsImptOrdPk) {

        List<String> emlAdderList = new ArrayList<String>();

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", slsDt);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSlsRepEmlAddr(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return emlAdderList;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (Map<String, Object> result : resultList) {
            String emlAddr = (String) result.get("EML_ADDR");
            if (S21StringUtil.isNotEmpty(emlAddr)) {
                emlAdderList.add(emlAddr);
            }
        }
        return emlAdderList;
    }

    private List<String> getSlsAdminEmlAddr(String glblCmpyCd, String slsDt, BigDecimal dsImptOrdPk) {

        List<String> emlAdderList = new ArrayList<String>();

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", slsDt);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSlsAdminEmlAddr(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return emlAdderList;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (Map<String, Object> result : resultList) {
            String emlAddr = (String) result.get("EML_ADDR");
            if (S21StringUtil.isNotEmpty(emlAddr)) {
                emlAdderList.add(emlAddr);
            }
        }
        return emlAdderList;
    }

    private boolean deriveSalesrep(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // get sales representative.
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        queryParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        queryParam.put("slsRepTocNm", bizMsg.slsRepTocNm.getValue());
        queryParam.put("slsRepPsnNum", bizMsg.slsRepPsnNum.getValue());
        queryParam.put("rgtnStsCd", RGTN_STS.TERMINATED); // S21_NA#14607
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSlsRepInfoList(queryParam);
        if (ssmResult.isCodeNotFound()) {
            if (ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
                bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0181E, new String[] {"Salesrep" });
            }
            if (ZYPCommonFunc.hasValue(bizMsg.slsRepPsnNum)) {
                bizMsg.slsRepPsnNum.setErrorInfo(1, NWAM0181E, new String[] {"Salesrep" });
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> slsRepInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> slsRepInfo = slsRepInfoList.get(0);
        // set sales representative.
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepPsnNum, slsRepInfo.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepInfo.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, slsRepInfo.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, slsRepInfo.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, slsRepInfo.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, slsRepInfo.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, slsRepInfo.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, slsRepInfo.get("COA_BR_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, slsRepInfo.get("COA_EXTN_ITEM"));

        // derive sales credit.
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(bizMsg.H);
        setDefaultSlsCreditInfo(bizMsg, bizMsg.slsRepTocCd.getValue());
        return true;
    }

    /**
     * Set Default Sales Credit Information
     * @param bizMsg NWAL2200CMsg
     * @param writerSlsRepTocCd Write Sales Rep Toc Code
     */
    private static void setDefaultSlsCreditInfo(NWAL2200CMsg bizMsg, String writerSlsRepTocCd) {

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String slsRepRoleTpCd = null;
        // 2018/01/23 QC#18798 Mod Start
//        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
//
//            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
//        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
//
//            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
//        } else {
//
//            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
//        }
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {

            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {

            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {

            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {

            String fstBizCtxAttbTxt = NWAL2200Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                slsRepRoleTpCd = fstBizCtxAttbTxt;
            }
        }
        // 2018/01/23 QC#18798 Mod End

        // For Header
        if (bizMsg.F.getValidCount() == 0) {
            NWAL2200_FCMsg hdrSlsCreditMsg = bizMsg.F.no(bizMsg.F.getValidCount());
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.dsImptOrdPk_FS, bizMsg.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepTocCd_FS, writerSlsRepTocCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepRoleTpCd_FS, slsRepRoleTpCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepCrPct_FS, BigDecimal.TEN.multiply(BigDecimal.TEN));
            ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_ON_Y);
            bizMsg.F.setValidCount(bizMsg.F.getValidCount() + 1);
        }

        // For Line configuration
        if (bizMsg.G.getValidCount() == 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                NWAL2200_GCMsg lineSlsCreditMsg = bizMsg.G.no(bizMsg.G.getValidCount());
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.dsImptOrdPk_GS, bizMsg.A.no(i).dsImptOrdPk_LC);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.dsImptOrdConfigPk_GS, bizMsg.A.no(i).dsImptOrdConfigPk_LC);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.dsOrdPosnNum_GS, bizMsg.A.no(i).dsOrdPosnNum_LC);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepTocCd_GS, writerSlsRepTocCd);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepRoleTpCd_GS, slsRepRoleTpCd);
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepCrPct_GS, BigDecimal.TEN.multiply(BigDecimal.TEN));
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsCrQuotFlg_GS, ZYPConstant.FLG_ON_Y);
                bizMsg.G.setValidCount(bizMsg.G.getValidCount() + 1);
            }
        }
        // For RMA
        if (bizMsg.H.getValidCount() == 0) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

                NWAL2200_HCMsg rmaSlsCreditMsg = bizMsg.H.no(bizMsg.H.getValidCount());
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.dsImptOrdPk_HS, bizMsg.C.no(i).dsImptOrdPk_RC);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.dsImptOrdConfigPk_HS, bizMsg.C.no(i).dsImptOrdConfigPk_RC);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.dsOrdPosnNum_HS, bizMsg.C.no(i).dsOrdPosnNum_RC);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepTocCd_HS, writerSlsRepTocCd);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepRoleTpCd_HS, slsRepRoleTpCd);
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepCrPct_HS, BigDecimal.TEN.multiply(BigDecimal.TEN));
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsCrQuotFlg_HS, ZYPConstant.FLG_ON_Y);
                bizMsg.H.setValidCount(bizMsg.H.getValidCount() + 1);
            }
        }
    }

    private boolean deriveModifiedItems(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        int gi = 0;
        for (int i = 0; i < glblMsg.B.getValidCount() && gi < glblMsg.J.getValidCount(); i++) {

            NWAL2200_BSMsg line = glblMsg.B.no(i);
            NWAL2200_JSMsg originalLine = glblMsg.J.no(gi);

            for (; gi < glblMsg.J.getValidCount();) {
                if (NWAL2200CommonLogic.compareBigDecimal(originalLine.dsImptOrdDtlPk_LL.getValue(), line.dsImptOrdDtlPk_LL.getValue()) == 0) {
                    break;
                }

                // seek line
                gi++;
                originalLine = glblMsg.J.no(gi);
            }

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end

            if (!S21StringUtil.isEquals(line.mdseCd_LL.getValue(), originalLine.mdseCd_LL.getValue())) {

                // item has changed.

                // deriving item
                if (NWAL2200CommonLogic.deriveFromItem(bizMsg, line)) {

                    if (!S21StringUtil.isEquals(bizMsg.cpoSrcTpCd.getValue(), CPO_SRC_TP.EDI)) {
                        // configuration
                        String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
                        NWAL2200_ACMsg config = NWAL2200CommonLogicForLineControl.getConfig(bizMsg.A, dsOrdPosnNum);
                        if (config == null) {
                            return false;
                        }

                        // price calculation base
                        List<NWAL2200_ISMsg> priceCalcBaseList = NWAL2200CommonLogicForLineControl.getPriceCalcBaseList(glblMsg.I, line.dsImptOrdDtlPk_LL.getValue());

                        // pricing
                        NWAL2200CommonLogicForPricing.pricingForEdi(bizMsg, config, line, priceCalcBaseList, NWZC157001.GET_LINE_PRICE);
                    }
                } else {
                    return false;
                }
            }
            gi++;
        }
        return true;
    }
    // QC#27364 2018/11/05 Add Start
    private List<Map<String, Object>> getImptSvcForBand(String glblCmpyCd, String slsDt, BigDecimal dsImptOrdPk) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getImptSvcForBand(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return new ArrayList<Map<String, Object>>();
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        return resultList;
    }

    private static Map<String, Object> getPrcListBandCdPrcBookMdseCd(//
            String glblCmpyCd //
            , String maintPrcCatgCd //
            , BigDecimal mdlId //
            , BigDecimal prcMtrPkgPk //
            , String prcSvcPlnTpCd //
            , String prcSvcContrTpCd //
            , String bllgMtrLbCd //
            , String slsDt //
            , String splyAgmtPlnShortNm) {
        if (S21StringUtil.isEmpty(maintPrcCatgCd)) {
            return null;
        }
        if (mdlId == null) {
            return null;
        }
        if (prcMtrPkgPk == null) {
            return null;
        }
        if (S21StringUtil.isEmpty(prcSvcPlnTpCd)) {
            return null;
        }
        if (S21StringUtil.isEmpty(prcSvcContrTpCd)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgCd", maintPrcCatgCd);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmParam.put("prcSvcPlnTpCd", prcSvcPlnTpCd);
        ssmParam.put("prcSvcContrTpCd", prcSvcContrTpCd);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmParam.put("prcRateTp", PRC_RATE_TP.CPC);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("splyAgmtPlnShortNm", splyAgmtPlnShortNm);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getPrcListBandCdPrcBookMdseCd(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
        return result;
    }
    // QC#27364 2018/11/05 Add End
}
