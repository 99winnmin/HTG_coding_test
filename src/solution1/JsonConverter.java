package solution1;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * JsonConverter
 * Java 객체를 JSON 문자열 표현으로 변환하는 정적 메소드를 제공합니다.
 * 리플렉션을 사용하여 객체의 필드에 동적으로 접근합니다.
 */
public class JsonConverter {
	/**
	 * 객체를 JSON 문자열로 변환하여 StringBuilder 에 결과를 추가합니다.
	 *
	 * @param obj    JSON 으로 변환될 객체
	 * @param sb     JSON 문자열이 추가될 StringBuilder
	 * @param indent 현재 들여쓰기 레벨
	 * @return 객체의 JSON 문자열 또는 객체가 null 인 경우 빈 문자열을 반환
	 */
	public static String toJson(Object obj, StringBuilder sb, int indent) {
		if (obj == null) {
			sb.append("null");
			return "";
		}

		if (obj instanceof Collection) {
			return toJsonCollection((Collection<?>) obj, sb, indent);
		} else {
			String indentString = "  ".repeat(indent);
			sb.append(indentString);
			return toJsonSingleObject(obj, sb, indent);
		}
	}

	/**
	 * 단일 객체를 JSON 형식으로 변환하여 StringBuilder 에 결과를 추가합니다.
	 * 리플렉션을 이용하여 객체의 필드에 접근하고, 이를 JSON 형식으로 변환합니다.
	 *
	 * @param obj    필드가 JSON 으로 변환될 객체
	 * @param sb     JSON 표현이 추가될 StringBuilder
	 * @param indent 현재 들여쓰기 레벨
	 * @return 생성된 JSON 문자열을 반환
	 */
	private static String toJsonSingleObject(Object obj, StringBuilder sb, int indent) {
		String indentString = "  ".repeat(indent);
		sb.append("{\n");

		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			// 필드명을 키로 사용
			sb.append(indentString).append("  ").append("\"").append(fields[i].getName()).append("\": ");
			// value 붙이기, Collection 일 경우 재귀 호출
			try {
				Object value = fields[i].get(obj);
				if (value instanceof String) {
					sb.append("\"").append(value).append("\"");
				} else if (value instanceof Collection) {
					toJson(value, sb, indent + 1);
				} else {
					sb.append(value);
				}
			} catch (IllegalAccessException e) {
				sb.append("null");
			}
			// 마지막 필드가 아니면 콤마를 붙임
			if (i < fields.length - 1) sb.append(",\n");
		}
		sb.append("\n").append(indentString).append("}");
		return sb.toString();
	}

	/**
	 * Collection 타입의 객체를 JSON 배열 형식으로 변환하여 StringBuilder 에 추가합니다.
	 * 각 요소를 재귀적으로 처리하여 JSON 배열을 구성합니다.
	 *
	 * @param list    JSON 으로 변환될 Collection
	 * @param sb      결과 JSON 문자열이 추가될 StringBuilder
	 * @param indent  현재 들여쓰기 레벨
	 * @return 생성된 JSON 배열 문자열을 반환
	 */
	private static String toJsonCollection(Collection<?> list, StringBuilder sb, int indent) {
		if (list.isEmpty()) return sb.append("[]").toString();

		String indentString = "  ".repeat(indent);
		sb.append("[\n");
		boolean first = true;
		for (Object item : list) {
			if (!first) sb.append(",\n");
			toJson(item, sb, indent + 1);
			first = false;
		}
		sb.append("\n").append(indentString).append("]");
		return sb.toString();
	}

}
