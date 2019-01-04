package cn.ziav.common.serialize;

import com.alibaba.fastjson.JSONObject;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/** @author Ziav */
@MappedTypes({Object[].class, Object.class, List.class, Set.class, Map.class})
@MappedJdbcTypes({JdbcType.VARCHAR, JdbcType.LONGNVARCHAR, JdbcType.CHAR})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

  private final Class<T> typeClz;

  public JsonTypeHandler(Class<T> typeClz) {
    if (typeClz == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.typeClz = typeClz;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, JSONObject.toJSONString(parameter));
  }

  @Override
  public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String jsonStr = rs.getString(columnName);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, typeClz);
    }
    return defaultResult();
  }

  @Override
  public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String jsonStr = rs.getString(columnIndex);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, typeClz);
    }
    return defaultResult();
  }

  @Override
  public T getResult(ResultSet rs, String columnName) throws SQLException {
    T result;
    try {
      result = getNullableResult(rs, columnName);
    } catch (Exception e) {
      throw new ResultMapException(
          "Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
    }
    return result;
  }

  @Override
  public T getResult(ResultSet rs, int columnIndex) throws SQLException {
    T result;
    try {
      result = getNullableResult(rs, columnIndex);
    } catch (Exception e) {
      throw new ResultMapException(
          "Error attempting to get column #" + columnIndex + " from result set.  Cause: " + e, e);
    }
    return result;
  }

  @Override
  public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
    T result;
    try {
      result = getNullableResult(cs, columnIndex);
    } catch (Exception e) {
      throw new ResultMapException(
          "Error attempting to get column #"
              + columnIndex
              + " from callable statement.  Cause: "
              + e,
          e);
    }
    return result;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private T defaultResult() {
    if (List.class.isAssignableFrom(typeClz)) {
      return (T) new ArrayList<>();
    }
    if (Set.class.isAssignableFrom(typeClz)) {
      return (T) new HashSet<>();
    }
    if (Map.class.isAssignableFrom(typeClz)) {
      return (T) new HashMap<>();
    }
    if (Object[].class.isAssignableFrom(typeClz)) {
      return (T) new Object[] {};
    }
    return null;
  }

  @Override
  public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String jsonStr = cs.getString(columnIndex);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, typeClz);
    }
    return defaultResult();
  }
}
