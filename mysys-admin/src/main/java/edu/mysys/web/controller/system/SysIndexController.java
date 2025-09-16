package edu.mysys.web.controller.system;

import edu.mysys.common.config.MySysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysIndexController {
    @Autowired
    private MySysConfig mySysConfig;
    @RequestMapping("/")
    public String index(){
        return "欢迎使用 MySys 管理系统";
    }
}
