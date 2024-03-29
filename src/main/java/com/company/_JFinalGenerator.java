package com.company;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

public class _JFinalGenerator {

    public static void main(String[] args) {
        DruidPlugin dp = new DruidPlugin(
                "jdbc:mysql://192.168.80.128:3306/test",
                "root",
                "123456",
                "com.mysql.cj.jdbc.Driver");
        dp.start();
        DataSource dataSource = dp.getDataSource();

        // model 所使用的包名，路径
        String modelPkg = "com.company.db";
        String modelDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPkg.replace('.', '/');

        // base model 所使用的包名，路径
        String baseModelPkg = modelPkg + ".base";
        String baseModelDir = modelDir + "/base";

        Generator generator = new Generator(dataSource, baseModelPkg, baseModelDir, modelPkg, modelDir);
        // 配置是否生成备注
        generator.setGenerateRemarks(true);
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(true);
        // 添加不需要生成的表名
//        generator.addExcludedTable("");
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
//        generator.setRemovedTableNamePrefixes("r_");

        generator.generate();
    }
}
