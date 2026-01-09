package ${packageName}.entity.convert;

import ${packageName}.common.BeanConverter;
import java.io.Serializable;

/**
 * @author solom
 * @classname Convert.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public class Convert implements Serializable {

  /**
   * 获取自动转换后的JavaBean对象
   *
   * @param clazz
   * @param <T>
   * @return
   */
  public <T> T convert(Class<T> clazz) {
    return BeanConverter.convert(clazz, this);
  }
}