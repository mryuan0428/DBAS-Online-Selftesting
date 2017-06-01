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
	JScrollPane scrollpane,panelright,scorepane;//�������������������ʾ����ʹ�,�Լ��ɼ�
	JPanel panel,panelr;//������ʾÿ����ʹ�

	JLabel question;//������ʾÿһ�����Ŀ

	JPanel ans;//ѡ�����
	ButtonGroup buttongroup;//��ť��
	JRadioButton answerA, answerB,answerC,answerD;

	JPanel control;//��ť���
	JButton next,OK;//��һ�⡢�ύ��ť

	JList answer;//��ʾ���б��
	@SuppressWarnings("unchecked")
	Vector vector;

	JTable score;//��ʾ�ɼ�
	DefaultTableModel dm;//�������

	ResultSet rs;//�����������ݿ�
	 ConnectData  singlechoice;

	int[] Id={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int number = 0;
	int N = 0;//�����������
	int donumber = 0;//����Ŀ������
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
    	leftpanel();//���õ�ѡ�����
    	rightpanel();//���ô��б��
    	scorepanel();
    	dopanel.add(scrollpane);
        dopanel.add(panelright);
        dopanel.add(scorepane);

        frame.setVisible(true);



        singlechoice = new ConnectData("SingleChoice",ID);
		rs = singlechoice.getResultSet();
		getdonumber();
		getN();
        testSinglechoice();//����
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
    //��ѡ��ť�¼���Ӧ
    @SuppressWarnings("unchecked")
	public void JRadioButtonA_actionPerformed(ActionEvent e){
    	int n= number + 1;
    	if(answerlist[number].equals("")){
    		//��һ�δ����
    		answerlist[number] = new String(answerA.getText());
    		vector.addElement("��"+n+"��𰸣�"+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//�޸Ĵ�
    		answerlist[number] = new String(answerA.getText());
    		vector.setElementAt("��"+n+"��𰸣�"+answerlist[number], number);//�޸Ĵ�
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
    //��ѡ��ť�¼���Ӧ
    @SuppressWarnings("unchecked")
	public void JRadioButtonB_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//��һ�δ����
    		answerlist[number] = new String(answerB.getText());
    		vector.addElement("��"+n+"��𰸣�"+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//�޸Ĵ�
    		answerlist[number] = new String(answerB.getText());
    		vector.setElementAt("��"+n+"��𰸣�"+answerlist[number], number);//�޸Ĵ�
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
    //��ѡ��ť�¼���Ӧ
    @SuppressWarnings({ "unchecked", "unchecked" })
	public void JRadioButtonC_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//��һ�δ����
    		answerlist[number] = new String(answerC.getText());
    		vector.addElement("��"+n+"��𰸣�"+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//�޸Ĵ�
    		answerlist[number] = new String(answerC.getText());
    		vector.setElementAt("��"+n+"��𰸣�"+answerlist[number], number);//�޸Ĵ�
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
    //��ѡ��ť�¼���Ӧ
    @SuppressWarnings("unchecked")
	public void JRadioButtonD_actionPerformed(ActionEvent e){
    	int n = number + 1;
    	if(answerlist[number].equals("")){
    		//��һ�δ����
    		answerlist[number] = new String(answerD.getText());
    		vector.addElement("��"+n+"��𰸣�"+answerlist[number]);
            answer.setListData(vector);
    	}else{
    		//�޸Ĵ�
    		answerlist[number] = new String(answerD.getText());
    		vector.setElementAt("��"+n+"��𰸣�"+answerlist[number], number);//�޸Ĵ�
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
    //����"�ύ"��ť
    	int i = JOptionPane.showOptionDialog(null, "�ύ�󲻿��޸��Ծ��Ƿ�ȷ���ύ��", "�ύ", 0, 1, null, null, null);
    	if(i == 0){
    		//ȷ���ύ����
    		answer.setEnabled(false);
    		next.setEnabled(false);
    		OK.setEnabled(false);
    		answerA.setEnabled(false);
    		answerB.setEnabled(false);
    		answerC.setEnabled(false);
    		answerD.setEnabled(false);
        	scorepane.setVisible(true);//�ɼ���嵱ǰ�ɼ�
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
        	dm.setValueAt("������ȷ�ʣ�"+answerrightnumber+"/"+n, 0, 0);
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
    		//��δ�����
    		JOptionPane.showMessageDialog(null, "��δ�ش���⣡");
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
					if(k == Integer.parseInt(rs.getString("��Ŀ���"))){
						//�ҵ���ǰ��ŵ���Ŀ
						Id[number] = k;//��¼�ش�����
						find = true;//�Ѿ��ҵ�
						String ques = new String(number+1+rs.getString("��Ŀ����"));
                        question.setText(ques);
                        quesscore[number] =ques;//��¼����Ŀ����
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
                        //��¼�ɼ�
                        answerright[number]=new String(rs.getString("��")+rs.getString(rs.getString("��")));
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
					if(k == Integer.parseInt(rs.getString("��Ŀ���"))){
						//�ҵ���ǰ��ŵ���Ŀ
						Id[number] = k;//��¼�ش�����
						find = true;//�Ѿ��ҵ�
						String ques = new String(number+1+rs.getString("��Ŀ����"));
                        question.setText(ques);
                        quesscore[number] =ques;//��¼����Ŀ����
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
                        //��¼�ɼ�
                        answerright[number]=new String(rs.getString("��")+rs.getString(rs.getString("��")));
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
		ans = new JPanel();//�����
    	ans.setBounds(1,23,xx,200);
    	//���ò���
    	final GridLayout gridlayout1 = new GridLayout(4,1);
    	gridlayout1.setHgap(3);
    	gridlayout1.setVgap(3);
    	ans.setLayout(gridlayout1);
    	buttongroup = new ButtonGroup();//������ť�����
    	answerA = new JRadioButton();//������ѡ��ť����
		answerA.addActionListener(new JRadioButtonA_actionAdapter(this));//ע���¼�������
		answerA.setSelected(false);
		buttongroup.add(answerA);//����ѡ��ť��ӵ���ť����
		ans.add(answerA);
		answerB = new JRadioButton();//������ѡ��ť����
		answerB.addActionListener(new JRadioButtonB_actionAdapter(this));//ע���¼�������
		answerB.setSelected(false);
		buttongroup.add(answerB);//����ѡ��ť��ӵ���ť����
		ans.add(answerB);
		answerC = new JRadioButton();//������ѡ��ť����
		answerC.addActionListener(new JRadioButtonC_actionAdapter(this));//ע���¼�������
		answerC.setSelected(false);
		buttongroup.add(answerC);//����ѡ��ť��ӵ���ť����
		ans.add(answerC);
		answerD = new JRadioButton();//������ѡ��ť����
		answerD.addActionListener(new JRadioButtonD_actionAdapter(this));//ע���¼�������
		answerD.setSelected(false);
		buttongroup.add(answerD);//����ѡ��ť��ӵ���ť����
		ans.add(answerD);
		panel.add(ans);

		control = new JPanel();
    	control.setBounds(1,350,xx, 100);
    	next = new JButton("��һ��");
    	next.addActionListener(new nextJButton_actionAdapter(this));
    	OK = new JButton("�ύ");
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
    	JLabel label = new JLabel("��");
    	panelr.setLayout(new BorderLayout());
    	panelr.add(label,BorderLayout.NORTH);
        answer = new JList();
        vector = new Vector();
        answer.addMouseListener(new jlist_mouseAdapter());
    	panelr.add(answer,BorderLayout.CENTER);//���ı�����ӵ����panel��
        panelright.setViewportView(panelr);//�����panel��ӵ����������

    }
	private void scorepanel(){
		String[] cols = {"���","��Ŀ����","��ȷ��","��ѡ��"};//����
		dm = new DefaultTableModel(cols,1);
		score = new JTable(dm);
		scorepane.setViewportView(score);//�������ӵ��������
		scorepane.setHorizontalScrollBarPolicy(32);//����ˮƽ������һֱ��ʾ
		scorepane.setVerticalScrollBarPolicy(22);//���ô�ֱ������һֱ��ʾ
		scorepane.setVisible(false);//��ʼ���ɼ�
	}

}
