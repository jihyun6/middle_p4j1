package kr.or.ddit.filter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {
    private Set<String> badWords;

    public XssRequestWrapper(HttpServletRequest request, Set<String> badWords) {
        super(request);
        this.badWords = badWords;
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
    	
		System.out.println("getParameterMap");

		Map<String, String[]> parameterMap = super.getParameterMap();
		Map<String, String[]> filterMap = new HashMap();
		Iterator<String> it = parameterMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			
			String[] array =  getParameterValues(key);
			filterMap.put(key, array);
		}
        return filterMap;
    }

    @Override
    public String getParameter(String name) {
    	
    	System.out.println("getParameter" + name);
    	
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return filterBadWords(value);
    }

    @Override
    public String[] getParameterValues(String name) {
    	
    	System.out.println("getParameterValues" + name);
    	
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        
        String[] filteredValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            filteredValues[i] = filterBadWords(values[i]);
        }
        return filteredValues;
    }

    private String filterBadWords(String value) {
    	
    	System.out.println("filterBadWords" + value);
    	
        String filteredValue = value;
        for (String badWord : badWords) {
            if (filteredValue.contains(badWord)) {
                filteredValue = filteredValue.replace(badWord, "*".repeat(badWord.length()));
            }
        }
        return filteredValue;
    }
}