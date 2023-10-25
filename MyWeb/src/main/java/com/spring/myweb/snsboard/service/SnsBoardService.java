package com.spring.myweb.snsboard.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.response.FreeDetailResponseDTO;
import com.spring.myweb.snsboard.dto.SnsBoardRequestDTO;
import com.spring.myweb.snsboard.dto.SnsBoardResponseDTO;
import com.spring.myweb.snsboard.entity.SnsBoard;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SnsBoardService  {
	
		private final  ISnsBoardMapper mapper;

		public void insert(SnsBoardRequestDTO dto) {
			System.out.println("SnsBoardService 들어 왔다 : " + dto);

	
			//날짜별로 폴더를 생성해서 관리할 예정입니다.(yyyyMMdd)
	        //날짜는 LocalDateTime과 DateTimeFormatter를 이용하세요.
	
			// 현재 날짜와 시간 가져오기
			LocalDateTime now = LocalDateTime.now();
			// 원하는 날짜 형식을 정의합니다. 여기서는 "yyyyMMdd" 형식으로 정의합니다.
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			// 형식에 따라 날짜를 문자열로 변환합니다.
			String fileLoca = now.format(dtf);
			
		
	        //기본 경로는 C:/test/upload로 사용 하겠습니다.
	        String uploadPath = "C:/test/upload/";
	        
	        //폴더가 존재하지 않다면 새롭게 생성해 주시라~
	        File folder = new File(uploadPath + fileLoca);
	        if (!folder.exists()) {
	            if (folder.mkdirs()) {
	                System.out.println("폴더가 성공적으로 생성되었습니다.");
	            } else {
	                System.out.println("폴더 생성에 실패했습니다.");
	            }
	        } else {
	            System.out.println("폴더가 이미 존재합니다.");
	        }	        
	        
	        //저장될 파일명을 UUID를 이용한 파일명으로 저장합니다.
	        //UUID가 제공하는 랜덤 문자열에 -을 제거해서 전부 사용하시면 됩니다.
	        String fileRealName = dto.getFile().getOriginalFilename();//파일 원본명.
			
			UUID uuid = UUID.randomUUID();
			String uuids = uuid.toString().replace("-","");
			
			//확장자 추출
			String fileExtenstion = fileRealName.substring(
							fileRealName.lastIndexOf("."));//확장자
			String fileName = uuids + fileExtenstion;
			//DB 에는 파일 경로를 지정한다고 가정하고, 실제 파일은 서버 콤퓨터의 로컬 경로에 저장하는 방식.
			File saveFile = new File(uploadPath + fileLoca + "/" +  fileName);
			try {
				//매개값으로 받은 첨부 파일을 지정한 로컬 경로에 보내는 메서드.
				dto.getFile().transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			mapper.insert(SnsBoard.builder()
					.writer(dto.getWriter())
					.uploadPath(uploadPath)
					.fileLoca(fileLoca)
					.fileName(fileName)
					.fileRealName(fileRealName)
					.content(dto.getContent())
					.build());

		}

		public List<SnsBoardResponseDTO> getList(int page) {
			List<SnsBoardResponseDTO> dtolist = new ArrayList<>();
			List<SnsBoard> list =  mapper.getList(Page.builder()
					.pageNo(page)
					.amount(3)
					.build());
			for(SnsBoard board : list) {
				dtolist.add(new SnsBoardResponseDTO(board));
			}
			return dtolist;
		}


		public SnsBoardResponseDTO getContent(int bno) {
			System.out.println("getDetail :  " + bno);
			return new SnsBoardResponseDTO(mapper.getDetail(bno));
		}

		public void delete(int bno) {
			mapper.delete(bno);
			
		}

		public String searchLike(Map<String, String> params) {
			if(mapper.searchLike(params) == 0 ) {
				mapper.createLike(params);
				return "like";
			} else {
				mapper.deleteLike(params);
				return "delete";
			}
			
		}


		public List<Integer> likeList(String userId) {
			return mapper.likeList(userId);
		}

}
