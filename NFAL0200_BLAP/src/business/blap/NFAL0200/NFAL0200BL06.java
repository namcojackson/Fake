package business.blap.NFAL0200;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFAL0200.common.NFAL0200CommonLogic;
import business.blap.NFAL0200.constant.NFAL0200Constant;
import business.blap.NFAL0200.constant.NFAL0200Constant.CoaSegmentTabs;
import business.db.COA_ACCTTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.PRNT_COA_ACCTTMsg;
import business.db.PRNT_COA_BRTMsg;
import business.db.PRNT_COA_CCTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

public class NFAL0200BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_CMN_Submit(cMsg, sMsg);
            // START 2016/10/21 J.Kim [QC#13514,ADD]
            } else if ("NFAL0200Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_DeleteSearch(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_SaveSearch(cMsg, sMsg);
            // END 2016/10/21 J.Kim [QC#13514,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }
    
    /**
    * Method name: doProcess_NFAL0200Scrn00_CMN_Submit
    * <dd>The method explanation: Update detail records.
    * <dd>Remarks:
    * @param cMsg Business Component Interface Message
    * @param sMsg Global area information
    */
   private void doProcess_NFAL0200Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

       NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
       NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
       
       CoaSegmentTabs selTab = NFAL0200CommonLogic.getSelectedTab(bizMsg);
       
       if (selTab == CoaSegmentTabs.BR) {
           if (bizMsg.C.getValidCount() <= 0) {
               return;
           }
           brUpdate(bizMsg, glblMsg);
       } else if (selTab == CoaSegmentTabs.ACCT) {
           if (bizMsg.E.getValidCount() <= 0) {
               return;
           }
           acctUpdate(bizMsg, glblMsg);
        // START 2016/08/26 J.Kim [QC#13515,ADD]
       } else if (selTab == CoaSegmentTabs.CC) {
           if (bizMsg.D.getValidCount() <= 0) {
               return;
           }
           ccUpdate(bizMsg, glblMsg);
       }
       // END 2016/08/26 J.Kim [QC#13515,ADD]
    }

   private void brUpdate(NFAL0200CMsg bizMsg, NFAL0200SMsg glblMsg) {
       boolean isUpdate = false;
       
       List<EZDTMsg> listTmsg = new ArrayList<EZDTMsg>();
       List<EZDTMsg> prntListTmsg = new ArrayList<EZDTMsg>();
       
       for (int i=0; i < bizMsg.C.getValidCount(); i++) {
           
           NFAL0200_CCMsg line = bizMsg.C.no(i);

           // START 2016/11/02 J.Kim [QC#13443,ADD]
           if (!getCoaBr(bizMsg, line)) {
               line.contrCoaBrCd_C.setErrorInfo(1, "NFAM0066E", new String[] {"Contract Branch", "COA Branch" });
                return;
           }
           // END 2016/11/02 J.Kim [QC#13443,ADD]

           // If value is updated, update the record
           if (!NFAL0200CommonLogic.compStr(line.coaBrRgCd_O.getValue(), line.coaBrRgCd_C.getValue())) {
               isUpdate = true;
           }
           if (!NFAL0200CommonLogic.compStr(line.coaBrZnCd_O.getValue(), line.coaBrZnCd_C.getValue())) {
               isUpdate = true;
           }
           if (!NFAL0200CommonLogic.compStr(line.coaBrPrintCd_O.getValue(), line.coaBrPrintCd_C.getValue())) {
               isUpdate = true;
           }
           if (!NFAL0200CommonLogic.compStr(line.coaBrTpCd_O.getValue(), line.coaBrTpCd_C.getValue())) {
               isUpdate = true;
           }
           // START 2016/11/02 J.Kim [QC#13443,MOD]
           // if (!NFAL0200CommonLogic.compStr(line.coaBrContrCd_O.getValue(), line.coaBrContrCd_C.getValue())) {
           if (!NFAL0200CommonLogic.compStr(line.contrCoaBrCd_O.getValue(), line.contrCoaBrCd_C.getValue())) {
           // END 2016/11/02 J.Kim [QC#13443,MOD]
               isUpdate = true;
           }
           if (!NFAL0200CommonLogic.compStr(line.coaBrPrtrCd_O.getValue(), line.coaBrPrtrCd_C.getValue())) {
               isUpdate = true;
           }
           // START 2016/11/02 J.Kim [QC#13443,MOD]
           // if (!NFAL0200CommonLogic.compStr(line.coaBrGeoCd_O.getValue(), line.coaBrGeoCd_C.getValue())) {
           if (!NFAL0200CommonLogic.compStr(line.coaBrGeoTxt_O.getValue(), line.coaBrGeoTxt_C.getValue())) {
           // END 2016/11/02 J.Kim [QC#13443,MOD]
               isUpdate = true;
           }
           
           if (isUpdate) {
               
               // summary
               if (ZYPConstant.FLG_ON_Y.equals(line.xxChkBox_C.getValue())) {
                   PRNT_COA_BRTMsg tmsg = new PRNT_COA_BRTMsg();
                   
                   setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                   setValue(tmsg.coaBrCd, line.coaBrCd_C.getValue());
                   
                   tmsg = (PRNT_COA_BRTMsg) EZDTBLAccessor.findByKey(tmsg);
                   
                   // exclusive check
                   if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_C.getValue(), line.ezUpTimeZone_C.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                       bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Branch"});
                       return;
                   }
                   
                   // values to be updated
                   setValue(tmsg.coaBrRgCd, line.coaBrRgCd_C.getValue());
                   setValue(tmsg.coaBrZnCd, line.coaBrZnCd_C.getValue());
                   setValue(tmsg.coaBrPrintCd, line.coaBrPrintCd_C.getValue());
                   setValue(tmsg.coaBrTpCd, line.coaBrTpCd_C.getValue());
                   // START 2016/11/02 J.Kim [QC#13443,MOD]
                   // setValue(tmsg.coaBrContrCd, line.coaBrContrCd_C.getValue());
                   setValue(tmsg.contrCoaBrCd, line.contrCoaBrCd_C.getValue());
                   // END 2016/11/02 J.Kim [QC#13443,MOD]
                   setValue(tmsg.coaBrPrtrCd, line.coaBrPrtrCd_C.getValue());
                   // START 2016/11/02 J.Kim [QC#13443,MOD]
                   // setValue(tmsg.coaBrGeoCd, line.coaBrGeoCd_C.getValue());
                   setValue(tmsg.coaBrGeoTxt, line.coaBrGeoTxt_C.getValue());
                   // END 2016/11/02 J.Kim [QC#13443,MOD]
                   prntListTmsg.add(tmsg);
                   
               } else {
                   COA_BRTMsg tmsg = new COA_BRTMsg();
                   
                   setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                   setValue(tmsg.coaBrCd, line.coaBrCd_C.getValue());
                   
                   tmsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(tmsg);
                   
                   // exclusive check
                   if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_C.getValue(), line.ezUpTimeZone_C.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                       bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Branch"});
                       return;
                   }
                   
                   // values to be updated
                   setValue(tmsg.coaBrRgCd, line.coaBrRgCd_C.getValue());
                   setValue(tmsg.coaBrZnCd, line.coaBrZnCd_C.getValue());
                   setValue(tmsg.coaBrPrintCd, line.coaBrPrintCd_C.getValue());
                   setValue(tmsg.coaBrTpCd, line.coaBrTpCd_C.getValue());
                   // START 2016/11/02 J.Kim [QC#13443,MOD]
                   // setValue(tmsg.coaBrContrCd, line.coaBrContrCd_C.getValue());
                   setValue(tmsg.contrCoaBrCd, line.contrCoaBrCd_C.getValue());
                   // END 2016/11/02 J.Kim [QC#13443,MOD]
                   setValue(tmsg.coaBrPrtrCd, line.coaBrPrtrCd_C.getValue());
                   // START 2016/11/02 J.Kim [QC#13443,MOD]
                   // setValue(tmsg.coaBrGeoCd, line.coaBrGeoCd_C.getValue());
                   setValue(tmsg.coaBrGeoTxt, line.coaBrGeoTxt_C.getValue());
                   // END 2016/11/02 J.Kim [QC#13443,MOD]
                   
                   listTmsg.add(tmsg);
               }
               
               // reset
               isUpdate = false;
           }
       }
       
       if (listTmsg.size() > 0) {
           
           EZDTMsg[] arryTmsg = listTmsg.toArray(new EZDTMsg[listTmsg.size()]);
           
           int rtn = S21FastTBLAccessor.update(arryTmsg);
               
           if (rtn != listTmsg.size()) {
               bizMsg.setMessageInfo("NFAM0032E");
               return;
           }
       }
       
       if (prntListTmsg.size() > 0) {
           
           EZDTMsg[] arryTmsg = prntListTmsg.toArray(new EZDTMsg[prntListTmsg.size()]);
           
           int rtn = S21FastTBLAccessor.update(arryTmsg);
               
           if (rtn != prntListTmsg.size()) {
               bizMsg.setMessageInfo("NFAM0032E");
               return;
           }
       }
       
       // refresh result (To update original value)
       NFAL0200CommonLogic.search(bizMsg, glblMsg, true);
       
       // set success message
       bizMsg.setMessageInfo("ZZZM9003I", new String[] {"COA Branch Update"});
   }
   

   private void acctUpdate(NFAL0200CMsg bizMsg, NFAL0200SMsg glblMsg) {
       boolean isUpdate = false;
       
       List<EZDTMsg> listTmsg = new ArrayList<EZDTMsg>();
       List<EZDTMsg> prntListTmsg = new ArrayList<EZDTMsg>();  // need separate list
       
       for (int i=0; i < bizMsg.E.getValidCount(); i++) {
           
           NFAL0200_ECMsg line = bizMsg.E.no(i);

           // If value is updated, update the record
           if (!NFAL0200CommonLogic.compStrForFlg(line.coaAcrlFlg_O.getValue(), line.coaAcrlFlg_E.getValue())) {
               isUpdate = true;
           }
           
           if (isUpdate) {
               if (ZYPConstant.FLG_ON_Y.equals(line.xxChkBox_E.getValue())) {
                   PRNT_COA_ACCTTMsg tmsg = new PRNT_COA_ACCTTMsg();
                   
                   setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                   setValue(tmsg.coaAcctCd, line.coaAcctCd_E.getValue());
                   
                   tmsg = (PRNT_COA_ACCTTMsg) EZDTBLAccessor.findByKey(tmsg);
                   
                   // exclusive check
                   if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_E.getValue(), line.ezUpTimeZone_E.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                       bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Account"});
                       return;
                   }
                   
                   // values to be updated
                   setValue(tmsg.coaAcrlFlg, NFAL0200CommonLogic.getFlgValue(line.coaAcrlFlg_E.getValue()));
                   
                   prntListTmsg.add(tmsg);
                               
               } else {
                   COA_ACCTTMsg tmsg = new COA_ACCTTMsg();
                   
                   setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                   setValue(tmsg.coaAcctCd, line.coaAcctCd_E.getValue());
                   
                   tmsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(tmsg);
                   
                   // exclusive check
                   if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_E.getValue(), line.ezUpTimeZone_E.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                       bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Account"});
                       return;
                   }
                   
                   // values to be updated
                   setValue(tmsg.coaAcrlFlg, NFAL0200CommonLogic.getFlgValue(line.coaAcrlFlg_E.getValue()));
                   
                   listTmsg.add(tmsg);
               }
                      
               // reset
               isUpdate = false;
           }
       }
       
       if (listTmsg.size() > 0) {
           
           EZDTMsg[] arryTmsg = listTmsg.toArray(new EZDTMsg[listTmsg.size()]);
           
           int rtn = S21FastTBLAccessor.update(arryTmsg);
               
           if (rtn != listTmsg.size()) {
               bizMsg.setMessageInfo("NFAM0032E");
               return;
           }
       }
       
       if (prntListTmsg.size() > 0) {
           
           EZDTMsg[] arryTmsg = prntListTmsg.toArray(new EZDTMsg[prntListTmsg.size()]);
           
           int rtn = S21FastTBLAccessor.update(arryTmsg);
               
           if (rtn != prntListTmsg.size()) {
               bizMsg.setMessageInfo("NFAM0032E");
               return;
           }
       }
       
       // refresh result (To update original value)
       NFAL0200CommonLogic.search(bizMsg, glblMsg, true);
       
       // set success message
       bizMsg.setMessageInfo("ZZZM9003I", new String[] {"COA Account Update"});
   }

   // START 2016/08/26 J.Kim [QC#13515,ADD]
    private void ccUpdate(NFAL0200CMsg bizMsg, NFAL0200SMsg glblMsg) {

        boolean isUpdate = false;
        List<EZDTMsg> listTmsg = new ArrayList<EZDTMsg>();
        List<EZDTMsg> prntListTmsg = new ArrayList<EZDTMsg>();
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NFAL0200_DCMsg line = bizMsg.D.no(i);
            // If value is updated, update the record
            if (!NFAL0200CommonLogic.compStrForFlg(line.coaLineBizTpCd_O.getValue(), line.coaLineBizTpCd_D.getValue())) {
                isUpdate = true;
            }

            if (isUpdate) {
                if (ZYPConstant.FLG_ON_Y.equals(line.xxChkBox_D.getValue())) {

                    PRNT_COA_CCTMsg tmsg = new PRNT_COA_CCTMsg();
                    setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                    setValue(tmsg.coaCcCd, line.coaCcCd_D);
                    tmsg = (PRNT_COA_CCTMsg) EZDTBLAccessor.findByKey(tmsg);
                    // exclusive check
                    if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_D.getValue(), line.ezUpTimeZone_D.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Cost Center" });
                        return;
                    }
                    // values to be updated
                    setValue(tmsg.coaLineBizTpCd, line.coaLineBizTpCd_D.getValue());
                    prntListTmsg.add(tmsg);

                } else {

                    COA_CCTMsg tmsg = new COA_CCTMsg();
                    setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                    setValue(tmsg.coaCcCd, line.coaCcCd_D.getValue());
                    tmsg = (COA_CCTMsg) EZDTBLAccessor.findByKey(tmsg);
                    // exclusive check
                    if (!ZYPDateUtil.isSameTimeStamp(line.ezUpTime_D.getValue(), line.ezUpTimeZone_D.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo("NFCM0794E", new String[] {"COA Cost Center" });
                        return;
                    }
                    // values to be updated
                    setValue(tmsg.coaLineBizTpCd, line.coaLineBizTpCd_D.getValue());

                    listTmsg.add(tmsg);
                }

                // reset
                isUpdate = false;
            }
        }

        if (listTmsg.size() > 0) {
            EZDTMsg[] arryTmsg = listTmsg.toArray(new EZDTMsg[listTmsg.size()]);
            int rtn = S21FastTBLAccessor.update(arryTmsg);
            if (rtn != listTmsg.size()) {
                bizMsg.setMessageInfo("NFAM0032E");
                return;
            }
        }

        if (prntListTmsg.size() > 0) {
            EZDTMsg[] arryTmsg = prntListTmsg.toArray(new EZDTMsg[prntListTmsg.size()]);
            int rtn = S21FastTBLAccessor.update(arryTmsg);
            if (rtn != prntListTmsg.size()) {
                bizMsg.setMessageInfo("NFAM0032E");
                return;
            }
        }

        // refresh result (To update original value)
        NFAL0200CommonLogic.search(bizMsg, glblMsg, true);
        // set success message
        bizMsg.setMessageInfo("ZZZM9003I", new String[] {"COA Cost Center Update" });
    }
   // END 2016/08/26 J.Kim [QC#13515,ADD]

    // START 2016/10/21 J.Kim [QC#13514,ADD]
    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFAL0200Scrn00_DeleteSearch(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFAL0200Constant.BIZ_ID);

        // call Search Option API
        if (NFAL0200CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFAL0200CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk_S.clear();
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFAL0200Scrn00_SaveSearch(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;

        if (NFAL0200CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NFBM0226E", new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) && NFAL0200CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            NFAL0200CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFAL0200Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.coaCcCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.coaChCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.coaProjCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxChkBox);

        // call Search Option API
        if (NFAL0200CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFAL0200CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
        }
    }
    // END 2016/10/21 J.Kim [QC#13514,ADD]

    // START 2016/11/02 J.Kim [QC#13443,ADD]
    private boolean getCoaBr(NFAL0200CMsg cMsg, NFAL0200_CCMsg ccMsg) {

        if (ZYPCommonFunc.hasValue(ccMsg.contrCoaBrCd_C)) {
            COA_BRTMsg coaBrTMsg = new COA_BRTMsg();
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.coaBrCd, ccMsg.contrCoaBrCd_C);
            coaBrTMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(coaBrTMsg);
            if (coaBrTMsg == null) {
                return false;
            }
        }
        return true;
    }
    // END 2016/11/02 J.Kim [QC#13443,ADD]
}
