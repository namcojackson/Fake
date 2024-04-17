/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3170;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 * 2018/08/17   CITS            K.Ogino         Update          QC#27601
 * 2019/02/25   CITS            K.Ogino         Update          QC#30504
 * 2019/08/06   CITS            R.Shimamoto     Update          QC#52078
 * 2020/02/18   Fujitsu         R.Nakamura      Update          QC#55897
 *</pre>
 */
public final class NLBL3170Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3170Query MYINSTANCE = new NLBL3170Query();

    /**
     * Constructor.
     */
    private NLBL3170Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3070Query
     */
    public static NLBL3170Query getInstance() {
        return MYINSTANCE;
    }

    public S21SsmEZDResult searchProNumList(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // Mod Start 2020/02/18 QC#55897
//        ssmParam.put("trxHdrNum", cMsg.trxHdrNum.getValue());
        ssmParam.put("trxHdrNum", cMsg.xxDplyOrdNum_TH.getValue());
        // Mod End 2020/02/18 QC#55897

        return getSsmEZDClient().queryEZDMsgArray("searchProNumList", ssmParam, sMsg.A);
    }

    // QC#27601
    public S21SsmEZDResult searchAsnProNumList(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg, String shipFromSoNum) {

    	// QC:52078 Mod Start
    	S21SsmEZDResult result = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("cusaGlblCmpyCd", "ABR");
        // Mod Start 2020/02/18 QC#55897
//        ssmParam.put("trxHdrNum", cMsg.trxHdrNum.getValue());
        ssmParam.put("trxHdrNum", cMsg.xxDplyOrdNum_TH.getValue());
        // Mod End 2020/02/18 QC#55897
        // QC#30504
        ssmParam.put("ws", VND_SYS_TP.WHOLE_SALES);
        ssmParam.put("parts", VND_SYS_TP.PARTS);

        result = getSsmEZDClient().queryEZDMsgArray("searchAsnProNumList", ssmParam, sMsg.A);

        if (!result.isCodeNormal() && shipFromSoNum != null) {
        	ssmParam.put("trxHdrNum", shipFromSoNum);
        	result = getSsmEZDClient().queryEZDMsgArray("searchAsnProNumList", ssmParam, sMsg.A);
        }

        return result;
        // QC:52078 Mod End
    }

    // QC:52078 Start
    public S21SsmEZDResult searchShipFromSoNum(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // Mod Start 2020/02/18 QC#55897
//        ssmParam.put("asnSoNum", cMsg.trxHdrNum.getValue());
        ssmParam.put("asnSoNum", cMsg.xxDplyOrdNum_TH.getValue());
        // Mod End 2020/02/18 QC#55897

        return getSsmEZDClient().queryObject("searchShipFromSoNum", ssmParam);
    }
    // QC:52078 End

}
