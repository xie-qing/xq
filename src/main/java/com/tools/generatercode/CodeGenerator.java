package com.tools.generatercode;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
/**
 * Demo class
 *
 * @author xq
 * @date 2019/3/31
 */

public class CodeGenerator {

	// driverClassName: com.mysql.jdbc.Driver #是 mysql-connector-java 5中的 //
	// driverClassName:com.mysql.cj.jdbc.Driver#是 mysql-connector-java 6 中的

	public static final String URL = "jdbc:mysql://localhost:3306/vitea?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//	 public static final String URL ="jdbc:mysql://127.0.0.1:3306/vitea?useUnicode=true&useSSL=false&characterEncoding=utf8";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	public static final String PACK_NAME = "com.tools";
	public static final String PROJECT_NAME = "/";
	public static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	// public static final String DRIVERNAME ="com.mysql.jdbc.Driver";

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public String scanner(String tip) {
		@SuppressWarnings("resource")
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

	/**
	 * 代码生成器
	 *
	 *
	 */
	public void getAutoGenerator() {
		AutoGenerator autoGenerator = new AutoGenerator();
		autoGenerator.setGlobalConfig(getGlobalConfig());
		autoGenerator.setPackageInfo(getPackageConfig());
		autoGenerator.setDataSource(getDataSourceConfig());

		autoGenerator.setTemplate(getTemplateConfig());

		autoGenerator.setCfg(getInjectionConfig());
		autoGenerator.setStrategy(getStrategyConfig());
		// 使用freemarker模板，默认使用的是velocity模板 //
//		autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
		autoGenerator.execute();

	}

	/**
	 *
	 * 配置自定义魔板定义
	 */

	public InjectionConfig getInjectionConfig() {
		InjectionConfig injectionConfig = new InjectionConfig() {

			@Override
			public void initMap() { // 配置生成异常类的包名${cfg.ExceptionPak}获取属性
				Map<String, Object> map = new HashMap<>();
				map.put("ExceptionPak", PACK_NAME + "." + packageConfig.getModuleName() + ".exception");
				this.setMap(map);
			}
		};
		// 如果模板引擎是 freemarker
		// String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		String templatePath = "/templates/vm/exception.java.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>(); // 将包路径转化为文件路径
		String pack = PACK_NAME.replace(".", "/"); // 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {

			@Override
			public String outputFile(TableInfo tableInfo) {

				// 自定义输出文件名 按自定义魔板生成对应异常处理类
				return projectPath + "/" + pack + "/" + packageConfig.getModuleName() + "/exception/"
						+ tableInfo.getEntityName() + "Exception" + StringPool.DOT_JAVA;
			}

		}); // 自定义配置会被优先输出
		focList.add(new FileOutConfig("/templates/vm/request.java.vm") {

			@Override
			public String outputFile(TableInfo tableInfo) {
				System.out.println(
						packageConfig.getController() + "  =============》       " + packageConfig.getModuleName()); // 自定义输出文件名
				return projectPath + "/" + pack + "/" + packageConfig.getModuleName() + "/"
						+ packageConfig.getController() + "/request/" + tableInfo.getEntityName() + "Request"
						+ StringPool.DOT_JAVA;
			}

		}); //
		focList.add(new FileOutConfig("/templates/vm/mapper.xml.vm") { //
			@Override
			public String outputFile(TableInfo tableInfo) { //
				System.out.println("  =============》       " + projectPath.lastIndexOf("/"));
				// // 自定义输入文件名称
				return projectPath.substring(0, projectPath.lastIndexOf("/")) + "/resources/mapper" + "/"
						+ packageConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper"
						+ StringPool.DOT_XML;
			}
		});
		injectionConfig.setFileOutConfigList(focList);
		return injectionConfig;

	}

	/**
	 * 自定义实体类魔板
	 */
	public TemplateConfig getTemplateConfig() {
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setController("/templates/vm/controller.java.vm");
		templateConfig.setService("/templates/vm/service.java.vm");
		templateConfig.setServiceImpl("/templates/vm/serviceImpl.java.vm");
		templateConfig.setEntity("/templates/vm/entity.java.vm");
		templateConfig.setMapper("/templates/vm/mapper.java.vm"); //
		templateConfig.setXml("/templates/vm/mapper.xml.vm");
		return templateConfig;
	}

	public static String projectPath = System.getProperty("user.dir") + PROJECT_NAME + "/src/main/java";

	/**
	 * 全局配置
	 */

	public GlobalConfig getGlobalConfig() {
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(projectPath);
		globalConfig.setAuthor("xq");
		globalConfig.setOpen(false);
		globalConfig.setFileOverride(true);
		globalConfig.setActiveRecord(true);
		// 不需要ActiveRecord特性的请改为false
		globalConfig.setEnableCache(false);// XML 二级缓存
		globalConfig.setBaseResultMap(true);// XML ResultMap
		globalConfig.setBaseColumnList(true);// XML columList 
		return globalConfig;
	}

	/**
	 * 包配置
	 *
	 */

	PackageConfig packageConfig;

	public PackageConfig getPackageConfig() {
		packageConfig = new PackageConfig();
		packageConfig.setModuleName(scanner("模块名"));
		packageConfig.setParent(PACK_NAME);
		return packageConfig;
	}

	/**
	 * 数据源配置
	 *
	 *
	 */

	public DataSourceConfig getDataSourceConfig() {
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl(URL); //
//		dsc.setSchemaName("public");
		dataSourceConfig.setDriverName(DRIVERNAME);
		dataSourceConfig.setUsername(USERNAME);
		dataSourceConfig.setPassword(PASSWORD);
		return dataSourceConfig;
	}

	/**
	 *
	 * 策略配置
	 *
	 *
	 *
	 */
	public StrategyConfig getStrategyConfig() {
		// 策略配置

		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel); //
//		strategy.setSuperEntityClass("com.xq.viteaadmin.users.Entity");
		strategy.setEntityLombokModel(true);
//		strategy.setInclude(new String[] { "vitea_user" });
		// strategy.setRestControllerStyle(true);
//		strategy.setSuperControllerClass("com.xq.viteaadmin.users.controller");
		// strategy.setInclude(scanner("表名"));
		// strategy.setSuperEntityColumns("id");
		// String filePr[] = {"static"}; // strategy.setFieldPrefix(); 
		//strategy.setControllerMappingHyphenStyle(true); //
//		strategy.setTablePrefix(packageConfig.getModuleName() + "_");
		return strategy;
	}
}
