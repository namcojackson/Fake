/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC203001;

import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.CR_CARD_ACCESS_MODE_DEV;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.CR_CARD_ACCESS_MODE_LOG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM1071E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM1084E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM1085E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM1086E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM2307E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM2308E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM2309E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.NWZM2314E;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_ADD_PRFL_ORD_NUM_A;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6001;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6001_PROC_STS_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6001_RESP_CODE_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6002;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6002_PROC_STS_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6002_RESP_CODE_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6003;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6003_PRFL_PROC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6003_PRFL_PROC_STS_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_ERR_6003_PROC_STS_MSG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_AMT_REQ_100;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_APVL_STS_NUM_1;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_PRFL_ACT_PRFL_CHNG;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_PROC_STS_CD_0;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_TRNSF_TP_A;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_TRNSF_TP_AC;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PMTC_TRNSF_TP_R;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PROC_MODE_GET_AUTH;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PROC_MODE_REFUND;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PROC_MODE_SETTLE;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PSM_DO_NOT_HONOR;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PSM_INSUFFICIENT_FUNDS;
import static com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant.PSM_OVER_THE_LIMIT;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.S21CreditCardProcessingCpgmServiceProxy;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.MarkForCaptureElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.MarkForCaptureResponseElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderResponseElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.PC3LineItem;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.ProfileChangeElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.ProfileResponseElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.ReversalElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.ReversalResponseElement;


/**
 * <pre> 
 *  Credit Card Validation API.
 * This class defines the paymentech access used in the api application
 * program of BusinessID NWZC203001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------
 * 11/20/2015   Fujitsu         C.Yokoi         Create          N/A
 * 06/17/2015   Fujitsu         H.Nagashima     Update          QC#9764
 * 03/01/2017   Fujitsu         Y.Kanefusa      Update          S21_NA#17727
 * 05/21/2018   Fujitsu         A.Kosai         Update          S21_NA#25797
 * 2019/07/25   Hitachi         H.Umeda         Update          QC#51638
 * 2020/05/28   Fujitsu         C.Hara          Update          QC#56866
 * 2021/11/26   CITS            K.Ogino         Update          QC#59458
 * 2022/04/05   CITS            K.Suzuki        Update          QC#59860
 *</pre>
 */
public class NWZC203001PmtcHelper {

    // START 2022/04/05 K.Suzuki [QC#59860,ADD]
    private boolean timeoutExceptionFlg = false;
    // END   2022/04/05 K.Suzuki [QC#59860,ADD]
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWZC203001PmtcHelper() {
        // START 2022/04/05 K.Suzuki [QC#59860,ADD]
        // Initialize
        timeoutExceptionFlg = false;
        // END   2022/04/05 K.Suzuki [QC#59860,ADD]
    }

