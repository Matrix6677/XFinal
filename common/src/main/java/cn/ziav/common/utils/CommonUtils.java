package cn.ziav.common.utils;

/**
 * 通用工具类
 *
 * @author Ziav
 */
public interface CommonUtils {

  /**
   * 转换为下划线
   *
   * @param camelCaseName
   * @return
   */
  static String underscoreName(String camelCaseName) {
    StringBuilder result = new StringBuilder();
    if (camelCaseName != null && camelCaseName.length() > 0) {
      result.append(camelCaseName.substring(0, 1).toLowerCase());
      for (int i = 1; i < camelCaseName.length(); i++) {
        char ch = camelCaseName.charAt(i);
        if (Character.isUpperCase(ch)) {
          result.append("_");
          result.append(Character.toLowerCase(ch));
        } else {
          result.append(ch);
        }
      }
    }
    return result.toString();
  }
}
