/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1010;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSchemaInfo;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL1010.common.NLCL1010CommonLogic;
import business.blap.NLCL1010.constant.NLCL1010Constant;
import business.db.CPOTMsg;
import business.db.INVTY_LOC_VTMsg;
import business.db.INVTY_LOC_VTMsgArray;
import business.db.INVTY_ORDTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SER_NUM_RNGTMsgArray;
import business.db.POTMsg;
import business.db.RWS_HDRTMsg;
import business.db.SER_ERR_STSTMsg;
import business.db.SER_ERR_STSTMsgArray;
import business.db.SER_TRX_EVENTTMsg;
import business.db.SER_TRX_EVENTTMsgArray;
import business.db.SER_TRX_SRC_TPTMsg;
import business.db.SER_TRX_SRC_TPTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.STK_STSTMsg;
import business.db.STK_STSTMsgArray;
import business.db.VND_RTRNTMsg;
import business.db.WRK_ORDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_ERR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * 08/12/2013   Fujitsu         Y.Taoka         Update          Defect#1627
 *</pre>
 */
public class NLCL1010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        NLCL1010CMsg cMsg = (NLCL1010CMsg) ezdCMsg;
        NLCL1010SMsg sMsg = (NLCL1010SMsg) ezdSMsg;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NLCL1010_INIT".equals(screenAplID)) {
                doProcess_NLCL1010_INIT(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_Search".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_Search(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_PageNext(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_Add".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_Add(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NLCL1010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_TBLColumnSort(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * previous page
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL1010Scrn00_PagePrev(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        NLCL1010CommonLogic.prevPage(cMsg, cMsg.A, sMsg.A);
    }

    /**
     * next page
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL1010Scrn00_PageNext(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        NLCL1010CommonLogic.nextPage(cMsg, cMsg.A, sMsg.A);
    }

    /**
     * initialize
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void doProcess_NLCL1010_INIT(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.serNum_C0) || ZYPCommonFunc.hasValue(cMsg.mdseCd_C0)) {
            createPullDownList(cMsg);
            doProcess_NLCL1010Scrn00_Search(cMsg, sMsg);
        } else {
            initialScreen(cMsg, sMsg);
            createPullDownList(cMsg);
        }
    }

    /**
     * reset
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void doProcess_NLCL1010Scrn00_CMN_Reset(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.serNum_C0) || ZYPCommonFunc.hasValue(cMsg.mdseCd_C0)) {
            // store search condition
            String serNum = cMsg.serNum_C0.getValue();
            String mdseCd = cMsg.mdseCd_C0.getValue();
            // initialize
            initialScreen(cMsg, sMsg);
            createPullDownList(cMsg);
            // search
            ZYPEZDItemValueSetter.setValue(cMsg.serNum_C0, serNum);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_C0, mdseCd);
            doProcess_NLCL1010Scrn00_Search(cMsg, sMsg);
        } else {
            initialScreen(cMsg, sMsg);
            createPullDownList(cMsg);
        }
    }

    /**
     * Initial screen.
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void initialScreen(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * create pulldown List
     * @param cMsg NLCL1010CMsg
     */
    private void createPullDownList(NLCL1010CMsg cMsg) {

        // SER_ERR_STS
        SER_ERR_STSTMsgArray serErrStsMsgList = (SER_ERR_STSTMsgArray) ZYPCodeDataUtil.findAll(new SER_ERR_STSTMsg());
        Map<String, String> serErrStsKeys = new HashMap<String, String>();
        serErrStsKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "serErrStsCd");
        serErrStsKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "serErrStsDescTxt");
        ZYPPulldownValueSetter.set(serErrStsMsgList, serErrStsKeys, cMsg.serErrStsCd_C0, cMsg.serErrStsDescTxt_C0);

        // SER_TRX_EVENT
        SER_TRX_EVENTTMsgArray serTrxEventMsgList = (SER_TRX_EVENTTMsgArray) ZYPCodeDataUtil.findAll(new SER_TRX_EVENTTMsg());
        Map<String, String> serTrxEventKeys = new HashMap<String, String>();
        serTrxEventKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "serTrxEventCd");
        serTrxEventKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "serTrxEventDescTxt");
        ZYPPulldownValueSetter.set(serTrxEventMsgList, serTrxEventKeys, cMsg.serTrxEventCd_C0, cMsg.serTrxEventDescTxt_C0);
        ZYPPulldownValueSetter.set(serTrxEventMsgList, serTrxEventKeys, cMsg.serTrxEventCd_H0, cMsg.serTrxEventDescTxt_H0);

        // SER_TRX_SRC_TP
        SER_TRX_SRC_TPTMsgArray serTrxSrcTpMsgList = (SER_TRX_SRC_TPTMsgArray) ZYPCodeDataUtil.findAll(new SER_TRX_SRC_TPTMsg());
        Map<String, String> serTrxSrcTpKeys = new HashMap<String, String>();
        serTrxSrcTpKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "serTrxSrcTpCd");
        serTrxSrcTpKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "serTrxSrcTpDescTxt");
        ZYPPulldownValueSetter.set(serTrxSrcTpMsgList, serTrxSrcTpKeys, cMsg.serTrxSrcTpCd_C0, cMsg.serTrxSrcTpDescTxt_C0);
        ZYPPulldownValueSetter.set(serTrxSrcTpMsgList, serTrxSrcTpKeys, cMsg.serTrxSrcTpCd_H0, cMsg.serTrxSrcTpDescTxt_H0);

        // 10/15/2015 add start
        // STK_STS
        STK_STSTMsgArray stkStsMsgList = (STK_STSTMsgArray) ZYPCodeDataUtil.findAll(new STK_STSTMsg());
        Map<String, String> stkStsKeys = new HashMap<String, String>();
        stkStsKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "stkStsCd");
        stkStsKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "stkStsDescTxt");
        ZYPPulldownValueSetter.set(stkStsMsgList, stkStsKeys, cMsg.stkStsCd_F0, cMsg.stkStsDescTxt_F0);
        ZYPPulldownValueSetter.set(stkStsMsgList, stkStsKeys, cMsg.stkStsCd_T0, cMsg.stkStsDescTxt_T0);
        // 10/15/2015 add end
    }

    /**
     * search
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void doProcess_NLCL1010Scrn00_Search(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        // check search condition
        if (!checkSearchCondition(cMsg)) {
            return;
        }

        // search
        Map<String, Object> queryParam = createSearchQueryParam(cMsg, sMsg);
        S21SsmEZDResult ssmResult = searchSerTrx(cMsg, sMsg, queryParam);

        // no result found
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NLCL1010Constant.NLCM0002I);
            return;
        }

        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NLCL1010Constant.ZZZM9002W);
            sMsg.A.setValidCount(sMsg.A.length());
        } else {
            sMsg.A.setValidCount(queryResCnt);
        }

        cMsg.xxPageShowFromNum.setValue(1);
        NLCL1010CommonLogic.dispPage(cMsg, cMsg.A, sMsg.A);
    }

    private void doProcess_NLCL1010Scrn00_CMN_Download(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        // check search condition
        if (!checkSearchCondition(cMsg)) {
            return;
        }

        // search
        Map<String, Object> queryParam = createSearchQueryParam(cMsg, sMsg);
        String errorCode = NLCL1010Query.getInstance().createSerTrxCSV(cMsg, queryParam);

        if (ZYPCommonFunc.hasValue(errorCode)) {
            cMsg.setMessageInfo(errorCode);
            return;
        }

    }

    /**
     * check if search condition is wrong or not
     * @param cMsg
     * @return return false if any error does exist
     */
    private boolean checkSearchCondition(NLCL1010CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_C0) && ZYPCommonFunc.hasValue(cMsg.xxThruDt_C0)) {
            if (cMsg.xxFromDt_C0.getValue().compareTo(cMsg.xxThruDt_C0.getValue()) > 0) {
                cMsg.xxFromDt_C0.setErrorInfo(1, NLCL1010Constant.NLCM0115E, new String[] {"Trx Term" });
                cMsg.xxThruDt_C0.setErrorInfo(1, NLCL1010Constant.NLCM0115E, new String[] {"Trx Term" });
                return false;
            }
        }
        return true;
    }

    private Map<String, Object> createSearchQueryParam(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serNum", cMsg.serNum_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "mdseCd", cMsg.mdseCd_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serTrxEventCd", cMsg.serTrxEventCd_P0.getValue());
        // convert yyyyMMdd to yyyymmddhhmiss (make fit to DB format)
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_C0)) {
            NLCL1010CommonLogic.setOptionalParam(queryParam, "fromSerTrxTs", cMsg.xxFromDt_C0.getValue() + NLCL1010Constant.MIN_HHMMSS);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxThruDt_C0)) {
            NLCL1010CommonLogic.setOptionalParam(queryParam, "thruSerTrxTs", cMsg.xxThruDt_C0.getValue() + NLCL1010Constant.MAX_HHMMSS);
        }
        NLCL1010CommonLogic.setOptionalParam(queryParam, "fromLocCd", cMsg.fromLocCd_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "toLocCd", cMsg.toLocCd_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serTrxSrcTpCd", cMsg.serTrxSrcTpCd_P0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serTrxSrcHdrNum", cMsg.serTrxSrcHdrNum_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serTrxRefNum", cMsg.serTrxRefNum_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "origMdseCd", cMsg.origMdseCd_C0.getValue());
        NLCL1010CommonLogic.setOptionalParam(queryParam, "serTrxLtstFlg", cMsg.serTrxLtstFlg_C0.getValue());

        NLCL1010CommonLogic.setOptionalParam(queryParam, "serErrStsCd", cMsg.serErrStsCd_P0.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.xxRsltFlg_C0)) {
            NLCL1010CommonLogic.setOptionalParam(queryParam, "errorOnly", ZYPConstant.FLG_ON_Y);
        }

        // code table value
        queryParam.put("serTrxSrcTpRws", SER_TRX_SRC_TP.RWS);

        return queryParam;
    }

    private S21SsmEZDResult searchSerTrx(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg, Map<String, Object> queryParam) {
        queryParam.put("rowNum", sMsg.A.length() + 1);
        queryParam.put("invtyLocTpVnd", LOC_TP.VENDOR);
        queryParam.put("invtyLocTpCust", LOC_TP.CUSTOMER);

        return NLCL1010Query.getInstance().findSerTrx(queryParam, sMsg);
    }


    /**
     * clear
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void doProcess_NLCL1010Scrn00_CMN_Clear(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        initialScreen(cMsg, sMsg);
        createPullDownList(cMsg);
    }

    /**
     * add
     * @param cMsg NLCL1010CMsg
     * @param sMsg NLCL1010SMsg
     */
    private void doProcess_NLCL1010Scrn00_Add(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {

        NLCL1010CommonLogic.saveCurrentPage(cMsg, cMsg.A, sMsg.A);

        // //////////////////////////////////////////////////////////////////////
        // Check Item
        // //////////////////////////////////////////////////////////////////////

        MDSETMsg mdseTMsg = findMdse(getGlobalCompanyCode(), cMsg.mdseCd_H0.getValue());
        // merchandise must be registered in MDSE
        if (mdseTMsg == null) {
            cMsg.mdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0108E);
            return;
        }

        // merchandise must be Serialized Intangible
        if (!(ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue()))) {
            cMsg.mdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0118E);
            return;
        }

        // when we enter RefurbishStockIn, Merchandise Type must be Refurbished
        if (SER_TRX_EVENT.REFURBISH_STOCK_IN.equals(cMsg.serTrxEventCd_P1.getValue()) && !MDSE_TP.REFURBISHED.equals(mdseTMsg.mdseTpCd.getValue())) {
            cMsg.serTrxEventCd_P1.setErrorInfo(1, NLCL1010Constant.NLCM0119E);
            cMsg.mdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0119E);
            return;
        }

        // when we enter RefurbishStockOut, Original Merchandise Code must be registered in MDSE
        if (SER_TRX_EVENT.REFURBISH_STOCK_OUT.equals(cMsg.serTrxEventCd_P1.getValue()) && findMdseByOrigMdseCd(getGlobalCompanyCode(), cMsg.mdseCd_H0.getValue()) == null) {
            cMsg.serTrxEventCd_P1.setErrorInfo(1, NLCL1010Constant.NLCM0120E);
            cMsg.mdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0120E);
            return;
        }

        // old Merchandise Code must be registered in MDSE
        MDSETMsg oldMdseTMsg = null;
        if (ZYPCommonFunc.hasValue(cMsg.oldMdseCd_H0)) {
            oldMdseTMsg = findMdse(getGlobalCompanyCode(), cMsg.oldMdseCd_H0.getValue());
            if (oldMdseTMsg == null) {
                cMsg.oldMdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0108E);
                return;
            }
        }

        // Location(From) must be registered in INVTY_LOC_V
        INVTY_LOC_VTMsg fromInvtyLocV = null;
        if (ZYPCommonFunc.hasValue(cMsg.fromLocCd_H0)) {
            fromInvtyLocV = findInvtyLocV(getGlobalCompanyCode(), cMsg.fromLocCd_H0.getValue());
            if (fromInvtyLocV == null) {
                cMsg.fromLocCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0110E);
                return;
            }
        }

        // Location(To) must be registered in INVTY_LOC_V
        INVTY_LOC_VTMsg toInvtyLocV = null;
        if (ZYPCommonFunc.hasValue(cMsg.toLocCd_H0)) {
            toInvtyLocV = findInvtyLocV(getGlobalCompanyCode(), cMsg.toLocCd_H0.getValue());
            if (toInvtyLocV == null) {
                cMsg.toLocCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0110E);
                return;
            }
        }

        // detail capacity will be over
        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NLCL1010Constant.NLCM0025E);
            return;
        }
        //08/12/2013 ADD Defect#1627
        boolean warningFlag = false;

        // check Serial Transaction Source Header#
        // 10/16/2015 delete start
