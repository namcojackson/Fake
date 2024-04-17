package com.canon.cusa.s21.common.NWX.NWXC412001;

import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.DEAL_CONFIG_RESPONSE_NORMAL;

import javax.xml.bind.JAXBElement;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.usa.s21.integration.service.dealconfig.api.element.one.ObjectFactory;
import com.canon.usa.s21.integration.service.dealconfig.api.element.one.RejectOrder;
import com.canon.usa.s21.integration.service.dealconfig.api.element.one.RejectOrderResponse;
import com.canon.usa.s21.integration.service.dealconfig.api.element.three.RejectOrderRequest;
import com.canon.usa.s21.integration.service.dealconfig.api.wrapper.DealConfigRejectOrderApiServiceProxy;

/**
 * <pre>
 * Deal Config web service
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/25   Fujitsu         M.Yamada        Create          QC#18548(L3)
 * </pre>
 */
public final class NWXC412001DcWebService {

    /** message ID */
    private enum MESSAGE_ID {
        /** Deal Config web service has ended abnormally. Deal Number : @ */
        NWAM4124E,
    }

    /**
     * reject
     * @param ordSrcRefNum String - mandatory
     * @param userId String - mandatory
     * @return message id
     */
    public static String reject(String ordSrcRefNum, String userId) {
        try {

            // for debug
            S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
            if (userProfSvc != null) {
                if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("SKIP_DC_WEB_SERVICE", userProfSvc.getGlobalCompanyCode()), ZYPConstant.FLG_ON_Y)) {

                    return "";
                }
            }

            DealConfigRejectOrderApiServiceProxy proxy = new DealConfigRejectOrderApiServiceProxy();

            RejectOrderResponse response = rejectOrderRequest(proxy, ordSrcRefNum, userId);

            S21LoggerFactory.getLogger(NWXC412001DcWebService.class).info("Reject Order Status Response: " + response.getRejectOrderResult().getValue());
            JAXBElement<String> errorCode = response.getRejectOrderResult().getValue().getErrorCode();

            if (!DEAL_CONFIG_RESPONSE_NORMAL.equals(errorCode.getValue())) {
                throw new Throwable(response.getRejectOrderResult().getValue().getError().getValue());
            }

        } catch (Throwable e) {

            e.printStackTrace();
            return MESSAGE_ID.NWAM4124E.name();
        }
        return "";

    }

    private static RejectOrderResponse rejectOrderRequest(DealConfigRejectOrderApiServiceProxy proxy, String ordSrcRefNum, String userId) throws Throwable {
        ObjectFactory of = new ObjectFactory();

        RejectOrderRequest roRequest = new RejectOrderRequest();
        roRequest.setOrderReferenceNumber(ordSrcRefNum);
        roRequest.setUserName(userId);

        JAXBElement<RejectOrderRequest> ra = of.createRejectOrderRequest(roRequest);
        RejectOrder ro = new RejectOrder();
        ro.setRequest(ra);

        RejectOrderResponse response = null;

        response = proxy.rejectOrder(ro);

        return response;
    }

}
