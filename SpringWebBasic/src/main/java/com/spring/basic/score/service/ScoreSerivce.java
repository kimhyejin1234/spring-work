package com.spring.basic.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.IScoreRepository;

import lombok.RequiredArgsConstructor;

//컨트롤러와 리파지토리 사이에 배치되어 기타 비즈니스 로직 처리
//ex)값을 가공,예외처리,dto 로 변환, 트렌잭션 등등...
@Service //빈 등록
@RequiredArgsConstructor
public class ScoreSerivce {

	private final IScoreRepository scoreRepository;
	//등록 중간 처리
	//컨트롤러는 나에게 DTO 를 줬어
	//하지만,온전한 학생의 정보를 가니는 객체는 -> Score(Entity)
	//내가 Entity 을 만들어서 넘겨야 겠다.
	public void insertScore(ScoreRequestDTO dto) {
		Score score =  new Score(dto);
		//Entity 를 완성했으니, Repository 에게 전달해서 DB 에 넣자
		scoreRepository.save(score);
	}
	
	/*
	 컨트롤러는 나에게 데이터베이스를 통해
	 성적 정보 리스트를 가져오길 원하고 있어.
	 근데 Rspositiry 는 학생 정보가 모두 포함된 리스트를 주네?
	 현재 요청에 어울리는 응답 화면에 맞는 DTO 로 변경해서 주자.
	 */
	public List<ScoreListResponseDTO> getList() {
		List<ScoreListResponseDTO>  dtoList = new ArrayList<>();
		List<Score> scoreList =  scoreRepository.findALL();
		for(Score s:scoreList) {
			ScoreListResponseDTO dto = new ScoreListResponseDTO(s); //Entity 를 DTO 로 변환
			dtoList.add(dto);//변환한 DTO 를 DTO List 에 추가
		}
		
		return dtoList;
	}
	
	//학생 점수 개별 조회 
	public Score retrieve(int stuNum) {
		//응답하는 화면에 맞는 DTO를 선언해서 주어야 하는 것이 원칙.
		//만약에 Score 전체 데이터가 필요한 것이 아니라면
		//몇 개만 추리고 가공할 수 있는 DTO를 설계해서 리턴하는 것이 맞습니다.
		return scoreRepository.findByStuNum(stuNum);
	}

	public void delete(int stuNum) {
		scoreRepository.deleteByStuNum(stuNum);
	}

	public void modify(int stuNum, ScoreRequestDTO dto) {
		Score score = scoreRepository.findByStuNum(stuNum);
//		dto.setName(score.getStuName());
//		Score modScore = new Score(dto);
//		modScore.setStuNum(stuNum);
		score.changeScore(dto);

		scoreRepository.modify(score);

	}

}
