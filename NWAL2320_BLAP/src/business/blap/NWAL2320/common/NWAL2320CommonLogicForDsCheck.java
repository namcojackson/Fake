package business.blap.NWAL2320.common;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2200.NWAL2200Query;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.blap.NWAL2320.NWAL2320Query;
import business.blap.NWAL2320.bean.NWAL2320_ImptConfigBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptDetailBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptHeaderBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptRtrnDetailBean;
import business.blap.NWAL2320.constant.NWAL2320Constant;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 * 2016/12/28   Fujitsu         W.Honda         Update          QC#16836
 * 2017/02/03   Fujitsu         M.Ohno          Update          QC#17407
 * 2017/02/16   Fujitsu         N.Aoyama        Update          QC#16575
 * 2017/11/21   Fujitsu         K.Ishizuka      Update          QC#22195
 * 2017/12/05   Fujitsu         K.Ishizuka      Update          QC#22195
 * 2018/06/01   Fujitsu         M.Ohno          Update          QC#26273
 * 2018/12/14   Fujitsu         K.Kato          Update          QC#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          QC#29241
 *</pre>
 */
public class NWAL2320CommonLogicForDsCheck {

    /**
     * dsCheck
     * @param bizMsg NWAL2320CMsg
     * @param imptBeansMap LinkedHashMap<String, NWAL2320_ImptHeaderBean>
     * @return boolean
     */
    public static boolean dsCheck(NWAL2320CMsg bizMsg, LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap) {
        boolean isSuccess = true;
        for (NWAL2320_ImptHeaderBean hdrBean : imptBeansMap.values()) {

            otherCheck(bizMsg, hdrBean);

            if (hdrBean.hasError()) {
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    private static void otherCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        otherHeaderCheck(bizMsg, hdrBean);

        if (hdrBean.isUpldTpSlsOrd()) {
            // QC#16750
            // Configuration/Detail count
            if (!hdrBean.isUpldTpNew()) { // 2017/02/03 S21_NA#17407 Add
                checkConfigCntForOutbound(bizMsg, hdrBean);
            }

            List< String > coaMdseTpList = NWAL2320Query.getInstance().getCoaMdseTpList(bizMsg.glblCmpyCd.getValue());
            for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
                otherDetailCheck(bizMsg, hdrBean, dtlBean);
                // 2019/01/08 QC#29241 Mod Start
                //otherQtyCheck(bizMsg, dtlBean, coaMdseTpList);
                otherQtyCheck(bizMsg, hdrBean, dtlBean, coaMdseTpList);
                // 2019/01/08 QC#29241 Mod End
            }
            otherEasyPackCheck(bizMsg, hdrBean);
        } else {
            // QC#16750
            // Return Configuration/Detail count
            if (!hdrBean.isUpldTpNew()) { // 2017/02/03 S21_NA#17407 Add
                checkConfigCntForInbound(bizMsg, hdrBean);
            }

            for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
                otherReturnDetailCheck(bizMsg, hdrBean, rtnDtlBean);
            }
            otherEasyPackCheckForReturn(bizMsg, hdrBean);
        }
    }

