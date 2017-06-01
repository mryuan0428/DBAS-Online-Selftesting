package student;

import com.ConnectData;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class SingleChoiceItem {
	JFrame frame;
	JPanel dopanel;
	JScrollPane scrollpane,panelright,scorepane;//带滚动条的面板用来显示试题和答案,以及成绩
	JPanel panel,panelr;//用来显示每道题和答案

	JLabel question;//用来显示每一题的题目

	JPanel ans;//选项面板
	ButtonGroup buttongroup;//按钮组
	JRadioButton answerA, answerB,answerC,answerD;

	JPanel control;//按钮面板
	JButton next,OK;//下一题、提交按钮

	JList answer;//显示答案列表框
	@SuppressWarnings("unchecked")
	Vector vector;

	JTable score;//显示成绩
	DefaultTableModel dm;//表格类型

	ResultSet rs;//用来接收数据库
	 ConnectData  singlechoice;

	int[] Id={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int number = 0;
	int N = 0;//题库中题总数
	int donumber = 0;//本科目的题数
	int answerrightnumber = 0;

	String[] quesscore={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String[] answerlist={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String[] answerright={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};


	public SingleChoiceItem(JFrame frame,JPanel dopanel,int ID){
		this.frame = frame;
		this.dopanel = dopanel;
		dopanel.setLayout(null);

		panelright = new JScrollPane();
    	scrollpane = new JScrollPane();
    	scorepane = new JScrollPane();

        int x = dopanel.getWidth();
    	int y = dopanel.getHeight();
    	scrollpane.setBounds(5, 0,4*x/5, y-200);
    	panelright.setBounds(4*x/5+10,0,x/5,y-200);
    	scorepane.setBounds(5, y-200, x, 200);
    	leftpanel();//设置单选题面板
    	rightpanel();//设置答案列表框
    	scorepanel();
    	dopanel.add(scrollpane);
        dopanel.add(panelright);
        dopanel.add(scorepane);

        frame.setVisible(true);



        singlechoice = new ConnectData("SingleChoice",ID);
		rs = singlechoice.getResultSet();
		getdonumber();
		getN();
        testSinglechoice();//出题
	}
	private void getdonumber(){
		try{
			rs.beforeFirst();
			while(rs.next()){
				donumber++;
			}
		 }catch(SQLException es){
	            System.out.println(es);
	        }
	}
	private int getrandom(){
		int next = 0;
		Random r;
		int i;
		boolean exit = true;
		boolean same;
		while(exit){
			same = false;
			r = new Random();
			next = r.nextInt();
			if(next < 0 ) next = - next;
			next = next%N + 1;
			for(i= 0 ;i < number;i++){
				if(next == Id[i]) same = true;
			}
			if (same) exit = true;
			else exit = false;
		}
		return next;
	}
	private void getN(){
		 ConnectData  singlechoicegetn= new ConnectData("SingleChoice");
		 ResultSet rsrs = singlechoicegetn.getResultSet();
		try{
			while(rsrs.next()){
				int nn = Integer.parseInt(rsrs.getString(1));
				if(nn > N){
					N=nn;
				}
			}
		 }catch(SQLException es){
	            System.out.println(es);
	        }
	}
    public void Close() throws Throwable{
    	number = 0;
    	N = 0;
    	answerrightnumber = 0;
    	this.finalize();
    }
	class JRadioButtonA_actionAdapter implements ActionListener{
		private SingleChoiceItem adaptee;
		JRadioButtonA_actionAdapter(SingleChoiceItem adaptee){
			this.adaptee = adaptee;
		}
    	public void actionPerformed(ActionEvent e){
    		adaptee.JRadioButtonA_actionPerformed(e);
    	}
    }
    //单选按钮事件响应
    @SuppressWarnings("unchecked")
	public void JRadioButtonA_actionPerformed(ActionEvent e){
    	int n= number + 1;
    	if(answerlist[number].equals("")){
    		//第一次答此题
    		answerlist[number] = new String(answerA.getText());
    		vector.addElement("第"+n+"题答案："+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//修改答案
    		answerlist[number] = new String(answerA.getText());
    		vector.setElementAt("第"+n+"题答案："+answerlist[number], number);//修改答案
            answer.setListData(vector);
    	}
    }
    class JRadioButtonB_actionAdapter implements ActionListener{
		private SingleChoiceItem adaptee;
		JRadioButtonB_actionAdapter(SingleChoiceItem adaptee){
			this.adaptee = adaptee;
		}
    	public void actionPerformed(ActionEvent e){
    		adaptee.JRadioButtonB_actionPerformed(e);
    	}
    }
    //单选按钮事件响应
    @SuppressWarnings("unchecked")
	public void JRadioButtonB_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//第一次答此题
    		answerlist[number] = new String(answerB.getText());
    		vector.addElement("第"+n+"题答案："+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//修改答案
    		answerlist[number] = new String(answerB.getText());
    		vector.setElementAt("第"+n+"题答案："+answerlist[number], number);//修改答案
            answer.setListData(vector);
    	}
    }
    class JRadioButtonC_actionAdapter implements ActionListener{
		private SingleChoiceItem adaptee;
		JRadioButtonC_actionAdapter(SingleChoiceItem adaptee){
			this.adaptee = adaptee;
		}
    	public void actionPerformed(ActionEvent e){
    		adaptee.JRadioButtonC_actionPerformed(e);
    	}
    }
    //单选按钮事件响应
    @SuppressWarnings({ "unchecked", "unchecked" })
	public void JRadioButtonC_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//第一次答此题
    		answerlist[number] = new String(answerC.getText());
    		vector.addElement("第"+n+"题答案："+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//修改答案
    		answerlist[number] = new String(answerC.getText());
    		vector.setElementAt("第"+n+"题答案："+answerlist[number], number);//修改答案
            answer.setListData(vector);
    	}
    }
    class JRadioButtonD_actionAdapter implements ActionListener{
		private SingleChoiceItem adaptee;
		JRadioButtonD_actionAdapter(SingleChoiceItem adaptee){
			this.adaptee = adaptee;
		}
    	public void actionPerformed(ActionEvent e){
    		adaptee.JRadioButtonD_actionPerformed(e);
    	}
    }
    //单选按钮事件响应
    @SuppressWarnings("unchecked")
	public void JRadioButtonD_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//第一次答此题
    		answerlist[number] = new String(answerD.getText());
    		vector.addElement("第"+n+"题答案："+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//修改答案
    		answerlist[number] = new String(answerD.getText());
    		vector.setElementAt("第"+n+"题答案："+answerlist[number], number);//修改答案
            answer.setListData(vector);
    	}
    }
    class jlist_mouseAdapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int i = answer.getSelectedIndex();
	    	number = i;
	    	testSinglechoice(Id[number]);
			if(number == donumber-1)
			{
				next.setEnabled(false);
    			OK.setEnabled(true);
			}else{
				next.setEnabled(true);
				OK.setEnabled(false);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

    }
    class OKJButton_actionAdapter implements ActionListener{
    	private SingleChoiceItem adaptee;
    	OKJButton_actionAdapter(SingleChoiceItem adaptee){
    		this.adaptee = adaptee;
    	}
    	public void actionPerformed(ActionEvent e){
    		adaptee.OKJButton_actionPerformed(e);
    	}
    }
    public void OKJButton_actionPerformed(ActionEvent e){
    //单击"提交"按钮
    	int i = JOptionPane.showOptionDialog(null, "提交后不可修改试卷！是否确定提交？", "提交", 0, 1, null, null, null);
    	if(i == 0){
    		//确定提交测试
    		answer.setEnabled(false);
    		next.setEnabled(false);
    		OK.setEnabled(false);
    		answerA.setEnabled(false);
    		answerB.setEnabled(false);
    		answerC.setEnabled(false);
    		answerD.setEnabled(false);
        	scorepane.setVisible(true);//成绩面板当前可见
        	for( int j = 0; j<=donumber ;j++){
        		String[] score1={"","","",""};
        		int k = j+1;
        		score1[0] = new String(""+k);
        		score1[1] = quesscore[j];
        		score1[2] = answerright[j];
        		score1[3] = answerlist[j];
        		dm.addRow(score1);
        		if(score1[2].equals(score1[3])){
        			answerrightnumber++;
        		}
        	}
        	int n = donumber;
        	dm.setValueAt("测试正确率："+answerrightnumber+"/"+n, 0, 0);
    	}
    }
    class nextJButton_actionAdapter implements ActionListener{
    	private SingleChoiceItem adaptee;
    	nextJButton_actionAdapter(SingleChoiceItem adaptee){
    		this.adaptee = adaptee;
    	}
    	public void actionPerformed(ActionEvent e){
    		adaptee.nextJButton_actionPerformed(e);
    	}
    }

    public void nextJButton_actionPerformed(ActionEvent e){
    	if(answerlist[number].equals("")){
    		//还未答此题
    		JOptionPane.showMessageDialog(null, "还未回答此题！");
    	}else if(number<donumber){
    		number++;
    		if(Id[number]!=0)testSinglechoice(Id[number]);
    		else testSinglechoice();
    		if(number == donumber-1)
    		{
    			next.setEnabled(false);
    			OK.setEnabled(true);
    		}

    	}
    }
    public void testSinglechoice(int k){
    	boolean find = false;
		while(!find){
			try{
				rs.beforeFirst();
				while(rs.next()){
					if(k == Integer.parseInt(rs.getString("题目编号"))){
						//找到当前序号的题目
						Id[number] = k;//记录回答的题号
						find = true;//已经找到
						String ques = new String(number+1+rs.getString("题目内容"));
                        question.setText(ques);
                        quesscore[number] =ques;//记录下题目内容
                        answerA.setText("A"+rs.getString("A"));
                        answerA.setSelected(false);
                        answerB.setText("B"+rs.getString("B"));
                        answerB.setSelected(false);
                        answerC.setText("C"+rs.getString("C"));
                        answerC.setSelected(false);
                        answerD.setText("D"+rs.getString("D"));
                        answerD.setSelected(false);
                        scrollpane.setViewportView(panel);
                        frame.setVisible(true);
                        //记录成绩
                        answerright[number]=new String(rs.getString("答案")+rs.getString(rs.getString("答案")));
                        rs.last();
				}
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		}
    }
	public void testSinglechoice(){
		boolean find = false;
		int k;
		while(!find){
			k = getrandom();
			try{
				rs.beforeFirst();
				while(rs.next()){
					if(k == Integer.parseInt(rs.getString("题目编号"))){
						//找到当前序号的题目
						Id[number] = k;//记录回答的题号
						find = true;//已经找到
						String ques = new String(number+1+rs.getString("题目内容"));
                        question.setText(ques);
                        quesscore[number] =ques;//记录下题目内容
                        answerA.setText("A"+rs.getString("A"));
                        answerA.setSelected(false);
                        answerB.setText("B"+rs.getString("B"));
                        answerB.setSelected(false);
                        answerC.setText("C"+rs.getString("C"));
                        answerC.setSelected(false);
                        answerD.setText("D"+rs.getString("D"));
                        answerD.setSelected(false);
                        scrollpane.setViewportView(panel);
                        frame.setVisible(true);
                        //记录成绩
                        answerright[number]=new String(rs.getString("答案")+rs.getString(rs.getString("答案")));
                        rs.last();
				}
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		}

	}
	private void leftpanel(){
		panel = new JPanel();
		panel.setLayout(null);
	    panel.setBounds(scrollpane.getBounds());
		int xx = panel.getWidth();
		question = new JLabel();
		question.setBounds(3,3, xx, 15);
		panel.add(question);
		ans = new JPanel();//答案面板
    	ans.setBounds(1,23,xx,200);
    	//设置布局
    	final GridLayout gridlayout1 = new GridLayout(4,1);
    	gridlayout1.setHgap(3);
    	gridlayout1.setVgap(3);
    	ans.setLayout(gridlayout1);
    	buttongroup = new ButtonGroup();//创建按钮组对象
    	answerA = new JRadioButton();//创建单选按钮对象
		answerA.addActionListener(new JRadioButtonA_actionAdapter(this));//注册事件监听者
		answerA.setSelected(false);
		buttongroup.add(answerA);//将单选按钮添加到按钮组中
		ans.add(answerA);
		answerB = new JRadioButton();//创建单选按钮对象
		answerB.addActionListener(new JRadioButtonB_actionAdapter(this));//注册事件监听者
		answerB.setSelected(false);
		buttongroup.add(answerB);//将单选按钮添加到按钮组中
		ans.add(answerB);
		answerC = new JRadioButton();//创建单选按钮对象
		answerC.addActionListener(new JRadioButtonC_actionAdapter(this));//注册事件监听者
		answerC.setSelected(false);
		buttongroup.add(answerC);//将单选按钮添加到按钮组中
		ans.add(answerC);
		answerD = new JRadioButton();//创建单选按钮对象
		answerD.addActionListener(new JRadioButtonD_actionAdapter(this));//注册事件监听者
		answerD.setSelected(false);
		buttongroup.add(answerD);//将单选按钮添加到按钮组中
		ans.add(answerD);
		panel.add(ans);

		control = new JPanel();
    	control.setBounds(1,350,xx, 100);
    	next = new JButton("下一题");
    	next.addActionListener(new nextJButton_actionAdapter(this));
    	OK = new JButton("提交");
    	OK.addActionListener(new OKJButton_actionAdapter(this));
    	OK.setEnabled(false);
   	    control.add(next);
    	control.add(OK);
    	panel.add(control);
    	scrollpane.setViewportView(panel);
	}
	@SuppressWarnings("unchecked")
	private void rightpanel(){
    	panelr = new JPanel();
    	JLabel label = new JLabel("答案");
    	panelr.setLayout(new BorderLayout());
    	panelr.add(label,BorderLayout.NORTH);
        answer = new JList();
        vector = new Vector();
        answer.addMouseListener(new jlist_mouseAdapter());
    	panelr.add(answer,BorderLayout.CENTER);//将文本域添加到面板panel中
        panelright.setViewportView(panelr);//将面板panel添加到滚动面板中

    }
	private void scorepanel(){
		String[] cols = {"题号","题目内容","正确答案","所选答案"};//列名
		dm = new DefaultTableModel(cols,1);
		score = new JTable(dm);
		scorepane.setViewportView(score);//将表格添加到滚动面板
		scorepane.setHorizontalScrollBarPolicy(32);//设置水平滚动条一直显示
		scorepane.setVerticalScrollBarPolicy(22);//设置垂直滚动条一直显示
		scorepane.setVisible(false);//初始不可见
	}

}
