package com.bankApp.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bankApp.model.Account;
import com.bankApp.model.Customer;

public class BaseController {

	   public static Map<String, Object> createResponse(Boolean success, String message){
	        Map<String, Object> result = new HashMap<>();
	        result.put("success", success);
	        if (message != null){
	            if (success){
	                result.put("message", message);
	            } else {
	                result.put("error", message);
	            }
	        }

	        return result;
	    }
	   
		public static Map<String, Object> createResponse(boolean success, Customer custCopy) {
			   Map<String, Object> result = new HashMap<>();
			   Map<String,Object> resultRow = new LinkedHashMap<>();
 		        result.put("success", success);
 		     
 		        if (custCopy != null){
 		        	resultRow.put("CustomerName",custCopy.getName());
 		        	resultRow.put("CustomerID",custCopy.getCId());
 		        	resultRow.put("CustomerStatus",custCopy.getStatus().name());
 		        	
 		        	for(Account acc : custCopy.getAccounts())
 		        	{
 		        	Map<String,Object>	accountRow = new HashMap<>();
 		        		accountRow.put("Balance", acc.getBalance());
 		        		accountRow.put("Account Status", acc.getStatus().name());
 		        		resultRow.put(acc.getType().name()+" ACCOUNT", accountRow);
 		        		 
 		        	
 		        	
 		        	
 		        	}
 		        	result.put("message", resultRow);
 		        }

 		        return result;
		        	}

}