//        if (SER_TRX_SRC_TP.RWS.equals(cMsg.serTrxSrcTpCd_P1.getValue())) {
//            // value changed
//            if (ZYPCommonFunc.hasValue(cMsg.serTrxRefNum_H0) && !cMsg.serTrxRefNum_H0.getValue().equals(cMsg.serTrxRefNum_HS.getValue())) {
//                // check serTrxRefNum
//                if (!existsSerTrxSrcHdr(getGlobalCompanyCode(), cMsg)) {
//                    cMsg.setMessageInfo(NLCL1010Constant.NLCM0124W, new String[] {"Serial Trx Ref#" });
//                    cMsg.serTrxRefNum_H0.setErrorInfo(2, NLCL1010Constant.NLCM0124W, new String[] {"Serial Trx Ref#" });
//                    // save
//                    ZYPEZDItemValueSetter.setValue(cMsg.serTrxRefNum_HS, cMsg.serTrxRefNum_H0);
//                    //08/12/2013 MOD Defect#1627
//                    //return;
//                    warningFlag = true;
//                }
//            }
//
//        } else {
        // 10/16/2015 delete end
        // value changed
        if (ZYPCommonFunc.hasValue(cMsg.serTrxSrcHdrNum_H0) && !cMsg.serTrxSrcHdrNum_H0.getValue().equals(cMsg.serTrxSrcHdrNum_HS.getValue())) {
            // check serTrxRefNum
            if (!existsSerTrxSrcHdr(getGlobalCompanyCode(), cMsg)) {
                cMsg.setMessageInfo(NLCL1010Constant.NLCM0124W, new String[] {"Trx SRC Header#" });
                cMsg.serTrxSrcHdrNum_H0.setErrorInfo(2, NLCL1010Constant.NLCM0124W, new String[] {"Trx SRC Header#" });
                // save
                ZYPEZDItemValueSetter.setValue(cMsg.serTrxSrcHdrNum_HS, cMsg.serTrxSrcHdrNum_H0);
                //08/12/2013 MOD Defect#1627
                //return;
                warningFlag = true;
            }
        }
