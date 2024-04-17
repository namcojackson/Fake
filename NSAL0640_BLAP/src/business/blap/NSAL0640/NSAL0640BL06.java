/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0640;

import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0419W;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0045E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0362E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.SVC_CONTR_BR;
import static business.blap.NSAL0640.constant.NSAL0640Constant.SVC_MEMO;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0453E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0394W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0640.common.NSAL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4916
 * 2016/04/14   Hitachi         M.Gotou         Update          QC#4703
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0640BL06 extends S21BusinessHandler {

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0640CMsg cMsg = (NSAL0640CMsg) ezdCMsg;
        NSAL0640SMsg sMsg = (NSAL0640SMsg) ezdSMsg;
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL0640Scrn00_CMN_Submit".equals(screenAplId)) {
            return checkInput_NSAL0640Scrn00_CMN_Submit(cMsg, sMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
    }

    private boolean checkInput_NSAL0640Scrn00_CMN_Submit(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        // mod start 2016/12/08 CSA QC#4210
        NSAL0640CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        boolean rtnVal = true;
        int pageIdx = 0;
        if (NSAL0640CommonLogic.checkSelect(cMsg, sMsg)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL0640_ASMsg asMsg = sMsg.A.no(i);
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    if (!NSAL0640CommonLogic.checkBranch(cMsg.glblCmpyCd.getValue(), asMsg.svcContrBrCd_A2.getValue())) {
                        asMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NSAM0045E, new String[] {SVC_CONTR_BR });
                        if (rtnVal) {
                            pageIdx = i;
                        }
                        rtnVal = false;
                    }
                    // mod start 2016/03/28 CSA Defect#4702,4703,4915
                    //if (!NSAL0640CommonLogic.checkRep(cMsg.glblCmpyCd.getValue(), acMsg.tocCd_A2.getValue())) {
                        //acMsg.tocNm_A2.setErrorInfo(1, NSAM0045E, new String[] {TOC });
                    if (!NSAL0640CommonLogic.checkRep(cMsg.glblCmpyCd.getValue(), asMsg.psnCd_A2.getValue())) {
                        // mod start 2016/03/28 CSA Defect#4703
                        //acMsg.xxPsnNm_A2.setErrorInfo(1, NSAM0045E, new String[] {S21_PSN });
                        asMsg.xxPsnNm_A2.setErrorInfo(1, NSAM0453E);
                        // mod start 2016/03/28 CSA Defect#4703
                        if (rtnVal) {
                            pageIdx = i;
                        }
                        rtnVal = false;
                    }

                    //if (!NSAL0640CommonLogic.checkRelationBrAndRep(cMsg.glblCmpyCd.getValue(), acMsg.svcContrBrCd_A2.getValue(), acMsg.psnCd_A2.getValue())) {
                        //acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NSAM0138E, new String[] {SVC_CONTR_BR, TOC });
                        //acMsg.tocNm_A2.setErrorInfo(1, NSAM0138E, new String[] {SVC_CONTR_BR, TOC });
                        //rtnVal = false;
                    //}
                    // mod end 2016/03/28 CSA Defect#4702,4703,4915
                }
            }
            if (!rtnVal) {
                cMsg.setMessageInfo(NSAM0394W);
                NSAL0640CommonLogic.showTargetPage(cMsg, sMsg, pageIdx);
            }
        } else {
            rtnVal = false;
            return rtnVal;
        }
        if (!hasValue(cMsg.svcMemoRsnCd_H)) {
            cMsg.svcMemoRsnCd_H.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }
        if (!hasValue(cMsg.svcCmntTxt_H)) {
            cMsg.svcCmntTxt_H.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }

        return rtnVal;
    }

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0640CMsg cMsg = (NSAL0640CMsg) arg0;
        NSAL0640SMsg sMsg = (NSAL0640SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0640Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0640Scrn00_CMN_Submit(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        // add start 2016/04/04 CSA Defect#4916
        String glblCmpyCd = getGlobalCompanyCode();
        boolean checkWarn = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (!NSAL0640CommonLogic.checkSvcBrResrcReln(glblCmpyCd, sMsg.A.no(i).svcContrBrCd_A2.getValue(), sMsg.A.no(i).psnCd_A2.getValue())) {
                    if (!ZYPCommonFunc.hasValue(cMsg.xxRsltFlg) || !ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                        sMsg.A.no(i).xxGenlFldAreaTxt_A2.setErrorInfo(2, NSAM0419W);
                        sMsg.A.no(i).xxPsnNm_A2.setErrorInfo(2, NSAM0419W);
                        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
                        checkWarn = false;
                        continue;
                    }
                }
            }
        }
        if (!checkWarn) {
            cMsg.setMessageInfo(NSAM0419W);
            NSAL0640CommonLogic.copyThisPageToCMsg(cMsg, sMsg);
            return;
        }
        cMsg.xxRsltFlg.clear();
        // add end 2016/04/04 CSA Defect#4916
        NSAL0640CommonLogic.updateContract(cMsg, sMsg);
        NSAL0640CommonLogic.copyThisPageToCMsg(cMsg, sMsg);
    }
    // mod end 2016/12/08 CSA QC#4210
}