    /**
     * Profile Change
     * @param msgMap Message Map
     * @param pmtcBean NWZC203001 Paymentech Bean
     */
    public void prflChng(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean) {
        ProfileChangeElement reqBean = new ProfileChangeElement();
        ProfileResponseElement resBean = null;

        try {

            reqBean.setVersion(pmtcBean.getPmtcVrsnCd());
            reqBean.setBin(pmtcBean.getPmtcBinNum());
            reqBean.setMerchantID(pmtcBean.getPmtcMrcntId());
            reqBean.setCcExp(pmtcBean.getPmtcExprDtTxt());
            reqBean.setCustomerRefNum(pmtcBean.getPmtcCustRefNum());

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcReqLog(reqBean);
                println(pmtcBean.toString());
            }

            if (CR_CARD_ACCESS_MODE_DEV.equals(pmtcBean.getPmtcAccesMode())) {
                resBean = getResBeanForDevEnv(reqBean);
            } else {
                // 2018/05/21 S21_NA#25797 Mod Start
//                S21CreditCardProcessingServiceProxy s21CreditCardProcessingServiceProxy = new S21CreditCardProcessingServiceProxy();
//                resBean = s21CreditCardProcessingServiceProxy.profileChange(reqBean);
                S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
                resBean = s21CreditCardProcessingCpgmServiceProxy.profileChange(reqBean);
                // 2018/05/21 S21_NA#25797 Mod End
            }

            if (resBean == null) {

                addMsgId(msgMap, NWZM1084E);
                return;
            } else if (!ZYPCommonFunc.hasValue(resBean.getProcStatus())) {

                printPmtcResLog(resBean);
                addMsgId(msgMap, NWZM1084E);
                return;
            }

            // set response
            pmtcBean.setPmtcProcStsCd(resBean.getProcStatus());
            pmtcBean.setPmtcProcStsMsgTxt(resBean.getProcStatusMessage());

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcResLog(resBean);
            }
        } catch (Throwable e) {

            addMsgId(msgMap, NWZM1084E);

            if (resBean == null) {
                resBean = new ProfileResponseElement();
            }

            resBean.setProcStatus(BigDecimal.ZERO.subtract(BigDecimal.ONE).toString());
            resBean.setProcStatusMessage(e.getMessage());

            // set response
            pmtcBean.setPmtcProcStsCd(resBean.getProcStatus());
            pmtcBean.setPmtcProcStsMsgTxt(resBean.getProcStatusMessage());

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                println(pmtcBean.toString());
                printPmtcReqLog(reqBean);
            }

            println(e.getMessage());
        }
    }

    private static void println(String message) {
        // Abends if message is null or empty.
        if (ZYPCommonFunc.hasValue(message)) {

            S21InfoLogOutput.println(message);
        }
    }

    private ProfileResponseElement getResBeanForDevEnv(ProfileChangeElement reqBean) {

        ProfileResponseElement resBean = new ProfileResponseElement();
//        resBean.setVersion(reqBean.getVersion());
        resBean.setBin(reqBean.getBin());
        resBean.setMerchantID(reqBean.getMerchantID());
        resBean.setCcExp(reqBean.getCcExp());
        resBean.setCustomerRefNum(reqBean.getCustomerRefNum());
        resBean.setProfileAction(PMTC_PRFL_ACT_PRFL_CHNG);
        resBean.setProcStatus(PMTC_PROC_STS_CD_0);
        resBean.setProcStatusMessage("Profile Request Processed");

        return resBean;
    }

    private static String toString(Object object) {
        if (object == null) {
            return "null";
        }

        StringBuilder buf = new StringBuilder();
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            buf.append(object.getClass().getSimpleName());
            buf.append("{");

            for (Field field : fields) {
                buf.append(field.getName());
                buf.append("=");
                buf.append(field.get(object));
                buf.append(", ");
            }

            buf.append("}");
        } catch (Throwable e) {
            buf.setLength(0);
        }
        return buf.toString();
    }

    private void printPmtcReqLog(ProfileChangeElement reqBean) {
        println(toString(reqBean));
    }

    private void printPmtcResLog(ProfileResponseElement resBean) {
        println(toString(resBean));
    }

    /**
     * <pre>
     * The credit card validation processing is executed. 
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param pmtcBean NWZC203001 Paymentech Bean
     */
    public void validCrCard(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean) {
        NewOrderRequestElement reqBean = new NewOrderRequestElement();
        NewOrderResponseElement respBean = null;
        // START 2022/04/05 K.Suzuki [QC#59860,ADD]
        boolean remoteExceptionFlg = false;
        // END   2022/04/05 K.Suzuki [QC#59860,ADD]

        try {
            // set request
            reqBean.setVersion(pmtcBean.getPmtcVrsnCd());
            reqBean.setBin(pmtcBean.getPmtcBinNum());
            reqBean.setMerchantID(pmtcBean.getPmtcMrcntId());
            reqBean.setTransType(pmtcBean.getPmtcTrxTpCd());
            reqBean.setIndustryType(pmtcBean.getPmtcIndyTp());
            reqBean.setTerminalID(pmtcBean.getPmtcTrmId());
            reqBean.setCcAccountNum("");

            reqBean.setUseCustomerRefNum(pmtcBean.getPmtcCustRefNum());
            reqBean.setProfileOrderOverideInd(pmtcBean.getPmtcPrflOrdOvrdCd());
            reqBean.setOrderID(pmtcBean.getPmtcOrdId());
            
            if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthAmt())) {
//                reqBean.setAmount(pmtcBean.getPmtcAuthAmt().multiply(PMTC_AMT_REQ_100).setScale(0).negate().toString());
                reqBean.setAmount(pmtcBean.getPmtcAuthAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
//            } else {
//                reqBean.setAmount(BigDecimal.ZERO.toString());
            }

            // Add QC#59458 Start
            NWZC203001PMsg pMsg = (NWZC203001PMsg) msgMap.getPmsg();
            String varChar = ZYPCodeDataUtil.getVarCharConstValue("NWZC2030_L2L3_SEND_FLG", pMsg.glblCmpyCd.getValue());
            if (!ZYPCommonFunc.hasValue(varChar)) {
                varChar = ZYPConstant.FLG_ON_Y;
            }
            if (ZYPConstant.FLG_ON_Y.equals(varChar)) {
            // Add QC#59458 End
                // if (!PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd())) {  2020/05/28 QC#56866 Del
                reqBean.setTaxInd(pmtcBean.getPmtcTaxIndNum());

                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthTaxAmt())) {
                    // reqBean.setTaxAmount(pmtcBean.getPmtcAuthTaxAmt().multiply(PMTC_AMT_REQ_100).setScale(0).negate().toString());
                    reqBean.setTaxAmount(pmtcBean.getPmtcAuthTaxAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                    // } else {
                    // reqBean.setTaxAmount(BigDecimal.ZERO.toString());
                }

                // 2020/05/28 QC#56866 Add Start
                reqBean.setPCardOrderID(pmtcBean.getPmtcAuthTrxNum());
                reqBean.setPCardDestZip(pmtcBean.getPmtcAuthPostCd());
                reqBean.setPCardDestName(pmtcBean.getPmtcAuthHldNm());
                reqBean.setPCardDestAddress(pmtcBean.getPmtcAuthAddr());
                reqBean.setPCardDestCity(pmtcBean.getPmtcAuthCtyAddr());
                reqBean.setPCardDestStateCd(pmtcBean.getPmtcAuthStCd());
                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthFrtAmt())) {
                    reqBean.setPCard3FreightAmt(pmtcBean.getPmtcAuthFrtAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                }
                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthDtyAmt())) {
                    reqBean.setPCard3DutyAmt(pmtcBean.getPmtcAuthDtyAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                }
                reqBean.setPCard3DestCountryCd(pmtcBean.getPmtcAuthCtryCd());
                reqBean.setPCard3ShipFromZip(pmtcBean.getPmtcAuthFromZipCd());
                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthDiscAmt())) {
                    reqBean.setPCard3DiscAmt(pmtcBean.getPmtcAuthDiscAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                }
                reqBean.setPCard3AltTaxInd(pmtcBean.getPmtcAuthAltTaxId());
                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthAltTaxAmt())) {
                    reqBean.setPCard3AltTaxAmt(pmtcBean.getPmtcAuthAltTaxAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                }
                if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthLineItemCnt())) {
                    reqBean.setPCard3LineItemCount(pmtcBean.getPmtcAuthLineItemCnt().toString());
                }

                PC3LineItem[] PCard3LineItems = new PC3LineItem[pmtcBean.getPmtcDtlList().size()];
                for (int i = 0; i < pmtcBean.getPmtcDtlList().size(); i++) {
                    NWZC203001PmtcDtlBean pmtcDtlBean = pmtcBean.getPmtcDtlList().get(i);
                    PC3LineItem reqDtlBean = new PC3LineItem();

                    reqDtlBean.setPCard3DtlIndex(pmtcDtlBean.getPmtcDtlLineNum());
                    reqDtlBean.setPCard3DtlDesc(pmtcDtlBean.getPmtcAuthMdseNm());
                    reqDtlBean.setPCard3DtlProdCd(pmtcDtlBean.getPmtcAuthProdCd());
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmtcAuthOrdQty())) {
                        reqDtlBean.setPCard3DtlQty(pmtcDtlBean.getPmtcAuthOrdQty().toString());
                    }
                    reqDtlBean.setPCard3DtlUOM(pmtcDtlBean.getPmtcAuthUomCd());
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmtcAuthDtlTaxAmt())) {
                        reqDtlBean.setPCard3DtlTaxAmt(pmtcDtlBean.getPmtcAuthDtlTaxAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                    }
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmctAuthDtlTaxPct())) {
                        reqDtlBean.setPCard3DtlTaxRate(pmtcDtlBean.getPmctAuthDtlTaxPct().toString());
                    }
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmtcAuthDtlAmt())) {
                        reqDtlBean.setPCard3Dtllinetot(pmtcDtlBean.getPmtcAuthDtlAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                    }
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmtcAuthDtlDiscAmt())) {
                        reqDtlBean.setPCard3DtlDisc(pmtcDtlBean.getPmtcAuthDtlDiscAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                    }
                    reqDtlBean.setPCard3DtlCommCd(pmtcDtlBean.getPmtcAuthCmdtyCd());
                    if (ZYPCommonFunc.hasValue(pmtcDtlBean.getPmtcAuthUnitPrcAmt())) {
                        reqDtlBean.setPCard3DtlUnitCost(pmtcDtlBean.getPmtcAuthUnitPrcAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
                    }
                    reqDtlBean.setPCard3DtlGrossNet(pmtcDtlBean.getPmtcAuthGrsNetInd());
                    reqDtlBean.setPCard3DtlTaxType(pmtcDtlBean.getPmtcAuthTaxTpCd());
                    reqDtlBean.setPCard3DtlDiscInd(pmtcDtlBean.getPmtcAuthDiscInd());

                    PCard3LineItems[i] = reqDtlBean;
                }
                reqBean.setPCard3LineItems(PCard3LineItems);
                // 2020/05/28 QC#56866 Add End
            }

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcReqLog(reqBean);
                S21InfoLogOutput.println(pmtcBean.toString());
                S21InfoLogOutput.println(reqBean.toString());
            }

            // START 2022/04/05 K.Suzuki [QC#59860,ADD]
            try {
                // END   2022/04/05 K.Suzuki [QC#59860,ADD]
                if (CR_CARD_ACCESS_MODE_DEV.equals(pmtcBean.getPmtcAccesMode())) {
                    // todo:use only development environment.
                    respBean = getRespBeanForDevEnv(reqBean);
                } else {

                    // create proxy
                    // 2018/05/21 S21_NA#25797 Mod Start
//                    S21CreditCardProcessingServiceProxy s21CreditCardProcessingServiceProxy = new S21CreditCardProcessingServiceProxy();
                    S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
                    // 2018/05/21 S21_NA#25797 Mod End

                    // call proxy authorization channel
                    // 2018/05/21 S21_NA#25797 Mod Start
//                    respBean = s21CreditCardProcessingServiceProxy.newOrder(reqBean);
                    respBean = s21CreditCardProcessingCpgmServiceProxy.newOrder(reqBean);
                    // 2018/05/21 S21_NA#25797 Mod End
                }
                // START 2022/04/05 K.Suzuki [QC#59860,ADD]
            } catch (RemoteException e) {
                S21InfoLogOutput.println("Catch RemoteException 1 (TimeoutException)");
                timeoutExceptionFlg = true;
                remoteExceptionFlg = true;
                this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
            }
            // END   2022/04/05 K.Suzuki [QC#59860,ADD]
            // START 2022/04/05 K.Suzuki [QC#59860,ADD]
            S21InfoLogOutput.println("After catch");
            // END   2022/04/05 K.Suzuki [QC#59860,ADD]

            if (respBean == null) {
                addMsgId(msgMap, NWZM1071E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            } else if (respBean.getProcStatus() == null) {
                addMsgId(msgMap, NWZM1071E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            }

            // set response
            pmtcBean.setPmtcProcStsCd(respBean.getProcStatus());
            pmtcBean.setPmtcApvlStsNum(respBean.getApprovalStatus());
            pmtcBean.setPmtcProcStsMsgTxt(respBean.getProcStatusMessage());
            //pmtcBean.setPmtcTrxRefIdxCd(respBean.getTxRefNum());
            pmtcBean.setPmtcTrxRefIdxCd(respBean.getTxRefIdx());

            //if (PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd()) && PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
            if (PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd()) || PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
                pmtcBean.setPmtcAuthRefNum(respBean.getTxRefNum());
            }

            //if (PROC_MODE_REFUND.equals(pmtcBean.getXxProcMd()) && PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
            if (PROC_MODE_REFUND.equals(pmtcBean.getXxProcMd()) || PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
                //pmtcBean.setPmtcTrxRefIdxCd(respBean.getTxRefNum());
                pmtcBean.setPmtcTrxRefIdxCd(respBean.getTxRefIdx());
            }
            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcRespLog(respBean);
            }

            if (!"1".equals(pmtcBean.getPmtcApvlStsNum()) || !"0".equals(pmtcBean.getPmtcProcStsCd())) {
                printPmtcRespLog(respBean);  // QC#17727 2017/03/01 Add Start
// START 2019/07/25 H.Umeda [QC#51638,MOD]
//              addMsgId(msgMap, NWZM1071E);
// Subdivide Message
                if( PSM_DO_NOT_HONOR.equals(respBean.getProcStatusMessage())){
                    addMsgId(msgMap, NWZM2307E);
                } else if( PSM_INSUFFICIENT_FUNDS.equals(respBean.getProcStatusMessage())){
                    addMsgId(msgMap, NWZM2308E);
                } else if( PSM_OVER_THE_LIMIT.equals(respBean.getProcStatusMessage())){
                    addMsgId(msgMap, NWZM2309E);
                } else {
                    addMsgId(msgMap, NWZM1071E);
                }
// END   2019/07/25 H.Umeda [QC#51638,MOD]
            }

