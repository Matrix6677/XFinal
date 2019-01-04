package cn.ziav.common.serialize;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/** @author Ziav */
public class LongListTypHandler extends BaseTypeHandler<List<Long>> {

  private static final Type type = new TypeReference<List<Long>>() {}.getType();

  @Override
  public void setNonNullParameter(
      PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, JSONObject.toJSONString(parameter));
  }

  @Override
  public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String jsonStr = rs.getString(columnName);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, type);
    }
    return new ArrayList<>();
  }

  @Override
  public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String jsonStr = rs.getString(columnIndex);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, type);
    }
    return new ArrayList<>();
  }

  @Override
  public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
    List<Long> result;
    try {
      result = getNullableResult(rs, columnName);
    } catch (Exception e) {
      throw new ResultMapException(
          "Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
    }
    return result;
  }

  @Override
  public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
    List<Long> result;
    try {
      result = getNullableResult(rs, columnIndex);
    } catch (Exception e) {
      throw new ResultMapException(
          "Error attempting to get column #" + columnIndex + " from result set.  Cause: " + e, e);
    }
    return result;
  }

  @Override
  public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
    List<Long> result;
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

  @Override
  public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String jsonStr = cs.getString(columnIndex);
    if (jsonStr != null) {
      return JSONObject.parseObject(jsonStr, type);
    }
    return new ArrayList<>();
  }
}
