package org.gsm.oneapi.server.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.gsm.oneapi.server.OneAPIServlet;
import org.gsm.oneapi.server.ValidationRule;

/**
 * Servlet implementing the OneAPI function for cancelling SMS message receipt subscriptions
 */
public class CancelSMSReceiptReportSubscriptionServlet extends OneAPIServlet {
	private static final long serialVersionUID = 4877311182916937332L;

	static Logger logger=Logger.getLogger(CancelSMSReceiptReportSubscriptionServlet.class);

	public void init() throws ServletException {
		logger.debug("CancelSMSReceiptReportSubscriptionServlet initialised");
    }
	
	private final String[] validationRules={"1", "smsmessaging", "inbound", "subscriptions", "*"};
	
	public void doDelete(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{		
		dumpRequestDetails(request, logger);

		String[] requestParts=getRequestParts(request);
		
		if (validateRequest(request, response, requestParts, validationRules)) {
			
			/*
			 * Decode the service parameters - in this case it is an HTTP GET request 
			 */			
			String subscriptionId=requestParts[4];
			
			logger.debug("subscriptionId = "+subscriptionId);
			ValidationRule[] rules={
					new ValidationRule(ValidationRule.VALIDATION_TYPE_MANDATORY, "subscriptionId", subscriptionId),
			};

			if (checkRequestParameters(response, rules)) {			
				response.setStatus(NOCONTENT);
			}			
		}
	}


}
