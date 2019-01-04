package cn.ziav.common.serialize;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.reflect.Type;

/**
 * 长整型转字符串
 *
 * @author Ziav
 */
public class LongToStringCodec extends LongCodec {

  public static LongToStringCodec instance = new LongToStringCodec();

  @Override
  public void write(
      JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
    SerializeWriter out = serializer.out;

    if (object == null) {
      out.writeNull(SerializerFeature.WriteNullNumberAsZero);
    } else {
      Long value = ((Long) object);
      out.write(value.toString());
    }
  }
}
