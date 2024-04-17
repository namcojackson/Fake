package business.blap.NMAL6830.common;

import java.util.List;
import java.util.Map;
import parts.common.EZDMsg;
import business.blap.NMAL6830.NMAL6830CMsg;
import business.blap.NMAL6830.NMAL6830Query;
import business.blap.NMAL6830.NMAL6830SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830CommonLogic {

	public static void setInitialCostTypePulldown(NMAL6830CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL6830Query.getInstance().getCostTypeList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				int i = 0;
				for (Map<String, String> map : list) {
					if (map != null && map.get("MDSE_CST_UPD_TP_CD") != null) {
				    	bizMsg.mdseCstUpdTpCd_HL.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_CD"));
				    	bizMsg.mdseCstUpdTpNm_HL.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_NM"));
				    	i++;
					}
				}
			}
		}
	}
	
    /**
     * Search
     * @param cMsg NMAL6830CMsg
     * @param sMsg NMAL6830SMsg
     */
    public static void search(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {

		ZYPTableUtil.clear(sMsg.A);
		ZYPTableUtil.clear(cMsg.A);
        S21SsmEZDResult ssmResult = NMAL6830Query.getInstance().getMdseUpdGroupList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            pageNation(cMsg, sMsg, 0);
        } else {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.setMessageInfo("NZZM0000E");
        }
    }

    /**
     * pageNation
     * @param cMsg NMAL6830CMsg
     * @param sMsg NMAL6830SMsg
     * @param pageFrom int
     */
    public static void pageNation(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg, int pageFrom) {
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }
}
