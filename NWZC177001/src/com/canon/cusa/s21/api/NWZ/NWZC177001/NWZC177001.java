package com.canon.cusa.s21.api.NWZ.NWZC177001;

import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.DOC_TP_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM0912E;
//import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM1756E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM1922E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM2047E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM2048E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM2049E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZM2050E;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.PROC_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.SUB_SYS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_CREDIT_REVIEW;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_LEASE_BUYOUT;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_PRFT_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_SPLY_ABUSE_CTAC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_SPLY_ABUSE_PEND_ORD;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_SPLY_ABUSE_SPLY_ENFORCE;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_SPLY_ABUSE_YEILD;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_VALID_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WFID_MAN_PRC_APVL;
//import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.WF_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.FIXED_REJECT_COMMENT;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZC1770_WF_REJ_CMNT;

import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_VALID_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_PRFT_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_SPLY_ABUSE_YEILD;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_SPLY_ABUSE_PEND_ORD;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_SPLY_ABUSE_CTAC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_SPLY_ABUSE_SPLY_ENFORCE;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_CREDIT_REVIEW;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_LEASE_BUYOUT;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.EVENTID_CANC_MAN_PRC_APVL;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.FORCE_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.AUTO;

import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;

import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC177001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC164001.NWZC164001TokenObject;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfCancelEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfInvalidStateException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.history.impl.S21NwfHistUserAction;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * <pre>
 * NWZC1770 Order Header Workflow Cancel API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/24   SRAA            K.Aratani       Update          QC4665
 * 2016/02/29   Fujitsu         S.Takami        Update          S21_NA#4291
 * 2016/11/07   Fujitsu         S.Takami        Update          S21_NA#14186 (Replace Exception Error Message ID)
 * 2018/08/01   Fujitsu         Mz.Takahashi    Update          S21_NA#27087
 * 2019/01/18   Fujitsu         S.Takami        Update          S21_NA#28773
 * 2019/12/14   Fujitsu         Mz.Takahashi    Update          QC#53588
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWZC177001 extends S21ApiCommonBase implements S21NwfCancelEvent<S21NwfContext, S21NwfToken> {

    // 2018/08/01 S21_NA#27087 Add Start
    private String rejectAction = "";
    // 2018/08/01 S21_NA#27087 Add End

    /**
     * constructor
     */
    public NWZC177001() {
        super();

        // 2018/08/01 S21_NA#27087 Add Start
        rejectAction = S21NwfHistUserAction.EVENT.LEV_REJECT.toString().replaceFirst("LEV_", "");
        // 2018/08/01 S21_NA#27087 Add End
    }

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /**
     * execute
     * @param pMsg NWZC177001PMsg
     * @param onBatch ONBATCH_TYPE
     */
    public void execute(final NWZC177001PMsg pMsg, final ONBATCH_TYPE onBatch) {
        this.msgMap = new S21ApiMessageMap(pMsg);

        // Order number input param check
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM0912E);
            msgMap.flush();
            return;
        }

        // Get TokenObject of WorkFlow
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        NWZC164001TokenObject tokenObj = new NWZC164001TokenObject();
        tokenObj.setAttribute1(pMsg.cpoOrdNum.getValue());

        // Cancel Workflow process
        // 2016/02/29 S21_NA#4291 Mod Start
        // if (!cancelProcess(factory, context, tokenObj)) {
        //     msgMap.addXxMsgId(NWZM1756E);
        // }
        String mdsId = cancelProcess(factory, context, tokenObj, pMsg.xxModeCd.getValue());
        if (!"".equals(mdsId)) {
            msgMap.addXxMsgId(mdsId);
        }
        // 2016/02/29 S21_NA#4291 Mod Start

        msgMap.flush();
    }

    /**
     * cancel
     * @param arg0 String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException S21NwfBizException
     */
    @Override
    public void cancel(String arg0, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        // Get TokenObject of WorkFlow
        NWZC164001TokenObject tokenObj = (NWZC164001TokenObject) token.getTokenObj();

        // if cancel flag is on, do nothing
        if (ZYPConstant.FLG_ON_Y.equals(tokenObj.getCondStr10())) {
            return;
        }

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();

        // Order number input param check
        if (!ZYPCommonFunc.hasValue(tokenObj.getAttribute1())) {
            throw new S21NwfBizException(NWZM0912E, null);
        }

        // Cancel Workflow process
        // 2016/02/29 S21_NA#4291 Mod Start
        // if (!cancelProcess(factory, context, tokenObj)) {
        //     throw new S21NwfBizException(NWZM1756E, null);
        // }
        String msdId = cancelProcess(factory, context, tokenObj, FORCE_CANCEL);
        if (!"".equals(msdId)) {
            throw new S21NwfBizException(msdId, null);
        }
        // 2016/02/29 S21_NA#4291 Mod End
    }

    // 2019/12/14 QC#53588 Add Start
    private String toModeCd(S21NwfProcess proc, boolean allOutBoundCancelFlg, boolean allInBoudCancelFlg){
        String ret = CANCEL;
        String wfId = proc.getProcessName();

        if (WFID_CREDIT_REVIEW.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_VALID_WF.equals(wfId)){
            if (allOutBoundCancelFlg && allInBoudCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_PRFT_WF.equals(wfId)){
            if (allOutBoundCancelFlg && allInBoudCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_SPLY_ABUSE_YEILD.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_SPLY_ABUSE_CTAC_STS.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_SPLY_ABUSE_PEND_ORD.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            }
        } else if (WFID_LEASE_BUYOUT.equals(wfId)){
            if (allOutBoundCancelFlg){
                ret = FORCE_CANCEL;
            } else {
                NWZC164001TokenObject token = (NWZC164001TokenObject)proc.getToken().getTokenObj();
                String targetLineNum =  token.getDtlAttrb6();

                if (S21StringUtil.isNotEmpty(targetLineNum)){
                    String[] list = targetLineNum.split("\\.");

                    if (list.length >= 2){
                        if (!NWXC150001DsCheck.isActiveLine(token.getGlblCmpyCd(), token.getCpoOrdNum(), list[0], list[1])){
                            ret = FORCE_CANCEL;
                        }
                    }
                }
                
            }
        }

        return ret;

    }
    // 2019/12/14 QC#53588 Add End

    /**
     * cancelProcess
     * @param arg0 String
     * @param context S21NwfContext
     * @param token NWZC164001TokenObject
     */
    /**
     * <pre>
     * cancelProcess
     * @param factory
     * @param context
     * @param tokenObj
     * @return Error message id. if normal end, this method return ""
     * </pre>
     */
//    public boolean cancelProcess(S21NwfContextFactory factory, S21NwfContext context, NWZC164001TokenObject tokenObj) { 2016/02/29 S21_NA#4291 Mod Interface
    public String cancelProcess(S21NwfContextFactory factory, S21NwfContext context, NWZC164001TokenObject tokenObj, String xxModeCd) {
        try {
            context = factory.getContex();

            // 2018/08/01 S21_NA#27087 Del Start
            //ArrayList<String> array = new ArrayList<String>();
            // 2018/08/01 S21_NA#27087 Del End
            NWZC164001TokenObject token = new  NWZC164001TokenObject();
            String wDocId = null;
            
            // 2018/08/01 S21_NA#27087 Del Start
            //array = setWorkFlowId(array);
            // Cancel all workflow linked to order number
            //for (int idx = 0; idx < array.size(); idx++) {
            // 2018/08/01 S21_NA#27087 Del End
                List<S21NwfProcess> processes = context.getProcessFindByGroup(tokenObj.getAttribute1());

                // 2019/12/14 QC#53588 Add Start
                boolean chekLineCancelStsFlg = false;
                boolean allOutBoundCancelFlg = false;
                boolean allInBoudCancelFlg = false;
                
                // 2019/12/14 QC#53588 Add End

                // add start 2023/04/25 QC#61337
                String trgetProcess = tokenObj.getCondStr9();
                // add end 2023/04/25 QC#61337

                for (S21NwfProcess proc : processes) {

                    // add start 2023/04/25 QC#61337
                    if (ZYPCommonFunc.hasValue(trgetProcess) //
                            && !S21StringUtil.isEquals(trgetProcess, proc.getProcessName())) {
                        continue;
                    }
                    // add end 2023/04/25 QC#61337

                    if (proc.isActive()) {
                        String modeCd = xxModeCd;

                        // 2019/12/14 QC#53588 Add Start
                        if (!chekLineCancelStsFlg){
                            token = (NWZC164001TokenObject) proc.getToken().getTokenObj();
                            allOutBoundCancelFlg = NWXC150001DsCheck.checkAllLineCancelledOrClosed(token.getGlblCmpyCd(), tokenObj.getAttribute1());
                            allInBoudCancelFlg = NWXC150001DsCheck.checkAllRmaLineCancelledOrClosed(token.getGlblCmpyCd(), tokenObj.getAttribute1());
                            chekLineCancelStsFlg = true;
                        }

                        if (AUTO.equals(xxModeCd)){
                            modeCd = toModeCd(proc, allOutBoundCancelFlg, allInBoudCancelFlg);
                        }
                        // 2019/12/14 QC#53588 Add End

                        // 2019/12/14 QC#53588 Mod Start
                        if (CANCEL.equals(modeCd)) { 
                        // 2018/08/01 S21_NA#27087 Add Start
                        //if (CANCEL.equals(xxModeCd)) {
                        // 2019/12/14 QC#53588 Mod End
                            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess)context.getProcessForBiz(proc.getProcessId());
                            List<S21NwfUtilBizHistWorkItem> hist = p.getHistory();

                            if ((hist != null) && !hist.isEmpty()) {
                                S21NwfUtilBizHistWorkItem wi = hist.get(hist.size() - 1);

                                if (rejectAction.equals(wi.getActName())){
                                    continue;
                                } 
                            }
                            // 2019/01/18 S21_NA#28773 Add Start
                            S21NwfToken rejectToken = proc.getToken();
                            S21NwfTokenObj rejectTokenObj = rejectToken.getTokenObj();
                            String rejectComment = getRejectComment(rejectTokenObj.getGlblCmpyCd());
                            if (ZYPCommonFunc.hasValue(rejectComment)) {
                                rejectTokenObj.setComment(rejectComment);
                            }
                            rejectToken.setTokenObj(rejectTokenObj);
                            // add start 2023/04/25 QC#61337
                            ((NWZC164001TokenObject) rejectTokenObj).setCondStr9("CANCEL");
                            // add end 2023/04/25 QC#61337
                            rejectToken.signal(SIGNAL_REJECT);
                            continue;
                            // 2019/01/18 S21_NA#28773 Add End
                        }
                        // 2018/08/01 S21_NA#27087 Add End

                        // set cancel flag on
                        token = (NWZC164001TokenObject) proc.getToken().getTokenObj();
                        token.setCondStr10(ZYPConstant.FLG_ON_Y);

                        // cancel
                        proc.getToken().cancel();

                        // insert BIZ_PROC_LOG
                        wDocId = getWDocId(proc.getProcessName());
                        insertBizProcLog(tokenObj.getAttribute1(), wDocId);

                    }
                }
            // 2018/08/01 S21_NA#27087 Del Start
            //}
            // 2018/08/01 S21_NA#27087 Del End

        } catch (S21NwfAuthException e) { // 2016/02/29 S21_NA#4291 Add Start
            return NWZM1922E;
        } catch (S21NwfBizException e) {
            return NWZM2047E; // 2016/11/07 S21_NA#14186 Replace Message ID
        } catch (S21NwfInvalidStateException e) {
            return NWZM2048E; // 2016/11/07 S21_NA#14186 Replace Message ID
        } catch (S21NwfSystemException e) {
            return NWZM2049E; // 2016/02/29 S21_NA#4291 Add End -> 2016/11/07 S21_NA#14186 Replace Message ID
        } catch (S21NwfException e) {
            // return false; 2016/02/29 S21_NA#4291 Mod Return Value
            return NWZM2050E; // 2016/11/07 S21_NA#14186 Replace Message ID
        }

        // return true;  2016/02/29 S21_NA#4291 Mod Return Value
        return "";
    }

    /**
     * setWorkFlowId
     * @param array ArrayList<String>
     * @return ArrayList<String>
     */
    public ArrayList<String> setWorkFlowId(ArrayList<String> array) {
        array.add(WFID_CREDIT_REVIEW);
        array.add(WFID_VALID_WF);
        array.add(WFID_PRFT_WF);
        array.add(WFID_SPLY_ABUSE_YEILD);
        array.add(WFID_SPLY_ABUSE_CTAC_STS);
        array.add(WFID_SPLY_ABUSE_PEND_ORD);
        array.add(WFID_SPLY_ABUSE_SPLY_ENFORCE);
        array.add(WFID_LEASE_BUYOUT);
        // add start 2023/04/25 QC#61337
        array.add(WFID_MAN_PRC_APVL);
        // add end 2023/04/25 QC#61337

        return array;
    }

    /**
     * getWDocId
     * @param processName String
     * @return boolean
     */
    private String getWDocId(String processName) {
//        StringBuilder wDocId = new StringBuilder();
//        wDocId.append(WF_CANCEL).append("(").append(processName).append(")");
//
//        return wDocId.toString();
    	
    	String wDocId = "";
    	if (WFID_VALID_WF.equals(processName)) {
    		wDocId = EVENTID_CANC_VALID_WF;
    	} else if (WFID_PRFT_WF.equals(processName)) {
    		wDocId = EVENTID_CANC_PRFT_WF;
    	} else if (WFID_SPLY_ABUSE_YEILD.equals(processName)) {
    		wDocId = EVENTID_CANC_SPLY_ABUSE_YEILD;
    	} else if (WFID_SPLY_ABUSE_CTAC_STS.equals(processName)) {
    		wDocId = EVENTID_CANC_SPLY_ABUSE_CTAC_STS;
    	} else if (WFID_SPLY_ABUSE_PEND_ORD.equals(processName)) {
    		wDocId = EVENTID_CANC_SPLY_ABUSE_PEND_ORD;
    	} else if (WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(processName)) {
    		wDocId = EVENTID_CANC_SPLY_ABUSE_SPLY_ENFORCE;
    	} else if (WFID_CREDIT_REVIEW.equals(processName)) {
    		wDocId = EVENTID_CANC_CREDIT_REVIEW;
    	} else if (WFID_LEASE_BUYOUT.equals(processName)) {
    		wDocId = EVENTID_CANC_LEASE_BUYOUT;
        // add start 2023/04/25 QC#61337
        } else if (WFID_MAN_PRC_APVL.equals(processName)) {
            wDocId = EVENTID_CANC_MAN_PRC_APVL;
        // add end 2023/04/25 QC#61337
    	}
    	return wDocId;
    }

    /**
     * insertBizProcLog
     * @param cpoOrdNum String
     * @param wDocId String
     * @return boolean
     */
    private void insertBizProcLog(String cpoOrdNum, String wDocId) {
        S21BusinessProcessLogMsg bizProcLog = new S21BusinessProcessLogMsg();

        bizProcLog.setSubSysId(SUB_SYS_ID);
        bizProcLog.setProcId(PROC_ID);
        bizProcLog.setDocTpCd(DOC_TP_CD);
        bizProcLog.setPrntDocId(cpoOrdNum);
        bizProcLog.setEventId(wDocId);

        S21BusinessProcessLog.print(bizProcLog);
    }

    // 2019/01/18 S21_NA#28773 Add Start
    /**
     * <pre>
     * Get Default Reject Comment.
     * @param glblCmpyCd Global Company Code
     * @return Default Reject Comment.
     * </pre>
     */
    private String getRejectComment(String glblCmpyCd) {

        String constRejectComment = null;
        if (ZYPCommonFunc.hasValue(glblCmpyCd)) {
            constRejectComment = ZYPCodeDataUtil.getVarCharConstValue(NWZC1770_WF_REJ_CMNT, glblCmpyCd);
        }
        if (!ZYPCommonFunc.hasValue(constRejectComment)) {
            constRejectComment = FIXED_REJECT_COMMENT;
        }
        return constRejectComment;
    }
    // 2019/01/18 S21_NA#28773 Add End
}
