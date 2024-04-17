/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.blap.NWAL2200.NWAL2200Query;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.parts.NWZC226001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC226001.NWZC226001;
import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NWAL2200CommonLogicForOrderCreation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/20   Fujitsu         T.Ishii         Create          N/A
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 *</pre>
 */
public class NWAL2200CommonLogicForOrderCreation {

    /**
     * validate
     * @param bizMsg NWAL2200CMsg
     * @return List<DS_IMPT_ORD_ERRTMsg>
     */
    public static List<DS_IMPT_ORD_ERRTMsg> validate(NWAL2200CMsg bizMsg) {

        return callOrderImportApi(bizMsg, NWZC226001Constant.MODE_VALIDATE);
    }

    // 2018/01/23 QC#18798 Mod Start
    /**
     * create order
     * @param bizMsg NWAL2200CMsg
     * @return List<DS_IMPT_ORD_ERRTMsg>
     */
    public static List<DS_IMPT_ORD_ERRTMsg> createOrder(NWAL2200CMsg bizMsg) {

        return callOrderImportApi(bizMsg, NWZC226001Constant.MODE_CREATE_ORDER);
    }

    /**
     * call Order Import Api
     * @param bizMsg NWAL2200CMsg
     * @param xxModeCd String
     * @return
     */
    private static List<DS_IMPT_ORD_ERRTMsg> callOrderImportApi(NWAL2200CMsg bizMsg, String xxModeCd) {

        List<DS_IMPT_ORD_ERRTMsg> errorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
        if (!CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {

            errorList = execOrderImportApi(bizMsg, xxModeCd, null);

        } else {

            List<DS_IMPT_ORD_ERRTMsg> configErrorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
            // Line Config
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                if (IMPT_STS.SUCCESS.equals(bizMsg.A.no(i).emsdImptStsCd_LC.getValue())) {
                    continue;
                }
                configErrorList = execOrderImportApi(bizMsg, xxModeCd, bizMsg.A.no(i).dsImptOrdConfigPk_LC.getValue());
                if (configErrorList.size() > 0) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).emsdImptStsCd_LC, IMPT_STS.ERROR);
                    // RMA
                    S21SsmEZDResult getRmaConfigPkList = NWAL2200Query.getInstance().getRmaConfigPkList(bizMsg, bizMsg.A.no(i).dsImptOrdConfigPk_LC.getValue(), CONFIG_CATG.INBOUND);
                    if (getRmaConfigPkList.isCodeNormal()) {
                        List<BigDecimal> rmaConfigPkList = (List<BigDecimal>) getRmaConfigPkList.getResultObject();
                        for (BigDecimal rmaConfigPk : rmaConfigPkList) {
                            for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                                if (rmaConfigPk.equals(bizMsg.C.no(j).dsImptOrdConfigPk_RC.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(j).emsdImptStsCd_RC, IMPT_STS.ERROR);
                                    break;
                                }

                            }
                        }
                    }
                    if (NWZC226001Constant.MODE_CREATE_ORDER.equals(xxModeCd)) {
                        EZDConnectionMgr.getInstance().rollback();
                    }
                } else {

                    if (NWZC226001Constant.MODE_VALIDATE.equals(xxModeCd)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).emsdImptStsCd_LC, IMPT_STS.VALIDATED);
                        // RMA
                        S21SsmEZDResult getRmaConfigPkList = NWAL2200Query.getInstance().getRmaConfigPkList(bizMsg, bizMsg.A.no(i).dsImptOrdConfigPk_LC.getValue(), CONFIG_CATG.INBOUND);
                        if (getRmaConfigPkList.isCodeNormal()) {
                            List<BigDecimal> rmaConfigPkList = (List<BigDecimal>) getRmaConfigPkList.getResultObject();
                            for (BigDecimal rmaConfigPk : rmaConfigPkList) {
                                for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                                    if (rmaConfigPk.equals(bizMsg.C.no(j).dsImptOrdConfigPk_RC.getValue())) {
                                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(j).emsdImptStsCd_RC, IMPT_STS.VALIDATED);
                                        break;
                                    }

                                }
                            }
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).emsdImptStsCd_LC, IMPT_STS.SUCCESS);
                        // RMA
                        S21SsmEZDResult getRmaConfigPkList = NWAL2200Query.getInstance().getRmaConfigPkList(bizMsg, bizMsg.A.no(i).dsImptOrdConfigPk_LC.getValue(), CONFIG_CATG.INBOUND);
                        if (getRmaConfigPkList.isCodeNormal()) {
                            List<BigDecimal> rmaConfigPkList = (List<BigDecimal>) getRmaConfigPkList.getResultObject();
                            for (BigDecimal rmaConfigPk : rmaConfigPkList) {
                                for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                                    if (rmaConfigPk.equals(bizMsg.C.no(j).dsImptOrdConfigPk_RC.getValue())) {
                                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(j).emsdImptStsCd_RC, IMPT_STS.SUCCESS);
                                        break;
                                    }

                                }
                            }
                        }
                        EZDConnectionMgr.getInstance().commit();
                    }
                }
                errorList.addAll(configErrorList);
            }

