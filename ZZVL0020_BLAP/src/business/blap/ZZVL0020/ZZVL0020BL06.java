package business.blap.ZZVL0020;

import java.math.BigDecimal;
import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZVL0020.common.ZZVL0020CommonLogic;
import business.db.MY_PROCTMsg;
import business.db.MY_PROCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZVL0020Scrn00_Delete".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_Delete((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_CMN_Submit((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZVL0020Scrn01_CMN_Submit(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        String sRoleNm = cMsg.roleNm_2.getValue();

        MY_PROCTMsg rMyProc = new MY_PROCTMsg();
        rMyProc.setSQLID("001");
        rMyProc.setConditionValue("usrNm01", sRoleNm);
        MY_PROCTMsgArray tMsgArray = (MY_PROCTMsgArray) EZDTBLAccessor.findByCondition(rMyProc);
        if ((tMsgArray != null) && (tMsgArray.length() != 0)) {
            // Remove
            for (int i = 0; i < tMsgArray.length(); i++) {
                // Search MY_PROC table by Primary Key
                MY_PROCTMsg tMsg = new MY_PROCTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_BK.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.myProcPk, tMsgArray.no(i).myProcPk.getValue());
                tMsg = (MY_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg != null) {
                    EZDTBLAccessor.remove(tMsg);
                    String sReturnCode = tMsg.getReturnCode();
                    if (!sReturnCode.equals(EZDTBLAccessor.RTNCD_NORMAL)) {
                        cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                        return;
                    }
                }
            }
        }
        String sGblCpyCd = cMsg.glblCmpyCd_BK.getValue();
        // create
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String xxUpTabCd = cMsg.B.no(i).upTabCd_B1.getValue();

            MY_PROCTMsg tMsg = new MY_PROCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, sGblCpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.myProcPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MY_PROC_SQ"));
            ZYPEZDItemValueSetter.setValue(tMsg.usrCmpyCd, sGblCpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.usrNm, sRoleNm);
            ZYPEZDItemValueSetter.setValue(tMsg.upTabOpSortNum, BigDecimal.valueOf(i));
            ZYPEZDItemValueSetter.setValue(tMsg.upTabCd, xxUpTabCd);
            EZDTBLAccessor.create(tMsg);
            String sReturnCode = tMsg.getReturnCode();
            if (!sReturnCode.equals(EZDTBLAccessor.RTNCD_NORMAL)) {
                cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
                return;
            }

        }

        ZZVL0020CommonLogic.doInit(sGblCpyCd, cMsg, sMsg);
        // correct
        cMsg.setMessageInfo(null, null);
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Maintenance"});

    }

    private void doProcess_ZZVL0020Scrn00_Delete(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        int delNum = cMsg.xxNum_1.getValueInt();
        String sRoleNm = cMsg.C.no(delNum).roleNm_C.getValue();

        MY_PROCTMsg rMyProc = new MY_PROCTMsg();
        rMyProc.setSQLID("001");
        rMyProc.setConditionValue("usrNm01", sRoleNm);
        MY_PROCTMsgArray tMsgArray = (MY_PROCTMsgArray) EZDTBLAccessor.findByCondition(rMyProc);
        if ((tMsgArray != null) && (tMsgArray.length() != 0)) {
            // Remove
            for (int i = 0; i < tMsgArray.length(); i++) {
                // Search MY_PROC table by Primary Key
                MY_PROCTMsg tMsg = new MY_PROCTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_1.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.myProcPk, tMsgArray.no(i).myProcPk.getValue());
                tMsg = (MY_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg != null) {
                    EZDTBLAccessor.remove(tMsg);
                    String sReturnCode = tMsg.getReturnCode();
                    if (!sReturnCode.equals(EZDTBLAccessor.RTNCD_NORMAL)) {
                        cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                        return;
                    }
                }
            }
        }
        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(cMsg.C);
        cMsg.xxPageShowFromNum_1.clear();
        cMsg.xxPageShowToNum_1.clear();
        cMsg.xxPageShowOfNum_1.clear();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("rowNum", sMsg.C.length() + 1);
        param.put("cMsg", cMsg);

        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getRoleList(param, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.C.length();
            } else {
                cMsg.setMessageInfo("ZZVM0004I");
            }

            int i = 0;
            for ( ; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(cMsg.C.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_1, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_1.clear();
            cMsg.xxPageShowToNum_1.clear();
            cMsg.xxPageShowOfNum_1.clear();
        }
    }

}
