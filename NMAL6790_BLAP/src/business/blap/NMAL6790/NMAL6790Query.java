/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6790;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.SVC_CTAC_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import business.blap.NMAL6790.constant.NMAL6790Constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 * 2024/03/11   Hitachi         K.Ogasawara     Update          QC#63585
 *</pre>
 */
public final class NMAL6790Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6790Query INSTANCE = new NMAL6790Query();

    /**
     * Constructor.
     */
    private NMAL6790Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6790Query
     */
    public static NMAL6790Query getInstance() {
        return INSTANCE;
    }

    /**
     * Contact Search.
     * @param cMsg NMAL6790CMsg
     * @param sMsg NMAL6790SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContact(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("phoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmParam.put("eMailWork", DS_CTAC_PNT_TP.EMAIL_WORK);

        // 2018/10/11 QC#27869 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.ctacTpCd_H1)) {
        // 2024/03/08 QC#63585 Mod Start
//          if (SVC_CTAC_TP.METER_READ.equals(cMsg.ctacTpCd_H1.getValue()) ||
//              SVC_CTAC_TP.FAX.equals(cMsg.ctacTpCd_H1.getValue()) ||
//              SVC_CTAC_TP.SVC_KEY_OPERATOR.equals(cMsg.ctacTpCd_H1.getValue()) ||
//              SVC_CTAC_TP.DELIVERY_CONTACT.equals(cMsg.ctacTpCd_H1.getValue()) ||
//              SVC_CTAC_TP.EMANAGE_ADMIN.equals(cMsg.ctacTpCd_H1.getValue())) {
            if (existsSvcCtacTp(cMsg.ctacTpCd_H1.getValue())){
                ssmParam.put("contSrchType", 2);
            } else {
                ssmParam.put("contSrchType", 1);
            }
        // 2024/03/08 QC#63585 Mod End
        } else {
            ssmParam.put("contSrchType", 0);
        }
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxDate", NMAL6790Constant.MAX_EFFECTIVE_DATE);
        // 2018/10/11 QC#27869 Add End

        return getSsmEZDClient().queryEZDMsgArray("getContact", ssmParam, sMsg.A);
    }

    /**
     * Contact Point Search Without Serial and IB Role.
     * @param cMsg NMAL6790CMsg
     * @param sMsg NMAL6790SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCtacPntWithoutSerialIB(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {

        BigDecimal ctacPsnPk = cMsg.A.no(cMsg.xxRowNum.getValueInt()).ctacPsnPk_A1.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctacPsnPk", ctacPsnPk);

        return getSsmEZDClient().queryEZDMsgArray("getDsCtacPntWithoutSerialIB", ssmParam, sMsg.B);
    }

    /**
     * Contact Point Search With Serial and IB Role.
     * @param cMsg NMAL6790CMsg
     * @param sMsg NMAL6790SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCtacPntWithSerialIB(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {

        BigDecimal ctacPsnPk = cMsg.A.no(cMsg.xxRowNum.getValueInt()).ctacPsnPk_A1.getValue();
        String serNum = cMsg.serNum_H1.getValue();
        String svcCtacTpCd = cMsg.svcCtacTpCd_H1.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("serNum", serNum);
        ssmParam.put("svcCtacTpCd", svcCtacTpCd);

        return getSsmEZDClient().queryEZDMsgArray("getDsCtacPntWithSerialIB", ssmParam, sMsg.B);
    }

    // 2018/10/11 QC#27869 Add Start
    /**
     * Get SVC_CTAC_TP value list.
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcCatgTpList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getSvcCatgTpList", ssmParam);
    }
    // 2018/10/11 QC#27869 Add End

    // 2024/03/08 QC#63585 Add Start
    private boolean existsSvcCtacTp(String svcCtacTpCd) {
        SVC_CTAC_TPTMsg tMsg = new SVC_CTAC_TPTMsg();
        setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(tMsg.svcCtacTpCd, svcCtacTpCd);
        tMsg = (SVC_CTAC_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }
    // 2024/03/08 QC#63585 Add End
}
