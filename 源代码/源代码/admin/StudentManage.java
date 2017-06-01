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
	DefaultTableModel dm;//�������
	ConnectData  studentcon;
	ResultSet studentsrs;//��������ѧ�����ݿ�
	ResultSet rs;
	int mindoradd;//��ʶ�޸Ļ���Ӳ�����0Ϊ�޸�1Ϊ���

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

	JButton search;//"��ѯ"��ť
	JButton mind;//"�޸�"��ť
	JButton cancel;//"ɾ��"��ť
	JButton adding;//"����"��ť
	JButton save;//"����"��ť
	JButton exit;//"ȡ��"��ť

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
		String[] cols = {"�ʺ�","����","����","רҵ"};//����
		dm = new DefaultTableModel(cols,0);
		student = new JTable(dm);
		students.setViewportView(student);//�������ӵ��������
		students.setHorizontalScrollBarPolicy(32);//����ˮƽ������һֱ��ʾ
		students.setVerticalScrollBarPolicy(22);//���ô�ֱ������һֱ��ʾ
		dopanel.add(students);

		studentnumberl = new JLabel("�ʺ�");
		studentpasswordl = new JLabel("����");

		studentnamel = new JLabel("����");
		studentsubjectl = new JLabel("רҵ");

		studentnumbert = new JTextField(10);//"���"�ı���
		studentnumbert.setEditable(true);
		studentpasswordt = new JTextField(10);
		studentpasswordt.setEditable(false);
		studentnamet = new JTextField(10);
		studentnamet.setEditable(false);
		studentsubjectt = new JComboBox();//"��ȷ��"�ı���
		studentsubjectt.addItem("�������ѧ�뼼��");
		studentsubjectt.addItem("��Ϣ����");
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

		search = new JButton("��ѯ");//"��ѯ"��ť
		mind = new JButton("�޸�");//"�޸�"��ť
		cancel = new JButton("ɾ��");//"ɾ��"��ť
		adding = new JButton("����");//"����"��ť
		save = new JButton("����");//"����"��ť
		exit = new JButton("ȡ��");//"ȡ��"��ť
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ��ѯ��ѧ���ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = studentcon.search("student", "�ʺ�", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ�޸ĵ�ѧ���ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = studentcon.search("student", "�ʺ�", number);
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
				  JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫɾ����ѧ���ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = studentcon.search("student", "�ʺ�", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
				 return;
			 }
			 studentcon.cancel("student", "�ʺ�", number);
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
			 //�޸ĺ󱣴�
			 String setstr = "���� = '" + studentpasswordt.getText().trim() +
			                 "',���� = '" + studentnamet.getText().trim() +
			                 "',רҵ = '" + studentsubjectt.getSelectedItem().toString()+"'";
			 studentcon.mind("student", setstr,"�ʺ�", number);
		 }else{
			 //��Ӻ󱣴�
			 if(studentnumbert.getText().trim().equals("")||
					 studentpasswordt.getText().trim().equals("")||
					 studentnamet.getText().trim().equals("")||
					 studentsubjectt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"��Ϣ������������дȫ����Ϣ��");
				 return;
			 }else{
				 if(Isexit(studentnumbert.getText().trim())){
					 JOptionPane.showMessageDialog(null,"���ʺ��Ѵ��ڣ�");
					 studentnumbert.setText("");
					 studentpasswordt.setText("");
					 studentnamet.setText("");
					 studentsubjectt.setSelectedItem(null);
					 return;
				 }
				 else{
					 String addstr = " (�ʺ�,����,����,רҵ)  values ('" +
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
						//�Ѵ��ڸ��ʺŵ�ѧ��
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
