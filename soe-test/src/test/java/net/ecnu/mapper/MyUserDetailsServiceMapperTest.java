package net.ecnu.mapper;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.ecnu.model.LoginUser;
import net.ecnu.model.MyUserDetails;
import net.ecnu.utils.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserDetailsServiceMapperTest{

    @Autowired
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String mobile = "13576845354";
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUserName(mobile);
        log.info("myUserDetails = {}", JSON.toJSON(myUserDetails));
    }

    @Test
    public void test02(){
        String mobile = "13576845354";
        List<String> roleByUserName = myUserDetailsServiceMapper.findRoleByUserName(mobile);
        String roles = roleByUserName.stream().collect(Collectors.joining(","));
        log.info("该用户具备的角色roles为：{}",JSON.toJSON(roles));
    }

    @Test
    public void test03(){
        String roleCode = "SUPER_ADMIN";
        List<String> apiByRoleCode = myUserDetailsServiceMapper.findApiByRoleCode(roleCode);
        String collect = apiByRoleCode.stream().collect(Collectors.joining(","));
        log.info("该用户可访问的接口为：{}",JSON.toJSON(collect));
    }

    @Test
    public void test04(){
        /*LoginUser loginUser = new LoginUser();
        loginUser.setAccountNo("user_13576845354");
        loginUser.setPhone("13576845354");
        loginUser.setRealName("Joshua");
        loginUser.setNickName("小煜");
        loginUser.setMail("1765947424@qq.com");*/
        String password = passwordEncoder.encode("shaoyu12");
        System.out.println("password = " + password);

    }

}
