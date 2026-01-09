package ${packageName};

import ${config.packageName}.common.Common;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author solom
 * @ClassName GsonRedisSerializer
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public class GsonRedisSerializer<T> implements RedisSerializer<T> {

  Gson gson = null;

  public GsonRedisSerializer() {
    this.gson = Common.lowerCaseWithUnderscoresGson;
  }

  @Override
  public byte[] serialize(T o) throws SerializationException {
    if (o instanceof List) {
      List<Map<String, Object>> arr = Lists.newArrayList();
      ((List<?>) o).stream().forEach(item -> {
        String json = gson.toJson(item);
        Type targetType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> m = gson.fromJson(json, targetType);
        m.put("@class", item.getClass().getName());
        arr.add(m);
      });
      return gson.toJson(arr).getBytes();
    } else {
      if (o instanceof String || o instanceof Integer || o instanceof Long || o instanceof Double
          || o instanceof Float || o instanceof Boolean || o instanceof Byte || o instanceof Short
          || o instanceof Character) {
        Map<String, Object> m = Maps.newHashMap();
        m.put("@class", o.getClass().getName());
        m.put("value", o);
        return gson.toJson(m).getBytes();
      } else {
        String json = gson.toJson(o);
        Type targetType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> m = gson.fromJson(json, targetType);
        m.put("@class", o.getClass().getName());
        return gson.toJson(m).getBytes();
      }
    }
  }

  @Override
  public T deserialize(byte[] bytes) throws SerializationException {
    try {
      String res = new String(bytes, "UTF-8");
      if (res.startsWith("[")) { // list
        List<Map<String, Object>> arr = gson.fromJson(res,
            new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        Class<?> clazz = Class.forName((String) arr.get(0).get("@class"));
        Type targetType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(res, targetType);
      } else {
        Map<String, Object> m = gson.fromJson(res,
            new TypeToken<Map<String, Object>>() {
            }.getType());
        Class<?> clazz = Class.forName((String) m.get("@class"));
        if (clazz.getName().startsWith("java.lang")) { //基础对象
          return (T) gson.fromJson(m.get("value").toString(), clazz);
        } else {
          return (T) gson.fromJson(res, clazz);
        }
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}