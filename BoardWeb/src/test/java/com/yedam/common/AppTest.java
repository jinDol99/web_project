package com.yedam.common;

import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;

public class AppTest {
	public static void main(String[] args) {
//		MemberVO mvo = new MemberVO();
//		mvo.setMemberId("user04");
//		mvo.setMemberName("김첨지");
//		mvo.setPassword("1111");
//		mvo.setEmail("kim@gmail.com");
		
		
		
//		if(mapper.insertMember(mvo) == 1) {
//			session.commit();
//		}
//
//		List<MemberVO> list = mapper.memberList();
//		list.forEach(member -> {
//			System.out.println(member.toString());
//		});
	
		

		/*
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);	// 솔직히 이 3줄을 왜 쓰고 뭘 의미하는지 1도 모르겠다

		List<BoardVO> list = mapper.selectList();	// [박진석 | boardSetting] 2-3.
		list.forEach(board -> {
			System.out.println(board.toString());
		});
		 */
//		BoardVO board = new BoardVO();
//		board.setTitle("입력테스트");
//		board.setContent("내용입니다.");
//		board.setWriter("kim");
//		
//		
//		BoardVO board2 = new BoardVO();
//		board2.setTitle("aaaaaaaaaaaaaaa");
//		board2.setContent("bbbbbbbbbbbbbbbbbbbbbbbb");
//		board2.setWriter("kimpark");
//		board.setBoardNo(255);
//		
//		BoardService svc = new BoardServiceImpl();
//		
////		svc.addBoard(board);
//		svc.modifyBoard(board2);
//		svc.removeBoard(4);
//		
//		System.out.println(svc.getBoard(board.getBoardNo()));
//		
//		
//		svc.boardList().forEach(System.out::println);	// board -> System.out.println(board)  이거 줄임말임
		
		
		
		//== 검색 테스트 ==//
		SearchDTO search = new SearchDTO();
		search.setSearchCondition("T");
		search.setKeyword("Java");
		search.setPage(1);
		
		BoardService svc = new BoardServiceImpl();
		svc.boardList(search).forEach(System.out::println);		
		
	}
}
