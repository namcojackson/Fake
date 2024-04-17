package business.servlet.ZZZL0010.common;

import com.canon.cusa.s21.framework.online.router.S21ApplicationDef;
import com.canon.cusa.s21.framework.online.router.S21RouteInfo;
import com.canon.cusa.s21.framework.online.router.S21RouteManager;

/**
 * 経路情報管理クラス。
 * @author q02673
 */
public class XmlRouteManager extends S21RouteManager {

    /** 画面遷移情報 */
    private static XmlApplicationDefHolder appList;

    static {
        appList = new XmlApplicationDefHolder();
        appList.init();
    };

    /**
     * コンストラクタ。
     * @param contextRoot コンテキストルート
     */
    public XmlRouteManager(String contextRoot) {
        super(contextRoot);
    }

    /**
     * 画面遷移情報を返します。
     * @param applicationId 遷移先アプリケーションID
     * @return 画面遷移情報
     */
    public S21RouteInfo getTransitionInfo(String applicationId) {
        S21ApplicationDef appDef = appList.getApplicationDefById(applicationId);
        if (appDef != null) {
            return createTransitionInfo(appDef);
        } else {
            return null;
        }
    }

    /**
     * 遷移先情報を取得して返します。
     * @param businessId 業務ID
     * @param contextRoot 現在のコンテキストルート
     * @return 遷移先情報
     */
    public S21RouteInfo getTransitionInfo(String businessId, String contextRoot) {

        if (businessId == null) {
            return null;
        }

        // １．業務IDによる検索
        // 自己ContextRootから検索
        S21ApplicationDef currentAppDef = appList.getApplicationDefByContextRoot(contextRoot);
        if (currentAppDef != null) {
            if (currentAppDef.getBusiness().containsKey(businessId)) {
                return createTransitionInfo(currentAppDef);
            }
        }

        // 全ContextRootから検索
        S21ApplicationDef appDef = appList.getApplicationDefByBusinessId(businessId);
        if (appDef != null) {
            return createTransitionInfo(appDef);
        }

        // ２．サブシステムIDによる検索
        String subsystemId = businessId;
        if (subsystemId.length() > 3) {
            subsystemId = subsystemId.substring(0, 3);

            // 自己ContextRootから検索
            if (currentAppDef != null) {
                if (currentAppDef.getSubsystem().containsKey(subsystemId)) {
                    return createTransitionInfo(currentAppDef);
                }
            }

            // 全ContextRootから検索
            appDef = appList.getApplicationDefBySubsystemId(subsystemId);
            if (appDef != null) {
                return createTransitionInfo(appDef);
            }
        }

        // ３．該当なければ自己ContextRootへの遷移
        return createTransitionInfo(new S21ApplicationDef().new CurrentApplicationDef(contextRoot));
    }
}
