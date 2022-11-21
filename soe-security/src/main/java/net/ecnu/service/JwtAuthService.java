package net.ecnu.service;

import net.ecnu.constant.RolesConst;
import net.ecnu.enums.BizCodeEnum;
import net.ecnu.exception.BizException;
import net.ecnu.mapper.MyUserDetailsServiceMapper;
import net.ecnu.model.LoginUser;
import net.ecnu.model.UserDO;
import net.ecnu.model.UserReq;
import net.ecnu.util.IDUtil;
import net.ecnu.util.JsonData;
import net.ecnu.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Objects;

public class JwtAuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    private JwtAuthService(){}

    public JwtAuthService(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 登录认证换取JWT令牌
     */
    public String login(LoginUser loginUser,String password){
        try {
            String username = loginUser.getPhone();
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new BizException(BizCodeEnum.USER_INPUT_ERROR.getCode(),BizCodeEnum.USER_INPUT_ERROR.getMessage());
        }
        return jwtTokenUtil.geneJsonWebToken(loginUser);
    }

    /**
     * 刷新token令牌
     */
    public String refreshToken(String token){
        //获取token中的LoginUser对象
        LoginUser loginUser = jwtTokenUtil.checkJWT(token);
        if(Objects.isNull(loginUser)){
            throw new BizException(BizCodeEnum.TOEKN_EXCEPTION.getCode(),BizCodeEnum.TOEKN_EXCEPTION.getMessage());
        }
        return jwtTokenUtil.geneJsonWebToken(loginUser);
    }

    /**
     * 用户注册，无需返回token令牌
     */
    public JsonData register(UserReq userReq){
        /**
         * 短信认证
         * TODO
         */
        //查询系统中是否已存在手机号相同的账户
        Integer isExists = myUserDetailsServiceMapper.checkUserByPhone(userReq.getPhone());
        if(isExists>0){
            return JsonData.buildCodeAndMsg(BizCodeEnum.USER_ALREADY_EXISTS.getCode(),BizCodeEnum.USER_ALREADY_EXISTS.getMessage());
        }else{
            //新增用户入库，客户端新增用户默认角色为学生
            UserDO newUserDO = new UserDO();
            //雪花算法生成用户唯一识别码
            String accountNo = "user_" + IDUtil.getSnowflakeId();
            newUserDO.setAccountNo(accountNo);
            newUserDO.setPhone(userReq.getPhone());
            userReq.setPwd(passwordEncoder.encode(userReq.getPwd()));
            //新增用户进user表
            myUserDetailsServiceMapper.insert(newUserDO);
            //给新增用户赋予默认角色
            myUserDetailsServiceMapper.insertUserRole(RolesConst.DEFAULT_ROLE,accountNo);
            return JsonData.buildSuccess("注册成功");
        }
    }
}
