package canon.batch.server;

import java.util.Vector;

import oracle.apps.fnd.cp.request.CpContext;
import oracle.apps.fnd.cp.request.JavaConcurrentProgram;
import oracle.apps.fnd.cp.request.ReqCompletion;
import oracle.apps.fnd.cp.request.ReqDetails;
import oracle.apps.fnd.cp.request.ReqGeneralInfo;
import oracle.apps.fnd.cp.request.ReqParameterInfo;
import oracle.apps.fnd.cp.request.ReqUserInfo;
import oracle.apps.fnd.util.ParameterList;

public class AutosysConcurrentProgram {
	static int requestId;
	static {
		java.util.Random rand = new java.util.Random();
		requestId = rand.nextInt(9000) + 1000;
	}

	public static CpContext cpContext() {
		String params=System.getenv("VAR_INPUT_PARAMS");
		System.out.println("VAR_INPUT_PARAMS is "+params);
		return cpContext(0,params);
	}
	
	public static CpContext cpContext(final String parameters) {
		return cpContext(0,parameters);
	}
	
	static CpContext cpContext(final int userId, final String parameters) {
		CpContext context = new CpContext() {

			public LineWriter getOutFile() {
				return new LineWriter() {

					public void writeln(Object o) {
						System.out.println(o);
					}
				};
			}

			public ReqCompletion getReqCompletion() {
				return new ReqCompletion() {

					public void setCompletion(int i, String s) {
					}
				};
			}

			public ReqDetails getReqDetails() {
				return new ReqDetails() {
					public ReqUserInfo getUserInfo() {
						return new ReqUserInfo() {
							public int getUserId() {
								return userId;
							}
						};
					}

					public int getRequestId() {
						return requestId;
					}

					public ReqGeneralInfo getGeneralInfo() {
						return new ReqGeneralInfo() {
							public String getProgName() {
								// throw new
								// UnsupportedOperationException("Not supported yet.");
								return "N/A";
							}
						};
					}

					public ReqParameterInfo getParaInfo() {
						// throw new
						// UnsupportedOperationException("Not supported yet.");
						return new ReqParameterInfo() {
							public Vector getList() {
								return new Vector();
							}
						};
					}
				};
			}

			public ParameterList getParameterList() {
				return new ParameterList(parameters);
			}
		};
		return context;

	}

}
