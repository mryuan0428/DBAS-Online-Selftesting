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
	String department;//接受数据库中该学生的专业字符串
	int[] subjects={0,0,0,0,0,0,0,0};
	JComboBox subjectsComboBox;//存放所有课程选项
	JComboBox typeComboBox;//存放所有试题类型

	String selectedsubject;//存放当前选中的课程名
	String selectedtype;//存放当前选中的题型

	int ID;//课程所属科目
	int time=0;//课程设置次数

	ResultSet subjectrs;//用来接收课程数据库

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
		int i = 0;// 记录课程数
		try{
			while(subjectrs.next()){
				String getdepartment = subjectrs.getString("所属专业");
				if(getdepartment.equals(department)){
					subjectsComboBox.addItem(subjectrs.getString("名称"));
					subjects[i++]=subjectrs.getInt("所属科目");
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
		subjectchoice.add(new JLabel("选择课程"));

		getsubjects();

		subjectsComboBox.setSelectedItem(null);
		subjectsComboBox.addActionListener(new subjectsComboBox_actionAdapter(this));
		subjectchoice.add(subjectsComboBox);
		container.add(subjectchoice);

		typechoice = new JPanel();
		typechoice.setBounds(x/2,0, x/2, 50);
		typeComboBox = new JComboBox();
		typechoice.add(new JLabel("选择题型：  "));

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
		         if(selectedtype.equals("单选题")){
		    		singleitem = new SingleChoiceItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("填空题")){
		        	 blankfill  = new BlankFillItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("判断题")){
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
		         if(selectedtype.equals("单选题")){
		    		singleitem = new SingleChoiceItem(frame,dopanel,ID);
		          }
		         else if(selectedtype.equals("填空题")){
		        	 blankfill  = new BlankFillItem(frame,dopanel,ID);
		         }
		         else if(selectedtype.equals("判断题")){
		        	 truefalse = new TrueFalseItem(frame,dopanel,ID);
		         }
		     }
		 }
   }
}

