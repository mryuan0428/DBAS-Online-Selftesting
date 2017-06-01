package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ConnectData;

public class StudentManage {
	Manage frame;
	JPanel dopanel;
	JScrollPane students;
	JTable student;
	DefaultTableModel dm;//表格类型
	ConnectData  studentcon;
	ResultSet studentsrs;//用来接收学生数据库
	ResultSet rs;
	int mindoradd;//标识修改或添加操作，0为修改1为添加

	JPanel studentnumber;
	JPanel studentpassword;
	JPanel studentname;
	JPanel studentsubject;
	JPanel buttonpanel;

	JLabel studentnumberl;
	JLabel studentpasswordl;
	JLabel studentnamel;
	JLabel studentsubjectl;

	JTextField studentnumbert;
	JTextField studentpasswordt;
	JTextField studentnamet;
	JComboBox studentsubjectt;

	JButton search;//"查询"按钮
	JButton mind;//"修改"按钮
	JButton cancel;//"删除"按钮
	JButton adding;//"增加"按钮
	JButton save;//"保存"按钮
	JButton exit;//"取消"按钮

	public StudentManage(Manage frame,JPanel dopanel){
		this.frame = frame;
	    this.dopanel = dopanel;
	    dopanel.setLayout(null);
	    panel();

	    showitem();
	}
	private void panel(){
		students = new JScrollPane();
		students.setBounds(0, 0, 600, 300);
		String[] cols = {"帐号","密码","姓名","专业"};//列名
		dm = new DefaultTableModel(cols,0);
		student = new JTable(dm);
		students.setViewportView(student);//将表格添加到滚动面板
		students.setHorizontalScrollBarPolicy(32);//设置水平滚动条一直显示
		students.setVerticalScrollBarPolicy(22);//设置垂直滚动条一直显示
		dopanel.add(students);

		studentnumberl = new JLabel("帐号");
		studentpasswordl = new JLabel("密码");

		studentnamel = new JLabel("姓名");
		studentsubjectl = new JLabel("专业");

		studentnumbert = new JTextField(10);//"题号"文本框
		studentnumbert.setEditable(true);
		studentpasswordt = new JTextField(10);
		studentpasswordt.setEditable(false);
		studentnamet = new JTextField(10);
		studentnamet.setEditable(false);
		studentsubjectt = new JComboBox();//"正确答案"文本框
		studentsubjectt.addItem("计算机科学与技术");
		studentsubjectt.addItem("信息管理");
		studentsubjectt.setSelectedItem(null);
		studentsubjectt.setEditable(false);

		studentnumber = new JPanel();
		studentnumber.setBounds(3, 300, 150, 30);
		studentpassword = new JPanel();
		studentpassword.setBounds(3, 335, 150, 30);
		studentname = new JPanel();
		studentname.setBounds(3,370,150, 30);
		studentsubject = new JPanel();
		studentsubject.setBounds(3,410,200, 30);
		buttonpanel = new JPanel();
		buttonpanel.setBounds(15,500,400, 100);

		studentnumber.add(studentnumberl);
		studentnumber.add(studentnumbert);
		studentpassword.add(studentpasswordl);
		studentpassword.add(studentpasswordt);
		studentname.add(studentnamel);
		studentname.add(studentnamet);
		studentsubject.add(studentsubjectl);
		studentsubject.add(studentsubjectt);

		search = new JButton("查询");//"查询"按钮
		mind = new JButton("修改");//"修改"按钮
		cancel = new JButton("删除");//"删除"按钮
		adding = new JButton("增加");//"增加"按钮
		save = new JButton("保存");//"保存"按钮
		exit = new JButton("取消");//"取消"按钮
		exit.setEnabled(false);
		save.setEnabled(false);
		buttonpanel.add(search);
		search.addActionListener(new search_actionAdapter(this));
		buttonpanel.add(mind);
		mind.addActionListener(new mind_actionAdapter(this));
		buttonpanel.add(cancel);
		cancel.addActionListener(new cancel_actionAdapter(this));
		buttonpanel.add(adding);
		adding.addActionListener(new adding_actionAdapter(this));
		buttonpanel.add(save);
		save.addActionListener(new save_actionAdapter(this));
		buttonpanel.add(exit);
		exit.addActionListener( new exit_actionAdapter(this));

		dopanel.add(studentnumber);
		dopanel.add(studentpassword);
		dopanel.add(studentname);
		dopanel.add(studentsubject);
		dopanel.add(buttonpanel);

		frame.setVisible(true);
	}
	class search_actionAdapter implements ActionListener{
		StudentManage adaptee;
		 search_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.search_actionPerformed(e);
	    }
	}
	 public void search_actionPerformed(ActionEvent e){
		 String number = studentnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要查询的学生帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = studentcon.search("student", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					studentpasswordt.setText(rs.getString(2));
					studentnamet.setText(rs.getString(3));
					studentsubjectt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }
		 }
	 }
	 class mind_actionAdapter implements ActionListener{
		 StudentManage adaptee;
		 mind_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.mind_actionPerformed(e);
	    }
	}
	 public void mind_actionPerformed(ActionEvent e){
		 mindoradd = 0;
		 String number = studentnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要修改的学生帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = studentcon.search("student", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					studentpasswordt.setText(rs.getString(2));
					studentnamet.setText(rs.getString(3));
					studentsubjectt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				  JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }else{
				 studentpasswordt.setEditable(true);
				 studentnamet.setEditable(true);
				 studentsubjectt.setEditable(true);
			     save.setEnabled(true);
			     exit.setEnabled(true);
			 }
		 }
	 }
	 class cancel_actionAdapter implements ActionListener{
		 StudentManage adaptee;
		 cancel_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.cancel_actionPerformed(e);
	    }
	}
	 public void cancel_actionPerformed(ActionEvent e){
		 String number = studentnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要删除的学生帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = studentcon.search("student", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					studentpasswordt.setText(rs.getString(2));
					studentnamet.setText(rs.getString(3));
					studentsubjectt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }
			 studentcon.cancel("student", "帐号", number);
			 studentcon.Close();
			 clearRows();
			 showitem();
			 studentnumbert.setText("");
			 studentpasswordt.setText("");
			 studentnamet.setText("");
			 studentsubjectt.setSelectedItem(null);
		 }
	 }
	 class adding_actionAdapter implements ActionListener{
		 StudentManage adaptee;
		 adding_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.adding_actionPerformed(e);
	    }
	}
	 public void adding_actionPerformed(ActionEvent e){
		 mindoradd = 1;
	     studentnumbert.setText("");
		 studentpasswordt.setText("");
		 studentnamet.setText("");
		 studentsubjectt.setSelectedItem(null);
		 studentnumbert.setEditable(true);
		 studentpasswordt.setEditable(true);
		 studentnamet.setEditable(true);
		 studentsubjectt.setEditable(true);
		 save.setEnabled(true);
		 exit.setEnabled(true);
	 }
	 class exit_actionAdapter implements ActionListener{
		 StudentManage adaptee;
		 exit_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.exit_actionPerformed(e);
	    }
	 }
	 public void exit_actionPerformed(ActionEvent e){
		 studentnumbert.setText("");
		 studentpasswordt.setText("");
		 studentnamet.setText("");
		 studentsubjectt.setSelectedItem(null);
		 studentnumbert.setEditable(true);
		 studentpasswordt.setEditable(false);
		 studentnamet.setEditable(false);
		 studentsubjectt.setEditable(false);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 class save_actionAdapter implements ActionListener{
		 StudentManage adaptee;
		 save_actionAdapter(StudentManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.save_actionPerformed(e);
	    }
	}
	 public void save_actionPerformed(ActionEvent e){
		 String number = studentnumbert.getText().trim();
		 if(mindoradd == 0){
			 //修改后保存
			 String setstr = "密码 = '" + studentpasswordt.getText().trim() +
			                 "',姓名 = '" + studentnamet.getText().trim() +
			                 "',专业 = '" + studentsubjectt.getSelectedItem().toString()+"'";
			 studentcon.mind("student", setstr,"帐号", number);
		 }else{
			 //添加后保存
			 if(studentnumbert.getText().trim().equals("")||
					 studentpasswordt.getText().trim().equals("")||
					 studentnamet.getText().trim().equals("")||
					 studentsubjectt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"信息不完整！请填写全部信息！");
				 return;
			 }else{
				 if(Isexit(studentnumbert.getText().trim())){
					 JOptionPane.showMessageDialog(null,"该帐号已存在！");
					 studentnumbert.setText("");
					 studentpasswordt.setText("");
					 studentnamet.setText("");
					 studentsubjectt.setSelectedItem(null);
					 return;
				 }
				 else{
					 String addstr = " (帐号,密码,姓名,专业)  values ('" +
				                 studentnumbert.getText().trim()+"','"+
				                 studentpasswordt.getText().trim()+"','"+
				                 studentnamet.getText().trim()+"','"+
				                 studentsubjectt.getSelectedItem().toString()+"')";
				    
				     studentcon.adding("student", addstr);
				 }

			 }
		 }

		 studentcon.Close();
		 clearRows();
		 showitem();

		 studentnumbert.setEditable(true);
		 studentpasswordt.setEditable(false);
		 studentnamet.setEditable(false);
		 studentsubjectt.setEditable(false);
		 studentnumbert.setText("");
		 studentpasswordt.setText("");
		 studentnamet.setText("");
		 studentsubjectt.setSelectedItem(null);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 private boolean Isexit(String number){
		 boolean exit = false;
		 studentcon = new ConnectData("student");
		 rs = studentcon.getResultSet();
			try{
				rs.beforeFirst();
				while(rs.next()){
					if(rs.getString(1).equals(number)){
						//已存在该帐号的学生
						exit = true;
						rs.last();
					}
			   }
			 }catch(SQLException es){
		            System.out.println(es);
		        }
		 return exit;
	 }
	 private void clearRows(){
		 int n = dm.getRowCount();
		 for(int i = n-1;i>=0 ;i--){
			 dm.removeRow(i);
		 }
	 }

	 private void showitem(){
		studentcon = new ConnectData("student");
		studentsrs = studentcon.getResultSet();
		try{
			studentsrs.beforeFirst();
			while(studentsrs.next()){
					String[] oneitem={"","","",""};
	        		oneitem[0] = studentsrs.getString(1);
	        		oneitem[1] = studentsrs.getString(2);
	        		oneitem[2] = studentsrs.getString(3);
	        		oneitem[3] = studentsrs.getString(4);
	        		dm.addRow(oneitem);
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		 }
}
