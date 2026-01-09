package ${packageName}.modelmapper.jdk8;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

/**
 * @author solom
 * @classname Jdk8Module.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public class Jdk8Module implements Module {

  @Override
  public void setupModule(ModelMapper modelMapper) {
    modelMapper.getConfiguration().getConverters().add(0, new FromOptionalConverter());
    modelMapper.getConfiguration().getConverters().add(0, new ToOptionalConverter());
  }
}