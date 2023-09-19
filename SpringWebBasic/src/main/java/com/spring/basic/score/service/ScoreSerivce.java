package com.spring.basic.score.service;

import org.springframework.stereotype.Service;

import com.spring.basic.score.dto.ScoreRequestDTO;

//컨트롤러와 리파지토리 사이에 배치되어 기타 비즈니스 로직 처리
//ex)값을 가공,예외처리,dto 로 변환, 트렌잭션 등등...
@Service //빈 등록
public class ScoreSerivce {

	//등록 중간 처리
	public void insertScore(ScoreRequestDTO dto) {
		
	}
	
}