//        } catch (ConfigurationException e) {
//            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (TargetSystemException e) {
//            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (RemoteException e) {
//            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (ResourceException e) {
//            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (DataException e) {
//            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
        } catch (Throwable e) {
            // START 2022/04/05 K.Suzuki [QC#59860,ADD]
            S21InfoLogOutput.println("Catch Throwable");
            if (remoteExceptionFlg) {
                timeoutExceptionFlg = true;
            }
            // END   2022/04/05 K.Suzuki [QC#59860,ADD]
            this.printErr(msgMap, pmtcBean, reqBean, respBean, e);
        }
    }

    /**
     * <pre>
     * The Mark of Capture processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param pmtcBean NWZC203001 Paymentech Bean
     */
    public void markForCapture(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean) {

        MarkForCaptureElement reqBean = new MarkForCaptureElement();
        MarkForCaptureResponseElement respBean = null;

        try {
            // set request
            reqBean.setBin(pmtcBean.getPmtcBinNum());
            reqBean.setMerchantID(pmtcBean.getPmtcMrcntId());
            reqBean.setTerminalID(pmtcBean.getPmtcTrmId());
            reqBean.setOrderID(pmtcBean.getPmtcOrdId());

            if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthAmt())) {
                reqBean.setAmount(pmtcBean.getPmtcAuthAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
            } else {
                reqBean.setAmount(BigDecimal.ZERO.toString());
            }
            reqBean.setTaxInd(pmtcBean.getPmtcTaxIndNum());
            if (ZYPCommonFunc.hasValue(pmtcBean.getPmtcAuthTaxAmt())) {
                reqBean.setTaxAmount(pmtcBean.getPmtcAuthTaxAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
            } else {
                reqBean.setTaxAmount(BigDecimal.ZERO.toString());
            }
            reqBean.setTxRefNum(pmtcBean.getPmtcAuthRefNum());

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcReqLog(reqBean);
                S21InfoLogOutput.println(pmtcBean.toString());
                S21InfoLogOutput.println(reqBean.toString());
            }

            if (CR_CARD_ACCESS_MODE_DEV.equals(pmtcBean.getPmtcAccesMode())) {
                // use only development environment.
                respBean = getMarkForCaptureResBeanForDevEnv(reqBean);

            } else {
                // create proxy
                // 2018/05/21 S21_NA#25797 Mod Start
//                S21CreditCardProcessingServiceProxy s21CreditCardProcessingServiceProxy = new S21CreditCardProcessingServiceProxy();
                S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
                // 2018/05/21 S21_NA#25797 Mod End

                // call proxy authorization channel
                // 2018/05/21 S21_NA#25797 Mod Start
//                respBean = s21CreditCardProcessingServiceProxy.markForCapture(reqBean);
                respBean = s21CreditCardProcessingCpgmServiceProxy.markForCapture(reqBean);
                // 2018/05/21 S21_NA#25797 Mod End
            }

            if (respBean == null) {
                addMsgId(msgMap, NWZM1085E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            } else if (respBean.getProcStatus() == null) {
                addMsgId(msgMap, NWZM1085E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            }

            // set response
            pmtcBean.setPmtcProcStsCd(respBean.getProcStatus());
            pmtcBean.setPmtcProcStsMsgTxt(respBean.getProcStatusMessage());
            pmtcBean.setPmtcTrxRefIdxCd(respBean.getTxRefIdx());
            // pmtcBean.setPmtcApvlStsNum(respBean.getApprovalStatus());

            if (CR_CARD_ACCESS_MODE_DEV.equals(pmtcBean.getPmtcAccesMode())) {
                if (PMTC_AMT_ERR_6001_PROC_STS_MSG.equals(respBean.getProcStatusMessage())) {
                    pmtcBean.setPmtcApvlStsNum("0");
                } else {
                    pmtcBean.setPmtcApvlStsNum(PMTC_APVL_STS_NUM_1);
                }
            }

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                S21InfoLogOutput.println(respBean.toString());
            }

//        } catch (ConfigurationException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (TargetSystemException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (RemoteException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (ResourceException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (DataException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (Exception e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
        } catch (Throwable e) {
            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
        }
    }

    /**
     * <pre>
     * The Reversal(VOID) processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param pmtcBean NWZC203001 Paymentech Bean
     */
    public void reversal(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean) {

        ReversalElement reqBean = new ReversalElement();
        ReversalResponseElement respBean = null;
        try {
            // set request
            reqBean.setVersion(pmtcBean.getPmtcVrsnCd());
            reqBean.setBin(pmtcBean.getPmtcBinNum());
            reqBean.setMerchantID(pmtcBean.getPmtcMrcntId());
            reqBean.setTerminalID(pmtcBean.getPmtcTrmId());
            reqBean.setOrderID(pmtcBean.getPmtcOrdId());
            reqBean.setTxRefNum(pmtcBean.getPmtcAuthRefNum());
            reqBean.setTxRefIdx(pmtcBean.getPmtcTrxRefIdxCd());
            // 2018/07/10 S21_NA#25797 Add Start
            reqBean.setAdjustedAmount(pmtcBean.getPmtcAuthAmt().multiply(PMTC_AMT_REQ_100).setScale(0).toString());
            // 2018/07/10 S21_NA#25797 Add End

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcReqLog(reqBean);
                S21InfoLogOutput.println(pmtcBean.toString());
                S21InfoLogOutput.println(reqBean.toString());
            }

            if (CR_CARD_ACCESS_MODE_DEV.equals(pmtcBean.getPmtcAccesMode())) {
                // use only development environment.
                respBean = getReversalResBeanForDevEnv(reqBean);
            } else {
                // create proxy
                // 2018/05/21 S21_NA#25797 Mod Start
//                S21CreditCardProcessingServiceProxy s21CreditCardProcessingServiceProxy = new S21CreditCardProcessingServiceProxy();
                S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
                // 2018/05/21 S21_NA#25797 Mod End

                // call proxy authorization channel
                // 2018/05/21 S21_NA#25797 Mod Start
//                respBean = s21CreditCardProcessingServiceProxy.reversal(reqBean);
                respBean = s21CreditCardProcessingCpgmServiceProxy.reversal(reqBean);
                // 2018/05/21 S21_NA#25797 Mod Emd
            }

            if (respBean == null) {
                addMsgId(msgMap, NWZM1086E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            } else if (respBean.getProcStatus() == null) {
                addMsgId(msgMap, NWZM1086E);
                if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                    S21InfoLogOutput.println(pmtcBean.toString());
                    S21InfoLogOutput.println(reqBean.toString());
                }
                return;
            }

            // set response
            pmtcBean.setPmtcProcStsCd(respBean.getProcStatus());
            pmtcBean.setPmtcProcStsMsgTxt(respBean.getProcStatusMessage());

            if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {
                printPmtcRespLog(respBean);
//                S21InfoLogOutput.println(respBean.toString());
            }

//        } catch (ConfigurationException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (TargetSystemException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (RemoteException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (ResourceException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
//        } catch (DataException e) {
//            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
        } catch (Throwable e) {
            this.setErrCode(msgMap, pmtcBean, reqBean, respBean, e);
        }
    }

    /**
     * <pre>
     * Print debug request message.
     * </pre>
     * @param reqBean debug NewOrderRequestElement
     */
    private void printPmtcReqLog(NewOrderRequestElement reqBean) {

        println("TransType:" + reqBean.getTransType());
        println("Version:" + reqBean.getVersion());
        println("IndustryType:" + reqBean.getIndustryType());
        println("Bin:" + reqBean.getBin());
        println("MerchantID:" + reqBean.getMerchantID());
        println("TerminalID:" + reqBean.getTerminalID());
        println("CardBrand:" + reqBean.getCardBrand());
        println("CcAccountNum:" + reqBean.getCcAccountNum());
        println("CcExp:" + reqBean.getCcExp());
        println("CcCardVerifyPresenceInd:" + reqBean.getCcCardVerifyPresenceInd());
        println("CcCardVerifyNum:" + reqBean.getCcCardVerifyNum());
        println("AvsZip:" + reqBean.getAvsZip());
        println("AvsAddress1:" + reqBean.getAvsAddress1());
        println("AvsAddress2:" + reqBean.getAvsAddress2());
        println("AvsCity:" + reqBean.getAvsCity());
        println("AvsState:" + reqBean.getAvsState());
        println("AvsName:" + reqBean.getAvsName());
        println("AvsCountryCode:" + reqBean.getAvsCountryCode());
        println("AvsPhone:" + reqBean.getAvsPhone());
        println("AddProfileFromOrder:" + reqBean.getAddProfileFromOrder());
        println("CustomerRefNum:" + reqBean.getCustomerRefNum());
        println("UseCustomerRefNum:" + reqBean.getUseCustomerRefNum());
        println("ProfileOrderOverideInd:" + reqBean.getProfileOrderOverideInd());
        println("OrderID:" + reqBean.getOrderID());
        println("Amount:" + reqBean.getAmount());
        println("Comments:" + reqBean.getComments());
        println("ShippingRef:" + reqBean.getShippingRef());
        println("TaxInd:" + reqBean.getTaxInd());
        println("TaxAmount:" + reqBean.getTaxAmount());
        println("AmexTranAdvAddn1:" + reqBean.getAmexTranAdvAddn1());
        println("AmexTranAdvAddn2:" + reqBean.getAmexTranAdvAddn2());
        println("AmexTranAdvAddn3:" + reqBean.getAmexTranAdvAddn3());
        println("TxRefNum:" + reqBean.getTxRefNum());
        println("RetryTrace:" + reqBean.getRetryTrace());
        println("PCardOrderID:" + reqBean.getPCardOrderID());
        println("PCardDestZip:" + reqBean.getPCardDestZip());
        println("PCardDestName:" + reqBean.getPCardDestName());
        println("PCardDestAddress:" + reqBean.getPCardDestAddress());
        println("PCardDestAddress2:" + reqBean.getPCardDestAddress2());
        println("PCardDestCity:" + reqBean.getPCardDestCity());
        println("PCardDestStateCd:" + reqBean.getPCardDestStateCd());
    }

    /**
     * <pre>
     * Print debug response message.
     * </pre>
     * @param reqBean debug NewOrderResponseElement
     */
    private void printPmtcRespLog(NewOrderResponseElement respBean) {

        println("Version:" + respBean.getVersion());
        println("IndustryType:" + respBean.getIndustryType());
        println("TransType:" + respBean.getTransType());
        println("Bin:" + respBean.getBin());
        println("MerchantID:" + respBean.getMerchantID());
        println("TerminalID:" + respBean.getTerminalID());
        println("CardBrand:" + respBean.getCardBrand());
        println("OrderID:" + respBean.getOrderID());
        println("TxRefNum:" + respBean.getTxRefNum());
        println("TxRefIdx:" + respBean.getTxRefIdx());
        println("RespDateTime:" + respBean.getRespDateTime());
        println("ProcStatus:" + respBean.getProcStatus());
        println("ApprovalStatus:" + respBean.getApprovalStatus());
        println("RespCode:" + respBean.getRespCode());
        println("AvsRespCode:" + respBean.getAvsRespCode());
        println("CvvRespCode:" + respBean.getCvvRespCode());
        println("AuthorizationCode:" + respBean.getAuthorizationCode());
        println("McRecurringAdvCode:" + respBean.getMcRecurringAdvCode());
        println("VisaVbVRespCode:" + respBean.getVisaVbVRespCode());
        println("ProcStatusMessage:" + respBean.getProcStatusMessage());
        println("RespCodeMessage:" + respBean.getRespCodeMessage());
        println("HostRespCode:" + respBean.getHostRespCode());
        println("HostAVSRespCode:" + respBean.getHostAVSRespCode());
        println("HostCVVRespCode:" + respBean.getHostCVVRespCode());
        println("RetryTrace:" + respBean.getRetryTrace());
        println("RetryAttempCount:" + respBean.getRetryAttempCount());
        println("LastRetryDate:" + respBean.getLastRetryDate());
        println("CustomerRefNum:" + respBean.getCustomerRefNum());
        println("CcAccountNum:" + respBean.getCcAccountNum());
        println("ProfileProcStatus:" + respBean.getProfileProcStatus());
        println("ProfileProcStatusMsg:" + respBean.getProfileProcStatusMsg());
        println("RemainingBalance:" + respBean.getRemainingBalance());
        println("RequestAmount:" + respBean.getRequestAmount());
        println("RedeemedAmount:" + respBean.getRedeemedAmount());
        println("DebitBillerReferenceNumber:" + respBean.getDebitBillerReferenceNumber());
    }

