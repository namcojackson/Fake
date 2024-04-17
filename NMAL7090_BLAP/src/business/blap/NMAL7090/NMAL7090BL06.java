/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7090;

import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_CMN_DELETE;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_CMN_SUBMIT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM0049E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM0833E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8121E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8175E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8216E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8426E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8427E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8428E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8429E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8431E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.ZZM9000E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.ZZM9037E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.ZZMM0001E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7090.common.NMAL7090CommonLogic;
import business.db.PRC_LIST_EQUIP_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8180
 * 2017/12/19   Fujitsu         Mz.Takahashi    Update          QC#22760
 *</pre>
 */
public class NMAL7090BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        //     

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;
            NMAL7090SMsg glblMsg = (NMAL7090SMsg) sMsg;

            if (EVENT_NM_NMAL7090_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL7090_Submit(bizMsg, glblMsg);

                // UPDATE START 2016/05/11 QC#8180
            } else if (EVENT_NM_NMAL7090_CMN_DELETE.equals(screenAplID)) {
                doProcess_NMAL7090_Delete(bizMsg, glblMsg);
                // UPDATE START 2016/05/11 QC#8180
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL7090_Submit(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        NMAL7090CommonLogic.copyBCMsgToBSMsg(cMsg, sMsg);

        boolean errFlg = false;
        int indx = 0;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NMAL7090_BSMsg line = sMsg.B.no(i);

            // MANDATORY CHECK
            if (!ZYPCommonFunc.hasValue(line.oldMdseCd_B)) {
                // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                // cMsg.B.no(indx).oldMdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {"Old Item Code" });
                sMsg.B.no(i).oldMdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {"Old Item Code" });

                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(line.newMdseCd_B)) {
                // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                // cMsg.B.no(indx).newMdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {"New Item Code" });
                sMsg.B.no(i).newMdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {"New Item Code" });
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(line.prcListTpCd_BS)) {
                // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                // cMsg.B.no(indx).prcListTpCd_BS.setErrorInfo(1, ZZM9000E, new String[] {"Update Price List Type" });
                sMsg.B.no(i).prcListTpCd_BS.setErrorInfo(1, ZZM9000E, new String[] {"Update Price List Type" });
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(line.prcListActTpCd_BS)) {
                // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                // cMsg.B.no(indx).prcListActTpCd_BS.setErrorInfo(1, ZZM9000E, new String[] {"Update Price List Action" });
                sMsg.B.no(i).prcListActTpCd_BS.setErrorInfo(1, ZZM9000E, new String[] {"Update Price List Action" });
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            // when price list action type is EXCEPT ALL or SPECIFIC PRICE LIST ONLY, Price List IDs are necessary. NMAM0049E
            if (PRC_LIST_ACT_TP.EXCEPT_ALL.equals(line.prcListActTpCd_BS.getValue()) || PRC_LIST_ACT_TP.SPECIFIC_PRICE_LIST_ONLY.equals(line.prcListActTpCd_BS.getValue())) {
                if (!ZYPCommonFunc.hasValue(line.prcListsDescTxt_B)) {
                    // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                    // cMsg.B.no(indx).prcListsDescTxt_B.setErrorInfo(1, NMAM0049E, new String[] {"Update Price List Action", "Price List ID" });
                    // cMsg.setMessageInfo(NMAM0049E, new String[] {"Update Price List Action", "Price List ID" });
                    sMsg.B.no(i).prcListsDescTxt_B.setErrorInfo(1, NMAM0049E, new String[] {"Update Price List Action", "Price List ID" });
                    // return;
                    if (!errFlg) {
                        indx = i;
                    }
                    errFlg = true;
                }
            }

            // when Old Item Code and New Item Code are the same,
            if (line.oldMdseCd_B.getValue().equals(line.newMdseCd_B.getValue())) {
                // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                // cMsg.B.no(indx).oldMdseCd_B.setErrorInfo(1, NMAM0833E, new String[] {"Old Item", "New Item" });
                // cMsg.B.no(indx).newMdseCd_B.setErrorInfo(1, NMAM0833E, new String[] {"Old Item", "New Item" });
                sMsg.B.no(i).oldMdseCd_B.setErrorInfo(1, NMAM0833E, new String[] {"Old Item", "New Item" });
                sMsg.B.no(i).newMdseCd_B.setErrorInfo(1, NMAM0833E, new String[] {"Old Item", "New Item" });
                // cMsg.setMessageInfo(NMAM0833E);
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            // Price List Type X Price List IDs combination check NMAM8426E
            if (ZYPCommonFunc.hasValue(line.prcListsDescTxt_B)) {
                if (NMAL7090CommonLogic.isExistPrcListTpCd(line)) {
                    // int indx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                    // cMsg.B.no(indx).prcListsDescTxt_B.setErrorInfo(1, NMAM8426E);
                    sMsg.B.no(i).prcListsDescTxt_B.setErrorInfo(1, NMAM8426E);
                    // cMsg.setMessageInfo(NMAM8426E);
                    // return;
                    if (!errFlg) {
                        indx = i;
                    }
                    errFlg = true;
                }
            }

            // when retain original price flag is checked, New Price is not necessary.
            if (ZYPConstant.FLG_ON_Y.equals(line.retanOrigPrcFlg_B.getValue()) && ZYPCommonFunc.hasValue(line.newPrcAmt_B)) {
                // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);

                // cMsg.B.no(pageIndx).newPrcAmt_B.setErrorInfo(1, NMAM8427E);
                sMsg.B.no(i).newPrcAmt_B.setErrorInfo(1, NMAM8427E);
                // cMsg.setMessageInfo(NMAM8427E);
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }
            // when retain original price flag is not checked, New Price is necessary. NMAM8121E
            if (ZYPConstant.FLG_OFF_N.equals(line.retanOrigPrcFlg_B.getValue()) && !ZYPCommonFunc.hasValue(line.newPrcAmt_B)) {
                // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);

                // cMsg.B.no(pageIndx).newPrcAmt_B.setErrorInfo(1, NMAM8428E);
                sMsg.B.no(i).newPrcAmt_B.setErrorInfo(1, NMAM8428E);
                // cMsg.setMessageInfo(NMAM8428E);
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            // when discard flag is on
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).rqstDscdFlg_B.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).submtFlg_B.getValue())) {

                int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);

                // cMsg.B.no(pageIndx).submtFlg_B.setErrorInfo(1, NMAM8429E);
                // cMsg.B.no(pageIndx).rqstDscdFlg_B.setErrorInfo(1, NMAM8429E);
                sMsg.B.no(i).submtFlg_B.setErrorInfo(1, NMAM8429E);
                sMsg.B.no(i).rqstDscdFlg_B.setErrorInfo(1, NMAM8429E);
                // cMsg.setMessageInfo(NMAM8429E);
                // return;
                if (!errFlg) {
                    indx = i;
                }
                errFlg = true;
            }

            // UPDATE START 2017/12/19 QC#22760
            Boolean isErr = false;

            // OLD ITEM MASTER CHECK
            if (!NMAL7090CommonLogic.isExistInOrdTakeMdse(line.oldMdseCd_B.getValue())) {
                if (line.oldMdseCd_B.getValue().length() >= 8) {
                    if (NMAL7090CommonLogic.isExistInOrdTakeMdse(line.oldMdseCd_B.getValue().substring(0, 8))) {
                        // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                        // cMsg.B.no(pageIndx).oldMdseCd_B.setErrorInfo(1, NMAM8216E);
                        sMsg.B.no(i).oldMdseCd_B.setErrorInfo(1, NMAM8216E);
                        // cMsg.setMessageInfo(NMAM8216E);
                        // return;
                        if (!errFlg) {
                            indx = i;
                        }
                        errFlg = true;
                        isErr = true;
                    }
                } 

                if (isErr == false){
                    if (!NMAL7090CommonLogic.isExistInMdse(line.oldMdseCd_B.getValue())) {
                        // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                        // cMsg.B.no(pageIndx).oldMdseCd_B.setErrorInfo(1, NMAM8121E, new String[] {line.oldMdseCd_B.getValue() });
                        sMsg.B.no(i).oldMdseCd_B.setErrorInfo(1, NMAM8121E, new String[] {line.oldMdseCd_B.getValue() });
                        // cMsg.setMessageInfo(NMAM8121E, new String[] {line.oldMdseCd_B.getValue() });
                        // return;
                        if (!errFlg) {
                            indx = i;
                        }
                        errFlg = true;
                    }
                }
            }

            // NEW ITEM MASTER CHECK
            isErr = false;

            if (!NMAL7090CommonLogic.isExistInOrdTakeMdse(line.newMdseCd_B.getValue())) {
                if (line.newMdseCd_B.getValue().length() >= 8) {
                    if (NMAL7090CommonLogic.isExistInOrdTakeMdse(line.newMdseCd_B.getValue().substring(0, 8))) {
                        // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                        // cMsg.B.no(pageIndx).newMdseCd_B.setErrorInfo(1, NMAM8216E);
                        sMsg.B.no(i).newMdseCd_B.setErrorInfo(1, NMAM8216E);
                        // cMsg.setMessageInfo(NMAM8216E);
                        // return;
                        if (!errFlg) {
                            indx = i;
                        }
                        errFlg = true;
                        isErr = true;
                    }
                } 

                if(isErr == false){
                    if (!NMAL7090CommonLogic.isExistInMdse(line.newMdseCd_B.getValue())) {
                        // int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                        // cMsg.B.no(pageIndx).newMdseCd_B.setErrorInfo(1, NMAM8121E, new String[] {line.newMdseCd_B.getValue() });
                        sMsg.B.no(i).newMdseCd_B.setErrorInfo(1, NMAM8121E, new String[] {line.newMdseCd_B.getValue() });
                        // cMsg.setMessageInfo(NMAM8121E, new String[] {line.newMdseCd_B.getValue() });
                        // return;
                        if (!errFlg) {
                            indx = i;
                        }
                        errFlg = true;
                    }
                }
            }
            // UPDATE END 2017/12/19 QC#22760
        }

        if (errFlg) {
            int pageCnt = indx / cMsg.B.length();
            int pageIndx = indx % cMsg.B.length();

            int pageFrom = pageCnt * cMsg.B.length();
            int j = pageFrom;
            for (; j < pageFrom + cMsg.B.length(); j++) {
                if (j < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.no(j), null, cMsg.B.no(j - pageFrom), null);
                } else {
                    break;
                }
            }
            cMsg.B.setValidCount(j - pageFrom);

            cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
            cMsg.setMessageInfo(ZZM9037E);
            return;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NMAL7090_BSMsg line = sMsg.B.no(i);

            // UPDATE OR INSERT
            PRC_LIST_EQUIP_RQSTTMsg tMsg = new PRC_LIST_EQUIP_RQSTTMsg();
            if (ZYPCommonFunc.hasValue(line.prcListEquipRqstPk_B)) {
                tMsg = NMAL7090CommonLogic.findPrcListEquipRqst(getGlobalCompanyCode(), line.prcListEquipRqstPk_B.getValue());
                NMAL7090CommonLogic.setPrcListEquipRqst(line, tMsg);
                ZYPEZDItemValueSetter.setValue(tMsg.lastUpdDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                ZYPEZDItemValueSetter.setValue(tMsg.lastUpdUsrId, getContextUserInfo().getUserId());
                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                    // NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value= @ ]
                    cMsg.setMessageInfo(NMAM8175E, new String[] {"PRC_LIST_EQUIP_RQST", "PRC_LIST_EQUIP_RQST_PK", line.prcListEquipRqstPk_B.getValue().toString() });
                    return;
                }

            } else {
                NMAL7090CommonLogic.setPrcListEquipRqst(line, tMsg);
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_RQST_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipRqstPk, pk);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.cratDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                ZYPEZDItemValueSetter.setValue(tMsg.cratUsrId, getContextUserInfo().getUserId());
                ZYPEZDItemValueSetter.setValue(tMsg.lastUpdDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                ZYPEZDItemValueSetter.setValue(tMsg.lastUpdUsrId, getContextUserInfo().getUserId());

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);
                    // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                    cMsg.setMessageInfo(ZZMM0001E, new String[] {"PRC_LIST_EQUIP_RQST", "PRC_LIST_EQUIP_RQST_PK", pk.toString() });
                    return;
                }
            }
        }
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    // UPDATE START 2016/05/11 QC#8180
    private void doProcess_NMAL7090_Delete(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        NMAL7090CommonLogic.copyBCMsgToBSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "rqstDscdFlg_B", ZYPConstant.FLG_ON_Y);
        if (selectedRows.size() == 0) {
            cMsg.setMessageInfo(NMAM8431E);
            return;
        }
        List<Integer> deleteIndex = new ArrayList<Integer>();
        for (int i : selectedRows) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).submtFlg_B.getValue())) {
                int pageIndx = NMAL7090CommonLogic.errorPage(cMsg, sMsg, i);

                cMsg.B.no(pageIndx).submtFlg_B.setErrorInfo(1, NMAM8429E);
                cMsg.B.no(pageIndx).rqstDscdFlg_B.setErrorInfo(1, NMAM8429E);

                return;
            }

            BigDecimal pk = sMsg.B.no(i).prcListEquipRqstPk_B.getValue();

            if (!ZYPCommonFunc.hasValue(pk)) {
                deleteIndex.add(i);
            } else {
                PRC_LIST_EQUIP_RQSTTMsg deleteMsg = NMAL7090CommonLogic.findPrcListEquipRqst(getGlobalCompanyCode(), pk);
                EZDTBLAccessor.logicalRemove(deleteMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(deleteMsg.getReturnCode())) {
                    // NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value= @ ]
                    cMsg.setMessageInfo(NMAM8175E, new String[] {"PRC_LIST_EQUIP_RQST", "PRC_LIST_EQUIP_RQST_PK", pk.toString() });
                } else {
                    deleteIndex.add(i);
                }
            }
        }
        ZYPTableUtil.deleteRows(sMsg.B, deleteIndex);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxNum_B, BigDecimal.valueOf(i));
        }

        // NMAL7090CommonLogic.copySMsgBtoSMsgX(sMsg);

        // Copy 1 page Data(SMsg -> CMsg)
        cMsg.B.clear();
        int i = 0;
        for (; i < sMsg.B.getValidCount(); i++) {
            if (i == cMsg.B.length()) {
                break;
            }
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

        }
        cMsg.B.setValidCount(i);

        // Setting Next Page
        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete" });
    }
    // UPDATE START 2016/05/11 QC#8180
}
