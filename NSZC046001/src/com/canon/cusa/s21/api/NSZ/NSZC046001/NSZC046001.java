/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.INV_LINETMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.PRC_SVC_PLN_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TERM_CONDTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrPhysBllgMtrRelnListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;
import business.parts.NSZC046001_xxDsContrAddlChrgListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxDsContrBrAllocListPMsg;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsg;
import business.parts.NSZC046001_xxDsContrPrcEffListPMsg;
import business.parts.NSZC046001_xxDsContrRnwEsclListPMsg;
import business.parts.NSZC046001_xxDsContrSegAllocListPMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;
import business.parts.NSZC046001_xxSvcTermCondListPMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047004PMsg;
import business.parts.NSZC047004_xxMtrLineListPMsg;
import business.parts.NSZC047006PMsg;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;
import business.parts.NSZC047008_xxMtrLineListPMsg;
import business.parts.NSZC047009PMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC080001PMsg;
import business.parts.NSZC080001_segmentsListPMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC080001.NSZC080001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDiffChecker;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDiffCheckerBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetUplftFromDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Hitachi         K.Kasai         Create          N/A
 * 2015/12/18   Hitachi         T.Tsuchida      Update          QC#2136
 * 2015/12/25   Hitachi         T.Tomita        Update          QC#2133
 * 2016/01/18   Hitachi         T.Tomita        Update          QC#1088
 * 2016/01/21   Hitachi         T.Iwamoto       Update          QC#3201
 * 2016/01/25   Hitachi         T.Iwamoto       Update          QC#3531
 * 2016/03/14   Hitachi         T.Iwamoto       Update          QC#5298,5426,5429
 * 2016/03/23   Hitachi         T.Iwamoto       Update          QC#5035
 * 2016/03/31   Hitachi         Y.Tsuchimoto    Update          QC#6339
 * 2016/04/04   Hitachi         Y.Tsuchimoto    Update          QC#6487
 * 2016/04/08   Hitachi         A.Kohinata      Update          QC#6622
 * 2016/04/11   Hitachi         T.Tomita        Update          QC#5039
 * 2016/05/11   Hitachi         T.Kanasaka      Update          QC#3771
 * 2016/05/13   Hitachi         T.Kanasaka      Update          QC#8233
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/05/20   Hitachi         M.Gotou         Update          QC#5922
 * 2016/05/25   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/06/07   Hitachi         T.Iwamoto       Update          QC#4061
 * 2016/06/14   Hitachi         T.Iwamoto       Update          QC#9944
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/21   Hitachi         T.Tomita        Update          QC#446
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11720
 * 2016/08/05   Hitachi         T.Tomita        Update          QC#12230
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12136
 * 2016/09/02   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/28   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/31   Hitachi         A.Kohinata      Update          QC#13299
 * 2016/12/01   Hitachi         A.Kohinata      Update          QC#16205
 * 2016/12/19   Hitachi         T.Tomita        Update          QC#16097
 * 2016/12/21   Hitachi         T.Kanasaka      Update          QC#16650
 * 2016/12/27   Hitachi         A.Kohinata      Update          QC#14773
 * 2017/02/28   Hitachi         K.Kishimoto     Update          QC#17809
 * 2017/04/26   Hitachi         Y.Takeno        Update          RS#7238
 * 2017/05/08   Hitachi         K.Kitachi       Update          QC#18268
 * 2017/06/02   Hitachi         K.Kitachi       Update          QC#18568
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2017/07/10   Hitachi         K.Kojima        Update          QC#19822
 * 2017/07/11   Hitachi         K.Kojima        Update          QC#19822
 * 2017/09/06   CITS            M.Naito         Update          QC#18724
 * 2017/09/27   Hitachi         T.Tomita        Update          QC#21459
 * 2017/10/05   Hitachi         M.Kidokoro      Update          QC#21544
 * 2017/11/17   Hitachi         K.Kojima        Update          QC#22659
 * 2017/11/27   Hitachi         T.Tomita        Update          QC#21724
 * 2018/03/15   Hitachi         T.Tomita        Update          QC#23429
 * 2018/04/06   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * 2018/05/10   CITS            T.Wada          Update          QC#17645
 * 2018/05/15   Hitachi         K.Kojima        Update          QC#23303
 * 2018/05/15   CITS            M.Naito         Update          QC#21679
 * 2018/05/22   Hitachi         K.Kojima        Update          QC#23302
 * 2018/06/01   CITS            T.Wada          Update          QC#17645-2
 * 2018/07/04   Hitachi         T.Tomita        Update          QC#27123
 * 2019/04/18   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2019/05/15   Hitachi         K.Fujimoto      Update          QC#50279
 * 2019/05/22   Hitachi         K.Kim           Update          QC#50212
 * 2019/11/06   Hitachi         K.Kitachi       Update          QC#54368
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/03/22   Hitachi         K.Kishimoto     Update          QC#59683
 * 2022/07/07   Hitachi         A.Kohinata      Update          QC#60167
 * 2023/01/05   CITS            L.Mandanas      Update          QC#60949
 * </pre>
 */
