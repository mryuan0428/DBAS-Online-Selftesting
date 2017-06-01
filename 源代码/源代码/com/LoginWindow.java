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
     JPanel p1=new JPanel();//��������
     JPanel p2=new JPanel();
     JPanel p3=new JPanel();
     JPanel p4=new JPanel();
     JPanel p5=new JPanel();
     JPanel p6=new JPanel();
     JPanel p7=new JPanel();
     JTextField userNameText=new JTextField(15);
     JPasswordField passWordText=new JPasswordField(15);
     JButton yesButton=new JButton("ȷ��");
     JButton cancelButton=new JButton("ȡ��");
     JComboBox loginPrivelegeComboBox;

     String subject;//��Ž�ʦ�γ̻�ѧ������רҵ
     String department;

     ResultSet rs;//�����������ݿ�

    /** Creates a new instance of LoginWindow */
    public LoginWindow() {
        setModal(true);//����Ϊģʽ�Ի���
        setBackground(Color.LIGHT_GRAY);//���ñ���ɫ
        Container contentPane=this.getContentPane();//�������
        contentPane.setLayout(new GridLayout(7,1));// ���ò���
        //��ӡ��û�������ǩ
        p2.add(new JLabel("�û�����"));
        //��ӡ��û����������
        p2.add(userNameText);
        //��ӡ����롱��ǩ
        p3.add(new JLabel("��     �룺"));
        //��ӡ����롱�����
        p3.add(passWordText);
        //��ӡ���ɫ����ǩ
        p4.add(new JLabel("��     ɫ��"));
        loginPrivelegeComboBox = new JComboBox();
        loginPrivelegeComboBox.addItem("ѧ��               ");
        loginPrivelegeComboBox.addItem("��ʦ               ");
        loginPrivelegeComboBox.addItem("ϵͳ����Ա          ");
        loginPrivelegeComboBox.setSelectedItem(null);
        p4.add(loginPrivelegeComboBox);
        //��ӡ�ȷ�ϡ���ť
        p6.add(yesButton);
        //��ӡ�ȡ������ť
        p6.add(cancelButton);
        //Ϊ��ȷ�ϡ���ť�����¼�������
        yesButton.addActionListener(this);
        //Ϊ��ȡ������ť�����¼�������
        cancelButton.addActionListener(this);
        //Ϊ���û��������������¼�������
        userNameText.addActionListener(this);
        //Ϊ�����롱���������¼�������
        passWordText.addActionListener(this);
        //���õ�������
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        contentPane.add(p5);
        contentPane.add(p6);
        contentPane.add(p7);
        //���öԻ����С
        setSize(300,280);
        //���öԻ���λ��
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((screen.width-300)/2),((screen.height-220)/2));
        setTitle("��¼����");
        setResizable(false);//���ܸı��С
        setVisible(true);
    }
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==yesButton || e.getSource()==passWordText){
            	//������ȷ������ť�����뿪�����롱�����
            	char[] password = passWordText.getPassword();
        		//���û�������������ַ�����ת�����ַ���
        		String passwordStr = new String(password);
        		String name = new String(userNameText.getText().trim());
                if(name.equals("")){
                	//�ж��û����Ƿ�Ϊ��
                	JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
                	return ;
                }
                if(passwordStr.equals("")){
            			//�ж��û�������û����Ƿ�Ϊ��
            		JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
            		return ;
            	}
                int index = loginPrivelegeComboBox.getSelectedIndex();//��ȡ�û�ѡ��Ľ�ɫ
                if (index == 1){
                	//��ʦ��ɫ
                	ConnectData  user = new ConnectData("teacher");
            		rs = user.getResultSet();
				     if(testuser(name,passwordStr,1)){
                          new Interface(department,subject);
                        //  user.Close();
                          this.dispose();
                     }
				     else{
	                     JOptionPane.showMessageDialog(null,"�û������������");
	                     userNameText.requestFocus();
	                     userNameText.setSelectionStart(0);
	                     userNameText.setSelectionEnd(userNameText.getText().length());
	                }
                }else if(index ==0 ){
                	//ѧ����ɫ
                	ConnectData  user = new ConnectData("student");
                	rs = user.getResultSet();
                	if(testuser(name,passwordStr,0)){
                        new ShowItem(department);
                      //  user.Close();
                        this.dispose();
                   }
				     else{
	                     JOptionPane.showMessageDialog(null,"�û������������");
	                     userNameText.requestFocus();
	                     userNameText.setSelectionStart(0);
	                     userNameText.setSelectionEnd(userNameText.getText().length());
	                }
                }
                else if(index == 2){
                	//����Ա��ɫ
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
    				String password1 = rs.getString("����");
    				if(name.equals(name1)&&password.equals(password1)){
    					//�ҵ���ǰ�û�
    					department = new String(rs.getString(4));//��ʦ������̿γ̣�ѧ���������רҵ
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


