/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6770;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.SVC_CTAC_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#2041
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 * 2024/03/11   Hitachi         K.Ogasawara     Update          QC#63585
 *</pre>
 */
public final class NMAL6770Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6770Query INSTANCE = new NMAL6770Query();

    /**
     * Constructor.
     */
    private NMAL6770Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6770Query
     */
    public static NMAL6770Query getInstance() {
        return INSTANCE;
    }

    /**
     * Contact Search.
     * @param cMsg NMAL6770CMsg
     * @param sMsg NMAL6770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContact(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsCtacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmParam.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        ssmParam.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        ssmParam.put("dsCtacPntTpCd_A", DS_CTAC_PNT_TP.PHONE_ASSISTANT);
        ssmParam.put("dsCtacPntTpCd_M", DS_CTAC_PNT_TP.PHONE_MOBILE);
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

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
        // 2018/10/11 QC#27869 Add End

        if (ZYPCommonFunc.hasValue(cMsg.dsCtacPntValTxt_H2) || ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H2.getValue())) {
            ssmParam.put("emailFlg", 1);
        }
        if (ZYPCommonFunc.hasValue(cMsg.locNum_H1) || ZYPCommonFunc.hasValue(cMsg.dsLocNm_H1) || ZYPCommonFunc.hasValue(cMsg.billToCustCd_H1)) {
            ssmParam.put("locSrch", ZYPConstant.FLG_ON_Y);
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1) || ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
                ssmParam.put("acctSrch", ZYPConstant.FLG_ON_Y);
                // QC#16452 add Start 2017/08/03
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H3.getValue())) {
                    ssmParam.put("inclLocFlg", 1);
                }
                // QC#16452 add End 2017/08/03
            }
        }

        return getSsmEZDClient().queryEZDMsgArray("getContact", ssmParam, sMsg.A);
    }

    /**
     * Contact Point Search.
     * @param cMsg NMAL6770CMsg
     * @param sMsg NMAL6770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCtacPnt(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        BigDecimal ctacPsnPk = cMsg.A.no(cMsg.xxRowNum.getValueInt()).ctacPsnPk_A1.getValue();
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("rowNum", sMsg.P.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getDsCtacPnt", ssmParam, sMsg.P);
    }

    // QC#7781
    /**
     * @param cMsg NMAL6770CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactDetail(NMAL6770CMsg cMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        ssmParam.put("dsCtacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmParam.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        ssmParam.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        ssmParam.put("dsCtacPntTpCd_A", DS_CTAC_PNT_TP.PHONE_ASSISTANT);
        ssmParam.put("dsCtacPntTpCd_M", DS_CTAC_PNT_TP.PHONE_MOBILE);

        return getSsmEZDClient().queryObjectList("getContactDetail", ssmParam);
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
