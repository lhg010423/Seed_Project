package seed.project.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import seed.project.board.model.dto.Board;
import seed.project.board.model.dto.Comment;
import seed.project.board.model.service.BoardService;

@Controller
@RequestMapping("board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	
	/**
	 * @param cp : 현재 조회 요청한 페이지 (없으면 1)
	 * @param model
	 * @param paramMap : 제출될 파라미터가 모두 저장된 Map
	 * 					(검색 시 keyp, query 담겨있음)
	 * @return
	 */
	@GetMapping("{boardCode:[1]}")
	public String board1(
			@PathVariable("boardCode") int boardCode,
			@RequestParam(value="cp", required=false, defaultValue="1") int cp,
			Model model,
			@RequestParam Map<String, Object> paramMap
			) {
		
		Map<String, Object> map = null;
		
		
		// 검색 안할 때
		if(paramMap.get("key") == null) { 
			
			// 게시글 목록 조회 서비스 호출
			map = service.selectBoardList1(boardCode, cp);
			
		// 검색할 때
		} else {
			
			paramMap.put("boardCode", boardCode);
			// -> paramMap은 {key=t, quer=검색어, boardCode=1}
			
			map = service.searchList1(paramMap, cp);
		}

		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		
		return "board/board1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
  
	  @GetMapping("{boardCode:[2]}")
	  public String board2(Model model,
							@RequestParam(value="cp", required = false, defaultValue="1") int cp
							) {
			
			Map<String, Object> map = service.selectBoard2List(2, cp); 
			
			if(map != null) {
				model.addAttribute("boardList", map.get("boardList"));
				model.addAttribute("pagination", map.get("pagination"));
			}
			
			
			return "board/board2";
	  }
	  
	  
	  @GetMapping("{boardCode:[2]}/detail")
	  public String board2Detail(
			  			@RequestParam(value="boardNo") int boardNo,
			  			Model model
			  			) {
		  
		  Board boardInfo = service.board2Detail(boardNo);
		  List<Comment> commentList = service.board2CommentList(boardNo);
		  		
		  model.addAttribute("boardInfo", boardInfo);
		  model.addAttribute("commentList", commentList);
		  
		  return "board/board2Detail";
	  }
	  
  
  
  
  
	/** [3] 팁과 노하우 게시글 목록 조회 + 검색 서비스
	 * @param boardCode
	 * @param cp
	 * @param model
	 * @param paramMap
	 * @return
	 */
	@GetMapping("{boardCode:[3]}")
	public String selectBoard3(@PathVariable("boardCode") int boardCode,
								@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
								Model model,
								@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = null;
		
		if(paramMap.get("key") == null) {
			
			map = service.selectBoard3(boardCode, cp);
			
		} 
		
		else { // 검색인 경우 추후 구성
			
			paramMap.put("boardCode", boardCode); 
			
			// 검색 서비스 호출
			map = service.searchList3(paramMap, cp);
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		return "board/board3"; // board3.html로 forward
	    
	}
	
	
	
}
