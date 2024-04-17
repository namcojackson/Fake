/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9900;

import static business.blap.NSAL9900.constant.NSAL9900Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.leftPad;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsgCommons;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL9900.common.NSAL9900CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 * 2016/03/09   Hitachi         T.Aoyagi        Update          QC#5213
 * 2016/05/10   Hitachi         A.Kohinata      Update          QC#5389
 * 2016/06/15   Hitachi         T.Aoyagi        Update          QC#9685
 * 2016/07/12   Fujitsu         M.Ugaki         Update          QC#11674
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 *</pre>
 */
public class NSAL9900BL06 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL9900CMsg cMsg = (NSAL9900CMsg) arg0;
        NSAL9900SMsg sMsg = (NSAL9900SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL9900Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CMN_Submit(cMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NSAL9900Scrn00_CMN_Submit(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        // validation check
        if (!NSAL9900CommonLogic.commonValidation(cMsg, sMsg)) {
            int pageFromNum = NSAL9900CommonLogic.getErrPageFromNum(cMsg, sMsg);
            setValue(cMsg.xxPageShowFromNum, new BigDecimal(pageFromNum));
            NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
            return;
        }
        if (!NSAL9900CommonLogic.addonValidation(cMsg, sMsg)) {
            int pageFromNum = NSAL9900CommonLogic.getErrPageFromNum(cMsg, sMsg);
            setValue(cMsg.xxPageShowFromNum, new BigDecimal(pageFromNum));
            NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
            return;
        }

        if (sMsg.D.getValidCount() == 0 && sMsg.B.getValidCount() == 0) {
            cMsg.setMessageInfo(MSG_ID_NSAM0020E);
            return;
        }

        try {
            String tblClsNm = TMSG_PACKAGE + cMsg.tblCd.getValue() + TMSG_SFX;
            Class<EZDTMsg> tblClass = (Class<EZDTMsg>) Class.forName(tblClsNm);
            EZDTMsg tMsg = (EZDTMsg) tblClass.newInstance();
            if (!deleteTableInfo(sMsg, cMsg, tMsg)) {
                return;
            }

            if (!createUpdateTableInfo(cMsg, sMsg, tMsg)) {
                // add start 2016/05/10 QC#5389
                int pageFromNum = NSAL9900CommonLogic.getErrPageFromNum(cMsg, sMsg);
                setValue(cMsg.xxPageShowFromNum, new BigDecimal(pageFromNum));
                NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
                // add end 2016/05/10 QC#5389
                return;
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
        cMsg.setMessageInfo(MSG_ID_NZZM0002I);
    }

    // START 2016/07/12 M.Ugaki [QC#11674, UPD]
    // private String getAttrCd(String strPhysicalAttr) {
    private String getAttrCd(String strPhysicalAttr, boolean suffixMode) {
    // END 2016/07/12 M.Ugaki [QC#11674, UPD]

        StringBuffer strAttrCd = new StringBuffer();
        String[] strAttrList = strPhysicalAttr.split(STR_UNDER_BAR);

        strAttrCd.append(strAttrList[0].toLowerCase());
        for (int i = 1; i < strAttrList.length; i++) {

            // START 2016/07/12 M.Ugaki [QC#11674, ADD]
            if (suffixMode && (i == strAttrList.length - 1)) {
                strAttrCd.append(STR_UNDER_BAR);
            }
            // END 2016/07/12 M.Ugaki [QC#11674, ADD]

            if (strAttrList[i].length() == 1) {
                strAttrCd.append(strAttrList[i].toLowerCase());
            } else {
                strAttrCd.append(strAttrList[i].substring(0, 1).toUpperCase() + strAttrList[i].substring(1).toLowerCase());
            }
        }
        return strAttrCd.toString();
    }

    // START 2016/07/12 M.Ugaki [QC#11674, ADD]
    // For w/o Suffix
    private String getAttrCd(String strPhysicalAttr) {
        return getAttrCd(strPhysicalAttr, false);
    }

    // For with Suffix
    private String getAttrCdWithSuffix(String strPhysicalAttr) {
        return getAttrCd(strPhysicalAttr, true);
    }
    // END 2016/07/12 M.Ugaki [QC#11674, ADD]

    private boolean deleteTableInfo(NSAL9900SMsg sMsg, NSAL9900CMsg cMsg, EZDTMsg tMsg) {

        EZDItemAttribute[] item = new EZDItemAttribute[cMsg.C.getValidCount()];
        String glblCmpyCd = sMsg.glblCmpyCd.getValue();

        int colCnt;
        String keyStr;
        BigDecimal rowNum;
        String strAttrCd;
        boolean setTMsgFlg;
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            tMsg.clear();
            tMsg.setDBValue("glblCmpyCd", glblCmpyCd);
            setTMsgFlg = false;

            for (colCnt = 0; colCnt < PK_COL_CNT; colCnt++) {
                String strSfx = leftPad(String.valueOf(colCnt), PAD_NUM, STR_ZERO);
                keyStr = STR_XX_ROW_NUM_PK + STR_UNDER_BAR + strSfx;
                rowNum = sMsg.D.no(i).getValueBigDecimal(keyStr, -1);
                if (!hasValue(rowNum)) {
                    continue;
                }

                strAttrCd = getAttrCd(cMsg.C.no(rowNum.intValue()).physMaintTrgtColNm.getValue());
                item[rowNum.intValue()] = tMsg.getAttr(strAttrCd);

                // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                if (item[rowNum.intValue()] == null) {
                    strAttrCd = getAttrCdWithSuffix(cMsg.C.no(rowNum.intValue()).physMaintTrgtColNm.getValue());
                    item[rowNum.intValue()] = tMsg.getAttr(strAttrCd);
                }
                // END 2016/07/12 M.Ugaki [QC#11674, ADD]

                // Set value to PK Column
                if (item[rowNum.intValue()].isDBPrimaryKey()) {
                    setTMsgFlg = true;
                    if (COL_TP_NUMBER.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueBigDecimal(STR_XX_NUMBER_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_STRING.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_STRING_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_DATE.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_DATE_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_YEARMONTH.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_YEARMONTH_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_YEAR.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_YEAR_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_TIME.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_TIME_PK + STR_UNDER_BAR + strSfx, -1));
                    } else if (COL_TP_TS.equals(cMsg.C.no(rowNum.intValue()).colTpCd.getValue())) {
                        tMsg.setDBValue(strAttrCd, sMsg.D.no(i).getValueString(STR_XX_TS_PK + STR_UNDER_BAR + strSfx, -1));
                    }
                }
            }

            if (!setTMsgFlg) {
                continue;
            }

            try {
                EZDTMsg delTMsg = EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (delTMsg != null) {
                    EZDTBLAccessor.logicalRemove(delTMsg);
                    if (!RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(MSG_ID_ZZZM9014E, new String[]{delTMsg.getReturnCode()});
                        return false;
                    }
                }
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(MSG_ID_NZZM0003E);
                return false;
            }
        }
        return true;
    }

    private boolean createUpdateTableInfo(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, EZDTMsg tMsg) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (FLG_OFF_N.equals(sMsg.B.no(i).updateFlg.getValue())) {
                continue;
            } else if (hasValue(sMsg.B.no(i).ezUpTime)) {
                if (!updateTableInfo(cMsg, sMsg, i, tMsg)) {
                    return false;
                }
            } else {
                if (!createTableInfo(cMsg, sMsg, i, tMsg)) {
                    return false;
                }
            }
        }
        // add start 2016/05/10 QC#5389
        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        // add end 2016/05/10 QC#5389
        return true;
    }

    private boolean updateTableInfo(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, int bIdx, EZDTMsg tMsg) {

        boolean result = true;
        EZDItemAttribute[] item = new EZDItemAttribute[sMsg.C.getValidCount()];
        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        String strAttrCd = "";

        tMsg.clear();
        tMsg.setDBValue("glblCmpyCd", glblCmpyCd);
        for (int colInfoIdx = 0; colInfoIdx < sMsg.C.getValidCount(); colInfoIdx++) {
            strAttrCd = getAttrCd(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
            item[colInfoIdx] = tMsg.getAttr(strAttrCd);

            // START 2016/07/12 M.Ugaki [QC#11674, ADD]
            if (item[colInfoIdx] == null) {
                strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                item[colInfoIdx] = tMsg.getAttr(strAttrCd);
            }
            // END 2016/07/12 M.Ugaki [QC#11674, ADD]
  
            if (item[colInfoIdx] != null) {
                if (item[colInfoIdx].isDBPrimaryKey()) {
                    if (COL_TP_NUMBER.equals(sMsg.C.no(colInfoIdx).colTpCd.getValue())) {
                        setValueForBigDecimal(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                    } else {
                        setValueForString(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                    }
                }
            }
        }

        try {
            tMsg = EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(MSG_ID_NZZM0003E);
            return false;
        }

        if (tMsg != null) {
            for (int colInfoIdx = 0; colInfoIdx < sMsg.C.getValidCount(); colInfoIdx++) {
                strAttrCd = getAttrCd(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                item[colInfoIdx] = tMsg.getAttr(strAttrCd);

                // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                if (item[colInfoIdx] == null) {
                    strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                    item[colInfoIdx] = tMsg.getAttr(strAttrCd);
                }
                // END 2016/07/12 M.Ugaki [QC#11674, ADD]

                if (item[colInfoIdx] != null) {
                    if (!item[colInfoIdx].isDBPrimaryKey()) {
                        if (COL_TP_NUMBER.equals(sMsg.C.no(colInfoIdx).colTpCd.getValue())) {
                            setValueForBigDecimal(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                        } else {
                            setValueForString(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                        }
                    }
                }
            }
            EZDTBLAccessor.update(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[]{tMsg.getReturnCode()});
                result = false;
            }
        }
        return result;
    }

    private static void setValueForString(NSAL9900SMsg sMsg, int bIdx, int cIdx, String colNm, EZDTMsg tMsg) {
        if (hasValue(sMsg.B.no(bIdx).getValueString(sMsg.C.no(cIdx).detailItemNm.getValue(), -1))) {
            tMsg.setDBValue(colNm, sMsg.B.no(bIdx).getValueString(sMsg.C.no(cIdx).detailItemNm.getValue(), -1));
        } else {
            if (DPLY_CTRL_CHECK_BOX.equals(sMsg.C.no(cIdx).dplyCtrlCd.getValue())) {
                tMsg.setDBValue(colNm, FLG_OFF_N);
            } else {
                tMsg.setDBValue(colNm, "");
            }
        }
    }

    private static void setValueForBigDecimal(NSAL9900SMsg sMsg, int bIdx, int cIdx, String colNm, EZDTMsg tMsg) {
        if (hasValue(sMsg.B.no(bIdx).getValueBigDecimal(sMsg.C.no(cIdx).detailItemNm.getValue(), -1))) {
            tMsg.setDBValue(colNm, sMsg.B.no(bIdx).getValueBigDecimal(sMsg.C.no(cIdx).detailItemNm.getValue(), -1));
        } else {
            // START 2016/03/09 T.Aoyagi [QC#5213, MOD]
//            tMsg.setDBValue(colNm, BigDecimal.ZERO);
            tMsg.setDBValue(colNm, null);
            // END 2016/03/09 T.Aoyagi [QC#5213, MOD]
        }
    }

    private BigDecimal getOracleSq(String colNm) {
        String rtnValue = colNm;
        if (colNm.endsWith(PK_SFX)) {
            rtnValue = colNm.substring(0, colNm.lastIndexOf(PK_SFX)) + SQ_SFX;
        }
        return ZYPOracleSeqAccessor.getNumberBigDecimal(rtnValue);
    }

    private void setValueForOracleSq(BigDecimal value, String colNm, EZDTMsg tMsg) {
        if (hasValue(value)) {
            tMsg.setDBValue(colNm, value);
        }
    }

    private boolean createTableInfo(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, int bIdx, EZDTMsg tMsg) {

        boolean result = true;

        // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
        if (NSAL9900CommonLogic.isDuplicate(cMsg, sMsg, bIdx)) {
            sMsg.B.no(bIdx).xxChkBox.setErrorInfo(1, MSG_ID_NSAM0537E);
            return false;
        }
        // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

        EZDItemAttribute[] item = new EZDItemAttribute[sMsg.C.getValidCount()];
        String strAttrCd = "";
        BigDecimal pk;

        tMsg.clear();
        tMsg.setDBValue("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        for (int colInfoIdx = 0; colInfoIdx < sMsg.C.getValidCount(); colInfoIdx++) {
            strAttrCd = getAttrCd(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
            item[colInfoIdx] = tMsg.getAttr(strAttrCd);

            // START 2016/07/12 M.Ugaki [QC#11674, ADD]
            if (item[colInfoIdx] == null) {
                strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                item[colInfoIdx] = tMsg.getAttr(strAttrCd);
            }
            // END 2016/07/12 M.Ugaki [QC#11674, ADD]

            if (item[colInfoIdx] != null) {
                String colNm = sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue();
                // mod start 2017/09/15 QC#19775
                //if (item[colInfoIdx].isDBPrimaryKey() && colNm.endsWith(PK_SFX)) {
                if (item[colInfoIdx].isDBPrimaryKey() && colNm.endsWith(PK_SFX) && colNm.startsWith(sMsg.C.no(colInfoIdx).physMaintTrgtTblNm.getValue())) {
                // mod end 2017/09/15 QC#19775
                    pk = getOracleSq(colNm);
                    if (hasValue(pk)) {
                        setValueForOracleSq(pk, strAttrCd, tMsg);
                    } else {
                        throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{SCREEN_ID});
                    }
                } else {
                    if (COL_TP_NUMBER.equals(sMsg.C.no(colInfoIdx).colTpCd.getValue())) {
                        setValueForBigDecimal(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                    } else {
                        setValueForString(sMsg, bIdx, colInfoIdx, strAttrCd, tMsg);
                    }
                }
            }
        }
        EZDTBLAccessor.create(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            // add start 2016/05/10 QC#5389
            if (RTNCD_DUPLICATE.equals(tMsg.getReturnCode())) {
                String detailItemNm;
                try {
                    for (int colInfoIdx = 0; colInfoIdx < sMsg.C.getValidCount(); colInfoIdx++) {
                        strAttrCd = getAttrCd(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                        item[colInfoIdx] = tMsg.getAttr(strAttrCd);

                        // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                        if (item[colInfoIdx] == null) {
                            strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue());
                            item[colInfoIdx] = tMsg.getAttr(strAttrCd);
                        }
                        // END 2016/07/12 M.Ugaki [QC#11674, ADD]

                        detailItemNm = sMsg.C.no(colInfoIdx).detailItemNm.getValue();
                        if (item[colInfoIdx] != null && item[colInfoIdx].isDBPrimaryKey()) {
                            Field f = NSAL9900_BSMsg.class.getField(detailItemNm);
                            ((EZDSItem) f.get(sMsg.B.no(bIdx))).setErrorInfo(1, MSG_ID_NSAM0035E, new String[] {"Key" });
                        }
                    }
                } catch (Exception ex) {
                    throw new S21AbendException(MSG_ID_ZZZM9012E, new String[] {tMsg.getReturnCode() });
                }
            }
            // add end 2016/05/10 QC#5389
            cMsg.setMessageInfo(MSG_ID_ZZZM9012E, new String[]{tMsg.getReturnCode()});
            result = false;
        }
        return result;
    }
}
