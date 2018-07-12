package com.example.demo;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

import static org.jooq.impl.DSL.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	DSLContext jooq;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		//----------生成sql字符串----------
		String sql = jooq.insertInto(table("user"))
				.columns(field("userid"),field("password"),field("username"))
				.values(2,"p2","u2")
				.getSQL();
		System.out.println(sql);


		//-----------增删改查---------------
		//增
		jooq.insertInto(table("user"))
				.columns(field("userid"),field("password"),field("username"))
				.values(2,"p2","u2")
				.execute();
		//删
		jooq.delete(table("user"))
				.where("userid=2")
				.execute();
		//改
		jooq.update(table("user"))
				.set(field("username"),"u1改")
				.where("userid=1")
				.execute();
		//查
		Result<Record> res   = jooq.select().from(table("user")).where("userid > 0").fetch();
		Iterator it=res.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}

		//-----------事务--------------
		try{
			jooq.transaction(
				configuration -> {
					jooq.insertInto(table("user"))
							.columns(field("userid"),field("password"),field("username"))
							.values(3,"p3","u3")
							.execute();
					//!!!!!!!!这里如果出现异常，则这两个记录都不会被插入
					int a=1/0;
					jooq.insertInto(table("user"))
							.columns(field("userid"),field("password"),field("username"))
							.values(4,"p4","u4")
							.execute();
				}
		);
		}
		catch(Exception e){
			System.out.println("出现异常，两条记录都不被插入，数据库内容没有变化");
			Result<Record> res2   = jooq.select().from(table("user")).where("userid > 0").fetch();
			Iterator it2=res.iterator();
			while (it2.hasNext()){
				System.out.println(it2.next());
			}
		};
	}
}
