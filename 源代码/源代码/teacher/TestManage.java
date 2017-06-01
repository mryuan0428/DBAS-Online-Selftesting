package teacher;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



import com.ConnectData;



@SuppressWarnings("serial")
public class TestManage {
	Interface frame;
	Container container;
	JPanel subjectchoice;
	JPanel typechoice;
	JPanel dopanel;
	String subject;//�������ݿ��иý�ʦ�����пγ���ɵ��ַ���
	String[] subjects={"","","","","","",""};//������пγ̣��Կγ̴������ʽ
	JComboBox subjectsComboBox;//������пγ�ѡ��
	JComboBox typeComboBox;//���������������

	String selectedsubject;//��ŵ�ǰѡ�еĿγ���
	String selectedtype;//��ŵ�ǰѡ�е�����

	int ID;//�γ�������Ŀ
	int time=0;//�γ����ô���

	ResultSet subjectrs;//�������տγ����ݿ�

	SingleItem singleitem=null;
	BlankItem blankitem = null;
	TrueItem truefalseitem = null;

	public TestManage(Interface frame, Container container,String subject){
		this.frame = frame;
		this.container = container;
		container.setLayout(null);
		this.subject = subject;

		menu();
		//table();

		frame.setVisible(true);

	}

	private void  menu(){
		int x = container.getWidth();
	    int y = container.getHeight();

	    subjectchoice = new JPanel();
	    subjectchoice.setBounds(0, 0, x/2, 50);
		subjectsComboBox = new JComboBox();
		subjectchoice.add(new JLabel("ѡ��γ�"));

		//��Ӹý�ʦ���̵����пγ�
		ConnectData  subject1 = new ConnectData("subject");
		subjectrs = subject1.getResultSet();
		
		for( int i= 0,j=0 ;i<subject.length(); i+=11,j++)
		{
			subjects[j] = subject.substring(i, i+10);
			try{
				subjectrs.beforeFirst();
				while(subjectrs.next()){
					if(subjectrs.getString("����").equals(subjects[j])){
						subjectsComboBox.addItem(subjectrs.getString("����"));
						subjectrs.last();
					}
				}
				}catch(SQLException es){
						System.out.println(es);
				}
		}

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
		 TestManage adaptee;
		 subjectsComboBox_actionAdapter(TestManage adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.subjectsComboBox_actionPerformed(e);
	    }
	}

	private void  getIdofsubject(){
		ConnectData  subject = new ConnectData("subject");
		subjectrs = subject.getResultSet();
		ID = 0;
		try{
			while(subjectrs.next()){
				String subjectnumber = subjectrs.getString("����");
				if(subjectnumber.equals(selectedsubject)){
					//�ҵ���ǰ�γ�
					ID = subjectrs.getInt("������Ŀ");
	                subjectrs.last();
				}
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
	}

	public void subjectsComboBox_actionPerformed(ActionEvent e){
		 if(time!= 0){
			 try {
						singleitem.Close();
						blankitem.Close();
						truefalseitem.Close();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 }
		 selectedsubject = subjectsComboBox.getSelectedItem().toString();
		 if(subjectsComboBox.getSelectedItem()!=null){
			 getIdofsubject();
		     if(typeComboBox.getSelectedItem()!=null){
		    	 time++;
			     selectedtype = typeComboBox.getSelectedItem().toString();
		         if(selectedtype.equals("��ѡ��")){
		    		singleitem = new SingleItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("�����")){
		        	 blankitem = new BlankItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("�ж���")){
		        	 truefalseitem = new TrueItem(frame,dopanel,ID);
		         }
		     }
		 }
  }
	class typeComboBox_actionAdapter implements ActionListener{
		 TestManage adaptee;
		 typeComboBox_actionAdapter(TestManage adaptee){
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
				 blankitem.Close();
				 truefalseitem.Close();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 }
		 selectedsubject = subjectsComboBox.getSelectedItem().toString();
		 if(subjectsComboBox.getSelectedItem()!=null){
			 getIdofsubject();
		     if(typeComboBox.getSelectedItem()!=null){
		    	 time++;
			     selectedtype = typeComboBox.getSelectedItem().toString();
		         if(selectedtype.equals("��ѡ��")){
		    		singleitem = new SingleItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("�����")){
		        	 blankitem = new BlankItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("�ж���")){
		        	 truefalseitem = new TrueItem(frame,dopanel,ID);
		         }
		     }
		 }
   }
	/*public static void main(String args[]){
        JDialog.setDefaultLookAndFeelDecorated(true);
        @SuppressWarnings("unused")
 		Font font=new Font("JFrame",Font.PLAIN,14);
        new TestManage();
        }*/

}
