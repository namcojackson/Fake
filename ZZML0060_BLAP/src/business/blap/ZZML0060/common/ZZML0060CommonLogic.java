package business.blap.ZZML0060.common;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0010.ZZML0010CMsg;
import business.blap.ZZML0060.ZZML0060CMsg;
import business.blap.ZZML0060.ZZML0060Query;
import business.blap.ZZML0060.ZZML0060SMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


/**
 * @author Q02673
 */
public class ZZML0060CommonLogic {

    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     */
    public static void searchZZML0060Scrn00(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        sMsg.clear();
        sMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_S.getValue());
        cMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_S.getValue());

        S21SsmEZDResult ssmResult = ZZML0060Query.getInstance().getMlGrpAddr(cMsg, sMsg);

        if (!ssmResult.isCodeNormal()) {
            if (!"E".equals(cMsg.getMessageKind())) {
                cMsg.setMessageInfo("ZZZM9005W", null);
            }
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.A.setValidCount(0);
        } else {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W"); //DZZM0001W
                queryResCnt = sMsg.A.length();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxNum_A.setValue(i + 1);
            }

            int i = 0;
            for (; i < cMsg.A.length() && i < sMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        }
    }
    
    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     */
    public static void research(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        sMsg.clear();
        sMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_S.getValue());
        cMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_S.getValue());
        
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        if (pagenationFrom == -1) {
            pagenationFrom += 1;
        }

        S21SsmEZDResult ssmResult = ZZML0060Query.getInstance().getMlGrpAddr(cMsg, sMsg);

        if (!ssmResult.isCodeNormal()) {
            if (!"E".equals(cMsg.getMessageKind())) {
                cMsg.setMessageInfo("ZZZM9005W", null);
            }
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.A.setValidCount(0);
        } else {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W"); //DZZM0001W
                queryResCnt = sMsg.A.length();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxNum_A.setValue(i + 1);
            }
            
            cMsg.A.clear();
            cMsg.A.setValidCount(0);       
            
            // The posting of search results for the designated page
            int cnt = 0;
            for (int k = pagenationFrom; k < sMsg.A.getValidCount(); k++) {
                if (cnt < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(k), null, cMsg.A.no(cnt), null);
                } else {
                    break;
                }
                cnt++;
            }
            cMsg.A.setValidCount(cnt);

            // The setting of the value to the turning a page item
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        }
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     * @param pagenationFrom pagenationFrom(0 origin)
     * @param pagenationTo   pagenationTo
     */
    public static void storeEventSourceScrn00(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg, int pagenationFrom, int pagenationTo) {
        for (int i = 0; i < pagenationTo - pagenationFrom; i++) {
            //EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pagenationFrom + i), null);
            sMsg.A.no(pagenationFrom + i).xxChkBox_A.setValue(cMsg.A.no(i).xxChkBox_A.getValue());
        }
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     * @param rowIndex target row index(0 origin)
     */
    public static void refreshScrn00(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg, int rowIndex) {
        int pageRows = cMsg.A.length();
        int pageNo = rowIndex / pageRows;
        int pagenationFrom = pageRows * pageNo;

        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length() && i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     */
    public static void refreshCurrentScrn00(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();

        for (int i = 0; i < pagenationTo - pagenationFrom; i++) {
            EZDMsg.copy(sMsg.A.no(pagenationFrom + i), null, cMsg.A.no(i), null);
        }
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     */
    public static void refreshCurrentScrn01(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        searchZZML0060Scrn01(cMsg, sMsg);

        int index = cMsg.xxNum_01.getValueInt() - 1;

        if (!"E".equals(cMsg.getMessageCode())) {
            //EZDMsg.copy(cMsg, "01", sMsg.A.no(index), "A");
            copyCMsg01ToSMsgA(cMsg, sMsg, index);
        }
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     */
    public static void searchZZML0060Scrn01(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        int index = cMsg.xxNum_01.getValueInt() - 1;

        cMsg.glblCmpyCd_01.setValue(cMsg.glblCmpyCd_S.getValue());
        cMsg.mlGrpId_01.setValue(sMsg.A.no(index).mlGrpId_A.getValue());

        S21SsmEZDResult ssmResult = ZZML0060Query.getInstance().getMlGrpAddrByKey(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9004E", null);
        }
    }


    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     * @param targetIndex target row index(0 origin)
     */
    public static void copyCMsg01ToSMsgA(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg, int targetIndex) {
        sMsg.A.no(targetIndex).xxNum_A.setValue(cMsg.xxNum_01.getValue());
        sMsg.A.no(targetIndex).mlGrpId_A.setValue(cMsg.mlGrpId_01.getValue());
        sMsg.A.no(targetIndex).mlGrpNm_A.setValue(cMsg.mlGrpNm_01.getValue());
        sMsg.A.no(targetIndex).mlKeyFirstNm_A.setValue(cMsg.mlKeyFirstNm_01.getValue());
        sMsg.A.no(targetIndex).mlKeyScdNm_A.setValue(cMsg.mlKeyScdNm_01.getValue());
        sMsg.A.no(targetIndex).mlKeyThirdNm_A.setValue(cMsg.mlKeyThirdNm_01.getValue());
        sMsg.A.no(targetIndex).mlGrpDescTxt_A.setValue(cMsg.mlGrpDescTxt_01.getValue());
        sMsg.A.no(targetIndex).ezUpTime_A.setValue(cMsg.ezUpTime_01.getValue());
        sMsg.A.no(targetIndex).ezUpTimeZone_A.setValue(cMsg.ezUpTimeZone_01.getValue());
    }
    
    public static boolean chkGlbCmpCd(ZZML0060CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd_S.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd_S.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
            return false;
        }
        return true;
    }
}
