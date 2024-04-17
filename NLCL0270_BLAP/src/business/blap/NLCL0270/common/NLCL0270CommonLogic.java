/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0270.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0270.NLCL0270CMsg;
import business.blap.NLCL0270.NLCL0270Query;
import business.blap.NLCL0270.NLCL0270SMsg;
import business.blap.NLCL0270.NLCL0270_ACMsgArray;
import business.blap.NLCL0270.NLCL0270_ASMsgArray;
import business.blap.NLCL0270.constant.NLCL0270Constant;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0270 Supersession Inventory Inquiry Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 * 07/04/2017   CITS            R.Shimamoto     Update          QC#18187
 * 12/20/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NLCL0270CommonLogic implements NLCL0270Constant {

    /**
     * clear Msg
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     */
    public static void clearMsg(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {

        // cMsg initialization
        // Header
        cMsg.mdseCd_H2.clear();
        cMsg.mdseCd_H1.clear();
        cMsg.mdseDescShortTxt_H1.clear();
        cMsg.stkStsCd_H1.clear();
        cMsg.rtlWhNm_H2.clear();
        cMsg.rtlWhNm_H1.clear();
        cMsg.rtlSwhNm_H2.clear();
        cMsg.rtlSwhNm_H1.clear();

        ZYPTableUtil.clear(cMsg.A);

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

    }

    /**
     * Set Item Description
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     * @param glblCmpyCd String
     */
    public static void setItemDescription(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg, String glblCmpyCd) {

        MDSETMsg inMsg = new MDSETMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.mdseCd.setValue(cMsg.mdseCd_H1.getValue());

        MDSETMsg outMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);

        if (outMsg != null) {
            // 1 recode only
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, outMsg.mdseNm);

        } else {
            cMsg.mdseCd_H1.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd_H1.getValue() });
        }
    }

    /**
     * The method explanation: The search processing of Retail
     * Warehouse information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult search(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg, S21BusinessHandler handler) {

        List<String> mdseList = new ArrayList<String>();
        mdseList.add(cMsg.mdseCd_H1.getValue());

        ORD_TAKE_MDSETMsg inOrdTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        inOrdTakeMdseTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_G1.getValue());
        //QC:14539
        //inOrdTakeMdseTMsg.setConditionValue("ordTakeMdseCd01", cMsg.mdseCd_H1.getValue().substring(0, 8));
        if (cMsg.mdseCd_H1.getValue().length() < 8) {
            inOrdTakeMdseTMsg.setConditionValue("ordTakeMdseCd01", cMsg.mdseCd_H1.getValue());
        } else {
            inOrdTakeMdseTMsg.setConditionValue("ordTakeMdseCd01", cMsg.mdseCd_H1.getValue().substring(0, 8));
        }
        inOrdTakeMdseTMsg.setSQLID("002");
        ORD_TAKE_MDSETMsgArray outOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(inOrdTakeMdseTMsg);

        MDSETMsg inMdseTMsg = new MDSETMsg();
        MDSETMsgArray outMdseTMsg = new MDSETMsgArray();
        if (outOrdTakeMdseTMsg.getValidCount() > 0) {
            inMdseTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_G1.getValue());
            inMdseTMsg.setConditionValue("mdseCd01", cMsg.mdseCd_H1.getValue().substring(0, 8).concat(DB_PARAM_DATA_LIKE));
            inMdseTMsg.setSQLID("080");
            outMdseTMsg = (MDSETMsgArray) EZDTBLAccessor.findByCondition(inMdseTMsg);
        }

        // ---------------------------------------------
        // Supersede API(NWZC2060)
        // ---------------------------------------------
        NWZC206001PMsg nwzc206001PMsg = new NWZC206001PMsg();
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxModeCd, MODE);
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);
        if (outMdseTMsg.getValidCount() > 0) {
            for (int i = 0; i < outMdseTMsg.length(); i++) {
                ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.mdseCd, outMdseTMsg.no(i).mdseCd);

                // Supersede API(NWZC2060) is executed
                NWZC206001 nwzc206001 = new NWZC206001();
                nwzc206001.execute(nwzc206001PMsg, ONBATCH_TYPE.ONLINE);

                if (mdseList.size() > 1000) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                // QC#18187 Mod.
//                mdseList.add(nwzc206001PMsg.mdseCd.getValue());
                for (int s = 0; s < nwzc206001PMsg.A.getValidCount(); s++) {
                	mdseList.add(nwzc206001PMsg.A.no(s).mdseCd.getValue());
                }

                if (mdseList.size() > 1000) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                mdseList.add(outMdseTMsg.no(i).mdseCd.getValue());
            }
        } else {
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.mdseCd, cMsg.mdseCd_H1);

            // Supersede API(NWZC2060) is executed
            NWZC206001 nwzc206001 = new NWZC206001();
            nwzc206001.execute(nwzc206001PMsg, ONBATCH_TYPE.ONLINE);

            // QC#29214 Add.
            for (int i = 0; i < nwzc206001PMsg.A.getValidCount(); i++) {
                mdseList.add(nwzc206001PMsg.A.no(i).mdseCd.getValue());
            }
        }

        // QC#29214 Add compatible item
        List<String> tmpMdseList = new ArrayList<String>();
        for (String mdseCd : mdseList) {
            List<String> result = NLCL0270Query.getInstance().getCompatibleItem(cMsg.glblCmpyCd_G1.getValue(), mdseCd);

            for (String rs : result) {
                if (!mdseList.contains(rs)) {
                    tmpMdseList.add(rs);
                }
            }
        }

        if (!tmpMdseList.isEmpty()) {
            mdseList.addAll(tmpMdseList);
        }

        S21SsmEZDResult ssmResult = NLCL0270Query.getInstance().getSpssIvntyInfo(cMsg, sMsg, mdseList);

        if (ssmResult.isCodeNormal()) {

            EZDMsg.copy(sMsg, null, cMsg, null);

            int queryResCnt = ssmResult.getQueryResultCount();

            int i = 0;
            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {
                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseCd_O1, cMsg.mdseCd_H1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_O1, cMsg.mdseCd_H1);
                }
            }
            cMsg.A.setValidCount(i);
        }

        // QC#29214 Update
        setItemDescription(cMsg, sMsg, cMsg.glblCmpyCd_G1.getValue());

        return ssmResult;
    }

    /**
     * Copy CMsg to SMsg
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     */
    public static void copyCMsgToSMsg(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        cMsg.clearErrorInfo();
        sMsg.clearErrorInfo();

        copyInputValueToSMsg(cMsg, sMsg);

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {

            int sMsgIdx = idx + cMsg.xxPageShowFromNum.getValueInt() - 1;

            if (sMsgIdx < sMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.no(idx), null, sMsg.A.no(sMsgIdx), null);
            }
        }
    }

    /**
     * Copy SMsg to CMsg
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     * @param index int
     */
    public static void copySMsgToCMsg(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg, int index) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        int startIdx = 0;
        int sMsgIdx = 0;
        int dispRowCnt = 0;

        startIdx = getPageStartRowIndex(index, cMsg.A);
        sMsgIdx = startIdx;

        for (int idx = 0; idx < cMsg.A.length(); idx++) {

            if (sMsgIdx > sMsg.A.getValidCount() - 1) {
                break;
            }

            EZDMsg.copy(sMsg.A.no(sMsgIdx), null, cMsg.A.no(idx), null);
            dispRowCnt++;
            sMsgIdx++;
        }
        cMsg.A.setValidCount(dispRowCnt);
        cMsg.xxPageShowFromNum.setValue(startIdx + 1);
        cMsg.xxPageShowToNum.setValue(startIdx + dispRowCnt);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Copy input value to SMsg
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     */
    private static void copyInputValueToSMsg(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_H2, cMsg.mdseCd_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_H1, cMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseDescShortTxt_H1, cMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.stkStsCd_H1, cMsg.stkStsCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H2, cMsg.rtlWhNm_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, cMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_H2, cMsg.rtlSwhNm_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_H1, cMsg.rtlSwhNm_H1);
    }

    /**
     * Get page start row index
     * @param index int
     * @param aMsg NLCL0270_ACMsgArray
     * @return int
     */
    private static int getPageStartRowIndex(int index, NLCL0270_ACMsgArray aMsg) {
        int startIndex = (index / aMsg.length()) * aMsg.length();
        return startIndex;
    }

    public static void dispPage(NLCL0270CMsg cMsg, NLCL0270_ACMsgArray cMsgAry, NLCL0270_ASMsgArray sMsgAry) {
        ZYPTableUtil.clear(cMsgAry);
        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int dispRowNum = 0;
        if (cMsg.xxPageShowFromNum.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < cMsgAry.length() && startIndex + dispRowNum < sMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(sMsgAry.get(startIndex + dispRowNum), null, cMsgAry.get(dispRowNum), null);
        }
        cMsgAry.setValidCount(dispRowNum);
        cMsg.xxPageShowToNum.setValue(startIndex + dispRowNum);
        cMsg.xxPageShowOfNum.setValue(sMsgAry.getValidCount());
        
    }

    /**QC#18187 Mod.
     * checkOrdTakeFromOrgMdse
     * @param cMsg NLCL0270CMsg
     */
    public static void checkOrdTakeFromOrgMdse(NLCL0270CMsg cMsg) {

        String inqiuryOnlyFlg = cMsg.xxReadOnlyFlg_G1.getValue();
        if (!ZYPCommonFunc.hasValue(inqiuryOnlyFlg) || ZYPConstant.FLG_ON_Y.equals(inqiuryOnlyFlg)) {
        	// Check 8 Digit.
        	String orgItemCd = cMsg.mdseCd_H1.getValue();
        	if (MDSE_8_DIGIT < orgItemCd.length()) {
        		orgItemCd = orgItemCd.substring(0, MDSE_8_DIGIT);
        	}
        	ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, orgItemCd);
            ORD_TAKE_MDSETMsg outMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(tMsg);

            if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.ordTakeMdseCd)) {
            	// order take Flag.
            	ZYPEZDItemValueSetter.setValue(cMsg.xxOrdTakeMdseFlg_G1, ZYPConstant.FLG_ON_Y);
            	return;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxOrdTakeMdseFlg_G1, ZYPConstant.FLG_OFF_N);
    	return;
    }
}
