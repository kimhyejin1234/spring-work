package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.request.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.dto.response.FreeDetailResponseDTO;
import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardSerive implements IFreeBoardService {

	private final IFreeBoardMapper mapper;
	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
			.title(dto.getTitle())
			.content(dto.getContent())
			.writer(dto.getWriter())
			.build());


	}

	@Override
	public List<FreeListResponseDTO> getList(Page page) {
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		List<FreeBoard> list =  mapper.getList(page);
		for(FreeBoard board : list) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		return dtoList;
	}

	
	@Override
	public int getTotal(Page page) {
		return mapper.getTotal(page);
	}
	@Override
	public FreeDetailResponseDTO getContent(int bno) {
		FreeBoard board = mapper.getContent(bno);
		//엔터티의 객체를 FreeDetailResponsetDTO 객체로 넘겨준다
		return new FreeDetailResponseDTO(board);
	}

	@Override
	public void update(FreeUpdateRequestDTO dto) {
		System.out.println("update service 들어옴 " + dto);
		mapper.update(FreeBoard.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build());
	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);
	}

}
