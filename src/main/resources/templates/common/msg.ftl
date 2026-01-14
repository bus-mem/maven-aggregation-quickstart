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

  private Integer code;
  private String msg;
  private T data;

  /**
   * 成功
   */
  public static final int SUCCESS = 200;

  /**
   * 失败
   */
  public static final int FAIL = 500;

  public static Msg<Void> success() {
    Msg<Void> result = new Msg<>();
    result.code = SUCCESS;
    result.msg = "ok";
    return result;
  }

  public static <T> Msg<T> success(int code, String msg, T data) {
    Msg<T> result = new Msg<>();
    result.code = code;
    result.msg = msg;
    result.data = data;
    return result;
  }

  public static <T> Msg<T> success(int code, String msg) {
    return success(code, msg, null);
  }

  public static <T> Msg<T> success(T data) {
    return success(SUCCESS, "ok", data);
  }

  public static <T> Msg<T> failure(int code, String msg) {
    Msg<T> result = new Msg<>();
    result.code = code;
    result.msg = msg;
    result.data = null;
    return result;
  }

  public static <T> Msg<T> failure(String msg) {
    return failure(FAIL, msg);
  }

  public static Msg ok(String msg) {
    return success(SUCCESS, msg);
  }

  public static <T> Msg<T> ok(String msg, T data) {
    return success(SUCCESS, msg, data);
  }

  public static <T> Msg<T> ok(T data) {
    return success(SUCCESS, "ok", data);
  }

  public static <T> Msg<T> fail(String msg) {
    return success(FAIL, msg);
  }

  public static <T> Msg<T> fail(int code, String msg) {
    return success(code, msg);
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return Common.identityGson.toJson(this);
  }
}