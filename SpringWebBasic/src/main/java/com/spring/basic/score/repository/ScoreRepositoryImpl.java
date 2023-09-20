package com.spring.basic.score.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
@Repository //빈 등록 -> Service  가 의존하는 객체니까
//@Component --> 3계층 이외의 객체를 빈으로 등록할 때 사용

public class ScoreRepositoryImpl  implements IScoreRepository{
	//key:학번, value:성적 정보를 담은 객체
	private static final Map<Integer,Score> SCORE_MAP;
	// 학번에 사용할 일련번호
	private static int sequence;
	
	static {
		SCORE_MAP = new HashMap<>();
		Score stu1 = new Score(new ScoreRequestDTO("뽀로로",34,61, 50));
		Score stu2 = new Score(new ScoreRequestDTO("춘식이",34,61,30));
		Score stu3 = new Score(new ScoreRequestDTO("대길이",34,61,60));
		stu1.setStuNum(++sequence);
		stu2.setStuNum(++sequence);
		stu3.setStuNum(++sequence);
		SCORE_MAP.put(stu1.getStuNum(), stu1);
		SCORE_MAP.put(stu2.getStuNum(), stu2);
		SCORE_MAP.put(stu3.getStuNum(), stu3);
	}
	
	@Override
	public List<Score> findALL() {
		//SCORE_MAP 은 번호가 key, 객체가 value 로 이루어져 있음.
		//객체들만 전부 뽑아서 List 로 만들 것이기 때문에
		//SCORE_MAP 에서 value 들만 전부 뽀아낸 뒤, ArrayList 의 생성자의 매개값으로 
		
		List<Score> scoreList = new ArrayList<Score>(SCORE_MAP.values());
		return scoreList;
	}
	
	@Override
	public void save(Score score) {
		score.setStuNum(++sequence);
		SCORE_MAP.put(score.getStuNum(), score);
	}
	
	@Override
	public Score findByStuNum(int stuNum) {
		return SCORE_MAP.get(stuNum);
	}

	@Override
	public void deleteByStuNum(int stuNum) {
		SCORE_MAP.remove(stuNum);
	}

	@Override
	public void modify(Score modScore) {
		SCORE_MAP.put(modScore.getStuNum(), modScore);

	}
}