public class NSZC046001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    // add start 2016/09/02 CSA Defect#11243
    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp = null;
    // add end 2016/09/02 CSA Defect#11243

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NSZC046001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Main routine of Service Contract Data Update API
     * </pre>
     * @param param Input parameter
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(NSZC046001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        String processMode = param.xxModeCd.getValue();
        // add start 2016/09/02 CSA Defect#11243
        this.onBatchTp = onBatchType;
        // add end 2016/09/02 CSA Defect#11243

        if (!checkParam(msgMap, onBatchType)) {
            msgMap.flush();
            return;
        }

        if (processMode.equals(MODE_CREATE)) {
            if (!validateForCreateMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessCreateContr(msgMap, onBatchType);
        }

        if (processMode.equals(MODE_UPDATE)) {
            if (!validateForUpdateMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessUpdateContr(msgMap, onBatchType);
        }

        if (processMode.equals(MODE_ADD_TO_CONTR)) {
            if (!validateForAddToContrMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessAddToContr(msgMap, onBatchType);
        }

        if (processMode.equals(MODE_DELETE)) {
            if (!validateForDeleteMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessDeleteContr(msgMap, onBatchType);
        }

        if (processMode.equals(MODE_TERMINATE)) {
            if (!validateForTerminateMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessTerminateContr(msgMap, onBatchType);
        }

        if (processMode.equals(MODE_RENEWAL)) {
            if (!validateForRenewalMode(msgMap, onBatchType)) {
                msgMap.flush();
                return;
            }
            doProcessRenewalContr(msgMap, onBatchType);
        }
        msgMap.flush();
    }

    /**
     * <pre>
     * Service Contract Data Update API (List)
     * </pre>
     * @param params Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NSZC046001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NSZC046001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * Check the Parameter
     *  Check mandatory item
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType Type of Online or Batch.
     */
    private boolean checkParam(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // check common mandatory parameter
        checkCommonParameter(msgMap, param.glblCmpyCd, NSZM0589E, "Global Company Code");
        checkCommonParameter(msgMap, param.xxModeCd, NSZM0589E, "Mode Code");
        checkCommonParameter(msgMap, param.slsDt, NSZM0589E, "Sales Date");
        // START 2016/01/20 T.Iwamoto [QC#3201, MOD]
        if (!MODE_RENEWAL.equals(param.xxModeCd.getValue())) {
            checkCommonParameter(msgMap, param.dsContrSrcTpCd, NSZM0589E, "DS Contract Source Type Code");
        }
        // END 2016/01/20 T.Iwamoto [QC#3201, MOD]
        checkCommonParameter(msgMap, param.psnCd, NSZM0589E, "Person Code");

        if (!MODE_CREATE.equals(param.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.dsContrPk) && !ZYPCommonFunc.hasValue(param.dsContrNum)) {
                msgMap.addXxMsgIdWithPrm(NSBM0050E, new String[]{"DS Contract Numer", "DS Contract PK"});
            }

            DS_CONTRTMsg dsContr = null;
            if (ZYPCommonFunc.hasValue(param.dsContrPk)) {
                dsContr = NSZC046001CommonLogic.getDsContr(param.glblCmpyCd.getValue(), param.dsContrPk.getValue());
                if (dsContr == null) {
                    msgMap.addXxMsgIdWithPrm(NSZM0652E, new String[]{param.dsContrPk.getValue().toPlainString(), "DS_CONTR"});
                }
            }
            if (dsContr == null && ZYPCommonFunc.hasValue(param.dsContrNum)) {
                dsContr = NSZC046001CommonLogic.getDsContr(param.glblCmpyCd.getValue(), param.dsContrNum.getValue());
                if (dsContr == null) {
                    msgMap.addXxMsgIdWithPrm(NSZM0652E, new String[]{param.dsContrNum.getValue(), "DS_CONTR"});
                }
            }

            if (dsContr != null) {
                ZYPEZDItemValueSetter.setValue(param.dsContrPk, dsContr.dsContrPk);
                ZYPEZDItemValueSetter.setValue(param.dsContrNum, dsContr.dsContrNum);
            }

            if (MODE_ADD_TO_CONTR.equals(param.xxModeCd.getValue())) {
                EZDMsg.copy(dsContr, null, param, null);
            }

        }

        if (S21ApiUtil.isXxMsgId(param)) {
            return false;
        }
        return true;

    }

    private boolean validateForCreateMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!ContrHdrValidation.validateForCreateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrDtlValidation.validateForCreateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrBllgMtrPhysRelnValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrBllgMtrValidation.validateForCreateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrXsCopyValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrTandCValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrRnwValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrCcCardValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrAddlChrgValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrBrAllocValidation.validateForCreateMode(param)) {
            return false;
        }
        if (!ContrSegAllocValidation.validateForCreateMode(param)) {
            return false;
        }
        return true;
    }

    private boolean validateForUpdateMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!ContrHdrValidation.validateForUpdateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrDtlValidation.validateForUpdateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrBllgMtrPhysRelnValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrBllgMtrValidation.validateForUpdateMode(param, onBatchType)) {
            return false;
        }
        if (!ContrXsCopyValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrTandCValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrRnwValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrCcCardValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrAddlChrgValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrBrAllocValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrSegAllocValidation.validateForUpdateMode(param)) {
            return false;
        }
        if (!ContrSvcMemoValidation.validateForUpdateMode(param)) {
            return false;
        }
        return true;
    }

    private boolean validateForAddToContrMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // START 2016/04/08 [QC#6622, ADD]
        if (!ContrHdrValidation.validateForAddContrMode(param)) {
            return false;
        }
        // END 2016/04/08 [QC#6622, ADD]
        if (!ContrDtlValidation.validateForAddContrMode(param, onBatchType)) {
            return false;
        }
        if (!ContrBllgMtrPhysRelnValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrBllgMtrValidation.validateForAddContrMode(param, onBatchType)) {
            return false;
        }
        if (!ContrXsCopyValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrTandCValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrRnwValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrCcCardValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrAddlChrgValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrBrAllocValidation.validateForAddContrMode(param)) {
            return false;
        }
        if (!ContrSegAllocValidation.validateForAddContrMode(param)) {
            return false;
        }
        return true;
    }

    private boolean validateForDeleteMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // START 2016/04/08 [QC#6622, ADD]
        if (!ContrHdrValidation.validateForDeleteMode(param)) {
            return false;
        }
        // END 2016/04/08 [QC#6622, ADD]
        if (!ContrDtlValidation.validateForDeleteMode(param)) {
            return false;
        }
        if (!ContrSvcMemoValidation.validateForDeleteMode(param)) {
            return false;
        }
        return true;
    }

    private boolean validateForTerminateMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // START 2016/04/08 [QC#6622, ADD]
        if (!ContrHdrValidation.validateForTerminateMode(param)) {
            return false;
        }
        // END 2016/04/08 [QC#6622, ADD]
        if (!ContrDtlValidation.validateForTerminateMode(param)) {
            return false;
        }
        if (!ContrSvcMemoValidation.validateForTerminateMode(param)) {
            return false;
        }
        return true;
    }

    private boolean validateForRenewalMode(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!ContrDtlValidation.validateForRenewalMode(param)) {
            return false;
        }
        if (!ContrBllgMtrValidation.validateForRenewalMode(param)) {
            return false;
        }
        if (!ContrXsCopyValidation.validateForRenewalMode(param)) {
            return false;
        }
        if (!ContrSvcMemoValidation.validateForRenewalMode(param)) {
            return false;
        }

        return true;
    }

    private void doProcessCreateContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();
        //Contract Header
        createDsContr(msgMap);

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            //Contract Detail
            createDsContrDtl(msgMap, paramDtl);

            // START 2016/05/25 [QC#4061, ADD]
            if (isManContrOvrd(param)) {
                continue;
            }
            // END   2016/05/25 [QC#4061, ADD]
            if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
                //Billing Meter
                List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = getBllgMtrInfo(param, paramDtl);
                createDsContrBllgMtr(param, paramDtl, bllgMtrList);

                //Billing Physical Meter Relation
                List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList = getPhysBllgRelnInfo(param, paramDtl);
                createContrPhysBllgMtrReln(param, paramDtl, relnList);

                // Excess Copy
                for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
                    List<NSZC046001_xxContrXsCopyListPMsg> xsList = getXsCopyInfo(param, bllgMtr);
                    createContrXsCopy(param, bllgMtr, xsList);
                }

                // del start 2016/05/17 CSA Defect#3771
                // Set Invoice up to date
                // if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
                //     setInvUpDt(param, paramDtl, bllgMtrList);
                // }
                // del end 2016/05/17 CSA Defect#3771
            }

            // del start 2016/05/17 CSA Defect#3771
            // if (NSZC046001CommonLogic.isBaseChrg(param, paramDtl)) {
                // Set Invoice up to date
            //     if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
            //         setInvUpDt(param, paramDtl);
            //     }
            // }
            // del end 2016/05/17 CSA Defect#3771

            // START 2016/12/19 T.Tomita [QC#16097, ADD]
            Map<String, Object> invLineKeyMap = getInvLineKeyMap(param, paramDtl);
            if (invLineKeyMap == null || invLineKeyMap.size() == 0) {
                continue;
            }
            INV_LINETMsg invLineTMsg = getInvLineTMsgForUpdate(param, invLineKeyMap);
            if (invLineTMsg == null) {
                continue;
            }
            updateInvLine(invLineTMsg, param, paramDtl);
            // END 2016/12/19 T.Tomita [QC#16097, ADD]
        }
        // add start 2016/08/05 CSA Defect#12230
        if (DS_CONTR_CATG.WARRANTY.equals(param.dsContrCatgCd.getValue())) {
            return;
        }
        // add end 2016/08/05 CSA Defect#12230

        // Credit Card
        createCcCardPo(param);
        // Renewal Escalation
        createRnwEscl(param);
        // Terms and Conditions
        createTermsAndConditions(param);
        // Additional Charge
        createAddlChrg(param);
        // START 2016/05/25 [QC#4061, MOD]
        if (!isManContrOvrd(param)) {
            // Branch Allocation
            createBrAlloc(param);
            // Segment Allocation
            createSegAlloc(param);
            // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
            // if (param.xxDsContrBrAllocList.getValidCount() > 0
            //        || param.xxDsContrSegAllocList.getValidCount() > 0) {
            if (param.xxDsContrSegAllocList.getValidCount() > 0){
                callRevenueDistributionAPI(msgMap, param, onBatchType);
            // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
            }
            // call billing schedule api
            callBllgSchdApi(msgMap, onBatchType);

            // add start 2016/05/17 CSA Defect#3771
            for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
                NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);

                if (NSZC046001CommonLogic.isBaseChrg(param, paramDtl)) {
                    // Set Invoice up to date
                    if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
                        setInvUpDt(param, paramDtl);
                    }
                }
            }
            // add end 2016/05/17 CSA Defect#3771
        }
        // END   2016/05/25 [QC#4061, MOD]
    }

    private void doProcessUpdateContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();
        //Check difference for calling billing schedule api
        @SuppressWarnings("unused")
        List<BigDecimal> baseBillSchdApiCallList = getBaseDiffData(param);
        @SuppressWarnings("unused")
        List<BigDecimal> usgBillSchdApiCallList = getUsgDiffData(param);

        //Contract Header
        updateDsContr(msgMap);

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            //Contract Detail
            updateDsContrDtl(msgMap, paramDtl);

            if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
                //Billing Meter
                List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = getBllgMtrInfo(param, paramDtl);
                processDsContrBllgMtr(param, paramDtl, bllgMtrList);

                //Billing Physical Meter Relation
                List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList = getPhysBllgRelnInfo(param, paramDtl);
                updateContrPhysBllgMtrReln(param, paramDtl, relnList);

                // Excess Copy
                for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
                    List<NSZC046001_xxContrXsCopyListPMsg> xsList = getXsCopyInfo(param, bllgMtr);
                    processContrXsCopy(param, paramDtl, bllgMtr, xsList);
                }

                // del start 2016/05/17 CSA Defect#3771
                // Set Invoice up to date
                // if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
                //     setInvUpDt(param, paramDtl, bllgMtrList);
                // }
                // del start 2016/05/17 CSA Defect#3771

            }

            // del start 2016/05/17 CSA Defect#3771
            // if (NSZC046001CommonLogic.isBaseChrg(param, paramDtl)) {
                // Set Invoice up to date
            //    if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
            //         setInvUpDt(param, paramDtl);
            //     }
            // }
            // del start 2016/05/17 CSA Defect#3771

        }
        // add start 2016/12/01 QC#16205
        if (DS_CONTR_CATG.WARRANTY.equals(param.dsContrCatgCd.getValue())) {
            return;
        }
        // add end 2016/12/01 QC#16205

        // Credit Card
        processContrCcCard(param);
        // Renewal Escalation
        processContrRnwEscl(param);
        // Terms and Conditions
        processContrTermsAndConditions(param);
        // Additional Charge
        processAddlChrg(param);
        // Branch Allocation
        processBrAlloc(param);
        // Segment Allocation
        processSegAlloc(param);
        //Memo
        processSvcMemo(param);
        // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        // if (param.xxDsContrBrAllocList.getValidCount() > 0
        //        || param.xxDsContrSegAllocList.getValidCount() > 0) {
        if (param.xxDsContrSegAllocList.getValidCount() > 0){
            callRevenueDistributionAPI(msgMap, param, onBatchType);
            // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        }
        // call billing schedule api
        callBllgSchdApi(msgMap, onBatchType);

        // add start 2016/05/17 CSA Defect#3771
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);

            if (NSZC046001CommonLogic.isBaseChrg(param, paramDtl)) {
                // Set Invoice up to date
                if (ZYPCommonFunc.hasValue(paramDtl.invUpToDt)) {
                    setInvUpDt(param, paramDtl);
                }
            }
        }
        // add end 2016/05/17 CSA Defect#3771
    }

    private void doProcessDeleteContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            //Contract Detail
            cancelDsContrDtl(msgMap, paramDtl);

            if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
                //Billing Meter
                cancelDsContrBllgMtrByContrDtl(msgMap, paramDtl);
            }
        }

        List<BigDecimal> effectiveContrDtlList = getEffectiveDsContrDtl(param);
        if (effectiveContrDtlList == null || effectiveContrDtlList.isEmpty()) {
            //Contract Header
            cancelDsContr(msgMap);
        }

        //Memo
        processSvcMemo(param);

        // call billing schedule api
        callBllgSchdApiForDelete(msgMap, onBatchType);

        // add start 2016/12/27 QC#14773
        deleteContrFltAggLine(msgMap, onBatchType);
        // add end 2016/12/27 QC#14773
    }

    private void doProcessTerminateContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            //Contract Detail
            terminateDsContrDtl(msgMap, paramDtl);

            if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
                //Billing Meter
                terminateDsContrBllgMtrByContrDtl(msgMap, paramDtl);
            }

            // START 2018/05/22 K.Kojima [QC#23302,ADD]
            if (ZYPCommonFunc.hasValue(paramDtl.contrCloDt)) {
                terminateDsContrAddlChrg(msgMap, param.glblCmpyCd.getValue(), null, paramDtl.dsContrDtlPk.getValue(), paramDtl.contrCloDt.getValue());
            }
            // END 2018/05/22 K.Kojima [QC#23302,ADD]
        }

        List<BigDecimal> effectiveContrDtlList = getEffectiveDsContrDtl(param);
        if (effectiveContrDtlList == null || effectiveContrDtlList.isEmpty()) {
            //Contract Header
            terminateDsContr(msgMap);
        }

        //Memo
        processSvcMemo(param);

        // call billing schedule api
        callBllgSchdApiForTerminate(msgMap, onBatchType);

        // add start 2016/12/27 QC#14773
        terminateContrFltAggLine(msgMap, onBatchType);
        // add end 2016/12/27 QC#14773
    }

    private void doProcessAddToContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);

            // START 2016/03/30 [QC#6487, ADD]
            if (DS_CONTR_DTL_TP.FLEET.equals(paramDtl.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(paramDtl.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END   2016/03/30 [QC#6487, ADD]
            // process DS_CONTR_DTL
            createDsContrDtl(msgMap, paramDtl);

            // START 2016/05/25 [QC#4061, ADD]
            if (isManContrOvrd(param)) {
                continue;
            }
            // END   2016/05/25 [QC#4061, ADD]

            // Meter charge
            if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
                //Billing Meter
                List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = getBllgMtrInfo(param, paramDtl);
                createDsContrBllgMtr(param, paramDtl, bllgMtrList);

                //Billing Physical Meter Relation
                List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList = getPhysBllgRelnInfo(param, paramDtl);
                createContrPhysBllgMtrReln(param, paramDtl, relnList);

                // Excess Copy
                for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
                    List<NSZC046001_xxContrXsCopyListPMsg> xsList = getXsCopyInfo(param, bllgMtr);
                    createContrXsCopy(param, bllgMtr, xsList);
                }
            }
        }

        // add start 2016/10/31 QC#13299
        if (DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue())) {
            updateDsContrDtlForAggLine(param);
        }
        // add end 2016/10/31 QC#13299

        // add start 2016/08/05 CSA Defect#12230
        if (DS_CONTR_CATG.WARRANTY.equals(param.dsContrCatgCd.getValue())) {
            return;
        }
        // add end 2016/08/05 CSA Defect#12230

        // Credit Card
        createCcCardPo(param);
        // Renewal Escalation
        createRnwEscl(param);
        // Terms and Conditions
        createTermsAndConditions(param);
        // Additional Charge
        createAddlChrg(param);
        // START 2016/05/25 [QC#4061, MOD]
        if (!isManContrOvrd(param)) {
            // Branch Allocation
            createBrAlloc(param);
            // Segment Allocation
            createSegAlloc(param);
            // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
            //if (param.xxDsContrBrAllocList.getValidCount() > 0
            //        || param.xxDsContrSegAllocList.getValidCount() > 0) {
            if (param.xxDsContrSegAllocList.getValidCount() > 0){
                callRevenueDistributionAPI(msgMap, param, onBatchType);
                // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
            }
            // call billing schedule api
            callBllgSchdApi(msgMap, onBatchType);
        }
        // END   2016/05/25 [QC#4061, MOD]
    }

    private void doProcessRenewalContr(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> oldContrDtlList = new ArrayList<DS_CONTR_DTLTMsg>();
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            DS_CONTR_DTLTMsg dsContr = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue());
            oldContrDtlList.add(dsContr);
            //Contract Detail
            renewalDsContrDtl(msgMap, paramDtl);

            //QC#17645 Add
            renewalDsContrAddlChrg(msgMap, null, dsContr, paramDtl.contrEffThruDt.getValue(), LVL_CONTRCT_DTL);

            // START 2018/05/15 K.Kojima [QC#23303,ADD]
            renewalDsSubContr(msgMap, dsContr, paramDtl.contrEffThruDt.getValue());
            // END 2018/05/15 K.Kojima [QC#23303,ADD]
        }

        //Contract Header
        renewalDsContr(msgMap);

        // call billing schedule api
        callBllgSchdApiForRenew(msgMap, onBatchType, oldContrDtlList);

        // START 2017/11/17 K.Kojima [QC#22659,ADD]
        // update Term Amount for DS_CONTR_DTL
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg paramDtl = param.xxContrDtlList.no(i);
            DS_CONTR_DTLTMsg dsContrDtl = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue());
            ZYPEZDItemValueSetter.setValue(dsContrDtl.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue()));
            S21ApiTBLAccessor.update(dsContrDtl);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtl.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
            }
        }
        // END 2017/11/17 K.Kojima [QC#22659,ADD]

        //Memo
        renewalSvcMemo(param);
    }

    // START 2017/11/17 K.Kojima [QC#22659,ADD]
    private BigDecimal getBasePrcTermDealAmtRate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_PRC_EFFTMsgArray resultArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        for (int i = 0; i < resultArray.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(resultArray.no(i).basePrcTermDealAmtRate)) {
                continue;
            }
            rtnVal = rtnVal.add(resultArray.no(i).basePrcTermDealAmtRate.getValue());
        }
        return rtnVal;
    }

    // END 2017/11/17 K.Kojima [QC#22659,ADD]

    private void processDsContrBllgMtr(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl,
            List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList) {

        for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
            String mode = bllgMtr.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createDsContrBllgMtr(param, paramDtl, bllgMtr);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateDsContrBllgMtr(param, paramDtl, bllgMtr);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteDsContrBllgMtr(param, paramDtl, bllgMtr);
            }
        }
    }

    private void processContrXsCopy(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl,
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr, List<NSZC046001_xxContrXsCopyListPMsg> xsList) {

        for (NSZC046001_xxContrXsCopyListPMsg xsCopy : xsList) {
            String mode = xsCopy.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createContrXsCopy(param, bllgMtr, xsCopy);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateContrXsCopy(param, bllgMtr, xsCopy);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteContrXsCopy(param, bllgMtr, xsCopy);
            }
        }
    }

    private void processContrCcCard(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg ccMsg = param.xxDsContrCrCardPoList.no(i);
            String mode = ccMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createCcCardPo(param, ccMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateCcCardPo(param, ccMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteCcCardPo(param, ccMsg);
            }
        }
    }

    private void processContrRnwEscl(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrRnwEsclList.getValidCount(); i++) {
            NSZC046001_xxDsContrRnwEsclListPMsg rnwMsg = param.xxDsContrRnwEsclList.no(i);
            String mode = rnwMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createRnwEscl(param, rnwMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateRnwEscl(param, rnwMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteRnwEscl(param, rnwMsg);
            }
        }
    }

    private void processContrTermsAndConditions(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxSvcTermCondList.getValidCount(); i++) {
            NSZC046001_xxSvcTermCondListPMsg tAndCMsg = param.xxSvcTermCondList.no(i);
            String mode = tAndCMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createTermsAndConditions(param, tAndCMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateTermsAndConditions(param, tAndCMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteTermsAndConditions(param, tAndCMsg);
            }
        }
    }

    private void processAddlChrg(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addlMsg = param.xxDsContrAddlChrgList.no(i);
            String mode = addlMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createAddlChrg(param, addlMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateAddlChrg(param, addlMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteAddlChrg(param, addlMsg);
            }
        }
    }

    private void processBrAlloc(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            String mode = brMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createBrAlloc(param, brMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateBrAlloc(param, brMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteBrAlloc(param, brMsg);
            }
        }
    }

    private void processSegAlloc(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            String mode = segMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createSegAlloc(param, segMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateSegAlloc(param, segMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteSegAlloc(param, segMsg);
            }
        }
    }

    private void processSvcMemo(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg memoMsg = param.xxSvcMemoList.no(i);
            String mode = memoMsg.xxModeCd.getValue();
            if (CRUD_MODE_CREATE.equals(mode)) {
                createSvcMemo(param, memoMsg);
            } else if (CRUD_MODE_UPDATE.equals(mode)) {
                updateSvcMemo(param, memoMsg);
            } else if (CRUD_MODE_DELETE.equals(mode)) {
                deleteSvcMemo(param, memoMsg);
            }
        }
    }

    private S21ApiMessageMap createDsContr(S21ApiMessageMap msgMap) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_SQ);
        DS_CONTRTMsg inParam = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrSqNum, "00");
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, dsContrPk);
        if (ZYPCommonFunc.hasValue(param.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrNum, param.dsContrNum);
        } else {
            String dsContrNum = ZYPExtnNumbering.getUniqueID(param.glblCmpyCd.getValue(), "DS_CONTR_NUM");
            ZYPEZDItemValueSetter.setValue(inParam.dsContrNum, dsContrNum);
        }
        // mod start 2016/07/21 CSA Defect#446
        String dsContrTpCd = DS_CONTR_TP.SERVICE;
        if (ZYPCommonFunc.hasValue(param.dsContrTpCd)) {
            dsContrTpCd = param.dsContrTpCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(inParam.dsContrTpCd, dsContrTpCd);

        String dsContrStsCd = DS_CONTR_STS.DRAFT;
        if (ZYPCommonFunc.hasValue(param.dsContrStsCd)) {
            dsContrStsCd = param.dsContrStsCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(inParam.dsContrStsCd, dsContrStsCd);
        // mod end 2016/07/21 CSA Defect#446
        ZYPEZDItemValueSetter.setValue(inParam.contrVrsnEffFromDt, param.contrVrsnEffFromDt);
        ZYPEZDItemValueSetter.setValue(inParam.contrVrsnEffThruDt, param.contrVrsnEffThruDt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrCatgCd, param.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(inParam.altPayerCustCd, param.billToCustCd);
        // START 2016/12/21 T.Kanasaka [QC#16650, MOD]
//        if (param.xxDsContrCrCardPoList.getValidCount() > 0) {
        if (hasCrCard(param)) {
        // END 2016/12/21 T.Kanasaka [QC#16650, MOD]
            ZYPEZDItemValueSetter.setValue(inParam.pmtTermCashDiscCd, "CC");
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.pmtTermCashDiscCd, param.pmtTermCashDiscCd);
        }
        ZYPEZDItemValueSetter.setValue(inParam.svcContrRefCmntTxt, param.svcContrRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(inParam.bllgApvlReqFlg, FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(param.ccyCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.ccyCd, getCcyCd(param.glblCmpyCd.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.ccyCd, param.ccyCd);
        }
        ZYPEZDItemValueSetter.setValue(inParam.preInvReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
        // Start 2022/03/22 [QC#59683, Add]
        ZYPEZDItemValueSetter.setValue(inParam.dsInvTgtrTpCd, param.dsInvTgtrTpCd);
        // End   2022/03/22 [QC#59683, Add]
        ZYPEZDItemValueSetter.setValue(inParam.invSeptBaseUsgFlg, param.invSeptBaseUsgFlg);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrCratDt, param.dsContrCratDt);

        if (!ZYPCommonFunc.hasValue(param.dsContrCratPsnCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrCratPsnCd, param.psnCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrCratPsnCd, param.dsContrCratPsnCd);
        }
        ZYPEZDItemValueSetter.setValue(inParam.leaseCmpyCd, param.leaseCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsAcctNum, param.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(inParam.tocCd, param.tocCd);
        if (ZYPCommonFunc.hasValue(param.baseChrgToLeaseCmpyFlg)) {
            ZYPEZDItemValueSetter.setValue(inParam.baseChrgToLeaseCmpyFlg, param.baseChrgToLeaseCmpyFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.baseChrgToLeaseCmpyFlg, FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(param.usgChrgToLeaseCmpyFlg)) {
            ZYPEZDItemValueSetter.setValue(inParam.usgChrgToLeaseCmpyFlg, param.usgChrgToLeaseCmpyFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.usgChrgToLeaseCmpyFlg, FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(inParam.mtrEstTpCd, param.mtrEstTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.prcAllocByMachQtyFlg, param.prcAllocByMachQtyFlg);
        ZYPEZDItemValueSetter.setValue(inParam.svcContrBrCd, param.svcContrBrCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrClsCd, param.dsContrClsCd);
        ZYPEZDItemValueSetter.setValue(inParam.ctacPsnPk, param.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(inParam.contrInvCmntTxt, param.contrInvCmntTxt);
        ZYPEZDItemValueSetter.setValue(inParam.svcLineBizCd, param.svcLineBizCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrSrcTpCd, param.dsContrSrcTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrEdiCd, param.dsContrEdiCd);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(param.svcPgmMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.svcPgmMdseCd, param.xxContrDtlList.no(0).svcPgmMdseCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.svcPgmMdseCd, param.svcPgmMdseCd);
        }
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/01/18 T.Tomita [QC#1088, ADD]
        // START 2016/06/30 T.Iwamoto [QC#10661, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.contrAdminPsnCd, param.contrAdminPsnCd_HD);
        // END 2016/06/30 T.Iwamoto [QC#10661, ADD]

        // add start 2016/10/31 QC#13299
        ZYPEZDItemValueSetter.setValue(inParam.baseBllgCycleCd, param.baseBllgCycleCd);
        // add end 2016/10/31 QC#13299

        // add start 2016/07/21 CSA Defect#11720
        ContrDurationInfo info = new ContrDurationInfo();
        info.setGlblCmpyCd(inParam.glblCmpyCd.getValue());
        info.setContrEffFromDt(inParam.contrVrsnEffFromDt.getValue());
        info.setContrEffThruDt(inParam.contrVrsnEffThruDt.getValue());
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(info);
        calculator.calcDuration();

        ZYPEZDItemValueSetter.setValue(inParam.contrDurnAot, info.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(inParam.bllgCycleUomCd, info.getCycleUomCd());
        // add end 2016/07/21 CSA Defect#11720

        // START 2017/04/26 [RS#7238, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.applyEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        // END   2017/04/26 [RS#7238, ADD]
        // add start 2017/06/19 CSA Defect#19036
        ZYPEZDItemValueSetter.setValue(inParam.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        // add end 2017/06/19 CSA Defect#19036

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0391E);
            return msgMap;
        }
        ZYPEZDItemValueSetter.setValue(param.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(param.dsContrNum, inParam.dsContrNum);
        return msgMap;
    }

    private void updateDsContr(S21ApiMessageMap msgMap) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        String contrSts = decisionDsContrStsCd(param);
        if (ZYPCommonFunc.hasValue(contrSts)) {
            return;
        }

        DS_CONTRTMsg inParam = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);

        inParam = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        // add start 2016/12/01 QC#16205
        Boolean updateContrDurn = false;
        if (!param.contrVrsnEffFromDt.getValue().equals(inParam.contrVrsnEffFromDt.getValue()) || !param.contrVrsnEffThruDt.getValue().equals(inParam.contrVrsnEffThruDt.getValue())) {
            updateContrDurn = true;
        }
        // add end 2016/12/01 QC#16205

        EZDMsg.copy(param, null, inParam, null);
        // START 2016/12/21 T.Kanasaka [QC#16650, MOD]
//        if (param.xxDsContrCrCardPoList.getValidCount() == 0) {
        if (hasCrCard(param)) {
        // END 2016/12/21 T.Kanasaka [QC#16650, MOD]
            ZYPEZDItemValueSetter.setValue(inParam.pmtTermCashDiscCd, "CC");
        }
        if (!ZYPCommonFunc.hasValue(param.ccyCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.ccyCd, getCcyCd(param.glblCmpyCd.getValue()));
        }
        if (DS_CONTR_CTRL_STS.QA_HOLD.equals(contrSts)) {
            ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, FLG_ON_Y);
        }
        // add start 2016/05/20 CSA Defect#5922
        if (ZYPCommonFunc.hasValue(param.billToCustCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.altPayerCustCd, param.billToCustCd);
        }
        // add end 2016/05/20 CSA Defect#5922
        // START 2016/06/30 T.Iwamoto [QC#10661, ADD]
        if (ZYPCommonFunc.hasValue(param.contrAdminPsnCd_HD)) {
            ZYPEZDItemValueSetter.setValue(inParam.contrAdminPsnCd, param.contrAdminPsnCd_HD);
        }
        // END 2016/06/30 T.Iwamoto [QC#10661, ADD]

        // add start 2016/12/01 QC#16205
        if (updateContrDurn) {
            ContrDurationInfo info = new ContrDurationInfo();
            info.setGlblCmpyCd(inParam.glblCmpyCd.getValue());
            info.setContrEffFromDt(inParam.contrVrsnEffFromDt.getValue());
            info.setContrEffThruDt(inParam.contrVrsnEffThruDt.getValue());
            NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(info);
            calculator.calcDuration();

            ZYPEZDItemValueSetter.setValue(inParam.contrDurnAot, info.getContrDurnNum());
            ZYPEZDItemValueSetter.setValue(inParam.bllgCycleUomCd, info.getCycleUomCd());
        }
        // add end 2016/12/01 QC#16205

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0391E);
        }
    }

    private void cancelDsContr(S21ApiMessageMap msgMap) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTRTMsg inParam = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);

        inParam = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrStsCd, DS_CONTR_STS.CANCELLED);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
        }
    }

    private void terminateDsContr(S21ApiMessageMap msgMap) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTRTMsg inParam = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);

        inParam = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrStsCd, DS_CONTR_STS.TERMINATED);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
        }
    }

    private void renewalDsContr(S21ApiMessageMap msgMap) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTRTMsg inParam = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);

        inParam = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        String maxEffThruDt = getMaxContrDtlEffThruDt(param);
        if (!ZYPCommonFunc.hasValue(maxEffThruDt) || maxEffThruDt.compareTo(
                inParam.contrVrsnEffThruDt.getValue()) <= 0) {
            return;
        }

        // QC#17645 Add Start
        renewalDsContrAddlChrg(msgMap, inParam, null, maxEffThruDt, LVL_CONTRCT);
        // QC#17645 Add End

        if (!ZYPCommonFunc.hasValue(inParam.firstRnwEffFromDt)) {
            ZYPEZDItemValueSetter.setValue(inParam.firstRnwEffFromDt
                    , ZYPDateUtil.addDays(inParam.contrVrsnEffThruDt.getValue(), 1));
        }
        ZYPEZDItemValueSetter.setValue(inParam.contrVrsnEffThruDt, maxEffThruDt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrLastUpdDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrLastUpdPsnCd, param.psnCd);

        // add start 2016/07/21 CSA Defect#11720
        ContrDurationInfo info = new ContrDurationInfo();
        info.setGlblCmpyCd(inParam.glblCmpyCd.getValue());
        info.setContrEffFromDt(inParam.contrVrsnEffFromDt.getValue());
        info.setContrEffThruDt(inParam.contrVrsnEffThruDt.getValue());
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(info);
        calculator.calcDuration();

        ZYPEZDItemValueSetter.setValue(inParam.contrDurnAot, info.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(inParam.bllgCycleUomCd, info.getCycleUomCd());
        // add end 2016/07/21 CSA Defect#11720

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
        }
    }

    private void createDsContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        BigDecimal dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_DTL_SQ);
        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlTpCd, paramDtl.dsContrDtlTpCd);
        if (ZYPCommonFunc.hasValue(paramDtl.dsContrDtlStsCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlStsCd, paramDtl.dsContrDtlStsCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlStsCd, DS_CONTR_DTL_STS.SAVED);
        }

        ZYPEZDItemValueSetter.setValue(inParam.cpoOrdNum, paramDtl.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inParam.cpoDtlLineNum, paramDtl.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inParam.cpoDtlLineSubNum, paramDtl.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inParam.dsOrdTpCd, paramDtl.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsOrdRsnCd, paramDtl.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcConfigMstrPk, paramDtl.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, paramDtl.svcMachMstrPk);

        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String contrCloDay = getDplyPerEndDay(paramDtl.contrCloDay.getValue(), paramDtl.contrEffFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(inParam.contrCloDay, contrCloDay);
        ZYPEZDItemValueSetter.setValue(inParam.baseDplyPerEndDay, paramDtl.contrCloDay);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        ZYPEZDItemValueSetter.setValue(inParam.baseBllgCycleCd, paramDtl.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(inParam.baseBllgTmgCd, paramDtl.baseBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(inParam.contrBllgDay, paramDtl.contrBllgDay);
        ZYPEZDItemValueSetter.setValue(inParam.mtrBllgCycleCd, paramDtl.mtrBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(inParam.mtrBllgTmgCd, paramDtl.mtrBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(inParam.basePrcDealAmt, paramDtl.basePrcDealAmt);
        ZYPEZDItemValueSetter.setValue(inParam.contrEffFromDt, paramDtl.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(inParam.contrEffThruDt, paramDtl.contrEffThruDt);
        ZYPEZDItemValueSetter.setValue(inParam.baseBillToCustCd, paramDtl.baseBillToCustCd);
        ZYPEZDItemValueSetter.setValue(inParam.usgBillToCustCd, paramDtl.usgBillToCustCd);

        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String mtrCloDay = getDplyPerEndDay(paramDtl.mtrCloDay.getValue(), paramDtl.contrEffFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(inParam.mtrCloDay, mtrCloDay);
        ZYPEZDItemValueSetter.setValue(inParam.mtrDplyPerEndDay, paramDtl.mtrCloDay);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        ZYPEZDItemValueSetter.setValue(inParam.mtrBllgDay, paramDtl.mtrBllgDay);
        ZYPEZDItemValueSetter.setValue(inParam.supprCrFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrReadMethCd, paramDtl.mtrReadMethCd);
        ZYPEZDItemValueSetter.setValue(inParam.contrRnwTotCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inParam.ctacPsnPk, paramDtl.ctacPsnPk);
        // mod 2016/06/07 Start QC#4061
        // if (!ZYPCommonFunc.hasValue(paramDtl.basePrcTermDealAmtRate)) {
        ZYPEZDItemValueSetter.setValue(inParam.basePrcTermDealAmtRate, paramDtl.basePrcTermDealAmtRate);
        // } else {
            //TODO Term Amount
        // }
        // if (!NSZC046001CommonLogic.isAggLine(paramDtl)) {
        //    ZYPEZDItemValueSetter.setValue(inParam.basePrcTermDealAmtRate, summaryTermAmt(param));
        // }
        // mod 2016/06/07 End QC#4061
        ZYPEZDItemValueSetter.setValue(inParam.maxContrRnwCnt, paramDtl.maxContrRnwCnt);
        ZYPEZDItemValueSetter.setValue(inParam.maxPrcUpRatio, paramDtl.maxPrcUpRatio);
        ZYPEZDItemValueSetter.setValue(inParam.contrRnwTotCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.svcPgmMdseCd, paramDtl.svcPgmMdseCd);

        // START 2016/03/14 T.Iwamoto [QC#5429, MOD]
        if (ZYPCommonFunc.hasValue(paramDtl.prntSvcMachMstrPk) || NSZC046001CommonLogic.isFleetMach(param, paramDtl) || NSZC046001CommonLogic.isAggMach(param, paramDtl)) {
        // END 2016/03/14 T.Iwamoto [QC#5429, MOD]
            NSZC046001_xxContrDtlListPMsg prntDtl = getContrDtlInfo(param, paramDtl.prntSvcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.prntDsContrDtlPk, prntDtl.dsContrDtlPk);
        }
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/01/18 T.Tomita [QC#1088, ADD]

        // START 2016/09/28 T.Kanasaka [QC#9905, ADD]
        String shipToCustCd = getShipToCustCd(param, paramDtl);
        ZYPEZDItemValueSetter.setValue(inParam.shipToCustCd, shipToCustCd);
        // END 2016/09/28 T.Kanasaka [QC#9905, ADD]

        // START 2016/12/19 T.Tomita [QC#16097, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.cpoSvcDtlPk, paramDtl.cpoSvcDtlPk);
        // END 2016/12/19 T.Tomita [QC#16097, ADD]

        // START 2017/04/26 [RS#7238, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.addContrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.addAsryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.billWithEquipFlg , ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        // END   2017/04/26 [RS#7238, ADD]

        // Add Start 2018/08/28 QC#27102
        if (ZYPCommonFunc.hasValue(paramDtl.uplftFromDt)) {
            ZYPEZDItemValueSetter.setValue(inParam.uplftFromDt, paramDtl.uplftFromDt);
        } else if (ZYPCommonFunc.hasValue(inParam.contrEffFromDt)) {
            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(inParam.glblCmpyCd.getValue(), inParam.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, param.dsContrClsCd.getValue(), param.svcLineBizCd.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.uplftFromDt, uplftFromDt);
        }
        // Add End 2018/08/28 QC#27102

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, inParam.dsContrDtlTpCd);
        ZYPEZDItemValueSetter.setValue(paramDtl.prntDsContrDtlPk, inParam.prntDsContrDtlPk);
    }

    private void cancelDsContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);

        inParam = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, inParam.dsContrDtlTpCd);
    }

    private void terminateDsContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);

        inParam = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        if (ZYPCommonFunc.hasValue(paramDtl.contrCloDt)) {
            // Mod Start 2017/11/27 QC#21724
            if (param.slsDt.getValue().compareTo(paramDtl.contrCloDt.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlStsCd, DS_CONTR_DTL_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, ZYPConstant.FLG_OFF_N);
            }
            // Mod End 2017/11/27 QC#21724
        }
        ZYPEZDItemValueSetter.setValue(inParam.contrCloDt, paramDtl.contrCloDt);
        ZYPEZDItemValueSetter.setValue(inParam.trmnTotAmt, paramDtl.trmnTotAmt);
        ZYPEZDItemValueSetter.setValue(inParam.trmnOvrdTotAmt, paramDtl.trmnOvrdTotAmt);
        ZYPEZDItemValueSetter.setValue(inParam.supprCrFlg, paramDtl.supprCrFlg);
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.manTrmnTpCd, paramDtl.manTrmnTpCd);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        S21ApiTBLAccessor.update(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, inParam.dsContrDtlTpCd);

        // START 2018/05/15 M.Naito [QC#21679, ADD]
        // Check Sub Contract
        if (DS_CONTR_DTL_STS.TERMINATED.equals(inParam.dsContrDtlStsCd.getValue())) {
            // START 2019/05/22 [QC#50212,MOD]
            // DS_SUB_CONTRTMsgArray dsSubContrList = getDsSubContrList(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue());
            // for (int i = 0; i < dsSubContrList.getValidCount(); i++) {
            //     // Call NSZC0520:Sub Contract Update API
            //     DS_SUB_CONTRTMsg dsSubContrTMsg = dsSubContrList.no(i);
            //     NSZC052001PMsg nszc052001PMsg = new NSZC052001PMsg();
            //     setParamForNSZC052001(nszc052001PMsg, dsSubContrTMsg, param);
            //     NSZC052001 apiNSZC052001 = new NSZC052001();
            //     apiNSZC052001.execute(nszc052001PMsg, onBatchTp);
            // }
            updateDsSubContr(msgMap, param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue());
            // END 2019/05/22 [QC#50212,MOD]
        }
        // END 2018/05/15 M.Naito [QC#21679, ADD]
    }

    private void updateDsContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        String contrSts = decisionDsContrDtlStsCd(param, paramDtl);
        if (ZYPCommonFunc.hasValue(contrSts)) {
            return;
        }

        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);

        inParam = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        // Add Start 2018/08/28 QC#27102
        if (!ZYPCommonFunc.hasValue(paramDtl.uplftFromDt)) {
            ZYPEZDItemValueSetter.setValue(paramDtl.uplftFromDt, inParam.uplftFromDt);
        }
        // Add End 2018/08/28 QC#27102
        EZDMsg.copy(paramDtl, null, inParam, null);
        if (DS_CONTR_CTRL_STS.QA_HOLD.equals(contrSts)) {
            ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, FLG_ON_Y);
        }
        // mod 2016/06/07 Start QC#4061
        // if (!ZYPCommonFunc.hasValue(paramDtl.basePrcTermDealAmtRate)) {
        ZYPEZDItemValueSetter.setValue(inParam.basePrcTermDealAmtRate, paramDtl.basePrcTermDealAmtRate);
        // } else {
            //TODO 部品を呼び出しTerm Amountを算�E
        // }
        // if (!NSZC046001CommonLogic.isAggLine(paramDtl)) {
        //     ZYPEZDItemValueSetter.setValue(inParam.basePrcTermDealAmtRate, summaryTermAmt(param));
        // }
        // mod 2016/06/07 End QC#4061
        // add start 2016/05/20 CSA Defect#5922
        if (ZYPCommonFunc.hasValue(paramDtl.baseBillToCustCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.dtlBillToCustCd, paramDtl.baseBillToCustCd);
        }
        // add end 2016/05/20 CSA Defect#5922

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, inParam.dsContrDtlTpCd);
    }

    // add start 2016/10/31 QC#13299
    private void updateDsContrDtlForAggLine(NSZC046001PMsg param) {
        BigDecimal basePrcDealAmt = BigDecimal.ZERO;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(param.xxContrDtlList.no(i).basePrcDealAmt)) {
                basePrcDealAmt = basePrcDealAmt.add(param.xxContrDtlList.no(i).basePrcDealAmt.getValue());
            }
        }

        BigDecimal dsContrDtlPk = getDsContrDtlInfoForAggLine(param.glblCmpyCd.getValue(), param.dsContrPk.getValue());
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return;
        }

        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        setValue(inParam.dsContrDtlPk, dsContrDtlPk);
        inParam = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        basePrcDealAmt = inParam.basePrcDealAmt.getValue().add(basePrcDealAmt);
        setValue(inParam.basePrcDealAmt, basePrcDealAmt);
        S21ApiTBLAccessor.update(inParam);
    }
    // add end 2016/10/31 QC#13299

    private void renewalDsContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);

        inParam = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        ZYPEZDItemValueSetter.setValue(inParam.rnwEffFromDt, ZYPDateUtil.addDays(inParam.contrEffThruDt.getValue(), 1));
        ZYPEZDItemValueSetter.setValue(inParam.contrEffThruDt, paramDtl.contrEffThruDt);
        BigDecimal rnwCnt = null;
        // mod start 2016/04/11 CSA Defect#5039
        if (ZYPCommonFunc.hasValue(inParam.contrRnwTotCnt)) {
            rnwCnt = inParam.contrRnwTotCnt.getValue().add(BigDecimal.ONE);
        } else {
            rnwCnt = BigDecimal.ONE;
        }
        // mod end 2016/04/11 CSA Defect#5039
        ZYPEZDItemValueSetter.setValue(inParam.contrRnwTotCnt, rnwCnt);

        S21ApiTBLAccessor.update(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, inParam.dsContrDtlTpCd);
    }

    // QC#17645 Add
    private void renewalDsContrAddlChrg(S21ApiMessageMap msgMap, DS_CONTRTMsg dsContr, DS_CONTR_DTLTMsg dsContrDtl, String newEffThruDt, int mode) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // get additional charge data
        List<Map<String, Object>> addlChrgInfoList = null;
        if (mode == LVL_CONTRCT) {
            addlChrgInfoList = getAddlChrgInfo(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), null, param.slsDt.getValue());

        } else if(mode == LVL_CONTRCT_DTL) {
            addlChrgInfoList = getAddlChrgInfo(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), param.slsDt.getValue());
        }

        if(addlChrgInfoList == null || addlChrgInfoList.size() == 0) {
            return;
        }

        // get contract_end_date
        int contrEffThruDtVal = 0;
        if (mode == LVL_CONTRCT) {
            if (ZYPCommonFunc.hasValue(dsContr.contrVrsnEffThruDt)) {
                contrEffThruDtVal = new BigDecimal(dsContr.contrVrsnEffThruDt.getValue()).intValue();
            }
        } else if(mode == LVL_CONTRCT_DTL) {
            if (ZYPCommonFunc.hasValue(dsContrDtl.contrEffThruDt)) {
                contrEffThruDtVal = new BigDecimal(dsContrDtl.contrEffThruDt.getValue()).intValue();
            }
        }

        for (Map<String, Object> addlChrgInfo : addlChrgInfoList) {

            BigDecimal dsContrAddlChrgPk = (BigDecimal)addlChrgInfo.get("DS_CONTR_ADDL_CHRG_PK");
            String addChrgEffThruDt = (String)addlChrgInfo.get("EFF_THRU_DT");

            // Do not process if the end date of additional charge is before contract end date
            if (ZYPCommonFunc.hasValue(addChrgEffThruDt)) {
                int addChrgEffThruDtVal = new BigDecimal(addChrgEffThruDt).intValue();

                if (addChrgEffThruDtVal < contrEffThruDtVal) {
                    continue;
                }
            }

            // markup caluclation start
            BigDecimal markedUpPrice = null;
            BigDecimal addlChrgFlatDealPrcAmt = (BigDecimal)addlChrgInfo.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT");
            String  addlChrgInvTpCd = (String)addlChrgInfo.get("ADDL_CHRG_INV_TP_CD");
            if ((ZYPCommonFunc.hasValue(addlChrgFlatDealPrcAmt)) 
                    && (addlChrgFlatDealPrcAmt.compareTo(BigDecimal.ZERO) != 0 )) {

                Map<String, Object> prcUpRatioInfo = new HashMap<String, Object>();
                if (mode == LVL_CONTRCT) {
                    prcUpRatioInfo = getPrcUpRatio(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), null, param.slsDt.getValue(), null);
                } else if(mode == LVL_CONTRCT_DTL) {
                    prcUpRatioInfo = getPrcUpRatio(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), param.slsDt.getValue(), addlChrgInvTpCd);
                }

                if (prcUpRatioInfo != null) {
                    String rnwPrcMethCd = (String)prcUpRatioInfo.get("RNW_PRC_METH_CD");
                    String rnwBaseFlg = (String)prcUpRatioInfo.get("RNW_BASE_FLG");

                    BigDecimal prcUpRatio = null;

                    if (ZYPCommonFunc.hasValue(rnwPrcMethCd)) {

                        if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd) && ZYPConstant.FLG_ON_Y.equals(rnwBaseFlg)) {

                            if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) {
                                prcUpRatio = (BigDecimal)prcUpRatioInfo.get("BASE_PRC_UP_RATIO");
                            } else if (ADDL_CHRG_INV_TP.USAGE.equals(addlChrgInvTpCd)) {
                                prcUpRatio = (BigDecimal)prcUpRatioInfo.get("MTR_PRC_UP_RATIO");
                            }
                        }
                        else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd) && ZYPConstant.FLG_ON_Y.equals(rnwBaseFlg)) {

                            if (mode == LVL_CONTRCT) {
                                // do Nothing
                                prcUpRatio = null;
                            } else if(mode == LVL_CONTRCT_DTL) {
                                BigDecimal uplftBasePrcUpRatio = getUplftBasePrcUpRatio(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), param.slsDt.getValue());
                                if (ZYPCommonFunc.hasValue(uplftBasePrcUpRatio)) {
                                    prcUpRatio = uplftBasePrcUpRatio ;
                                }
                            }
                        }
                        // markup
                        if (ZYPCommonFunc.hasValue(prcUpRatio)) {
                            markedUpPrice = addlChrgFlatDealPrcAmt.add(addlChrgFlatDealPrcAmt.multiply(prcUpRatio).divide(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP);
                        }
                    }
                }
            }
            // markup caluclation end

            DS_CONTR_ADDL_CHRGTMsg inParam = new DS_CONTR_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrAddlChrgPk, dsContrAddlChrgPk);
            inParam = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKey(inParam);

            BigDecimal addlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_ADDL_CHRG_SQ");
            ZYPEZDItemValueSetter.setValue(inParam.dsContrAddlChrgPk, addlPk);
            ZYPEZDItemValueSetter.setValue(inParam.effFromDt, ZYPDateUtil.addDays(addChrgEffThruDt, 1));
            ZYPEZDItemValueSetter.setValue(inParam.effThruDt, newEffThruDt);

            if (ZYPCommonFunc.hasValue(markedUpPrice)) {
                ZYPEZDItemValueSetter.setValue(inParam.addlChrgFlatDealPrcAmt, markedUpPrice);
            }

            S21ApiTBLAccessor.insert(inParam);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
                return;
            }

