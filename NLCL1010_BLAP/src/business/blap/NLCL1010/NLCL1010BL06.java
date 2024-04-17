/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1010;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL1010.common.NLCL1010CommonLogic;
import business.blap.NLCL1010.constant.NLCL1010Constant;
import business.db.SER_ERR_STSTMsg;
import business.db.SER_TRXTMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC302001_UpdateDetailListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_ERR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        NLCL1010CMsg cMsg = (NLCL1010CMsg) ezdCMsg;
        NLCL1010SMsg sMsg = (NLCL1010SMsg) ezdSMsg;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NLCL1010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL1010Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL1010Scrn00_CMN_Submit(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {

        NLCL1010CommonLogic.saveCurrentPage(cMsg, cMsg.A, sMsg.A);
        cMsg.setCommitSMsg(true);

        // check update
        if (!hasTouchedRecord(cMsg, sMsg)) {
            cMsg.setMessageInfo(NLCL1010Constant.NLCM0123E);
            return;
        }

        // Create Serial Update API
        NLZC302001 serUpdApi = new NLZC302001();
        NLZC302001PMsg serUpdParam = new NLZC302001PMsg();
        // common parameter
        ZYPEZDItemValueSetter.setValue(serUpdParam.glblCmpyCd, getGlobalCompanyCode());

        int serUpdParamCount = 0;
        boolean hasDetailError = false;
        int firstErrorFoundIndex = 0;

        // save association index of sMsg.A.no(n) and index of NLZC201001.UpdateDetailList.no(n)
        // to detect error source
        // - key: index of NLZC201001.UpdateDetailList.no(n)
        // - value: index of sMsg.A.no(n)
        Map<Integer, Integer> mapScrIndex2ApiIndex = new HashMap<Integer, Integer>();

        // save Primary Key that created by API, for enable to refresh screen value
        // - key: index of sMsg.A.no(n)
        // - value: Primary Key
        Map<Integer, BigDecimal> mapScrIndex2Pk = new HashMap<Integer, BigDecimal>();

        // Update Table
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLCL1010_ASMsg detail = sMsg.A.no(i);
            // added by manual
            if (isAddRecord(detail)) {
                // Create parameter to Add record
                NLZC302001_UpdateDetailListPMsg serUpdDtl = serUpdParam.UpdateDetailList.no(serUpdParamCount);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serNum, detail.serNum_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.mdseCd, detail.mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxTs, detail.serTrxTs_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxEventCd, detail.serTrxEventCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxSrcTpCd, detail.serTrxSrcTpCd_A1);
                // swap SER_TRX_SRC_HDR_NUM and SER_TRX_REF_NUM when RWS selected
                // 10/16/2015 delete start
//                if (SER_TRX_SRC_TP.RWS.equals(detail.serTrxSrcTpCd_A1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxSrcHdrNum, detail.serTrxRefNum_A1);
//                } else {
                // 10/16/2015 delete end
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxSrcHdrNum, detail.serTrxSrcHdrNum_A1);
//                } // 10/16/2015 delete
                // 10/16/2015 mod end
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxSrcLineNum, detail.serTrxSrcLineNum_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxSrcLineSubNum, detail.serTrxSrcLineSubNum_A1);
                // swap SER_TRX_SRC_HDR_NUM and SER_TRX_REF_NUM when RWS selected
                // 10/16/2015 delete start
