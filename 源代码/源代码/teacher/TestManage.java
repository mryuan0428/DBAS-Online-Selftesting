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
	String subject;//接受数据库中该教师的所有课程组成的字符串
	String[] subjects={"","","","","","",""};//存放所有课程，以课程代码的形式
	JComboBox subjectsComboBox;//存放所有课程选项
	JComboBox typeComboBox;//存放所有试题类型

	String selectedsubject;//存放当前选中的课程名
	String selectedtype;//存放当前选中的题型

	int ID;//课程所属科目
	int time=0;//课程设置次数

	ResultSet subjectrs;//用来接收课程数据库

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
		subjectchoice.add(new JLabel("选择课程"));

		//添加该教师所教的所有课程
		ConnectData  subject1 = new ConnectData("subject");
		subjectrs = subject1.getResultSet();
		
		for( int i= 0,j=0 ;i<subject.length(); i+=11,j++)
		{
			subjects[j] = subject.substring(i, i+10);
			try{
				subjectrs.beforeFirst();
				while(subjectrs.next()){
					if(subjectrs.getString("编码").equals(subjects[j])){
						subjectsComboBox.addItem(subjectrs.getString("名称"));
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
		typechoice.add(new JLabel("选择体型：  "));

		typeComboBox.addItem("单选题");
		typeComboBox.addItem("判断题");
		typeComboBox.addItem("填空题");

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
				String subjectnumber = subjectrs.getString("名称");
				if(subjectnumber.equals(selectedsubject)){
					//找到当前课程
					ID = subjectrs.getInt("所属科目");
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
		         if(selectedtype.equals("单选题")){
		    		singleitem = new SingleItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("填空题")){
		        	 blankitem = new BlankItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("判断题")){
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
		         if(selectedtype.equals("单选题")){
		    		singleitem = new SingleItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("填空题")){
		        	 blankitem = new BlankItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("判断题")){
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