    private static void otherHeaderCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(imptTMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdTpCd.getValue(), imptTMsg.dsOrdCatgCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1450E));
        }

        if (NWXC150001DsCheck.checkCpoOrdTpAndDsOrdRsnRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.dsOrdTpCd.getValue(), imptTMsg.dsOrdRsnCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1451E));
        }

        if (NWXC150001DsCheck.checkCarrSvcLvlAndShpgSvcLvlRelation(
                bizMsg.glblCmpyCd.getValue(), imptTMsg.shpgSvcLvlCd.getValue(), imptTMsg.carrSvcLvlCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1457E));
        }

        // 2018/12/12 QC#29315 Mod Start
        //if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.dsOrdTpCd.getValue(), imptTMsg.frtCondCd.getValue(), imptTMsg.shpgSvcLvlCd.getValue(), imptTMsg.carrSvcLvlCd.getValue())) {
        if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(bizMsg.glblCmpyCd.getValue()
        		                                       , bizMsg.slsDt.getValue()
        		                                       , imptTMsg.dsOrdTpCd.getValue()
        		                                       , imptTMsg.frtCondCd.getValue()
        		                                       , imptTMsg.shpgSvcLvlCd.getValue()
        		                                       , imptTMsg.carrSvcLvlCd.getValue()
        		                                       , imptTMsg.shipToCustAcctCd.getValue()
        		                                       , NWAL2320Query.getInstance().getLocNum(bizMsg.glblCmpyCd.getValue(), imptTMsg.shipToCustCd.getValue()))) {
        // 2018/12/12 QC#29315 Mod End
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1458E));
        }

        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(bizMsg.glblCmpyCd.getValue(), imptTMsg.carrAcctNum.getValue(), imptTMsg.frtCondCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1459E));
        }

        if (NWXC150001DsCheck.checkSalesRepRelation(bizMsg.glblCmpyCd.getValue(), imptTMsg.slsRepTocCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1463E));
        }

        if (NWXC150001DsCheck.checkPrcCatgRelation(bizMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdTpCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.prcCatgCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1464E));
        }

        if (NWXC150001DsCheck.checkFlPrcListRelation(bizMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdTpCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.flPrcListCd.getValue())) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1465E));
        }
    }

    private static void otherDetailCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean, NWAL2320_ImptDetailBean dtlBean) {
        DS_IMPT_ORD_DTLTMsg dtlTMsg = dtlBean.me;
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;

        if (NWXC150001DsCheck.checkDetailMdseRelation(
                                        bizMsg.glblCmpyCd.getValue()
                                        , dtlTMsg.mdseCd.getValue()
                                        , dtlTMsg.custMdseCd.getValue()
                                        , imptTMsg.sellToCustCd.getValue())) {
            dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1468E));
        }

        if (NWXC150001DsCheck.checkDetailLineCatgRelation(
                                        bizMsg.glblCmpyCd.getValue()
                                        , dtlTMsg.dsOrdLineCatgCd.getValue()
                                        , imptTMsg.dsOrdTpCd.getValue())) {
            dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1469E));
        }

        if (NWXC150001DsCheck.checkRetailWhRelation(
                                        bizMsg.glblCmpyCd.getValue()
                                        , bizMsg.slsDt.getValue()
                                        , imptTMsg.dsOrdTpCd.getValue()
                                        , dtlTMsg.rtlWhCd.getValue()
                                        , dtlTMsg.rtlSwhCd.getValue())) {
            dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1470E));
        }

        if (dtlBean.mdseTMsg != null) {
            if (NWXC150001DsCheck.checkDealWh(bizMsg.glblCmpyCd.getValue(), dtlTMsg.rtlWhCd.getValue(), dtlTMsg.dsOrdLineCatgCd.getValue(), dtlBean.mdseTMsg.invtyCtrlFlg.getValue())) {
                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1478E));
            }
        }
    }

    private static void otherReturnDetailCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean, NWAL2320_ImptRtrnDetailBean rtnDtlBean) {
        DS_IMPT_ORD_RTRN_DTLTMsg rtnDtlTMsg = rtnDtlBean.me;
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;

        if (NWXC150001DsCheck.checkDetailMdseRelation(
                                        bizMsg.glblCmpyCd.getValue()
                                        , rtnDtlTMsg.mdseCd.getValue()
                                        , rtnDtlTMsg.custMdseCd.getValue()
                                        , imptTMsg.sellToCustCd.getValue())) {
            rtnDtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1468E));
        }

        if (NWXC150001DsCheck.checkDetailLineCatgRelation(
                                        bizMsg.glblCmpyCd.getValue()
                                        , rtnDtlTMsg.dsCpoLineCatgCd.getValue()
                                        , imptTMsg.dsOrdTpCd.getValue())) {
            rtnDtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1469E));
        }

        //2017/12/05 QC#22195 DEL START
        //if(!hdrBean.isUpldTpNew()){ // 2017/11/21 QC#22195 ADD
        // 2017/02/16 QC#16575 UPD START
        //    Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(
        //                                    bizMsg.glblCmpyCd.getValue()
        //                                    , rtnDtlTMsg.serNum.getValue());
