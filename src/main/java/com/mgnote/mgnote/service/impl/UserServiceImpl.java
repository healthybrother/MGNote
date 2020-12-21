package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.CommonException;
import com.mgnote.mgnote.exception.EntityAlreadyExistException;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.BriefUser;
import com.mgnote.mgnote.model.Directory;
import com.mgnote.mgnote.model.User;
import com.mgnote.mgnote.repository.DirectoryRepository;
import com.mgnote.mgnote.repository.UserRepository;
import com.mgnote.mgnote.service.DirectoryService;
import com.mgnote.mgnote.service.UserService;
import com.mgnote.mgnote.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private DirectoryRepository directoryRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, DirectoryRepository directoryRepository){
        this.userRepository = userRepository;
        this.directoryRepository = directoryRepository;
    }

    //todo 要不要做个邮箱登录
    @Override
    public User loginByName(String userName, String password) {
        Preconditions.checkNotNull(userName, password,"未输入用户名", "未输入用户密码");
        Optional<User> opt = userRepository.findByUserName(userName);//这个后面实现
        if(!opt.isPresent())throw new EntityNotExistException("符合用户名的用户不存在");
        User toLogin = opt.get();
        if(toLogin.getPassword().equals(password)){
            return toLogin;
        }throw new CommonException(401,"密码错误");
    }

    @Override
    public User loginByMail(String mail, String password) {
        Preconditions.checkNotNull(mail, password,"未输入用户名", "未输入用户密码");
        Optional<User> opt = userRepository.findByMail(mail);//这个后面实现
        if(!opt.isPresent())throw new EntityNotExistException("使用该邮箱注册的用户不存在");
        User toLogin = opt.get();
        if(toLogin.getPassword().equals(password)){
            return toLogin;
        }throw new CommonException(401,"密码错误");
    }

    @Override
    public String addUser(User user) {
        Preconditions.checkNotNull(user, "未输入用户信息");
        String inputUserName = user.getUserName();
        String inputMail = user.getMail();
        Optional<User> opt1 = userRepository.findByUserName(inputUserName);
        Optional<User> opt2 = userRepository.findByMail(inputMail);
        if(opt1.isPresent())throw new EntityAlreadyExistException("用户名已存在");
        if(opt2.isPresent())throw new EntityAlreadyExistException("邮箱已被注册");
        String id = UUID.randomUUID().toString();
        user.setId(id);
        EntityUtil.copyProperties(new User(), user, true);
        userRepository.save(user);
        Directory root = new Directory();
        root.setUserId(id);
        root.setId(UUID.randomUUID().toString());
        directoryRepository.save(root);
        return id;
    }

    @Override
    public User getUserById(String userId) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new EntityNotExistException("符合id的用户不存在");
    }

    @Override
    public User getUserByMail(String mail) {
        Preconditions.checkNotNull(mail, "未输入用户邮箱");
        Optional<User> opt = userRepository.findByMail(mail);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new EntityNotExistException("符合邮箱的用户不存在");
    }

    @Override
    public User getUserByName(String userName) {
        Preconditions.checkNotNull(userName, "未输入用户名");
        Optional<User> opt = userRepository.findByUserName(userName);//这个后面实现
        if(opt.isPresent()){
            return opt.get();
        }
        throw new EntityNotExistException("符合用户名的用户不存在");
    }

    @Override
    public void addFocus(String userId, String otherId) {
        Preconditions.checkNotNull(userId, otherId, "未输入用户id", "未输入关注者");
        Optional<User> opt1 = userRepository.findById(userId);
        Optional<User> opt2 = userRepository.findById(otherId);
        if(!opt1.isPresent() || !opt2.isPresent()){
            throw new EntityNotExistException("用户不存在");
        }
        User focus = opt1.get();
        BriefUser briefFoused = new BriefUser(opt2.get());
        List<BriefUser> friends = focus.getFriends();
        if(friends.contains(briefFoused))throw new EntityAlreadyExistException("已关注该用户");//已关注则直接跳出，应该抛出异常
        friends.add(briefFoused);
        focus.setFriends(friends);
        userRepository.save(focus);
    }

    @Override
    public void updateUser(String userId, User user) {
        Preconditions.checkNotNull(user, userId,"未输入关注者", "未输入用户id");
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            user.setId(userId);
            User updated = EntityUtil.copyProperties(user, opt.get(), true);
            userRepository.save(updated);
            return;
        }throw new EntityNotExistException("符合用户名的用户不存在");
    }
}
