package com.yyjj.reading;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;


//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {


  static String datasourceUrl = "127.0.0.1";
  static String name = "root";
  static String password = "123456";
  
  static String ModuleName = "com.yyjj.reading";
  
  static String Parent = "com.yyjj.reading.db.model";
  static String projectPath = "G:\\turing\\项目\\客服\\reading";
  
  /**
   * <p>
   * 读取控制台内容
   * </p>
   */
  @SuppressWarnings("resource")
  public static String scanner(String tip) {
      Scanner scanner = new Scanner(System.in);
      StringBuilder help = new StringBuilder();
      help.append("请输入" + tip + "：");
      System.out.println(help.toString());
      if (scanner.hasNext()) {
          String ipt = scanner.next();
          if (StringUtils.isNotEmpty(ipt)) {
              return ipt;
          }
      }
      throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  public static void main(String[] args) {
      // 代码生成器
      AutoGenerator mpg = new AutoGenerator();

      // 全局配置
      GlobalConfig gc = new GlobalConfig();
//      String projectPath = System.getProperty("user.dir");
     // projectPath = projectPath;
      gc.setOutputDir(projectPath + "/src/main/java");
      gc.setAuthor("yml");
      gc.setOpen(false);
      // 是否覆盖已有文件
      gc.setFileOverride(false);
      // gc.setSwagger2(true); 实体属性 Swagger2 注解
      mpg.setGlobalConfig(gc);

      // 数据源配置
      DataSourceConfig dsc = new DataSourceConfig();
      dsc.setUrl("jdbc:mysql://"+datasourceUrl+":3306/online_reading?serverTimezone=GMT&useUnicode=true&useSSL=false&characterEncoding=utf8");
      // dsc.setSchemaName("public");
      dsc.setDriverName("com.mysql.cj.jdbc.Driver");
      dsc.setUsername(name);
      dsc.setPassword(password);
      mpg.setDataSource(dsc);
      //announcement,instrument,instrument_make,laboratory,laboratory_make,leave_message,make_history,user
      // 包配置
      PackageConfig pc = new PackageConfig();
      pc.setModuleName(ModuleName);
      pc.setParent(Parent);
      mpg.setPackageInfo(pc);

      // 自定义配置
      InjectionConfig cfg = new InjectionConfig() {
          @Override
          public void initMap() {
              // to do nothing
          }
      };
      cfg.setFileCreate(new IFileCreate() {
          @Override
          public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) { // 判断自定义文件夹是否需要创建
              if (fileType==FileType.MAPPER||fileType==FileType.ENTITY) {
                  return true;
              }else {
                  return false;
              }
          }
      });
      
      mpg.setCfg(cfg);

      // 配置模板
//      TemplateConfig templateConfig = new TemplateConfig();

      // 配置自定义输出模板
      // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
      // templateConfig.setEntity("templates/entity2.java");
      // templateConfig.setService();
      // templateConfig.setController();
//
//      templateConfig.setXml(null);
//      mpg.setTemplate(templateConfig);

      // 策略配置
      StrategyConfig strategy = new StrategyConfig();
      strategy.setNaming(NamingStrategy.underline_to_camel);
      strategy.setColumnNaming(NamingStrategy.underline_to_camel);
      strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
      strategy.setTablePrefix(pc.getModuleName() + "_");
      strategy.setSkipView(true);
      mpg.setStrategy(strategy);
      mpg.setTemplateEngine(new FreemarkerTemplateEngine());
      mpg.execute();
  }

}