/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2570;

import static business.blap.NMAL2570.constant.NMAL2570Constant.NZZM0000E;
import static business.blap.NMAL2570.constant.NMAL2570Constant.NZZM0001W;
import static business.blap.NMAL2570.constant.NMAL2570Constant.NMAM0072E;
import static business.blap.NMAL2570.constant.NMAL2570Constant.NMAM8187E;
import static business.blap.NMAL2570.constant.NMAL2570Constant.NMAM8461E;
import static business.blap.NMAL2570.constant.NMAL2570Constant.MULT_SEL_MODE_CD;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2570.common.NMAL2570CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_FUNC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL2570BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            // QC#7781
            cMsg.setCommitSMsg(true);

            if ("NMAL2570_INIT".equals(screenAplID)) {
                doProcess_NMAL2570_INIT((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            } else if ("NMAL2570Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_Search((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            } else if ("NMAL2570Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_CMN_Clear((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            } else if ("NMAL2570Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_PageNext((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            } else if ("NMAL2570Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_PagePrev((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            // QC#7781
            } else if ("NMAL2570Scrn00_AddSelected".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_AddSelected((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
            } else if ("NMAL2570Scrn00_DeleteSelected".equals(screenAplID)) {
                doProcess_NMAL2570Scrn00_DeleteSelected((NMAL2570CMsg) cMsg, (NMAL2570SMsg) sMsg);
                
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2570Scrn00_Search(NMAL2570CMsg bizMsg, NMAL2570SMsg sMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2570Query.getInstance().getResource(bizMsg, sMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setCommitSMsg(true);
            bizMsg.setMessageInfo(NZZM0000E);
        } else if (ssmResult.isCodeNormal()) {

            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            } else {
                sMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg.A);

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }

        }
    }

    /**
     * @param bizMsg NMAL2530CMsg
     * @param sMsg NMAL2530SMsg
     */
    private void doProcess_NMAL2570_INIT(NMAL2570CMsg bizMsg, NMAL2570SMsg sMsg) {

        createPullDown(bizMsg);

        // When the parameter is set.
        if (ZYPCommonFunc.hasValue(bizMsg.xxPsnNm_H1) || ZYPCommonFunc.hasValue(bizMsg.psnCd_H1) || ZYPCommonFunc.hasValue(bizMsg.jobTtlCd_H1) || ZYPCommonFunc.hasValue(bizMsg.psnNum_H1) || ZYPCommonFunc.hasValue(bizMsg.orgFuncRoleTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H1) || ZYPCommonFunc.hasValue(bizMsg.effFromDt_H1) || ZYPCommonFunc.hasValue(bizMsg.effThruDt_H1)) {
            doProcess_NMAL2570Scrn00_Search(bizMsg, sMsg);
        }
        
        // QC#7781
        if(MULT_SEL_MODE_CD.equals(bizMsg.xxModeCd_H1.getValue())){
            for(int i=0; i<bizMsg.B.getValidCount(); i++){
                String psnCd = bizMsg.B.no(i).psnCd_B1.getValue();
                S21SsmEZDResult ssmResult = NMAL2570Query.getInstance().getPerson(bizMsg, psnCd);
                if (ssmResult.isCodeNormal()) {
                    List<Map> list = (List<Map>) ssmResult.getResultObject();
                    if( list.size() > 0){
                        Map map = list.get(0);
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnCd_B1, (String)map.get("PSN_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnTpCd_B1, (String)map.get("PSN_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnTpNm_B1, (String)map.get("PSN_TP_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnLastNm_B1, (String)map.get("PSN_LAST_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnFirstNm_B1, (String)map.get("PSN_FIRST_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).jobTtlCd_B1, (String)map.get("JOB_TTL_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).hrTtlNm_B1, (String)map.get("HR_TTL_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).jobTtlNm_B1, (String)map.get("JOB_TTL_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).psnNum_B1, (String)map.get("PSN_NUM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).hrSupvNm_B1, (String)map.get("HR_SUPV_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).effFromDt_B1, (String)map.get("EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).effThruDt_B1, (String)map.get("EFF_THRU_DT"));
                    }
                }
            }
        }
    }

    private void doProcess_NMAL2570Scrn00_CMN_Clear(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL2570_INIT(cMsg, sMsg);
    }

    private void doProcess_NMAL2570Scrn00_PageNext(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {

        NMAL2570CommonLogic.updateGlblMsg(cMsg, sMsg);

        // copy data from glblMsg onto bizMsg
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2570CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }

    private void doProcess_NMAL2570Scrn00_PagePrev(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {

        NMAL2570CommonLogic.updateGlblMsg(cMsg, sMsg);

        // copy data from glblMsg onto bizMsg
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NMAL2570CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }

    private void createPullDown(NMAL2570CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL2570Query.getInstance().getRoleName(cMsg);

        if (ssmResult.isCodeNormal()) {

            List<Map> whList = (List<Map>) ssmResult.getResultObject();
            createPulldownList(cMsg.orgFuncRoleTpCd_H2, cMsg.orgFuncRoleTpNm_H1, whList, new String[] {"ORG_FUNC_ROLE_TP_CD", "ORG_FUNC_ROLE_TP_NM" });
        }

        ZYPCodeDataUtil.createPulldownList(ORG_FUNC_ROLE_TP.class, cMsg.orgFuncRoleTpCd_H2, cMsg.orgFuncRoleTpNm_H1);

    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < cd.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cd.no(i))) {
                for (int j = 0; j < pullDownList.size(); j++) {

                    Map pullDownData = pullDownList.get(j);
                    cd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
                    value.no(i).setValue((String) pullDownData.get(dbColumn[0]));

                    i = i + 1;
                    if (i >= cd.length()) {
                        break;
                    }
                }
                break;
            }
        }
    }
    
    // QC#7781
    private void doProcess_NMAL2570Scrn00_AddSelected(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {
        // save check box input to SMsg
        NMAL2570CommonLogic.updateGlblMsg(cMsg, sMsg);

        // check duplicate
        int firstErrorRowIndex = -1;
        List<Integer> listSelected = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        for (int selectedIndex : listSelected) {
            String psnCd = sMsg.A.no(selectedIndex).psnCd_A1.getValue();
            if (existInSelectedRecords(cMsg, psnCd)) {
                sMsg.A.no(selectedIndex).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {"Person Code" });
                firstErrorRowIndex = selectedIndex;
            }
        }
        if (firstErrorRowIndex >= 0) {
            cMsg.xxPageShowFromNum.setValue(firstErrorRowIndex + 1);
            NMAL2570CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
            return;
        }

        // check max selected rows
        if (listSelected.size() == 0) {
            cMsg.setMessageInfo(NMAM8461E, new String[] {"Resource" });
            return;
        } else if (listSelected.size() + cMsg.B.getValidCount() > cMsg.B.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Selected Row", Integer.toString(cMsg.B.length()) });
            return;
        }

        // add selected rows to bottom list
        for (int i = 0; i < listSelected.size(); i++) {
            int upperListRowIndex = listSelected.get(i);
            int bottomListRowIndex = cMsg.B.getValidCount();
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A1", cMsg.B.no(bottomListRowIndex), "B1");
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A2", cMsg.B.no(bottomListRowIndex), "B2");
            sMsg.A.no(upperListRowIndex).xxChkBox_A1.clear();
            cMsg.B.setValidCount(bottomListRowIndex + 1);
        }
        
        // refresh current page
        NMAL2570CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }
    
    private boolean existInSelectedRecords(NMAL2570CMsg cMsg, String psnCd) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (psnCd.equals(cMsg.B.no(i).psnCd_B1.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void doProcess_NMAL2570Scrn00_DeleteSelected(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {
        List<Integer> listSelected = ZYPTableUtil.getSelectedRows(cMsg.B, "xxChkBox_B1", ZYPConstant.CHKBOX_ON_Y);
        if (listSelected.size() == 0) {
            cMsg.setMessageInfo(NMAM8461E, new String[] {"Resource"});
            return;
        }
        ZYPTableUtil.deleteRows(cMsg.B, listSelected);
    }
}
