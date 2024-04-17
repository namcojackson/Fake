/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 03/17/2011   Fujitsu         K.Kimura        Update          DefID:1875
 * 11/11/2022  	Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
package business.blap.NFCL5140.common;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.blap.NFCL5140.NFCL5140Query;
import business.blap.NFCL5140.NFCL5140SMsg;
import business.blap.NFCL5140.constant.NFCL5140Constant;
import business.db.AR_ADJ_COA_INFOTMsg;
import business.db.COA_PRODTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.S21_ORGTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * NFCL5140CommonLogic class.
 */
public class NFCL5140CommonLogic implements NFCL5140Constant {
    
    // START 2022/11/18 R.Takau [QC#57252, ADD]
    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    // END 2022/11/18 R.Takau [QC#57252, ADD]
    
    /**
     * @param glblCmpyCd String
     * @return AR_ADJ_TPTMsgArray
     */
    public static S21SsmEZDResult findArAdjTpList(String glblCmpyCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_FNAME_GLBL_CMPY_CD, glblCmpyCd);
        //---- start 2016/04/13 QC#7003 category to be 'WRT'
        queryParam.put("arAdjCatgCd", AR_ADJ_CATG.WRITE_OFF);
        //---- end 2016/04/13
        S21SsmEZDResult dsResult = NFCL5140Query.getInstance().findArAdjTpForCateAdj(queryParam);

        if (dsResult.isCodeNormal()) {
        	return dsResult;
        }
        
        return null;
    }

    /**
     * @param bizMsg NFCL5140CMsg
     * @param globalMsg NFCL5140SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageIn(NFCL5140CMsg bizMsg, NFCL5140SMsg globalMsg, int pageFrom) {

        int cnt = pageFrom;

        for (; cnt < pageFrom + bizMsg.A.length(); cnt++) {

            if (cnt < pageFrom + bizMsg.A.getValidCount()) {

                EZDMsg.copy(bizMsg.A.no(cnt - pageFrom), null, globalMsg.A.no(cnt), null);

            } else {
                break;
            }
        }
    }

    /**
     * @param bizMsg NFCL5140CMsg
     * @param globalMsg NFCL5140SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageOut(NFCL5140CMsg bizMsg, NFCL5140SMsg globalMsg, int pageFrom) {

        if (pageFrom < 0) {
            pageFrom = 0;
        } else {
            // do nothing.
        }

        int cnt = pageFrom;
        bizMsg.A.clear();

        for (; cnt < pageFrom + bizMsg.A.length(); cnt++) {
            if (cnt < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(cnt), null, bizMsg.A.no(cnt - pageFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(cnt - pageFrom);
        bizMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pageFrom + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * @param glblCmpyCd String
     * @param tocCd String
     * @return outMsg
     */
    public static S21_ORGTMsg findS21OrgInfo(String glblCmpyCd, String tocCd) {

        S21_ORGTMsg tMsg = new S21_ORGTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.tocCd.setValue(tocCd);

        S21_ORGTMsg outMsg = (S21_ORGTMsg) EZDTBLAccessor.findByKey(tMsg);

        return outMsg;

    }

    /**
     * @param glblCmpyCd String
     * @param coaProdCd String
     * @return outMsg
     */
    public static COA_PRODTMsg findCoaProdInfo(String glblCmpyCd, String coaProdCd) {

        COA_PRODTMsg tMsg = new COA_PRODTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.coaProdCd.setValue(coaProdCd);

        COA_PRODTMsg outMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(tMsg);

        return outMsg;

    }
    // START 2022/11/10 R.Takau [QC#57252, ADD]
    /**
     * Check 9 Segment
     * @param bizMsg NFCL5140CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean check9Seg(NFCL5140CMsg bizMsg, String[] coa) {
    	
        AR_ADJ_COA_INFOTMsg arAdjCoaInfoTMsg = new AR_ADJ_COA_INFOTMsg();
        int coaCmpyCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaCmpyCd").getDigit();
        int coaBrCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaBrCd").getDigit();
        int coaCcCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaCcCd").getDigit();
        int coaAcctCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaAcctCd").getDigit();
        int coaProdCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaProdCd").getDigit();
        int coaChCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaChCd").getDigit();
        int coaAfflCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaAfflCd").getDigit();
        int coaProjCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaProjCd").getDigit();
        int coaExtnCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaExtnCd").getDigit();   
        int coaIdx = 0;

        if (coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            bizMsg.xxCmntTxt.setErrorInfo(1, NFCM0833E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }
    
    
    /**
     * glCodeCombinationCheck
     * @param bizMsg NFBL1130CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean checkGlCodeCombination(NFCL5140CMsg bizMsg, String[] coa) {
        if (!NFCL5140CommonLogic.check9Seg(bizMsg, coa)) {
            return false;
        }

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        int coaIdx = 0;
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, "NFCL5140");

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();
            bizMsg.xxCmntTxt.clearErrorInfo();
            bizMsg.xxCmntTxt.setErrorInfo(1, msgId, new String[] {msgTxt });
            return false;
        } else {
            bizMsg .xxCmntTxt.clearErrorInfo();
        }
        return true;
    }
    
    /**
     * get9SegDefault
     * @param bizMsg
     */
    public static void get9SegDefault(NFCL5140CMsg bizMsg) {
        
        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizMsg);

        if (tMsg == null) {
            bizMsg.coaCmpyCd_DF.clear();
            bizMsg.coaAfflCd_DF.clear();
            bizMsg.coaBrCd_DF.clear();
            bizMsg.coaCcCd_DF.clear();
            bizMsg.coaAcctCd_DF.clear();
            bizMsg.coaProdCd_DF.clear();
            bizMsg.coaChCd_DF.clear();
            bizMsg.coaProjCd_DF.clear();
            bizMsg.coaExtnCd_DF.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_DF, tMsg.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_DF, tMsg.coaAfflCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_DF, tMsg.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_DF, tMsg.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_DF, tMsg.coaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_DF, tMsg.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_DF, tMsg.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_DF, tMsg.coaProjCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_DF, tMsg.coaExtnCd.getValue());
        }
        
        return;
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    public static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(NFCL5140CMsg bizMsg) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsg.appFuncId.setValue(bizMsg.getBusinessID());

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // END  2022/11/10 R.Takau [QC#57252,ADD]
}
