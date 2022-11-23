package net.ecnu.controller;

import lombok.extern.slf4j.Slf4j;
import net.ecnu.util.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthTestController {

    @RequestMapping("/admin")
    public JsonData testAuthURL(){
        return JsonData.buildSuccess("用户可以调通该接口");
    }

}
