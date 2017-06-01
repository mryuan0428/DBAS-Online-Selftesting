package com;


import teacher.Interface;
import student.ShowItem;
import admin.Manage;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


@SuppressWarnings("serial")
public class LoginWindow extends JDialog implements ActionListener {
     JPanel p1=new JPanel();//建立容器
     JPanel p2=new JPanel();
     JPanel p3=new JPanel();
     JPanel p4=new JPanel();
     JPanel p5=new JPanel();
     JPanel p6=new JPanel();
     JPanel p7=new JPanel();
     JTextField userNameText=new JTextField(15);
     JPasswordField passWordText=new JPasswordField(15);
     JButton yesButton=new JButton("确定");
     JButton cancelButton=new JButton("取消");
     JComboBox loginPrivelegeComboBox;

     String subject;//存放教师课程或学生所属专业
     String department;

     ResultSet rs;//用来接收数据库

    /** Creates a new instance of LoginWindow */
    public LoginWindow() {
        setModal(true);//设置为模式对话框
        setBackground(Color.LIGHT_GRAY);//设置背景色
        Container contentPane=this.getContentPane();//获得容器
        contentPane.setLayout(new GridLayout(7,1));// 设置布局
        //添加“用户名”标签
        p2.add(new JLabel("用户名："));
        //添加“用户名”输入框
        p2.add(userNameText);
        //添加“密码”标签
        p3.add(new JLabel("密     码："));
        //添加“密码”输入框
        p3.add(passWordText);
        //添加“角色”标签
        p4.add(new JLabel("角     色："));
        loginPrivelegeComboBox = new JComboBox();
        loginPrivelegeComboBox.addItem("学生               ");
        loginPrivelegeComboBox.addItem("教师               ");
        loginPrivelegeComboBox.addItem("系统管理员          ");
        loginPrivelegeComboBox.setSelectedItem(null);
        p4.add(loginPrivelegeComboBox);
        //添加“确认”按钮
        p6.add(yesButton);
        //添加“取消”按钮
        p6.add(cancelButton);
        //为“确认”按钮加入事件监听者
        yesButton.addActionListener(this);
        //为“取消”按钮加入事件监听者
        cancelButton.addActionListener(this);
        //为“用户名”输入框加入事件监听者
        userNameText.addActionListener(this);
        //为“密码”输入框加入事件监听者
        passWordText.addActionListener(this);
        //放置到容器中
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        contentPane.add(p5);
        contentPane.add(p6);
        contentPane.add(p7);
        //设置对话框大小
        setSize(300,280);
        //设置对话框位置
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((screen.width-300)/2),((screen.height-220)/2));
        setTitle("登录窗口");
        setResizable(false);//不能改变大小
        setVisible(true);
    }
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==yesButton || e.getSource()==passWordText){
            	//单击“确定”按钮或光标离开“密码”输入框
            	char[] password = passWordText.getPassword();
        		//将用户输入的密码由字符数组转换成字符串
        		String passwordStr = new String(password);
        		String name = new String(userNameText.getText().trim());
                if(name.equals("")){
                	//判断用户名是否为空
                	JOptionPane.showMessageDialog(null, "用户名不能为空");
                	return ;
                }
                if(passwordStr.equals("")){
            			//判断用户输入的用户名是否为空
            		JOptionPane.showMessageDialog(null, "密码不可为空");
            		return ;
            	}
                int index = loginPrivelegeComboBox.getSelectedIndex();//获取用户选择的角色
                if (index == 1){
                	//教师角色
                	ConnectData  user = new ConnectData("teacher");
            		rs = user.getResultSet();
				     if(testuser(name,passwordStr,1)){
                          new Interface(department,subject);
                        //  user.Close();
                          this.dispose();
                     }
				     else{
	                     JOptionPane.showMessageDialog(null,"用户名或密码错误！");
	                     userNameText.requestFocus();
	                     userNameText.setSelectionStart(0);
	                     userNameText.setSelectionEnd(userNameText.getText().length());
	                }
                }else if(index ==0 ){
                	//学生角色
                	ConnectData  user = new ConnectData("student");
                	rs = user.getResultSet();
                	if(testuser(name,passwordStr,0)){
                        new ShowItem(department);
                      //  user.Close();
                        this.dispose();
                   }
				     else{
	                     JOptionPane.showMessageDialog(null,"用户名或密码错误！");
	                     userNameText.requestFocus();
	                     userNameText.setSelectionStart(0);
	                     userNameText.setSelectionEnd(userNameText.getText().length());
	                }
                }
                else if(index == 2){
                	//管理员角色
                	if(name.equals("admin")&&passwordStr.equals("admin")){
                		new Manage();
                		this.dispose();
                	}
                }
            }
            else if(e.getSource()==cancelButton){
                dispose();
                System.exit(0);
               }
            else if(e.getSource()==userNameText){
                passWordText.requestFocus();
             }
        }
        private boolean testuser(String name, String password,int i){
        	boolean tf = false;
        	try{
    			while(rs.next()){
    				String name1 = rs.getString(1);
    				String password1 = rs.getString("密码");
    				if(name.equals(name1)&&password.equals(password1)){
    					//找到当前用户
    					department = new String(rs.getString(4));//教师存放所教课程；学生存放所属专业
    					if(i == 1) subject = new String(rs.getString(5));
                        rs.last();
                        tf = true;
    				}
    		   }
    		 }catch(SQLException es){
    	            System.out.println(es);
    	        }

    		 return tf;
        }
    public static void main(String args[]){
            JDialog.setDefaultLookAndFeelDecorated(true);
            @SuppressWarnings("unused")
			Font font=new Font("JFrame",Font.PLAIN,14);
            new LoginWindow();
            }
}


