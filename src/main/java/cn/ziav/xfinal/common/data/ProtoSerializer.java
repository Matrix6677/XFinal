package cn.ziav.xfinal.common.data;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtoSerializer implements RedisSerializer<Object> {
  static final byte[] EMPTY_ARRAY = new byte[0];

  static final boolean isEmpty(byte[] data) {
    return (data == null || data.length == 0);
  }

  private final Schema<ProtoWrapper> schema;
  private final ProtoWrapper wrapper;
  private final LinkedBuffer buffer;

  public ProtoSerializer() {
    this.wrapper = new ProtoWrapper();
    this.schema = RuntimeSchema.getSchema(ProtoWrapper.class);
    this.buffer = LinkedBuffer.allocate();
  }

  @Override
  public byte[] serialize(Object t) throws SerializationException {
    if (t == null) {
      return EMPTY_ARRAY;
    }
    wrapper.data = t;

    try {
      return ProtostuffIOUtil.toByteArray(wrapper, schema, buffer);
    } finally {
      buffer.clear();
    }
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    if (isEmpty(bytes)) {
      return null;
    }

    ProtoWrapper newMessage = schema.newMessage();
    ProtostuffIOUtil.mergeFrom(bytes, newMessage, schema);
    return newMessage.data;
  }

}
