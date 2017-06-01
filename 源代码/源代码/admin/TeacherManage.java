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
	DefaultTableModel dm;//�������
	ConnectData  teachercon;
	ResultSet teachersrs;//��������ѧ�����ݿ�
	ResultSet rs;
	int mindoradd;//��ʶ�޸Ļ���Ӳ�����0Ϊ�޸�1Ϊ���

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

	JButton search;//"��ѯ"��ť
	JButton mind;//"�޸�"��ť
	JButton cancel;//"ɾ��"��ť
	JButton adding;//"����"��ť
	JButton save;//"����"��ť
	JButton exit;//"ȡ��"��ť

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
		String[] cols = {"�˺�","����","����","�γ�","רҵ"};//����
		dm = new DefaultTableModel(cols,0);
		teacher = new JTable(dm);
		teachers.setViewportView(teacher);//�������ӵ��������
		teachers.setHorizontalScrollBarPolicy(32);//����ˮƽ������һֱ��ʾ
		teachers.setVerticalScrollBarPolicy(22);//���ô�ֱ������һֱ��ʾ
		dopanel.add(teachers);

		teachernumberl = new JLabel("�˺�");
		teacherpasswordl = new JLabel("����");

		teachernamel = new JLabel("����");
                teacherclassl=new JLabel("�γ�");
		teachersubjectl = new JLabel("רҵ");

		teachernumbert = new JTextField(10);//"���"�ı���
		teachernumbert.setEditable(true);
		teacherpasswordt = new JTextField(10);
		teacherpasswordt.setEditable(false);
		teachernamet = new JTextField(10);
		teachernamet.setEditable(false);
                teacherclasst=new JTextField(10);
                teacherclasst.setEditable(false);
		teachersubjectt = new JComboBox();//"��ȷ��"�ı���
		teachersubjectt.addItem("�������ѧ�뼼��");
		teachersubjectt.addItem("��Ϣ����");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ��ѯ�Ľ�ʦ�ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = teachercon.search("teacher", "�ʺ�", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ�޸ĵĽ�ʦ�ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = teachercon.search("teacher", "�ʺ�", number);
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
				  JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫɾ���Ľ�ʦ�ʺţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = teachercon.search("teacher", "�ʺ�", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
				 return;
			 }
			 teachercon.cancel("teacher", "�ʺ�", number);
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
			 //�޸ĺ󱣴�
			 String setstr = "���� = '" + teacherpasswordt.getText().trim() +
			                 "',���� = '" + teachernamet.getText().trim() +
                                         "',�γ� = '" + teacherclasst.getText().trim() +
			                 "',רҵ = '" + teachersubjectt.getSelectedItem().toString()+"'";
			 teachercon.mind("teacher", setstr,"�ʺ�", number);
		 }else{
			 //��Ӻ󱣴�
			 if(teachernumbert.getText().trim().equals("")||
					 teacherpasswordt.getText().trim().equals("")||
					 teachernamet.getText().trim().equals("")||
                                         teacherclasst.getText().trim().equals("")||
					 teachersubjectt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"��Ϣ������������дȫ����Ϣ��");
				 return;
			 }else{
				 if(Isexit(teachernumbert.getText().trim())){
					 JOptionPane.showMessageDialog(null,"���ʺ��Ѵ��ڣ�");
					 teachernumbert.setText("");
					 teacherpasswordt.setText("");
					 teachernamet.setText("");
					 teachersubjectt.setSelectedItem(null);
					 return;
				 }
				 else{
					 String addstr = " (�ʺ�,����,����,�γ�,רҵ)  values ('" +
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
						//�Ѵ��ڸ��ʺŵĽ�ʦ
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
