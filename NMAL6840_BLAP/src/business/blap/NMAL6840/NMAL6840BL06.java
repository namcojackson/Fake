/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6840;

import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_CMN_SUBMIT;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0010E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0074E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0186E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_PARAM_CONFIGURED;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_PARAM_RTL_SWH;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_PARAM_RTL_SWH_CD;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NMAL6840.common.NMAL6840CommonLogic;
import business.db.RTL_WH_CATG_SWH_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * </pre>
 */
public class NMAL6840BL06 extends S21BusinessHandler {

    /*
     * (non-Javadoc)
     * @see
     * parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg
     * , parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();

            NMAL6840CMsg bizMsg = (NMAL6840CMsg) cMsg;
            NMAL6840SMsg glblMsg = (NMAL6840SMsg) sMsg;

            if (EVENT_NM_CMN_SUBMIT.equals(scrnAplId)) {
                doProcessCmnSubmit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Runs the onclick event of "SUBMIT" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnSubmit(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        // Checks if WH Category is changed after search.
        if (NMAL6840CommonLogic.isChangedAfterSearch(sMsg.rtlWhCatgCd_H1, cMsg.rtlWhCatgCd_H1)) {
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM0186E);
            return;
        }

        // Gets the digit number of Sub WH Code.
        for (int rowIndex = 0; rowIndex < cMsg.A.getValidCount(); rowIndex++) {

            NMAL6840_ACMsg row = cMsg.A.no(rowIndex);

            // Gets Sub WH Relation to be updated.
            RTL_WH_CATG_SWH_RELNTMsg updateMsg = searchSubWarehouseForUpdate(cMsg.rtlWhCatgCd_H1, row.rtlSwhCd_A1);

            if (updateMsg == null) {

                // Registers Retail WH Category and Sub WH Relation.
                RTL_WH_CATG_SWH_RELNTMsg insertedMsg = insertRtlWhCatgSwhReln(cMsg.rtlWhCatgCd_H1, row);

                // Checks the unique constraint.
                if (S21FastTBLAccessor.RTNCD_DUPLICATE.equals(insertedMsg.getReturnCode())) {
                    NMAL6840CommonLogic.setErrorInfo(row.rtlSwhCd_A1, MESSAGE_CD_NMAM0010E, MESSAGE_PARAM_RTL_SWH_CD);
                    continue;
                }

            } else {

                // Checks if the row is updated by other user.
                if (isUpdatedByOtherUser(row, updateMsg)) {
                    NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM0186E);
                    return;
                }

                // Checks if can update the disabled flag.
                if (NMAL6840CommonLogic.isChecked(row.rtlSwhDsblFlg_A1)) {
                    if (!canUpdateDisabledFlag(cMsg, row)) {
                        NMAL6840CommonLogic.setErrorInfo(row.rtlSwhCd_A1, MESSAGE_CD_NMAM0074E, MESSAGE_PARAM_RTL_SWH, MESSAGE_PARAM_CONFIGURED);
                        continue;
                    }
                }

                // Updates the Retail WH Category and Sub WH Relation.
                updateRtlWhCatgSwhReln(updateMsg, row);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {
                    NMAL6840CommonLogic.setErrorInfo(row.rtlSwhCd_A1, MESSAGE_CD_NMAM0010E, MESSAGE_PARAM_RTL_SWH_CD);
                    continue;
                }
            }
        }
    }

    /**
     * <p>
     * Searches Retail WH Category and Sub WH Relation for update.
     * </p>
     * @param whCatgCd WH Category
     * @param subWhCd Sub WH Code
     * @return the search result
     */
    private RTL_WH_CATG_SWH_RELNTMsg searchSubWarehouseForUpdate(EZDCStringItem whCatgCd, EZDCStringItem subWhCd) {

        RTL_WH_CATG_SWH_RELNTMsg condition = new RTL_WH_CATG_SWH_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(condition.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(condition.rtlWhCatgCd, whCatgCd);
        ZYPEZDItemValueSetter.setValue(condition.rtlSwhCd, subWhCd);

        RTL_WH_CATG_SWH_RELNTMsg updateMsg = (RTL_WH_CATG_SWH_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(condition);
        return updateMsg;
    }

    /**
     * <p>
     * Inserts a new Retail WH Category and Sub WH Relation.
     * </p>
     * @param whCatgCd WH Category
     * @param row the row data of screen
     * @return the inserted Retail WH Category and Sub WH Relation
     */
    private RTL_WH_CATG_SWH_RELNTMsg insertRtlWhCatgSwhReln(EZDCStringItem whCatgCd, NMAL6840_ACMsg row) {

        RTL_WH_CATG_SWH_RELNTMsg insertMsg = new RTL_WH_CATG_SWH_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(insertMsg.rtlWhCatgCd, whCatgCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhCd, row.rtlSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhNm, row.rtlSwhNm_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhSortNum, row.rtlSwhSortNum_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhDescTxt, row.rtlSwhDescTxt_A1);
        if (NMAL6840CommonLogic.isChecked(row.rtlSwhDsblFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhDsblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhDsblFlg, ZYPConstant.FLG_OFF_N);
        }
        if (NMAL6840CommonLogic.isChecked(row.rtlSwhMndFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(insertMsg.rtlSwhMndFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(insertMsg.prtyLocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertMsg.mrpEnblFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(insertMsg);

        return insertMsg;
    }

    /**
     * <p>
     * Updates Retail WH Category and Sub WH Relation.
     * </p>
     * @param updateMsg the target to be updated
     * @param row the row data of screen
     */
    private void updateRtlWhCatgSwhReln(RTL_WH_CATG_SWH_RELNTMsg updateMsg, NMAL6840_ACMsg row) {
        ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhNm, row.rtlSwhNm_A1);
        ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhSortNum, row.rtlSwhSortNum_A1);
        ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhDescTxt, row.rtlSwhDescTxt_A1);
        if (NMAL6840CommonLogic.isChecked(row.rtlSwhDsblFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhDsblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhDsblFlg, ZYPConstant.FLG_OFF_N);
        }
        if (NMAL6840CommonLogic.isChecked(row.rtlSwhMndFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(updateMsg.rtlSwhMndFlg, ZYPConstant.FLG_OFF_N);
        }

        S21FastTBLAccessor.update(updateMsg);
    }

    /**
     * <p>
     * Checks whether the row to be updated has been updated by the
     * other user.
     * </p>
     * @param scrnRow the row of screen
     * @param dbRecord the record of database
     * @return if already is updated, returns true.
     */
    private boolean isUpdatedByOtherUser(NMAL6840_ACMsg scrnRow, RTL_WH_CATG_SWH_RELNTMsg dbRecord) {

        String scrnUpTime = NMAL6840CommonLogic.getValueIfNullReturnEmpty(scrnRow.ezUpTime_A1);
        String scrnUpTimeZone = NMAL6840CommonLogic.getValueIfNullReturnEmpty(scrnRow.ezUpTimeZone_A1);

        String dbUpTime = NMAL6840CommonLogic.getValueIfNullReturnEmpty(dbRecord.ezUpTime);
        String dbUpTimeZone = NMAL6840CommonLogic.getValueIfNullReturnEmpty(dbRecord.ezUpTimeZone);

        return !(ZYPDateUtil.isSameTimeStamp(scrnUpTime, scrnUpTimeZone, dbUpTime, dbUpTimeZone));
    }

    /**
     * <p>
     * Checks If the disable flag can be updated.
     * </p>
     * @param cMsg CMsg
     * @param row row data
     * @return if can be updated, returns true.
     */
    private boolean canUpdateDisabledFlag(NMAL6840CMsg cMsg, NMAL6840_ACMsg row) {
        // If the Sub WH is configured on WH Setup Screen,
        // can NOT update the disabled flag.
        S21SsmEZDResult result = NMAL6840Query.getInstance().searchSubWarehouseRecordCount(cMsg, row.rtlSwhCd_A1);
        if (!result.isCodeNormal()) {
            return false;
        }
        Integer count = (Integer) result.getResultObject();
        return (count == 0);
    }

}
