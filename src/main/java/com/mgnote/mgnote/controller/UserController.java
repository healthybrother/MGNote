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
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;
    private NoteService noteService;
    private NoteContentService noteContentService;

    @Autowired
    public UserController(UserService userService, NoteService noteService, NoteContentService noteContentService){
        this.userService = userService;
        this.noteService = noteService;
        this.noteContentService = noteContentService;
    }

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
    @RequestMapping(value = "/loginByName", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 203, message = "Successful Login"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> loginByName(@ApiParam(value = "登录信息", required = true)  @Validated @RequestBody User user){
        if(user!=null){
            User login;
            login = userService.loginByName(user.getUserName(), user.getPassword());
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("以用邮箱和密码登录")
    @RequestMapping(value = "/loginByMail", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 203, message = "Successful Login"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> loginByMail(@ApiParam(value = "登录信息", required = true)  @Validated @RequestBody User user){
        if(user!=null){
            User login;
            login = userService.loginByMail(user.getMail(), user.getPassword());
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按邮箱查找用户")
    @RequestMapping(value = "/queryByMail", method = RequestMethod.GET)
    public ResponseEntity<?> searchUserByMail(@ApiParam(value = "搜索邮箱", required = true) @RequestParam String inputMail){
        if(inputMail!=null){
            User search;
            search = userService.getUserByMail(inputMail);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按用户名查找用户")
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public ResponseEntity<?> searchUserByName(@ApiParam(value = "搜索用户名", required = true) @RequestParam String inputName){
        if(inputName!=null){
            User search;
            search = userService.getUserByName(inputName);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按id查找用户")
    @RequestMapping(value = "/queryById/{inputId}", method = RequestMethod.GET)
    public ResponseEntity<?> searchUserById(@ApiParam(value = "搜索用户id", required = true) @PathVariable String inputId){
        if(inputId!=null){
            User search;
            search = userService.getUserById(inputId);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按用户id进行关注行为")
    @RequestMapping(value = "/focus", method = RequestMethod.PUT)
    public ResponseEntity<?> focusPeople(@ApiParam(value = "关注用户", required = true)@RequestParam String userId, @RequestParam String focusId){
        if((userId!=null)&&(focusId!=null)){
            userService.addFocus(userId, focusId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("按id更新用户信息")
    @RequestMapping(value = "/update/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@ApiParam(value = "更新用户信息",required = true)@PathVariable String userId, @RequestBody User updated){
        if((userId!=null)&&(updated!=null)){
            userService.updateUser(userId, updated);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