//            S21ApiTBLAccessor.update(inParam);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
//                ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
//            }
        }

    }

    private void createDsContrBllgMtr(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList) {

        for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
            createDsContrBllgMtr(param, paramDtl, bllgMtr);
        }
    }

    private void createDsContrBllgMtr(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {

        BigDecimal bllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_BLLG_MTR_SQ);
        DS_CONTR_BLLG_MTRTMsg inParam = new DS_CONTR_BLLG_MTRTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtrPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLbCd, bllgMtr.bllgMtrLbCd);
        ZYPEZDItemValueSetter.setValue(inParam.bllgMtrBillToCustCd, bllgMtr.bllgMtrBillToCustCd);
        ZYPEZDItemValueSetter.setValue(inParam.bllgMtrBllgCycleCd, bllgMtr.bllgMtrBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(inParam.xsChrgTpCd, bllgMtr.xsChrgTpCd);
        if (ZYPCommonFunc.hasValue(bllgMtr.dsContrBllgMtrStsCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrStsCd, bllgMtr.dsContrBllgMtrStsCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
        }

        ZYPEZDItemValueSetter.setValue(inParam.bllgMinCnt, bllgMtr.bllgMinCnt);
        ZYPEZDItemValueSetter.setValue(inParam.bllgMinAmtRate, bllgMtr.bllgMinAmtRate);
        ZYPEZDItemValueSetter.setValue(inParam.bllgFreeCopyCnt, bllgMtr.bllgFreeCopyCnt);
        if (ZYPCommonFunc.hasValue(bllgMtr.bllgFreeCopyCnt) && !ZYPCommonFunc.hasValue(bllgMtr.bllgRollOverRatio)) {
            ZYPEZDItemValueSetter.setValue(inParam.bllgRollOverRatio, BigDecimal.valueOf(100));
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.bllgRollOverRatio, bllgMtr.bllgRollOverRatio);
        }
        ZYPEZDItemValueSetter.setValue(inParam.ctacPsnPk, bllgMtr.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(inParam.rollOverCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inParam.intgMdseCd, bllgMtr.intgMdseCd);
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.mtrHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.contrHldFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.bllgHldFlg, FLG_OFF_N);
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/01/18 T.Tomita [QC#1088, ADD]
        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(bllgMtr.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(bllgMtr.dsContrBllgMtrPk, bllgMtrPk);
    }

    private void updateDsContrBllgMtr(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {

        String contrSts = decisionDsContrBllgMtrStsCd(param, paramDtl, bllgMtr);
        if (ZYPCommonFunc.hasValue(contrSts)) {
            return;
        }

        DS_CONTR_BLLG_MTRTMsg inParam = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtr.dsContrBllgMtrPk);

        inParam = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        EZDMsg.copy(bllgMtr, null, inParam, null);
        if (DS_CONTR_CTRL_STS.QA_HOLD.equals(contrSts)) {
            ZYPEZDItemValueSetter.setValue(inParam.qltyAsrnHldFlg, FLG_ON_Y);
        }

        S21ApiTBLAccessor.update(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
    }

    private void deleteDsContrBllgMtr(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {

        DS_CONTR_BLLG_MTRTMsg inParam = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtr.dsContrBllgMtrPk);

        inParam = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        S21ApiTBLAccessor.logicalRemove(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
    }

    private void cancelDsContrBllgMtrByContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTR_BLLG_MTRTMsg inParam = new DS_CONTR_BLLG_MTRTMsg();
        inParam.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inParam.setConditionValue("dsContrDtlPk01", paramDtl.dsContrDtlPk.getValue());
        inParam.setMaxCount(0);
        inParam.setSQLID("001");

        DS_CONTR_BLLG_MTRTMsgArray bllgMtrList = (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inParam);

        if (bllgMtrList == null || bllgMtrList.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < bllgMtrList.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg bllgMtr = bllgMtrList.no(i);
            ZYPEZDItemValueSetter.setValue(bllgMtr.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(bllgMtr.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bllgMtr.contrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bllgMtr.bllgHldFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.update(bllgMtr);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bllgMtr.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
            }
        }
    }

    private void terminateDsContrBllgMtrByContrDtl(S21ApiMessageMap msgMap, NSZC046001_xxContrDtlListPMsg paramDtl) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(paramDtl.contrCloDt)) {
            return;
        }
        if (param.slsDt.getValue().compareTo(paramDtl.contrCloDt.getValue()) < 0) {
            return;
        }

        DS_CONTR_BLLG_MTRTMsg inParam = new DS_CONTR_BLLG_MTRTMsg();
        inParam.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inParam.setConditionValue("dsContrDtlPk01", paramDtl.dsContrDtlPk.getValue());
        inParam.setMaxCount(0);
        inParam.setSQLID("001");

        DS_CONTR_BLLG_MTRTMsgArray bllgMtrList = (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inParam);

        if (bllgMtrList == null || bllgMtrList.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < bllgMtrList.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg bllgMtr = bllgMtrList.no(i);
            ZYPEZDItemValueSetter.setValue(bllgMtr.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(bllgMtr.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bllgMtr.contrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bllgMtr.bllgHldFlg, ZYPConstant.FLG_OFF_N);

            S21ApiTBLAccessor.update(bllgMtr);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bllgMtr.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
            }
        }
    }

    private void createContrPhysBllgMtrReln(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList) {

        for (NSZC046001_xxContrPhysBllgMtrRelnListPMsg paramReln : relnList) {
            BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CONTR_PHYS_BLLG_MTR_RELN_SQ);
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = getBllgMtrInfo(param, paramReln);

            CONTR_PHYS_BLLG_MTR_RELNTMsg inParam = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);

            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, paramDtl.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(inParam.machMstrPk, paramReln.machMstrPk);
            ZYPEZDItemValueSetter.setValue(inParam.contrMtrMultRate, paramReln.contrMtrMultRate);
            ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLbCd, paramReln.bllgMtrLbCd);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtr.dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(inParam.svcPhysMtrPk, paramReln.svcPhysMtrPk);
            ZYPEZDItemValueSetter.setValue(inParam.bllblFlg, paramReln.bllblFlg);

            if (!ZYPCommonFunc.hasValue(paramReln.bllgMtrLvlNum)) {
                // mod start 2016/08/25 CSA Defect#12136
//                MTR_LBTMsg mtrLb = (MTR_LBTMsg) NSZC046001CommonLogic.getCodeTbl(MTR_LB.class.getSimpleName()
//                        , param.glblCmpyCd.getValue(), paramReln.bllgMtrLbCd.getValue());
                // START 2016/09/28 T.Kanasaka [QC#9905, Mod]
                String bllgMtrLvl = NSZC046001CommonLogic.getBllgMtrLvl(param, param.glblCmpyCd.getValue(), param.slsDt.getValue(), paramReln.svcPhysMtrPk.getValue(), paramReln.bllgMtrLbCd.getValue());
                // END   2016/09/28 T.Kanasaka [QC#9905, Mod]
                ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLvlNum, bllgMtrLvl);
                // mod end 2016/08/25 CSA Defect#12136
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLvlNum, paramReln.bllgMtrLvlNum);
            }

            // START 2017/04/26 [RS#7238, ADD]
            ZYPEZDItemValueSetter.setValue(inParam.actvFlg, ZYPConstant.FLG_ON_Y);
            // END   2017/04/26 [RS#7238, ADD]

            S21ApiTBLAccessor.insert(inParam);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(paramReln.xxMsgId, NSZM0391E);
            }
            ZYPEZDItemValueSetter.setValue(paramReln.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
        }
    }

    private void updateContrPhysBllgMtrReln(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl
            , List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList) {

        for (NSZC046001_xxContrPhysBllgMtrRelnListPMsg paramReln : relnList) {

            CONTR_PHYS_BLLG_MTR_RELNTMsg inParam = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.contrPhysBllgMtrRelnPk, paramReln.contrPhysBllgMtrRelnPk);

            inParam = (CONTR_PHYS_BLLG_MTR_RELNTMsg) S21ApiTBLAccessor.findByKey(inParam);
            EZDMsg.copy(paramReln, null, inParam, null);

            if (!ZYPCommonFunc.hasValue(paramReln.bllgMtrLvlNum)) {
                // mod start 2016/08/25 CSA Defect#12136
//                MTR_LBTMsg mtrLb = (MTR_LBTMsg) NSZC046001CommonLogic.getCodeTbl(MTR_LB.class.getSimpleName()
//                        , param.glblCmpyCd.getValue(), paramReln.bllgMtrLbCd.getValue());
                // START 2016/09/28 T.Kanasaka [QC#9905, Mod]
                String bllgMtrLvl = NSZC046001CommonLogic.getBllgMtrLvl(param, param.glblCmpyCd.getValue(), param.slsDt.getValue(), paramReln.svcPhysMtrPk.getValue(), paramReln.bllgMtrLbCd.getValue());
                // END   2016/09/28 T.Kanasaka [QC#9905, Mod]
                ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLvlNum, bllgMtrLvl);
                // mod end 2016/08/25 CSA Defect#12136
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.bllgMtrLvlNum, paramReln.bllgMtrLvlNum);
            }

            S21ApiTBLAccessor.update(inParam);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(paramReln.xxMsgId, NSZM0391E);
            }
        }
    }

    private void createContrXsCopy(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr
            , List<NSZC046001_xxContrXsCopyListPMsg> xsList) {

        // sort by XS_MTR_COPY_QTY
        NSZC046001CommonLogic.sortXsCopy(xsList);

        for (int i = 0; i < xsList.size(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsCopy = xsList.get(i);
            BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CONTR_XS_COPY_SQ);

            CONTR_XS_COPYTMsg inParam = new CONTR_XS_COPYTMsg();
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.contrXsCopyPk, contrXsCopyPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtr.dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(inParam.xsMtrCopyQty, xsCopy.xsMtrCopyQty);
            ZYPEZDItemValueSetter.setValue(inParam.xsMtrAmtRate, xsCopy.xsMtrAmtRate);
            if (ZYPCommonFunc.hasValue(xsCopy.xsMtrFirstFlg)) {
                ZYPEZDItemValueSetter.setValue(inParam.xsMtrFirstFlg, xsCopy.xsMtrFirstFlg);
            } else {
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(inParam.xsMtrFirstFlg, FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(inParam.xsMtrFirstFlg, FLG_OFF_N);
                }
            }

            S21ApiTBLAccessor.insert(inParam);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(xsCopy.xxMsgId, NSZM0391E);
            }
            ZYPEZDItemValueSetter.setValue(xsCopy.contrXsCopyPk, contrXsCopyPk);
        }
    }

    private void createContrXsCopy(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr
            , NSZC046001_xxContrXsCopyListPMsg xsCopy) {

        BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CONTR_XS_COPY_SQ);

        CONTR_XS_COPYTMsg inParam = new CONTR_XS_COPYTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.contrXsCopyPk, contrXsCopyPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMtr.dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(inParam.xsMtrCopyQty, xsCopy.xsMtrCopyQty);
        ZYPEZDItemValueSetter.setValue(inParam.xsMtrAmtRate, xsCopy.xsMtrAmtRate);
        ZYPEZDItemValueSetter.setValue(inParam.xsMtrFirstFlg, xsCopy.xsMtrFirstFlg);

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(xsCopy.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(xsCopy.contrXsCopyPk, contrXsCopyPk);
    }

    private void updateContrXsCopy(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr
            , NSZC046001_xxContrXsCopyListPMsg xsCopy) {


        CONTR_XS_COPYTMsg inParam = new CONTR_XS_COPYTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.contrXsCopyPk, xsCopy.contrXsCopyPk);

        inParam = (CONTR_XS_COPYTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(xsCopy, null, inParam, null);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(xsCopy.xxMsgId, NSZM0391E);
        }
    }

    private void deleteContrXsCopy(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr
            , NSZC046001_xxContrXsCopyListPMsg xsCopy) {


        CONTR_XS_COPYTMsg inParam = new CONTR_XS_COPYTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.contrXsCopyPk, xsCopy.contrXsCopyPk);

        inParam = (CONTR_XS_COPYTMsg) S21ApiTBLAccessor.findByKey(inParam);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(xsCopy.xxMsgId, NSZM0391E);
        }
    }

    private void createCcCardPo(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg ccMsg = param.xxDsContrCrCardPoList.no(i);
            createCcCardPo(param, ccMsg);
        }
    }

    private void createCcCardPo(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {
        BigDecimal ccCardPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_CR_CARD_PO_SQ);

        DS_CONTR_CR_CARD_POTMsg inParam = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrCrCardPoPk, ccCardPk);

        if (ZYPCommonFunc.hasValue(ccMsg.svcMachMstrPk) && ZYPCommonFunc.hasValue(ccMsg.bllgMtrLbCd)) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMsg = getBllgMtrInfo(param, ccMsg);
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, ccMsg.svcMachMstrPk.getValue());
            if (bllgMsg != null) {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, bllgMsg.dsContrBllgMtrPk);
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgMtrPk, ccMsg.dsContrBllgMtrPk);
            }
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "3");
        } else if (ZYPCommonFunc.hasValue(ccMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, ccMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "2");
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "1");
        }

        ZYPEZDItemValueSetter.setValue(inParam.crCardCustRefNum, ccMsg.crCardCustRefNum);

        if (ZYPCommonFunc.hasValue(ccMsg.crCardExprYrMth)) {
            ZYPEZDItemValueSetter.setValue(inParam.crCardExprYrMth, ccMsg.crCardExprYrMth);
        } else {
            // mod start 2016/09/02 CSA Defect#11243
            if (ZYPCommonFunc.hasValue(ccMsg.crCardCustRefNum)) {
                DS_CR_CARDTMsg crCard = NSZC046001CommonLogic.getCrCard(param.glblCmpyCd.getValue(), ccMsg.crCardCustRefNum.getValue());
                ZYPEZDItemValueSetter.setValue(inParam.crCardExprYrMth, crCard.crCardExprYrMth);
            }
            // mod end 2016/09/02 CSA Defect#11243
        }

        ZYPEZDItemValueSetter.setValue(inParam.custPoNum, ccMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(inParam.poDt, ccMsg.poDt);
        if (ZYPCommonFunc.hasValue(ccMsg.leaseCmpyFlg)) {
            ZYPEZDItemValueSetter.setValue(inParam.leaseCmpyFlg, ccMsg.leaseCmpyFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        }

        S21ApiTBLAccessor.insert(inParam);

        ZYPEZDItemValueSetter.setValue(ccMsg.dsContrCrCardPoPk, ccCardPk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
            return;
        }
        // add start 2016/09/02 CSA Defect#11243
        if (ZYPCommonFunc.hasValue(inParam.crCardCustRefNum.getValue())) {
            callCreditCardValidationApiForSave(param, ccMsg, inParam);
        } else {
            callCreditCardValidationApiForVoid(param, ccMsg, inParam);
        }
        // add end 2016/09/02 CSA Defect#11243
    }

    private void updateCcCardPo(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {

        DS_CONTR_CR_CARD_POTMsg inParam = new DS_CONTR_CR_CARD_POTMsg();
        boolean isChangePOInfo = false;

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrCrCardPoPk, ccMsg.dsContrCrCardPoPk);

        inParam = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKey(inParam);

        if (compareValue(ccMsg.custPoNum.getValue(), inParam.custPoNum.getValue())) {
            isChangePOInfo = true;
        }
        if (compareValue(ccMsg.poDt.getValue(), inParam.poDt.getValue())) {
            isChangePOInfo = true;
        }
        EZDMsg.copy(ccMsg, null, inParam, null);
        if (ZYPCommonFunc.hasValue(ccMsg.leaseCmpyFlg)) {
            ZYPEZDItemValueSetter.setValue(inParam.leaseCmpyFlg, ccMsg.leaseCmpyFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        }

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
            return;
        }
        // add start 2016/09/02 CSA Defect#11243
        if (ZYPCommonFunc.hasValue(inParam.crCardCustRefNum.getValue())) {
            callCreditCardValidationApiForSave(param, ccMsg, inParam);
        } else {
            callCreditCardValidationApiForVoid(param, ccMsg, inParam);
        }
        // add end 2016/09/02 CSA Defect#11243

        // release renewal hold
        if (!isChangePOInfo) {
            return;
        }

        List<Map<String, Object>> rnwHoldPeList = getRnwHoldContr(param.glblCmpyCd.getValue(), ccMsg.dsContrDtlPk.getValue());
        if (rnwHoldPeList == null || rnwHoldPeList.isEmpty()) {
            return;
        }
        for (Map<String, Object> rnwHoldPe : rnwHoldPeList) {
            if (FLG_ON_Y.equals((String) rnwHoldPe.get("BASE_CHRG_FLG"))) {
                // START 2017/10/05 M.Kidokoro [QC#21544, MOD]
//                List<String> poDtList = getDsCrCard(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), ccMsg.dsContrDtlPk.getValue(), null);
                List<String> poDtList = getPoDtListForBase(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), ccMsg.dsContrDtlPk.getValue());
                // END 2017/10/05 M.Kidokoro [QC#21544, MOD]
                for (String poDt : poDtList) {
                    String peEffThruDt = (String) rnwHoldPe.get("CONTR_PRC_EFF_THRU_DT");
                    if (ZYPCommonFunc.hasValue(poDt) && poDt.compareTo(peEffThruDt) >= 0) {
                        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffPk, (BigDecimal) rnwHoldPe.get("DS_CONTR_PRC_EFF_PK"));
                        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
                        S21ApiTBLAccessor.update(inMsg);
                    }
                }
            }
            if (FLG_ON_Y.equals((String) rnwHoldPe.get("USG_CHRG_FLG"))) {
                // START 2017/10/05 M.Kidokoro [QC#21544, MOD]
//                List<String> poDtList = getDsCrCard(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), ccMsg.dsContrDtlPk.getValue(), ccMsg.dsContrBllgMtrPk.getValue());
                List<String> poDtList = getPoDtListForUsg(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), ccMsg.dsContrDtlPk.getValue(), ccMsg.dsContrBllgMtrPk.getValue());
                // END 2017/10/05 M.Kidokoro [QC#21544, MOD]
                for (String poDt : poDtList) {
                    String peEffThruDt = (String) rnwHoldPe.get("CONTR_PRC_EFF_THRU_DT");
                    if (ZYPCommonFunc.hasValue(poDt) && poDt.compareTo(peEffThruDt) >= 0) {
                        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffPk, (BigDecimal) rnwHoldPe.get("DS_CONTR_PRC_EFF_PK"));
                        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
                        S21ApiTBLAccessor.update(inMsg);
                    }
                }
            }
        }
    }

    private void deleteCcCardPo(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {

        DS_CONTR_CR_CARD_POTMsg inParam = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrCrCardPoPk, ccMsg.dsContrCrCardPoPk);

        inParam = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKey(inParam);
        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
            return;
        }
        // add start 2016/09/02 CSA Defect#11243
        callCreditCardValidationApiForVoid(param, ccMsg, inParam);
        // add end 2016/09/02 CSA Defect#11243
    }

    // add start 2016/09/02 CSA Defect#11243
    private void callCreditCardValidationApiForSave(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg) {
        // get CrCardTrx
        List<BigDecimal> crCardTrxPkList = NSZC046001CommonLogic.getCrCardTrxPk(param, dsContrCrCardPoTMsg);

        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            // insert CrCardTrx
            NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.crCardCustRefNum, dsContrCrCardPoTMsg.crCardCustRefNum);
            setValue(apiPMsg.sellToCustCd, NSZC046001CommonLogic.getSellToCustCd(param, dsContrCrCardPoTMsg));
            setValue(apiPMsg.crCardTrxTpCd, NSZC046001CommonLogic.getCrCardTrxTp(dsContrCrCardPoTMsg));
            setValue(apiPMsg.firstTrxInfoNum, dsContrCrCardPoTMsg.dsContrPk);
            setValue(apiPMsg.scdTrxInfoNum, dsContrCrCardPoTMsg.dsContrDtlPk);
            setValue(apiPMsg.thirdTrxInfoNum, dsContrCrCardPoTMsg.dsContrBllgMtrPk);
            setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);

            new NWZC203001().execute(apiPMsg, this.onBatchTp);
            if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
                ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
            }
        } else {
            // update CrCardTrx
            for (BigDecimal crCardTrxPk : crCardTrxPkList) {
                CR_CARD_TRXTMsg crCardTrxTMsg = NSZC046001CommonLogic.getCrCardTrx(param.glblCmpyCd.getValue(), crCardTrxPk);
                if (crCardTrxTMsg == null) {
                    continue;
                }
                NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
                EZDMsg.copy(crCardTrxTMsg, null, apiPMsg, null);
                setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
                setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
                setValue(apiPMsg.slsDt, param.slsDt);
                setValue(apiPMsg.crCardCustRefNum, dsContrCrCardPoTMsg.crCardCustRefNum);
                setValue(apiPMsg.sellToCustCd, NSZC046001CommonLogic.getSellToCustCd(param, dsContrCrCardPoTMsg));
                setValue(apiPMsg.crCardTrxTpCd, NSZC046001CommonLogic.getCrCardTrxTp(dsContrCrCardPoTMsg));
                setValue(apiPMsg.firstTrxInfoNum, dsContrCrCardPoTMsg.dsContrPk);
                setValue(apiPMsg.scdTrxInfoNum, dsContrCrCardPoTMsg.dsContrDtlPk);
                setValue(apiPMsg.thirdTrxInfoNum, dsContrCrCardPoTMsg.dsContrBllgMtrPk);
                setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
                setValue(apiPMsg.xxPmtcProcStsCd, crCardTrxTMsg.crCardTrxProcStsCd);
                setValue(apiPMsg.xxPmtcApvlStsNum, crCardTrxTMsg.crCardTrxApvlStsCd);
                setValue(apiPMsg.xxPmtcTrxRefIdxCd, crCardTrxTMsg.crCardRefIdxNum);

                new NWZC203001().execute(apiPMsg, this.onBatchTp);
                if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
                    ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
                }
            }
        }
    }

    private void callCreditCardValidationApiForVoid(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg) {
        // get CrCardTrx
        List<BigDecimal> crCardTrxPkList = NSZC046001CommonLogic.getCrCardTrxPk(param, dsContrCrCardPoTMsg);
        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            return;
        }

        // cancel CrCardTrx
        for (BigDecimal crCardTrxPk : crCardTrxPkList) {
            CR_CARD_TRXTMsg crCardTrxTMsg = NSZC046001CommonLogic.getCrCardTrx(param.glblCmpyCd.getValue(), crCardTrxPk);
            if (crCardTrxTMsg == null) {
                continue;
            }
            NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
            EZDMsg.copy(crCardTrxTMsg, null, apiPMsg, null);
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.sellToCustCd, crCardTrxTMsg.billToCustAcctCd);
            setValue(apiPMsg.crCardTrxTpCd, NSZC046001CommonLogic.getCrCardTrxTp(dsContrCrCardPoTMsg));
            setValue(apiPMsg.firstTrxInfoNum, dsContrCrCardPoTMsg.dsContrPk);
            setValue(apiPMsg.scdTrxInfoNum, dsContrCrCardPoTMsg.dsContrDtlPk);
            setValue(apiPMsg.thirdTrxInfoNum, dsContrCrCardPoTMsg.dsContrBllgMtrPk);
            setValue(apiPMsg.crCardCancDt, param.slsDt);
            setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
            setValue(apiPMsg.xxPmtcProcStsCd, crCardTrxTMsg.crCardTrxProcStsCd);
            setValue(apiPMsg.xxPmtcApvlStsNum, crCardTrxTMsg.crCardTrxApvlStsCd);
            setValue(apiPMsg.xxPmtcTrxRefIdxCd, crCardTrxTMsg.crCardRefIdxNum);

            new NWZC203001().execute(apiPMsg, this.onBatchTp);
            if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
                ZYPEZDItemValueSetter.setValue(ccMsg.xxMsgId, NSZM0391E);
            }
        }
    }
   // add end 2016/09/02 CSA Defect#11243

    private void createRnwEscl(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrRnwEsclList.getValidCount(); i++) {
            NSZC046001_xxDsContrRnwEsclListPMsg  rnwMsg = param.xxDsContrRnwEsclList.no(i);
            createRnwEscl(param, rnwMsg);
        }
    }

    private void createRnwEscl(NSZC046001PMsg param, NSZC046001_xxDsContrRnwEsclListPMsg rnwMsg) {
        BigDecimal rnwPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_RNW_ESCL_SQ);

        DS_CONTR_RNW_ESCLTMsg inParam = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrRnwEsclPk, rnwPk);

        if (ZYPCommonFunc.hasValue(rnwMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, rnwMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
            if (ZYPCommonFunc.hasValue(rnwMsg.dsContrBaseUsgNm)) {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "3");
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "2");
            }
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, "1");
        }

        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        if (ZYPCommonFunc.hasValue(rnwMsg.dsContrMachLvlNum)) {
            ZYPEZDItemValueSetter.setValue(inParam.dsContrMachLvlNum, rnwMsg.dsContrMachLvlNum);
        }

        ZYPEZDItemValueSetter.setValue(inParam.dsContrBaseUsgNm, rnwMsg.dsContrBaseUsgNm);
        ZYPEZDItemValueSetter.setValue(inParam.contrAutoRnwTpCd, rnwMsg.contrAutoRnwTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.rnwPrcMethCd, rnwMsg.rnwPrcMethCd);
        ZYPEZDItemValueSetter.setValue(inParam.befEndRnwDaysAot, rnwMsg.befEndRnwDaysAot);
        ZYPEZDItemValueSetter.setValue(inParam.basePrcUpRatio, rnwMsg.basePrcUpRatio);
        ZYPEZDItemValueSetter.setValue(inParam.mtrPrcUpRatio, rnwMsg.mtrPrcUpRatio);
        ZYPEZDItemValueSetter.setValue(inParam.contrUplftTpCd, rnwMsg.contrUplftTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.uplftPrcMethCd, rnwMsg.uplftPrcMethCd);
        ZYPEZDItemValueSetter.setValue(inParam.uplftBasePrcUpRatio, rnwMsg.uplftBasePrcUpRatio);
        ZYPEZDItemValueSetter.setValue(inParam.uplftMtrPrcUpRatio, rnwMsg.uplftMtrPrcUpRatio);
        ZYPEZDItemValueSetter.setValue(inParam.firstYrContrUplftFlg, rnwMsg.firstYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.scdYrContrUplftFlg, rnwMsg.scdYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.thirdYrContrUplftFlg, rnwMsg.thirdYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.frthYrContrUplftFlg, rnwMsg.frthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.fifthYrContrUplftFlg, rnwMsg.fifthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.sixthYrContrUplftFlg, rnwMsg.sixthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.svnthYrContrUplftFlg, rnwMsg.svnthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.eighthYrContrUplftFlg, rnwMsg.eighthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.ninthYrContrUplftFlg, rnwMsg.ninthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.tenthYrContrUplftFlg, rnwMsg.tenthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(inParam.uplftBefEndRnwDaysAot, rnwMsg.uplftBefEndRnwDaysAot);

        // START 2017/09/06 M.Naito [QC#18724, ADD]
        setUplftFlgFromPlnTp(inParam, param.glblCmpyCd.getValue(), param.dsContrPk.getValue());
        // END 2017/09/06 M.Naito [QC#18724, ADD]

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(rnwMsg.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(rnwMsg.dsContrRnwEsclPk, rnwPk);
    }

    private void updateRnwEscl(NSZC046001PMsg param, NSZC046001_xxDsContrRnwEsclListPMsg rnwMsg) {

        DS_CONTR_RNW_ESCLTMsg inParam = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrRnwEsclPk, rnwMsg.dsContrRnwEsclPk);

        inParam = (DS_CONTR_RNW_ESCLTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(rnwMsg, null, inParam, null);

        // START 2017/09/06 M.Naito [QC#18724, ADD]
        setUplftFlgFromPlnTp(inParam, param.glblCmpyCd.getValue(), rnwMsg.dsContrPk.getValue());
        // END 2017/09/06 M.Naito [QC#18724, ADD]

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(rnwMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteRnwEscl(NSZC046001PMsg param, NSZC046001_xxDsContrRnwEsclListPMsg rnwMsg) {

        DS_CONTR_RNW_ESCLTMsg inParam = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrRnwEsclPk, rnwMsg.dsContrRnwEsclPk);

        inParam = (DS_CONTR_RNW_ESCLTMsg) S21ApiTBLAccessor.findByKey(inParam);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(rnwMsg.xxMsgId, NSZM0391E);
        }
    }

    private void createTermsAndConditions(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxSvcTermCondList.getValidCount(); i++) {
            NSZC046001_xxSvcTermCondListPMsg tAndCMsg = param.xxSvcTermCondList.no(i);
            createTermsAndConditions(param, tAndCMsg);
        }
    }

    private void createTermsAndConditions(NSZC046001PMsg param, NSZC046001_xxSvcTermCondListPMsg tAndCMsg) {

        BigDecimal tAndCPk = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_TERM_COND_SQ");

        SVC_TERM_CONDTMsg inParam = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondPk, tAndCPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        if (ZYPCommonFunc.hasValue(tAndCMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, tAndCMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        }
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondAttrbPk, tAndCMsg.svcTermCondAttrbPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermAttrbMapValCd, tAndCMsg.svcTermAttrbMapValCd);

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(tAndCMsg.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(tAndCMsg.svcTermCondPk, tAndCPk);
    }

    private void updateTermsAndConditions(NSZC046001PMsg param, NSZC046001_xxSvcTermCondListPMsg tAndCMsg) {

        SVC_TERM_CONDTMsg inParam = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondPk, tAndCMsg.svcTermCondPk);

        inParam = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(tAndCMsg, null, inParam, null);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(tAndCMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteTermsAndConditions(NSZC046001PMsg param, NSZC046001_xxSvcTermCondListPMsg tAndCMsg) {

        SVC_TERM_CONDTMsg inParam = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondPk, tAndCMsg.svcTermCondPk);

        inParam = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKey(inParam);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(tAndCMsg.xxMsgId, NSZM0391E);
        }
    }

    private void createAddlChrg(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addlMsg = param.xxDsContrAddlChrgList.no(i);
            createAddlChrg(param, addlMsg);
        }
    }

    private void createAddlChrg(NSZC046001PMsg param, NSZC046001_xxDsContrAddlChrgListPMsg addlMsg) {

        BigDecimal addlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_ADDL_CHRG_SQ");

        DS_CONTR_ADDL_CHRGTMsg inParam = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrAddlChrgPk, addlPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);

        if (ZYPCommonFunc.hasValue(addlMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, addlMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        }

        ZYPEZDItemValueSetter.setValue(inParam.addlChrgTpCd, addlMsg.addlChrgTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBillByTpCd, addlMsg.svcBillByTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.addlChrgInvTpCd, addlMsg.addlChrgInvTpCd);

        if (ZYPCommonFunc.hasValue(addlMsg.ccyCd)) {
            ZYPEZDItemValueSetter.setValue(inParam.ccyCd, addlMsg.ccyCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.ccyCd, getCcyCd(param.glblCmpyCd.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(inParam.addlChrgFlatDealPrcAmt, addlMsg.addlChrgFlatDealPrcAmt);
        ZYPEZDItemValueSetter.setValue(inParam.addlChrgAplcPct, addlMsg.addlChrgAplcPct);
        ZYPEZDItemValueSetter.setValue(inParam.printDtlFlg, addlMsg.printDtlFlg);
        ZYPEZDItemValueSetter.setValue(inParam.bllgCycleCd, addlMsg.bllgCycleCd);
        ZYPEZDItemValueSetter.setValue(inParam.effFromDt, addlMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(inParam.effThruDt, addlMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(inParam.trmnDt, addlMsg.trmnDt);
        ZYPEZDItemValueSetter.setValue(inParam.addlChrgInvdFlg, addlMsg.addlChrgInvdFlg);
        // Add Start 2018/03/15 QC#23429
        ZYPEZDItemValueSetter.setValue(inParam.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inParam.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        // Add End 2018/03/15 QC#23429
        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(addlMsg.xxMsgId, NSZM0391E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(addlMsg.dsContrAddlChrgPk, addlPk);
    }

    private void updateAddlChrg(NSZC046001PMsg param, NSZC046001_xxDsContrAddlChrgListPMsg addlMsg) {

        DS_CONTR_ADDL_CHRGTMsg inParam = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrAddlChrgPk, addlMsg.dsContrAddlChrgPk);

        inParam = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(addlMsg, null, inParam, null);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(addlMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteAddlChrg(NSZC046001PMsg param, NSZC046001_xxDsContrAddlChrgListPMsg addlMsg) {

        DS_CONTR_ADDL_CHRGTMsg inParam = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrAddlChrgPk, addlMsg.dsContrAddlChrgPk);

        inParam = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(addlMsg, null, inParam, null);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(addlMsg.xxMsgId, NSZM0391E);
        }
    }

    private void createBrAlloc(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            createBrAlloc(param, brMsg);
        }
    }

    private void createBrAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrBrAllocListPMsg brMsg) {

        BigDecimal brAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_BR_ALLOC_SQ");

        DS_CONTR_BR_ALLOCTMsg inParam = new DS_CONTR_BR_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBrAllocPk, brAllocPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        if (ZYPCommonFunc.hasValue(brMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, brMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        }

        ZYPEZDItemValueSetter.setValue(inParam.svcInvChrgTpCd, brMsg.svcInvChrgTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaBrCd, brMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(inParam.prcAllocRate, brMsg.prcAllocRate);

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(brMsg.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(brMsg.dsContrBrAllocPk, brAllocPk);
    }

    private void updateBrAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrBrAllocListPMsg brMsg) {

        DS_CONTR_BR_ALLOCTMsg inParam = new DS_CONTR_BR_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBrAllocPk, brMsg.dsContrBrAllocPk);

        inParam = (DS_CONTR_BR_ALLOCTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(brMsg, null, inParam, null);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(brMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteBrAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrBrAllocListPMsg brMsg) {

        DS_CONTR_BR_ALLOCTMsg inParam = new DS_CONTR_BR_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBrAllocPk, brMsg.dsContrBrAllocPk);

        inParam = (DS_CONTR_BR_ALLOCTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(brMsg, null, inParam, null);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(brMsg.xxMsgId, NSZM0391E);
        }
    }

    private void createSegAlloc(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            createSegAlloc(param, segMsg);
        }
    }

    private void createSegAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrSegAllocListPMsg segMsg) {

        BigDecimal segAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_SEG_ALLOC_SQ");

        DS_CONTR_SEG_ALLOCTMsg inParam = new DS_CONTR_SEG_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrSegAllocPk, segAllocPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        if (ZYPCommonFunc.hasValue(segMsg.svcMachMstrPk)) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, segMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        }
        ZYPEZDItemValueSetter.setValue(inParam.svcInvChrgTpCd, segMsg.svcInvChrgTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaCmpyCd, segMsg.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaAfflCd, segMsg.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaBrCd, segMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaChCd, segMsg.coaChCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaCcCd, segMsg.coaCcCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaAcctCd, segMsg.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaProdCd, segMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaProjCd, segMsg.coaProjCd);
        ZYPEZDItemValueSetter.setValue(inParam.coaExtnCd, segMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(inParam.prcAllocRate, segMsg.prcAllocRate);
        // START 2018/04/06 U.Kim [QC#23378(Sol320, ADD]
        ZYPEZDItemValueSetter.setValue(inParam.prcAllocAmt, segMsg.prcAllocAmt);
        // END 2018/04/06 U.Kim [QC#23378(Sol320, ADD]


        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(segMsg.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(segMsg.dsContrSegAllocPk, segAllocPk);
    }

    private void updateSegAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrSegAllocListPMsg segMsg) {

        DS_CONTR_SEG_ALLOCTMsg inParam = new DS_CONTR_SEG_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrSegAllocPk, segMsg.dsContrSegAllocPk);

        inParam = (DS_CONTR_SEG_ALLOCTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(segMsg, null, inParam, null);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(segMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteSegAlloc(NSZC046001PMsg param, NSZC046001_xxDsContrSegAllocListPMsg segMsg) {

        DS_CONTR_SEG_ALLOCTMsg inParam = new DS_CONTR_SEG_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrSegAllocPk, segMsg.dsContrSegAllocPk);

        inParam = (DS_CONTR_SEG_ALLOCTMsg) S21ApiTBLAccessor.findByKey(inParam);
        EZDMsg.copy(segMsg, null, inParam, null);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(segMsg.xxMsgId, NSZM0391E);
        }
    }

    private void createSvcMemo(NSZC046001PMsg param, NSZC046001_xxSvcMemoListPMsg memoMsg) {

        BigDecimal memoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ);

        SVC_MEMOTMsg inParam = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoPk, memoPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoCatgCd, memoMsg.svcMemoCatgCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTpCd, memoMsg.svcMemoTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcCmntTxt, memoMsg.svcCmntTxt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, memoMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, memoMsg.dsContrDtlPk);
        if (ZYPCommonFunc.hasValue(memoMsg.lastUpdUsrId)) {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdUsrId, memoMsg.lastUpdUsrId);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdUsrId, param.psnCd);
        }
        if (ZYPCommonFunc.hasValue(memoMsg.lastUpdTs)) {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdTs, memoMsg.lastUpdTs);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        }
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoRsnCd, memoMsg.svcMemoRsnCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTrxNum, memoMsg.svcMemoTrxNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTrxNm, memoMsg.svcMemoTrxNm);

        S21ApiTBLAccessor.insert(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(memoMsg.xxMsgId, NSZM0391E);
        }
        ZYPEZDItemValueSetter.setValue(memoMsg.svcMemoPk, memoPk);
    }

    private void updateSvcMemo(NSZC046001PMsg param, NSZC046001_xxSvcMemoListPMsg memoMsg) {

        SVC_MEMOTMsg inParam = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoPk, memoMsg.svcMemoPk);

        inParam = (SVC_MEMOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoCatgCd, memoMsg.svcMemoCatgCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTpCd, memoMsg.svcMemoTpCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcCmntTxt, memoMsg.svcCmntTxt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, memoMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, memoMsg.dsContrDtlPk);
        if (ZYPCommonFunc.hasValue(memoMsg.lastUpdUsrId)) {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdUsrId, memoMsg.lastUpdUsrId);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdUsrId, param.psnCd);
        }
        if (ZYPCommonFunc.hasValue(memoMsg.lastUpdTs)) {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdTs, memoMsg.lastUpdTs);
        } else {
            ZYPEZDItemValueSetter.setValue(inParam.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        }
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoRsnCd, memoMsg.svcMemoRsnCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTrxNum, memoMsg.svcMemoTrxNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTrxNm, memoMsg.svcMemoTrxNm);

        S21ApiTBLAccessor.update(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(memoMsg.xxMsgId, NSZM0391E);
        }
    }

    private void deleteSvcMemo(NSZC046001PMsg param, NSZC046001_xxSvcMemoListPMsg memoMsg) {

        SVC_MEMOTMsg inParam = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoPk, memoMsg.svcMemoPk);

        inParam = (SVC_MEMOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        S21ApiTBLAccessor.logicalRemove(inParam);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(memoMsg.xxMsgId, NSZM0391E);
        }
    }

    private void renewalSvcMemo(NSZC046001PMsg param) {

        for (int i = 0; i < param.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg memoMsg = param.xxSvcMemoList.no(i);
            if (!ZYPCommonFunc.hasValue(memoMsg.svcMemoPk)) {
                createSvcMemo(param, memoMsg);
            } else {
                updateSvcMemo(param, memoMsg);
            }
        }
    }

    private boolean setRenewalHoldForBase(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, NSZC047004PMsg outPMsg, ONBATCH_TYPE onBatchType) {

        Map<String, String> baseAcct = getBaseBillToAndAcct(param, dtlMsg);
        String baseBillTo = baseAcct.get("BILL_TO_CUST_CD");
        String dsAcct = baseAcct.get("DS_ACCT_NUM");

        boolean poReq = NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue()
                , param.slsDt.getValue(), dsAcct, baseBillTo, onBatchType);
        if (poReq) {
            // START 2019/11/06 K.Kitachi [QC#54368, MOD]
//            // START 2017/10/05 M.Kidokoro [QC#21544, MOD]
////            List<String> poDtList = getDsCrCard(param.glblCmpyCd.getValue()
////                    , param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue(), null);
//            List<String> poDtList = getPoDtListForBase(param.glblCmpyCd.getValue()
//                    , param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue());
//            // END 2017/10/05 M.Kidokoro [QC#21544, MOD]
//            boolean poExpired = true;
//            for (String poDt : poDtList) {
//                if (dtlMsg.contrEffThruDt.getValue().compareTo(poDt) <= 0) {
//                    poExpired = false;
//                    break;
//                }
//            }
            boolean poExpired = !isExistsPoInclRenewFromDate(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue(), null);
            // END 2019/11/06 K.Kitachi [QC#54368, MOD]
            // mod start 2016/05/13 CSA Defect#8233
            if (poExpired && ZYPCommonFunc.hasValue(outPMsg.dsContrPrcEffPk)) {
            // mod end   2016/05/13 CSA Defect#8233
                DS_CONTR_PRC_EFFTMsg prcEff = NSZC046001CommonLogic.getPrcEffForUpdate(param.glblCmpyCd.getValue(), outPMsg.dsContrPrcEffPk.getValue());
                ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);

                S21ApiTBLAccessor.update(prcEff);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean setRenewalHoldForUsg(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg,
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr, NSZC047004PMsg outPMsg, ONBATCH_TYPE onBatchType) {

        Map<String, String> usgAcct = getUsgBillToAndAcct(param, bllgMtr);
        String usgBillTo = usgAcct.get("BILL_TO_CUST_CD");
        String dsAcct = usgAcct.get("DS_ACCT_NUM");

        boolean poReq = NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue()
                , param.slsDt.getValue(), dsAcct, usgBillTo, onBatchType);
        if (poReq) {
            // START 2019/11/06 K.Kitachi [QC#54368, MOD]
//            // START 2017/10/05 M.Kidokoro [QC#21544, MOD]
////            List<String> poDtList = getDsCrCard(param.glblCmpyCd.getValue()
////                    , param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
//            List<String> poDtList = getPoDtListForUsg(param.glblCmpyCd.getValue()
//                    , param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
//            // END 2017/10/05 M.Kidokoro [QC#21544, MOD]
//            boolean poExpired = true;
//            for (String poDt : poDtList) {
//                if (dtlMsg.contrEffThruDt.getValue().compareTo(poDt) <= 0) {
//                    poExpired = false;
//                    break;
//                }
//            }
            boolean poExpired = !isExistsPoInclRenewFromDate(param.glblCmpyCd.getValue(), param.dsContrPk.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
            // END 2019/11/06 K.Kitachi [QC#54368, MOD]
            // mod start 2016/05/13 CSA Defect#8233
            if (poExpired && ZYPCommonFunc.hasValue(outPMsg.dsContrPrcEffPk)) {
            // mod end   2016/05/13 CSA Defect#8233
                DS_CONTR_PRC_EFFTMsg prcEff = NSZC046001CommonLogic.getPrcEffForUpdate(param.glblCmpyCd.getValue(), outPMsg.dsContrPrcEffPk.getValue());
                ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);

                S21ApiTBLAccessor.update(prcEff);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setInvUpDt(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl, List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList) {

        for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
            List<Map<String, Object>> bllgSchdList = getInvdBllgSchd(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue()
                    , bllgMtr.dsContrBllgMtrPk.getValue(), paramDtl.invUpToDt.getValue());

            for (Map<String, Object> bllgSchd : bllgSchdList) {
                if (!updateNextBllgDt(param.glblCmpyCd.getValue(), bllgSchd)) {
                    ZYPEZDItemValueSetter.setValue(bllgMtr.xxMsgId, NSZM0391E);
                }
            }
        }
    }

    private void setInvUpDt(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl) {

        List<Map<String, Object>> bllgSchdList = getInvdBllgSchd(param.glblCmpyCd.getValue()
                , paramDtl.dsContrDtlPk.getValue(), null, paramDtl.invUpToDt.getValue());

        for (Map<String, Object> bllgSchd : bllgSchdList) {
            if (!updateNextBllgDt(param.glblCmpyCd.getValue(), bllgSchd)) {
                ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
            }
        }
    }

    // mod start 2016/05/11 CSA Defect#3771
    private boolean updateNextBllgDt(String glblCmpyCd, Map<String, Object> bllgSchd) {

        BigDecimal pk = (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK");
//        String nextBllgDt = (String) bllgSchd.get("NEXT_BLLG_DT");

        DS_CONTR_BLLG_SCHDTMsg inParam = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrBllgSchdPk, pk);

        inParam = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);
//        ZYPEZDItemValueSetter.setValue(inParam.invDt, nextBllgDt);
        ZYPEZDItemValueSetter.setValue(inParam.invFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inParam.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);

        S21ApiTBLAccessor.update(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            return false;
        }
        return true;
    }
    // mod end   2016/05/11 CSA Defect#3771

    private void callBllgSchdApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // Add Start 2017/02/28 <QC#17809>
        boolean isBaseCalc = false;
        // Add End   2017/02/28 <QC#17809>
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);

            // START 2016/04/04 [QC#6487, ADD]
            if ((MODE_ADD_TO_CONTR.equals(param.xxModeCd.getValue()) && (DS_CONTR_DTL_TP.FLEET.equals(dtlMsg.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(dtlMsg.dsContrDtlTpCd.getValue())))) {
                dtlMsg = getDsContrDtlForAddToContract(param, dtlMsg.dsContrDtlTpCd.getValue());
            }
            // END   2016/04/04 [QC#6487, ADD]

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            // if (NSZC046001CommonLogic.isFleetMach(param, dtlMsg)) {
            if (NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
                continue;
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]

            if (NSZC046001CommonLogic.isBaseChrg(param, dtlMsg)) {
                // Add Start 2017/02/28 <QC#17809>
                isBaseCalc = true;
                // Add End   2017/02/28 <QC#17809>
                // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
                if(ZYPCommonFunc.hasValue(dtlMsg.oldDsContrDtlPk)) {
                    boolean isNoExchAcc = false;
                    peCheck : for (int j = 0; j < param.xxDsContrPrcEffList.getValidCount(); j++) {
                        if (param.xxDsContrPrcEffList.no(j).dsContrDtlPk.getValue().equals(dtlMsg.oldDsContrDtlPk.getValue())) {
                            isNoExchAcc = true;
                            break peCheck;
                        }
                    }
                    NSZC047008PMsg pMsg;
                    if (isNoExchAcc) {
                        pMsg = callBllgSchdApiForExchMultiPrcEffBaseNoExchAcc(param, dtlMsg, onBatchType);
                    } else {
                        pMsg = callBllgSchdApiForExchMultiPrcEffBase(param, dtlMsg, onBatchType);
                    }
                    if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                        for (S21ApiMessage msg : msgList) {
                            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                        }
                        return;
                    }
                } else {
                    // Add End   2019/04/15 K.Fujimoto QC#31137/50058
                    //set parameters for billing schedule api(base) and call the api.
                    // START 2017/07/10 K.Kojima [QC#19822,MOD]
                    // Map<String, Object> prfEffInfo = getPrcEffForBase(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue());
                    // START 2017/07/11 K.Kojima [QC#19822,MOD]
                    // Map<String, Object> prfEffInfo = null;
                    // if (DS_CONTR_DTL_TP.AGGREGATE.equals(dtlMsg.dsContrDtlTpCd.getValue())) {
                    //     prfEffInfo = getPrcEffForBaseForAggregateLine(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue());
                    // } else {
                    //     prfEffInfo = getPrcEffForBase(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue());
                    // }
                    Map<String, Object> prfEffInfo = getPrcEffForBase(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue());
                    // END 2017/07/11 K.Kojima [QC#19822,MOD]
                    // END 2017/07/10 K.Kojima [QC#19822,MOD]
                    DS_CONTR_DTLTMsg contrDtl = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());

                    NSZC047001PMsg pMsg = new NSZC047001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_CONTR_MODE);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, contrDtl.contrCloDay);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, contrDtl.baseBllgTmgCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, contrDtl.contrBllgDay);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, contrDtl.contrEffFromDt);
                    // START 2023/01/05 L.Mandanas [QC#60949, MOD]
                    //ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);
                    if (MODE_UPDATE.equals(param.xxModeCd.getValue()) && ZYPCommonFunc.hasValue(contrDtl.contrCloDt)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrCloDt);
                    }
                    else {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);
                    }
                    // END 2023/01/05 L.Mandanas [QC#60949, MOD]

                    NSZC047001_xxBaseLineListPMsg baseLine = pMsg.xxBaseLineList.no(0);
                    if (prfEffInfo != null && !prfEffInfo.isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffPk_BL, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_PK"));
                        ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffSqNum_BL, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                        ZYPEZDItemValueSetter.setValue(baseLine.effFromDt_BL, (String) prfEffInfo.get("CONTR_PRC_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(baseLine.effThruDt_BL, (String) prfEffInfo.get("CONTR_PRC_EFF_THRU_DT"));
                    }
                    ZYPEZDItemValueSetter.setValue(baseLine.baseBllgCycleCd_BL, contrDtl.baseBllgCycleCd);
                    ZYPEZDItemValueSetter.setValue(baseLine.basePrcDealAmt_BL, contrDtl.basePrcDealAmt);
                    ZYPEZDItemValueSetter.setValue(baseLine.basePrcTermDealAmtRate_BL, contrDtl.basePrcTermDealAmtRate);
                    pMsg.xxBaseLineList.setValidCount(1);

                    new NSZC047001().execute(pMsg, onBatchType);
                    if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                        for (S21ApiMessage msg : msgList) {
                            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                        }
                        return;
                    }
                }
            }
            if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
                List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = getBllgMtrInfo(param, dtlMsg);
                DS_CONTR_DTLTMsg contrDtl = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());

                for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
                    //set parameters for call billing schedule api(base) and call the api.
                    // START 2017/07/10 K.Kojima [QC#19822,MOD]
                    // Map<String, Object> prfEffInfo = getPrcEffForUsg(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
                    // START 2017/07/11 K.Kojima [QC#19822,MOD]
                    // Map<String, Object> prfEffInfo = null;
                    // if (DS_CONTR_DTL_TP.AGGREGATE.equals(dtlMsg.dsContrDtlTpCd.getValue())) {
                    //     prfEffInfo = getPrcEffForUsgForAggregateLine(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
                    // } else {
                    //     prfEffInfo = getPrcEffForUsg(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
                    // }

                    // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
                    if(ZYPCommonFunc.hasValue(dtlMsg.oldDsContrDtlPk)) {
                        NSZC047008PMsg pMsg = callBllgSchdApiForExchMultiPrcEffUsage(param, bllgMtr, contrDtl, dtlMsg, onBatchType);
                        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                            for (S21ApiMessage msg : msgList) {
                                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            }
                            return;
                        }
                    } else {
                        // Add End   2019/04/15 K.Fujimoto QC#31137/50058
                        Map<String, Object> prfEffInfo = getPrcEffForUsg(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
                        // END 2017/07/11 K.Kojima [QC#19822,MOD]
                        // END 2017/07/10 K.Kojima [QC#19822,MOD]
                        CONTR_XS_COPYTMsgArray xsList = NSZC046001CommonLogic.getXsCopy(param.glblCmpyCd.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());

                        NSZC047001PMsg pMsg = new NSZC047001PMsg();
                        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_CONTR_MODE);
                        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(pMsg.mtrCloDay, contrDtl.mtrCloDay);
                        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgTmgCd, contrDtl.mtrBllgTmgCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgDay, contrDtl.mtrBllgDay);
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, contrDtl.contrEffFromDt);
                        // START 2023/01/05 L.Mandanas [QC#60949, MOD]
                        //ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);
                        if (MODE_UPDATE.equals(param.xxModeCd.getValue()) && ZYPCommonFunc.hasValue(contrDtl.contrCloDt)) {
                            ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrCloDt);
                        }
                        else {
                            ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);
                        }
                        // END 2023/01/05 L.Mandanas [QC#60949, MOD]

                        int mtrIdx = 0;
                        for (; mtrIdx < xsList.getValidCount(); mtrIdx++) {
                            NSZC047001_xxMtrLineListPMsg mtrLine = pMsg.xxMtrLineList.no(mtrIdx);
                            if (prfEffInfo != null && !prfEffInfo.isEmpty()) {
                                // START 2016/03/31 Y.Tsuchimoto [QC#6339, MOD]
                                //ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffMtrPk_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_PK"));
                                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffPk_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_PK"));
                                // END   2016/03/31 Y.Tsuchimoto [QC#6339, MOD]
                                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffSqNum_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                                ZYPEZDItemValueSetter.setValue(mtrLine.effFromDt_ML, (String) prfEffInfo.get("CONTR_PRC_EFF_FROM_DT"));
                                ZYPEZDItemValueSetter.setValue(mtrLine.effThruDt_ML, (String) prfEffInfo.get("CONTR_PRC_EFF_THRU_DT"));
                            }
                            // START 2017/06/02 K.Kitachi [QC#18568, MOD]
//                            ZYPEZDItemValueSetter.setValue(mtrLine.mtrBllgCycleCd_ML, contrDtl.mtrBllgCycleCd);
                            ZYPEZDItemValueSetter.setValue(mtrLine.mtrBllgCycleCd_ML, bllgMtr.bllgMtrBllgCycleCd);
                            // END 2017/06/02 K.Kitachi [QC#18568, MOD]
                            ZYPEZDItemValueSetter.setValue(mtrLine.dsContrBllgMtrPk_ML, bllgMtr.dsContrBllgMtrPk);
                            ZYPEZDItemValueSetter.setValue(mtrLine.contrXsCopyPk_ML, xsList.no(mtrIdx).contrXsCopyPk);
                            ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrCopyQty_ML, xsList.no(mtrIdx).xsMtrCopyQty);
                            ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrAmtRate_ML, xsList.no(mtrIdx).xsMtrAmtRate);
                            ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrFirstFlg_ML, xsList.no(mtrIdx).xsMtrFirstFlg);
                        }
                        pMsg.xxMtrLineList.setValidCount(mtrIdx);

                        new NSZC047001().execute(pMsg, onBatchType);
                        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                            for (S21ApiMessage msg : msgList) {
                                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            }
                            return;
                        }
                    }
                }
            }
        }
        // Add Start 2017/02/28 <QC#17809>
        if (DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue()) && isBaseCalc) {
            sumAggregate(msgMap, onBatchType);
        }
        // Add End   2017/02/28 <QC#17809>
    }

    // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
    private NSZC047008PMsg callBllgSchdApiForExchMultiPrcEffBase(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, ONBATCH_TYPE onBatchType) {
        String contrEffFromDt = dtlMsg.contrEffFromDt.getValue();
        List<Map<String, Object>> prfEffInfoList = getMultiPrcEffForBase(param.glblCmpyCd.getValue(), contrEffFromDt, dtlMsg.oldDsContrDtlPk.getValue());
        DS_CONTR_DTLTMsg contrDtl = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());

        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_PE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, contrDtl.contrCloDay);
        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, contrDtl.baseBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, contrDtl.contrBllgDay);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, contrDtl.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);

        int baseLineCnt = 0;
        BigDecimal prcEffTermAmt = BigDecimal.ZERO;
        for (Map<String, Object> prfEffInfo : prfEffInfoList) {
            NSZC047008_xxBaseLineListPMsg baseLine = pMsg.xxBaseLineList.no(baseLineCnt);
            ZYPEZDItemValueSetter.setValue(baseLine.baseBllgCycleCd_BL, contrDtl.baseBllgCycleCd);
            String contrPrcEffFromDt = (String) prfEffInfo.get("CONTR_PRC_EFF_FROM_DT");
            if (contrPrcEffFromDt.compareTo(contrDtl.contrEffFromDt.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(baseLine.effFromDt_BL, contrPrcEffFromDt);
            } else {
                ZYPEZDItemValueSetter.setValue(baseLine.effFromDt_BL, contrDtl.contrEffFromDt.getValue());
            }
            ZYPEZDItemValueSetter.setValue(baseLine.effThruDt_BL, (String) prfEffInfo.get("CONTR_PRC_EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffSqNum_BL, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(baseLine.basePrcDealAmt_BL, (BigDecimal) prfEffInfo.get("BASE_PRC_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(baseLine.basePrcTermDealAmtRate_BL, (BigDecimal) prfEffInfo.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(baseLine.baseBllgCycleCd_BL, (String) prfEffInfo.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(baseLine.qltyAsrnHldFlg_BL, (String) prfEffInfo.get("QLTY_ASRN_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(baseLine.mtrHldFlg_BL , (String) prfEffInfo.get("MTR_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(baseLine.contrHldFlg_BL, (String) prfEffInfo.get("CONTR_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(baseLine.bllgHldFlg_BL, (String) prfEffInfo.get("BLLG_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffStsCd_BL, (String) prfEffInfo.get("DS_CONTR_PRC_EFF_STS_CD"));
            ZYPEZDItemValueSetter.setValue(baseLine.qltyAsrnHldPendApvlFlg_BL , (String) prfEffInfo.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
            if (baseLineCnt > 0) {
                prcEffTermAmt = prcEffTermAmt.add((BigDecimal) prfEffInfo.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
            }
            baseLineCnt++;
        }
        int baseCnt = prfEffInfoList.size();
        pMsg.xxBaseLineList.setValidCount(baseCnt);
        BigDecimal firstStubPrcEffTermAmt = contrDtl.basePrcTermDealAmtRate.getValue().subtract(prcEffTermAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, firstStubPrcEffTermAmt);
        new NSZC047001().execute(pMsg, onBatchType);
        return pMsg;
    }

    private NSZC047008PMsg callBllgSchdApiForExchMultiPrcEffUsage(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr, DS_CONTR_DTLTMsg contrDtl, NSZC046001_xxContrDtlListPMsg dtlMsg, ONBATCH_TYPE onBatchType) {
        //set parameters for call billing schedule api(base) and call the api.
        List<Map<String, Object>> prfEffInfoList = getMultiPrcEffForUsg(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dtlMsg.oldDsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_PE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrCloDay, contrDtl.mtrCloDay);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgTmgCd, contrDtl.mtrBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgDay, contrDtl.mtrBllgDay);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, contrDtl.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);

        int mtrLineCnt = 0;
        for (Map<String, Object> prfEffInfo : prfEffInfoList) {
            NSZC047008_xxMtrLineListPMsg mtrLine = pMsg.xxMtrLineList.no(mtrLineCnt);
            ZYPEZDItemValueSetter.setValue(mtrLine.mtrBllgCycleCd_ML, bllgMtr.bllgMtrBllgCycleCd);
            if (prfEffInfo != null && !prfEffInfo.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffPk_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_PK"));
                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffSqNum_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                String contrPrcEffFromDt = (String) prfEffInfo.get("CONTR_PRC_EFF_FROM_DT");
                if (contrPrcEffFromDt.compareTo(contrDtl.contrEffFromDt.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(mtrLine.effFromDt_ML, contrPrcEffFromDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(mtrLine.effFromDt_ML, contrDtl.contrEffFromDt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(mtrLine.effThruDt_ML, (String) prfEffInfo.get("CONTR_PRC_EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(mtrLine.mtrBllgCycleCd_ML, (String) prfEffInfo.get("BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(mtrLine.qltyAsrnHldFlg_ML, (String) prfEffInfo.get("QLTY_ASRN_HLD_FLG"));
                ZYPEZDItemValueSetter.setValue(mtrLine.contrHldFlg_ML, (String) prfEffInfo.get("CONTR_HLD_FLG"));
                ZYPEZDItemValueSetter.setValue(mtrLine.bllgHldFlg_ML, (String) prfEffInfo.get("BLLG_HLD_FLG"));
                ZYPEZDItemValueSetter.setValue(mtrLine.mtrHldFlg_ML, (String) prfEffInfo.get("MTR_HLD_FLG"));
                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrPrcEffStsCd_ML, (String) prfEffInfo.get("DS_CONTR_PRC_EFF_STS_CD"));
                ZYPEZDItemValueSetter.setValue(mtrLine.dsContrBllgMtrPk_ML, (BigDecimal) prfEffInfo.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(mtrLine.contrXsCopyPk_ML, (BigDecimal) prfEffInfo.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrCopyQty_ML, (BigDecimal) prfEffInfo.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrAmtRate_ML, (BigDecimal) prfEffInfo.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrFirstFlg_ML, (String) prfEffInfo.get("XS_MTR_FIRST_FLG"));
            }
            mtrLineCnt++;
        }
        pMsg.xxMtrLineList.setValidCount(mtrLineCnt);
        new NSZC047001().execute(pMsg, onBatchType);
        return pMsg;
    }

    private NSZC047008PMsg  callBllgSchdApiForExchMultiPrcEffBaseNoExchAcc(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, ONBATCH_TYPE onBatchType) {
        DS_CONTR_DTLTMsg contrDtl = NSZC046001CommonLogic.getDsContrDtl(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_PE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, contrDtl.contrCloDay);
        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, contrDtl.baseBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, contrDtl.contrBllgDay);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, contrDtl.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, contrDtl.contrEffThruDt);

        int baseLineCnt = 0;
        BigDecimal prcEffTermAmt = BigDecimal.ZERO;
        for (int i = 0; i < param.xxDsContrPrcEffList.getValidCount(); i++) {
            NSZC046001_xxDsContrPrcEffListPMsg prcEffPMsg = param.xxDsContrPrcEffList.no(i);
            if (dtlMsg.oldDsContrDtlPk.getValue().equals(prcEffPMsg.dsContrDtlPk.getValue())) {
                NSZC047008_xxBaseLineListPMsg baseLine = pMsg.xxBaseLineList.no(baseLineCnt);
                ZYPEZDItemValueSetter.setValue(baseLine.baseBllgCycleCd_BL, contrDtl.baseBllgCycleCd);
                String contrPrcEffFromDt = prcEffPMsg.contrPrcEffFromDt.getValue();
                if (contrPrcEffFromDt.compareTo(contrDtl.contrEffFromDt.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(baseLine.effFromDt_BL, contrPrcEffFromDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(baseLine.effFromDt_BL, contrDtl.contrEffFromDt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(baseLine.effThruDt_BL, prcEffPMsg.contrPrcEffThruDt);
                ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffSqNum_BL, prcEffPMsg.dsContrPrcEffSqNum);
                ZYPEZDItemValueSetter.setValue(baseLine.basePrcDealAmt_BL, prcEffPMsg.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(baseLine.basePrcTermDealAmtRate_BL, prcEffPMsg.basePrcTermDealAmtRate);
                ZYPEZDItemValueSetter.setValue(baseLine.qltyAsrnHldFlg_BL, prcEffPMsg.qltyAsrnHldFlg);
                ZYPEZDItemValueSetter.setValue(baseLine.mtrHldFlg_BL , prcEffPMsg.mtrHldFlg);
                ZYPEZDItemValueSetter.setValue(baseLine.contrHldFlg_BL, prcEffPMsg.contrHldFlg);
                ZYPEZDItemValueSetter.setValue(baseLine.bllgHldFlg_BL, prcEffPMsg.bllgHldFlg);
                ZYPEZDItemValueSetter.setValue(baseLine.dsContrPrcEffStsCd_BL, prcEffPMsg.dsContrPrcEffStsCd);
                ZYPEZDItemValueSetter.setValue(baseLine.qltyAsrnHldPendApvlFlg_BL , prcEffPMsg.qltyAsrnHldPendApvlFlg);
                if (baseLineCnt > 0) {
                    prcEffTermAmt = prcEffTermAmt.add(prcEffPMsg.basePrcTermDealAmtRate.getValue());
                }
                baseLineCnt++;
            }
        }
        pMsg.xxBaseLineList.setValidCount(baseLineCnt);
        BigDecimal firstStubPrcEffTermAmt = contrDtl.basePrcTermDealAmtRate.getValue().subtract(prcEffTermAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, firstStubPrcEffTermAmt);
        new NSZC047001().execute(pMsg, onBatchType);
        return pMsg;
    }
    // Add End   2019/04/15 K.Fujimoto QC#31137/50058
    private void callBllgSchdApiForDelete(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            // if (NSZC046001CommonLogic.isFleetMach(param, dtlMsg)) {
            if (NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
                continue;
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]

            // START 2017/05/08 K.Kitachi [QC#18268, MOD]
//            NSZC047009PMsg pMsg = new NSZC047009PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_DEL_MODE);
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
//            if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_OFF_N);
//            }
//
//            new NSZC047001().execute(pMsg, onBatchType);
//            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                for (S21ApiMessage msg : msgList) {
//                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                }
//                return;
//            }

            NSZC047009PMsg pMsg;
            NSZC047001 api = new NSZC047001();
            if (NSZC046001CommonLogic.isBaseChrg(param, dtlMsg)) {
                pMsg = setNSZC047009PMsgForBaseChrg(param, dtlMsg);
                api.execute(pMsg, onBatchType);
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (S21ApiMessage msg : msgList) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                    return;
                }
            }
            if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
                pMsg = setNSZC047009PMsgForUsgChrg(param, dtlMsg);
                api.execute(pMsg, onBatchType);
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (S21ApiMessage msg : msgList) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                    return;
                }
            }
            // END 2017/05/08 K.Kitachi [QC#18268, MOD]
        }
    }

    private void callBllgSchdApiForTerminate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            // if (NSZC046001CommonLogic.isFleetMach(param, dtlMsg)) {
            if (NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
                continue;
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]

            NSZC047006PMsg pMsg = new NSZC047006PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_TERM_MODE);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(pMsg.trmnDt, dtlMsg.contrCloDt);
            if (ZYPCommonFunc.hasValue(dtlMsg.trmnOvrdTotAmt)) {
                ZYPEZDItemValueSetter.setValue(pMsg.crAmt, dtlMsg.trmnOvrdTotAmt);
                // add start 2022/07/07 QC#60167
                ZYPEZDItemValueSetter.setValue(pMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
                // add end 2022/07/07 QC#60167
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.crAmt, dtlMsg.trmnTotAmt);
                // add start 2022/07/07 QC#60167
                ZYPEZDItemValueSetter.setValue(pMsg.ovrdFlg, ZYPConstant.FLG_OFF_N);
                // add end 2022/07/07 QC#60167
            }
            ZYPEZDItemValueSetter.setValue(pMsg.supprCrFlg, dtlMsg.supprCrFlg);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.manTrmnTpCd, dtlMsg.manTrmnTpCd);
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            new NSZC047001().execute(pMsg, onBatchType);
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                }
                return;
            }
        }
        // Add Start 2017/02/28 <QC#17809>
        if (DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue())) {
            sumAggregate(msgMap, onBatchType);
        }
        // Add End   2017/02/28 <QC#17809>
    }

    // Add Start 2017/02/28 <QC#17809>
    private void sumAggregate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();
        BigDecimal aggLinePk = getAggLinePk(param);
        if (ZYPCommonFunc.hasValue(aggLinePk)) {
            NSZC047011PMsg pMsg = new NSZC047011PMsg();
            setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(pMsg.xxModeCd, "11");
            setValue(pMsg.slsDt, param.slsDt);
            setValue(pMsg.dsContrDtlPk, aggLinePk);
            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, onBatchType);
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                }
                return;
            }
        }
    }

    private BigDecimal getAggLinePk(NSZC046001PMsg param) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", param.glblCmpyCd);
        ssmPrm.put("dsContrPk", param.dsContrPk);
        ssmPrm.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);

        @SuppressWarnings("unchecked")
        BigDecimal aggLinePk = (BigDecimal) ssmBatchClient.queryObject("getAggLinePk", ssmPrm);
        return aggLinePk;
    }
    // Add End   2017/02/28 <QC#17809>

    private void callBllgSchdApiForRenew(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, List<DS_CONTR_DTLTMsg> oldContrDtlList) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);

            if (NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
                continue;
            }

            DS_CONTR_DTLTMsg oldContr = null;
            for (DS_CONTR_DTLTMsg dsContr : oldContrDtlList) {
                if (dtlMsg.dsContrDtlPk.getValue().compareTo(dsContr.dsContrDtlPk.getValue()) == 0) {
                    oldContr = dsContr;
                    break;
                }
            }

            if (NSZC046001CommonLogic.isBaseChrg(param, dtlMsg) && NSZC046001CommonLogic.isBaseRnw(param, dtlMsg)) {
                //set parameters for billing schedule api(base) and call the api.
                NSZC047004PMsg pMsg = new NSZC047004PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_RNW_MODE);
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_OFF_N);
                // Mod Start 2017/09/27 QC#21459
