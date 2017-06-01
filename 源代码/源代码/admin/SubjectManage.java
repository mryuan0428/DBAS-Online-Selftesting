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
public class SubjectManage {
Manage frame;
	JPanel dopanel;
	JScrollPane subjects;
	JTable subject;
	DefaultTableModel dm;//�������
	ConnectData  subjectcon;
	ResultSet subjectsrs;//�������տ�Ŀ���ݿ�
	ResultSet rs;
	int mindoradd;//��ʶ�޸Ļ���Ӳ�����0Ϊ�޸�1Ϊ���

	JPanel subjectnumber;
	//JPanel subjectpassword;
	JPanel subjectname;
        JPanel subjectclass;
	JPanel kemusubject;
	JPanel buttonpanel;

	JLabel subjectnumberl;
	//JLabel subjectpasswordl;
	JLabel subjectnamel;
        JLabel subjectclassl;
	JLabel kemusubjectl;

	JTextField subjectnumbert;
	//JTextField subjectpasswordt;
	JTextField subjectnamet;
	JComboBox subjectclasst;
	JComboBox kemusubjectt;

	JButton search;//"��ѯ"��ť
	JButton mind;//"�޸�"��ť
	JButton cancel;//"ɾ��"��ť
	JButton adding;//"����"��ť
	JButton save;//"����"��ť
	JButton exit;//"ȡ��"��ť

