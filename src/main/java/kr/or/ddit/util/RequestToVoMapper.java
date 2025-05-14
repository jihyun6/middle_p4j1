package kr.or.ddit.util;

import java.lang.reflect.Field;

import jakarta.servlet.http.HttpServletRequest;

public class RequestToVoMapper {

	public static <T> T mapRequestToVo(HttpServletRequest request, Class<T> clazz) {
		try {
			// VO 객체 생성
			T instance = clazz.getDeclaredConstructor().newInstance();
			
			// VO의 모든 필드를 가져옴
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true); // private 필드 접근 허용

				// HttpServletRequest에서 VO 필드와 동일한 이름의 파라미터 값 가져오기
				String paramValue = request.getParameter(field.getName());
				if (paramValue != null) {
					// VO 필드 타입에 맞게 변환 후 값 설정
					Object value = convertToFieldType(field.getType(), paramValue);
					field.set(instance, value);
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("VO 매 핑 중 에러 발생", e);
		}
	}

	private static Object convertToFieldType(Class<?> fieldType, String value) {
		
		
		
	    if (value == null || value.equals("")) {
	        // 기본값 설정
	        if (fieldType == int.class || fieldType == Integer.class) {
	            return 0;
	        }
	        if (fieldType == double.class || fieldType == Double.class) {
	            return 0.0;
	        }
	        if (fieldType == boolean.class || fieldType == Boolean.class) {
	            return false;
	        }
	        return null; // 기본값이 없는 경우
	    }

	    // 기존 변환 로직
	    if (fieldType == String.class) {
	    	if (Utils.isNullOrEmpty(value)) {
	    		return null;
	    	}
	    	
	        return value;
	    }
	    if (fieldType == int.class || fieldType == Integer.class) {
	        return Integer.parseInt(value);
	    }
	    if (fieldType == double.class || fieldType == Double.class) {
	        return Double.parseDouble(value);
	    }
	    if (fieldType == boolean.class || fieldType == Boolean.class) {
	        return Boolean.parseBoolean(value);
	    }

	    throw new IllegalArgumentException("지원하지 않는 필드 타입입니다: " + fieldType.getName());
	}
}