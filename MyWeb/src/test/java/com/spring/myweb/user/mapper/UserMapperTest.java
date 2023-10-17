package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.user.entity.User;
import com.spring.myweb.user.mapper.IUserMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	@Test
    @DisplayName("회원 가입을 진행했을 때 회원가입이 성공해야 한다.")
    void registTest() {
		User user = User.builder()
		.userId("abc1")
		.userPw("abc123456")
		.userName("김혜진")
		.userPhone1("010")
		.userPhone2("1212121")
		.userEmail1("abc1111")
		.userEmail2("naver.com")
		.build();
		mapper.join(user);
        
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
    void checkIdTest() {
    	String id = "4070010";
    	assertEquals(1, mapper.idCheck(id));
        
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 입력 했을 시 그 회원의 비밀번호가 리턴되어야 한다.")
    void loginTest() {
    	String id = "abc1234";
    	assertNotNull(mapper.login(id));
        
    }
    
    @Test
    @DisplayName("존재하지 않는 회원의 아이디를 전달하면 null이 올 것이다.")
    void getInfoTest() {
    	String id = "메롱";
    	assertNull(mapper.getInfo(id));
    	
        
    }
    
    @Test
    @DisplayName("id를 제외한 회원의 정보를 수정할 수 있어야 한다.")
    void updateTest() {
        User user = User.builder()
        		.userId("4070010")
        		.userPw("aaa111!")
        		.userName("asdsadsa")
        		.userEmail1("abvasdasd")
        		.userEmail2("gmail.com")
        		.addrBasic("서울시 강동구")
        		.addrDetail("숭헝로")
        		.addrZipNum("04273")
        		.build();
        mapper.updateUser(user);
        assertEquals(user.getUserEmail1(),mapper.getInfo("4070010").getUserEmail1());
    }	
}
