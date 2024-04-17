/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0350.common;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import business.blap.NSAL0350.NSAL0350CMsg;
import business.blap.NSAL0350.NSAL0350SMsg;
import business.blap.NSAL0350.constant.NSAL0350Constant;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.SKIP_RECOV_TPTMsg;
import business.db.SKIP_RECOV_TPTMsgArray;
import business.db.SVC_INV_CHRG_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Hitachi         A.Kohinata      Update          N/A
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 *</pre>
 */
public class NSAL0350CommonLogic {

    /**
     * <pre>
     * createScheduleRowPulldownList
     * </pre>
     * @param cMsg NSAL0350CMsg
     */
    public static void createScheduleRowPulldownList(NSAL0350CMsg cMsg) {
        try {
            // START 2015/10/15 [N/A, MOD]
            //ZYPCodeDataUtil.createPulldownList(SKIP_RECOV_TP.class, (EZDCStringItemArray) ezdCMsg.getClass().getField(replSfx("skipRecovTpCd_A1", tblNm)).get(ezdCMsg), (EZDCStringItemArray) ezdCMsg.getClass().getField(
            //        replSfx("skipRecovTpNm_A2", tblNm)).get(ezdCMsg));
            ZYPCodeDataUtil.createPulldownList(SKIP_RECOV_TP.class, cMsg.skipRecovTpCd_H1, cMsg.skipRecovTpNm_H1);
            // END 2015/10/15 [N/A, MOD]
            // START 2017/08/07 K.Kitachi [QC#20048, ADD]
            ZYPCodeDataUtil.createPulldownList(DS_BLLG_SCHD_STS.class, cMsg.dsBllgSchdStsCd_PL, cMsg.dsBllgSchdStsDescTxt_PL);
            // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static DS_CONTR_BLLG_SCHDTMsg findDsContrBllgSchdForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdPk, BigDecimal dsContrBllgSchdSmryPk, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_SCHDTMsg prmTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    public static SVC_INV_CHRG_TPTMsg getSvcInvChrgTp(String glblCmpyCd, String svcInvChrgTpCd) {
        SVC_INV_CHRG_TPTMsg inMsg = new SVC_INV_CHRG_TPTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.svcInvChrgTpCd.setValue(svcInvChrgTpCd);
        inMsg.ezCancelFlag.setValue("0");
        return (SVC_INV_CHRG_TPTMsg) ZYPCodeDataUtil.findByKey(inMsg);
    }

    public static SKIP_RECOV_TPTMsgArray getSkipRecovTpArray(String glblCmpyCd) {
        SKIP_RECOV_TPTMsg inMsg = new SKIP_RECOV_TPTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.ezCancelFlag.setValue("0");
        return (SKIP_RECOV_TPTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
    }

    public static SKIP_RECOV_TPTMsgArray getSkipRecovTpByRecovFlg(String glblCmpyCd, String bllgRecovFlg) {
        SKIP_RECOV_TPTMsg inMsg = new SKIP_RECOV_TPTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bllgRecovFlg.setValue(bllgRecovFlg);
        inMsg.ezCancelFlag.setValue("0");
        return (SKIP_RECOV_TPTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
    }

    public static boolean isChangedSchedules(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {
        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd_H1.getValue();
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            if (isChangedSchedules(cMsg, sMsg, NSAL0350Constant.BASE_TBL_NM)) {
                return true;
            }
        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
            for (String tblNm : NSAL0350Constant.USAGE_TBL_NM_ARRAY) {
                if (isChangedSchedules(cMsg, sMsg, tblNm)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isChangedSchedules(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg, String tblNm) {

        EZDMsgArray cTblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        EZDMsgArray sTblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(sMsg, tblNm);

        if (sTblArray.getValidCount() != cTblArray.getValidCount()) {
            return true;
        }

        int cnt = sTblArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg cEsdMsg = cTblArray.get(i);
            EZDMsg sEsdMsg = sTblArray.get(i);

            if (isChanged(tblNm, cEsdMsg, sEsdMsg)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChanged(String tblNm, EZDMsg cEsdMsg, EZDMsg sEsdMsg) {
        EZDCStringItem skipRecovTpCd_C = NSAL0350CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "skipRecovTpCd_A3");
        EZDSStringItem skipRecovTpCd_S = NSAL0350CommonLogic.getStringValueFromEZDMsg_S(sEsdMsg, tblNm, "skipRecovTpCd_A3");
        if (!skipRecovTpCd_S.getValue().equals(skipRecovTpCd_C.getValue())) {
            return true;
        }
        return false;
    }

    public static EZDCStringItem getStringValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCStringItem result = null;
        try {
            result = (EZDCStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDCBigDecimalItem getBigDecimalValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCBigDecimalItem result = null;
        try {
            result = (EZDCBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDCDateItem getDateValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCDateItem result = null;
        try {
            result = (EZDCDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0350CMsg cMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) cMsg.getClass().getField(tblNm).get(cMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSStringItem getStringValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSStringItem result = null;
        try {
            result = (EZDSStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSBigDecimalItem getBigDecimalValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSBigDecimalItem result = null;
        try {
            result = (EZDSBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSDateItem getDateValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSDateItem result = null;
        try {
            result = (EZDSDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0350SMsg sMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) sMsg.getClass().getField(tblNm).get(sMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String replSfx(String str, String tblNm) {
        return str.replaceAll("_.", "_" + tblNm);
    }

    // START 2017/08/07 K.Kitachi [QC#20048, ADD]
    public static void clearSearchCriteria(NSAL0350CMsg cMsg) {
        cMsg.bllgSchdFromDt_HD.clear();
        cMsg.bllgSchdThruDt_HD.clear();
        cMsg.dsBllgSchdStsCd_HD.clear();
        cMsg.skipRecovTpCd_HD.clear();
    }
    // END 2017/08/07 K.Kitachi [QC#20048, ADD]
}