//            Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(
//                    bizMsg.glblCmpyCd.getValue()
//                    , rtnDtlTMsg.serNum.getValue()
//                    , rtnDtlTMsg.mdseCd.getValue());
            // 2017/02/16 QC#16575 UPD E N D
            //if (map == null) {
                //rtnDtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1471E));
            //}
        //}  // 2017/11/21 QC#22195 ADD
        //2017/12/05 QC#22195 DEL END
    }

    // 2019/01/08 QC#29241 Mod Start
    //private static void otherQtyCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptDetailBean dtlBean, List< String > coaMdseTpList) {
    private static void otherQtyCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean, NWAL2320_ImptDetailBean dtlBean, List< String > coaMdseTpList) {
    // 2019/01/08 QC#29241 Mod End
        DS_IMPT_ORD_DTLTMsg dtlTMsg = dtlBean.me;
        MDSETMsg mdseTMsg = dtlBean.mdseTMsg;
        // 2019/01/08 QC#29241 Add Start
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        // 2019/01/08 QC#29241 Add End

        if (mdseTMsg == null) {
            return;
        }

        BigDecimal qty = dtlTMsg.ordQty.getValue();

        String coaMdseTpCd = NWXC150001DsCheck.getCoaMdseTp(bizMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue());
        if (NWXC150001DsCheck.checkMachQty(coaMdseTpList, coaMdseTpCd, qty)) {
            dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1483E));
        }
        if (NWXC150001DsCheck.checkLicenseItemQty(mdseTMsg.thirdPtyVndDropFlg.getValue(), qty)) {
            dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1485E));
        }
        // 2018/06/01 QC#26273 mod start
        // 2019/01/08 QC#29241 Mod Start
        if (NWXC150001DsCheck.checkOrdQtyVldFlg(bizMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdTpCd.getValue(), dtlTMsg.dsOrdLineCatgCd.getValue(), bizMsg.slsDt.getValue())) {
            if (NWXC150001DsCheck.checkMinOrdQty(mdseTMsg.cpoMinOrdQty.getValue(), qty)) {
//                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1488E));
                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWZM2023E, new String[] {String.valueOf(mdseTMsg.cpoMinOrdQty.getValueInt())}));
            }
            if (NWXC150001DsCheck.checkMaxOrdQty(mdseTMsg.cpoMaxOrdQty.getValue(), qty)) {
//                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1489E));
                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWZM2024E, new String[] {String.valueOf(mdseTMsg.cpoMaxOrdQty.getValueInt())}));
            }
            if (NWXC150001DsCheck.checkIncrOrdQty(mdseTMsg.cpoIncrOrdQty.getValue(), qty)) {
//                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1492E));
                dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWZM2025E, new String[] {String.valueOf(mdseTMsg.cpoIncrOrdQty.getValueInt())}));
            }
        }
        // 2019/01/08 QC#29241 Mod End
        // 2018/06/01 QC#26273 mod end
    }

    private static void otherEasyPackCheck(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        MDSETMsg mdseTMsg;

        boolean isEasyPack = NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdCatgCd.getValue(), imptTMsg.dsOrdTpCd.getValue());

        for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
            if (dtlBean.mdseTMsg == null) {
                continue;
            }

            mdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, dtlBean.mdseTMsg.mdseCd.getValue());
            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

            if (isEasyPack) {
                if (mdseTMsg == null || !ZYPCommonFunc.hasValue(mdseTMsg.easyPackTpCd)) {
                    dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1530E));
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.billToCustAcctCd.getValue(), imptTMsg.billToCustCd.getValue())) {
                    dtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1532E));
                }
            }
        }
    }

    private static void otherEasyPackCheckForReturn(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        MDSETMsg mdseTMsg;

        boolean isEasyPack = NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), imptTMsg.dsOrdCatgCd.getValue(), imptTMsg.dsOrdTpCd.getValue());

        for (NWAL2320_ImptRtrnDetailBean rtnDdtlBean : hdrBean.rtrnDtlBeanList) {

            mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), rtnDdtlBean.me.mdseCd.getValue());
            if (mdseTMsg == null) {
                continue;
            }

            if (isEasyPack) {
                if (!ZYPCommonFunc.hasValue(mdseTMsg.easyPackTpCd)) {
                    rtnDdtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1530E));
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), imptTMsg.billToCustAcctCd.getValue(), imptTMsg.billToCustCd.getValue())) {
                    rtnDdtlBean.addErrorInfo(new NWXC220001ErrorInfo(NWZC150001Constant.NWZM1532E));
                }
            }
        }
    }

    // QC#16750
    private static void checkConfigCntForOutbound(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        Map<String, NWAL2320_ImptConfigBean> configBeanMap = hdrBean.configBeanMap;
        BigDecimal dsOrdPosnNum = BigDecimal.ZERO;

        S21SsmEZDResult configSsmResult = NWAL2320Query.getInstance().getMaxDsOrdPosnNum(bizMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum, CONFIG_CATG.OUTBOUND);

        if (configSsmResult.isCodeNormal()) {
            dsOrdPosnNum = NWXC220001Util.convToBigDecimal((String) configSsmResult.getResultObject(), BigDecimal.ZERO);
        }

        if (NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT < configBeanMap.size() + dsOrdPosnNum.intValue()) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT)}));
            return;
        }

        return;
    }

    // QC#16750
    private static void checkConfigCntForInbound(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        Map<String, NWAL2320_ImptConfigBean> configBeanMap = hdrBean.configBeanMap;
        BigDecimal dsOrdPosnNum = BigDecimal.ZERO;

        S21SsmEZDResult configSsmResult = NWAL2320Query.getInstance().getMaxDsOrdPosnNum(bizMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum, CONFIG_CATG.INBOUND);

        if (configSsmResult.isCodeNormal()) {
            dsOrdPosnNum = NWXC220001Util.convToBigDecimal((String) configSsmResult.getResultObject(), BigDecimal.ZERO);
        }

        if (NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT < configBeanMap.size() + dsOrdPosnNum.intValue()) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT)}));
            return;
        }

        return;
    }

}