//                if (SER_TRX_SRC_TP.RWS.equals(detail.serTrxSrcTpCd_A1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxRefNum, detail.serTrxSrcHdrNum_A1);
//                } else {
                // 10/16/2015 delete end
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxRefNum, detail.serTrxRefNum_A1);
//                } // 10/16/2015 delete
                ZYPEZDItemValueSetter.setValue(serUpdDtl.fromLocCd, detail.fromLocCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.toLocCd, detail.toLocCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.oldMdseCd, detail.oldMdseCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.manCratFlg, ZYPConstant.CHKBOX_ON_Y);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxCmntTxt, detail.serTrxCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serErrStsCd, detail.serErrStsCd_A1);
                // 10/16/2015 add start
                ZYPEZDItemValueSetter.setValue(serUpdDtl.fromStkStsCd, detail.stkStsCd_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.toStkStsCd, detail.stkStsCd_A2);
                // TODO Location Status Code
                // ZYPEZDItemValueSetter.setValue(serUpdDtl.locStsCd, detail.);
                // 10/16/2015 add end

                mapScrIndex2ApiIndex.put(serUpdParamCount, i);
                serUpdParamCount++;

            } else if (isUpdateRecord(detail)) {
                // updated

                // check
                SER_TRXTMsg record = findSerTrxForUpdate(getGlobalCompanyCode(), detail);

                // anyone deleted
                if (record == null) {
                    cMsg.setMessageInfo(NLCL1010Constant.ZZZM9004E);
                    return;
                }

                // anyone updated
                if (!ZYPDateUtil.isSameTimeStamp(detail.ezUpTime_A1.getValue(), detail.ezUpTimeZone_A1.getValue(), record.ezUpTime.getValue(), record.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NLCL1010Constant.ZZZM9004E);
                    return;
                }

                // create update parameter
                NLZC302001_UpdateDetailListPMsg serUpdDtl = serUpdParam.UpdateDetailList.no(serUpdParamCount);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxPk, detail.serTrxPk_A1);
                ZYPEZDItemValueSetter.setValue(serUpdDtl.serTrxCmntTxt, detail.serTrxCmntTxt_A1);
                if (ZYPConstant.CHKBOX_ON_Y.equals(detail.xxChkBox_A1.getValue())) {
                    // set delete error status when checked
                    ZYPEZDItemValueSetter.setValue(serUpdDtl.serErrStsCd, SER_ERR_STS.MDL);
                } else {
                    ZYPEZDItemValueSetter.setValue(serUpdDtl.serErrStsCd, detail.serErrStsCd_A1);
                }
                mapScrIndex2ApiIndex.put(serUpdParamCount, i);

                serUpdParamCount++;
            }

            // the number of API parameter list is ready to call
            if (serUpdParamCount >= serUpdParam.UpdateDetailList.length()) {
                serUpdParam.UpdateDetailList.setValidCount(serUpdParamCount);
                // call API
                serUpdApi.execute(serUpdParam, ONBATCH_TYPE.ONLINE);

                // check detail API error
                for (int j = 0; j < serUpdParam.UpdateDetailList.getValidCount(); j++) {
                    NLZC302001_UpdateDetailListPMsg updDtl = serUpdParam.UpdateDetailList.no(j);
                    int scrIndex = mapScrIndex2ApiIndex.get(j);
                    if (ZYPCommonFunc.hasValue(updDtl.xxMsgId)) {

                        // set error message
                        if (!hasDetailError) {
                            firstErrorFoundIndex = scrIndex;
                        }
                        hasDetailError = true;

                        sMsg.A.no(scrIndex).xxChkBox_A1.setErrorInfo(1, updDtl.xxMsgId.getValue());
                    }
                    // save primary key
                    mapScrIndex2Pk.put(scrIndex, updDtl.serTrxPk.getValue());
                }
                // check header API error (investigate when detail error not set)
                if (!hasDetailError) {
                    if (serUpdParam.xxMsgIdList.getValidCount() > 0) {
                        cMsg.setMessageInfo(serUpdParam.xxMsgIdList.no(0).xxMsgId.getValue());
                        return;
                    }
                }
                // reset
                mapScrIndex2ApiIndex.clear();
                serUpdParamCount = 0;
                ZYPTableUtil.clear(serUpdParam.UpdateDetailList);

            }
        }

        // the number of API parameter list is ready to call
        if (serUpdParamCount > 0) {
            serUpdParam.UpdateDetailList.setValidCount(serUpdParamCount);
            // call API
            serUpdApi.execute(serUpdParam, ONBATCH_TYPE.ONLINE);

            // check detail API error
            for (int j = 0; j < serUpdParam.UpdateDetailList.getValidCount(); j++) {
                NLZC302001_UpdateDetailListPMsg updDtl = serUpdParam.UpdateDetailList.no(j);
                int scrIndex = mapScrIndex2ApiIndex.get(j);
                if (ZYPCommonFunc.hasValue(updDtl.xxMsgId)) {

                    if (!hasDetailError) {
                        firstErrorFoundIndex = scrIndex;
                    }
                    hasDetailError = true;

                    // set error message
                    sMsg.A.no(scrIndex).xxChkBox_A1.setErrorInfo(1, updDtl.xxMsgId.getValue());
                }
                // save primary key
                mapScrIndex2Pk.put(scrIndex, updDtl.serTrxPk.getValue());
            }
            // check header API error (investigate when detail error not set)
            if (!hasDetailError) {
                if (serUpdParam.xxMsgIdList.getValidCount() > 0) {
                    cMsg.setMessageInfo(serUpdParam.xxMsgIdList.no(0).xxMsgId.getValue());
                    return;
                }
            }

            // reset
            mapScrIndex2ApiIndex.clear();
            serUpdParamCount = 0;
            ZYPTableUtil.clear(serUpdParam.UpdateDetailList);
        }

        if (hasDetailError) {
            // jump page to error found
            NLCL1010CommonLogic.jumpPage(cMsg, firstErrorFoundIndex, cMsg.A, sMsg.A);
            return;
        }

        // refresh screen with database values
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLCL1010_ASMsg detail = sMsg.A.no(i);
            if (isAddRecord(detail) || isUpdateRecord(detail)) {
                // added by manual
                if (isAddRecord(detail)) {
                    BigDecimal serTrxPk = mapScrIndex2Pk.get(i);
                    ZYPEZDItemValueSetter.setValue(detail.serTrxPk_A1, serTrxPk);
                }
                // get created record
                SER_TRXTMsg newRecord = findSerTrx(getGlobalCompanyCode(), detail.serTrxPk_A1.getValue());
                detail.xxChkBox_A1.clear();
                ZYPEZDItemValueSetter.setValue(detail.ezUpTime_A1, newRecord.ezUpTime);
                ZYPEZDItemValueSetter.setValue(detail.ezUpTimeZone_A1, newRecord.ezUpTimeZone);
                ZYPEZDItemValueSetter.setValue(detail.origMdseCd_A1, newRecord.origMdseCd);
                ZYPEZDItemValueSetter.setValue(detail.serTrxCmntTxt_AS, newRecord.serTrxCmntTxt);
                // error status (Name: Desc Text)
                String serErrStsNmDescTxt = null;
                if (ZYPCommonFunc.hasValue(newRecord.serErrStsCd)) {
                    SER_ERR_STSTMsg serErrStsRec = (SER_ERR_STSTMsg) ZYPCodeDataUtil.findByCode(SER_ERR_STS.class, getGlobalCompanyCode(), newRecord.serErrStsCd.getValue());
                    serErrStsNmDescTxt = serErrStsRec.serErrStsNm.getValue() + ":" + serErrStsRec.serErrStsDescTxt.getValue();
                }
                if (ZYPCommonFunc.hasValue(serErrStsNmDescTxt)) {
                    ZYPEZDItemValueSetter.setValue(detail.xxDplyByItemCnctLbNm_A3, serErrStsNmDescTxt);
                } else {
                    detail.xxDplyByItemCnctLbNm_A3.clear();
                }
            }
        }
        // sMsg -> cMsg
        NLCL1010CommonLogic.dispPage(cMsg, cMsg.A, sMsg.A);
        // success to update
        cMsg.setMessageInfo(NLCL1010Constant.ZZZM9003I, new String[] {"Submit" });
    }

    /**
     * find SER_TRX
     * @param glblCmpyCd String
     * @param detail NLCL1010_ASMsg
     * @return found one
     */
    private static SER_TRXTMsg findSerTrxForUpdate(String glblCmpyCd, NLCL1010_ASMsg detail) {
        SER_TRXTMsg cond = new SER_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.serTrxPk, detail.serTrxPk_A1);
        return (SER_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cond);
    }

    /**
     * find SER_TRX
     * @param glblCmpyCd String
     * @param detail NLCL1010_ASMsg
     * @return found one
     */
    private static SER_TRXTMsg findSerTrx(String glblCmpyCd, BigDecimal serTrxPk) {
        SER_TRXTMsg cond = new SER_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.serTrxPk, serTrxPk);
        return (SER_TRXTMsg) S21FastTBLAccessor.findByKey(cond);
    }

    /**
     * check if any record updated or not
     * @param cMsg
     * @param sMsg
     * @return true if updated
     */
    private boolean hasTouchedRecord(NLCL1010CMsg cMsg, NLCL1010SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLCL1010_ASMsg detail = sMsg.A.no(i);
            if (isAddRecord(detail)) {
                return true;
            }
            if (isUpdateRecord(detail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if given detail is added by manual
     * @param detail
     * @return true if added
     */
    private boolean isAddRecord(NLCL1010_ASMsg detail) {
        // added record (and not deleted)
        if (!ZYPCommonFunc.hasValue(detail.serTrxPk_A1) && !ZYPConstant.CHKBOX_ON_Y.equals(detail.xxChkBox_A1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * check if given detail is updated
     * @param detail
     * @return true if updated
     */
    private boolean isUpdateRecord(NLCL1010_ASMsg detail) {
        // updated record

        // comment
        if (!detail.serTrxCmntTxt_A1.getValue().equals(detail.serTrxCmntTxt_AS.getValue())) {
            return true;
        }

        // deleted record (and not added)
        if (ZYPCommonFunc.hasValue(detail.serTrxPk_A1) && ZYPConstant.CHKBOX_ON_Y.equals(detail.xxChkBox_A1.getValue())) {
            return true;
        }
        return false;
    }
}
