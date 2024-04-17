package oracle.apps.e580.itt.workbench;

/**
 * S21 API Call Back
 */
public interface CanonE580ITTWorkbenchCallback<T> {
	public void init(T... t);
	public void onSuccess(T... t);
	public void onError(String error);
	public void onFinally();
}
