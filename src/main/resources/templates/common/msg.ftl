package ${packageName};

import lombok.Data;

/**
 * @author solom
 * @classname Msg.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Data
public class Msg<T> {

  private int code;
  private long count;
  /*提示信息 */
  private String msg;
  /*具体内容*/
  private T data;
}