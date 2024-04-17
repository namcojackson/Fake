/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1400.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NSAL1400.NSAL1400CMsg;
import business.blap.NSAL1400.NSAL1400Query;
import business.blap.NSAL1400.NSAL1400SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400CommonLogic {

    /**
     * initMsg
     * @param cMsg
     * @param sMsg
     */
    public static void initMsg(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        // cMsg
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        // sMsg
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        // paging
        setValue(bizMsg.xxPageShowFromNum_A, BigDecimal.ZERO);
        setValue(bizMsg.xxPageShowToNum_A, BigDecimal.ZERO);
        setValue(bizMsg.xxPageShowOfNum_A, BigDecimal.ZERO);

    }

    /**
     * setSvcLineBizTpPulldown
     * @param bizMsg
     */
    public static void setSvcLineBizTpPulldown(NSAL1400CMsg bizMsg) {
        bizMsg.svcLineBizCd_PL.clear();
        bizMsg.lineBizTpDescTxt_PL.clear();

        List<Map<String, Object>> svcLineBizTpList = NSAL1400Query.getInstance().getSvcLineBizTp(bizMsg.glblCmpyCd.getValue());
        int i = 0;
        for (Map<String, Object> svcLineBizTp : svcLineBizTpList) {
            setValue(bizMsg.svcLineBizCd_PL.no(i), (String) svcLineBizTp.get("SVC_LINE_BIZ_CD"));
            setValue(bizMsg.lineBizTpDescTxt_PL.no(i), (String) svcLineBizTp.get("LINE_BIZ_TP_DESC_TXT"));
            i++;
        }
    }

    /**
     * Pagenation sMsg -> cMsg
     * @param cMsg NSAL1400CMsg
     * @param sMsg NSAL1400SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL1400CMsg cMsg, NSAL1400SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    /**
     * setPagenation cMsg -> sMsg
     * @param cMsg NSAL1400CMsg
     * @param sMsg NSAL1400SMsg
     * @param pageFrom int
     */
    public static void setPagenation(NSAL1400CMsg cMsg, NSAL1400SMsg sMsg, int pageFrom) {

        int cnt = pageFrom;
        for (; cnt < pageFrom + cMsg.A.length(); cnt++) {
            if (cnt < pageFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pageFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }
}
