/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC107001;

import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.DB_PARAM_INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.DB_PARAM_SUPD_FRM_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.DB_PARAM_SUPD_TO_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.FLG_ON_N;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.FLG_ON_Y;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.NPZM0001E;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.PARAM_NAME_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC107001.constant.NPZC107001Constant.STATUS_CD_AVAILABLE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MRP_INFOTMsg;
import business.parts.NPZC107001PMsg;
import business.parts.NPZC107001_xxSupdMdseListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Business ID : NPZC107001：Planning Update by Supersede API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/28/2015   CSAI       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPZC107001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    /**
     * Constructor
     */
    public NPZC107001() {
        super();
        // initializes SSM Client.
        this.client = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NPZC107001PMsg
     */
    public void execute(NPZC107001PMsg pMsg) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        ZYPTableUtil.clear(pMsg.xxMsgIdList);

        if (!checkInputParam(msgMap)) {
            return;
        }

        for (int i = 0; i < pMsg.xxSupdMdseList.getValidCount(); i++) {
            NPZC107001_xxSupdMdseListPMsg xxSupdMdse = pMsg.xxSupdMdseList.no(i);

            // Param Check
            if (!ZYPCommonFunc.hasValue(xxSupdMdse.supdFromMdseCd) //
                    || !ZYPCommonFunc.hasValue(xxSupdMdse.supdToMdseCd)) {
                continue;
            }

            List<MRP_INFOTMsg> supdFrmMdseList = getSupersessionFromMDSECode(pMsg.glblCmpyCd.getValue(), xxSupdMdse.supdFromMdseCd.getValue());

            // Super session From Mdse Cd is identified uniquely.
            // Duplicate is error.
            if (supdFrmMdseList == null || supdFrmMdseList.isEmpty()) {
                continue;
            }

            for (int j = 0; j < supdFrmMdseList.size(); j++) {

                if (!updateOfSuprFrmMds(supdFrmMdseList.get(j), xxSupdMdse.supdToMdseCd.getValue(), pMsg.glblCmpyCd.getValue())) {
                    continue;
                }

                if (!hasExistSupdToMdseCd(pMsg.glblCmpyCd.getValue() //
                        , xxSupdMdse.supdToMdseCd.getValue() //
                        , supdFrmMdseList.get(j).invtyLocCd.getValue())) {
                    insertSupdToMdseCd(pMsg.glblCmpyCd.getValue(), xxSupdMdse.supdToMdseCd.getValue(), supdFrmMdseList.get(j));
                }
            }
        }

        msgMap.flush();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkInputParam(S21ApiMessageMap msgMap) {
        NPZC107001PMsg pMsg = (NPZC107001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NPZM0001E, new String[] {PARAM_NAME_GLBL_CMPY_CD });
        }

        msgMap.flush();

        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 'Super session From MDSE Code to MRP_INFO
     * @param glblCmpyCd String
     * @param supdFrmMdsCd String
     * @return
     */
    private List<MRP_INFOTMsg> getSupersessionFromMDSECode(String glblCmpyCd, String supdFrmMdsCd) {
        Map<String, Object> queryParams = new HashMap<String, Object>();

        queryParams.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(DB_PARAM_SUPD_FRM_MDSE_CD, supdFrmMdsCd);

        return (List<MRP_INFOTMsg>) this.client.queryObjectList("getSupersessionFromMDSECode", queryParams);
    }

    /**
     * 'Super session To MDSE Code to MRP_INFO
     * @param glblCmpyCd String
     * @param supdFrmMdsCd String
     * @param invtyLocCd String
     * @return
     */
    private List<BigDecimal> getSupersessionToMDSECode(String glblCmpyCd, String supdToMdsCd, String invtyLocCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(DB_PARAM_SUPD_TO_MDSE_CD, supdToMdsCd);
        queryParams.put(DB_PARAM_INVTY_LOC_CD, invtyLocCd);

        return (List<BigDecimal>) this.client.queryObjectList("getSupersessionToMDSECode", queryParams);
    }

    /**
     * update Super session From MDSE Code
     * @param suprFrmMds MRP_INFOTMsg
     * @param supdToMdseCd String
     * @param glblCmpyCd String
     * @return true:Update OK false:Update NG
     */
    private boolean updateOfSuprFrmMds(MRP_INFOTMsg suprFrmMds, String supdToMdseCd, String glblCmpyCd) {
        MRP_INFOTMsg mrpInfoT = new MRP_INFOTMsg();

        // lock
        ZYPEZDItemValueSetter.setValue(mrpInfoT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mrpInfoPk, suprFrmMds.mrpInfoPk);
        mrpInfoT = (MRP_INFOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(mrpInfoT);

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(mrpInfoT.getReturnCode())) {
            // Update
            mrpInfoT.mrpEnblFlg.setValue(FLG_ON_N);
            mrpInfoT.ropQty.setValue(BigDecimal.ZERO);
            mrpInfoT.maxInvtyQty.setValue(BigDecimal.ZERO);
            mrpInfoT.minOrdQty.setValue(BigDecimal.ZERO);
            mrpInfoT.incrOrdQty.setValue(BigDecimal.ZERO);

            mrpInfoT.supdFlg.setValue(FLG_ON_Y);
            mrpInfoT.mrpInfoCmntTxt.setValue("Supersession to " + supdToMdseCd);
            S21ApiTBLAccessor.update(mrpInfoT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mrpInfoT.getReturnCode())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * check exist SupdToMdseCd
     * @param glblCmpyCd String
     * @param supdToMdseCd String
     * @param invtyLocCd String
     * @return true:Exist false: Not exist
     */
    private boolean hasExistSupdToMdseCd(String glblCmpyCd, String supdToMdseCd, String invtyLocCd) {

        List<BigDecimal> mrpInfoPkList = getSupersessionToMDSECode(glblCmpyCd, supdToMdseCd, invtyLocCd);

        // Supersession To Mdse Cd is identified uniquely.
        // Duplicate is error.
        if (mrpInfoPkList == null || mrpInfoPkList.isEmpty()) {
            return false;
        } else if (mrpInfoPkList.size() > 1) {
            return false;
        }

        return true;
    }

    private void insertSupdToMdseCd(String glblCmpyCd, String supdToMdseCd, MRP_INFOTMsg supdFrmMdse) {
        MRP_INFOTMsg mrpInfoT = new MRP_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(mrpInfoT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mrpInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MRP_INFO_SQ"));
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mdseCd, supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.invtyLocCd, supdFrmMdse.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mrpPlnNm, supdFrmMdse.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rtlWhCatgCd, supdFrmMdse.rtlWhCatgCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rtlWhCd, supdFrmMdse.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rtlSwhCd, supdFrmMdse.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mrpEnblFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.mrpInfoRgtnStsCd, STATUS_CD_AVAILABLE);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.ropQty, supdFrmMdse.ropQty);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.maxInvtyQty, supdFrmMdse.maxInvtyQty);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.minOrdQty, supdFrmMdse.minOrdQty);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.incrOrdQty, supdFrmMdse.incrOrdQty);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.procrTpCd, supdFrmMdse.procrTpCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.srcLocCd, supdFrmMdse.srcLocCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.srcRtlWhCd, supdFrmMdse.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.srcRtlSwhCd, supdFrmMdse.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshDlyFlg, supdFrmMdse.rplshDlyFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshMonFlg, supdFrmMdse.rplshMonFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshTueFlg, supdFrmMdse.rplshTueFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshWedFlg, supdFrmMdse.rplshWedFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshThuFlg, supdFrmMdse.rplshThuFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.rplshFriFlg, supdFrmMdse.rplshFriFlg);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.origMrpInfoPk, supdFrmMdse.origMrpInfoPk);
        ZYPEZDItemValueSetter.setValue(mrpInfoT.supdFlg, FLG_ON_N);

        S21ApiTBLAccessor.create(mrpInfoT);

    }
}
