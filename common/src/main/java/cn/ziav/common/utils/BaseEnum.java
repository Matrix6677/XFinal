package cn.ziav.common.utils;

import cn.ziav.common.exception.ManagedException;
import cn.ziav.common.exception.ResultCode;

public interface BaseEnum {

  int getId();

  static <T extends Enum<T>> T getById(Class<T> enumClz, int value) {
    if (enumClz.isAssignableFrom(BaseEnum.class)) {
      throw new IllegalArgumentException("illegal BaseEnum type");
    }
    T[] enums = enumClz.getEnumConstants();
    for (T t : enums) {
      BaseEnum baseEnum = (BaseEnum) t;
      if (baseEnum.getId() == value) {
        return (T) baseEnum;
      }
    }
    throw new ManagedException(ResultCode.NO_ENUM);
  }
}
