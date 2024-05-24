package com.example.report.controller;

import com.example.report.domain.Board;
import com.example.report.domain.dto.CreateBoardForm;
import com.example.report.domain.dto.DeleteBoardForm;
import com.example.report.domain.dto.UpdateBoardForm;
import com.example.report.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 목록 보기
    @GetMapping("/list")
    public String getBoardList(Model model, @RequestParam(defaultValue = "1")int page,
                               @RequestParam(defaultValue = "5") int size){
        Page<Board> boardList = boardService.getBoardList(PageRequest.of(page - 1, size));
        model.addAttribute("boardList", boardList);
        return "ListHtml";
    }

    // 게시글 조회
    @GetMapping("/view")
    public String getBoardView(Model model, @RequestParam Long id){
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "ViewHtml";
    }

    // 게시글 작성 폼
    @GetMapping("/writeform")
    public String getWriteForm(Model model){
        model.addAttribute("createBoardForm", new CreateBoardForm());
        return "WriteFormHtml";
    }

    // 게시글 작성
    @PostMapping("/write")
    public String createBoard(@ModelAttribute CreateBoardForm createBoardForm, RedirectAttributes redirectAttributes){
        boardService.createBoard(createBoardForm);
        redirectAttributes.addFlashAttribute("message", "게시글 작성 성공");
        return "redirect:/list";
    }

    // 게시글 삭제 폼
    @GetMapping("/deleteform")
    public String getDeleteForm(Model model, @RequestParam Long id){
        model.addAttribute("deleteBoardForm", new DeleteBoardForm(id));
        return "DeleteFormHtml";
    }

    // 게시글 삭제
    @PostMapping("/delete")
    public String deleteBoard(@ModelAttribute DeleteBoardForm deleteBoardForm, RedirectAttributes redirectAttributes){
        boardService.deleteBoard(deleteBoardForm);
        redirectAttributes.addFlashAttribute("message", "게시글 삭제 성공");
        return "redirect:/list";
    }

    // 게시글 수정 폼
    @GetMapping("/updateform")
    public String getUpdateForm(Model model, @RequestParam Long id){
        Board board = boardService.getBoard(id);
        model.addAttribute("updateBoardForm", new UpdateBoardForm(id, board.getName(), board.getTitle(), board.getContent()));
        return "UpdateFormHtml";
    }

    // 게시글 수정
    @PostMapping("/update")
    public String updateBoard(@ModelAttribute UpdateBoardForm updateBoardForm, RedirectAttributes redirectAttributes){
        boardService.updateBoard(updateBoardForm);
        redirectAttributes.addFlashAttribute("message", "게시글 수정 성공");
        return "redirect:/list";
    }
}
