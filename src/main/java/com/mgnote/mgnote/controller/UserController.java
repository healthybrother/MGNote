package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.service.NoteContentService;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.service.UserService;
import com.mgnote.mgnote.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/user")

public class UserController {
    private UserService userService;
    private NoteService noteService;
    private NoteContentService noteContentService;

    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @ApiOperation("输入用户信息注册")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Successfully Sign Up"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> addUser(@ApiParam(value = "用户信息", required = true) @Validated @RequestBody User input){
        if(input != null){
            String id;
            id = userService.addUser(input);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("以用户名和密码登录")
    @RequestMapping(value = "/user/loginByName", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 203, message = "Successful Login"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> loginByName(@ApiParam(value = "登录信息", required = true)  @Validated @RequestBody String inputName, String inputPassword){
        if((inputName!=null) && (inputPassword!=null)){
            User login;
            login = userService.loginByName(inputName, inputPassword);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("以用邮箱和密码登录")
    @RequestMapping(value = "/user/loginByMail", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 203, message = "Successful Login"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> loginByMail(@ApiParam(value = "登录信息", required = true)  @Validated @RequestBody String inputMail, String inputPassword){
        if((inputMail!=null) && (inputPassword!=null)){
            User login;
            login = userService.loginByMail(inputMail, inputPassword);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按邮箱查找用户")
    @RequestMapping(value = "/user/queryByMail")
    public ResponseEntity<?> searchUserByMail(@ApiParam(value = "搜索邮箱", required = true) @RequestBody String inputMail){
        if(inputMail!=null){
            User search;
            search = userService.getUserByMail(inputMail);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按用户名查找用户")
    @RequestMapping(value = "/user/queryByName")
    public ResponseEntity<?> searchUserByName(@ApiParam(value = "搜索用户名", required = true) @RequestBody String inputName){
        if(inputName!=null){
            User search;
            search = userService.getUserByName(inputName);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按id查找用户")
    @RequestMapping(value = "/user/queryById")
    public ResponseEntity<?> searchUserById(@ApiParam(value = "搜索用户id", required = true) @RequestBody String inputId){
        if(inputId!=null){
            User search;
            search = userService.getUserById(inputId);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按用户id进行关注行为")
    @RequestMapping(value = "/user/focus")
    public ResponseEntity<?> focusPeople(@ApiParam(value = "关注用户", required = true)@RequestBody String userId, String focusId){
        if((userId!=null)&&(focusId!=null)){
            userService.addFocus(userId, focusId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按id更新用户信息")
    @RequestMapping(value = "/user/update")
    public ResponseEntity<?> updateUser(@ApiParam(value = "更新用户信息",required = true)@RequestBody String userId, User updated){
        if((userId!=null)&&(updated!=null)){
            userService.updateUser(userId, updated);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