//                ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ZYPDateUtil.addBusinessDay(param.glblCmpyCd.getValue(), oldContr.contrEffThruDt.getValue(), 1));
                ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ZYPDateUtil.addDays(oldContr.contrEffThruDt.getValue(), 1));
                // Mod End 2017/09/27 QC#21459
                ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, dtlMsg.contrEffThruDt);
                ZYPEZDItemValueSetter.setValue(pMsg.basePrcDealAmt, dtlMsg.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(pMsg.basePrcTermDealAmtRate, dtlMsg.basePrcTermDealAmtRate);
                ZYPEZDItemValueSetter.setValue(pMsg.contrRnwErrRsnCd, dtlMsg.contrRnwErrRsnCd);

                new NSZC047001().execute(pMsg, onBatchType);
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (S21ApiMessage msg : msgList) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                    return;
                }

                if (!setRenewalHoldForBase(param, dtlMsg, pMsg, onBatchType)) {
                    return;
                }

            }
            if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg) && NSZC046001CommonLogic.isUsgRnw(param, dtlMsg)) {
                List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = getBllgMtrInfo(param, dtlMsg);

                for (NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr : bllgMtrList) {
                    //set parameters for call billing schedule api(base) and call the api.
                    List<NSZC046001_xxContrXsCopyListPMsg> xsList = getXsCopyInfo(param, bllgMtr);

                    NSZC047004PMsg pMsg = new NSZC047004PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_RNW_MODE);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, FLG_ON_Y);
                    // Mod Start 2017/09/27 QC#21459
