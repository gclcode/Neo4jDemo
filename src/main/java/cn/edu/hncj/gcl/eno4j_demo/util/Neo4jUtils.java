package cn.edu.hncj.gcl.eno4j_demo.util;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jUtils {
	
    public static Session getsession() {
    	String url = "bolt://127.0.0.1:7687";//neo4j链接地址
    	String username = "neo4j";//用户名
    	String userpassword = "123456";//用户密码
    	Driver driver = GraphDatabase.driver(url,AuthTokens.basic(username,userpassword));
    	return driver.session();
	}
    
    public static void closesession() {
    	Neo4jUtils.getsession().close();
	}
    
}
