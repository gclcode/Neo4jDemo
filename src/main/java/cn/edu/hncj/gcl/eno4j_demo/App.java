package cn.edu.hncj.gcl.eno4j_demo;

import cn.edu.hncj.gcl.eno4j_demo.service.Neo4jServiceImpl;

public class App 
{
	public static void main( String[] args ){
		
		//请在cn.edu.hncj.gcl.eno4j_demo.util.Neo4jUtils类中配置您的neo4j数据库信息
		Neo4jServiceImpl neo4jServiceImpl = new Neo4jServiceImpl();
		
		neo4jServiceImpl.add();//添加节点和关系信息
		neo4jServiceImpl.change();//修改节点和关系信息
		neo4jServiceImpl.query("王子");//查询节点信息
		neo4jServiceImpl.query("公主");//查询节点信息
//		neo4jServiceImpl.delete();//删除节点和关系信息
		
	}
}
