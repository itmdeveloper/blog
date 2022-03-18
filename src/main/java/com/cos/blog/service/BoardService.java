package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	

	@Transactional
	public void 글쓰기(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);

	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> {
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
			.orElseThrow(() -> {
				return new IllegalArgumentException("글 찾기 실패: 해당 게시글을 찾을 수 없습니다.");
			});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		/*
		 * User user =
		 * userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
		 * return new IllegalArgumentException("댓글 쓰기 실패: 유저를 찾을 수 없습니다."); });
		 * 
		 * Board board =
		 * boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() ->
		 * { return new IllegalArgumentException("댓글 쓰기 실패: 게시글을 찾을 수 없습니다."); });
		 * 
		 * Reply reply = Reply.builder() .user(user) .board(board)
		 * .content(replySaveRequestDto.getContent()) .build();
		 */
		 
			/*
			 * Reply reply = new Reply(); reply.update(user, board,
			 * replySaveRequestDto.getContent());
			 * 
			 * 
			 */
		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}

	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
		
	}
}
