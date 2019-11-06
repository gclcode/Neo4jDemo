package cn.edu.hncj.gcl.eno4j_demo.service;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import cn.edu.hncj.gcl.eno4j_demo.util.Neo4jUtils;

public class Neo4jServiceImpl implements Neo4jService{
	
	Session session = Neo4jUtils.getsession();
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		try (Transaction transaction = session.beginTransaction();){
			//对象1
			String name1 = "G";
			String sex1 = "boy";
			//对象2
			String name2 = "C";
			String sex2 = "girl";
			//对象关系
			String info = "classmate";
			//运行语句
			transaction.run("create(p1:Person{name:'"+name1+"',sex:'"+sex1+"'})");
			transaction.run("create(p2:Person{name:'"+name2+"',sex:'"+sex2+"'})");
			transaction.run("match (p1:Person),(p2:Person) where p1.name='"+name1+"' and p2.name='"+name2+"' create (p1)-[r:"+info+"]->(p2)");
			//执行
			transaction.success();
			Neo4jUtils.closesession();
		}
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		try (Transaction transaction = session.beginTransaction();){
			//删除所有关系和节点
			transaction.run("MATCH (n:Person)-[r]-() DELETE n,r");
			//执行
			transaction.success();
			Neo4jUtils.closesession();
		}
	}

	@Override
	public void change() {
		// TODO Auto-generated method stub
		try (Transaction transaction = session.beginTransaction();){
			//修改关系和节点信息
			//对象1
			String name1 = "G";
			String name1_c = "王子";
			String sex1_c = "男";
			//对象2
			String name2 = "C";
			String name2_c = "公主";
			String sex2_c = "女";
			//对象关系
			String info_c = "情侣";
			// 修改节点信息和关系信息
			transaction.run("MATCH (p1:Person {name:'"+name1+"'})-[r]-(p2:Person {name:'"+name2+"'}) Delete r ");
			transaction.run("MATCh (p1:Person),(p2:Person) where p1.name='"+name1+"' and p2.name='"+name2+"' create (p1)-[r:"+info_c+"]->(p2)");
			transaction.run("MATCH (p1:Person {name:'"+name1+"'}) SET p1.sex= '"+sex1_c+ "'");
			transaction.run("MATCH (p2:Person {name:'"+name2+"'}) SET p2.sex= '"+sex2_c+ "'");
			transaction.run("MATCH (p1:Person {name:'"+name1+"'}) SET p1.name= '"+name1_c+ "'");
			transaction.run("MATCH (p2:Person {name:'"+name2+"'}) SET p2.name= '"+name2_c+ "'");
			//执行
			transaction.success();
			Neo4jUtils.closesession();
		}
	}

	@Override
	public void query(String name) {
		// TODO Auto-generated method stub
		try (Transaction transaction = session.beginTransaction();){
			//查询节点信息
			StatementResult result = transaction.run("match (p1:Person) WHERE p1.name ='"+name+"' RETURN p1.name AS name,p1.sex AS sex");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("查询的节点信息如下:\n姓名: %s \n性别: %s",record.get("name").asString(),record.get("sex").asString()));
			}
			//执行
			transaction.success();
			Neo4jUtils.closesession();
		}
	}
}
