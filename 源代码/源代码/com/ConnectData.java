package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectData {
	Connection con;
    Statement sql;
    PreparedStatement presql;
    ResultSet rs;
    String url="jdbc:odbc:exam";
    //String  option[]={"A","B","C","D","E","F"};//���ݿ��д��ֶ�����

    public ConnectData(String dr){
    	try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }catch(ClassNotFoundException ec){
            System.out.println(""+ec);
        }
        try{ //���������Ӷ�ȡ���ݿ�
        con = DriverManager.getConnection(url,"","");
        sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String f = "SELECT * FROM "+ dr ;
        rs = sql.executeQuery(f);
        }catch(SQLException es){
            System.out.println(es);
        }
    }
    public ConnectData(String dr,int id){
    	try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }catch(ClassNotFoundException ec){
            System.out.println(""+ec);
        }
    	try{
    		 con = DriverManager.getConnection(url,"","");
    	     sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	     String f = "select * from " + dr + " where " + "������Ŀ="+ id ;
    	     rs = sql.executeQuery(f);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    public ResultSet getResultSet(){
    	return this.rs;
    }
    public ResultSet search(String dataname,String str,String ques){
    	String f = "select * from " + dataname + " where " + str + "=?";
    	try{
    		con = DriverManager.getConnection(url,"","");
    		presql = con.prepareStatement(f);
    	    presql.setString(1, ques);
        	rs = presql.executeQuery();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return rs;
    }
    public ResultSet search(String dataname,int id,String str,String ques){
    	String f = "select * from " + dataname + " where " + "������Ŀ="+ id + " and "+ str + "=?";
    	try{
    		con = DriverManager.getConnection(url,"","");
    		presql = con.prepareStatement(f);
    	    presql.setString(1, ques);
        	rs = presql.executeQuery();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return rs;
    }
    public void  cancel(String dataname,String str,String ques){
    	int i = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ��ɾ����", "ɾ��", 0, 1, null, null, null);
    	if(i == 0){
    		//ȷ��ɾ��
    		String f = "delete from " + dataname + " where " + str + "=?";
        	try{
        		con = DriverManager.getConnection(url,"","");
        		presql = con.prepareStatement(f);
        	    presql.setString(1, ques);
            	presql.executeUpdate();
        	}catch(SQLException e){
        		e.printStackTrace();
        	}
    	}
    	else
    		return;
    }
    public void mind(String dataname,String setstr,String str,String ques){
    	String f = "update " + dataname + " set " + setstr + " where " + str + "=?";
    	try{
    		con = DriverManager.getConnection(url,"","");
    		presql = con.prepareStatement(f);
    	    presql.setString(1, ques);
        	presql.executeUpdate();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    public void mind(String dataname,int id,String setstr,String str,String ques){
    	String f = "update " + dataname + " set " + setstr + " where " + "������Ŀ="+ id + " and "+ str + "=?";
    	try{
    		con = DriverManager.getConnection(url,"","");
    		presql = con.prepareStatement(f);
    	    presql.setString(1, ques);
        	presql.executeUpdate();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    public void adding(String dataname,String addstr){
    	String f = "insert into " + dataname + addstr;
    	try{
    		con = DriverManager.getConnection(url,"","");
    		presql = con.prepareStatement(f);
        	presql.executeUpdate();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }

    public  void Close() {
    	try{
    		con.close();
        }catch(SQLException es){
        	System.out.println(es);
        }
    }
 
}
