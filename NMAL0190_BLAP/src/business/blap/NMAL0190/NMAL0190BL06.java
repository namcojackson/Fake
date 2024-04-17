/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0190;

import static business.blap.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_WARN;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_ITEM_NUM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_TBL_MDSE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_TBL_MDSE_ITEM_FLIP_SET;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_TBL_SUPD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_TBL_SUPD_RELN;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MSG_PRM_TBL_SUPD_RELN_STAGE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM8121E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM8175E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM8377E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM8378E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM8440E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_CONST_COMPATIBLE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.ZZMM0001E;
import static business.blap.NMAL0190.constant.NMAL0190Constant.ZZMM0015E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0190.common.NMAL0190CommonLogic;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_FLIP_SETTMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.SUPDTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SUPD_RELN_STAGETMsg;
import business.parts.NPZC107001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC107001.NPZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0190BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/07   CITS            S.Tanikawa      UPDATE          QC#2669
 * 2016/03/16   CITS            S.Tanikawa      UPDATE          QC#5651
 * 2016/06/23   SRAA            K.Aratani       UPDATE          QC#10653
 *</pre>
 */
public class NMAL0190BL06 extends S21BusinessHandler {

    @Override
    /**
     * Process Event
     */
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0190CMsg bizMsg = (NMAL0190CMsg) cMsg;
            NMAL0190SMsg glblMsg = (NMAL0190SMsg) sMsg;

            if ("NMAL0190Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL0190Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0190Scrn00_CMN_Submit(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {

    	//QC#10653
    	List<String> mdseCdListToDealConfig = new ArrayList<String>();
        // UPDATE START 2016/03/07 QC#2669

        // List<Integer> selectedRows =
        // ZYPTableUtil.getSelectedRows(bizMsg.A, TBL_ITEM_CHECKBOX,
        // CHKBOX_ON_Y);
        //
        // Map<String, NMAL0190_ACMsg> selectItemNumbers = new
        // HashMap<String, NMAL0190_ACMsg>(selectedRows.size());
        //
        // for (int row : selectedRows) {
        // NMAL0190_ACMsg lineMsg = bizMsg.A.no(row);
        // selectItemNumbers.put(lineMsg.supdToMdseCd.getValue(),
        // lineMsg);
        // }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL0190_ACMsg lineMsg = bizMsg.A.no(i);
            String supdToMdseCd = lineMsg.supdToMdseCd.getValue();
            String supdFromMdseCd = lineMsg.supdFromMdseCd.getValue();
            // MDSE check
            // Error if ItemNumber does not exsist in MDSE
            if (NMAL0190CommonLogic.existMdse(this.getGlobalCompanyCode(), supdToMdseCd) == null) {
                bizMsg.A.no(i).supdToMdseCd.setErrorInfo(1, NMAM8121E, new String[] {bizMsg.A.no(i).supdToMdseCd.getValue() });
                return;
            }
            if (NMAL0190CommonLogic.existMdse(this.getGlobalCompanyCode(), supdFromMdseCd) == null) {
                bizMsg.A.no(i).supdFromMdseCd.setErrorInfo(1, NMAM8121E, new String[] {bizMsg.A.no(i).supdFromMdseCd.getValue() });
                return;
            }

            // update MDSE and MDSE
            if (!updateMdse(bizMsg, lineMsg)) {
                return;
            }

            // UPDATE START 2016/03/17 QC#5651
            // SUPD check
            // insert if Supersede does not exsist in SUPD
            if (NMAL0190CommonLogic.existSupd(this.getGlobalCompanyCode(), supdToMdseCd) == null) {
                // bizMsg.A.no(i).supdToMdseCd.setErrorInfo(1, NMAM8440E, new String[] {bizMsg.A.no(i).supdToMdseCd.getValue() });
                // return;

                // Insert new SUPD Record
                if (!insertSupd(bizMsg, lineMsg.supdToMdseCd.getValue(), ZYPConstant.FLG_ON_Y)) {
                    return;
                }
            }
            // update if ItemNumber already exsists in SUPD
            if (NMAL0190CommonLogic.existSupd(this.getGlobalCompanyCode(), supdFromMdseCd) != null) {
                if (!updateSupd(bizMsg, supdFromMdseCd)) {
                    return;
                }
            // insert if Supersede does not exsist in SUPD
            } else {
                // Insert new SUPD Record
                if (!insertSupd(bizMsg, lineMsg.supdFromMdseCd.getValue(), ZYPConstant.FLG_OFF_N)) {
                    return;
                }
            }
            // ADD END 2016/03/17 QC#5651

            // SUPD_RELN check
            // Error if ItemNumber already exsists in SUPD_RELN
            if (NMAL0190CommonLogic.existSupdReln(this.getGlobalCompanyCode(), supdToMdseCd, supdFromMdseCd) != null) {
                bizMsg.A.no(i).supdToMdseCd.setErrorInfo(1, NMAM8440E, new String[] {bizMsg.A.no(i).supdToMdseCd.getValue() });
                return;
            }
            // when supsersedes already has relation with other Items
            if (NMAL0190CommonLogic.isExistSupdFrom(lineMsg)) {
                lineMsg.supdFromMdseCd.setErrorInfo(1, NMAM8377E, new String[] {});
                return;
            }
            // when Supersudes Relation is Looping
            if (NMAL0190CommonLogic.isLoopSupd(lineMsg)) {
                lineMsg.supdToMdseCd.setErrorInfo(1, NMAM8378E, new String[] {});
                return;
            }
            // Insert new SUPD_RELN Record
            if (!insertSupdReln(bizMsg, lineMsg)) {
                return;
            }

            // Update SUPD_RELN_STAGE.SUBMT_FLG ON
            if (!updateSupdRelnStage(bizMsg, lineMsg)) {
                return;
            }

            // CALL NPAZ1070
            boolean errFlg = false;
            NPZC107001PMsg apiMsg = executeNPZC1070(lineMsg);
            if (S21ApiUtil.isXxMsgId(apiMsg)) {
                List<String> msgList = S21ApiUtil.getXxMsgIdList(apiMsg);
                for (int j = 0; j < msgList.size(); j++) {
                    String messageId = msgList.get(j);
                    if (messageId.endsWith(MESSAGE_KIND_ERROR) || messageId.endsWith(MESSAGE_KIND_WARN)) {
                        bizMsg.setMessageInfo(messageId);
                        errFlg = true;
                    }
                }
            }
            //QC#10653
            if (!errFlg && !mdseCdListToDealConfig.contains(lineMsg.supdFromMdseCd.getValue())) {
                mdseCdListToDealConfig.add(lineMsg.supdFromMdseCd.getValue());
            }
        }

