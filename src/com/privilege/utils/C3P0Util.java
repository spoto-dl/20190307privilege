package com.privilege.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class C3P0Util {

    private static QueryRunner queryRunner;

    public static QueryRunner getQueryRunner(){

        //准备数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        queryRunner= new QueryRunner(dataSource);

        return queryRunner;
    }



}
