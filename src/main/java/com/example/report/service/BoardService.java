package com.example.report.service;

import com.example.report.domain.Board;
import com.example.report.domain.dto.CreateBoardForm;
import com.example.report.domain.dto.DeleteBoardForm;
import com.example.report.domain.dto.UpdateBoardForm;
import com.example.report.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }

    public void createBoard(CreateBoardForm createBoardForm) {
        boardRepository.save(new Board(createBoardForm));
    }

    public void deleteBoard(DeleteBoardForm deleteBoardForm) {
        boardRepository.deleteById(deleteBoardForm.getId());
    }

    public void updateBoard(UpdateBoardForm updateBoardForm) {
        Board board = boardRepository.findById(updateBoardForm.getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        board.update(updateBoardForm);
        boardRepository.save(board);
    }
}