        // MDSE_ITEM_FLIP_SET
        for (int bi = 0; bi < bizMsg.B.getValidCount(); bi++) {
            NMAL0190_BCMsg lineBMsg = bizMsg.B.no(bi);
            String supdToMdseCd = lineBMsg.supdToMdseCd_B.getValue();
            String relnMdseCd = lineBMsg.relnMdseCd.getValue();

            // compatible with FORWARD
            // when xxSupdFlg_FW is newly checked
            if (ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_FW.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_FH.getValue())) {
                // check inserting record is already exist
                if (NMAL0190CommonLogic.existMdseItemFlipSet(this.getGlobalCompanyCode(), supdToMdseCd, relnMdseCd) != null) {
                    lineBMsg.xxSupdFlg_FW.setErrorInfo(1, NMAM8440E, new String[] {lineBMsg.supdToMdseCd_B.getValue() + " & " +  lineBMsg.relnMdseCd.getValue()});
                    return;
                }
                // insert new MDSE_ITEM_FLIP_SET Record
                if (!insertMdseItemFlipSet(bizMsg, supdToMdseCd, relnMdseCd)) {
                    return;
                }
                //QC#10653
                if (!mdseCdListToDealConfig.contains(supdToMdseCd)) {
                    mdseCdListToDealConfig.add(supdToMdseCd);
                }

                // when xxSupdFlg_FW is checked off
            } else if (!ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_FW.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_FH.getValue())) {
                // delete MDSE_ITEM_FLIP_SET Record
                if (!deleteMdseItemFlipSet(bizMsg, supdToMdseCd, relnMdseCd)) {
                    return;
                }
                //QC#10653
                if (!mdseCdListToDealConfig.contains(supdToMdseCd)) {
                    mdseCdListToDealConfig.add(supdToMdseCd);
                }
            }
            // compatible with BACKWARD
            // when xxSupdFlg_BW is newly checked
            if (ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_BW.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_BH.getValue())) {
                // check inserting record is already exist
                if (NMAL0190CommonLogic.existMdseItemFlipSet(this.getGlobalCompanyCode(), relnMdseCd, supdToMdseCd) != null) {
                    lineBMsg.xxSupdFlg_BW.setErrorInfo(1, NMAM8440E, new String[] {lineBMsg.relnMdseCd.getValue() + " & " +  lineBMsg.supdToMdseCd_B.getValue()});
                    return;
                }
                // insert new MDSE_ITEM_FLIP_SET Record
                if (!insertMdseItemFlipSet(bizMsg, relnMdseCd, supdToMdseCd)) {
                    return;
                }
                //QC#10653
                if (!mdseCdListToDealConfig.contains(relnMdseCd)) {
                    mdseCdListToDealConfig.add(relnMdseCd);
                }

                // when xxSupdFlg_BW is checked off
            } else if (!ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_BW.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(lineBMsg.xxSupdFlg_BH.getValue())) {
                // delete MDSE_ITEM_FLIP_SET Record
                if (!deleteMdseItemFlipSet(bizMsg, relnMdseCd, supdToMdseCd)) {
                    return;
                }
                //QC#10653
                if (!mdseCdListToDealConfig.contains(relnMdseCd)) {
                    mdseCdListToDealConfig.add(relnMdseCd);
                }
            }
            // DELETE START 2016/03/07 QC#2669
            // if (!selectItemNumbers.keySet().contains(mdseCd)) {
            // continue;
            // }
            // if
            // (NMAL0190CommonLogic.existSupdReln(this.getGlobalCompanyCode(),
            // mdseCd) != null) {
            // bizMsg.setMessageInfo(NMAM0834E, new String[]
            // {MSG_PRM_TBL_SUPD_RELN, mdseCd });
            // return;
            // }
            // if
            // (NMAL0190CommonLogic.existSupd(this.getGlobalCompanyCode(),
            // mdseCd) != null) {
            // bizMsg.setMessageInfo(NMAM0834E, new String[]
            // {MSG_PRM_TBL_SUPD, mdseCd });
            // return;
            // }
            // if
            // (NMAL0190CommonLogic.existMdseItemFlipSet(this.getGlobalCompanyCode(),
            // mdseCd) != null) {
            // bizMsg.setMessageInfo(NMAM0834E, new String[]
            // {MSG_PRM_TBL_MDSE_ITEM_FLIP_SET, mdseCd });
            // return;
            // }
            //
            // if
            // (NMAL0190CommonLogic.existMdseItemFlipSet(this.getGlobalCompanyCode(),
            // lineMsg.relnMdseCd.getValue()) != null) {
            // bizMsg.setMessageInfo(NMAM0834E, new String[]
            // {MSG_PRM_TBL_MDSE_ITEM_FLIP_SET,
            // lineMsg.relnMdseCd.getValue() });
            // return;
            // }
            // if (!updateSupdRelnStage(bizMsg, lineMsg)) {
            // return;
            // }
            // String dsctnFlg =
            // selectItemNumbers.get(mdseCd).dsctnFlg.getValue();
            // if (ZYPCommonFunc.hasValue(dsctnFlg)) {
            // if (!this.updateMdse(bizMsg, lineMsg)) {
            // return;
            // }
            // }
            // if (!insertSupd(bizMsg, lineMsg)) {
            // return;
            // }
            // if (!insertSupdReln(bizMsg, mdseCd)) {
            // return;
            // }
            // if (!insertMdseItemFlipSet(bizMsg, lineMsg)) {
            // return;
            // }
            // NPZC107001PMsg apiMsg =
            // executeNPZC1070(selectItemNumbers.get(mdseCd));
            // if (S21ApiUtil.isXxMsgId(apiMsg)) {
            // List<String> msgList =
            // S21ApiUtil.getXxMsgIdList(apiMsg);
            // for (int i = 0; i < msgList.size(); i++) {
            // String messageId = msgList.get(i);
            // if (messageId.endsWith(MESSAGE_KIND_ERROR) ||
            // messageId.endsWith(MESSAGE_KIND_WARN)) {
            // bizMsg.setMessageInfo(messageId);
            // }
            // }
            // }
            // DELETE END 2016/03/07 QC#2669
        }
        // UPDATE END 2016/03/07 QC#2669
        
        //QC#10653
        if (!mdseCdListToDealConfig.isEmpty()) {
    		NMAL0190CommonLogic.invokeMasterDataMessaging(mdseCdListToDealConfig);
        }
        
    }

    // UPDATE START 2016/03/07 QC#2669
    /**
     * Update Supd Reln Stage 
     * - SUBMIT FLG : N -> Y 
     * - If updated another user, Show Error : NMLA8175E
     * @param bizMsg Business Message
     * @param lineMsg Table A Line Message
     * @return result : Nomal (ture)
     */
    private boolean updateSupdRelnStage(NMAL0190CMsg bizMsg, NMAL0190_ACMsg lineMsg) {
        SUPD_RELN_STAGETMsg updateMsg = NMAL0190CommonLogic.findSupdRelnStage(getGlobalCompanyCode(), bizMsg.supdRelnStagePk_P.getValue(), true);

        String tmpTimeZone = updateMsg.ezUpTimeZone.getValue();
        String tmpTime = updateMsg.ezUpTime.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(lineMsg.ezUpTime_H.getValue(), lineMsg.ezUpTimeZone_H.getValue(), tmpTime, tmpTimeZone)) {
            // NMAM8175E=0,This data has been updated by another user.
            // [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo(NMAM8175E, new String[] {MSG_PRM_TBL_SUPD_RELN_STAGE, MSG_PRM_ITEM_NUM, String.valueOf(updateMsg.supdToMdseCd.getValue()) });
            return false;
        }
        ZYPEZDItemValueSetter.setValue(updateMsg.submtFlg, FLG_ON_Y);
        S21FastTBLAccessor.update(updateMsg);
        String returnCd = updateMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCd)) {
            // ZZMM0015E=0,Data update failure. [ TableName = @ , key
            // = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0015E, new String[] {MSG_PRM_TBL_SUPD_RELN_STAGE, MSG_PRM_ITEM_NUM, String.valueOf(updateMsg.supdToMdseCd.getValue()) });
            return false;
        }
        return true;
    }
    // UPDATE END 2016/03/07 QC#2669

    // UPDATE START 2016/03/07 QC#2669
    /**
     * Update MDSE 
     * - DSCTN_FLG : N -> Y 
     * - If updated another user, Show Error : NMLA8175E
     * @param bizMsg NMAL0190CMsg
     * @param lineMsg NMAL0190_ACMsg
     * @return result : Nomal (ture)
     */
    private boolean updateMdse(NMAL0190CMsg bizMsg, NMAL0190_ACMsg lineMsg) {

        MDSE_ITEM_STSTMsg mdseItemStsTMsg = NMAL0190CommonLogic.findMdseItemSts(getGlobalCompanyCode(), lineMsg.mdseItemStsCd_SL.getValue());

        // UPDATE MDSE
        MDSETMsg updateMsg = NMAL0190CommonLogic.findMdse(getGlobalCompanyCode(), lineMsg.supdFromMdseCd.getValue(), true);

        ZYPEZDItemValueSetter.setValue(updateMsg.mdseItemStsCd, mdseItemStsTMsg.mdseItemStsCd);
        ZYPEZDItemValueSetter.setValue(updateMsg.dsctnFlg, mdseItemStsTMsg.dsctnFlg);
        ZYPEZDItemValueSetter.setValue(updateMsg.sellHldFlg, mdseItemStsTMsg.sellHldFlg);
        S21FastTBLAccessor.update(updateMsg);
        String returnCd = updateMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCd)) {
            // ZZMM0015E=0,Data update failure. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0015E, new String[] {MSG_PRM_TBL_MDSE, MSG_PRM_ITEM_NUM, String.valueOf(updateMsg.mdseCd.getValue()) });
            return false;
        }
        return true;
    }
    // UPDATE END 2016/03/07 QC#2669

    // UPDATE START 2016/03/07 QC#2669
    /**
     * Insert SUPD
     * - MDSE_CD : Item Number
     * - INCG_FLG: N
     * 
     * @param bizMsg NMAL0190CMsg
     * @param mdseCd String
     * @param incgFlg String
     * @return result : Normal (true)
     */
    // private boolean insertSupd(NMAL0190CMsg bizMsg, NMAL0190_BCMsg lineMsg) {
    // private boolean insertSupd(NMAL0190CMsg bizMsg, NMAL0190_ACMsg lineMsg) {
    private boolean insertSupd(NMAL0190CMsg bizMsg, String mdseCd, String incgFlg) {
        SUPDTMsg insertMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd, mdseCd);
        // UPDATE START 2016/03/16 QC#5651
        // ZYPEZDItemValueSetter.setValue(insertMsg.incgFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insertMsg.incgFlg, incgFlg);
        // UPDATE END   2016/03/16 QC#5651
        S21FastTBLAccessor.create(insertMsg);
        String returnCd = insertMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCd)) {
            // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0001E, new String[] {MSG_PRM_TBL_SUPD, MSG_PRM_MDSE_CD, mdseCd });
            return false;
        }
        return true;
    }

    /**
     * updateSupd
     * @param bizMsg NMAL0190CMsg
     * @param mdseCd String
     * @return result : Normal (true)
     */
    private boolean updateSupd(NMAL0190CMsg bizMsg, String mdseCd) {

        SUPDTMsg updateMsg = new SUPDTMsg();

        ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(updateMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(updateMsg.ezCancelFlag, "0");
        updateMsg = (SUPDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(updateMsg);

        ZYPEZDItemValueSetter.setValue(updateMsg.incgFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(updateMsg);
        String returnCdDsMdseInfo = updateMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCdDsMdseInfo)) {
            // ZZMM0015E=0,Data update failure. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0015E, new String[] {MSG_PRM_TBL_SUPD, MSG_PRM_ITEM_NUM, String.valueOf(updateMsg.mdseCd.getValue()) });
            return false;
        }

        return true;
    }
    // UPDATE END 2016/03/07 QC#2669

    /**
     * Insert SUPD RELN
     * - MDSE_CD : Item Number
     * - INCG_FLG: Y
     * 
     * @param bizMsg NMAL0190CMsg
     * @param lineMsg NMAL0190_ACMsg
     * @return result : Nomal (ture)
     */
    private boolean insertSupdReln(NMAL0190CMsg bizMsg, NMAL0190_ACMsg lineMsg) {
        // UPDATE START 2016/03/07 QC#2669
        // NMAL0190_ACMsg lineMsg = null;
        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        // lineMsg = bizMsg.A.no(i);
        // if (lineMsg.supdToMdseCd.equals(mdseCd)) {
        // break;
        // }
        // }
        SUPD_RELNTMsg insertMsg = new SUPD_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(insertMsg.supdRelnPk, NMAL0190CommonLogic.getSupdRelnSeq());
        ZYPEZDItemValueSetter.setValue(insertMsg.supdToMdseCd, lineMsg.supdToMdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.supdFromMdseCd, lineMsg.supdFromMdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.supdCratDt, lineMsg.supdRelnStageDt.getValue());
        S21FastTBLAccessor.create(insertMsg);
        String returnCd = insertMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCd)) {
            // ZZMM0001E=0,Data insert failure. [ TableName = @ , key
            // = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0001E, new String[] {MSG_PRM_TBL_SUPD_RELN, MSG_PRM_MDSE_CD, lineMsg.supdToMdseCd.getValue() });
            return false;
        }
        return true;
        // UPDATE END 2016/03/07 QC#2669
    }

    // UPDATE START 2016/03/07 QC#2669
    /**
     * Insert MDSE ITEM FLIP SET
     * - MDSE_CD : Item Number
     * - INCG_FLG: Y
     * 
     * @param bizMsg NMAL0190CMsg
     * @param mdseCd MDSE_CD
     * @param relnMdseCd RELN_MDSE_CD
     * @return result : Nomal (ture)
     */
    private boolean insertMdseItemFlipSet(NMAL0190CMsg bizMsg, String mdseCd, String relnMdseCd) {
        // private boolean insertMdseItemFlipSet(NMAL0190CMsg bizMsg,
        // NMAL0190_BCMsg lineMsg) {
        MDSE_ITEM_FLIP_SETTMsg insertMsg = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, getGlobalCompanyCode());
        // if (CHKBOX_ON_Y.equals(lineMsg.xxSupdFlg_FW.getValue())) {
        // ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd,
        // lineMsg.supdToMdseCd_B);
        // ZYPEZDItemValueSetter.setValue(insertMsg.relnMdseCd,
        // lineMsg.relnMdseCd);
        // }
        // if (CHKBOX_ON_Y.equals(lineMsg.xxSupdFlg_BW.getValue())) {
        // ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd,
        // lineMsg.relnMdseCd);
        // ZYPEZDItemValueSetter.setValue(insertMsg.relnMdseCd,
        // lineMsg.supdToMdseCd_B);
        // }
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.relnMdseCd, relnMdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseItemRelnTpCd, SQL_CONST_COMPATIBLE);
        S21FastTBLAccessor.create(insertMsg);
        String returnCd = insertMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCd)) {
            // ZZMM0001E=0,Data insert failure. [ TableName = @ , key
            // = @, value = @ ]
            bizMsg.setMessageInfo(ZZMM0001E, new String[] {MSG_PRM_TBL_MDSE_ITEM_FLIP_SET, MSG_PRM_MDSE_CD, mdseCd });
            return false;
        }
        return true;
    }

    /**
     * DELETE MDSE ITEM FLIP SET - MDSE_CD : Item Number
     * @param bizMsg NMAL0190CMsg
     * @param mdseCd MDSE_CD
     * @param relnMdseCd RELN_MDSE_CD
     * @return result : Nomal (ture)
     */
    private boolean deleteMdseItemFlipSet(NMAL0190CMsg bizMsg, String mdseCd, String relnMdseCd) {
        List<MDSE_ITEM_FLIP_SETTMsg> deleteMsgs = new ArrayList<MDSE_ITEM_FLIP_SETTMsg>();
        MDSE_ITEM_FLIP_SETTMsg targetMsg = new MDSE_ITEM_FLIP_SETTMsg();

        ZYPEZDItemValueSetter.setValue(targetMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(targetMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(targetMsg.relnMdseCd, relnMdseCd);
        ZYPEZDItemValueSetter.setValue(targetMsg.mdseItemRelnTpCd, SQL_CONST_COMPATIBLE);

        MDSE_ITEM_FLIP_SETTMsg deleteTMsg = (MDSE_ITEM_FLIP_SETTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(targetMsg);
        if (null != deleteTMsg) {
            deleteMsgs.add(deleteTMsg);
        }

        if (!deleteMsgs.isEmpty()) {
            int retCnt = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new MDSE_ITEM_FLIP_SETTMsg[0]));
            if (retCnt == deleteMsgs.size()) {
                return true;
            }
        }
        bizMsg.setMessageInfo(ZZMM0015E, new String[] {MSG_PRM_TBL_MDSE_ITEM_FLIP_SET, MSG_PRM_MDSE_CD, mdseCd });
        return true;
    }
    // UPDATE END 2016/03/07 QC#2669

    /**
     * Execute NPZC10701
     * @param lineMsg tblAMgs
     * @return
     */
    private NPZC107001PMsg executeNPZC1070(NMAL0190_ACMsg lineMsg) {
        NPZC107001PMsg apiParam = new NPZC107001PMsg();
        ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(apiParam.xxSupdMdseList.no(0).supdToMdseCd, lineMsg.supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(apiParam.xxSupdMdseList.no(0).supdFromMdseCd, lineMsg.supdFromMdseCd);
        apiParam.xxSupdMdseList.setValidCount(1);
        NPZC107001 apz10701Api = new NPZC107001();
        apz10701Api.execute(apiParam);

        return apiParam;
    }
}