//            // RMA
//            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//
//                if (IMPT_STS.SUCCESS.equals(bizMsg.C.no(i).emsdImptStsCd_RC.getValue())) {
//                    continue;
//                }
//                configErrorList = execOrderImportApi(bizMsg, xxModeCd, bizMsg.C.no(i).dsImptOrdConfigPk_RC.getValue());
//                if (configErrorList.size() > 0) {
//
//                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).emsdImptStsCd_RC, IMPT_STS.ERROR);
//                    if (NWZC226001Constant.MODE_CREATE_ORDER.equals(xxModeCd)) {
//                        EZDConnectionMgr.getInstance().rollback();
//                    }
//                } else {
//
//                    if (NWZC226001Constant.MODE_VALIDATE.equals(xxModeCd)) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).emsdImptStsCd_RC, IMPT_STS.VALIDATED);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).emsdImptStsCd_RC, IMPT_STS.SUCCESS);
//                        EZDConnectionMgr.getInstance().commit();
//                    }
//                }
//                errorList.addAll(configErrorList);
//            }
        }
        return errorList;
    }

    /**
     * execute Order Import Api
     * @param bizMsg NWAL2200CMsg
     * @param xxModeCd String
     * @param dsImptOrdPk BigDecimal
     * @return
     */
    private static List<DS_IMPT_ORD_ERRTMsg> execOrderImportApi(NWAL2200CMsg bizMsg, String xxModeCd, BigDecimal dsImptOrdPk) {

        List<DS_IMPT_ORD_ERRTMsg> errorList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();

        NWZC226001PMsg param = new NWZC226001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(param.dsImptOrdPk, bizMsg.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, xxModeCd);
        if (ZYPCommonFunc.hasValue(dsImptOrdPk)) {
            ZYPEZDItemValueSetter.setValue(param.dsImptOrdConfigPk, dsImptOrdPk);
        }

        new NWZC226001().execute(param, ONBATCH_TYPE.ONLINE);

        for (int i = 0; i < param.xxMsgPrmList.getValidCount(); i++) {

            DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
            EZDMsg.copy(param.xxMsgPrmList.no(i), null, dsImptOrdErr, null);
            errorList.add(dsImptOrdErr);
        }

        if (param.xxMsgPrmList.getValidCount() == 0) {

            if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {

                DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, bizMsg.dsImptOrdPk);
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrMsgId, NWZC225001Constant.NWZM2200E);
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(NWZC225001Constant.NWZM2200E));

                errorList.add(dsImptOrdErr);
            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, param.cpoOrdNum);
            }
        }

        return errorList;
    }
    // 2018/01/23 QC#18798 Mod End
}
