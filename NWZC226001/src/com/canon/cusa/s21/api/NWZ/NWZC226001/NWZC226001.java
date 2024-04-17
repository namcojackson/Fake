/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import static com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant.RQST_TP_DEL;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.BLANK;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.COMMA;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.CONST_NM_THEREFORE_COND_FLD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.CTAC_PT_TP_CD_FAX;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.CTAC_PT_TP_CD_MAIL;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.CTAC_PT_TP_CD_PHONE;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.DROP_SHIP_RTL_WH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.DS_ORD_POSN_NUM_1;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.DS_ORD_TP_INVENTORY_TRANSFER;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.DS_ORD_TP_INVENTORY_TRANSFER_CONFIG;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.EDI_CTY_MND_CHK_TGT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.EDI_CTY_ST_CMBN_CHK_TGT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.EDI_POST_MND_VALID_CHK_TGT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.EDI_ST_MND_VALID_CHK_RGT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.EXCLUDE_MESSAGE_CODE;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LEASE_BYOT_MDSE_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LINE_STS_BILLING_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LOAN_CONV_LINE_CRAT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LOAN_ORD_ACTION_LOAN_RETURN;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LOAN_ORD_ACTION_LOAN_SELL;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.LOAN_RTRN_RSN_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MDSE_CD_SHORT_LENGTH;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MDSE_TP_CTX_TP_MAIN_MACH;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MODE_CREATE_ORDER;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MODE_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MODE_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MODE_VALIDATE;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.NOT_ALLOC_WH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.ORD_LINE_STS_NM_CLOSED;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.ORD_LINE_STS_NM_ON_LOAN;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.ORD_LINE_STS_NM_PENDING_FULFILLMENT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PERCENT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PROCESS_DEFAULT_BILL_SHIP;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PROCESS_DEFAULT_TRANSACTION;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PROCESS_INSTRUCTION;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PROCESS_SHIPPING_DEFAULT_INFORMATION;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.SUB_WH_CD_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.THEREFORE_CONN_AVAL_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.TIME_SUBSTRING_FROM;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.TIME_SUBSTRING_TO;
import static com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.TOTAL_WT_THRESHOLD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDPMsg;
import parts.common.EZDStringUtil;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgConst;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CONFIG_TPTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DOC_MGT_ATT_RQSTTMsg;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_FLDTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CRTMsgArray;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_SVC_ADDL_BASETMsg;
import business.db.DS_IMPT_SVC_ADDL_CHRGTMsg;
import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.EDI_PO_ACK_DTLTMsg;
import business.db.EDI_PO_ACK_HDRTMsg;
import business.db.EDI_PO_ACK_SHPG_PLNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.POSTTMsg;
import business.db.POSTTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_MTR_PKGTMsg;
import business.db.PRC_MTR_PKGTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.STTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SWHTMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import business.db.XTRNL_INTFC_XREFTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_ShippingDefaultInfoListPMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NSZC048001_xxChildMdseCdListPMsgArray;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcAddlBasePrcListPMsg;
import business.parts.NSZC115001_svcAddlChrgPrcListPMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NSZC115001_svcDtlListPMsg;
import business.parts.NSZC115001_svcPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoIstlInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150001_siteSrvInfoListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC172001PMsg;
import business.parts.NWZC172002PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC181001PMsg;
import business.parts.NWZC181002PMsg;
import business.parts.NWZC182001PMsg;
import business.parts.NWZC182002PMsg;
import business.parts.NWZC206001PMsg;
import business.parts.NWZC224001PMsg;
import business.parts.NWZC226001PMsg;
import business.parts.NWZC226001_xxMsgPrmListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.NWZC172001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC181001.NWZC181001;
import com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC182001.NWZC182001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MSG_ID;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ImportAttribute;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001SomWebService;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ATT_DATA_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_RQST_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EDI_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;

/**
 * <pre>
 * NWZC2260 : Order Import API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2017/02/17   Fujitsu         M.Yamada        Update          QC#17666
 * 2017/02/27   Fujitsu         S.Ohki          Update          QC#17842
 * 2017/03/02   Fujitsu         N.Aoyama        Update          QC#16575
 * 2017/03/07   Fujitsu         S.Iidaka        Update          QC#17668
 * 2017/03/09   Fujitsu         Y.Kanefusa      Update          S21_NA#19701
 * 2017/03/13   Fujitsu         S.Ohki          Update          S21_NA#16790
 * 2017/03/14   Fujitsu         M.Ohno          Update          S21_NA#16855
 * 2017/03/16   Fujitsu         S.Iidaka        Update          S21_NA#18064
 * 2017/03/30   Fujitsu         S.Iidaka        Update          S21_NA#18180
 * 2017/03/30   Fujitsu         S.Ohki          Update          S21_NA#18175
 * 2017/03/30   Fujitsu         S.Iidaka        Update          S21_NA#18179
 * 2017/04/05   Fujitsu         S.Iidaka        Update          S21_NA#18206
 * 2017/04/12   Fujitsu         S.Iidaka        Update          S21_NA#18284
 * 2017/04/18   Fujitsu         S.Iidaka        Update          S21_NA#18360
 * 2017/04/19   Fujitsu         S.Iidaka        Update          S21_NA#18286
 * 2017/04/19   CITS            K.Ogino         Update          S21_NA#18366
 * 2017/04/24   Fujitsu         S.Iidaka        Update          S21_NA#18286-2
 * 2017/05/17   Fujitsu         S.Iidaka        Update          RS#8364
 * 2017/05/24   Fujitsu         M.Yamada        Update          S21_NA#18451
 * 2017/06/13   Fujitsu         R.Nakamura      Update          S21_NA#18984
 * 2017/06/15   Fujitsu         R.Nakamura      Update          S21_NA#19071
 * 2017/06/26   Fujitsu         R.Nakamura      Update          S21_NA#19449
 * 2017/06/28   Fujitsu         R.Nakamura      Update          S21_NA#19440
 * 2017/06/29   Fujitsu         R.Nakamura      Update          S21_NA#19068
 * 2017/07/04   Fujitsu         R.Nakamura      Update          S21_NA#19692
 * 2017/07/10   Fujitsu         S.Takami        Update          S21_NA#19789
 * 2017/07/19   Fujitsu         R.Nakamura      Update          S21_NA#19979
 * 2017/07/19   Fujitsu         R.Nakamura      Update          S21_NA#19797
 * 2017/07/20   Fujitsu         R.Nakamura      Update          S21_NA#19802
 * 2017/07/25   Fujitsu         R.Nakamura      Update          S21_NA#20114
 * 2017/07/27   Fujitsu         R.Nakamura      Update          S21_NA#20064
 * 2017/08/01   Fujitsu         S.Iidaka        Update          S21_NA#20097
 * 2017/08/01   Fujitsu         A.Kosai         Update          S21_NA#19413
 * 2017/08/01   Fujitsu         W.Honda         Update          S21_NA#20000
 * 2017/08/02   Fujitsu         S.Iidaka        Update          S21_NA#19127
 * 2017/08/09   Fujitsu         R.Nakamura      Update          S21_NA#20521
 * 2017/08/14   Fujitsu         S.Iidaka        Update          S21_NA#20579
 * 2017/08/15   Fujitsu         S.Takami        Update          S21_NA#20000-2
 * 2017/08/16   Fujitsu         S.Iidaka        Update          S21_NA#19088, 20627
 * 2017/08/21   Fujitsu         S.Iidaka        Update          S21_NA#20097-5
 * 2017/08/21   Fujitsu         R.Nakamura      Update          S21_NA#19233
 * 2017/08/25   Fujitsu         S.Iidaka        Update          S21_NA#20740-1
 * 2017/09/04   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2017/09/06   Fujitsu         S.Iidaka        Update          S21_NA#20790
 * 2017/09/08   Fujitsu         S.Iidaka        Update          S21_NA#20992
 * 2017/09/11   Fujitsu         R.Nakamura      Update          S21_NA#21033
 * 2017/09/13   Fujitsu         S.Iidaka        Update          S21_NA#21084
 * 2017/09/14   Fujitsu         T.Aoi           Update          S21_NA#20841
 * 2017/09/21   Fujitsu         S.Iidaka        Update          S21_NA#20548
 * 2017/09/25   Fujitsu         A.Kosai         Update          S21_NA#20799
 * 2017/09/26   Fujitsu         M.Yamada        Update          S21_NA#20841(L3#384)
 * 2017/09/28   Fujitsu         R.Nakamura      Update          S21_NA#20689
 * 2017/10/02   Fujitsu         S.Iidaka        Update          S21_NA#20625
 * 2017/10/03   Fujitsu         R.Nakamura      Update          S21_NA#20020
 * 2017/10/05   Fujitsu         M.Yamada        Update          S21_NA#21634
 * 2017/10/23   Fujitsu         R.Nakamura      Update          S21_NA#20625-2
 * 2017/10/24   CITS            T.Gotoda        Update          S21_NA#22010, 22011, 22012, 22014
 * 2017/11/01   Fujitsu         S.Ohki          Update          S21_NA#22184
 * 2017/11/01   Fujitsu         R.Nakamura      Update          S21_NA#21386
 * 2017/11/10   Fujitsu         S.Takami        Update          S21_NA#22464
 * 2017/11/14   Fujitsu         Y.Kanefusa      Update          S21_NA#22490
 * 2017/11/20   Fujitsu         M.Yamada        Update          S21_NA#22626
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2017/11/21   Fujitsu         M.Yamada        Update          S21_NA#22641
 * 2017/11/28   Fujitsu         Y.Kanefusa      Update          S21_NA#22443
 * 2017/12/06   Fujitsu         S.Takami        Update          S21_NA#22794
 * 2017/12/07   Fujitsu         K.Ishizuka      Update          S21_NA#22923
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          S21_NA#19804(Sol349)
 * 2017/12/28   Fujitsu         A.Kosai         Update          S21_NA#23083 
 * 2018/01/04   Fujitsu         A.Kosai         Update          S21_NA#23116
 * 2018/01/09   Fujitsu         T.Murai         Update          S21_NA#23116
 * 2017/12/10   Fujitsu         Mz.Takahashi    Update          S21_NA#22371
 * 2018/01/12   Fujitsu         N.Sugiura       Update          S21_NA#22205
 * 2018/01/16   Fujitsu         S.Ohki          Update          S21_NA#22372
 * 2018/01/23   Fujitsu         T.Aoi           Update          S21_NA#18798(Sol173)
 * 2018/01/23   Fujitsu         M.Ohno          Update          S21_NA#22776
 * 2018/01/29   Fujitsu         M.Ohno          Update          S21_NA#22772
 * 2018/01/31   Fujitsu         M.Ohno          Update          S21_NA#22772-2
 * 2018/01/31   Fujitsu         T.Aoi           Update          S21_NA#23866
 * 2018/02/01   Fujitsu         A.Kosai         Update          S21_NA#22617
 * 2018/02/05   Fujitsu         T.Aoi           Update          S21_NA#23329(Sol#387)
 * 2018/02/07   Fujitsu         T.Aoi           Update          S21_NA#23998
 * 2018/02/08   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#397)
 * 2018/02/09   Fujitsu         Y.Kanefusa      Update          S21_NA#23192
 * 2018/02/19   Fujitsu         Y.Kanefusa      Update          S21_NA#24093
 * 2018/02/28   Fujitsu         T.Aoi           Update          S21_NA#24115
 * 2018/03/01   Fujitsu         T.Aoi           Update          S21_NA#23934
 * 2018/03/01   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/03/05   Fujitsu         S.Ohki          Update          S21_NA#24103
 * 2018/03/13   Fujitsu         Hd.Sugawara     Update          S21_NA#22967
 * 2018/03/14   Fujitsu         R.Nakamura      Update          S21_NA#23507
 * 2018/03/14   Fujitsu         S.Ohki          Update          S21_NA#20153(Sol#152)
 * 2018/03/15   Fujitsu         R.Nakamura      Update          S21_NA#24258
 * 2018/03/20   Fujitsu         S.Ohki          Update          S21_NA#24208
 * 2018/03/20   Fujitsu         R.Nakamura      Update          S21_NA#23991
 * 2018/03/30   Fujitsu         R.Nakamura      Update          S21_NA#25134
 * 2018/04/02   Fujitsu         M.Ohno          Update          S21_NA#20162
 * 2018/04/04   Fujitsu         S.Ohki          Update          S21_NA#22025
 * 2018/04/17   Fujitsu         R.Nakamura      Update          S21_NA#22187
 * 2018/04/27   Fujitsu         S.Takami        Update          S21_NA#22189
 * 2018/05/08   Fujitsu         S.Ohki          Update          S21_NA#25770
 * 2018/05/10   Fujitsu         M.Yamada        Update          S21_NA#25030
 * 2018/05/20   Fujitsu         W.Honda         Update          S21_NA#24244
 * 2018/05/29   Fujitsu         A.Kosai         Update          S21_NA#24587
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 * 2018/06/05   Fujitsu         A.Kosai         Update          S21_NA#26443
 * 2018/06/06   Fujitsu         S.Ohki          Update          S21_NA#25770-2
 * 2018/06/11   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/25   Fujitsu         Hd.Sugawara     Update          S21_NA#23726
 * 2018/06/28   Fujitsu         K.Ishizuka      Update          S21_NA#26608,26549
 * 2018/06/29   Fujitsu         Hd.Sugawara     Update          S21_NA#27035
 * 2018/07/02   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/07/05   Fujitsu         A.Kosai         Update          S21_NA#26909
 * 2018/07/10   Fujitsu         H.Nagashima     Update          S21_NA#26895
 * 2018/07/10   Fujitsu         A.Kosai         Update          S21_NA#26936
 * 2018/07/13   Fujitsu         S.Takami        Update          S21_NA#27228
 * 2018/07/24   Fujitsu         R.Nakamura      Update          S21_NA#26828
 * 2018/07/25   Fujitsu         M.Yamada        Update          S21_NA#26909
 * 2018/07/30   Fujitsu         R.Nakamura      Update          S21_NA#27389
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/09   Fujitsu         K.Ishizuka      Update          S21_NA#27677
 * 2018/08/10   Fujitsu         K.Ishizuka      Update          S21_NA#25829
 * 2018/08/15   Fujitsu         K.Ishizuka      Update          S21_NA#27771
 * 2018/08/16   Fujitsu         M.Yamada        Update          S21_NA#27738
 * 2018/08/21   Fujitsu         K.Ishizuka      Update          S21_NA#27817
 * 2018/08/21   Fujitsu         Hd.Sugawara     Update          S21_NA#27489
 * 2018/08/30   Fujitsu         S.Takami        Update          S21_NA#26895-3
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/09/18   Fujitsu         K.Ishizuka      Update          S21_NA#28270
 * 2018/09/27   Fujitsu         S.Takami        Update          S21_NA#28199 (S21_NA#26895-3 -> reuse S21_NA#26895 without comments)
 * 2018/10/25   Fujitsu         K.Ishizuka      Update          S21_NA#28817
 * 2018/11/08   Fujitsu         K.Kato          Update          S21_NA#28951
 * 2018/11/21   Fujitsu         R.Nakamura      Update          S21_NA#29184
 * 2018/11/28   Fujitsu         K.Ishizuka      Update          S21_NA#28899
 * 2018/11/29   Fujitsu         Y.Kanefusa      Update          S21_NA#29282
 * 2018/12/03   Fujitsu         M.Ishii         Update          S21_NA#29362
 * 2018/12/05   Fujitsu         K.Ishizuka      Update          S21_NA#29287
 * 2018/12/05   Fujitsu         K.Ishizuka      Update          S21_NA#29484
 * 2018/12/14   Fujitsu         T.Noguchi       Update          S21_NA#29315
 * 2018/12/28   Fujitsu         K.Ishizuka      Update          S21_NA#29772
 * 2018/12/28   Fujitsu         N.Sugiura       Update          S21_NA#29759
 * 2019/01/05   Fujitsu         N.Sugiura       Update          S21_NA#29579
 * 2019/01/08   Fujitsu         K.Ishizuka      Update          S21_NA#29788
 * 2019/01/11   Fujitsu         K.Ishizuka      Update          S21_NA#29875
 * 2019/01/16   Fujitsu         K.Ishizuka      Update          S21_NA#29967
 * 2019/01/16   Fujitsu         S.Kosaka        Update          S21_NA#29642
 * 2019/01/16   Fujitsu         T.Noguchi       Update          S21_NA#29535
 * 2019/01/17   Fujitsu         K.Kato          Update          S21_NA#29942
 * 2019/01/25   Fujitsu         K.Kato          Update          S21_NA#29942
 * 2019/01/29   Fujitsu         K.Ishizuka      Update          S21_NA#30022
 * 2019/01/30   Fujitsu         K.Kato          Update          S21_NA#30036
 * 2019/02/05   Fujitsu         K.Ishizuka      Update          S21_NA#30236
 * 2019/02/14   Fujitsu         K.Ishizuka      Update          S21_NA#30340
 * 2019/03/13   Fujitsu         R.Nakamura      Update          S21_NA#30751
 * 2019/03/14   Fujitsu         K.Ishizuka      Update          S21_NA#30770
 * 2019/03/20   Fujitsu         K.Ishizuka      Update          S21_NA#30821
 * 2019/05/29   Fujitsu         R.Nakamura      Update          S21_NA#50405
 * 2019/05/30   Fujitsu         Y.Kanefusa      Update          S21_NA#50512
 * 2019/06/12   Fujitsu         R.Nakamura      Update          S21_NA#50799
 * 2019/10/01   Fujitsu         S.Kosaka        Update          S21_NA#53597
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/10/30   Fujitsu         Mz.Takahashi    Update          S21_NA#53993
 * 2019/11/07   Fujitsu         R.Nakamura      Update          S21_NA#54511
 * 2019/11/08   Fujitsu         S.Takami        Update          S21_NA#54208
 * 2019/12/05   Fujitsu         K.Kato          Update          QC#54219
 * 2019/12/17   Fujitsu         S.Takami        Update          S21_NA#54721
 * 2020/01/06   Fujitsu         K.Kato          Update          QC#54219
 * 2020/01/14   Fujitsu         Y.Kanefusa      Update          S21_NA#55397
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2021/01/30   CITS            K.Ogino         Update          QC#58230
 * 2021/02/17   CITS            K.Ogino         Update          QC#59719
 * 2022/02/21   Fujitsu         C.Hara          Update          QC#59693
 * 2022/05/20   CITS            K.Ogino         Update          QC#59719-1
 * 2022/07/08   CITS            K.Ogino         Update          QC#60296
 * 2022/12/10   CITS            S.Sugimoto      Update          QC#60885/60717
 * 2021/01/05   CITS            K.Ogino         Update          QC#60971
 * 2023/04/04   Hitachi         T.Doi           Update          QC#60254
 * 2023/05/09   CITS            R.Azucena       Update          QC#61420
 *</pre>
 */
public class NWZC226001 extends S21ApiCommonBase {

    // 2017/03/30 S21_NA#18179 Mod Start
    /** LEASE_BYOT_MDSE_CD */
    private String leaseByotMdseCd = null;
    // 2017/03/30 S21_NA#18179 Mod End

    // 2018/01/23 QC#18798 Add Start
    private BigDecimal paramDsImptOrdConfigPk = null;
    private String dsImptOrdConfigFlg = ZYPConstant.FLG_OFF_N;
    // 2018/01/23 QC#18798 Add End

    /** constructor */
    public NWZC226001() {

        super();
    }

    /**
     * Order Import API
     * 
     * <pre>
     * Import Order.
     * 
     * @param param         NWZC226001PMsg
     * @param onBatchType   ONBATCH_TP
     * </pre>
     */
    public void execute(final NWZC226001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {

            // check input parameters
            checkParam(msgMap);
            if (msgMap.getMsgMgr().isXxMsgId()) {

                return;
            }

            // import order.
            mainRoutine(msgMap, onBatchType);
            if (msgMap.getMsgMgr().isXxMsgId()) {

                return;
            }

        } catch (EZDDBRecordLockedException e) {

            throw e;
        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    private void checkParam(S21ApiMessageMap msgMap) {

        NWZC226001PMsg inPrmPMsg = (NWZC226001PMsg) msgMap.getPmsg();

        // global company code is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM0188E);
        }

        // sales date is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsDt)) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM0978E);
        }

        // mode is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.xxModeCd)) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM0012E);
        }

        // valid mode.
        if (!Arrays.asList(MODE_VALIDATE, MODE_CREATE_ORDER).contains(inPrmPMsg.xxModeCd.getValue())) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM0013E);
        }

        // order source reference number is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.dsImptOrdPk)) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM1905E);
        }
    }

    private void mainRoutine(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        writeStartLogLn("mainRoutine");

        NWZC226001PMsg msg = (NWZC226001PMsg) msgMap.getPmsg();
        // 2017/03/30 S21_NA#18179 Mod Start
        this.leaseByotMdseCd = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_MDSE_CD, msg.glblCmpyCd.getValue());
        // 2017/03/30 S21_NA#18179 Mod End

        try {

            // *****************************************************************
            // Get ImptHdrBean From DS_IMPT_ORD
            // *****************************************************************
            List<ImptHdrBean> hdrBeanList = createHeaderBeanList(msgMap, onBatchType);

            if (hdrBeanList != null) {
                for (ImptHdrBean hdrBean : hdrBeanList) {
                    if (hdrBean != null) {

                        // *************************************************************
                        // Table Line Lock
                        // *************************************************************
                        DS_IMPT_ORDTMsg dsImptOrdTMsg = new DS_IMPT_ORDTMsg();
                        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.glblCmpyCd, msg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
                        // 2018/06/13 S21_NA#24294 Mod Start
                        // dsImptOrdTMsg = (DS_IMPT_ORDTMsg) S21ApiTBLAccessor.findByKey(dsImptOrdTMsg);
                        dsImptOrdTMsg = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKey(dsImptOrdTMsg);
                        // 2018/06/13 S21_NA#24294 Mod End
                        checkTMsgDbAccess(dsImptOrdTMsg);
                        
                        // *************************************************************
                        // Import Order
                        // *************************************************************
                        if (importOrder(msgMap, onBatchType, hdrBean, dsImptOrdTMsg)) {

                            // *********************************************************
                            // Update EDI Data
                            // *********************************************************
                            if (hdrBean.isEdiData()) {

                                if (!updateEDiData(hdrBean, dsImptOrdTMsg)) {

                                    // no process
                                }
                            }

                        } else {

                            writeLogLn("error (%s)", getTargetKey(hdrBean));
                        }

                        // *************************************************************
                        // Regist EDI ACK Data
                        // *************************************************************
                        if (hdrBean.isEdiData() && ZYPConstant.FLG_ON_Y.equals(hdrBean.getSendPoAckFlg())) {

                            registEDiAckData(hdrBean);
                        }

                        // *************************************************************
                        // set error
                        // *************************************************************
                        setError(msgMap, hdrBean);

                        // *************************************************************
                        // out put
                        // *************************************************************
                        setOutputParam(msgMap, hdrBean);
                    }
                }
            }

        } finally {

            writeEndLogLn("mainRoutine");
        }
    }

    private void setOutputParam(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {

        NWZC226001PMsg msg = (NWZC226001PMsg) msgMap.getPmsg();

        writeStartLogLn("setOutputParam", hdrBean);

        try {

            if (hdrBean == null) {

                return;
            }
            ZYPEZDItemValueSetter.setValue(msg.ordSrcImptTs, hdrBean.getOrdSrcImptTs());
            if (hdrBean.isUpdateData()) {

                ZYPEZDItemValueSetter.setValue(msg.cpoOrdNum, hdrBean.getOrigOrdNum());
            } else {

                ZYPEZDItemValueSetter.setValue(msg.cpoOrdNum, hdrBean.getNewOrdNum());
            }
            if (ZYPCommonFunc.hasValue(msg.cpoOrdNum)) {

                CPOTMsg cpo = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, msg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, msg.cpoOrdNum);
                cpo = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpo);
                if (cpo != null) {

                    ZYPEZDItemValueSetter.setValue(msg.dsCpoCratTs, cpo.dsCpoCratTs);
                }
            }
            ZYPEZDItemValueSetter.setValue(msg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
        } finally {

            writeEndLogLn("setOutputParam", hdrBean);
        }
    }

    private void setError(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {

        writeStartLogLn("setError", hdrBean);

        try {

            List<DsImptOrdErrBean> dsImptOrdErrList = hdrBean.getAllErrorBean();

            for (DsImptOrdErrBean errBean : dsImptOrdErrList) {

                setMsgIdWithParam(msgMap, errBean, errBean.dsImptOrdErrMsgId.getValue(), errBean.getMsgParam());
            }
        } finally {

            writeErrLog(hdrBean);
            writeEndLogLn("setError", hdrBean);
        }
    }

    private void setMsgIdWithParam(S21ApiMessageMap msgMap, NWZC226001_xxMsgPrmListPMsg msgPrm, MSG_ID msgId, String... param) {

        setMsgIdWithParam(msgMap, msgPrm, msgId.name(), param);
    }

    private void setMsgIdWithParam(S21ApiMessageMap msgMap, NWZC226001_xxMsgPrmListPMsg msgPrm, String msgId, String... param) {

        if (EXCLUDE_MESSAGE_CODE.contains(msgId)) {

            // exclude error message
            return;
        }

        NWZC226001PMsg inPrmPMsg = (NWZC226001PMsg) msgMap.getPmsg();
        if (msgPrm == null) {

            msgPrm = new NWZC226001_xxMsgPrmListPMsg();
        }
        ZYPEZDItemValueSetter.setValue(msgPrm.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(msgId, param));

        int validCount = inPrmPMsg.xxMsgIdList.getValidCount();
        if (validCount >= inPrmPMsg.xxMsgIdList.length()) {

            return;
        }

        NWZC226001_xxMsgPrmListPMsg xxMsgPrm = inPrmPMsg.xxMsgPrmList.no(validCount);
        EZDMsg.copy(msgPrm, null, xxMsgPrm, null);
        msgMap.addXxMsgIdWithPrm(msgId, param);
        inPrmPMsg.xxMsgPrmList.setValidCount(validCount + 1);
    }

    private List<ImptHdrBean> createHeaderBeanList(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC226001PMsg msg = (NWZC226001PMsg) msgMap.getPmsg();

        DS_IMPT_ORDTMsg dsImptOrdTMsg = new DS_IMPT_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.glblCmpyCd, msg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsImptOrdPk, msg.dsImptOrdPk);
        // 2018/06/13 S21_NA#24294 Mod Start
        // dsImptOrdTMsg = (DS_IMPT_ORDTMsg) S21ApiTBLAccessor.findByKey(dsImptOrdTMsg);
        dsImptOrdTMsg = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKey(dsImptOrdTMsg);
        // 2018/06/13 S21_NA#24294 Mod End

        if (dsImptOrdTMsg == null) {
            setMsgIdWithParam(msgMap, null, MSG_ID.NWAM0525E, new String[] {"DS_IMPT_ORD", String.valueOf(msg.dsImptOrdPk.getValue()) });
            return null;
        }

        Map<String, Object> loanCxtCntParam = new HashMap<String, Object>();
        loanCxtCntParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
        loanCxtCntParam.put("dsImptOrdPk", msg.dsImptOrdPk);
        loanCxtCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
        BigDecimal loanCxtCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtx", loanCxtCntParam);

        boolean isLoan = false;
        if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(dsImptOrdTMsg.cpoSrcTpCd.getValue())) {  // 2017/03/13 S21_NA#16790 Add
            if (loanCxtCnt.compareTo(BigDecimal.ZERO) > 0) {
                isLoan = true;
            } else {
                Map<String, Object> getConfigParam = new HashMap<String, Object>();
                getConfigParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
                getConfigParam.put("dsImptOrdPk", msg.dsImptOrdPk);
                getConfigParam.put("configTpCd", CONFIG_TP.TO_SALES_CONVERSION);
                // 2019/01/29 S21_NA#30022 Add Start
                getConfigParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);
                getConfigParam.put("configCatgIn", CONFIG_CATG.INBOUND);
                getConfigParam.put("imptLineFlgY", ZYPConstant.FLG_ON_Y);
                // 2019/01/29 S21_NA#30022 Add End

                List<Map<String, Object>> configList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdConfigPk", getConfigParam);
                if (hasValueList(configList)) {
                    isLoan = true;
                }
            }
        }

        // L3#384
        Map<String, Object> leaseCxtCntParam = new HashMap<String, Object>();
        leaseCxtCntParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
        leaseCxtCntParam.put("dsImptOrdPk", msg.dsImptOrdPk);
        leaseCxtCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LEASE_ORDER_VALUE_SET);
        BigDecimal leaseCxtCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtx", leaseCxtCntParam);
        boolean isLeaseOrd = leaseCxtCnt.compareTo(BigDecimal.ZERO) > 0;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        ssmParam.put("dsImptOrdPk", msg.dsImptOrdPk.getValue());
        ssmParam.put("isLoan", String.valueOf(isLoan));
        List<String> ordHdrStsCdList = new ArrayList<String>(); // S21_NA#22626
        List<String> ordLineStsCdList = new ArrayList<String>(); // S21_NA#22626
        if (isLoan) {
            ssmParam.put("configCatgCd", CONFIG_CATG.OUTBOUND);
            ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);

//            List<String> ordHdrStsCdList = new ArrayList<String>(); // S21_NA#22626
            ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
            ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
            ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

//            List<String> ordLineStsCdList = new ArrayList<String>(); // S21_NA#22626
            //ordLineStsCdList.add(ORD_LINE_STS.CLOSED);  // 2017/09/04 S21_NA#19800 Del
            ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("ordLineStsCdList", ordLineStsCdList);
        }

        List<Map<String, Object>> headerList = NWZC226001Query.getInstance().queryMapList("getImptHdr", ssmParam);

        if (headerList == null) {

            setMsgIdWithParam(msgMap, null, MSG_ID.NWZM2204E);
            return null;
        }

        List<ImptHdrBean> hdrBeanList = new ArrayList<ImptHdrBean>();
        ImptHdrBean headerBean = null;

        for (Map<String, Object> header : headerList) {
            headerBean = new ImptHdrBean();

            headerBean.setGlblCmpyCd(msg.glblCmpyCd.getValue());
            headerBean.setSlsDt(msg.slsDt.getValue());
            headerBean.setXxModeCd(msg.xxModeCd.getValue());
            headerBean.setOnbtchType(onBatchType);

            headerBean.setDsImptOrdPk(convToBigDecimal((BigDecimal) header.get("DS_IMPT_ORD_PK")));
            headerBean.setCpoSrcTpCd(convToString((String) header.get("CPO_SRC_TP_CD")));
            headerBean.setOrdSrcImptTs(convToString((String) header.get("ORD_SRC_IMPT_TS")));
            headerBean.setOrdSrcRefNum(convToString((String) header.get("ORD_SRC_REF_NUM")));
            headerBean.setImptStsCd(convToString((String) header.get("IMPT_STS_CD")));
            headerBean.setSysSrcCd(convToString((String) header.get("SYS_SRC_CD")));
            headerBean.setDsOrdCatgCd(convToString((String) header.get("DS_ORD_CATG_CD")));
            headerBean.setDsOrdTpCd(convToString((String) header.get("DS_ORD_TP_CD")));
            headerBean.setDsOrdRsnCd(convToString((String) header.get("DS_ORD_RSN_CD")));
            headerBean.setCpoTpCd(convToString((String) header.get("CPO_ORD_TP_CD")));
            headerBean.setCustIssPoNum(convToString((String) header.get("CUST_ISS_PO_NUM")));
            headerBean.setCustIssPoDt(convToString((String) header.get("CUST_ISS_PO_DT")));
            headerBean.setBillToCustAcctCd(convToString((String) header.get("BILL_TO_CUST_ACCT_CD")));
            headerBean.setBillToCustCd(convToString((String) header.get("BILL_TO_CUST_CD")));
            headerBean.setShipToCustAcctCd(convToString((String) header.get("SHIP_TO_CUST_ACCT_CD")));
            headerBean.setShipToCustCd(convToString((String) header.get("SHIP_TO_CUST_CD")));
            headerBean.setSellToCustCd(convToString((String) header.get("SELL_TO_CUST_CD")));
            headerBean.setSoldToCustLocCd(convToString((String) header.get("SOLD_TO_CUST_LOC_CD")));
            headerBean.setDropShipFlg(convToString((String) header.get("DROP_SHIP_FLG")));
            headerBean.setShipToLocNm(convToString((String) header.get("SHIP_TO_LOC_NM")));
            headerBean.setShipToAddlLocNm(convToString((String) header.get("SHIP_TO_ADDL_LOC_NM")));
            headerBean.setShipToFirstLineAddr(convToString((String) header.get("SHIP_TO_FIRST_LINE_ADDR")));
            headerBean.setShipToScdLineAddr(convToString((String) header.get("SHIP_TO_SCD_LINE_ADDR")));
            headerBean.setShipToThirdLineAddr(convToString((String) header.get("SHIP_TO_THIRD_LINE_ADDR")));
            headerBean.setShipToFrthLineAddr(convToString((String) header.get("SHIP_TO_FRTH_LINE_ADDR")));
            headerBean.setShipToCtyAddr(convToString((String) header.get("SHIP_TO_CTY_ADDR")));
            headerBean.setShipToStCd(convToString((String) header.get("SHIP_TO_ST_CD")));
            headerBean.setShipToProvNm(convToString((String) header.get("SHIP_TO_PROV_NM")));
            headerBean.setShipToCntyNm(convToString((String) header.get("SHIP_TO_CNTY_NM")));
            headerBean.setShipToPostCd(convToString((String) header.get("SHIP_TO_POST_CD")));
            headerBean.setShipToCtryCd(convToString((String) header.get("SHIP_TO_CTRY_CD")));
            headerBean.setShipTo01RefCmntTxt(convToString((String) header.get("SHIP_TO_01_REF_CMNT_TXT")));
            headerBean.setShipTo02RefCmntTxt(convToString((String) header.get("SHIP_TO_02_REF_CMNT_TXT")));
            headerBean.setAdminPsnCd(convToString((String) header.get("ADMIN_PSN_CD")));
            headerBean.setRddDt(convToString((String) header.get("RDD_DT")));
            headerBean.setFrtCondCd(convToString((String) header.get("FRT_COND_CD")));
            headerBean.setSpclHdlgTpCd(convToString((String) header.get("SPCL_HDLG_TP_CD")));
            headerBean.setCarrCd(convToString((String) header.get("CARR_CD")));
            headerBean.setCarrSvcLvlCd(convToString((String) header.get("CARR_SVC_LVL_CD")));
            headerBean.setShpgSvcLvlCd(convToString((String) header.get("SHPG_SVC_LVL_CD")));
            headerBean.setFrtChrgToCd(convToString((String) header.get("FRT_CHRG_TO_CD")));
            headerBean.setFrtChrgMethCd(convToString((String) header.get("FRT_CHRG_METH_CD")));
            headerBean.setCarrAcctNum(convToString((String) header.get("CARR_ACCT_NUM")));
            headerBean.setAddPmtTermCashDiscCd(convToString((String) header.get("ADD_PMT_TERM_CASH_DISC_CD")));
            headerBean.setDsPmtMethCd(convToString((String) header.get("DS_PMT_METH_CD")));
            headerBean.setPrePmtChkNum(convToString((String) header.get("PRE_PMT_CHK_NUM")));
            headerBean.setPrePmtAmt(convToBigDecimal((BigDecimal) header.get("PRE_PMT_AMT")));
            headerBean.setPrePmtTpCd(convToString((String) header.get("PRE_PMT_TP_CD")));
            headerBean.setPrcBaseDt(convToString((String) header.get("PRC_BASE_DT")));
            headerBean.setNegoDealAmt(convToBigDecimal((BigDecimal) header.get("NEGO_DEAL_AMT")));
            headerBean.setPrcCatgCd(convToString((String) header.get("PRC_CATG_CD")));
            headerBean.setFlPrcListCd(convToString((String) header.get("FL_PRC_LIST_CD")));
            headerBean.setPrcContrNum(convToString((String) header.get("PRC_CONTR_NUM")));
            headerBean.setCsmpContrNum(convToString((String) header.get("CSMP_CONTR_NUM")));
            headerBean.setDlrRefNum(convToString((String) header.get("DLR_REF_NUM")));
            headerBean.setOrdSgnDt(convToString((String) header.get("ORD_SGN_DT")));
            headerBean.setAquNum(convToString((String) header.get("AQU_NUM")));
            headerBean.setSlsRepTocCd(convToString((String) header.get("SLS_REP_TOC_CD")));
            headerBean.setLoanPerDaysAot((BigDecimal) header.get("LOAN_PER_DAYS_AOT"));
            // S21_NA#11841
            headerBean.setLeaseCmpyPoNum(convToString((String) header.get("LEASE_CMPY_PO_NUM")));
            headerBean.setLeaseTermCd(convToString((String) header.get("LEASE_TERM_CD")));
            headerBean.setLeaseEndTermPrchOptCd(convToString((String) header.get("LEASE_PRCH_OPT_CD")));
            headerBean.setLeasePmtFreqCd(convToString((String) header.get("LEASE_PMT_FREQ_CD")));
            headerBean.setOrdLogTpCd(convToString((String) header.get("ORD_LOG_TP_CD")));
            headerBean.setCrRebilRsnCatgCd(convToString((String) header.get("CR_REBIL_RSN_CATG_CD")));
            headerBean.setOrigOrdNum(convToString((String) header.get("ORIG_ORD_NUM")));
            headerBean.setSendInvFlg(convToString((String) header.get("SEND_INV_FLG")));
            headerBean.setReBillPairCpoOrdNum(convToString((String) header.get("RE_BILL_PAIR_CPO_ORD_NUM")));
            headerBean.setOrdHdrEdtblFlg(convToString((String) header.get("ORD_HDR_EDTBL_FLG")));
            headerBean.setOrdCratModeCd(convToString((String) header.get("ORD_CRAT_MODE_CD")));
            headerBean.setAutoDocAttFlg(convToString((String) header.get("AUTO_DOC_ATT_FLG")));
            headerBean.setDclnSvcCd(convToString((String) header.get("DCLN_SVC_CD")));
            headerBean.setLeaseTermMthAot(convToBigDecimal((BigDecimal) header.get("LEASE_TERM_MTH_AOT")));
            headerBean.setPrcFrzFlg(convToString((String) header.get("PRC_FRZ_FLG")));
            headerBean.setMaintOnlyFlg(convToString((String) header.get("MAINT_ONLY_FLG")));

            if (isLoan) {
             // 2017/02/27 QC#17842 Add Start
                // 2017/09/27 S21_NA#19800(L3) Mod Start
                // if (ZYPCommonFunc.hasValue((BigDecimal) header.get("SVC_CONFIG_MSTR_PK")) && !ZYPCommonFunc.hasValue((String) header.get("LOAN_ORIG_ORD_NUM"))) {
                if (!ZYPCommonFunc.hasValue((String) header.get("LOAN_ORIG_ORD_NUM"))) {
                    ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", headerBean.getGlblCmpyCd());
                    ssmParam.put("dsImptOrdPk", headerBean.getDsImptOrdPk());
                    List<Map<String, Object>> dsImptOrdConfigList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdConfigList", ssmParam);
                    if (dsImptOrdConfigList == null) {

                        NWZC226001_xxMsgPrmListPMsg pMsg = new NWZC226001_xxMsgPrmListPMsg();
                        pMsg.dsImptOrdPk.setValue(headerBean.getDsImptOrdPk());
                        pMsg.dsImptOrdErrMsgId.setValue(MSG_ID.NWZM2221E.toString());
                        setMsgIdWithParam(msgMap, pMsg, MSG_ID.NWZM2221E);
                        return null;
                    }
                    for (Map<String, Object> config : dsImptOrdConfigList) {
                        if (ZYPCommonFunc.hasValue((BigDecimal) config.get("SVC_CONFIG_MSTR_PK"))) {

                            // 2018/03/14 QC#20153 Del Start
//                                NWZC226001_xxMsgPrmListPMsg pMsg = new NWZC226001_xxMsgPrmListPMsg();
//                                pMsg.dsImptOrdPk.setValue(headerBean.getDsImptOrdPk());
//                                pMsg.dsImptOrdErrMsgId.setValue(MSG_ID.NWZM2221E.toString());
//                                setMsgIdWithParam(msgMap, pMsg, MSG_ID.NWZM2221E);
//                                return null; 
                            // 2018/03/14 QC#20153 Del Start
                            
                            // 2018/03/14 QC#20153 Add Start
                            Map<String, Object> getOrigOrdNumParam = new HashMap<String, Object>();
                            getOrigOrdNumParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
                            getOrigOrdNumParam.put("svcConfigMstrPk", (BigDecimal) config.get("SVC_CONFIG_MSTR_PK"));
                            String origOrdNum = NWZC226001Query.getInstance().queryString("getOrigOrdNum", getOrigOrdNumParam);

                            // Del Start 2018/07/30 QC#27389
//                            // 2018/06/28 S21_NA#26608,26549 Add Start
//                            if (!ZYPCommonFunc.hasValue(origOrdNum)) {
//                                NWZC226001_xxMsgPrmListPMsg pMsg = new NWZC226001_xxMsgPrmListPMsg();
//                                pMsg.dsImptOrdPk.setValue(headerBean.getDsImptOrdPk());
//                                pMsg.dsImptOrdErrMsgId.setValue(MSG_ID.NWZM2221E.toString());
//                                setMsgIdWithParam(msgMap, pMsg, MSG_ID.NWZM2221E);
//                                return null;
//                            }
                            // Del End 2018/07/30 QC#27389
                            // 2018/06/28 S21_NA#26608,26549 Add End
                            // Mod Start 2018/07/30 QC#27389
                            BigDecimal loanOrdCnt = BigDecimal.ZERO;
                            if (ZYPCommonFunc.hasValue(origOrdNum)) {
                                Map<String, Object> loanOrdCntParam = new HashMap<String, Object>();
                                loanOrdCntParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
                                loanOrdCntParam.put("cpoOrdNum", origOrdNum);
                                loanOrdCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
//                                BigDecimal loanOrdCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtxByOrdNum", loanOrdCntParam);
                                loanOrdCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtxByOrdNum", loanOrdCntParam);
                            }
                            // Mod End 2018/07/30 QC#27389

                            // Original Order is LOAN
                            if (loanOrdCnt.compareTo(BigDecimal.ZERO) > 0) {
                                NWZC226001_xxMsgPrmListPMsg pMsg = new NWZC226001_xxMsgPrmListPMsg();
                                pMsg.dsImptOrdPk.setValue(headerBean.getDsImptOrdPk());
                                pMsg.dsImptOrdErrMsgId.setValue(MSG_ID.NWZM2221E.toString());
                                setMsgIdWithParam(msgMap, pMsg, MSG_ID.NWZM2221E);
                                return null;                                                // QC#55237 2020/01/14 Add
                            // Original Order is Not LOAN
                            } else {
                                // Set a flag if the original order is not a Loan.
                                headerBean.setIsNotLoanOrigOrdFlg(true);
                            }
                            // 2018/03/14 QC#20153 Add End
                        }
                    }
                }
                // 2017/09/27 S21_NA#19800(L3) Mod End
                // 2017/02/27 QC#17842 Add End

                headerBean.setConfigTpCd(convToString((String) header.get("CONFIG_TP_CD")));
                // 2018/03/14 QC#20153 Del Start
//                headerBean.setLoanOrigOrdNum(convToString((String) header.get("LOAN_ORIG_ORD_NUM")));
//                headerBean.setLoanOrigDsOrdCatgCd(convToString((String) header.get("LOAN_ORIG_DS_ORD_CATG_CD")));
//                if (!CONFIG_TP.TO_SALES_CONVERSION.equals((String) header.get("CONFIG_TP_CD"))) {
//                    headerBean.setOrigOrdNum(convToString((String) header.get("LOAN_ORIG_ORD_NUM")));
//                }
                // 2018/03/14 QC#20153 Del End

                // 2018/03/14 QC#20153 Add Start
                if (ZYPCommonFunc.hasValue((String) header.get("LOAN_ORIG_ORD_NUM"))) {

                    String loanOrigOrdNum = (String) header.get("LOAN_ORIG_ORD_NUM");

                    Map<String, Object> loanOrdCntParam = new HashMap<String, Object>();
                    loanOrdCntParam.put("glblCmpyCd", msg.glblCmpyCd.getValue());
                    loanOrdCntParam.put("cpoOrdNum", loanOrigOrdNum);
                    loanOrdCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
                    BigDecimal loanOrdCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtxByOrdNum", loanOrdCntParam);

                    // Original Order is LOAN
                    // Mod Start 2018/03/30 QC#25134
                    if (loanOrdCnt.compareTo(BigDecimal.ZERO) > 0) {

                        headerBean.setLoanOrigOrdNum(convToString((String) header.get("LOAN_ORIG_ORD_NUM")));
                        headerBean.setLoanOrigDsOrdCatgCd(convToString((String) header.get("LOAN_ORIG_DS_ORD_CATG_CD")));
                        if (!CONFIG_TP.TO_SALES_CONVERSION.equals((String) header.get("CONFIG_TP_CD"))) {
                            headerBean.setOrigOrdNum(convToString((String) header.get("LOAN_ORIG_ORD_NUM")));
                        }
                    } else {
                        // Set a flag if the original order is not a Loan.
                        headerBean.setIsNotLoanOrigOrdFlg(true);
                    }
                    // Mod End 2018/03/30 QC#25134
                }
                // 2018/03/14 QC#20153 Add End
            }
            headerBean.setIsLeaseOrd(isLeaseOrd); // L3#384

            writeLogLn(headerBean.toString());

            hdrBeanList.add(headerBean);
        }

        return hdrBeanList;
    }

    private boolean importOrder(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, DS_IMPT_ORDTMsg dsImptOrdTMsg) {

        writeStartLogLn("importOrder", hdrBean);

        NWZC226001PMsg msg = (NWZC226001PMsg) msgMap.getPmsg();

        try {

            // 2018/01/23 QC#18798 Add Start
            if (ZYPCommonFunc.hasValue(msg.dsImptOrdConfigPk)) { // 2018/01/31 QC#23866 Mod
                paramDsImptOrdConfigPk = msg.dsImptOrdConfigPk.getValue();
                dsImptOrdConfigFlg = ZYPConstant.FLG_ON_Y;
            }
            // 2018/01/23 QC#18798 Add End

            // *****************************************************************
            // Derive EDI Header Data
            // *****************************************************************
            if (hdrBean.isEdiData()) {

                if (!deriveEdiHdrData(onBatchType, hdrBean)) {

                    return false;
                }

                // *****************************************************************
                // Update DS_IMPT_ORD
                // *****************************************************************
                if (!deriveDsImptOrdAttForEDi(hdrBean)) {

                    return false;
                }

                // Sales Credit Header Level
                if (!insertDsImptOrdSlsCr(hdrBean, null)) {

                    return false;
                }
            }

            // *****************************************************************
            // Check & Derive Order Info
            // *****************************************************************
            if (!checkDeriveDsOrdInfo(hdrBean)) {

                return false;
            }

            // *****************************************************************
            // Derive Bill/Sell/Sold Default
            // *****************************************************************
            if (!deriveDefBillSellSoldDefaultVal(onBatchType, hdrBean)) {

                return false;
            }

            // 2020/01/06 QC#54219 Del Start
            //// 2019/12/05 QC#54219 Add Start
            //// *****************************************************************
            //// Check mentenance Only
            //// *****************************************************************
            //if (checkMaintenanceOnly( hdrBean)) {
            //
            //    return false;
            //}
            //// 2019/12/05 QC#54219 Add End
            // 2020/01/06 QC#54219 Del End

            // Add Start 2018/06/25 QC#23726
            // get DS_ORD_TP_PROC_DFN
            NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result = getDsOrdTpProcDfn(hdrBean);
            // Add End 2018/06/25 QC#23726

            // *****************************************************************
            // Derive Defaulting
            // *****************************************************************
            // Mod Start 2018/06/25 QC#23726
            //if (!deriveDefaultOrdInfo(onBatchType, hdrBean)) {
            if (!deriveDefaultOrdInfo(onBatchType, hdrBean, result)) {
                // Mod End 2018/06/25 QC#23726

                return false;
            }

            // *****************************************************************
            // Exist Order
            // *****************************************************************
            if (hdrBean.isUpdateData()) {

                // Exist Data
                if (!deriveCpoInfo(hdrBean)) {

                    return false;
                }
            }

            // 2017/10/02 QC#20625 Del Start
//            if (hdrBean.hasError()) {
//
//                return false;
//            }
            // 2017/10/02 QC#20625 Del End

            // *****************************************************************
            // Derive DS_IMPT_ORD_CONFIG
            // *****************************************************************
            deriveDsImptOrdConfig(hdrBean);

            // *****************************************************************
            // Derive Line Data
            // *****************************************************************
            if (!deriveLineData(onBatchType, hdrBean)) {

                return false;
            }

            // *****************************************************************
            // Return Line Data
            // *****************************************************************
            if (!deriveReturnLineData(onBatchType, hdrBean)) {

                return false;
            }

            // *****************************************************************
            // Set Original Line Number
            // *****************************************************************
            setOrigLineNum(hdrBean);

            // Check for Loan
            if (hdrBean.isLoanConversion()) {
                // Check for Sales Conversion
                if (!loanInputCheck(msgMap, hdrBean)) {
                    return false;
                }

                // Check for Return Conversion
                if (!loanInputCheckForDowngrade(msgMap, hdrBean)) {
                    return false;
                }
            }

            if (hdrBean.isEdiData()) {

                // 2018/05/08 QC#25770 Add Start
                if (hdrBean.isEdiData() && NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(hdrBean.getDsEdiTrdPtnrRefCd())) {
                    setFrtCondCdForPound(hdrBean);
                    
                    if (!ZYPCommonFunc.hasValue(hdrBean.getCarrSvcLvlCd())) {

                        if (!deriveDefSvcLvlCdForEdi(hdrBean)) {

                            return false;
                        }
                    }

                    if (!ZYPCommonFunc.hasValue(hdrBean.getCarrAcctNum()) && FRT_COND.COLLECT.equals(hdrBean.getFrtCondCd())) {

                        if (!deriveDefCarrAcctNumForEdi(hdrBean)) {

                            return false;
                        }
                    }
                }
                // 2018/05/08 QC#25770 Add End

                // *****************************************************************
                // Insert DS_IMPT_ORD_DELY_INFO
                // *****************************************************************
                if (!insertDsImptOrdDelyInfoForEDi(onBatchType, hdrBean)) {

                    return false;
                }

                // *****************************************************************
                // Insert DS_IMPT_ORD_CTAC_PSN
                // *****************************************************************
                if (!insertDsImptOrdCtacPsnForEDi(hdrBean)) {

                    return false;
                }
            }

            // Add Start 2018/06/25 QC#23726
            // *****************************************************************
            // Carrier Service Level Code
            // *****************************************************************
            deriveCarrSvcLvl(onBatchType, hdrBean, result);

            // *****************************************************************
            // Check relationship between 'Line Of Business', 'Ship To Account' and 'Carrier Service Level'
            // *****************************************************************
            checkCarrSvcLvlRelation(hdrBean);
            // Add End 2018/06/25 QC#23726

            // *****************************************************************
            // Service Shell Data
            // *****************************************************************
            deriveServiceShellData(hdrBean);

            // *****************************************************************
            // Check Relation Shell and Schedule
            // *****************************************************************
            otherCheck(msgMap, hdrBean);

            // *****************************************************************
            // SalesCredit Data
            // *****************************************************************
            deriveSalesCreditData(hdrBean);

            // *****************************************************************
            // Delivery Data
            // *****************************************************************
            // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
//            deriveDeliveryData(hdrBean);
            deriveDeliveryData(onBatchType, hdrBean);
            // 2018/02/08 S21_NA#20297(Sol#379) Mod End

            // *****************************************************************
            // Site Survey Data
            // *****************************************************************
            deriveSiteSurveyData(hdrBean);

            // 2017/08/01 QC#20000 Add Start
            // *****************************************************************
            // Site Survey Data For RMA(Deal Config)
            // *****************************************************************
            // 2017/09/13 QC#21084 Mod Start
            if(CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd())) {
                deriveDcRmaConfigSiteSurveyData(hdrBean);
            }
            // 2017/09/13 QC#21084 Mod End
            // 2017/08/01 QC#20000 Add End

            // *****************************************************************
            // Install Data
            // *****************************************************************
            deriveInstallData(hdrBean);

            // *****************************************************************
            // Contact Person Data
            // *****************************************************************
            deriveContactPersonData(onBatchType, hdrBean);

            // *****************************************************************
            // Super session
            // *****************************************************************
            updateSupersession(onBatchType, hdrBean);

            // 2017/10/02 QC#20625 Del Start
//            if (hdrBean.hasError()) {
//                return false;
//            }
            // 2017/10/02 QC#20625 Del End

            // *****************************************************************
            // Registration Data
            // *****************************************************************
            if (!registerData(onBatchType, hdrBean)) {

                return false;
            }

            if (S21StringUtil.isEquals(msg.xxModeCd.getValue(), NWZC226001Constant.MODE_CREATE_ORDER)) {

                // *****************************************************************
                // Call Order Created Web Service For SOM
                // *****************************************************************
                if (CPO_SRC_TP.SOM.equals(hdrBean.getCpoSrcTpCd()) && !hdrBean.hasError()) {

                    if (!callOrdCreateWebSvcForSom(hdrBean)) {

                        return false;
                    }
                }

                // 2018/01/23 QC#18798 Add Start
                // *****************************************************************
                // Call Order Created Web Service For EOPS
                // *****************************************************************
                if (CPO_SRC_TP.EOPS.equals(hdrBean.getCpoSrcTpCd()) && !hdrBean.hasError()) {

                    if (!callOrdCreateWebSvcForEops(hdrBean)) {

                        return false;
                    }
                }
                // 2018/01/23 QC#18798 Add End

                // *****************************************************************
                // Attach Therefore Document
                // *****************************************************************
                try {

                    if (!hdrBean.hasError()) {

                        if (ZYPConstant.FLG_ON_Y.equals(hdrBean.getAutoDocAttFlg())) {

                            thereforeDocumentAttach(onBatchType, hdrBean);
                        }
                    }
                } catch (Throwable e) {

                    writeLogLn("Therefore Document Automatic Attachment Error.");
                    writeLogLn(e.toString());
                }
            }

            return (hdrBean.getAllErrorBean().size() == 0);
        } finally {

            writeEndLogLn("importOrder", hdrBean);
        }
    }

    private boolean deriveEdiHdrData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveEdiHdrData", hdrBean);

        try {

            // *****************************************************************
            // derive EDI Attribute
            // *****************************************************************
            if (!deriveEdiAttrb(hdrBean)) {
                return false;
            }

            // *****************************************************************
            // derive Default DS_ORD_CATG_CD
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getDsOrdCatgCd())) {

                if (!deriveDefDsOrdCatgCdForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default LINE_BIZ_TP_CD
            // *****************************************************************
            if (!deriveDefLineBizTpCdForEdi(hdrBean)) {

                return false;
            }

            // *****************************************************************
            // derive Default Sold To
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getSellToCustCd())) {

                if (!deriveDefSoldToForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default Bill To
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getBillToCustAcctCd())) {

                if (!deriveDefBillToForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default Ship To
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getShipToCustAcctCd())) {

                if (!deriveDefShipToForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default FRT_COND_CD
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getFrtCondCd())) {

                if (!deriveDefFrtCondCdForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default CARR_SVC_LVL_CD / SHPG_SVC_LVL_CD
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getShpgSvcLvlCd())) {

                if (!deriveDefSvcLvlCdForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default ADD_PMT_TERM_CASH_DISC_CD
            // *****************************************************************
            if (!ZYPCommonFunc.hasValue(hdrBean.getAddPmtTermCashDiscCd())) {

                if (!deriveDefAddPmtTermCashDiscCdForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default CARR_ACCT_NUM
            // *****************************************************************

            if (!ZYPCommonFunc.hasValue(hdrBean.getCarrAcctNum()) && FRT_COND.COLLECT.equals(hdrBean.getFrtCondCd())) {

                if (!deriveDefCarrAcctNumForEdi(hdrBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // derive Default DROP_SHIP_FLG
            // *****************************************************************
            // Mod Start 2017/10/24 QC#22014
            if (!deriveDefDropShipFlgForEdi(hdrBean)) {

                return false;
            }
            // Mod End 2017/10/24 QC#22014

            // *****************************************************************
            // derive Sales rep
            // *****************************************************************
            if (!deriveSlsRepForEdi(onBatchType, hdrBean)) {

                return false;
            }

            // *****************************************************************
            // derive Customer PO Duplication Check
            // *****************************************************************
            if (!custPoDupCheckForEdi(hdrBean)) {

                return false;
            }

            // *****************************************************************
            // derive Price List
            // *****************************************************************
            if (!derivePrcCatgCdForEdi(onBatchType, hdrBean)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("deriveEdiHdrData", hdrBean);
        }
    }

    private boolean checkDeriveDsOrdInfo(ImptHdrBean hdrBean) {

        writeStartLogLn("checkDeriveDsOrdInfo", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            // 2018/06/14 S21_NA#24294 Mod Start
            // ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            // ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
            // ssmParam.put("dsOrdCatgCd", hdrBean.getDsOrdCatgCd());
            // ssmParam.put("dsOrdTpCd", convToString(hdrBean.getDsOrdTpCd(), ""));
            // ssmParam.put("dsOrdRsnCd", convToString(hdrBean.getDsOrdRsnCd(), ""));

            // String ordCatgCtxTpCd = (String) NWZC226001Query.getInstance().queryString("getOrdCatgCtxTpCd", ssmParam);

            // if (ZYPCommonFunc.hasValue(ordCatgCtxTpCd)) {
            //     hdrBean.setOrdCatgCtxTpCd(ordCatgCtxTpCd);
            // }
            if(isOrderRetailEquipment(hdrBean)) {
                hdrBean.setOrdCatgCtxTpCd(ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
            }

            // ssmParam = new HashMap<String, Object>();
            // 2018/06/14 S21_NA#24294 Mod End
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsOrdTpCd", convToString(hdrBean.getDsOrdTpCd(), ""));

            String lineBizTpCd = (String) NWZC226001Query.getInstance().queryString("getLineBizTpCd", ssmParam);

            if (ZYPCommonFunc.hasValue(lineBizTpCd)) {

                hdrBean.setLineBizTpCd(lineBizTpCd);
            }

            return true;
        } finally {

            writeEndLogLn("checkDeriveDsOrdInfo", hdrBean);
        }

    }

    private boolean deriveEdiAttrb(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveEdiAttrb", hdrBean);

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());

            List<Map<String, Object>> editAttrbList = NWZC226001Query.getInstance().queryMapList("getEdiAttrb", ssmParam);

            if (!hasValueList(editAttrbList)) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_EXT_ATTRB");

                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            boolean isSuccess = true;
            Map<String, Object> editAttrb = editAttrbList.get(0);

            hdrBean.setSendPoAckFlg(convToString(editAttrb.get("SEND_PO_ACK_FLG")));
            hdrBean.setDsEdiTrdPtnrRefCd(convToString(editAttrb.get("DS_EDI_TRD_PTNR_REF_CD")));
            hdrBean.setPoInbdIntfcId(convToString(editAttrb.get("PO_INBD_INTFC_ID")));

            DS_IMPT_EXT_ATTRBTMsg dsImptExtAttrbTMsg = new DS_IMPT_EXT_ATTRBTMsg();

            ZYPEZDItemValueSetter.setValue(dsImptExtAttrbTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(dsImptExtAttrbTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());

            dsImptExtAttrbTMsg = (DS_IMPT_EXT_ATTRBTMsg) S21ApiTBLAccessor.findByKey(dsImptExtAttrbTMsg);
            if (!addFindErrorMsgList(dsImptExtAttrbTMsg, "DS_IMPT_EXT_ATTRB", hdrBean.getDsImptOrdPk(), hdrBean)) {
                isSuccess = false;
            }

            hdrBean.setDsImptExtAttrbTMsg(dsImptExtAttrbTMsg);

            BigDecimal dsImptOrdDtlPk;
            DS_IMPT_DTL_EXT_ATTRBTMsg dsImptDtlExtAttrbTMsg;
            for (int i = 0; i < editAttrbList.size(); i++) {

                editAttrb = editAttrbList.get(i);

                dsImptOrdDtlPk = convToBigDecimal(editAttrb.get("DS_IMPT_ORD_DTL_PK"));

                dsImptDtlExtAttrbTMsg = new DS_IMPT_DTL_EXT_ATTRBTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrbTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrbTMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);

                dsImptDtlExtAttrbTMsg = (DS_IMPT_DTL_EXT_ATTRBTMsg) S21ApiTBLAccessor.findByKey(dsImptDtlExtAttrbTMsg);
                if (!addFindErrorMsgList(dsImptDtlExtAttrbTMsg, "DS_IMPT_DTL_EXT_ATTRB", dsImptOrdDtlPk, hdrBean)) {

                    isSuccess = false;
                }

                hdrBean.getDsImptDtlExtAttrbTMsgList().add(dsImptDtlExtAttrbTMsg);
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveEdiAttrb", hdrBean);
        }
    }

    private boolean deriveDefDsOrdCatgCdForEdi(ImptHdrBean hdrBean) {
        writeStartLogLn("deriveDefDsOrdCatgCdForEdi", hdrBean);

        try {

            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());

            if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, attrbTMsg.idocPoOrgValTxt_01);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, attrbTMsg.idocPoOrdRsnCd);
            } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.NCR_ORDER_TYPE_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, hdrBean.getDsImptDtlExtAttrbTMsgList().get(0).idocPoDtlDelyPrtyNm);
            }

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            hdrBean.setDsOrdCatgCd(resultTMsg.trgtAttrbTxt_02.getValue());
            hdrBean.setDsOrdTpCd(resultTMsg.trgtAttrbTxt_03.getValue());
            hdrBean.setDsOrdRsnCd(resultTMsg.trgtAttrbTxt_04.getValue());

            if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {

                hdrBean.setFrtCondCd(resultTMsg.trgtAttrbTxt_05.getValue());
                hdrBean.setShpgSvcLvlCd(resultTMsg.trgtAttrbTxt_06.getValue());
                hdrBean.setCarrSvcLvlCd(resultTMsg.trgtAttrbTxt_07.getValue());
            }

            return true;
        } finally {

            writeEndLogLn("deriveDefDsOrdCatgCdForEdi", hdrBean);
        }
    }

    private boolean deriveDefLineBizTpCdForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefLineBizTpCdForEdi", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsOrdTpCd", convToString(hdrBean.getDsOrdTpCd(), ""));
            String lineBizTpCd = (String) NWZC226001Query.getInstance().queryString("getLineBizTpCd", ssmParam);

            if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_ORD_TP_PROC_DFN");
                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            hdrBean.setLineBizTpCd(lineBizTpCd);

            return true;
        } finally {

            writeEndLogLn("deriveDefLineBizTpCdForEdi", hdrBean);
        }
    }

    private boolean deriveDefSoldToForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefSoldToForEdi", hdrBean);

        try {
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();
            String srcAttrb3 = "";
            String srcAttrb4 = "";

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SOLD_TO_ATTRB2);

            if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
                srcAttrb4 = attrbTMsg.idocPoPtnrNum_01.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_IHREZ;
                srcAttrb4 = attrbTMsg.idocPtnrCustRefTxt_01.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
                srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
                srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
            }

            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Sold To");

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            hdrBean.setSellToCustCd(resultTMsg.trgtAttrbTxt_01.getValue());
            hdrBean.setSoldToCustLocCd(resultTMsg.trgtAttrbTxt_02.getValue());

            return true;
        } finally {

            writeEndLogLn("deriveDefSoldToForEdi", hdrBean);
        }
    }

    private boolean deriveDefBillToForEdi(ImptHdrBean hdrBean) {
        writeStartLogLn("deriveDefBillToForEdi", hdrBean);

        try {
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();
            // Mod Start 2017/10/24 QC#22010
            String srcAttrb3 = "";
            String srcAttrb4 = "";

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_BILL_TO_ATTRB2);

            if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
                srcAttrb4 = attrbTMsg.idocPoPtnrNum_02.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_IHREZ;
                srcAttrb4 = attrbTMsg.idocPtnrCustRefTxt_01.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
                srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
                srcAttrb4 = attrbTMsg.idocPoPtnrId_02.getValue();
            }

            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);
            // Mod End 2017/10/24 QC#22010

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Bill To");

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            hdrBean.setBillToCustAcctCd(resultTMsg.trgtAttrbTxt_03.getValue());
            hdrBean.setBillToCustCd(resultTMsg.trgtAttrbTxt_04.getValue());

            return true;
        } finally {

            writeEndLogLn("deriveDefBillToForEdi", hdrBean);
        }
    }

    private boolean deriveDefShipToForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefShipToForEdi", hdrBean);

        try {

            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();
            String srcAttrb3 = "";
            String srcAttrb4 = "";

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SHIP_TO_ATTRB2);

            if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
                srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
                srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
                srcAttrb4 = attrbTMsg.idocPoPtnrId_03.getValue();
            } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {

                srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
                srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
            }

            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Ship To");

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            hdrBean.setShipToCustAcctCd(resultTMsg.trgtAttrbTxt_05.getValue());
            hdrBean.setShipToCustCd(resultTMsg.trgtAttrbTxt_06.getValue());

            return true;
        } finally {

            writeEndLogLn("deriveDefShipToForEdi", hdrBean);
        }
    }

    private boolean deriveDefFrtCondCdForEdi(ImptHdrBean hdrBean) {
        writeStartLogLn("deriveDefFrtCondCdForEdi", hdrBean);

        try {
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.FREIGHT_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_FRT_COND_CD_ATTRB2);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_FRT_COND_CD_ATTRB3);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoDelyCondCd);

            // Mod Start 2017/10/24 QC#22011
            NWXC220001Result<String> result = NWXC220001ImportAttribute.deriveDefFrtCondCd(inTMsg, hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), hdrBean.getDsOrdTpCd());

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            hdrBean.setFrtCondCd(result.getResultObject());
            // Mod End 2017/10/24 QC#22011

            return true;
        } finally {

            writeEndLogLn("deriveDefFrtCondCdForEdi", hdrBean);
        }
    }

    private boolean deriveDefSvcLvlCdForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefSvcLvlCdForEdi", hdrBean);

        try {

            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();

            if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SVC_LVL_CD_ATTRB2);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
            } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {

                return true;
            }

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefSvcLvlCd(inTMsg);

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            hdrBean.setShpgSvcLvlCd(resultTMsg.trgtAttrbTxt_01.getValue());
            // Del Start 2018/06/25 QC#23726
            //if (FRT_COND.COLLECT.equals(hdrBean.getFrtCondCd())) {
                // Del End 2018/06/25 QC#23726

                hdrBean.setCarrSvcLvlCd(resultTMsg.trgtAttrbTxt_02.getValue());
                // Del Start 2018/06/25 QC#23726
            //} else {

            //    hdrBean.setCarrSvcLvlCd(BLANK);
            //}
            // Del End 2018/06/25 QC#23726

            return true;
        } finally {

            writeEndLogLn("deriveDefSvcLvlCdForEdi", hdrBean);
        }
    }

    private boolean deriveDefAddPmtTermCashDiscCdForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefAddPmtTermCashDiscCdForEdi", hdrBean);

        try {

            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();

            if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SVC_LVL_CD_ATTRB2);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
            } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {

                return true;
            }

            NWXC220001Result<String> result = NWXC220001ImportAttribute.deriveDefAddPmtTermCashDiscCd(hdrBean.getGlblCmpyCd(), hdrBean.getBillToCustCd(), hdrBean.getBillToCustAcctCd());
            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            String addPmtTermCashDiscCd = result.getResultObject();

            if (ZYPCommonFunc.hasValue(addPmtTermCashDiscCd)) {

                hdrBean.setAddPmtTermCashDiscCd(result.getResultObject());
            }

            return true;
        } finally {

            writeEndLogLn("deriveDefAddPmtTermCashDiscCdForEdi", hdrBean);
        }
    }

    private boolean deriveDefCarrAcctNumForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefCarrAcctNumForEdi", hdrBean);

        try {
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();

            if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.NCR_CARRIER_ACCOUNT_NUMBER_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CARR_ACCT_NUM_ATTRB2);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
            }

            NWXC220001Result<String> result = NWXC220001ImportAttribute.deriveDefCarrAcctNum(dsEdiTrdPtnrRefCd, inTMsg, attrbTMsg.idocPoNoteTxt.getValue(), hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), hdrBean.getShipToCustAcctCd(), hdrBean
            // 2018/12/14 S21_NA#29315 Mod Start
            //      .getOnbtchType());
                    .getOnbtchType(), getShipToLocNum(hdrBean), hdrBean.getLineBizTpCd(), NWXC150001DsCheck.getDsBizArea(hdrBean.getGlblCmpyCd(), hdrBean.getDsOrdCatgCd(), hdrBean.getDsOrdTpCd(), hdrBean.getDsOrdRsnCd()), hdrBean.getFrtCondCd(), hdrBean.getShpgSvcLvlCd());
            // 2018/12/14 S21_NA#29315 Mod End
            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            String carrAcctNum = result.getResultObject();

            if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                hdrBean.setCarrAcctNum(carrAcctNum);
            }

            return true;
        } finally {
            writeEndLogLn("deriveDefCarrAcctNumForEdi", hdrBean);
        }
    }

    private boolean deriveDefDropShipFlgForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefDropShipFlgForEdi", hdrBean);

        try {
            
            // 2018/11/29 S21_NA#28899 Add Start // abort mod 2018/12/18
//            Map<String, Object> custInfo = getShipInfo(hdrBean.getGlblCmpyCd(), hdrBean.getShipToCustCd());
//            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
//            if (custInfo != null) {
//
//                String s21LocNm = convFormat(convToString(custInfo.get("LOC_NM")));
//                String s21FirstLineAddr = convFormat(convToString(custInfo.get("FIRST_LINE_ADDR")));
//                String s21ScdLineAddr = convFormat(convToString(custInfo.get("SCD_LINE_ADDR")));
//                String s21CtyAddr = convFormat(convToString(custInfo.get("CTY_ADDR")));
//                String s21StCd = convFormat(convToString(custInfo.get("ST_CD")));
//                String s21PostCd = convFormat(convToString(custInfo.get("POST_CD")));
//                String s21CntyCd = convFormat(convToString(custInfo.get("CNTY_NM")));
//                
//                String ediLocNm = convFormat(attrbTMsg.idocPtnrCtacNm_03.getValue());
//                String ediFirstLineAddr = convFormat(attrbTMsg.idocFirstLineAddr.getValue());
//                String ediScdLineAddr = convFormat(attrbTMsg.idocScdLineAddr.getValue());
//                String ediCtyAddr = convFormat(attrbTMsg.idocPtnrCtyNm.getValue());
//                String ediStCd = convFormat(attrbTMsg.idocPtnrStCd.getValue());
//                String ediPostCd = convFormat(attrbTMsg.idocPtnrPostCd.getValue());
//                String ediCntyCd = convFormat(attrbTMsg.idocPtnrCntyNm.getValue());
//                
//                if(S21StringUtil.isEquals(s21LocNm, ediLocNm) // 
//                        && S21StringUtil.isEquals(s21FirstLineAddr, ediFirstLineAddr) //
//                        && S21StringUtil.isEquals(s21ScdLineAddr, ediScdLineAddr) //
//                        && S21StringUtil.isEquals(s21CtyAddr, ediCtyAddr) //
//                        && S21StringUtil.isEquals(s21StCd, ediStCd) //
//                        && comparePostCd(s21PostCd, ediPostCd) //
//                        && compareCntyNm(s21CntyCd, ediCntyCd) //
//                ) {
//                    hdrBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
//                } else {
//                    hdrBean.setDropShipFlg(ZYPConstant.FLG_ON_Y);
//                }
//            } else {
//                return false;
//            }
            // 2018/11/29 S21_NA#28899 Add End
            
            // 2018/11/29 S21_NA#28899 Del Start
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();
            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();

            if (NWXC220001ImportAttribute.isContentsInNcrMgJpmc(dsEdiTrdPtnrRefCd)) {
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
                ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.DROP_SHIP_MAPPING);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            } else if (NWXC220001ImportAttribute.isContentsInArc(dsEdiTrdPtnrRefCd)) {

                if (NWXC220001Constant.EDI_DROP_SHIP_FLG_ARC_TXT.equalsIgnoreCase(attrbTMsg.idocPoOrgValTxt_01.getValue())) {
                    hdrBean.setDropShipFlg(ZYPConstant.FLG_ON_Y);
                } else {
                    hdrBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
                }

                return true;
            }

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

            if (result.hasError()) {
                addErrorMsgListFromImptAttrb(result, hdrBean);
                return false;
            }

            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
            if (resultTMsg != null && ZYPCommonFunc.hasValue(resultTMsg.trgtAttrbTxt_01)) {
                hdrBean.setDropShipFlg(resultTMsg.trgtAttrbTxt_01.getValue());
            }
            // 2018/11/29 S21_NA#28899 Del End

            return true;
        } finally {

            writeEndLogLn("deriveDefDropShipFlgForEdi", hdrBean);
        }
    }
    
    // 2018/11/29 S21_NA#28899 Add Start
    
    /**
     * change UpperCasee
     */
    private String toUpper(String str){
        if(!ZYPCommonFunc.hasValue(str)){
            return "";
        }
        return str.toUpperCase();
    }
    /**
     * convert string format
     * @param str
     * @return
     */
    private String convFormat(String str){
        if(!ZYPCommonFunc.hasValue(str)){
            return "";
        }
        return str.replaceAll(" ", "").toUpperCase();
    }
    
    /**
     * compare post code
     * @param s21PostCd
     * @param ediPostCd
     * @return
     */
    private static boolean comparePostCd(String s21PostCd, String ediPostCd) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher s21Mat = pattern.matcher(s21PostCd);
        Matcher ediMat = pattern.matcher(ediPostCd);
        s21PostCd = s21Mat.replaceAll("");
        ediPostCd = ediMat.replaceAll("");
        int s21PostCdLength = s21PostCd.length();
        int ediPostCdLength = ediPostCd.length();
        if(!((s21PostCdLength == 5 || s21PostCdLength == 9) // 
                && (ediPostCdLength == 5 || ediPostCdLength == 9))){
            return false;
        }
        if (s21PostCdLength == ediPostCdLength) {
            return S21StringUtil.isEquals(s21PostCd, ediPostCd);
        } else if (s21PostCdLength < ediPostCdLength) {
            ediPostCd = ediPostCd.substring(0, s21PostCdLength);
            return S21StringUtil.isEquals(s21PostCd, ediPostCd);
        } else if (s21PostCdLength > ediPostCdLength) {
            s21PostCd = s21PostCd.substring(0, ediPostCdLength);
            return S21StringUtil.isEquals(s21PostCd, ediPostCd);
        }
        return true;
    }
    
    private static boolean compareCntyNm(String s21CntyNm, String ediCntyNm) {
        if (!ZYPCommonFunc.hasValue(ediCntyNm)) {
            return true;
        }
        return S21StringUtil.isEquals(s21CntyNm, ediCntyNm);
    }
    // 2018/11/29 S21_NA#28899 Add End

    private boolean custPoDupCheckForEdi(ImptHdrBean hdrBean) {

        writeStartLogLn("custPoDupCheckForEdi", hdrBean);

        try {
            // 2018/07/10 S21_NA#26936 Mod Start
//            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();
//
//            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
//
//            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
//            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
//            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_PO_DUPLICATION_CHECK);
//            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
//
//            // 2018/07/02 S21_NA#26908 Mod Start
////            NWXC220001Result<String> result = NWXC220001ImportAttribute.checkDuplicatePo(inTMsg, hdrBean.getOrigOrdNum(), hdrBean.getDsImptExtAttrbTMsg().idocPoCustRefNum.getValue(), ORD_HDR_STS.SAVED);
//            NWXC220001Result<String> result = NWXC220001ImportAttribute.checkDuplicatePo(inTMsg, hdrBean.getOrigOrdNum(), hdrBean.getDsImptExtAttrbTMsg().idocPoDocNum.getValue(), ORD_HDR_STS.SAVED);
//            // 2018/07/02 S21_NA#26908 Mod End
//
//            if (result.hasError()) {
//
//                hdrBean.setCustIssPoNum(null);
//            } else {
//
//                // 2018/07/02 S21_NA#26908 Mod Start
////                hdrBean.setCustIssPoNum(hdrBean.getDsImptExtAttrbTMsg().idocPoCustRefNum.getValue());
//                hdrBean.setCustIssPoNum(hdrBean.getDsImptExtAttrbTMsg().idocPoDocNum.getValue());
//                // 2018/07/02 S21_NA#26908 Mod End
//            }
            hdrBean.setCustIssPoNum(hdrBean.getDsImptExtAttrbTMsg().idocPoDocNum.getValue());
            // 2018/07/10 S21_NA#26936 Mod End

            return true;
        } finally {

            writeEndLogLn("custPoDupCheckForEdi", hdrBean);
        }
    }

    private boolean derivePrcCatgCdForEdi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("derivePrcCatgCdForEdi", hdrBean);

        try {
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, hdrBean.getLineBizTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, hdrBean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
            // Add Start 2017/06/13 QC#18984
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, hdrBean.getDsPmtMethCd());
            // Add End 2017/06/13 QC#18984

            new NWZC157001().execute(prcApiPMsg, onBatchType);

            if (addPrcErrorMsgList(prcApiPMsg, hdrBean, hdrBean).size() > 0) {

                return false;
            }

            if (prcApiPMsg.xxPrcList.getValidCount() > 0) {

                hdrBean.setPrcCatgCd(prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue());
                return true;
            }
            if (ZYPCommonFunc.hasValue(prcApiPMsg.defPrcCatgCd)) {

                hdrBean.setPrcCatgCd(prcApiPMsg.defPrcCatgCd.getValue());
                return true;
            }

            return false;
        } finally {

            writeEndLogLn("derivePrcCatgCdForEdi", hdrBean);
        }
    }

    private boolean deriveSlsRepForEdi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveSlsRepForEdi", hdrBean);

        try {

            if (!ZYPCommonFunc.hasValue(hdrBean.getSlsRepRoleTpCd())) {

                return setDefSlsReqInHdrBean(onBatchType, hdrBean);
            }

            return true;
        } finally {

            writeEndLogLn("deriveSlsRepForEdi", hdrBean);
        }
    }

    /**
     * Bill/Sell/Sold Default Deriving
     * @param hdrBean
     * @return boolean
     */
    private boolean deriveDefBillSellSoldDefaultVal(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDefBillSellSoldDefaultVal", hdrBean);

        try {
            Map<String, Object> ssmParam;

            // Ship to Location
            if (!ZYPCommonFunc.hasValue(hdrBean.getShipToCustCd())) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMandatoryError(hdrBean, "'Ship To Customer Code'");

                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            // Ship to Account
            if (!ZYPCommonFunc.hasValue(hdrBean.getShipToCustAcctCd())) {

                ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("shipToCustCd", hdrBean.getShipToCustCd());
                ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

                List<Map<String, Object>> shipToActList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getShipToAccount", ssmParam);

                if (shipToActList.size() != 1) {

                    DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(SHIP_TO_CUSTTMsg.class, hdrBean, hdrBean.getShipToCustCd());

                    hdrBean.dsImptOrdErrList.add(errBean);
                    return false;
                }

                Map<String, Object> shipToAct = (Map<String, Object>) shipToActList.get(0);
                hdrBean.setShipToCustAcctCd(convToString(shipToAct.get("SELL_TO_CUST_CD")));
            }

            // Bill to Location
            if (!getBillOrSoldToLoc(onBatchType, hdrBean, true)) {

                return false;
            }

            // Bill to Account
            if (!ZYPCommonFunc.hasValue(hdrBean.getBillToCustAcctCd())) {

                if (ZYPCommonFunc.hasValue(hdrBean.getBillToCustCd())) {

                    ssmParam = new HashMap<String, Object>();

                    ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                    ssmParam.put("billOrSldToCustCd", hdrBean.getBillToCustCd());
                    ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

                    List<Map<String, Object>> billToActList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getBillOrSoldToAccount", ssmParam);

                    if (billToActList.size() != 1) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(BILL_TO_CUSTTMsg.class, hdrBean, hdrBean.getBillToCustCd());

                        hdrBean.dsImptOrdErrList.add(errBean);
                        return false;
                    }

                    Map<String, Object> billToAct = billToActList.get(0);
                    hdrBean.setBillToCustAcctCd(convToString(billToAct.get("SELL_TO_CUST_CD")));
                }
            }

            // Sold to Location
            if (!getBillOrSoldToLoc(onBatchType, hdrBean, false)) {

                hdrBean.setSoldToCustLocCd(hdrBean.getBillToCustCd());
            }

            // Sold to Account
            if (!ZYPCommonFunc.hasValue(hdrBean.getSellToCustCd())) {

                ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("billOrSldToCustCd", hdrBean.getSoldToCustLocCd());
                ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

                List<Map<String, Object>> soldToActList = NWZC226001Query.getInstance().queryMapList("getBillOrSoldToAccount", ssmParam);

                if (soldToActList.size() != 1) {

                    DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(SELL_TO_CUSTTMsg.class, hdrBean, hdrBean.getSoldToCustLocCd());

                    hdrBean.dsImptOrdErrList.add(errBean);
                    return false;
                }

                Map<String, Object> soldToAct = soldToActList.get(0);
                hdrBean.setSellToCustCd((String) soldToAct.get("SELL_TO_CUST_CD"));
            }

            return true;
        } finally {

            writeEndLogLn("deriveDefBillSellSoldDefaultVal", hdrBean);
        }
    }

    /**
     * getBillOrSoldToLoc
     * @param hdrBean ImptHdrBean
     * @param isBill boolean
     * @return boolean
     */
    private boolean getBillOrSoldToLoc(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, boolean isBill) {

        writeStartLogLn("getBillOrSoldToLoc", hdrBean);

        try {
            String checkCd, searchCd;

            if (isBill) {

                checkCd = hdrBean.getBillToCustCd();
                searchCd = hdrBean.getShipToCustAcctCd();
            } else {

                checkCd = hdrBean.getSoldToCustLocCd();
                searchCd = hdrBean.getBillToCustAcctCd();
            }

            if (!ZYPCommonFunc.hasValue(checkCd)) {

                String mode;
                String shipToCd;
                String billToCd;
                if (isBill) {

                    mode = NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO;
                    shipToCd = hdrBean.getShipToCustCd();
                    billToCd = null;
                } else {

                    mode = NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT;
                    shipToCd = null;
                    billToCd = hdrBean.getBillToCustCd();
                }
                NMZC610001PMsg pMsg = callCustInfoGetApiForDefaultMode(onBatchType, hdrBean, searchCd, shipToCd, billToCd, mode);

                if (hasValidValue(pMsg.DefaultBillShipList)) {

                    if (isBill) {

                        hdrBean.setBillToCustCd(pMsg.DefaultBillShipList.no(0).billToCustCd.getValue());
                    } else {

                        hdrBean.setSoldToCustLocCd(pMsg.DefaultBillShipList.no(0).shipToCustCd.getValue());
                    }
                } else {

                    return false;
                }
            }

            return true;
        } finally {

            writeEndLogLn("getBillOrSoldToLoc", hdrBean);
        }

    }

    /**
     * <pre>
     * Call Customer Information Get API For Default Mode
     * </pre>
     * @param hdrBean ImptHdrBean
     * @param dsAcctNum Direct Sales Account Number
     * @param shipToCustCd Ship to Customer Code (if you set bill to,
     * this parameter should be null)
     * @param billToCustCd Bill To Customer Code (if you set ship to,
     * this parameter should be null)
     * @param xxMode API calling mode
     * @return NMZC610001PMsg Default Customer API return value
     */
    private NMZC610001PMsg callCustInfoGetApiForDefaultMode(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode) {

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(hdrBean.getGlblCmpyCd());
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(hdrBean));
        paramBean.setDsAcctNum(dsAcctNum);
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {

            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {

            paramBean.setBillToCustCd(billToCustCd);
        }
        paramBean.setXxMode(xxMode);
        paramBean.setOnBatchType(onBatchType);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        addErrorMsgList(pMsg, hdrBean);

        return pMsg;
    }

    /**
     * getDsTrxRuleTpCd
     * @param hdrBean ImptHdrBean
     * @return String
     */
    private String getDsTrxRuleTpCd(ImptHdrBean hdrBean) {
        writeStartLogLn("getDsTrxRuleTpCd", hdrBean);

        try {
            ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
            condition.setSQLID("003");
            condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
            condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
            condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
            condition.setConditionValue("dsOrdCatgCd01", hdrBean.getDsOrdCatgCd());
            condition.setConditionValue("dsOrdTpCd01", hdrBean.getDsOrdTpCd());

            ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
            if (hasValidValue(tmsgArray)) {

                return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
            }

            condition = new ORD_CATG_BIZ_CTXTMsg();
            condition.setSQLID("002");
            condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
            condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
            condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
            condition.setConditionValue("dsOrdCatgCd01", hdrBean.getDsOrdCatgCd());

            tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
            if (hasValidValue(tmsgArray)) {

                return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
            }

            return BLANK;
        } finally {

            writeEndLogLn("getDsTrxRuleTpCd", hdrBean);
        }

    }

    /**
     * callCustomerInfoApi
     * @param hdrBean ImptHdrBean
     * @param modeCd String
     * @param dsAcctNum String
     * @return NMZC610001PMsg
     */
    private NMZC610001PMsg callCustomerInfoApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, String modeCd, String... param) {

        writeStartLogLn("callCustomerInfoApi", hdrBean);

        try {

            NMZC610001PMsg pMsg = new NMZC610001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, modeCd);
            if (PROCESS_DEFAULT_TRANSACTION.equals(modeCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, getDsTrxRuleTpCd(hdrBean));
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, param[0]);
                // 2019/01/30 S21_NA#30036 Add Start
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, param[1]);
                // 2019/01/30 S21_NA#30036 Add End
            } else if (PROCESS_DEFAULT_BILL_SHIP.equals(modeCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, param[0]);
            } else if (PROCESS_INSTRUCTION.equals(modeCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, LINE_BIZ_TP.LFS);
                ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, DS_BIZ_AREA.SUPPLIES);
                ZYPEZDItemValueSetter.setValue(pMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SPCL_HANDLING);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, param[0]);
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, param[1]);
            // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
//            }
            } else if (PROCESS_SHIPPING_DEFAULT_INFORMATION.equals(modeCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, param[0]);
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, param[1]);
                ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, param[2]);
                ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, param[3]);
            }
            // 2018/02/08 S21_NA#20297(Sol#379) Mod End

            // call NMZC6100 Customer Information Get API
            new NMZC610001().execute(pMsg, onBatchType);

            addErrorMsgList(pMsg, hdrBean);

            return pMsg;
        } finally {

            writeEndLogLn("callCustomerInfoApi", hdrBean);
        }
    }

    // Mod Start 2018/06/25 QC#23726
    ///**
    // * deriveDefaultOrdInfo
    // * @param hdrBean ImptHdrBean
    // * @return boolean
    // */
    //private boolean deriveDefaultOrdInfo(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
    /**
     * deriveDefaultOrdInfo
     * @param onBatchType ONBATCH_TYPE
     * @param hdrBean ImptHdrBean
     * @param result NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg>
     * @return boolean
     */
    private boolean deriveDefaultOrdInfo(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result) {
        // Mod End 2018/06/25 QC#23726
        writeStartLogLn("deriveDefaultOrdInfo", hdrBean);

        try {

            // Del Start 2018/06/25 QC#23726
            //DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            //
            //ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            //ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            //if (ZYPCommonFunc.hasValue(hdrBean.getOrdSrcImptTs())) {
            //
            //    String chkDate = hdrBean.getOrdSrcImptTs_Len8();
            //    ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, chkDate);
            //    ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, chkDate);
            //}
            //
            //NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result = NWXC220001.getDefDsOrdTpProcDfn(tMsg);
            // Del End 2018/06/25 QC#23726

            if (result.hasResultObject()) {

                DS_ORD_TP_PROC_DFNTMsg resultTMsg = result.getResultObject();

                // Price List Category
                if (!ZYPCommonFunc.hasValue(hdrBean.getPrcCatgCd())) {

                    hdrBean.setPrcCatgCd(convToString(resultTMsg.defPrcCatgCd.getValue()));
                }
                if (!ZYPCommonFunc.hasValue(hdrBean.getFlPrcListCd())) {

                    hdrBean.setFlPrcListCd(convToString(resultTMsg.defPrcCatgCd.getValue()));
                }

                // 2018/02/23 S21_NA#20297(Sol#379) Del Start
//                // Freight Term
//                if (!ZYPCommonFunc.hasValue(hdrBean.getFrtCondCd())) {
//
//                    hdrBean.setFrtCondCd(convToString(resultTMsg.frtCondCd.getValue()));
//                }
//
//                // Shipping Service Level
//                if (!ZYPCommonFunc.hasValue(hdrBean.getShpgSvcLvlCd())) {
//
//                    hdrBean.setShpgSvcLvlCd(convToString(resultTMsg.defShpgSvcLvlCd.getValue()));
//                }
                // 2018/02/23 S21_NA#20297(Sol#379) Del End

                // Del Start 2018/06/25 QC#23726
                //// Carrier Service Level
                //if (!ZYPCommonFunc.hasValue(hdrBean.getCarrSvcLvlCd())) {
                //
                //    String defCarrSvcLvlCd = convToString(resultTMsg.defCarrSvcLvlCd.getValue());
                //
                //    if (!ZYPCommonFunc.hasValue(defCarrSvcLvlCd)) {
                //
                //        // Carrier Service Level 2nd Times
                //        defCarrSvcLvlCd = deriveDefCarrSvcLvl(onBatchType, hdrBean);
                //    }
                //
                //    hdrBean.setCarrSvcLvlCd(defCarrSvcLvlCd);
                //}
                // Del End 2018/06/25 QC#23726
            }

            if (!ZYPCommonFunc.hasValue(hdrBean.getCustIssPoNum())) {

                // call NMZC6100 Customer Information Get API
                // Customer PO#
                // 2019/01/30 S21_NA#30036 Mod Start
                //NMZC610001PMsg apiNMZC6100pMsg = callCustomerInfoApi(onBatchType, hdrBean, PROCESS_DEFAULT_TRANSACTION, hdrBean.getSellToCustCd());
                NMZC610001PMsg apiNMZC6100pMsg = callCustomerInfoApi(onBatchType, hdrBean, PROCESS_DEFAULT_TRANSACTION, hdrBean.getSellToCustCd(), hdrBean.getSoldToCustLocCd());
                // 2019/01/30 S21_NA#30036 Mod End
                // 2017/10/02 QC#20625 Del Start
//                if (hdrBean.hasError()) {
//
//                    return false;
//                }
                // 2017/10/02 QC#20625 Del End

                if (hasValidValue(apiNMZC6100pMsg.TransactionRuleList)) {

                    hdrBean.setCustIssPoNum(apiNMZC6100pMsg.TransactionRuleList.no(0).dsBlktPoNum.getValue());
                }
            }

            // call NMZC260001 Default Sales Req API
            if (!ZYPCommonFunc.hasValue(hdrBean.getSlsRepTocCd())) {

                if (!setDefSlsReqInHdrBean(onBatchType, hdrBean)) {

                    return false;
                }
            }

            // 2018/02/08 S21_NA#20297(Sol#379) Add Start
            NMZC610001_ShippingDefaultInfoListPMsg shpgDefPMsg = null;

            NMZC610001PMsg apiNMZC6100ShpgDefPMsg = callCustomerInfoApi(
                    onBatchType,
                    hdrBean,
                    PROCESS_SHIPPING_DEFAULT_INFORMATION,
                    hdrBean.getShipToCustCd(),
                    hdrBean.getShipToCustAcctCd(),
                    hdrBean.getDsOrdCatgCd(),
                    hdrBean.getDsOrdTpCd());

            if (hasValidValue(apiNMZC6100ShpgDefPMsg.ShippingDefaultInfoList)) {

                shpgDefPMsg = apiNMZC6100ShpgDefPMsg.ShippingDefaultInfoList.no(0);
            }

            // Freight Term
            if (!ZYPCommonFunc.hasValue(hdrBean.getFrtCondCd())) {

                if (ZYPCommonFunc.hasValue(shpgDefPMsg.frtCondCd)) {

                    hdrBean.setFrtCondCd(convToString(shpgDefPMsg.frtCondCd.getValue()));

                } else if (result.hasResultObject()) {

                    hdrBean.setFrtCondCd(convToString(result.getResultObject().frtCondCd.getValue()));
                }
            }

            // Shipping Service Level
            if (!ZYPCommonFunc.hasValue(hdrBean.getShpgSvcLvlCd())) {

                if (ZYPCommonFunc.hasValue(shpgDefPMsg.shpgSvcLvlCd)) {

                    hdrBean.setShpgSvcLvlCd(convToString(shpgDefPMsg.shpgSvcLvlCd.getValue()));

                } else if (result.hasResultObject()) {

                    hdrBean.setShpgSvcLvlCd(convToString(result.getResultObject().defShpgSvcLvlCd.getValue()));
                }
            }
            // 2018/02/08 S21_NA#20297(Sol#379) Add End

            return true;
        } finally {

            writeEndLogLn("deriveDefaultOrdInfo", hdrBean);
        }

    }

    private boolean setDefSlsReqInHdrBean(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("setDefSlsReqInHdrBean", hdrBean);

        try {

            if (!ZYPCommonFunc.hasValue(hdrBean.getSlsRepRoleTpCd())) {

                NMZC260001PMsg apiNMZC260001PMsg = callDefSlsReqApi(onBatchType, hdrBean);

                // 2017/10/02 QC#20625 Del Start
//                if (hdrBean.hasError()) {
//
//                    return false;
//                }
                // 2017/10/02 QC#20625 Del End

                if (hasValidValue(apiNMZC260001PMsg.defSlsRepList)) {

                    // 2017/03/14 S21_NA#16855 Add Start
                    // 2017/12/14 S21_NA#19804(Sol349) Mod Start
                    String[] trtyGrpTpList = getTrtyGrpTpCdFromDsOrdTpCd(hdrBean);
                    // 2017/12/14 S21_NA#19804(Sol349) Mod End

                    LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
                    lineBizRoleTpTMsg.setSQLID("001");
                    lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
                    lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
                    LINE_BIZ_ROLE_TPTMsgArray tMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) S21ApiTBLAccessor.findByCondition(lineBizRoleTpTMsg);
                    List<String> targetWriterList = new ArrayList<String>();
                    if (tMsgArray != null && tMsgArray.length() > 0) {
                        for (int i = 0; i < tMsgArray.length(); i++) {
                            LINE_BIZ_ROLE_TPTMsg tMsg = tMsgArray.no(i);
                            targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
                        }
                    }
                    // 2017/03/14 S21_NA#16855 Add End

                    // 2017/12/14 S21_NA#19804(Sol349) Add Start
                    Boolean isMatch = false;

                    NMZC260001_defSlsRepListPMsg slsRepPMsg;

                    if (trtyGrpTpList != null){
                        for (int i = 0; i < apiNMZC260001PMsg.defSlsRepList.getValidCount(); i++) {
                            slsRepPMsg = apiNMZC260001PMsg.defSlsRepList.no(i);

                            for (String trtyGrpTpCd : trtyGrpTpList){
                                if (trtyGrpTpCd.equals(slsRepPMsg.trtyGrpTpCd.getValue())){
                                    if (targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                                        hdrBean.setSlsRepTocCd(slsRepPMsg.tocCd.getValue());
                                        hdrBean.setSlsRepRoleTpCd(slsRepPMsg.lineBizRoleTpCd.getValue());
                                        isMatch = true;
                                        break;
                                    }
                                }
                            }
                            if (isMatch){
                                break;
                            }
                        }
                    }

                    if (isMatch == false){
                        for (int i = 0; i < apiNMZC260001PMsg.defSlsRepList.getValidCount(); i++) {
                            slsRepPMsg = apiNMZC260001PMsg.defSlsRepList.no(i);
                            if (slsRepPMsg.lineBizTpCd.getValue().equals(hdrBean.getLineBizTpCd())){
                                if (targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                                    hdrBean.setSlsRepTocCd(slsRepPMsg.tocCd.getValue());
                                    hdrBean.setSlsRepRoleTpCd(slsRepPMsg.lineBizRoleTpCd.getValue());
                                    break;
                                }
                            }
                        }
                    }
                    // 2017/12/14 S21_NA#19804(Sol349) Add End

                    // 2017/12/14 S21_NA#19804(Sol349) Del Start
                    //NMZC260001_defSlsRepListPMsg slsRepPMsg;
                    //for (int i = 0; i < apiNMZC260001PMsg.defSlsRepList.getValidCount(); i++) {
                    //    Boolean isMatch = false;

                    //    slsRepPMsg = apiNMZC260001PMsg.defSlsRepList.no(i);

                        // 2017/12/14 S21_NA#19804(Sol349) Add Start
                    //    if (slsRepPMsg.lineBizTpCd.getValue().equals(hdrBean.getLineBizTpCd()) //
                    //            && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
                    //            || trtyGrpTpCd.equals(slsRepPMsg.trtyGrpTpCd.getValue()))) {
                    //        if (targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                    //            hdrBean.setSlsRepTocCd(slsRepPMsg.tocCd.getValue());
                    //            hdrBean.setSlsRepRoleTpCd(slsRepPMsg.lineBizRoleTpCd.getValue());
                    //            break;
                    //        }
                    //    }
                        // 2017/12/14 S21_NA#19804(Sol349) Add End

                        // 2017/03/14 S21_NA16855 Mod Start
//                        if (slsRepPMsg.lineBizTpCd.getValue().equals(hdrBean.getLineBizTpCd())) {
                        //if (slsRepPMsg.lineBizTpCd.getValue().equals(hdrBean.getLineBizTpCd()) //
                        //        && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
                        //        || trtyGrpTpCd.equals(slsRepPMsg.trtyGrpTpCd.getValue()))) {
                        // 2017/03/14 S21_NA16855 Mod End

                            // 2017/03/14 S21_NA16855 Mod Start
                            // if (WRITTER_LIST.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                        //    if (targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                            // 2017/03/14 S21_NA16855 Mod End
                        //        hdrBean.setSlsRepTocCd(slsRepPMsg.tocCd.getValue());
                        //        hdrBean.setSlsRepRoleTpCd(slsRepPMsg.lineBizRoleTpCd.getValue());
                        //        break;
                        //    }
                        //}
                    //}
                    // 2017/12/14 S21_NA#19804(Sol349) Del End
                }
            }

            return true;
        } finally {

            writeEndLogLn("setDefSlsReqInHdrBean", hdrBean);
        }
    }

    /**
     * callDefSlsReqApi
     * @param hdrBean ImptHdrBean
     * @return NMZC260001PMsg
     */
    private NMZC260001PMsg callDefSlsReqApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("callDefSlsReqApi", hdrBean);

        try {

            NMZC260001PMsg pMsg = new NMZC260001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, hdrBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd()); // S21_NA#15004
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd()); // 2018/08/15 S21_NA#27771 Add
            // 2020/04/27 QC#56638 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, hdrBean.getSoldToCustLocCd());

            // call NMZC2600 Default Sales Rep API
            new NMZC260001().execute(pMsg, onBatchType);

            addErrorMsgList(pMsg, hdrBean);

            return pMsg;
        } finally {

            writeEndLogLn("callDefSlsReqApi", hdrBean);
        }
    }

    // Mod Start 2018/06/25 QC#23726
    ///**
    // * deriveDefCarrSvcLvl
    // * @param hdrBean ImptHdrBean
    // * @return String
    // */
    //private String deriveDefCarrSvcLvl(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
    /**
     * setCarrSvcLvl
     * @param onBatchType ONBATCH_TYPE
     * @param hdrBean ImptHdrBean
     */
    private void setCarrSvcLvl(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
        // Mod End 2018/06/25 QC#23726
        writeStartLogLn("deriveDefCarrSvcLvl", hdrBean);

        try {

            // API
            NMZC611001PMsg pMsg = new NMZC611001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, hdrBean.getShipToCustAcctCd());
            // 2018/12/14 S21_NA#29315 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.locNum, getShipToLocNum(hdrBean));
            ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, hdrBean.getLineBizTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(hdrBean.getGlblCmpyCd(), hdrBean.getDsOrdCatgCd(), hdrBean.getDsOrdTpCd(), hdrBean.getDsOrdRsnCd()));
            ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, hdrBean.getFrtCondCd());
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
            // 2018/12/14 S21_NA#29315 Add End
            new NMZC611001().execute(pMsg, onBatchType);

            String carrCd = pMsg.vndCd_O.getValue();
            if (ZYPCommonFunc.hasValue(carrCd)) {
                // Add Start 2018/06/25 QC#23726
                if (FRT_COND.COLLECT.equals(hdrBean.getFrtCondCd())) {
                    hdrBean.setCarrAcctNum(pMsg.dsCarrAcctNum.getValue());
                } else {
                    hdrBean.setCarrAcctNum("");
                }
                // Add End 2018/06/25 QC#23726

                // Del Start 2018/12/28 QC#29759
//                SHPG_SVC_LVL_CARR_RELNTMsg tMsg = new SHPG_SVC_LVL_CARR_RELNTMsg();
//
//                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
//                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
//                ZYPEZDItemValueSetter.setValue(tMsg.carrCd, carrCd);
//
//                List<Map<String, Object>> carrSvcLvlList = NWZC226001Query.getInstance().queryMapList("getCarrSvcLvlCd", tMsg);
//
//                if (hasValueList(carrSvcLvlList)) {
//
//                    Map<String, Object> carrSvcLvl = carrSvcLvlList.get(0);
//                    // Mod Start 2018/06/25 QC#23726
//                    //return convToString(carrSvcLvl.get("CARR_SVC_LVL_CD"));
//                    hdrBean.setCarrSvcLvlCd(convToString(carrSvcLvl.get("CARR_SVC_LVL_CD")));
//                    // Mod End 2018/06/25 QC#23726
//                }
                // Del Start 2018/12/28 QC#29759
            }

            // Del Start 2018/06/25 QC#23726
            //return BLANK;
            // Del End 2018/06/25 QC#23726
        } finally {

            writeEndLogLn("deriveDefCarrSvcLvl", hdrBean);
        }

    }

    /**
     * deriveCpoInfo
     * @param hdrBean ImptHdrBean
     * @return boolean
     */
    private boolean deriveCpoInfo(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveCpoInfo", hdrBean);

        try {

            String origOrdNum = hdrBean.getOrigOrdNum();

            // *********************************************************************
            // Derive CPO
            // *********************************************************************
            CPOTMsg cpoCondTMsg = deriveCpo(hdrBean.getGlblCmpyCd(), origOrdNum);
            if (cpoCondTMsg == null) {

                DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWAM0799E);

                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            // CPO Status Check
            if (NWXC220001Util.isTargetContents(cpoCondTMsg.ordHdrStsCd.getValue(), ORD_HDR_STS.CLOSED, ORD_HDR_STS.CANCELLED)) {

                DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWAM0862E);
                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoCondTMsg.ordBookFlg.getValue()) && !hdrBean.isLoanConversion()) {

                DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWAM0863E, "Booked");

                hdrBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            hdrBean.setOrigCpo(cpoCondTMsg);

            // *********************************************************************
            // Derive DS_CPO_SLS_CR
            // *********************************************************************
            DS_CPO_SLS_CRTMsgArray dsCpoSlsCrTMsgArray = this.getOrigSlsCrList(hdrBean.getGlblCmpyCd(), origOrdNum);
            if (hasValidValue(dsCpoSlsCrTMsgArray)) {

                hdrBean.setOrigDsCpoSlsCrArray(dsCpoSlsCrTMsgArray);
            }

            // *********************************************************************
            // Derive DS_CPO_CONFIG
            // *********************************************************************
            DS_CPO_CONFIGTMsg dsCpoConfCondTMsg = new DS_CPO_CONFIGTMsg();
            dsCpoConfCondTMsg.setSQLID("001");
            dsCpoConfCondTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
            dsCpoConfCondTMsg.setConditionValue("cpoOrdNum01", origOrdNum);
            DS_CPO_CONFIGTMsgArray dsCpoConfTMsg = (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoConfCondTMsg);
            if (hasValidValue(dsCpoConfTMsg)) {

                hdrBean.setOrigDsCpoConfigArray(dsCpoConfTMsg);
            }

            // *********************************************************************
            // Derive CPO_DTL
            // *********************************************************************
            CPO_DTLTMsgArray cpoDtlTMsgArray = deriveCpoDtl(hdrBean.getGlblCmpyCd(), origOrdNum);
            if (hasValidValue(cpoDtlTMsgArray)) {

                hdrBean.setOrigCpoDtlArray(cpoDtlTMsgArray);
            }

            // *********************************************************************
            // Derive DS_CPO_RTRN_DTL
            // *********************************************************************
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            dsCpoRtrnDtlTMsg.setSQLID("002");
            dsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
            dsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", origOrdNum);
            dsCpoRtrnDtlTMsg.setConditionValue("rtrnLineStsCd01", RTRN_LINE_STS.CANCELLED);

            DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoRtrnDtlTMsg);
            if (hasValidValue(dsCpoRtrnDtlTMsgArray)) {

                hdrBean.setOrigDsCpoRtrnDtlTMsgArray(dsCpoRtrnDtlTMsgArray);
            }

            // *********************************************************************
            // Add Configuration Beans & Detail Beans&
            // *********************************************************************
            addBeanFromCpoData(hdrBean);

            return true;
        } finally {

            writeEndLogLn("deriveCpoInfo", hdrBean);
        }
    }

    private void addBeanFromCpoData(ImptHdrBean hdrBean) {

        writeStartLogLn("addBeanFromCpoData", hdrBean);

        try {

            DS_CPO_CONFIGTMsgArray configTMsgArray = hdrBean.getOrigDsCpoConfigArray();

            if (!hasValidValue(configTMsgArray)) {

                return;
            }

            DS_CPO_CONFIGTMsg baseConfTMsg;
            DsImptOrdConfigBean configBean;
            for (int i = 0; i < configTMsgArray.length(); i++) {

                baseConfTMsg = hdrBean.getOrigDsCpoConfigArray().no(i);
                configBean = addConfigBeanFromCpoConfig(hdrBean, baseConfTMsg);
                if (configBean.isOutbound()) {

                    addLineBeanFromCpoDtl(hdrBean, configBean);
                } else {

                    addLineBeanFromCpoRtrnDtl(hdrBean, configBean);
                }
            }

        } finally {
            writeEndLogLn("addBeanFromCpoData", hdrBean);
        }
    }

    private DsImptOrdConfigBean addConfigBeanFromCpoConfig(ImptHdrBean hdrBean, DS_CPO_CONFIGTMsg origConfTMsg) {

        writeStartLogLn("addConfigBeanFromCpoConfig", hdrBean);

        try {
            DS_IMPT_ORD_CONFIGTMsg imptTMsg = new DS_IMPT_ORD_CONFIGTMsg();
            DsImptOrdConfigBean configBean;

            ZYPEZDItemValueSetter.setValue(imptTMsg.glblCmpyCd, origConfTMsg.glblCmpyCd);
            // Generate dummy PK (dsCpoConfigPk * -1)
            BigDecimal configPk = origConfTMsg.dsCpoConfigPk.getValue().negate();
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdConfigPk, configPk);
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdPosnNum, origConfTMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(imptTMsg.configCatgCd, origConfTMsg.configCatgCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.configTpCd, origConfTMsg.configTpCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.svcConfigMstrPk, origConfTMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(imptTMsg.mdlId, origConfTMsg.mdlId);
            ZYPEZDItemValueSetter.setValue(imptTMsg.mdlDescTxt, origConfTMsg.mdlDescTxt);
            ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustAcctCd, origConfTMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustLocCd, origConfTMsg.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustAcctCd, origConfTMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustLocCd, origConfTMsg.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.dropShipFlg, origConfTMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToLocNm, origConfTMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToAddlLocNm, origConfTMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstLineAddr, origConfTMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdLineAddr, origConfTMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToThirdLineAddr, origConfTMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFrthLineAddr, origConfTMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstRefCmntTxt, origConfTMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdRefCmntTxt, origConfTMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtyAddr, origConfTMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToStCd, origConfTMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToProvNm, origConfTMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCntyNm, origConfTMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToPostCd, origConfTMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtryCd, origConfTMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.configCratTs, origConfTMsg.configCratTs);

            configBean = new DsImptOrdConfigBean(hdrBean, imptTMsg, origConfTMsg);

            configBean.isOrigConfig = true;

            hdrBean.dsImptOrdConfigMap.put(configPk, configBean);

            return configBean;
        } finally {

            writeEndLogLn("addConfigBeanFromCpoConfig", hdrBean);
        }
    }

    private void addLineBeanFromCpoDtl(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean) {

        writeStartLogLn("addLineBeanFromCpoDtl", configBean);

        try {

            DS_IMPT_ORD_DTLTMsg imptTMsg;
            DsImptLineBean lineBean;
            List<CPO_DTLTMsg> cpoDtlTMsgList = hdrBean.getCpoDtlTMsg(configBean.dsOrdPosnNum.getValue());
            for (CPO_DTLTMsg cpoDtlTMsg : cpoDtlTMsgList) {


                imptTMsg = new DS_IMPT_ORD_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(imptTMsg.glblCmpyCd, cpoDtlTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
                ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcRefLineNum, cpoDtlTMsg.ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcRefLineSubNum, cpoDtlTMsg.ordSrcRefLineSubNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.mdseCd, cpoDtlTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.mdseNm, cpoDtlTMsg.mdseNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.origMdseCd, cpoDtlTMsg.origMdseCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.origMdseNm, cpoDtlTMsg.origMdseNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.setMdseCd, cpoDtlTMsg.setMdseCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dropShipFlg, cpoDtlTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustCd, cpoDtlTMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToLocNm, cpoDtlTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToAddlLocNm, cpoDtlTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstLineAddr, cpoDtlTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdLineAddr, cpoDtlTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToThirdLineAddr, cpoDtlTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFrthLineAddr, cpoDtlTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstRefCmntTxt, cpoDtlTMsg.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdRefCmntTxt, cpoDtlTMsg.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtyAddr, cpoDtlTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToStCd, cpoDtlTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToProvNm, cpoDtlTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCntyNm, cpoDtlTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToPostCd, cpoDtlTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtryCd, cpoDtlTMsg.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.ordQty, cpoDtlTMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(imptTMsg.ordCustUomQty, cpoDtlTMsg.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(imptTMsg.invtyLocCd, cpoDtlTMsg.invtyLocCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.prcCatgCd, cpoDtlTMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.flPrcListCd, cpoDtlTMsg.flPrcListCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dealPrcListPrcAmt, cpoDtlTMsg.dealPrcListPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.funcPrcListPrcAmt, cpoDtlTMsg.funcPrcListPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.entDealNetUnitPrcAmt, cpoDtlTMsg.entDealNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.entFuncNetUnitPrcAmt, cpoDtlTMsg.entFuncNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.cpoDtlDealNetAmt, cpoDtlTMsg.cpoDtlDealNetAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.cpoDtlDealSlsAmt, cpoDtlTMsg.cpoDtlDealSlsAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.cpoDtlFuncNetAmt, cpoDtlTMsg.cpoDtlFuncNetAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.cpoDtlFuncSlsAmt, cpoDtlTMsg.cpoDtlFuncSlsAmt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.prcBaseDt, cpoDtlTMsg.prcBaseDt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.ccyCd, cpoDtlTMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.exchRate, cpoDtlTMsg.exchRate);
                ZYPEZDItemValueSetter.setValue(imptTMsg.rddDt, cpoDtlTMsg.rddDt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.rsdDt, cpoDtlTMsg.rsdDt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shipCpltCd, cpoDtlTMsg.shipCpltCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.uomCpltFlg, cpoDtlTMsg.uomCpltFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.stkStsCd, cpoDtlTMsg.stkStsCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.slsRepOrSlsTeamTocCd, cpoDtlTMsg.slsRepOrSlsTeamTocCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.custMdseCd, cpoDtlTMsg.custMdseCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.custUomCd, cpoDtlTMsg.custUomCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.origCpoOrdNum, cpoDtlTMsg.origCpoOrdNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.origInvNum, cpoDtlTMsg.origInvNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.setItemShipCpltFlg, cpoDtlTMsg.setItemShipCpltFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdPosnNum, cpoDtlTMsg.dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.unitNetWt, cpoDtlTMsg.unitNetWt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.configItemFlg, cpoDtlTMsg.configItemFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.baseCmptFlg, cpoDtlTMsg.baseCmptFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.custIstlFlg, cpoDtlTMsg.custIstlFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.svcConfigMstrPk, cpoDtlTMsg.svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(imptTMsg.svcMachMstrPk, cpoDtlTMsg.svcMachMstrPk);
                // Set dummy PK
                ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdLineCatgCd, cpoDtlTMsg.dsOrdLineCatgCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.ordLineSrcCd, cpoDtlTMsg.ordLineSrcCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.rtlWhCd, cpoDtlTMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.rtlSwhCd, cpoDtlTMsg.rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.serNum, cpoDtlTMsg.serNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.refCpoDtlLineNum, cpoDtlTMsg.refCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.refCpoDtlLineSubNum, cpoDtlTMsg.refCpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dplyLineRefNum, cpoDtlTMsg.dplyLineRefNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.crRebilCd, cpoDtlTMsg.crRebilCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.splyTpCd, cpoDtlTMsg.splyTpCd);
                // 2019/10/04 S21_NA#51372 Mod Start
                //ZYPEZDItemValueSetter.setValue(imptTMsg.splyNm, cpoDtlTMsg.splyNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.prntVndNm, cpoDtlTMsg.prntVndNm);
                // 2019/10/04 S21_NA#51372 Mod End
                ZYPEZDItemValueSetter.setValue(imptTMsg.splyFirstAddr, cpoDtlTMsg.splyFirstAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.splyCtyAddr, cpoDtlTMsg.splyCtyAddr);
                ZYPEZDItemValueSetter.setValue(imptTMsg.splyStCd, cpoDtlTMsg.splyStCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.splyPostCd, cpoDtlTMsg.splyPostCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.csmpContrNum, cpoDtlTMsg.csmpContrNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.dlrRefNum, cpoDtlTMsg.dlrRefNum);
                ZYPEZDItemValueSetter.setValue(imptTMsg.csmpPrcListCd, cpoDtlTMsg.csmpPrcListCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.rntlTrmnDt, cpoDtlTMsg.rntlTrmnDt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.firstBllgAttrbNm, cpoDtlTMsg.firstBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.firstBllgAttrbValTxt, cpoDtlTMsg.firstBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.scdBllgAttrbNm, cpoDtlTMsg.scdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.scdBllgAttrbValTxt, cpoDtlTMsg.scdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.thirdBllgAttrbNm, cpoDtlTMsg.thirdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.thirdBllgAttrbValTxt, cpoDtlTMsg.thirdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.frthBllgAttrbNm, cpoDtlTMsg.frthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.frthBllgAttrbValTxt, cpoDtlTMsg.frthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.fifthBllgAttrbNm, cpoDtlTMsg.fifthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.fifthBllgAttrbValTxt, cpoDtlTMsg.fifthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.sixthBllgAttrbNm, cpoDtlTMsg.sixthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptTMsg.sixthBllgAttrbValTxt, cpoDtlTMsg.sixthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptTMsg.sbstMdseCd, cpoDtlTMsg.sbstMdseCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.supdLockFlg, cpoDtlTMsg.supdLockFlg);
                ZYPEZDItemValueSetter.setValue(imptTMsg.prcListEquipConfigNum, cpoDtlTMsg.prcListEquipConfigNum);

                lineBean = new DsImptLineBean(hdrBean, imptTMsg, cpoDtlTMsg);
                lineBean.cpoDtlLineNum = cpoDtlTMsg.cpoDtlLineNum.getValue();
                lineBean.cpoDtlLineSubNum = cpoDtlTMsg.cpoDtlLineSubNum.getValue();
                lineBean.dsCpoLineNum = cpoDtlTMsg.dsCpoLineNum.getValue();
                if (ZYPCommonFunc.hasValue(cpoDtlTMsg.dsCpoLineSubNum)) {
                    lineBean.dsCpoLineSubNum = cpoDtlTMsg.dsCpoLineSubNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(cpoDtlTMsg.refCpoDtlLineNum)) {
                    lineBean.refCpoDtlLineNum = cpoDtlTMsg.refCpoDtlLineNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(cpoDtlTMsg.refCpoDtlLineSubNum)) {
                    lineBean.refCpoDtlLineSubNum = cpoDtlTMsg.refCpoDtlLineSubNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(cpoDtlTMsg.dplyLineRefNum)) {
                    lineBean.dplyLineRefNum = cpoDtlTMsg.dplyLineRefNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(cpoDtlTMsg.loanBalQty)) {
                    lineBean.loanBalQty = cpoDtlTMsg.loanBalQty.getValue();
                }
                lineBean.isOrigCpoDtl = true;

                // *************************************************************
                // Derive MDSETMsg
                // *************************************************************
                deriveMdseInfo(lineBean);

                hdrBean.addDsImptLine(lineBean);
            }
        } finally {

            writeEndLogLn("addLineBeanFromCpoDtl", configBean);
        }
    }

    private void addLineBeanFromCpoRtrnDtl(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean) {

        writeStartLogLn("addLineBeanFromCpoRtrnDtl", configBean);

        try {

            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg;
            DS_IMPT_ORD_RTRN_DTLTMsg imptRtrnTMsg;
            DsImptRtnLineBean rtnBean;
            List<DS_CPO_RTRN_DTLTMsg> dsCpoRtnDtlTMsgList = hdrBean.getDsCpoRtnDtlTMsg(configBean.dsOrdPosnNum.getValue());
            for (DS_CPO_RTRN_DTLTMsg dsCpoRtnDtlTMsg : dsCpoRtnDtlTMsgList) {

                dsCpoRtrnDtlTMsg = hdrBean.getCpoRtnDtlTMsg(dsCpoRtnDtlTMsg);

                imptRtrnTMsg = new DS_IMPT_ORD_RTRN_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.glblCmpyCd, dsCpoRtnDtlTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ordSrcRefLineNum, dsCpoRtnDtlTMsg.ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ordSrcRefLineSubNum, dsCpoRtnDtlTMsg.ordSrcRefLineSubNum);
                // Set dummy PK
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsOrdPosnNum, dsCpoRtnDtlTMsg.dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsCpoLineCatgCd, dsCpoRtnDtlTMsg.dsOrdLineCatgCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ordLineSrcCd, dsCpoRtnDtlTMsg.ordLineSrcCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.mdseCd, dsCpoRtnDtlTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.mdseNm, dsCpoRtnDtlTMsg.mdseNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.origMdseCd, dsCpoRtnDtlTMsg.origMdseCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.origMdseNm, dsCpoRtnDtlTMsg.origMdseNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.custMdseCd, dsCpoRtnDtlTMsg.custMdseCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.setMdseCd, dsCpoRtnDtlTMsg.setMdseCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.baseCmptFlg, dsCpoRtnDtlTMsg.baseCmptFlg);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dropShipFlg, dsCpoRtnDtlTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToCustCd, dsCpoRtnDtlTMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToLocNm, dsCpoRtnDtlTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToAddlLocNm, dsCpoRtnDtlTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToFirstLineAddr, dsCpoRtnDtlTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToScdLineAddr, dsCpoRtnDtlTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToThirdLineAddr, dsCpoRtnDtlTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToFrthLineAddr, dsCpoRtnDtlTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToFirstRefCmntTxt, dsCpoRtnDtlTMsg.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToScdRefCmntTxt, dsCpoRtnDtlTMsg.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToCtyAddr, dsCpoRtnDtlTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToStCd, dsCpoRtnDtlTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToProvNm, dsCpoRtnDtlTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToCntyNm, dsCpoRtnDtlTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToPostCd, dsCpoRtnDtlTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.shipToCtryCd, dsCpoRtnDtlTMsg.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.custUomCd, dsCpoRtnDtlTMsg.custUomCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ordQty, dsCpoRtnDtlTMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ordCustUomQty, dsCpoRtnDtlTMsg.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.invtyLocCd, dsCpoRtnDtlTMsg.invtyLocCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.rtlWhCd, dsCpoRtnDtlTMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.rtlSwhCd, dsCpoRtnDtlTMsg.rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.stkStsCd, dsCpoRtnDtlTMsg.stkStsCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.prcBaseDt, dsCpoRtnDtlTMsg.prcBaseDt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.prcCatgCd, dsCpoRtnDtlTMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.flPrcListCd, dsCpoRtnDtlTMsg.flPrcListCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dealPrcListPrcAmt, dsCpoRtnDtlTMsg.dealPrcListPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.funcPrcListPrcAmt, dsCpoRtnDtlTMsg.funcPrcListPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.entDealNetUnitPrcAmt, dsCpoRtnDtlTMsg.entDealNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.entFuncNetUnitPrcAmt, dsCpoRtnDtlTMsg.entFuncNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.cpoDtlDealNetAmt, dsCpoRtnDtlTMsg.cpoDtlDealNetAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.cpoDtlDealSlsAmt, dsCpoRtnDtlTMsg.cpoDtlDealSlsAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.cpoDtlFuncNetAmt, dsCpoRtnDtlTMsg.cpoDtlFuncNetAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.cpoDtlFuncSlsAmt, dsCpoRtnDtlTMsg.cpoDtlFuncSlsAmt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.ccyCd, dsCpoRtnDtlTMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.exchRate, dsCpoRtnDtlTMsg.exchRate);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.slsRepOrSlsTeamTocCd, dsCpoRtnDtlTMsg.slsRepOrSlsTeamTocCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.serNum, dsCpoRtnDtlTMsg.serNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.rqstPickUpDt, dsCpoRtnDtlTMsg.rqstPickUpDt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.origCpoOrdNum, dsCpoRtnDtlTMsg.origCpoOrdNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.origInvNum, dsCpoRtnDtlTMsg.origInvNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.svcConfigMstrPk, dsCpoRtnDtlTMsg.svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsContrNum, dsCpoRtnDtlTMsg.dsContrNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dsContrSqNum, dsCpoRtnDtlTMsg.dsContrSqNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.svcMachMstrPk, dsCpoRtnDtlTMsg.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.refCpoDtlLineNum, dsCpoRtnDtlTMsg.refCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.refCpoDtlLineSubNum, dsCpoRtnDtlTMsg.refCpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dplyLineRefNum, dsCpoRtnDtlTMsg.dplyLineRefNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.csmpContrNum, dsCpoRtnDtlTMsg.csmpContrNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.dlrRefNum, dsCpoRtnDtlTMsg.dlrRefNum);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.csmpPrcListCd, dsCpoRtnDtlTMsg.csmpPrcListCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.hddRmvCd, dsCpoRtnDtlTMsg.hddRmvCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.rtrnRsnCd, dsCpoRtnDtlTMsg.rtrnRsnCd);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.unitNetWt, dsCpoRtnDtlTMsg.unitNetWt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.firstBllgAttrbNm, dsCpoRtnDtlTMsg.firstBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.firstBllgAttrbValTxt, dsCpoRtnDtlTMsg.firstBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.scdBllgAttrbNm, dsCpoRtnDtlTMsg.scdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.scdBllgAttrbValTxt, dsCpoRtnDtlTMsg.scdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.thirdBllgAttrbNm, dsCpoRtnDtlTMsg.thirdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.thirdBllgAttrbValTxt, dsCpoRtnDtlTMsg.thirdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.frthBllgAttrbNm, dsCpoRtnDtlTMsg.frthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.frthBllgAttrbValTxt, dsCpoRtnDtlTMsg.frthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.fifthBllgAttrbNm, dsCpoRtnDtlTMsg.fifthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.fifthBllgAttrbValTxt, dsCpoRtnDtlTMsg.fifthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.sixthBllgAttrbNm, dsCpoRtnDtlTMsg.sixthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(imptRtrnTMsg.sixthBllgAttrbValTxt, dsCpoRtnDtlTMsg.sixthBllgAttrbValTxt);

                rtnBean = new DsImptRtnLineBean(hdrBean, imptRtrnTMsg, dsCpoRtrnDtlTMsg);

                rtnBean.dsCpoConfigPk = dsCpoRtnDtlTMsg.dsCpoConfigPk.getValue();
                rtnBean.dsCpoRtrnLineNum = dsCpoRtnDtlTMsg.dsCpoRtrnLineNum.getValue();
                rtnBean.dsCpoRtrnLineSubNum = dsCpoRtnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
                rtnBean.dsCpoLineNum = dsCpoRtnDtlTMsg.dsCpoLineNum.getValue();
                if (ZYPCommonFunc.hasValue(dsCpoRtnDtlTMsg.dsCpoLineSubNum)) {
                    rtnBean.dsCpoLineSubNum = dsCpoRtnDtlTMsg.dsCpoLineSubNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(dsCpoRtnDtlTMsg.refCpoDtlLineNum)) {
                    rtnBean.refCpoDtlLineNum = dsCpoRtnDtlTMsg.refCpoDtlLineNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(dsCpoRtnDtlTMsg.refCpoDtlLineSubNum)) {
                    rtnBean.refCpoDtlLineSubNum = dsCpoRtnDtlTMsg.refCpoDtlLineSubNum.getValue();
                }
                if (ZYPCommonFunc.hasValue(dsCpoRtnDtlTMsg.dplyLineRefNum)) {
                    rtnBean.dplyLineRefNum = dsCpoRtnDtlTMsg.dplyLineRefNum.getValue();
                }
                rtnBean.isOrigRtnDtl = true;

                // *************************************************************
                // Derive MDSETMsg
                // *************************************************************
                deriveMdseInfoForRtn(rtnBean);

                hdrBean.addDsImptRtnLine(rtnBean);
            }
        } finally {

            writeEndLogLn("addLineBeanFromCpoRtrnDtl", configBean);
        }
    }

    private CPOTMsg deriveCpo(String glblCmpyCd, String cpoOrdNum) {

        writeStartLogLn("deriveCpo", cpoOrdNum);

        try {

            CPOTMsg cpo = new CPOTMsg();

            ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, cpoOrdNum);

            return (CPOTMsg) S21ApiTBLAccessor.findByKey(cpo);
        } finally {

            writeEndLogLn("deriveCpo", cpoOrdNum);
        }
    }

    private CPO_DTLTMsgArray deriveCpoDtl(String glblCmpyCd, String cpoOrdNum) {

        writeStartLogLn("deriveCpoDtl", cpoOrdNum);

        try {

            CPO_DTLTMsg cpoDtlCondTMsg = new CPO_DTLTMsg();
            cpoDtlCondTMsg.setSQLID("016");
            cpoDtlCondTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            cpoDtlCondTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

            CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlCondTMsg);

            if (hasValidValue(cpoDtlTMsgArray)) {

                return cpoDtlTMsgArray;
            } else {

                return null;
            }

        } finally {

            writeEndLogLn("deriveCpoDtl", cpoOrdNum);
        }
    }

    private void deriveDsImptOrdConfig(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveDsImptOrdConfig", hdrBean);

        try {

            Map<String, Object> getConfigParam = new HashMap<String, Object>();
            getConfigParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            getConfigParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            // 2018/01/23 S21_NA#18798 Add Start
            if (paramDsImptOrdConfigPk != null) {
                Map<String, Object> getConfigPkParam = new HashMap<String, Object>();
                getConfigPkParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                getConfigPkParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
                getConfigPkParam.put("dsImptOrdConfigPk", paramDsImptOrdConfigPk);

                List<BigDecimal> dsImptOrdConfigPkList = NWZC226001Query.getInstance().queryBigDecimalList("getDsImptOrdConfigPkList", getConfigPkParam);
                if (dsImptOrdConfigPkList.size() > 0) {
                    getConfigParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);
                }
            }
            // 2018/01/23 S21_NA#18798 Add End

            getConfigParam.put("configTpCd", hdrBean.getConfigTpCd());
            if (ZYPCommonFunc.hasValue(hdrBean.getLoanOrigOrdNum())) {
                getConfigParam.put("origCpoOrdNum", hdrBean.getLoanOrigOrdNum());
            }
            // 2018/03/14 QC#20153 Mod Start
//            if (hdrBean.isLoanNew()) {
            if (hdrBean.isLoanNew() && !hdrBean.getIsNotLoanOrigOrdFlg()) {
            // 2018/03/14 QC#20153 Mod End
                getConfigParam.put("isLoanNewFlg", ZYPConstant.FLG_ON_Y);
            } else {
                getConfigParam.put("isLoanNewFlg", ZYPConstant.FLG_OFF_N);
            }
            // 2019/01/29 S21_NA#30022 Add Start
            getConfigParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);
            getConfigParam.put("configCatgIn", CONFIG_CATG.INBOUND);
            getConfigParam.put("imptLineFlgY", ZYPConstant.FLG_ON_Y);
            // 2019/01/29 S21_NA#30022 Add End
            List<Map<String, Object>> dsImptOrdConfigList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdConfigPk", getConfigParam);
            if (hasValueList(dsImptOrdConfigList)) {

                String dsOrdPosnNum = getMaxDsOrdPosnNum(hdrBean, CONFIG_CATG.OUTBOUND);
                String dsOrdPosnNumRtrn = getMaxDsOrdPosnNum(hdrBean, CONFIG_CATG.INBOUND);
                for (int i = 0; i < dsImptOrdConfigList.size(); i++) {

                    Map<String, Object> dsImptOrdConfigMap = (Map<String, Object>) dsImptOrdConfigList.get(i);

                    // *********************************************************
                    // Derive DS_IMPT_ORD_CONFIG
                    // *********************************************************
                    DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = new DS_IMPT_ORD_CONFIGTMsg();
                    // 2018/06/12 S21_NA#24294 Mod Start
                    mapData(dsImptOrdConfigMap, dsImptOrdConfigTMsg);
                    // BigDecimal dsImptOrdConfigPk = convToBigDecimal(dsImptOrdConfigMap.get("DS_IMPT_ORD_CONFIG_PK"));
                    // ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    // ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
                    // dsImptOrdConfigTMsg = (DS_IMPT_ORD_CONFIGTMsg) S21ApiTBLAccessor.findByKey(dsImptOrdConfigTMsg);
                    // checkTMsgDbAccess(dsImptOrdConfigTMsg);
                    // 2018/06/12 S21_NA#24294 Mod End
                    if (dsImptOrdConfigTMsg != null) {

                        DsImptOrdConfigBean configBean = new DsImptOrdConfigBean(hdrBean, dsImptOrdConfigTMsg);
                        if (hdrBean.isEdiData()) {

                            if (!updateDsImptOrdConfigForEDi(configBean)) {

                                continue;
                            }

                            // Sales Credit Configuration Level
                            if (!insertDsImptOrdSlsCr(hdrBean, configBean)) {

                                continue;
                            }
                            dsImptOrdConfigTMsg = (DS_IMPT_ORD_CONFIGTMsg) configBean;
                        }

                        if (!configBean.isOrigConfig) {
                            if (CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue())) {
                                dsOrdPosnNum = getNextDsOrdPosnNum(dsOrdPosnNum);
                                ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsOrdPosnNum, dsOrdPosnNum);
                                ZYPEZDItemValueSetter.setValue(configBean.dsOrdPosnNum, dsOrdPosnNum);
                            } else {
                                dsOrdPosnNumRtrn = getNextDsOrdPosnNum(dsOrdPosnNumRtrn);
                                ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsOrdPosnNum, dsOrdPosnNumRtrn);
                                ZYPEZDItemValueSetter.setValue(configBean.dsOrdPosnNum, dsOrdPosnNumRtrn);
                            }
                        }

                        // 2018/01/23 QC#18798 Add Start
                        if (paramDsImptOrdConfigPk != null) {
                            configBean.origDsOrdPosnNum = convToString(dsImptOrdConfigMap.get("DS_ORD_POSN_NUM"));
                        }
                        // 2018/01/23 QC#18798 Add End

                        // Loan Upgrade
                        if (hdrBean.isLoanUpgrade()) {
                            BigDecimal imptSvcConfigMstrPk = configBean.svcConfigMstrPk.getValue();
                            String imptConfigCatgCd = configBean.configCatgCd.getValue();

                            // find Original Config
                            for (Map.Entry<BigDecimal, DsImptOrdConfigBean> entry : hdrBean.dsImptOrdConfigMap.entrySet()) {
                                BigDecimal origKey = entry.getKey();
                                DsImptOrdConfigBean origConfigBean = entry.getValue();
                                BigDecimal origSvcConfigMstrPk = origConfigBean.svcConfigMstrPk.getValue();
                                String origConfigCatgCd = origConfigBean.configCatgCd.getValue();

                                // Matching
                                if (imptSvcConfigMstrPk.compareTo(origSvcConfigMstrPk) == 0 && imptConfigCatgCd.equals(origConfigCatgCd)) {

                                    // Delete Original Config from Map
                                    hdrBean.dsImptOrdConfigMap.remove(origKey);

                                    // Update Original Detail
                                    // dsImptOrdConfigPk
                                    ZYPEZDItemValueSetter.setValue(origConfigBean.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk.getValue());
                                    // Put Original config by new
                                    // key(imptSvcConfigMstrPk)
                                    hdrBean.dsImptOrdConfigMap.put(configBean.dsImptOrdConfigPk.getValue(), origConfigBean);
                                    break;
                                }
                            }

                        } else {

                            hdrBean.dsImptOrdConfigMap.put(dsImptOrdConfigTMsg.dsImptOrdConfigPk.getValue(), configBean);

                            // *****************************************************
                            // Derive Configuration TOC
                            // *****************************************************
                            Map<String, Object> ssmParam = new HashMap<String, Object>();
                            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                            ssmParam.put("dsImptOrdConfigPk", dsImptOrdConfigTMsg.dsImptOrdConfigPk.getValue());
                            ssmParam.put("essw", LINE_BIZ_ROLE_TP.ESS_WRITER);
                            ssmParam.put("ppsw", LINE_BIZ_ROLE_TP.PPS_WRITER);
                            ssmParam.put("lfsw", LINE_BIZ_ROLE_TP.LFS_WRITER);
                            ssmParam.put("emsdw", LINE_BIZ_ROLE_TP.EMSD_WRITER); // 2018/01/23 QC#18798 Add
                            List<Map<String, Object>> dconfigTocList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getConfigToc", ssmParam);
                            if (hasValueList(dconfigTocList)) {

                                Map<String, Object> dconfigToc = (Map<String, Object>) dconfigTocList.get(0);
                                configBean.slsRepTocCd = convToString(dconfigToc.get("SLS_REP_TOC_CD"));
                                configBean.coaBrCd = convToString(dconfigToc.get("COA_BR_CD"));
                                configBean.coaExtnCd = convToString(dconfigToc.get("COA_EXTN_CD"));
                            }
                        }

                        if (!ZYPCommonFunc.hasValue(configBean.configTpCd)) {

                            if (CONFIG_CATG.INBOUND.equals(configBean.configCatgCd.getValue())) {

                                ZYPEZDItemValueSetter.setValue(configBean.configTpCd, CONFIG_TP.RETURN_EXISTING_IB);
                            } else {

                                ZYPEZDItemValueSetter.setValue(configBean.configTpCd, CONFIG_TP.NEW);
                            }
                        }
                    }
                }
            }
        } finally {

            writeEndLogLn("deriveDsImptOrdConfig", hdrBean);
        }

    }

    private boolean deriveLineData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveLineData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            ssmParam.put("slsDt", hdrBean.getSlsDt());
            ssmParam.put("dsOrdTpCd", convToString(hdrBean.getDsOrdTpCd(), ""));
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);

            if (isLeaseOrder(hdrBean)) {

                // Financed Items
                ssmParam.put("finItemLineFlg", ZYPConstant.FLG_ON_Y);
                ssmParam.put("finItemLineFlg_N", ZYPConstant.FLG_OFF_N); // 2019/01/16 S21_NA#29967 Add
            }

             List<Map<String, Object>> imptOrdLineList = new ArrayList<Map<String, Object>>(0);
            if (!hdrBean.isLoanDowngrade()) {
                 imptOrdLineList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getLineData", ssmParam);
            }

            // *****************************************************************
            // Create Import Order Line Data
            // *****************************************************************
            LinkedHashMap<BigDecimal, DS_IMPT_ORD_DTLTMsg> dsImptOrdDtlMsgMap = new LinkedHashMap<BigDecimal, DS_IMPT_ORD_DTLTMsg>();

            String dsOrdPosnNum = getMaxDsOrdPosnNum(hdrBean, CONFIG_CATG.OUTBOUND);
            BigDecimal configPk = null;

            // Add Start 2017/06/15 QC#19071
            boolean errFlg = false;
            // Add End 2017/06/15 QC#19071
            for (int i = 0; i < imptOrdLineList.size(); i++) {

                // 2017/11/10 S21_NA#22464 Add Start
                boolean lineErrFlg = false;
                // 2017/11/10 S21_NA#22464 Add End
                Map<String, Object> imptOrdLine = imptOrdLineList.get(i);
                BigDecimal dsImptOrdConfigPk = convToBigDecimal(imptOrdLine.get("DS_IMPT_ORD_CONFIG_PK"));
                BigDecimal dsImptOrdDtlPk = convToBigDecimal(imptOrdLine.get("DS_IMPT_ORD_DTL_PK"));

                DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg = dsImptOrdDtlMsgMap.get(dsImptOrdDtlPk);

                if (dsImptOrdDtlTMsg == null) {

                    dsImptOrdDtlTMsg = new DS_IMPT_ORD_DTLTMsg();
                    // 2018/06/11 S21_NA#24294 Mod Start
                    mapData(imptOrdLine, dsImptOrdDtlTMsg);
                    // ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    // ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);
                    // dsImptOrdDtlTMsg = (DS_IMPT_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(dsImptOrdDtlTMsg);
                    // checkTMsgDbAccess(dsImptOrdDtlTMsg);
                    // 2018/06/11 S21_NA#24294 Mod End
                    if (dsImptOrdDtlTMsg != null) {

                        dsImptOrdDtlMsgMap.put(dsImptOrdDtlPk, dsImptOrdDtlTMsg);
                    }
                }

                if (hdrBean.isLoanUpgrade()) {
                    if (ZYPCommonFunc.hasValue(dsImptOrdDtlTMsg.svcConfigMstrPk)) {
                        ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.dsOrdPosnNum, getPosnNumBySvcConfigMstrPk(hdrBean, dsImptOrdDtlTMsg));
                    }
                } else if (hdrBean.isLoanToSales() || hdrBean.isLoanNew()) {
                    if (!ZYPCommonFunc.hasValue(configPk) || dsImptOrdConfigPk.compareTo(configPk) != 0) {
                        dsOrdPosnNum = getNextDsOrdPosnNum(dsOrdPosnNum);
                        configPk = dsImptOrdConfigPk;
                    }
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.dsOrdPosnNum, dsOrdPosnNum);
                }

                DsImptLineBean addLineBean = new DsImptLineBean(hdrBean, dsImptOrdDtlTMsg);

                hdrBean.addDsImptLine(addLineBean);

                // Mod Start 2017/06/15 QC#19071
                if (!ZYPCommonFunc.hasValue(addLineBean.dsOrdLineCatgCd)) {
//                if (!ZYPCommonFunc.hasValue(addLineBean.dsOrdLineCatgCd) && !errFlg) {
                    if (!hdrBean.isLoanToSales()) {
                        if (!deriveDsOrdLineCatgCd(addLineBean)) {
//                            return false;
                            lineErrFlg = true;
                        }
                    }   
                }
                // Mod End 2017/06/15 QC#19071

                // *************************************************************
                // Derive EDI Detail Data
                // *************************************************************
                if (hdrBean.isEdiData()) {

                    addLineBean.setDsImptDtlExtAttrbTMsg();

                    // *********************************************************
                    // Derive MDSE Code For EDI
                    // *********************************************************
                    // Mod Start 2017/06/15 QC#19071
                    if (!ZYPCommonFunc.hasValue(addLineBean.mdseCd)) {
//                    if (!ZYPCommonFunc.hasValue(addLineBean.mdseCd) && !errFlg) {

                        if (!deriveMdseCdForEdi(addLineBean)) {

//                            return false;
                            lineErrFlg = true;
                        }
                    }
                    // Mod End 2017/06/15 QC#19071

                    // 2018/06/04 S21_NA#26443 Del Start
//                    // *********************************************************
//                    // Derive CUST_UOM_CD
//                    // *********************************************************
//                    // Mod Start 2017/06/15 QC#19071
//                    if (!ZYPCommonFunc.hasValue(addLineBean.custUomCd)) {
////                    if (!ZYPCommonFunc.hasValue(addLineBean.custUomCd) && !errFlg) {
//                        if (!deriveCustUomCdForEdi(addLineBean)) {
////                            return false;
//                            lineErrFlg = true;
//                        }
//                    }
//                    // Mod End 2017/06/15 QC#19071
                    // 2018/06/04 S21_NA#26443 Del End
                }

                // *************************************************************
                // Derive MDSETMsg
                // *************************************************************
                if (!deriveMdseInfo(addLineBean)) {

                    // Mod Start 2017/06/15 QC#19071
//                    return false;
                    lineErrFlg = true;
                    // Mod End 2017/06/15 QC#19071
                }

                // 2018/06/04 S21_NA#26443 Add Start
                // *********************************************************
                // Derive EDI CUST_UOM_CD
                // *********************************************************
                if (hdrBean.isEdiData() && !lineErrFlg) {

                    if (!ZYPCommonFunc.hasValue(addLineBean.custUomCd)) {

                        if (!deriveCustUomCdForEdi(addLineBean)) {

                            lineErrFlg = true;
                        }
                    }
                }
                // 2018/06/04 S21_NA#26443 Add End

                // Mod Start 2017/06/15 QC#19071
//                if (hdrBean.isEdiData()) {
                if (hdrBean.isEdiData() && !lineErrFlg) {

                    // *********************************************************
                    // Update DS_IMPT_ORD_DTL
                    // *********************************************************
                    if (!deriveDsImptOrdDtlAttForEDi(addLineBean)) {

//                        return false;
                        lineErrFlg = true;
                    }
                }
                // Mod End 2017/06/15 QC#19071
                // 2017/11/10 S21_NA#22464 Add Start
                if (lineErrFlg) {
                    errFlg = true;
                }
                // 2017/11/10 S21_NA#22464 Add End
            }

            // Add Start 2017/06/15 QC#19071
            if (errFlg) {
                return false;
            }
            // Add End 2017/06/15 QC#19071

            if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(hdrBean.getCpoSrcTpCd())) {

                // *************************************************************
                // Overwrite Config's SHIP_TO_CUST_LOC_CD
                // *************************************************************
                overwriteConfigShipToCustLocCdForIr(hdrBean);
            }

            // 2018/02/05 QC#23329 Add Start
            String setDsOrdPosnNum = null; 
            String softwarePrntFlg = null;
            // 2018/02/05 QC#23329 Add End
            Map<String, List<NSZC048001PMsg>> softwareErrMap = new HashMap<String, List<NSZC048001PMsg>>(); // 2018/02/07 QC#23998 Add
            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                // *************************************************************
                // Expand Set MDSE
                // *************************************************************
                ExpendMdseBean expendMdseBean = new ExpendMdseBean(lineBean, null);
                expendMdseBean.setMdseCd(lineBean.mdseCd.getValue());
                expendMdseBean.setChildMdseCd(lineBean.mdseInfo.mdseCd.getValue());
                expendMdseBean.setChildMdseQty(BigDecimal.ONE);
                expendMdseBean.setMdseTpCd(lineBean.mdseInfo.mdseTpCd.getValue());

                expendMdseBean.setCpoDtlLineNum(lineBean.cpoDtlLineNum);
                expendMdseBean.setCpoDtlLineSubNum(lineBean.cpoDtlLineSubNum);
                expendMdseBean.setDsCpoLineNum(lineBean.dsCpoLineNum);
                expendMdseBean.setDsCpoLineSubNum(lineBean.dsCpoLineSubNum);
                expendMdseBean.setRefCpoDtlLineNum(lineBean.refCpoDtlLineNum);
                expendMdseBean.setRefCpoDtlLineSubNum(lineBean.refCpoDtlLineSubNum);
                expendMdseBean.setDplyLineRefNum(lineBean.dplyLineRefNum);
                expendMdseBean.setBaseCmptFlg(lineBean.baseCmptFlg.getValue());
                expendMdseBean.setSerNum(lineBean.serNum.getValue());

                lineBean.mdseAllList.add(expendMdseBean);
                if (lineBean.isSetMdseTp()) {

                    // not original
                    if (!lineBean.isOrigCpoDtl) {
                        if (!expandChildMdse(lineBean)) {

                            return false;
                        }
                    }
                }

                // 2018/02/05 QC#23329 Add Start
                // Software Model
                String dsOrdPosnNumForMdl = lineBean.dsOrdPosnNum.getValue();
                if (!dsOrdPosnNumForMdl.equals(setDsOrdPosnNum)) {
                    softwarePrntFlg = ZYPConstant.FLG_OFF_N;
                }
                if (!ZYPConstant.FLG_ON_Y.equals(softwarePrntFlg)) {
                    DsImptOrdConfigBean configBean = lineBean.dsImptOrdConfigBean;
                    BigDecimal mdlId = configBean.mdlId.getValue();

                    if (mdlId != null) {
                        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
                        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, configBean.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, configBean.mdlId);
                        // 2018/06/13 S21_NA#24294 Mod Start
                        // dsMdlTMsg = (DS_MDLTMsg) S21ApiTBLAccessor.findByKey(dsMdlTMsg);
                        dsMdlTMsg = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdlTMsg);
                        // 2018/06/13 S21_NA#24294 Mod End

                        if (dsMdlTMsg != null) {
                            if (SVC_MDL_TP.SOFTWARE.equals(dsMdlTMsg.svcMdlTpCd.getValue())) {
                                NSZC048001PMsg svcMdlApiPMsg = new NSZC048001PMsg();
                                ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                                ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.slsDt, hdrBean.getSlsDt());
                                NSZC048001_xxChildMdseCdListPMsgArray childMdseCdList = svcMdlApiPMsg.xxChildMdseCdList;
                                ZYPEZDItemValueSetter.setValue(childMdseCdList.no(0).childMdseCd, lineBean.mdseCd.getValue());
                                childMdseCdList.setValidCount(1);

                                // call NSZC0480 Service Model API
                                new NSZC048001().execute(svcMdlApiPMsg, ONBATCH_TYPE.ONLINE);

                                // 2018/02/07 QC#23998 Mod Start
                                if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
                                    // 2018/03/01 QC#24117 del start
//                                    List<NSZC048001PMsg> list = softwareErrMap.get(lineBean.dsOrdPosnNum.getValue());
//                                    if (list == null) {
//                                        list = new ArrayList<NSZC048001PMsg>();
//                                        softwareErrMap.put(lineBean.dsOrdPosnNum.getValue(), list);
//                                    }
//                                    list.add(svcMdlApiPMsg);
                                    // 2018/03/01 QC#24117 del end
                                } else {
                                    if (ZYPCommonFunc.hasValue(svcMdlApiPMsg.prntMdseCd)) {
                                        expendMdseBean = lineBean.mdseAllList.get(lineBean.mdseAllList.size() - 1);
                                        expendMdseBean.setInstlBaseCtrlFlg(ZYPConstant.FLG_OFF_N);
                                        lineBean.mdseAllList.set(lineBean.mdseAllList.size() - 1, expendMdseBean);

                                        expendMdseBean = new ExpendMdseBean(lineBean, null);
                                        expendMdseBean.setMdseCd(svcMdlApiPMsg.prntMdseCd.getValue());
                                        expendMdseBean.setChildMdseCd(svcMdlApiPMsg.prntMdseCd.getValue());
                                        expendMdseBean.setChildMdseQty(BigDecimal.ONE);
                                        expendMdseBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                                        expendMdseBean.setInstlBaseCtrlFlg(ZYPConstant.FLG_ON_Y);
                                        expendMdseBean.setSoftwarePrnt(true);

                                        lineBean.mdseAllList.add(expendMdseBean);
                                    }
                                    softwarePrntFlg = ZYPConstant.FLG_ON_Y;
                                    softwareErrMap.remove(lineBean.dsOrdPosnNum.getValue());
                                }
                            }
                        }
                        setDsOrdPosnNum = dsOrdPosnNumForMdl;
                    }
                }
                // 2018/02/05 QC#23329 Add End
                // 2018/02/07 QC#23998 Mod End

                // *************************************************************
                // Derive MDSE Supply Info
                // *************************************************************
                deriveMdseSupplInfo(lineBean);
            }

            // 2018/03/01 QC#24117 del start
            // 2018/02/07 QC#23998 Add Start
//            if (!addErrorMsgListForSvcMdlApi(hdrBean, softwareErrMap)) {
//                return false;
//            }
            // 2018/02/07 QC#23998 Add End
            // 2018/03/01 QC#24117 del end

            // *****************************************************************
            // Supply Auto Add
            // *****************************************************************
            if (ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS.equals(hdrBean.getOrdCatgCtxTpCd())) {

                for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                    if (configBean.isUpdateData()) {

                        continue;
                    }
                    if (configBean.hasBaseComponent()) {

                        if (ZYPCommonFunc.hasValue(configBean.mdlId)) {

                            NWZC181001PMsg apiMsg = callAutoAddSplyApi(onBatchType, hdrBean, configBean);
                            if (!autoAddSupply(apiMsg, hdrBean, configBean)) {

                                continue;
                            }
                        }
                    }
                }
            }

            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                // *********************************************************
                // Derive Default WH
                // *********************************************************
                if (!ZYPCommonFunc.hasValue(lineBean.rtlWhCd) && NWXC220001.needsWarehouseMdse(hdrBean.getGlblCmpyCd(), lineBean.mdseCd.getValue())) {

                    boolean callDefWhApi = true;
                    if (hdrBean.isEdiData()) {

                        callDefWhApi = deriveDefWhInfoForEdi(lineBean);
                    }

                    if (lineBean.isAutoAddSupply) {

                        // prepare child
                        deriveDefWhInfoForSupplyAutoAdd(lineBean);
                        callDefWhApi = false;
                    }

                    if (callDefWhApi) {

                        // call default warehouse API
                        // QC#58230
                        // 2022/12/10 QC#60885 Mod Start
                        if (!callDefWhApiForLineConf(onBatchType, lineBean, hdrBean.getCpoSrcTpCd(), hdrBean)) {
                        // if (!callDefWhApiForLineConf(onBatchType, lineBean, hdrBean.getCpoSrcTpCd())) {
                        // 2022/12/10 QC#60885 Mod End
                            return false;
                        }
                    }
                }

                // *************************************************************
                // Derive currency code
                // *************************************************************
                if (!ZYPCommonFunc.hasValue(lineBean.ccyCd)) {

                    ZYPEZDItemValueSetter.setValue(lineBean.ccyCd, deriveCcyCdByPrcCatgCd(hdrBean.getGlblCmpyCd(), lineBean.prcCatgCd.getValue()));
                }

                // *************************************************************
                // Calculate UOM Quantity
                // *************************************************************
                lineBean.calcMdseQty();
                if (!calcUomQty(lineBean)) {

                    return false;
                }
            }

            // *****************************************************************
            // Set Line Reference Number
            // *****************************************************************
            setLineReferenceNumber(hdrBean);

            return true;
        } finally {

            writeEndLogLn("deriveLineData", hdrBean);
        }
    }

    private boolean deriveDsOrdLineCatgCd(DsImptLineBean lineBean) {

        writeStartLogLn("deriveDsOrdLineCatgCd", lineBean);

        try {

            // 2017/03/30 S21_NA#18179 Mod Start
            if (this.leaseByotMdseCd.equals(lineBean.mdseCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineBean.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LEASE_BUYOUT);
                return true;
            }
            // 2017/03/30 S21_NA#18179 Mod End

            NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(lineBean.imptHdrBean.getGlblCmpyCd(), lineBean.imptHdrBean.getDsOrdTpCd(), lineBean.imptHdrBean.getSlsDt(), DS_ORD_LINE_DRCTN.OUTBOUND);

            if (result.hasError()) {

                return false;
            }

            ZYPEZDItemValueSetter.setValue(lineBean.dsOrdLineCatgCd, (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD"));

            return true;
        } finally {

            writeEndLogLn("deriveDsOrdLineCatgCd", lineBean);
        }
    }

    private String deriveCcyCdByPrcCatgCd(String glblCmpyCd, String prcCatgCd) {

        String ccyCd = null;
        if (ZYPCommonFunc.hasValue(prcCatgCd)) {

            PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, prcCatgCd);
            prcCatg = (PRC_CATGTMsg) S21ApiTBLAccessor.findByKey(prcCatg);
            if (prcCatg != null) {

                ccyCd = prcCatg.ccyCd.getValue();
            }
        }

        return ccyCd;
    }

    private boolean deriveMdseCdForEdi(DsImptLineBean lineBean) {

        writeStartLogLn("deriveMdseCdForEdi", lineBean);

        try {

            if (lineBean.dsImptDtlExtAttrbTMsg == null) {

                DsImptOrdErrBean errBean = new DsImptOrdErrBean(lineBean, MSG_ID.NWZM1906E);
                lineBean.getDsImptOrdErrList().add(errBean);
                return false;
            }
            // Mod Start 2017/10/24 QC#22012
            NWXC220001Result<Map<String, String>> result = NWXC220001ImportAttribute.deriveDefMdse(lineBean.imptHdrBean.getGlblCmpyCd(), lineBean.imptHdrBean.getSellToCustCd(), lineBean.dsImptDtlExtAttrbTMsg.idocPoDtlMatNum_01.getValue(),
                    lineBean.dsImptDtlExtAttrbTMsg.idocPoDtlMatNum_02.getValue());
            // Mod End 2017/10/24 QC#22012

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, lineBean);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(lineBean.mdseCd, result.getResultObject().get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(lineBean.mdseNm, result.getResultObject().get("MDSE_NM"));

            return true;
        } finally {

            writeEndLogLn("deriveMdseCdForEdi", lineBean);
        }
    }

    private boolean deriveCustUomCdForEdi(DsImptLineBean lineBean) {

        writeStartLogLn("deriveCustUomCdForEdi", lineBean);

        try {
            if (lineBean.dsImptDtlExtAttrbTMsg == null) {

                DsImptOrdErrBean errBean = new DsImptOrdErrBean(lineBean, MSG_ID.NWZM1906E);
                lineBean.getDsImptOrdErrList().add(errBean);
                lineBean.hasEdiCustUomError = true;
                return false;
            }

            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, lineBean.imptHdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, lineBean.imptHdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.UOM_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, lineBean.imptHdrBean.getDsImptExtAttrbTMsg().ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CUST_UOM_CD_ATTRB2);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_UOM_CD_ATTRB3);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, lineBean.dsImptDtlExtAttrbTMsg.idocPoDtlUomCd);
            // 2018/06/04 S21_NA#26443 Add Start
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_05, lineBean.mdseInfo.pkgUomClsCd);
            // 2018/06/04 S21_NA#26443 Add End

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

            if (result.hasError()) {

                addErrorMsgListFromImptAttrb(result, lineBean);
                lineBean.hasEdiCustUomError = true;
                return false;
            }

            ZYPEZDItemValueSetter.setValue(lineBean.custUomCd, result.getResultObject().trgtAttrbTxt_01);

            return true;
        } finally {

            writeEndLogLn("deriveCustUomCdForEdi", lineBean);
        }
    }

    private boolean deriveMdseInfo(DsImptLineBean lineBean) {

        writeStartLogLn("deriveMDSE", lineBean);

        // 2017/11/10 S21_NA#22464 Add Start
        if (!ZYPCommonFunc.hasValue(lineBean.mdseCd.getValue())) {
            return true;
        }
        // 2017/11/10 S21_NA#22464 Add End
        try {

            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(lineBean.imptHdrBean.getGlblCmpyCd(), lineBean.mdseCd.getValue());
            if (mdseTMsg == null) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(MDSETMsg.class, lineBean, lineBean.mdseCd.getValue());
                lineBean.dsImptOrdErrList.add(errBean);
                return false;
            }
            lineBean.mdseInfo = mdseTMsg;

            return true;
        } finally {

            writeEndLogLn("deriveMDSE", lineBean);
        }
    }

    private boolean deriveMdseInfoForRtn(DsImptRtnLineBean rtnBean) {

        writeStartLogLn("deriveMdseInfoForRtn", rtnBean);

        try {

            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(rtnBean.imptHdrBean.getGlblCmpyCd(), rtnBean.mdseCd.getValue());
            if (mdseTMsg == null) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(MDSETMsg.class, rtnBean, rtnBean.mdseCd.getValue());
                rtnBean.dsImptOrdErrList.add(errBean);
                return false;
            }
            rtnBean.mdseInfo = mdseTMsg;

            return true;
        } finally {

            writeEndLogLn("deriveMdseInfoForRtn", rtnBean);
        }
    }

    private void overwriteConfigShipToCustLocCdForIr(ImptHdrBean hdrBean) {

        if (isWHTransfer(hdrBean)) {

            return;
        }

        for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

            if (CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue())) {

                if (configBean.dsImptOrdLineList.size() > 0) {

                    ZYPEZDItemValueSetter.setValue(configBean.shipToCustLocCd, configBean.dsImptOrdLineList.get(0).shipToCustCd);
                }
            }
        }
    }

    private NWZC181001PMsg callAutoAddSplyApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, DsImptOrdConfigBean configBean) {

        writeStartLogLn("callAutoAddSplyApi", configBean);

        try {

            NWZC181001PMsg pMsg = new NWZC181001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
            // 2017/09/08 S21_NA#20992 Mod Start
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NWZC181001Constant.PROC_MD_ODR_IMP);
            // 2017/09/08 S21_NA#20992 Mod End
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.mdlId, configBean.mdlId);
            // 2017/09/08 S21_NA#20992 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            // 2017/09/08 S21_NA#20992 Add End

            // call NWZC181001 Auto Add Supply API
            new NWZC181001().execute(pMsg, onBatchType);

            return pMsg;
        } finally {

            writeEndLogLn("callAutoAddSplyApi", configBean);
        }
    }

    private boolean autoAddSupply(NWZC181001PMsg apiResultMsg, ImptHdrBean hdrBean, DsImptOrdConfigBean configBean) {

        writeStartLogLn("autoAddSupply", configBean);

        try {

            writeLogLn("Auto Add Supply Count : %d", apiResultMsg.NWZC181002PMsg.getValidCount());

            if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd())) {
                return true;
            }

            if (!hasValidValue(apiResultMsg.NWZC181002PMsg)) {

                return true;
            }

            for (int i = 0; i < apiResultMsg.NWZC181002PMsg.getValidCount(); i++) {

                NWZC181002PMsg resultMsg = apiResultMsg.NWZC181002PMsg.no(i);

                DS_IMPT_ORD_DTLTMsg dtlMsg = new DS_IMPT_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dtlMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
                ZYPEZDItemValueSetter.setValue(dtlMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);

                DsImptLineBean addLineBean = new DsImptLineBean(hdrBean, dtlMsg);

                EZDMsg.copy(configBean, "", addLineBean, "");
                ZYPEZDItemValueSetter.setValue(addLineBean.shipToCustCd, configBean.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(addLineBean.prcCatgCd, hdrBean.getPrcCatgCd());

                String mdseCd = resultMsg.mdseCd.getValue();
                ZYPEZDItemValueSetter.setValue(addLineBean.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(addLineBean.ordQty, resultMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(addLineBean.custUomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(addLineBean.ordCustUomQty, resultMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(addLineBean.prcBaseDt, hdrBean.getSlsDt());

                addLineBean.isAutoAddSupply = true;

                if (!deriveMdseInfo(addLineBean)) {
                    return false;
                }

                if (ZYPCommonFunc.hasValue(resultMsg.dsOrdLineCatgCd)) {

                    ZYPEZDItemValueSetter.setValue(addLineBean.dsOrdLineCatgCd, resultMsg.dsOrdLineCatgCd);
                } else {
                    if (!hdrBean.isLoanToSales()) {
                        if (!deriveDsOrdLineCatgCd(addLineBean)) {
                            return false;
                        }
                    }
                }

                if (addLineBean.isSetMdseTp()) {

                    // case : set merchandise
                    hdrBean.addDsImptLine(addLineBean);
                    // QC#23192 2018/02/09 Add Start
                    ExpendMdseBean expendMdseBean = new ExpendMdseBean(addLineBean, null);
                    expendMdseBean.setMdseCd(mdseCd);
                    expendMdseBean.setChildMdseCd(addLineBean.mdseInfo.mdseCd.getValue());
                    expendMdseBean.setChildMdseQty(BigDecimal.ONE);
                    expendMdseBean.setMdseTpCd(addLineBean.mdseInfo.mdseTpCd.getValue());
                    addLineBean.mdseAllList.add(expendMdseBean);
                    // QC#23192 2018/02/09 Add End
                    if (!expandChildMdse(addLineBean)) {

                        return false;
                    }
                } else {

                    // case : regular merchandise
                    // 2017/09/08 S21_NA#20992 Mod Start
                    if (!configBean.hasMdse(mdseCd, true, addLineBean.dsOrdLineCatgCd.getValue())) {

                        // 2017/09/08 S21_NA#20992 Mod End

                        hdrBean.addDsImptLine(addLineBean);

                        ExpendMdseBean expendMdseBean = new ExpendMdseBean(addLineBean, null);
                        expendMdseBean.setMdseCd(mdseCd);
                        expendMdseBean.setChildMdseCd(addLineBean.mdseInfo.mdseCd.getValue());
                        expendMdseBean.setChildMdseQty(BigDecimal.ONE);
                        expendMdseBean.setMdseTpCd(addLineBean.mdseInfo.mdseTpCd.getValue());

                        addLineBean.mdseAllList.add(expendMdseBean);
                    }
                }
            }
            return true;
        } finally {

            writeEndLogLn("autoAddSupply", configBean);
        }
    }

    private boolean deriveDefWhInfoForEdi(DsImptLineBean lineBean) {

        writeStartLogLn("deriveDefWhInfoForEdi", lineBean);

        try {

            if (ZYPConstant.FLG_ON_Y.equals(lineBean.mdseInfo.invtyCtrlFlg.getValue())) {

                return true;
            } else {

                // intangible item
                lineBean.rtlWhCd.clear();
                lineBean.rtlSwhCd.clear();
                lineBean.invtyLocCd.clear();

                return false;
            }
        } finally {

            writeEndLogLn("deriveDefWhInfoForEdi", lineBean);
        }
    }


    private void deriveDefWhInfoForSupplyAutoAdd(DsImptLineBean lineBean) {

        writeStartLogLn("deriveDefWhInfoForSupplyAutoAdd", lineBean);

        try {

            DsImptLineBean baseCompntBean = lineBean.dsImptOrdConfigBean.getBaseComponetMdse().getDsImptLineBean();
            if (baseCompntBean == null) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(lineBean.rtlWhCd, baseCompntBean.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, baseCompntBean.rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, baseCompntBean.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(lineBean.ordLineSrcCd, baseCompntBean.ordLineSrcCd);

        } finally {

            writeEndLogLn("deriveDefWhInfoForSupplyAutoAdd", lineBean);
        }
    }
    // QC#58230
    private boolean callDefWhApiForLineConf(ONBATCH_TYPE onBatchType, DsImptLineBean lineBean, String cpoSrcTpCd, ImptHdrBean imptHdrBean) {

        writeStartLogLn("callDefWhApiForLineConf", lineBean);

        try {

            String shipToPostCd;

            if (ZYPCommonFunc.hasValue(lineBean.shipToPostCd)) {

                shipToPostCd = lineBean.shipToPostCd.getValue();
            } else if (ZYPCommonFunc.hasValue(lineBean.dsImptOrdConfigBean.shipToPostCd)) {

                shipToPostCd = lineBean.dsImptOrdConfigBean.shipToPostCd.getValue();
            } else {

                shipToPostCd = lineBean.imptHdrBean.getShipToPostCd();
            }

            NWZC180001PMsg pMsg = callDefWhApi(onBatchType, lineBean, lineBean.mdseInfo.mdseCd.getValue(), shipToPostCd, lineBean.ordQty.getValue(), null, null);

            if (pMsg != null) {
                String prodCondCd =lineBean.getDsImptOrdConfigBean().prodCondCd.getValue();
                if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd)) {
                    String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", lineBean.getDsImptOrdConfigBean().imptHdrBean.getGlblCmpyCd());
                    if (!ZYPCommonFunc.hasValue(dsWhCd)) {
                        dsWhCd = "DS";
                    }
                    if (dsWhCd.equals(pMsg.rtlWhCd.getValue())) {
                        lineBean.rtlWhCd.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(lineBean.rtlWhCd, pMsg.rtlWhCd);
                    }

                    // QC#60971
                    boolean isSwhChg = false;
                    if (ZYPCommonFunc.hasValue(lineBean.rtlSwhCd) && ZYPCommonFunc.hasValue(pMsg.rtlSwhCd) //
                            && !lineBean.rtlSwhCd.getValue().equals(pMsg.rtlSwhCd.getValue())) {
                        isSwhChg = true;
                    }
                    if ("NEW".equals(lineBean.getDsImptOrdConfigBean().prodCondCd.getValue()) || "REMAN".equals(lineBean.getDsImptOrdConfigBean().prodCondCd.getValue())) {

                        String defWhNew = "NEW";
                        String rtlSwhCd = pMsg.rtlSwhCd.getValue();
                        if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                            SWHTMsg tMsg = new SWHTMsg();
                            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, lineBean.imptHdrBean.getGlblCmpyCd());
                            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rtlSwhCd);
        
                            tMsg = (SWHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
                            if (tMsg != null) {
                                int costPct = tMsg.mdseInvtyCostPct.getValueInt();
                                if (!isSwhChg && 100 == costPct) {
                                    ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, tMsg.rtlSwhCd);
                                } else if (!isSwhChg){
                                    ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, defWhNew);
                                }
                            } else if (!isSwhChg) {
                                ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, defWhNew);
                            }
                        } else if (!isSwhChg && !dsWhCd.equals(pMsg.rtlWhCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, defWhNew);
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, pMsg.rtlWhCd.getValue() + lineBean.rtlSwhCd.getValue());
                } else {
                    
                    ZYPEZDItemValueSetter.setValue(lineBean.rtlWhCd, pMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, pMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, pMsg.rtlWhCd.getValue() + pMsg.rtlSwhCd.getValue());

                    // 2022/12/10 QC#60885 Add Start
                    if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd)) {

                        String loanCnvRtlSwhCd = getRtlSwhCdLoanConv(lineBean, imptHdrBean);
                        if (loanCnvRtlSwhCd != null) {
                            ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, loanCnvRtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, pMsg.rtlWhCd.getValue() + loanCnvRtlSwhCd);
                        }
                    }
                    // 2022/12/10 QC#60885 Add End
                    
                }

                // START 2023/04/04 T.Doi [QC#60254, ADD]
                boolean swhAllowedExternal = true;
                String varSubWh = ZYPCodeDataUtil.getVarCharConstValue("EXTERNAL_RTL_SUB_WH_CD", lineBean.getDsImptOrdConfigBean().imptHdrBean.getGlblCmpyCd());
                List<String> varSubWhList = new ArrayList<String>();
                if (varSubWh != null) {
                    String[] varSubWhArray = varSubWh.split(COMMA);
                    varSubWhList = Arrays.asList(varSubWhArray);

                    if (varSubWhList.size() > 0 && ZYPCommonFunc.hasValue(lineBean.rtlSwhCd) && !varSubWhList.contains(lineBean.rtlSwhCd.getValue())) {
                        swhAllowedExternal = false;
                    }
                }
                if (!swhAllowedExternal && ORD_LINE_SRC.CUSA_DROP_SHIP.equals(pMsg.ordLineSrcCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lineBean.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
                } else {
                // END 2023/04/04 T.Doi [QC#60254, ADD]
                    ZYPEZDItemValueSetter.setValue(lineBean.ordLineSrcCd, pMsg.ordLineSrcCd);
                // START 2023/04/04 T.Doi [QC#60254, ADD]
                }
                // END 2023/04/04 T.Doi [QC#60254, ADD]

                // 2022/02/21 QC#59693 Add Start
                MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(lineBean.imptHdrBean.getGlblCmpyCd(), lineBean.mdseCd.getValue());
                if (mdseTMsg != null && ZYPCommonFunc.hasValue(mdseTMsg.defSrcWhCd)) {
                    return (pMsg != null);
                }
                // 2022/02/21 QC#59693 Add End

                // 2018/08/30 S21_NA#26895-3 Del Start -> 2018/09/27 S21_NA#28199 Reuse S21_NA#26895
                // S21_NA#26895 2018/07/10 add Start
                ImptHdrBean hdrBean = lineBean.getDsImptOrdConfigBean().imptHdrBean;
                if (NWXC150001DsCheck.isAvalableOverWriteRtlWhCd(hdrBean.getGlblCmpyCd(), lineBean.rtlWhCd.getValue())) {

//                    if(CONFIG_TP.NEW.equals(lineBean.dsImptOrdConfigBean.configTpCd.getValue())) { 2018/07/13 S21_NA#27228 Del Condition
                        ExpendMdseBean mdseBean = lineBean.dsImptOrdConfigBean.getBaseComponetMdse();

                        if (mdseBean != null) {
                            DsImptLineBean baseCompntBean = mdseBean.getDsImptLineBean();
                            // 2018/07/13 S21_NA#27228 Mod Start without any commnets.
                            boolean isOverwriteWithBaseRtlWhCd = false;
                            if (baseCompntBean != null //
                                    && ZYPCommonFunc.hasValue(baseCompntBean.rtlWhCd)) {
                                isOverwriteWithBaseRtlWhCd = NWXC150001DsCheck.isAvalableOverWriteBaseRtlWhCd(hdrBean.getGlblCmpyCd(), baseCompntBean.rtlWhCd.getValue());
                            }
                            if (isOverwriteWithBaseRtlWhCd) {
                                ZYPEZDItemValueSetter.setValue(lineBean.rtlWhCd, baseCompntBean.rtlWhCd);
                                // QC#58230
                                if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd)) {
                                    ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, lineBean.rtlSwhCd);
                                    // 2022/12/10 QC#60885 Add Start
                                    ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, baseCompntBean.invtyLocCd);
                                    // 2022/12/10 QC#60885 Add End
                                } else {
                                    ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, baseCompntBean.rtlSwhCd);

                                    // 2022/12/10 QC#60885 Add Start
                                    ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, baseCompntBean.invtyLocCd);

                                    if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd)) {

                                        String loanCnvRtlSwhCd = getRtlSwhCdLoanConv(lineBean, imptHdrBean);
                                        if (loanCnvRtlSwhCd != null) {
                                            ZYPEZDItemValueSetter.setValue(lineBean.rtlSwhCd, loanCnvRtlSwhCd);
                                            ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, baseCompntBean.rtlWhCd.getValue() + loanCnvRtlSwhCd);
                                        }
                                    }
                                    // 2022/12/10 QC#60885 Add End
                                }
                                // 2022/12/10 QC#60885 Del Start
                                // ZYPEZDItemValueSetter.setValue(lineBean.invtyLocCd, baseCompntBean.invtyLocCd);
                                // 2022/12/10 QC#60885 Del End
                            // START 2023/04/04 T.Doi [QC#60254, ADD]
                            if (!swhAllowedExternal && ORD_LINE_SRC.CUSA_DROP_SHIP.equals(lineBean.ordLineSrcCd.getValue())) {
                            // END 2023/04/04 T.Doi [QC#60254, ADD]
                                ZYPEZDItemValueSetter.setValue(lineBean.ordLineSrcCd, baseCompntBean.ordLineSrcCd);
                            // START 2023/04/04 T.Doi [QC#60254, ADD]
                            }
                            // END 2023/04/04 T.Doi [QC#60254, ADD]
                            }
                            // 2018/07/13 S21_NA#27228 Mod Start without any commnets.
                        }
//                    } // 2018/07/13 S21_NA#27228 Del Condition
                }
                // S21_NA#26895 2018/07/10 add End
                // 2018/08/30 S21_NA#26895-3 Del End  -> 2018/09/27 S21_NA#28199 Reuse S21_NA#26895
            }

            return (pMsg != null);

        } finally {

            writeEndLogLn("callDefWhApiForLineConf", lineBean);
        }
    }

    // 2022/12/15 QC#60885 Add Start
    private String getRtlSwhCdLoanConv(DsImptLineBean lineBean, ImptHdrBean imptHdrBean) {
        if (ZYPCommonFunc.hasValue(lineBean.dsOrdLineCatgCd)) {
            if (DS_ORD_LINE_CATG.LOAN_BILL_ONLY.equals(lineBean.dsOrdLineCatgCd.getValue())) {
                
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", imptHdrBean.getGlblCmpyCd());
                param.put("cpoOrdNum", imptHdrBean.getLoanOrigOrdNum());
                param.put("svcConfigMstrPK", lineBean.svcConfigMstrPk.getValue());
                param.put("svcMachMstrPK", lineBean.svcMachMstrPk.getValue());

                return NWZC226001Query.getInstance().queryString("getRtrSubWH", param);
            }
        }
        return null;
    }
    // 2022/12/15 QC#60885 Add End

//    private boolean callDefWhApiForRtnLineConf(ONBATCH_TYPE onBatchType, DsImptRtnLineBean rtnBean) {
    private boolean callDefWhApiForRtnLineConf(ONBATCH_TYPE onBatchType, DsImptRtnLineBean rtnBean, DsImptRtnLineBean baseComponentline, String cpoSrcTpCd) {

        writeStartLogLn("callDefWhApiForRtnLineConf", rtnBean);

        try {

            String shipToPostCd;

            if (ZYPCommonFunc.hasValue(rtnBean.shipToPostCd)) {

                shipToPostCd = rtnBean.shipToPostCd.getValue();
            } else if (ZYPCommonFunc.hasValue(rtnBean.dsImptOrdConfigBean.shipToPostCd)) {

                shipToPostCd = rtnBean.dsImptOrdConfigBean.shipToPostCd.getValue();
            } else {

                shipToPostCd = rtnBean.imptHdrBean.getShipToPostCd();
            }

            String dsRtrnRsnCd = null;
            if (ZYPCommonFunc.hasValue(rtnBean.rtrnRsnCd)) {

                dsRtrnRsnCd = rtnBean.rtrnRsnCd.getValue();
            } else {

                for (DsImptRtnLineBean srcRtnBean : rtnBean.dsImptOrdConfigBean.dsImptRtnLineList) {

                    if (ZYPCommonFunc.hasValue(srcRtnBean.rtrnRsnCd)) {

                        dsRtrnRsnCd = srcRtnBean.rtrnRsnCd.getValue();
                        break;
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(rtnBean.rtrnRsnCd, dsRtrnRsnCd);
            ZYPEZDItemValueSetter.setValue(rtnBean.shipToPostCd, shipToPostCd);
            
            // 2019/03/20 S21_NA#30821 Add Start
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(rtnBean.getDsImptOrdConfigBean().imptHdrBean.getGlblCmpyCd(), rtnBean.mdseCd.getValue());
            if (mdseTMsg != null) {
                if (S21StringUtil.isEquals(mdseTMsg.invtyCtrlFlg.getValue(), ZYPConstant.FLG_OFF_N)) {
                    return true;
                }
            }
            // 2019/03/20 S21_NA#30821 Add End

            // Add Start 2018/03/15 QC#24258
            // if (CPO_SRC_TP.SOM.equals(cpoSrcTpCd) && null != baseComponentline) { // 2019/03/14 S21_NA#30770 Add Start
            if ((CPO_SRC_TP.SOM.equals(cpoSrcTpCd) || CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd)) && null != baseComponentline) {
                ZYPEZDItemValueSetter.setValue(rtnBean.rtlWhCd, baseComponentline.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(rtnBean.rtlSwhCd, baseComponentline.rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(rtnBean.invtyLocCd, baseComponentline.invtyLocCd);
                ZYPEZDItemValueSetter.setValue(rtnBean.ordLineSrcCd, baseComponentline.ordLineSrcCd);

                return true;
            }
            // Add End 2018/03/15 QC#24258

            // NWZC180001PMsg pMsg = callDefWhApi(onBatchType, rtnBean, rtnBean.mdseInfo.mdseCd.getValue(), shipToPostCd, rtnBean.ordQty.getValue(), dsRtrnRsnCd); // QC#30770 Add Param
            NWZC180001PMsg pMsg = callDefWhApi(onBatchType, rtnBean, rtnBean.mdseInfo.mdseCd.getValue(), shipToPostCd, rtnBean.ordQty.getValue(), dsRtrnRsnCd, null);

            if (pMsg != null && ZYPCommonFunc.hasValue(pMsg.rtlWhCd)) {

                ZYPEZDItemValueSetter.setValue(rtnBean.rtlWhCd, pMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(rtnBean.rtlSwhCd, pMsg.rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(rtnBean.invtyLocCd, pMsg.rtlWhCd.getValue() + pMsg.rtlSwhCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtnBean.ordLineSrcCd, pMsg.ordLineSrcCd);
            } else {

                for (DsImptRtnLineBean srcRtnBean : rtnBean.dsImptOrdConfigBean.dsImptRtnLineList) {

                    if (ZYPCommonFunc.hasValue(srcRtnBean.rtlWhCd)) {

                        ZYPEZDItemValueSetter.setValue(rtnBean.rtlWhCd, srcRtnBean.rtlWhCd);
                        ZYPEZDItemValueSetter.setValue(rtnBean.rtlSwhCd, srcRtnBean.rtlSwhCd);
                        ZYPEZDItemValueSetter.setValue(rtnBean.invtyLocCd, srcRtnBean.invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(rtnBean.ordLineSrcCd, srcRtnBean.ordLineSrcCd);
                        break;
                    }
                }
            }

            return true;

        } finally {

            writeEndLogLn("callDefWhApiForRtnLineConf", rtnBean);
        }
    }

    // private NWZC180001PMsg callDefWhApi(ONBATCH_TYPE onBatchType, IImportBean iBean, String mdseCd, String shipToPostCd, BigDecimal ordQty, String dsRtrnRsnCd) { // 2019/03/17 S21_NA#30770 Add Param
    private NWZC180001PMsg callDefWhApi(ONBATCH_TYPE onBatchType, IImportBean iBean, String mdseCd, String shipToPostCd, BigDecimal ordQty, String dsRtrnRsnCd, BigDecimal svcMachMstrPk) {

        writeStartLogLn("callDefWhApi", iBean);

        try {

            ImptHdrBean hdrBean = iBean.getDsImptOrdConfigBean().imptHdrBean;

            NWZC180001PMsg pMsg = new NWZC180001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
            if (iBean.getDsImptOrdConfigBean().isOutbound()) {

                // out bound
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
            } else {

                // in bound
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
                ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, dsRtrnRsnCd);
                // 2019/03/17 S21_NA#30770 Add Start
                if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
                }
                // 2019/03/17 S21_NA#30770 Add End
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.postCd, shipToPostCd);
            ZYPEZDItemValueSetter.setValue(pMsg.ordQty, ordQty);

            // call NWZC1800 Default WH API
            new NWZC180001().execute(pMsg, onBatchType);

            if (S21ApiUtil.getXxMsgList(pMsg).size() > 0) {
                // 2018/08/09 S21_NA#27677 Add Start
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {

                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();

                    if (msgId.endsWith("E")) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, msgId, msg.getXxMsgPrmArray());

                        if (!hdrBean.isSameErrorExists(errBean)) {
                            hdrBean.getDsImptOrdErrList().add(errBean);
                        }
                    }
                }
                // 2018/08/09 S21_NA#27677 Add End
                return null;
            } else {

                return pMsg;
            }

        } finally {

            writeEndLogLn("callDefWhApi", iBean);
        }
    }

    private boolean expandChildMdse(DsImptLineBean lineBean) {

        writeStartLogLn("expandChildMdse", lineBean);

        try {

            DsImptOrdConfigBean configBean = lineBean.dsImptOrdConfigBean;

            List<ExpendMdseBean> childMdseList = deriveChildMdse(lineBean, lineBean.mdseAllList.get(0), lineBean.mdseInfo.mdseCd.getValue(), lineBean.imptHdrBean.getOrdSrcImptTs_Len8());

            for (ExpendMdseBean bean : childMdseList) {

                if (MDSE_TP.SET.equals(bean.getMdseTpCd())) {

                    // SET in SET
                    MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(lineBean.imptHdrBean.getGlblCmpyCd(), lineBean.mdseCd.getValue());
                    if (mdseTMsg == null) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(MDSETMsg.class, lineBean, lineBean.mdseCd.getValue());
                        lineBean.dsImptOrdErrList.add(errBean);
                        return false;
                    }

                    List<ExpendMdseBean> childChildMdseList = deriveChildMdse(lineBean, bean, mdseTMsg.mdseCd.getValue(), lineBean.imptHdrBean.getOrdSrcImptTs_Len8());

                    if (lineBean.isAutoAddSupply) {

                        for (ExpendMdseBean inSetMdseBean : childChildMdseList) {

                            // 2017/09/08 S21_NA#20992 Mod Start
                            if (!configBean.hasMdse(bean.getChildMdseCd(), true, lineBean.dsOrdLineCatgCd.getValue())) {

                                // 2017/09/08 S21_NA#20992 Mod End

                                lineBean.mdseAllList.add(inSetMdseBean);
                            }
                        }
                    } else {

                        lineBean.mdseAllList.addAll(childChildMdseList);
                    }
                } else {

                    // 2017/09/08 S21_NA#20992 Mod Start
                    if (lineBean.isAutoAddSupply && configBean.hasMdse(bean.getChildMdseCd(), true, lineBean.dsOrdLineCatgCd.getValue())) {

                        // 2017/09/08 S21_NA#20992 Mod End

                        continue;
                    }
                    lineBean.mdseAllList.add(bean);
                }
            }

            return true;
        } finally {
            writeEndLogLn("expandChildMdse", lineBean);
        }

    }

    private List<ExpendMdseBean> deriveChildMdse(DsImptLineBean lineBean, ExpendMdseBean parentBean, String prntMdseCd, String ordSrcImptTs) {

        writeStartLogLn("deriveChildMdse", prntMdseCd);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", lineBean.imptHdrBean.getGlblCmpyCd());
            ssmParam.put("prntMdseCd", prntMdseCd);
            ssmParam.put("ordSrcImptTs", ordSrcImptTs);
            List<Map<String, Object>> mdseList = NWZC226001Query.getInstance().queryMapList("getMdseForSet", ssmParam);

            List<ExpendMdseBean> expendMdseBeanList = new ArrayList<ExpendMdseBean>();
            for (int i = 0; i < mdseList.size(); i++) {

                Map<String, Object> mdse = mdseList.get(i);

                String mdseCd = convToString(mdse.get("MDSE_CD"));
                if (ZYPCommonFunc.hasValue(mdseCd)) {

                    MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(lineBean.imptHdrBean.getGlblCmpyCd(), mdseCd);
                    if (mdseTMsg != null) {

                        ExpendMdseBean expendMdseBean = new ExpendMdseBean(lineBean, parentBean);
                        expendMdseBean.setMdseCd(mdseCd);
                        expendMdseBean.setChildMdseCd(convToString(mdseTMsg.mdseCd.getValue()));
                        expendMdseBean.setChildMdseQty(convToBigDecimal(mdse.get("CHILD_MDSE_QTY")));
                        expendMdseBean.setMdseTpCd(convToString(mdseTMsg.mdseTpCd.getValue()));
                        expendMdseBeanList.add(expendMdseBean);
                    }
                }
            }

            return expendMdseBeanList;
        } finally {

            writeEndLogLn("deriveChildMdse", prntMdseCd);
        }
    }

    private boolean calcUomQty(DsImptLineBean lineBean) {

        writeStartLogLn("calcUomQty", lineBean);

        try {

            for (ExpendMdseBean bean : lineBean.mdseAllList) {

                BigDecimal inEachQty = getInEachQty(lineBean, bean.getChildMdseCd());
                if (inEachQty == null) {

                    return false;
                }
                bean.setCalcUomQty(bean.getCalcMdseQty().divide(inEachQty));
            }

            return true;
        } finally {

            writeEndLogLn("calcUomQty", lineBean);
        }
    }

    //private BigDecimal getInEachQty(DsImptLineBean lineBean, String mdseCd) {
    private BigDecimal getInEachQty(DsImptLineBean lineBean, String mdseCd) {

        writeStartLogLn("getInEachQty", lineBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            // 2018/06/05 S21_NA#26443 Mod Start
//            Object result;
            Map<String, Object> result;
            // 2018/06/05 S21_NA#26443 Mod End

            ssmParam.put("glblCmpyCd", lineBean.imptHdrBean.getGlblCmpyCd());
            ssmParam.put("pkgUomCd", lineBean.custUomCd.getValue());

            // START 2023/05/09 R.Azucena [QC#61420 MOD]
            // ssmParam.put("mdseCd", mdseCd);
            MDSETMsg mdseTMsg = getMdse(lineBean.imptHdrBean.getGlblCmpyCd(), mdseCd);

            if (mdseTMsg != null) {
                ssmParam.put("mdseCd", mdseTMsg.mdseCd.getValue());
            } else {
                ssmParam.put("mdseCd", mdseCd);
            }
            // END 2023/05/09 R.Azucena [QC#61420 MOD]

            // 2018/06/05 S21_NA#26443 Mod Start
//            result = NWZC226001Query.getInstance().queryBigDecimal("getEachQtyForUom", ssmParam);
            result = NWZC226001Query.getInstance().queryMap("getMdseStorePkgInfo", ssmParam);
            // 2018/06/05 S21_NA#26443 Mod End
            if (result == null) {

                // 2019/01/17 S21_NA#29942 Mod Start
                // 2019/01/25 S21_NA#29942 Add Start
                String custUomCd = lineBean.custUomCd.getValue();
                String itemCd = mdseCd;
                if (lineBean.imptHdrBean.isEdiData()) {
                    DS_IMPT_DTL_EXT_ATTRBTMsg tMsg = lineBean.dsImptDtlExtAttrbTMsg;
                    custUomCd = tMsg.idocPoDtlUomCd.getValue();
                    if (ZYPCommonFunc.hasValue(tMsg.idocPoDtlMatNum_02)) {
                        itemCd = tMsg.idocPoDtlMatNum_02.getValue();
                    } else {
                        itemCd = tMsg.idocPoDtlMatNum_01.getValue();
                    }
                }
                // 2019/01/25 S21_NA#29942 Add End
                //DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(lineBean, "MDSE_STORE_PKG", mdseCd);
                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableUomNotExistError(lineBean, custUomCd, itemCd);
                // 2019/01/17 S21_NA#29942 Mod End

                lineBean.dsImptOrdErrList.add(errBean);

                return null;
            }

            // 2018/06/05 S21_NA#26443 Mod Start
//            return convToBigDecimal(result);
            return convToBigDecimal(result.get("IN_EACH_QTY"));
            // 2018/06/05 S21_NA#26443 Mod End
        } finally {
            writeEndLogLn("getInEachQty", lineBean);
        }
    }

    private boolean deriveMdseSupplInfo(DsImptLineBean lineBean) {

        writeStartLogLn("deriveMdseSupplinfo", lineBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", lineBean.imptHdrBean.getGlblCmpyCd());
            ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP_MAIN_MACH);
            for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                ssmParam.put("mdseCd", mdseBean.getChildMdseCd());

                List<Map<String, Object>> mdseList = NWZC226001Query.getInstance().queryMapList("getMdseSupplInfo", ssmParam);

                if (mdseList == null || mdseList.size() == 0) {
                    continue;
                }

                Map<String, Object> mdse = mdseList.get(0);
                mdseBean.setInstlBaseCtrlFlg(convToString(mdse.get("INSTL_BASE_CTRL_FLG")));
                mdseBean.setMdseTpCtxTpCd(convToString(mdse.get("MDSE_TP_CTX_TP_CD")));
            }

            return true;
        } finally {

            writeEndLogLn("deriveMdseSupplinfo", lineBean);
        }
    }

    private boolean deriveMdseSupplInfoForRtn(DsImptRtnLineBean rtnBean) {

        writeStartLogLn("deriveMdseSupplInfoForRtn", rtnBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", rtnBean.imptHdrBean.getGlblCmpyCd());
            ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP_MAIN_MACH);
            ssmParam.put("mdseCd", rtnBean.mdseBean.getChildMdseCd());

            List<Map<String, Object>> mdseList = NWZC226001Query.getInstance().queryMapList("getMdseSupplInfo", ssmParam);

            if (!NWXC220001Util.hasValueList(mdseList)) {

                return false;
            }

            Map<String, Object> mdse = mdseList.get(0);
            rtnBean.mdseBean.setInstlBaseCtrlFlg(convToString(mdse.get("INSTL_BASE_CTRL_FLG")));
            rtnBean.mdseBean.setMdseTpCtxTpCd(convToString(mdse.get("MDSE_TP_CTX_TP_CD")));

            return true;
        } finally {

            writeEndLogLn("deriveMdseSupplInfoForRtn", rtnBean);
        }
    }

    private void setLineReferenceNumber(ImptHdrBean hdrBean) {

        writeStartLogLn("setLineReferenceNumber", hdrBean);

        try {

            int cpoDtlLineNum = NWXC220001Util.convZZ9ToNum(getMaxCpoDtlLineNum(hdrBean)) + 1;
            boolean isOrderRetailEquipment = isOrderRetailEquipment(hdrBean);
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                // *************************************************************
                // Set Line Number / Line Sub Number
                // *************************************************************
                int dsCpoLineNum = getMaxDsCpoLineNum(hdrBean, configBean.dsOrdPosnNum.getValue()) + 1;

                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                    // Skip Original
                    if (lineBean.isOrigCpoDtl) {
                        continue;
                    }

                    if (lineBean.isSetMdseTp()) {

                        // case : set merchandise

                        // Parent MDSE
                        ExpendMdseBean parentBean = lineBean.mdseAllList.get(0);

                        // DS CPO Line Number
                        parentBean.setDsCpoLineNum(BigDecimal.valueOf(dsCpoLineNum));

                        // CPO Detail Line Number
                        parentBean.setCpoDtlLineNum(NWXC220001Util.convNumToZ99(cpoDtlLineNum));
                        parentBean.setCpoDtlLineSubNum(NWXC220001Util.convNumToZ99(0));

                        int lineSubNum = 1;
                        for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                            if (mdseBean == parentBean) {
                                continue;
                            }

                            // DS CPO Line Number
                            mdseBean.setDsCpoLineNum(BigDecimal.valueOf(dsCpoLineNum));
                            mdseBean.setDsCpoLineSubNum(BigDecimal.valueOf(lineSubNum));

                            // CPO Detail Line Number
                            mdseBean.setCpoDtlLineNum(NWXC220001Util.convNumToZ99(cpoDtlLineNum));
                            mdseBean.setCpoDtlLineSubNum(NWXC220001Util.convNumToZ99(lineSubNum));

                            lineSubNum++;
                        }
                    } else {

                        // case : regular merchandise

                        ExpendMdseBean parentBean = lineBean.mdseAllList.get(0);

                        // DS CPO Line Number
                        parentBean.setDsCpoLineNum(BigDecimal.valueOf(dsCpoLineNum));

                        // CPO Detail Line Number
                        parentBean.setCpoDtlLineNum(NWXC220001Util.convNumToZ99(cpoDtlLineNum));
                        parentBean.setCpoDtlLineSubNum(NWXC220001Util.convNumToZ99(1));

                        // 2018/02/05 QC#23329 Add Start
                        int lineSubNum = 1;
                        for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                            if (mdseBean == parentBean) {
                                continue;
                            }

                            // DS CPO Line Number
                            dsCpoLineNum++;
                            mdseBean.setDsCpoLineNum(BigDecimal.valueOf(dsCpoLineNum));

                            // CPO Detail Line Number
                            cpoDtlLineNum++;
                            mdseBean.setCpoDtlLineNum(NWXC220001Util.convNumToZ99(cpoDtlLineNum));
                            mdseBean.setCpoDtlLineSubNum(NWXC220001Util.convNumToZ99(lineSubNum));

                            lineSubNum++;
                        }
                        // 2018/02/05 QC#23329 Add End
                    }
                    dsCpoLineNum++;
                    cpoDtlLineNum++;
                }

                boolean isReqBaseCmptFlg = NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(hdrBean.getGlblCmpyCd(), configBean.configTpCd.getValue());

                // *************************************************************
                // Derive Base Component
                // *************************************************************
                ExpendMdseBean baseComponent = null;
                long minLineNum = Long.MAX_VALUE;
                int lineNumCnt = 1;

                // derive from main machine
                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                    for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                        mdseBean.setLineNum(String.format("%s.%d", configBean.dsOrdPosnNum.getValue(), lineNumCnt));
                        if (ZYPCommonFunc.hasValue(mdseBean.getMdseTpCtxTpCd())) {

                            long targetLineNum = getOrdSrcRefLineNumToNumber(mdseBean.getDsImptLineBean().ordSrcRefLineNum.getValue(), Long.MAX_VALUE);
                            if (baseComponent == null || minLineNum > targetLineNum) {

                                targetLineNum = minLineNum;
                                baseComponent = mdseBean;
                            }
                        }

                        lineNumCnt++;
                    }
                }

                // 2017/08/14 S21_NA#20579 Mod Start
                // minLineNum = Long.MAX_VALUE;

                // derive from install base control
                if (baseComponent == null) {

                    for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                        for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                            if (ZYPConstant.FLG_ON_Y.equals(mdseBean.getInstlBaseCtrlFlg())) {

                                // long targetLineNum = getOrdSrcRefLineNumToNumber(mdseBean.getDsImptLineBean().ordSrcRefLineNum.getValue(), Long.MAX_VALUE);
                                // if (baseComponent == null || minLineNum > targetLineNum) {
                                //
                                //     targetLineNum = minLineNum;
                                //     baseComponent = mdseBean;
                                // }
                                if (baseComponent == null) {

                                    baseComponent = mdseBean;
                                }
                                // 2017/08/14 S21_NA#20579 Mod End
                            }
                        }
                    }
                }

                if (!isOrderRetailEquipment || !isReqBaseCmptFlg) {

                    baseComponent = null;
                }
                
                // 2018/10/25 S21_NA#28817 Add Start
                ExpendMdseBean tangibleItem = null;
                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {
                    for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {
                        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(hdrBean.getGlblCmpyCd(), mdseBean.getMdseCd());
                        if (mdseTMsg != null) {
                            if (S21StringUtil.isEquals(mdseTMsg.invtyCtrlFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                                tangibleItem = mdseBean;
                                break;
                            }
                        }
                    }
                    if(tangibleItem != null){
                        break;
                    }
                }
                // 2018/10/25 S21_NA#28817 Add End

                // *************************************************************
                // Set Line Reference Number
                // *************************************************************
                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                    // Skip Original
                    if (lineBean.isOrigCpoDtl) {
                        continue;
                    }

                    for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                        if (baseComponent == null) {
                            // 2018/10/25 S21_NA#28817 Add Start
                            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(hdrBean.getGlblCmpyCd(), mdseBean.getMdseCd());
                            if (mdseTMsg != null) {
                                if (isOrderRetailEquipment && tangibleItem != mdseBean && tangibleItem != null) {
                                    mdseBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
                                    // Reference CPO Detail Line Number
                                    mdseBean.setRefCpoDtlLineNum(tangibleItem.getCpoDtlLineNum());
                                    mdseBean.setRefCpoDtlLineSubNum(tangibleItem.getCpoDtlLineSubNum());
                                    continue;
                                }
                            }
                            // 2018/10/25 S21_NA#28817 Add End

                            // non reference line
                            mdseBean.setRefCpoDtlLineNum("");
                            mdseBean.setRefCpoDtlLineSubNum("");
                            mdseBean.setDplyLineRefNum("");
                            mdseBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
                            continue;
                        }

                        if (mdseBean == baseComponent) {

                            // non reference line
                            mdseBean.setRefCpoDtlLineNum("");
                            mdseBean.setRefCpoDtlLineSubNum("");
                            mdseBean.setDplyLineRefNum("");
                            mdseBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                        } else {

                            mdseBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
                            if (baseComponent != null && ZYPCommonFunc.hasValue(hdrBean.getOrdCatgCtxTpCd())) {

                                // Reference CPO Detail Line Number
                                mdseBean.setRefCpoDtlLineNum(baseComponent.getCpoDtlLineNum());
                                mdseBean.setRefCpoDtlLineSubNum(baseComponent.getCpoDtlLineSubNum());
                            }
                        }
                    }
                }

            }

        } finally {

            writeEndLogLn("setLineReferenceNumber", hdrBean);
        }
    }

    private void setRtnLineReferenceNumber(ImptHdrBean hdrBean) {

        writeStartLogLn("setRtnLineReferenceNumber", hdrBean);

        try {

            int dsCpoRtrnLineNum = NWXC220001Util.convZZ9ToNum(getMaxDsCpoRtrnLineNum(hdrBean)) + 1;
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isOutbound()) {
                    continue;
                }

                // *************************************************************
                // Set Line Number / Line Sub Number
                // *************************************************************
                int dsCpoLineNum = 1;

                for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                    // Skip Original
                    if (rtnBean.isOrigRtnDtl) {
                        continue;
                    }

                    ExpendMdseBean parentBean = rtnBean.mdseBean;

                    // DS CPO Line Number
                    parentBean.setDsCpoLineNum(BigDecimal.valueOf(dsCpoLineNum));

                    // CPO Detail Line Number
                    parentBean.setCpoDtlLineNum(NWXC220001Util.convNumToZ99(dsCpoRtrnLineNum));
                    parentBean.setCpoDtlLineSubNum(NWXC220001Util.convNumToZ99(1));
                    dsCpoLineNum++;
                    dsCpoRtrnLineNum++;
                }

                // *************************************************************
                // Derive Base Component
                // *************************************************************
                ExpendMdseBean baseComponent = null;
                long minLineNum = Long.MAX_VALUE;
                int lineNumCnt = 1;

                // derive from main machine
                for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                    ExpendMdseBean mdseBean = rtnBean.mdseBean;
                    mdseBean.setLineNum(String.format("%s.%d", configBean.dsOrdPosnNum.getValue(), lineNumCnt));
                    if (ZYPCommonFunc.hasValue(mdseBean.getMdseTpCtxTpCd())) {

                        long targetLineNum = getOrdSrcRefLineNumToNumber(mdseBean.getDsImptRtnLineBean().ordSrcRefLineNum.getValue(), Long.MAX_VALUE);
                        if (baseComponent == null || minLineNum > targetLineNum) {

                            targetLineNum = minLineNum;
                            baseComponent = mdseBean;
                        }
                    }
                    lineNumCnt++;
                }

                // 2017/08/14 S21_NA#20579 Mod Start
                // minLineNum = Long.MAX_VALUE;

                // derive from install base control
                if (baseComponent == null) {

                    for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                        ExpendMdseBean mdseBean = rtnBean.mdseBean;
                        if (ZYPConstant.FLG_ON_Y.equals(mdseBean.getInstlBaseCtrlFlg())) {

                            // long targetLineNum = getOrdSrcRefLineNumToNumber(mdseBean.getDsImptRtnLineBean().ordSrcRefLineNum.getValue(), Long.MAX_VALUE);
                            // if (baseComponent == null || minLineNum > targetLineNum) {
                            //
                            //     targetLineNum = minLineNum;
                            //     baseComponent = mdseBean;
                            // }
                            if (baseComponent == null) {

                                baseComponent = mdseBean;
                            }
                            // 2017/08/14 S21_NA#20579 Mod End
                        }
                    }
                }

                // *************************************************************
                // Set Line Reference Number
                // *************************************************************
                for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                    if (rtnBean.isOrigRtnDtl) {
                        continue;
                    }

                    ExpendMdseBean mdseBean = rtnBean.mdseBean;
                    if (baseComponent == null) {

                        // non reference line
                        mdseBean.setRefCpoDtlLineNum("");
                        mdseBean.setRefCpoDtlLineSubNum("");
                        mdseBean.setDplyLineRefNum("");
                        mdseBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
                        continue;
                    }

                    if (mdseBean == baseComponent) {

                        // non reference line
                        mdseBean.setRefCpoDtlLineNum("");
                        mdseBean.setRefCpoDtlLineSubNum("");
                        mdseBean.setDplyLineRefNum("");
                        mdseBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                    } else {

                        mdseBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
                        if (baseComponent != null && ZYPCommonFunc.hasValue(hdrBean.getOrdCatgCtxTpCd())) {

                            // Reference CPO Detail Line Number
                            mdseBean.setRefCpoDtlLineNum(baseComponent.getCpoDtlLineNum());
                            mdseBean.setRefCpoDtlLineSubNum(baseComponent.getCpoDtlLineSubNum());
                        }
                    }
                }
            }

        } finally {

            writeEndLogLn("setRtnLineReferenceNumber", hdrBean);
        }
    }

    private static long getOrdSrcRefLineNumToNumber(String ordSrcRefLineNum, long defVal) {

        if (!ZYPCommonFunc.hasValue(ordSrcRefLineNum)) {

            return defVal;
        }

        if (isNumber(ordSrcRefLineNum)) {

            return Long.valueOf(ordSrcRefLineNum);
        }

        long retVal = 0;
        char[] chrAry = ordSrcRefLineNum.toCharArray();

        for (int i = 0; i < chrAry.length; i++) {

            if (chrAry[i] >= '0' && chrAry[i] <= '9') {

                retVal = retVal * 10 + (chrAry[i] - '0');
            }
        }

        return retVal;
    }

    private static boolean isNumber(String val) {

        String regex = "\\A[-]?[0-9]+\\z";
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(val);
        return m1.find();
    }

    private boolean deriveReturnLineData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveReturnLineData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);

            List<Map<String, Object>> imptRtnLineList = new ArrayList<Map<String, Object>>(0);
            if (hdrBean.isLoanDowngrade() || !hdrBean.isLoanConversion()) {
                imptRtnLineList = NWZC226001Query.getInstance().queryMapList("getReturnLineData", ssmParam);
            }

            String dsOrdPosnNum = getMaxDsOrdPosnNum(hdrBean, CONFIG_CATG.INBOUND);
            BigDecimal configPk = null;

            // Add Start 2017/06/16 QC#19071
            boolean errFlg = false;
            // Add End 2017/06/16 QC#19071
            for (int i = 0; i < imptRtnLineList.size(); i++) {
                Map<String, Object> imptRtnMap = imptRtnLineList.get(i);
                BigDecimal dsImptOrdConfigPk = convToBigDecimal(imptRtnMap.get("DS_IMPT_ORD_CONFIG_PK"));
                // BigDecimal dsImptOrdRtrnDtlPk = convToBigDecimal(imptRtnMap.get("DS_IMPT_ORD_RTRN_DTL_PK")); // 2018/06/12 S21_NA#24294 Del

                DS_IMPT_ORD_RTRN_DTLTMsg tMsg = new DS_IMPT_ORD_RTRN_DTLTMsg();
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptRtnMap, tMsg);
                // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                // ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdRtrnDtlPk, dsImptOrdRtrnDtlPk);
                // tMsg = (DS_IMPT_ORD_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
                // checkTMsgDbAccess(tMsg);
                // 2018/06/12 S21_NA#24294 Mod End
                if (tMsg == null) {

                    continue;
                }

                // 2017/03/30 S21_NA#18180 Mod Start
                if (hdrBean.isLoanDowngrade() || !hdrBean.isLoanConversion()) {
                // 2017/03/30 S21_NA#18180 Mod End
                    if (!ZYPCommonFunc.hasValue(configPk) || dsImptOrdConfigPk.compareTo(configPk) != 0) {
                        dsOrdPosnNum = getNextDsOrdPosnNum(dsOrdPosnNum);
                        configPk = dsImptOrdConfigPk;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdPosnNum, dsOrdPosnNum);
                }

                DsImptRtnLineBean rtnLineBean = new DsImptRtnLineBean(hdrBean, tMsg);
                hdrBean.addDsImptRtnLine(rtnLineBean);

                // 2017/03/30 S21_NA#18175 Add Start
                if (!deriveMdseInfoForRtn(rtnLineBean)) {
                    // Mod Start 2017/06/16 QC#19071
//                    return false;
                    errFlg = true;
                    // Mod End 2017/06/16 QC#19071
                }
                // 2017/03/30 S21_NA#18175 Add End
            }

            // Add Start 2017/06/16 QC#19071
            if (errFlg) {
                return false;
            }
            // Add ENd 2017/06/16 QC#19071

            if (!autoAddRma(onBatchType, hdrBean)) {
                return false;
            }
            // 2017/04/24 S21_NA#18286-2 Mod End

            for (DsImptRtnLineBean rtnBean : hdrBean.getDsImptRtnLineList()) {

                deriveMdseInfoForRtn(rtnBean);
                // *************************************************************
                // Expand Set MDSE
                // *************************************************************
                ExpendMdseBean expendMdseBean = new ExpendMdseBean(rtnBean, null);
                expendMdseBean.setMdseCd(rtnBean.mdseCd.getValue());
                expendMdseBean.setChildMdseCd(rtnBean.mdseInfo.mdseCd.getValue());
                expendMdseBean.setChildMdseQty(BigDecimal.ONE);
                expendMdseBean.setMdseTpCd(rtnBean.mdseInfo.mdseTpCd.getValue());

                expendMdseBean.setCpoDtlLineNum(rtnBean.dsCpoRtrnLineNum);
                expendMdseBean.setCpoDtlLineSubNum(rtnBean.dsCpoRtrnLineSubNum);
                expendMdseBean.setDsCpoLineNum(rtnBean.dsCpoLineNum);
                expendMdseBean.setDsCpoLineSubNum(rtnBean.dsCpoLineSubNum);
                expendMdseBean.setRefCpoDtlLineNum(rtnBean.refCpoDtlLineNum);
                expendMdseBean.setRefCpoDtlLineSubNum(rtnBean.refCpoDtlLineSubNum);
                expendMdseBean.setDplyLineRefNum(rtnBean.dplyLineRefNum);
                expendMdseBean.setBaseCmptFlg(rtnBean.baseCmptFlg.getValue());

                rtnBean.mdseBean = expendMdseBean;

                // *************************************************************
                // Derive MDSE Supply Info
                // *************************************************************
                deriveMdseSupplInfoForRtn(rtnBean);

                // *************************************************************
                // Derive ccyCd
                // *************************************************************
                if (!ZYPCommonFunc.hasValue(rtnBean.ccyCd)) {

                    ZYPEZDItemValueSetter.setValue(rtnBean.ccyCd, deriveCcyCdByPrcCatgCd(hdrBean.getGlblCmpyCd(), rtnBean.prcCatgCd.getValue()));
                }
            }

            // *****************************************************************
            // Set Line Reference Number
            // *****************************************************************
            setRtnLineReferenceNumber(hdrBean);

            return true;

        } finally {

            writeEndLogLn("deriveReturnLineData", hdrBean);
        }
    }

    private boolean autoAddRma(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("autoAddRma", hdrBean);

        try {

            HashMap<BigDecimal, List<NWZC182001PMsg>> apiMap = new HashMap<BigDecimal, List<NWZC182001PMsg>>();
            List<NWZC182001PMsg> apiMsgList = new ArrayList<NWZC182001PMsg>();
            Map<BigDecimal, List<String>> exstConfigMdseSerMap = new HashMap<BigDecimal, List<String>>();
            List<NWZC182001PMsg> apiConfigList = new ArrayList<NWZC182001PMsg>();

            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (!CONFIG_CATG.INBOUND.equals(configBean.configCatgCd.getValue()) || hdrBean.isLoanConversion()) {
                    continue;
                }

                boolean rslt = NWXC150001DsCheck.matchConfigTp(hdrBean.getGlblCmpyCd(), configBean.configTpCd.getValue(), CONFIG_CATG.INBOUND, false, true, false);
                if (!rslt) {

                    // skip new
                    continue;
                }

                // 2017/04/24 S21_NA#18286-2 Add Start
                if (ignoreAutoAddRma(configBean)) {
                    continue;
                }
                // 2017/04/24 S21_NA#18286-2 Add End

                if (configBean.isUpdateData()) {

                    continue;
                }

                setBaseComponent(configBean);

                BigDecimal configPk = configBean.dsImptOrdConfigPk.getValue();
                // *************************************************************
                // Set Configuration Level Parameters
                // *************************************************************
                if (ZYPCommonFunc.hasValue(configBean.svcConfigMstrPk)) {

                    NWZC182001PMsg apiMsg = new NWZC182001PMsg();

                    ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(apiMsg.svcConfigMstrPk, configBean.svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
                    ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());

                    apiMsgList.add(apiMsg);

                    if (!apiMap.containsKey(configPk)) {

                        apiConfigList = new ArrayList<NWZC182001PMsg>();
                        apiMap.put(configPk, apiConfigList);
                    }
                    apiConfigList = apiMap.get(configPk);
                    apiConfigList.add(apiMsg);
                }

                // *************************************************************
                // Set Return Line Level Parameters
                // *************************************************************
                exstConfigMdseSerMap.put(configPk, new ArrayList<String>());

                for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                    // 2017/03/02 QC#16575 UPD START
                    // if (ZYPCommonFunc.hasValue(rtnBean.serNum)) {
                    if (ZYPCommonFunc.hasValue(rtnBean.serNum) && ZYPCommonFunc.hasValue(rtnBean.mdseCd)) {
                        // 2017/03/02 QC#16575 UPD E N D

                        NWZC182001PMsg apiMsg = new NWZC182001PMsg();
                        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                        ZYPEZDItemValueSetter.setValue(apiMsg.serNum, rtnBean.serNum);
                        // 2017/03/02 QC#16575 ADD START
                        ZYPEZDItemValueSetter.setValue(apiMsg.mdseCd, rtnBean.mdseCd);
                        // 2017/03/02 QC#16575 ADD E N D
                        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
                        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());

                        apiMsgList.add(apiMsg);

                        if (!apiMap.containsKey(configPk)) {

                            apiConfigList = new ArrayList<NWZC182001PMsg>();
                            apiMap.put(configPk, apiConfigList);
                        }
                        apiConfigList = apiMap.get(configPk);
                        apiConfigList.add(apiMsg);

                        // 2017/08/01 S21_NA#19413 Del Start
                        // String mdseSerKey = String.format("%s@%s", rtnBean.mdseCd.getValue(), rtnBean.serNum.getValue());
                        // exstConfigMdseSerMap.get(configPk).add(mdseSerKey);
                        // 2017/08/01 S21_NA#19413 Del End
                    }
                    // 2017/08/01 S21_NA#19413 Add Start
                    MDSETMsg rtnBeanMdseMsg = getMdse(hdrBean.getGlblCmpyCd(), rtnBean.mdseCd.getValue());
                    // 2018/04/27 S21_NA#22189 Mod Start
//                    String mdseSerKey = String.format("%s@%s", rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue());
                    if (!ZYPCommonFunc.hasValue(rtnBean.svcMachMstrPk)) {
                        BigDecimal svcMachMstrPk = getSvcMachMstrPkByMdseSerNum(hdrBean.getGlblCmpyCd(), rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue());
                        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                            rtnBean.svcMachMstrPk.setValue(svcMachMstrPk);
                        }
                    }
                    String mdseSerKey = getRmaAutoAddKey(rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue(), rtnBean.svcMachMstrPk.getValue());
                    // 2018/04/27 S21_NA#22189 Mod End
                    exstConfigMdseSerMap.get(configPk).add(mdseSerKey);
                    // 2017/08/01 S21_NA#19413 Add Start

                }
            }

            boolean isSuccess = true;
            if (apiMsgList.size() > 0) {

                new NWZC182001().execute(apiMsgList, onBatchType);
                for (Map.Entry<BigDecimal, List<NWZC182001PMsg>> result : apiMap.entrySet()) {

                    Map<String, NWZC182002PMsg> addNwzc18200PMsgMap = new HashMap<String, NWZC182002PMsg>();

                    BigDecimal configPk = result.getKey();
                    DsImptOrdConfigBean targetConfigBean = hdrBean.dsImptOrdConfigMap.get(configPk);

                    List<NWZC182001PMsg> resultMsgList = result.getValue();

                    // Overwrite Serial#
                    boolean doBreak = false;
                    for (DsImptRtnLineBean rtnBean : targetConfigBean.dsImptRtnLineList) {

                        if (!S21StringUtil.isEquals(rtnBean.baseCmptFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                            continue;
                        }
                        if (ZYPCommonFunc.hasValue(rtnBean.serNum)) {

                            break;
                        }
                        // 2017/08/01 S21_NA#19413 Add Start
                        MDSETMsg rtnBeanMdseMsg = getMdse(hdrBean.getGlblCmpyCd(), rtnBean.mdseCd.getValue());
                        // 2017/08/01 S21_NA#19413 Add End
                        for (NWZC182001PMsg resultMsg : resultMsgList) {

                            for (int i = 0; i < resultMsg.NWZC182002PMsg.getValidCount(); i++) {

                                NWZC182002PMsg rmaApiMsg = resultMsg.NWZC182002PMsg.no(i);
                                // 2017/08/01 S21_NA#19413 Mod Start
                                // if (ZYPCommonFunc.hasValue(rmaApiMsg.serNum) && S21StringUtil.isEquals(rtnBean.mdseCd.getValue(), rmaApiMsg.mdseCd.getValue())) {
                                if (ZYPCommonFunc.hasValue(rmaApiMsg.serNum) && S21StringUtil.isEquals(rtnBeanMdseMsg.mdseCd.getValue(), rmaApiMsg.mdseCd.getValue())) {
                                // 2017/08/01 S21_NA#19413 Mod End

                                    // Overwrite Serial#
                                    ZYPEZDItemValueSetter.setValue(rtnBean.serNum, rmaApiMsg.serNum);
                                    // Add Exist List
                                    // 2018/04/27 S21_NA#22189 Del Start
//                                    // 2017/08/01 S21_NA#19413 Mod Start
//                                    // String mdseSerKey = String.format("%s@%s", rtnBean.mdseCd.getValue(), rtnBean.serNum.getValue());
//                                    String mdseSerKey = String.format("%s@%s", rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue());
//                                    // 2017/08/01 S21_NA#19413 Mod End
                                    // 2018/04/27 S21_NA#22189 Del End
                                    // 2018/04/27 S21_NA#22189 Add Start
                                    if (!ZYPCommonFunc.hasValue(rtnBean.svcMachMstrPk)) {
                                        BigDecimal svcMachMstrPk = getSvcMachMstrPkByMdseSerNum(hdrBean.getGlblCmpyCd(), rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue());
                                        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                                            rtnBean.svcMachMstrPk.setValue(svcMachMstrPk);
                                        }
                                    }
                                    String mdseSerKey = getRmaAutoAddKey(rtnBeanMdseMsg.mdseCd.getValue(), rtnBean.serNum.getValue(), rtnBean.svcMachMstrPk.getValue());
                                    // 2018/04/27 S21_NA#22189 Add End
                                    exstConfigMdseSerMap.get(configPk).add(mdseSerKey);
                                    doBreak = true;
                                    break;
                                }
                            }
                            if (doBreak) {

                                break;
                            }
                        }
                        break;
                    }

                    // Log Write (RMA Exist Mdse@Serial# List)
                    writeLogLn("RMA Exist Mdse@Serial# List");
                    for (String key : exstConfigMdseSerMap.get(configPk)) {

                        writeLogLn("    ConfigPk : %.0f, MdseCd@Ser# : %s", configPk, key);
                    }

                    for (NWZC182001PMsg resultMsg : resultMsgList) {

                        // Config level
                        if (S21ApiUtil.getXxMsgList(resultMsg).size() > 0) {

                            isSuccess = false;
                            continue;
                        }

                        if (!ZYPCommonFunc.hasValue(targetConfigBean.svcConfigMstrPk)) {

                            ZYPEZDItemValueSetter.setValue(targetConfigBean.svcConfigMstrPk, resultMsg.svcConfigMstrPk);
                        }

                        for (int i = 0; i < resultMsg.NWZC182002PMsg.getValidCount(); i++) {

                            // Detail Level
                            NWZC182002PMsg rmaApiMsg = resultMsg.NWZC182002PMsg.no(i);
                            if (ZYPCommonFunc.hasValue(rmaApiMsg.svcMachMstrPk) || ZYPCommonFunc.hasValue(rmaApiMsg.serNum)) {
                                // 2018/04/27 S21_NA#22189 Mod Start
//                                String mdseSerKey = String.format("%s@%s", rmaApiMsg.mdseCd.getValue(), rmaApiMsg.serNum.getValue());
                                String mdseSerKey = getRmaAutoAddKey(rmaApiMsg.mdseCd.getValue(), rmaApiMsg.serNum.getValue(), rmaApiMsg.svcMachMstrPk.getValue());
                                // 2018/04/27 S21_NA#22189 Mod End
                                if (!exstConfigMdseSerMap.get(result.getKey()).contains(mdseSerKey) && !addNwzc18200PMsgMap.containsKey(mdseSerKey)) {

                                    // When Not Contents MDSE/SER Key
                                    addNwzc18200PMsgMap.put(mdseSerKey, rmaApiMsg);
                                } else {

                                    List<DsImptRtnLineBean> exitRtnBeanList = targetConfigBean.findRtnBeanListByMdseCdAndSerNum(rmaApiMsg.mdseCd.getValue(), rmaApiMsg.serNum.getValue());
                                    for (DsImptRtnLineBean rtnBean : exitRtnBeanList) {

                                        ZYPEZDItemValueSetter.setValue(rtnBean.svcConfigMstrPk, resultMsg.svcConfigMstrPk);
                                        ZYPEZDItemValueSetter.setValue(rtnBean.svcMachMstrPk, rmaApiMsg.svcMachMstrPk);
                                    }
                                }
                            }
                        }
                    }

                    if (!isSuccess) {

                        continue;
                    }

                    writeLogLn("Auto Add RMA Count: ConfigPK : %.0f => %d", targetConfigBean.dsImptOrdConfigPk.getValue(), addNwzc18200PMsgMap.size());
                    DsImptRtnLineBean baseComponentline = getBaseComponent(targetConfigBean); // QC#24093 2018/02/19 Add
                    setRtrnWh4BaseComp(onBatchType, baseComponentline); // 2019/03/17 S21_NA#30770 Add
                    for (NWZC182002PMsg addRmaMsg : addNwzc18200PMsgMap.values()) {

                        writeLogLn("    svcMachMstrPk : %.0f, MdseCd : %s, Ser# : %s", addRmaMsg.svcMachMstrPk.getValue(), addRmaMsg.mdseCd.getValue(), addRmaMsg.serNum.getValue());
                        // QC#24093 2018/02/19 Mod Start
                        //addAutoAddRma(onBatchType, targetConfigBean, addRmaMsg);
                        addAutoAddRma(onBatchType, targetConfigBean, baseComponentline, addRmaMsg);
                        // QC#24093 2018/02/19 Mod End
                    }
                }
            }

            return true;
        } finally {

            writeEndLogLn("autoAddRma", hdrBean);
        }
    }

    // 2017/04/24 S21_NA#18286-2 Add Start
    private boolean ignoreAutoAddRma(DsImptOrdConfigBean configBean) {

        ImptHdrBean hdrBean = configBean.imptHdrBean;
        if (hdrBean == null) {
            return false;
        }

        // Deal Config Loan Downgrade
        if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd()) && hdrBean.isLoanDowngrade()) {
            return true;
        }

        // IB Trackable and Main Machine
        if (isMainMachInIB(configBean)) {
            return false;
        }

        // Del Start 2017/10/03 QC#20020
        // Only Accessory(s) exist or not
//        ALL_MDSE_VTMsg tMsg = new ALL_MDSE_VTMsg();
//        ALL_MDSE_VTMsgArray tMsgArray = null;
//
//        for (DsImptRtnLineBean rtnBean : hdrBean.getDsImptRtnLineList()) {
//            tMsg = new ALL_MDSE_VTMsg();
//            tMsg.setSQLID("003");
//            tMsg.setConditionValue("glblCmpyCd01", rtnBean.imptHdrBean.getGlblCmpyCd());
//            tMsg.setConditionValue("mdseCd01", rtnBean.mdseCd.getValue());
//
//            tMsgArray = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
//            if (tMsgArray != null && tMsgArray.length() > 0) {
//                tMsg = (ALL_MDSE_VTMsg) tMsgArray.get(0);
//                if (!MDSE_ITEM_TP.ACCESSORY.equals(tMsg.mdseItemTpCd.getValue())) {
//                    return false;
//                }
//            }
//        }
        // Del End 2017/10/03 QC#20020
        return true;
    }

    private boolean isMainMachInIB(DsImptOrdConfigBean configBean) {
        // Config Type Code
        if (!ZYPCommonFunc.hasValue(configBean.configTpCd) || !CONFIG_TP.RETURN_EXISTING_IB.equals(configBean.configTpCd.getValue())) {
            return false;
        }


        // Base Component Flag
        List<String> mdseList = new ArrayList<String>();

        for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {
            mdseList.add(rtnBean.mdseCd.getValue());
        }

        String baseComponent = NWXC220001.getBaseComponentMdseCd(configBean.imptHdrBean.getGlblCmpyCd(), mdseList);
        if (ZYPCommonFunc.hasValue(baseComponent)) {

            // 2017/08/01 S21_NA#19413 Add Start
            if (!this.isMainMachMdse(configBean, baseComponent)) {
                return false;
            }
            // 2017/08/01 S21_NA#19413 Add End

            for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                if (S21StringUtil.isEquals(baseComponent, rtnBean.mdseCd.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }
    // 2017/04/24 S21_NA#18286-2 Add End


    private void setBaseComponent(DsImptOrdConfigBean configBean) {

        List<String> mdseList = new ArrayList<String>();

        for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

            mdseList.add(rtnBean.mdseCd.getValue());
        }

        String baseComponent = NWXC220001.getBaseComponentMdseCd(configBean.imptHdrBean.getGlblCmpyCd(), mdseList);
        if (ZYPCommonFunc.hasValue(baseComponent)) {

            for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                if (S21StringUtil.isEquals(baseComponent, rtnBean.mdseCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(rtnBean.baseCmptFlg, ZYPConstant.FLG_ON_Y);
                } else {

                    ZYPEZDItemValueSetter.setValue(rtnBean.baseCmptFlg, ZYPConstant.FLG_OFF_N);
                }
            }
        }
    }

    // QC#24093 2018/02/19 Add Start
    private DsImptRtnLineBean getBaseComponent(DsImptOrdConfigBean configBean) {
        for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {
            if(ZYPConstant.FLG_ON_Y.equals(rtnBean.baseCmptFlg.getValue())){
                return rtnBean;
            }
        }
        return null;
    }
    // QC#24093 2018/02/19 Add End

    //private boolean addAutoAddRma(ONBATCH_TYPE onBatchType, DsImptOrdConfigBean configBean, NWZC182002PMsg addRmaMsg) { // QC#24093 2018/02/19 Mod
    private boolean addAutoAddRma(ONBATCH_TYPE onBatchType, DsImptOrdConfigBean configBean, DsImptRtnLineBean baseComponentline, NWZC182002PMsg addRmaMsg) {

        writeStartLogLn("addAutoAddRma", configBean);

        try {

            boolean isSuccess = true;
            ImptHdrBean hdrBean = configBean.imptHdrBean;
            DS_IMPT_ORD_RTRN_DTLTMsg rtrnDtlMsg = new DS_IMPT_ORD_RTRN_DTLTMsg();

            EZDMsg.copy(configBean, "", rtrnDtlMsg, "");

            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.shipToCustCd, configBean.shipToCustLocCd);
            // QC#24093 2018/02/19 Mod Start
            // ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.prcCatgCd, hdrBean.getPrcCatgCd());
            if (baseComponentline == null) {
                ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.prcCatgCd, hdrBean.getPrcCatgCd());
            } else {
                ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.prcCatgCd, baseComponentline.prcCatgCd);
            }
            // QC#24093 2018/02/19 Mod End
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.mdseCd, addRmaMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.serNum, addRmaMsg.serNum);
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.ordCustUomQty, BigDecimal.ONE.negate());
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.ordQty, BigDecimal.ONE.negate());
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.svcConfigMstrPk, configBean.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(rtrnDtlMsg.svcMachMstrPk, addRmaMsg.svcMachMstrPk);

            DsImptRtnLineBean rtnLineBean = new DsImptRtnLineBean(hdrBean, rtrnDtlMsg);
            hdrBean.addDsImptRtnLine(rtnLineBean);

            rtnLineBean.isAutoAddRMA = true;
            if (!deriveMdseInfoForRtn(rtnLineBean)) {
                return false;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            params.put("mdseCd", addRmaMsg.mdseCd.getValue());
            params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            params.put("isCalledMdl", String.valueOf(false));
            List<Map<String, Object>> mdseList = NWZC226001Query.getInstance().queryMapList("getMdseInfo", params);
            if (!NWXC220001Util.hasValueList(mdseList)) {

                return false;
            }

            Map<String, Object> mdse = mdseList.get(0);
            ZYPEZDItemValueSetter.setValue(rtnLineBean.custUomCd, (String) mdse.get("PKG_UOM_CD"));

            NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(hdrBean.getGlblCmpyCd(), rtnLineBean.imptHdrBean.getDsOrdTpCd(), hdrBean.getSlsDt(), DS_ORD_LINE_DRCTN.INBOUND);
            if (result.hasResultObject()) {

                ZYPEZDItemValueSetter.setValue(rtnLineBean.dsCpoLineCatgCd, (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD"));
            } else {

                isSuccess = false;
            }

            // Mod Start 2018/03/15 QC#24258
//            callDefWhApiForRtnLineConf(onBatchType, rtnLineBean);
            callDefWhApiForRtnLineConf(onBatchType, rtnLineBean, baseComponentline, hdrBean.getCpoSrcTpCd());
            // Mod End 2018/03/15 QC#24258

            // QC#24093 2018/02/19 Mod Start
            // ZYPEZDItemValueSetter.setValue(rtnLineBean.flPrcListCd, hdrBean.getFlPrcListCd());
            if (baseComponentline == null) {
                ZYPEZDItemValueSetter.setValue(rtnLineBean.flPrcListCd, hdrBean.getFlPrcListCd());
            } else {
                ZYPEZDItemValueSetter.setValue(rtnLineBean.flPrcListCd, baseComponentline.flPrcListCd);
            }
            // QC#24093 2018/02/19 Mod End
            ZYPEZDItemValueSetter.setValue(rtnLineBean.prcBaseDt, hdrBean.getSlsDt());
            // ZYPEZDItemValueSetter.setValue(rtnLineBean.rqstPickUpDt, hdrBean.getSlsDt()); // 2017/08/25 S21_NA#20740-1 Del

            return isSuccess;

        } finally {

            writeEndLogLn("addAutoAddRma", configBean);
        }
    }

    private boolean deriveServiceShellData(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveServiceShellData", hdrBean);

        try {

            // 2017/12/06 S21_NA#22794 Add Start
            if(isOrderInbound(hdrBean)) {
                return true;
            }
            // 2017/12/06 S21_NA#22794 Add End
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            // 2018/01/23 QC#18798 Add Start
            List<BigDecimal> dsImptOrdDtlPkList = new ArrayList<BigDecimal>();
            if (ZYPConstant.FLG_ON_Y.equals(dsImptOrdConfigFlg)) {
                for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                    // Out-bound
                    if (CONFIG_CATG.OUTBOUND.equals(confBean.configCatgCd.getValue())) {
                        for (DsImptLineBean lineBean : confBean.dsImptOrdLineList) {
                            BigDecimal dsImptOrdDtlPk = lineBean.dsImptOrdDtlPk.getValue();
                            if (dsImptOrdDtlPk == null) {
                                continue;
                            }
                            dsImptOrdDtlPkList.add(dsImptOrdDtlPk);
                        }
                    // In-bound
                    } else if (CONFIG_CATG.INBOUND.equals(confBean.configCatgCd.getValue())) {
                        for (DsImptRtnLineBean rtnLineBean : confBean.dsImptRtnLineList) {
                            BigDecimal dsImptOrdRtrnDtlPk = rtnLineBean.dsImptOrdRtrnDtlPk.getValue();
                            if (dsImptOrdRtrnDtlPk == null) {
                                continue;
                            }
                            dsImptOrdDtlPkList.add(dsImptOrdRtrnDtlPk);
                        }
                    }
                }
            }
            if (dsImptOrdDtlPkList.size() > 0) {
                ssmParam.put("dsImptOrdDtlPkList", dsImptOrdDtlPkList);
            }
            // 2018/01/23 QC#18798 Add End

            List<Map<String, Object>> imptSvcDtlList = NWZC226001Query.getInstance().queryMapList("getDsImptSvcDtl", ssmParam);
            if (!hasValueList(imptSvcDtlList)) {
                return true;
            }

            for (int i = 0; i < imptSvcDtlList.size(); i++) {

                // 2018/06/12 S21_NA#24294 Mod Start
                // BigDecimal svcDtlPk = convToBigDecimal(imptSvcDtlList.get(i));
                Map<String, Object> imptSvcDtlMap = imptSvcDtlList.get(i);
                // 2018/06/12 S21_NA#24294 Mod End

                // *************************************************************
                // DS_IMPT_SVC_DTL
                // *************************************************************
                DS_IMPT_SVC_DTLTMsg svcDtlMsg = new DS_IMPT_SVC_DTLTMsg();
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptSvcDtlMap, svcDtlMsg);
                // ZYPEZDItemValueSetter.setValue(svcDtlMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                // ZYPEZDItemValueSetter.setValue(svcDtlMsg.dsImptSvcDtlPk, svcDtlPk);
                // svcDtlMsg = (DS_IMPT_SVC_DTLTMsg) S21ApiTBLAccessor.findByKey(svcDtlMsg);
                // if (svcDtlMsg != null) {
                if (svcDtlMsg != null && ZYPCommonFunc.hasValue(svcDtlMsg.dsImptSvcDtlPk)) {
                // 2018/06/12 S21_NA#24294 Mod End

                    DsImptSvcDtlBean svcDtlBean = new DsImptSvcDtlBean(hdrBean, svcDtlMsg);
                    hdrBean.getDsImptSvcDtlList().add(svcDtlBean);
                    BigDecimal svcDtlPk = svcDtlMsg.dsImptSvcDtlPk.getValue();

                    // *********************************************************
                    // DS_IMPT_SVC_CONFIG_REF
                    // *********************************************************
                    ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                    ssmParam.put("dsImptSvcDtlPk", svcDtlPk);
                    // 2018/01/23 QC#18798 Add Start
                    if (dsImptOrdDtlPkList.size() > 0) {
                        ssmParam.put("dsImptOrdDtlPkList", dsImptOrdDtlPkList);
                    }
                    // 2018/01/23 QC#18798 Add End
                    List<Map<String, Object>> imptSvcConfigRef = NWZC226001Query.getInstance().queryMapList("getDsImptSvcConfigRef", ssmParam);
                    if (hasValueList(imptSvcConfigRef)) {

                        for (int j = 0; j < imptSvcConfigRef.size(); j++) {

                            DS_IMPT_SVC_CONFIG_REFTMsg svcConfigRefMsg = new DS_IMPT_SVC_CONFIG_REFTMsg();
                            // 2018/06/12 S21_NA#24294 Mod Start
                            Map<String, Object> svcConfigRefMap = imptSvcConfigRef.get(j);
                            mapData(svcConfigRefMap, svcConfigRefMsg);
                            // ZYPEZDItemValueSetter.setValue(svcConfigRefMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                            // BigDecimal dsImptSvcConfigRefPk = convToBigDecimal(imptSvcConfigRef.get(j));
                            // ZYPEZDItemValueSetter.setValue(svcConfigRefMsg.dsImptSvcConfigRefPk, dsImptSvcConfigRefPk);
                            // svcConfigRefMsg = (DS_IMPT_SVC_CONFIG_REFTMsg) S21ApiTBLAccessor.findByKey(svcConfigRefMsg);
                            // if (!addFindErrorMsgList(svcConfigRefMsg, "DS_IMPT_SVC_CONFIG_REF", dsImptSvcConfigRefPk, svcDtlBean)) {
                            if (!ZYPCommonFunc.hasValue(svcConfigRefMsg.dsImptSvcConfigRefPk)) { 
                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_CONFIG_REF", svcDtlPk);
                                hdrBean.dsImptOrdErrList.add(errBean);
                            // 2018/06/12 S21_NA#24294 Mod End
                                return false;
                            }
                            svcDtlBean.svcConfigRefMsgList.add(svcConfigRefMsg);
                        }
                    }

                    // *********************************************************
                    // DS_IMPT_SVC_PRC
                    // *********************************************************
                    List<Map<String, Object>> imptSvcPrc = NWZC226001Query.getInstance().queryMapList("getDsImptSvcPrc", ssmParam);
                    if (hasValueList(imptSvcPrc)) {

                        for (int j = 0; j < imptSvcPrc.size(); j++) {

                            // 2018/06/12 S21_NA#24294 Mod Start
                            // BigDecimal svcPrcPk = convToBigDecimal(imptSvcPrc.get(j));
                            DS_IMPT_SVC_PRCTMsg svcPrcMsg = new DS_IMPT_SVC_PRCTMsg();
                            Map<String, Object> imptSvcPrcMap = imptSvcPrc.get(j);
                            mapData(imptSvcPrcMap, svcPrcMsg);
                            // ZYPEZDItemValueSetter.setValue(svcPrcMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                            // ZYPEZDItemValueSetter.setValue(svcPrcMsg.dsImptSvcPrcPk, svcPrcPk);
                            // svcPrcMsg = (DS_IMPT_SVC_PRCTMsg) S21ApiTBLAccessor.findByKey(svcPrcMsg);
                            // if (!addFindErrorMsgList(svcPrcMsg, "DS_IMPT_SVC_PRC", svcPrcPk, svcDtlBean)) {
                            if (!ZYPCommonFunc.hasValue(svcPrcMsg.dsImptSvcPrcPk)) {
                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_PRC", svcDtlPk);
                                hdrBean.dsImptOrdErrList.add(errBean);
                            // 2018/06/12 S21_NA#24294 Mod End
                                return false;
                            }
                            svcDtlBean.svcPrcMsgList.add(svcPrcMsg);
                        }
                    }

                    // *********************************************************
                    // DS_IMPT_SVC_USG_PRC
                    // *********************************************************
                    List<Map<String, Object>> imptSvcUsgPrc = NWZC226001Query.getInstance().queryMapList("getDsImptSvcUsgPrc", ssmParam);
                    if (hasValueList(imptSvcUsgPrc)) {

                        for (int j = 0; j < imptSvcUsgPrc.size(); j++) {

                            // 2018/06/12 S21_NA#24294 Mod Start
                            // BigDecimal svcPrcUsgPrcPk = convToBigDecimal(imptSvcUsgPrc.get(j));
                            DS_IMPT_SVC_USG_PRCTMsg svcUsgPrcMsg = new DS_IMPT_SVC_USG_PRCTMsg();
                            Map<String, Object> imptSvcUsgPrcMap = imptSvcUsgPrc.get(j);
                            mapData(imptSvcUsgPrcMap, svcUsgPrcMsg);
                            // ZYPEZDItemValueSetter.setValue(svcUsgPrcMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                            // ZYPEZDItemValueSetter.setValue(svcUsgPrcMsg.dsImptSvcUsgPrcPk, svcPrcUsgPrcPk);
                            // svcUsgPrcMsg = (DS_IMPT_SVC_USG_PRCTMsg) S21ApiTBLAccessor.findByKey(svcUsgPrcMsg);
                            // if (!addFindErrorMsgList(svcUsgPrcMsg, "DS_IMPT_SVC_USG_PRC", svcPrcUsgPrcPk, svcDtlBean)) {
                            if (!ZYPCommonFunc.hasValue(svcUsgPrcMsg.dsImptSvcUsgPrcPk)) {
                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_USG_PRC", svcDtlPk);
                                hdrBean.dsImptOrdErrList.add(errBean);
                            // 2018/06/12 S21_NA#24294 Mod End
                                return false;
                            }
                            svcDtlBean.svcUsgPrcMsgList.add(svcUsgPrcMsg);
                        }
                    }

                    // *********************************************************
                    // DS_IMPT_SVC_ADDL_BASE
                    // *********************************************************
                    // 2022/12/14 QC#60717 Add Start
                    if (hasValueList(imptSvcConfigRef)) {

                        for (int k = 0; k < imptSvcConfigRef.size(); k++) {
                            Map<String, Object> svcConfigRefMap = imptSvcConfigRef.get(k);
                            BigDecimal confRefDsimptOrdPk = (BigDecimal) svcConfigRefMap.get("DS_IMPT_ORD_PK");
                            BigDecimal confRefDsimptOrdDtlPk = (BigDecimal) svcConfigRefMap.get("DS_IMPT_ORD_DTL_PK");

                            Map<String, Object> queryParam = new HashMap<String, Object>();
                            queryParam = new HashMap<String, Object>();
                            queryParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                            queryParam.put("dsImptOrdPk", confRefDsimptOrdPk);
                            queryParam.put("dsImptOrdDtlPk", confRefDsimptOrdDtlPk);
                            List<Map<String, Object>> dsImptOrdDtl = NWZC226001Query.getInstance().queryMapList("getDsImptOrdDtl", queryParam);

                            for (int l = 0; l < dsImptOrdDtl.size(); l++) {
                                BigDecimal addlBasefDsimptOrdDtlPk = (BigDecimal) dsImptOrdDtl.get(l).get("DS_IMPT_ORD_DTL_PK");

                                ssmParam.put("dsImptOrdDtlPk", addlBasefDsimptOrdDtlPk);
                                List<Map<String, Object>> imptSvcAddlBase = NWZC226001Query.getInstance().queryMapList("getDsImptSvcAddlBase", ssmParam);
                                if (hasValueList(imptSvcAddlBase)) {

                                    for (int j = 0; j < imptSvcAddlBase.size(); j++) {

                                        DS_IMPT_SVC_ADDL_BASETMsg svcAddlBaseMsg = new DS_IMPT_SVC_ADDL_BASETMsg();
                                        Map<String, Object> imptSvcAddlBaseMap = imptSvcAddlBase.get(j);
                                        mapData(imptSvcAddlBaseMap, svcAddlBaseMsg);
                                        if (!ZYPCommonFunc.hasValue(svcAddlBaseMsg.dsImptSvcAddlBasePk)) {
                                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_ADDL_BASE", svcDtlPk);
                                            hdrBean.dsImptOrdErrList.add(errBean);
                                            return false;
                                        }
                                        svcDtlBean.svcAddlBaseMsgList.add(svcAddlBaseMsg);
                                    }
                                }
                            }
                        }
                    }
                    // 2022/12/14 QC#60717 Add End
                    // 2022/12/14 QC#60717 Del Start

//                        List<Map<String, Object>> imptSvcAddlBase = NWZC226001Query.getInstance().queryMapList("getDsImptSvcAddlBase", ssmParam);
//                        if (hasValueList(imptSvcAddlBase)) {
//
//                            for (int j = 0; j < imptSvcAddlBase.size(); j++) {
//
//                                // 2018/06/12 S21_NA#24294 Mod Start
//                                // BigDecimal svcAddlBasePk = convToBigDecimal(imptSvcAddlBase.get(j));
//                                DS_IMPT_SVC_ADDL_BASETMsg svcAddlBaseMsg = new DS_IMPT_SVC_ADDL_BASETMsg();
//                                Map<String, Object> imptSvcAddlBaseMap = imptSvcAddlBase.get(j);
//                                mapData(imptSvcAddlBaseMap, svcAddlBaseMsg);
//                                // ZYPEZDItemValueSetter.setValue(svcAddlBaseMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
//                                // ZYPEZDItemValueSetter.setValue(svcAddlBaseMsg.dsImptSvcAddlBasePk, svcAddlBasePk);
//                                // svcAddlBaseMsg = (DS_IMPT_SVC_ADDL_BASETMsg) S21ApiTBLAccessor.findByKey(svcAddlBaseMsg);
//                                // if (!addFindErrorMsgList(svcAddlBaseMsg, "DS_IMPT_SVC_ADDL_BASE", svcAddlBasePk, svcDtlBean)) {
//                                if (!ZYPCommonFunc.hasValue(svcAddlBaseMsg.dsImptSvcAddlBasePk)) {
//                                    DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_ADDL_BASE", svcDtlPk);
//                                    hdrBean.dsImptOrdErrList.add(errBean);
//                                    // 2018/06/12 S21_NA#24294 Mod End
//                                    return false;
//                                }
//                                svcDtlBean.svcAddlBaseMsgList.add(svcAddlBaseMsg);
//                            }
//                        }
                    // 2022/12/14 QC#60717 Del End

                    // *********************************************************
                    // DS_IMPT_SVC_ADDL_CHRG
                    // *********************************************************
                    List<Map<String, Object>> imptSvcAddlChrg = NWZC226001Query.getInstance().queryMapList("getDsImptSvcAddlChrg", ssmParam);
                    if (hasValueList(imptSvcAddlChrg)) {

                        for (int j = 0; j < imptSvcAddlChrg.size(); j++) {

                            // 2018/06/12 S21_NA#24294 Mod Start
                            // BigDecimal svcAddlChrgPk = convToBigDecimal(imptSvcAddlChrg.get(j));
                            DS_IMPT_SVC_ADDL_CHRGTMsg svcAddlChrgMsg = new DS_IMPT_SVC_ADDL_CHRGTMsg();
                            Map<String, Object> imptSvcAddlChrgMap = imptSvcAddlChrg.get(j);
                            mapData(imptSvcAddlChrgMap, svcAddlChrgMsg);
                            // ZYPEZDItemValueSetter.setValue(svcAddlChrgMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                            // ZYPEZDItemValueSetter.setValue(svcAddlChrgMsg.dsImptSvcAddlChrgPk, svcAddlChrgPk);
                            // svcAddlChrgMsg = (DS_IMPT_SVC_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKey(svcAddlChrgMsg);
                            // if (!addFindErrorMsgList(svcAddlChrgMsg, "DS_IMPT_SVC_ADDL_CHRG", svcAddlChrgPk, svcDtlBean)) {
                            if (!ZYPCommonFunc.hasValue(svcAddlChrgMsg.dsImptSvcAddlChrgPk)) {
                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_SVC_ADDL_CHRG", svcDtlPk);
                                hdrBean.dsImptOrdErrList.add(errBean);
                            // 2018/06/12 S21_NA#24294 Mod End
                                return false;
                            }
                            svcDtlBean.svcAddlChrgMsgList.add(svcAddlChrgMsg);
                        }
                    }
                }
            }

            return true;
        } finally {

            writeEndLogLn("deriveServiceShellData", hdrBean);
        }
    }

    private boolean deriveSalesCreditData(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveSalesCreditData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);

            // 2018/01/23 QC#18798 Mod Start
            //// 2017/03/16 S21_NA#18064 Add Start
            //if (hdrBean.isLoanConversion()) {
            //    ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_ON_Y);
            //} else {
            //    ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_OFF_N);
            //}
            // 2017/03/16 S21_NA#18064 Add End
            if (hdrBean.isLoanConversion() || ZYPConstant.FLG_ON_Y.equals(dsImptOrdConfigFlg)) {
                ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_OFF_N);
            }
            // 2018/01/23 QC#18798 Mod End

            List<Map<String, Object>> imptSlsCrList = NWZC226001Query.getInstance().queryMapList("getDsImptOrdSlsCr", ssmParam);
            if (!hasValueList(imptSlsCrList)) {

                return true;
            }

            boolean isSuccess = true;
            for (int i = 0; i < imptSlsCrList.size(); i++) {

                Map<String, Object> imptSlsCr = imptSlsCrList.get(i);

                boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptSlsCr.get("DS_IMPT_ORD_CONFIG_PK")));

                DS_IMPT_ORD_SLS_CRTMsg imptSlsCrMsg = new DS_IMPT_ORD_SLS_CRTMsg();
                // 2019/01/29 S21_NA#30022 Add Start
                // Mod Start 2019/03/13 QC#30751
//                if (!hdrBean.dsImptOrdConfigMap.containsKey(imptSlsCr.get("DS_IMPT_ORD_CONFIG_PK"))) {
                if (!isHdrlevel && !hdrBean.dsImptOrdConfigMap.containsKey(imptSlsCr.get("DS_IMPT_ORD_CONFIG_PK"))) {
                    continue;
                }
                // Mod End 2019/03/13 QC#30751
                // 2019/01/29 S21_NA#30022 Add End
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptSlsCr, imptSlsCrMsg);
                // ZYPEZDItemValueSetter.setValue(imptSlsCrMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                BigDecimal dsImptOrdSlsCrPk = convToBigDecimal(imptSlsCr.get("DS_IMPT_ORD_SLS_CR_PK"));
                // ZYPEZDItemValueSetter.setValue(imptSlsCrMsg.dsImptOrdSlsCrPk, dsImptOrdSlsCrPk);
                // imptSlsCrMsg = (DS_IMPT_ORD_SLS_CRTMsg) S21ApiTBLAccessor.findByKey(imptSlsCrMsg);
                // checkTMsgDbAccess(imptSlsCrMsg);
                // 2018/06/12 S21_NA#24294 Mod End
                if (imptSlsCrMsg == null) {

                    if (isHdrlevel) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SLS_CR", dsImptOrdSlsCrPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptSlsCr.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_SLS_CR", configPk);
                            configBean.dsImptOrdErrList.add(errBean);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SLS_CR", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        }
                    }

                    isSuccess = false;
                    continue;
                }

                if (isHdrlevel) {

                    hdrBean.getDsImptSlsCrList().add(imptSlsCrMsg);
                } else {

                    BigDecimal configPk = convToBigDecimal(imptSlsCr.get("DS_IMPT_ORD_CONFIG_PK"));
                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        configBean.dsImptSlsCrList.add(imptSlsCrMsg);
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SLS_CR", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveSalesCreditData", hdrBean);
        }
    }

    // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
//    private boolean deriveDeliveryData(ImptHdrBean hdrBean) {
    private boolean deriveDeliveryData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
    // 2018/02/08 S21_NA#20297(Sol#379) Mod End

        writeStartLogLn("deriveDeliveryData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            // Del Start 2017/07/04 QC#19692
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);
            // Del End 2017/07/04 QC#19692

            List<Map<String, Object>> imptDelyList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdDelyInfo", ssmParam);
            if (!hasValueList(imptDelyList)) {

                return true;
            }

            // 2018/02/08 S21_NA#20297(Sol#379) Add Start
            StringBuilder defShpgCmnt = new StringBuilder();
            NMZC610001PMsg apiNMZC6100pMsg = callCustInfoGetApiForDefShpgCmnt(onBatchType, hdrBean);
            if (!S21ApiUtil.isXxMsgId(apiNMZC6100pMsg)) {
               for (int i = 0; i < apiNMZC6100pMsg.InstructionList.getValidCount(); i++) {
                   if (defShpgCmnt.length() > 0) {
                       defShpgCmnt.append(System.getProperty("line.separator"));
                   }
                   defShpgCmnt.append(apiNMZC6100pMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
               }
            }
            // 2018/02/08 S21_NA#20297(Sol#379) Add End

            boolean isSuccess = true;
            for (int i = 0; i < imptDelyList.size(); i++) {

                Map<String, Object> imptDely = imptDelyList.get(i);
                
                DS_IMPT_ORD_DELY_INFOTMsg imptDelyMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
                // 2019/01/29 S21_NA#30022 Add Start
                if (!hdrBean.dsImptOrdConfigMap.containsKey(imptDely.get("DS_IMPT_ORD_CONFIG_PK"))) {
                    continue;
                }
                // 2019/01/29 S21_NA#30022 Add End
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptDely, imptDelyMsg);
                // ZYPEZDItemValueSetter.setValue(imptDelyMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                BigDecimal dsImptOrdDelyInfoPk = convToBigDecimal(imptDely.get("DS_IMPT_ORD_DELY_INFO_PK"));
                // ZYPEZDItemValueSetter.setValue(imptDelyMsg.dsImptOrdDelyInfoPk, dsImptOrdDelyInfoPk);
                // imptDelyMsg = (DS_IMPT_ORD_DELY_INFOTMsg) S21ApiTBLAccessor.findByKey(imptDelyMsg);
                // checkTMsgDbAccess(imptDelyMsg);
                // 2018/06/12 S21_NA#24294 Mod End

                boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptDely.get("DS_IMPT_ORD_CONFIG_PK")));
                if (imptDelyMsg == null) {

                    if (isHdrlevel) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_DELY_INFO", dsImptOrdDelyInfoPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptDely.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_DELY_INFO", configPk);
                            configBean.dsImptOrdErrList.add(errBean);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_DELY_INFO", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        }
                    }

                    isSuccess = false;
                    continue;
                }

                // 2018/02/08 S21_NA#20297(Sol#379) Add Start
                if (!ZYPCommonFunc.hasValue(imptDelyMsg.delyAddlCmntTxt)) {
                    ZYPEZDItemValueSetter.setValue(imptDelyMsg.delyAddlCmntTxt, defShpgCmnt.toString());
                }
                // 2018/02/08 S21_NA#20297(Sol#379) Add End

                if (isHdrlevel) {

                    hdrBean.getDsImptDelyList().add(imptDelyMsg);
                } else {

                    BigDecimal configPk = convToBigDecimal(imptDely.get("DS_IMPT_ORD_CONFIG_PK"));
                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        configBean.dsImptDelyList.add(imptDelyMsg);
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_DELY_INFO", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveDeliveryData", hdrBean);
        }
    }

    private boolean deriveSiteSurveyData(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveSiteSurveyData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            // Del Start 2017/07/04 QC#19692
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);
            // Del End 2017/07/04 QC#19692

            List<Map<String, Object>> imptSiteSrvyList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdSiteSrvy", ssmParam);
            if (!hasValueList(imptSiteSrvyList)) {
                // QC#22641
                if(hdrBean.isLoanDowngrade()){
                    deriveSiteSurveyDataFromOutbound(hdrBean);
                }

                return true;
            }

            boolean isSuccess = true;
            for (int i = 0; i < imptSiteSrvyList.size(); i++) {

                Map<String, Object> imptSiteSrvy = imptSiteSrvyList.get(i);
                
                DS_IMPT_ORD_SITE_SRVYTMsg imptSiteSrvyMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();
                // 2019/01/29 S21_NA#30022 Add Start
                if (!hdrBean.dsImptOrdConfigMap.containsKey(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"))) {
                    continue;
                }
                // 2019/01/29 S21_NA#30022 Add End
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptSiteSrvy, imptSiteSrvyMsg);
                // ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                BigDecimal dsImptOrdSiteSrvyPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_SITE_SRVY_PK"));
                // ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.dsImptOrdSiteSrvyPk, dsImptOrdSiteSrvyPk);
                // imptSiteSrvyMsg = (DS_IMPT_ORD_SITE_SRVYTMsg) S21ApiTBLAccessor.findByKey(imptSiteSrvyMsg);
                // checkTMsgDbAccess(imptSiteSrvyMsg);
                // 2018/06/12 S21_NA#24294 Mod End

                boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK")));
                if (imptSiteSrvyMsg == null) {

                    if (isHdrlevel) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", dsImptOrdSiteSrvyPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                            configBean.dsImptOrdErrList.add(errBean);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        }
                    }

                    isSuccess = false;
                    continue;
                }

                if (isHdrlevel) {

                    hdrBean.getDsImptSiteSrvyList().add(imptSiteSrvyMsg);
                } else {

                    BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        configBean.dsImptSiteSrvyList.add(imptSiteSrvyMsg);
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveSiteSurveyData", hdrBean);
        }
    }

    // QC#22641
    private void deriveSiteSurveyDataFromOutbound(ImptHdrBean hdrBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
        ssmParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);
        ssmParam.put("configCatgIn", CONFIG_CATG.INBOUND);
        List<BigDecimal> svcConfigMstrPkList = new ArrayList<BigDecimal>();
        for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
            svcConfigMstrPkList.add(confBean.svcConfigMstrPk.getValue());
        }
        ssmParam.put("svcConfigMstrPkList", svcConfigMstrPkList);

        List<Map<String, Object>> imptSiteSrvyList //
        = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdSiteSrvyFromOutbound", ssmParam);

        for (int i = 0; i < imptSiteSrvyList.size(); i++) {

            Map<String, Object> imptSiteSrvy = imptSiteSrvyList.get(i);
            DS_IMPT_ORD_SITE_SRVYTMsg imptSiteSrvyMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();
            ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            BigDecimal dsImptOrdSiteSrvyPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_SITE_SRVY_PK"));
            ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.dsImptOrdSiteSrvyPk, dsImptOrdSiteSrvyPk);
            imptSiteSrvyMsg = (DS_IMPT_ORD_SITE_SRVYTMsg) S21ApiTBLAccessor.findByKey(imptSiteSrvyMsg);
            checkTMsgDbAccess(imptSiteSrvyMsg);

            if (imptSiteSrvyMsg == null) {
                continue;
            }

            BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("INBD_DS_IMPT_ORD_CONFIG_PK"));
            ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.dsImptOrdConfigPk, configPk);
            DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
            if (configBean != null) {
                configBean.dsImptSiteSrvyList.add(imptSiteSrvyMsg);
            }
        }

    }

    // Add Start 2017/08/01 QC#20000
    private boolean deriveDcRmaConfigSiteSurveyData(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveSiteSurveyData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            ssmParam.put("configCatg", CONFIG_CATG.INBOUND);
            // 2017/09/21 S21_NA#20548 Add Start
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);
            // 2017/09/21 S21_NA#20548 Add End

            List<Map<String, Object>> dcRmaSiteSrvyList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDcRmaConfigDsImptOrdSiteSrvy", ssmParam);
            if (!hasValueList(dcRmaSiteSrvyList)) {

                return true;
            }

            boolean isSuccess = true;
            // 2017/08/15 S21_NA#20000-2 Add Start
            List<BigDecimal> checkConfigList = new ArrayList<BigDecimal>(0);
            // 2017/08/15 S21_NA#20000-2 Add End
            for (int i = 0; i < dcRmaSiteSrvyList.size(); i++) {

                Map<String, Object> imptSiteSrvy = dcRmaSiteSrvyList.get(i);
                DS_IMPT_ORD_SITE_SRVYTMsg imptSiteSrvyMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();
                BigDecimal dsImptOrdSiteSrvyPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_SITE_SRVY_PK"));

                if (ZYPCommonFunc.hasValue(dsImptOrdSiteSrvyPk)) {
                    ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(imptSiteSrvyMsg.dsImptOrdSiteSrvyPk, dsImptOrdSiteSrvyPk);
                    imptSiteSrvyMsg = (DS_IMPT_ORD_SITE_SRVYTMsg) S21ApiTBLAccessor.findByKey(imptSiteSrvyMsg);
                    checkTMsgDbAccess(imptSiteSrvyMsg);

                    boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK")));
                    if (imptSiteSrvyMsg == null) {

                        if (isHdrlevel) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", dsImptOrdSiteSrvyPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        } else {

                            BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                            DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                            if (configBean != null) {

                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                                configBean.dsImptOrdErrList.add(errBean);
                            } else {

                                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                                hdrBean.dsImptOrdErrList.add(errBean);
                            }
                        }

                        isSuccess = false;
                        continue;
                    }

                    if (isHdrlevel) {

                        hdrBean.getDsImptSiteSrvyList().add(imptSiteSrvyMsg);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            configBean.dsImptSiteSrvyList.add(imptSiteSrvyMsg);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                            isSuccess = false;
                        }

                    }

                } else {
                    // 2017/08/15 S21_NA#20000-2 Add Start
                    BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                    if (ZYPCommonFunc.hasValue(configPk) && checkConfigList.contains(configPk)) {
                        continue;
                    }
                    checkConfigList.add(configPk);
                    List<DS_IMPT_ORD_SITE_SRVYTMsg> tMsgList = getSiteSrvyTMsg4RmaFromLineConfig(hdrBean, (String) imptSiteSrvy.get("SHIP_TO_CUST_LOC_CD"));
                    // 2017/08/15 S21_NA#20000-2 Add End
                    if (tMsgList == null) { // 2017/08/15 S21_NA#20000-2 Add Condition
                        Map<String, Object> ssmParamOrdSiteSrvy = new HashMap<String, Object>();
                        ssmParamOrdSiteSrvy.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                        // 2017/08/15 S21_NA#20000-2 Del Start
                        // BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                        // 2017/08/15 S21_NA#20000-2 Del End
                        ssmParamOrdSiteSrvy.put("dsImptOrdConfigPk", configPk);
                        ssmParamOrdSiteSrvy.put("withMdlFlg", ZYPConstant.FLG_ON_Y);
                        ssmParamOrdSiteSrvy.put("ordHdrStsSaved", ORD_HDR_STS.SAVED);
                        ssmParamOrdSiteSrvy.put("ordHdrStsCancelled", ORD_HDR_STS.CANCELLED);
    
                        Map<String, Object> ordSiteSrvyDataMap = null;
                        ordSiteSrvyDataMap = (Map<String, Object>) NWZC226001Query.getInstance().queryMap("getOrdSiteSrvyDataForDcRma", ssmParamOrdSiteSrvy);
    
                        if (ordSiteSrvyDataMap == null) {
                            ssmParamOrdSiteSrvy.put("withMdlFlg", ZYPConstant.FLG_OFF_N);
                            ordSiteSrvyDataMap = (Map<String, Object>) NWZC226001Query.getInstance().queryMap("getOrdSiteSrvyDataForDcRma", ssmParamOrdSiteSrvy);
    
                            if (ordSiteSrvyDataMap == null) {
                                return true;
                            }
                        }

                        DS_IMPT_ORD_SITE_SRVYTMsg tMsg = setDsImptOrdSiteSuvy(ordSiteSrvyDataMap);
                        // 2017/08/15 S21_NA#20000-2 Add Start
                        tMsgList = new ArrayList<DS_IMPT_ORD_SITE_SRVYTMsg>(0);
                        tMsgList.add(tMsg);
                        // 2017/08/15 S21_NA#20000-2 Add End
                    }  // 2017/08/15 S21_NA#20000-2 Add Condition

                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        // 2017/08/15 S21_NA#20000-2 Mod Start
//                        configBean.dsImptSiteSrvyList.add(tMsg);
                        for (DS_IMPT_ORD_SITE_SRVYTMsg tMsg : tMsgList) {
                            DS_IMPT_ORD_SITE_SRVYTMsg tMsg4Set = new DS_IMPT_ORD_SITE_SRVYTMsg();
                            EZDMsg.copy(tMsg, null, tMsg4Set, null);
                            tMsg4Set.dsImptOrdConfigPk.setValue(configPk);
                            tMsg4Set.dsImptOrdSiteSrvyPk.clear();
                            configBean.dsImptSiteSrvyList.add(tMsg4Set);
                        }
                        // 2017/08/15 S21_NA#20000-2 Mod End
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_SITE_SRVY", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                    }
                    
                }

            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveSiteSurveyData", hdrBean);
        }
    }

    private DS_IMPT_ORD_SITE_SRVYTMsg setDsImptOrdSiteSuvy(Map<String, Object> resultMap) {

        DS_IMPT_ORD_SITE_SRVYTMsg tMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoAptBldgNm, (String) resultMap.get("CMPY_INFO_APT_BLDG_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoFlNm, (String) resultMap.get("CMPY_INFO_FL_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoDeptNm, (String) resultMap.get("CMPY_INFO_DEPT_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.otsdStepNum, (String) resultMap.get("OTSD_STEP_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.insdStepNum, (String) resultMap.get("INSD_STEP_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.stairCrawReqFlg, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.flgtStairNum, (String) resultMap.get("FLGT_STAIR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFlg, (String) resultMap.get("ELEV_AVAL_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFromHourMn, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalToHourMn, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevApptReqFlg, (String) resultMap.get("ELEV_APPT_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevCtacTelNum, (String) resultMap.get("ELEV_CTAC_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevProtReqFlg, (String) resultMap.get("ELEV_PROT_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevWdt, (BigDecimal) resultMap.get("ELEV_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevDepthNum, (BigDecimal) resultMap.get("ELEV_DEPTH_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevCtacPsnNm, (String) resultMap.get("ELEV_CTAC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevCapWt, (BigDecimal) resultMap.get("ELEV_CAP_WT"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevDoorHgt, (BigDecimal) resultMap.get("ELEV_DOOR_HGT"));
        ZYPEZDItemValueSetter.setValue(tMsg.elevDoorWdt, (BigDecimal) resultMap.get("ELEV_DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.stairAndLdgWdt, (BigDecimal) resultMap.get("STAIR_AND_LDG_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.crdrWdt, (BigDecimal) resultMap.get("CRDR_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.doorWdt, (BigDecimal) resultMap.get("DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFlg, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.loadDockHgt, (BigDecimal) resultMap.get("LOAD_DOCK_HGT"));
        ZYPEZDItemValueSetter.setValue(tMsg.trctrAndTrailAccsFlg, (String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.bldgEntDoorHgt, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_HGT"));
        ZYPEZDItemValueSetter.setValue(tMsg.bldgEntDoorWdt, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFromHourMn, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalToHourMn, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
        ZYPEZDItemValueSetter.setValue(tMsg.carrDelyTmHourMn, (String) resultMap.get("CARR_DELY_TM_HOUR_MN"));
        ZYPEZDItemValueSetter.setValue(tMsg.delyTrnspOptCd, (String) resultMap.get("DELY_TRNSP_OPT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyAddlCmntTxt, (String) resultMap.get("SITE_SRVY_ADDL_CMNT_TXT"));

        return tMsg;
    }
    // Add End 2017/08/01 QC#20000

    private boolean deriveInstallData(ImptHdrBean hdrBean) {

        writeStartLogLn("deriveInstallData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            // Del Start 2017/07/04 QC#19692
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);
            // Del End 2017/07/04 QC#19692

            List<Map<String, Object>> imptIstlList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdIstlInfo", ssmParam);
            if (!hasValueList(imptIstlList)) {
                return true;
            }

            boolean isSuccess = true;
            for (int i = 0; i < imptIstlList.size(); i++) {

                Map<String, Object> imptSiteSrvy = (Map<String, Object>) imptIstlList.get(i);
                DS_IMPT_ORD_ISTL_INFOTMsg imptIstlMsg = new DS_IMPT_ORD_ISTL_INFOTMsg();
                // 2019/01/29 S21_NA#30022 Add Start
                if (!hdrBean.dsImptOrdConfigMap.containsKey(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"))) {
                    continue;
                }
                // 2019/01/29 S21_NA#30022 Add End
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptSiteSrvy, imptIstlMsg);
                // ZYPEZDItemValueSetter.setValue(imptIstlMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                BigDecimal dsImptOrdIstlInfoPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_ISTL_INFO_PK"));
                // ZYPEZDItemValueSetter.setValue(imptIstlMsg.dsImptOrdIstlInfoPk, dsImptOrdIstlInfoPk);
                // imptIstlMsg = (DS_IMPT_ORD_ISTL_INFOTMsg) S21ApiTBLAccessor.findByKey(imptIstlMsg);
                // checkTMsgDbAccess(imptIstlMsg);
                // 2018/06/12 S21_NA#24294 Mod End

                boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK")));
                if (imptIstlMsg == null) {

                    if (isHdrlevel) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_ISTL_INFO", dsImptOrdIstlInfoPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_ISTL_INFO", configPk);
                            configBean.dsImptOrdErrList.add(errBean);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_ISTL_INFO", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        }
                    }

                    isSuccess = false;
                    continue;
                }

                if (isHdrlevel) {

                    hdrBean.getDsImptInstList().add(imptIstlMsg);
                } else {

                    BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        configBean.dsImptInstList.add(imptIstlMsg);
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_ISTL_INFO", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveInstallData", hdrBean);
        }
    }

    private boolean deriveContactPersonData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("deriveContactPersonData", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>();
            for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                dsImptOrdConfigPkList.add(confBean.dsImptOrdConfigPk.getValue());
            }
            ssmParam.put("dsImptOrdConfigPkList", dsImptOrdConfigPkList);

            // 2018/01/23 QC#18798 Mod Start
            //// 2017/03/16 S21_NA#18064 Add Start
            //if (hdrBean.isLoanConversion()) {
            //    ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_ON_Y);
            //} else {
            //    ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_OFF_N);
            //}
            //// 2017/03/16 S21_NA#18064 Add End
            if (hdrBean.isLoanConversion() || ZYPConstant.FLG_ON_Y.equals(dsImptOrdConfigFlg)) {
                ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("isLoanConversionFlg", ZYPConstant.FLG_OFF_N);
            }
            // 2018/01/23 QC#18798 Mod End

            List<Map<String, Object>> imptIstlList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdCtacPsn", ssmParam);
            if (!hasValueList(imptIstlList)) {
                // 2018/01/04 S21_NA#23116 Add Start
                if (hdrBean.isLoanDowngrade()) {
                    deriveContactPersonDataFromOutbound(onBatchType, hdrBean);
                }
                // 2018/01/04 S21_NA#23116 Add End
                return true;
            }

            boolean isSuccess = true;
            Map<String, BigDecimal> contactInfoMap = new HashMap<String, BigDecimal>(); // 2018/06/15 S21_NA#24294 Add
            for (int i = 0; i < imptIstlList.size(); i++) {

                Map<String, Object> imptSiteSrvy = (Map<String, Object>) imptIstlList.get(i);
                DS_IMPT_ORD_CTAC_PSNTMsg imptCtacPsnMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
                // 2019/01/29 S21_NA#30022 Add Start
                if (!hdrBean.dsImptOrdConfigMap.containsKey(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"))) {
                    continue;
                }
                // 2019/01/29 S21_NA#30022 Add End
                // 2018/06/12 S21_NA#24294 Mod Start
                mapData(imptSiteSrvy, imptCtacPsnMsg);
                // ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                BigDecimal dsImptOrdCtacPsnPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CTAC_PSN_PK"));
                // ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.dsImptOrdCtacPsnPk, dsImptOrdCtacPsnPk);
                // imptCtacPsnMsg = (DS_IMPT_ORD_CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(imptCtacPsnMsg);
                // checkTMsgDbAccess(imptCtacPsnMsg);
                // 2018/06/12 S21_NA#24294 Mod End

                boolean isHdrlevel = !ZYPCommonFunc.hasValue(convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK")));
                if (imptCtacPsnMsg == null) {

                    if (isHdrlevel) {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_CTAC_PSN", dsImptOrdCtacPsnPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                    } else {

                        BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                        DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                        if (configBean != null) {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(configBean, "DS_IMPT_ORD_CTAC_PSN", configPk);
                            configBean.dsImptOrdErrList.add(errBean);
                        } else {

                            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_CTAC_PSN", configPk);
                            hdrBean.dsImptOrdErrList.add(errBean);
                        }
                    }

                    isSuccess = false;
                    continue;
                }

                if (isHdrlevel) {

                    hdrBean.getDsImptCtacPsnList().add(imptCtacPsnMsg);
                    // 2018/06/15 S21_NA#24294 Mod Start
                    // if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, hdrBean)) {
                    if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, hdrBean, contactInfoMap)) {
                        // 2018/06/15 S21_NA#24294 Mod End

                        isSuccess = false;
                        continue;
                    }
                } else {

                    BigDecimal configPk = convToBigDecimal(imptSiteSrvy.get("DS_IMPT_ORD_CONFIG_PK"));
                    DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
                    if (configBean != null) {

                        configBean.dsImptCtacPsnList.add(imptCtacPsnMsg);
                        // 2018/06/15 S21_NA#24294 Mod Start
                        // if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, hdrBean)) {
                        if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, configBean, contactInfoMap)) {
                            // 2018/06/15 S21_NA#24294 Mod End

                            isSuccess = false;
                            continue;
                        }
                    } else {

                        DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_CTAC_PSN", configPk);
                        hdrBean.dsImptOrdErrList.add(errBean);
                        isSuccess = false;
                        continue;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("deriveContactPersonData", hdrBean);
        }
    }

    // 2018/01/04 S21_NA#23116 Add Start
    private boolean deriveContactPersonDataFromOutbound(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
        ssmParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);
        ssmParam.put("configCatgIn", CONFIG_CATG.INBOUND);
        List<BigDecimal> svcConfigMstrPkList = new ArrayList<BigDecimal>();
        for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
            svcConfigMstrPkList.add(confBean.svcConfigMstrPk.getValue());
        }
        ssmParam.put("svcConfigMstrPkList", svcConfigMstrPkList);

        List<Map<String, Object>> imptCtacPsnList //
        = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdCtacPsnFromOutbound", ssmParam);

        boolean isSuccess = true;
        Map<String, BigDecimal> contactInfoMap = new HashMap<String, BigDecimal>(); // 2018/06/15 S21_NA#24294 Add
        for (int i = 0; i < imptCtacPsnList.size(); i++) {

            Map<String, Object> imptCtacPsn = imptCtacPsnList.get(i);
            DS_IMPT_ORD_CTAC_PSNTMsg imptCtacPsnMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            BigDecimal dsImptOrdCtacPsnPk = convToBigDecimal(imptCtacPsn.get("DS_IMPT_ORD_CTAC_PSN_PK"));
            ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.dsImptOrdCtacPsnPk, dsImptOrdCtacPsnPk);
            imptCtacPsnMsg = (DS_IMPT_ORD_CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(imptCtacPsnMsg);
            checkTMsgDbAccess(imptCtacPsnMsg);

            if (imptCtacPsnMsg == null) {
                continue;
            }

            BigDecimal configPk = convToBigDecimal(imptCtacPsn.get("INBD_DS_IMPT_ORD_CONFIG_PK"));
            ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.dsImptOrdConfigPk, configPk);
            DsImptOrdConfigBean configBean = hdrBean.dsImptOrdConfigMap.get(configPk);
            if (configBean != null) {
                configBean.dsImptCtacPsnList.add(imptCtacPsnMsg);
                // 2018/06/15 S21_NA#24294 Mod Start
                // if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, configBean)) {
                if (!callContactUpdateApi(onBatchType, hdrBean, imptCtacPsnMsg, configBean, contactInfoMap)) {
                    // 2018/06/15 S21_NA#24294 Mod End

                    isSuccess = false;
                    continue;
                }
            } else {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(hdrBean, "DS_IMPT_ORD_CTAC_PSN", configPk);
                hdrBean.dsImptOrdErrList.add(errBean);
                isSuccess = false;
                continue;
            }
        }
        return isSuccess;
    }
    // 2018/01/04 S21_NA#23116 Add End
    // 2018/06/15 S21_NA#24294 Mod Start
    // private boolean callContactUpdateApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, DS_IMPT_ORD_CTAC_PSNTMsg imptCtacPsnMsg, IImportBean ownerBean) {
    private boolean callContactUpdateApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, DS_IMPT_ORD_CTAC_PSNTMsg imptCtacPsnMsg, IImportBean ownerBean, Map<String, BigDecimal> contactInfoMap) {
    // 2018/06/15 S21_NA#24294 Mod End
        writeStartLogLn("callContactUpdateApi", hdrBean);

        try {

            Map<String, Object> custInfo = getShipInfo(hdrBean.getGlblCmpyCd(), hdrBean.getShipToCustCd());
            if (custInfo == null) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableExistError(ownerBean, "SHIP_TO_CUST", hdrBean.getShipToCustCd());
                ownerBean.getDsImptOrdErrList().add(errBean);
                return false;
            }

            // 2017/09/14 QC#20841 Add Start
            String ctacCustRefTpCd = getCtacCustRefTpCd(hdrBean.getGlblCmpyCd(), imptCtacPsnMsg.ctacPsnTpCd.getValue());
            // QC#21634
            if (hdrBean.getIsLeaseOrd() && CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
                return true;
            }
            String locNum = null;

            if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
                // 2018/06/13 S21_NA#24294 Mod Start
                // BILL_TO_CUSTTMsg billToCustCondTMsg = new BILL_TO_CUSTTMsg();
                // billToCustCondTMsg.setSQLID("003");
                // billToCustCondTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
                // billToCustCondTMsg.setConditionValue("billToCustCd01", hdrBean.getSoldToCustLocCd());

                // BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustCondTMsg);
                Map<String, Object> mapParam = new HashMap<String, Object>();
                mapParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                mapParam.put("billToCustCd", hdrBean.getSoldToCustLocCd());
                String billToLocNum = DataCacheForSSM.getInstance().getBillToLocNum(mapParam);
                // if (hasValidValue(billToCustTMsgArray)) {
                    // locNum = billToCustTMsgArray.no(0).locNum.getValue();
                if (ZYPCommonFunc.hasValue(billToLocNum)){
                    locNum = billToLocNum;
                }
            } else if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
                // BILL_TO_CUSTTMsg billToCustCondTMsg = new BILL_TO_CUSTTMsg();
                // billToCustCondTMsg.setSQLID("003");
                // billToCustCondTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
                // billToCustCondTMsg.setConditionValue("billToCustCd01", hdrBean.getBillToCustCd());

                // BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustCondTMsg);
                Map<String, Object> mapParam = new HashMap<String, Object>();
                mapParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                mapParam.put("billToCustCd", hdrBean.getBillToCustCd());
                String billToLocNum = DataCacheForSSM.getInstance().getBillToLocNum(mapParam);
                // if (hasValidValue(billToCustTMsgArray)) {
                    // locNum = billToCustTMsgArray.no(0).locNum.getValue();
                if (ZYPCommonFunc.hasValue(billToLocNum)){
                    locNum = billToLocNum;
                }
            } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
                // SHIP_TO_CUSTTMsg shipToCustCondTMsg = new SHIP_TO_CUSTTMsg();
                // shipToCustCondTMsg.setSQLID("004");
                // shipToCustCondTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
                // shipToCustCondTMsg.setConditionValue("shipToCustCd01", hdrBean.getShipToCustCd());

                // SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustCondTMsg);
                Map<String, Object> mapParam = new HashMap<String, Object>();
                mapParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                mapParam.put("shipToCustCd", hdrBean.getShipToCustCd());
                String shipToLocNum = DataCacheForSSM.getInstance().getShipToLocNum(mapParam);
                // if (hasValidValue(shipToCustTMsgArray)) {
                    // locNum = shipToCustTMsgArray.no(0).locNum.getValue();
                if (ZYPCommonFunc.hasValue(shipToLocNum)){
                    locNum = shipToLocNum;
                }
                // 2018/06/13 S21_NA#24294 Mod End
            }
            // 2017/09/14 QC#20841 Add End

            NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
            if (ZYPCommonFunc.hasValue(imptCtacPsnMsg.ctacPsnPk) && BigDecimal.ZERO.compareTo(imptCtacPsnMsg.ctacPsnPk.getValue()) != 0) {

                ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnPk, imptCtacPsnMsg.ctacPsnPk);
                ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_MODIFY);
            } else {

                ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_NEW);
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, convToString(custInfo.get("SELL_TO_CUST_CD")));
            // 2017/09/14 QC#20841 Mod Start
            //ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, convToString(custInfo.get("LOC_NUM")));
            if (locNum != null) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, locNum);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, convToString(custInfo.get("LOC_NUM")));
            }
            // 2017/09/14 QC#20841 Mod End
            ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, imptCtacPsnMsg.ctacPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, imptCtacPsnMsg.ctacPsnLastNm);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, imptCtacPsnMsg.ctacPsnTpCd);

            int cpIdx = 0;
            if (ZYPCommonFunc.hasValue(imptCtacPsnMsg.ctacPsnTelNum)) {

                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_PHONE);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, imptCtacPsnMsg.ctacPsnTelNum);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPsnExtnNum, imptCtacPsnMsg.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                // 2019/01/16 QC#29642 Add Start
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).updCtrlFlg, ZYPConstant.FLG_ON_Y);
                // 2019/01/16 QC#29642 Add End
                cpIdx++;
            }

            if (ZYPCommonFunc.hasValue(imptCtacPsnMsg.ctacPsnEmlAddr)) {

                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_MAIL);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, imptCtacPsnMsg.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                cpIdx++;
            }

            if (ZYPCommonFunc.hasValue(imptCtacPsnMsg.ctacPsnFaxNum)) {

                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_FAX);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, imptCtacPsnMsg.ctacPsnFaxNum);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                cpIdx++;
            }

            if (cpIdx == 0) {
                return true;
            }

            apiPMsg.ContactPointInfoList.setValidCount(cpIdx);
            
            // 2018/06/15 S21_NA#24294 Add Start
            String key = createKey(apiPMsg);
            if(contactInfoMap.containsKey(key)){
                ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.ctacPsnPk, contactInfoMap.get(key));
                return true;
            }
            // 2018/06/15 S21_NA#24294 Add End
            new NMZC002001().execute(apiPMsg, onBatchType);

            if (addErrorMsgList(apiPMsg, ownerBean).size() == 0) {

                ZYPEZDItemValueSetter.setValue(imptCtacPsnMsg.ctacPsnPk, apiPMsg.ctacPsnPk);
                contactInfoMap.put(key, apiPMsg.ctacPsnPk.getValue()); // 2018/06/15 S21_NA#24294 Add 
                return true;
            }

            return false;

        } finally {

            writeEndLogLn("callContactUpdateApi", hdrBean);
        }
    }
    
    // 2018/06/15 S21_NA#24294 Add Start
    private String createKey(NMZC002001PMsg apiPMsg){
        StringBuilder sb = new StringBuilder();
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.ctacPsnPk) ? apiPMsg.ctacPsnPk.getValue().toString()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.xxModeCd) ? apiPMsg.xxModeCd.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.glblCmpyCd) ? apiPMsg.glblCmpyCd.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.dsAcctNum) ? apiPMsg.dsAcctNum.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.locNum) ? apiPMsg.locNum.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.slsDt) ? apiPMsg.slsDt.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.ctacPsnFirstNm) ? apiPMsg.ctacPsnFirstNm.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.ctacPsnLastNm) ? apiPMsg.ctacPsnLastNm.getValue()+"," : "");
        sb.append(ZYPCommonFunc.hasValue(apiPMsg.ctacTpCd) ? apiPMsg.ctacTpCd.getValue()+"," : "");
        
        for(int i = 0; i < apiPMsg.ContactPointInfoList.getValidCount(); i++){
            sb.append(ZYPCommonFunc.hasValue(apiPMsg.ContactPointInfoList.no(i).xxModeCd) ? apiPMsg.ContactPointInfoList.no(i).xxModeCd.getValue()+"," : "");
            sb.append(ZYPCommonFunc.hasValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntTpCd) ? apiPMsg.ContactPointInfoList.no(i).dsCtacPntTpCd.getValue()+"," : "");
            sb.append(ZYPCommonFunc.hasValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntValTxt) ? apiPMsg.ContactPointInfoList.no(i).dsCtacPntValTxt.getValue()+"," : "");
            sb.append(ZYPCommonFunc.hasValue(apiPMsg.ContactPointInfoList.no(i).dsOpsOutFlg) ? apiPMsg.ContactPointInfoList.no(i).dsOpsOutFlg.getValue()+"," : "");
            sb.append(ZYPCommonFunc.hasValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg) ? apiPMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg.getValue()+"," : "");
        }
        return sb.toString();
    }
    // 2018/06/15 S21_NA#24294 Add End

    private boolean updateSupersession(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("updateSupersession", hdrBean);

        try {

            // Derive Drop Ship VarCharConst
            VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
            ZYPEZDItemValueSetter.setValue(varCharConstTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(varCharConstTMsg.varCharConstNm, DROP_SHIP_RTL_WH_CD);
            varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21ApiTBLAccessor.findByKey(varCharConstTMsg);
            String dropShipWh = varCharConstTMsg.varCharConstVal.getValue();

            // Derive Not Hard Alloc WH Code
            varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
            ZYPEZDItemValueSetter.setValue(varCharConstTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(varCharConstTMsg.varCharConstNm, NOT_ALLOC_WH_CD);
            varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21ApiTBLAccessor.findByKey(varCharConstTMsg);

            List<String> whLst = null;
            if (varCharConstTMsg != null) {

                String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
                whLst = Arrays.asList(varCharConstVal.split(","));
            } else {

                whLst = new ArrayList<String>();
            }

            boolean isSuccess = true;
            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                boolean doUpdate = false;
                String modeCd = "";
                if (ZYPConstant.FLG_OFF_N.equals(lineBean.supdLockFlg.getValue())) {

                    if (lineBean.rtlWhCd.getValue().equals(dropShipWh)) {

                        doUpdate = true;
                        modeCd = NWZC206001.SUPD_LATEST_MODE;
                    } else if (!whLst.contains(lineBean.rtlWhCd.getValue())) {

                        doUpdate = true;
                        modeCd = NWZC206001.SUPD_LIST_MODE;
                    }
                }

                if (doUpdate) {

                    String supdMdseCd = null;
                    String supdMdseNm = null;

                    NWZC206001PMsg apiPMsg = callSupersedeApi(onBatchType, lineBean, modeCd);
                    if (addErrorMsgList(apiPMsg, lineBean).size() > 0) {

                        isSuccess = false;
                        continue;
                    }

                    if (hasValidValue(apiPMsg.A)) {

                        if (NWZC206001.SUPD_LATEST_MODE.equals(modeCd)) {

                            supdMdseCd = apiPMsg.A.no(0).mdseCd.getValue();
                            supdMdseNm = apiPMsg.A.no(0).mdseNm.getValue();
                        } else {

                            for (int i = 0; i < apiPMsg.A.getValidCount(); i++) {

                                if (lineBean.ordQty.getValue().compareTo(apiPMsg.A.no(i).invtyAvalQty.getValue()) <= 0) {

                                    supdMdseCd = apiPMsg.A.no(i).mdseCd.getValue();
                                    supdMdseNm = apiPMsg.A.no(i).mdseNm.getValue();
                                    break;
                                }
                            }
                        }

                        if (ZYPCommonFunc.hasValue(supdMdseCd)) {

                            ZYPEZDItemValueSetter.setValue(lineBean.origMdseCd, lineBean.mdseCd);
                            ZYPEZDItemValueSetter.setValue(lineBean.origMdseNm, lineBean.mdseNm);
                            ZYPEZDItemValueSetter.setValue(lineBean.mdseCd, supdMdseCd);
                            ZYPEZDItemValueSetter.setValue(lineBean.mdseNm, supdMdseNm);

                            lineBean.mdseAllList.get(0).setMdseCd(supdMdseCd);
                            lineBean.mdseAllList.get(0).setChildMdseCd(supdMdseCd);
                            lineBean.mdseAllList.get(0).setSpuersession(true);
                        }
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("updateSupersession", hdrBean);
        }
    }

    private NWZC206001PMsg callSupersedeApi(ONBATCH_TYPE onBatchType, DsImptLineBean lineBean, String modeCd) {

        writeStartLogLn("callSupersedeApi", lineBean);

        try {

            String invtyLocCd;
            if (ZYPCommonFunc.hasValue(lineBean.rtlSwhCd)) {

                invtyLocCd = lineBean.rtlWhCd.getValue() + lineBean.rtlSwhCd.getValue();
            } else {

                invtyLocCd = lineBean.rtlWhCd.getValue() + SUB_WH_CD_NEW;
            }

            NWZC206001PMsg apiPMsg = new NWZC206001PMsg();

            ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, lineBean.imptHdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, lineBean.mdseInfo.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, lineBean.imptHdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, modeCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(apiPMsg.whCd, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
            new NWZC206001().execute(apiPMsg, onBatchType);

            return apiPMsg;
        } finally {

            writeEndLogLn("callSupersedeApi", lineBean);
        }
    }

    private boolean registerData(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("registerData", hdrBean);

        try {

            // Add Start 2017/10/23 QC#20625-2
            boolean isNotExistErr = true;
            // Add End 2017/10/23 QC#20625-2

            // *****************************************************************
            // Call Pricing API
            // *****************************************************************
            if (!callPricingApi(onBatchType, hdrBean)) {

                // Mod Start 2017/10/23 QC#20625-2
//                return false;
                isNotExistErr = false;
                // Mod End 2017/10/23 QC#20625-2
            }

            // *****************************************************************
            // Call DS CPO Update API
            // *****************************************************************
            if (!callDsCpoUpdateApi(onBatchType, hdrBean)) {

                // Mod Start 2017/10/23 QC#20625-2
//                return false;
                isNotExistErr = false;
                // Mod End 2017/10/23 QC#20625-2
            }

            // *****************************************************************
            // Call Contract Import API
            // ***********3*****************************************************
            if (!callContractImportApi(onBatchType, hdrBean)) {

                // Mod Start 2017/10/23 QC#20625-2
//                return false;
                isNotExistErr = false;
                // Mod End 2017/10/23 QC#20625-2
            }

            // *****************************************************************
            // Call Creation of Schedule via the Deal Configurator API
            // *****************************************************************
            if (!callCreationOfScheduleViaTheDealConfiguratorApi(onBatchType, hdrBean)) {

                // Mod Start 2017/10/23 QC#20625-2
//                return false;
                isNotExistErr = false;
                // Mod End 2017/10/23 QC#20625-2
            }

            // Mod Start 2017/10/23 QC#20625-2
//            return true;
            return isNotExistErr;
            // Mod End 2017/10/23 QC#20625-2

        } finally {

            writeEndLogLn("registerData", hdrBean);
        }
    }

    private boolean callContractImportApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("callContractImportApi", hdrBean);

        boolean result = true;
        try {

            NSZC115001PMsg pMsg = new NSZC115001PMsg();

            // *****************************************************************
            // Header Param
            // *****************************************************************
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_NEW);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(pMsg.refCpoOrdNum, hdrBean.getNewOrdNum());
            ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, hdrBean.getCpoSrcTpCd()); // 2018/03/01 QC#23934 Add 

            List<DsImptSvcDtlBean> svcDtlBeanList = hdrBean.getDsImptSvcDtlList();

            // Mod Start 2017/09/28 QC#20689
            // *************************************************************
            // CPO Service Config Reference
            // *************************************************************
//            setContrImptApiSvcConfigRefListPMsg(hdrBean, svcDtlBeanList, pMsg);
            Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap = setContrImptApiSvcConfigRefListPMsg(hdrBean, svcDtlBeanList, pMsg);

            // *************************************************************
            // CPO Service Detail
            // *************************************************************
//            setContrImptApiSvcDtlListPMsg(hdrBean, svcDtlBeanList, pMsg);
            if (!setContrImptApiSvcDtlListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap)) {
                return false;
            }

            if (addAccPkMap.isEmpty()) {
                // *************************************************************
                // CPO Service Price List
                // *************************************************************
//                setContrImptApiSvcPrcListPMsg(hdrBean, svcDtlBeanList, pMsg);
                setContrImptApiSvcPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);

                // *************************************************************
                // CPO Service Usage Price List
                // *************************************************************
//                setContrImptApiSvcUsgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg);
                setContrImptApiSvcUsgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);

                // *************************************************************
                // CPO Service Additional Base Price List
                // *************************************************************
//                setContrImptApiSvcAddlBasePrcListPMsg(hdrBean, svcDtlBeanList, pMsg);
                setContrImptApiSvcAddlBasePrcListPMsg(hdrBean, svcDtlBeanList, pMsg);

                // *************************************************************
                // CPO Service Additional Charge Price List
                // *************************************************************
//                setContrImptApiSvcAddlChrgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg);
                setContrImptApiSvcAddlChrgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);
                
                // 2018/12/12 S21_NA#29484 Add Start
                (new NSZC115001()).execute(pMsg, onBatchType);
                
                if (addErrorMsgListForContractApi(pMsg, hdrBean, null).size() > 0) {

                    result = false;
                }

                for (DsImptSvcDtlBean dsImptSvcDtlBean : hdrBean.getDsImptSvcDtlList()) {
                    if (addErrorMsgListForContractApi(pMsg, hdrBean, dsImptSvcDtlBean).size() > 0) {

                        result = false;
                    }
                }
                // 2018/12/12 S21_NA#29484 Add End
            }
            // Mod End 2017/09/28 QC#20689

            // 2018/12/12 S21_NA#29484 Del Start
            // execute API
            // (new NSZC115001()).execute(pMsg, onBatchType);
            // 2018/12/12 S21_NA#29484 Del End

            // error handling
            // Mod Start 2017/06/29 QC#19068
//            if (addErrorMsgList(pMsg, hdrBean).size() > 0) {
//
//                result = false;
//            }
            // 2018/12/12 S21_NA#29484 Del Start
            // if (addErrorMsgListForContractApi(pMsg, hdrBean, null).size() > 0) {

            //     result = false;
            // }

            // for (DsImptSvcDtlBean dsImptSvcDtlBean : hdrBean.getDsImptSvcDtlList()) {
            //     if (addErrorMsgListForContractApi(pMsg, hdrBean, dsImptSvcDtlBean).size() > 0) {

            //         result = false;
            //     }
            // }
            // 2018/12/12 S21_NA#29484 Del End
            // Mod End 2017/06/29 QC#19068

            // Add Start 2017/09/28 QC#20689
            if (!result) {
                return result;
            }

            // Add Accessory
            if (!addAccPkMap.isEmpty()) {
                // Change Request Mode : Mod
                // setChangeReqModeMod(pMsg); // 2018/12/12 S21_NA#29484 Del

                // *************************************************************
                // CPO Service Price List
                // *************************************************************
                setContrImptApiSvcPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);

                // *************************************************************
                // CPO Service Usage Price List
                // *************************************************************
                setContrImptApiSvcUsgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);

                // *************************************************************
                // CPO Service Additional Base Price List
                // *************************************************************
                setContrImptApiSvcAddlBasePrcListPMsg(hdrBean, svcDtlBeanList, pMsg);

                // *************************************************************
                // CPO Service Additional Charge Price List
                // *************************************************************
                setContrImptApiSvcAddlChrgPrcListPMsg(hdrBean, svcDtlBeanList, pMsg, addAccPkMap);

                // execute API
                (new NSZC115001()).execute(pMsg, onBatchType);

                if (addErrorMsgListForContractApi(pMsg, hdrBean, null).size() > 0) {

                    result = false;
                }

                for (DsImptSvcDtlBean dsImptSvcDtlBean : hdrBean.getDsImptSvcDtlList()) {
                    if (addErrorMsgListForContractApi(pMsg, hdrBean, dsImptSvcDtlBean).size() > 0) {

                        result = false;
                    }
                }
            }
            // Add End 2017/09/28 QC#20689

            return result;

        } finally {

            writeEndLogLn("callContractImportApi", hdrBean);
        }
    }

    private boolean callPricingApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
        writeStartLogLn("callPricingApi", hdrBean);

        try {

            // *****************************************************************
            // Header Param
            // *****************************************************************
            NWZC157001PMsg prcApiPMsg = setPricingApiHdrMsg(hdrBean, NWZC157001.GET_ORDER_ALL);

            hdrBean.setPrcApiPMsg(prcApiPMsg);

            // *****************************************************************
            // Detail Param(Outbound)
            // *****************************************************************
            int dtlCnt = 0;
            boolean isPriceFreezeForAutoAdd = isPriceFreezeForAutoAdd(hdrBean); // QC#18451
            // Get Freight CalcBaseTMsg
            List<DS_IMPT_PRC_CALC_BASETMsg> prcCalcBaseTMsgList = getFreightCalcBaseTMsg(hdrBean);

            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                NWZC157002PMsg prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(dtlCnt);
                DsImptOrdConfigBean configBean = lineBean.dsImptOrdConfigBean;

                ExpendMdseBean mdseBean = lineBean.mdseAllList.get(0);

                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineNum, mdseBean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineSubNum, mdseBean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.billToCustCd, configBean.billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shipToCustCd, configBean.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_SH, configBean.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_BL, configBean.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCatgCd, lineBean.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.csmpNum, lineBean.csmpContrNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dlrRefNum, lineBean.dlrRefNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcContrNum, hdrBean.getPrcContrNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaBrCd, configBean.coaBrCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ccyCd, lineBean.ccyCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcListEquipConfigNum, lineBean.prcListEquipConfigNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdseCd, lineBean.mdseCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.pkgUomCd, lineBean.custUomCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdLineCatgCd, lineBean.dsOrdLineCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordQty, lineBean.ordQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordCustUomQty, lineBean.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtrnRsnCd, "");
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdlId, configBean.mdlId);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.firstLineAddr_SH, lineBean.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.scdLineAddr_SH, lineBean.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctyAddr_SH, lineBean.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.stCd_SH, lineBean.shipToStCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.cntyNm_SH, lineBean.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.postCd_SH, lineBean.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctryCd_SH, lineBean.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.slsRepOrSlsTeamTocCd, configBean.slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlWhCd, lineBean.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.frtCondCd, hdrBean.getFrtCondCd());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaExtnCd, configBean.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
                // QC#18451
//              ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, lineBean.entDealNetUnitPrcAmt);
                if (isPriceFreezeForAutoAdd && lineBean.isAutoAddSupply) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, lineBean.entDealNetUnitPrcAmt);
                }
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.entCpoDtlDealSlsAmt, prcApi2PMsg.xxUnitPrc);

                // 2018/01/09 S21_NA#22371 Add Start
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlSwhCd, lineBean.rtlSwhCd);
                // 2018/01/09 S21_NA#22371 Add End

                if (lineBean.hasError(false)) {

                    return false;
                }

                // Add Freight
                setPricingPrcCalcBaseApiMsg(lineBean, prcApi2PMsg, prcCalcBaseTMsgList);

                dtlCnt++;
            }

            // *****************************************************************
            // Detail Param(Inbound)
            // *****************************************************************
            for (DsImptRtnLineBean rtnBean : hdrBean.getDsImptRtnLineList()) {

                NWZC157002PMsg prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(dtlCnt);
                DsImptOrdConfigBean configBean = rtnBean.dsImptOrdConfigBean;

                ExpendMdseBean mdseBean = rtnBean.mdseBean;

                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineNum, mdseBean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineSubNum, mdseBean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.configCatgCd, CONFIG_CATG.INBOUND);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.billToCustCd, configBean.billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shipToCustCd, configBean.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_SH, configBean.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_BL, configBean.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCatgCd, rtnBean.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.csmpNum, rtnBean.csmpContrNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dlrRefNum, rtnBean.dlrRefNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcContrNum, hdrBean.getPrcContrNum());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaBrCd, configBean.coaBrCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ccyCd, rtnBean.ccyCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdseCd, rtnBean.mdseCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.pkgUomCd, rtnBean.custUomCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdLineCatgCd, rtnBean.dsCpoLineCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordQty, rtnBean.ordQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordCustUomQty, rtnBean.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtrnRsnCd, "");
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdlId, configBean.mdlId);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.firstLineAddr_SH, rtnBean.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.scdLineAddr_SH, rtnBean.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctyAddr_SH, rtnBean.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.stCd_SH, rtnBean.shipToStCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.cntyNm_SH, rtnBean.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.postCd_SH, rtnBean.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctryCd_SH, rtnBean.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.slsRepOrSlsTeamTocCd, configBean.slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlWhCd, rtnBean.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.frtCondCd, hdrBean.getFrtCondCd());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaExtnCd, configBean.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
                // QC#18451
//              ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, rtnBean.entDealNetUnitPrcAmt);
                if (isPriceFreezeForAutoAdd && rtnBean.isAutoAddRMA) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, rtnBean.entDealNetUnitPrcAmt);
                }
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.entCpoDtlDealSlsAmt, prcApi2PMsg.xxUnitPrc);

                // 2018/01/09 S21_NA#22371 Add Start
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlSwhCd, rtnBean.rtlSwhCd);
                // 2018/01/09 S21_NA#22371 Add End

                if (rtnBean.hasError(false)) {

                    return false;
                }
                dtlCnt++;
            }

            prcApiPMsg.NWZC157002PMsg.setValidCount(dtlCnt);

            new NWZC157001().execute(prcApiPMsg, onBatchType);

            boolean hasError = false;
            if (addErrorMsgList(prcApiPMsg, hdrBean).size() > 0) {

                hasError = true;
            }

            for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {

                NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);
                String nwzc157002LineNum = nwzc157002PMsg.trxLineNum.getValue();
                String nwzc157002LineSubNum = nwzc157002PMsg.trxLineSubNum.getValue();
                for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {

                    if (CONFIG_CATG.OUTBOUND.equals(nwzc157002PMsg.configCatgCd.getValue())) {

                        DsImptLineBean preLineBean = null;
                        for (DsImptLineBean lineBean : confBean.dsImptOrdLineList) {

                            if (ZYPCommonFunc.hasValue(lineBean.dsImptOrdDtlPk)) {

                                preLineBean = lineBean;
                            }

                            ExpendMdseBean exMdseBean = lineBean.getMdseBean(nwzc157002LineNum, nwzc157002LineSubNum);
                            if (exMdseBean != null) {

                                if (addPrcErrorMsgList(nwzc157002PMsg, preLineBean, hdrBean).size() > 0) { // 10/12/2016

                                    hasError = true;
                                }

                                for (int k = 0; k < nwzc157002PMsg.NWZC157003PMsg.getValidCount(); k++) {

                                    if (addPrcErrorMsgList(nwzc157002PMsg.NWZC157003PMsg.no(k), preLineBean, hdrBean).size() > 0) { // 10/12/2016

                                        hasError = true;
                                    }
                                }
                            }
                        }
                    } else {

                        DsImptRtnLineBean preRtnLineBean = null;
                        for (DsImptRtnLineBean rtnLineBean : confBean.dsImptRtnLineList) {

                            if (ZYPCommonFunc.hasValue(rtnLineBean.dsImptOrdRtrnDtlPk)) {

                                preRtnLineBean = rtnLineBean;
                            }

                            ExpendMdseBean exMdseBean = rtnLineBean.mdseBean;
                            if (exMdseBean.getCpoDtlLineNum().equals(nwzc157002LineNum) && exMdseBean.getCpoDtlLineSubNum().equals(nwzc157002LineSubNum)) {

                                if (addPrcErrorMsgList(nwzc157002PMsg, preRtnLineBean, hdrBean).size() > 0) { // 10/12/2016

                                    hasError = true;
                                }

                                for (int k = 0; k < nwzc157002PMsg.NWZC157003PMsg.getValidCount(); k++) {

                                    if (addPrcErrorMsgList(nwzc157002PMsg.NWZC157003PMsg.no(k), preRtnLineBean, hdrBean).size() > 0) { // 10/12/2016

                                        hasError = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // set api result to detail ant return detail
            hdrBean.setPricingApiResultToDtl(hdrBean.getSlsDt());
            hdrBean.setPricingApiResultToRtnDtl(hdrBean.getSlsDt());

            return !hasError;
        } finally {

            writeEndLogLn("callPricingApi", hdrBean);
        }
    }

    // QC#18451
    private static boolean isPriceFreezeForAutoAdd(ImptHdrBean hdrBean) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, hdrBean.getCpoSrcTpCd());
        tMsg = (CPO_SRC_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return ZYPConstant.FLG_ON_Y.equals(tMsg.prcFrzFlg.getValue());
    }

    private List<DS_IMPT_PRC_CALC_BASETMsg> getFreightCalcBaseTMsg(ImptHdrBean hdrBean) {

        writeStartLogLn("getFreightCalcBaseTMsg", hdrBean);

        List<DS_IMPT_PRC_CALC_BASETMsg> prcCalcBaseTMsgList = new ArrayList<DS_IMPT_PRC_CALC_BASETMsg>();
        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("prcDtlGrpCd", PRC_DTL_GRP.FREIGHT);
            // 2018/06/14 S21_NA#24294 Mod Start
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            
            List<Map<String, Object>> prcCalcBaseList = NWZC226001Query.getInstance().queryMapList("getDsImptPrcCalcBaseInfo", ssmParam);
            
            for (int i = 0; i < prcCalcBaseList.size(); i++) {
                Map<String, Object> prcCalcBase = prcCalcBaseList.get(i);
                DS_IMPT_PRC_CALC_BASETMsg prcCalcBaseTMsg = new DS_IMPT_PRC_CALC_BASETMsg();
                mapData(prcCalcBase, prcCalcBaseTMsg);
                prcCalcBaseTMsgList.add(prcCalcBaseTMsg);
            }

            // for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                // BigDecimal dsImptOrdDtlPk = lineBean.dsImptOrdDtlPk.getValue();
                // if (dsImptOrdDtlPk == null) {

                    // continue;
                // }
                // ssmParam.put("dsImptOrdDtlPk", lineBean.dsImptOrdDtlPk.getValue());

                // List<Map<String, Object>> prcCalcBaseList = NWZC226001Query.getInstance().queryMapList("getDsImptPrcCalcBaseInfo", ssmParam);
                // for (int i = 0; i < prcCalcBaseList.size(); i++) {

                    // BigDecimal dsImptPrcCalcBasePk = (BigDecimal) prcCalcBaseList.get(i);
                    // DS_IMPT_PRC_CALC_BASETMsg prcCalcBaseTMsg = new DS_IMPT_PRC_CALC_BASETMsg();
                    // ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.glblCmpyCd, lineBean.glblCmpyCd.getValue());
                    // ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.dsImptPrcCalcBasePk, dsImptPrcCalcBasePk);
                    // prcCalcBaseTMsg = (DS_IMPT_PRC_CALC_BASETMsg) S21ApiTBLAccessor.findByKey(prcCalcBaseTMsg);
                    // if (null == prcCalcBaseTMsg) {

                        // continue;
                    // }
                    // prcCalcBaseTMsgList.add(prcCalcBaseTMsg);
                // }
            // }
            // 2018/06/14 S21_NA#24294 Mod End

            return prcCalcBaseTMsgList;
        } finally {

            writeEndLogLn("getFreightCalcBaseTMsg", hdrBean);
        }
    }

    private NWZC157001PMsg setPricingApiHdrMsg(ImptHdrBean hdrBean, String xxModeCd) {

        writeStartLogLn("setPricingApiHdrMsg", hdrBean);

        try {
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            // 2018/08/02 S21_NA#26665 mod start
            CPO_SRC_TPTMsg cpoSrcTpTMsg = getCpoSrcTp(hdrBean.getCpoSrcTpCd(), hdrBean.getGlblCmpyCd());
            if (ZYPConstant.FLG_ON_Y.equals(cpoSrcTpTMsg.prcFrzFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, xxModeCd);
            }
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, xxModeCd);
            // 2018/08/02 S21_NA#26665 mod end
            // Mod Start 2018/03/20 QC#24208
//            if (hdrBean.isEdiData()) {
//                ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, hdrBean.getSlsDt());
//            } else {
//                ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, hdrBean.getPrcBaseDt());
//            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, hdrBean.getSlsDt());
            // Mod End 2018/03/20 QC#24208

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, hdrBean.getLineBizTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, hdrBean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, hdrBean.getCpoSrcTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, hdrBean.getCustIssPoNum());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, hdrBean.getNegoDealAmt());
            // Mod Start 2017/09/06 QC#20790
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
            // 2018/07/05 S21_NA#26909 Mod Start
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
            if (hdrBean.isEdiData()) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
            }
            // 2018/07/05 S21_NA#26909 Mod End
            // Mod End 2017/09/06 QC#20790
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, hdrBean.getSpclHdlgTpCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, hdrBean.getLeaseEndTermPrchOptCd());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2017/06/13 QC#18984
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, hdrBean.getDsPmtMethCd());
            // Add End 2017/06/13 QC#18984

            return prcApiPMsg;
        } finally {

            writeEndLogLn("setPricingApiHdrMsg", hdrBean);
        }
    }

    private void setPricingPrcCalcBaseApiMsg(DsImptLineBean lineBean, NWZC157002PMsg prcApi2PMsg, List<DS_IMPT_PRC_CALC_BASETMsg> prcCalcBaseTMsgList) {
        writeStartLogLn("setPricingPrcCalcBaseApiMsg", lineBean);

        try {

            if (!ZYPCommonFunc.hasValue(lineBean.dsImptOrdDtlPk)) {

                return;
            }

            int cnt = 0;
            for (DS_IMPT_PRC_CALC_BASETMsg tMsg : prcCalcBaseTMsgList) {

                if (lineBean.dsImptOrdDtlPk.getValue().equals(tMsg.dsImptOrdDtlPk.getValue())) {

                    NWZC157003PMsg prcApi3PMsg = prcApi2PMsg.NWZC157003PMsg.no(cnt);

                    prcApi3PMsg.ordPrcCalcBasePk.clear();
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.trxLineNum, lineBean.mdseAllList.get(0).getCpoDtlLineNum());
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.trxLineSubNum, lineBean.mdseAllList.get(0).getCpoDtlLineSubNum());
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondTpCd, tMsg.prcCondTpCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.configCatgCd, prcApi2PMsg.configCatgCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcDtlGrpCd, tMsg.prcDtlGrpCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcJrnlGrpCd, tMsg.prcJrnlGrpCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondManEntryFlg, tMsg.prcCondManEntryFlg);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondManAddFlg, tMsg.prcCondManAddFlg);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondManDelFlg, tMsg.prcCondManDelFlg);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.pkgUomCd, tMsg.pkgUomCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondUnitCd, tMsg.prcCondUnitCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCalcMethCd, tMsg.prcCalcMethCd);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.autoPrcAmtRate, tMsg.autoPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.maxPrcAmtRate, tMsg.maxPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.minPrcAmtRate, tMsg.minPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.manPrcAmtRate, tMsg.manPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.calcPrcAmtRate, tMsg.calcPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.unitPrcAmt, tMsg.unitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.dsMdsePrcPk, tMsg.dsMdsePrcPk);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.specCondPrcPk, tMsg.specCondPrcPk);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.frtPerWtPk, tMsg.frtPerWtPk);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.autoPrcCcyCd, tMsg.autoPrcCcyCd);
                    // QC#9700  2018/09/03 Add Start
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcRuleApplyFlg, tMsg.prcRuleApplyFlg);
                    ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcRulePrcdPk, tMsg.prcRulePrcdPk);
                    // QC#9700  2018/09/03 Add End
                    cnt++;
                }
                prcApi2PMsg.NWZC157003PMsg.setValidCount(cnt);
            }

        } finally {

            writeEndLogLn("setPricingPrcCalcBaseApiMsg", lineBean);
        }
    }

    private boolean callDsCpoUpdateApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("callDsCpoUpdateApi", hdrBean);

        try {

            boolean retResult = true;
            // Add Start 2017/11/02 QC#21386
            Map<String, Object> loanCxtCntParam = new HashMap<String, Object>();
            loanCxtCntParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            loanCxtCntParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            loanCxtCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
            BigDecimal loanCxtCnt = NWZC226001Query.getInstance().queryBigDecimal("countOrdCatgBizCtx", loanCxtCntParam);

            boolean isLoan = false;
            if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd())) {
                if (loanCxtCnt.compareTo(BigDecimal.ZERO) > 0) {
                    isLoan = true;
                } else {
                    Map<String, Object> getConfigParam = new HashMap<String, Object>();
                    getConfigParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                    getConfigParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
                    getConfigParam.put("configTpCd", CONFIG_TP.TO_SALES_CONVERSION);
                    // 2019/01/29 S21_NA#30022 Add Start
                    getConfigParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);
                    getConfigParam.put("configCatgIn", CONFIG_CATG.INBOUND);
                    getConfigParam.put("imptLineFlgY", ZYPConstant.FLG_ON_Y);
                    // 2019/01/29 S21_NA#30022 Add End

                    List<Map<String, Object>> configList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdConfigPk", getConfigParam);
                    if (hasValueList(configList)) {
                        isLoan = true;
                    }
                }
            }
            // Add End 2017/11/02 QC#21386

            NWZC150001PMsg cpoUpdApiPMsg = new NWZC150001PMsg();

            // *****************************************************************
            // Header Param
            // *****************************************************************
            String modeCd;
            String rqstTpCd;
            if (hdrBean.isUpdateData()) {

                if (hdrBean.isExistValidatedSts() || hdrBean.isLoanUpgrade() || hdrBean.isLoanDowngrade()) {

                    modeCd = NWZC150001Constant.MODE_SUBMIT;
                } else {

                    modeCd = NWZC150001Constant.MODE_SAVE;
                }
                rqstTpCd = NWZC150001Constant.RQST_TP_DTL_NEW;
            } else {

                if ("B".equals(hdrBean.getOrdCratModeCd())) {
                    modeCd = NWZC150001Constant.MODE_SUBMIT;
                } else {
                    modeCd = NWZC150001Constant.MODE_SAVE;
                }
                rqstTpCd = NWZC150001Constant.RQST_TP_DTL_NEW;
            }

            if (hdrBean.isLoanUpgrade() || hdrBean.isLoanDowngrade()) {
                setCpoUpdApiOrdPMsgForOrig(modeCd, hdrBean, cpoUpdApiPMsg);
            } else {
                setCpoUpdApiOrdPMsg(modeCd, hdrBean, cpoUpdApiPMsg);
            }

            // *****************************************************************
            // Config Param
            // *****************************************************************
            // Add Start 2017/07/20 QC#19802
            List<BigDecimal> origMdlIdList = new ArrayList<BigDecimal>();
            // Add End 2017/07/20 QC#19802
            int configIdx = 0;
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isExistValidatedSts()) {

                    continue;
                }

                // Add Start 2017/07/20 QC#19802
                if (ZYPCommonFunc.hasValue(configBean.mdlId)) {
                    origMdlIdList.add(configBean.mdlId.getValue());
                }
                // Add End 2017/07/20 QC#19802. Mod QC#59719
                setCpoUpdApiConfigPMsg(configBean, cpoUpdApiPMsg, configIdx, origMdlIdList);
                configIdx++;
            }
            cpoUpdApiPMsg.cpoConfig.setValidCount(configIdx);

            // *****************************************************************
            // outbound
            // *****************************************************************
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                    if (lineBean.isExistValidatedSts()) {
                        continue;
                    }

                    if (hdrBean.isLoanToSales() && lineBean.isOrigCpoDtl) {
                        continue;
                    }

                    // Add Start 2019/06/12 QC#50799
                    if (lineBean.isSkipLineSts()) {
                        continue;
                    }
                    // Add End 2019/06/12 QC#50799

                    // *************************************************************
                    // Detail Param
                    // *************************************************************
                    if (hdrBean.isLoanUpgrade() || hdrBean.isLoanDowngrade()) {
                        if (lineBean.isOrigCpoDtl) {
                            rqstTpCd = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                        } else {
                            rqstTpCd = NWZC150001Constant.RQST_TP_DTL_NEW;
                        }
                    }

                    // Mod Start 2017/11/02 QC#21386
//                    if (!setCpoUpdApiDtlPMsg(rqstTpCd, lineBean, cpoUpdApiPMsg)) {
                    if (!setCpoUpdApiDtlPMsg(rqstTpCd, lineBean, cpoUpdApiPMsg, isLoan)) {

                        retResult = false;
                    }
                    // Mod End 2017/11/02 QC#21386

                    // *************************************************************
                    // Detail Price Param
                    // *************************************************************
                    setCpoUpdApiDtlPrcPMsg(lineBean, cpoUpdApiPMsg);
                }
            }

            // *****************************************************************
            // Inbound
            // *****************************************************************
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                for (DsImptRtnLineBean rtnLineBean : configBean.dsImptRtnLineList) {

                    if (rtnLineBean.isExistValidatedSts()) {

                        continue;
                    }

                    // Add Start 2019/06/12 QC#50799
                    if (rtnLineBean.isSkipLineSts()) {

                        continue;
                    }
                    // Add End 2019/06/12 QC#50799

                    // *************************************************************
                    // Detail Param
                    // *************************************************************
                    if (hdrBean.isLoanUpgrade() || hdrBean.isLoanDowngrade()) {
                        if (rtnLineBean.isOrigRtnDtl) {
                            rqstTpCd = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                        } else {
                            rqstTpCd = NWZC150001Constant.RQST_TP_DTL_NEW;
                        }
                    }
                    // 2017/04/18 S21_NA#18360 Add Start
                    if (!setCpoUpdApiRtnPMsg(rqstTpCd, rtnLineBean, cpoUpdApiPMsg)) {

                        retResult = false;
                    }
                    // 2017/04/18 S21_NA#18360 Add End

                    // *************************************************************
                    // Detail Price Param
                    // *************************************************************
                    setCpoUpdApiRtnPrcPMsg(rtnLineBean, cpoUpdApiPMsg);
                }

                // *************************************************************
                // Update Detail Param
                // *************************************************************
                // 2017/07/10 S21_NA#19789 reverce to before QC#19440
                if (hdrBean.isLoanDowngrade() && CONFIG_CATG.INBOUND.equals(configBean.configCatgCd.getValue())) {
                    updateMdseCdForRtn(hdrBean, configBean, cpoUpdApiPMsg);
                }

            }

            // 2017/07/10 S21_NA#19789 Add Start
            if (!hdrBean.isLoanDowngrade()) {
                updateMdseCdForNoLoanRtn(hdrBean, cpoUpdApiPMsg);
            }
            // 2017/07/10 S21_NA#19789 Add End
            // *****************************************************************
            // CPO Sales Credit
            // *****************************************************************
            setCpoUpdApiSlsCrPMsg(hdrBean, cpoUpdApiPMsg);

            // *****************************************************************
            // CPO Delivery
            // *****************************************************************
            setCpoUpdApiDlvyInfoPMsg(hdrBean, cpoUpdApiPMsg);

            // *****************************************************************
            // CPO Site Survey
            // *****************************************************************
            setCpoUpdApiSiteSrvInfoPMsg(hdrBean, cpoUpdApiPMsg);

            // *****************************************************************
            // CPO Install
            // *****************************************************************
            setCpoUpdApiIstlInfoPMsg(hdrBean, cpoUpdApiPMsg);

            // *****************************************************************
            // CPO Config Contact Person
            // *****************************************************************
            setCpoUpdApiCtacPsnPMsg(hdrBean, cpoUpdApiPMsg);

            // 2017/10/02 QC#20625 Del Start
//            if (hdrBean.hasError()) {
//
//                return false;
//            }
            // 2017/10/02 QC#20625 Del End

            // Add Start 2019/05/29 QC#50405
            NWXC150001SalesRep.updateToLatestSalesRep(cpoUpdApiPMsg);
            // Add End 2019/05/29 QC#50405

            // *****************************************************************
            // NWZC150001 : DS CPO Update API
            // *****************************************************************
            final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
            final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

            new NWZC150001().execute(cpoUpdApiPMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, onBatchType);

            if (addErrorMsgListForDsCpoUpdateApi(cpoUpdApiPMsg, hdrBean, null).size() > 0) {

                retResult = false;
            }

            for (DsImptSvcDtlBean dsImptSvcDtlBean : hdrBean.getDsImptSvcDtlList()) {

                if (addErrorMsgListForDsCpoUpdateApi(cpoUpdApiPMsg, hdrBean, dsImptSvcDtlBean).size() > 0) {

                    retResult = false;
                }
            }

            for (NWZC150002PMsg cpoUpdApiPMsg02 : cpoUpdApiOutMsgList) {

                String lineNum = cpoUpdApiPMsg02.cpoDtlLineNum.getValue();
                String lineSubNum = cpoUpdApiPMsg02.cpoDtlLineSubNum.getValue();
                for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                    // 2018/03/05 S21_NA#24103 Add Start
                    if (!CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue())) {
                        continue;
                    }
                    // 2018/03/05 S21_NA#24103 Add End

                    // configuration level
                    if (S21StringUtil.isEmpty(lineNum)) {

                        if (S21StringUtil.isEquals(cpoUpdApiPMsg02.dsOrdPosnNum.getValue(), configBean.dsOrdPosnNum.getValue())) {

                            if (addErrorMsgList(cpoUpdApiPMsg02, configBean).size() > 0) {

                                retResult = false;
                                break;
                            }
                        }
                        continue;
                    }

                    // line level
                    DsImptLineBean preLineBean = null;
                    for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                        if (ZYPCommonFunc.hasValue(lineBean.dsImptOrdDtlPk)) {

                            preLineBean = lineBean;
                        }

                        ExpendMdseBean mdseBean = lineBean.getMdseBean(lineNum, lineSubNum);

                        if (mdseBean != null) {

                            if (addErrorMsgList(cpoUpdApiPMsg02, preLineBean).size() > 0) {

                                retResult = false;
                            }
                        }
                    }
                }
            }

            for (NWZC150003PMsg cpoUpdApiPMsg03 : cpoUpdApiOutMsgList03) {

                String lineNum = cpoUpdApiPMsg03.cpoDtlLineNum.getValue();
                String lineSubNum = cpoUpdApiPMsg03.cpoDtlLineSubNum.getValue();
                for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                    // 2018/03/05 S21_NA#24103 Add Start
                    if (!CONFIG_CATG.INBOUND.equals(configBean.configCatgCd.getValue())) {
                        continue;
                    }
                    // 2018/03/05 S21_NA#24103 Add End

                    // configuration level
                    if (S21StringUtil.isEmpty(lineNum)) {

                        if (S21StringUtil.isEquals(cpoUpdApiPMsg03.dsOrdPosnNum.getValue(), configBean.dsOrdPosnNum.getValue())) {

                            if (addErrorMsgList(cpoUpdApiPMsg03, configBean).size() > 0) {

                                retResult = false;
                                break;
                            }
                        }
                        continue;
                    }

                    DsImptRtnLineBean preRtnLineBean = null;

                    for (DsImptRtnLineBean rtnLineBean : configBean.dsImptRtnLineList) {

                        if (ZYPCommonFunc.hasValue(rtnLineBean.dsImptOrdRtrnDtlPk)) {

                            preRtnLineBean = rtnLineBean;
                        }

                        ExpendMdseBean mdseBean = rtnLineBean.mdseBean;
                        if (mdseBean.getCpoDtlLineNum().equals(lineNum) && mdseBean.getCpoDtlLineSubNum().equals(lineSubNum)) {

                            if (addErrorMsgList(cpoUpdApiPMsg03, preRtnLineBean).size() > 0) {

                                retResult = false;
                            }
                        }
                    }
                }
            }

            // Add Start 2017/07/20 QC#19802
            boolean isMdlErr = false;
            int count = 0;
            for (int i = 0; i < cpoUpdApiPMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg cpoConfigPMsg = cpoUpdApiPMsg.cpoConfig.no(i);
                BigDecimal mdlId;
                if (origMdlIdList.size() == 0) {
                    break;
                }
                // ADD START 2017/12/07 QC#22923
                if(CONFIG_CATG.INBOUND.equals(cpoConfigPMsg.configCatgCd.getValue())){
                    continue;
                }
                // ADD MOD 2017/12/07 QC#22923
                
                if (ZYPCommonFunc.hasValue(cpoConfigPMsg.mdlId)) {
                    mdlId = cpoConfigPMsg.mdlId.getValue();
                } else {
                    continue;
                }
                // Mod Start 2017/08/09 QC#20521
//                if (mdlId.compareTo(origMdlIdList.get(count)) != 0) {
                if (!origMdlIdList.contains(mdlId)) {
                    isMdlErr = true;
                    retResult = false;
                    break;
                }
                // Mod End 2017/08/09 QC#20521
                count++;
            }
            if (isMdlErr) {
                DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2234E);
                hdrBean.dsImptOrdErrList.add(errBean);
            }
            // Add End 2017/07/20 QC#19802

            if (retResult) {

                hdrBean.setNewOrdNum(cpoUpdApiPMsg.cpoOrdNum.getValue());
                writeLogLn("DsImptOrdPk:%.0f -> New CpoOrdNum:%s", hdrBean.getDsImptOrdPk(), cpoUpdApiPMsg.cpoOrdNum.getValue());
            }

            return retResult;

        } finally {

            writeEndLogLn("callDsCpoUpdateApi", hdrBean);
        }
    }

    private void setCpoUpdApiOrdPMsg(String modeCd, ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiOrdPMsg", hdrBean);

        try {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, modeCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());
            if (ZYPCommonFunc.hasValue(hdrBean.getAdminPsnCd())) {

                ZYPEZDItemValueSetter.setValue(pMsg.usrId, hdrBean.getAdminPsnCd());
            } else {

                ZYPEZDItemValueSetter.setValue(pMsg.usrId, PROGRAM_ID);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, PROGRAM_ID);

            // 2018/04/04 QC#22025 Add Start
            pMsg.invCmntTxt.clear();
            // 2018/04/04 QC#22025 Add End

            if (hdrBean.isUpdateData()) {
                ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, hdrBean.getOrigOrdNum());
                // 2018/04/04 QC#22025 Add Start
                ZYPEZDItemValueSetter.setValue(pMsg.invCmntTxt, getOrigOrderInvCmnt(hdrBean.getGlblCmpyCd(), hdrBean.getOrigOrdNum()));
                // 2018/04/04 QC#22025 Add End
            }

            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, hdrBean.getDsOrdRsnCd());
            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, hdrBean.getCustIssPoNum());
            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, hdrBean.getCustIssPoDt());
            ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, hdrBean.getSysSrcCd());
            ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, hdrBean.getCpoSrcTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, hdrBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, hdrBean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, hdrBean.getSoldToCustLocCd());
            ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, hdrBean.getAdminPsnCd());
            if (ZYPCommonFunc.hasValue(hdrBean.getRddDt())) {

                ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, hdrBean.getRddDt());
            } else {

                ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, hdrBean.getSlsDt());
            }
            pMsg.addRsdDt.clear();

            if (hdrBean.isLoanToSales()) {
                ZYPEZDItemValueSetter.setValue(pMsg.addOrigCpoOrdNum, hdrBean.getLoanOrigOrdNum());
            }
            ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
            ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, hdrBean.getDropShipFlg());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, hdrBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, hdrBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, hdrBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, hdrBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, hdrBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, hdrBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, hdrBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, hdrBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, hdrBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, hdrBean.getShipToCtryCd());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, hdrBean.getAddPmtTermCashDiscCd());
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, hdrBean.getCarrAcctNum());
            ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, hdrBean.getOrdSgnDt());
            ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, hdrBean.getSlsRepTocCd());
            if (ZYPCommonFunc.hasValue(hdrBean.getPrcBaseDt())) {

                ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, hdrBean.getPrcBaseDt());
            } else {

                ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, hdrBean.getOrdSrcImptTs_Len8());
            }
            ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, hdrBean.getNegoDealAmt());
            ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, hdrBean.getPrcCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, hdrBean.getFlPrcListCd());
            ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, hdrBean.getCsmpContrNum());
            ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, hdrBean.getPrcContrNum());
            // 2018/01/23 QC#18798 Mod Start
            //ZYPEZDItemValueSetter.setValue(pMsg.aquNum, hdrBean.getAquNum());
            if (paramDsImptOrdConfigPk == null) {
                ZYPEZDItemValueSetter.setValue(pMsg.aquNum, hdrBean.getAquNum());
            } else {
                for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                    ZYPEZDItemValueSetter.setValue(pMsg.aquNum, hdrBean.getAquNum() + '-' + confBean.origDsOrdPosnNum + '(' + confBean.eopsConfigLtrTxt.getValue() + ')');
                }
            }
            // 2018/01/23 QC#18798 Mod End
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, hdrBean.getOrdSrcImptTs_Len8());
            if (hdrBean.isUpdateData()) {

                ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, hdrBean.getOrigCpo().ordSrcRefNum);
            } else {

                // 2018/01/23 QC#18798 Mod Start
                //ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
                if (paramDsImptOrdConfigPk == null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
                } else {
                    for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum() + '-' + confBean.origDsOrdPosnNum + '(' + confBean.eopsConfigLtrTxt.getValue() + ')');
                    }
                }
                // 2018/01/23 QC#18798 Mod End
            }

            ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, convToBigDecimal(hdrBean.getLoanPerDaysAot()));
            ZYPEZDItemValueSetter.setValue(pMsg.leaseCmpyPoNum, hdrBean.getLeaseCmpyPoNum());
            ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, hdrBean.getLeaseEndTermPrchOptCd());
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermCd, hdrBean.getLeaseTermCd());
            ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, hdrBean.getLeasePmtFreqCd());
            ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, hdrBean.getOrdLogTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, hdrBean.getDlrRefNum());
            ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, hdrBean.getFrtCondCd());
            ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, hdrBean.getCarrSvcLvlCd());
            ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, hdrBean.getSpclHdlgTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtChkNum, hdrBean.getPrePmtChkNum());
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtAmt, hdrBean.getPrePmtAmt());
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtTpCd, hdrBean.getPrePmtTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.crRebilRsnCatgCd, hdrBean.getCrRebilRsnCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, ZYPConstant.FLG_ON_Y);
            // 2018/04/04 QC#22025 Del Start
//            pMsg.invCmntTxt.clear();
            // 2018/04/04 QC#22025 Del End
            ZYPEZDItemValueSetter.setValue(pMsg.dclnSvcCd, hdrBean.getDclnSvcCd());
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, hdrBean.getLeaseTermMthAot());
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptTs, hdrBean.getOrdSrcImptTs());
            ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, hdrBean.getDsPmtMethCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            // 2018/01/31 QC#22776-2 add start
            ZYPEZDItemValueSetter.setValue(pMsg.sellToFirstRefCmntTxt, hdrBean.getSellToFirstRefCmntTxt());
            // 2018/01/31 QC#22776-2 add start
        } finally {

            writeEndLogLn("setCpoUpdApiOrdPMsg", hdrBean);
        }
    }

    private void setCpoUpdApiOrdPMsgForOrig(String modeCd, ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiOrdPMsgForOrig", hdrBean);

        try {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, modeCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());

            if (ZYPCommonFunc.hasValue(hdrBean.getAdminPsnCd())) {
                ZYPEZDItemValueSetter.setValue(pMsg.usrId, hdrBean.getAdminPsnCd());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.usrId, PROGRAM_ID);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, PROGRAM_ID);

            // 2018/04/04 QC#22025 Add Start
            pMsg.invCmntTxt.clear();
            // 2018/04/04 QC#22025 Add End

            if (hdrBean.isUpdateData()) {
                ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, hdrBean.getOrigOrdNum());
                // 2018/04/04 QC#22025 Add Start
                ZYPEZDItemValueSetter.setValue(pMsg.invCmntTxt, getOrigOrderInvCmnt(hdrBean.getGlblCmpyCd(), hdrBean.getOrigOrdNum()));
                // 2018/04/04 QC#22025 Add End
            }

            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, hdrBean.getOrigCpo().dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, hdrBean.getOrigCpo().dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, hdrBean.getOrigCpo().dsOrdRsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, hdrBean.getOrigCpo().billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, hdrBean.getOrigCpo().shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, hdrBean.getOrigCpo().soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, hdrBean.getOrigCpo().carrAcctNum);
            ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, hdrBean.getOrigCpo().ordSgnDt);
            ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, hdrBean.getOrigCpo().prcBaseDt);
            ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, hdrBean.getOrigCpo().negoDealAmt);
            ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, hdrBean.getOrigCpo().prcCatgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, hdrBean.getOrigCpo().flPrcListCd);
            ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, hdrBean.getOrigCpo().csmpContrNum);
            ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, hdrBean.getOrigCpo().prcContrNum);
            ZYPEZDItemValueSetter.setValue(pMsg.aquNum, hdrBean.getOrigCpo().aquNum);
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, hdrBean.getOrigCpo().ordSrcRefNum);
            ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, hdrBean.getOrigCpo().loanPerDaysAot);
            ZYPEZDItemValueSetter.setValue(pMsg.leaseCmpyPoNum, hdrBean.getOrigCpo().leaseCmpyPoNum);
            ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, hdrBean.getOrigCpo().leasePrchOptCd);
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermCd, hdrBean.getOrigCpo().leaseTermCd);
            ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, hdrBean.getOrigCpo().leasePmtFreqCd);
            ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, hdrBean.getOrigCpo().ordLogTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, hdrBean.getOrigCpo().dlrRefNum);
            ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, hdrBean.getOrigCpo().frtCondCd);
            ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, hdrBean.getOrigCpo().carrSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, hdrBean.getOrigCpo().spclHdlgTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtChkNum, hdrBean.getOrigCpo().prePmtChkNum);
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtAmt, hdrBean.getOrigCpo().prePmtAmt);
            ZYPEZDItemValueSetter.setValue(pMsg.prePmtTpCd, hdrBean.getOrigCpo().prePmtTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.crRebilRsnCatgCd, hdrBean.getOrigCpo().crRebilRsnCatgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dclnSvcCd, hdrBean.getOrigCpo().dclnSvcCd);
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, hdrBean.getOrigCpo().leaseTermMthAot);
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, hdrBean.getOrigCpo().ordSrcImptDt);
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptTs, hdrBean.getOrigCpo().ordSrcImptTs);
            ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, hdrBean.getOrigCpo().dsPmtMethCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, hdrBean.getOrigCpo().slsRepTocCd);

            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, hdrBean.getOrigCpo().custIssPoNum);
            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, hdrBean.getOrigCpo().custIssPoDt);
            ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, hdrBean.getOrigCpo().sysSrcCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, hdrBean.getOrigCpo().cpoSrcTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, hdrBean.getOrigCpo().ordFuflLvlCd);
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, hdrBean.getOrigCpo().billToCustCd);
            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, hdrBean.getOrigCpo().sellToCustCd);
            ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, hdrBean.getOrigCpo().adminPsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, hdrBean.getOrigCpo().addRddDt);
            ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, hdrBean.getOrigCpo().addShpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, hdrBean.getOrigCpo().addDropShipFlg);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, hdrBean.getOrigCpo().addShipToCustCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, hdrBean.getOrigCpo().addShipToLocNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, hdrBean.getOrigCpo().addShipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, hdrBean.getOrigCpo().addShipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, hdrBean.getOrigCpo().addShipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, hdrBean.getOrigCpo().addShipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, hdrBean.getOrigCpo().addShipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, hdrBean.getOrigCpo().addShipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, hdrBean.getOrigCpo().addShipToStCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, hdrBean.getOrigCpo().addShipToProvNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, hdrBean.getOrigCpo().addShipToCntyNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, hdrBean.getOrigCpo().addShipToPostCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, hdrBean.getOrigCpo().addShipToCtryCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, hdrBean.getOrigCpo().addShipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, hdrBean.getOrigCpo().addShipTo02RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, hdrBean.getOrigCpo().addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, hdrBean.getOrigCpo().prcCalcDt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, hdrBean.getOrigCpo().sendInvFlg);
            ZYPEZDItemValueSetter.setValue(pMsg.addRsdDt, hdrBean.getOrigCpo().addRsdDt);
            // 2018/04/04 QC#22025 Add Start
//            pMsg.invCmntTxt.clear();
            // 2018/04/04 QC#22025 Add End

        } finally {

            writeEndLogLn("setCpoUpdApiOrdPMsgForOrig", hdrBean);
        }
    }

    // Mod QC#59719 Add Parameter
    private void setCpoUpdApiConfigPMsg(DsImptOrdConfigBean configBean, NWZC150001PMsg pMsg, int idx, List<BigDecimal> origMdlIdList) {

        writeStartLogLn("setCpoUpdApiConfigPMsg", configBean);

        try {

            ImptHdrBean hdrBean = configBean.imptHdrBean;

            // 2018/12/3 QC#29362 Del Start
            // 2018/11/08 S21_NA#28951 Add Start
//            DsImptLineBean lineBean = configBean.dsImptOrdLineList.get(0);
            // 2018/11/08 S21_NA#28951 Add Start
            // 2018/12/3 QC#29362 Del End

            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(idx);

            if (configBean.isUpdateData()) {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_MODIFY);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, configBean.origDsCpoConfTMsg.dsCpoConfigPk);
            } else {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
                cpoConfigPMsg.dsCpoConfigPk.clear();
            }
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, configBean.configTpCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, configBean.svcConfigMstrPk);
            // QC#59719 Start
            DsImptLineBean dtlLine = null;
            for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {
                if (ZYPCommonFunc.hasValue(lineBean.dsOrdPosnNum) && lineBean.dsOrdPosnNum.getValue().equals(configBean.dsOrdPosnNum.getValue())) {
                    dtlLine = lineBean;
                    break;
                }
            }
            // QC#59719-1 Start. QC#60296 Add Null check.
            if (dtlLine != null && ZYPCommonFunc.hasValue(configBean.configCatgCd) && CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue())) {
                DsImptOrdConfigBean origConfigBean = isAddAccessory(hdrBean, dtlLine);
                if (origConfigBean != null) {
                    SVC_CONFIG_MSTRTMsg svcConfigMstrMsg = new SVC_CONFIG_MSTRTMsg();
                    ZYPEZDItemValueSetter.setValue(svcConfigMstrMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(svcConfigMstrMsg.svcConfigMstrPk, origConfigBean.svcConfigMstrPk);
                    svcConfigMstrMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcConfigMstrMsg);
    
                    if (svcConfigMstrMsg != null) {
                        origMdlIdList.add(svcConfigMstrMsg.mdlId.getValue());
                        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, svcConfigMstrMsg.mdlId);
                    } else {
                        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, configBean.mdlId);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, configBean.mdlId);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, configBean.mdlId);
            }
            // QC#59719-1 end
            // QC#59719 End
            if (ZYPCommonFunc.hasValue(configBean.billToCustAcctCd)) {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, configBean.billToCustAcctCd);
            } else {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
            }
            if (ZYPCommonFunc.hasValue(configBean.billToCustLocCd)) {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, configBean.billToCustLocCd);
            } else {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, hdrBean.getBillToCustCd());
            }
            if (ZYPCommonFunc.hasValue(configBean.shipToCustAcctCd)) {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, configBean.shipToCustAcctCd);
            } else {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
            }
            // 2018/11/08 S21_NA#28951 Mod Start
            if (DS_ORD_TP_INVENTORY_TRANSFER.equals(hdrBean.getDsOrdTpCd())
              || DS_ORD_TP_INVENTORY_TRANSFER_CONFIG.equals(hdrBean.getDsOrdTpCd())) {
                // 2018/12/3 QC#29362 Add Start
                DsImptLineBean lineBean = configBean.dsImptOrdLineList.get(0);
                // 2018/12/3 QC#29362 Add End
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, lineBean.shipToCustCd);
//              if (ZYPCommonFunc.hasValue(configBean.shipToCustLocCd)) {
            } else if (ZYPCommonFunc.hasValue(configBean.shipToCustLocCd)) {
            // 2018/11/08 S21_NA#28951 Mod End

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, configBean.shipToCustLocCd);
            } else {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, hdrBean.getShipToCustCd());
            }
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, configBean.dropShipFlg);
            if (configBean.hasShipToCustInfo()) {

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, configBean.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, configBean.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, configBean.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, configBean.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, configBean.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, configBean.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, configBean.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, configBean.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, configBean.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, configBean.shipToStCd);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, configBean.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, configBean.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, configBean.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, configBean.shipToCtryCd);
            } else {

                Map<String, Object> custInfo = getShipInfo(hdrBean.getGlblCmpyCd(), hdrBean.getShipToCustCd());

                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, convToString(custInfo.get("LOC_NM")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, convToString(custInfo.get("ADDL_LOC_NM")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, convToString(custInfo.get("FIRST_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, convToString(custInfo.get("SCD_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, convToString(custInfo.get("THIRD_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, convToString(custInfo.get("FRTH_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, convToString(custInfo.get("FIRST_REF_CMNT_TXT")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, convToString(custInfo.get("SCD_REF_CMNT_TXT")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, convToString(custInfo.get("CTY_ADDR")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, convToString(custInfo.get("ST_CD")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, convToString(custInfo.get("PROV_NM")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, convToString(custInfo.get("CNTY_NM")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, convToString(custInfo.get("POST_CD")));
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, convToString(custInfo.get("CTRY_CD")));

            }
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dclnSvcCd, configBean.dclnSvcCd);

            if (!S21StringUtil.isEquals(cpoConfigPMsg.dclnSvcCd.getValue(), ZYPConstant.FLG_ON_1)) {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dclnSvcCd, ZYPConstant.FLG_OFF_0);
            }

        } finally {

            writeEndLogLn("setCpoUpdApiConfigPMsg", configBean);
        }
    }

    private boolean setCpoUpdApiDtlPMsg(String rqstTpCd, DsImptLineBean lineBean, NWZC150001PMsg pMsg, boolean isLoan) {

        writeStartLogLn("setCpoUpdApiDtlPMsg", lineBean);

        try {

            ImptHdrBean hdrBean = lineBean.imptHdrBean;
            DsImptOrdConfigBean configBean = lineBean.dsImptOrdConfigBean;
            int idx = pMsg.A.getValidCount();
            NWZC157004PMsg priceApiResult;
            BigDecimal unitNetWt, prcListAmt;
            NWZC150001_APMsg dsCpoUpdDtlPMsg;

            //Map<String, Object> lineValSet = null;
            List<Map<String, Object>> lineValSet = new ArrayList<Map<String, Object>>(); // 2017/09/04 S21_NA#19800 Mod
            if (hdrBean.isLoanToSales()) {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                //ssmParam.put("ordLineCtxTpCd", ORD_LINE_CTX_TP.LOAN_CONV_LINE);
                ssmParam.put("ordLineCtxTpCd", LOAN_CONV_LINE_CRAT); // 2017/09/04 S21_NA#19800 Mod
                ssmParam.put("actionNm", LOAN_ORD_ACTION_LOAN_SELL);
                //lineValSet = NWZC226001Query.getInstance().queryMap("getLineValSet", ssmParam);
                lineValSet = NWZC226001Query.getInstance().queryMapList("getLineValSet", ssmParam); // 2017/09/04 S21_NA#19800 Mod

                if (lineValSet == null || lineValSet.size() < 0) {
                    return false;
                }
            }

            for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                dsCpoUpdDtlPMsg = pMsg.A.no(idx);

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxRqstTpCd_A1, rqstTpCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.cpoDtlLineNum_A1, mdseBean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.cpoDtlLineSubNum_A1, mdseBean.getCpoDtlLineSubNum());
                // Mod Start 2017/11/02 QC#21386
                String mdseCd = mdseBean.getMdseCd();
                if (isLoan) {
                    MDSETMsg mdseTMsg = getMdse(lineBean.glblCmpyCd.getValue(), mdseCd);
                    mdseCd = mdseTMsg.mdseCd.getValue();
                }
                // 2017/12/28 S21_NA#23083 Add Start
                if (hdrBean.isLoanToSales()) {
                    SHPG_PLNTMsg origShpgPln = getOrigShpgPln(hdrBean, mdseBean);
                    if (origShpgPln != null) {
                        mdseCd = origShpgPln.mdseCd.getValue();
                    }
                }
                // 2017/12/28 S21_NA#23083 Add End
//                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.mdseCd_A1, mdseBean.getMdseCd());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.mdseCd_A1, mdseCd);
                if (isLoan) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.origMdseCd_A1, mdseBean.getMdseCd());
                } else if (mdseBean.getParentBean() == null) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.origMdseCd_A1, lineBean.origMdseCd);
                }
                // Mod End 2017/11/02 QC#21386
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dropShipFlg_A1, configBean.dropShipFlg);
                if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(hdrBean.getCpoSrcTpCd()) && isWHTransfer(hdrBean)) {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCustCd_A1, lineBean.shipToCustCd);
                } else if (ZYPCommonFunc.hasValue(configBean.shipToCustLocCd)) {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCustCd_A1, configBean.shipToCustLocCd);
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCustCd_A1, lineBean.imptHdrBean.getShipToCustCd());
                }
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToLocNm_A1, configBean.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToAddlLocNm_A1, configBean.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToFirstLineAddr_A1, configBean.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToScdLineAddr_A1, configBean.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToThirdLineAddr_A1, configBean.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToFrthLineAddr_A1, configBean.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCtyAddr_A1, configBean.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToStCd_A1, configBean.shipToStCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToProvNm_A1, configBean.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToPostCd_A1, configBean.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCtryCd_A1, configBean.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToCntyNm_A1, configBean.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToFirstRefCmntTxt_A1, configBean.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipToScdRefCmntTxt_A1, configBean.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordQty_A1, mdseBean.getCalcMdseQty());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordCustUomQty_A1, mdseBean.getCalcUomQty());
                // Del Start 2019/11/07 QC#54511
//                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.invtyLocCd_A1, lineBean.invtyLocCd);
                // Del End 2019/11/07 QC#54511
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.entDealNetUnitPrcAmt_A1, lineBean.entDealNetUnitPrcAmt);

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ccyCd_A1, lineBean.ccyCd);
                if (ZYPCommonFunc.hasValue(lineBean.rddDt)) {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rddDt_A1, lineBean.rddDt);
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rddDt_A1, hdrBean.getRddDt());
                }
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rsdDt_A1, lineBean.rsdDt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shipCpltCd_A1, lineBean.shipCpltCd);
                if (ZYPCommonFunc.hasValue(lineBean.stkStsCd)) {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.stkStsCd_A1, lineBean.stkStsCd);
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.stkStsCd_A1, STK_STS.GOOD);
                }
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.slsRepOrSlsTeamTocCd_A1, configBean.slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.custMdseCd_A1, lineBean.custMdseCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.custUomCd_A1, lineBean.custUomCd);
                // 2019/01/29 S21_NA#30022 Mod Start
                // ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdPosnNum_A1, lineBean.dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdPosnNum_A1, configBean.dsOrdPosnNum);
                // 2019/01/29 S21_NA#30022 Mod End
                // 2018/01/23 QC#18798 Add Start
                if (paramDsImptOrdConfigPk != null) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdPosnNum_A1, DS_ORD_POSN_NUM_1);
                }
                // 2018/01/23 QC#18798 Add End

                unitNetWt = getUnitNetWt(hdrBean.getGlblCmpyCd(), mdseBean);
                if (unitNetWt == null) {

                    return false;
                }
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.unitNetWt_A1, unitNetWt);
                priceApiResult = hdrBean.getPricingApiResult4(lineBean);
                // 2017/11/01 S21_NA#22184 Mod Start
//                if (mdseBean.getParentBean() == null) {
                if (mdseBean.getParentBean() == null && priceApiResult != null) {
                // 2017/11/01 S21_NA#22184 Mod Start

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotBaseAmt_A1, priceApiResult.xxTotAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotDiscAmt_A1, priceApiResult.xxTotDiscAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotNetDiscAmt_A1, priceApiResult.xxTotNetDiscAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotNetPrcAmt_A1, priceApiResult.xxTotNetPrcAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotFrtAmt_A1, priceApiResult.xxTotFrtAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotTaxAmt_A1, priceApiResult.xxTotTaxAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxSlsAmt_A1, priceApiResult.xxSlsAmt);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotAmt_A1, priceApiResult.xxTotAmt);
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotBaseAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotDiscAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotNetDiscAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotNetPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotFrtAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotTaxAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxSlsAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotAmt_A1, BigDecimal.ZERO);
                }
                dsCpoUpdDtlPMsg.vndInvtyLocCd_A1.clear();
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.frtCondCd_A1, hdrBean.getFrtCondCd());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsContrNum_A1, lineBean.dsContrNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsContrSqNum_A1, lineBean.dsContrSqNum);
                dsCpoUpdDtlPMsg.dsCpoConfigPk_A1.clear();
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsCpoLineNum_A1, convToBigDecimal(mdseBean.getDsCpoLineNum()));
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsCpoLineSubNum_A1, convToBigDecimal(mdseBean.getDsCpoLineSubNum()));

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordLineSrcCd_A1, lineBean.ordLineSrcCd);
                if (!ZYPCommonFunc.hasValue(dsCpoUpdDtlPMsg.ordLineSrcCd_A1)) {

                    MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(hdrBean.getGlblCmpyCd(), mdseBean.getMdseCd());
                    if (mdseTMsg != null) {
                        if (S21StringUtil.isEquals(mdseTMsg.invtyCtrlFlg.getValue(), ZYPConstant.FLG_OFF_N)) {

                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordLineSrcCd_A1, ORD_LINE_SRC.INTERNAL);
                        }
                    }
                }

                if (hdrBean.isLoanToSales() && !lineBean.isLoanConvAddFlg) {
                    // Mod Start 2018/07/24 QC#26828
//                    // 2017/09/04 S21_NA#19800 add start
//                    Map<String,Object> lineVal = null;
//
//                    if (!ZYPCommonFunc.hasValue(mdseBean.getOrigCpoDtlLineNum()) || !ZYPCommonFunc.hasValue(mdseBean.getOrigCpoDtlLineSubNum())) {
//                        lineVal = lineValSet.get(1);
//                    } else {
//                        lineVal = lineValSet.get(0);
//                    }
//                    // 2017/09/04 S21_NA#19800 add end
//
//                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, (String) lineVal.get("DS_ORD_LINE_CATG_CD"));
//                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlWhCd_A1, (String) lineVal.get("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);
                    // Mod Start 2019/11/05 QC#54511
//                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlWhCd_A1, lineBean.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlWhCd_A1, getRtlWhCd(lineValSet, lineBean));
                    // Mod End 2019/11/05 QC#54511
                    // Mod End 2018/07/24 QC#26828
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.origCpoOrdNum_A1, hdrBean.getLoanOrigOrdNum());
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.origCpoDtlLineNum_A1, mdseBean.getOrigCpoDtlLineNum());
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.origCpoDtlLineSubNum_A1, mdseBean.getOrigCpoDtlLineSubNum());
                    // 2017/04/18 S21_NA#18360 Add Start
                    if (ZYPCommonFunc.hasValue(lineBean.svcMachMstrPk)) {
                        updateMdseCdForLoanToSales(dsCpoUpdDtlPMsg, lineBean);
                    }
                    // 2017/04/18 S21_NA#18360 Add End
                } else {

                    if (hdrBean.isLoanNew()) {
                        // QC#22443 2017/11/28 Add Start
                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);

                        ALL_MDSE_VTMsg allMdseV = getAllMdseV(hdrBean.getGlblCmpyCd(), dsCpoUpdDtlPMsg.mdseCd_A1.getValue());
                        if(!ZYPConstant.FLG_ON_Y.equals(allMdseV.invtyCtrlFlg.getValue()) || !ZYPConstant.FLG_ON_Y.equals(allMdseV.instlBaseCtrlFlg.getValue())){
                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, DS_ORD_LINE_CATG.MERCHANDISE);
                        }
                        // QC#22443 2017/11/28 Add End
                        Map<String, Object> ssmParam = new HashMap<String, Object>();
                        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                        ssmParam.put("mdseCd", lineBean.mdseCd.getValue() + PERCENT);
                        ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.DEFAULT_LINE_CATEGORY);
                        String mdseValSetLineCatgCd = NWZC226001Query.getInstance().queryString("getMdseValSet", ssmParam);

                        if (ZYPCommonFunc.hasValue(mdseValSetLineCatgCd)) {
                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, mdseValSetLineCatgCd);
                        // QC#22443 2017/11/28 Del Start
                        // } else {
                        //     ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);
                        // QC#22443 2017/11/28 Del End
                        }
                    } else if (hdrBean.isLoanToSales() && lineBean.isLoanConvAddFlg) {
                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, DS_ORD_LINE_CATG.MERCHANDISE);
                    } else {
                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);
                    }

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlWhCd_A1, lineBean.rtlWhCd);
                }

                // Update dsOrdLineCatgCd_A1
                // QC#22443 2017/11/28 Mod Start
                //if (ZYPCommonFunc.hasValue(lineBean.dsOrdLineCatgCd)) {
                //    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);
                //}
                if (!ZYPCommonFunc.hasValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1, lineBean.dsOrdLineCatgCd);
                }
                // QC#22443 2017/11/28 Mod End

                if (!S21StringUtil.isEquals(dsCpoUpdDtlPMsg.dsOrdLineCatgCd_A1.getValue(), DS_ORD_LINE_CATG.BILL_ONLY)) { // 2017/09/04 S21_NA#19800 add
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlSwhCd_A1, lineBean.rtlSwhCd);
                }

                // Add Start 2019/11/07 QC#54511
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.invtyLocCd_A1, setInvtyLocCd(dsCpoUpdDtlPMsg.rtlWhCd_A1.getValue(), dsCpoUpdDtlPMsg.rtlSwhCd_A1.getValue()));
                // Add End 2019/11/07 QC#54511
                // Q#58230
                if (ZYPCommonFunc.hasValue(configBean.prodCondCd)) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rtlSwhCd_A1, lineBean.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.prodCondCd, configBean.prodCondCd);
                }

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.serNum_A1, lineBean.serNum);

                if (ZYPCommonFunc.hasValue(lineBean.flPrcListCd)) {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.flPrcListCd_A1, lineBean.flPrcListCd);
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.flPrcListCd_A1, hdrBean.getFlPrcListCd());
                }

                if (mdseBean.getParentBean() == null) {

                    if (ZYPCommonFunc.hasValue(lineBean.dealPrcListPrcAmt)) {

                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealPrcListPrcAmt_A1, lineBean.dealPrcListPrcAmt);
                    } else {

                        if (lineBean.getBasePrice() != null) {

                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealPrcListPrcAmt_A1, lineBean.getBasePrice().autoPrcAmtRate);
                        }
                    }
                    if (ZYPCommonFunc.hasValue(lineBean.funcPrcListPrcAmt)) {

                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcPrcListPrcAmt_A1, lineBean.funcPrcListPrcAmt);
                    } else {

                        if (lineBean.getBasePrice() != null) {

                            prcListAmt = lineBean.getBasePrice().autoPrcAmtRate.getValue();
                            prcListAmt = NWXC220001.exchFuncUnitPrice(hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), lineBean.ccyCd.getValue(), prcListAmt, prcListAmt);
                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcPrcListPrcAmt_A1, prcListAmt);
                        }
                    }
                } else {

                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealPrcListPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcPrcListPrcAmt_A1, BigDecimal.ZERO);
                }

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.refCpoDtlLineNum_A1, mdseBean.getRefCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.refCpoDtlLineSubNum_A1, mdseBean.getRefCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dplyLineRefNum_A1, mdseBean.getDplyLineRefNum());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.crRebilCd_A1, lineBean.crRebilCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.prcBaseDt_A1, lineBean.prcBaseDt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyTpCd_A1, lineBean.splyTpCd);
                // 2019/10/04 S21_NA#51372 Mod Start
                //ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyNm_A1, lineBean.splyNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.prntVndNm_A1, lineBean.prntVndNm);
                // 2019/10/04 S21_NA#51372 Mod End
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyFirstAddr_A1, lineBean.splyFirstAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyCtyAddr_A1, lineBean.splyCtyAddr);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyStCd_A1, lineBean.splyStCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.splyPostCd_A1, lineBean.splyPostCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.csmpContrNum_A1, lineBean.csmpContrNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dlrRefNum_A1, lineBean.dlrRefNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.csmpPrcListCd_A1, lineBean.csmpPrcListCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.rntlTrmnDt_A1, lineBean.rntlTrmnDt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.bllgAttrbCustAcctCd_A1, hdrBean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.firstBllgAttrbNm_A1, lineBean.firstBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.firstBllgAttrbValTxt_A1, lineBean.firstBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.scdBllgAttrbNm_A1, lineBean.scdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.scdBllgAttrbValTxt_A1, lineBean.scdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.thirdBllgAttrbNm_A1, lineBean.thirdBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.thirdBllgAttrbValTxt_A1, lineBean.thirdBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.frthBllgAttrbNm_A1, lineBean.frthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.frthBllgAttrbValTxt_A1, lineBean.frthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.fifthBllgAttrbNm_A1, lineBean.fifthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.fifthBllgAttrbValTxt_A1, lineBean.fifthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.sixthBllgAttrbNm_A1, lineBean.sixthBllgAttrbNm);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.sixthBllgAttrbValTxt_A1, lineBean.sixthBllgAttrbValTxt);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.sbstMdseCd_A1, lineBean.sbstMdseCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordSrcRefLineNum_A1, lineBean.ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordSrcRefLineSubNum_A1, lineBean.ordSrcRefLineSubNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.carrSvcLvlCd_A1, hdrBean.getCarrSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.supdLockFlg_A1, lineBean.supdLockFlg);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.prcListEquipConfigNum_A1, lineBean.prcListEquipConfigNum);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.prcCatgCd_A1, lineBean.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.loanBalQty_A1, lineBean.loanBalQty);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.svcConfigMstrPk_A1, lineBean.dsImptOrdConfigBean.svcConfigMstrPk);

                // Add Start 2018/04/16 QC#22187
                if (ZYPCommonFunc.hasValue(hdrBean.getCpoSrcTpCd()) && CPO_SRC_TP.ORDER_UPLOAD.equals(hdrBean.getCpoSrcTpCd())) {
                    for (int i = 0;i < hdrBean.getOrigCpoDtlArray().getValidCount(); i++) {
                        CPO_DTLTMsg origCpoDtlTMsg = hdrBean.getOrigCpoDtlArray().no(i);
                        if (mdseBean.getCpoDtlLineNum().equals(origCpoDtlTMsg.cpoDtlLineNum.getValue()) && mdseBean.getCpoDtlLineSubNum().equals(origCpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.cpoSrcTpCd_A1, origCpoDtlTMsg.cpoSrcTpCd);
                            break;
                        } else {
                            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.cpoSrcTpCd_A1, hdrBean.getCpoSrcTpCd());
                        }
                    }
                }
                // Add Start 2018/04/16 QC#22187
                if (S21StringUtil.isEquals(hdrBean.getCpoSrcTpCd(), CPO_SRC_TP.ORDER_UPLOAD)) {

                    if (configBean.isUpdateData()) {

                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordUpldRefNum_A1, hdrBean.getOrigCpo().ordSrcRefNum);
                    } else {

                        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordUpldRefNum_A1, hdrBean.getOrdSrcRefNum());
                    }
                } else {

                    dsCpoUpdDtlPMsg.ordUpldRefNum_A1.clear();
                }
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.baseCmptFlg_A1, mdseBean.getBaseCmptFlg());
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.svcMachMstrPk_A1, lineBean.svcMachMstrPk);

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaCmpyCd_A1, lineBean.coaCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaAfflCd_A1, lineBean.coaAfflCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaBrCd_A1, lineBean.coaBrCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaChCd_A1, lineBean.coaChCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaCcCd_A1, lineBean.coaCcCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaAcctCd_A1, lineBean.coaAcctCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaProjCd_A1, lineBean.coaProjCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaExtnCd_A1, lineBean.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.coaProdCd_A1, lineBean.coaProdCd);

                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.shopItemFlg_A1, ZYPConstant.FLG_OFF_N);

                // 2018/01/16 S21_NA#22372 Add Start
                ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcUnitFlPrcAmt_A1, lineBean.funcUnitFlPrcAmt);
                // 2018/01/16 S21_NA#22372 Add End

                // QC#18366 ADD
                if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(hdrBean.getCpoSrcTpCd())) {
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.carrCd_A1, hdrBean.getCarrCd());
                }

                // 2017/08/01 S21_NA#20097 Add Start
                setTrnsfAmt(dsCpoUpdDtlPMsg, hdrBean, lineBean.dsImptOrdDtlPk.getValue());
                // 2017/08/01 S21_NA#20097 Add End

                // 2018/02/05 QC#23329 Add Start
                if (mdseBean.getSoftwarePrnt()) {
                    dsCpoUpdDtlPMsg.invtyLocCd_A1.clear();
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.ordQty_A1, BigDecimal.ONE); // 2018/02/28 QC#24115 Add
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotBaseAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotDiscAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotNetPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotFrtAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotTaxAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxSlsAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.xxTotAmt_A1, BigDecimal.ZERO);
                    dsCpoUpdDtlPMsg.rtlWhCd_A1.clear();
                    dsCpoUpdDtlPMsg.rtlSwhCd_A1.clear();
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcUnitFlPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealPrcListPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcPrcListPrcAmt_A1, BigDecimal.ZERO);
                    dsCpoUpdDtlPMsg.bllgAttrbCustAcctCd_A1.clear();
                    dsCpoUpdDtlPMsg.ordSrcRefLineNum_A1.clear();
                    ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_OFF_N);
                }
                // 2018/02/05 QC#23329 Add End

                idx++;
            }

            pMsg.A.setValidCount(idx);

            return true;
        } finally {

            writeEndLogLn("setCpoUpdApiDtlPMsg", lineBean);
        }
    }

    // 2017/08/01 S21_NA#20097 Add Start
    private void setTrnsfAmt(NWZC150001_APMsg dsCpoUpdDtlPMsg, ImptHdrBean hdrBean, BigDecimal dsImptOrdDtlPk) {
        // 2018/06/13 S21_NA#24294 Mod Start
        // if (dsImptOrdDtlPk == null) {
        if (dsImptOrdDtlPk == null || !existSvcRef(hdrBean, dsImptOrdDtlPk)) {
            return;
        }
        // 2018/06/13 S21_NA#24294 Mod End

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsImptOrdDtlPk", dsImptOrdDtlPk);
        Map<String, Object> trnsfAmtSet = NWZC226001Query.getInstance().queryMap("getTrnsfAmt", ssmParam);

        if (trnsfAmtSet == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealSvcRevTrnsfAmt_A1, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_REV_TRNSF_AMT"));
        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.dealSvcCostTrnsfAmt_A1, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_COST_TRNSF_AMT"));
        // 2017/08/21 S21_NA#20097-5 Add Start
        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcSvcRevTrnsfAmt_A1, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_REV_TRNSF_AMT"));
        ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.funcSvcCostTrnsfAmt_A1, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_COST_TRNSF_AMT"));
        // 2017/08/21 S21_NA#20097-5 Add End
    }
    // 2017/08/01 S21_NA#20097 Add End
    
    // 2018/06/13 S21_NA#24294 Add Start
    private boolean existSvcRef(ImptHdrBean hdrBean, BigDecimal dsImptOrdDtlPk){
        for(DsImptSvcDtlBean svcDtlBean: hdrBean.getDsImptSvcDtlList()){
            for(DS_IMPT_SVC_CONFIG_REFTMsg tMsg : svcDtlBean.svcConfigRefMsgList){
                if(dsImptOrdDtlPk.compareTo(tMsg.dsImptOrdDtlPk.getValue()) == 0){
                    return true;
                }
            }
        }
        return false;
    }
    // 2018/06/13 S21_NA#24294 Add End

    // 2017/04/18 S21_NA#18360 Add Start
    private void updateMdseCdForLoanToSales(NWZC150001_APMsg dsCpoUpdDtlPMsg, DsImptLineBean lineBean) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, lineBean.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, lineBean.svcMachMstrPk);
        tMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);

        if (null != tMsg) {
            ZYPEZDItemValueSetter.setValue(dsCpoUpdDtlPMsg.mdseCd_A1, tMsg.mdseCd);
        }
    }
    // 2017/04/18 S21_NA#18360 Add End

    private boolean setCpoUpdApiDtlPrcPMsg(DsImptLineBean lineBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiDtlPrcPMsg", lineBean);

        try {

            ImptHdrBean hdrBean = lineBean.imptHdrBean;
            NWZC157002PMsg priceApiResult = hdrBean.getPricingApiResult2(lineBean);
            NWZC157003PMsg prcMsg;
            NWZC150001_priceListPMsg apiMsg;
            int idx = pMsg.priceList.getValidCount();

            for (int i = 0; i < priceApiResult.NWZC157003PMsg.getValidCount(); i++) {

                prcMsg = priceApiResult.NWZC157003PMsg.no(i);
                apiMsg = pMsg.priceList.no(idx);

                apiMsg.ordPrcCalcBasePk.clear();
                ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, prcMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, prcMsg.trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondTpCd, prcMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondTpDescTxt, prcMsg.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcDtlGrpCd, prcMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcJrnlGrpCd, prcMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManEntryFlg, prcMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManAddFlg, prcMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManDelFlg, prcMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.pkgUomCd, prcMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondUnitCd, prcMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCalcMethCd, prcMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.autoPrcAmtRate, prcMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.maxPrcAmtRate, prcMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.minPrcAmtRate, prcMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.manPrcAmtRate, prcMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.calcPrcAmtRate, prcMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.unitPrcAmt, prcMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(apiMsg.dsMdsePrcPk, prcMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.specCondPrcPk, prcMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.frtPerWtPk, prcMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.autoPrcCcyCd, prcMsg.autoPrcCcyCd);
               // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(apiMsg.prcRuleApplyFlg, prcMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcRulePrcdPk, prcMsg.prcRulePrcdPk);
               // QC#9700  2018/09/03 Add End
                idx++;
            }

            pMsg.priceList.setValidCount(idx);
            return true;
        } finally {

            writeEndLogLn("setCpoUpdApiDtlPrcPMsg", lineBean);
        }
    }

    private boolean setCpoUpdApiRtnPMsg(String rqstTpCd, DsImptRtnLineBean rtnBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiRtnPMsg", rtnBean);

        try {

            NWZC157004PMsg priceApiResult;
            ImptHdrBean hdrBean = rtnBean.imptHdrBean;
            int idx = pMsg.rtnDtl.getValidCount();

            NWZC150001_rtnDtlPMsg rtnPMsg = pMsg.rtnDtl.no(idx);
            BigDecimal unitNetWt, prcListAmt;
            ExpendMdseBean mdseBean = rtnBean.mdseBean;

            String confgTpCd = null;
            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
                if (CONFIG_CATG.INBOUND.equals(cpoConfigPMsg.configCatgCd.getValue()) && cpoConfigPMsg.dsOrdPosnNum.getValue().equals(rtnBean.dsOrdPosnNum.getValue())) {
                    confgTpCd = cpoConfigPMsg.configTpCd.getValue();
                    break;
                }
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.xxRqstTpCd_B1, rqstTpCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.configTpCd_B1, confgTpCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.cpoDtlLineNum_B1, mdseBean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.cpoDtlLineSubNum_B1, mdseBean.getCpoDtlLineSubNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsOrdPosnNum_B1, rtnBean.dsOrdPosnNum);
            // 2018/01/23 QC#18798 Add Start
            if (paramDsImptOrdConfigPk != null) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.dsOrdPosnNum_B1, DS_ORD_POSN_NUM_1);
            }
            // 2018/01/23 QC#18798 Add End

            if (hdrBean.isLoanDowngrade()) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                //ssmParam.put("ordLineCtxTpCd", ORD_LINE_CTX_TP.LOAN_CONV_LINE);
                ssmParam.put("ordLineCtxTpCd", LOAN_CONV_LINE_CRAT); // 2017/09/04 S21_NA#19800 Mod
                ssmParam.put("actionNm", LOAN_ORD_ACTION_LOAN_RETURN);

                Map<String, Object> lineValSet = NWZC226001Query.getInstance().queryMap("getLineValSet", ssmParam);

                if (lineValSet == null) {
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(rtnPMsg.dsOrdLineCatgCd_B1, (String) lineValSet.get("DS_ORD_LINE_CATG_CD"));
            } else {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.dsOrdLineCatgCd_B1, rtnBean.dsCpoLineCatgCd);
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.ordLineSrcCd_B1, rtnBean.ordLineSrcCd);
            if (!ZYPCommonFunc.hasValue(rtnPMsg.ordLineSrcCd_B1)) {

                MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(hdrBean.getGlblCmpyCd(), mdseBean.getMdseCd());
                if (mdseTMsg != null) {

                    if (S21StringUtil.isEquals(mdseTMsg.invtyCtrlFlg.getValue(), ZYPConstant.FLG_OFF_N)) {

                        ZYPEZDItemValueSetter.setValue(rtnPMsg.ordLineSrcCd_B1, ORD_LINE_SRC.INTERNAL);
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.mdseCd_B1, rtnBean.mdseCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.origMdseCd_B1, rtnBean.origMdseCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.custMdseCd_B1, rtnBean.custMdseCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.baseCmptFlg_B1, mdseBean.getBaseCmptFlg());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dropShipFlg_B1, rtnBean.dropShipFlg);

            ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCustCd_B1, rtnBean.shipToCustCd);
            if (rtnBean.hasShipToCust()) {

                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToLocNm_B1, rtnBean.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToAddlLocNm_B1, rtnBean.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFirstLineAddr_B1, rtnBean.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToScdLineAddr_B1, rtnBean.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToThirdLineAddr_B1, rtnBean.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFrthLineAddr_B1, rtnBean.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFirstRefCmntTxt_B1, rtnBean.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToScdRefCmntTxt_B1, rtnBean.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCtyAddr_B1, rtnBean.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToStCd_B1, rtnBean.shipToStCd);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToProvNm_B1, rtnBean.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCntyNm_B1, rtnBean.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToPostCd_B1, rtnBean.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCtryCd_B1, rtnBean.shipToCtryCd);
            } else {

                Map<String, Object> custInfo = getShipInfo(hdrBean.getGlblCmpyCd(), hdrBean.getShipToCustCd());

                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToLocNm_B1, convToString(custInfo.get("LOC_NM")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToAddlLocNm_B1, convToString(custInfo.get("ADDL_LOC_NM")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFirstLineAddr_B1, convToString(custInfo.get("FIRST_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToScdLineAddr_B1, convToString(custInfo.get("SCD_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToThirdLineAddr_B1, convToString(custInfo.get("THIRD_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFrthLineAddr_B1, convToString(custInfo.get("FRTH_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToFirstRefCmntTxt_B1, convToString(custInfo.get("FIRST_REF_CMNT_TXT")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToScdRefCmntTxt_B1, convToString(custInfo.get("SCD_REF_CMNT_TXT")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCtyAddr_B1, convToString(custInfo.get("CTY_ADDR")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToStCd_B1, convToString(custInfo.get("ST_CD")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToProvNm_B1, convToString(custInfo.get("PROV_NM")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCntyNm_B1, convToString(custInfo.get("CNTY_NM")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToPostCd_B1, convToString(custInfo.get("POST_CD")));
                ZYPEZDItemValueSetter.setValue(rtnPMsg.shipToCtryCd_B1, convToString(custInfo.get("CTRY_CD")));
            }
            ZYPEZDItemValueSetter.setValue(rtnPMsg.custUomCd_B1, rtnBean.custUomCd);

            if (hdrBean.isLoanDowngrade()) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.ordQty_B1, BigDecimal.ONE.negate());
                ZYPEZDItemValueSetter.setValue(rtnPMsg.ordCustUomQty_B1, BigDecimal.ONE.negate());
            } else {

                if (ZYPCommonFunc.hasValue(rtnBean.ordQty) && rtnBean.ordQty.getValueInt() > 0) {
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordQty_B1, rtnBean.ordQty.getValue().negate());
                } else {
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordQty_B1, rtnBean.ordQty);
                }

                if (ZYPCommonFunc.hasValue(rtnBean.ordCustUomQty) && rtnBean.ordCustUomQty.getValueInt() > 0) {
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordCustUomQty_B1, rtnBean.ordCustUomQty.getValue().negate());
                } else {
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordCustUomQty_B1, rtnBean.ordCustUomQty);
                }
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.invtyLocCd_B1, rtnBean.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.rtlWhCd_B1, rtnBean.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.rtlSwhCd_B1, rtnBean.rtlSwhCd);
            if (ZYPCommonFunc.hasValue(rtnBean.stkStsCd)) {

                ZYPEZDItemValueSetter.setValue(rtnPMsg.stkStsCd_B1, rtnBean.stkStsCd);
            } else {

                ZYPEZDItemValueSetter.setValue(rtnPMsg.stkStsCd_B1, STK_STS.GOOD);
            }
            ZYPEZDItemValueSetter.setValue(rtnPMsg.prcBaseDt_B1, rtnBean.prcBaseDt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.prcCatgCd_B1, rtnBean.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.flPrcListCd_B1, rtnBean.flPrcListCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.entDealNetUnitPrcAmt_B1, rtnBean.entDealNetUnitPrcAmt);
            if (ZYPCommonFunc.hasValue(rtnBean.dealPrcListPrcAmt)) {

                ZYPEZDItemValueSetter.setValue(rtnPMsg.dealPrcListPrcAmt_B1, rtnBean.dealPrcListPrcAmt);
            } else {

                if (rtnBean.getBasePrice() != null) {

                    ZYPEZDItemValueSetter.setValue(rtnPMsg.dealPrcListPrcAmt_B1, rtnBean.getBasePrice().autoPrcAmtRate);
                } else {

                    ZYPEZDItemValueSetter.setValue(rtnPMsg.dealPrcListPrcAmt_B1, BigDecimal.ZERO);
                }
            }
            if (ZYPCommonFunc.hasValue(rtnBean.funcPrcListPrcAmt)) {

                ZYPEZDItemValueSetter.setValue(rtnPMsg.funcPrcListPrcAmt_B1, rtnBean.funcPrcListPrcAmt);
            } else {

                if (rtnBean.getBasePrice() != null) {

                    prcListAmt = rtnBean.getBasePrice().autoPrcAmtRate.getValue();
                    prcListAmt = NWXC220001.exchFuncUnitPrice(hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), rtnBean.ccyCd.getValue(), prcListAmt, prcListAmt);
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.funcPrcListPrcAmt_B1, prcListAmt);
                } else {

                    ZYPEZDItemValueSetter.setValue(rtnPMsg.funcPrcListPrcAmt_B1, BigDecimal.ZERO);
                }
            }
            ZYPEZDItemValueSetter.setValue(rtnPMsg.ccyCd_B1, rtnBean.ccyCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.slsRepOrSlsTeamTocCd_B1, rtnBean.slsRepOrSlsTeamTocCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.serNum_B1, rtnBean.serNum);

            if(ZYPCommonFunc.hasValue(rtnBean.rqstPickUpDt)) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rqstPickUpDt_B1, rtnBean.rqstPickUpDt);
            } else {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rqstPickUpDt_B1, getRqstPickUpDt(hdrBean, rtnBean));
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.cpoDtlCancFlg_B1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.pmtTermCd_B1, hdrBean.getFrtCondCd());
            rtnPMsg.crRebilCd_B1.clear();
            rtnPMsg.machConfigNum_B1.clear();
            ZYPEZDItemValueSetter.setValue(rtnPMsg.svcConfigMstrPk_B1, rtnBean.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsContrNum_B1, rtnBean.dsContrNum);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsContrSqNum_B1, rtnBean.dsContrSqNum);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.svcMachMstrPk_B1, rtnBean.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.refCpoDtlLineNum_B1, mdseBean.getRefCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.refCpoDtlLineSubNum_B1, mdseBean.getRefCpoDtlLineSubNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dplyLineRefNum_B1, mdseBean.getDplyLineRefNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.csmpContrNum_B1, rtnBean.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dlrRefNum_B1, rtnBean.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.csmpPrcListCd_B1, rtnBean.csmpPrcListCd);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.hddRmvCd_B1, rtnBean.hddRmvCd);

            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsCpoLineNum_B1, mdseBean.getDsCpoLineNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsCpoLineSubNum_B1, mdseBean.getDsCpoLineSubNum());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.dsCpoConfigPk_B1, rtnBean.dsCpoConfigPk);

            if (hdrBean.isLoanDowngrade()) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.origCpoOrdNum_B1, hdrBean.getOrigOrdNum());
                ZYPEZDItemValueSetter.setValue(rtnPMsg.origCpoDtlLineNum_B1, rtnBean.origCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.origCpoDtlLineSubNum_B1, rtnBean.origCpoDtlLineSubNum);
                // QC#50512 2019/05/30 Add Start
                // ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnRsnCd_B1, RTRN_RSN_REGR_RTRN);
                String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(LOAN_RTRN_RSN_CD, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnRsnCd_B1, defaultRsnCd);
                // QC#50512 2019/05/30 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnRsnCd_B1, rtnBean.rtrnRsnCd);
            }
            ZYPEZDItemValueSetter.setValue(rtnPMsg.taxFlg_B1, ZYPConstant.FLG_ON_Y);

            unitNetWt = getUnitNetWt(rtnBean);
            if (unitNetWt == null) {

                return false;
            }
            ZYPEZDItemValueSetter.setValue(rtnPMsg.lineNetWt_B1, unitNetWt);

            priceApiResult = hdrBean.getPricingApiResult4(rtnBean);
            // 2017/11/01 S21_NA#22184 Mod Start
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.basePrcAmt_B1, priceApiResult.xxTotAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.discPrcAmt_B1, priceApiResult.xxTotDiscAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotNetDiscAmt_B1, priceApiResult.xxTotNetDiscAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B1, priceApiResult.xxTotNetPrcAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.totFrtAmt_B1, priceApiResult.xxTotFrtAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotTaxAmt_B1, priceApiResult.xxTotTaxAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B2, priceApiResult.xxSlsAmt);
//            ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnTotAmt_B1, priceApiResult.xxTotAmt);
            if (priceApiResult != null) {
            	ZYPEZDItemValueSetter.setValue(rtnPMsg.basePrcAmt_B1, priceApiResult.xxTotAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.discPrcAmt_B1, priceApiResult.xxTotDiscAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotNetDiscAmt_B1, priceApiResult.xxTotNetDiscAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B1, priceApiResult.xxTotNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.totFrtAmt_B1, priceApiResult.xxTotFrtAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotTaxAmt_B1, priceApiResult.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B2, priceApiResult.xxSlsAmt);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnTotAmt_B1, priceApiResult.xxTotAmt);
            } else {
            	ZYPEZDItemValueSetter.setValue(rtnPMsg.basePrcAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.discPrcAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotNetDiscAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.totFrtAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.xxTotTaxAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.netAmt_B2, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnTotAmt_B1, BigDecimal.ZERO);
            }
            // 2017/11/01 S21_NA#22184 Mod Start

            ZYPEZDItemValueSetter.setValue(rtnPMsg.bllgAttrbCustAcctCd_B1, hdrBean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(rtnPMsg.frthBllgAttrbNm_B1, rtnBean.firstBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.frthBllgAttrbValTxt_B1, rtnBean.firstBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.scdBllgAttrbNm_B1, rtnBean.scdBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.scdBllgAttrbValTxt_B1, rtnBean.scdBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.thirdBllgAttrbNm_B1, rtnBean.thirdBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.thirdBllgAttrbValTxt_B1, rtnBean.thirdBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.frthBllgAttrbNm_B1, rtnBean.frthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.frthBllgAttrbValTxt_B1, rtnBean.frthBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.fifthBllgAttrbNm_B1, rtnBean.fifthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.fifthBllgAttrbValTxt_B1, rtnBean.fifthBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.sixthBllgAttrbNm_B1, rtnBean.sixthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.sixthBllgAttrbValTxt_B1, rtnBean.sixthBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.ordSrcRefLineNum_B1, rtnBean.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(rtnPMsg.ordSrcRefLineSubNum_B1, rtnBean.ordSrcRefLineSubNum);

            // Add Start 2018/04/16 QC#22187
            if (ZYPCommonFunc.hasValue(hdrBean.getCpoSrcTpCd()) && CPO_SRC_TP.ORDER_UPLOAD.equals(hdrBean.getCpoSrcTpCd())) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.cpoSrcTpCd_B1, hdrBean.getCpoSrcTpCd());
            }
            // Add End 2018/04/16 QC#22187
            if (CPO_SRC_TP.ORDER_UPLOAD.equals(hdrBean.getCpoSrcTpCd())) {

                if (rtnBean.dsImptOrdConfigBean.isUpdateData()) {

                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordUpldRefNum_B1, hdrBean.getOrigCpo().ordSrcRefNum);
                } else {

                    ZYPEZDItemValueSetter.setValue(rtnPMsg.ordUpldRefNum_B1, hdrBean.getOrdSrcRefNum());
                }
            } else {

                rtnPMsg.ordUpldRefNum_B1.clear();
            }

            ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnQty_B1, BigDecimal.ZERO);

            // 2018/01/16 S21_NA#22372 Add Start
            ZYPEZDItemValueSetter.setValue(rtnPMsg.funcUnitFlPrcAmt_B1, rtnBean.funcUnitFlPrcAmt);
            // 2018/01/16 S21_NA#22372 Add End

            // Add Start 2019/06/12 QC#50799
            if(rtnBean.isOrigRtnDtl) {
                ZYPEZDItemValueSetter.setValue(rtnPMsg.rtrnLineStsCd_B1, rtnBean.origDsCpoRtrnDtlTMsg.rtrnLineStsCd);
            }
            // Add End 2019/06/12 QC#50799

            idx++;
            pMsg.rtnDtl.setValidCount(idx);

            return true;
        } finally {

            writeEndLogLn("setCpoUpdApiRtnPMsg", rtnBean);
        }
    }

    private boolean setCpoUpdApiRtnPrcPMsg(DsImptRtnLineBean rtnBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiRtnPrcPMsg", rtnBean);

        try {

            ImptHdrBean hdrBean = rtnBean.imptHdrBean;
            NWZC157002PMsg priceApiResult = hdrBean.getPricingApiResult2(rtnBean);
            NWZC157003PMsg prcMsg;
            NWZC150001_rtnPriceListPMsg apiMsg;
            int idx = pMsg.rtnPriceList.getValidCount();

            for (int i = 0; i < priceApiResult.NWZC157003PMsg.getValidCount(); i++) {

                prcMsg = priceApiResult.NWZC157003PMsg.no(i);
                apiMsg = pMsg.rtnPriceList.no(idx);

                apiMsg.ordPrcCalcBasePk.clear();
                ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, prcMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, prcMsg.trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondTpCd, prcMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondTpDescTxt, prcMsg.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcDtlGrpCd, prcMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcJrnlGrpCd, prcMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManEntryFlg, prcMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManAddFlg, prcMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondManDelFlg, prcMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.pkgUomCd, prcMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCondUnitCd, prcMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcCalcMethCd, prcMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.autoPrcAmtRate, prcMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.maxPrcAmtRate, prcMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.minPrcAmtRate, prcMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.manPrcAmtRate, prcMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.calcPrcAmtRate, prcMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(apiMsg.unitPrcAmt, prcMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(apiMsg.dsMdsePrcPk, prcMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.specCondPrcPk, prcMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.frtPerWtPk, prcMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(apiMsg.autoPrcCcyCd, prcMsg.autoPrcCcyCd);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(apiMsg.prcRuleApplyFlg, prcMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcRulePrcdPk, prcMsg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End
                idx++;
            }

            pMsg.rtnPriceList.setValidCount(idx);
            return true;
        } finally {

            writeEndLogLn("setCpoUpdApiRtnPrcPMsg", rtnBean);
        }
    }

    // private BigDecimal getOrdLinePosnNumByImptSvcConfigRefPk(String glblCmpyCd, BigDecimal dsImptSvcConfigRefPk) { // 2019/01/29 S21_NA#30022 Mod
    private BigDecimal getOrdLinePosnNumByImptSvcConfigRefPk(String glblCmpyCd, BigDecimal dsImptSvcConfigRefPk, ImptHdrBean hdrBean) {

        if (dsImptSvcConfigRefPk == null) {

            return null;
        }

        DS_IMPT_SVC_CONFIG_REFTMsg configRef = new DS_IMPT_SVC_CONFIG_REFTMsg();
        ZYPEZDItemValueSetter.setValue(configRef.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(configRef.dsImptSvcConfigRefPk, dsImptSvcConfigRefPk);
        configRef = (DS_IMPT_SVC_CONFIG_REFTMsg) S21ApiTBLAccessor.findByKey(configRef);
        if (configRef == null) {

            return null;
        }
        if (!ZYPCommonFunc.hasValue(configRef.dsImptOrdDtlPk)) {

            return null;
        }

        DS_IMPT_ORD_DTLTMsg line = new DS_IMPT_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(line.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(line.dsImptOrdDtlPk, configRef.dsImptOrdDtlPk);
        line = (DS_IMPT_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(line);
        if (line == null) {

            return null;
        }

        if (!ZYPCommonFunc.hasValue(line.dsOrdPosnNum)) {

            return null;
        }
        // 2019/01/29 S21_NA#30022 Add Start
        if (ZYPCommonFunc.hasValue(line.dsImptOrdConfigPk) && hdrBean.dsImptOrdConfigMap.containsKey(line.dsImptOrdConfigPk.getValue())){
            return BigDecimal.valueOf(Integer.valueOf((hdrBean.dsImptOrdConfigMap.get(line.dsImptOrdConfigPk.getValue()).dsOrdPosnNum.getValue())));
        }
        // 2019/01/29 S21_NA#30022 Add End
        return BigDecimal.valueOf(Integer.valueOf(line.dsOrdPosnNum.getValue()));
    }

    private void setCpoUpdApiSlsCrPMsg(ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiSlsCredit", hdrBean);

        try {
            int cnt = 0;
            NWZC150001_cpoSlsCrPMsg apiMsg;

            // Original
            DS_CPO_SLS_CRTMsgArray dsCpoSlsCrTMsgArray = hdrBean.getOrigDsCpoSlsCrArray();
            if (hasValidValue(dsCpoSlsCrTMsgArray)) {
                for (; cnt < dsCpoSlsCrTMsgArray.getValidCount();) {
                    apiMsg = pMsg.cpoSlsCr.no(cnt);
                    setOrigCpoUpdApiSlsCrPMsg(dsCpoSlsCrTMsgArray.no(cnt), apiMsg);
                    cnt++;
                }
            }

            // Header Level
            // if sales credit already exits, skip
            if (!existsSlsCr(hdrBean, null)) {
                // Mod Start 2017/07/25 QC#20114
//                if (hdrBean.getDsImptSlsCrList().size() > 0) {
                for (int i = 0; i < hdrBean.getDsImptSlsCrList().size(); i++) {
                    apiMsg = pMsg.cpoSlsCr.no(cnt);
//                    setCpoUpdApiSlsCrPMsg(hdrBean.getDsImptSlsCrList().get(0), apiMsg, null);
                    setCpoUpdApiSlsCrPMsg(hdrBean.getDsImptSlsCrList().get(i), apiMsg, null);
                    cnt++;
                }
                // Mod End 2017/07/25 QC#20114
            }

            // Config Level
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {
                // if sales credit already exits, skip
                if (!existsSlsCr(hdrBean, configBean.dsOrdPosnNum.getValue())) {
                    for (DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrMsg : configBean.dsImptSlsCrList) {

                        apiMsg = pMsg.cpoSlsCr.no(cnt);

                        setCpoUpdApiSlsCrPMsg(dsImptOrdSlsCrMsg, apiMsg, configBean);
                        cnt++;
                    }
                }
            }

            pMsg.cpoSlsCr.setValidCount(cnt);
            // 2019/12/17 S21_NA#54721 Add Start
            for (int n = 0; n < pMsg.cpoConfig.getValidCount(); n++) {
                NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(n);
                if (!existsConfSlsCr(pMsg, cpoConfigPMsg)) {
                    List<NWZC150001_cpoSlsCrPMsg> inbndSlsCrList = getConfigSlsCrFromSameShipTo(cpoConfigPMsg, hdrBean, pMsg);
                    for (NWZC150001_cpoSlsCrPMsg inbndSlsCrPMsg : inbndSlsCrList) {
                        EZDPMsg.copy(inbndSlsCrPMsg, null, pMsg.cpoSlsCr.no(cnt), null);
                        cnt++;
                    }
                }
            }
            pMsg.cpoSlsCr.setValidCount(cnt);
            // 2019/12/17 S21_NA#54721 Add End
        } finally {

            writeEndLogLn("setCpoUpdApiSlsCredit", hdrBean);
        }
    }

    private void setCpoUpdApiSlsCrPMsg(DS_IMPT_ORD_SLS_CRTMsg tMsg, NWZC150001_cpoSlsCrPMsg apiMsg, DsImptOrdConfigBean configBean) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        if (configBean != null) {

            ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepCd, tMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepRoleTpCd, tMsg.slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepCrPct, tMsg.slsRepCrPct);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsCrQuotFlg, tMsg.slsCrQuotFlg);
    }

    private void setOrigCpoUpdApiSlsCrPMsg(DS_CPO_SLS_CRTMsg tMsg, NWZC150001_cpoSlsCrPMsg apiMsg) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_MODIFY);
        ZYPEZDItemValueSetter.setValue(apiMsg.dsCpoSlsCrPk, tMsg.dsCpoSlsCrPk);
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, tMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.dsCpoConfigPk, tMsg.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepCd, tMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepRoleTpCd, tMsg.slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsRepCrPct, tMsg.slsRepCrPct);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsCrQuotFlg, tMsg.slsCrQuotFlg);

        if (ZYPCommonFunc.hasValue(tMsg.dsOrdPosnNum)) {
            DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, tMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, tMsg.dsCpoConfigPk);
            configTMsg = (DS_CPO_CONFIGTMsg) S21ApiTBLAccessor.findByKey(configTMsg);

            if (configTMsg != null) {
                ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configTMsg.configCatgCd);
            }

        }
    }

    private void setCpoUpdApiDlvyInfoPMsg(ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiDlvyInfoPMsg", hdrBean);

        try {

            int cnt = 0;
            NWZC150001_cpoDlvyInfoListPMsg apiMsg;

            // Header Level
            if (!hdrBean.isExistValidatedSts()) {

                // Mod Start 2017/07/04 QC#19692
//                for (DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDlvyInfoMsg : hdrBean.getDsImptDelyList()) {
//
//                    apiMsg = pMsg.cpoDlvyInfoList.no(cnt);
//
//                    setCpoUpdApiDlvyInfoPMsg(dsImptOrdDlvyInfoMsg, apiMsg, null);
//                    cnt++;
//                }
                if (hdrBean.getDsImptDelyList().size() > 0) {
                    apiMsg = pMsg.cpoDlvyInfoList.no(cnt);
                    setCpoUpdApiDlvyInfoPMsg(hdrBean.getDsImptDelyList().get(0), apiMsg, null);
                    cnt++;
                }
                // Mod End 2017/07/04 QC#19692
            }

            // Config Level
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isExistValidatedSts()) {

                    continue;
                }

                for (DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDlvyInfoMsg : configBean.dsImptDelyList) {

                    // Add Start 2018/01/09 S21_NA#23116
                    if (hdrBean.isLoanUpgrade() //
                            || (hdrBean.isLoanDowngrade() && CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue()))) {
                        continue;
                    }
                    // Add End 2018/01/09 S21_NA#23116

                    apiMsg = pMsg.cpoDlvyInfoList.no(cnt);

                    setCpoUpdApiDlvyInfoPMsg(dsImptOrdDlvyInfoMsg, apiMsg, configBean);
                    cnt++;
                }
            }

            pMsg.cpoDlvyInfoList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setCpoUpdApiDlvyInfoPMsg", hdrBean);
        }
    }

    private void setCpoUpdApiDlvyInfoPMsg(DS_IMPT_ORD_DELY_INFOTMsg tMsg, NWZC150001_cpoDlvyInfoListPMsg apiMsg, DsImptOrdConfigBean configBean) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        if (configBean != null) {

            ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.dsDelyTpCd, tMsg.dsDelyTpCd);
//        ZYPEZDItemValueSetter.setValue(apiMsg.rddDt, tMsg.rddDt); // 2017/08/25 S21_NA#20740-1 Del
        // 2019/02/14 S21_NA#30340 Mod Start
        // ZYPEZDItemValueSetter.setValue(apiMsg.opsFromHourMn, tMsg.opsFromHourMn);
        // ZYPEZDItemValueSetter.setValue(apiMsg.opsToHourMn, tMsg.opsToHourMn);
        ZYPEZDItemValueSetter.setValue(apiMsg.opsFromHourMn, getConvertTime(configBean, tMsg.opsFromHourMn.getValue()));
        ZYPEZDItemValueSetter.setValue(apiMsg.opsToHourMn, getConvertTime(configBean, tMsg.opsToHourMn.getValue()));
        // 2019/02/14 S21_NA#30340 Mod End
        ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalFlg, tMsg.loadDockAvalFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.stairCrawReqFlg, tMsg.stairCrawReqFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.stairCrawNum, tMsg.stairCrawNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalFlg, tMsg.elevAvalFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.delyAddlCmntTxt, tMsg.delyAddlCmntTxt);
    }

    private void setCpoUpdApiSiteSrvInfoPMsg(ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiSiteSrvInfoPMsg", hdrBean);

        try {

            int cnt = 0;
            NWZC150001_siteSrvInfoListPMsg apiMsg;

            // Header Level
            if (!hdrBean.isExistValidatedSts()) {

                // Mod Start 2017/07/04 QC#19692
//                for (DS_IMPT_ORD_SITE_SRVYTMsg dsImptOrdSiteSrvInfoMsg : hdrBean.getDsImptSiteSrvyList()) {
//
//                    apiMsg = pMsg.siteSrvInfoList.no(cnt);
//
//                    setCpoUpdApiSiteSrvInfoPMsg(dsImptOrdSiteSrvInfoMsg, apiMsg, null);
//                    cnt++;
//                }
                if (hdrBean.getDsImptSiteSrvyList().size() > 0) {
                    apiMsg = pMsg.siteSrvInfoList.no(cnt);
                    setCpoUpdApiSiteSrvInfoPMsg(hdrBean.getDsImptSiteSrvyList().get(0), apiMsg, null);
                    cnt++;
                }
                // Mod End 2017/07/04 QC#19692
            }

            // Config Level
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isExistValidatedSts()) {

                    continue;
                }
                for (DS_IMPT_ORD_SITE_SRVYTMsg dsImptOrdSiteSrvInfoMsg : configBean.dsImptSiteSrvyList) {

                    // Add Start 2018/01/09 S21_NA#23116
                    if (hdrBean.isLoanUpgrade() //
                            || (hdrBean.isLoanDowngrade() && CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue()))) {
                        continue;
                    }
                    // Add End 2018/01/09 S21_NA#23116

                    apiMsg = pMsg.siteSrvInfoList.no(cnt);

                    setCpoUpdApiSiteSrvInfoPMsg(dsImptOrdSiteSrvInfoMsg, apiMsg, configBean);
                    cnt++;
                }
            }

            pMsg.siteSrvInfoList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setCpoUpdApiSiteSrvInfoPMsg", hdrBean);
        }
    }

    private void setCpoUpdApiSiteSrvInfoPMsg(DS_IMPT_ORD_SITE_SRVYTMsg tMsg, NWZC150001_siteSrvInfoListPMsg apiMsg, DsImptOrdConfigBean configBean) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SITE_SRVY_NEW);
        if (configBean != null) {

            ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.cmpyInfoAptBldgNm, tMsg.cmpyInfoAptBldgNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.cmpyInfoFlNm, tMsg.cmpyInfoFlNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.cmpyInfoDeptNm, tMsg.cmpyInfoDeptNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.otsdStepNum, tMsg.otsdStepNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.insdStepNum, tMsg.insdStepNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.stairCrawReqFlg, tMsg.stairCrawReqFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.flgtStairNum, tMsg.flgtStairNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalFlg, tMsg.elevAvalFlg);
        // 2019/02/14 S21_NA#30340 Mod Start
        // ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalFromHourMn, tMsg.elevAvalFromHourMn);
        // ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalToHourMn, tMsg.elevAvalToHourMn);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalFromHourMn, getConvertTime(configBean, tMsg.elevAvalFromHourMn.getValue()));
        ZYPEZDItemValueSetter.setValue(apiMsg.elevAvalToHourMn, getConvertTime(configBean, tMsg.elevAvalToHourMn.getValue()));
        // 2019/02/14 S21_NA#30340 Mod End
        ZYPEZDItemValueSetter.setValue(apiMsg.elevApptReqFlg, tMsg.elevApptReqFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevCtacTelNum, tMsg.elevCtacTelNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevProtReqFlg, tMsg.elevProtReqFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevWdt, tMsg.elevWdt);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevDepthNum, tMsg.elevDepthNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevCtacPsnNm, tMsg.elevCtacPsnNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevCapWt, tMsg.elevCapWt);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevDoorHgt, tMsg.elevDoorHgt);
        ZYPEZDItemValueSetter.setValue(apiMsg.elevDoorWdt, tMsg.elevDoorWdt);
        ZYPEZDItemValueSetter.setValue(apiMsg.stairAndLdgWdt, tMsg.stairAndLdgWdt);
        ZYPEZDItemValueSetter.setValue(apiMsg.crdrWdt, tMsg.crdrWdt);
        ZYPEZDItemValueSetter.setValue(apiMsg.doorWdt, tMsg.doorWdt);
        ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalFlg, tMsg.loadDockAvalFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.loadDockHgt, tMsg.loadDockHgt);
        ZYPEZDItemValueSetter.setValue(apiMsg.trctrAndTrailAccsFlg, tMsg.trctrAndTrailAccsFlg);
        ZYPEZDItemValueSetter.setValue(apiMsg.bldgEntDoorHgt, tMsg.bldgEntDoorHgt);
        ZYPEZDItemValueSetter.setValue(apiMsg.bldgEntDoorWdt, tMsg.bldgEntDoorWdt);
        // 2019/02/14 S21_NA#30340 Mod Start
        // ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalFromHourMn, tMsg.loadDockAvalFromHourMn);
        // ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalToHourMn, tMsg.loadDockAvalToHourMn);
        // ZYPEZDItemValueSetter.setValue(apiMsg.carrDelyTmHourMn, tMsg.carrDelyTmHourMn);
        ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalFromHourMn, getConvertTime(configBean, tMsg.loadDockAvalFromHourMn.getValue()));
        ZYPEZDItemValueSetter.setValue(apiMsg.loadDockAvalToHourMn, getConvertTime(configBean, tMsg.loadDockAvalToHourMn.getValue()));
        ZYPEZDItemValueSetter.setValue(apiMsg.carrDelyTmHourMn, getConvertTime(configBean, tMsg.carrDelyTmHourMn.getValue()));
        // 2019/02/14 S21_NA#30340 Mod End
        ZYPEZDItemValueSetter.setValue(apiMsg.delyTrnspOptCd, tMsg.delyTrnspOptCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.siteSrvyAddlCmntTxt, tMsg.siteSrvyAddlCmntTxt);
    }

    private void setCpoUpdApiIstlInfoPMsg(ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {
        writeStartLogLn("setCpoUpdApiIstlInfoPMsg", hdrBean);

        try {

            int cnt = 0;
            NWZC150001_cpoIstlInfoListPMsg apiMsg;

            // Header Level
            if (!hdrBean.isExistValidatedSts()) {

                // Mod Start 2017/07/04 QC#19692
//                for (DS_IMPT_ORD_ISTL_INFOTMsg dsImptOrdIstlInfoMsg : hdrBean.getDsImptInstList()) {
//
//                    apiMsg = pMsg.cpoIstlInfoList.no(cnt);
//
//                    setCpoUpdApiIstlInfoPMsg(dsImptOrdIstlInfoMsg, apiMsg, null);
//                    cnt++;
//                }
                if (hdrBean.getDsImptInstList().size() > 0) {
                    apiMsg = pMsg.cpoIstlInfoList.no(cnt);

                    // 2017/08/16 S21_NA#20627 Mod
                    setCpoUpdApiIstlInfoPMsg(hdrBean.getDsImptInstList().get(0), apiMsg, null, hdrBean);
                    cnt++;
                }
                // Mod End 2017/07/04 QC#19692
            }

            // Config Level
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isExistValidatedSts()) {

                    continue;
                }
                for (DS_IMPT_ORD_ISTL_INFOTMsg dsImptOrdIstlInfoMsg : configBean.dsImptInstList) {

                    // Add Start 2018/01/09 S21_NA#23116
                    if (hdrBean.isLoanUpgrade() //
                            || (hdrBean.isLoanDowngrade() && CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue()))) {
                        continue;
                    }
                    // Add End 2018/01/09 S21_NA#23116

                    apiMsg = pMsg.cpoIstlInfoList.no(cnt);

                    // 2017/08/16 S21_NA#20627 Mod
                    setCpoUpdApiIstlInfoPMsg(dsImptOrdIstlInfoMsg, apiMsg, configBean, hdrBean);
                    cnt++;
                }
            }

            pMsg.cpoIstlInfoList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setCpoUpdApiIstlInfoPMsg", hdrBean);
        }
    }

    // 2017/08/16 S21_NA#20627 Mod
    private void setCpoUpdApiIstlInfoPMsg(DS_IMPT_ORD_ISTL_INFOTMsg tMsg, NWZC150001_cpoIstlInfoListPMsg apiMsg, DsImptOrdConfigBean configBean, ImptHdrBean hdrBean) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_INSTL_INFO_NEW);
        if (configBean != null) {

            ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.istlDivCd, tMsg.istlDivCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.istlTechCd, tMsg.istlTechCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.rqstIstlDt, tMsg.rqstIstlDt);
        // 2019/02/14 S21_NA#30340 Mod Start
        // ZYPEZDItemValueSetter.setValue(apiMsg.rqstIstlTm, tMsg.rqstIstlTm);
        ZYPEZDItemValueSetter.setValue(apiMsg.rqstIstlTm, getConvertTime(configBean, tMsg.rqstIstlTm.getValue()));
        // 2019/02/14 S21_NA#30340 Mod Start
        ZYPEZDItemValueSetter.setValue(apiMsg.istlAddlCmntTxt, tMsg.istlAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(apiMsg.svcIstlRuleNum, tMsg.svcIstlRuleNum);

        // 2019/10/30 QC#53993 Add Start
        ZYPEZDItemValueSetter.setValue(apiMsg.istlBySvcPrvdPtyCd, tMsg.istlBySvcPrvdPtyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.svcBySvcPrvdPtyCd, tMsg.svcBySvcPrvdPtyCd);
        // 2019/10/30 QC#53993 Add End

        // 2017/08/16 S21_NA#20627 Add Start
        if (!isOrderRetailEquipment(hdrBean)) {

            // Header
            if (configBean == null) {

                apiMsg.istlDivCd.clear();
                ZYPEZDItemValueSetter.setValue(apiMsg.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);
            }

            // Outbound Config
            if (configBean != null && S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configBean.configCatgCd.getValue())) {

                apiMsg.istlDivCd.clear();
                ZYPEZDItemValueSetter.setValue(apiMsg.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);
            }
        }
        // 2017/08/16 S21_NA#20627 Add End

        // 2017/11/21 S21_NA#22555 Del Start
//        // 2017/09/25 S21_NA#20799 Add Start
//        if (configBean != null) {
//
//            DS_MDLTMsg dsMdl = new DS_MDLTMsg();
//            ZYPEZDItemValueSetter.setValue(dsMdl.glblCmpyCd, hdrBean.getGlblCmpyCd());
//            ZYPEZDItemValueSetter.setValue(dsMdl.mdlId, configBean.mdlId);
//            dsMdl = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdl);
//
//            if (dsMdl == null) {
//
//                if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configBean.configCatgCd.getValue())) {
//
//                    apiMsg.istlDivCd.clear();
//                    ZYPEZDItemValueSetter.setValue(apiMsg.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);
//                }
//            }
//        }
//        // 2017/09/25 S21_NA#20799 Add End
        // 2017/11/21 S21_NA#22555 Del End
    }

    private void setCpoUpdApiCtacPsnPMsg(ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        writeStartLogLn("setCpoUpdApiCtacPsnPMsg", hdrBean);

        try {

            int cnt = 0;
            NWZC150001_cpoCtacPsnInfoListPMsg apiMsg;

            // Header Level
            if (!hdrBean.isExistValidatedSts()) {

                for (DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsnMsg : hdrBean.getDsImptCtacPsnList()) {

                    apiMsg = pMsg.cpoCtacPsnInfoList.no(cnt);

                    setCpoUpdApiCtacPsnPMsg(dsImptOrdCtacPsnMsg, apiMsg, null);
                    // QC#21634
                    if (hdrBean.getIsLeaseOrd() && CTAC_CUST_REF_TP.BILL_TO.equals(apiMsg.ctacCustRefTpCd.getValue())) {
                        continue;
                    }
                    cnt++;
                }
            }

            // Config Level
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (configBean.isExistValidatedSts()) {

                    continue;
                }
                // Add Start 2018/01/09 S21_NA#23116
                if (hdrBean.isLoanUpgrade() //
                        || (hdrBean.isLoanDowngrade() && CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue()))) {
                    continue;
                }
                // Add End 2018/01/09 S21_NA#23116

                for (DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsnMsg : configBean.dsImptCtacPsnList) {

                    apiMsg = pMsg.cpoCtacPsnInfoList.no(cnt);

                    setCpoUpdApiCtacPsnPMsg(dsImptOrdCtacPsnMsg, apiMsg, configBean);
                    // QC#21634
                    if (hdrBean.getIsLeaseOrd() && CTAC_CUST_REF_TP.BILL_TO.equals(apiMsg.ctacCustRefTpCd.getValue())) {
                        continue;
                    }
                    cnt++;
                }
            }

            pMsg.cpoCtacPsnInfoList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setCpoUpdApiCtacPsnPMsg", hdrBean);
        }
    }

    private void setCpoUpdApiCtacPsnPMsg(DS_IMPT_ORD_CTAC_PSNTMsg tMsg, NWZC150001_cpoCtacPsnInfoListPMsg apiMsg, DsImptOrdConfigBean configBean) {

        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
        if (configBean != null) {

            ZYPEZDItemValueSetter.setValue(apiMsg.configCatgCd, configBean.configCatgCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, configBean.dsOrdPosnNum);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.ctacTpCd, tMsg.ctacPsnTpCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.firstNm, tMsg.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.lastNm, tMsg.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(apiMsg.telNum, tMsg.ctacPsnTelNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.faxNum, tMsg.ctacPsnFaxNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.emlAddr, tMsg.ctacPsnEmlAddr);
        ZYPEZDItemValueSetter.setValue(apiMsg.ctacPsnExtnNum, tMsg.ctacPsnExtnNum);
        ZYPEZDItemValueSetter.setValue(apiMsg.ctacPsnPk, tMsg.ctacPsnPk);
        // 2017/09/14 QC#20841 Add Start
        String ctacCustRefTpCd = getCtacCustRefTpCd(tMsg.glblCmpyCd.getValue(), tMsg.ctacPsnTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.ctacCustRefTpCd, ctacCustRefTpCd);
        // 2017/09/14 QC#20841 Add End
    }

    private boolean callCreationOfScheduleViaTheDealConfiguratorApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("callCreationOfScheduleViaTheDealConfiguratorApi", hdrBean);

        boolean result = true;
        try {

            List<Map<String, Object>> scheduleTemplateList = getScheduleTemplateList(hdrBean);
            if (scheduleTemplateList == null) {

                return true;
            }

            for (Map<String, Object> scheduleTemplate : scheduleTemplateList) {

                // 2018/08/10 S21_NA#25829 Del Start
                // if (!SPLY_AGMT_DOC_TP.SCHEDULING_AGREEMENT.equals((String) scheduleTemplate.get("SPLY_AGMT_DOC_TP_CD"))) {
                //     S21InfoLogOutput.println(MSG_ID.NWAM0902E.toString(), new String[] {scheduleTemplate.get("DS_IMPT_SCHD_TMPL_PK").toString() });
                //     continue;
                // }
                // 2018/08/10 S21_NA#25829 Del End 

                // schedule template
                NWZC172001PMsg scheduleTemplatePMsg = setScheduleTemplate(hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), scheduleTemplate);

                List<Map<String, Object>> scheduleTemplateLineList = getScheduleTemplateLineList(hdrBean.getGlblCmpyCd(), scheduleTemplate);
                List<NWZC172002PMsg> linePMsgList = new ArrayList<NWZC172002PMsg>();
                if (scheduleTemplateLineList != null) {

                    for (Map<String, Object> scheduleTemplateLine : scheduleTemplateLineList) {

                        // schedule template line
                        linePMsgList.add(setScheduleTemplateLine(scheduleTemplateLine));
                    }
                    if (scheduleTemplateLineList.size() > 0) {

                        ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.dsContrDtlPk, linePMsgList.get(0).dsContrDtlPk);
                    }
                }

                // execute API
                (new NWZC172001()).execute(scheduleTemplatePMsg, linePMsgList, onBatchType);

                // error handling
                if (addErrorMsgList(scheduleTemplatePMsg, hdrBean).size() > 0) {

                    result = false;
                }
                for (NWZC172002PMsg linePMsg : linePMsgList) {

                    if (addErrorMsgList(linePMsg, hdrBean).size() > 0) {

                        result = false;
                    }
                }
            }

            return result;
        } finally {

            writeEndLogLn("getScheduleTemplateList", result);
        }
    }

    private List<Map<String, Object>> getScheduleTemplateList(ImptHdrBean hdrBean) {

        writeStartLogLn("getScheduleTemplateList", hdrBean);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
            ssmParam.put("ordSrcRefNum", convToString(hdrBean.getOrdSrcRefNum(), ""));
            ssmParam.put("cpoOrdNum", convToString(hdrBean.getNewOrdNum(), ""));
            ssmParam.put("svcPrcCatgCd", SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);

            List<Map<String, Object>> scheduleTemplateList = NWZC226001Query.getInstance().queryMapList("getScheduleTemplateList", ssmParam);

            return scheduleTemplateList;
        } finally {

            writeEndLogLn("getScheduleTemplateList", hdrBean);
        }
    }

    private List<Map<String, Object>> getScheduleTemplateLineList(String glblCmpyCd, Map<String, Object> scheduleTemplateLine) {

        writeStartLogLn("getScheduleTemplateLineList", scheduleTemplateLine);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsImptSchdTmplPk", (BigDecimal) scheduleTemplateLine.get("DS_IMPT_SCHD_TMPL_PK"));
            ssmParam.put("dsContrDtlPk", (BigDecimal) scheduleTemplateLine.get("DS_CONTR_DTL_PK"));
            ssmParam.put("cpoOrdNum", (String) scheduleTemplateLine.get("CPO_ORD_NUM"));

            List<Map<String, Object>> scheduleTemplateList = NWZC226001Query.getInstance().queryMapList("getScheduleTemplateLineList", ssmParam);

            return scheduleTemplateList;
        } finally {

            writeEndLogLn("getScheduleTemplateLineList", scheduleTemplateLine);
        }
    }

    private NWZC172001PMsg setScheduleTemplate(String glblCmpyCd, String slsDt, Map<String, Object> scheduleTemplate) {

        writeStartLogLn("getScheduleTemplateLineList", scheduleTemplate);

        NWZC172001PMsg scheduleTemplatePMsg = new NWZC172001PMsg();

        try {

            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.xxRqstTpCd, NWZC172001Constant.RQST_TP_TMPL);
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.dsContrPk, (BigDecimal) scheduleTemplate.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.dsContrDtlPk, (BigDecimal) scheduleTemplate.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.refCpoOrdNum, (String) scheduleTemplate.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.cpoSrcTpCd, (String) scheduleTemplate.get("CPO_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.ordSrcRefNum, (String) scheduleTemplate.get("ORD_SRC_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.dsOrdTpCd, (String) scheduleTemplate.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.sysSrcRefNum, (String) scheduleTemplate.get("SYS_SRC_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.sysSrcCd, (String) scheduleTemplate.get("SYS_SRC_CD"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.qtyContrCapQty, (BigDecimal) scheduleTemplate.get("QTY_CONTR_CAP_QTY")); // QC#19701 2017/03/09 Add
            ZYPEZDItemValueSetter.setValue(scheduleTemplatePMsg.splyBaseAmt, (BigDecimal) scheduleTemplate.get("SPLY_BASE_AMT"));        // QC#19701 2017/03/09 Add

            return scheduleTemplatePMsg;
        } finally {

            writeEndLogLn("getScheduleTemplateLineList", scheduleTemplatePMsg);
        }
    }

    private NWZC172002PMsg setScheduleTemplateLine(Map<String, Object> scheduleTemplateLine) {

        writeStartLogLn("getScheduleTemplateLineList", scheduleTemplateLine);

        NWZC172002PMsg scheduleTemplateLinePMsg = new NWZC172002PMsg();

        try {

            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.dsContrDtlPk, (BigDecimal) scheduleTemplateLine.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.mdseCd, (String) scheduleTemplateLine.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.schdAllwQty, (BigDecimal) scheduleTemplateLine.get("SCHD_ALLW_QTY"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.shpgIntvlCd, (String) scheduleTemplateLine.get("SHPG_INTVL_CD"));
            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.otmShipQty, (BigDecimal) scheduleTemplateLine.get("SCHD_SHPG_QTY"));
            // 2017/03/07 QC#17668 Add Start
            ZYPEZDItemValueSetter.setValue(scheduleTemplateLinePMsg.firstShipQty, (BigDecimal) scheduleTemplateLine.get("FIRST_SHIP_QTY"));
            // 2017/03/07 QC#17668 Add End

            return scheduleTemplateLinePMsg;
        } finally {

            writeEndLogLn("getScheduleTemplateLineList", scheduleTemplateLinePMsg);
        }
    }

    private BigDecimal getUnitNetWt(String glblCmpyCd, ExpendMdseBean mdseBean) {

        writeStartLogLn("getUnitNetWt", mdseBean);

        try {

            BigDecimal unitNetWt = getUnitNetWt(glblCmpyCd, mdseBean.getChildMdseCd(), mdseBean.getChildMdseQty());
            if (null == unitNetWt) {

                DsImptLineBean lineBean = mdseBean.getDsImptLineBean();
                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(MDSE_STORE_PKGTMsg.class, lineBean, mdseBean.getChildMdseCd());

                lineBean.dsImptOrdErrList.add(errBean);
            }

            return unitNetWt;
        } finally {

            writeEndLogLn("getUnitNetWt", mdseBean);
        }
    }

    private BigDecimal getUnitNetWt(DsImptRtnLineBean rtnBean) {

        writeStartLogLn("getUnitNetWt", rtnBean);

        try {

            String mdseCd = rtnBean.mdseInfo.mdseCd.getValue();
            BigDecimal unitNetWt = getUnitNetWt(rtnBean.imptHdrBean.getGlblCmpyCd(), mdseCd, rtnBean.ordQty.getValue());
            if (null == unitNetWt) {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMasterExistError(MDSE_STORE_PKGTMsg.class, rtnBean, mdseCd);

                rtnBean.dsImptOrdErrList.add(errBean);
            }

            return unitNetWt;
        } finally {

            writeEndLogLn("getUnitNetWt", rtnBean);
        }
    }

    private BigDecimal getUnitNetWt(String glblCmpyCd, String mdse, BigDecimal qty) {
        writeStartLogLn("getUnitNetWt", mdse);

        try {

            MDSE_STORE_PKGTMsg strePkgTMsg = new MDSE_STORE_PKGTMsg();
            ZYPEZDItemValueSetter.setValue(strePkgTMsg.glblCmpyCd, glblCmpyCd);

            // START 2023/05/09 R.Azucena [QC#61420 MOD]
            // ZYPEZDItemValueSetter.setValue(strePkgTMsg.mdseCd, mdse);
            MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdse);

            if (mdseTMsg != null) {
                ZYPEZDItemValueSetter.setValue(strePkgTMsg.mdseCd, mdseTMsg.mdseCd.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(strePkgTMsg.mdseCd, mdse);
            }
            // END 2023/05/09 R.Azucena [QC#61420 MOD]

            ZYPEZDItemValueSetter.setValue(strePkgTMsg.pkgUomCd, PKG_UOM.EACH);

            strePkgTMsg = (MDSE_STORE_PKGTMsg) S21ApiTBLAccessor.findByKey(strePkgTMsg);
            if (null == strePkgTMsg) {

                return null;
            }

            BigDecimal inPoundWt = strePkgTMsg.inPoundWt.getValue();
            BigDecimal unitNetWt = inPoundWt.multiply(qty);

            return unitNetWt;
        } finally {

            writeEndLogLn("getUnitNetWt", mdse);
        }
    }

    /**
     * getShipInfo
     * @param shipToCustCd String
     * @return Map< ? , ? >
     */
    private Map<String, Object> getShipInfo(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        // 2018/06/13 S21_NA#24294 Mod Start
        // List<Map<String, Object>> shipInfoList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getShipInfo", params);
        Map<String, Object> shipInfo = DataCacheForSSM.getInstance().getShipToCustInfo(params);

        // if (!hasValueList(shipInfoList)) {
            // return null;
        // }

        // Map<String, Object> shipInfo = (Map<String, Object>) shipInfoList.get(0);
        // 2018/06/13 S21_NA#24294 Mod End
        return shipInfo;
    }

    // 2017/09/14 QC#20841 Add Start
    /**
     * getCtacCustRefTpCd
     * @param ctacPsnTpCd String
     * @param isLeaseOrd boolean
     * @return String
     */
    private String getCtacCustRefTpCd(String glblCmpyCd, String ctacPsnTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ctacPsnTpCd", ctacPsnTpCd);

        // 2018/06/14 S21_NA#24294 Mod Start
        // String ctacCustRefTpCd = NWZC226001Query.getInstance().queryString("getCtacCustRefTpCd", params);
        String ctacCustRefTpCd = DataCacheForSSM.getInstance().getCtacCustRefTpCd(params);
        // 2018/06/14 S21_NA#24294 Mod End

        return ctacCustRefTpCd;
    }
    // 2017/09/14 QC#20841 Add End

    private boolean updateEDiData(ImptHdrBean hdrBean, DS_IMPT_ORDTMsg dsImptOrdTMsg) {

        writeStartLogLn("updateEDiData", hdrBean);

        try {
            boolean isSuccess = true;

            // *****************************************************************
            // Update DS_IMPT_ORD
            // *****************************************************************
            if (!updateDsImptOrdTMsgForEDI(dsImptOrdTMsg, hdrBean)) {

                isSuccess = false;
            }

            // *****************************************************************
            // Update DS_IMPT_ORD_DTL
            // *****************************************************************
            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                if (!updateDsImptOrdDtlForEDi(lineBean)) {

                    isSuccess = false;
                }
            }

            // *****************************************************************
            // Insert DS_IMPT_PRC_CALC_BASE
            // *****************************************************************
            for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                if (!insertDsImptPrcCalcBase(lineBean)) {

                    isSuccess = false;
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("updateEDiData", hdrBean);
        }
    }

    private boolean updateDsImptOrdTMsgForEDI(DS_IMPT_ORDTMsg ordTMsg, ImptHdrBean hdrBean) {

        ZYPEZDItemValueSetter.setValue(ordTMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
        ZYPEZDItemValueSetter.setValue(ordTMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.dsOrdRsnCd, hdrBean.getDsOrdRsnCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.custIssPoNum, hdrBean.getCustIssPoNum());
        ZYPEZDItemValueSetter.setValue(ordTMsg.custIssPoDt, hdrBean.getCustIssPoDt());
        ZYPEZDItemValueSetter.setValue(ordTMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.billToCustCd, hdrBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToCustCd, hdrBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.sellToCustCd, hdrBean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.soldToCustLocCd, hdrBean.getSoldToCustLocCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.dropShipFlg, hdrBean.getDropShipFlg());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToLocNm, hdrBean.getShipToLocNm());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToAddlLocNm, hdrBean.getShipToAddlLocNm());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToScdLineAddr, hdrBean.getShipToScdLineAddr());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToCtyAddr, hdrBean.getShipToCtyAddr());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToStCd, hdrBean.getShipToStCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToProvNm, hdrBean.getShipToProvNm());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToCntyNm, hdrBean.getShipToCntyNm());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToPostCd, hdrBean.getShipToPostCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipToCtryCd, hdrBean.getShipToCtryCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipTo01RefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shipTo02RefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
        ZYPEZDItemValueSetter.setValue(ordTMsg.rddDt, hdrBean.getRddDt());
        ZYPEZDItemValueSetter.setValue(ordTMsg.frtCondCd, hdrBean.getFrtCondCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.carrCd, hdrBean.getCarrCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.carrSvcLvlCd, hdrBean.getCarrSvcLvlCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.frtChrgToCd, hdrBean.getFrtChrgToCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.frtChrgMethCd, hdrBean.getFrtChrgMethCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.carrAcctNum, hdrBean.getCarrAcctNum());
        ZYPEZDItemValueSetter.setValue(ordTMsg.addPmtTermCashDiscCd, hdrBean.getAddPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.prcCatgCd, hdrBean.getPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.flPrcListCd, hdrBean.getFlPrcListCd());
        ZYPEZDItemValueSetter.setValue(ordTMsg.slsRepTocCd, hdrBean.getSlsRepTocCd());
        S21ApiTBLAccessor.update(ordTMsg);
        if (!addUpdateErrorMsgList(ordTMsg, hdrBean, true)) {

            return false;
        }
        return true;
    }

    private boolean deriveDsImptOrdAttForEDi(ImptHdrBean hdrBean) {

        writeStartLogLn("updateDsImptOrdForEDi", hdrBean);

        try {

            boolean isSuccess = true;
            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();

            if (ZYPCommonFunc.hasValue(attrbTMsg.idocPoDocNum)) {

                hdrBean.setOrdSrcRefNum(attrbTMsg.idocPoDocNum.getValue());
            } else {

                DsImptOrdErrBean errBean = DsImptOrdErrBean.createMandatoryError(hdrBean, "Order Source Reference Number");
                hdrBean.getDsImptOrdErrList().add(errBean);
                isSuccess = false;
            }

            hdrBean.setCustIssPoDt(attrbTMsg.idocPoCustRefDt.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(hdrBean.getDropShipFlg())) {
                // 2018/11/29 S21_NA#28899 Add Start
                List<String> ediCtyMndChkTgtList = Arrays.asList(ZYPCodeDataUtil.getVarCharConstValue(EDI_CTY_MND_CHK_TGT, hdrBean.getGlblCmpyCd()).split(","));
                List<String> ediPostMndValidChkTgtList = Arrays.asList(ZYPCodeDataUtil.getVarCharConstValue(EDI_POST_MND_VALID_CHK_TGT, hdrBean.getGlblCmpyCd()).split(","));
                List<String> ediStMndValidChkTgtList = Arrays.asList(ZYPCodeDataUtil.getVarCharConstValue(EDI_ST_MND_VALID_CHK_RGT, hdrBean.getGlblCmpyCd()).split(","));
                List<String> ediCtyStCmbnChkTgtList = Arrays.asList(ZYPCodeDataUtil.getVarCharConstValue(EDI_CTY_ST_CMBN_CHK_TGT, hdrBean.getGlblCmpyCd()).split(","));
                String trdPtnrCd = attrbTMsg.ediTrdPtnrCd.getValue();
                String cntyNm = getCntyNm(hdrBean, attrbTMsg); 
                // City Address Madatory Check
                if (ediCtyMndChkTgtList.contains(trdPtnrCd) || ediCtyStCmbnChkTgtList.contains(trdPtnrCd)) {
                    if(!ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrCtyNm)) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2295E);
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    }
                }
                // Post Mandatory/Master Check
                if (ediPostMndValidChkTgtList.contains(trdPtnrCd) || ediCtyStCmbnChkTgtList.contains(trdPtnrCd)) {
                    if (!ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrPostCd)) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2296E);
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    // } else if (!isExistPostCd(hdrBean.getGlblCmpyCd(), attrbTMsg)) { // 2019/01/15 S21_NA#29875 Mod
                    } else if (!ZYPCommonFunc.hasValue(getExistPostCd(hdrBean.getGlblCmpyCd(), attrbTMsg))) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2298E, attrbTMsg.idocPtnrPostCd.getValue());
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    }
                }
                // State Mandatory/Master Check
                if (ediStMndValidChkTgtList.contains(trdPtnrCd) || ediCtyStCmbnChkTgtList.contains(trdPtnrCd)) {
                    if(!ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrStCd)) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2297E);
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    } else if (!isExistStCd(hdrBean.getGlblCmpyCd(), attrbTMsg)) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2299E, attrbTMsg.idocPtnrStCd.getValue());
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    }
                }
                // Combination Check
                if (ediCtyStCmbnChkTgtList.contains(trdPtnrCd)) {
                    if(!ZYPCommonFunc.hasValue(cntyNm)) {
                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2300E);
                        hdrBean.getDsImptOrdErrList().add(errBean);
                        isSuccess = false;
                    } 
                }
                
                if (isSuccess) {
                    hdrBean.setShipToLocNm(toUpper(attrbTMsg.idocPtnrCtacNm_03.getValue()));
                    hdrBean.setShipToFirstLineAddr(toUpper(attrbTMsg.idocFirstLineAddr.getValue()));
                    hdrBean.setShipToScdLineAddr(toUpper(attrbTMsg.idocScdLineAddr.getValue()));
                    hdrBean.setShipToCtyAddr(toUpper(attrbTMsg.idocPtnrCtyNm.getValue()));
                    hdrBean.setShipToStCd(toUpper(attrbTMsg.idocPtnrStCd.getValue()));
                    // 2019/01/15 S21_NA#29875 Mod Start
                    // hdrBean.setShipToPostCd(attrbTMsg.idocPtnrPostCd.getValue());
                    hdrBean.setShipToPostCd(getExistPostCd(hdrBean.getGlblCmpyCd(), attrbTMsg));
                    // 2019/01/15 S21_NA#29875 Mod End
                    hdrBean.setShipToCntyNm(cntyNm);
                    String idocPtnrCtryCd = attrbTMsg.idocPtnrCtryCd.getValue();
                    if (!ZYPCommonFunc.hasValue(idocPtnrCtryCd)) {
                        idocPtnrCtryCd = getDefCtry(hdrBean.getGlblCmpyCd());
                    }
                    hdrBean.setShipToCtryCd(toUpper(idocPtnrCtryCd));
                }
                // 2018/11/29 S21_NA#28899 Add End
                
                // 2018/11/29 S21_NA#28899 Del Start
//                hdrBean.setShipToLocNm(attrbTMsg.idocPtnrCtacNm_03.getValue());
//                hdrBean.setShipToFirstLineAddr(attrbTMsg.idocFirstLineAddr.getValue());
//                hdrBean.setShipToScdLineAddr(attrbTMsg.idocScdLineAddr.getValue());
//                hdrBean.setShipToCtyAddr(attrbTMsg.idocPtnrCtyNm.getValue());
//                hdrBean.setShipToStCd(attrbTMsg.idocPtnrStCd.getValue());
//                hdrBean.setShipToPostCd(attrbTMsg.idocPtnrPostCd.getValue());
//
//                String idocPtnrCtryCd = attrbTMsg.idocPtnrCtryCd.getValue();
//                if (!ZYPCommonFunc.hasValue(idocPtnrCtryCd)) {
//
//                    idocPtnrCtryCd = getDefCtry(hdrBean.getGlblCmpyCd());
//                }
//                hdrBean.setShipToCtryCd(idocPtnrCtryCd);
                // 2018/11/29 S21_NA#28899 Del End
            } else {

                Map<String, Object> custInfo = getShipInfo(hdrBean.getGlblCmpyCd(), hdrBean.getShipToCustCd());
                if (custInfo != null) {

                    hdrBean.setShipToLocNm(convToString(custInfo.get("LOC_NM")));
                    hdrBean.setShipToAddlLocNm(convToString(custInfo.get("ADDL_LOC_NM")));
                    hdrBean.setShipToFirstLineAddr(convToString(custInfo.get("FIRST_LINE_ADDR")));
                    hdrBean.setShipToScdLineAddr(convToString(custInfo.get("SCD_LINE_ADDR")));
                    hdrBean.setShipToThirdLineAddr(convToString(custInfo.get("THIRD_LINE_ADDR")));
                    hdrBean.setShipToFrthLineAddr(convToString(custInfo.get("FRTH_LINE_ADDR")));
                    hdrBean.setShipToCtyAddr(convToString(custInfo.get("CTY_ADDR")));
                    hdrBean.setShipToStCd(convToString(custInfo.get("ST_CD")));
                    hdrBean.setShipToProvNm(convToString(custInfo.get("PROV_NM")));
                    hdrBean.setShipToCntyNm(convToString(custInfo.get("CNTY_NM")));
                    hdrBean.setShipToPostCd(convToString(custInfo.get("POST_CD")));
                    hdrBean.setShipToCtryCd(convToString(custInfo.get("CTRY_CD")));
                    hdrBean.setShipTo01RefCmntTxt(convToString(custInfo.get("FIRST_REF_CMNT_TXT")));
                    hdrBean.setShipTo02RefCmntTxt(convToString(custInfo.get("SCD_REF_CMNT_TXT")));
                }
            }
            // 2018/05/29 S21_NA#24587 Mod Start
//            hdrBean.setRddDt(convToString(attrbTMsg.idocPoDtValTxt.getValue(), hdrBean.getSlsDt()));
            hdrBean.setRddDt(convToString(attrbTMsg.idocPoCustRefDt.getValue(), hdrBean.getSlsDt()));
            // 2018/05/29 S21_NA#24587 Mod End
            String frtCondCd = hdrBean.getFrtCondCd();

            FRT_CONDTMsg frtCondTMsg = new FRT_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(frtCondTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(frtCondTMsg.frtCondCd, frtCondCd);
            frtCondTMsg = (FRT_CONDTMsg) S21ApiTBLAccessor.findByKey(frtCondTMsg);
            checkTMsgDbAccess(frtCondTMsg);
            if (frtCondTMsg != null) {

                hdrBean.setFrtChrgToCd(frtCondTMsg.frtChrgToCd.getValue());
                hdrBean.setFrtChrgMethCd(frtCondTMsg.frtChrgMethCd.getValue());
            }

            return isSuccess;
        } finally {

            writeEndLogLn("updateDsImptOrdForEDi", hdrBean);
        }
    }
    
    // 2018/11/29 S21_NA#28899 Add Start
    /**
     * get County Name Related with Ship Info
     * @param hdrBean
     * @param attrbTMsg
     * @return
     */
    private String getCntyNm(ImptHdrBean hdrBean, DS_IMPT_EXT_ATTRBTMsg attrbTMsg){
        String cntyNm = "";
        String postCd = "";
        String postCd9 = "";
        if (ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrPostCd)){
            postCd = attrbTMsg.idocPtnrPostCd.getValue();
            int length = postCd.length();
            if (length == 9){
                postCd9 = new StringBuilder(postCd).insert(5, "-").toString();
                postCd = postCd.substring(0, 5);
            }
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("postCd5", postCd);
        ssmParam.put("postCd9", postCd9);
        ssmParam.put("ctyAddr", toUpper(attrbTMsg.idocPtnrCtyNm.getValue()));
        ssmParam.put("stCd", toUpper(attrbTMsg.idocPtnrStCd.getValue()));
        ssmParam.put("cntyNm", attrbTMsg.idocPtnrCntyNm.getValue());
        List<Map<String, Object>> cntyList = NWZC226001Query.getInstance().queryMapList("getCntyNm", ssmParam);
        
        if (cntyList.size() == 1) {
            cntyNm = (String) cntyList.get(0).get("CNTY_NM");
        } else {
            if (cntyList.size() > 1) {
                DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NMAM8501E);
                hdrBean.getDsImptOrdErrList().add(errBean);
            }
        }
        return cntyNm;
    }
    
    /**
     * Exist Check Post Code
     * @param glblCmpyCd
     * @param attrbTMsg
     * @return
     */
    // private boolean isExistPostCd(String glblCmpyCd, DS_IMPT_EXT_ATTRBTMsg attrbTMsg){ // 2019/01/15 S21_NA#29875 Mod
    private String getExistPostCd(String glblCmpyCd, DS_IMPT_EXT_ATTRBTMsg attrbTMsg){
        
        POSTTMsg postTmsg = new POSTTMsg();
        postTmsg.setSQLID("001");
        postTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        postTmsg.setConditionValue("postCd01", attrbTMsg.idocPtnrPostCd.getValue());
        POSTTMsgArray tmsgArray = (POSTTMsgArray) S21ApiTBLAccessor.findByCondition(postTmsg);
        if (hasValidValue(tmsgArray)) {
            // return true; // 2019/01/15 S21_NA#29875 Mod
            return attrbTMsg.idocPtnrPostCd.getValue();
        }
        
        String postCd = attrbTMsg.idocPtnrPostCd.getValue();
        String postCd9 = "";
        int length = postCd.length();
        if (length == 9) {
            postCd9 = new StringBuilder(postCd).insert(5, "-").toString();
            postCd = postCd.substring(0, 5);
        }
        
        postTmsg.setConditionValue("postCd01", postCd9);
        tmsgArray = (POSTTMsgArray) S21ApiTBLAccessor.findByCondition(postTmsg);
        if (hasValidValue(tmsgArray)) {
            // ZYPEZDItemValueSetter.setValue(attrbTMsg.idocPtnrPostCd, postCd9); // 2019/01/11 S21_NA#29875 Del
            // return true; // 2019/01/15 S21_NA#29875 Mod 
            return postCd9;
        }
        
        postTmsg.setConditionValue("postCd01", postCd);
        tmsgArray = (POSTTMsgArray) S21ApiTBLAccessor.findByCondition(postTmsg);
        if (hasValidValue(tmsgArray)) {
            // ZYPEZDItemValueSetter.setValue(attrbTMsg.idocPtnrPostCd, postCd); // 2019/01/11 S21_NA#29875 Del
            // return true; // 2019/01/15 S21_NA#29875 Mod
            return postCd;
        }
        
        return null;
        // return false; // 2019/01/15 S21_NA#29875 Mod
    }
    
    /**
     * Exist Check State Code
     * @param glblCmpyCd
     * @param attrbTMsg
     * @return
     */
    private boolean isExistStCd (String glblCmpyCd, DS_IMPT_EXT_ATTRBTMsg attrbTMsg) {
        STTMsg chkStTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(chkStTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkStTMsg.stCd, toUpper(attrbTMsg.idocPtnrStCd.getValue()));
        chkStTMsg = (STTMsg) EZDTBLAccessor.findByKey(chkStTMsg);
        if (chkStTMsg == null) {
            return false;
        }
        return true;
    }
    // 2018/11/29 S21_NA#28899 Add End

    private String getDefCtry(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return (String) NWZC226001Query.getInstance().queryString("getDefCtry", ssmParam);
    }

    private boolean updateDsImptOrdConfigForEDi(DsImptOrdConfigBean configBean) {

        writeStartLogLn("DsImptOrdConfigBean", configBean);

        try {

            ImptHdrBean hdrBean = configBean.imptHdrBean;
            DS_IMPT_ORD_CONFIGTMsg configTMsg = (DS_IMPT_ORD_CONFIGTMsg) configBean;
            ZYPEZDItemValueSetter.setValue(configTMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.billToCustLocCd, hdrBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustLocCd, hdrBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.dropShipFlg, hdrBean.getDropShipFlg());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToLocNm, hdrBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToAddlLocNm, hdrBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdLineAddr, hdrBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstRefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdRefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtyAddr, hdrBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToStCd, hdrBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToProvNm, hdrBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCntyNm, hdrBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToPostCd, hdrBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtryCd, hdrBean.getShipToCtryCd());

            S21ApiTBLAccessor.update(configTMsg);
            if (!addUpdateErrorMsgList(configTMsg, configBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("DsImptOrdConfigBean", configBean);
        }
    }

    private boolean insertDsImptOrdCtacPsnForEDi(ImptHdrBean hdrBean) {

        writeStartLogLn("insertDsImptOrdCtacPsnForEDi", hdrBean);

        try {

            boolean isSuccess = true;
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());

            List<Map<String, Object>> imptIstlList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getDsImptOrdCtacPsn", ssmParam);

            if (hasValueList(imptIstlList)) {

                return true;
            }
            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();

            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(attrbTMsg.idocPoPtnrTpCd_05.getValue())) {

                if (!insertDsImptOrdCtacPsnFromTpForEDi(hdrBean, NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP)) {

                    isSuccess = false;
                }
            }

            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_YP.equals(attrbTMsg.idocPoPtnrTpCd_06.getValue())) {

                if (!insertDsImptOrdCtacPsnFromTpForEDi(hdrBean, NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_YP)) {

                    isSuccess = false;
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("insertDsImptOrdCtacPsnForEDi", hdrBean);
        }
    }

    private boolean insertDsImptOrdCtacPsnFromTpForEDi(ImptHdrBean hdrBean, String idocPoPtrnTpCd) {

        writeStartLogLn("insertDsImptOrdCtacPsnFromTpForEDi", hdrBean);

        try {

            boolean isSuccess = true;
            XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();
            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            CTAC_PSNTMsg ctacPsnTMsg = null;

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, hdrBean.getPoInbdIntfcId());
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CONTACT_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CONTACT_MAPPING_ATTRB3);
            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {

                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CONTACT_MAPPING_ATTRB2_AP);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrId_05); // S21_NA#15559
            } else {

                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CONTACT_MAPPING_ATTRB2_YP);
                ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrId_06); // S21_NA#15559
            }

            NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

            // 2018/07/27 QC#26909 add start
            boolean hasValueCtacNm = false;
            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
                hasValueCtacNm = ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrCtacNm_05);
            } else {
                hasValueCtacNm = ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrCtacNm_06);
            }
            // 2018/07/27 QC#26909 add end
            // 2018/01/23 QC#22776 mod start
//          if (!result.hasError()) {
            if (!result.hasError() && ZYPConstant.FLG_OFF_N.equals(hdrBean.getDropShipFlg())
                    && !hasValueCtacNm) { // QC#26909
          // 2018/01/23 QC#22776 mod end

                XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();

                BigDecimal ctacPsnPk = convToBigDecimal(resultTMsg.trgtAttrbNum_01);

                ctacPsnTMsg = new CTAC_PSNTMsg();
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPk, ctacPsnPk);

                ctacPsnTMsg = (CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(ctacPsnTMsg);
                if (!addFindErrorMsgList(ctacPsnTMsg, "CTAC_PSN", ctacPsnPk, hdrBean)) {

                    return false;
                }

                // Header Level
                // 2018/08/21 S21_NA#27817 Del Start
                // if (!insertDsImptOrdCtacPsnFromTMsgForEDi(hdrBean, null, idocPoPtrnTpCd, ctacPsnTMsg, null)) {

                //     isSuccess = false;
                // }
                // 2018/08/21 S21_NA#27817 Del End

                // Config Level
                for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                    if (!insertDsImptOrdCtacPsnFromTMsgForEDi(hdrBean, configBean, idocPoPtrnTpCd, ctacPsnTMsg, null)) {

                        isSuccess = false;
                    }
                }
            } else {

                // Header Level
                // isSuccess = insertDsImptOrdCtacPsnFromNoDataForEDi(hdrBean, null, idocPoPtrnTpCd); // 2018/08/21 S21_NA#27817 Del
                
                // Config Level
                // 2019/01/16 S21_NA#29535 Mod Start
                // ctacPsnTMsg = getRelnCtacPsnWithConfigInfo(hdrBean, idocPoPtrnTpCd);
                XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
                BigDecimal ctacPsnPk = convToBigDecimal(resultTMsg.trgtAttrbNum_01);
                ctacPsnTMsg = getRelnCtacPsnWithConfigInfo(hdrBean, idocPoPtrnTpCd, ctacPsnPk);
                // 2019/01/16 S21_NA#29535 Mod End
                for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {
                    // 2018/08/21 S21_NA#27817 Add Start
                    if(ctacPsnTMsg != null){
                        if (!insertDsImptOrdCtacPsnFromTMsgForEDi(hdrBean, configBean, idocPoPtrnTpCd, ctacPsnTMsg, null)) {
                            isSuccess = false;
                        }
                        continue;
                    }
                    // 2018/08/21 S21_NA#27817 Add End

                    if (!insertDsImptOrdCtacPsnFromNoDataForEDi(hdrBean, configBean, idocPoPtrnTpCd)) {

                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("insertDsImptOrdCtacPsnFromTpForEDi", hdrBean);
        }
    }

    private boolean insertDsImptOrdCtacPsnFromTMsgForEDi(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean, String idocPoPtrnTpCd, CTAC_PSNTMsg ctacPsnTMsg, String ctacTpCd) {

        writeStartLogLn("insertDsImptOrdCtacPsnFromTMsgForEDi", configBean);

        try {

            DS_IMPT_ORD_CTAC_PSNTMsg tMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
            IImportBean ownerBean = hdrBean;
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ);

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdCtacPsnPk, pk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            if (configBean != null) {

                ownerBean = configBean;
                ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            }

            // RS#7991 temporary change Start
            // ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd,
            // ctacPsnTMsg.ctacTpCd);
            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
                // Mod Start 2017/07/19 QC#19979
//                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_AP", configBean.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_AP", hdrBean.getGlblCmpyCd()));
                // Mod End 2017/07/19 QC#19979
            } else {
                // Mod Start 2017/07/19 QC#19979
//                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_YP", configBean.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_YP", hdrBean.getGlblCmpyCd()));
                // Mod End 2017/07/19 QC#19979

                // 2018/01/31 QC#22776-2 add start
                // if (configBean == null) {
                if(!ZYPCommonFunc.hasValue(hdrBean.getSellToFirstRefCmntTxt())) { // 2018/08/22 S21_NA#27817 Mod
                    StringBuilder attn = new StringBuilder();
                    attn.append(ctacPsnTMsg.ctacPsnFirstNm.getValue());
                    if (ZYPCommonFunc.hasValue(ctacPsnTMsg.ctacPsnMidNm)) {
                        attn.append(" ");
                        attn.append(ctacPsnTMsg.ctacPsnMidNm.getValue());
                    }

                    if (ZYPCommonFunc.hasValue(ctacPsnTMsg.ctacPsnLastNm)) {
                        attn.append(" ");
                        attn.append(ctacPsnTMsg.ctacPsnLastNm.getValue());
                    }
                    
                    hdrBean.setSellToFirstRefCmntTxt(attn.toString());
                }
                // 2018/01/31 QC#22776-2 add end
            }
            // RS#7991 temporary change End

            // 2019/01/16 S21_NA#29535 Add Start
            String catcPsnFirstNm = ctacPsnTMsg.ctacPsnFirstNm.getValue();
            String ctacPsnLastNm  = ctacPsnTMsg.ctacPsnLastNm.getValue();
            String ctacPsnTelNum  = ctacPsnTMsg.ctacPsnTelNum.getValue();
            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
                DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
                if (ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrCtacNm_05)) {
                    String[] splitNm = attrbTMsg.idocPtnrCtacNm_05.getValue().split(" ");
                    if (splitNm.length > 2) {
                        StringBuilder firstNm = new StringBuilder();
                        firstNm.append(splitNm[0]).append(" ").append(splitNm[1]);
                        catcPsnFirstNm = S21StringUtil.subStringByLength(firstNm.toString(), 0, 30);
                        ctacPsnLastNm  = splitNm[2];
                    } else {
                        if (splitNm.length > 0) {
                            catcPsnFirstNm = splitNm[0];
                            ctacPsnLastNm = "";
                        }
                        if (splitNm.length > 1) {
                            ctacPsnLastNm = splitNm[1];
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(attrbTMsg.idocPtnrTelNum_03)) {
                    ctacPsnTelNum = S21StringUtil.subStringByLength(attrbTMsg.idocPtnrTelNum_03.getValue(), 0, 20);
                }
            }
            // 2019/01/16 S21_NA#29535 Add End

            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, catcPsnFirstNm); //ctacPsnTMsg.ctacPsnFirstNm);  // 2019/01/16 S21_NA#29535 Mod
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnMidNm, ctacPsnTMsg.ctacPsnMidNm);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, ctacPsnLastNm);  // ctacPsnTMsg.ctacPsnLastNm);   // 2019/01/16 S21_NA#29535 Mod
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnEmlAddr, ctacPsnTMsg.ctacPsnEmlAddr);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnExtnNum, ctacPsnTMsg.ctacPsnExtnNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTelNum, ctacPsnTelNum);  // ctacPsnTMsg.ctacPsnTelNum);   // 2019/01/16 S21_NA#29535 Mod
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFaxNum, ctacPsnTMsg.ctacPsnFaxNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnCmntTxt, ctacPsnTMsg.ctacPsnCmntTxt);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnCellPhoNum, ctacPsnTMsg.ctacPsnCellPhoNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, ctacPsnTMsg.ctacPsnPk);

            S21ApiTBLAccessor.insert(tMsg);
            if (!addInsertErrorMsgList(tMsg, ownerBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("insertDsImptOrdCtacPsnFromTMsgForEDi", configBean);
        }
    }

    private boolean insertDsImptOrdCtacPsnFromNoDataForEDi(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean, String idocPoPtrnTpCd) {

        writeStartLogLn("insertDsImptOrdCtacPsnFromNoDataForEDi", configBean);

        try {

            DS_IMPT_ORD_CTAC_PSNTMsg tMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
            DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();
            IImportBean ownerBean = hdrBean;
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ);

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdCtacPsnPk, pk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            if (configBean != null) {

                ownerBean = configBean;
                ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            }

            String ctacPsnTelNum, idocPtnrCtacNm;
            if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {

                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_AP", hdrBean.getGlblCmpyCd()));
                idocPtnrCtacNm = attrbTMsg.idocPtnrCtacNm_05.getValue();
                ctacPsnTelNum = attrbTMsg.idocPtnrTelNum_05.getValue();
            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_YP", hdrBean.getGlblCmpyCd()));
                idocPtnrCtacNm = attrbTMsg.idocPtnrCtacNm_06.getValue();
                ctacPsnTelNum = attrbTMsg.idocPtnrTelNum_06.getValue();
                // 2018/01/31 QC#22776-2 add start
                // if (configBean == null) { 
                if(!ZYPCommonFunc.hasValue(hdrBean.getSellToFirstRefCmntTxt())) { // 2018/08/22 S21_NA#27817 Mod
                    hdrBean.setSellToFirstRefCmntTxt(idocPtnrCtacNm);
                }
                // 2018/01/31 QC#22776-2 add end
            }

            String[] splitNm = idocPtnrCtacNm.split(" ");
            if (splitNm.length > 2) {

                // 2018/01/23 QC#22776 add start
                StringBuilder firstNm = new StringBuilder();
                firstNm.append(splitNm[0]).append(" ").append(splitNm[1]);

                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, S21StringUtil.subStringByLength(firstNm.toString(), 0, 30));
                // 2018/07/02 S21_NA#26908 Mod Start
//                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, splitNm[1]);
                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, splitNm[2]);
                // 2018/07/02 S21_NA#26908 Mod End
            } else {
                 // 2018/01/23 QC#22776 add end
                if (splitNm.length > 0) {

                    ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, splitNm[0]);
                }
                if (splitNm.length > 1) {

                    ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, splitNm[1]);
                }
            }

            if (ZYPCommonFunc.hasValue(ctacPsnTelNum) && ctacPsnTelNum.length() > 20) {

                ctacPsnTelNum = ctacPsnTelNum.substring(0, 20);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTelNum, ctacPsnTelNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);

            tMsg.ctacPsnMidNm.clear();
            tMsg.ctacPsnEmlAddr.clear();
            tMsg.ctacPsnExtnNum.clear();
            tMsg.ctacPsnFaxNum.clear();
            tMsg.ctacPsnCmntTxt.clear();
            tMsg.ctacPsnCellPhoNum.clear();
            tMsg.ctacPsnPk.clear();

            S21ApiTBLAccessor.insert(tMsg);
            if (!addInsertErrorMsgList(tMsg, ownerBean, true)) {

                return false;
            }

            return true;

        } finally {

            writeEndLogLn("insertDsImptOrdCtacPsnFromNoDataForEDi", configBean);
        }
    }

    private boolean insertDsImptOrdDelyInfoForEDi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        writeStartLogLn("insertDsImptOrdDelyInfoForEDi", hdrBean);

        try {

            boolean isSuccess = true;

            String dsEdiTrdPtnrRefCd = hdrBean.getDsEdiTrdPtnrRefCd();
            // 2018/01/12 S21_NA#22205 Mod Start
            // String delyAddlCmntTxt = hdrBean.getDsImptExtAttrbTMsg().idocPoNoteTxt.getValue();
            String delyAddlCmntTxt = "";
            if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {

                NMZC610001PMsg apiMsg = callCustomerInfoApi(onBatchType, hdrBean, PROCESS_INSTRUCTION, hdrBean.getBillToCustCd(), hdrBean.getShipToCustAcctCd());
                delyAddlCmntTxt = apiMsg.InstructionList.no(0).dsCustMsgTxt.getValue();
            } else {

                delyAddlCmntTxt = hdrBean.getDsImptExtAttrbTMsg().idocPoNoteTxt.getValue();
            }
            // 2018/01/12 S21_NA#22205 Mod End

            // for header
            // insertDsImptOrdDelyInfoForEDi(hdrBean, null, delyAddlCmntTxt); // 2018/08/21 S21_NA#27817 Del

            // for configuration
            for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

                if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {

                    NMZC610001PMsg apiMsg = callCustomerInfoApi(onBatchType, hdrBean, PROCESS_INSTRUCTION, configBean.billToCustLocCd.getValue(), hdrBean.getShipToCustAcctCd());
                    delyAddlCmntTxt = apiMsg.InstructionList.no(0).dsCustMsgTxt.getValue();
                } else {

                    delyAddlCmntTxt = hdrBean.getDsImptExtAttrbTMsg().idocPoNoteTxt.getValue();
                }
                if (!insertDsImptOrdDelyInfoForEDi(hdrBean, configBean, delyAddlCmntTxt)) {

                    isSuccess = false;
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("insertDsImptOrdDelyInfoForEDi", hdrBean);
        }
    }

    private boolean insertDsImptOrdDelyInfoForEDi(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean, String delyAddlCmntTxt) {

        writeStartLogLn("insertDsImptOrdDelyInfoForEDi", configBean);

        try {

            DS_IMPT_ORD_DELY_INFOTMsg tMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
            IImportBean ownerBean = hdrBean;
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ);

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdDelyInfoPk, pk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            if (configBean != null) {

                ownerBean = configBean;
                ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.delyAddlCmntTxt, delyAddlCmntTxt);

            S21ApiTBLAccessor.insert(tMsg);
            if (!addInsertErrorMsgList(tMsg, ownerBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("insertDsImptOrdDelyInfoForEDi", configBean);
        }
    }

    private boolean updateDsImptOrdDtlForEDi(DsImptLineBean lineBean) {

        writeStartLogLn("updateDsImptOrdDtlForEDi", lineBean);

        try {

            DS_IMPT_ORD_DTLTMsg dtlTMsg = new DS_IMPT_ORD_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, lineBean.imptHdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsImptOrdDtlPk, lineBean.dsImptOrdDtlPk);

            dtlTMsg = (DS_IMPT_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dtlTMsg);
            if (!addFindErrorMsgList(dtlTMsg, "DS_IMPT_ORD_DTL", lineBean.dsImptOrdDtlPk.getValue(), lineBean)) {

                return false;
            }

            BigDecimal ordQty = lineBean.ordQty.getValue();

            BigDecimal dealPrcListPrcAmt = convToBigDecimal(lineBean.prcResultNWZC157004PMsg.xxUnitGrsPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dealPrcListPrcAmt, dealPrcListPrcAmt);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.funcPrcListPrcAmt, exchFuncUnitPrice(lineBean, dealPrcListPrcAmt));
            BigDecimal entDealNetUnitPrcAmt = convToBigDecimal(lineBean.prcResultNWZC157004PMsg.xxUnitNetPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.entDealNetUnitPrcAmt, entDealNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.entFuncNetUnitPrcAmt, exchFuncUnitPrice(lineBean, entDealNetUnitPrcAmt));
            // 2018/07/05 S21_NA#26909 Mod Start
//            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealNetAmt, dealPrcListPrcAmt.multiply(ordQty));
//            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealSlsAmt, entDealNetUnitPrcAmt.multiply(ordQty));
            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealNetAmt, entDealNetUnitPrcAmt.multiply(ordQty));
            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealSlsAmt, dealPrcListPrcAmt.multiply(ordQty));
            // 2018/07/05 S21_NA#26909 Mod End
            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlFuncNetAmt, exchFuncUnitPrice(lineBean, dtlTMsg.cpoDtlDealNetAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlFuncSlsAmt, exchFuncUnitPrice(lineBean, dtlTMsg.cpoDtlDealSlsAmt.getValue()));

            S21ApiTBLAccessor.update(dtlTMsg);
            if (!addUpdateErrorMsgList(dtlTMsg, lineBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("updateDsImptOrdDtlForEDi", lineBean);
        }
    }

    private boolean deriveDsImptOrdDtlAttForEDi(DsImptLineBean lineBean) {

        writeStartLogLn("updateDsImptOrdDtlForEDi", lineBean);

        try {

            ImptHdrBean hdrBean = lineBean.imptHdrBean;
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlAttrb = lineBean.dsImptDtlExtAttrbTMsg;
            DS_IMPT_ORD_DTLTMsg dtlTMsg = (DS_IMPT_ORD_DTLTMsg) lineBean;

            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordSrcRefLineNum, dtlAttrb.dsImptDtlAttrbTxt_08);
            dtlTMsg.ordSrcRefLineSubNum.setValue("001");
            ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseCd, lineBean.mdseCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseNm, lineBean.mdseNm);
            if (lineBean.isSetMdseTp()) {

                ZYPEZDItemValueSetter.setValue(dtlTMsg.setMdseCd, lineBean.mdseCd);
            } else {

                dtlTMsg.setMdseCd.clear();
            }
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dropShipFlg, hdrBean.getDropShipFlg());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCustCd, hdrBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToLocNm, hdrBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToAddlLocNm, hdrBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdLineAddr, hdrBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstRefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdRefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtyAddr, hdrBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToStCd, hdrBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToProvNm, hdrBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCntyNm, hdrBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToPostCd, hdrBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtryCd, hdrBean.getShipToCtryCd());

            NWXC220001Result<Map<String, Object>> result = NWXC220001.getQtyInfoForEdi(hdrBean.getGlblCmpyCd(), lineBean.custUomCd.getValue(), lineBean.mdseInfo.mdseCd.getValue(), dtlAttrb.idocPoDtlOrdQty.getValue());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.custUomCd, lineBean.custUomCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordQty, BigDecimal.ONE);

            if (result.hasResultObject()) {

                Map<String, Object> resultObjectMap = result.getResultObject();

                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordCustUomQty, convToBigDecimal(resultObjectMap.get("ORD_CUST_UOM_QTY")));
                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordQty, convToBigDecimal(resultObjectMap.get("ORD_QTY")));
                // 2018/02/01 S21_NA#22617 Del Start
//                String pkgUomCd = ((MDSE_STORE_PKGTMsg) resultObjectMap.get("MDSE_STORE_PKGTMsg")).pkgUomCd.getValue();
//                ZYPEZDItemValueSetter.setValue(lineBean.custUomCd, pkgUomCd);
                // 2018/02/01 S21_NA#22617 Del End
            }

            ZYPEZDItemValueSetter.setValue(dtlTMsg.invtyLocCd, lineBean.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.prcCatgCd, hdrBean.getPrcCatgCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.flPrcListCd, hdrBean.getFlPrcListCd());

            ZYPEZDItemValueSetter.setValue(dtlTMsg.prcBaseDt, hdrBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ccyCd, lineBean.ccyCd);
            // 2018/01/29 QC#22772 add start
            if (ZYPCommonFunc.hasValue(dtlAttrb.dsImptDtlAttrbTxt_04)) {
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rddDt, dtlAttrb.dsImptDtlAttrbTxt_04.getValue());
            } else {
            // 2018/01/29 QC#22772 add end
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rddDt, hdrBean.getRddDt());
            }
            ZYPEZDItemValueSetter.setValue(dtlTMsg.slsRepOrSlsTeamTocCd, hdrBean.getSlsRepTocCd());
            ZYPEZDItemValueSetter.setValue(dtlTMsg.custMdseCd, dtlAttrb.idocPoDtlMatNum_01);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.unitNetWt, lineBean.unitNetWt);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdLineCatgCd, lineBean.dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordLineSrcCd, lineBean.ordLineSrcCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlWhCd, lineBean.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlSwhCd, lineBean.rtlSwhCd);

            S21ApiTBLAccessor.update(dtlTMsg);
            if (!addUpdateErrorMsgList(dtlTMsg, lineBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("updateDsImptOrdDtlForEDi", lineBean);
        }
    }

    private BigDecimal exchFuncUnitPrice(DsImptLineBean lineBean, BigDecimal dealAmt) {

        writeStartLogLn("exchFuncUnitPrice", lineBean);

        try {

            BigDecimal funcAmt = null;
            NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
            exchData.setGlblCmpyCd(lineBean.imptHdrBean.getGlblCmpyCd());
            exchData.setSlsDt(lineBean.imptHdrBean.getSlsDt());
            exchData.setCcyCd(lineBean.ccyCd.getValue());
            List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
            NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

            NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
            grsAmt.setDealAmt(dealAmt);

            exchAmtData.setGrsAmt(grsAmt);
            priceDataList.add(exchAmtData);

            exchData.setPriceData(priceDataList);

            NWXC001001Exchange.exchFuncUnitPrice(exchData);
            if (!exchData.getXxMsgIdList().isEmpty()) {

                return null;
            }
            for (int i = 0; i < exchData.getPriceData().size(); i++) {

                NWXC001001ExchangePriceData prcData = exchData.getPriceData().get(i);
                for (int j = 0; j < prcData.getAmountList().size(); j++) {

                    funcAmt = prcData.getAmountList().get(j).getFuncAmt();
                }
            }
            return funcAmt;
        } finally {

            writeEndLogLn("exchFuncUnitPrice", lineBean);
        }
    }

    private boolean insertDsImptOrdSlsCr(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean) {

        writeStartLogLn("insertDsImptOrdSlsCr", hdrBean);

        try {
            DS_IMPT_ORD_SLS_CRTMsg slsCrTMsg = new DS_IMPT_ORD_SLS_CRTMsg();
            IImportBean ownerBean = hdrBean;
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ);

            ZYPEZDItemValueSetter.setValue(slsCrTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdSlsCrPk, pk);
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            if (configBean != null) {

                ownerBean = configBean;
                ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdConfigPk, configBean.dsImptOrdConfigPk);
            }
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepTocCd, hdrBean.getSlsRepTocCd());
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepRoleTpCd, hdrBean.getSlsRepRoleTpCd());
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepCrPct, BigDecimal.valueOf(100));
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);

            S21ApiTBLAccessor.insert(slsCrTMsg);
            if (!addInsertErrorMsgList(slsCrTMsg, ownerBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("insertDsImptOrdSlsCr", hdrBean);
        }
    }

    private boolean insertDsImptPrcCalcBase(DsImptLineBean lineBean) {

        writeStartLogLn("insertDsImptPrcCalcBase", lineBean);

        try {

            NWZC157002PMsg prcNWZC157002PMsg = lineBean.prcResultNWZC157002PMsg;

            if (prcNWZC157002PMsg == null || prcNWZC157002PMsg.NWZC157003PMsg.getValidCount() == 0) {

                return true;
            }

            // 2018/07/05 S21_NA#26909 Add Start
            for (int i = 0; i < prcNWZC157002PMsg.NWZC157003PMsg.getValidCount(); i++) {
            // 2018/07/05 S21_NA#26909 Add End
                // 2018/07/05 S21_NA#26909 Mod Start
//                NWZC157003PMsg prcNWZC157003PMsg = prcNWZC157002PMsg.NWZC157003PMsg.no(0);
                NWZC157003PMsg prcNWZC157003PMsg = prcNWZC157002PMsg.NWZC157003PMsg.no(i);
                // 2018/07/05 S21_NA#26909 Mod End

                DS_IMPT_PRC_CALC_BASETMsg prcCalcBaseTMsg = new DS_IMPT_PRC_CALC_BASETMsg();
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_PRC_CALC_BASE_SQ);

                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.glblCmpyCd, lineBean.imptHdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.dsImptPrcCalcBasePk, pk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.dsImptOrdPk, lineBean.dsImptOrdPk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.dsImptOrdDtlPk, lineBean.dsImptOrdDtlPk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCondTpCd, prcNWZC157003PMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcDtlGrpCd, prcNWZC157003PMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcJrnlGrpCd, prcNWZC157003PMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.pkgUomCd, prcNWZC157003PMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCondUnitCd, prcNWZC157003PMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCalcMethCd, prcNWZC157003PMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.dsMdsePrcPk, prcNWZC157003PMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.specCondPrcPk, prcNWZC157003PMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.frtPerWtPk, prcNWZC157003PMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCondManEntryFlg, prcNWZC157003PMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCondManAddFlg, prcNWZC157003PMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcCondManDelFlg, prcNWZC157003PMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.autoPrcAmtRate, prcNWZC157003PMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.maxPrcAmtRate, prcNWZC157003PMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.minPrcAmtRate, prcNWZC157003PMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.manPrcAmtRate, prcNWZC157003PMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.calcPrcAmtRate, prcNWZC157003PMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.unitPrcAmt, prcNWZC157003PMsg.unitPrcAmt);
                prcCalcBaseTMsg.coaAcctCd.clear();
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.autoPrcCcyCd, prcNWZC157003PMsg.autoPrcCcyCd);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcRuleApplyFlg, prcNWZC157003PMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(prcCalcBaseTMsg.prcRulePrcdPk, prcNWZC157003PMsg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End

                S21ApiTBLAccessor.insert(prcCalcBaseTMsg);
                if (!addInsertErrorMsgList(prcCalcBaseTMsg, lineBean, true)) {
    
                    return false;
                }
            // 2018/07/05 S21_NA#26909 Add Start
            }
            // 2018/07/05 S21_NA#26909 Add End

            return true;
        } finally {

            writeEndLogLn("insertDsImptPrcCalcBase", lineBean);
        }
    }

    // Call Order Created Web Service For SOM.
    private boolean callOrdCreateWebSvcForSom(ImptHdrBean hdrBean) {

        writeStartLogLn("callOrdCreateWebSvcForSom", hdrBean);

        try {

            String userId = null;
            Map<String, Object> slsRep = NWXC220001.getSlsRepInfo(hdrBean.getGlblCmpyCd(), null, hdrBean.getSlsRepTocCd(), null, hdrBean.getSlsDt());
            if (slsRep != null && !slsRep.isEmpty()) {

                userId = (String) slsRep.get("SLS_REP_PSN_NUM");
            }
            String messageId = NWXC412001SomWebService.orderCreated(hdrBean.getOrdSrcRefNum(), hdrBean.getNewOrdNum(), userId, null);

            if (S21StringUtil.isNotEmpty(messageId)) {

                if (S21StringUtil.isEquals(messageId, "NWAM4120E")) {

                    new DsImptOrdErrBean(hdrBean, messageId, new String[] {hdrBean.getOrdSrcRefNum() });
                } else {

                    new DsImptOrdErrBean(hdrBean, messageId, new String[] {hdrBean.getNewOrdNum() });
                }
                return false;
            } else {

                return true;
            }
        } finally {

            writeEndLogLn("callOrdCreateWebSvcForSom", hdrBean);
        }
    }

    // 2018/01/23 QC#18798 Add Start
    // Call Order Created Web Service For SOM.
    private boolean callOrdCreateWebSvcForEops(ImptHdrBean hdrBean) {

        writeStartLogLn("callOrdCreateWebSvcForSom", hdrBean);

        try {

            String userId = null;
            Map<String, Object> slsRep = NWXC220001.getSlsRepInfo(hdrBean.getGlblCmpyCd(), null, hdrBean.getSlsRepTocCd(), null, hdrBean.getSlsDt());
            if (slsRep != null && !slsRep.isEmpty()) {

                userId = (String) slsRep.get("SLS_REP_PSN_NUM");
            }
            String messageId = NWXC412001SomWebService.orderCreated(hdrBean.getOrdSrcRefNum(), hdrBean.getNewOrdNum(), userId, null);

            if (S21StringUtil.isNotEmpty(messageId)) {

                if (S21StringUtil.isEquals(messageId, "NWAM4120E")) {

                    new DsImptOrdErrBean(hdrBean, messageId, new String[] {hdrBean.getOrdSrcRefNum() });
                } else {

                    new DsImptOrdErrBean(hdrBean, messageId, new String[] {hdrBean.getNewOrdNum() });
                }
                return false;
            } else {

                return true;
            }
        } finally {

            writeEndLogLn("callOrdCreateWebSvcForSom", hdrBean);
        }
    }
    // 2018/01/23 QC#18798 Add End

    private void thereforeDocumentAttach(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {
        writeStartLogLn("thereforeDocumentAttach", hdrBean);

        try {

            String thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_CONN_AVAL_FLG, hdrBean.getGlblCmpyCd());
            if (!ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {

                S21InfoLogOutput.println("NWAB221001 Therefore process was skipped. THEREFORE_CONN_AVAL_FLG is 'N'.");
                return;
            }

            Integer thereforeDocId;

            if (ZYPCommonFunc.hasValue(hdrBean.getAquNum())) {

                thereforeDocId = callThereforeWebService(hdrBean);
                if (thereforeDocId != null) {

                    writeLogLn("therefore Doc Id : %d", thereforeDocId);
                    callThereforeDocumentAttachApi(onBatchType, hdrBean, thereforeDocId);
                }
            }

        } finally {

            writeEndLogLn("thereforeDocumentAttach", hdrBean);
        }
    }

    private Integer callThereforeWebService(ImptHdrBean hdrBean) {

        writeStartLogLn("callThereforeWebService", hdrBean);

        try {

            // call Therefore Web Service
            Integer thereforeDocId = null;

            IThereforeService thereforWebSerive = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

            DOC_MGT_CATGTMsg catg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(catg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(catg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
            catg = (DOC_MGT_CATGTMsg) S21ApiTBLAccessor.findByKey(catg);
            if (catg == null || !ZYPCommonFunc.hasValue(catg.docMgtCatgNum)) {

                return null;
            }

            // Mod Start 2018/03/20 QC#23991
//            String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NWAB2210_THEREFORE_COND_FLD, hdrBean.getGlblCmpyCd());
            String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_THEREFORE_COND_FLD, hdrBean.getGlblCmpyCd());
            // Mod End 2018/03/20 QC#23991
            if (!ZYPCommonFunc.hasValue(searchCondFldCd)) {

                return null;
            }

            DOC_MGT_FLDTMsg fld = new DOC_MGT_FLDTMsg();
            ZYPEZDItemValueSetter.setValue(fld.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(fld.docMgtFldCd, searchCondFldCd);
            fld = (DOC_MGT_FLDTMsg) S21ApiTBLAccessor.findByKey(fld);
            if (fld == null || !ZYPCommonFunc.hasValue(fld.docMgtFldNum)) {
                return null;
            }

            // Set parameters
            ExecuteSingleQueryParams params = new ExecuteSingleQueryParams();
            // category
            QueryObject queryObject = new QueryObject();
            ArrayOfWSCondition conditions = new ArrayOfWSCondition();
            queryObject.setConditions(conditions);
            queryObject.setCategoryNo(catg.docMgtCatgNum.getValueInt());
            params.setQuery(queryObject);

            // condition
            WSCondition condition = new WSCondition();
            // 2018/01/23 QC#18798 Mod Start
            //condition.setCondition(hdrBean.getAquNum());
            if (paramDsImptOrdConfigPk == null) {
                condition.setCondition(hdrBean.getAquNum());
            } else {
                for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
                    condition.setCondition(hdrBean.getAquNum() + '-' + confBean.origDsOrdPosnNum + '(' + confBean.eopsConfigLtrTxt.getValue() + ')');
                }
            }
            // 2018/01/23 QC#18798 Mod End

            condition.setFieldNoOrName(fld.docMgtFldNum.getValue().toPlainString());
            conditions.getWSCondition().add(condition);

            // Invoke Therefore web service
            String startDt = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss:SSS");
            writeLogLn("Invoke Therefore web service Start Time : %s", startDt);

            // Call Web Service
            ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);

            String endDt = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss:SSS");
            writeLogLn("Invoke Therefore web service End Time : %s", endDt);
            java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
            if (rows.size() > 0) {

                thereforeDocId = rows.get(0).getDocNo();
            }

            return thereforeDocId;

        // 2018/05/21 QC#24244 Add Start
        } catch (SOAPFaultException e) {
            e.printStackTrace();

            DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
            docMgtCatg = (DOC_MGT_CATGTMsg) EZDTBLAccessor.findByKey(docMgtCatg);

            DOC_MGT_ATT_RQSTTMsg docMgtAttRqstTMsg = new DOC_MGT_ATT_RQSTTMsg();

            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_ATT_RQST_SQ));
            //ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtDocId, );
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtCatgNum, docMgtCatg.docMgtCatgNum);
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtPrntDocNum, hdrBean.getNewOrdNum());
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtBizDocNum, hdrBean.getNewOrdNum());
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtRqstTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtRqstStsCd, DOC_MGT_RQST_STS.NEW);
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRqstProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRtryRqstFlg, ZYPConstant.FLG_ON_Y);

            EZDTBLAccessor.insert(docMgtAttRqstTMsg);
            return null;
        // 2018/05/21 QC#24244 Add End
        } finally {

            writeEndLogLn("callThereforeWebService", hdrBean);
        }
    }

    private void callThereforeDocumentAttachApi(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, Integer thereforeDocId) {

        writeStartLogLn("callThereforeDocumentAttachApi", hdrBean);

        try {

            ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();

            params.setBusinessId("NWAL1500");
            params.setAttDataGrp(hdrBean.getNewOrdNum());
            params.setBusinessNm("Order Entry");

            // 2017/04/05 S21_NA#18206 Mod Start
            params.setAttDocTpCd(ATT_DATA_DOC_TP.SALES_ORDER_THEREFORE);
            params.setAttDataKeyNm("Order Number");
            params.setThereforeDocId(thereforeDocId.toString());
            params.setBizApiIdTherefore("NWZC224001");

            NWZC224001PMsg pmsg = new NWZC224001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(pmsg.docMgtDocId, BigDecimal.valueOf(thereforeDocId));
            ZYPEZDItemValueSetter.setValue(pmsg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
            ZYPEZDItemValueSetter.setValue(pmsg.docMgtPrntDocNum, hdrBean.getNewOrdNum());
            ZYPEZDItemValueSetter.setValue(pmsg.docMgtBizDocNum, hdrBean.getNewOrdNum());
            // 2017/04/05 S21_NA#18206 Mod End

            params.setArgsForBizApiTherefore(new Object[] {pmsg, onBatchType, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE });

            // Call Therefore Document Attach API
            ZYPFileUpDown.uploadTherefore(params);
        } finally {

            writeEndLogLn("callThereforeDocumentAttachApi", hdrBean);
        }
    }

    private static boolean checkTMsgDbAccess(EZDTMsg tMsg) {

        return checkTMsgDbAccess(tMsg, true);
    }

    private static boolean checkTMsgDbAccess(EZDTMsg tMsg, boolean doThrowErr) {

        if (tMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            if (doThrowErr) {

                throw new S21AbendException(MSG_ID.NMAM0283E.toString());
            }
            return false;
        }

        return true;
    }

    private static boolean addFindErrorMsgList(EZDTMsg tMsg, String tableNm, Object pk, IImportBean owner) {

        return addFindErrorMsgList(tMsg, tableNm, pk, owner, true);
    }

    private static boolean addFindErrorMsgList(EZDTMsg tMsg, String tableNm, Object pk, IImportBean owner, boolean doThrowErr) {

        boolean isSuccess = true;
        if (tMsg == null) {

            isSuccess = false;
        } else if (!checkTMsgDbAccess(tMsg, doThrowErr)) {

            isSuccess = false;
        }

        if (!isSuccess) {

            DsImptOrdErrBean errBean;

            if (pk == null) {

                errBean = DsImptOrdErrBean.createTableExistError(owner, tableNm);
            } else {

                errBean = DsImptOrdErrBean.createTableExistError(owner, tableNm, pk);
            }
            owner.getDsImptOrdErrList().add(errBean);
        }

        return isSuccess;
    }

    private static boolean addInsertErrorMsgList(EZDTMsg tMsg, IImportBean owner, boolean doThrowErr) {

        if (!checkTMsgDbAccess(tMsg, doThrowErr)) {

            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableInsertError(owner, tMsg.getTableName());
            owner.getDsImptOrdErrList().add(errBean);
            return false;
        }

        return true;
    }

    private static boolean addUpdateErrorMsgList(EZDTMsg tMsg, IImportBean owner, boolean doThrowErr) {

        if (!checkTMsgDbAccess(tMsg, doThrowErr)) {

            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableUpdateError(owner, tMsg.getTableName());
            owner.getDsImptOrdErrList().add(errBean);
            return false;
        }

        return true;
    }

    private static List<DsImptOrdErrBean> addErrorMsgList(EZDPMsg pMsg, IImportBean owner) {

        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
        DsImptOrdErrBean errBean;

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {

                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {

                    // 2017/08/16 S21_NA#19088 Add Start
                    // Mod Start 2018/03/13 QC#22967
                    //if (owner instanceof DsImptOrdConfigBean && MSG_ID.NWZM1455E.toString().equals(msgId)) {
                    //
                    //    DsImptOrdConfigBean config = (DsImptOrdConfigBean) owner;
                    //
                    //    String shipTo = config.shipToCustLocCd.getValue();
                    //    String soldTo = config.imptHdrBean.getSoldToCustLocCd();
                    //    String billTo = config.billToCustLocCd.getValue();
                    //
                    //    msgId = MSG_ID.NWZM2236E.toString();
                    //    errBean = new DsImptOrdErrBean(owner, msgId, new String[] {shipTo, soldTo , shipTo, billTo});
                    if (owner instanceof DsImptOrdConfigBean) {

                        DsImptOrdConfigBean config = (DsImptOrdConfigBean) owner;

                        String shipTo = config.shipToCustLocCd.getValue();
                        String soldTo = config.imptHdrBean.getSoldToCustLocCd();
                        String billTo = config.billToCustLocCd.getValue();

                        if (MSG_ID.NWZM2254E.toString().equals(msgId)) {
                            msgId = MSG_ID.NWZM2257E.toString();
                            errBean = new DsImptOrdErrBean(owner, msgId, new String[] {shipTo, soldTo });
                        } else if (MSG_ID.NWZM2255E.toString().equals(msgId)) {
                            msgId = MSG_ID.NWZM2258E.toString();
                            errBean = new DsImptOrdErrBean(owner, msgId, new String[] {soldTo, billTo });
                        } else {
                            errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                        }
                        // Mod End 2018/03/13 QC#22967

                    // 2018/06/01 QC#26273 add start
                    } else if (owner instanceof DsImptLineBean) { 

                        DsImptLineBean line = (DsImptLineBean) owner;
                        MDSETMsg mdseTMsg = line.mdseInfo;

                        if (NWZC150001Constant.NWZM1488E.equals(msgId)) {
                            msgId = MSG_ID.NWZM2023E.toString();
                            errBean = new DsImptOrdErrBean(owner, msgId, new String[] {String.valueOf(mdseTMsg.cpoMinOrdQty.getValueInt())});
                        } else if (NWZC150001Constant.NWZM1489E.equals(msgId)) {
                            msgId = MSG_ID.NWZM2024E.toString();
                            errBean = new DsImptOrdErrBean(owner, msgId, new String[] {String.valueOf(mdseTMsg.cpoMaxOrdQty.getValueInt())});
                        } else if (NWZC150001Constant.NWZM1492E.equals(msgId)) {
                            msgId = MSG_ID.NWZM2025E.toString();
                            errBean = new DsImptOrdErrBean(owner, msgId, new String[] {String.valueOf(mdseTMsg.cpoIncrOrdQty.getValueInt())});
                        } else {
                            errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                        }
                    // 2018/06/01 QC#26273 add end
                    } else {
                        errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                    }

                    // 2017/08/16 S21_NA#19088 Add End

                    // errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                    errBeanList.add(errBean);

                    if (owner != null) {

                        if (!owner.isSameErrorExists(errBean)) {

                            owner.getDsImptOrdErrList().add(errBean);
                        }
                    }

                    writeErrLog(errBean);
                }
            }
        }

        return errBeanList;
    }

    private List<DsImptOrdErrBean> addPrcErrorMsgList(EZDPMsg pMsg, IImportBean owner, ImptHdrBean hdrBean) {

        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
        DsImptOrdErrBean errBean;
        // boolean isWhTran = isWHTransfer(hdrBean); // 2018/06/12 S21_NA#24294 Mod

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            boolean isWhTran = isWHTransfer(hdrBean); // 2018/06/12 S21_NA#24294 Mod
            for (int i = 0; i < msgList.size(); i++) {

                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (MSG_ID.NWZM1328E.toString().equals(msgId) && isWhTran) {

                    continue;
                }

                if (msgId.endsWith("E")) {

                    errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                    errBeanList.add(errBean);

                    if (owner != null) {

                        owner.getDsImptOrdErrList().add(errBean);
                    }

                    writeErrLog(errBean);
                }
            }
        }

        return errBeanList;
    }

    private static <T> List<DsImptOrdErrBean> addErrorMsgListFromImptAttrb(NWXC220001Result<T> attbResult, IImportBean owner) {

        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
        DsImptOrdErrBean errBean;

        for (NWXC220001ErrorInfo errorInfo : attbResult.getErrInfoList()) {

            errBean = new DsImptOrdErrBean(owner, errorInfo.getErrMsgId(), errorInfo.getParamArray());
            errBeanList.add(errBean);

            owner.getDsImptOrdErrList().add(errBean);

            writeErrLog(errBean);
        }

        return errBeanList;
    }

    private static List<DsImptOrdErrBean> addErrorMsgListForDsCpoUpdateApi(NWZC150001PMsg pMsg, ImptHdrBean hdrBean, DsImptSvcDtlBean dsImptSvcDtlBean) {

        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
        DsImptOrdErrBean errBean;

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {

                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String lineNum = pMsg.xxMsgIdList.no(i).xxLineNum.getValue();
                String msgTp = pMsg.xxMsgIdList.no(i).xxRsltModeCd.getValue();
                if (msgId.endsWith("E")) {

                    IImportBean owner = null;
                    if (S21StringUtil.isEquals(msgTp, NWZC150001Constant.RESULT_TP_SHELL)) {

                        if (dsImptSvcDtlBean == null) {

                            continue;
                        }
                        if (ZYPCommonFunc.hasValue(dsImptSvcDtlBean.dsImptSvcLineNum) && S21StringUtil.isEquals(dsImptSvcDtlBean.dsImptSvcLineNum.getValue().toPlainString(), lineNum)) {

                            owner = dsImptSvcDtlBean;
                        } else {

                            continue;
                        }
                    } else {

                        owner = hdrBean;
                    }
                    if (owner == null) {

                        owner = hdrBean;
                    }

                    // 2017/08/16 S21_NA#19088 Add Start
                    // Mod Start 2018/03/13 QC#22967
                    //if (MSG_ID.NWZM1455E.toString().equals(msgId)) {
                    //
                    //    String shipTo = pMsg.shipToCustAcctCd.getValue();
                    //    String soldTo = pMsg.soldToCustLocCd.getValue();
                    //    String billTo = pMsg.billToCustCd.getValue();
                    //
                    //    msgId = MSG_ID.NWZM2236E.toString();
                    //    errBean = new DsImptOrdErrBean(owner, msgId, new String[] {shipTo, soldTo , shipTo, billTo});
                    if (MSG_ID.NWZM2254E.toString().equals(msgId)) {
                        String shipTo = pMsg.addShipToCustCd.getValue();
                        String soldTo = pMsg.soldToCustLocCd.getValue();

                        msgId = MSG_ID.NWZM2257E.toString();
                        errBean = new DsImptOrdErrBean(owner, msgId, new String[] {shipTo, soldTo });
                    } else if (MSG_ID.NWZM2255E.toString().equals(msgId)) {
                        String soldTo = pMsg.soldToCustLocCd.getValue();
                        String billTo = pMsg.billToCustCd.getValue();

                        msgId = MSG_ID.NWZM2258E.toString();
                        errBean = new DsImptOrdErrBean(owner, msgId, new String[] {soldTo, billTo });
                        // Mod End 2018/03/13 QC#22967
                    } else {

                        errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                    }

                    // 2017/08/16 S21_NA#19088 Add End

                    // errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                    errBeanList.add(errBean);

                    if (!owner.isSameErrorExists(errBean)) {

                        owner.getDsImptOrdErrList().add(errBean);
                    }

                    writeErrLog(errBean);
                }
            }
        }

        return errBeanList;
    }


    // Add Start 2017/06/29 QC#19068
    private static List<DsImptOrdErrBean> addErrorMsgListForContractApi(NSZC115001PMsg pMsg, ImptHdrBean hdrBean, DsImptSvcDtlBean dsImptSvcDtlBean) {
        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
        DsImptOrdErrBean errBean = null;

        // QC#27738 add
        List<String> svcErrMsgIdList = new ArrayList<String>(pMsg.xxMsgIdList.getValidCount());
        if (dsImptSvcDtlBean != null){
            svcErrMsgIdList = getErrMsgIdList(pMsg, dsImptSvcDtlBean.dsImptSvcLineNum.getValue().toPlainString());
            if(svcErrMsgIdList.isEmpty()){
                return errBeanList;
            }
        }

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {

                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                IImportBean owner = null;
                boolean isShellHeaderError = false; // QC#27738 add

                // Mod Start 2017/09/11 QC#21033
                if (msgId.endsWith("E")) {
                    for (int j = 0; j < pMsg.svcDtlList.getValidCount(); j++) {
                        String shellNum = null;
                        // String mdseCd = null; // 2018/12/05 S21_NA#29287 Del

                        if (ZYPCommonFunc.hasValue(pMsg.svcDtlList.no(j).shellLineNum)) {
                            shellNum = pMsg.svcDtlList.no(j).shellLineNum.getValue().toPlainString();
                        }
                        // 2018/12/05 S21_NA#29287 Del Start
                        // if (ZYPCommonFunc.hasValue(pMsg.svcAddlChrgPrcList.no(j).addlChrgMdseCd)) {
                        //     mdseCd = pMsg.svcAddlChrgPrcList.no(j).addlChrgMdseCd.getValue();
                        // }
                        // 2018/12/05 S21_NA#29287 Del End
                        if (dsImptSvcDtlBean == null) {
                            continue;
                        }
                        if (ZYPCommonFunc.hasValue(dsImptSvcDtlBean.dsImptSvcLineNum) //
                                && null != shellNum //
                                && S21StringUtil.isEquals(dsImptSvcDtlBean.dsImptSvcLineNum.getValue().toPlainString(), shellNum)) {
                            if (svcErrMsgIdList.contains(msgId)) { // QC#27738 add
                                owner = dsImptSvcDtlBean;
                            }
                        } else {
                            continue;
                        }
                        if (owner == null) {
                            owner = hdrBean;
                            isShellHeaderError = true; // QC#27738 add
                        }

                        // Mod Start 2017/08/21 QC#19233
                        boolean svcPrcErr = false;
                        boolean addlChrgPrcErr = false; // 2018/12/05 S21_NA#29287 Add
                        boolean addlBasePrcErr = false; // 2019/01/05 S21_NA#29579 Add

                        if (MSG_ID.NSZM1167E.toString().equals(msgId)) {
                            // 2018/12/05 S21_NA#29287 Mod Start
                            // msgId = MSG_ID.NWZM2233E.toString();
                            //errBean = new DsImptOrdErrBean(owner, msgId, new String[] {mdseCd });
                            addlChrgPrcErr = true;
                            // 2018/12/05 S21_NA#29287 Mod End
                        // 2019/01/05 S21_NA#29579 Add Start
                        } else if (MSG_ID.NSZM1219E.toString().equals(msgId)) {
                            addlBasePrcErr = true;
                        // 2019/01/05 S21_NA#29579 Add End
                        } else if (MSG_ID.NSZM1189E.toString().equals(msgId)) {
                            svcPrcErr = true;
                        } else {
                            errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
                        }
                        // QC#27738 add
                        if (isShellHeaderError) {
                            errBean.dsImptSvcDtlPk.setValue(BigDecimal.ZERO);
                        }

                        // 2018/12/05 S21_NA#29287 Add Start
                        if (addlChrgPrcErr) {
                            for (int l = 0; l < pMsg.svcAddlChrgPrcList.getValidCount(); l++) {
                                NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg = pMsg.svcAddlChrgPrcList.no(l);
                                String addlMdseCd = svcAddlChrgPrcPMsg.addlChrgMdseCd.getValue();
                                if (!ZYPCommonFunc.hasValue(dsImptSvcDtlBean.dsImptSvcLineNum) //
                                        || !ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.shellLineNum) //
                                        || !dsImptSvcDtlBean.dsImptSvcLineNum.getValue().equals(svcAddlChrgPrcPMsg.shellLineNum.getValue())) {
                                    continue;
                                }
                                if (!S21StringUtil.isEquals(MSG_ID.NSZM1167E.toString(), svcAddlChrgPrcPMsg.xxMsgId.getValue())) {
                                    continue;
                                }
                                errBean = new DsImptOrdErrBean(owner, MSG_ID.NWZM2233E, new String[] {addlMdseCd });
                            }
                        }
                        // 2018/12/05 S21_NA#29287 Add End
                        // 2019/01/05 S21_NA#29579 Add Start
                        if (addlBasePrcErr) {
                            for (int m = 0; m < pMsg.svcAddlBasePrcList.getValidCount(); m++) {
                                NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = pMsg.svcAddlBasePrcList.no(m);
                                if (!ZYPCommonFunc.hasValue(dsImptSvcDtlBean.dsImptSvcLineNum)
                                        || !ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.shellLineNum)
                                        || !dsImptSvcDtlBean.dsImptSvcLineNum.getValue().equals(svcAddlBasePrcPMsg.shellLineNum.getValue())) {
                                    continue;
                                }
                                if (!S21StringUtil.isEquals(MSG_ID.NSZM1219E.toString(), svcAddlBasePrcPMsg.xxMsgId.getValue())) {
                                    continue;
                                }
                                String addlBaseMdseCd = getAddlBaseMdseCd(pMsg.glblCmpyCd.getValue(), svcAddlBasePrcPMsg);
                                errBean = new DsImptOrdErrBean(owner, MSG_ID.NWZM2304E, new String[] {addlBaseMdseCd });
                            }
                        }
                        // 2019/01/05 S21_NA#29579 Add End

                        if (svcPrcErr) {
                            for (int k = 0; k < pMsg.svcPrcList.getValidCount(); k++) {
                                NSZC115001_svcPrcListPMsg svcPrcPMsg = pMsg.svcPrcList.no(k);
                                if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                                    continue;
                                }
                                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.prcMtrPkgPk)) {
                                    continue;
                                }
                                if (!ZYPCommonFunc.hasValue(dsImptSvcDtlBean.dsImptSvcLineNum) //
                                        || !ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum) //
                                        || !dsImptSvcDtlBean.dsImptSvcLineNum.getValue().equals(svcPrcPMsg.shellLineNum.getValue())) {
                                    continue;
                                }
                                if (!isValidPrcMtrPkg(pMsg, svcPrcPMsg)) {
                                    String glblCmpyCd = pMsg.glblCmpyCd.getValue();
                                    String slsDt = pMsg.slsDt.getValue();
                                    String prcMtrPkgNm = getprcMtrPkgNm(svcPrcPMsg.prcMtrPkgPk.getValue(), glblCmpyCd, slsDt);
                                    String prcListCd = svcPrcPMsg.maintPrcCatgCd.getValue();
                                    String mdlNm = getMdlNm(svcPrcPMsg.mdlId.getValue(), glblCmpyCd);
                                    errBean = new DsImptOrdErrBean(owner, MSG_ID.NWZM2237E, new String[] {prcMtrPkgNm, prcListCd, mdlNm });
                                }
                            }
                        }
                    }
                    // Mod End 2017/08/21 QC#19233
                    
                    // 2019/02/05 S21_NA#30236 Add Start
//                    if (owner == null) {
//                        owner = hdrBean;
//                    }
//                    
//                    if (errBean == null) {
//                        errBean = new DsImptOrdErrBean(owner, msgId, msg.getXxMsgPrmArray());
//                    }
                    // 2019/02/05 S21_NA#30236 Add End

                    if (null != errBean) {
                        errBeanList.add(errBean);

                        if (!owner.isSameErrorExists(errBean)) {

                            owner.getDsImptOrdErrList().add(errBean);
                        }

                        writeErrLog(errBean);
                    }
                    // Mod End 2017/09/11 QC#21033
                }
            }
        }

        return errBeanList;
    }
    // Add End 2017/06/29 QC#19068

    // QC#27738 add
    private static List<String> getErrMsgIdList(NSZC115001PMsg pMsg, String shellLineNum) {
        List<String> errMsgIdList = new ArrayList<String>(pMsg.xxMsgIdList.getValidCount());

        for (int i = 0; i < pMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg pSvcDtlMsg = pMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcDtlMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcDtlMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcDtlMsg.xxMsgId)) {
                if (pSvcDtlMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcDtlMsg.xxMsgId.getValue());
                }
            }
        }
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg pSvcConfigRefMsg = pMsg.svcConfigRefList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcConfigRefMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcConfigRefMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcConfigRefMsg.xxMsgId)) {
                if (pSvcConfigRefMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcConfigRefMsg.xxMsgId.getValue());
                }
            }
        }
        for (int i = 0; i < pMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg pSvcPrcMsg = pMsg.svcPrcList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcPrcMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcPrcMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcPrcMsg.xxMsgId)) {
                if (pSvcPrcMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcPrcMsg.xxMsgId.getValue());
                }
            }
        }
        for (int i = 0; i < pMsg.svcUsgPrcList.getValidCount(); i++) {
            NSZC115001_svcUsgPrcListPMsg pSvcUsgPrcMsg = pMsg.svcUsgPrcList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcUsgPrcMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcUsgPrcMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcUsgPrcMsg.xxMsgId)) {
                if (pSvcUsgPrcMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcUsgPrcMsg.xxMsgId.getValue());
                }
            }
        }
        for (int i = 0; i < pMsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg pSvcAddlBasePrcMsg = pMsg.svcAddlBasePrcList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcAddlBasePrcMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlBasePrcMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcAddlBasePrcMsg.xxMsgId)) {
                if (pSvcAddlBasePrcMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcAddlBasePrcMsg.xxMsgId.getValue());
                }
            }
        }
        for (int i = 0; i < pMsg.svcAddlChrgPrcList.getValidCount(); i++) {
            NSZC115001_svcAddlChrgPrcListPMsg pSvcAddlChrgPrcMsg = pMsg.svcAddlChrgPrcList.no(i);
            if (!ZYPCommonFunc.hasValue(pSvcAddlChrgPrcMsg.shellLineNum)) {
                continue;
            }
            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlChrgPrcMsg.shellLineNum.getValue().toPlainString())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(pSvcAddlChrgPrcMsg.xxMsgId)) {
                if (pSvcAddlChrgPrcMsg.xxMsgId.getValue().endsWith("E")) {
                    errMsgIdList.add(pSvcAddlChrgPrcMsg.xxMsgId.getValue());
                }
            }
        }
        return errMsgIdList;
    }

//    private static boolean hasNoErrorInShell(NSZC115001PMsg pMsg, String shellLineNum) {
//        for (int i = 0; i < pMsg.svcDtlList.getValidCount(); i++) {
//            NSZC115001_svcDtlListPMsg pSvcDtlMsg = pMsg.svcDtlList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcDtlMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcDtlMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcDtlMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
//            NSZC115001_svcConfigRefListPMsg pSvcConfigRefMsg = pMsg.svcConfigRefList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcConfigRefMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcConfigRefMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcConfigRefMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        for (int i = 0; i < pMsg.svcPrcList.getValidCount(); i++) {
//            NSZC115001_svcPrcListPMsg pSvcPrcMsg = pMsg.svcPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcPrcMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        for (int i = 0; i < pMsg.svcUsgPrcList.getValidCount(); i++) {
//            NSZC115001_svcUsgPrcListPMsg pSvcUsgPrcMsg = pMsg.svcUsgPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcUsgPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcUsgPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcUsgPrcMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        for (int i = 0; i < pMsg.svcAddlBasePrcList.getValidCount(); i++) {
//            NSZC115001_svcAddlBasePrcListPMsg pSvcAddlBasePrcMsg = pMsg.svcAddlBasePrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcAddlBasePrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlBasePrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcAddlBasePrcMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        for (int i = 0; i < pMsg.svcAddlChrgPrcList.getValidCount(); i++) {
//            NSZC115001_svcAddlChrgPrcListPMsg pSvcAddlChrgPrcMsg = pMsg.svcAddlChrgPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcAddlChrgPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlChrgPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(pSvcAddlChrgPrcMsg.xxMsgId)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean isExistsMsgIdInPMsgList(NSZC115001PMsg pMsg, String msgId, String shellLineNum) {
//        for (int i = 0; i < pMsg.svcDtlList.getValidCount(); i++) {
//            NSZC115001_svcDtlListPMsg pSvcDtlMsg = pMsg.svcDtlList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcDtlMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcDtlMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcDtlMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
//            NSZC115001_svcConfigRefListPMsg pSvcConfigRefMsg = pMsg.svcConfigRefList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcConfigRefMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcConfigRefMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcConfigRefMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.svcPrcList.getValidCount(); i++) {
//            NSZC115001_svcPrcListPMsg pSvcPrcMsg = pMsg.svcPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcPrcMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.svcUsgPrcList.getValidCount(); i++) {
//            NSZC115001_svcUsgPrcListPMsg pSvcUsgPrcMsg = pMsg.svcUsgPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcUsgPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcUsgPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcUsgPrcMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.svcAddlBasePrcList.getValidCount(); i++) {
//            NSZC115001_svcAddlBasePrcListPMsg pSvcAddlBasePrcMsg = pMsg.svcAddlBasePrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcAddlBasePrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlBasePrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcAddlBasePrcMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.svcAddlChrgPrcList.getValidCount(); i++) {
//            NSZC115001_svcAddlChrgPrcListPMsg pSvcAddlChrgPrcMsg = pMsg.svcAddlChrgPrcList.no(i);
//            if (!ZYPCommonFunc.hasValue(pSvcAddlChrgPrcMsg.shellLineNum)) {
//                continue;
//            }
//            if (!S21StringUtil.isEquals(shellLineNum, pSvcAddlChrgPrcMsg.shellLineNum.getValue().toPlainString())) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(msgId, pSvcAddlChrgPrcMsg.xxMsgId.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }

    // 2018/02/07 QC#23998 Add Start
// Del Start 2018/08/21 QC#27489
//    private static boolean  addErrorMsgListForSvcMdlApi(ImptHdrBean hdrBean, Map<String, List<NSZC048001PMsg>> softwareErrMap) {
//
//        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
//        DsImptOrdErrBean errBean = null;
//        boolean returnFlg = true;
//
//        for (DsImptOrdConfigBean confBean : hdrBean.dsImptOrdConfigMap.values()) {
//            List<NSZC048001PMsg> pMsgList = softwareErrMap.get(confBean.dsOrdPosnNum.getValue());
//            if (pMsgList != null) {
//                for (NSZC048001PMsg pMsg : pMsgList) {
//                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                    for (int i = 0; i < msgList.size(); i++) {
//                        S21ApiMessage msg = msgList.get(i);
//                        String msgId = msg.getXxMsgid();
//                        if (msgId.endsWith("E")) {
//                            errBean = new DsImptOrdErrBean(confBean, msgId, msg.getXxMsgPrmArray());
//                        }
//                        if (null != errBean) {
//                            errBeanList.add(errBean);
//                            if (!confBean.isSameErrorExists(errBean)) {
//                                confBean.getDsImptOrdErrList().add(errBean);
//                            }
//                            returnFlg = false;
//                            writeErrLog(errBean);
//                        }
//                    }
//                }
//            }
//        }
//        return returnFlg;
//    }
// Del End 2018/08/21 QC#27489

    // 2018/02/07 QC#23998 Add End

    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {

        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
    }

    private static <T extends List<Map<String, Object>>> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    /**
     * Convert To BigDecimal
     * @param val Object
     * @return BigDecimal
     */
    public static BigDecimal convToBigDecimal(Object val) {

        if (null == val) {

            return null;
        } else if (val instanceof BigDecimal) {

            return (BigDecimal) val;
        } else if (val instanceof EZDTStringItem) {

            return convToBigDecimal(((EZDTStringItem) val).getValue());
        } else if (val instanceof EZDTBigDecimalItem) {

            return ((EZDTBigDecimalItem) val).getValue();
        } else {

            return new BigDecimal(val.toString());
        }
    }

    /**
     * Convert To String
     * @param val Object
     * @return String
     */
    public static String convToString(Object val) {

        return convToString(val, null);
    }

    /**
     * Convert To String
     * @param val Object
     * @param defVal String
     * @return String
     */
    public static String convToString(Object val, String defVal) {

        if (null == val) {

            return defVal;
        } else if (val instanceof BigDecimal) {

            return ((BigDecimal) val).toPlainString();
        } else {

            return val.toString();
        }
    }

    private boolean isWHTransfer(ImptHdrBean hdrBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsOrdTpCd", hdrBean.getDsOrdTpCd());
        ssmParam.put("slsDt", hdrBean.getSlsDt());
        String itrlOrdProcFlg = NWZC226001Query.getInstance().queryString("getItrlOrdProcFlg", ssmParam);

        if (ZYPConstant.FLG_ON_Y.equals(itrlOrdProcFlg)) {
            return true;
        }
        return false;
    }

    private boolean isOrderRetailEquipment(ImptHdrBean hdrBean) {

        // 2018/06/14 S21_NA#24294 Mod Start
        // return NWXC220001.isExistOrdCatg(hdrBean.getGlblCmpyCd(), hdrBean.getDsOrdCatgCd(), hdrBean.getDsOrdTpCd(), hdrBean.getDsOrdRsnCd(), ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, false);
        return NWXC150001DsCheck.isRetailEquipOrder(hdrBean.getGlblCmpyCd(), hdrBean.getDsOrdCatgCd(), hdrBean.getDsOrdTpCd(), hdrBean.getDsOrdRsnCd());
        // 2018/06/14 S21_NA#24294 Mod End
    }

    private boolean isLeaseOrder(ImptHdrBean hdrBean) {

        return NWXC220001.isExistOrdCatg(hdrBean.getGlblCmpyCd(), hdrBean.getDsOrdCatgCd(), hdrBean.getDsOrdTpCd(), hdrBean.getDsOrdRsnCd(), ORD_CATG_CTX_TP.LEASE_ORDER_VALUE_SET, false);
    }

    private void registEDiAckData(ImptHdrBean hdrBean) {
        writeStartLogLn("registEDiAckData", hdrBean);

        try {

            // *****************************************************************
            // Update Latest Create Flag
            // *****************************************************************
            if (ZYPCommonFunc.hasValue(hdrBean.getOrdSrcRefNum())) {
                updateLtstCratFlg(hdrBean);
            }

            // *****************************************************************
            // Insert EDI_PO_ACK_HDR
            // *****************************************************************
            insertEdiPoAckHdr(hdrBean);

            // *****************************************************************
            // Insert EDI_PO_ACK_DTL
            // *****************************************************************
            insertEdiPoAckDtl(hdrBean);

            // *****************************************************************
            // Insert EDI_PO_ACK_SHPG_PLN
            // *****************************************************************
            insertEdiPoAckShpgPln(hdrBean);
        } finally {
            writeEndLogLn("registEDiAckData", hdrBean);
        }
    }

    private boolean insertEdiPoAckHdr(ImptHdrBean hdrBean) {

        writeStartLogLn("insertEdiPoAckHdr", hdrBean);

        try {

            BigDecimal ediPoAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_HDR_SQ);
            hdrBean.setEdiPoAckHdrPk(ediPoAckHdrPk);

            EDI_PO_ACK_HDRTMsg tMsg = new EDI_PO_ACK_HDRTMsg();
            if (hdrBean.hasError()) {

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, ediPoAckHdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
                tMsg.cpoOrdNum.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, hdrBean.getDsOrdRsnCd());
                ZYPEZDItemValueSetter.setValue(tMsg.addPmtTermCashDiscCd, hdrBean.getAddPmtTermCashDiscCd());
                ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, hdrBean.getFrtCondCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, hdrBean.getCarrSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, hdrBean.getBillToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, hdrBean.getShipToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, hdrBean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, hdrBean.getSoldToCustLocCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dropShipFlg, hdrBean.getDropShipFlg());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, hdrBean.getShipToLocNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToAddlLocNm, hdrBean.getShipToAddlLocNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, hdrBean.getShipToScdLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, hdrBean.getShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, hdrBean.getShipToStCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToProvNm, hdrBean.getShipToProvNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCntyNm, hdrBean.getShipToCntyNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, hdrBean.getShipToPostCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtryCd, hdrBean.getShipToCtryCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstRefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdRefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
                tMsg.entCpoTotDealNetAmt.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, hdrBean.getCustIssPoNum());
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, hdrBean.getCustIssPoDt());
                ZYPEZDItemValueSetter.setValue(tMsg.carrAcctNum, hdrBean.getCarrAcctNum());
                tMsg.cpoOrdTs.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.rddDt, hdrBean.getRddDt());
                ZYPEZDItemValueSetter.setValue(tMsg.ediAckSentFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
                tMsg.delyAddlCmntTxt.clear();
                tMsg.prcCalcDt.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
            } else {

                String newOrdNum = hdrBean.getNewOrdNum();

                CPOTMsg cpoTMsg = deriveCpo(hdrBean.getGlblCmpyCd(), newOrdNum);
                hdrBean.setOrigCpo(cpoTMsg);

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, ediPoAckHdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, cpoTMsg.ordSrcRefNum);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoTMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, cpoTMsg.dsOrdCatgCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, cpoTMsg.dsOrdTpCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, cpoTMsg.dsOrdRsnCd);
                ZYPEZDItemValueSetter.setValue(tMsg.addPmtTermCashDiscCd, cpoTMsg.addPmtTermCashDiscCd);
                ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, cpoTMsg.frtCondCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, cpoTMsg.addShpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, cpoTMsg.carrSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, cpoTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, cpoTMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, cpoTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, cpoTMsg.addShipToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, cpoTMsg.sellToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, cpoTMsg.soldToCustLocCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dropShipFlg, cpoTMsg.addDropShipFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, cpoTMsg.addShipToLocNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToAddlLocNm, cpoTMsg.addShipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, cpoTMsg.addShipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, cpoTMsg.addShipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToThirdLineAddr, cpoTMsg.addShipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFrthLineAddr, cpoTMsg.addShipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, cpoTMsg.addShipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, cpoTMsg.addShipToStCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToProvNm, cpoTMsg.addShipToProvNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCntyNm, cpoTMsg.addShipToCntyNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, cpoTMsg.addShipToPostCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtryCd, cpoTMsg.addShipToCtryCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstRefCmntTxt, cpoTMsg.addShipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdRefCmntTxt, cpoTMsg.addShipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.entCpoTotDealNetAmt, cpoTMsg.entCpoTotDealNetAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, cpoTMsg.custIssPoNum);
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, cpoTMsg.custIssPoDt);
                ZYPEZDItemValueSetter.setValue(tMsg.carrAcctNum, cpoTMsg.carrAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTs, cpoTMsg.cpoOrdTs);
                ZYPEZDItemValueSetter.setValue(tMsg.rddDt, cpoTMsg.addRddDt);
                ZYPEZDItemValueSetter.setValue(tMsg.ediAckSentFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("cpoOrdNum", newOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.delyAddlCmntTxt, NWZC226001Query.getInstance().queryString("getDelyAddlCmnt", ssmParam));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCalcDt, cpoTMsg.prcCalcDt);
            }

            S21ApiTBLAccessor.insert(tMsg);
            if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {

                return false;
            }

            return true;
        } finally {

            writeEndLogLn("insertEdiPoAckHdr", hdrBean);
        }
    }

    private boolean insertEdiPoAckDtl(ImptHdrBean hdrBean) {

        writeStartLogLn("insertEdiPoAckDtl", hdrBean);

        try {
            BigDecimal ediPoAckDtlPk;
            EDI_PO_ACK_DTLTMsg tMsg;
            ExpendMdseBean mdseBean;
            String ediAckStsCd;
            boolean isSuccess = true;

            if (hdrBean.hasError()) {

                for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {

                    tMsg = new EDI_PO_ACK_DTLTMsg();
                    ediPoAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_DTL_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, ediPoAckDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, convToString(hdrBean.getOrdSrcRefNum()));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineNum, lineBean.ordSrcRefLineNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineSubNum, lineBean.ordSrcRefLineSubNum);
                    tMsg.cpoOrdNum.clear();
                    tMsg.cpoDtlLineNum.clear();
                    tMsg.cpoDtlLineSubNum.clear();

                    if (lineBean.hasEdiCustUomError) {
                        ediAckStsCd = EDI_ACK_STS.ITEM_REJECTED_UNIT_OF_MEASURE;
                    } else {
                        ediAckStsCd = EDI_ACK_STS.ITEM_REJECTED;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.ediAckStsCd, ediAckStsCd);
                    tMsg.ordLineStsCd.clear();
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, lineBean.ordQty);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, lineBean.ordCustUomQty);
                    ZYPEZDItemValueSetter.setValue(tMsg.entDealNetUnitPrcAmt, lineBean.entDealNetUnitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealNetAmt, lineBean.cpoDtlDealNetAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealSlsAmt, lineBean.cpoDtlDealSlsAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.unitNetWt, lineBean.unitNetWt);
                    ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, lineBean.ccyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, lineBean.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, lineBean.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, lineBean.custUomCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, lineBean.mdseCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, lineBean.custMdseCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.exchRate, lineBean.exchRate);

                    S21ApiTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {

                        isSuccess = false;
                    }
                }
            } else {
                String newOrdNum = hdrBean.getNewOrdNum();
                Map<String, Object> ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("cpoOrdNum", newOrdNum);

                List<Map<String, Object>> cpoDtlList = NWZC226001Query.getInstance().queryMapList("getCpoDtlForEdiAck", ssmParam);

                if (!hasValueList(cpoDtlList)) {

                    return true;
                }
                Map<String, Object> cpoDtl;
                for (int i = 0; i < cpoDtlList.size(); i++) {
                    cpoDtl = cpoDtlList.get(i);

                    tMsg = new EDI_PO_ACK_DTLTMsg();
                    ediPoAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_DTL_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, ediPoAckDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, convToString(hdrBean.getOrigCpo().ordSrcRefNum.getValue()));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineNum, convToString(cpoDtl.get("ORD_SRC_REF_LINE_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineSubNum, convToString(cpoDtl.get("ORD_SRC_REF_LINE_SUB_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, convToString(cpoDtl.get("CPO_ORD_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, convToString(cpoDtl.get("CPO_DTL_LINE_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, convToString(cpoDtl.get("CPO_DTL_LINE_SUB_NUM")));

                    mdseBean = hdrBean.getImptLineMdseBean(tMsg.cpoDtlLineNum.getValue(), tMsg.cpoDtlLineSubNum.getValue());
                    mdseBean.setEdiPoAckDtlPk(ediPoAckDtlPk);
                    if (mdseBean.isSpuersession()) {
                        ediAckStsCd = EDI_ACK_STS.ITEM_SUPERCEDED;
                    } else {
                        ediAckStsCd = EDI_ACK_STS.ITEM_ACCEPTED;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.ediAckStsCd, ediAckStsCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordLineStsCd, convToString(cpoDtl.get("ORD_LINE_STS_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, convToBigDecimal(cpoDtl.get("ORD_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, convToBigDecimal(cpoDtl.get("ORD_CUST_UOM_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entDealNetUnitPrcAmt, convToBigDecimal(cpoDtl.get("ENT_DEAL_NET_UNIT_PRC_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealNetAmt, convToBigDecimal(cpoDtl.get("ENT_CPO_DTL_DEAL_NET_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealSlsAmt, convToBigDecimal(cpoDtl.get("ENT_CPO_DTL_DEAL_SLS_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.unitNetWt, convToBigDecimal(cpoDtl.get("UNIT_NET_WT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, convToString(cpoDtl.get("CCY_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, convToString(cpoDtl.get("RTL_WH_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, convToString(cpoDtl.get("RTL_SWH_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, convToString(cpoDtl.get("CUST_UOM_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, convToString(cpoDtl.get("MDSE_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, convToString(cpoDtl.get("CUST_MDSE_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.exchRate, convToBigDecimal(cpoDtl.get("EXCH_RATE")));

                    S21ApiTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {
            writeEndLogLn("insertEdiPoAckDtl", hdrBean);
        }
    }

    private boolean insertEdiPoAckShpgPln(ImptHdrBean hdrBean) {

        writeStartLogLn("insertEdiPoAckShpgPln", hdrBean);

        try {
            boolean isSuccess = true;

            if (!hdrBean.hasError()) {

                BigDecimal ediPoAckShpgPlnPk;
                EDI_PO_ACK_SHPG_PLNTMsg tMsg;
                ExpendMdseBean mdseBean;
                String newOrdNum = hdrBean.getNewOrdNum();
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                String trxLineNum, trxLineSubNum;

                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("trxHdrNum", newOrdNum);

                List<Map<String, Object>> shpgPlnList = NWZC226001Query.getInstance().queryMapList("getSpngPlnForEdiAck", ssmParam);

                if (!hasValueList(shpgPlnList)) {

                    return true;
                }
                Map<String, Object> shpgPln;
                for (int i = 0; i < shpgPlnList.size(); i++) {

                    shpgPln = shpgPlnList.get(i);
                    trxLineNum = convToString(shpgPln.get("TRX_LINE_NUM"));
                    trxLineSubNum = convToString(shpgPln.get("TRX_LINE_SUB_NUM"));
                    mdseBean = hdrBean.getImptLineMdseBean(trxLineNum, trxLineSubNum);

                    tMsg = new EDI_PO_ACK_SHPG_PLNTMsg();
                    ediPoAckShpgPlnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_SHPG_PLN_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckShpgPlnPk, ediPoAckShpgPlnPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, mdseBean.getEdiPoAckDtlPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, convToString(shpgPln.get("SHPG_PLN_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, convToString(shpgPln.get("TRX_HDR_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.trxLineNum, trxLineNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.trxLineSubNum, trxLineSubNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.shpgStsCd, convToString(shpgPln.get("SHPG_STS_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.spDealUnitPrcAmt, convToBigDecimal(shpgPln.get("SP_DEAL_UNIT_PRC_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, convToBigDecimal(shpgPln.get("ORD_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rddDt, convToString(shpgPln.get("RDD_DT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.pddDt, convToString(shpgPln.get("PDD_DT")));

                    S21ApiTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, mdseBean.getDsImptLineBean(), true)) {

                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {

            writeEndLogLn("insertEdiPoAckShpgPln", hdrBean);
        }
    }

    private void updateLtstCratFlg(ImptHdrBean hdrBean) {
        writeStartLogLn("updateLtstCratFlg", hdrBean);

        try {
            EDI_PO_ACK_HDRTMsg hdrTMsg;
            BigDecimal ediPoAckHdrPk;
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("ordSrcRefNum", hdrBean.getOrdSrcRefNum());
            List<Map<String, Object>> shpgPlnList = NWZC226001Query.getInstance().queryMapList("getEdiPoAckHdrForEdiAck", ssmParam);

            if (!hasValueList(shpgPlnList)) {

                return;
            }

            for (int i = 0; i < shpgPlnList.size(); i++) {
                ediPoAckHdrPk = (BigDecimal) shpgPlnList.get(i);

                hdrTMsg = new EDI_PO_ACK_HDRTMsg();

                ZYPEZDItemValueSetter.setValue(hdrTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(hdrTMsg.ediPoAckHdrPk, ediPoAckHdrPk);

                hdrTMsg = (EDI_PO_ACK_HDRTMsg) S21ApiTBLAccessor.findByKey(hdrTMsg);
                checkTMsgDbAccess(hdrTMsg);

                if (hdrTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(hdrTMsg.ltstCratFlg, ZYPConstant.FLG_OFF_N);
                    EZDTBLAccessor.updateSelectionField(hdrTMsg, new String[] {"ltstCratFlg" });
                    addUpdateErrorMsgList(hdrTMsg, hdrBean, true);
                }
            }
        } finally {
            writeEndLogLn("updateLtstCratFlg", hdrBean);
        }
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private static void writeStartLogLn(String methodNm) {

        writeLogLn("[START] %s", methodNm);
    }

    private static void writeStartLogLn(String methodNm, Object target) {

        writeLogLn("[START] %s(%s)", methodNm, getTargetKey(target));
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private static void writeEndLogLn(String methodNm) {

        writeLogLn("[END] %s\r\n", methodNm);
    }

    private static void writeEndLogLn(String methodNm, Object target) {

        writeLogLn("[END] %s(%s)\r\n", methodNm, getTargetKey(target));
    }

    private static void writeErrLog(ImptHdrBean hdrBean) {

        if (hdrBean != null) {

            // *****************************************************************
            // Error
            // *****************************************************************
            List<DsImptOrdErrBean> allErrorList = hdrBean.getAllErrorBean();
            for (DsImptOrdErrBean errBean : allErrorList) {

                writeErrLog(errBean);
            }
        }
    }

    private static String getTargetKey(Object target) {

        StringBuilder sb = new StringBuilder();

        if (target instanceof ImptHdrBean) {

            sb.append(String.format("DsImptOrdPk:%.0f HasError:%b", ((ImptHdrBean) target).getDsImptOrdPk(), ((ImptHdrBean) target).hasError()));
        } else if (target instanceof DsImptOrdConfigBean) {

            DsImptOrdConfigBean configBean = (DsImptOrdConfigBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdConfigPk:%.0f HasError:%b", configBean.imptHdrBean.getDsImptOrdPk(), configBean.dsImptOrdConfigPk.getValue(), configBean.hasError(true)));
        } else if (target instanceof DsImptLineBean) {

            DsImptLineBean lineBean = (DsImptLineBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdDtlPk:%.0f HasError:%b", lineBean.imptHdrBean.getDsImptOrdPk(), lineBean.dsImptOrdDtlPk.getValue(), lineBean.hasError(true)));
        } else if (target instanceof DsImptRtnLineBean) {

            DsImptRtnLineBean rtnBean = (DsImptRtnLineBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdRtrnDtlPk:%.0f HasError:%b", rtnBean.imptHdrBean.getDsImptOrdPk(), rtnBean.dsImptOrdRtrnDtlPk.getValue(), rtnBean.hasError(true)));
        } else if (target instanceof ExpendMdseBean) {

            ExpendMdseBean mdseBean = (ExpendMdseBean) target;
            sb.append(String.format("DsImptOrdDtlPk:%.0f - MdseCd:%s", mdseBean.getDsImptLineBean().dsImptOrdDtlPk.getValue(), mdseBean.getChildMdseCd()));
        } else if (target instanceof DsImptSvcDtlBean) {

            DsImptSvcDtlBean imptSvcDtlBean = (DsImptSvcDtlBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - dsImptSvcDtlPk:%.0f", imptSvcDtlBean.imptHdrBean.getDsImptOrdPk(), imptSvcDtlBean.dsImptSvcDtlPk.getValue()));
        } else if (target == null) {

            sb.append("Target:null");
        } else {

            sb.append(String.format("Target:%s", target.toString()));
        }

        return sb.toString();
    }

    /**
     * writeErrIdLog
     * @param msgId
     */
    @SuppressWarnings("unused")
    private static void writeErrLog(String msgId, String[] list) {

        writeLogLn(S21MessageFunc.clspGetMessage(msgId, list));
    }

    private static void writeErrLog(DsImptOrdErrBean errBean) {

        writeLogLn(errBean.toString());
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", PROGRAM_ID));

        if (param.length > 0) {

            sb.append(String.format(format, param));
        } else {

            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    private String getMaxCpoDtlLineNum(ImptHdrBean hdrBean) {
        String result = "000";
        if (ZYPCommonFunc.hasValue(hdrBean.getOrigOrdNum())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("cpoOrdNum", hdrBean.getOrigOrdNum());

            return NWZC226001Query.getInstance().queryString("getMaxCpoDtlLineNum", ssmParam);
        }
        return result;
    }

    private int getMaxDsCpoLineNum(ImptHdrBean hdrBean, String dsOrdPosnNum) {
        BigDecimal result = new BigDecimal(0);
        if (ZYPCommonFunc.hasValue(hdrBean.getOrigOrdNum())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("cpoOrdNum", hdrBean.getOrigOrdNum());
            ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);

            result = NWZC226001Query.getInstance().queryBigDecimal("getMaxDsCpoLineNum", ssmParam);
        }
        return result.intValue();
    }

    private String getMaxDsCpoRtrnLineNum(ImptHdrBean hdrBean) {
        String result = "000";
        if (ZYPCommonFunc.hasValue(hdrBean.getOrigOrdNum())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("cpoOrdNum", hdrBean.getOrigOrdNum());

            return NWZC226001Query.getInstance().queryString("getMaxDsCpoRtrnLineNum", ssmParam);
        }
        return result;
    }

    private String getMaxDsOrdPosnNum(ImptHdrBean hdrBean, String configCatgCd) {
        String result = "0";
        if (ZYPCommonFunc.hasValue(hdrBean.getOrigOrdNum())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("cpoOrdNum", hdrBean.getOrigOrdNum());
            ssmParam.put("configCatgCd", configCatgCd);

            return NWZC226001Query.getInstance().queryString("getMaxDsOrdPosnNum", ssmParam);
        }
        return result;
    }

    private String getNextDsOrdPosnNum(String dsOrdPosnNum) {
        String result = "1";
        if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            int currentNum = Integer.valueOf(dsOrdPosnNum).intValue();
            return Integer.toString(currentNum + 1);
        }
        return result;
    }

    private boolean existsSlsCr(ImptHdrBean hdrBean, String dsOrdPosnNum) {
        DS_CPO_SLS_CRTMsg tMsg = new DS_CPO_SLS_CRTMsg();
        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            tMsg.setSQLID("001");
        } else {
            tMsg.setSQLID("003");
            tMsg.setConditionValue("dsOrdPosnNum01", dsOrdPosnNum);
        }

        tMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
        tMsg.setConditionValue("cpoOrdNum01", hdrBean.getOrigOrdNum());

        DS_CPO_SLS_CRTMsgArray tmsgArray = (DS_CPO_SLS_CRTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);

        return (hasValidValue(tmsgArray));
    }

    private DS_CPO_SLS_CRTMsgArray getOrigSlsCrList(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getOrigSlsCrList", ssmParam);
        List<DS_CPO_SLS_CRTMsg> dsCpoSlsCrTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>(0);
        for (Map<String, Object> rslt : rsltList) {
            DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
            dsCpoSlsCrTMsg.glblCmpyCd.setValue(glblCmpyCd);
            dsCpoSlsCrTMsg.cpoOrdNum.setValue(cpoOrdNum);
            dsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(convToBigDecimal(rslt.get("DS_CPO_SLS_CR_PK")));
            dsCpoSlsCrTMsg.slsCrQuotFlg.setValue(convToString(rslt.get("SLS_CR_QUOT_FLG")));

            dsCpoSlsCrTMsg.dsOrdPosnNum.setValue(convToString(rslt.get("DS_ORD_POSN_NUM"), ""));
            dsCpoSlsCrTMsg.slsRepTocCd.setValue(convToString(rslt.get("SLS_REP_TOC_CD"), ""));
            dsCpoSlsCrTMsg.slsRepRoleTpCd.setValue(convToString(rslt.get("SLS_REP_ROLE_TP_CD"), ""));
            dsCpoSlsCrTMsg.coaExtnCd.setValue(convToString(rslt.get("COA_EXTN_CD"), ""));
            dsCpoSlsCrTMsg.coaBrCd.setValue(convToString(rslt.get("COA_BR_CD"), ""));
            dsCpoSlsCrTMsg.coaCcCd.setValue(convToString(rslt.get("COA_CC_CD"), ""));

            dsCpoSlsCrTMsg.dsCpoConfigPk.setValue(convToBigDecimal(rslt.get("DS_CPO_CONFIG_PK")));
            dsCpoSlsCrTMsg.slsRepCrPct.setValue(convToBigDecimal(rslt.get("SLS_REP_CR_PCT")));

            dsCpoSlsCrTMsgList.add(dsCpoSlsCrTMsg);
        }
        DS_CPO_SLS_CRTMsgArray dsCpoSlsCrTMsgArray = new DS_CPO_SLS_CRTMsgArray();
        dsCpoSlsCrTMsgArray.setMsgList(dsCpoSlsCrTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
        dsCpoSlsCrTMsgArray.setValidCount(dsCpoSlsCrTMsgList.size());

        return dsCpoSlsCrTMsgArray;
    }

    private boolean loanInputCheck(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {

        for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

            if (!CONFIG_TP.TO_SALES_CONVERSION.equals(configBean.configTpCd.getValue())) {
                continue;
            }

            BigDecimal svcConfigMstrPk = configBean.svcConfigMstrPk.getValue();

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("origCpoOrdNum", hdrBean.getLoanOrigOrdNum());

            List<String> ordLineStsNmList = new ArrayList<String>();
            ordLineStsNmList.add(ORD_LINE_STS_NM_ON_LOAN);
            ordLineStsNmList.add(ORD_LINE_STS_NM_CLOSED);
            ordLineStsNmList.add(ORD_LINE_STS_NM_PENDING_FULFILLMENT); // 2017/09/08 S21_NA#19800 add
            ssmParam.put("ordLineStsNmList", ordLineStsNmList);

            ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
            ssmParam.put("isSearchCondOnLoanFlg", ZYPConstant.FLG_OFF_N);

            List<String> ordHdrStsCdList = new ArrayList<String>();
            ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
            ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
            ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

            List<String> ordLineStsCdList = new ArrayList<String>();
            ordLineStsCdList.add(ORD_LINE_STS.CLOSED);
            ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("ordLineStsCdList", ordLineStsCdList);

            List<String> rtrnLineStsCdList = new ArrayList<String>();
            rtrnLineStsCdList.add(ORD_LINE_STS.CLOSED);
            rtrnLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

            List<Map<String, Object>> convNotOnLoanItemMapList = NWZC226001Query.getInstance().queryMapList("getConversableItems", ssmParam);

            int holdCount = 0;
            if (convNotOnLoanItemMapList.size() > 0) {
                for (Map<String, Object> itemMap : convNotOnLoanItemMapList) {
                    String stsNm = (String) itemMap.get("ORD_LINE_STS_NM");
                    if (LINE_STS_BILLING_HOLD.equals(stsNm)) {
                        holdCount++;
                    }
                }

                if (convNotOnLoanItemMapList.size() > holdCount) {
                    DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM2219E);
                    configBean.dsImptOrdErrList.add(errBean);
                    return false;
                }
            }

            // 2017/09/08 S21_NA#19800 del start
//            ordLineStsNmList = new ArrayList<String>();
//            ordLineStsNmList.add(ORD_LINE_STS_NM_ON_LOAN);
//            ssmParam.put("ordLineStsNmList", ordLineStsNmList);
            // 2017/09/08 S21_NA#19800 del end
            // 2017/09/08 S21_NA#19800 add start
            ordLineStsCdList = new ArrayList<String>();
            ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("ordLineStsCdList", ordLineStsCdList);
            // 2017/09/08 S21_NA#19800 add end

            ssmParam.put("isSearchCondOnLoanFlg", ZYPConstant.FLG_ON_Y);
            List<Map<String, Object>> convOnloanItemMapList = NWZC226001Query.getInstance().queryMapList("getConversableItems", ssmParam);

            List<String> loanMdseList = new ArrayList<String>();
            for (Map<String, Object> itemMap : convOnloanItemMapList) {
                String loanMdseCd = (String) itemMap.get("MDSE_CD");
                if (loanMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                    loanMdseCd = loanMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                }
                loanMdseList.add(loanMdseCd);
            }

            if (loanMdseList == null || loanMdseList.size() == 0) {
                DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM2215E);
                configBean.dsImptOrdErrList.add(errBean);
                return false;
            }

            for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {

                if (lineBean.isOrigCpoDtl) {
                    continue;
                }

                boolean existFlg = false;

                // Add Start 2018/08/21 QC#27489
                if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd()) && //
                        DS_ORD_LINE_CATG.MERCHANDISE.equals(lineBean.dsOrdLineCatgCd.getValue())) {
                    lineBean.isLoanConvAddFlg = true;
                } else {
                // Add End 2018/08/21 QC#27489

                for (int i = 0; i < lineBean.mdseAllList.size(); i++) {

                    ExpendMdseBean mdseBean = lineBean.mdseAllList.get(i);
                    String mdseCd = mdseBean.getMdseCd();
                    if (mdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                        mdseCd = mdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                    }

                    for (int j = 0; j < loanMdseList.size(); j++) {
                        String loanMdseCd = loanMdseList.get(j);

                        if (mdseCd.equals(loanMdseCd)) {
                            loanMdseList.remove(j);
                            if (i == 0) {
                                existFlg = true;
                            }
                            break;
                        }
                    }
                }

                if (!existFlg) {
                    lineBean.isLoanConvAddFlg = true;
                }

                // Add Start 2018/08/21 QC#27489
                }
                // Add End 2018/08/21 QC#27489
            }

            // Del Start 2018/08/21 QC#27489
            //if (loanMdseList.size() > 0) {
            //    // QC#22490 2017/11/14 Add Start
            //    // DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM2213E);
            //    for (String m : loanMdseList) {
            //        DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM2213E, new String[] {m });
            //        configBean.dsImptOrdErrList.add(errBean);
            //    }
            //    // QC#22490 2017/11/14 Add End
            //    return false;
            //}
            // Del End 2018/08/21 QC#27489
        }

        return true;
    }

    private boolean loanInputCheckForDowngrade(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {

        for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

            if (!hdrBean.isLoanDowngrade()) {
                return true;
            }

            if (CONFIG_CATG.OUTBOUND.equals(configBean.configCatgCd.getValue()) || configBean.isOrigConfig) {
                continue;
            }

            BigDecimal svcConfigMstrPk = configBean.svcConfigMstrPk.getValue();

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            ssmParam.put("origCpoOrdNum", hdrBean.getLoanOrigOrdNum());

            List<String> ordLineStsNmList = new ArrayList<String>();
            ordLineStsNmList.add(ORD_LINE_STS_NM_ON_LOAN);
            ordLineStsNmList.add(ORD_LINE_STS_NM_CLOSED); // 2017/09/08 S21_NA#19800 add
            ordLineStsNmList.add(ORD_LINE_STS_NM_PENDING_FULFILLMENT); // 2017/09/08 S21_NA#19800 add
            ssmParam.put("ordLineStsNmList", ordLineStsNmList);

            ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
            ssmParam.put("isSearchCondOnLoanFlg", ZYPConstant.FLG_ON_Y);

            List<String> ordHdrStsCdList = new ArrayList<String>();
            ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
            ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
            ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

            List<String> ordLineStsCdList = new ArrayList<String>();
            // ordLineStsCdList.add(ORD_LINE_STS.CLOSED); // 2017/09/08 S21_NA#19800 del
            ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("ordLineStsCdList", ordLineStsCdList);

            List<String> rtrnLineStsCdList = new ArrayList<String>();
            rtrnLineStsCdList.add(ORD_LINE_STS.CLOSED);
            rtrnLineStsCdList.add(ORD_LINE_STS.CANCELLED);
            ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

            List<Map<String, Object>> convItemMapList = NWZC226001Query.getInstance().queryMapList("getConversableItems", ssmParam);

            List<String> loanMdseList = new ArrayList<String>();
            for (Map<String, Object> itemMap : convItemMapList) {
                String loanMdseCd = (String) itemMap.get("MDSE_CD");
                if (loanMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                    loanMdseCd = loanMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                }
                loanMdseList.add(loanMdseCd);
            }

            if (loanMdseList == null || loanMdseList.size() == 0) {
                setMsgIdWithParam(msgMap, null, MSG_ID.NWZM2215E);
                hdrBean.dsImptOrdErrList.add(new DsImptOrdErrBean(configBean, MSG_ID.NWZM2215E));
                return false;
            }

            for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                if (rtnBean.isOrigRtnDtl) {
                    continue;
                }

                String mdseCd = rtnBean.mdseCd.getValue();
                if (mdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                    mdseCd = mdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                }

                boolean existFlg = false;
                for (int i = 0; i < loanMdseList.size(); i++) {
                    String loanMdseCd = loanMdseList.get(i);

                    if (mdseCd.equals(loanMdseCd)) {
                        loanMdseList.remove(i);
                        existFlg = true;
                        break;
                    }
                }

                if (!existFlg) {
                    hdrBean.dsImptOrdErrList.add(new DsImptOrdErrBean(rtnBean, MSG_ID.NWZM2214E));
                    return false;
                }
            }

            // Check Base Component exists or not
            if (loanMdseList.size() > 0) {

                // Find Base Component
                String baseCompMdseCd = null;
                for (DsImptRtnLineBean rtnBean : configBean.dsImptRtnLineList) {

                    if (rtnBean.isOrigRtnDtl) {
                        continue;
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(rtnBean.baseCmptFlg.getValue())) {
                        baseCompMdseCd = rtnBean.mdseCd.getValue();
                        break;
                    }
                }

                // Find Base Component from Mdse List
                if (ZYPCommonFunc.hasValue(baseCompMdseCd)) {
                    if (!loanMdseList.contains(baseCompMdseCd)) {
                        // Base Component does not exist
                        hdrBean.dsImptOrdErrList.add(new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2209E));
                        return false;
                    }
                }
            }

            // Check Not On Loan Items exist or not
            if (loanMdseList.size() == 0) {
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("origCpoOrdNum", hdrBean.getLoanOrigOrdNum());

                ordLineStsNmList = new ArrayList<String>();
                ordLineStsNmList.add(ORD_LINE_STS_NM_ON_LOAN);
                ordLineStsNmList.add(ORD_LINE_STS_NM_CLOSED);
                ordLineStsNmList.add(ORD_LINE_STS_NM_PENDING_FULFILLMENT); // 2017/09/08 S21_NA#19800 add
                ssmParam.put("ordLineStsNmList", ordLineStsNmList);

                ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
                ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);
                ssmParam.put("ordLineStsCdList", ordLineStsCdList);
                ssmParam.put("isSearchCondOnLoanFlg", ZYPConstant.FLG_OFF_N);

                rtrnLineStsCdList = new ArrayList<String>();
                rtrnLineStsCdList.add(ORD_LINE_STS.CLOSED);
                rtrnLineStsCdList.add(ORD_LINE_STS.CANCELLED);
                ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

                List<Map<String, Object>> unConvItemMapList = NWZC226001Query.getInstance().queryMapList("getConversableItems", ssmParam);
                if (unConvItemMapList != null && unConvItemMapList.size() > 0) {
                    int holdCount = 0;
                    for (Map<String, Object> itemMap : unConvItemMapList) {
                        String stsNm = (String) itemMap.get("ORD_LINE_STS_NM");
                        if (LINE_STS_BILLING_HOLD.equals(stsNm)) {
                            holdCount++;
                        }
                    }

                    if (unConvItemMapList.size() > holdCount) {
                        hdrBean.dsImptOrdErrList.add(new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2209E));
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean setOrigLineNum(ImptHdrBean hdrBean) {

        for (DsImptOrdConfigBean configBean : hdrBean.dsImptOrdConfigMap.values()) {

            SVC_MACH_MSTRTMsg condition = new SVC_MACH_MSTRTMsg();
            condition.setSQLID("019");
            condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
            condition.setConditionValue("svcConfigMstrPk01", configBean.svcConfigMstrPk.getValue());
            condition.setConditionValue("cpoOrdNum01", hdrBean.getLoanOrigOrdNum());

            SVC_MACH_MSTRTMsgArray tmsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
            if (!hasValidValue(tmsgArray)) {
                return false;
            }

            if (hdrBean.isLoanToSales()) {
                for (DsImptLineBean lineBean : configBean.dsImptOrdLineList) {
                    
                    // 2018/08/29 S21_NA#27489 Add Start
                    if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd()) && //
                            DS_ORD_LINE_CATG.MERCHANDISE.equals(lineBean.dsOrdLineCatgCd.getValue())) {
                        continue;
                    }
                    // 2018/08/29 S21_NA#27489 Add End

                    for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {

                        String mdseCd = mdseBean.getMdseCd();
                        String serNum = mdseBean.getSerNum();

                        for (int i = 0; i < tmsgArray.length(); i++) {

                            SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) tmsgArray.get(i);
                            String svcMdseCd = svcMachMstrTMsg.mdseCd.getValue();

                            if (mdseCd.length() == MDSE_CD_SHORT_LENGTH && svcMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                                svcMdseCd = svcMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                            }

                            if (mdseCd.equals(svcMdseCd)) {
                                if (!ZYPCommonFunc.hasValue(serNum) || ZYPCommonFunc.hasValue(serNum) && serNum.equals(svcMachMstrTMsg.serNum.getValue())) {
                                    mdseBean.setOrigCpoDtlLineNum(svcMachMstrTMsg.cpoDtlLineNum.getValue());
                                    mdseBean.setOrigCpoDtlLineSubNum(svcMachMstrTMsg.cpoDtlLineSubNum.getValue());
                                    tmsgArray.get(i).clear();
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (hdrBean.isLoanDowngrade()) {
                for (DsImptRtnLineBean rtnLineBean : configBean.dsImptRtnLineList) {

                    String mdseCd = rtnLineBean.mdseCd.getValue();
                    String serNum = rtnLineBean.serNum.getValue();

                    for (int i = 0; i < tmsgArray.length(); i++) {

                        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) tmsgArray.get(i);
                        String svcMdseCd = svcMachMstrTMsg.mdseCd.getValue();

                        if (mdseCd.length() == MDSE_CD_SHORT_LENGTH && svcMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                            svcMdseCd = svcMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                        }

                        if (mdseCd.equals(svcMdseCd)) {
                            if (!ZYPCommonFunc.hasValue(serNum) || ZYPCommonFunc.hasValue(serNum) && serNum.equals(svcMachMstrTMsg.serNum.getValue())) {
                                rtnLineBean.origCpoDtlLineNum = svcMachMstrTMsg.cpoDtlLineNum.getValue();
                                rtnLineBean.origCpoDtlLineSubNum = svcMachMstrTMsg.cpoDtlLineSubNum.getValue();
                                tmsgArray.get(i).clear();
                                break;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private EZDTStringItem getPosnNumBySvcConfigMstrPk(ImptHdrBean hdrBean, DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg) {
        DS_CPO_CONFIGTMsg condition = new DS_CPO_CONFIGTMsg();
        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
        condition.setConditionValue("cpoOrdNum01", hdrBean.getOrigOrdNum());
        condition.setConditionValue("svcConfigMstrPk01", dsImptOrdDtlTMsg.svcConfigMstrPk.getValue());
        condition.setConditionValue("configCatgCd01", CONFIG_CATG.OUTBOUND);

        DS_CPO_CONFIGTMsgArray tmsgArray = (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (hasValidValue(tmsgArray)) {

            return tmsgArray.no(0).dsOrdPosnNum;
        }
        return null;
    }

    private void setInstalledMdseCd(NWZC150001_rtnDtlPMsg rtnPMsg, SVC_MACH_MSTRTMsgArray tmsgArray) {
        if (!ZYPCommonFunc.hasValue(rtnPMsg.mdseCd_B1) || !hasValidValue(tmsgArray)) {
            return;
        }

        String imptMdse = rtnPMsg.mdseCd_B1.getValue();
        String imptSerNum = "";
        if (ZYPCommonFunc.hasValue(rtnPMsg.serNum_B1)) {
            imptSerNum = rtnPMsg.serNum_B1.getValue();
        }

        for (int i = 0; i < tmsgArray.getValidCount(); i++) {
            SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) tmsgArray.get(i);
            String svcMdse = tMsg.mdseCd.getValue();
            String svcSerNum = "";
            if (ZYPCommonFunc.hasValue(tMsg.serNum)) {
                svcSerNum = tMsg.serNum.getValue();
            }

            // (1) MDSE + SerNum vs MDSE + SerNum
            if (imptMdse.equals(svcMdse) && imptSerNum.equals(svcSerNum)) {
                tmsgArray.get(i).clear();
                rtnPMsg.mdseCd_B1.setValue(svcMdse);
                break;

                // (2) MDSE vs MDSE
            } else if (!ZYPCommonFunc.hasValue(imptSerNum) && imptMdse.equals(svcMdse)) {
                tmsgArray.get(i).clear();
                rtnPMsg.mdseCd_B1.setValue(svcMdse);
                break;
            }

            String imptMdse8 = imptMdse;
            String svcMdse8 = svcMdse;

            if (imptMdse.length() > MDSE_CD_SHORT_LENGTH) {
                imptMdse8 = imptMdse.substring(0, MDSE_CD_SHORT_LENGTH);
            }

            if (svcMdse.length() > MDSE_CD_SHORT_LENGTH) {
                svcMdse8 = svcMdse.substring(0, MDSE_CD_SHORT_LENGTH);
            }

            // (3) MDSE(8) + SerNum vs MDSE(8) + SerNum
            if (imptMdse8.equals(svcMdse8) && imptSerNum.equals(svcSerNum)) {
                tmsgArray.get(i).clear();
                rtnPMsg.mdseCd_B1.setValue(svcMdse);
                break;

                // (4) MDSE(8) vs MDSE(8)
            } else if (!ZYPCommonFunc.hasValue(imptSerNum) && imptMdse8.equals(svcMdse8)) {
                tmsgArray.get(i).clear();
                rtnPMsg.mdseCd_B1.setValue(svcMdse);
                break;
            }
        }
    }

    private void updateMdseCdForRtn(ImptHdrBean hdrBean, DsImptOrdConfigBean configBean, NWZC150001PMsg cpoUpdApiPMsg) {
        // 2017/07/10 S21_NA#19789 reverse to before QC#19440
        if (!hasValidValue(cpoUpdApiPMsg.rtnDtl) || !ZYPCommonFunc.hasValue(hdrBean.getGlblCmpyCd()) || !ZYPCommonFunc.hasValue(configBean.svcConfigMstrPk) || !ZYPCommonFunc.hasValue(hdrBean.getLoanOrigOrdNum())) {

            return;
        }

        SVC_MACH_MSTRTMsg condition = new SVC_MACH_MSTRTMsg();
        condition.setSQLID("019");
        condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
        condition.setConditionValue("svcConfigMstrPk01", configBean.svcConfigMstrPk.getValue());
        condition.setConditionValue("cpoOrdNum01", hdrBean.getLoanOrigOrdNum());
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(condition);

        if (!hasValidValue(svcMachMstrTMsgArray)) {
            return;
        }

        for (int i = 0; i < cpoUpdApiPMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnPMsg = cpoUpdApiPMsg.rtnDtl.no(i);
            if (ZYPCommonFunc.hasValue(rtnPMsg.svcConfigMstrPk_B1) && rtnPMsg.svcConfigMstrPk_B1.getValue().compareTo(configBean.svcConfigMstrPk.getValue()) != 0) {
                continue;
            }

            if (!NWZC150001Constant.RQST_TP_DTL_NEW.equals(rtnPMsg.xxRqstTpCd_B1.getValue())) {
                continue;
            }

            setInstalledMdseCd(rtnPMsg, svcMachMstrTMsgArray);
        }
    }

    // 2017/03/14 S21_NA#16855 Add Start
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg
     * </pre>
     */
    private static String[] getTrtyGrpTpCdFromDsOrdTpCd(ImptHdrBean hdrBean) {

        if (!ZYPCommonFunc.hasValue(hdrBean.getDsOrdTpCd())) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21ApiTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // 2017/12/14 S21_NA#19804(Sol349) Mod Start
            if (ZYPCommonFunc.hasValue(dsOrdTpProcDfnTMsg.trtyGrpTpTxt)){
                return  dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue().split(",");
            }
            // 2017/12/14 S21_NA#19804(Sol349) Mod End
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End

    private boolean setContrImptApiSvcDtlListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg, Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap) {

        writeStartLogLn("setContrImptApiSvcDtlListPMsg", svcDtlBeanList);

        try {
            int cnt = 0;

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                // *************************************************************
                // CPO Service Detail
                // *************************************************************
                NSZC115001_svcDtlListPMsg apiMsg = pMsg.svcDtlList.no(cnt);

                ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.svcPgmMdseCd, svcDtlBean.dsImptSvcMdseCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcContrTpCd, svcDtlBean.prcSvcContrTpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcPlnTpCd, svcDtlBean.prcSvcPlnTpCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.dsContrCatgCd, svcDtlBean.dsContrCatgCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.baseBllgCycleCd, svcDtlBean.baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.usgBllgCycleCd, svcDtlBean.usgBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.fromPerMthNum, svcDtlBean.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.toPerMthNum, svcDtlBean.toPerMthNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.billWithEquipFlg, svcDtlBean.billWithEquipFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.billByTpCd, svcDtlBean.billByTpCd);
                if (ZYPCommonFunc.hasValue(svcDtlBean.soldToCustLocCd)) {

                    ZYPEZDItemValueSetter.setValue(apiMsg.soldToCustLocCd, svcDtlBean.soldToCustLocCd);
                } else {

                    ZYPEZDItemValueSetter.setValue(apiMsg.soldToCustLocCd, hdrBean.getSoldToCustLocCd());
                }
                if (ZYPCommonFunc.hasValue(svcDtlBean.sellToCustCd)) {

                    ZYPEZDItemValueSetter.setValue(apiMsg.sellToCustCd, svcDtlBean.sellToCustCd);
                } else {

                    ZYPEZDItemValueSetter.setValue(apiMsg.sellToCustCd, hdrBean.getSellToCustCd());
                }
                ZYPEZDItemValueSetter.setValue(apiMsg.cpoSvcAgmtVerNum, svcDtlBean.svcAgmtVrsnNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.manContrOvrdFlg, svcDtlBean.manContrOvrdFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.manContrOvrdRsnCd, svcDtlBean.manContrOvrdRsnCd);
                ZYPEZDItemValueSetter.setValue(apiMsg.manContrOvrdCmntTxt, svcDtlBean.manContrOvrdCmntTxt);

                // QC#25030
//                ZYPEZDItemValueSetter.setValue(apiMsg.fixTermInMthAot, svcDtlBean.fixTermInMthAot);
                // Mod Start 2017/09/28 QC#20689
                if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd())) {
                    for (DS_IMPT_SVC_CONFIG_REFTMsg svcConfigRefTMsg : svcDtlBean.svcConfigRefMsgList) {

                        DsImptOrdConfigBean configBean = isAddAccessory(hdrBean, svcConfigRefTMsg);
                        if (null != configBean) {

                            BigDecimal svcConfigPk = getConfigPk(addAccPkMap, svcConfigRefTMsg.dsImptOrdDtlPk.getValue());
                            if (BigDecimal.ZERO.compareTo(svcConfigPk) < 0) {

                                // QC#29282 2018/11/29 Add Start
                                //Map<String, Object> ssmParam = new HashMap<String, Object>();
                                //ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                                //ssmParam.put("svcConfigMstrPk", svcConfigPk);
                                //List<BigDecimal> dsContrPkList = NWZC226001Query.getInstance().queryBigDecimalList("getDsContrPk", ssmParam);
                                //
                                //if (dsContrPkList.size() == 0) {
                                //    ssmParam = new HashMap<String, Object>();
                                //    ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                                //    ssmParam.put("dsImptOrdDtlPk", svcConfigRefTMsg.dsImptOrdDtlPk.getValue());
                                //    Map<String, Object> result = NWZC226001Query.getInstance().queryMap("getConfigLineInfo", ssmParam);
                                //
                                //    if (configBean.dsImptOrdConfigPk.getValue().equals((BigDecimal) result.get("DS_IMPT_ORD_CONFIG_PK"))) {
                                //        DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM1998E);
                                //        configBean.getDsImptOrdErrList().add(errBean);
                                //    }
                                //
                                //    return false;
                                //} else {
                                //    ZYPEZDItemValueSetter.setValue(apiMsg.dsContrPk, dsContrPkList.get(0));
                                //}

                                DS_CONTRTMsg csdTMsg = new DS_CONTRTMsg();
                                ZYPEZDItemValueSetter.setValue(csdTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                                ZYPEZDItemValueSetter.setValue(csdTMsg.dsContrPk, svcDtlBean.dsContrPk);
                                csdTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(csdTMsg);
                                if (csdTMsg == null) {
                                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                                    ssmParam = new HashMap<String, Object>();
                                    ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                                    ssmParam.put("dsImptOrdDtlPk", svcConfigRefTMsg.dsImptOrdDtlPk.getValue());
                                    Map<String, Object> result = NWZC226001Query.getInstance().queryMap("getConfigLineInfo", ssmParam);

                                    if (configBean.dsImptOrdConfigPk.getValue().equals((BigDecimal) result.get("DS_IMPT_ORD_CONFIG_PK"))) {
                                        DsImptOrdErrBean errBean = new DsImptOrdErrBean(configBean, MSG_ID.NWZM1998E);
                                        configBean.getDsImptOrdErrList().add(errBean);
                                    }

                                    return false;
                                } else {
                                    ZYPEZDItemValueSetter.setValue(apiMsg.dsContrPk, svcDtlBean.dsContrPk);
                                    // 2018/12/10 S21_NA#29484 Add Start
                                    // 2018/12/28 S21_NA#29772 Mod Start
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.svcPgmMdseCd, csdTMsg.svcPgmMdseCd);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcContrTpCd, csdTMsg.prcSvcContrTpCd);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcPlnTpCd, csdTMsg.prcSvcPlnTpCd);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.dsContrCatgCd, csdTMsg.dsContrCatgCd);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.baseBllgCycleCd, csdTMsg.baseBllgCycleCd);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.usgBllgCycleCd, csdTMsg.mtrBllgCycleCd);
                                    if (ZYPCommonFunc.hasValue(csdTMsg.svcPgmMdseCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.svcPgmMdseCd, csdTMsg.svcPgmMdseCd);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.prcSvcContrTpCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcContrTpCd, csdTMsg.prcSvcContrTpCd);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.prcSvcPlnTpCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcPlnTpCd, csdTMsg.prcSvcPlnTpCd);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.dsContrCatgCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.dsContrCatgCd, csdTMsg.dsContrCatgCd);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.baseBllgCycleCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.baseBllgCycleCd, csdTMsg.baseBllgCycleCd);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.mtrBllgCycleCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.usgBllgCycleCd, csdTMsg.mtrBllgCycleCd);
                                    }
                                    // 2018/12/28 S21_NA#29772 Mod End
                                    if (ZYPCommonFunc.hasValue(csdTMsg.fromPerMthNum) && ZYPCommonFunc.hasValue(csdTMsg.toPerMthNum)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.fromPerMthNum, csdTMsg.fromPerMthNum);
                                        ZYPEZDItemValueSetter.setValue(apiMsg.toPerMthNum, csdTMsg.toPerMthNum);
                                    }
                                    // 2018/12/28 S21_NA#29772 Mod Start
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.billWithEquipFlg, csdTMsg.billWithEquipFlg);
                                    // ZYPEZDItemValueSetter.setValue(apiMsg.billByTpCd, csdTMsg.billByTpCd);
                                    if (ZYPCommonFunc.hasValue(csdTMsg.billWithEquipFlg)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.billWithEquipFlg, csdTMsg.billWithEquipFlg);
                                    }
                                    if (ZYPCommonFunc.hasValue(csdTMsg.billByTpCd)) {
                                        ZYPEZDItemValueSetter.setValue(apiMsg.billByTpCd, csdTMsg.billByTpCd);
                                    }
                                    // 2018/12/28 S21_NA#29772 Mod End
                                    // 2018/12/10 S21_NA#29484 Add End
                                }
                                // QC#29282 2018/11/29 Add End
                                ZYPEZDItemValueSetter.setValue(apiMsg.addAsryFlg, ZYPConstant.FLG_ON_Y);
                            }
                        }
                    }
                }
                if (!ZYPCommonFunc.hasValue(apiMsg.dsContrPk)) {
                    ZYPEZDItemValueSetter.setValue(apiMsg.dsContrPk, svcDtlBean.dsContrPk);
                }
                // Mod End 2017/09/28 QC#20689
                // Add Start 2017/07/19 QC#19797
                if (ZYPCommonFunc.hasValue(apiMsg.dsContrPk)) {
                    ZYPEZDItemValueSetter.setValue(apiMsg.dsContrPk_AD, apiMsg.dsContrPk);
                }
                // Add End 2017/07/19 QC#19797
                ZYPEZDItemValueSetter.setValue(apiMsg.applyEquipBillToFlg, svcDtlBean.useEquipBillToFlg);
                ZYPEZDItemValueSetter.setValue(apiMsg.fixTermInMthAot, svcDtlBean.fixTermInMthAot);
                ZYPEZDItemValueSetter.setValue(apiMsg.maxUplftPct, svcDtlBean.maxUplftPct);

                cnt++;
            }
            pMsg.svcDtlList.setValidCount(cnt);

            return true;

        } finally {

            writeEndLogLn("setContrImptApiSvcDtlListPMsg", svcDtlBeanList);
        }
    }

    private Map<String, Map<BigDecimal, BigDecimal>> setContrImptApiSvcConfigRefListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg) {

        writeStartLogLn("setContrImptApiSvcConfigRefListPMsg");

        // Add Start 2017/09/28 QC#20689
        Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap = new HashMap<String, Map<BigDecimal,BigDecimal>>();
        // Add End 2017/09/28 QC#20689

        try {
            int cnt = 0;

            // *************************************************************
            // CPO Service Config Reference
            // *************************************************************
            NSZC115001_svcConfigRefListPMsg apiMsg;
            ExpendMdseBean lineBean;
            String dsOrdPosnNum = "";
            // Add Start 2017/09/28 QC#20689
            List<BigDecimal> ordDtlPkList = new ArrayList<BigDecimal>();
            List<BigDecimal> svcDtlPkList = new ArrayList<BigDecimal>();
            // Add End 2017/09/28 QC#20689

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                for (DS_IMPT_SVC_CONFIG_REFTMsg tMsg : svcDtlBean.svcConfigRefMsgList) {

                    apiMsg = pMsg.svcConfigRefList.no(cnt);
                    // Mod Start 2017/09/28 QC#20689
                    boolean addAccFlg = false;
                    lineBean = null;

                    if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(hdrBean.getCpoSrcTpCd())) {

                        BigDecimal checkOrdDtlPk = BigDecimal.ZERO;
                        // 2018/12/11 S21_NA#29484 Mod Start
                        DsImptOrdConfigBean configBean = isAddAccessory(hdrBean, tMsg);
                        if (null != configBean) {
                            for (DS_IMPT_SVC_ADDL_BASETMsg addlBaseTMsg : svcDtlBean.svcAddlBaseMsgList) {
                                for (DsImptLineBean ordLineBean : configBean.dsImptOrdLineList) {
                                    if (ordLineBean.dsImptOrdDtlPk.getValue().compareTo(addlBaseTMsg.dsImptOrdDtlPk.getValue()) == 0) {
                                        checkOrdDtlPk = addlBaseTMsg.dsImptOrdDtlPk.getValue();
                                        break;
                                    }
                                }
                                if (checkOrdDtlPk.compareTo(BigDecimal.ZERO) != 0) {
                                    break;
                                }
                            }
                        }
                        // for (DS_IMPT_SVC_ADDL_BASETMsg addlBaseTMsg : svcDtlBean.svcAddlBaseMsgList) {
                        //     if (tMsg.dsImptSvcDtlPk.getValue().equals(addlBaseTMsg.dsImptSvcDtlPk.getValue())) {
                        //         checkOrdDtlPk = addlBaseTMsg.dsImptOrdDtlPk.getValue();
                        //     }
                        // }

                        // DsImptOrdConfigBean configBean = isAddAccessory(hdrBean, tMsg);
                        // 2018/12/11 S21_NA#29484 Mod End
                        if (null != configBean) {

                            for (DsImptLineBean ordLineBean : configBean.dsImptOrdLineList) {

                                BigDecimal ordDtlPk = ordLineBean.dsImptOrdDtlPk.getValue();
                                BigDecimal svcDtlPk = tMsg.dsImptSvcDtlPk.getValue();
                                if (addAccFlg || (ordDtlPkList.contains(ordDtlPk) && svcDtlPkList.contains(svcDtlPk))) {
                                    continue;
                                }

                                if (checkOrdDtlPk.equals(ordDtlPk) //
                                        && ZYPConstant.FLG_ON_Y.equals(ordLineBean.imptLineFlg.getValue())) {

                                    // 2019/11/08 S21_NA#54208 Mod Start
//                                    dsOrdPosnNum = ordLineBean.dsOrdPosnNum.getValue();
                                    dsOrdPosnNum = configBean.dsOrdPosnNum.getValue();
                                    // 2019/11/08 S21_NA#54208 Mod End
                                    lineBean = hdrBean.getImptMdseByDtlPk(ordDtlPk);
                                    String setPkKey = setPkKey(svcDtlPk, ordDtlPk);
                                    Map<BigDecimal, BigDecimal> configMap = new HashMap<BigDecimal, BigDecimal>();
                                    configMap.put(tMsg.dsImptOrdDtlPk.getValue(), configBean.svcConfigMstrPk.getValue());
                                    addAccPkMap.put(setPkKey, configMap);
                                    ordDtlPkList.add(ordDtlPk);
                                    svcDtlPkList.add(svcDtlPk);
                                    addAccFlg = true;
                                }
                            }
                        }
                    }

                    if (null == lineBean) {
                        lineBean = hdrBean.getImptMdseByDtlPk(tMsg.dsImptOrdDtlPk.getValue());

                        // 2017/04/12 S21_NA#18284 Add Start
                        if (lineBean == null) {
                            continue;
                        }
                        // 2017/04/12 S21_NA#18284 Add End

                        if (ZYPCommonFunc.hasValue(lineBean.getDsImptLineBean().dsImptOrdConfigBean.dsOrdPosnNum)) {
                            dsOrdPosnNum = lineBean.getDsImptLineBean().dsImptOrdConfigBean.dsOrdPosnNum.getValue();
                        }
                    }

                    ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                    ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);

                    ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(apiMsg.cpoOrdNum, hdrBean.getNewOrdNum());
                    if (null != lineBean) {
                        ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, lineBean.getCpoDtlLineNum());
                        ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, lineBean.getCpoDtlLineSubNum());
                    }
                    // Mod End 2017/09/28 QC#20689
                    ZYPEZDItemValueSetter.setValue(apiMsg.svcConfigMstrPk, getSvcConfigMstrPk(hdrBean.getGlblCmpyCd(), hdrBean.getNewOrdNum(), dsOrdPosnNum, CONFIG_CATG.OUTBOUND));
                    ZYPEZDItemValueSetter.setValue(apiMsg.custIssPoNum, tMsg.custIssPoNum);
                    ZYPEZDItemValueSetter.setValue(apiMsg.custIssPoDt, tMsg.custIssPoDt);
                    ZYPEZDItemValueSetter.setValue(apiMsg.mtrReadMethCd, tMsg.mtrReadMethCd);
                    // 2018/04/02 QC#20162 add start
                    ZYPEZDItemValueSetter.setValue(apiMsg.dsPoExprDt, tMsg.dsPoExprDt);
                    // 2018/04/02 QC#20162 add end
                    
                    cnt++;
                }
            }
            pMsg.svcConfigRefList.setValidCount(cnt);

        } finally {

            writeEndLogLn("setContrImptApiSvcConfigRefListPMsg");
        }

        return addAccPkMap;
    }

    private BigDecimal getSvcConfigMstrPk(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum, String configCatgCd) {
        DS_CPO_CONFIGTMsg condition = new DS_CPO_CONFIGTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("cpoOrdNum01", cpoOrdNum);
        condition.setConditionValue("dsOrdPosnNum01", dsOrdPosnNum);
        condition.setConditionValue("configCatgCd01", configCatgCd);

        DS_CPO_CONFIGTMsgArray tmsgArray = (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (hasValidValue(tmsgArray)) {

            return tmsgArray.no(0).svcConfigMstrPk.getValue();
        }

        return null;
    }

    private void setContrImptApiSvcPrcListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg, Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap) {

        writeStartLogLn("setContrImptApiSvcPrcListPMsg", hdrBean);

        try {
            int cnt = 0;

            // *************************************************************
            // CPO Service Price List
            // *************************************************************
            NSZC115001_svcPrcListPMsg apiMsg;

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                DsImptLineBean lineBean = null;
                for (DS_IMPT_SVC_PRCTMsg tMsg : svcDtlBean.svcPrcMsgList) {

                    // Mod Start 2017/09/28 QC#20689
                    if (!checkAddAcc(addAccPkMap, tMsg.dsImptSvcDtlPk.getValue())) {
                        apiMsg = pMsg.svcPrcList.no(cnt);

                        BigDecimal dsImptOrdDtlPk = svcDtlBean.getImptOrdDtlPkFromSvcConfigRefPk(tMsg.dsImptSvcConfigRefPk.getValue());
                        if (ZYPCommonFunc.hasValue(dsImptOrdDtlPk)) {
                            lineBean = hdrBean.getImptLineByDtlPk(dsImptOrdDtlPk);
                        }

                        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                        ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);
                        if (lineBean != null) {

                            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, lineBean.dsImptOrdConfigBean.dsOrdPosnNum);
                        }

                        ZYPEZDItemValueSetter.setValue(apiMsg.mdlId, tMsg.mdlId);
                        ZYPEZDItemValueSetter.setValue(apiMsg.maintPrcCatgCd, tMsg.maintPrcCatgCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.prcMtrPkgPk, tMsg.prcMtrPkgPk);
                        ZYPEZDItemValueSetter.setValue(apiMsg.basePrcDealAmt, tMsg.basePrcDealAmt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.dealPrcListPrcAmt, tMsg.dealPrcListPrcAmt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.prcTierSvcOfferCd, tMsg.prcTierSvcOfferCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.corpAdvPrcFlg, tMsg.corpAdvPrcFlg);
                        ZYPEZDItemValueSetter.setValue(apiMsg.billToCustCd, tMsg.billToLocNum);
                        ZYPEZDItemValueSetter.setValue(apiMsg.sellToCustCd, tMsg.billToCustCd);

                        cnt++;
                    }
                    // Mod End 2017/09/28 QC#20689
                }
            }
            pMsg.svcPrcList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setContrImptApiSvcPrcListPMsg", hdrBean);
        }
    }

    private void setContrImptApiSvcUsgPrcListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg, Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap) {

        writeStartLogLn("setContrImptApiSvcUsgPrcListPMsg");

        try {
            int cnt = 0;

            // *************************************************************
            // CPO Service Usage Price List
            // *************************************************************
            NSZC115001_svcUsgPrcListPMsg apiMsg;
            DS_IMPT_SVC_PRCTMsg dsImptSvcPrcMsg;

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                for (DS_IMPT_SVC_USG_PRCTMsg tMsg : svcDtlBean.svcUsgPrcMsgList) {

                    // Mod Start 2017/09/28 QC#20689
                    if (!checkAddAcc(addAccPkMap, tMsg.dsImptSvcDtlPk.getValue())) {
                        apiMsg = pMsg.svcUsgPrcList.no(cnt);

                        dsImptSvcPrcMsg = svcDtlBean.getDsImptSvcPrcMsgFromSvcPrcPk(tMsg.dsImptSvcPrcPk.getValue());

                        if (dsImptSvcPrcMsg == null) {

                            writeLogLn("Not Exist in DS_IMPT_SVC_PRC.(DsImptSvcDtlPk : %d, DsImptSvcPrcPk : %d)", svcDtlBean.dsImptSvcDtlPk.getValueInt(), tMsg.dsImptSvcPrcPk.getValueInt());
                            continue;
                        }

                        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                        ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);

                        ZYPEZDItemValueSetter.setValue(apiMsg.mdlId, dsImptSvcPrcMsg.mdlId);
                        ZYPEZDItemValueSetter.setValue(apiMsg.prcListBandCd, tMsg.prcListBandCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.prcBookMdseCd, tMsg.prcBookMdseCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.bllgMtrLbCd, tMsg.bllgMtrLbCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.usgMdseCd, tMsg.usgMdseCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.copyInclPrcQty, tMsg.mlyCopyInclPrcQty);
                        ZYPEZDItemValueSetter.setValue(apiMsg.regMtrLbCd, tMsg.regMtrLbCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.xsMtrAmtRate, tMsg.xsMtrAmtRate);
                        ZYPEZDItemValueSetter.setValue(apiMsg.contrMtrMultRate, tMsg.contrMtrMultRate);
                        ZYPEZDItemValueSetter.setValue(apiMsg.prcSvcTierTpCd, tMsg.prcSvcTierTpCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.minCopyVolCnt, tMsg.minCopyVolCnt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.maxCopyVolCnt, tMsg.maxCopyVolCnt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.billToCustCd, tMsg.billToLocNum);
                        ZYPEZDItemValueSetter.setValue(apiMsg.sellToCustCd, tMsg.billToCustCd);
                        // QC#25030
                        ZYPEZDItemValueSetter.setValue(apiMsg.bllgFreeCopyCnt, tMsg.bllgFreeCopyCnt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.bllgMinAmtRate, tMsg.bllgMinAmtRate);
                        ZYPEZDItemValueSetter.setValue(apiMsg.bllgMinCnt, tMsg.bllgMinCnt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.bllgRollOverRatio, tMsg.bllgRollOverRatio);

                        // 2018/01/23 QC#18798 Mod Start
                        //ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, convToString(getOrdLinePosnNumByImptSvcConfigRefPk(hdrBean.getGlblCmpyCd(), dsImptSvcPrcMsg.dsImptSvcConfigRefPk.getValue())));
                        if (!ZYPConstant.FLG_ON_Y.equals(dsImptOrdConfigFlg)) {
                            // 2019/01/29 S21_NA#30022 Mod Start
                            // ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, convToString(getOrdLinePosnNumByImptSvcConfigRefPk(hdrBean.getGlblCmpyCd(), dsImptSvcPrcMsg.dsImptSvcConfigRefPk.getValue())));
                            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, convToString(getOrdLinePosnNumByImptSvcConfigRefPk(hdrBean.getGlblCmpyCd(), dsImptSvcPrcMsg.dsImptSvcConfigRefPk.getValue(), hdrBean)));
                            // 2019/01/29 S21_NA#30022 Mod End
                        } else {
                            ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdPosnNum, DS_ORD_POSN_NUM_1);
                        }
                        // 2018/01/23 QC#18798 Mod End
                        ZYPEZDItemValueSetter.setValue(apiMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(apiMsg.dsContrPk, svcDtlBean.dsContrPk); // 2019/01/08 S21_NA#29788 Add

                        cnt++;
                    }
                    // Mod End 2017/09/28 QC#20689
                }
            }
            pMsg.svcUsgPrcList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setContrImptApiSvcUsgPrcListPMsg");
        }
    }

    private void setContrImptApiSvcAddlBasePrcListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg) {
        writeStartLogLn("setContrImptApiSvcAddlBasePrcListPMsg");

        try {
            int cnt = 0;

            // *************************************************************
            // CPO Service Additional Base Price List
            // *************************************************************
            NSZC115001_svcAddlBasePrcListPMsg apiMsg;
            DsImptLineBean lineBean;

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                for (DS_IMPT_SVC_ADDL_BASETMsg tMsg : svcDtlBean.svcAddlBaseMsgList) {

                    apiMsg = pMsg.svcAddlBasePrcList.no(cnt);
                    lineBean = hdrBean.getImptLineByDtlPk(tMsg.dsImptOrdDtlPk.getValue());

                    ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                    ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);

                    if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals(tMsg.svcPrcCatgCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgCatgCd, ADDL_CHRG_CATG.RENTAL);

                    } else if (SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE.equals(tMsg.svcPrcCatgCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgCatgCd, ADDL_CHRG_CATG.ACCESSORY);

                    } else {
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgCatgCd, ADDL_CHRG_CATG.NORMAL);
                    }

                    if (lineBean != null) {

                        // error
                        ZYPEZDItemValueSetter.setValue(apiMsg.cpoOrdNum, hdrBean.getNewOrdNum());
                        ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, lineBean.mdseAllList.get(0).getCpoDtlLineNum());
                        ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, lineBean.mdseAllList.get(0).getCpoDtlLineSubNum());
                    }
                    ZYPEZDItemValueSetter.setValue(apiMsg.addlBasePrcCatgCd, tMsg.addlBasePrcCatgCd);
                    ZYPEZDItemValueSetter.setValue(apiMsg.addlBasePrcDealAmt, tMsg.addlBasePrcDealAmt);

                    ZYPEZDItemValueSetter.setValue(apiMsg.dealPrcListPrcAmt, tMsg.dealPrcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(apiMsg.prcListEquipConfigNum, tMsg.prcListEquipConfigNum);
                    ZYPEZDItemValueSetter.setValue(apiMsg.svcPrcCatgCd, tMsg.svcPrcCatgCd);

                    cnt++;
                }
            }
            pMsg.svcAddlBasePrcList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setContrImptApiSvcAddlBasePrcListPMsg");
        }
    }

    private void setContrImptApiSvcAddlChrgPrcListPMsg(ImptHdrBean hdrBean, List<DsImptSvcDtlBean> svcDtlBeanList, NSZC115001PMsg pMsg, Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap) {

        writeStartLogLn("setContrImptApiSvcAddlChrgPrcListPMsg");

        try {
            int cnt = 0;

            // *************************************************************
            // CPO Service Additional Charge Price List
            // *************************************************************
            NSZC115001_svcAddlChrgPrcListPMsg apiMsg;
            // Mod Start 2017/06/26 QC#19449
//            DsImptLineBean lineBean;
            ExpendMdseBean lineBean;
            // Mod End 2017/06/26 QC#19449

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {

                for (DS_IMPT_SVC_ADDL_CHRGTMsg tMsg : svcDtlBean.svcAddlChrgMsgList) {

                    // Mod Start 2017/09/28 QC#20689
                    if (!checkAddAcc(addAccPkMap, tMsg.dsImptSvcDtlPk.getValue())) {
                        apiMsg = pMsg.svcAddlChrgPrcList.no(cnt);
                        // Mod Start 2017/06/26 QC#19449
//                        lineBean = hdrBean.getImptLineByDtlPk(tMsg.dsImptOrdDtlPk.getValue());
                        lineBean = hdrBean.getImptMdseByDtlPk(tMsg.dsImptOrdDtlPk.getValue());
                        // Mod End 2017/06/26 QC#19449

                        ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                        ZYPEZDItemValueSetter.setValue(apiMsg.shellLineNum, svcDtlBean.dsImptSvcLineNum);

                        if (lineBean != null) {

                            ZYPEZDItemValueSetter.setValue(apiMsg.cpoOrdNum, hdrBean.getNewOrdNum());
                            // Mod Start 2017/06/26 QC#19449
//                            ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, lineBean.mdseAllList.get(0).getCpoDtlLineNum());
//                            ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, lineBean.mdseAllList.get(0).getCpoDtlLineSubNum());
                            ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineNum, lineBean.getCpoDtlLineNum());
                            ZYPEZDItemValueSetter.setValue(apiMsg.cpoDtlLineSubNum, lineBean.getCpoDtlLineSubNum());
                            // Mod End 2017/06/26 QC#19449
                        }
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgPrcCatgCd, tMsg.addlChrgPrcCatgCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgMdseCd, tMsg.addlChrgMdseCd);
                        ZYPEZDItemValueSetter.setValue(apiMsg.addlChrgPrcDealAmt, tMsg.addlChrgPrcDealAmt);

                        ZYPEZDItemValueSetter.setValue(apiMsg.dealPrcListPrcAmt, tMsg.dealPrcListPrcAmt);
                        ZYPEZDItemValueSetter.setValue(apiMsg.svcPrcCatgCd, tMsg.svcPrcCatgCd);

                        ZYPEZDItemValueSetter.setValue(apiMsg.printDtlFlg, tMsg.printDtlFlg);

                        cnt++;
                    }
                    // Mod End 2017/09/28 QC#20689
                }
            }
            pMsg.svcAddlChrgPrcList.setValidCount(cnt);
        } finally {

            writeEndLogLn("setContrImptApiSvcAddlChrgPrcListPMsg");
        }
    }

    // Add Start 2017/06/28 QC#19440
    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }
    // Add End 2017/06/28 QC#19440

    // 2017/07/10 S21_NA#19789 Add Start
    private void updateMdseCdForNoLoanRtn(ImptHdrBean hdrBean, NWZC150001PMsg cpoUpdApiPMsg) {

        if (!hasValidValue(cpoUpdApiPMsg.rtnDtl) || !ZYPCommonFunc.hasValue(hdrBean.getGlblCmpyCd())) {
            return;
        }

        if (ZYPCommonFunc.hasValue(hdrBean.getLoanOrigOrdNum())) {
            return;
        }

        for (int configIdx = 0; configIdx < cpoUpdApiPMsg.cpoConfig.getValidCount(); configIdx++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = cpoUpdApiPMsg.cpoConfig.no(configIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, cpoConfigPMsg.configCatgCd.getValue())) {
                continue;
            }
            SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = null;

            BigDecimal svcConfigMstrPk = cpoConfigPMsg.svcConfigMstrPk.getValue();

            if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                SVC_MACH_MSTRTMsg condition = new SVC_MACH_MSTRTMsg();
                condition.setSQLID("016");
                condition.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
                condition.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
                svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
            }

            for (int lineIdx = 0; lineIdx < cpoUpdApiPMsg.rtnDtl.getValidCount(); lineIdx++) {
                MDSETMsg mdseTMsg = null;
                NWZC150001_rtnDtlPMsg rtnPMsg = cpoUpdApiPMsg.rtnDtl.no(lineIdx);

                String dsOrdPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
                String lineDsOrdPosnNum = rtnPMsg.dsOrdPosnNum_B1.getValue();
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineDsOrdPosnNum)) {
                    continue;
                }

                if (!isOrderTakeMdseCd(hdrBean.getGlblCmpyCd(), rtnPMsg.mdseCd_B1.getValue())) {
                    continue;
                }
                mdseTMsg = getMdse(hdrBean.getGlblCmpyCd(), rtnPMsg.mdseCd_B1.getValue());
                if (mdseTMsg != null //
                        && rtnPMsg.mdseCd_B1.getValue().length() == mdseTMsg.mdseCatgCd.getValue().length() //
                        && rtnPMsg.mdseCd_B1.getValue().length() == mdseTMsg.mdseCatgCd.getValue().length()) {
                    continue;
                }

                String imptMdseCd = rtnPMsg.mdseCd_B1.getValue();
                if (svcMachMstrTMsgArray != null && svcMachMstrTMsgArray.getValidCount() > 0) {
                    setInstalledMdseCd(rtnPMsg, svcMachMstrTMsgArray);
                }
                if (S21StringUtil.isEquals(imptMdseCd, rtnPMsg.mdseCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rtnPMsg.mdseCd_B1, mdseTMsg.mdseCd);
                }
            }
        }
    }

    private boolean isOrderTakeMdseCd(String glblCmpyCd, String ordTakeMdseCd) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) //
                || !ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
            return false;
        }
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeMdseCdLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
        int paramOrdTakeMdseCdLen = ordTakeMdseCd.length();
        if (paramOrdTakeMdseCdLen > ordTakeMdseCdLen) {
            return false;
        }
        ordTakeMdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ordTakeMdseTMsg.ordTakeMdseCd.setValue(ordTakeMdseCd);

        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
        if (ordTakeMdseTMsg == null) {
            return false;
        }
        return true;
    }
    // 2017/07/10 S21_NA#19789 Add End

    // 2017/08/01 S21_NA#19413 Add Start
    private boolean isMainMachMdse(DsImptOrdConfigBean configBean, String mdseCd) {

        String mainMachMdseCd = this.getMainMachMdseCd(configBean.glblCmpyCd.getValue(), configBean.svcConfigMstrPk.getValue());

        if (!ZYPCommonFunc.hasValue(mainMachMdseCd)) {
            return false;
        }

        MDSETMsg mainMachMdseMsg = getMdse(configBean.glblCmpyCd.getValue(), mainMachMdseCd);

        MDSETMsg mdseMsg = getMdse(configBean.glblCmpyCd.getValue(), mdseCd);

        return S21StringUtil.isEquals(mainMachMdseMsg.mdseCd.getValue(), mdseMsg.mdseCd.getValue());
    }

    private String getMainMachMdseCd(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        String mainMachMdseCd = null;

        SVC_CONFIG_MSTRTMsg svcConfigMstrMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcConfigMstrMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcConfigMstrMsg.svcConfigMstrPk, svcConfigMstrPk);
        svcConfigMstrMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcConfigMstrMsg);

        if (svcConfigMstrMsg != null) {
            SVC_MACH_MSTRTMsg svcMachMstrMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrMsg.svcMachMstrPk, svcConfigMstrMsg.svcMachMstrPk);
            svcMachMstrMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstrMsg);

            if (svcMachMstrMsg != null) {
                mainMachMdseCd = svcMachMstrMsg.mdseCd.getValue();
            }
        }

        return mainMachMdseCd;
    }
    // 2017/08/01 S21_NA#19413 Add End

    // 2017/08/02 S21_NA#19127 Add Start
    private boolean otherCheck(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {
        boolean result = true;

        if (!otherCheckSplyAgmtPln(msgMap, hdrBean)) {
            result = false;
        }

        return result;
    }

    private boolean otherCheckSplyAgmtPln(S21ApiMessageMap msgMap, ImptHdrBean hdrBean) {
        writeStartLogLn("otherCheckSplyAgmtPln", hdrBean);

        try {

            List<DsImptSvcDtlBean> svcDtlBeanList = hdrBean.getDsImptSvcDtlList();
            boolean result = true;

            if (svcDtlBeanList == null || svcDtlBeanList.size() == 0) {

                // not exists shell data
                return true;
            }

            for (DsImptSvcDtlBean svcDtlBean : svcDtlBeanList) {
                IImportBean owner = svcDtlBean;

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                ssmParam.put("dsImptSvcDtlPk", svcDtlBean.dsImptSvcDtlPk.getValue());

                List<Map<String, Object>> SchdTmplList = NWZC226001Query.getInstance().queryMapList("getSchdTmplListForCheck", ssmParam);

                if (SchdTmplList == null || SchdTmplList.size() == 0) {
                    // not exists schedule template data
                    continue;
                }

                for (Map<String, Object> schdTmpl : SchdTmplList) {
                    // 2018/09/18 S21_NA#28270 Add Start
                    if (!ZYPCommonFunc.hasValue((String) SchdTmplList.get(0).get("PRC_CATG_CD")) || !ZYPCommonFunc.hasValue((String) SchdTmplList.get(0).get("PRC_SVC_PLN_TP_CD")) ) {
                        continue;
                    }
                    // 2018/09/18 S21_NA#28270 Add End
                    // get corresponding splyAgmtPlnPk
                    ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
                    ssmParam.put("prcCatgCd", (String) schdTmpl.get("PRC_CATG_CD"));
                    ssmParam.put("mdlId", (BigDecimal) schdTmpl.get("MDL_ID"));
                    ssmParam.put("prcMtrPkgPk", (BigDecimal) schdTmpl.get("PRC_MTR_PKG_PK"));
                    ssmParam.put("prcSvcPlnTpCd", (String) schdTmpl.get("PRC_SVC_PLN_TP_CD"));
                    ssmParam.put("prcSvcContrTpCd", (String) schdTmpl.get("PRC_SVC_CONTR_TP_CD"));
                    ssmParam.put("bllgMtrLbCd", (String) schdTmpl.get("BLLG_MTR_LB_CD"));
                    ssmParam.put("prcListBandCd", (String) schdTmpl.get("PRC_LIST_BAND_CD"));
                    ssmParam.put("slsDt", hdrBean.getSlsDt());
                    ssmParam.put("splyAgmtPlnShortNm", (String) schdTmpl.get("SPLY_AGMT_PLN_SHORT_NM"));

                    BigDecimal count = NWZC226001Query.getInstance().queryBigDecimal("getSplyAgmtPlnInfo", ssmParam);

                    // Check Supply Plan is ready or not
                    if (ZYPCommonFunc.hasValue(count) && count.compareTo(BigDecimal.ZERO) != 0) {
                        continue;
                    }

                    List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();
                    DsImptOrdErrBean errBean;

                    errBean = new DsImptOrdErrBean(owner, MSG_ID.NWZM2235E.toString(), new String[] {(String) schdTmpl.get("SPLY_AGMT_PLN_SHORT_NM"), (String) schdTmpl.get("PRC_CATG_NM") });
                    errBeanList.add(errBean);

                    if (!owner.isSameErrorExists(errBean)) {

                        owner.getDsImptOrdErrList().add(errBean);
                    }

                    writeErrLog(errBean);

                    result = false;

                }
            }
            return result;

        } finally {

            writeEndLogLn("otherCheckSplyAgmtPln", hdrBean);
        }
    }

    // 2017/08/02 S21_NA#19127 Add End

    // 2017/08/15 S21_NA#20000-2 Add Start
    private List<DS_IMPT_ORD_SITE_SRVYTMsg> getSiteSrvyTMsg4RmaFromLineConfig(ImptHdrBean hdrBean, String shiptoCustLocCd) {

        Set<BigDecimal> dsImptOrdConfigPkSet = hdrBean.dsImptOrdConfigMap.keySet();
        List<BigDecimal> dsImptOrdConfigPkList = new ArrayList<BigDecimal>(0);
        for (BigDecimal dsImptOrdConfigPk : dsImptOrdConfigPkSet) {
            dsImptOrdConfigPkList.add(dsImptOrdConfigPk);
        }
        // Sort Order By dsImptOrdConfigPk
        Collections.sort(dsImptOrdConfigPkList);

        for (BigDecimal dsImptOrdConfigPk : dsImptOrdConfigPkList) {
            DsImptOrdConfigBean dsImptOrdConfigBean = hdrBean.dsImptOrdConfigMap.get(dsImptOrdConfigPk);
            if (dsImptOrdConfigBean == null) {
                continue;
            }
            if (!S21StringUtil.isEquals(dsImptOrdConfigBean.shipToCustLocCd.getValue(), shiptoCustLocCd)) {
                continue;
            }
            return dsImptOrdConfigBean.dsImptSiteSrvyList;
        }
        return null;
    }
    // 2017/08/15 S21_NA#20000-2 Add End

    // Add Start 2017/08/21 QC#19233
    private static boolean isValidPrcMtrPkg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        String glblCmpyCd = inPrmPMsg.glblCmpyCd.getValue();
        String slsDt = inPrmPMsg.slsDt.getValue();

        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        if (ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
            mdlIdList.add(svcPrcPMsg.mdlId.getValue());
        } else {
            // fleet
            mdlIdList = getMdlIdList(inPrmPMsg, svcPrcPMsg);
        }
        if (mdlIdList == null || mdlIdList.size() == 0) {
            return false;
        }

        String prcCatgCd = svcPrcPMsg.maintPrcCatgCd.getValue();
        String prcListTp = getPrcListTp(prcCatgCd, glblCmpyCd);

        DS_CONTRTMsg csdTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(csdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(csdTMsg.dsContrPk, svcPrcPMsg.dsContrPk);
        csdTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(csdTMsg);
        if (csdTMsg == null) {
            return false;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prcListTp", prcListTp);
        param.put("prcCatgCd", prcCatgCd);
        param.put("mdlIdList", mdlIdList);
        param.put("prcSvcPlnTp", csdTMsg.prcSvcPlnTpCd);
        param.put("prcSvcContrTp", csdTMsg.prcSvcContrTpCd);
        param.put("prcMtrPkgPk", svcPrcPMsg.prcMtrPkgPk);
        param.put("slsDt", slsDt);

        Integer cnt = NWZC226001Query.getInstance().queryInt("getCountPrcMtrPkg", param);

        return cnt > 0;
    }

    private static String getPrcListTp(String prcCatgCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgCd", prcCatgCd);

        return NWZC226001Query.getInstance().queryString("getPrcListTp", ssmParam);
    }

    private static String getprcMtrPkgNm(BigDecimal prcMtrPkgPk, String glblCmpyCd, String slsDt) {

        PRC_MTR_PKGTMsg prcMtrPkgTMsg = new PRC_MTR_PKGTMsg();
        prcMtrPkgTMsg.setSQLID("001");
        prcMtrPkgTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prcMtrPkgTMsg.setConditionValue("prcMtrPkgPk01", prcMtrPkgPk);
        prcMtrPkgTMsg.setConditionValue("effFromDt01", slsDt);
        prcMtrPkgTMsg.setConditionValue("effThruDt01", slsDt);
        PRC_MTR_PKGTMsgArray prcMtrPkgTMsgAry = (PRC_MTR_PKGTMsgArray) S21ApiTBLAccessor.findByCondition(prcMtrPkgTMsg);

        if (prcMtrPkgTMsgAry.length() > 0) {
            return prcMtrPkgTMsgAry.no(0).prcMtrPkgDescTxt.getValue();
        } else {
            return null;
        }
    }

    private static String getMdlNm(BigDecimal mdlId, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlId", mdlId);

        return NWZC226001Query.getInstance().queryString("getMdlNm", ssmParam);
    }

    private static List<BigDecimal> getMdlIdList(NSZC115001PMsg inPrmPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("configCatgOutbound", CONFIG_CATG.OUTBOUND);
        param.put("dsContrPk", svcPrcPMsg.dsContrPk.getValue());

        List<BigDecimal> mdlIdList =NWZC226001Query.getInstance().queryBigDecimalList("getMdlIdList", param);

        return mdlIdList;
    }
    // Add End 2017/08/21 QC#19233

    // 2017/08/25 S21_NA#20740-1 Add Start
    private String getRqstPickUpDt(ImptHdrBean hdrBean, DsImptRtnLineBean rtnLineBean) {

        if (hdrBean == null) {
            return null;

        } else if (rtnLineBean.dsImptOrdConfigBean == null) {
            return hdrBean.getSlsDt();
        }

        DsImptOrdConfigBean configBean = rtnLineBean.dsImptOrdConfigBean;

        for (DsImptRtnLineBean otherRtnLineBean : configBean.dsImptRtnLineList) {

            if (ZYPCommonFunc.hasValue(otherRtnLineBean.rqstPickUpDt)) {
                return otherRtnLineBean.rqstPickUpDt.getValue();
            }
        }

        for (DS_IMPT_ORD_ISTL_INFOTMsg instInfo : configBean.dsImptInstList) {

            if(rtnLineBean.dsImptOrdConfigPk.getValue().compareTo(instInfo.dsImptOrdConfigPk.getValue()) == 0) {
                if (ZYPCommonFunc.hasValue(instInfo.rqstIstlDt)) {
                    return instInfo.rqstIstlDt.getValue();
                }
            }
        }

        return hdrBean.getSlsDt();
    }
    // 2017/08/25 S21_NA#20740-1 Add End

    // Add Start 2017/09/28 QC#20689
    private boolean checkAddAcc(Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap, BigDecimal svcDtlPk) {

        for (Map.Entry<String, Map<BigDecimal, BigDecimal>> addAccMap : addAccPkMap.entrySet()) {

            String key = addAccMap.getKey();
            int idx = key.indexOf(":");
            if (idx > 0) {
                String getSvcDtlPk = key.substring(0, idx);
                if (getSvcDtlPk.equals(svcDtlPk.toString())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * QC#59719 Add
     */
    private DsImptOrdConfigBean isAddAccessory(ImptHdrBean hdrBean, DsImptLineBean lineBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsImptOrdDtlPk", lineBean.dsImptOrdDtlPk.getValue());
        // 2018/06/14 S21_NA#24294 Mod Start
        // Map<String, Object> result = NWZC226001Query.getInstance().queryMap("getConfigLineInfo", ssmParam);
        Map<String, Object> result = DataCacheForSSM.getInstance().getConfigLineInfo(ssmParam);
        // 2018/06/14 S21_NA#24294 Mod End

        for (Map.Entry<BigDecimal, DsImptOrdConfigBean> configBeanEntry : hdrBean.dsImptOrdConfigMap.entrySet()) {

            DsImptOrdConfigBean configBean = configBeanEntry.getValue();
            if ((configBean.dsImptOrdConfigPk.getValue().equals((BigDecimal) result.get("DS_IMPT_ORD_CONFIG_PK"))) //
                    && (CONFIG_TP.ADD_TO_CONFIG.equals((String) result.get("CONFIG_TP_CD")) //
                    || (CONFIG_TP.NEW.equals((String) result.get("CONFIG_TP_CD")) //
                    && ZYPCommonFunc.hasValue(configBean.svcConfigMstrPk)))) {

                return configBean;
            }
        }

        return null;
    }

    private DsImptOrdConfigBean isAddAccessory(ImptHdrBean hdrBean, DS_IMPT_SVC_CONFIG_REFTMsg svcConfigRefTMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        ssmParam.put("dsImptOrdDtlPk", svcConfigRefTMsg.dsImptOrdDtlPk.getValue());
        // 2018/06/14 S21_NA#24294 Mod Start
        // Map<String, Object> result = NWZC226001Query.getInstance().queryMap("getConfigLineInfo", ssmParam);
        Map<String, Object> result = DataCacheForSSM.getInstance().getConfigLineInfo(ssmParam);
        // 2018/06/14 S21_NA#24294 Mod End

        for (Map.Entry<BigDecimal, DsImptOrdConfigBean> configBeanEntry : hdrBean.dsImptOrdConfigMap.entrySet()) {

            DsImptOrdConfigBean configBean = configBeanEntry.getValue();
            if ((configBean.dsImptOrdConfigPk.getValue().equals((BigDecimal) result.get("DS_IMPT_ORD_CONFIG_PK"))) //
                    && (CONFIG_TP.ADD_TO_CONFIG.equals((String) result.get("CONFIG_TP_CD")) //
                    || (CONFIG_TP.NEW.equals((String) result.get("CONFIG_TP_CD")) //
                    && ZYPCommonFunc.hasValue(configBean.svcConfigMstrPk)))) {

                return configBean;
            }
        }

        return null;
    }

    private void setChangeReqModeMod(NSZC115001PMsg pMsg) {

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg;
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = pMsg.svcConfigRefList.no(i);
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        }

        NSZC115001_svcDtlListPMsg svcDtlListPMsg;
        for (int i = 0; i < pMsg.svcDtlList.getValidCount(); i++) {
            svcDtlListPMsg = pMsg.svcDtlList.no(i);
            ZYPEZDItemValueSetter.setValue(svcDtlListPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        }

    }

    private String setPkKey(BigDecimal svcDtlPk, BigDecimal ordDtlPk) {

        StringBuilder sb = new StringBuilder();
        sb.append(svcDtlPk);
        sb.append(":");
        sb.append(ordDtlPk);

        return sb.toString();
        
    }

    private BigDecimal getConfigPk(Map<String, Map<BigDecimal, BigDecimal>> addAccPkMap, BigDecimal ordDtlPk) {

        for(Map.Entry<String, Map<BigDecimal, BigDecimal>> configMap : addAccPkMap.entrySet()) {

            if (configMap.getValue().containsKey(ordDtlPk)) {
                return configMap.getValue().get(ordDtlPk);
            }
        }

        return BigDecimal.valueOf(-1);
    }
    // Add End 2017/09/28 QC#20689
    // QC#22443 2017/11/28 Add Start
    private ALL_MDSE_VTMsg getAllMdseV(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
        tmsg.setSQLID("003");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("mdseCd01", mdseCd);

        ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        if (tmsgArray.length() == 0) {
            return null;
        }
        return (ALL_MDSE_VTMsg) tmsgArray.get(0);
    }
    // QC#22443 2017/11/28 Add End

    // 2017/12/06 S21_NA#22794 Add Start
    private boolean isOrderInbound(ImptHdrBean hdrBean) {

        CONFIG_TPTMsg configTpTMsg = new CONFIG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(configTpTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(configTpTMsg.configTpCd, hdrBean.getConfigTpCd());
        configTpTMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(configTpTMsg);
        if (configTpTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configTpTMsg.configCatgCd.getValue());
    }
    // 2017/12/06 S21_NA#22794 Add End

    // 2017/12/28 S21_NA#23083 Add Start
    private SHPG_PLNTMsg getOrigShpgPln(ImptHdrBean hdrBean, ExpendMdseBean mdseBean) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.setSQLID("001");
        shpgPlnTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
        shpgPlnTMsg.setConditionValue("trxHdrNum01", hdrBean.getLoanOrigOrdNum());
        // Mod Start 2018/03/14 QC#23507
//        shpgPlnTMsg.setConditionValue("trxLineNum01", mdseBean.getCpoDtlLineNum());
//        shpgPlnTMsg.setConditionValue("trxLineSubNum01", mdseBean.getCpoDtlLineSubNum());
        shpgPlnTMsg.setConditionValue("trxLineNum01", mdseBean.getOrigCpoDtlLineNum());
        shpgPlnTMsg.setConditionValue("trxLineSubNum01", mdseBean.getOrigCpoDtlLineSubNum());
        // Mod End 2018/03/14 QC#23507

        SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(shpgPlnTMsg);
        if (shpgPlnTMsgArray.length() == 0) {
            return null;
        }
        return (SHPG_PLNTMsg) shpgPlnTMsgArray.get(0);
    }
    // 2017/12/28 S21_NA#23083 Add End

    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    private NMZC610001PMsg callCustInfoGetApiForDefShpgCmnt(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, PROCESS_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, hdrBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, getBizAreaCd(hdrBean));
        ZYPEZDItemValueSetter.setValue(pMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, hdrBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, hdrBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, hdrBean.getShipToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, hdrBean.getSlsDt());

        new NMZC610001().execute(pMsg, onBatchType);

        addErrorMsgList(pMsg, hdrBean);

        return pMsg;
    }

    private String getBizAreaCd(ImptHdrBean hdrBean) {

        ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
        ordCatgBizCtxTMsg.setSQLID("002");
        ordCatgBizCtxTMsg.setConditionValue("glblCmpyCd01", hdrBean.getGlblCmpyCd());
        ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        ordCatgBizCtxTMsg.setConditionValue("dsOrdCatgCd01", hdrBean.getDsOrdCatgCd());

        ORD_CATG_BIZ_CTXTMsgArray ordCatgBizCtxTMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(ordCatgBizCtxTMsg);
        if (hasValidValue(ordCatgBizCtxTMsgArray)) {
            return ordCatgBizCtxTMsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        } else {
            return null;
        }
    }
    // 2018/02/08 S21_NA#20297(Sol#379) Add End
    // 2018/04/04 QC#22025 Add Start
    private String getOrigOrderInvCmnt(String glblCmpyCd, String origOrdNum) {

        StringBuilder commentTxt = new StringBuilder();

        MSG_TXT_DTLTMsg keyTMsg = new MSG_TXT_DTLTMsg();
        keyTMsg.setSQLID("001");
        keyTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyTMsg.setConditionValue("cpoOrdNum01", origOrdNum);
        keyTMsg.setConditionValue("txtTpCd01", TXT_TP.INVOICE_COMMENT);
        MSG_TXT_DTLTMsgArray tMsgs = (MSG_TXT_DTLTMsgArray) EZDTBLAccessor.findByCondition(keyTMsg);

        if (tMsgs != null) {
            for (int i = 0; i < tMsgs.getValidCount(); i++) {
                MSG_TXT_DTLTMsg tMsg = (MSG_TXT_DTLTMsg) tMsgs.get(i);
                commentTxt.append(tMsg.msgTxtInfoTxt.getValue());
            }
        }
        return commentTxt.toString();
    }
    // 2018/04/04 QC#22025 Add End
    // 2018/04/27 S21_NA#22189 Add Start
    private String getRmaAutoAddKey(String mdseCd, String serNum, BigDecimal svcMachMstrPk) {

        String mdseSerKey = mdseCd;
        if (ZYPCommonFunc.hasValue(serNum)) {
            mdseSerKey = mdseSerKey + "-" + serNum;
        } else {
            mdseSerKey = mdseSerKey + "-NOSERIAL";
        }
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            mdseSerKey = mdseSerKey + "-" + String.valueOf(svcMachMstrPk);
        } else {
            mdseSerKey = mdseSerKey + "-NOSMMPK";
        }
        return mdseSerKey;
    }

    private BigDecimal getSvcMachMstrPkByMdseSerNum(String glblCmpyCd, String mdseCd, String serNum) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) //
                || !ZYPCommonFunc.hasValue(mdseCd) //
                || !ZYPCommonFunc.hasValue(serNum)) {
            return null;
        }

        SVC_MACH_MSTRTMsg queryKeySvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        queryKeySvcMachMstrTMsg.setSQLID("001");
        queryKeySvcMachMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        queryKeySvcMachMstrTMsg.setConditionValue("serNum01", serNum);
        queryKeySvcMachMstrTMsg.setConditionValue("mdseCd01", mdseCd);

        SVC_MACH_MSTRTMsgArray rsltSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(queryKeySvcMachMstrTMsg);

        if (rsltSvcMachMstrTMsgArray == null //
                || rsltSvcMachMstrTMsgArray.getValidCount() == 0) {
            return null;
        }
        return rsltSvcMachMstrTMsgArray.no(0).svcMachMstrPk.getValue();
    }
    // 2018/04/27 S21_NA#22189 Add End
    // 2018/05/08 QC#25770 Add Start
    private void setFrtCondCdForPound(ImptHdrBean hdrBean) {

        BigDecimal totalWt = new BigDecimal(0);

        for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {
            // 2018/06/05 S21_NA#26643 Mod Start
//            MDSE_STORE_PKGTMsg keyTMsg = new MDSE_STORE_PKGTMsg();
//            ZYPEZDItemValueSetter.setValue(keyTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
//            ZYPEZDItemValueSetter.setValue(keyTMsg.pkgUomCd, lineBean.custUomCd.getValue());
//            ZYPEZDItemValueSetter.setValue(keyTMsg.mdseCd, lineBean.mdseCd.getValue());
//            keyTMsg = (MDSE_STORE_PKGTMsg) S21ApiTBLAccessor.findByKey(keyTMsg);
//
//            BigDecimal inPoundWt = keyTMsg.inPoundWt.getValue();
            // Mod Start 2018/06/29 QC#27035
            //Map<String, Object> ssmParam = new HashMap<String, Object>();
            //ssmParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
            //ssmParam.put("pkgUomCd", lineBean.custUomCd.getValue());
            //ssmParam.put("mdseCd", lineBean.mdseCd.getValue());
            //Map<String, Object> result = NWZC226001Query.getInstance().queryMap("getMdseStorePkgInfo", ssmParam);
            //
            //BigDecimal inPoundWt = BigDecimal.ZERO;
            //if (result != null) {
            //    inPoundWt = (BigDecimal) result.get("IN_POUND_WT");
            //}

            // Mod Start 2018/11/21 QC#29184
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(hdrBean.getGlblCmpyCd(), lineBean.mdseCd.getValue());

//            MDSE_STORE_PKGTMsg keyTMsg = new MDSE_STORE_PKGTMsg();
            MDSE_STORE_PKGTMsg keyTMsg = null;

            if (mdseTMsg != null) {
                keyTMsg = new MDSE_STORE_PKGTMsg();

                ZYPEZDItemValueSetter.setValue(keyTMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(keyTMsg.pkgUomCd, PKG_UOM.EACH);
//                ZYPEZDItemValueSetter.setValue(keyTMsg.mdseCd, lineBean.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(keyTMsg.mdseCd, mdseTMsg.mdseCd.getValue());

                keyTMsg = (MDSE_STORE_PKGTMsg) S21ApiTBLAccessor.findByKey(keyTMsg);
            }
            // Mod End 2018/11/21 QC#29184

            BigDecimal inPoundWt = BigDecimal.ZERO;

            
            if (keyTMsg != null) {
                inPoundWt = keyTMsg.inPoundWt.getValue();
            }
            // Mod End 2018/06/29 QC#27035
            // 2018/06/05 S21_NA#26643 Mod End

            totalWt = totalWt.add(inPoundWt.multiply(lineBean.ordQty.getValue()));
        }

        // 2018/06/06 QC#25770-2 Mod Start
//      if (TOTAL_WT_THRESHOLD.compareTo(totalWt) > 0) {
        if (TOTAL_WT_THRESHOLD.compareTo(totalWt) >= 0) {
        // 2018/06/06 QC#25770-2 Mod End
            hdrBean.setFrtCondCd(FRT_COND.COLLECT);
        } else {
            hdrBean.setFrtCondCd(FRT_COND.PREPAID_AND_ADD);
        }
    }
    // 2018/05/08 QC#25770 Add End
    
    // 2018/06/11 S21_NA#24294 Add Start
    private void mapData(Map<String, Object> map, EZDTMsg outMsg) {

        for (Map.Entry<String, Object> bar : map.entrySet()) {
            String key = snakeToCamel(bar.getKey());
            Object getVal = bar.getValue();
            int type = outMsg.getSchema().getJavaType(key);
            if(EZDItemAttrDefines.JT_NOT_DEFINED == type){
               key =  convCommonCol(key);
               type = outMsg.getSchema().getJavaType(key);
            }

            switch (type) {
                case EZDItemAttrDefines.JT_STRING:
                    getVal = (String) getVal;
                    if (EZDStringUtil.isShiftJisEncoding()) {
                        int ktype = outMsg.getSchema().getType(key);
                        if (ktype == EZDItemAttrDefines.TYPE_ZENKAKU || ktype == EZDItemAttrDefines.TYPE_KONZAI) {
                            getVal = ((String) getVal).replace('\u301c', '\uff5e');
                        }
                    }

                    break;

                case EZDItemAttrDefines.JT_BIGDECIMAL:
                    getVal = (BigDecimal) getVal;
                    break;

                default:
                    continue;
            }

            outMsg.setDBValue(key, getVal);
        }
    }

    private String snakeToCamel(final String snake) {

        final StringBuilder sb = new StringBuilder(snake.length() + snake.length());
        for (int i = 0; i < snake.length(); i++) {
            final char c = snake.charAt(i);
            if (c == '_') {
                sb.append((i + 1) < snake.length() ? Character.toUpperCase(snake.charAt(++i)) : "");
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }
    
    private String convCommonCol(final String key) {
        for (int i = 0; i < EZDTMsgConst.COMMON_COLUMN_NAMES.length; i++) {
            String str = EZDTMsgConst.COMMON_COLUMN_NAMES[i];
            if ((str.toLowerCase()).equals(key)) {
                return EZDTMsgConst.COMMON_COLUMN_NAMES[i];
            }
        }
        return null;
    }
    // 2018/06/11 S21_NA#24294 Add End

    // Add Start 2018/06/25 QC#23726
    /**
     * getDsOrdTpProcDfn
     * @param hdrBean ImptHdrBean
     * @return NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg>
     */
    private NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> getDsOrdTpProcDfn(ImptHdrBean hdrBean) {
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
        if (ZYPCommonFunc.hasValue(hdrBean.getOrdSrcImptTs())) {
            String chkDate = hdrBean.getOrdSrcImptTs_Len8();
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, chkDate);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, chkDate);
        }

        NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result = NWXC220001.getDefDsOrdTpProcDfn(tMsg);

        return result;
    }

    /**
     * deriveCarrSvcLvl
     * @param onBatchType ONBATCH_TYPE
     * @param hdrBean ImptHdrBean
     * @param result NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg>
     */
    private void deriveCarrSvcLvl(ONBATCH_TYPE onBatchType, ImptHdrBean hdrBean, NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result) {

        if (ZYPCommonFunc.hasValue(hdrBean.getCarrSvcLvlCd())) {
            return;
        }

        // Carrier Service Level
        setCarrSvcLvl(onBatchType, hdrBean);

        // Del Start 2018/12/28 QC#29759
//        if (!ZYPCommonFunc.hasValue(hdrBean.getCarrSvcLvlCd()) && result.hasResultObject()) {
//            // Default Carrier Service Level
//            DS_ORD_TP_PROC_DFNTMsg resultTMsg = result.getResultObject();
//
//            String defCarrSvcLvlCd = convToString(resultTMsg.defCarrSvcLvlCd.getValue());
//            hdrBean.setCarrSvcLvlCd(defCarrSvcLvlCd);
//        }
        // Del End 2018/12/28 QC#29759
    }

    /**
     * checkCarrSvcLvlRelation
     * @param hdrBean ImptHdrBean
     * @return boolean
     */
    private boolean checkCarrSvcLvlRelation(ImptHdrBean hdrBean) {
        if (NWXC150001DsCheck.checkCustCarrSvcLvlRelation(hdrBean.getGlblCmpyCd(), //
                getBizAreaCd(hdrBean), hdrBean.getShipToCustAcctCd(), //
                hdrBean.getCarrSvcLvlCd(), hdrBean.getFrtCondCd())) {

            DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2267E);

            if (!hdrBean.isSameErrorExists(errBean)) {
                hdrBean.getDsImptOrdErrList().add(errBean);
            }

            return false;
        }

        return true;
    }
    // Add End 2018/06/25 QC#23726
    // 2018/08/02 S21_NA#26665 add start
    private static CPO_SRC_TPTMsg getCpoSrcTp(String cpoSrcTpCd, String glblCmpyCd) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, cpoSrcTpCd);
        tMsg = (CPO_SRC_TPTMsg)S21FastTBLAccessor.findByKey(tMsg);

        return tMsg;
    }
    // 2018/08/02 S21_NA#26665 add end
    
    // 2018/08/21 S21_NA#27817 Add Start
    // 2019/01/16 S21_NA#29535 Mod Start
    //private CTAC_PSNTMsg getRelnCtacPsnWithConfigInfo(ImptHdrBean hdrBean, String idocPoPtrnTpCd) {
    private CTAC_PSNTMsg getRelnCtacPsnWithConfigInfo(ImptHdrBean hdrBean, String idocPoPtrnTpCd, BigDecimal ctacPsnPk) {
    // 2019/01/16 S21_NA#29535 Mod End
        DS_IMPT_EXT_ATTRBTMsg attrbTMsg = hdrBean.getDsImptExtAttrbTMsg();

        Map<String, String> queryParam = new HashMap<String, String>(0);
        queryParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        queryParam.put("salesDate", hdrBean.getSlsDt());
        queryParam.put("dsCtacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        queryParam.put("dsCtacPntTpCd_M", DS_CTAC_PNT_TP.PHONE_MOBILE);
        // 2019/01/16 S21_NA#29535 Add Start
        if (!NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
        // 2019/01/16 S21_NA#29535 Add End
            queryParam.put("soldToCustLocCd", hdrBean.getSoldToCustLocCd());
        // 2019/01/16 S21_NA#29535 Add Start
        }
        // 2019/01/16 S21_NA#29535 Add End

        String ctacTpCd, idocPtnrCtacNm;
        if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
            ctacTpCd = ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_AP", hdrBean.getGlblCmpyCd());
            idocPtnrCtacNm = attrbTMsg.idocPtnrCtacNm_05.getValue();
        } else {
            ctacTpCd = ZYPCodeDataUtil.getVarCharConstValue("NWAB2210_CTAC_PSN_TP_CD_YP", hdrBean.getGlblCmpyCd());
            idocPtnrCtacNm = attrbTMsg.idocPtnrCtacNm_06.getValue();
        }

        if(CTAC_TP.ORDER_CONTACT.equals(ctacTpCd)){
            queryParam.put("ctacTpCd", CTAC_TP.DELIVERY_INSTALL);
        } else{
            queryParam.put("ctacTpCd", ctacTpCd);
        }
        // 2019/01/16 S21_NA#29535 Add Start
        if (NWXC220001Constant.EDI_IDOC_PO_PTNR_TP_AP.equals(idocPoPtrnTpCd)) {
            queryParam.put("ctacPsnPk", ctacPsnPk.toString());
        } else {
        // 2019/01/16 S21_NA#29535 Add End
            String[] splitNm = idocPtnrCtacNm.split(" ");
            if (splitNm.length > 2) {
                StringBuilder firstNm = new StringBuilder();
                firstNm.append(splitNm[0]).append(" ").append(splitNm[1]);

                queryParam.put("ctacPsnFirstNm", S21StringUtil.subStringByLength(firstNm.toString(), 0, 30));
                queryParam.put("ctacPsnLastNm", splitNm[2]);
            } else {
                if (splitNm.length > 0) {
                    queryParam.put("ctacPsnFirstNm", splitNm[0]);
                }
                if (splitNm.length > 1) {
                    queryParam.put("ctacPsnLastNm", splitNm[1]);
                }
            }
        // 2019/01/16 S21_NA#29535 Add Start
        }
        // 2019/01/16 S21_NA#29535 Add End

        S21SsmEZDResult result = NWZC226001Query.getInstance().getSsmEZDClient().queryObject("getContact", queryParam);
        if (result.isCodeNormal()) {
            return (CTAC_PSNTMsg) result.getResultObject();
        } else {
            queryParam.remove("soldToCustLocCd");
            queryParam.put("sellToCustCd", hdrBean.getSellToCustCd());

            result = NWZC226001Query.getInstance().getSsmEZDClient().queryObject("getContact", queryParam);
            if (result.isCodeNormal()) {
                return (CTAC_PSNTMsg) result.getResultObject();
            } else {
                return null;
            }
        }
    }
    // 2018/08/21 S21_NA#27817 Add End

    // 2018/12/14 S21_NA#29315 Add Start
    private String getShipToLocNum(ImptHdrBean hdrBean) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
        mapParam.put("shipToCustCd", hdrBean.getShipToCustCd());
        String shipToLocNum = DataCacheForSSM.getInstance().getShipToLocNum(mapParam);

        return shipToLocNum;
    }
    // 2018/12/14 S21_NA#29315 Add End
    // 2019/01/05 S21_NA#29579 Add Start
    private static String getAddlBaseMdseCd(String glblCmpyCd, NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg) {
        CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, svcAddlBasePrcPMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, svcAddlBasePrcPMsg.cpoDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
        tMsg = (CPO_DTLTMsg)S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return "";
        }
        return tMsg.mdseCd.getValue();
    }
    // 2019/01/05 S21_NA#29579 Add End
    // 2019/02/14 S21_NA#30340 Add Start
    private static String getConvertTime(DsImptOrdConfigBean configBean, String time) {
        // 2019/10/01 QC#53597 Mod Start
        //if(!ZYPCommonFunc.hasValue(time)){
        if(!ZYPCommonFunc.hasValue(time) || !ZYPCommonFunc.hasValue(configBean.shipToCtryCd) || !ZYPCommonFunc.hasValue(configBean.shipToPostCd)){
            // 2019/10/01 QC#53597 Mod End
            return null;
        }
        String convertTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC,//
                ZYPDateUtil.getSalesDate() + time, configBean.shipToCtryCd.getValue(), //
                configBean.shipToPostCd.getValue()).getDateTime().substring(TIME_SUBSTRING_FROM, TIME_SUBSTRING_TO);

        return convertTime;
    }
    // 2019/02/14 S21_NA#30340 Add End
    // 2019/03/18 S21_NA#30770 Add Start
    private void setRtrnWh4BaseComp(ONBATCH_TYPE onBatchType, DsImptRtnLineBean baseComponentline) {

        if (baseComponentline == null || (baseComponentline != null && ZYPCommonFunc.hasValue(baseComponentline.rtlWhCd))) {
            return;
        }

        String shipToPostCd;

        if (ZYPCommonFunc.hasValue(baseComponentline.shipToPostCd)) {
            shipToPostCd = baseComponentline.shipToPostCd.getValue();
        } else if (ZYPCommonFunc.hasValue(baseComponentline.dsImptOrdConfigBean.shipToPostCd)) {
            shipToPostCd = baseComponentline.dsImptOrdConfigBean.shipToPostCd.getValue();
        } else {
            shipToPostCd = baseComponentline.imptHdrBean.getShipToPostCd();
        }

        String dsRtrnRsnCd = null;
        if (ZYPCommonFunc.hasValue(baseComponentline.rtrnRsnCd)) {
            dsRtrnRsnCd = baseComponentline.rtrnRsnCd.getValue();
        } else {
            for (DsImptRtnLineBean srcRtnBean : baseComponentline.dsImptOrdConfigBean.dsImptRtnLineList) {
                if (ZYPCommonFunc.hasValue(srcRtnBean.rtrnRsnCd)) {
                    dsRtrnRsnCd = srcRtnBean.rtrnRsnCd.getValue();
                    break;
                }
            }
        }
        NWZC180001PMsg pMsg = callDefWhApi(onBatchType, baseComponentline, baseComponentline.mdseInfo.mdseCd.getValue(), shipToPostCd,//
                baseComponentline.ordQty.getValue(), dsRtrnRsnCd, baseComponentline.svcMachMstrPk.getValue());
        if (pMsg != null && ZYPCommonFunc.hasValue(pMsg.rtlWhCd)) {

            ZYPEZDItemValueSetter.setValue(baseComponentline.rtlWhCd, pMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(baseComponentline.rtlSwhCd, pMsg.rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(baseComponentline.invtyLocCd, pMsg.rtlWhCd.getValue() + pMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(baseComponentline.ordLineSrcCd, pMsg.ordLineSrcCd);
        
        }
        
    }
    // 2019/03/18 S21_NA#30770 Add End

    // Add Start 2019/11/05 QC#54511
    private String getRtlWhCd(List<Map<String, Object>> lineValSet, DsImptLineBean lineBean) {

        for (Map<String, Object> lineVal : lineValSet) {
            if (ZYPCommonFunc.hasValue(lineBean.dsOrdLineCatgCd) //
                    && lineBean.dsOrdLineCatgCd.getValue().equals((String) lineVal.get("DS_ORD_LINE_CATG_CD"))) {
                return (String) lineVal.get("RTL_WH_CD");
            }
        }

        return lineBean.rtlWhCd.getValue();
    }

    private String setInvtyLocCd(String rtlWhCd, String rtlSwhCd) {

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }

        if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
            return rtlWhCd;
        }

        StringBuilder invtyLocCd = new StringBuilder();
        invtyLocCd.append(rtlWhCd);
        invtyLocCd.append(rtlSwhCd);

        return invtyLocCd.toString();
    }
    // Add End 2019/11/05 QC#54511

    // 2020/01/06 QC#54219 Del Start
    //// 2019/12/05 QC#54219 Add Start
    ///**
    // * CheckMaintenanceOnly
    // * @param hdrBean
    // * @return boolean
    // */
    //private boolean checkMaintenanceOnly(ImptHdrBean hdrBean) {
    //
    //    writeStartLogLn("checkMaintenanceOnly", hdrBean);
    //
    //    try {
    //        // Check Exists Shell
    //        if (!(getCountDsImptSvcDtl(hdrBean) > 0)) {
    //            return false;
    //        }
    //
    //        // Check Exists Shell On Shpping Line
    //        if (getCountShellOnShippingLine(hdrBean) > 0) {
    //            return false;
    //        }
    //
    //        DsImptOrdErrBean errBean = new DsImptOrdErrBean(hdrBean, MSG_ID.NWZM2312E);
    //        hdrBean.dsImptOrdErrList.add(errBean);
    //
    //        return true;
    //    } finally {
    //
    //        writeEndLogLn("checkMaintenanceOnly", hdrBean);
    //    }
    //}
    //
    ///**
    // * getCountDsImptSvcDtl
    // * @param hdrBean
    // * @return boolean
    // */
    //private int getCountDsImptSvcDtl(ImptHdrBean hdrBean) {
    //    Map<String, Object> param = new HashMap<String, Object>();
    //    param.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
    //    param.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
    //
    //    return NWZC226001Query.getInstance().queryInt("getCountDsImptSvcDtl", param);
    //
    //}
    //
    ///**
    // * getCountShellOnShippingLine
    // * @param hdrBean
    // * @return boolean
    // */
    //private int getCountShellOnShippingLine(ImptHdrBean hdrBean) {
    //    Map<String, Object> param = new HashMap<String, Object>();
    //    param.put("glblCmpyCd", hdrBean.getGlblCmpyCd());
    //    param.put("dsImptOrdPk", hdrBean.getDsImptOrdPk());
    //    param.put("flgY", ZYPConstant.FLG_ON_Y);
    //
    //    return NWZC226001Query.getInstance().queryInt("getCountShellOnShippingLine", param);
    //
    //}
    //// 2019/12/05 QC#54219 Add End
    // 2020/01/06 QC#54219 Del End

    // 2019/12/17 S21_NA#54721 Add Start
    private boolean existsConfSlsCr(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        String trgtConfigCatgCd = cpoConfigPMsg.configCatgCd.getValue();
        String trgtPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
        for (int n = 0; n < pMsg.cpoSlsCr.getValidCount(); n++) {
            NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(n);
            if (S21StringUtil.isEquals(trgtConfigCatgCd, cpoSlsCrPMsg.configCatgCd.getValue()) //
                    && S21StringUtil.isEquals(trgtPosnNum, cpoSlsCrPMsg.dsOrdPosnNum.getValue())) {
                return true;
            }
        }
        return false;
    }
    /**
     * <pre>
     * If there are no Inbound Sales Credit date, set up from Outbound or Default Or Header.
     *  1) Set up from Same Ship to Outbound.
     *  2) Default Sales rep from Inbound Ship To.
     *  3) Headr.
     * @param configBean Config Bean
     * @param hdrBean Header Bean
     * @param pMsg CPO Update API Parameter
     * @return List of Sales Credit 
     * </pre>
     */
    List<NWZC150001_cpoSlsCrPMsg> getConfigSlsCrFromSameShipTo(NWZC150001_cpoConfigPMsg cpoConfigPMsg, ImptHdrBean hdrBean, NWZC150001PMsg pMsg) {

        List<NWZC150001_cpoSlsCrPMsg> inbndSlsCrList = new ArrayList<NWZC150001_cpoSlsCrPMsg>(0);

        // 1) From Same Ship To Outbound
        String trgtShipToCustCd = cpoConfigPMsg.shipToCustCd.getValue();
        String targetConfigCatgCd = CONFIG_CATG.OUTBOUND;
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, cpoConfigPMsg.configCatgCd.getValue())) {
            targetConfigCatgCd = CONFIG_CATG.INBOUND;
        }
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg targetConfigPMsg = pMsg.cpoConfig.no(i);
            if (S21StringUtil.isEquals(targetConfigCatgCd, targetConfigPMsg.configCatgCd.getValue()) //
                    && S21StringUtil.isEquals(trgtShipToCustCd, targetConfigPMsg.shipToCustCd.getValue())) {
                String posnNum = targetConfigPMsg.dsOrdPosnNum.getValue();
                for (int n = 0; n < pMsg.cpoSlsCr.getValidCount(); n++) {
                    NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(n);
                    if (S21StringUtil.isEquals(targetConfigCatgCd, cpoSlsCrPMsg.configCatgCd.getValue()) //
                            && S21StringUtil.isEquals(posnNum, cpoSlsCrPMsg.dsOrdPosnNum.getValue())) {
                        NWZC150001_cpoSlsCrPMsg newCpoSlsCrPMsg = new NWZC150001_cpoSlsCrPMsg();
                        EZDMsg.copy(cpoSlsCrPMsg, null, newCpoSlsCrPMsg, null);
                        ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.configCatgCd, cpoConfigPMsg.configCatgCd);
                        ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.dsOrdPosnNum, cpoConfigPMsg.dsOrdPosnNum);
                        inbndSlsCrList.add(newCpoSlsCrPMsg);
                    }
                }
                break;
            }
        }
        // 2) Default Sales rep from Inbound Ship To.
        if (inbndSlsCrList.isEmpty()) {
            // 2020/04/27 QC#56638 Mod Start
            NMZC260001PMsg defSlsRepPMsg = callDefSlsReqApi(hdrBean.getOnbtchType(), hdrBean.getGlblCmpyCd(), cpoConfigPMsg, hdrBean);
            for (int i = 0; i < defSlsRepPMsg.defSlsRepList.getValidCount(); i++) {
                NMZC260001_defSlsRepListPMsg dsrlPMsg = defSlsRepPMsg.defSlsRepList.no(i);

                if (isWriter(pMsg.glblCmpyCd.getValue(), dsrlPMsg.lineBizRoleTpCd.getValue()) //
                        && hdrBean.getLineBizTpCd().equals(dsrlPMsg.lineBizTpCd.getValue())) {
                    NWZC150001_cpoSlsCrPMsg newCpoSlsCrPMsg = new NWZC150001_cpoSlsCrPMsg();

                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.configCatgCd, cpoConfigPMsg.configCatgCd);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.dsOrdPosnNum, cpoConfigPMsg.dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.slsRepCd, dsrlPMsg.tocCd);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.slsRepRoleTpCd //
                            , NWXC150001DsCheck.getLineBizRoleTpCdForWriter(hdrBean.getGlblCmpyCd(), hdrBean.getSlsDt(), hdrBean.getDsOrdTpCd()));
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.slsRepCrPct, BigDecimal.valueOf(100));
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
                    inbndSlsCrList.add(newCpoSlsCrPMsg);
                    break;
                }
            }
        }
        // 3) Headr.
        if (inbndSlsCrList.isEmpty()) {
            for (int n = 0; n < pMsg.cpoSlsCr.getValidCount(); n++) {
                NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(n);
                if (!ZYPCommonFunc.hasValue(cpoSlsCrPMsg.dsOrdPosnNum)) {
                    NWZC150001_cpoSlsCrPMsg newCpoSlsCrPMsg = new NWZC150001_cpoSlsCrPMsg();
                    EZDMsg.copy(cpoSlsCrPMsg, null, newCpoSlsCrPMsg, null);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.configCatgCd, cpoConfigPMsg.configCatgCd);
                    ZYPEZDItemValueSetter.setValue(newCpoSlsCrPMsg.dsOrdPosnNum, cpoConfigPMsg.dsOrdPosnNum);
                    inbndSlsCrList.add(newCpoSlsCrPMsg);
                }
            }
        }
        return inbndSlsCrList;
    }

    private NMZC260001PMsg callDefSlsReqApi(ONBATCH_TYPE onBatchType, String glblCmpyCd, NWZC150001_cpoConfigPMsg cpoConfigPMsg, ImptHdrBean hdrBean) {

        NMZC260001PMsg defSlsRepPMsg = new NMZC260001PMsg();

        ZYPEZDItemValueSetter.setValue(defSlsRepPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defSlsRepPMsg.shipToCustCd, cpoConfigPMsg.shipToCustCd.getValue());
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(defSlsRepPMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(defSlsRepPMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(defSlsRepPMsg.billToCustCd, cpoConfigPMsg.billToCustCd);

        NMZC260001 drApi = new NMZC260001();
        drApi.execute(defSlsRepPMsg, onBatchType);
        return defSlsRepPMsg;
    }

    private static boolean isWriter(String glblCmpyCd, String lineBizRoleTpCd) {

        if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) //
                || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) //
                || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) //
                || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTpCd)) {

            LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(lineBizRoleTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(lineBizRoleTpTMsg.lineBizRoleTpCd, lineBizRoleTpCd);

            lineBizRoleTpTMsg = (LINE_BIZ_ROLE_TPTMsg) S21CacheTBLAccessor.findByKey(lineBizRoleTpTMsg);
            if (lineBizRoleTpTMsg == null) {
                return false;
            }
            return ZYPConstant.FLG_ON_Y.equals(lineBizRoleTpTMsg.primRepRoleFlg.getValue());
        }
        return false;
    }
    // 2019/12/17 S21_NA#54721 Add End
}
