/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.validation;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.EXST_WH_CONFIG_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_SUBMIT;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWAM0054E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0049E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0087E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0100E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0101E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0336E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0492E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0507E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0617E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0619E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1266E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1377E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1379E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1401E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1402E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1403E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1405E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1406E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1407E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1408E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1409E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1410E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1413E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1414E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1415E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1416E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1417E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1418E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1419E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1420E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1421E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1422E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1423E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1424E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1425E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1426E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1427E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1428E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1429E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1430E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1431E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1432E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1433E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1434E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1435E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1436E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1438E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1439E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1440E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1441E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1442E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1443E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1444E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1445E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1446E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1448E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1486E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1593E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1594E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1595E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1887E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1944E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2033E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.SET_LINE_SUB_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.MDSETMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_APMsgArray;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoConfigPMsgArray;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnDtlPMsgArray;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001DsCpoCommonBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Query;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.derive.NWZC150001ForDefaultValueSetter;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/03   Fujitsu         T.Ogura         Create          N/A
 * 2017/11/07   Fujitsu         S.Takami        Update          S21_NA#22323
 * 2017/11/14   Fujitsu         S.Takami        Update          S21_NA#22216
 * 2017/12/18   Fujitsu         S.Takami        Update          S21_NA#23162
 * 2018/05/18   Fujitsu         H.Tomimatsu     Update          S21_NA#25192
 * 2018/06/18   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/07/27   Fujitsu         Y.Kanefusa      Update          S21_NA#27485
 * 2018/09/21   Fujitsu         K.Ishizuka      Update          S21_NA#28363
 * 2018/09/25   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/16   Fujitsu         K.Ishizuka      Update          S21_NA#28727
 * 2018/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#28772
 * 2019/03/29   Fujitsu         K.Ishizuka      Update          S21_NA#30978
 * 2019/04/24   Fujitsu         K.Ishizuka      Update          S21_NA#31185
 * 2019/08/20   Fujitsu         T.Noguchi       Update          S21_NA#52619
 * 2019/09/27   Fujitsu         R.Matsuki       Update          QC#53593
 * 2021/01/30   CITS            K.Ogino         Update          QC#58230
 * 2023/09/22   CITS            K.Ikeda         Update          QC#61894
 * </pre>
 */
public class NWZC150001ForDsCheck extends S21ApiCommonBase {

    public static void execDsCheck(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> pMsg2List //
            , List<NWZC150003PMsg> pMsg3List
            , CPOTMsg cpoTMsg
            // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
            , NWZC150001CpouLocalCache localCache
            , S21ApiMessageIdMgr msgIdMgr
            , NWZC150001DsCpoCommonBean commonBean
            // 2017/05/11 S21_NA#Review structure Lv.2 Add End
            // 2019/09/27 QC#53593 ADD START
            , String slsDt
            // 2019/09/27 QC#53593 ADD END
    ) {
        if (!MODE_SAVE.equals(pMsg.xxModeCd.getValue()) //
                && !MODE_SUBMIT.equals(pMsg.xxModeCd.getValue()) //
                && !MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM0977E, pMsg);
            return;
        }

        if (checkEssentialForHeader(pMsg, msgIdMgr)) {
            return;
        }

        // 2016/06/02 S21_NA#8464 Add Start
        if (checkLineVsConfig(pMsg, pMsg2List, pMsg3List)) {
            return;
        }
        // 2016/06/02 S21_NA#8464 Add End

        if (!MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            if (checkEssentialForConfig(pMsg.cpoConfig, pMsg2List)) {
                return;
            }
        }

        if (checkEssentialForDetail(pMsg.A, pMsg2List)) {
            return;
        }

        if (checkEssentialForReturn(pMsg.rtnDtl, pMsg3List)) {
            return;
        }

        if (checkMasterForHeader(pMsg, msgIdMgr)) {
            return;
        }

        // 2019/09/27 QC#53593 MOD START
//        if (checkMasterForDetail(pMsg.A, pMsg2List, pMsg)) {
//            return;
//        }
//
//        if (checkMasterForReturn(pMsg.rtnDtl, pMsg3List, pMsg)) {
//            return;
//        }
        if (checkMasterForDetail(pMsg.A, pMsg2List, pMsg, slsDt)) {
            return;
        }

        if (checkMasterForReturn(pMsg.rtnDtl, pMsg3List, pMsg, slsDt)) {
            return;
        }
        // 2019/09/27 QC#53593 MOD END

        if (checkMasterForConfig(pMsg.cpoConfig, pMsg2List, pMsg)) {
            return;
        }