//QC#13099 add Start
    /**
     * <pre>
     * Print debug response message.
     * </pre>
     * @param reqBean debug ReversalResponseElement
     */
    private void printPmtcRespLog(ReversalResponseElement respBean) {

        println("Version:" + respBean.getVersion());
        println("OutstandingAmt:" + respBean.getOutstandingAmt());
        println("Bin:" + respBean.getBin());
        println("MerchantID:" + respBean.getMerchantID());
        println("TerminalID:" + respBean.getTerminalID());
        println("OrderID:" + respBean.getOrderID());
        println("TxRefNum:" + respBean.getTxRefNum());
        println("TxRefIdx:" + respBean.getTxRefIdx());
        println("RespDateTime:" + respBean.getRespDateTime());
        println("ProcStatus:" + respBean.getProcStatus());
        println("ProcStatusMessage:" + respBean.getProcStatusMessage());
        println("RetryTrace:" + respBean.getRetryTrace());
        println("RetryAttempCount:" + respBean.getRetryAttempCount());
        println("LastRetryDate:" + respBean.getLastRetryDate());
    }
//QC#13099 add End
    /**
     * <pre>
     * Set the message id.
     * </pre>
     * @param msgMap S21ApiMessage Map
     * @param msgId String setting value for Message ID
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);
        println("setMsgId:" + msgId);
    }

    /**
     * <pre>
     * print Error
     * </pre>
     * @param msgMap S21ApiMessage Map
     * @param pmtcBean NWZC203001PmtcBean
     * @param reqBean NewOrderRequestElement
     * @param respBean NewOrderResponseElement
     * @param e Exception
     */
