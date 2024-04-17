package business.blap.ZZOL0130.common;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0130.ZZOL0130CMsg;
import business.blap.ZZOL0130.ZZOL0130Query;
import business.blap.ZZOL0130.ZZOL0130SMsg;
import business.db.GLBL_CMPYTMsg;

public class ZZOL0130CommonLogic {

    public static String convEzCancFlgSpecCdToName(String ezCancFlgSpecCd) {
        if ("0".equals(ezCancFlgSpecCd)) {
            return "Not Cancel";
        } else if ("1".equals(ezCancFlgSpecCd)) {
            return "Cancel";
        } else {
            return "All";
        }
    }

    /**
     * Method name: doClear
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0130CMsg
     * @param sMsg ZZOL0130SMsg
     */
    public static void doClear(String sGlblCmpyCd, String delTblId, ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {

        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        cMsg.glblCmpyCd.setValue(sGlblCmpyCd);
        cMsg.delTblId_A0.setValue(delTblId);
    }

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg ZZOL0100CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, ZZOL0130CMsg cMsg) {

        // for s21
        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(sGblCpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        } else {
            return true;
        }
        
        // for parts
        // return true;
    }
    
    public static void getDeleteTableList(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        S21SsmEZDResult ssmResult = ZZOL0130Query.getInstance().getDelTblList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String ezCancCd = sMsg.A.no(i).specEffCancCd_A1.getValue();
                String ezCancNm = ZZOL0130CommonLogic.convEzCancFlgSpecCdToName(ezCancCd);

                sMsg.A.no(i).xxSpecEffCancNm_A1.setValue(ezCancNm);
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.delTblConfigPk.clear();
            cMsg.delTblId.clear();
            cMsg.specEffCancCd_L.clear();
            cMsg.specEffMthAot.clear();
            cMsg.delTblCmntTxt.clear();
            cMsg.delExecDt.clear();
            cMsg.ezUpTime.clear();
            cMsg.ezUpTimeZone.clear();

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            cMsg.xxPageShowFromNum_A0.setValue(1);

            cMsg.setMessageInfo("ZZM8002I");
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowFromNum_A0.clear();
        }
    }

    public static void pageForward(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        // copy data from CMsg onto SMsg
        int sMsgIdx = cMsg.xxPageShowFromNum_A0.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            sMsg.A.no(sMsgIdx + i).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());
        }

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowFromNum_A0.setValue(pagenationFrom + 1);
    }

    public static void pageBack(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        // copy data from CMsg onto SMsg
        int sMsgIdx = cMsg.xxPageShowFromNum_A0.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.length(); i++) {
            sMsg.A.no(sMsgIdx + i).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());
        }

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
        cMsg.xxPageShowFromNum_A0.setValue(pagenationFrom);
    }
}
