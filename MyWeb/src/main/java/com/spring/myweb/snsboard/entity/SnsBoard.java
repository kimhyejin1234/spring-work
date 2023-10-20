package com.spring.myweb.snsboard.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 
create table snsboard(
    bno NUMBER PRIMARY KEY,
    writer VARCHAR2(50) NOT NULL,
    upload_path VARCHAR2(100),
    file_loca VARCHAR2(100),
    file_name VARCHAR2(100),
    file_read_name VARCHAR2(100),
    content VARCHAR2(4000),
    reg_date DATE DEFAULT sysdate
);

create SEQUENCE snsboard_seq
START WITH 1
INCREMENT by 1
maxvalue 100000
NOCYCLE
NOCACHE


 */

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SnsBoard {

	private int bno;
	private String writer;
	private String uploadPath;
	private String fileLoca;
	private String fileName;
	private String fileRealName;
	private String content;
	private LocalDateTime regDate;
}
