package seed.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import seed.project.board.model.dto.Board;
import seed.project.board.model.dto.Comment;
import seed.project.board.model.dto.Pagination;
import seed.project.board.model.mapper.BoardMapper;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper mapper;
	
	
	// [공통] 게시판 종류 조회
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}

	// 자유 게시판의 지정된 페이지 목록 조회
	@Override
	public Map<String, Object> selectBoardList1(int boardCode, int cp) {
		
		// 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);
		
		if(listCount > 0) {
			
			// listCount + cp
			Pagination pagination = new Pagination(cp, listCount);
			
			int limit = pagination.getLimit();
			int offset = (cp - 1) * limit;
			RowBounds rowBounds = new RowBounds(offset, limit);
			
			
			List<Board> boardList = mapper.selectBoardList1(boardCode, rowBounds);
			
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("pagination", pagination);
			map.put("boardList", boardList);
			
			return map;
		}
		return null;
	}

	// [2] 문의 게시판 게시글 조회
	@Override
	public Map<String, Object> selectBoard2List(int boardCode, int cp) {
		
		// 삭제 되지 않은 게시판
		int listCount = mapper.getListCount(boardCode);
		
		
		// 게시글이 존재할때 페이징 객체 생성  
		if(listCount > 0) {
			Pagination pagination = new Pagination(cp, listCount);
			
			int offset = (cp - 1) * pagination.getLimit();
			RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
			
			List<Board> boardList = mapper.selectBoard2List(boardCode, rowBounds);
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("pagination", pagination);
			map.put("boardList", boardList);
			
			return map;
		}
		
		
		return null;
		
	}

	// [2] 게시글 정보 받아오기
	@Override
	public Board board2Detail(int boardNo) {
		
		
		return mapper.board2Detail(boardNo);
	}

	
	
	// [2] 게시글 댓글 정보
	@Override
	public List<Comment> board2CommentList(int boardNo) {
		
		return mapper.board2CommentList(boardNo);
	}
	
	
	// [3] 팁과 노하우 - 게시글 목록
	@Override
	public Map<String, Object> selectBoard3(int boardCode, int cp) {
		

		// 1. 삭제되지 않은 게시글 수를 조회
		int listCount = mapper.getListCount(boardCode);
		
		// Pagination 객체를 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		
		// 3. 특정 게시판의 지정된 페이지 목록 조회
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		/* Mapper 메서드 호출 시
		 * - 첫 번째 매개변수 -> SQL에 전달할 파라미터
		 * - 두 번째 매개변수 -> RowBounds 객체 전달
		 */
		List<Board> boardList = mapper.selectBoard3(boardCode, rowBounds);
		
		
		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;


	}
	
	
	// [3] 팁과 노하우 게시판 - 게시글 검색
	@Override
	public Map<String, Object> searchList3(Map<String, Object> paramMap, int cp) {
		
		int listCount = mapper.getSearchCount3(paramMap);
		
			
		Pagination pagination = new Pagination(cp, listCount);
		
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		

		List<Board> boardList = mapper.selectSearchList3(paramMap, rowBounds);
		
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
			
		

	}

	// [1] 자유 게시판 검색 서비스
	@Override
	public Map<String, Object> searchList1(Map<String, Object> paramMap, int cp) {
		
		// 1. 자유 게시판에서 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getSearchCount1(paramMap);
		// 검색된 게시글이 몇개인지 알아야 Pagination을 만들 수 있음
		
		// 2. listCount + cp (전체게시글수, 현제페이지번호)
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3. 자유게시판의 지정된 페이지 목록 조회
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		List<Board> boardList = mapper.selectSearchList1(paramMap, rowBounds);
		
		// 목록 조회 결과 + Pagination 을 map으로 묶음
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		return map;
	}
	
	
	// [3] 팁과 노하우 게시판 - 게시글 상세 조회
	@Override
	public Board selectOne3(Map<String, Integer> map) {
		
		return mapper.selectOne3(map);
	}

	
	// [1] 자유 게시판 - 게시글 상세조회
	@Override
	public Board selectOne1(Map<String, Object> map) {
		return mapper.selectOne1(map);
	}

	
	// [2] 게시글 삭제(업데이트)
	@Override
	public int board2Delete(int boardNo) {
		
		return mapper.board2Delete(boardNo);
	}

	// [2] 게시글 작성하기
	@Override
	public int board2Write(Map<String, String> board) {

		return mapper.board2Write(board);
	}

	
	// [2] 게시글 정보 가져오기
	@Override
	public Board board2Info(int boardNo) {
		
		return mapper.board2Info(boardNo);
	}

	// [2] 게시글 수정하기
	@Override
	public int board2Update(Map<String, Object> board) {
		
		return mapper.board2Update(board);
	}

	// [2] 문의 게시판 검색
	@Override
	public Map<String, Object> selectBoard2SearchList(Map<String, Object> paramMap, int cp) {
		
		// 삭제되지 않은 게시글 검색
		List<Board> boardList = mapper.getSearchCount2(paramMap);
		
		// 게시글이 존재할 때 페이징 객체 생성
		if(boardList.size() > 0) {
			Pagination pagination = new Pagination(cp, boardList.size());
			
			int offset = (cp - 1) * pagination.getLimit();
			RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

			// 반환되는 boardList2
			List<Board> boardList2 = mapper.getSearchCount2(paramMap, rowBounds);
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("pagination", pagination);
			map.put("boardList", boardList2);
			
			return map;
		}
		
		return null;
	}

	
	// [3] 팁과 노하우 게시판 - 조회수 증가(쿠키)
	@Override
	public int readCount3(int boardNo) {
		
		int result = mapper.readCount3(boardNo);
		
		if(result > 0) {
			
			return mapper.selectReadCount3(boardNo);
		}
		
		return -1;
	}

	
	// [3] 팁과 노하우 게시판 - 이전글
	@Override
	public int beforePage(Map<String, Integer> map) {
		
		return mapper.beforePage(map);
	}

	
	// [3] 팁과 노하우 게시판 - 다음글
	@Override
	public int afterPage(Map<String, Integer> map) {
		

		return mapper.afterPage(map);
	}

	// [2] 댓글 달기
	@Override
	public int board2Comment(Map<String, Object> commentMap) {
		
		return mapper.board2Comment(commentMap);
	}

	// [2] 댓글 삭제
	@Override
	public int board2CommentDelete(int commentNo) {
		
		return mapper.board2CommentDelete(commentNo);
	}



	// [1] 게시글 수정하기
	@Override
	public int board1Update(Map<String, Object> board) {
		return mapper.board1Update(board);
	}

	// [1] 게시글 삭제하기
	@Override
	public int board1Delete(int boardNo) {
		return mapper.board1Delete(boardNo);
	}

	// [2] 댓글 수정하기
	@Override
	public int board2CommentUpdate(Map<String, Object> commentMap) {
		
		return mapper.board2CommentUpdate(commentMap);
	}


}

