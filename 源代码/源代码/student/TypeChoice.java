package student;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ConnectData;
import teacher.Interface;

public class TypeChoice {
	JFrame frame;
	Container container;
	JPanel subjectchoice;
	JPanel typechoice;
	JPanel dopanel;
	String department;//�������ݿ��и�ѧ����רҵ�ַ���
	int[] subjects={0,0,0,0,0,0,0,0};
	JComboBox subjectsComboBox;//������пγ�ѡ��
	JComboBox typeComboBox;//���������������

	String selectedsubject;//��ŵ�ǰѡ�еĿγ���
	String selectedtype;//��ŵ�ǰѡ�е�����

	int ID;//�γ�������Ŀ
	int time=0;//�γ����ô���

	ResultSet subjectrs;//�������տγ����ݿ�

	SingleChoiceItem singleitem=null;
	BlankFillItem blankfill = null;
	TrueFalseItem truefalse = null;

	public TypeChoice(ShowItem frame, Container container,String department){
		this.frame = frame;
		this.container = container;
		container.setLayout(null);
		this.department = department;

		menu();

		frame.setVisible(true);
	}
	
	public TypeChoice(Interface frame, Container container,String department){
		this.frame = frame;
		this.container = container;
		container.setLayout(null);
		this.department = department;

		menu();

		frame.setVisible(true);
	}
	private void getsubjects(){
		ConnectData  subject = new ConnectData("subject");
		subjectrs = subject.getResultSet();
		int i = 0;// ��¼�γ���
		try{
			while(subjectrs.next()){
				String getdepartment = subjectrs.getString("����רҵ");
				if(getdepartment.equals(department)){
					subjectsComboBox.addItem(subjectrs.getString("����"));
					subjects[i++]=subjectrs.getInt("������Ŀ");
				}
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
	}
	private void  menu(){
		int x = container.getWidth();
	    int y = container.getHeight();

	    subjectchoice = new JPanel();
	    subjectchoice.setBounds(0, 0, x/2, 50);
		subjectsComboBox = new JComboBox();
		subjectchoice.add(new JLabel("ѡ��γ�"));

		getsubjects();

		subjectsComboBox.setSelectedItem(null);
		subjectsComboBox.addActionListener(new subjectsComboBox_actionAdapter(this));
		subjectchoice.add(subjectsComboBox);
		container.add(subjectchoice);

		typechoice = new JPanel();
		typechoice.setBounds(x/2,0, x/2, 50);
		typeComboBox = new JComboBox();
		typechoice.add(new JLabel("ѡ�����ͣ�  "));

		typeComboBox.addItem("��ѡ��");
		typeComboBox.addItem("�ж���");
		typeComboBox.addItem("�����");

		typeComboBox.setSelectedItem(null);
		typeComboBox.addActionListener(new typeComboBox_actionAdapter(this));
		typechoice.add(typeComboBox);
		container.add(typechoice);

		dopanel = new JPanel();
		dopanel.setBounds(0, 50, x, y-50);
		container.add(dopanel);
	}
	class subjectsComboBox_actionAdapter implements ActionListener{
		 TypeChoice adaptee;
		 subjectsComboBox_actionAdapter(TypeChoice adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.subjectsComboBox_actionPerformed(e);
	    }
	}
	 public void subjectsComboBox_actionPerformed(ActionEvent e){
		 if(time!= 0){
			 try {
						singleitem.Close();
						blankfill.Close();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 }
		 selectedsubject = subjectsComboBox.getSelectedItem().toString();
		 if(subjectsComboBox.getSelectedItem()!=null){
			 ID=subjects[subjectsComboBox.getSelectedIndex()];
		     if(typeComboBox.getSelectedItem()!=null){
		    	 time++;
			     selectedtype = typeComboBox.getSelectedItem().toString();
		         if(selectedtype.equals("��ѡ��")){
		    		singleitem = new SingleChoiceItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("�����")){
		        	 blankfill  = new BlankFillItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("�ж���")){
		        	 truefalse = new TrueFalseItem(frame,dopanel,ID);
		         }
		     }
		 }
  }
	class typeComboBox_actionAdapter implements ActionListener{
		TypeChoice adaptee;
		 typeComboBox_actionAdapter(TypeChoice adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.typeComboBox_actionPerformed(e);
	    }
	}
	 public void typeComboBox_actionPerformed(ActionEvent e){
		 if(time!= 0){
			 try {
				 singleitem.Close();
				 blankfill.Close();
				 truefalse.Close();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 }
		 selectedsubject = subjectsComboBox.getSelectedItem().toString();
		 if(subjectsComboBox.getSelectedItem()!=null){
			 ID=subjects[subjectsComboBox.getSelectedIndex()];
		     if(typeComboBox.getSelectedItem()!=null){
		    	 time++;
			     selectedtype = typeComboBox.getSelectedItem().toString();
		         if(selectedtype.equals("��ѡ��")){
		    		singleitem = new SingleChoiceItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("�����")){
		        	 blankfill  = new BlankFillItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("�ж���")){
		        	 truefalse = new TrueFalseItem(frame,dopanel,ID);
		         }
		     }
		 }
   }
}

