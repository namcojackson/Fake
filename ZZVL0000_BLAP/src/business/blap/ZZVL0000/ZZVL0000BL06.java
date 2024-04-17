package business.blap.ZZVL0000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZVL0000.constant.ZZVL0000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
// START 01/27/17 C.Ogaki [Delete] Release 2000 byte length limit
//import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
// END   01/27/17 C.Ogaki [Delete] Release 2000 byte length limit
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 * 2017/01/27   Fujitsu         C.Ogaki         Update          ---
 *</pre>
 */
public class ZZVL0000BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZVL0000Scrn01_CMN_Add".equals(screenAplID)) {
                doProcess_ZZVL0000Scrn01_CMN_Add((ZZVL0000CMsg) cMsg, sMsg);
            } else if ("ZZVL0000Scrn00_Delete".equals(screenAplID)) {
                doProcess_ZZVL0000Scrn00_Delete((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn00_Set_Default".equals(screenAplID)) {
                doProcess_ZZVL0000Scrn00_Set_Default((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZVL0000Scrn01_CMN_Add(ZZVL0000CMsg cMsg, EZDSMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        int settingCount = 0;
        int existDefNm = 0;
        boolean isError = false;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String scrAppId = bizMsg.B.no(i).bizAppId_3.getValue();
            String scrTblNm = bizMsg.B.no(i).scrTblNm_2.getValue();
            String scrTblColDefNm = bizMsg.B.no(i).scrTblColDefNm_2.getValue();
            String newTblColDefNm = bizMsg.B.no(i).scrTblColDefNm_3.getValue();
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.B.no(i).xxChkBox_2.getValue())) {
                settingCount = ZZVL0000Query.getInstance().getPreferredViewCount(bizMsg.glblCmpyCd_1.getValue(), bizMsg.roleNm_2.getValue(), scrAppId, scrTblNm, null);
                existDefNm =  ZZVL0000Query.getInstance().getPreferredViewCount(bizMsg.glblCmpyCd_1.getValue(), bizMsg.roleNm_2.getValue(), scrAppId, scrTblNm, newTblColDefNm);
                if ((settingCount < ZZVL0000Constant.PREFERRED_VIEW_SETTING_MAX)
                        || ((settingCount >= ZZVL0000Constant.PREFERRED_VIEW_SETTING_MAX) && (existDefNm == 1))) {
                    ScrTblColDefAccessor query = new ScrTblColDefAccessor();
                    int returnCode = query.create(bizMsg.glblCmpyCd_1.getValue(), bizMsg.roleNm_2.getValue(), scrAppId, scrTblNm, scrTblColDefNm, newTblColDefNm, bizMsg.usrId_2.getValue());
                    if (returnCode == ZZVL0000Constant.SETTING_NOT_FOUND) {
                        bizMsg.setMessageInfo(ZZVL0000Constant.ZZVM0003E, new String[]{bizMsg.usrId_2.getValue(), scrAppId, scrTblNm, scrTblColDefNm});
                        return;
                    }
                } else {
                    bizMsg.B.no(i).scrTblColDefNm_3.setErrorInfo(2, ZZVL0000Constant.ZZVM0002W , new String[]{bizMsg.roleNm_2.getValue(), scrAppId});
                    isError = true;
                }
            }
        }
        if (!isError) {
            bizMsg.setMessageInfo(ZZVL0000Constant.ZZM8100I);
        }
    }

    private void doProcess_ZZVL0000Scrn00_Delete(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {
        List<String[]> deleteList = new ArrayList<String[]>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_1.getValue())) {
                String[] deleteInfo = new String[] {
                        cMsg.glblCmpyCd_1.getValue(),
                        cMsg.roleNm_1.getValue(),
                        cMsg.A.no(i).scrAppId_1.getValue(),
                        cMsg.A.no(i).scrTblNm_1.getValue(),
                        cMsg.A.no(i).scrTblColDefNm_1.getValue(),
                        };
                deleteList.add(deleteInfo);
            }
        }
        ScrTblColDefAccessor query = new ScrTblColDefAccessor();
        int count = query.logicalRemove(deleteList);


        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        // START 01/27/17 C.Ogaki [Modify] Release 2000 byte length limit
//        S21SsmEZDResult ssmResult = ZZVL0000Query.getInstance().getPreferredViewList(cMsg, sMsg);
//
//        if (ssmResult.isCodeNormal()) {
//
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt > sMsg.A.length()) {
//                cMsg.setMessageInfo("ZZZM9002W");
//                queryResCnt = sMsg.A.length();
//            }
//
//            int i = 0;
//            for (; i < sMsg.A.getValidCount(); i++) {
//                if (i == cMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
//            }
//            cMsg.A.setValidCount(i);
//
//            cMsg.xxPageShowFromNum_A.setValue(1);
//            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
//            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
//
//        } else {
//            cMsg.xxPageShowFromNum_A.clear();
//            cMsg.xxPageShowToNum_A.clear();
//            cMsg.xxPageShowOfNum_A.clear();
//        }

        List<String[]> result = query.getPreferredViewList(cMsg.glblCmpyCd_1.getValue(), cMsg.roleNm_1.getValue(), sMsg.A.length() + 1);
        if (result.size() > 0) {
            int queryResCnt = result.size();
            if (result.size() > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            sMsg.A.setValidCount(queryResCnt);
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                String defNm     = result.get(i)[0];
                String scrAppId  = result.get(i)[1];
                String scrTblNm  = result.get(i)[2];
                String bizAppNm  = result.get(i)[3];
                String usrDefFlg = result.get(i)[4];
                String orgOwner  = result.get(i)[5];
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblColDefNm_1, defNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrAppId_1, scrAppId);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblNm_1, scrTblNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).bizAppNm_1,  bizAppNm);
                if (Boolean.parseBoolean(usrDefFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "Y");
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "N");
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).usrId_1, orgOwner);

                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
            }
            if (i < cMsg.A.length()) {
                cMsg.A.setValidCount(i);
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }
        // END   01/27/17 C.Ogaki [Modify] Release 2000 byte length limit
        String[] msg = {"SCR_TBL_COL_DEF", String.valueOf(count)};
        cMsg.setMessageInfo(ZZVL0000Constant.ZZVM0001I, msg);

    }

    private void doProcess_ZZVL0000Scrn00_Set_Default(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        int eventLineNumber = bizMsg.xxNum_1.getValueInt();
        String roleNm = bizMsg.roleNm_1.getValue();
        String bizAppId = bizMsg.A.no(eventLineNumber).scrAppId_1.getValue();
        String tblNm = bizMsg.A.no(eventLineNumber).scrTblNm_1.getValue();
        String scrDefNm = bizMsg.A.no(eventLineNumber).scrTblColDefNm_1.getValue();
        String orgUsrNm = bizMsg.A.no(eventLineNumber).usrId_1.getValue();

        ScrTblColDefAccessor query = new ScrTblColDefAccessor();
        query.updateDefault(getGlobalCompanyCode(), roleNm, bizAppId, tblNm, scrDefNm, orgUsrNm);
        cMsg.setMessageInfo(ZZVL0000Constant.ZZM8100I);

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        // START 01/27/17 C.Ogaki [Modify] Release 2000 byte length limit
//        S21SsmEZDResult ssmResult = ZZVL0000Query.getInstance().getPreferredViewList(cMsg, sMsg);
//
//        if (ssmResult.isCodeNormal()) {
//
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt > sMsg.A.length()) {
//                cMsg.setMessageInfo("ZZZM9002W");
//                queryResCnt = sMsg.A.length();
//            }
//
//            int i = 0;
//            for (; i < sMsg.A.getValidCount(); i++) {
//                if (i == cMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
//            }
//            cMsg.A.setValidCount(i);
//
//            cMsg.xxPageShowFromNum_A.setValue(1);
//            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
//            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
//
//        } else {
//            cMsg.setMessageInfo("ZZZM9005W");
//            cMsg.xxPageShowFromNum_A.clear();
//            cMsg.xxPageShowToNum_A.clear();
//            cMsg.xxPageShowOfNum_A.clear();
//        }

        List<String[]> result = query.getPreferredViewList(cMsg.glblCmpyCd_1.getValue(), cMsg.roleNm_1.getValue(), sMsg.A.length() + 1);
        if (result.size() > 0) {
            int queryResCnt = result.size();
            if (result.size() > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            sMsg.A.setValidCount(queryResCnt);
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                String defNm     = result.get(i)[0];
                String scrAppId  = result.get(i)[1];
                String scrTblNm  = result.get(i)[2];
                String bizAppNm  = result.get(i)[3];
                String usrDefFlg = result.get(i)[4];
                String orgOwner  = result.get(i)[5];
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblColDefNm_1, defNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrAppId_1, scrAppId);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblNm_1, scrTblNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).bizAppNm_1,  bizAppNm);
                if (Boolean.parseBoolean(usrDefFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "Y");
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "N");
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).usrId_1, orgOwner);

                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
            }
            if (i < cMsg.A.length()) {
                cMsg.A.setValidCount(i);
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(queryResCnt));
        } else {
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }
        // END   01/27/17 C.Ogaki [Modify] Release 2000 byte length limit
    }
}
