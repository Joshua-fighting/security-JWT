package net.ecnu.controller;

import net.ecnu.enums.BizCodeEnum;
import net.ecnu.exception.BizException;
import net.ecnu.model.JwtProperties;
import net.ecnu.model.LoginUser;
import net.ecnu.model.UserReq;
import net.ecnu.service.JwtAuthService;
import net.ecnu.util.JsonData;
import net.ecnu.utils.JWTConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@ConditionalOnBean({JwtAuthService.class})
@ConditionalOnProperty(name = "soe.jwt.useDefaultController",havingValue = "true")
public class JwtAuthController {

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtAuthService jwtAuthService;

    /**
     * 用户登录统一接口
     * @param userReq
     * @return 返回该账号对应的token值
     */
    @PostMapping(JWTConstants.LOGIN)
    public JsonData login(@RequestBody UserReq userReq){
        String username = userReq.getPhone();
        String password = userReq.getPwd();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return JsonData.buildCodeAndMsg(BizCodeEnum.PARAM_IS_EMPTY.getCode(),BizCodeEnum.PARAM_IS_EMPTY.getMessage());
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userReq,loginUser);
        return JsonData.buildSuccess(jwtAuthService.login(loginUser,password));
    }

    /**
     * 支持用户在登录后定时刷新JWT令牌
     */
    @RequestMapping(JWTConstants.REFRESH_TOKEN)
    public JsonData refresh(@RequestHeader("${soe.jwt.header}")String token){
        try{
            String newToken = jwtAuthService.refreshToken(token);
            return JsonData.buildSuccess(newToken);
        }catch (BizException e){
            return JsonData.buildError(e.getMsg());
        }
    }

    /**
     * 用户注册接口
     */
    @PostMapping(JWTConstants.REGISTER)
    public JsonData register(@RequestBody @Validated UserReq userReq){
        //非空判断，暂时不做短信验证码的非空校验
        if(Objects.isNull(userReq)||StringUtils.isBlank(userReq.getPhone())||
            StringUtils.isBlank(userReq.getPwd())){
            return JsonData.buildCodeAndMsg(BizCodeEnum.PARAM_IS_EMPTY.getCode(),BizCodeEnum.PARAM_IS_EMPTY.getMessage());
        }
        return jwtAuthService.register(userReq);
    }


}
