package com.util;

import java.io.*;
import java.lang.*;

/*   
 * 还原MySql数据库   
 * */
public class Recover {
	public boolean load() {
		String filepath = "d:\\jxc.sql"; // 备份的路径地址
		// 新建数据库test

		String stmt1 = "mysqladmin -u root -proot create jxctest";

		String stmt2 = "mysql -u root -proot jxctest < " + filepath;
		String[] cmd = { "cmd", "/c", stmt2 };

		try {
			Runtime.getRuntime().exec(stmt1);
			Runtime.getRuntime().exec(cmd);
			System.out.println("数据已从 " + filepath + " 导入到数据库中");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}