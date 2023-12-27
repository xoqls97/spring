package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class BoardVO {
//	create table board(
//			bno int auto_increment,
//			title varchar(200),
//			writer varchar(200),
//			content text,
//			isDel varchar(10) default 'N',
//			reg_date datetime default now(),
//			read_count int,
//			primary key(bno));
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String isDel;
	private String reg_date;
	private int read_count;
	
	

}
