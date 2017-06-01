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
public class TeacherManage {
Manage frame;
	JPanel dopanel;
	JScrollPane teachers;
	JTable teacher;
	DefaultTableModel dm;//表格类型
	ConnectData  teachercon;
	ResultSet teachersrs;//用来接收学生数据库
	ResultSet rs;
	int mindoradd;//标识修改或添加操作，0为修改1为添加

	JPanel teachernumber;
	JPanel teacherpassword;
	JPanel teachername;
        JPanel teacherclass;
	JPanel teachersubject;
	JPanel buttonpanel;

	JLabel teachernumberl;
	JLabel teacherpasswordl;
	JLabel teachernamel;
        JLabel teacherclassl;
	JLabel teachersubjectl;

	JTextField teachernumbert;
	JTextField teacherpasswordt;
	JTextField teachernamet;
        JTextField teacherclasst;
	JComboBox teachersubjectt;

	JButton search;//"查询"按钮
	JButton mind;//"修改"按钮
	JButton cancel;//"删除"按钮
	JButton adding;//"增加"按钮
	JButton save;//"保存"按钮
	JButton exit;//"取消"按钮

	public TeacherManage(Manage frame,JPanel dopanel){
		this.frame = frame;
	    this.dopanel = dopanel;
	    dopanel.setLayout(null);
	    panel();
	    showitem();
	}
	private void panel(){
		teachers = new JScrollPane();
		teachers.setBounds(0, 0, 600, 300);
		String[] cols = {"账号","密码","姓名","课程","专业"};//列名
		dm = new DefaultTableModel(cols,0);
		teacher = new JTable(dm);
		teachers.setViewportView(teacher);//将表格添加到滚动面板
		teachers.setHorizontalScrollBarPolicy(32);//设置水平滚动条一直显示
		teachers.setVerticalScrollBarPolicy(22);//设置垂直滚动条一直显示
		dopanel.add(teachers);

		teachernumberl = new JLabel("账号");
		teacherpasswordl = new JLabel("密码");

		teachernamel = new JLabel("姓名");
                teacherclassl=new JLabel("课程");
		teachersubjectl = new JLabel("专业");

		teachernumbert = new JTextField(10);//"题号"文本框
		teachernumbert.setEditable(true);
		teacherpasswordt = new JTextField(10);
		teacherpasswordt.setEditable(false);
		teachernamet = new JTextField(10);
		teachernamet.setEditable(false);
                teacherclasst=new JTextField(10);
                teacherclasst.setEditable(false);
		teachersubjectt = new JComboBox();//"正确答案"文本框
		teachersubjectt.addItem("计算机科学与技术");
		teachersubjectt.addItem("信息管理");
		teachersubjectt.setSelectedItem(null);
		teachersubjectt.setEditable(false);

		teachernumber = new JPanel();
		teachernumber.setBounds(3, 300, 150, 30);
		teacherpassword = new JPanel();
		teacherpassword.setBounds(3, 335, 150, 30);
		teachername = new JPanel();
		teachername.setBounds(3,370,150, 30);
                teacherclass=new JPanel();
                teacherclass.setBounds(3,415,150, 30);
		teachersubject = new JPanel();
		teachersubject.setBounds(3,450,200, 30);
		buttonpanel = new JPanel();
		buttonpanel.setBounds(3,500,400, 100);

		teachernumber.add(teachernumberl);
		teachernumber.add(teachernumbert);
		teacherpassword.add(teacherpasswordl);
		teacherpassword.add(teacherpasswordt);
		teachername.add(teachernamel);
		teachername.add(teachernamet);
                teacherclass.add(teacherclassl);
                teacherclass.add(teacherclasst);
		teachersubject.add(teachersubjectl);
		teachersubject.add(teachersubjectt);


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

		dopanel.add(teachernumber);
		dopanel.add(teacherpassword);
		dopanel.add(teachername);
                dopanel.add(teacherclass);
		dopanel.add(teachersubject);
		dopanel.add(buttonpanel);

		frame.setVisible(true);
	}
	class search_actionAdapter implements ActionListener{
		TeacherManage adaptee;
		 search_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.search_actionPerformed(e);
	    }
	}
	 public void search_actionPerformed(ActionEvent e){
		 String number = teachernumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要查询的教师帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = teachercon.search("teacher", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					teacherpasswordt.setText(rs.getString(2));
					teachernamet.setText(rs.getString(3));
                                        teacherclasst.setText(rs.getString(4));
					teachersubjectt.setSelectedItem(rs.getString(5));
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
		 TeacherManage adaptee;
		 mind_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.mind_actionPerformed(e);
	    }
	}
	 public void mind_actionPerformed(ActionEvent e){
		 mindoradd = 0;
		 String number = teachernumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要修改的教师帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = teachercon.search("teacher", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					teacherpasswordt.setText(rs.getString(2));
					teachernamet.setText(rs.getString(3));
                                        teacherclasst.setText(rs.getString(4));
					teachersubjectt.setSelectedItem(rs.getString(5));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				  JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }else{
				 teacherpasswordt.setEditable(true);
				 teachernamet.setEditable(true);
				 teachersubjectt.setEditable(true);
			     save.setEnabled(true);
			     exit.setEnabled(true);
			 }
		 }
	 }
	 class cancel_actionAdapter implements ActionListener{
		TeacherManage adaptee;
		 cancel_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.cancel_actionPerformed(e);
	    }
	}
	 public void cancel_actionPerformed(ActionEvent e){
		 String number = teachernumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要删除的教师帐号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = teachercon.search("teacher", "帐号", number);
			 try{
				while(rs.next()){
					i ++;
					teacherpasswordt.setText(rs.getString(2));
					teachernamet.setText(rs.getString(3));
                                        teacherclasst.setText(rs.getString(4));
					teachersubjectt.setSelectedItem(rs.getString(5));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }
			 teachercon.cancel("teacher", "帐号", number);
			 teachercon.Close();
			 clearRows();
			 showitem();
			 teachernumbert.setText("");
			 teacherpasswordt.setText("");
			 teachernamet.setText("");
			 teachersubjectt.setSelectedItem(null);
		 }
	 }
	 class adding_actionAdapter implements ActionListener{
		 TeacherManage adaptee;
		 adding_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.adding_actionPerformed(e);
	    }
	}
	 public void adding_actionPerformed(ActionEvent e){
		 mindoradd = 1;
	     teachernumbert.setText("");
		 teacherpasswordt.setText("");
		 teachernamet.setText("");
		 teachersubjectt.setSelectedItem(null);
		 teachernumbert.setEditable(true);
		 teacherpasswordt.setEditable(true);
		 teachernamet.setEditable(true);
		 teachersubjectt.setEditable(true);
		 save.setEnabled(true);
		 exit.setEnabled(true);
	 }
	 class exit_actionAdapter implements ActionListener{
		 TeacherManage adaptee;
		 exit_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.exit_actionPerformed(e);
	    }
	 }
	 public void exit_actionPerformed(ActionEvent e){
		 teachernumbert.setText("");
		 teacherpasswordt.setText("");
		 teachernamet.setText("");
		 teachersubjectt.setSelectedItem(null);
		 teachernumbert.setEditable(true);
		 teacherpasswordt.setEditable(false);
		 teachernamet.setEditable(false);
		 teachersubjectt.setEditable(false);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 class save_actionAdapter implements ActionListener{
		 TeacherManage adaptee;
		 save_actionAdapter(TeacherManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.save_actionPerformed(e);
	    }
	}
	 public void save_actionPerformed(ActionEvent e){
		 String number = teachernumbert.getText().trim();
		 if(mindoradd == 0){
			 //修改后保存
			 String setstr = "密码 = '" + teacherpasswordt.getText().trim() +
			                 "',姓名 = '" + teachernamet.getText().trim() +
                                         "',课程 = '" + teacherclasst.getText().trim() +
			                 "',专业 = '" + teachersubjectt.getSelectedItem().toString()+"'";
			 teachercon.mind("teacher", setstr,"帐号", number);
		 }else{
			 //添加后保存
			 if(teachernumbert.getText().trim().equals("")||
					 teacherpasswordt.getText().trim().equals("")||
					 teachernamet.getText().trim().equals("")||
                                         teacherclasst.getText().trim().equals("")||
					 teachersubjectt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"信息不完整！请填写全部信息！");
				 return;
			 }else{
				 if(Isexit(teachernumbert.getText().trim())){
					 JOptionPane.showMessageDialog(null,"该帐号已存在！");
					 teachernumbert.setText("");
					 teacherpasswordt.setText("");
					 teachernamet.setText("");
					 teachersubjectt.setSelectedItem(null);
					 return;
				 }
				 else{
					 String addstr = " (帐号,密码,姓名,课程,专业)  values ('" +
				                 teachernumbert.getText().trim()+"','"+
				                 teacherpasswordt.getText().trim()+"','"+
				                 teachernamet.getText().trim()+"','"+
                                                 teacherclasst.getText().trim()+"','"+
				                 teachersubjectt.getSelectedItem().toString()+"')";
				     teachercon.adding("teacher", addstr);
				 }

			 }
		 }

		 teachercon.Close();
		 clearRows();
		 showitem();

		 teachernumbert.setEditable(true);
		 teacherpasswordt.setEditable(false);
		 teachernamet.setEditable(false);
		 teachersubjectt.setEditable(false);
		 teacherpasswordt.setText("");
		 teachernamet.setText("");
		 teacherclasst.setText("");
		 teachersubjectt.setSelectedItem(null);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 private boolean Isexit(String number){
		 boolean exit = false;
		 teachercon = new ConnectData("teacher");
		 rs = teachercon.getResultSet();
			try{
				rs.beforeFirst();
				while(rs.next()){
					if(rs.getString(1).equals(number)){
						//已存在该帐号的教师
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
		teachercon = new ConnectData("teacher");
		teachersrs = teachercon.getResultSet();
		try{
			teachersrs.beforeFirst();
			while(teachersrs.next()){
					String[] oneitem={"","","","",""};
	        		oneitem[0] = teachersrs.getString(1);
	        		oneitem[1] = teachersrs.getString(2);
	        		oneitem[2] = teachersrs.getString(3);
	        		oneitem[3] = teachersrs.getString(4);
                                oneitem[4] = teachersrs.getString(5);
	        		dm.addRow(oneitem);
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		 }
}