//        } // 10/16/2015 delete

        // check serNum
        String serErrStsCd = chkSerNumRng(getGlobalCompanyCode(), cMsg.serNum_H0.getValue(), cMsg.mdseCd_H0.getValue());
        if (!cMsg.serNum_H0.getValue().equals(cMsg.serNum_HS.getValue())) {
            // check serNum
            if (ZYPCommonFunc.hasValue(serErrStsCd)) {
                if (SER_ERR_STS.LEN.equals(serErrStsCd)) {
                    cMsg.setMessageInfo(NLCL1010Constant.NLCM0121W);
                    cMsg.serNum_H0.setErrorInfo(2, NLCL1010Constant.NLCM0121W);
                } else if (SER_ERR_STS.RAN.equals(serErrStsCd)) {
                    cMsg.setMessageInfo(NLCL1010Constant.NLCM0122W);
                    cMsg.serNum_H0.setErrorInfo(2, NLCL1010Constant.NLCM0122W);
                }

                ZYPEZDItemValueSetter.setValue(cMsg.serNum_HS, cMsg.serNum_H0);
                //08/12/2013 MOD Defect#1627
                //return;
                warningFlag = true;
            }
        }
        //08/12/2013 ADD Defect#1627
        if (warningFlag) {
            return;
        }

        // clear warning variables
        cMsg.serTrxRefNum_HS.clear();
        cMsg.serTrxSrcHdrNum_HS.clear();
        cMsg.serNum_HS.clear();

        NLCL1010_ASMsg newItem = new NLCL1010_ASMsg();
        newItem.clear();

        // //////////////////////////////////////////////////////////////////////
        // Add Item
        // //////////////////////////////////////////////////////////////////////

        String serTrxEventDescTxt = null;
        if (ZYPCommonFunc.hasValue(cMsg.serTrxEventCd_P1)) {
            serTrxEventDescTxt = ZYPCodeDataUtil.getName(SER_TRX_EVENT.class, getGlobalCompanyCode(), cMsg.serTrxEventCd_P1.getValue());
        }
        // 10/16/2015 mod start
        // String salesDate = ZYPDateUtil.getSalesDate();
        // String curTimeStamp = salesDate + ZYPDateUtil.getCurrentSystemTime(NLCL1010Constant.FORMAT_TIMESTAMP_TIME);
        String serTrxTs = cMsg.xxDt10Dt_H0.getValue() + cMsg.xxSvcFromHourMnTxt_H0.getValue().substring(0,2) + cMsg.xxSvcFromHourMnTxt_H0.getValue().substring(3,5) + "00";
        // 10/16/2015 mod end
        String serTrxSrcTpDescTxt = null;
        if (ZYPCommonFunc.hasValue(cMsg.serTrxSrcTpCd_P1.getValue())) {
            serTrxSrcTpDescTxt = ZYPCodeDataUtil.getName(SER_TRX_SRC_TP.class, getGlobalCompanyCode(), cMsg.serTrxSrcTpCd_P1.getValue());
        }

        String serErrStsNmDescTxt = null;
        if (ZYPCommonFunc.hasValue(serErrStsCd)) {
            SER_ERR_STSTMsg serErrStsRec = (SER_ERR_STSTMsg) ZYPCodeDataUtil.findByCode(SER_ERR_STS.class, getGlobalCompanyCode(), serErrStsCd);
            serErrStsNmDescTxt = serErrStsRec.serErrStsNm.getValue() + ":" + serErrStsRec.serErrStsDescTxt.getValue();
        }

        newItem.xxChkBox_A1.clear();
        ZYPEZDItemValueSetter.setValue(newItem.serNum_A1, cMsg.serNum_H0);
        ZYPEZDItemValueSetter.setValue(newItem.mdseCd_A1, cMsg.mdseCd_H0);
        //ZYPEZDItemValueSetter.setValue(newItem.mdseDescShortTxt_A1, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newItem.mdseDescShortTxt_A1, mdseTMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxEventDescTxt_A1, serTrxEventDescTxt);
        // 10/15/2015 mod start
        // ZYPEZDItemValueSetter.setValue(newItem.xxDt10Dt_A1, salesDate);
        String trxDtTm = cMsg.xxDt10Dt_H0.getValue().substring(4,6) + "/" + cMsg.xxDt10Dt_H0.getValue().substring(6,8) + "/" +cMsg.xxDt10Dt_H0.getValue().substring(0,4) + " " + cMsg.xxSvcFromHourMnTxt_H0.getValue();
        ZYPEZDItemValueSetter.setValue(newItem.xxDtNm_A1, trxDtTm);
        // 10/15/2015 mod end
        ZYPEZDItemValueSetter.setValue(newItem.manCratFlg_A1, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(cMsg.fromLocCd_H0)) {
            ZYPEZDItemValueSetter.setValue(newItem.xxScrItem500Txt_A1, cMsg.fromLocCd_H0.getValue() + ":" + fromInvtyLocV.invtyLocNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.toLocCd_H0)) {
            ZYPEZDItemValueSetter.setValue(newItem.xxScrItem500Txt_A1, cMsg.toLocCd_H0.getValue() + ":" + toInvtyLocV.invtyLocNm.getValue());
        }
        ZYPEZDItemValueSetter.setValue(newItem.serTrxSrcTpDescTxt_A1, serTrxSrcTpDescTxt);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxSrcHdrNum_A1, cMsg.serTrxSrcHdrNum_H0);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxSrcLineNum_A1, cMsg.serTrxSrcLineNum_H0);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxSrcLineSubNum_A1, cMsg.serTrxSrcLineSubNum_H0);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxRefNum_A1, cMsg.serTrxRefNum_H0);
        // 10/16/2015 add start
        String stkStsDescTxt_A1 = null;
        if (ZYPCommonFunc.hasValue(cMsg.stkStsCd_F1.getValue())) {
            stkStsDescTxt_A1 = ZYPCodeDataUtil.getName(STK_STS.class, getGlobalCompanyCode(), cMsg.stkStsCd_F1.getValue());
        }
        String stkStsDescTxt_A2 = null;
        if (ZYPCommonFunc.hasValue(cMsg.stkStsCd_T1.getValue())) {
            stkStsDescTxt_A2 = ZYPCodeDataUtil.getName(STK_STS.class, getGlobalCompanyCode(), cMsg.stkStsCd_T1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(newItem.stkStsDescTxt_A1, stkStsDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(newItem.stkStsDescTxt_A2, stkStsDescTxt_A2);
        // 10/16/2015 add end
        newItem.origMdseCd_A1.clear();
        if (ZYPCommonFunc.hasValue(cMsg.serTrxCmntTxt_H0)) {
            ZYPEZDItemValueSetter.setValue(newItem.xxYesNoCd_A1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(newItem.xxYesNoCd_A1, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(serErrStsCd)) {
            ZYPEZDItemValueSetter.setValue(newItem.xxDplyByItemCnctLbNm_A3, serErrStsNmDescTxt);
        }
        newItem.serTrxPk_A1.clear();
        // 10/16/2015 mod start
        // ZYPEZDItemValueSetter.setValue(newItem.serTrxTs_A1, curTimeStamp);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxTs_A1, serTrxTs);
        // 10/16/2015 mod end
        ZYPEZDItemValueSetter.setValue(newItem.serTrxEventCd_A1, cMsg.serTrxEventCd_P1);
        ZYPEZDItemValueSetter.setValue(newItem.serTrxSrcTpCd_A1, cMsg.serTrxSrcTpCd_P1);
        ZYPEZDItemValueSetter.setValue(newItem.fromLocCd_A1, cMsg.fromLocCd_H0);
        ZYPEZDItemValueSetter.setValue(newItem.toLocCd_A1, cMsg.toLocCd_H0);
        if (SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN.equals(cMsg.serTrxEventCd_P1.getValue())) {
            ZYPEZDItemValueSetter.setValue(newItem.oldMdseCd_A1, cMsg.oldMdseCd_H0);
        } else if (SER_TRX_EVENT.REFURBISH_STOCK_IN.equals(cMsg.serTrxEventCd_P1.getValue())) {
            ZYPEZDItemValueSetter.setValue(newItem.oldMdseCd_A1, mdseTMsg.origMdseCd);
        } else {
            newItem.oldMdseCd_A1.clear();
        }
        ZYPEZDItemValueSetter.setValue(newItem.serTrxCmntTxt_A1, cMsg.serTrxCmntTxt_H0);
        ZYPEZDItemValueSetter.setValue(newItem.serErrStsCd_A1, serErrStsCd);
        ZYPEZDItemValueSetter.setValue(newItem.stkStsCd_A1, cMsg.stkStsCd_F1); // 10/16/2015 add
        ZYPEZDItemValueSetter.setValue(newItem.stkStsCd_A2, cMsg.stkStsCd_T1); // 10/16/2015 add
        newItem.ezUpTime_A1.clear();
        newItem.ezUpTimeZone_A1.clear();

        EZDMsg.copy(newItem, null, sMsg.A.no(sMsg.A.getValidCount()), null);
        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);

        NLCL1010CommonLogic.jumpPage(cMsg, sMsg.A.getValidCount() - 1, cMsg.A, sMsg.A);

        // clear Add Serial Trx
        cMsg.serNum_H0.clear();
        cMsg.mdseCd_H0.clear();
        cMsg.serTrxEventCd_P1.clear();
        cMsg.fromLocCd_H0.clear();
        cMsg.toLocCd_H0.clear();
        cMsg.stkStsCd_F1.clear(); // 10/19/2015 add
        cMsg.stkStsCd_T1.clear(); // 10/19/2015 add
        cMsg.serTrxSrcTpCd_P1.clear();
        cMsg.serTrxSrcHdrNum_H0.clear();
        cMsg.serTrxSrcLineNum_H0.clear();
        cMsg.serTrxSrcLineSubNum_H0.clear();
        cMsg.serTrxRefNum_H0.clear();
        cMsg.oldMdseCd_H0.clear();
        cMsg.xxDt10Dt_H0.clear();           // 10/19/2015 add
        cMsg.xxSvcFromHourMnTxt_H0.clear(); // 10/19/2015 add
        cMsg.serTrxCmntTxt_H0.clear();

    }

    private MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg cond = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.mdseCd, mdseCd);
        cond = (MDSETMsg) EZDTBLAccessor.findByKey(cond);

        if (cond == null) {
            return null;
        }
        return cond;
    }

    private MDSETMsg findMdseByOrigMdseCd(String glblCmpyCd, String origMdseCd) {

        MDSETMsg cond = new MDSETMsg();
        cond.setSQLID("078");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("origMdseCd01", origMdseCd);

        EZDTMsgArray record = EZDTBLAccessor.findByCondition(cond);
        if (record.length() > 0) {
            return (MDSETMsg) record.get(0);
        } else {
            return null;
        }
    }

    private INVTY_LOC_VTMsg findInvtyLocV(String glblCmpyCd, String invtyLocCd) {

        INVTY_LOC_VTMsg cond = new INVTY_LOC_VTMsg();
        cond.setSQLID("001");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("invtyLocCd01", invtyLocCd);

        INVTY_LOC_VTMsgArray result = (INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(cond);

        if (result.length() == 0) {
            return null;
        } else {
            return result.no(0);
        }
    }

    private boolean existsSerTrxSrcHdr(String glblCmpyCd, NLCL1010CMsg cMsg) {
        String serTrxSrcHdrNum = cMsg.serTrxSrcHdrNum_H0.getValue();
        String serTrxRefNum = cMsg.serTrxRefNum_H0.getValue();
        String serTrxSrcTpCd = cMsg.serTrxSrcTpCd_P1.getValue();

        int serTrxSrcHdrNumLen = serTrxSrcHdrNum.length(); // 08/12/2013 ADD Defect#1627

        boolean exists = false;
        // switch source TABLE (depending on selected SER_TRX_SRC_TP value)
        if (SER_TRX_SRC_TP.PO.equals(serTrxSrcTpCd)) {
            // PO
            POTMsg cond = new POTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.PO_ORD_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.poOrdNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.RWS.equals(serTrxSrcTpCd)) {
            // RWS_HDR
            RWS_HDRTMsg cond = new RWS_HDRTMsg();
            // 10/16/2015 mod start
            /*
            cond.setSQLID("010");
            cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
            cond.setConditionValue("rwsRefNum01", serTrxRefNum);
            exists = EZDTBLAccessor.findByCondition(cond).length() != 0;
            */
            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.rwsNum, serTrxRefNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;
            // 10/16/2015 mod end

        } else if (SER_TRX_SRC_TP.CPO.equals(serTrxSrcTpCd)) {
            // CPO
            CPOTMsg cond = new CPOTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.CPO_ORD_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.cpoOrdNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.SO.equals(serTrxSrcTpCd)) {
            // SHPG_ORD
            SHPG_ORDTMsg cond = new SHPG_ORDTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.SO_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.soNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.RMA.equals(serTrxSrcTpCd)) {
//            // RMA_HDR
//            RMA_HDRTMsg cond = new RMA_HDRTMsg();
//
//            // START 08/12/2013 ADD Defect#1627
//            EZDSchemaInfo schema = cond.getSchema();
//            if (schema.getAttr(NLCL1010Constant.RMA_NUM).getDigit() < serTrxSrcHdrNumLen) {
//                return false;
//            }
//            // END   08/12/2013 ADD Defect#1627    
//
//            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cond.rmaNum, serTrxSrcHdrNum);
//            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.RECEIVING_REPORT.equals(serTrxSrcTpCd)) {
//            // RCV_RPT_HDR
//            RCV_RPT_HDRTMsg cond = new RCV_RPT_HDRTMsg();
//
//            // START 08/12/2013 ADD Defect#1627
//            EZDSchemaInfo schema = cond.getSchema();
//            if (schema.getAttr(NLCL1010Constant.RCV_RPT_NUM).getDigit() < serTrxSrcHdrNumLen) {
//                return false;
//            }
//            // END   08/12/2013 ADD Defect#1627    
//
//            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cond.rcvRptNum, serTrxSrcHdrNum);
//            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.VENDOR_RETURN_ORDER.equals(serTrxSrcTpCd)) {
            // VND_RTRN_HDR
            VND_RTRNTMsg cond = new VND_RTRNTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.VND_RTRN_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.vndRtrnNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.INVENTORY_ORDER.equals(serTrxSrcTpCd)) {
            // INVTY_ORD
            INVTY_ORDTMsg cond = new INVTY_ORDTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.INVTY_ORD_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.invtyOrdNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        } else if (SER_TRX_SRC_TP.WORK_ORDER.equals(serTrxSrcTpCd)) {
            // WRK_ORD
            WRK_ORDTMsg cond = new WRK_ORDTMsg();

            // START 08/12/2013 ADD Defect#1627
            EZDSchemaInfo schema = cond.getSchema();
            if (schema.getAttr(NLCL1010Constant.WRK_ORD_NUM).getDigit() < serTrxSrcHdrNumLen) {
                return false;
            }
            // END   08/12/2013 ADD Defect#1627    

            ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cond.wrkOrdNum, serTrxSrcHdrNum);
            exists = S21FastTBLAccessor.findByKey(cond) != null;

        }

        return exists;
    }

    private String chkSerNumRng(String glblCmpyCd, String serNum, String mdseCd) {

        MDSE_SER_NUM_RNGTMsg mdseSerNumRngTMsg = new MDSE_SER_NUM_RNGTMsg();
        mdseSerNumRngTMsg.setSQLID("001");
        mdseSerNumRngTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        mdseSerNumRngTMsg.setConditionValue("mdseCd01", mdseCd);

        MDSE_SER_NUM_RNGTMsgArray mdseSerNumRngTMsgArray = (MDSE_SER_NUM_RNGTMsgArray) EZDTBLAccessor.findByCondition(mdseSerNumRngTMsg);

        int lgSerNum = 0;
        String fromSerNum = null;
        String thruSerNum = null;

        // no data
        if (mdseSerNumRngTMsgArray.length() == 0) {
            // no constraint
            return null;
        }

        // check serial length
        boolean lngChkPassed = false;
        for (int i = 0; i < mdseSerNumRngTMsgArray.length(); i++) {
            lgSerNum = mdseSerNumRngTMsgArray.no(i).lgSerNum.getValueInt();
            if (lgSerNum == serNum.length()) {
                lngChkPassed = true;
                break;
            }
        }

        if (!lngChkPassed) {
            return SER_ERR_STS.LEN;
        }

        // check serial range
        boolean rngChkPassed = false;
        for (int i = 0; i < mdseSerNumRngTMsgArray.length(); i++) {

            lgSerNum = mdseSerNumRngTMsgArray.no(i).lgSerNum.getValueInt();
            fromSerNum = mdseSerNumRngTMsgArray.no(i).fromSerNum.getValue();
            thruSerNum = mdseSerNumRngTMsgArray.no(i).thruSerNum.getValue();

            if (lgSerNum == serNum.length()) {

                if (!ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum)) {
                    rngChkPassed = true;
                    break;
                } else if (!ZYPCommonFunc.hasValue(fromSerNum) && ZYPCommonFunc.hasValue(thruSerNum)) {
                    if (fromSerNum.compareTo(serNum) <= 0) {
                        rngChkPassed = true;
                        break;
                    }
                } else if (ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum)) {
                    if (thruSerNum.compareTo(serNum) >= 0) {
                        rngChkPassed = true;
                        break;
                    }
                } else if (ZYPCommonFunc.hasValue(fromSerNum) && ZYPCommonFunc.hasValue(thruSerNum)) {
                    if (fromSerNum.compareTo(serNum) <= 0 && thruSerNum.compareTo(serNum) >= 0) {
                        rngChkPassed = true;
                        break;
                    }
                }
            }
        }

        if (!rngChkPassed) {
            return SER_ERR_STS.RAN;
        }

        return null;
    }

    /**
     * table column sort
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL1010Scrn00_TBLColumnSort(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if (NLCL1010Constant.TABLE_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            cMsg.xxPageShowFromNum.setValue(1);
            NLCL1010CommonLogic.dispPage(cMsg, cMsg.A, sMsg.A);
        }
    }

}
