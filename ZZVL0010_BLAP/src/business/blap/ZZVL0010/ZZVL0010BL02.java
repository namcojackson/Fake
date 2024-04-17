package business.blap.ZZVL0010;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
//import parts.dbcommon.EZDConnectionMgr;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
//import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("ZZVL0010_INIT".equals(screenAplId)) {
                doProcess_ZZVL0010_INIT((ZZVL0010CMsg) cMsg, (ZZVL0010SMsg) sMsg);
            } else if ("ZZVL0010Scrn00_PageNext".equals(screenAplId)) {
                doProcess_ZZVL0010Scrn00_PageNext((ZZVL0010CMsg) cMsg, (ZZVL0010SMsg) sMsg);
            } else if ("ZZVL0010Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_ZZVL0010Scrn00_PagePrev((ZZVL0010CMsg) cMsg, (ZZVL0010SMsg) sMsg);
            } else if ("ZZVL0010Scrn00_Search".equals(screenAplId)) {
                doProcess_ZZVL0010Scrn00_Search((ZZVL0010CMsg) cMsg, (ZZVL0010SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZVL0010_INIT(ZZVL0010CMsg cMsg, ZZVL0010SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZVL0010Query.getInstance().getRoleList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {

            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // １ページ分の検索結果の転記（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // 改ページ項目への値の設定
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(queryResCnt));

        // 検索結果なし
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    private void doProcess_ZZVL0010Scrn00_Search(ZZVL0010CMsg cMsg, ZZVL0010SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZVL0010Query.getInstance().getRoleList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {

            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // １ページ分の検索結果の転記（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.setMessageInfo("ZZM8002I");
            // 改ページ項目への値の設定
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(queryResCnt));

        // 検索結果なし
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    private void doProcess_ZZVL0010Scrn00_PageNext(ZZVL0010CMsg cMsg, ZZVL0010SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(pagenationFrom + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(pagenationFrom + cMsg.A.getValidCount()));

    }

    private void doProcess_ZZVL0010Scrn00_PagePrev(ZZVL0010CMsg cMsg, ZZVL0010SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
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
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(pagenationFrom));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(pagenationFrom + cMsg.A.getValidCount() - 1));

    }

}
