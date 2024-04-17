/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0390.common;

import static business.blap.NSBL0390.constant.NSBL0390Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MNF;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.blap.NSBL0390.NSBL0390CMsg;
import business.blap.NSBL0390.NSBL0390Query;
import business.db.SVC_MODTMsg;

/**
 *<pre>
 * Mods Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   Hitachi         O.Okuma         Create          N/A
 * 2016/04/11   Hitachi         M.Gotou         Update          QC#4716
 * 2018/07/04   Fujitsu         T.Ogura         Update          QC#27065
 *</pre>
 */
public class NSBL0390CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0390CMsg
     */
    public static void clearMsg(NSBL0390CMsg cMsg) {
        cMsg.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0390CMsg
     */
    public static void createPullDown(NSBL0390CMsg cMsg) {
        // mod start 2016/04/11 CSA Defect#4716
        ZYPCodeDataUtil.createPulldownList(SVC_MNF.class, cMsg.svcMnfCd_01, cMsg.xxDplyByCdNmCnctTxt_01, ":");
        // mod end 2016/04/11 CSA Defect#4716
        // START 2018/07/04 T.Ogura [QC#27065,ADD]
        creatYearPullDown(cMsg);
        // END   2018/07/04 T.Ogura [QC#27065,ADD]
        creatMonthPullDown(cMsg);
        creatDayPullDown(cMsg);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0390CMsg
     */
    public static void setInitParams(NSBL0390CMsg cMsg) {

    }

    /**
     * checktSvcModSqNum
     * @param cMsg NSBL0390CMsg
     * @return boolean
     */
    public static boolean checktSvcModSqNum(NSBL0390CMsg cMsg) {

        String svcModSqNum = cMsg.svcModSqNum.getValue();

        if (svcModSqNum.equals(getSvcModSqNext(cMsg))) {
            return true;
        }
        return false;
    }

    /**
     * Insert Service Modification
     * @param cMsg NSBL0390CMsg
     */
    public static void insertSvcMnf(NSBL0390CMsg cMsg) {

        SVC_MODTMsg tMsg = new SVC_MODTMsg();
        setValue(cMsg.svcModPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MOD_SQ));

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModPk, cMsg.svcModPk);
        setValue(tMsg.svcModPlnId, cMsg.svcModPlnId);
        setValue(tMsg.svcModYr, cMsg.svcModYr);
        setValue(tMsg.svcModMth, cMsg.svcModMth);
        setValue(tMsg.svcModDay, cMsg.svcModDay);
        setValue(tMsg.svcMnfCd, cMsg.svcMnfCd);
        setValue(tMsg.svcModSqNum, cMsg.svcModSqNum);
        setValue(tMsg.svcModNm, cMsg.svcModNm);

        EZDTBLAccessor.insert(tMsg);

        setValue(cMsg.ezUpTime, tMsg.ezUpTime.getValue());
        setValue(cMsg.ezUpTimeZone, tMsg.ezUpTimeZone.getValue());

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }

    /**
     * Update Service Modification
     * @param cMsg NSBL0390CMsg
     */
    public static void updateSvcMnf(NSBL0390CMsg cMsg) {

        NSBL0390Query query = NSBL0390Query.getInstance();
        SVC_MODTMsg tMsg = query.getMod(cMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {MOD });
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModPk, cMsg.svcModPk);
        setValue(tMsg.svcModPlnId, cMsg.svcModPlnId);
        setValue(tMsg.svcModYr, cMsg.svcModYr);
        setValue(tMsg.svcModMth, cMsg.svcModMth);
        setValue(tMsg.svcModDay, cMsg.svcModDay);
        setValue(tMsg.svcMnfCd, cMsg.svcMnfCd);
        setValue(tMsg.svcModSqNum, cMsg.svcModSqNum);
        setValue(tMsg.svcModNm, cMsg.svcModNm);

        EZDTBLAccessor.update(tMsg);

        setValue(cMsg.ezUpTime, tMsg.ezUpTime.getValue());
        setValue(cMsg.ezUpTimeZone, tMsg.ezUpTimeZone.getValue());

       if (EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
           cMsg.setMessageInfo(NSBM0005I);
       }

    }

    /**
     * setSvcModSqNum
     * @param cMsg NSBL0390CMsg
     */
    public static void setSvcModSqNum(NSBL0390CMsg cMsg) {

        String svcModSqNum = getSvcModSqNext(cMsg);

        setValue(cMsg.svcModSqNum, svcModSqNum);
    }

    /**
     * setSvcModPlnId
     * @param cMsg NSBL0390CMsg
     */
    public static void setSvcModPlnId(NSBL0390CMsg cMsg) {

        StringBuilder svcModPlnId = new StringBuilder();

        svcModPlnId.append(cMsg.svcModYr.getValue());
        svcModPlnId.append(cMsg.svcModMth.getValue());
        svcModPlnId.append(cMsg.svcModDay.getValue());
        svcModPlnId.append("-");
        svcModPlnId.append(cMsg.svcMnfCd.getValue());
        svcModPlnId.append("-");
        svcModPlnId.append(cMsg.svcModSqNum.getValue());

        setValue(cMsg.svcModPlnId, svcModPlnId.toString());
    }

    /**
     * getBizIdSlsDt
     * @return String
     */
    public static String getBizIdSlsDt() {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        return ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_ID);
    }

    /**
     * initDate
     * @param cMsg NSBL0390CMsg
     */
    public static void initDate(NSBL0390CMsg cMsg) {

        String strSalesDate = ZYPDateUtil.getSalesDate();
        setValue(cMsg.svcModYr, strSalesDate.substring(0, 4));
        setValue(cMsg.svcModMth, strSalesDate.substring(4, 6));
        setValue(cMsg.svcModDay, strSalesDate.substring(6, 8));
    }

    // START 2018/07/04 T.Ogura [QC#27065,ADD]
    private static void creatYearPullDown(NSBL0390CMsg cMsg) {
        int salesDateYear = Integer.valueOf(ZYPDateUtil.getSalesDate().substring(0, 4));
        int cnt = -5;
        for (int i = 0; i < 11; i++) {
            setValue(cMsg.xxDplyByCtrlItemCd_YY.no(i), String.valueOf(salesDateYear + cnt));
            setValue(cMsg.xxDplyByCtrlItemCdNm_YY.no(i), String.valueOf(salesDateYear + cnt));
            cnt++;
        }
    }
    // END   2018/07/04 T.Ogura [QC#27065,ADD]

    private static void creatMonthPullDown(NSBL0390CMsg cMsg) {
        String strVal;
        for (int i = 0; i < INT_MONTH_LST; i++) {
            strVal = String.format("%1$02d", i + 1);
            setValue(cMsg.xxDplyByCtrlItemCd_MM.no(i), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_MM.no(i), strVal);
        }
    }

    private static void creatDayPullDown(NSBL0390CMsg cMsg) {
        String strVal;
        for (int i = 0; i < INT_DAY_LST; i++) {
            strVal = String.format("%1$02d", i + 1);
            setValue(cMsg.xxDplyByCtrlItemCd_DD.no(i), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_DD.no(i), strVal);
        }
    }

    private static String getSvcModSqNext(NSBL0390CMsg cMsg) {

        NSBL0390Query query = NSBL0390Query.getInstance();

        S21SsmEZDResult rslt = query.getSvcMdSqNm(cMsg);

        String svcModSqNum;

        if (rslt != null && rslt.isCodeNormal()) {
            svcModSqNum = (String) rslt.getResultObject();
        } else {
            svcModSqNum = "0001";
        }

        return svcModSqNum;
    }
}
