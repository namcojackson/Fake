package com.canon.cusa.s21.framework.generictable.fw;

public class S21NEContextHolder {

    /**
     * Context container.
     */
    private static ThreadLocal<S21NEContext> contextHolder = new ThreadLocal<S21NEContext>();

    /**
     * Returns context value.
     * @return S21NEContext stored in the holder
     */
    public static S21NEContext getContext() {
        return (S21NEContext) contextHolder.get();
    }

    /**
     * Sets context value.
     * @param context S21NEContext to be stored
     */
    public static void setContext(S21NEContext context) {
        contextHolder.set(context);
    }

    /**
     * Clear security context value.
     */
    public static void clearContext() {
        contextHolder.remove();
    }
}
