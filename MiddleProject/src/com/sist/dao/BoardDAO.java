package com.sist.dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;


public class BoardDAO {
	//�Ľ�
	 private static SqlSessionFactory ssf;
			static {
				try {
					//xml���� �б�
					Reader reader=Resources.getResourceAsReader("Config.xml");
					// ClassPath = > src�ν�
					// ssf => �Ľ̳��� ���� ��!
					ssf = new SqlSessionFactoryBuilder().build(reader);	
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	   }
			// ��
			public static List<BoardVO> qboardListData(Map map) {
				   List<BoardVO> list = new ArrayList<BoardVO>();
				   
				   // db����
				   SqlSession session = ssf.openSession();
				   list = session.selectList("qboardListData",map);
			
				   // db����
				   session.close();
				   return list;
			   }	
		
		//--------------------------------------------------------------------
	   // �Խ��Ǹ���Ʈ
	   public static List<BoardVO> boardListData(Map map) {
		   List<BoardVO> list = new ArrayList<BoardVO>();
		   
		   // db����
		   SqlSession session = ssf.openSession();
		   list = session.selectList("boardListData",map);
	
		   // db����
		   session.close();
		   return list;
	   }
	   //�۾���
	   public static void boardInsert(BoardVO vo) {
		   SqlSession session = ssf.openSession(true);
		   System.err.println("Ȯ��");
		   System.out.println(vo.getSubject());
		   session.insert("boardInsert",vo);
		   session.close();
	   }
	   
	   //�������� �б�
	 /*  public static int boardTotalPage() {
		   int total=0;
		   SqlSession session = ssf.openSession();
		   total=session.selectOne("boardTotalPage");
		   session.close();
		   return total;
	   }*/
	   public static int boardTotalPage() {
		   int total = 0;
		   SqlSession session = null;
		   try {
			// ����(getConnection)
			   session = ssf.openSession();
			// sql���� ����
			   total = session.selectOne("boardTotalPage");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// ��ȯ(disConnection)
			if(session!=null)
			session.close();
		}
		   return total;
	   }
	   //�Խù� ��ü���� �б�
	   public static int boardRowCount() {
		   int count=0;
		   SqlSession session = ssf.openSession();
		   count=session.selectOne("boardRowCount");
		   session.close();
		   return count;
	   }
	   //�󼼺���
	   public static BoardVO boardDetailData(int no, String type) {
		   BoardVO vo = new BoardVO();
		   SqlSession session = ssf.openSession();
		   if(type.equals("detail")) {
			   session.update("hit",no);
			   session.commit();
		   }
		   vo = session.selectOne("boardDetailData",no);
		   session.close();
		   return vo;
	   }
	   //�����ϱ�
	   public static int boardUpdate(BoardVO vo) {
		   int no=0;
		   SqlSession session = ssf.openSession();
		   //���Ȯ��
		   String pwd = session.selectOne("boardGetPwd", vo.getBoardno());
		   if(pwd.equals(vo.getPwd())) {
			   no = vo.getBoardno();
			   session.update("boardUpdate", vo);
			   session.commit();   
		   } 
		   session.close();
		   return no;
	   }
	   
	   // �˻��ϱ�
	   public static List<BoardVO> boardListDataTEST(Map map) {
		   List<BoardVO> list = new ArrayList<BoardVO>();
		   
		   // db����
		   SqlSession session = ssf.openSession();
		   list = session.selectList("boardListDataTEST",map);
	
		   // db����
		   session.close();
		   return list;
	   }
	 //��ۿø���
	   public static void replyInsert(ReplyVO vo) {
		   SqlSession session = ssf.openSession(true);
		   session.insert("replyInsert",vo); // (sql���̵��Ī,vo) mapper�� sql�� ������
		   								//== vo���޴°��� Model�̶� �𵨷ΰ�
		   session.close();
	   }
	   

	   //��۰���������
	   public static List<BoardDAO> replyListData(int bno) {
		   SqlSession session = ssf.openSession(true);
		   List<BoardDAO> list = session.selectList("replyListData",bno); //��Ϲ����� selectList
		   session.close();
		   return list;
	   }
	   
	   //��۰�����������
	   public static int replyListCount(int bno) {
		   SqlSession session = ssf.openSession(true);
		   int count = session.selectOne("replyListCount",bno); //�Ѱ������� selectOne *#{} �� ��������
		   session.close();
		   return count;
	   }
	   public static void replyReInsert(int root, ReplyVO vo) { //root = �Ʊ� �޾Ҵ� pno�� 
		   SqlSession session = ssf.openSession(true); 
		   
		   // 4�� ȣ���ϱ�
		   // 1 ����
		   ReplyVO pvo = session.selectOne("replyParentInfo",root); //mapper 106
		   System.out.println(pvo.getGroup_id());
		   // 2 step����
		   session.update("replyStepIncrement",pvo); //mapper 112
		   // 3 insert ��ä���
		   vo.setGroup_id(pvo.getGroup_id()); //pvo = ������
		   vo.setGroup_step(pvo.getGroup_step()+1); 
		   vo.setGroup_tab(pvo.getGroup_tab()+1);
		   vo.setRoot(root);
		   session.insert("replyReInsert",vo); //mapper 118
		   // 4 depth ����
		   session.update("replyDepthIncrement",root); //mapper 131
		   

		   System.out.println(pvo);
		   System.out.println(vo);
		   System.out.println(root);
		   
		   //Ʈ����� : �ϰ�ó��!!! 
		   session.commit(); 
		   session.close();
	   }
	   
	   public static void replyUpdate(ReplyVO vo) {
		   SqlSession session = ssf.openSession(true);
		   session.update("replyUpdate",vo); // (sql���̵��Ī,vo) mapper�� sql�� ������
		   								//== vo���޴°��� Model�̶� �𵨷ΰ�
		   session.close();
	   }
	   
	   //�����ϱ�----------------------------------------------------------------------------
	   public static void replyDelete(int no) {
		   SqlSession session = ssf.openSession();
		   ReplyVO vo = session.selectOne("replyGetDepth",no);
		   if(vo.getDepth()==0) {
			   session.delete("replyDelete",no);
		   } else {
			   ReplyVO fvo = new ReplyVO();
			   fvo.setMsg("<font color=grey>[��]�����ڰ� ������ �Խñ� �Դϴ�</font>");
			   fvo.setReplyno(no);
			   session.update("replyDataUpdate",fvo);
		   }
		   session.update("replyDepthDecrement",vo.getRoot());
		   session.commit(); //Ŀ���� �Ǹ������� �ϰ�ó�� : Ʈ�����
		   session.close();
	   }
	   public static int boardDelete(int no, String pwd) {
		   int result=0;
		   SqlSession session = ssf.openSession(); //db����
		   String db_pwd=session.selectOne("boardGetPwd",no); //���������
		   System.out.println("db�� ����� �����?"+db_pwd);
		   if(db_pwd.equals(pwd)) {
			   result=1;
			   session.delete("boardReplyDelete",no);
			   session.delete("boardDelete",no);
			   session.commit();
		   }
		   session.close(); //db�ݱ�
		   return result;
	   }
}