package cn.ezs.fta.controller;

import cn.ezs.fta.annotation.TokenRequired;
import cn.ezs.fta.pojo.Application;
import cn.ezs.fta.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>fta</h3>
 * <p>申请相关接口</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-12 11:32
 **/
@Controller
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    /**
     * 发起一次申请
     * @param application application
     * @return 提升信息
     */
    @TokenRequired
    @PutMapping(value = "/addApplication",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> addApplication( @RequestBody Application application){
        Map<String,String> message=new HashMap<>(1);
        try {
            //前端穿来的值，没有时间这些信息
            application.setApplicationDate(new Date());
            applicationService.addNewApplication(application);
            message.put("message","申请提交成功！");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put("message","服务端出现未知错误！");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
        }
    }

    /**
     * 用户查看自己的申请
     * @param username 用户名
     * @return ListApplicatino
     */
    @TokenRequired
    @GetMapping(value = "/getMyApplications/{username}",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Application>> getMyApplication(@PathVariable String username){
        try {
            List<Application> applicationByUserName = applicationService.getApplicationByUserName(username);
            return ResponseEntity.status(HttpStatus.OK).body(applicationByUserName);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 获得所有通过了的申请
     * @return List
     */
    @TokenRequired
    @GetMapping(value = "/getAllPassApplication",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Application>> getPassApplication(){
        try {
            List<Application> allPassApplication = applicationService.getAllPassApplication();
            return  ResponseEntity.status(HttpStatus.OK).body(allPassApplication);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 获取所有申请
     * @return List
     */
    @TokenRequired
    @GetMapping(value = "/getAllApplication",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Application>> getAllApplication(){
        try {
            List<Application> allApplication = applicationService.getAllApplication();
            return ResponseEntity.status(HttpStatus.OK).body(allApplication);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 获取所有没有通过的申请
     * @return List
     */
    @TokenRequired
    @GetMapping(value = "/getNoPassApplication",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Application>> getNoPassApplication(){
        try {
            List<Application> noPassApplication = applicationService.getNoPassApplication();
            return ResponseEntity.status(HttpStatus.OK).body(noPassApplication);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 管理员审核
     * @param passChar 审核标志 p代表通过，n代表不通过
     * @param aid 申请id
     * @return Map message
     */
    @TokenRequired
    @PutMapping(value = "/audit/{passChar}/{aid}",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> auditApplication(@PathVariable("passChar") char passChar,@PathVariable("aid") String aid){
        Map<String,String> message=new HashMap<>(1);
        try {
            applicationService.audit(aid,passChar);
            message.put("message","审核结果提交成功");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put("message","服务端出现未知错误");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
        }
    }
}