	public SubjectManage(Manage frame,JPanel dopanel){
		this.frame = frame;
	    this.dopanel = dopanel;
	    dopanel.setLayout(null);
	    panel();

	    showitem();
	}
	private void panel(){
		subjects = new JScrollPane();
		subjects.setBounds(0, 0, 600, 300);
		String[] cols = {"����","����","רҵ","��Ŀ"};//����
		dm = new DefaultTableModel(cols,0);
		subject = new JTable(dm);
		subjects.setViewportView(subject);//�������ӵ��������
		subjects.setHorizontalScrollBarPolicy(32);//����ˮƽ������һֱ��ʾ
		subjects.setVerticalScrollBarPolicy(22);//���ô�ֱ������һֱ��ʾ
		dopanel.add(subjects);

		subjectnumberl = new JLabel("����");
		//subjectpasswordl = new JLabel("����");

		subjectnamel = new JLabel("����");
        subjectclassl=new JLabel("רҵ");
		kemusubjectl = new JLabel("��Ŀ");

		subjectnumbert = new JTextField(10);//"���"�ı���
		subjectnumbert.setEditable(true);
		//subjectpasswordt = new JTextField(10);
		//subjectpasswordt.setEditable(false);
		subjectnamet = new JTextField(10);
		subjectnamet.setEditable(false);
        subjectclasst=new JComboBox();
        subjectclasst.addItem("�������ѧ�뼼��");
        subjectclasst.addItem("��Ϣ����");
        subjectclasst.setSelectedItem(null);
		kemusubjectt = new JComboBox();//"��ȷ��"�ı���
		kemusubjectt.addItem("1               ");
		kemusubjectt.addItem("2               ");
		kemusubjectt.setSelectedItem(null);
		kemusubjectt.setEditable(false);

		subjectnumber = new JPanel();
		subjectnumber.setBounds(3, 300, 150, 30);
		//subjectpassword = new JPanel();
		//subjectpassword.setBounds(3, 335, 150, 30);
		subjectname = new JPanel();
		subjectname.setBounds(3,335,150, 30);
        subjectclass=new JPanel();
        subjectclass.setBounds(3,370,200, 30);
		kemusubject = new JPanel();
		kemusubject.setBounds(3,415,200, 30);
		buttonpanel = new JPanel();
		buttonpanel.setBounds(3,500,400, 100);

		subjectnumber.add(subjectnumberl);
		subjectnumber.add(subjectnumbert);
		subjectname.add(subjectnamel);
		subjectname.add(subjectnamet);
        subjectclass.add(subjectclassl);
        subjectclass.add(subjectclasst);
		kemusubject.add(kemusubjectl);
		kemusubject.add(kemusubjectt);


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

		dopanel.add(subjectnumber);
		//dopanel.add(subjectpassword);
		dopanel.add(subjectname);
                dopanel.add(subjectclass);
		dopanel.add(kemusubject);
		dopanel.add(buttonpanel);

		frame.setVisible(true);
	}
	class search_actionAdapter implements ActionListener{
		SubjectManage adaptee;
		 search_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.search_actionPerformed(e);
	    }
	}
	 public void search_actionPerformed(ActionEvent e){
		 String number = subjectnumbert.getText().trim();
		 if(number.equals("")){
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ��ѯ�ı���ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = subjectcon.search("subject", "����", number);
			 try{
				while(rs.next()){
					i ++;
					subjectnamet.setText(rs.getString(2));
                    subjectclasst.setSelectedItem(rs.getString(3));
					kemusubjectt.setSelectedItem(rs.getString(4));
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
		 SubjectManage adaptee;
		 mind_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.mind_actionPerformed(e);
	    }
	}
	 public void mind_actionPerformed(ActionEvent e){
		 mindoradd = 0;
		 String number = subjectnumbert.getText().trim();
		 if(number.equals("")){
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ�޸ĵı���ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = subjectcon.search("subject", "����", number);
			 try{
				while(rs.next()){
					i ++;
					subjectnamet.setText(rs.getString(2));
                    subjectclasst.setSelectedItem(rs.getString(3));
					kemusubjectt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				  JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
				 return;
			 }else{
				 //subjectpasswordt.setEditable(true);
				 subjectnamet.setEditable(true);
				 kemusubjectt.setEditable(true);
			     save.setEnabled(true);
			     exit.setEnabled(true);
			 }
		 }
	 }
	 class cancel_actionAdapter implements ActionListener{
		SubjectManage adaptee;
		 cancel_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.cancel_actionPerformed(e);
	    }
	}
	 public void cancel_actionPerformed(ActionEvent e){
		 String number = subjectnumbert.getText().trim();
		 if(number.equals("")){
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫɾ���ı���ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = subjectcon.search("subject", "����", number);
			 try{
				while(rs.next()){
					i ++;
					subjectnamet.setText(rs.getString(2));
                    subjectclasst.setSelectedItem(rs.getString(3));
					kemusubjectt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
				 return;
			 }
			 subjectcon.cancel("subject", "����", number);
			 subjectcon.Close();
			 clearRows();
			 showitem();
			 subjectnumbert.setText("");
			 subjectnamet.setText("");
			 kemusubjectt.setSelectedItem(null);
		 }
	 }
	 class adding_actionAdapter implements ActionListener{
		 SubjectManage adaptee;
		 adding_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.adding_actionPerformed(e);
	    }
	}
	 public void adding_actionPerformed(ActionEvent e){
		 mindoradd = 1;
	     subjectnumbert.setText("");
		 subjectnamet.setText("");
		 kemusubjectt.setSelectedItem(null);
		 subjectnumbert.setEditable(true);
		 subjectclasst.setEditable(true);
		 subjectclasst.setSelectedItem(null);
		 subjectnamet.setEditable(true);
		 kemusubjectt.setEditable(true);
		 save.setEnabled(true);
		 exit.setEnabled(true);
	 }
	 class exit_actionAdapter implements ActionListener{
		 SubjectManage adaptee;
		 exit_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.exit_actionPerformed(e);
	    }
	 }
	 public void exit_actionPerformed(ActionEvent e){
		 subjectnumbert.setText("");
		 //subjectpasswordt.setText("");
		 subjectnamet.setText("");
		 kemusubjectt.setSelectedItem(null);
		 subjectnumbert.setEditable(true);
		 //subjectpasswordt.setEditable(false);
		 subjectnamet.setEditable(false);
		 //subjectsubjectt.setEditable(false);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 class save_actionAdapter implements ActionListener{
		 SubjectManage adaptee;
		 save_actionAdapter(SubjectManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.save_actionPerformed(e);
	    }
	}
	 public void save_actionPerformed(ActionEvent e){
		 String number = subjectnumbert.getText().trim();
		 if(mindoradd == 0){
			 //�޸ĺ󱣴�
			 String setstr = 
			                 "���� = '" + subjectnamet.getText().trim() +
                                         "',����רҵ = '" + subjectclasst.getSelectedItem().toString().trim() +
			                 "',������Ŀ = '" + kemusubjectt.getSelectedItem().toString()+"'";
			 subjectcon.mind("subject", setstr,"����", number);
		 }else{
			 //��Ӻ󱣴�
			 if(subjectnumbert.getText().trim().equals("")||

					 subjectnamet.getText().trim().equals("")||
                                         subjectclasst.getSelectedItem().equals("")||
					 kemusubjectt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"��Ϣ������������дȫ����Ϣ��");
				 return;
			 }else{
				 if(Isexit(subjectnumbert.getText().trim())){
					 JOptionPane.showMessageDialog(null,"�ñ�����Ѵ��ڣ�");
					 subjectnumbert.setText("");
					 //subjectpasswordt.setText("");
					 subjectnamet.setText("");
					 kemusubjectt.setSelectedItem(null);
					 return;
				 }
				 else{
					 String addstr = " (����,����,����רҵ,������Ŀ)  values ('" +
				                 subjectnumbert.getText().trim()+"','"+
				                 //subjectpasswordt.getText().trim()+"','"+
				                 subjectnamet.getText().trim()+"','"+
                                 subjectclasst.getSelectedItem().toString().trim()+"','"+
				                 kemusubjectt.getSelectedItem().toString()+"')";
				     subjectcon.adding("subject", addstr);
				 }

			 }
		 }

		 subjectcon.Close();
		 clearRows();
		 showitem();

		 subjectnumbert.setEditable(true);
		 subjectnamet.setEditable(false);
		 subjectclasst.setEditable(false);
		 subjectnumbert.setText("");
		 subjectnamet.setText("");
		 subjectclasst.setSelectedItem(null);
		 kemusubjectt.setSelectedItem(null);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 private boolean Isexit(String number){
		 boolean exit = false;
		 subjectcon = new ConnectData("subject");
		 rs = subjectcon.getResultSet();
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
		subjectcon = new ConnectData("subject");
		subjectsrs = subjectcon.getResultSet();
		try{
			subjectsrs.beforeFirst();
			while(subjectsrs.next()){
					String[] oneitem={"","","",""};
	        		oneitem[0] = subjectsrs.getString(1);
	        		oneitem[1] = subjectsrs.getString(2);
	        		oneitem[2] = subjectsrs.getString(3);
	        		oneitem[3] = subjectsrs.getString(4);
                                
	        		dm.addRow(oneitem);
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		 }
}