//                    ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ZYPDateUtil.addBusinessDay(param.glblCmpyCd.getValue(), oldContr.contrEffThruDt.getValue(), 1));
                    ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ZYPDateUtil.addDays(oldContr.contrEffThruDt.getValue(), 1));
                    // Mod End 2017/09/27 QC#21459
                    ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, dtlMsg.contrEffThruDt);
                    // START 2016/01/20 T.Iwamoto [QC#3201, DEL]
//                    ZYPEZDItemValueSetter.setValue(pMsg.contrRnwErrRsnCd, dtlMsg.contrRnwErrRsnCd);
                    // END 2016/01/20 T.Iwamoto [QC#3201, DEL]

                    int mtrIdx = 0;
                    for (; mtrIdx < xsList.size(); mtrIdx++) {
                        NSZC047004_xxMtrLineListPMsg mtrLine = pMsg.xxMtrLineList.no(mtrIdx);
                        ZYPEZDItemValueSetter.setValue(mtrLine.dsContrBllgMtrPk_ML, bllgMtr.dsContrBllgMtrPk);
                        ZYPEZDItemValueSetter.setValue(mtrLine.contrXsCopyPk_ML, xsList.get(mtrIdx).contrXsCopyPk);
                        ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrCopyQty_ML, xsList.get(mtrIdx).xsMtrCopyQty);
                        ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrAmtRate_ML, xsList.get(mtrIdx).xsMtrAmtRate);
                        ZYPEZDItemValueSetter.setValue(mtrLine.xsMtrFirstFlg_ML, xsList.get(mtrIdx).xsMtrFirstFlg);
                        // START 2016/01/20 T.Iwamoto [QC#3201, MOD]
                        if (ZYPCommonFunc.hasValue(param.xxContrXsCopyList.no(mtrIdx).contrRnwErrRsnCd)) {
                            ZYPEZDItemValueSetter.setValue(pMsg.contrRnwErrRsnCd, param.xxContrXsCopyList.no(mtrIdx).contrRnwErrRsnCd);
                        }
                        // END 2016/01/20 T.Iwamoto [QC#3201, MOD]
                    }
                    pMsg.xxMtrLineList.setValidCount(mtrIdx);

                    new NSZC047001().execute(pMsg, onBatchType);
                    if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                        for (S21ApiMessage msg : msgList) {
                            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                        }
                        return;
                    }

                    if (!setRenewalHoldForUsg(param, dtlMsg, bllgMtr, pMsg, onBatchType)) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * CCY
     */
    private String getCcyCd(String glblCmpyCd) {

        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (outMsg != null) {
            return outMsg.stdCcyCd.getValue();
        }
        return null;
    }

    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            msgMap.addXxMsgIdWithPrm(messageId, param);
        }
    }

    private NSZC046001_xxContrDtlListPMsg getContrDtlInfo(NSZC046001PMsg hdr, BigDecimal svcMachMstrPk) {

        for (int i = 0; i < hdr.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtl = hdr.xxContrDtlList.no(i);

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal dtlMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtl.svcMachMstrPk)) {
                dtlMachMstrPk = dtl.svcMachMstrPk.getValue();
            }
            if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                svcMachMstrPk = BigDecimal.ZERO;
            }
            if (svcMachMstrPk.compareTo(dtlMachMstrPk) == 0) {
                return dtl;
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        // START 2016/06/14 [QC#9944, ADD]
        BigDecimal dsContrDtlPk = getDsContrDtlInfo(hdr.glblCmpyCd.getValue(), hdr.dsContrPk.getValue(), svcMachMstrPk);
        NSZC046001_xxContrDtlListPMsg msg = new NSZC046001_xxContrDtlListPMsg();
        ZYPEZDItemValueSetter.setValue(msg.dsContrDtlPk, dsContrDtlPk);
        // END 2016/06/14 [QC#9944, ADD]
        return msg;
    }

    private List<NSZC046001_xxDsContrBllgMtrListPMsg> getBllgMtrInfo(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = new ArrayList<NSZC046001_xxDsContrBllgMtrListPMsg>();

        for (int i = 0; i < hdr.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = hdr.xxDsContrBllgMtrList.no(i);

            if (ZYPCommonFunc.hasValue(dtl.dsContrDtlPk) && ZYPCommonFunc.hasValue(bllgMtr.dsContrDtlPk)
                    && dtl.dsContrDtlPk.getValue().compareTo(bllgMtr.dsContrDtlPk.getValue()) == 0) {
                bllgMtrList.add(bllgMtr);
                continue;
            }

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal dtlMachMstrPk = BigDecimal.ZERO;
            BigDecimal bllgMtrMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtl.svcMachMstrPk)) {
                dtlMachMstrPk = dtl.svcMachMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
                bllgMtrMachMstrPk = bllgMtr.svcMachMstrPk.getValue();
            }
            if (dtlMachMstrPk.compareTo(bllgMtrMachMstrPk) == 0) {
                bllgMtrList.add(bllgMtr);
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        return bllgMtrList;
    }

    private NSZC046001_xxDsContrBllgMtrListPMsg getBllgMtrInfo(NSZC046001PMsg hdr, NSZC046001_xxContrPhysBllgMtrRelnListPMsg reln) {

        NSZC046001_xxDsContrBllgMtrListPMsg msg = null;
        for (int i = 0; i < hdr.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = hdr.xxDsContrBllgMtrList.no(i);

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal relnMachMstrPk = BigDecimal.ZERO;
            BigDecimal bllgMtrMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(reln.machMstrPk)) {
                relnMachMstrPk = reln.machMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
                bllgMtrMachMstrPk = bllgMtr.svcMachMstrPk.getValue();
            }
            if (relnMachMstrPk.compareTo(bllgMtrMachMstrPk) == 0) {
                if (ZYPCommonFunc.hasValue(reln.bllgMtrLbCd) && ZYPCommonFunc.hasValue(bllgMtr.bllgMtrLbCd)
                        && reln.bllgMtrLbCd.getValue().equals(bllgMtr.bllgMtrLbCd.getValue())) {
                    msg = bllgMtr;
                    break;
                }
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        return msg;
    }

    private NSZC046001_xxDsContrBllgMtrListPMsg getBllgMtrInfo(NSZC046001PMsg hdr, NSZC046001_xxDsContrCrCardPoListPMsg ccCard) {

        NSZC046001_xxDsContrBllgMtrListPMsg msg = null;
        for (int i = 0; i < hdr.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = hdr.xxDsContrBllgMtrList.no(i);

            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal ccCardMachMstrPk = BigDecimal.ZERO;
            BigDecimal bllgMtrMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(ccCard.svcMachMstrPk)) {
                ccCardMachMstrPk = ccCard.svcMachMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
                bllgMtrMachMstrPk = bllgMtr.svcMachMstrPk.getValue();
            }
            if (ccCardMachMstrPk.compareTo(bllgMtrMachMstrPk) == 0) {
                if (ZYPCommonFunc.hasValue(ccCard.bllgMtrLbCd) && ZYPCommonFunc.hasValue(bllgMtr.bllgMtrLbCd)
                        && ccCard.bllgMtrLbCd.getValue().equals(bllgMtr.bllgMtrLbCd.getValue())) {
                    msg = bllgMtr;
                    break;
                }
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        return msg;
    }

    private List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> getPhysBllgRelnInfo(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        List<NSZC046001_xxContrPhysBllgMtrRelnListPMsg> relnList = new ArrayList<NSZC046001_xxContrPhysBllgMtrRelnListPMsg>();

        for (int i = 0; i < hdr.xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
            NSZC046001_xxContrPhysBllgMtrRelnListPMsg reln = hdr.xxContrPhysBllgMtrRelnList.no(i);

            if (ZYPCommonFunc.hasValue(reln.dsContrDtlPk)
                    && dtl.dsContrDtlPk.getValue().compareTo(reln.dsContrDtlPk.getValue()) == 0) {
                relnList.add(reln);
                continue;
            }
            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal relnMachMstrPk = BigDecimal.ZERO;
            BigDecimal dtlMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(reln.machMstrPk)) {
                relnMachMstrPk = reln.machMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(dtl.svcMachMstrPk)) {
                dtlMachMstrPk = dtl.svcMachMstrPk.getValue();
            }
            if (dtlMachMstrPk.compareTo(relnMachMstrPk) == 0) {
                relnList.add(reln);
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        return relnList;
    }

    private List<NSZC046001_xxContrXsCopyListPMsg> getXsCopyInfo(NSZC046001PMsg hdr, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        List<NSZC046001_xxContrXsCopyListPMsg> xsList = new ArrayList<NSZC046001_xxContrXsCopyListPMsg>();

        for (int i = 0; i < hdr.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsCopy = hdr.xxContrXsCopyList.no(i);

            if (ZYPCommonFunc.hasValue(xsCopy.dsContrBllgMtrPk)
                    && xsCopy.dsContrBllgMtrPk.getValue().compareTo(bllgMtr.dsContrBllgMtrPk.getValue()) == 0) {
                xsList.add(xsCopy);
                continue;
            }
            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal xsCopyMachMstrPk = BigDecimal.ZERO;
            BigDecimal bllgMtrMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(xsCopy.svcMachMstrPk)) {
                xsCopyMachMstrPk = xsCopy.svcMachMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
                bllgMtrMachMstrPk = bllgMtr.svcMachMstrPk.getValue();
            }
            if (xsCopyMachMstrPk.compareTo(bllgMtrMachMstrPk) == 0) {
                if (ZYPCommonFunc.hasValue(xsCopy.bllgMtrLbCd)
                        && xsCopy.bllgMtrLbCd.getValue().equals(bllgMtr.bllgMtrLbCd.getValue())) {
                    xsList.add(xsCopy);
                }
            }
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
        }
        return xsList;
    }

    private BigDecimal summaryTermAmt(NSZC046001PMsg param) {
        BigDecimal sumTermAmt = BigDecimal.ZERO;

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            if (ZYPCommonFunc.hasValue(dtlMsg.basePrcTermDealAmtRate)) {
                sumTermAmt = sumTermAmt.add(dtlMsg.basePrcTermDealAmtRate.getValue());
            } else {
                //TODO 部品を呼び出してTerm Amount算�E
            }
        }
        return sumTermAmt;
    }

    private List<BigDecimal> getEffectiveDsContrDtl(NSZC046001PMsg hdr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", hdr.dsContrPk.getValue());
        ssmPrm.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        ssmPrm.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        // add start 2019/05/15 K.Fujimoto CSAQC#50279
        ssmPrm.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // add End 2019/05/15 K.Fujimoto CSAQC#50279

        @SuppressWarnings("unchecked")
        List<BigDecimal> contrDtlPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getEffectiveDsContrDtl", ssmPrm);
        return contrDtlPkList;
    }

    private String getMaxContrDtlEffThruDt(NSZC046001PMsg hdr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", hdr.dsContrPk.getValue());
        ssmPrm.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        ssmPrm.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        // add start 2019/05/15 K.Fujimoto CSAQC#50279
        ssmPrm.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // add End 2019/05/15 K.Fujimoto CSAQC#50279

        String maxEffThruDt = (String) ssmBatchClient.queryObject("getMaxContrDtlEffThruDt", ssmPrm);
        return maxEffThruDt;
    }

    private Map<String, String> getBaseBillToAndAcct(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrDtlPk", dtlMsg.dsContrDtlPk.getValue());

        @SuppressWarnings("unchecked")
        Map<String, String> baseBillAcct = (Map<String, String>) ssmBatchClient.queryObject("getBaseBillToAndAcct", ssmPrm);
        return baseBillAcct;
    }

    private Map<String, String> getUsgBillToAndAcct(NSZC046001PMsg hdr, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrBllgMtrPk", bllgMtr.dsContrBllgMtrPk.getValue());

        @SuppressWarnings("unchecked")
        Map<String, String> usgBillAcct = (Map<String, String>) ssmBatchClient.queryObject("getUsgBillToAndAcct", ssmPrm);
        return usgBillAcct;
    }

    // START 2017/10/05 M.Kidokoro [QC#21544, DEL]
//    private List<String> getDsCrCard(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("dsContrPk", dsContrPk);
//        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
//            ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
//        }
//        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
//            ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        }
//
//        @SuppressWarnings("unchecked")
//        List<String> crCardPoDtList = (List<String>) ssmBatchClient.queryObjectList("getDsContrCrCard", ssmPrm);
//        return crCardPoDtList;
//    }
    // END 2017/10/05 M.Kidokoro [QC#21544, DEL]

    // START 2017/10/02 M.Kidokoro [QC#21544, ADD]
    private List<String> getPoDtListForBase(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrMachLvlNum1", LVL_NUM_1);
        ssmPrm.put("dsContrMachLvlNum2", LVL_NUM_2);
        ssmPrm.put("dsContrMachLvlNum3", LVL_NUM_3);
        List<String> poDtList = (List<String>) ssmBatchClient.queryObjectList("getPoDtListForBase", ssmPrm);
        return poDtList;
    }

    private List<String> getPoDtListForUsg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("dsContrMachLvlNum1", LVL_NUM_1);
        ssmPrm.put("dsContrMachLvlNum2", LVL_NUM_2);
        ssmPrm.put("dsContrMachLvlNum3", LVL_NUM_3);
        List<String> poDtList = (List<String>) ssmBatchClient.queryObjectList("getPoDtListForUsg", ssmPrm);
        return poDtList;
    }
    // END 2017/10/02 M.Kidokoro [QC#21544, ADD]

    private List<Map<String, Object>> getInvdBllgSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String invUpDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
            ssmPrm.put("usgChrgFlg", FLG_ON_Y);
        } else {
            ssmPrm.put("baseChrgFlg", FLG_ON_Y);
        }
        ssmPrm.put("invUpDt", invUpDt);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> bllgSchdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvdBllgSchd", ssmPrm);
        return bllgSchdList;
    }

    private List<Map<String, Object>> getRnwHoldContr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("renewalHoldWithPo", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> bllgSchdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRnwHoldContr", ssmPrm);
        return bllgSchdList;
    }

    private List<BigDecimal> getBaseDiffData(NSZC046001PMsg param) {
        List<BigDecimal> baseDsContrDtlPkList = null;

        // update data
        NSXC001001ContrDiffCheckerBean bean = new NSXC001001ContrDiffCheckerBean();

        DS_CONTRTMsg contr = new DS_CONTRTMsg();
        EZDMsg.copy(param, null, contr, null);
        bean.setDsContr(contr);

        List<DS_CONTR_DTLTMsg> contrDtlList = new ArrayList<DS_CONTR_DTLTMsg>();
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            DS_CONTR_DTLTMsg contrDtl = new DS_CONTR_DTLTMsg();
            EZDMsg.copy(param.xxContrDtlList.no(i), null, contrDtl, null);
            ZYPEZDItemValueSetter.setValue(contrDtl.glblCmpyCd, param.glblCmpyCd);
            contrDtlList.add(contrDtl);
        }
        bean.setDsContrDtlList(contrDtlList);

        List<DS_CONTR_ADDL_CHRGTMsg> addlChrgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrAddlChrgList.no(i).xxModeCd.getValue())) {
                DS_CONTR_ADDL_CHRGTMsg addlChrg = new DS_CONTR_ADDL_CHRGTMsg();
                EZDMsg.copy(param.xxDsContrAddlChrgList.no(i), null, addlChrg, null);
                ZYPEZDItemValueSetter.setValue(addlChrg.glblCmpyCd, param.glblCmpyCd);
                addlChrgList.add(addlChrg);
            }
        }
        bean.setDsContrAddlChrgList(addlChrgList);

        List<DS_CONTR_BR_ALLOCTMsg> brAllocList = new ArrayList<DS_CONTR_BR_ALLOCTMsg>();
        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrBrAllocList.no(i).xxModeCd.getValue())) {
                DS_CONTR_BR_ALLOCTMsg brAlloc = new DS_CONTR_BR_ALLOCTMsg();
                EZDMsg.copy(param.xxDsContrBrAllocList.no(i), null, brAlloc, null);
                ZYPEZDItemValueSetter.setValue(brAlloc.glblCmpyCd, param.glblCmpyCd);
                brAllocList.add(brAlloc);
            }
        }
        bean.setDsContrBrAllocList(brAllocList);

        List<DS_CONTR_SEG_ALLOCTMsg> segAllocList = new ArrayList<DS_CONTR_SEG_ALLOCTMsg>();
        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrSegAllocList.no(i).xxModeCd.getValue())) {
                DS_CONTR_SEG_ALLOCTMsg segAlloc = new DS_CONTR_SEG_ALLOCTMsg();
                EZDMsg.copy(param.xxDsContrSegAllocList.no(i), null, segAlloc, null);
                ZYPEZDItemValueSetter.setValue(segAlloc.glblCmpyCd, param.glblCmpyCd);
                segAllocList.add(segAlloc);
            }
        }
        bean.setDsContrSegAllocList(segAllocList);

        baseDsContrDtlPkList = NSXC001001ContrDiffChecker.getDiffContrListForBase(bean);

        //create or delete data
        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addlMsg = param.xxDsContrAddlChrgList.no(i);
            if (CRUD_MODE_UPDATE.equals(addlMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(addlMsg.dsContrDtlPk)) {
                baseDsContrDtlPkList.add(addlMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllDetailForBase(bean, baseDsContrDtlPkList);
            }
        }

        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            if (CRUD_MODE_UPDATE.equals(brMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(brMsg.dsContrDtlPk)) {
                baseDsContrDtlPkList.add(brMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllDetailForBase(bean, baseDsContrDtlPkList);
            }
        }

        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            if (CRUD_MODE_UPDATE.equals(segMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(segMsg.dsContrDtlPk)) {
                baseDsContrDtlPkList.add(segMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllDetailForBase(bean, baseDsContrDtlPkList);
            }
        }

        return baseDsContrDtlPkList;
    }

    private List<BigDecimal> getUsgDiffData(NSZC046001PMsg param) {
        List<BigDecimal> usgDsContrDtlPkList = null;

        // update data
        NSXC001001ContrDiffCheckerBean bean = new NSXC001001ContrDiffCheckerBean();

        DS_CONTRTMsg contr = new DS_CONTRTMsg();
        EZDMsg.copy(param, null, contr, null);
        bean.setDsContr(contr);

        List<DS_CONTR_DTLTMsg> contrDtlList = new ArrayList<DS_CONTR_DTLTMsg>();
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            DS_CONTR_DTLTMsg contrDtl = new DS_CONTR_DTLTMsg();
            EZDMsg.copy(param.xxContrDtlList.no(i), null, contrDtl, null);
            ZYPEZDItemValueSetter.setValue(contrDtl.glblCmpyCd, param.glblCmpyCd);
            contrDtlList.add(contrDtl);
        }
        bean.setDsContrDtlList(contrDtlList);

        List<DS_CONTR_BLLG_MTRTMsg> bllgMtrList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        for (int i = 0; i < param.xxDsContrBllgMtrList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrBllgMtrList.no(i).xxModeCd.getValue())) {
                DS_CONTR_BLLG_MTRTMsg bllgMtr = new DS_CONTR_BLLG_MTRTMsg();
                EZDMsg.copy(param.xxDsContrBllgMtrList.no(i), null, bllgMtr, null);
                ZYPEZDItemValueSetter.setValue(bllgMtr.glblCmpyCd, param.glblCmpyCd);
                bllgMtrList.add(bllgMtr);
            }
        }
        bean.setDsContrBllgMtrList(bllgMtrList);

        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> relnList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        for (int i = 0; i < param.xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg reln = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            EZDMsg.copy(param.xxContrPhysBllgMtrRelnList.no(i), null, reln, null);
            ZYPEZDItemValueSetter.setValue(reln.glblCmpyCd, param.glblCmpyCd);
            relnList.add(reln);
        }
        bean.setContrPhysBllgMtrRelnList(relnList);

        List<CONTR_XS_COPYTMsg> xsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        for (int i = 0; i < param.xxContrXsCopyList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxContrXsCopyList.no(i).xxModeCd.getValue())) {
                CONTR_XS_COPYTMsg xsCopy = new CONTR_XS_COPYTMsg();
                EZDMsg.copy(param.xxContrXsCopyList.no(i), null, xsCopy, null);
                ZYPEZDItemValueSetter.setValue(xsCopy.glblCmpyCd, param.glblCmpyCd);
                xsCopyList.add(xsCopy);
            }
        }
        bean.setContrXsCopyList(xsCopyList);

        List<DS_CONTR_ADDL_CHRGTMsg> addlChrgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrAddlChrgList.no(i).xxModeCd.getValue())) {
                DS_CONTR_ADDL_CHRGTMsg addlChrg = new DS_CONTR_ADDL_CHRGTMsg();
                EZDMsg.copy(param.xxDsContrAddlChrgList.no(i), null, addlChrg, null);
                ZYPEZDItemValueSetter.setValue(addlChrg.glblCmpyCd, param.glblCmpyCd);
                addlChrgList.add(addlChrg);
            }
        }
        bean.setDsContrAddlChrgList(addlChrgList);

        List<DS_CONTR_BR_ALLOCTMsg> brAllocList = new ArrayList<DS_CONTR_BR_ALLOCTMsg>();
        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrBrAllocList.no(i).xxModeCd.getValue())) {
                DS_CONTR_BR_ALLOCTMsg brAlloc = new DS_CONTR_BR_ALLOCTMsg();
                EZDMsg.copy(param.xxDsContrBrAllocList.no(i), null, brAlloc, null);
                ZYPEZDItemValueSetter.setValue(brAlloc.glblCmpyCd, param.glblCmpyCd);
                brAllocList.add(brAlloc);
            }
        }
        bean.setDsContrBrAllocList(brAllocList);

        List<DS_CONTR_SEG_ALLOCTMsg> segAllocList = new ArrayList<DS_CONTR_SEG_ALLOCTMsg>();
        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            if (CRUD_MODE_UPDATE.equals(param.xxDsContrSegAllocList.no(i).xxModeCd.getValue())) {
                DS_CONTR_SEG_ALLOCTMsg segAlloc = new DS_CONTR_SEG_ALLOCTMsg();
                EZDMsg.copy(param.xxDsContrSegAllocList.no(i), null, segAlloc, null);
                ZYPEZDItemValueSetter.setValue(segAlloc.glblCmpyCd, param.glblCmpyCd);
                segAllocList.add(segAlloc);
            }
        }
        bean.setDsContrSegAllocList(segAllocList);

        usgDsContrDtlPkList = NSXC001001ContrDiffChecker.getDiffContrListForUsg(bean);

        //create or delete data
        for (int i = 0; i < param.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = param.xxDsContrBllgMtrList.no(i);
            if (CRUD_MODE_UPDATE.equals(bllgMtr.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(bllgMtr.dsContrDtlPk)) {
                NSXC001001ContrDiffChecker.addBllgMtrForUsgByContrDtlPk(bean, usgDsContrDtlPkList, bllgMtr.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllBllgMtrForUsg(bean, usgDsContrDtlPkList);
            }
        }

        for (int i = 0; i < param.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsCopy = param.xxContrXsCopyList.no(i);
            if (CRUD_MODE_UPDATE.equals(xsCopy.xxModeCd.getValue())) {
                continue;
            }
            usgDsContrDtlPkList.add(xsCopy.dsContrBllgMtrPk.getValue());
        }

        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addlMsg = param.xxDsContrAddlChrgList.no(i);
            if (CRUD_MODE_UPDATE.equals(addlMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(addlMsg.dsContrDtlPk)) {
                NSXC001001ContrDiffChecker.addBllgMtrForUsgByContrDtlPk(bean, usgDsContrDtlPkList, addlMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllBllgMtrForUsg(bean, usgDsContrDtlPkList);
            }
        }

        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            if (CRUD_MODE_UPDATE.equals(brMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(brMsg.dsContrDtlPk)) {
                NSXC001001ContrDiffChecker.addBllgMtrForUsgByContrDtlPk(bean, usgDsContrDtlPkList, brMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllBllgMtrForUsg(bean, usgDsContrDtlPkList);
            }
        }

        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            if (CRUD_MODE_UPDATE.equals(segMsg.xxModeCd.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(segMsg.dsContrDtlPk)) {
                NSXC001001ContrDiffChecker.addBllgMtrForUsgByContrDtlPk(bean, usgDsContrDtlPkList, segMsg.dsContrDtlPk.getValue());
            } else {
                NSXC001001ContrDiffChecker.addAllBllgMtrForUsg(bean, usgDsContrDtlPkList);
            }
        }

        return usgDsContrDtlPkList;
    }

    private Map<String, Object> getPrcEffForBase(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);

        @SuppressWarnings("unchecked")
        Map<String, Object> prfEffInfo = (Map<String, Object>) ssmBatchClient.queryObject("getPrcEffForBase", ssmPrm);
        return prfEffInfo;
    }

    private Map<String, Object> getPrcEffForUsg(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("slsDt", slsDt);

        @SuppressWarnings("unchecked")
        Map<String, Object> prfEffInfo = (Map<String, Object>) ssmBatchClient.queryObject("getPrcEffForUsg", ssmPrm);
        return prfEffInfo;
    }
    // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
    private List<Map<String, Object>> getMultiPrcEffForBase(String glblCmpyCd, String exchDt, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("exchDt", exchDt);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> prfEffInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMultiPrcEffForBase", ssmPrm);
        return prfEffInfoList;
    }

    private List<Map<String, Object>> getMultiPrcEffForUsg(String glblCmpyCd, String exchDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("exchDt", exchDt);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> prfEffInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMultiPrcEffForUsg", ssmPrm);
        return prfEffInfoList;
    }
     // Add End   2019/04/15 K.Fujimoto QC#31137/50058
    // START 2017/07/11 K.Kojima [QC#19822,DEL]
    // // START 2017/07/10 K.Kojima [QC#19822,ADD]
    // private Map<String, Object> getPrcEffForBaseForAggregateLine(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
    //     Map<String, Object> ssmPrm = new HashMap<String, Object>();
    //     ssmPrm.put("glblCmpyCd", glblCmpyCd);
    //     ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
    //     ssmPrm.put("slsDt", slsDt);
    // 
    //     @SuppressWarnings("unchecked")
    //     Map<String, Object> prfEffInfo = (Map<String, Object>) ssmBatchClient.queryObject("getPrcEffForBaseForAggregateLine", ssmPrm);
    //     return prfEffInfo;
    // }
    // 
    // private Map<String, Object> getPrcEffForUsgForAggregateLine(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    //     Map<String, Object> ssmPrm = new HashMap<String, Object>();
    //     ssmPrm.put("glblCmpyCd", glblCmpyCd);
    //     ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
    //     ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
    //     ssmPrm.put("slsDt", slsDt);
    // 
    //     @SuppressWarnings("unchecked")
    //     Map<String, Object> prfEffInfo = (Map<String, Object>) ssmBatchClient.queryObject("getPrcEffForUsgForAggregateLine", ssmPrm);
    //     return prfEffInfo;
    // }
    // END 2017/07/10 K.Kojima [QC#19822,ADD]
    // END 2017/07/11 K.Kojima [QC#19822,DEL]

    private String decisionDsContrStsCd(NSZC046001PMsg param) {
        //DS_CONTR
        DS_CONTRTMsg contr = new DS_CONTRTMsg();
        EZDMsg.copy(param, null, contr, null);
        List<String> diffColumns = NSXC001001ContrDiffChecker.getDsContrDiffColumns(contr);

        String currentSts = getDsContrStsV(param.glblCmpyCd.getValue(), param.dsContrPk.getValue());
        String dsContrSts = decisionSts(param, diffColumns, currentSts);
        if (!ZYPCommonFunc.hasValue(dsContrSts)) {
            //set error message
        }
        return dsContrSts;

    }

    private String decisionDsContrDtlStsCd(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl) {
        //DS_CONTR_DTL
        DS_CONTR_DTLTMsg contr = new DS_CONTR_DTLTMsg();
        EZDMsg.copy(paramDtl, null, contr, null);
        ZYPEZDItemValueSetter.setValue(contr.glblCmpyCd, param.glblCmpyCd);
        List<String> diffColumns = NSXC001001ContrDiffChecker.getDsContrDtlDiffColumns(contr);

        String currentSts = getDsContrDtlStsV(param.glblCmpyCd.getValue(), paramDtl.dsContrDtlPk.getValue());
        String dsContrSts = decisionSts(param, diffColumns, currentSts);
        if (!ZYPCommonFunc.hasValue(dsContrSts)) {
            //set error message
        }
        return dsContrSts;

    }

    private String decisionDsContrBllgMtrStsCd(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        DS_CONTR_BLLG_MTRTMsg contr = new DS_CONTR_BLLG_MTRTMsg();
        EZDMsg.copy(bllgMtr, null, contr, null);
        ZYPEZDItemValueSetter.setValue(contr.glblCmpyCd, param.glblCmpyCd);
        List<String> diffColumns = NSXC001001ContrDiffChecker.getContrBllgMtrDiffColumns(contr);

        // START 2015/12/25 T.Tomita [QC#2133, MOD]
        String currentSts = getDsContrBllgMtrStsV(param.glblCmpyCd.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
        // END 2015/12/25 T.Tomita [QC#2133, MOD]
        String dsContrSts = decisionSts(param, diffColumns, currentSts);
        if (!ZYPCommonFunc.hasValue(dsContrSts)) {
            //set error message
        }
        return dsContrSts;

    }

    private String decisionSts(NSZC046001PMsg param, List<String> diffColumns, String currentSts) {

        Map<String, Integer> diffJudgeMap = new HashMap<String, Integer>();
        for (String diffColumn : diffColumns) {
            //check availability on current contract status
            boolean available = false;
            List<Map<String, Object>> formCtrl = getDsContrFormCtrl(param.glblCmpyCd.getValue(), diffColumn, "*", param.dsContrCatgCd.getValue(), currentSts);
            for (Map<String, Object> ctrl : formCtrl) {
                String fldNm = (String) ctrl.get("DS_CONTR_SCR_FLD_NM");
                String actvFlg = (String) ctrl.get("SCR_FLD_ACTV_FLG");

                if (fldNm.equals(diffColumn) && FLG_ON_Y.equals(actvFlg)) {
                    available = true;
                    break;
                }
            }

            if (available) {
                diffJudgeMap.put(diffColumn, UPD_AVAILABLE);
                continue;
            }

            //check availability on QA hold
            boolean qaAvailable = false;
            List<Map<String, Object>> formCtrlQa = getDsContrFormCtrl(param.glblCmpyCd.getValue(), diffColumn, "*", param.dsContrCatgCd.getValue(), DS_CONTR_CTRL_STS.QA_HOLD);
            for (Map<String, Object> ctrl : formCtrlQa) {
                String fldNm = (String) ctrl.get("DS_CONTR_SCR_FLD_NM");
                String actvFlg = (String) ctrl.get("SCR_FLD_ACTV_FLG");

                if (fldNm.equals(diffColumn) && FLG_ON_Y.equals(actvFlg)) {
                    qaAvailable = true;
                    break;
                }
            }

            if (qaAvailable) {
                diffJudgeMap.put(diffColumn, UPD_AVAILABLE_QA_HOLD);
                continue;
            } else {
                diffJudgeMap.put(diffColumn, UPD_NOT_AVAILABLE);
                break;
            }
        }

        //decision status
        boolean qaHldFlg = false;
        boolean updError = false;
        for (Entry<String, Integer> entry : diffJudgeMap.entrySet()) {
            Integer value = entry.getValue();
            if (value == UPD_NOT_AVAILABLE) {
                //error
                updError = true;
                break;
            }
            if (value == UPD_AVAILABLE_QA_HOLD) {
                qaHldFlg = true;
            }
        }

        if (updError) {
            return null;
        }
        if (qaHldFlg) {
            return DS_CONTR_CTRL_STS.QA_HOLD;
        }
        return currentSts;
    }

    private List<Map<String, Object>> getDsContrFormCtrl(String glblCmpyCd, String scrnFldNm, String dsContrDtlTpCd, String dsContrCatgCd, String contrStsCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("scrnFldNm", scrnFldNm);
        ssmPrm.put("dsContrDtlTpCd", dsContrDtlTpCd);
        ssmPrm.put("dsContrCatgCd", dsContrCatgCd);
        ssmPrm.put("contrStsCd", contrStsCd);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> dsContrFormList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsContrFormCtrl", ssmPrm);
        return dsContrFormList;
    }

    private String getDsContrStsV(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        String dsContrCtrlStsCd = (String) ssmBatchClient.queryObject("getDsContrStsV", ssmPrm);
        return dsContrCtrlStsCd;
    }

    private String getDsContrDtlStsV(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

        String dsContrCtrlStsCd = (String) ssmBatchClient.queryObject("getDsContrDtlStsV", ssmPrm);
        return dsContrCtrlStsCd;
    }

    private String getDsContrBllgMtrStsV(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);

        String dsContrCtrlStsCd = (String) ssmBatchClient.queryObject("getDsContrBllgMtrStsV", ssmPrm);
        return dsContrCtrlStsCd;
    }

    private boolean compareValue(String val1, String val2) {
        if (val1 == null && val2 == null) {
            return false;
        }
        if (val1 == null || val2 == null) {
            return true;
        }
        if (val1.equals(val2)) {
            return false;
        }
        return true;
    }
    // START 2016/04/04 [QC#6487, ADD]
    private NSZC046001_xxContrDtlListPMsg getDsContrDtlForAddToContract(NSZC046001PMsg param, String dsContrDtlTpCd) {
        if (param == null) {
            return null;
        }
        Map<String, Object> dsContrDtlForAddToContract = getDsContrDtlForAddToContract(param.glblCmpyCd.getValue(), param.dsContrNum.getValue(), dsContrDtlTpCd);
        if (dsContrDtlForAddToContract != null) {
            NSZC046001_xxContrDtlListPMsg paramDtl = new NSZC046001_xxContrDtlListPMsg();
            ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlPk, (BigDecimal) dsContrDtlForAddToContract.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(paramDtl.dsContrDtlTpCd, (String) dsContrDtlForAddToContract.get("DS_CONTR_DTL_TP_CD"));
            ZYPEZDItemValueSetter.setValue(paramDtl.prntDsContrDtlPk, (BigDecimal) dsContrDtlForAddToContract.get("PRNT_DS_CONTR_DTL_PK"));
            return paramDtl;
        }
        return null;
    }

    private Map<String, Object> getDsContrDtlForAddToContract(String glblCmpyCd, String dsContrNum, String dsContrDtlTpCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrDtlTpCd", dsContrDtlTpCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrDtlForAddToContract", ssmPrm);
    }
    // END   2016/04/04 [QC#6487, ADD]

    // START 2016/06/14 [QC#9944, ADD]
    private BigDecimal getDsContrDtlInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal svcMachMstrPk) {
        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        inParam.setSQLID("003");
        inParam.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inParam.setConditionValue("dsContrPk01", dsContrPk);
        inParam.setConditionValue("svcMachMstrPk01", svcMachMstrPk);

        DS_CONTR_DTLTMsgArray outList = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inParam);
        if (outList.getValidCount() > 0) {
            return outList.no(0).dsContrDtlPk.getValue();
        }
        return null;
    }
    // END 2016/06/14 [QC#9944, ADD]

    // add start 2016/10/31 QC#13299
    private BigDecimal getDsContrDtlInfoForAggLine(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        inParam.setSQLID("006");
        inParam.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inParam.setConditionValue("dsContrPk01", dsContrPk);
        inParam.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);

        DS_CONTR_DTLTMsgArray outList = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inParam);
        if (outList.getValidCount() > 0) {
            return outList.no(0).dsContrDtlPk.getValue();
        }
        return null;
    }
    // add end 2016/10/31 QC#13299

    // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
    private String getDplyPerEndDay(String cloDay, String contrEffFromDt) {
        if (CLO_BLLG_MIN_DAY.equals(cloDay)) {
            int fromDt = 0;
            if (ZYPCommonFunc.hasValue(contrEffFromDt)) {
                fromDt = Integer.parseInt(contrEffFromDt.substring(6));
            }
            if (fromDt >= 2 && fromDt <= 28) {
                fromDt--;
                cloDay = new Integer(fromDt).toString();
            } else {
                cloDay = CLO_BLLG_LAST_DAY;
            }
        }
        return cloDay;
    }
    // END 2016/05/18 T.Kanasaka [QC#2184, ADD]

    // START 2016/05/25 [QC#4061, MOD]
    private boolean isManContrOvrd(NSZC046001PMsg param) {
        if (ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
            return true;
        }
        return false;
    }
    // End 2016/05/25 [QC#4061, MOD]

    // START 2016/09/28 T.Kanasaka [QC#9905, ADD]
    private String getShipToCustCd(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl) {
        if (!DS_CONTR_DTL_TP.FLEET.equals(paramDtl.dsContrDtlTpCd.getValue())) {
            return null;
        }

        // get default ship to
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, param.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, paramDtl.baseBillToCustCd);
        new NMZC610001().execute(pMsg, onBatchTp);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }
        if (pMsg.DefaultBillShipList.getValidCount() > 0) {
            return pMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        }
        return null;
    }
    // END 2016/09/28 T.Kanasaka [QC#9905, ADD]

    // START 2016/12/19 T.Tomita [QC#16097, ADD]
    private Map<String, Object> getInvLineKeyMap(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl) {
        if (!ZYPCommonFunc.hasValue(paramDtl.cpoOrdNum) || !ZYPCommonFunc.hasValue(paramDtl.cpoSvcDtlPk)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmPrm.put("cpoOrdNum", paramDtl.cpoOrdNum.getValue());
        ssmPrm.put("cpoSvcDtlPk", paramDtl.cpoSvcDtlPk.getValue());

        return (Map<String, Object>) ssmBatchClient.queryObject("getInvLineKey", ssmPrm);
    }

    private INV_LINETMsg getInvLineTMsgForUpdate(NSZC046001PMsg param, Map<String, Object> map) {
        INV_LINETMsg inMsg = new INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invNum, (String) map.get("INV_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.invBolLineNum, (String) map.get("INV_BOL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.invLineNum, (String) map.get("INV_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.invLineSubNum, (String) map.get("INV_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.invLineSubTrxNum, (String) map.get("INV_LINE_SUB_TRX_NUM"));

        return (INV_LINETMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    private void updateInvLine(INV_LINETMsg inMsg, NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg paramDtl) {
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, param.dsContrNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrSqNum, param.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, paramDtl.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, paramDtl.svcMachMstrPk);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrTMsg(param.glblCmpyCd.getValue(), paramDtl.svcMachMstrPk.getValue());
        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        }

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(paramDtl.xxMsgId_DT, NSZM0391E);
        }
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstrTMsg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // END 2016/12/19 T.Tomita [QC#16097, ADD]

    // START 2016/12/21 T.Kanasaka [QC#16650, ADD]
    private boolean hasCrCard(NSZC046001PMsg param) {
        for (int i = 0; i < param.xxDsContrCrCardPoList.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(param.xxDsContrCrCardPoList.no(i).crCardCustRefNum)) {
                return true;
            }
        }
        return false;
    }
    // END 2016/12/21 T.Kanasaka [QC#16650, ADD]

    // add start 2016/12/27 QC#14773
    private void deleteContrFltAggLine(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!DS_CONTR_CATG.FLEET.equals(param.dsContrCatgCd.getValue()) && !DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue())) {
            return;
        }

        Map<String, Object> dsContrDtlMap = getEffectiveDsContrDtlFltAggLine(param);
        if (dsContrDtlMap == null || dsContrDtlMap.isEmpty()) {
            return;
        }

        int cnt = getEffectiveDsContrDtlCount(param);
        if (cnt > 0) {
            return;
        }

        NSZC046001_xxContrDtlListPMsg paramDtl = new NSZC046001_xxContrDtlListPMsg();
        setValue(paramDtl.dsContrDtlPk, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
        setValue(paramDtl.dsContrDtlTpCd, (String) dsContrDtlMap.get("DS_CONTR_DTL_TP_CD"));

        // Contract Detail (Fleet Line/Aggregate Line)
        cancelDsContrDtl(msgMap, paramDtl);

        if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
            // Billing Meter (Fleet Line/Aggregate Line)
            cancelDsContrBllgMtrByContrDtl(msgMap, paramDtl);
        }

        List<BigDecimal> effectiveContrDtlList = getEffectiveDsContrDtl(param);
        if (effectiveContrDtlList == null || effectiveContrDtlList.isEmpty()) {
            // Contract Header
            cancelDsContr(msgMap);
        }

        // call billing schedule api (Fleet Line/Aggregate Line)
        callBllgSchdApiForDeleteFltAggLine(msgMap, onBatchType, paramDtl);
    }

    private void terminateContrFltAggLine(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        if (!DS_CONTR_CATG.FLEET.equals(param.dsContrCatgCd.getValue()) && !DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue())) {
            return;
        }

        Map<String, Object> dsContrDtlMap = getEffectiveDsContrDtlFltAggLine(param);
        if (dsContrDtlMap == null || dsContrDtlMap.isEmpty()) {
            return;
        }

        int cnt = getEffectiveDsContrDtlCount(param);
        if (cnt > 0) {
            return;
        }

        String closeDate = getCloseDate(param);
        if (closeDate == null) {
            return;
        }

        NSZC046001_xxContrDtlListPMsg paramDtl = new NSZC046001_xxContrDtlListPMsg();
        setValue(paramDtl.dsContrDtlPk, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
        setValue(paramDtl.dsContrDtlTpCd, (String) dsContrDtlMap.get("DS_CONTR_DTL_TP_CD"));
        setValue(paramDtl.supprCrFlg, (String) dsContrDtlMap.get("SUPPR_CR_FLG"));
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        setValue(paramDtl.manTrmnTpCd, (String) dsContrDtlMap.get("MAN_TRMN_TP_CD"));
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        setValue(paramDtl.contrCloDt, closeDate);

        // Contract Detail (Fleet Line/Aggregate Line)
        terminateDsContrDtl(msgMap, paramDtl);

        if (NSZC046001CommonLogic.isUsgChrg(param, paramDtl)) {
            // Billing Meter (Fleet Line/Aggregate Line)
            terminateDsContrBllgMtrByContrDtl(msgMap, paramDtl);
        }

        List<BigDecimal> effectiveContrDtlList = getEffectiveDsContrDtl(param);
        if (effectiveContrDtlList == null || effectiveContrDtlList.isEmpty()) {
            // Contract Header
            terminateDsContr(msgMap);
        }

        // call billing schedule api (Fleet Line/Aggregate Line)
        callBllgSchdApiForTerminateFltAggLine(msgMap, onBatchType, paramDtl);
    }

    private void callBllgSchdApiForDeleteFltAggLine(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // START 2017/05/08 K.Kitachi [QC#18268, MOD]
//        NSZC047009PMsg pMsg = new NSZC047009PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_DEL_MODE);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
//        if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_OFF_N);
//        }
//
//        new NSZC047001().execute(pMsg, onBatchType);
//        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//            for (S21ApiMessage msg : msgList) {
//                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//            }
//            return;
//        }

        NSZC047009PMsg pMsg;
        NSZC047001 api = new NSZC047001();
        if (NSZC046001CommonLogic.isBaseChrg(param, dtlMsg)) {
            pMsg = setNSZC047009PMsgForBaseChrg(param, dtlMsg);
            api.execute(pMsg, onBatchType);
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                }
                return;
            }
        }
        if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
            pMsg = setNSZC047009PMsgForUsgChrg(param, dtlMsg);
            api.execute(pMsg, onBatchType);
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                }
                return;
            }
        }
        // END 2017/05/08 K.Kitachi [QC#18268, MOD]
    }

    private void callBllgSchdApiForTerminateFltAggLine(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        NSZC047006PMsg pMsg = new NSZC047006PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_TERM_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.trmnDt, dtlMsg.contrCloDt);
        if (ZYPCommonFunc.hasValue(dtlMsg.trmnOvrdTotAmt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.crAmt, dtlMsg.trmnOvrdTotAmt);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.crAmt, dtlMsg.trmnTotAmt);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.supprCrFlg, dtlMsg.supprCrFlg);

        new NSZC047001().execute(pMsg, onBatchType);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
            return;
        }
    }

    private Map<String, Object> getEffectiveDsContrDtlFltAggLine(NSZC046001PMsg hdr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", hdr.dsContrPk.getValue());
        ssmPrm.put("fleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        ssmPrm.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        ssmPrm.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        // add start 2019/05/15 K.Fujimoto CSAQC#50279
        ssmPrm.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // add End 2019/05/15 K.Fujimoto CSAQC#50279

        return (Map<String, Object>) ssmBatchClient.queryObject("getEffectiveDsContrDtlFltAggLine", ssmPrm);
    }

    private Integer getEffectiveDsContrDtlCount(NSZC046001PMsg hdr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", hdr.dsContrPk.getValue());
        ssmPrm.put("fleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        ssmPrm.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        ssmPrm.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        // add start 2019/05/15 K.Fujimoto CSAQC#50279
        ssmPrm.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // add End 2019/05/15 K.Fujimoto CSAQC#50279

        return (Integer) ssmBatchClient.queryObject("getEffectiveDsContrDtlCount", ssmPrm);
    }

    private String getCloseDate(NSZC046001PMsg hdr) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", hdr.dsContrPk.getValue());
        ssmPrm.put("fleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        ssmPrm.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        // add start 2019/05/15 K.Fujimoto CSAQC#50279
        ssmPrm.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // add End 2019/05/15 K.Fujimoto CSAQC#50279

        return (String) ssmBatchClient.queryObject("getCloseDate", ssmPrm);
    }
    // add end 2016/12/27 QC#14773

    // START 2017/05/08 K.Kitachi [QC#18268, ADD]
    private NSZC047009PMsg setNSZC047009PMsgForBaseChrg(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        NSZC047009PMsg pMsg = new NSZC047009PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_DEL_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_OFF_N);
        return pMsg;
    }

    private NSZC047009PMsg setNSZC047009PMsgForUsgChrg(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        NSZC047009PMsg pMsg = new NSZC047009PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, BLLG_SCHD_DEL_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dtlMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
        return pMsg;
    }
    // END 2017/05/08 K.Kitachi [QC#18268, ADD]

    // START 2017/09/06 M.Naito [QC#18724, ADD]
    private void setUplftFlgFromPlnTp(DS_CONTR_RNW_ESCLTMsg inParam, String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsContrPk, dsContrPk);
        DS_CONTRTMsg outTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inTMsg);

        if (!ZYPCommonFunc.hasValue(outTMsg.prcSvcPlnTpCd)) {
            return;
        }

        // get PRC_SVC_PLN_TP Information
        PRC_SVC_PLN_TPTMsg plnTpInTMsg = new PRC_SVC_PLN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(plnTpInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(plnTpInTMsg.prcSvcPlnTpCd, outTMsg.prcSvcPlnTpCd);
        PRC_SVC_PLN_TPTMsg plnTpOutTMsg = (PRC_SVC_PLN_TPTMsg) S21FastTBLAccessor.findByKey(plnTpInTMsg);

        int contrFixedYearsAot = plnTpOutTMsg.contrFixedYearsAot.getValue().intValue();

        if (contrFixedYearsAot > 1) {
            ZYPEZDItemValueSetter.setValue(inParam.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 2) {
            ZYPEZDItemValueSetter.setValue(inParam.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 3) {
            ZYPEZDItemValueSetter.setValue(inParam.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 4) {
            ZYPEZDItemValueSetter.setValue(inParam.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 5) {
            ZYPEZDItemValueSetter.setValue(inParam.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 6) {
            ZYPEZDItemValueSetter.setValue(inParam.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 7) {
            ZYPEZDItemValueSetter.setValue(inParam.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 8) {
            ZYPEZDItemValueSetter.setValue(inParam.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 9) {
            ZYPEZDItemValueSetter.setValue(inParam.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 10) {
            ZYPEZDItemValueSetter.setValue(inParam.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
    }
    // END 2017/09/06 M.Naito [QC#18724, ADD]
    // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
    private boolean callRevenueDistributionAPI(S21ApiMessageMap msgMap, NSZC046001PMsg param, final ONBATCH_TYPE onBatchType) {
        NSZC080001 api = new NSZC080001();

        for (int detailCount = 0; detailCount < param.xxContrDtlList.getValidCount(); detailCount++) {
            BigDecimal dsContrDtlPk = param.xxContrDtlList.no(detailCount).dsContrDtlPk.getValue();
            int addCount = 0;
            NSZC080001PMsg pMsg = new NSZC080001PMsg();
            for (int segCount = 0; segCount < param.xxDsContrSegAllocList.getValidCount(); segCount++) {
                if (dsContrDtlPk.compareTo(param.xxDsContrSegAllocList.no(segCount).dsContrDtlPk.getValue()) != 0) {
                    continue;
                }
                NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(segCount);
                NSZC080001_segmentsListPMsg segment = pMsg.segmentsList.no(addCount);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, segMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, segMsg.dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(pMsg.svcInvChrgTpCd, segMsg.svcInvChrgTpCd);
                ZYPEZDItemValueSetter.setValue(pMsg.resrcObjNm, BUSINESS_ID);
                ZYPEZDItemValueSetter.setValue(segment.coaCmpyCd, segMsg.coaCmpyCd);
                ZYPEZDItemValueSetter.setValue(segment.coaAfflCd, segMsg.coaAfflCd);
                ZYPEZDItemValueSetter.setValue(segment.coaBrCd, segMsg.coaBrCd);
                ZYPEZDItemValueSetter.setValue(segment.coaChCd, segMsg.coaChCd);
                ZYPEZDItemValueSetter.setValue(segment.coaCcCd, segMsg.coaCcCd);
                ZYPEZDItemValueSetter.setValue(segment.coaAcctCd, segMsg.coaAcctCd);
                ZYPEZDItemValueSetter.setValue(segment.coaProdCd, segMsg.coaProdCd);
                ZYPEZDItemValueSetter.setValue(segment.coaProjCd, segMsg.coaProjCd);
                ZYPEZDItemValueSetter.setValue(segment.coaExtnCd, segMsg.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(segment.prcAllocRate, segMsg.prcAllocRate);
                ZYPEZDItemValueSetter.setValue(segment.prcAllocAmt, segMsg.prcAllocAmt);
                addCount++;
            }
            if (addCount == 0) {
                continue;
            }
            pMsg.segmentsList.setValidCount(addCount);
            api.execute(pMsg, onBatchType);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    return false;
                }
            }
        }
        return true;
    }
    // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]

    // QC#17645 Add
    private List<Map<String, Object>> getAddlChrgInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            param.put("dsContrDtlPk", dsContrDtlPk);
        }
        param.put("slsDt", slsDt);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAddlChrgInfo", param);
    }

    // START 2018/05/15 K.Kojima [QC#23303,ADD]
    private void renewalDsSubContr(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtl, String newEffThruDt) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // get sub contract Data
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", dsContrDtl.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtl.dsContrDtlPk.getValue());
        inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
        DS_SUB_CONTRTMsgArray dsSubContrArray = (DS_SUB_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (dsSubContrArray.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < dsSubContrArray.getValidCount(); i++) {
            DS_SUB_CONTRTMsg dsSubContr = dsSubContrArray.no(i);
            if (ZYPCommonFunc.hasValue(dsSubContr.effThruDt)) {
                if (dsSubContr.effThruDt.getValue().compareTo(dsContrDtl.contrEffThruDt.getValue()) < 0) {
                    continue;
                }
            }
            ZYPEZDItemValueSetter.setValue(dsSubContr.effThruDt, newEffThruDt);
            S21ApiTBLAccessor.update(dsSubContr);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsSubContr.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
            }
        }
    }
    // END 2018/05/15 K.Kojima [QC#23303,ADD]

// START 2019/05/22 [QC#50212,DEL]
//    // START 2018/05/15 M.Naito [QC#21679, ADD]
//    private DS_SUB_CONTRTMsgArray getDsSubContrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
//        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
//        inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
//        return (DS_SUB_CONTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
//    }
//
//    private void setParamForNSZC052001(NSZC052001PMsg nszc052001PMsg, DS_SUB_CONTRTMsg dsSubContrTMsg, NSZC046001PMsg nszc046001PMsg) {
//
//        if (nszc046001PMsg == null || dsSubContrTMsg == null) {
//            return;
//        }
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", nszc046001PMsg.glblCmpyCd.getValue());
//        param.put("dsSubContrPk", dsSubContrTMsg.dsSubContrPk.getValue());
//        Map<String, Object> dsSubContrInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsSubContrInfo", param);
//        // Mod Start 2018/07/04 QC#27123
//        if (dsSubContrInfo == null || dsSubContrInfo.isEmpty()) {
//            return;
//        }
//        // Mod End 2018/07/04 QC#27123
//
//        setValue(nszc052001PMsg.glblCmpyCd, nszc046001PMsg.glblCmpyCd.getValue());
//        setValue(nszc052001PMsg.slsDt, nszc046001PMsg.slsDt.getValue());
//        setValue(nszc052001PMsg.serNum, (String) dsSubContrInfo.get("SER_NUM"));
//        setValue(nszc052001PMsg.dsContrNum, (String) dsSubContrInfo.get("DS_CONTR_NUM"));
//
//        nszc052001PMsg.xxSubContrList.getValidCount();
//        setValue(nszc052001PMsg.xxSubContrList.no(0).vndCd, dsSubContrTMsg.vndCd);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).techCd, dsSubContrTMsg.techTocCd);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).effFromDt, dsSubContrTMsg.effFromDt);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).effThruDt, dsSubContrTMsg.effThruDt);
//        // update subcontract Terminated
//        setValue(nszc052001PMsg.xxSubContrList.no(0).contrTrmnFlg, ZYPConstant.FLG_ON_Y);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).basePrcDealAmt, dsSubContrTMsg.basePrcDealAmt);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).adminPrcDealAmt, dsSubContrTMsg.adminPrcDealAmt);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).prepdMaintFlg, dsSubContrTMsg.prepdMaintFlg);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).bwMtrAlwncCnt, dsSubContrTMsg.bwMtrAlwncCnt);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).colorMtrAlwncCnt, dsSubContrTMsg.colorMtrAlwncCnt);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).bwMtrPrcAmtRate, dsSubContrTMsg.bwMtrPrcAmtRate);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).colorMtrPrcAmtRate, dsSubContrTMsg.colorMtrPrcAmtRate);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).splyInclFlg, dsSubContrTMsg.splyInclFlg);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).bllgCycleCd, dsSubContrTMsg.bllgCycleCd);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).dlrFleetFlg, dsSubContrTMsg.dlrFleetFlg);
//        setValue(nszc052001PMsg.xxSubContrList.no(0).dlrFleetNum, dsSubContrTMsg.dlrFleetNum);
//
//        nszc052001PMsg.xxSubContrList.setValidCount(1);
//    }
//    // END 2018/05/15 M.Naito [QC#21679, ADD]
// END 2019/05/22 [QC#50212,DEL]

    // START 2019/05/22 [QC#50212,ADD]
    private void updateDsSubContr(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        // get sub contract Data
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
        DS_SUB_CONTRTMsgArray dsSubContrArray = (DS_SUB_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (dsSubContrArray == null || dsSubContrArray.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < dsSubContrArray.getValidCount(); i++) {
            DS_SUB_CONTRTMsg tmsg = dsSubContrArray.no(i);
            ZYPEZDItemValueSetter.setValue(tmsg.contrTrmnFlg, ZYPConstant.FLG_ON_Y);
            S21ApiTBLAccessor.update(tmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
            }
        }
    }
    // END 2019/05/22 [QC#50212,ADD]

    // START 2018/05/22 K.Kojima [QC#23302,ADD]
    private void terminateDsContrAddlChrg(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String trmnDt) {
        NSZC046001PMsg param = (NSZC046001PMsg) msgMap.getPmsg();

        DS_CONTR_ADDL_CHRGTMsgArray tMsgArray = null;
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            tMsgArray = (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        } else {
            DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("dsContrPk01", dsContrPk);
            tMsgArray = (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        }
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_ADDL_CHRGTMsg tMsg = tMsgArray.no(i);
            if (ZYPCommonFunc.hasValue(tMsg.trmnDt)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(tMsg.effFromDt)) {
                if (tMsg.effFromDt.getValue().compareTo(trmnDt) > 0) {
                    continue;
                }
            }
            if (ZYPCommonFunc.hasValue(tMsg.effThruDt)) {
                if (tMsg.effThruDt.getValue().compareTo(trmnDt) <= 0) {
                    continue;
                }
            }
            ZYPEZDItemValueSetter.setValue(tMsg.trmnDt, trmnDt);
            S21ApiTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(param.xxMsgId_HD, NSZM0391E);
            }
        }
    }
    // END 2018/05/22 K.Kojima [QC#23302,ADD]

    // QC#17645-2 Add Start
    private Map<String, Object> getPrcUpRatio(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String slsDt, String addlChrgInvTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            param.put("dsContrDtlPk", dsContrDtlPk);
        }
        param.put("dsContrMachLvlNum1", "1");
        param.put("dsContrMachLvlNum2", "2");
        param.put("dsContrMachLvlNum3", "3");

        if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) {
            param.put("usgNm", DS_CONTR_BASE_USG_NM_B);
        } else {
            param.put("usgNm", DS_CONTR_BASE_USG_NM_U);
        }

//        param.put("slsDt", slsDt);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getPrcUpRatio", param);
    }
    private BigDecimal getUplftBasePrcUpRatio(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            param.put("dsContrDtlPk", dsContrDtlPk);
        }
        param.put("slsDt", slsDt);
        return (BigDecimal) this.ssmBatchClient.queryObject("getUplftBasePrcUpRatio", param);
    }
    // QC#17645-2 Add End

    // START 2019/11/06 K.Kitachi [QC#54368, ADD]
    private boolean isExistsPoInclRenewFromDate(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("dsContrMachLvlNum1", "1");
        param.put("dsContrMachLvlNum2", "2");
        param.put("dsContrMachLvlNum3", "3");
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countPoInclRenewFromDate", param);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }
    // END 2019/11/06 K.Kitachi [QC#54368, ADD]
}