        if (!MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            // 2018/06/18 S21_NA#24294 Mod Start
            // List<MDSETMsg> mdseTMsgList = getMdseTMsgList(pMsg);
            List<MDSETMsg> mdseTMsgList = getMdseTMsgList(pMsg, commonBean);
            // 2018/06/18 S21_NA#24294 Mod End
            List<MDSETMsg> mdseTMsgRtrnList = getMdseTMsgRtrnList(pMsg);
            // 2018/06/18 S21_NA#24294 Del Start
            // getDsMdseInfoTMsgList(pMsg, mdseTMsgList, commonBean); // 2016/07/22 S21_NA#11970 Add
            // 2018/06/18 S21_NA#24294 Del End

            // 2016/10/25 S21_NA#9705 Add Start
            if (checkAvailavleSlsCr(pMsg, pMsg2List, pMsg3List, msgIdMgr)) {
                return;
            }
            // 2016/10/25 S21_NA#9705 Add Start

            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            NWZC150001ForDefaultValueSetter.setDefaultValues(pMsg, pMsg2List, pMsg3List, mdseTMsgList, mdseTMsgRtrnList, cpoTMsg); // Mod(Add Param) S21_NA#14107
            NWZC150001ForDefaultValueSetter.setDefaultValues(pMsg, pMsg2List, pMsg3List, mdseTMsgList, mdseTMsgRtrnList, cpoTMsg, localCache, msgIdMgr, commonBean); // Mod(Add Param) S21_NA#14107
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
            // 2018/09/21 S21_NA#28363 Add Start
            if (msgIdMgr.isXxMsgId()) {
                return;
            }
            // 2018/09/21 S21_NA#28363 Add End
            // 2019/09/27 QC#53593 MOD START
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            NWZC150001ForOtherCheck.otherCheck(pMsg, pMsg2List, pMsg3List, mdseTMsgList, mdseTMsgRtrnList);
//            NWZC150001ForOtherCheck.otherCheck(pMsg, pMsg2List, pMsg3List, mdseTMsgList, mdseTMsgRtrnList, localCache, msgIdMgr, commonBean);
            NWZC150001ForOtherCheck.otherCheck(pMsg, pMsg2List, pMsg3List, mdseTMsgList, mdseTMsgRtrnList, localCache, msgIdMgr, commonBean, slsDt);
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
            // 2019/09/27 QC#53593 MOD END
        }
        return;

    }

    /**
     * @param pMsg NWZC150001PMsg
     * @param pMsg NWZC150001PMsg
     * @return boolean
     */
    private static boolean checkEssentialForHeader(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
        boolean ret = false;
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgIdMgr.addXxMsgId(NWZM0011E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgIdMgr.addXxMsgId(NWZM0346E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.usrId)) {
            msgIdMgr.addXxMsgId(NWZM0336E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.bizAppId)) {
            msgIdMgr.addXxMsgId(NWZM0087E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
            msgIdMgr.addXxMsgId(NWZM1401E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd)) {
            msgIdMgr.addXxMsgId(NWZM1253E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.billToCustAcctCd)) {
            msgIdMgr.addXxMsgId(NWZM1377E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
            msgIdMgr.addXxMsgId(NWZM0617E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.shipToCustAcctCd)) {
            msgIdMgr.addXxMsgId(NWZM1379E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
            msgIdMgr.addXxMsgId(NWZM1402E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.soldToCustLocCd)) {
            msgIdMgr.addXxMsgId(NWZM1403E, pMsg);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.addShpgSvcLvlCd)) {
            msgIdMgr.addXxMsgId(NWZM0049E, pMsg);
            ret = true;
        }
        if (isShipToCustCdEmpty(pMsg.A)) {
            if (!ZYPCommonFunc.hasValue(pMsg.addShipToCustCd)) {
                msgIdMgr.addXxMsgId(NWZM0619E, pMsg);
                ret = true;
            }
        }
        // 2018/09/21 S21_NA#28363 Del Start
        // if (!ZYPCommonFunc.hasValue(pMsg.slsRepCd)) {
        //     msgIdMgr.addXxMsgId(NWZM0688E, pMsg);
        //     ret = true;
        // }
        // 2018/09/21 S21_NA#28363 Del End
        if (!MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.prcCatgCd)) {
                msgIdMgr.addXxMsgId(NWZM1405E, pMsg);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.flPrcListCd)) {
                msgIdMgr.addXxMsgId(NWZM1406E, pMsg);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.frtCondCd)) {
                msgIdMgr.addXxMsgId(NWZM1266E, pMsg);
                ret = true;
            }
        }
        return ret;
    }

    private static boolean isShipToCustCdEmpty(NWZC150001_APMsgArray pMsgAry) {
        for (int i = 0; i < pMsgAry.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(pMsgAry.no(i).shipToCustCd_A1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkLineVsConfig(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List) {

        boolean rslt = false;

        for (int i =0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = getOutBoundConfigPMsg(pMsg.A.no(i).dsOrdPosnNum_A1.getValue(), pMsg);
            if (null == configPMsg) {
                NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                NWZC150001Common.setMsgId2(pMsg2, NWZM1944E);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, pMsg.A.no(i).dsOrdPosnNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, pMsg.A.no(i).cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, pMsg.A.no(i).cpoDtlLineSubNum_A1);
                pMsg2List.add(pMsg2);
                rslt = true;
            }
        }

        for (int i =0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = getInBoundConfigPMsg(pMsg.rtnDtl.no(i).dsOrdPosnNum_B1.getValue(), pMsg);
            if (null == configPMsg) {
                NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                NWZC150001Common.setMsgId3(pMsg3, NWZM1944E);
                ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, pMsg.rtnDtl.no(i).dsOrdPosnNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, pMsg.rtnDtl.no(i).cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, pMsg.rtnDtl.no(i).cpoDtlLineSubNum_B1);
                pMsg3List.add(pMsg3);
                rslt = true;
            }
        }
        return rslt;
    }

    private static NWZC150001_cpoConfigPMsg getOutBoundConfigPMsg(String dsOrdPosnNum, NWZC150001PMsg pMsg) {
        for (int i = 0; i < pMsg.cpoConfig.getValidCount();i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            if (dsOrdPosnNum.equals(configPMsg.dsOrdPosnNum.getValue()) //
                    && CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
                return configPMsg;
            }
        }
        return null;
    }

    private static NWZC150001_cpoConfigPMsg getInBoundConfigPMsg(String dsOrdPosnNum, NWZC150001PMsg pMsg) {
        for (int i = 0; i < pMsg.cpoConfig.getValidCount();i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            if (dsOrdPosnNum.equals(configPMsg.dsOrdPosnNum.getValue()) //
                    && CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
                return configPMsg;
            }
        }
        return null;
    }

    private static boolean checkEssentialForConfig(NWZC150001_cpoConfigPMsgArray pMsgAry, List<NWZC150002PMsg> pMsg2List) {
        boolean ret = false;

        for (int i = 0; i < pMsgAry.getValidCount(); i++) {
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            NWZC150001_cpoConfigPMsg pMsg = pMsgAry.no(i);
            boolean b = false;

            if (!RQST_TP_CONFIG_NEW.equals(pMsg.xxRqstTpCd.getValue()) //
                    && !RQST_TP_CONFIG_MODIFY.equals(pMsg.xxRqstTpCd.getValue()) //
                    && !RQST_TP_CONFIG_DELETE.equals(pMsg.xxRqstTpCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM0977E);
                b = true;
            }

            if (!RQST_TP_CONFIG_NEW.equals(pMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.dsCpoConfigPk)) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1407E);
                    b = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(pMsg.dsOrdPosnNum)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1408E);
                b = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.configCatgCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1409E);
                b = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.configTpCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1410E);
                b = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.billToCustAcctCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1377E);
                b = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM0617E);
                b = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustAcctCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1379E);
                b = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM0619E);
                b = true;
            }

            if (b) {
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, pMsg.dsOrdPosnNum);
                pMsg2List.add(pMsg2);
                ret = b;
            }

        }
        return ret;
    }

    private static boolean checkEssentialForDetail(NWZC150001_APMsgArray pMsgAry, List<NWZC150002PMsg> pMsg2List) {
        boolean ret = false;
        NWZC150001_APMsg parentLine = null;

        //        List<String> setLineNumList = getSetLineNumList(pMsgAry);

        for (int i = 0; i < pMsgAry.getValidCount(); i++) {
            boolean b = false;
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            NWZC150001_APMsg pMsg = pMsgAry.no(i);

            //            if (!ZYPCommonFunc.hasValue(pMsg.dsCpoLineNum_A1)) {
            //                setMsgId2(pMsg2, NWZM1411E);
            //                b = true;
            //            }
            //            if (setLineNumList.contains(pMsg.cpoDtlLineNum_A1.getValue()) //
            //                    && SET_LINE_SUB_NUM.equals(pMsg.cpoDtlLineSubNum_A1.getValue())) {
            //                if (!ZYPCommonFunc.hasValue(pMsg.dsCpoLineSubNum_A1)) {
            //                    setMsgId2(pMsg2, NWZM1412E);
            //                    b = true;
            //                }
            //            }
            if (!RQST_TP_DTL_CANCEL.equals(pMsg.xxRqstTpCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.dsOrdLineCatgCd_A1)) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1413E);
                    b = true;
                }
                if (!ZYPCommonFunc.hasValue(pMsg.baseCmptFlg_A1)) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1414E);
                    b = true;
                }
            }

            if (b) {
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, pMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum_A1);

                pMsg2List.add(pMsg2);
                ret = b;
            }
            if (!RQST_TP_DTL_CANCEL.equals(pMsg.xxRqstTpCd_A1.getValue())) {
                if (SET_LINE_SUB_NUM.equals(pMsg.cpoDtlLineSubNum_A1.getValue())) {
                    parentLine = pMsg;
                }else{
                    if(parentLine != null && pMsg.cpoDtlLineNum_A1.getValue().equals(parentLine.cpoDtlLineNum_A1.getValue())){
                        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd_A1, parentLine.prcCatgCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rtlWhCd_A1, parentLine.rtlWhCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rtlSwhCd_A1, parentLine.rtlSwhCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.ordLineSrcCd_A1, parentLine.ordLineSrcCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd_A1, parentLine.flPrcListCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt_A1, parentLine.prcBaseDt_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rddDt_A1, parentLine.rddDt_A1);
                    }else{
                        parentLine = null;
                    }
                }
            }
        }
        return ret;
    }

    private static boolean checkEssentialForReturn(NWZC150001_rtnDtlPMsgArray rtnDtl, List<NWZC150003PMsg> msg3List) {
        boolean ret = false;
        NWZC150001_rtnDtlPMsg parentLine = null;

        for (int i = 0; i < rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg pMsg = rtnDtl.no(i);
            NWZC150003PMsg pMsg3 = new NWZC150003PMsg();

            if (!RQST_TP_RTRN_DTL_SAVE.equals(pMsg.xxRqstTpCd_B1.getValue()) //
                    && !RQST_TP_RTRN_DTL_NEW.equals(pMsg.xxRqstTpCd_B1.getValue()) //
                    && !RQST_TP_RTRN_DTL_CANCEL.equals(pMsg.xxRqstTpCd_B1.getValue()) //
                    && !RQST_TP_RTRN_DTL_MODIFY.equals(pMsg.xxRqstTpCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM0977E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.cpoDtlLineNum_B1)) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM0100E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.cpoDtlLineSubNum_B1)) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM0101E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.mdseCd_B1)) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM0492E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd_B1)) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM0507E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.ordQty_B1) //
                    && BigDecimal.ZERO.compareTo(pMsg.ordQty_B1.getValue()) == 0) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1486E);
                ret = true;
            }
            if (ret) {
                msg3List.add(pMsg3);
            }
            if (!RQST_TP_DTL_CANCEL.equals(pMsg.xxRqstTpCd_B1.getValue())) {
                if (SET_LINE_SUB_NUM.equals(pMsg.cpoDtlLineSubNum_B1.getValue())) {
                    parentLine = pMsg;
                }else{
                    if(parentLine != null && pMsg.cpoDtlLineNum_B1.getValue().equals(parentLine.cpoDtlLineNum_B1.getValue())){
                        ZYPEZDItemValueSetter.setValue(pMsg.rtrnRsnCd_B1, parentLine.rtrnRsnCd_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd_B1, parentLine.prcCatgCd_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rtlWhCd_B1, parentLine.rtlWhCd_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rtlSwhCd_B1, parentLine.rtlSwhCd_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd_B1, parentLine.flPrcListCd_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt_B1, parentLine.prcBaseDt_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg.rqstPickUpDt_B1, parentLine.rqstPickUpDt_B1);
                    }else{
                        parentLine = null;
                    }
                }
            }
        }
        return ret;
    }

    private static boolean checkMasterForHeader(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
        boolean ret = false;

        if (!NWXC150001DsCheck.existsDsOrdCatg(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1415E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAccount(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.billToCustAcctCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1416E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAccount(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.shipToCustAcctCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1417E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsSoldToLocation(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.soldToCustLocCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1418E, pMsg);
            ret = true;
        }
        // 2018/09/21 S21_NA#28363 Mod Start
        // if (!NWXC150001DsCheck.existsSalesRep(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue())) {
        //     msgIdMgr.addXxMsgId(NWAM0054E, pMsg);
        //     ret = true;
        // }
        if (ZYPCommonFunc.hasValue(pMsg.slsRepCd)) {
            if (!NWXC150001DsCheck.existsSalesRep(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue())) {
                msgIdMgr.addXxMsgId(NWAM0054E, pMsg);
                ret = true;
            }
        }
        // 2018/09/21 S21_NA#28363 Mod End
        if (!NWXC150001DsCheck.existsPrcCatg(pMsg.glblCmpyCd.getValue(), pMsg.prcCatgCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1419E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsFlPrcList(pMsg.glblCmpyCd.getValue(), pMsg.flPrcListCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1420E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAssnPgm(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.prcContrNum.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1421E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeasePrchOpt(pMsg.glblCmpyCd.getValue(), pMsg.leasePrchOptCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1422E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeaseTerm(pMsg.glblCmpyCd.getValue(), pMsg.leaseTermCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1423E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeasePmtFreq(pMsg.glblCmpyCd.getValue(), pMsg.leasePmtFreqCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1424E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsOrdLogTp(pMsg.glblCmpyCd.getValue(), pMsg.ordLogTpCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1425E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsFrtCond(pMsg.glblCmpyCd.getValue(), pMsg.frtCondCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1426E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsCarrSvcLvl(pMsg.glblCmpyCd.getValue(), pMsg.carrSvcLvlCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1427E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsSpclHdlgTp(pMsg.glblCmpyCd.getValue(), pMsg.spclHdlgTpCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1428E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsPrePmtTp(pMsg.glblCmpyCd.getValue(), pMsg.prePmtTpCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1429E, pMsg);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsCrRebilRsnCatg(pMsg.glblCmpyCd.getValue(), pMsg.crRebilRsnCatgCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1430E, pMsg);
            ret = true;
        }

        return ret;
    }

    // 2019/09/27 QC#53593 MOD START
//    private static boolean checkMasterForDetail(NWZC150001_APMsgArray aPMsgAry, List<NWZC150002PMsg> pMsg2List, NWZC150001PMsg pMsg) {
    private static boolean checkMasterForDetail(NWZC150001_APMsgArray aPMsgAry, List<NWZC150002PMsg> pMsg2List, NWZC150001PMsg pMsg, String slsDt) {
    // 2019/09/27 QC#53593 MOD END
        boolean ret = false;

        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = aPMsgAry.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();

            if (!NWXC150001DsCheck.existsDsOrdLineCatg(//
                    pMsg.glblCmpyCd.getValue() //
                    , aPMsg.dsOrdLineCatgCd_A1.getValue() //
                    , DS_ORD_LINE_DRCTN.OUTBOUND)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1431E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsOrdLineSrc(pMsg.glblCmpyCd.getValue(), aPMsg.ordLineSrcCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1432E);
                ret = true;
            }
            // 2019/09/27 QC#53593 MOD START
//            if (!NWXC150001DsCheck.existsRtlWh(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), aPMsg.rtlWhCd_A1.getValue())) {
//                NWZC150001Common.setMsgId2(pMsg2, NWZM1433E);
//                ret = true;
//            }
//            if (!NWXC150001DsCheck.existsRtlSubWh(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), aPMsg.rtlWhCd_A1.getValue(), aPMsg.rtlSwhCd_A1.getValue())) {
//                NWZC150001Common.setMsgId2(pMsg2, NWZM1434E);
//                ret = true;
//            }
            if (!NWXC150001DsCheck.existsRtlWh(pMsg.glblCmpyCd.getValue(), slsDt, aPMsg.rtlWhCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1433E);
                ret = true;
            }
            // QC#58230
            if (ZYPCommonFunc.hasValue(pMsg.cpoSrcTpCd) && //
                    CPO_SRC_TP.DEAL_CONFIGURATOR.equals(pMsg.cpoSrcTpCd.getValue()) && //
                    ZYPCommonFunc.hasValue(aPMsg.prodCondCd)) {
                // no process.
            } else if (!NWXC150001DsCheck.existsRtlSubWh(pMsg.glblCmpyCd.getValue(), slsDt, aPMsg.rtlWhCd_A1.getValue(), aPMsg.rtlSwhCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1434E);
                ret = true;
            }
            // 2019/09/27 QC#53593 MOD END
            if (!NWXC150001DsCheck.existsFlPrcList(pMsg.glblCmpyCd.getValue(), aPMsg.flPrcListCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1420E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsCrRebil(pMsg.glblCmpyCd.getValue(), aPMsg.crRebilCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1435E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsSplyTp(pMsg.glblCmpyCd.getValue(), aPMsg.splyTpCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1436E);
                ret = true;
            }
            // post code does not set to the master table.
            //            if (!NWXC150001DsCheck.existsPost(glblCmpyCd, aPMsg.splyPostCd_A1.getValue())) {
            //                setMsgId2(pMsg2, NWZM1437E);
            //                ret = true;
            //            }
            if (!NWXC150001DsCheck.existsBilgAttrCustAcct(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), aPMsg.bllgAttrbCustAcctCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1438E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsSbstItem(pMsg.glblCmpyCd.getValue(), aPMsg.sbstMdseCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1439E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsCarrSvcLvl(pMsg.glblCmpyCd.getValue(), aPMsg.carrSvcLvlCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1427E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsPrcCatg(pMsg.glblCmpyCd.getValue(), aPMsg.prcCatgCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1419E);
                ret = true;
            }

            if (ret) {
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);

                pMsg2List.add(pMsg2);
            }
        }
        return ret;
    }

    // 2019/09/27 QC#53593 MOD START
//    private static boolean checkMasterForReturn(NWZC150001_rtnDtlPMsgArray dtlPMsgArray, List<NWZC150003PMsg> pMsg3List, NWZC150001PMsg pMsg) {
    private static boolean checkMasterForReturn(NWZC150001_rtnDtlPMsgArray dtlPMsgArray, List<NWZC150003PMsg> pMsg3List, NWZC150001PMsg pMsg, String slsDt) {
    // 2019/09/27 QC#53593 MOD END
        boolean ret = false;

        for (int i = 0; i < dtlPMsgArray.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = dtlPMsgArray.no(i);
            NWZC150003PMsg pMsg3 = new NWZC150003PMsg();

            if (!NWXC150001DsCheck.existsDsOrdLineCatg(//
                    pMsg.glblCmpyCd.getValue() //
                    , rtnDtlPMsg.dsOrdLineCatgCd_B1.getValue() //
                    , DS_ORD_LINE_DRCTN.INBOUND)) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1431E);
                ret = true;
            }
            // 2019/09/27 QC#53593 MOD START
//            if (!NWXC150001DsCheck.existsRtlWh(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), rtnDtlPMsg.rtlWhCd_B1.getValue())) {
//                NWZC150001Common.setMsgId3(pMsg3, NWZM1433E);
//                ret = true;
//            }
//            if (!NWXC150001DsCheck.existsRtlSubWh(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), rtnDtlPMsg.rtlWhCd_B1.getValue(), rtnDtlPMsg.rtlSwhCd_B1.getValue())) {
//                NWZC150001Common.setMsgId3(pMsg3, NWZM1434E);
//                ret = true;
//            }
            if (!NWXC150001DsCheck.existsRtlWh(pMsg.glblCmpyCd.getValue(), slsDt, rtnDtlPMsg.rtlWhCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1433E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsRtlSubWh(pMsg.glblCmpyCd.getValue(), slsDt, rtnDtlPMsg.rtlWhCd_B1.getValue(), rtnDtlPMsg.rtlSwhCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1434E);
                ret = true;
            }
            // 2019/09/27 QC#53593 MOD END
            if (!NWXC150001DsCheck.existsFlPrcList(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.flPrcListCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1420E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsBilgAttrCustAcct(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), rtnDtlPMsg.bllgAttrbCustAcctCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1438E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsPrcCatg(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.prcCatgCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1419E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsHddRmv(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.hddRmvCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1593E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsRtrnRsn(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.rtrnRsnCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1594E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsThirdPtyDspTp(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.thirdPtyDspTpCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1595E);
                ret = true;
            }

            if (ret) {
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);

                pMsg3List.add(pMsg3);
            }
        }
        return ret;
    }

    private static boolean checkMasterForConfig(NWZC150001_cpoConfigPMsgArray cpoConfigAry, List<NWZC150002PMsg> pMsg2List, NWZC150001PMsg pMsg) {
        boolean ret = false;

        for (int i = 0; i < cpoConfigAry.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = cpoConfigAry.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();

            if (!NWXC150001DsCheck.existsConfigCatg(pMsg.glblCmpyCd.getValue(), configPMsg.configCatgCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1440E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1441E);
                ret = true;
            }
            // 2015/11/19 S21_NA#894 Add if statement, CONFIT_TP.NEW.....
//            if (!CONFIG_TP.NEW.equals(configPMsg.configTpCd.getValue())) {
            // Out bound Not Y N N
//            if (!NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
            // 2019/08/20 S21_NA#52619 Add Start
            if (!MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            // 2019/08/20 S21_NA#52619 Add End
                if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) { // S21_NA#11297
                    // 2019/04/24 S21_NA#31185 Mod Start
                    boolean hasShippedLine = isExistShippedLine(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), configPMsg.dsOrdPosnNum.getValue());

                    // if (!NWXC150001DsCheck.existsSvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue())) {
                    if (!NWXC150001DsCheck.existsSvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue()) && !hasShippedLine) {
                        // 2019/04/24 S21_NA#31185 Mod End
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1442E);
                        ret = true;
                    }
                }
            // 2019/08/20 S21_NA#52619 Add Start
            }
            // 2019/08/20 S21_NA#52619 Add End
            if (!NWXC150001DsCheck.existsMdlId(pMsg.glblCmpyCd.getValue(), configPMsg.mdlId.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1443E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsAccount(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), configPMsg.billToCustAcctCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1416E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsBillToLocation(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), configPMsg.billToCustCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1444E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsAccount(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), configPMsg.shipToCustAcctCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1417E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsShipToLocation(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), configPMsg.shipToCustCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1445E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsState(pMsg.glblCmpyCd.getValue(), configPMsg.shipToStCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1446E);
                ret = true;
            }
            // post code does not set to the master table.
            //            if (!NWXC150001DsCheck.existsPost(glblCmpyCd, configPMsg.shipToPostCd.getValue())) {
            //                setMsgId2(pMsg2, NWZM1447E);
            //                ret = true;
            //            }
            if (!NWXC150001DsCheck.existsCtry(pMsg.glblCmpyCd.getValue(), configPMsg.shipToCtryCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1448E);
                ret = true;
            }

            // 2017/11/07 S21_NA#22323 Add Start
            boolean isOrderCredit = S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, pMsg.cpoSrcTpCd.getValue());
            boolean isAllDtlRebil = NWZC150001Common.isAllDetailRebillConfig(configPMsg, pMsg);
            // 2017/11/07 S21_NA#22323 Add End
            // 2016/01/25 S21_NA#3505 Add Start
            if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, false) //
                    && !isOrderCredit && !isAllDtlRebil) { // 2017/11/07 S21_NA#22323 Add "&& !isOrderCredit && !isAllDtlRebil"
                // 2018/10/16 S21_NA#28727 Mod Start
                // if (!SVC_MACH_USG_STS.AT_CUSTOMER.equals(NWXC150001DsCheck.getMachineUsageStatus(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue()))) {
                if(!checkMachUsgSts(pMsg, configPMsg)){
                    // 2018/10/16 S21_NA#28727 Mod End
                    // 2018/05/18 S21_NA#25192 Add Start
                    if(NWXC150001DsCheck.getTrxHdrNumMatchCnt(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), configPMsg.svcConfigMstrPk.getValue())){
                    // 2018/05/18 S21_NA#25192 Add End
                        
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1887E);
                        ret = true;
                    }
                }
            }
            // 2016/01/25 S21_NA#3505 Add End

            // QC#24245 2018/06/13 Add Start
            if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, false, true) 
                    && !isOrderCredit && !isAllDtlRebil) {
                // QC#27485 2018/07/27 Add Start
                //if (!SVC_MACH_USG_STS.IN_INVENTORY.equals(NWXC150001DsCheck.getMachineUsageStatus(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue()))) {
                // 2018/09/25 S21_NA#28482 Mod Start
                // String svcMachUsgStsCd = NWXC150001DsCheck.getMachineUsageStatus(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
                String svcMachMstrStsCd = NWXC150001DsCheck.getMachineMasterStatus(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
                String externalLocConstValue = ZYPCodeDataUtil.getVarCharConstValue(EXST_WH_CONFIG_STS, pMsg.glblCmpyCd.getValue());
                List<String> avalStsList = new ArrayList<String>();
                if (externalLocConstValue != null) {
                    String[] externalLocConstArr = externalLocConstValue.split(",");
                    for (int k = 0; k < externalLocConstArr.length; k++) {
                        avalStsList.add(externalLocConstArr[k]);
                    }
                }
                // QC#61894 K.ikeda Add Start 
                final boolean hasShippedLine = isExistShippedLine(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), configPMsg.dsOrdPosnNum.getValue());
                // if (!SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && !SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd)) {
                // if (!avalStsList.contains(svcMachMstrStsCd)) { // 2019/04/24 S21_NA#31185 Mod
                // if (svcMachMstrStsCd != null && !avalStsList.contains(svcMachMstrStsCd)) {
                if (!hasShippedLine && svcMachMstrStsCd != null && !avalStsList.contains(svcMachMstrStsCd)) {
                // QC#61894 K.ikeda Add END 
                    // 2018/09/25 S21_NA#28482 Mod End
                // QC#27485 2018/07/27 Add End
                    NWZC150001Common.setMsgId2(pMsg2, NWZC150001Constant.NWZM2266E);
                    ret = true;
                }
                // QC#28772 2018/10/16 Add Start
                if(isExistConfigInDetail(pMsg, configPMsg)){
                    NWZC150001Common.setMsgId2(pMsg2, NWZC150001Constant.NWZM2286E);
                    ret = true;
                }
                // QC#28772 2018/10/16 Add End
            }
            // QC#24245 2018/06/13 Add End
            if (ret) {
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                pMsg2List.add(pMsg2);
            }
        }
        return ret;
    }
    
    // 2018/10/16 S21_NA#28727 Add Start
    private static boolean checkMachUsgSts(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {
        String machUsgSts = NWXC150001DsCheck.getMachineUsageStatus(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
        if (S21StringUtil.isEquals(configPMsg.configTpCd.getValue(), CONFIG_TP.ADD_TO_CONFIG) && S21StringUtil.isEquals(machUsgSts, SVC_MACH_USG_STS.IN_TRANSIT)) {
            return true;
        }
        if (S21StringUtil.isEquals(machUsgSts, SVC_MACH_USG_STS.AT_CUSTOMER)) {
            return true;
        }
        return false;
    }
    // 2018/10/16 S21_NA#28727 Add End

    // 2018/06/18 S21_NA#24294 Mod Start
    // private static List<MDSETMsg> getMdseTMsgList(NWZC150001PMsg pMsg) {
    private static List<MDSETMsg> getMdseTMsgList(NWZC150001PMsg pMsg, NWZC150001DsCpoCommonBean commonBean) {
    // 2018/06/18 S21_NA#24294 Mod End
        List<MDSETMsg> mdseList = new ArrayList<MDSETMsg>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            MDSETMsg mdseTMsg //
            = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), pMsg.A.no(i).mdseCd_A1.getValue());
            if (mdseTMsg == null) {
                mdseTMsg = new MDSETMsg();
            }
            mdseList.add(mdseTMsg);
        }
        commonBean.setMdseInfoList(mdseList); // 2018/06/18 S21NA#24294 Add
        return mdseList;
    }

    private static List<MDSETMsg> getMdseTMsgRtrnList(NWZC150001PMsg pMsg) {
        List<MDSETMsg> mdseList = new ArrayList<MDSETMsg>();
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            MDSETMsg mdseTMsg //
            = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), pMsg.rtnDtl.no(i).mdseCd_B1.getValue());
            if (mdseTMsg == null) {
                mdseTMsg = new MDSETMsg();
            } else { // 2017/11/14 S21_NA#22216 Add Start
                boolean hasSerNum = ZYPCommonFunc.hasValue(pMsg.rtnDtl.no(i).serNum_B1);
                boolean isShpgSer = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue());
                if (!isShpgSer && hasSerNum) {
                    pMsg.rtnDtl.no(i).serNum_B1.clear();
                }
            }
            // 2017/11/14 S21_NA#22216 Add End
            mdseList.add(mdseTMsg);
        }
        return mdseList;
    }

    // 2018/06/18 S21_NA#24294 Del Start
//    private static void getDsMdseInfoTMsgList(NWZC150001PMsg pMsg, List<MDSETMsg> mdseTMsgList, NWZC150001DsCpoCommonBean commonBean) {
//
//        List<MDSETMsg> dsMdseInfoList = new ArrayList<MDSETMsg>(0);
//        for (MDSETMsg mdseTMsg : mdseTMsgList) {
//            MDSETMsg dsMdseInfoMsg = new MDSETMsg();
//            ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.glblCmpyCd, pMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.mdseCd, mdseTMsg.mdseCd);
//            dsMdseInfoMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoMsg);
//            if (dsMdseInfoMsg == null) {
//                dsMdseInfoMsg = new MDSETMsg();
//            }
//            dsMdseInfoList.add(dsMdseInfoMsg);
//        }
//        commonBean.setDsMdseInfoList(dsMdseInfoList);
//    }
    // 2018/06/18 S21_NA#24294 Del End

    private static boolean checkAvailavleSlsCr(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, S21ApiMessageIdMgr msgIdMgr) {

        boolean isModeSubmit = S21StringUtil.isEquals(MODE_SUBMIT, pMsg.xxModeCd.getValue());
        boolean isModeCancel = S21StringUtil.isEquals(MODE_CANCEL, pMsg.xxModeCd.getValue());
        if (!isModeSubmit || isModeCancel) {
            return false;
        }
        boolean isError = false;

        for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(i);
            // 2017/12/18 S21_NA#23162 Add Start
            if (S21StringUtil.isEquals(NWZC150001Constant.RQST_TP_SLS_CR_DELETE, cpoSlsCrPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            // 2017/12/18 S21_NA#23162 Add End
            if (!NWZC150001Common.isAvalableTocCd(pMsg.glblCmpyCd.getValue(), cpoSlsCrPMsg.slsRepCd.getValue())) {
                isError = true;
                String dsOrdPosnNum = cpoSlsCrPMsg.dsOrdPosnNum.getValue();
                if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                    if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, cpoSlsCrPMsg.configCatgCd.getValue())) {
                        NWZC150001Common.addMsgId2List(pMsg2List, dsOrdPosnNum, NWZM2033E);
                    } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, cpoSlsCrPMsg.configCatgCd.getValue())) {
                        NWZC150001Common.addMsgId3List(pMsg3List, dsOrdPosnNum, NWZM2033E);
                    }
                } else {
                    msgIdMgr.addXxMsgId(NWZM2033E, pMsg);
                }
            }
        }
        return isError;
    }
    // QC#28772 2018/10/16 Add Start
    private static boolean isExistConfigInDetail(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {
        boolean hitFlg = false;
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        List<Map<String, Object>> resultList = NWZC150001Query.getInstance().getSvcConfig(pMsg, configPMsg.svcConfigMstrPk.getValue());
        boolean hasIB = false; // 2019/03/29 S21_NA#30978 Add
        for (Map<String, Object> map : resultList) {
            hitFlg = false;
            BigDecimal svcMachMstrPk = (BigDecimal) map.get("SVC_MACH_MSTR_PK");
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                if (!S21StringUtil.isEquals(dsOrdPosnNum, pMsg.A.no(i).dsOrdPosnNum_A1.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(pMsg.A.no(i).svcMachMstrPk_A1.getValue())) {
                    hasIB = true; // 2019/03/29 S21_NA#30978 Add
                    if (svcMachMstrPk.compareTo(pMsg.A.no(i).svcMachMstrPk_A1.getValue()) == 0) {
                        hitFlg = true;
                        break;
                    }
                }
            }
            // if (!hitFlg) { // 2019/03/29 S21_NA#30978 Mod
            if (!hitFlg && hasIB) {
                return true;
            }
        }
        
        // 2019/03/29 S21_NA#30978 Add Start
        if (resultList.size() > 0 &&  !hasIB && !MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            return true;
        }
        // 2019/03/29 S21_NA#30978 Add End
        return false;
    }
    // QC#28772 2018/10/16 Add End
    // 2019/04/24 S21_NA#31185 Add Start
    /**
     * OutBound after shipped Line(CPO_DTLTMsg) existing check
     * @param glblCmpyCd Global Company Code
     * @param cpoOrdNum Order Number
     * @param dsOrdPosnNum Order Position Number
     * @return boolean
     */
    private static boolean isExistShippedLine(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum) {

        if (dsOrdPosnNum == null || glblCmpyCd == null || dsOrdPosnNum == null) {
            return false;
        }

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("502");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        cpoDtlTMsg.setConditionValue("dsOrdPosnNum01", dsOrdPosnNum);
        CPO_DTLTMsgArray resultList = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(cpoDtlTMsg);

        CPO_DTLTMsg resultMsg = null;
        for (int i = 0; i < resultList.getValidCount(); i++) {
            resultMsg = resultList.no(i);
            if (ZYPCommonFunc.hasValue(resultMsg.svcMachMstrPk) &&
                    (S21StringUtil.isEquals(ORD_LINE_STS.SHIPPED, resultMsg.ordLineStsCd.getValue()) || S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, resultMsg.ordLineStsCd.getValue()))) {
                return true;
            }
        }
        return false;
    }
    // 2019/04/24 S21_NA#31185 Add End
}
