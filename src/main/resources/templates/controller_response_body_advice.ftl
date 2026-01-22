package ${packageName};

import ${config.packageName}.common.Msg;
import cn.hutool.json.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author solom
 * @ClassName ControllerResponseBodyAdvice
 * @Description
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Slf4j
@RestControllerAdvice
public class ControllerResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter methodParameter,
      Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {
    if(o instanceof Msg){
      Msg msg = (Msg) o;
      if(msg.getData() == null){
        msg.setData(new JSONObject());
      }
      return msg;
    }
    return o;
  }
}