//    private void printErr(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, NewOrderRequestElement reqBean, NewOrderResponseElement respBean, Exception e) {
    private void printErr(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, NewOrderRequestElement reqBean, NewOrderResponseElement respBean, Throwable e) {

        respBean = new NewOrderResponseElement();
        // START 2022/04/05 K.Suzuki [QC#59860,ADD]
        if (timeoutExceptionFlg) {
            addMsgId(msgMap, NWZM2314E);
        } else {
            // END   2022/04/05 K.Suzuki [QC#59860,ADD]
            addMsgId(msgMap, NWZM1071E);
            // START 2022/04/05 K.Suzuki [QC#59860,ADD]
        }
        timeoutExceptionFlg = false;
        // END   2022/04/05 K.Suzuki [QC#59860,ADD]
        respBean.setProcStatus(BigDecimal.ZERO.toString());
        respBean.setApprovalStatus(BigDecimal.ZERO.toString());
        respBean.setProcStatusMessage(e.getMessage());

        if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {

            println(pmtcBean.toString());
            println(reqBean.toString());
        }

        String enter = System.getProperty("line.separator");

        StringBuilder stackStr = new StringBuilder();

        stackStr.append(e.toString());
        stackStr.append(enter);

        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement emt : elements) {
            stackStr.append(emt.toString());
            stackStr.append(enter);
        }

        println(stackStr.toString());
    }

    /**
     * @param msgMap
     * @param pmtcBean
     * @param reqBean
     * @param respBean
     * @param e
     */
