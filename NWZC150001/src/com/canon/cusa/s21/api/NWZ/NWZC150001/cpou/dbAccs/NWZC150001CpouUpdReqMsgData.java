/**
 * <pre>
 * CPO Update API module:
 * Insert / Delete: LB_REQ_INFO
 * Insert: MSG_TXT_DTL / MSG_TXT_DTL
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/10   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.remove;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;
import business.db.LB_REQ_INFOTMsg;
import business.db.LB_REQ_INFOTMsgArray;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;

public class NWZC150001CpouUpdReqMsgData {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    private static final NWZC150001CpouUpdReqMsgData MY_INSTANCE = new NWZC150001CpouUpdReqMsgData();
    // 2017/06/13 S21_NA#18869-2 Del End

    public NWZC150001CpouUpdReqMsgData() {
        super();
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdReqMsgData getInstance() {
//
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End
    /**
     * <pre>
     * The order number of label information registered at Save is rewritten in case of the Submit event.
     * Because a different respectively an order number is numbered in Save and Submit, label information at Save is registered again by the new order number numbered by Submit.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    public void changeLbReqInfoCpoNum(NWZC150001CpouBean cpoBean) {
        final String methodNm = "changeLbReqInfoCpoNum";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            String oldCpoOrdNum = cpoBean.getCpoOrdNum();
            String newCpoOrdNum = cpoBean.getReNumCpoOrdNum();

            if (!hasValue(oldCpoOrdNum)) {
                return;
            }

            final LB_REQ_INFOTMsg reqLbReqInfoTMsg = new LB_REQ_INFOTMsg();
            reqLbReqInfoTMsg.setSQLID("002");
            reqLbReqInfoTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            reqLbReqInfoTMsg.setConditionValue("cpoOrdNum01", oldCpoOrdNum);

            final LB_REQ_INFOTMsgArray resLbReqInfoTMsgArray = (LB_REQ_INFOTMsgArray) findByConditionForUpdate(reqLbReqInfoTMsg);

            for (int i = 0; i < resLbReqInfoTMsgArray.getValidCount(); i++) {
                // ***** [remove]
                remove(resLbReqInfoTMsgArray.no(i));
            }

            for (int i = 0; i < resLbReqInfoTMsgArray.getValidCount(); i++) {

                LB_REQ_INFOTMsg resLbReqInfoTMsg = resLbReqInfoTMsgArray.no(i);

                resLbReqInfoTMsg.cpoOrdNum.setValue(newCpoOrdNum);

                String cpoDtlLineNum = resLbReqInfoTMsg.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = resLbReqInfoTMsg.cpoDtlLineSubNum.getValue();

                for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {
                    String oldCpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
                    String oldCpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();
                    if (cpoDtlLineNum.equals(oldCpoDtlLineNum) && cpoDtlLineSubNum.equals(oldCpoDtlLineSubNum)) {
                        resLbReqInfoTMsg.cpoDtlLineNum.setValue(cpoDtlBean.getReNumCpoDtlLineNum());
                        resLbReqInfoTMsg.cpoDtlLineSubNum.setValue(cpoDtlBean.getReNumCpoDtlLineSubNum());
                        break;
                    }
                }

                // ***** [insert]
                insert(resLbReqInfoTMsg);

                if (!RTNCD_NORMAL.equals(resLbReqInfoTMsg.getReturnCode())) {
                    throw new S21AbendException("changeLableReqInfoOrdNum : Insert Error");
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * <pre>
     * The order number of message information registered at Save is rewritten in case of the Submit event.
     * Because a different respectively an order number is numbered in Save and Submit, message information at Save is registered again by the new order number numbered by Submit.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    public void changeMsgTxtDtlCpoNum(NWZC150001CpouBean cpoBean) {
        final String methodNm = "changeMsgTxtDtlCpoNum";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String oldCpoOrdNum = cpoBean.getCpoOrdNum();
            final String newCpoOrdNum = cpoBean.getReNumCpoOrdNum();

            if (!hasValue(oldCpoOrdNum)) {
                return;
            }

            final MSG_TXT_DTLTMsg reqMsgTxtDtlTMsg = new MSG_TXT_DTLTMsg();
            reqMsgTxtDtlTMsg.setSQLID("002");
            reqMsgTxtDtlTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            reqMsgTxtDtlTMsg.setConditionValue("cpoOrdNum01", oldCpoOrdNum);

            final MSG_TXT_DTLTMsgArray resMsgTxtDtlTMsgArray = (MSG_TXT_DTLTMsgArray) findByConditionForUpdate(reqMsgTxtDtlTMsg);

            for (int i = 0; i < resMsgTxtDtlTMsgArray.getValidCount(); i++) {
                // ***** [remove]
                remove(resMsgTxtDtlTMsgArray.no(i));
            }

            for (int i = 0; i < resMsgTxtDtlTMsgArray.getValidCount(); i++) {
                MSG_TXT_DTLTMsg resMsgTxtDtlTMsg = resMsgTxtDtlTMsgArray.no(i);
                resMsgTxtDtlTMsg.cpoOrdNum.setValue(newCpoOrdNum);
                // ***** [insert]
                insert(resMsgTxtDtlTMsg);
                if (!RTNCD_NORMAL.equals(resMsgTxtDtlTMsg.getReturnCode())) {
                    throw new S21AbendException("changeMsgTxtDtlOrdNum : Insert Error");
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * <pre>
     * Label information on the cancellation details is deleted.
     * If Save and the request type of details are the Cancel data, the order request type deletes label information.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    public void deleteLbReqInfo(NWZC150001CpouBean cpoBean) {
        final String methodNm = "deleteLbReqInfo";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

                if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {

                    final LB_REQ_INFOTMsg reqLbReqInfoTMsg = new LB_REQ_INFOTMsg();
                    reqLbReqInfoTMsg.setSQLID("003");
                    reqLbReqInfoTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                    reqLbReqInfoTMsg.setConditionValue("cpoOrdNum01", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
                    reqLbReqInfoTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlBean.getCpoDtlLineNum());
                    reqLbReqInfoTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlBean.getCpoDtlLineSubNum());

                    final LB_REQ_INFOTMsgArray resLbReqInfoTMsgArray = (LB_REQ_INFOTMsgArray) findByConditionForUpdate(reqLbReqInfoTMsg);

                    for (int i = 0; i < resLbReqInfoTMsgArray.getValidCount(); i++) {
                        // ***** [remove]
                        remove(resLbReqInfoTMsgArray.no(i));
                    }
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * <pre>
     * Message information on the order is deleted.
     * If Save and request types of details are the Cancel all data, the order request type deletes message information.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    public void deleteMsgTxtDtl(NWZC150001CpouBean cpoBean) {
        final String methodNm = "deleteMsgTxtDtl";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // is all cancelled line?
            boolean isAllCancelledLine = true;
            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {
                if (!NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                    isAllCancelledLine = false;
                    break;
                }
            }

            if (isAllCancelledLine) {

                final MSG_TXT_DTLTMsg reqMsgTxtDtlTMsg = new MSG_TXT_DTLTMsg();
                reqMsgTxtDtlTMsg.setSQLID("002");
                reqMsgTxtDtlTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                reqMsgTxtDtlTMsg.setConditionValue("cpoOrdNum01", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));

                final MSG_TXT_DTLTMsgArray resMsgTxtDtlTMsgArray = (MSG_TXT_DTLTMsgArray) findByConditionForUpdate(reqMsgTxtDtlTMsg);

                for (int i = 0; i < resMsgTxtDtlTMsgArray.getValidCount(); i++) {
                    // ***** [remove]
                    remove(resMsgTxtDtlTMsgArray.no(i));
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }
}
