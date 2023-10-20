package com.spring.myweb.reply.dto;

import com.spring.myweb.reply.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class ReplyRequestDTO {

	private int bno;
	private String replyText;
	private String replyId;
	private String replyPw;
	
	public Reply toEntity(ReplyRequestDTO dto) {
		return Reply.builder()
			.bno(this.bno)
			.replyText(this.replyText)
			.replyWriter(this.replyId)
			.replyPw(this.replyPw)
			.build();
			
	}
}