//    private void setErrCode(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, MarkForCaptureElement reqBean, MarkForCaptureResponseElement respBean, Exception e) {
    private void setErrCode(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, MarkForCaptureElement reqBean, MarkForCaptureResponseElement respBean, Throwable e) {

        addMsgId(msgMap, NWZM1085E);

        if (respBean == null) {
            respBean = new MarkForCaptureResponseElement();
        }

        respBean.setProcStatus(BigDecimal.ZERO.subtract(BigDecimal.ONE).toString());
        respBean.setProcStatusMessage(e.getMessage());

        // set response
        pmtcBean.setPmtcProcStsCd(respBean.getProcStatus());
        pmtcBean.setPmtcProcStsMsgTxt(respBean.getProcStatusMessage());

        if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {

            println(pmtcBean.toString());
            println(reqBean.toString());
        }

        infoLogStackTrace(e);
    }

    /**
     * @param msgMap
     * @param pmtcBean
     * @param reqBean
     * @param respBean
     * @param e
     */
//    private void setErrCode(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, ReversalElement reqBean, ReversalResponseElement respBean, Exception e) {
    private void setErrCode(S21ApiMessageMap msgMap, NWZC203001PmtcBean pmtcBean, ReversalElement reqBean, ReversalResponseElement respBean, Throwable e) {

        addMsgId(msgMap, NWZM1086E);

        if (respBean == null) {
            respBean = new ReversalResponseElement();
        }

        respBean.setProcStatus(BigDecimal.ZERO.subtract(BigDecimal.ONE).toString());

        // set response
        pmtcBean.setPmtcProcStsCd(respBean.getProcStatus());
        pmtcBean.setPmtcProcStsMsgTxt(respBean.getProcStatusMessage());

        if (CR_CARD_ACCESS_MODE_LOG.equals(pmtcBean.getPmtcAccesMode())) {

            println(pmtcBean.toString());
            println(reqBean.toString());
//QC#13099 add Start
            printPmtcRespLog(respBean);
//QC#13099 add End
        }

        infoLogStackTrace(e);
    }

    private void printPmtcReqLog(MarkForCaptureElement reqBean) {

        println(toString(reqBean));
    }

    private void printPmtcReqLog(ReversalElement reqBean) {

//        println(toString(reqBean));
        println("Version:" + reqBean.getVersion());
        println("Bin:" + reqBean.getBin());
        println("MerchantID:" + reqBean.getMerchantID());
        println("TerminalID:" + reqBean.getTerminalID());
        println("OrderID:" + reqBean.getOrderID());
        println("TxRefNum:" + reqBean.getTxRefNum());
        println("TxRefIdx:" + reqBean.getTxRefIdx());
        println("RetryTrace:" + reqBean.getRetryTrace());
        // 2018/07/13 S21_NA#25797 Add Start
        println("AdjustedAmount:" + reqBean.getAdjustedAmount());
        // 2018/07/13 S21_NA#25797 Add End
    }

    /**
     * @param e
     */
