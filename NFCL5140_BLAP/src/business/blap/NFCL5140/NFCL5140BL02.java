/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 02/08/2011   Fujitsu         K.Kimura        Update          For COA_GL_CMBN_4_SEG 
 * 02/24/2011   Fujitsu         K.Kimura        Update          DefID:1670
 * 03/17/2011   Fujitsu         K.Kimura        Update          DefID:1875
 * 04/15/2011   Fujitsu         K.Kimura        Update          DefID:1988
 * 04/20/2011   Fujitsu         K.Kimura        Update          DefID:1988
 * 08/09/2011   Fujitsu         T.Tanaka        Update          353895��65�韻rossPurge
 * 11/21/2022	Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
package business.blap.NFCL5140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL5140.common.NFCL5140CommonLogic;
import business.blap.NFCL5140.constant.NFCL5140Constant;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.COA_PRODTMsg;
import business.db.DEPT_PROD_COATMsg;
import business.db.S21_ORGTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * NFCL5140BL02 class.
 */
public class NFCL5140BL02 extends S21BusinessHandler implements NFCL5140Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL5140_INIT".equals(screenAplID)) {
                doProcess_NFCL5140_INIT(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_Add_Record".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_Add_Record(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_Delete_Record".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_Delete_Record(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_Onchange_PD_AdjTpCd".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_Onchange_PD_AdjTpCd(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_Insert_Record".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_Insert_Record(cMsg, sMsg);
                doProcess_NFCL5140Scrn00_Add_Record(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL5140Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_PagePrev(cMsg, sMsg);
            // START 2022/11/10 R.Takau [QC#57252, ADD]
            } else if ("NFCL5140Scrn00_OpenWin_ChargeAccount".equals(screenAplID)) {
                doProcess_NFCL5140Scrn00_OpenWin_ChargeAccount(cMsg, sMsg);
            // END 2022/11/10 R.Takau [QC#57252, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL5140_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.P.clear();
        bizMsg.P.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
        this.setADJType(bizMsg);

        setAftDeclPntDigitNum(bizMsg);
    }

    private void doProcess_NFCL5140Scrn00_Add_Record(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;
        EZDMsg.copy(globalMsg.A, "A1", bizMsg.P, "PA");
    }

    private void doProcess_NFCL5140Scrn00_Delete_Record(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();

        for (int i = pagenationFrom; i < pagenationTo; i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }

        boolean hasDeleteFlg = false;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                hasDeleteFlg = true;
            }
        }
        if (!hasDeleteFlg) {
            bizMsg.setMessageInfo("NFCM0094E");
            return;
        }

        int cnt = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                globalMsg.A.no(i).clear();
                cnt++;
            } else {
                EZDMsg.copy(globalMsg.A.no(i), null, globalMsg.A.no(i - cnt), null);
            }
        }
        int clearStart = globalMsg.A.getValidCount() - cnt;
        for (int i = clearStart; i < globalMsg.A.getValidCount(); i++) {
            globalMsg.A.no(i).clear();
        }
        globalMsg.A.setValidCount(clearStart);
        bizMsg.A.clear();

        int pageNum = (globalMsg.A.getValidCount() - 1) / bizMsg.A.length() + 1;

        int pageShowFromNum = (pageNum - 1) * bizMsg.A.length();

        int i = 0;
        for (; pageShowFromNum + i < globalMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(pageShowFromNum + i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pageShowFromNum + 1);
        bizMsg.xxPageShowToNum.setValue(globalMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

    }

    private void doProcess_NFCL5140Scrn00_Onchange_PD_AdjTpCd(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;

        this.getDefDataFromArAdjTp(bizMsg);
    }

    private void doProcess_NFCL5140Scrn00_Insert_Record(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;
        //START 2022/11/17 R.Takau [QC#57252,ADD]
        if (SLC_OTHER.equals(bizMsg.arAdjTpCd_P1.getValue())) {
            String[] coa = bizMsg.xxCmntTxt.getValue().split("\\.");
            if(!NFCL5140CommonLogic.checkGlCodeCombination(bizMsg, coa)){
                return;
            }
        }
        //END 2022/11/21 R.Takau [QC#57252,ADD]
        
        if (globalMsg.A.length() == globalMsg.A.getValidCount()) {
            setValue(bizMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo("NFCM0027E");
            return;
        }

        boolean errFlg = false;
        
        if (errFlg) {
            return;
        }
        
        if (AR_TRX_TP_CD.ADJ.getValue().equals(bizMsg.xxModeInd.getValue())) {
            
            // check AR_ADJ
            AR_ADJ_TPTMsg tMsgArAdjTp = findArAdjTpInfo(this.getGlobalCompanyCode(), bizMsg.arAdjTpCd_P1.getValue());
            if (tMsgArAdjTp == null) {
                return;
            } else {
                // nothing
            }
            
        }

        this.copyHeaderToDetail(bizMsg, globalMsg);

        bizMsg.A.clear();

        int pageNum = (globalMsg.A.getValidCount() - 1) / bizMsg.A.length() + 1;

        int pageShowFromNum = (pageNum - 1) * bizMsg.A.length();

        int i = 0;
        for (; pageShowFromNum + i < globalMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(pageShowFromNum + i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pageShowFromNum + 1);
        bizMsg.xxPageShowToNum.setValue(globalMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

    }

    private void doProcess_NFCL5140Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PageJump================================START", this);

        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());

        for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }

        // copy data from SMsg onto CMsg
        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PageJump================================END", this);
    }

    private static int getPagenationFrom(int pageFromNum) {

        int pagenationFrom = pageFromNum;

        if (0 != pagenationFrom) {
            pagenationFrom = pagenationFrom - 1;
        }
        return pagenationFrom;
    }

    private void doProcess_NFCL5140Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PageNext================================START", this);

        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();

        int i = (bizMsg.xxPageShowFromNum_H1.getValueInt() - 1);
        int count = 0;
        for (; i < pagenationTo; i++) {
            EZDMsg.copy(bizMsg.A.no(count), null, globalMsg.A.no(i), null);
            count++;
        }

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        // copy data from SMsg onto CMsg

        int j = pagenationFrom;
        for (; j < pagenationFrom + bizMsg.A.length(); j++) {
            if (j < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(j), null, bizMsg.A.no(j - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(j - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PageNext================================END", this);
    }

    private void doProcess_NFCL5140Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PagePrev================================START", this);

        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        NFCL5140SMsg globalMsg = (NFCL5140SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();

        int i = (pagenationFrom + bizMsg.A.length());
        int count = 0;
        for (; i < pagenationTo; i++) {
            EZDMsg.copy(bizMsg.A.no(count), null, globalMsg.A.no(i), null);
            count++;
        }

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        // copy data from SMsg onto CMsg

        int j = pagenationFrom;
        for (; j < pagenationFrom + bizMsg.A.length(); j++) {
            if (j < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(j), null, bizMsg.A.no(j - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(j - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(1, "doProcess_NFCL5140Scrn00_PagePrev================================END", this);
    }

    private void copyHeaderToDetail(NFCL5140CMsg bizMsg, NFCL5140SMsg globalMsg) {
        int targetRecordNum = globalMsg.A.getValidCount();
        setValue(globalMsg.A.no(targetRecordNum).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        setValue(globalMsg.A.no(targetRecordNum).xxInpAmtNum_A1, bizMsg.xxInpAmtNum);
        setValue(globalMsg.A.no(targetRecordNum).arTrxTpCd_A1, getArTrxTpCd(bizMsg));
        setValue(globalMsg.A.no(targetRecordNum).arCustRefNum_A1, "On Account");
        setValue(globalMsg.A.no(targetRecordNum).xxArAdjTpNm_A1, this.getPulldownValue(bizMsg, bizMsg.arAdjTpCd_P1.getValue()));
        setValue(globalMsg.A.no(targetRecordNum).arAdjTpCd_A1, bizMsg.arAdjTpCd_P1);
        setValue(globalMsg.A.no(targetRecordNum).xxInvCmntTxt_A1, bizMsg.xxInvCmntTxt);
        if (AR_TRX_TP_CD.ADJ.toString().equals(getArTrxTpCd(bizMsg))) {
            AR_ADJ_TPTMsg arAdjTp = findArAdjTpInfo(getGlobalCompanyCode(), bizMsg.arAdjTpCd_P1.getValue());
            setValue(globalMsg.A.no(targetRecordNum).arAdjManEntryFlg_A1, arAdjTp.arAdjManEntryFlg);
            setValue(globalMsg.A.no(targetRecordNum).arCustRefNum_A1, "");
        }

        globalMsg.A.setValidCount(targetRecordNum + 1);

    }

    private String getPulldownValue(NFCL5140CMsg bizMsg, String key) {

        if (S21StringUtil.isEmpty(key)) {
            return "";
        }

        for (int i = 0; i < bizMsg.xxArAdjTpNm.length(); i++) {
            if (key.equals(bizMsg.arAdjTpCd.no(i).getValue())) {
                return bizMsg.xxArAdjTpNm.no(i).getValue();
            }
        }
        return "";
    }

    /**
     * @param bizMsg NFCL5140CMsg
     * @return String
     */
    public String getArTrxTpCd(NFCL5140CMsg bizMsg) {
        if (AR_TRX_TP_CD.ACC.getValue().equals(bizMsg.xxModeInd.getValue())) {
            return AR_TRX_TP_CD.ACC.toString();
        } else if (AR_TRX_TP_CD.DED.getValue().equals(bizMsg.xxModeInd.getValue())) {
            return AR_TRX_TP_CD.DED.toString();
        } else if (AR_TRX_TP_CD.ADJ.getValue().equals(bizMsg.xxModeInd.getValue())) {
            return AR_TRX_TP_CD.ADJ.toString();
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param arAdjTpCd String
     * @return AR_ADJ_TPTMsg
     */
    public static AR_ADJ_TPTMsg findArAdjTpInfo(String glblCmpyCd, String arAdjTpCd) {

        AR_ADJ_TPTMsg tMsg = new AR_ADJ_TPTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.arAdjTpCd.setValue(arAdjTpCd);

        AR_ADJ_TPTMsg outMsg = (AR_ADJ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

        return outMsg;

    }

    private void setADJType(NFCL5140CMsg bizMsg) {

        try {

        	S21SsmEZDResult arAdjTpTMsgArray = new S21SsmEZDResult();
            arAdjTpTMsgArray = NFCL5140CommonLogic.findArAdjTpList(this.getGlobalCompanyCode());
            if (arAdjTpTMsgArray.isCodeNormal()) {
                List<Map> arAdjTpList = (List<Map>) arAdjTpTMsgArray.getResultObject();
                this.createAdjTpCdPullDown(bizMsg, arAdjTpList);
            }


        } catch (IllegalArgumentException e) {
            bizMsg.setMessageInfo("NFCM0106E", null);
        }
    }

    private void createAdjTpCdPullDown(NFCL5140CMsg bizMsg, List<Map> arAdjTpTMsgArray) {
    	if ( arAdjTpTMsgArray != null && arAdjTpTMsgArray.size() > 0) {
    		
    		for (int i = 0; i < arAdjTpTMsgArray.size(); i++) {

                Map pullDownData = arAdjTpTMsgArray.get(i);

                bizMsg.arAdjTpCd.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_CD"));
                bizMsg.xxArAdjTpNm.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_NM"));
            }
        } else {
            bizMsg.setMessageInfo("NFCM0106E", null);
        }

    }

    private void getDefDataFromArAdjTp(NFCL5140CMsg bizMsg) {
        AR_ADJ_TPTMsg keyInfo = new AR_ADJ_TPTMsg();
        setValue(keyInfo.glblCmpyCd, this.getGlobalCompanyCode());
        setValue(keyInfo.arAdjTpCd, bizMsg.arAdjTpCd_P1);

        AR_ADJ_TPTMsg result = (AR_ADJ_TPTMsg) EZDTBLAccessor.findByKey(keyInfo);
        //setValue(bizMsg.tocCd, result.arDefTocCd);
        //setValue(bizMsg.coaProdCd, result.arDefCoaProdCd);

        if (ZYPConstant.FLG_ON_Y.equals(result.arAdjTocEntryFlg.getValue())) {
            setValue(bizMsg.arAdjTocEntryFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(bizMsg.arAdjTocEntryFlg, ZYPConstant.FLG_OFF_N);
        }

        setValue(bizMsg.arMinusEntryTrgtFlg, result.arMinusEntryTrgtFlg);

    }

    private void setAftDeclPntDigitNum(NFCL5140CMsg bizMsg) {
        if (bizMsg.ccyCd_H1.isClear()) {
            // This logic shouldn't be used.
            bizMsg.aftDeclPntDigitNum_H1.setValue(new BigDecimal(2));

        } else {

            BigDecimal aftDeclPntDigitNum = NFCCmnMethod.getAftDeclPntDigitNum(getGlobalCompanyCode(), bizMsg.ccyCd_H1.getValue());

            bizMsg.aftDeclPntDigitNum_H1.setValue(aftDeclPntDigitNum);
        }
    }
    
    /**
     * @param glblCmpyCd String
     * @param arAdjTpCd String
     * @return AR_ADJ_TPTMsg
     */
    public static DEPT_PROD_COATMsg findDeptProdCoaInfo(String glblCmpyCd, String fourthOrgCd, String firstProdCd) {

        DEPT_PROD_COATMsg tMsg = new DEPT_PROD_COATMsg();

        if (fourthOrgCd.length() > 3 || fourthOrgCd == null) {
            return null;
        }
        
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.deptId.setValue(fourthOrgCd);
        tMsg.firstProdCtrlCd.setValue(firstProdCd);

        DEPT_PROD_COATMsg outMsg = (DEPT_PROD_COATMsg) EZDTBLAccessor.findByKey(tMsg);

        return outMsg;

    }
    
    // START 2022/11/10 R.Takau [QC#57252,ADD]
    /**
     * Method name: doProcess_NFCL5140Scrn00_OpenWin_ChargeAccount
     * <dd>The method explanation: Event when selecting Charge Account.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5140Scrn00_OpenWin_ChargeAccount(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        
        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt)) {
            String xxCmntTxt = bizMsg.xxCmntTxt.getValue();
            String[] strSplit = xxCmntTxt.split("\\.");
            if (!NFCL5140CommonLogic.check9Seg(bizMsg, strSplit)) {
                return;
            }           
        }
        NFCL5140CommonLogic.get9SegDefault(bizMsg);
    }
    // END 2022/11/10 R.Takau [QC#57252,ADD]
}
