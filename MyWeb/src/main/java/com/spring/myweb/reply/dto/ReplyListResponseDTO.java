package com.spring.myweb.reply.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.reply.entity.Reply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class ReplyListResponseDTO {

	private int replyNo;
	private String replyWriter;
	private String replyText;
	private String date;
	
	public ReplyListResponseDTO(Reply reply) {
		System.out.println(" ReplyListResponseDTO : " + reply );
		this.replyNo = reply.getReplyNo();
		this.replyWriter = reply.getReplyWriter();
		this.replyText = reply.getReplyText();
		if(reply.getUpdateDate() == null) {
			this.date = makePrettierDateString(reply.getReplyDate());
		} else {
			this.date = makePrettierDateString(reply.getUpdateDate()) + " (수정됨)";
		}
		System.out.println("this.date : " + this.date );
	}

	
	//static 을 붙이면 같은 패키지 내에서 불러 사용할수 있다.
	static String makePrettierDateString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate);
	}

	
}