//    private static void infoLogStackTrace(Exception e) {
    private static void infoLogStackTrace(Throwable e) {

        String enter = System.getProperty("line.separator");

        StringBuilder stackStr = new StringBuilder();

        stackStr.append(e.toString());
        stackStr.append(enter);

        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement emt : elements) {
            stackStr.append(emt.toString());
            stackStr.append(enter);
        }

        println(stackStr.toString());

    }

    /**
     * <pre>
    * Return RespBean For Development Environment
    * </pre>
     * @param reqBean NewOrderRequestElement
     * @return respBean NewOrderResponseElement
     */
    private NewOrderResponseElement getRespBeanForDevEnv(NewOrderRequestElement reqBean) throws RemoteException {

        NewOrderResponseElement respBean = new NewOrderResponseElement();
        if (PMTC_TRNSF_TP_A.equals(reqBean.getTransType())) {

            respBean.setCustomerRefNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            // Add
            if (PMTC_ADD_PRFL_ORD_NUM_A.equals(reqBean.getAddProfileFromOrder())) {
                respBean.setProcStatus(BigDecimal.ZERO.toString());
                respBean.setApprovalStatus(BigDecimal.ONE.toString());
                respBean.setProfileProcStatus(BigDecimal.ZERO.toString());
                // Auth
            } else {
                respBean.setProcStatus(BigDecimal.ZERO.toString());
                respBean.setApprovalStatus(BigDecimal.ONE.toString());
                respBean.setProcStatusMessage("#Authorization Request Processed");
            }
            // Auth & Capture
        } else if (PMTC_TRNSF_TP_AC.equals(reqBean.getTransType())) {
            respBean.setProcStatus(BigDecimal.ZERO.toString());
            respBean.setApprovalStatus(BigDecimal.ONE.toString());
            respBean.setCustomerRefNum(reqBean.getCustomerRefNum());
            respBean.setProcStatusMessage("#Settlement Request Processed");
            // Refund
        } else if (PMTC_TRNSF_TP_R.equals(reqBean.getTransType())) {
            respBean.setProcStatus(BigDecimal.ZERO.toString());
            respBean.setApprovalStatus(BigDecimal.ONE.toString());
            respBean.setProcStatusMessage("Reversal(REFUND) Request Processed");
        }

        if (!ZYPCommonFunc.hasValue(reqBean.getTxRefNum())) {
            respBean.setTxRefNum(new SimpleDateFormat("yyyy").format(new Date()));
        } else {
            respBean.setTxRefNum(reqBean.getTxRefNum());
        }
        respBean.setTxRefIdx("0");
        respBean.setRespDateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        respBean.setOrderID(reqBean.getOrderID());

        // for dev test error occur by amt
        BigDecimal amt = new BigDecimal(reqBean.getAmount());
        if (PMTC_AMT_ERR_6001.equals(amt.toString())) {
            respBean.setProcStatus(BigDecimal.ZERO.toString());
            respBean.setApprovalStatus(BigDecimal.ZERO.toString());
            respBean.setProcStatusMessage(PMTC_AMT_ERR_6001_PROC_STS_MSG);
            respBean.setRespCodeMessage(PMTC_AMT_ERR_6001_RESP_CODE_MSG);
        }
        if (PMTC_AMT_ERR_6002.equals(amt.toString())) {
            respBean.setProcStatus(BigDecimal.ZERO.toString());
            respBean.setApprovalStatus(BigDecimal.ZERO.toString());
            respBean.setProcStatusMessage(PMTC_AMT_ERR_6002_PROC_STS_MSG);
            respBean.setRespCodeMessage(PMTC_AMT_ERR_6002_RESP_CODE_MSG);
        }
        if (PMTC_AMT_ERR_6003.equals(amt.toString())) {
            respBean.setProcStatus(BigDecimal.ZERO.toString());
            respBean.setApprovalStatus(BigDecimal.ONE.toString());
            respBean.setProfileProcStatus(PMTC_AMT_ERR_6003_PRFL_PROC_STS);
            respBean.setProcStatusMessage(PMTC_AMT_ERR_6003_PROC_STS_MSG);
            respBean.setProfileProcStatusMsg(PMTC_AMT_ERR_6003_PRFL_PROC_STS_MSG);
        }

        return respBean;
    }

    private MarkForCaptureResponseElement getMarkForCaptureResBeanForDevEnv(MarkForCaptureElement reqBean) {

        MarkForCaptureResponseElement resBean = new MarkForCaptureResponseElement();

        resBean.setBin(reqBean.getBin());
        resBean.setMerchantID(reqBean.getMerchantID());
        resBean.setTerminalID(reqBean.getTerminalID());
        resBean.setOrderID(reqBean.getOrderID());
        resBean.setTxRefNum(reqBean.getTxRefNum());

        // for dev test error occur by amt
        BigDecimal amt = new BigDecimal(reqBean.getAmount());
        if (PMTC_AMT_ERR_6001.equals(amt.toString())) {
            resBean.setProcStatus(PMTC_PROC_STS_CD_0);
            resBean.setProcStatusMessage(PMTC_AMT_ERR_6001_PROC_STS_MSG);
        } else {
            resBean.setProcStatus(PMTC_PROC_STS_CD_0);
            resBean.setProcStatusMessage("Mark For Capture Request Processed");
        }

        return resBean;
    }

    private ReversalResponseElement getReversalResBeanForDevEnv(ReversalElement reqBean) throws Throwable{

        ReversalResponseElement resBean = new ReversalResponseElement();
        resBean.setBin(reqBean.getBin());
        resBean.setMerchantID(reqBean.getMerchantID());
        resBean.setTerminalID(reqBean.getTerminalID());
        resBean.setOrderID(reqBean.getOrderID());
        resBean.setTxRefNum(reqBean.getTxRefNum());
        resBean.setProcStatus(PMTC_PROC_STS_CD_0);
        resBean.setProcStatusMessage("Reversal(VOID) Request Processed");
//        resBean.setApprovalStatus(BigDecimal.ZERO.toString());

        if ("ZZZZZZZZZZ".equals(reqBean.getTxRefNum())) {
            //void failed
            resBean.setProcStatusMessage("Reversal(VOID) Request Failed");
            throw new Throwable();
        }

        return resBean;
    }
}
